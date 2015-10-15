package com.pg;

import java.io.Serializable;
 

public class MySurfaceViewData implements Serializable {
	//每次生成敌机的时间(毫秒)
	private int createEnemyTime = 50;
	private int count;//计数器
	//敌人数组：1和2表示敌机的种类，-1表示Boss

	//当前取出一维数组的下标
	private int enemyArrayIndex;
	//是否出现Boss标识位
	private boolean isBoss;
	//添加子弹的计数器
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
