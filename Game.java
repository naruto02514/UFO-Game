//インポート
import gameCanvasUtil.*;

/** ゲームクラス。
 *
 * 学生が編集すべきソースコードです。
 */
public class Game extends GameBase
{
	/********* 変数定義はこちらに *********/
    // 型 変数名;　の順に書いて定義する
    // プレイヤーのX座標
    int player_x;
    // プレイヤーのY座標
    int player_y;
    // プレイヤーの幅
    int player_w;
    // プレイヤーの高さ
    int player_h;
	
	//	道路中央ラインx座標
	int[] roadline_x=new int[10];
	//	道路中央ラインx座標
	int[] roadline_y=new int[10];
	// 道路中央ラインの幅
	int roadline_w;
	// 道路中央ラインの高さ
	int roadline_h;
	// 道路中央ラインの速度
	int roadline_speed;
	
	// 敵のX座標
    int enemy_x;
    // 敵のY座標
    int enemy_y;
    // 敵の幅
    int enemy_w;
    // 敵の高さ
    int enemy_h;
    // 敵の速度x
    int enemy_speed_y;
	// 敵の速度y
	int enemy_speed_x;
	
	// 全体敵移動の速度
	int speed_add = 10;
	int speed_dev = -10;
    // 時間を表す変数
    int time;
    // 打ち返した回数（スコア）
    int score;
    // 今までで一番よかったスコア（ハイスコア）
    int high_score;
    // ゲームの状態
    int gameState;
	//　レベル
	int level;
	//　レベル通過必要スコア
	int level_score;
	//　Ｅｎｔｅｒキーの判断
	int flag;
	
	
    /********* 初期化の手順はこちらに *********/
    public void initGame()
    {
		// プレイヤーの座標を初期化する
        player_x = 270;
        player_y = 350;
        // プレイヤーの幅・高さを初期化する
        player_w = 50;
        player_h = 70;
		
		// 道路中央ラインの行列生成する
		for(int i=0;i<10;i+=1){
			roadline_x[i]=320;
			roadline_y[i]=i*70;
		}
		// 道路中央ラインの幅・高さ・スピードを初期化する
		roadline_w = 10;
		roadline_h = 50;
		roadline_speed = 10;
		// 敵の番号
		// 敵の座標を初期化する
            enemy_x= 350;
            enemy_y = 0;
		// 敵の幅・高さを初期化する
        enemy_w = 60;
        enemy_h = 41;
        // 敵の速度を初期化
        enemy_speed_y = speed_add;
		enemy_speed_x = speed_dev;
		
		// 時間を表す変数を初期化
        time = 0;
        //　スコアを初期化
        score = 0;
        //　high score
        high_score = gc.load(0);
        //ゲームの状態
        gameState = 0;
		//　Ｅｎｔｅｒキーの判断を初期化
		flag=1;
		//　レベルを初期化
		level=1;
		//　レベル通過必要スコアを初期化
		level_score=150;
    }

    /********* 物体の移動等の更新処理はこちらに *********/
    public void updateGame()
    {
		switch(gameState){
		// タイトル画面
			case 0:
				// Enterキーが押されたらゲーム開始
				if(gc.isKeyPushed(gc.KEY_ENTER)){
					// ゲーム画面に状態を変更
					gameState = 1;
					// ゲーム開始なので、ゲーム画面の音楽を鳴らす
					gc.playBGM(0);
				}
				flag=0;
			break;
			// ゲーム画面
			case 1: case 3: case 5: case 7:
				keypress();
				enemy();
				//	道路中央ラインを走る
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
				// 時間を加算する
				time = time + 1;
			break;
			case 2: case 4: case 6:
				
				if(gc.isKeyPushed(gc.KEY_ENTER))
				{
				
                // ゲーム画面に状態を変更
                gameState += 1;
                // ゲーム開始なので、ゲーム画面の音楽を鳴らす
                gc.playBGM(0);
				}
			break;
				
			
		default:
		}
	}

