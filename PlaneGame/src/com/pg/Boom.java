package com.pg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * @author Himi
 *
 */
public class Boom {
	//��ըЧ����Դͼ
	private int score;
	private Bitmap bmpBoom;
	//��ըЧ����λ������
	private int boomX, boomY;
	//��ը�������ŵ�ǰ��֡�±�
	private int cureentFrameIndex;
	//��ըЧ������֡��
	private int totleFrame;
	//ÿ֡�Ŀ��
	private int frameW, frameH;
	//�Ƿ񲥷���ϣ��Ż�����
	public boolean playEnd;
	private int boomcount;

	//��ըЧ���Ĺ��캯��
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

	//��ըЧ������
	public void draw(Canvas canvas, Paint paint) {
		canvas.save();
		canvas.clipRect(boomX, boomY, boomX + frameW, boomY + frameH);
		canvas.drawBitmap(bmpBoom, boomX - cureentFrameIndex * frameW, boomY, paint);
		canvas.restore();
		
	}

	//��ըЧ�����߼�
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
