package com.pg;

import java.io.Serializable;

public class BulletData implements Serializable {
 
	//子弹的坐标
	private int bulletX, bulletY;
	//子弹的速度
	private int speed;
	//子弹的种类以及常量
	private int bulletType;

	//子弹是否超屏， 优化处理
	private boolean isDead;

	//Boss疯狂状态下子弹相关成员变量
	private int dir;//当前Boss子弹方向
  

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
