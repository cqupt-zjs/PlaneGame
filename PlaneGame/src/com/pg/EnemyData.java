package com.pg;

import java.io.Serializable;


public class EnemyData implements Serializable{
	//敌机的种类标识
	public int type;

	//敌机坐标
	public int x, y;
	//敌机每帧的宽高
	public int frameW, frameH;
	//敌机当前帧下标
	private int frameIndex;
	//敌机的移动速度
	private int speed;
	//判断敌机是否已经出屏
	public boolean isDead;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getFrameW() {
		return frameW;
	}
	public void setFrameW(int frameW) {
		this.frameW = frameW;
	}
	public int getFrameH() {
		return frameH;
	}
	public void setFrameH(int frameH) {
		this.frameH = frameH;
	}
	public int getFrameIndex() {
		return frameIndex;
	}
	public void setFrameIndex(int frameIndex) {
		this.frameIndex = frameIndex;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public boolean isDead() {
		return isDead;
	}
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
}
