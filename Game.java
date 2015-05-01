//�C���|�[�g
import gameCanvasUtil.*;

/** �Q�[���N���X�B
 *
 * �w�����ҏW���ׂ��\�[�X�R�[�h�ł��B
 */
public class Game extends GameBase
{
	/********* �ϐ���`�͂������ *********/
    // �^ �ϐ���;�@�̏��ɏ����Ē�`����
    // �v���C���[��X���W
    int player_x;
    // �v���C���[��Y���W
    int player_y;
    // �v���C���[�̕�
    int player_w;
    // �v���C���[�̍���
    int player_h;
	
	//	���H�������C��x���W
	int[] roadline_x=new int[10];
	//	���H�������C��x���W
	int[] roadline_y=new int[10];
	// ���H�������C���̕�
	int roadline_w;
	// ���H�������C���̍���
	int roadline_h;
	// ���H�������C���̑��x
	int roadline_speed;
	
	// �G��X���W
    int enemy_x;
    // �G��Y���W
    int enemy_y;
    // �G�̕�
    int enemy_w;
    // �G�̍���
    int enemy_h;
    // �G�̑��xx
    int enemy_speed_y;
	// �G�̑��xy
	int enemy_speed_x;
	
	// �S�̓G�ړ��̑��x
	int speed_add = 10;
	int speed_dev = -10;
    // ���Ԃ�\���ϐ�
    int time;
    // �ł��Ԃ����񐔁i�X�R�A�j
    int score;
    // ���܂łň�Ԃ悩�����X�R�A�i�n�C�X�R�A�j
    int high_score;
    // �Q�[���̏��
    int gameState;
	//�@���x��
	int level;
	//�@���x���ʉߕK�v�X�R�A
	int level_score;
	//�@�d���������L�[�̔��f
	int flag;
	
	
    /********* �������̎菇�͂������ *********/
    public void initGame()
    {
		// �v���C���[�̍��W������������
        player_x = 270;
        player_y = 350;
        // �v���C���[�̕��E����������������
        player_w = 50;
        player_h = 70;
		
		// ���H�������C���̍s�񐶐�����
		for(int i=0;i<10;i+=1){
			roadline_x[i]=320;
			roadline_y[i]=i*70;
		}
		// ���H�������C���̕��E�����E�X�s�[�h������������
		roadline_w = 10;
		roadline_h = 50;
		roadline_speed = 10;
		// �G�̔ԍ�
		// �G�̍��W������������
            enemy_x= 350;
            enemy_y = 0;
		// �G�̕��E����������������
        enemy_w = 60;
        enemy_h = 41;
        // �G�̑��x��������
        enemy_speed_y = speed_add;
		enemy_speed_x = speed_dev;
		
		// ���Ԃ�\���ϐ���������
        time = 0;
        //�@�X�R�A��������
        score = 0;
        //�@high score
        high_score = gc.load(0);
        //�Q�[���̏��
        gameState = 0;
		//�@�d���������L�[�̔��f��������
		flag=1;
		//�@���x����������
		level=1;
		//�@���x���ʉߕK�v�X�R�A��������
		level_score=150;
    }

    /********* ���̂̈ړ����̍X�V�����͂������ *********/
    public void updateGame()
    {
		switch(gameState){
		// �^�C�g�����
			case 0:
				// Enter�L�[�������ꂽ��Q�[���J�n
				if(gc.isKeyPushed(gc.KEY_ENTER)){
					// �Q�[����ʂɏ�Ԃ�ύX
					gameState = 1;
					// �Q�[���J�n�Ȃ̂ŁA�Q�[����ʂ̉��y��炷
					gc.playBGM(0);
				}
				flag=0;
			break;
			// �Q�[�����
			case 1: case 3: case 5: case 7:
				keypress();
				enemy();
				//	���H�������C���𑖂�
				road_line();
				if(score > high_score){
					high_score=score;
				}
				if(score < 800){
					if(score >= level_score){
						gc.stopBGM();
						level+=1;
						level_score*=2;
						gameState+=1;
						if(true){
						speed_add+=5;
						speed_dev-=5;
						break;
						}					}
				}
				// ���Ԃ����Z����
				time = time + 1;
			break;
			case 2: case 4: case 6:
				
				if(gc.isKeyPushed(gc.KEY_ENTER))
				{
				
                // �Q�[����ʂɏ�Ԃ�ύX
                gameState += 1;
                // �Q�[���J�n�Ȃ̂ŁA�Q�[����ʂ̉��y��炷
                gc.playBGM(0);
				}
			break;
				
			
		default:
		}
	}

