package com.pg;

import java.io.Serializable;
 
 
public class HpData implements Serializable{
  
	//���ǵ������Լ�λͼ
	private int x, y;
	//�������� 
	private int leftTime=-1;
	private boolean isDead=false;
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
	public int getLeftTime() {
		return leftTime;
	}
	public void setLeftTime(int leftTime) {
		this.leftTime = leftTime;
	}
	public boolean isDead() {
		return isDead;
	}
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
  
}
