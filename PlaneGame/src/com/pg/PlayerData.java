package com.pg;

import java.io.Serializable;


public class PlayerData implements Serializable{
	//Ĭ��3Ѫ
	private int playerHp = 3;
	//���ǵ����� 
	private int x, y;

	//�����ƶ��ٶ�
	private int speed = 5;
	//�����ƶ���ʶ�������½��ѽ��⣬�㶮�ã�
	private boolean isUp, isDown, isLeft, isRight;
	//��ײ�����޵�ʱ��
	//��ʱ��
	private int noCollisionCount = 0;
	//��Ϊ�޵�ʱ��
	private int noCollisionTime = 60;
	//�Ƿ���ײ�ı�ʶλ
	private boolean isCollision;
	
	public int getPlayerHp() {
		return playerHp;
	}
	public void setPlayerHp(int playerHp) {
		this.playerHp = playerHp;
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
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public boolean isUp() {
		return isUp;
	}
	public void setUp(boolean isUp) {
		this.isUp = isUp;
	}
	public boolean isDown() {
		return isDown;
	}
	public void setDown(boolean isDown) {
		this.isDown = isDown;
	}
	public boolean isLeft() {
		return isLeft;
	}
	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}
	public boolean isRight() {
		return isRight;
	}
	public void setRight(boolean isRight) {
		this.isRight = isRight;
	}
	public int getNoCollisionCount() {
		return noCollisionCount;
	}
	public void setNoCollisionCount(int noCollisionCount) {
		this.noCollisionCount = noCollisionCount;
	}
	public int getNoCollisionTime() {
		return noCollisionTime;
	}
	public void setNoCollisionTime(int noCollisionTime) {
		this.noCollisionTime = noCollisionTime;
	}
	public boolean isCollision() {
		return isCollision;
	}
	public void setCollision(boolean isCollision) {
		this.isCollision = isCollision;
	}
	
	
}
