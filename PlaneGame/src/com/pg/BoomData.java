package com.pg;

import java.io.Serializable;

public class BoomData implements Serializable {

	private int score;
	// 爆炸效果的位置坐标
	private int boomX, boomY;
	// 爆炸动画播放当前的帧下标
	private int cureentFrameIndex;
	// 爆炸效果的总帧数
	private int totleFrame;
	// 每帧的宽高
	private int frameW, frameH;
	// 是否播放完毕，优化处理
	public boolean playEnd;
	private int boomcount;

	public int getBoomX() {
		return boomX;
	}

	public void setBoomX(int boomX) {
		this.boomX = boomX;
	}
	public int getscore() {
		return score;
	}

	public void setscore(int score) {
		this.score = score;
	}
	public int getboomcount() {
		return boomcount;
	}

	public void setboomcount(int boomcount) {
		this.boomcount = boomcount;
	}

	public int getBoomY() {
		return boomY;
	}

	public void setBoomY(int boomY) {
		this.boomY = boomY;
	}

	public int getCureentFrameIndex() {
		return cureentFrameIndex;
	}

	public void setCureentFrameIndex(int cureentFrameIndex) {
		this.cureentFrameIndex = cureentFrameIndex;
	}

	public int getTotleFrame() {
		return totleFrame;
	}

	public void setTotleFrame(int totleFrame) {
		this.totleFrame = totleFrame;
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

	public boolean isPlayEnd() {
		return playEnd;
	}

	public void setPlayEnd(boolean playEnd) {
		this.playEnd = playEnd;
	}

}
