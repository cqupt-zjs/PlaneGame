package com.pg;

import java.io.Serializable;
 

public class BossData implements Serializable{
	//Boss��Ѫ��
	public int hp = 50;
 
	//Boss����
	public int x, y;
	//Bossÿ֡�Ŀ��
	public int frameW, frameH;
	//Boss��ǰ֡�±�
	private int frameIndex;
	//Boss�˶����ٶ�
	private int speed = 5;
	//Boss���˶��켣
	//һ��ʱ���������Ļ�·��˶������ҷ����Χ�ӵ������Ƿ��̬��
	//����״̬�� ���ӵ���ֱ�����˶�
	private boolean isCrazy;
	//������״̬��״̬ʱ����
	private int crazyTime = 200;
	//������
	private int count;
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
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
	public boolean isCrazy() {
		return isCrazy;
	}
	public void setCrazy(boolean isCrazy) {
		this.isCrazy = isCrazy;
	}
	public int getCrazyTime() {
		return crazyTime;
	}
	public void setCrazyTime(int crazyTime) {
		this.crazyTime = crazyTime;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
