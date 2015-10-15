package com.pg;

import java.io.Serializable;

public class BulletData implements Serializable {
 
	//�ӵ�������
	private int bulletX, bulletY;
	//�ӵ����ٶ�
	private int speed;
	//�ӵ��������Լ�����
	private int bulletType;

	//�ӵ��Ƿ����� �Ż�����
	private boolean isDead;

	//Boss���״̬���ӵ���س�Ա����
	private int dir;//��ǰBoss�ӵ�����
  

	public int getBulletX() {
		return bulletX;
	}

	public void setBulletX(int bulletX) {
		this.bulletX = bulletX;
	}

	public int getBulletY() {
		return bulletY;
	}

	public void setBulletY(int bulletY) {
		this.bulletY = bulletY;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getBulletType() {
		return bulletType;
	}

	public void setBulletType(int bulletType) {
		this.bulletType = bulletType;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

}
