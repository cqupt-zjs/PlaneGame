package com.pg;

import java.io.Serializable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
 
public class Enemy implements Serializable{
	//�л��������ʶ
	public int type;
	//��Ӭ
	public static final int TYPE_FLY = 1;
	//Ѽ��(���������˶�)
	public static final int TYPE_DUCKL = 2;
	//Ѽ��(���������˶�)
	public static final int TYPE_DUCKR = 3;
	//�л�ͼƬ��Դ
	public Bitmap bmpEnemy;
	//�л�����
	public int x, y;
	//�л�ÿ֡�Ŀ��
	public int frameW, frameH;
	//�л���ǰ֡�±�
	private int frameIndex;
	//�л����ƶ��ٶ�
	private int speed;
	//�жϵл��Ƿ��Ѿ�����
	public boolean isDead;

	//�л��Ĺ��캯��
	public Enemy() {
	}
	public Enemy(Bitmap bmpEnemy, int enemyType, int x, int y) {
		this.bmpEnemy = bmpEnemy;
		frameW = bmpEnemy.getWidth() / 10;
		frameH = bmpEnemy.getHeight();
		this.type = enemyType;
		this.x = x;
		this.y = y;
		//��ͬ����ĵл��ٶȲ�ͬ
		switch (type) {
		//��Ӭ
		case TYPE_FLY:
			speed = 25;
			break;
		//Ѽ��
		case TYPE_DUCKL:
			speed = 3;
			break;
		case TYPE_DUCKR:
			speed = 3;
			break;
		}
	}
	public void setBmpEnemy(Bitmap bmpEnemy){
		this.bmpEnemy=bmpEnemy;
	}
	//�л���ͼ����
	public void draw(Canvas canvas, Paint paint) {
		canvas.save();
		canvas.clipRect(x, y, x + frameW, y + frameH);
		canvas.drawBitmap(bmpEnemy, x - frameIndex * frameW, y, paint);
		canvas.restore();
	}

	//�л��߼�AI
	public void logic() {
		//����ѭ������֡�γɶ���
		frameIndex++;
		if (frameIndex >= 10) {
			frameIndex = 0;
		}
		//��ͬ����ĵл�ӵ�в�ͬ��AI�߼�
		switch (type) {
		case TYPE_FLY:
			if (isDead == false) {
				//���ٳ��֣����ٷ���
				speed -= 1;
				y += speed;
				if (y <= -200) {
					isDead = true;
				}
			}
			break;
		case TYPE_DUCKL:
			if (isDead == false) {
				//б���½��˶�
				x += speed / 2;
				y += speed;
				if (x > MySurfaceView.screenW) {
					isDead = true;
				}
			}
			break;
		case TYPE_DUCKR:
			if (isDead == false) {
				//б���½��˶�
				x -= speed / 2;
				y += speed;
				if (x < -50) {
					isDead = true;
				}
			}
			break;
		}
	}

	//�ж���ײ(�л��������ӵ���ײ)
	public boolean isCollsionWith(Bullet bullet) {
		int x2 = bullet.bulletX;
		int y2 = bullet.bulletY;
		int w2 = bullet.bmpBullet.getWidth();
		int h2 = bullet.bmpBullet.getHeight();
		if (x >= x2 && x >= x2 + w2) {
			return false;
		} else if (x <= x2 && x + frameW <= x2) {
			return false;
		} else if (y >= y2 && y >= y2 + h2) {
			return false;
		} else if (y <= y2 && y + frameH <= y2) {
			return false;
		}
		//������ײ����������
		isDead = true;
		return true;
	}
	
	public EnemyData getEnemyData(){
		EnemyData ed=new EnemyData();
		ed.setDead(isDead);
		ed.setFrameH(frameH);
		ed.setFrameW(frameW);
		ed.setFrameIndex(frameIndex);
		ed.setSpeed(speed);
		ed.setType(type);
		ed.setX(x);
		ed.setY(y);
		return ed;
	}
	
	public void setEnemyData(EnemyData ed){
		this.frameH=ed.getFrameH();
		this.frameW=ed.getFrameW();
		this.frameIndex=ed.getFrameIndex();
		this.isDead=ed.isDead();
		this.type=ed.getType();
		this.x=ed.getX();
		this.y=ed.getY();
		this.speed=ed.getSpeed();
	}
}
