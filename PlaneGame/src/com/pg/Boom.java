package com.pg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * @author Himi
 *
 */
public class Boom {
	//爆炸效果资源图
	private int score;
	private Bitmap bmpBoom;
	//爆炸效果的位置坐标
	private int boomX, boomY;
	//爆炸动画播放当前的帧下标
	private int cureentFrameIndex;
	//爆炸效果的总帧数
	private int totleFrame;
	//每帧的宽高
	private int frameW, frameH;
	//是否播放完毕，优化处理
	public boolean playEnd;
	private int boomcount;

	//爆炸效果的构造函数
	public Boom(Bitmap bmpBoom) {
		this.bmpBoom = bmpBoom;
	}
	public Boom(Bitmap bmpBoom, int x, int y, int totleFrame) {
		this.bmpBoom = bmpBoom;
		this.boomX = x;
		this.boomY = y;
		this.totleFrame = totleFrame;
		frameW = bmpBoom.getWidth() / totleFrame;
		frameH = bmpBoom.getHeight();
	}

	//爆炸效果绘制
	public void draw(Canvas canvas, Paint paint) {
		canvas.save();
		canvas.clipRect(boomX, boomY, boomX + frameW, boomY + frameH);
		canvas.drawBitmap(bmpBoom, boomX - cureentFrameIndex * frameW, boomY, paint);
		canvas.restore();
		
	}

	//爆炸效果的逻辑
	public void logic() {
		if (cureentFrameIndex < totleFrame) {
			cureentFrameIndex++;
		} else {
			playEnd = true;
		}
	}
	
	public BoomData getBoomData(){
		BoomData bm=new BoomData();
		bm.setBoomX(boomX);
		bm.setBoomY(boomY);
		bm.setCureentFrameIndex(cureentFrameIndex);
		bm.setFrameH(frameH);
		bm.setPlayEnd(playEnd);
		bm.setTotleFrame(totleFrame);
		bm.setFrameW(frameW);
		bm.setboomcount(boomcount);
		bm.setscore(score);
		
		return bm;
	}
	
	public void setBoomData(BoomData bd){
		this.boomX=bd.getBoomX();
		this.boomY=bd.getBoomY();
		this.cureentFrameIndex=bd.getCureentFrameIndex();
		this.frameH=bd.getFrameH();
		this.frameW=bd.getFrameW();
		this.totleFrame=bd.getTotleFrame();
		this.playEnd=bd.isPlayEnd();
		this.boomcount=bd.getboomcount();
		this.score=bd.getscore();
	}
}
