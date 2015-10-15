package com.pg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Random;
import java.util.Vector;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements Callback, Runnable {
	private SurfaceHolder sfh;
	private Paint paint;
	private Thread th;
	private boolean flag;
	private Canvas canvas;
	public static int screenW, screenH;
	// ������Ϸ״̬����
	public static final int GAME_MENU = 0;// ��Ϸ�˵�
	public static final int GAMEING = 1;// ��Ϸ��
	public static final int GAME_WIN = 2;// ��Ϸʤ��
	public static final int GAME_LOST = 3;// ��Ϸʧ��
	public static final int GAME_PAUSE = -1;// ��Ϸ�˵�
	public static boolean gameGoon = false;// ������Ϸ

	public static boolean newGame = true;
	// ��ǰ��Ϸ״̬(Ĭ�ϳ�ʼ����Ϸ�˵�����)
	public static int gameState = GAME_MENU;
	// ����һ��Resourcesʵ�����ڼ���ͼƬ
	private Resources res = this.getResources();
	// ������Ϸ��Ҫ�õ���ͼƬ��Դ(ͼƬ����)
	private Bitmap stop_bg;//��Ϸ��ɺ���ʾ�����ı���
	private Bitmap bmpBackGround;// ��Ϸ����
	private Bitmap bmpBoom;// ��ըЧ��
	private Bitmap bmpBoosBoom;// Boos��ըЧ��
	private Bitmap bmpButton;// ��Ϸ��ʼ��ť
	private Bitmap bmpButtonPress;// ��Ϸ��ʼ��ť�����
	private Bitmap bmpEnemyDuck;// ����Ѽ��
	private Bitmap bmpEnemyFly;// �����Ӭ
	private Bitmap bmpEnemyBoos;// ������ͷBoos
	private Bitmap bmpGameWin;// ��Ϸʤ������
	private Bitmap bmpGameLost;// ��Ϸʧ�ܱ���
	private Bitmap bmpPlayer;// ��Ϸ���Ƿɻ�
	private Bitmap bmpPlayerHp;// ���Ƿɻ�Ѫ��
	private Bitmap bmpGameGoon;// ������Ϸ
	private Bitmap bmpGameGoonPress;// ������Ϸ
	private Bitmap bmpMenu;// �˵�����
	private Bitmap bmpPause;// ��ͣ
	private Bitmap bmpGoon;// ����
	private int countHp = 0; // ��������
	private int score = 0;
	public static Bitmap bmpBullet;// �ӵ�
	public static Bitmap bmpEnemyBullet;// �л��ӵ�
	public static Bitmap bmpBossBullet;// Boss�ӵ�
	private StopScore stop_score;
	// ����һ���˵�����
	private GameMenuStart gameMenu;
	private GameMenuGoon gameMenuGoon;
	// ����һ��������Ϸ��������
	private GameBg backGround;
	// �������Ƕ���
	private Player player;
	private Hp hp;
	// ����һ���л�����
	private Vector<Enemy> vcEnemy;
	// ����һ����ͣ ����
	private PauseOrGoOn pause;
	// ÿ�����ɵл���ʱ��(����)
	private int createEnemyTime = 50;
	private int count;// ������
	// �������飺1��2��ʾ�л������࣬-1��ʾBoss
	// ��ά�����ÿһά����һ�����
	private int enemyArray[][] = { { 1, 2 }, { 1, 1 }, { 1, 3, 1, 2 },
			{ 1, 2 }, { 2, 3 }, { 3, 1, 3 }, { 2, 2 }, { 1, 2 }, { 2, 2 },
			{ 1, 3, 1, 1 }, { 2, 1 }, { 1, 3 }, { 2, 1 }, { -1 } };
	// ��ǰȡ��һά������±�
	private int enemyArrayIndex;
	// �Ƿ����Boss��ʶλ
	private boolean isBoss;
	// ����⣬Ϊ�����ĵл������漴����
	private Random random;
	// �л��ӵ�����
	private Vector<Bullet> vcBullet;
	// ����ӵ��ļ�����
	private int countEnemyBullet;
	// �����ӵ�����
	private Vector<Bullet> vcBulletPlayer;
	// ����ӵ��ļ�����
	private int countPlayerBullet;
	// ��ըЧ������
	private Vector<Boom> vcBoom;
	// ����Boss
	private Boss boss;
	// Boss���ӵ�����
	public static Vector<Bullet> vcBulletBoss;

	/**
	 * SurfaceView��ʼ������
	 */
	public MySurfaceView(Context context) {
		super(context);
		sfh = this.getHolder();
		sfh.addCallback(this);
		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setAntiAlias(true);
		// ��ȡ����
		setFocusable(true);
		setFocusableInTouchMode(true);
		// ���ñ�������
		this.setKeepScreenOn(true);
	}

	/**
	 * SurfaceView��ͼ��������Ӧ�˺���
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		screenW = this.getWidth();
		screenH = this.getHeight();
		initGame();
		flag = true;
		// ʵ���߳�
		th = new Thread(this);
		// �����߳�
		th.start();
	}

	/*
	 * �Զ������Ϸ��ʼ������
	 */
	private void initGame() {
		// ������Ϸ�����̨���½�����Ϸʱ����Ϸ������!
		// ����Ϸ״̬���ڲ˵�ʱ���Ż�������Ϸ
		if (gameState == GAME_MENU) {
						// ������Ϸ��Դ
			bmpBackGround = BitmapFactory.decodeResource(res,
					R.drawable.background);
			bmpBoom = BitmapFactory.decodeResource(res, R.drawable.boom);
			bmpBoosBoom = BitmapFactory.decodeResource(res,
					R.drawable.boos_boom);
			bmpButton = BitmapFactory.decodeResource(res, R.drawable.button);
			bmpButtonPress = BitmapFactory.decodeResource(res,
					R.drawable.button_press);
			bmpEnemyDuck = BitmapFactory.decodeResource(res,
					R.drawable.enemy_duck);
			bmpEnemyFly = BitmapFactory.decodeResource(res,
					R.drawable.enemy_fly);
			bmpEnemyBoos = BitmapFactory.decodeResource(res,
					R.drawable.enemy_pig);
			bmpGameWin = BitmapFactory.decodeResource(res, R.drawable.gamewin);
			bmpGameLost = BitmapFactory
					.decodeResource(res, R.drawable.gamelost);
			bmpPlayer = BitmapFactory.decodeResource(res, R.drawable.player);
			bmpPlayerHp = BitmapFactory.decodeResource(res, R.drawable.hp);
			bmpMenu = BitmapFactory.decodeResource(res, R.drawable.menu);
			bmpBullet = BitmapFactory.decodeResource(res, R.drawable.bullet);
			bmpEnemyBullet = BitmapFactory.decodeResource(res,
					R.drawable.bullet_enemy);
			bmpBossBullet = BitmapFactory.decodeResource(res,
					R.drawable.boosbullet);
			bmpPause = BitmapFactory.decodeResource(getResources(),
					R.drawable.pause);
			bmpGoon = BitmapFactory.decodeResource(getResources(),
					R.drawable.goon);
			bmpGameGoon = BitmapFactory.decodeResource(getResources(),
					R.drawable.jixuyouxi);
			bmpGameGoonPress = BitmapFactory.decodeResource(getResources(),
					R.drawable.jixuyouxipress);
			stop_bg=BitmapFactory.decodeResource(getResources(), R.drawable.stop_bg);

			reInitData();
		}
	}

	public void reInitData() {
		createEnemyTime = 50;
		score=0;
		

		// ��ǰȡ��һά������±�
		enemyArrayIndex = 0;
		// �Ƿ����Boss��ʶλ
		isBoss = false;
		// ����ӵ��ļ�����
		countEnemyBullet = 0;
		// ��ըЧ������ʵ��
		vcBoom = new Vector<Boom>();
		// �л��ӵ�����ʵ��
		vcBullet = new Vector<Bullet>();
		// �����ӵ�����ʵ��
		vcBulletPlayer = new Vector<Bullet>();
		// ʵ���л�����
		vcEnemy = new Vector<Enemy>();
		// ʵ��Boss�ӵ�����
		vcBulletBoss = new Vector<Bullet>();
		stop_score=new StopScore(score);

		// �˵���ʵ��
		gameMenu = new GameMenuStart(bmpMenu, bmpButton, bmpButtonPress);
		gameMenuGoon = new GameMenuGoon(bmpGameGoon, bmpGameGoonPress);
		// ʵ����Ϸ����
		backGround = new GameBg(bmpBackGround);
		// ʵ������
		player = new Player(bmpPlayer, bmpPlayerHp, score);
		// ����
		hp = new Hp(bmpPlayerHp, player);
		// ʵ�������
		random = new Random();
		// ---Boss���
		// ʵ��boss����
		boss = new Boss(bmpEnemyBoos);

		pause = new PauseOrGoOn(bmpPause, bmpGoon, PauseOrGoOn.PAUSE);
	}

	/**
	 * ��Ϸ��ͼ
	 */
	public void myDraw() {
		try {
			canvas = sfh.lockCanvas();
			if (canvas != null) {
				canvas.drawColor(Color.WHITE);
				// ��ͼ����������Ϸ״̬��ͬ���в�ͬ����
				switch (gameState) {
				case GAME_MENU:
					// �˵��Ļ�ͼ����
					gameMenu.draw(canvas, paint);
					gameMenuGoon.draw(canvas, paint);
					break;
				case GAMEING:
					// ��Ϸ����
					backGround.draw(canvas, paint);
					pause.draw(canvas, paint);
					// ���ǻ�ͼ����
					player.draw(canvas, paint);
					canvas.save();
					canvas.scale(2, 2, MySurfaceView.screenW - 140, 20);
					canvas.drawText("�÷֣�" + score,
							MySurfaceView.screenW - 140, 20, paint);
					canvas.restore();
					hp.draw(canvas, paint);
					hp.logic();

					if (isBoss == false) {
						// �л�����
						for (int i = 0; i < vcEnemy.size(); i++) {
							vcEnemy.elementAt(i).draw(canvas, paint);
						}
						// �л��ӵ�����
						for (int i = 0; i < vcBullet.size(); i++) {
							vcBullet.elementAt(i).draw(canvas, paint);
						}
					} else {
						// Boos�Ļ���f
						boss.draw(canvas, paint);
						// Boss�ӵ��߼�
						for (int i = 0; i < vcBulletBoss.size(); i++) {
							vcBulletBoss.elementAt(i).draw(canvas, paint);
						}
					}
					// ���������ӵ�����
					for (int i = 0; i < vcBulletPlayer.size(); i++) {
						vcBulletPlayer.elementAt(i).draw(canvas, paint);
					}
					// ��ըЧ������
					for (int i = 0; i < vcBoom.size(); i++) {
						vcBoom.elementAt(i).draw(canvas, paint);
						score += 10;
					}
					break;
				case GAME_PAUSE:
					
					break;
				case GAME_WIN:
					canvas.save();
					float sx = (float) (MySurfaceView.screenW)
							/ bmpGameWin.getWidth();
					float sy = (float) (MySurfaceView.screenH)
							/ bmpGameWin.getHeight();
					canvas.scale(sx, sy);
					canvas.drawBitmap(bmpGameWin, 0, 0, paint);
					canvas.restore();
					stop_score.logic(score);
					stop_score.drawscore(canvas, paint);
					break;
				case GAME_LOST:
					canvas.save();
					sx = (float) (MySurfaceView.screenW)
							/ bmpGameLost.getWidth();
					sy = (float) (MySurfaceView.screenH)
							/ bmpGameLost.getHeight();
					canvas.scale(sx, sy);
					canvas.drawBitmap(bmpGameLost, 0, 0, paint);
					canvas.restore();
					stop_score.logic(score);
					stop_score.drawscore(canvas, paint);
				
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (canvas != null)
				sfh.unlockCanvasAndPost(canvas);
		}
	}

	/**
	 * �����¼�����
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// ���������¼�����������Ϸ״̬��ͬ���в�ͬ����
		switch (gameState) {
		case GAME_MENU:
			// �˵��Ĵ����¼�����
			gameMenu.onTouchEvent(event);
			gameMenuGoon.onTouchEvent(event);
			break;
		case GAMEING:
			if (pause.onTouchEvent(event) == false) {
				if (pause.getType() == PauseOrGoOn.GOON) {
					return false;
				}
				player.onTouchEvent(event);
				hp.onTouchEvent(event);
			}
			break;
		case GAME_PAUSE:
			break;
		case GAME_WIN:
			break;
		case GAME_LOST:

			break;
		}
		return true;
	}

	/**
	 * ���������¼�����
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// ����back���ذ���
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// ��Ϸʤ����ʧ�ܡ�����ʱ��Ĭ�Ϸ��ز˵�
			if (gameState == GAMEING || gameState == GAME_WIN
					|| gameState == GAME_LOST) {
				if (gameState == GAMEING) {
					System.out.println("--------������Ϸ����-----------");
					pause.setType(PauseOrGoOn.PAUSE);
					// ������Ϸ
					try {
						saveGame();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					System.out.println("--------�����Ϸ����-----------");
					clearData();
				}
				gameState = GAME_MENU;
			} else if (gameState == GAME_MENU) {
				// ��ǰ��Ϸ״̬�ڲ˵����棬Ĭ�Ϸ��ذ����˳���Ϸ
				MainActivity.instance.finish();
				System.exit(0);
			}
			// ��ʾ�˰����Ѵ������ٽ���ϵͳ����
			// �Ӷ�������Ϸ�������̨
			return true;
		}
		// ���������¼�����������Ϸ״̬��ͬ���в�ͬ����
		switch (gameState) {
		case GAME_MENU:
			break;
		case GAMEING:
			// ���ǵİ��������¼�
			player.onKeyDown(keyCode, event);
			break;
		case GAME_PAUSE:
			break;
		case GAME_WIN:
			break;
		case GAME_LOST:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	// ������Ϸ
	private void saveGame() throws Exception {
		// ���汬ըЧ������ʵ��
		ObjectOutputStream oos = new ObjectOutputStream(
				MainActivity.instance.openFileOutput("vcboom",
						Context.MODE_PRIVATE));
		try {
			for (Boom boom : vcBoom) {
				oos.writeObject(boom.getBoomData());
				oos.flush();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oos.close();

		// ����л��ӵ�����ʵ��
		oos = new ObjectOutputStream(MainActivity.instance.openFileOutput(
				"vcbullet", Context.MODE_PRIVATE));
		try {
			for (Bullet bullet : vcBullet) {
				oos.writeObject(bullet.getBulletData());
				oos.flush();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oos.close();

		// ���������ӵ�����ʵ��
		oos = new ObjectOutputStream(MainActivity.instance.openFileOutput(
				"vcbulletplayer", Context.MODE_PRIVATE));
		try {
			for (Bullet bullet : vcBulletPlayer) {
				oos.writeObject(bullet.getBulletData());
				oos.flush();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oos.close();

		// ����ʵ���л�����
		oos = new ObjectOutputStream(MainActivity.instance.openFileOutput(
				"vcenemy", Context.MODE_PRIVATE));
		try {
			for (Enemy enemy : vcEnemy) {
				oos.writeObject(enemy.getEnemyData());
				oos.flush();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oos.close();

		// ����ʵ��Boss�ӵ�����
		oos = new ObjectOutputStream(MainActivity.instance.openFileOutput(
				"vcbulletboss", Context.MODE_PRIVATE));
		try {
			for (Bullet bullet : vcBulletBoss) {
				oos.writeObject(bullet.getBulletData());
				oos.flush();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oos.close();

		// ����player
		oos = new ObjectOutputStream(MainActivity.instance.openFileOutput(
				"player", Context.MODE_PRIVATE));
		PlayerData pd = player.getPlayerData();
		oos.writeObject(pd);
		oos.close();

		// ����boss
		oos = new ObjectOutputStream(MainActivity.instance.openFileOutput(
				"boss", Context.MODE_PRIVATE));
		BossData bd = boss.getBossData();
		oos.writeObject(bd);
		oos.close();

		// ����MySurfaceView����
		oos = new ObjectOutputStream(MainActivity.instance.openFileOutput(
				"mysurfaceview", Context.MODE_PRIVATE));
		MySurfaceViewData msvd = getMySurfaceViewData();
		oos.writeObject(msvd);
		oos.close();
		
		oos = new ObjectOutputStream(MainActivity.instance.openFileOutput(
				"hp", Context.MODE_PRIVATE));
		HpData hd=hp.getHpData();
		oos.writeObject(hd);
		oos.close();
	}

	// ������Ϸ ��ȡ���������
	private void getData() throws Exception {
		// ���汬ըЧ������ʵ��
		ObjectInputStream ois = new ObjectInputStream(
				MainActivity.instance.openFileInput("vcboom"));
		while (true) {
			try {
				BoomData bd = (BoomData) ois.readObject();
				Boom boom = new Boom(bmpBoom);
				boom.setBoomData(bd);
				vcBoom.add(boom);
			} catch (Exception e) {
				break;
			}
		}
		ois.close();

		// ����л��ӵ�����ʵ��
		ois = new ObjectInputStream(
				MainActivity.instance.openFileInput("vcbullet"));
		while (true) {
			try {
				BulletData bd = (BulletData) ois.readObject();
				Bullet bullet = new Bullet(bmpEnemyBullet);
				bullet.setBulletData(bd);
				vcBullet.add(bullet);
			} catch (Exception e) {
				break;
			}
		}
		ois.close();

		// ���������ӵ�����ʵ��
		ois = new ObjectInputStream(
				MainActivity.instance.openFileInput("vcbulletplayer"));
		while (true) {
			try {
				BulletData bd = (BulletData) ois.readObject();
				Bullet bullet = new Bullet(bmpBullet);
				bullet.setBulletData(bd);
				vcBulletPlayer.add(bullet);
			} catch (Exception e) {
				break;
			}
		}
		ois.close();

		// ����ʵ���л�����
		ois = new ObjectInputStream(
				MainActivity.instance.openFileInput("vcenemy"));
		while (true) {
			try {
				EnemyData ed = (EnemyData) ois.readObject();
				Enemy e = new Enemy();
				e.setEnemyData(ed);
				if (ed.getType() == Enemy.TYPE_FLY) {
					e.setBmpEnemy(bmpEnemyFly);
				} else if (ed.getType() == Enemy.TYPE_DUCKL
						|| ed.getType() == Enemy.TYPE_DUCKR) {
					e.setBmpEnemy(bmpEnemyDuck);
				}
				vcEnemy.add(e);
			} catch (Exception e) {
				break;
			}
		}
		ois.close();

		// ����ʵ��Boss�ӵ�����
		ois = new ObjectInputStream(
				MainActivity.instance.openFileInput("vcbulletboss"));
		while (true) {
			try {
				BulletData bd = (BulletData) ois.readObject();
				Bullet bullet = new Bullet(bmpBossBullet);
				bullet.setBulletData(bd);
				vcBulletBoss.add(bullet);
			} catch (Exception e) {
				break;
			}
		}
		ois.close();

		// ����player
		ois = new ObjectInputStream(
				MainActivity.instance.openFileInput("player"));
		PlayerData pd = (PlayerData) ois.readObject();
		player.setPlayerData(pd);
		ois.close();

		// ����boss
		ois = new ObjectInputStream(MainActivity.instance.openFileInput("boss"));
		BossData bd = (BossData) ois.readObject();
		boss.setBossData(bd);
		ois.close();

		// ����MySurfaceView����
		ois = new ObjectInputStream(
				MainActivity.instance.openFileInput("mysurfaceview"));
		MySurfaceViewData msvd = (MySurfaceViewData) ois.readObject();
		this.setMySurfaceViewData(msvd);
		ois.close();
		
		ois = new ObjectInputStream(
				MainActivity.instance.openFileInput("hp"));
		HpData hd=(HpData) ois.readObject();
		hp.setHpData(hd);
		ois.close();
	}

	public void clearData() {
		// �����Ϸ����
		try {
			OutputStream os = MainActivity.instance.openFileOutput("vcboom",
					Context.MODE_PRIVATE);
			os.write("".getBytes());
			os.close();

			os = MainActivity.instance.openFileOutput("vcbullet",
					Context.MODE_PRIVATE);
			os.write("".getBytes());
			os.close();

			os = MainActivity.instance.openFileOutput("vcbulletplayer",
					Context.MODE_PRIVATE);
			os.write("".getBytes());
			os.close();

			os = MainActivity.instance.openFileOutput("vcenemy",
					Context.MODE_PRIVATE);
			os.write("".getBytes());
			os.close();

			os = MainActivity.instance.openFileOutput("vcbulletboss",
					Context.MODE_PRIVATE);
			os.write("".getBytes());
			os.close();

			os = MainActivity.instance.openFileOutput("player",
					Context.MODE_PRIVATE);
			os.write("".getBytes());
			os.close();

			os = MainActivity.instance.openFileOutput("boss",
					Context.MODE_PRIVATE);
			os.write("".getBytes());
			os.close();

			os = MainActivity.instance.openFileOutput("mysurfaceview",
					Context.MODE_PRIVATE);
			os.write("".getBytes());
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ����̧���¼�����
	 */
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// ����back���ذ���
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// ��Ϸʤ����ʧ�ܡ�����ʱ��Ĭ�Ϸ��ز˵�
			if (gameState == GAMEING || gameState == GAME_WIN
					|| gameState == GAME_LOST) {
				gameState = GAME_MENU;
			}
			// ��ʾ�˰����Ѵ������ٽ���ϵͳ����
			// �Ӷ�������Ϸ�������̨
			return true;
		}
		// ���������¼�����������Ϸ״̬��ͬ���в�ͬ����
		switch (gameState) {
		case GAME_MENU:
			break;
		case GAMEING:
			// ����̧���¼�
			player.onKeyUp(keyCode, event);
			break;
		case GAME_PAUSE:
			break;
		case GAME_WIN:
			break;
		case GAME_LOST:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * ��Ϸ�߼�
	 */
	private void logic() {
		// �߼����������Ϸ״̬��ͬ���в�ͬ����
		switch (gameState) {
		case GAME_MENU:
			break;
		case GAMEING:
			// �����߼�
			backGround.logic();
			// �����߼�
			player.logic();
			// �л��߼�
			if (isBoss == false) {
				// �л��߼� ��boos ģʽ
				for (int i = 0; i < vcEnemy.size(); i++) {
					Enemy en = vcEnemy.elementAt(i);
					// ��Ϊ����������ӵл� ����ô�Եл�isDead�ж���
					// �����������ô�ʹ�������ɾ��,�����������Ż����ã�
					if (en.isDead) {
						vcEnemy.removeElementAt(i);
					} else {
						en.logic();
					}
				}
				// ���ɵл�
				count++;
				if (count % createEnemyTime == 0) {
					for (int i = 0; i < enemyArray[enemyArrayIndex].length; i++) {
						// ��Ӭ
						if (enemyArray[enemyArrayIndex][i] == 1) {
							int x = random.nextInt(screenW - 100) + 50;
							vcEnemy.addElement(new Enemy(bmpEnemyFly, 1, x, -50));
							// Ѽ����
						} else if (enemyArray[enemyArrayIndex][i] == 2) {
							int y = random.nextInt(20);
							vcEnemy.addElement(new Enemy(bmpEnemyDuck, 2, -50,
									y));
							// Ѽ����
						} else if (enemyArray[enemyArrayIndex][i] == 3) {
							int y = random.nextInt(20);
							vcEnemy.addElement(new Enemy(bmpEnemyDuck, 3,
									screenW + 50, y));
						}
					}
					// �����ж���һ���Ƿ�Ϊ���һ��(Boss)
					if (enemyArrayIndex == enemyArray.length - 1) {
						isBoss = true;
					} else {
						enemyArrayIndex++;
					}
				}
				// ����л������ǵ���ײ
				for (int i = 0; i < vcEnemy.size(); i++) {
					if (player.isCollsionWith(vcEnemy.elementAt(i))) {
						// ������ײ������Ѫ��-1
						player.setPlayerHp(player.getPlayerHp() - 1);
						// ������Ѫ��С��0���ж���Ϸʧ��
						if (player.getPlayerHp() <= -1) {
							gameState = GAME_LOST;
						}
					}
				}
				// ÿ2�����һ���л��ӵ�
				countEnemyBullet++;
				if (countEnemyBullet % 40 == 0) {
					for (int i = 0; i < vcEnemy.size(); i++) {
						Enemy en = vcEnemy.elementAt(i);
						// ��ͬ���͵л���ͬ���ӵ����й켣
						int bulletType = 0;
						switch (en.type) {
						// ��Ӭ
						case Enemy.TYPE_FLY:
							bulletType = Bullet.BULLET_FLY;
							break;
						// Ѽ��
						case Enemy.TYPE_DUCKL:
						case Enemy.TYPE_DUCKR:
							bulletType = Bullet.BULLET_DUCK;
							break;
						}
						vcBullet.add(new Bullet(bmpEnemyBullet, en.x + 10,
								en.y + 20, bulletType));
					}
				}
				// ����л��ӵ��߼�
				for (int i = 0; i < vcBullet.size(); i++) {
					Bullet b = vcBullet.elementAt(i);
					if (b.isDead) {
						vcBullet.removeElement(b);
					} else {
						b.logic();
					}
				}
				// ����л��ӵ���������ײ
				for (int i = 0; i < vcBullet.size(); i++) {
					if (player.isCollsionWith(vcBullet.elementAt(i))) {
						// ������ײ������Ѫ��-1
						player.setPlayerHp(player.getPlayerHp() - 1);
						// ������Ѫ��С��0���ж���Ϸʧ��
						if (player.getPlayerHp() <= -1) {
							gameState = GAME_LOST;
						}
					}
				}
				// ���������ӵ���л���ײ
				for (int i = 0; i < vcBulletPlayer.size(); i++) {
					// ȡ�������ӵ�������ÿ��Ԫ��
					Bullet blPlayer = vcBulletPlayer.elementAt(i);
					for (int j = 0; j < vcEnemy.size(); j++) {
						// ��ӱ�ըЧ��
						// ȡ���л�������ÿ��Ԫ�������ӵ������ж�
						if (vcEnemy.elementAt(j).isCollsionWith(blPlayer)) {
							vcBoom.add(new Boom(bmpBoom,
									vcEnemy.elementAt(j).x, vcEnemy
											.elementAt(j).y, 7));
						}
					}
				}
			} else {// Boss����߼�
				// ÿ0.5�����һ�������ӵ�
				boss.logic();
				if (countPlayerBullet % 10 == 0) {
					// Boss��û����֮ǰ����ͨ�ӵ�
					vcBulletBoss.add(new Bullet(bmpBossBullet, boss.x + 35,
							boss.y + 40, Bullet.BULLET_FLY));
				}
				// Boss�ӵ��߼�
				for (int i = 0; i < vcBulletBoss.size(); i++) {
					Bullet b = vcBulletBoss.elementAt(i);
					if (b.isDead) {
						vcBulletBoss.removeElement(b);
					} else {
						b.logic();
					}
				}
				// Boss�ӵ������ǵ���ײ
				for (int i = 0; i < vcBulletBoss.size(); i++) {
					if (player.isCollsionWith(vcBulletBoss.elementAt(i))) {
						// ������ײ������Ѫ��-1
						player.setPlayerHp(player.getPlayerHp() - 1);
						// ������Ѫ��С��0���ж���Ϸʧ��
						if (player.getPlayerHp() <= -1) {
							gameState = GAME_LOST;
						}
					}
				}
				// Boss�������ӵ����У�������ըЧ��
				for (int i = 0; i < vcBulletPlayer.size(); i++) {
					Bullet b = vcBulletPlayer.elementAt(i);
					if (boss.isCollsionWith(b)) {
						if (boss.hp <= 0) {
							// ��Ϸʤ��
							gameState = GAME_WIN;
						} else {
							// ��ʱɾ��������ײ���ӵ�����ֹ�ظ��ж����ӵ���Boss��ײ��
							b.isDead = true;
							// BossѪ����1
							boss.setHp(boss.hp - 1);
							// ��Boss���������Boss��ըЧ��
							vcBoom.add(new Boom(bmpBoosBoom, boss.x + 25,
									boss.y + 30, 5));
							vcBoom.add(new Boom(bmpBoosBoom, boss.x + 35,
									boss.y + 40, 5));
							vcBoom.add(new Boom(bmpBoosBoom, boss.x + 45,
									boss.y + 50, 5));
						}
					}
				}
			}
			// ÿ1/1000*50*5�����һ�������ӵ�
			countPlayerBullet++;
			if (countPlayerBullet % 5 == 0) {
				vcBulletPlayer.add(new Bullet(bmpBullet, player.x + 15,
						player.y - 20, Bullet.BULLET_PLAYER));
			}
			// ���������ӵ��߼�
			for (int i = 0; i < vcBulletPlayer.size(); i++) {
				Bullet b = vcBulletPlayer.elementAt(i);
				if (b.isDead) {
					if (gameState == GAMEING) {
						vcBulletPlayer.removeElement(b);
					}
				} else {
					b.logic();
				}
			}
			// ��ըЧ���߼�
			for (int i = 0; i < vcBoom.size(); i++) {
				Boom boom = vcBoom.elementAt(i);
				if (boom.playEnd) {
					// ������ϵĴ�������ɾ��
					vcBoom.removeElementAt(i);
				} else {
					vcBoom.elementAt(i).logic();
				}
			}
			countHp++;
			if (countHp % (20 * 8) == 0) {
				hp.show();
			}
			break;
		case GAME_PAUSE:
			break;
		case GAME_WIN:
			break;
		case GAME_LOST:
			break;

		}
	}

	@Override
	public void run() {
		while (flag) {
			long start = System.currentTimeMillis();
			myDraw();
			logic();
			long end = System.currentTimeMillis();
			while (pause.getType() == PauseOrGoOn.GOON) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				if (end - start < 50) {
					Thread.sleep(50 - (end - start));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (gameGoon == true) {
				gameGoon = false;
				try {
					System.out.println("----------��ȡ��Ϸ����-----------");
					reInitData();
					getData();
				} catch (Exception e) {
					reInitData();
				}
			}
			if (newGame) {
				newGame = false;
				reInitData();
			}
		}
	}

	/**
	 * SurfaceView��ͼ״̬�����ı䣬��Ӧ�˺���
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	/**
	 * SurfaceView��ͼ����ʱ����Ӧ�˺���
	 */
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		flag = false;
	}

	public MySurfaceViewData getMySurfaceViewData() {
		MySurfaceViewData msvd = new MySurfaceViewData();
		msvd.setBoss(isBoss);
		msvd.setCount(count);
		msvd.setCountEnemyBullet(countEnemyBullet);
		msvd.setCreateEnemyTime(createEnemyTime);
		msvd.setEnemyArrayIndex(enemyArrayIndex);
		msvd.setScore(score);
		return msvd;
	}

	public void setMySurfaceViewData(MySurfaceViewData msvd) {
		this.isBoss = msvd.isBoss();
		this.count = msvd.getCount();
		this.countEnemyBullet = msvd.getCountEnemyBullet();
		this.createEnemyTime = msvd.getCreateEnemyTime();
		this.enemyArrayIndex = msvd.getEnemyArrayIndex();
		this.score=msvd.getScore();
	}
}
