package com.pg;

import java.io.Serializable;
 

public class MySurfaceViewData implements Serializable {
	//ÿ�����ɵл���ʱ��(����)
	private int createEnemyTime = 50;
	private int count;//������
	//�������飺1��2��ʾ�л������࣬-1��ʾBoss

	//��ǰȡ��һά������±�
	private int enemyArrayIndex;
	//�Ƿ����Boss��ʶλ
	private boolean isBoss;
	//����ӵ��ļ�����
	private int countEnemyBullet;
	private int score;

	public int getCreateEnemyTime() {
		return createEnemyTime;
	}
	public void setCreateEnemyTime(int createEnemyTime) {
		this.createEnemyTime = createEnemyTime;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getEnemyArrayIndex() {
		return enemyArrayIndex;
	}
	public void setEnemyArrayIndex(int enemyArrayIndex) {
		this.enemyArrayIndex = enemyArrayIndex;
	}
	public boolean isBoss() {
		return isBoss;
	}
	public void setBoss(boolean isBoss) {
		this.isBoss = isBoss;
	}
	public int getCountEnemyBullet() {
		return countEnemyBullet;
	}
	public void setCountEnemyBullet(int countEnemyBullet) {
		this.countEnemyBullet = countEnemyBullet;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

}
