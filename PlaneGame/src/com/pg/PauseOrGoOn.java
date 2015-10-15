package com.pg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class PauseOrGoOn {

	private Bitmap bmpPause;     //ÔÝÍ£µÄÍ¼Æ¬
	private Bitmap bmpGoOn;		 // ¼ÌÐøµÄÍ¼Æ¬
	private Bitmap tmp;
	public static final int PAUSE=0;
	public static final int GOON=1;
	private int type=PAUSE;
	private int px,py;
	private int i=0;

	public PauseOrGoOn(Bitmap pause,Bitmap goon,int type) {
		this.bmpPause=pause;
		this.bmpGoOn=goon;
		this.type=type;
		this.px=5;
		this.py=5;
	}

	public void draw(Canvas canvas, Paint paint) {
		canvas.save();
		if(type==PAUSE){
			this.tmp=bmpPause;
		}
		else{
			this.tmp=bmpGoOn;
		}
		canvas.drawBitmap(tmp, px,py,paint);
		canvas.restore();
	}

	public void setType(int type) {
		this.type=type;
	}
	public int getType(){
		return type;
	}
	public boolean onTouchEvent(MotionEvent event) {
		float x=event.getX();
		float y=event.getY();

		if(event.getAction()==MotionEvent.ACTION_MOVE||event.getAction()==MotionEvent.ACTION_UP){
			if(i==1){
				i=0;
				return true;
			}
			return false;
		}

		else{
			if(x>px&&x<px+tmp.getWidth()&&y>py&&y<py+tmp.getHeight()){
				if(type==GOON){
					type=PAUSE;
				}
				else{
					type=GOON;
				}
				++i;
				return true;
			}
		}
		return false;
	}
}
