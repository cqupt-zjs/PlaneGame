package com.pg;

import java.io.Serializable;

public class BoomData implements Serializable {

	private int score;
	// ��ըЧ����λ������
	private int boomX, boomY;
	// ��ը�������ŵ�ǰ��֡�±�
	private int cureentFrameIndex;
	// ��ըЧ������֡��
	private int totleFrame;
	// ÿ֡�Ŀ��
	private int frameW, frameH;
	// �Ƿ񲥷���ϣ��Ż�����
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