    /********* 画像の描画はこちらに *********/
    public void drawGame()
    {
		// 画面を白で塗りつぶします
        gc.clearScreen();
        
        // タイトル画面
        switch(gameState){
		// タイトル画面の文字を描画
			case 0:
				//BGを描画
				gc.setColor(192, 255, 62);
				gc.fillRect(0,0,640,480);
				//道路を描画
				gc.setColor(0,0,0);
				gc.fillRect(150,0,350,480);
				//道路中央ラインを描画
				for(int i=0;i<480;i++){
					gc.setColor(250,250,250);
					gc.fillRect(320,i,10,50);
					i+=70;
				}
				//文字を描画
				gc.setColor(205, 92, 92);
				gc.drawString("進撃のＵＦＯ！！！", 200, 150);
				gc.drawString("Press Enter" , 260, 250);
			break;
        // ゲーム画面
			case 1: case 3: case 5: case 7:
				//BGを描画
				gc.setColor(192, 255, 62);
				gc.fillRect(0,0,640,480);
				//道路を描画する
				gc.setColor(0,0,0);
				gc.fillRect(150,0,350,480);
				// 道路中央ラインを描画するfor文
				for(int j=0; j<10; j++){
					// 道路中央ラインを描画します
					gc.setColor(250,250,250);
					gc.fillRect(roadline_x[j],roadline_y[j],roadline_w,roadline_h);       
				}
				// 敵を描画します
				gc.drawImage(0, enemy_x, enemy_y);
				// プレイヤーを描画します
				gc.drawImage(1, player_x, player_y);
				// 色を黒にセット
				gc.setColor(220, 220, 220);
				// スコアとハイスコアを描画
				gc.drawString("Score:" + score, 0, 0);
				gc.drawString("HighScore:" + high_score, 0, 32);
			break;
			//レベル変換画面
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
			// ゲームオーバー画面
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
		// ENTERキーが押されたらポーズ
        if(gc.isKeyPushed(gc.KEY_ENTER)){
			if(flag==1){
			//敵動きスピードアップ
			speed_add+=5;
			speed_dev-=5;
			// 画面に状態を変更
			gameState +=1;
			// ポーズ中は曲を停止
			gc.stopBGM();
			}
        }
        // 左キーが押され続けている場合に真
        if(gc.isKeyPress(gc.KEY_LEFT)){
            if (player_x < 160){
				player_x = 160;
			}
            player_x = player_x - 8;
        }
        // 右キーが押され続けている場合に真
        if(gc.isKeyPress(gc.KEY_RIGHT)){
            if (player_x > 440){
				player_x = 440;
			}
            player_x = player_x + 8;
        }
		// 上キーが押され続けている場合に真
        if(gc.isKeyPress(gc.KEY_UP)){
			if (player_y < 5){
				player_y = 5;
			}
			else player_y = player_y - 8;
        }
		// 下キーが押され続けている場合に真
		if(gc.isKeyPress(gc.KEY_DOWN)){
            if (player_y > 400){
				player_y = 400;
			}
             player_y = player_y + 8;
        }
	}
	//敵の動作判断
	public void enemy(){
			// Y方向に enemy_speed_y ずつ進める
			// 敵が画面の下を越えた場合
			if(enemy_y > 450){
				enemy_speed_y = speed_dev;
				score += 10;
            }
			// 敵が画面の上を越えた場合
			else if(enemy_y < 0){
				enemy_speed_y = speed_add;
				score += 10;
			}
			// X方向に enemy_speed_x ずつ進める
            
			// 敵が画面の右を越えた場合
			if(enemy_x > 470){
				enemy_speed_x = speed_dev;
				score += 10;
			}	
			// 敵が画面の左を越えた場合
			else if(enemy_x < 150){
				enemy_speed_x = speed_add;
				score += 10;
            }
			enemy_y += enemy_speed_y;
			enemy_x += enemy_speed_x;
			// 敵の攻撃に受けた場合
            if(gc.checkHitRect(enemy_x,enemy_y,enemy_w,enemy_h,player_x,player_y,player_w,player_h)){
                //ゲームオーバー
                gameState = 88;
                //曲を停止
                gc.stopBGM();
            }
	}
	//	道路中央ラインを上から下を動く
	public void road_line(){
		for(int i=0; i<10; i++){
			roadline_y[i] += roadline_speed;
			if(roadline_y[i]>480){
				roadline_y[i] = 0;
			}
        }
	}
	    /********* 終了時の処理はこちらに *********/
    public void finalGame() {
        // ハイスコアを0番にセーブ
        gc.save(0, high_score);
	}
}
