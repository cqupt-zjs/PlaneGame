package com.pg;

import java.io.Serializable;


public class EnemyData implements Serializable{
	//�л��������ʶ
	public int type;

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
