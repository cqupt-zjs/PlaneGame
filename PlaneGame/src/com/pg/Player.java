package com.pg;

import java.io.Serializable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class Player implements Serializable {
	// ���ǵ�Ѫ����Ѫ��λͼ
	// Ĭ��3Ѫ
	private int playerHp = 3;
	private int score;
	private Bitmap bmpPlayerHp;
	// ���ǵ������Լ�λͼ
	public int x, y;
	private Bitmap bmpPlayer;
	// �����ƶ��ٶ�
	private int speed = 5;
	// �����ƶ���ʶ�������½��ѽ��⣬�㶮�ã�
	private boolean isUp, isDown, isLeft, isRight;
	// ��ײ�����޵�ʱ��
	// ��ʱ��
	private int noCollisionCount = 0;
	// ��Ϊ�޵�ʱ��
	private int noCollisionTime = 60;
	// �Ƿ���ײ�ı�ʶλ
	private boolean isCollision;
	//private b

	// ���ǵĹ��캯��
	public Player(Bitmap bmpPlayer, Bitmap bmpPlayerHp, int score) {
		this.score = score;
		this.bmpPlayer = bmpPlayer;
		this.bmpPlayerHp = bmpPlayerHp;
		x = MySurfaceView.screenW / 2 - bmpPlayer.getWidth() / 2;
		y = MySurfaceView.screenH - bmpPlayer.getHeight();
	}

	// ���ǵĻ�ͼ����
	public void draw(Canvas canvas, Paint paint) {
		// ��������
		// �������޵�ʱ��ʱ����������˸

		if (isCollision) {
			// ÿ2����Ϸѭ��������һ������
			if (noCollisionCount % 2 == 0) {
				canvas.drawBitmap(bmpPlayer, x, y, paint);
			}
		} else {
			canvas.drawBitmap(bmpPlayer, x, y, paint);
			
		}
		// ��������Ѫ��
		for (int i = 0; i < playerHp; i++) {
			canvas.drawBitmap(bmpPlayerHp, i * bmpPlayerHp.getWidth(),
					MySurfaceView.screenH - bmpPlayerHp.getHeight(), paint);
		}
	}

	public Bitmap getBmpPlayer() {
		return bmpPlayer;
	}

	// ʵ�尴��
	public void onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
			isUp = true;
		}
		if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
			isDown = true;
		}
		if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
			isLeft = true;
		}
		if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
			isRight = true;
		}
	}

	public void onTouchEvent(MotionEvent event) {
		x = (int) event.getX()-bmpPlayer.getWidth()/2;
		y = (int) event.getY()-bmpPlayer.getHeight();
	}

	// ʵ�尴��̧��
	public void onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
			isUp = false;
		}
		if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
			isDown = false;
		}
		if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
			isLeft = false;
		}
		if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
			isRight = false;
		}
	}

	// ���ǵ��߼�
	public void logic() {
		// ���������ƶ�
		if (isLeft) {
			x -= speed;
		}
		if (isRight) {
			x += speed;
		}
		if (isUp) {
			y -= speed;
		}
		if (isDown) {
			y += speed;
		}
		// �ж���ĻX�߽�
		if (x + bmpPlayer.getWidth() >= MySurfaceView.screenW) {
			x = MySurfaceView.screenW - bmpPlayer.getWidth();
		} else if (x <= 0) {
			x = 0;
		}
		// �ж���ĻY�߽�
		if (y + bmpPlayer.getHeight() >= MySurfaceView.screenH) {
			y = MySurfaceView.screenH - bmpPlayer.getHeight();
		} else if (y <= 0) {
			y = 0;
		}
		// �����޵�״̬
		if (isCollision) {
			// ��ʱ����ʼ��ʱ
			noCollisionCount++;
			if (noCollisionCount >= noCollisionTime) {
				// �޵�ʱ����󣬽Ӵ��޵�״̬����ʼ��������
				isCollision = false;
				noCollisionCount = 0;
			}
		}
	}

	// ��������Ѫ��
	public void setPlayerHp(int hp) {
		this.playerHp = hp;
	}

	// ��ȡ����Ѫ��
	public int getPlayerHp() {
		return playerHp;
	}

	// �ж���ײ(������л�)
	public boolean isCollsionWith(Enemy en) {
		// �Ƿ����޵�ʱ��
		if (isCollision == false) {
			int x2 = en.x;
			int y2 = en.y;
			int w2 = en.frameW;
			int h2 = en.frameH;
			if (x >= x2 && x >= x2 + w2) {
				return false;
			} else if (x <= x2 && x + bmpPlayer.getWidth() <= x2) {
				return false;
			} else if (y >= y2 && y >= y2 + h2) {
				return false;
			} else if (y <= y2 && y + bmpPlayer.getHeight() <= y2) {
				return false;
			}
			// ��ײ�������޵�״̬
			isCollision = true;
			return true;
			// �����޵�״̬��������ײ
		} else {
			return false;
		}
	}

	// �ж���ײ(������л��ӵ�)
	public boolean isCollsionWith(Bullet bullet) {
		// �Ƿ����޵�ʱ��
		if (isCollision == false) {
			int x2 = bullet.bulletX;
			int y2 = bullet.bulletY;
			int w2 = bullet.bmpBullet.getWidth();
			int h2 = bullet.bmpBullet.getHeight();
			if (x >= x2 && x >= x2 + w2) {
				return false;
			} else if (x <= x2 && x + bmpPlayer.getWidth() <= x2) {
				return false;
			} else if (y >= y2 && y >= y2 + h2) {
				return false;
			} else if (y <= y2 && y + bmpPlayer.getHeight() <= y2) {
				return false;
			}
			// ��ײ�������޵�״̬
			isCollision = true;
			return true;
			// �����޵�״̬��������ײ
		} else {
			return false;
		}
	}

	public PlayerData getPlayerData() {
		PlayerData pd = new PlayerData();
		pd.setCollision(isCollision);
		pd.setDown(isDown);
		pd.setLeft(isLeft);
		pd.setNoCollisionCount(noCollisionCount);
		pd.setNoCollisionTime(noCollisionTime);
		pd.setPlayerHp(playerHp);
		pd.setRight(isRight);
		pd.setSpeed(speed);
		pd.setUp(isUp);
		pd.setX(x);
		pd.setY(y);

		return pd;
	}

	public void setPlayerData(PlayerData pd) {
		this.isCollision = pd.isCollision();
		this.isDown = pd.isDown();
		this.isLeft = pd.isLeft();
		this.isRight = pd.isRight();
		this.isUp = pd.isUp();
		this.playerHp = pd.getPlayerHp();
		this.x = pd.getX();
		this.y = pd.getY();
		this.noCollisionCount = pd.getNoCollisionCount();
		this.noCollisionTime = pd.getNoCollisionTime();
		this.speed = pd.getSpeed();
	}
}
