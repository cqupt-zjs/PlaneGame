package com.pg;

import java.io.Serializable;
 

public class BossData implements Serializable{
	//Boss的血量
	public int hp = 50;
 
	//Boss坐标
	public int x, y;
	//Boss每帧的宽高
	public int frameW, frameH;
	//Boss当前帧下标
	private int frameIndex;
	//Boss运动的速度
	private int speed = 5;
	//Boss的运动轨迹
	//一定时间会向着屏幕下方运动，并且发射大范围子弹，（是否狂态）
	//正常状态下 ，子弹垂直朝下运动
	private boolean isCrazy;
	//进入疯狂状态的状态时间间隔
	private int crazyTime = 200;
	//计数器
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