    /********* �摜�̕`��͂������ *********/
    public void drawGame()
    {
		// ��ʂ𔒂œh��Ԃ��܂�
        gc.clearScreen();
        
        // �^�C�g�����
        switch(gameState){
		// �^�C�g����ʂ̕�����`��
			case 0:
				//BG��`��
				gc.setColor(192, 255, 62);
				gc.fillRect(0,0,640,480);
				//���H��`��
				gc.setColor(0,0,0);
				gc.fillRect(150,0,350,480);
				//���H�������C����`��
				for(int i=0;i<480;i++){
					gc.setColor(250,250,250);
					gc.fillRect(320,i,10,50);
					i+=70;
				}
				//������`��
				gc.setColor(205, 92, 92);
				gc.drawString("�i���̂t�e�n�I�I�I", 200, 150);
				gc.drawString("Press Enter" , 260, 250);
			break;
        // �Q�[�����
			case 1: case 3: case 5: case 7:
				//BG��`��
				gc.setColor(192, 255, 62);
				gc.fillRect(0,0,640,480);
				//���H��`�悷��
				gc.setColor(0,0,0);
				gc.fillRect(150,0,350,480);
				// ���H�������C����`�悷��for��
				for(int j=0; j<10; j++){
					// ���H�������C����`�悵�܂�
					gc.setColor(250,250,250);
					gc.fillRect(roadline_x[j],roadline_y[j],roadline_w,roadline_h);       
				}
				// �G��`�悵�܂�
				gc.drawImage(0, enemy_x, enemy_y);
				// �v���C���[��`�悵�܂�
				gc.drawImage(1, player_x, player_y);
				// �F�����ɃZ�b�g
				gc.setColor(220, 220, 220);
				// �X�R�A�ƃn�C�X�R�A��`��
				gc.drawString("Score:" + score, 0, 0);
				gc.drawString("HighScore:" + high_score, 0, 32);
			break;
			//���x���ϊ����
			case 2: case 4: case 6:
				if (level == 4){
					gc.setColor(220, 220, 220);
					gc.drawString("MAX Level", 280, 200);
					gc.drawString("Press Enter" , 260, 250);	
				}
				else{
					gc.setColor(220, 220, 220);
					gc.drawString("Level " + level, 280, 200);
					gc.drawString("Press Enter" , 260, 250);	
				}
			break;
			// �Q�[���I�[�o�[���
			case 88:
            gc.setColor(0,0,0);
            gc.drawString("GAME OVER", 260, 150);
			gc.drawString("RESULT",260,200);
            gc.drawString("Score:" + score,260,250);
            gc.drawString("HighScore:" + high_score,240,300);
			break;
		default:
		}
    }


	public void keypress(){
		// ENTER�L�[�������ꂽ��|�[�Y
        if(gc.isKeyPushed(gc.KEY_ENTER)){
			if(flag==1){
			//�G�����X�s�[�h�A�b�v
			speed_add+=5;
			speed_dev-=5;
			// ��ʂɏ�Ԃ�ύX
			gameState +=1;
			// �|�[�Y���͋Ȃ��~
			gc.stopBGM();
			}
        }
        // ���L�[�������ꑱ���Ă���ꍇ�ɐ^
        if(gc.isKeyPress(gc.KEY_LEFT)){
            if (player_x < 160){
				player_x = 160;
			}
            player_x = player_x - 8;
        }
        // �E�L�[�������ꑱ���Ă���ꍇ�ɐ^
        if(gc.isKeyPress(gc.KEY_RIGHT)){
            if (player_x > 440){
				player_x = 440;
			}
            player_x = player_x + 8;
        }
		// ��L�[�������ꑱ���Ă���ꍇ�ɐ^
        if(gc.isKeyPress(gc.KEY_UP)){
			if (player_y < 5){
				player_y = 5;
			}
			else player_y = player_y - 8;
        }
		// ���L�[�������ꑱ���Ă���ꍇ�ɐ^
		if(gc.isKeyPress(gc.KEY_DOWN)){
            if (player_y > 400){
				player_y = 400;
			}
             player_y = player_y + 8;
        }
	}
	//�G�̓��씻�f
	public void enemy(){
			// Y������ enemy_speed_y ���i�߂�
			// �G����ʂ̉����z�����ꍇ
			if(enemy_y > 450){
				enemy_speed_y = speed_dev;
				score += 10;
            }
			// �G����ʂ̏���z�����ꍇ
			else if(enemy_y < 0){
				enemy_speed_y = speed_add;
				score += 10;
			}
			// X������ enemy_speed_x ���i�߂�
            
			// �G����ʂ̉E���z�����ꍇ
			if(enemy_x > 470){
				enemy_speed_x = speed_dev;
				score += 10;
			}	
			// �G����ʂ̍����z�����ꍇ
			else if(enemy_x < 150){
				enemy_speed_x = speed_add;
				score += 10;
            }
			enemy_y += enemy_speed_y;
			enemy_x += enemy_speed_x;
			// �G�̍U���Ɏ󂯂��ꍇ
            if(gc.checkHitRect(enemy_x,enemy_y,enemy_w,enemy_h,player_x,player_y,player_w,player_h)){
                //�Q�[���I�[�o�[
                gameState = 88;
                //�Ȃ��~
                gc.stopBGM();
            }
	}
	//	���H�������C�����ォ�牺�𓮂�
	public void road_line(){
		for(int i=0; i<10; i++){
			roadline_y[i] += roadline_speed;
			if(roadline_y[i]>480){
				roadline_y[i] = 0;
			}
        }
	}
	    /********* �I�����̏����͂������ *********/
    public void finalGame() {
        // �n�C�X�R�A��0�ԂɃZ�[�u
        gc.save(0, high_score);
	}
}
