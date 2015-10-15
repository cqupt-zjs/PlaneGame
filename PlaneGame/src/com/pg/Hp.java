package com.pg;

import java.io.Serializable;
import java.util.Random;

import android.drm.DrmStore.Action;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class Hp implements Serializable{

	private Bitmap bmpPlayerHp;
	private Player player;
	//主角的坐标以及位图
	private int x, y;
	//生命周期 
	private int leftTime=-1;
	private boolean isDead=false;
	private Random rand;
	//主角的构造函数
	public Hp(Bitmap bmpPlayerHp,Player player) {
		this.bmpPlayerHp = bmpPlayerHp;
		this.player=player;
		rand=new Random();
	}

	public void show(){
		isDead=false;
		leftTime=20*5;

		x=rand.nextInt(MySurfaceView.screenW);
		y=rand.nextInt(MySurfaceView.screenH);
	}
	//主角的绘图函数
	public void draw(Canvas canvas, Paint paint) {
		if(isDead==false){
			canvas.save();
			canvas.drawBitmap(bmpPlayerHp, x,y,paint);
			canvas.restore();
		}
	}

	public void onTouchEvent(MotionEvent event){
		if(isDead){
			return;
		}
		if(event.getAction()==MotionEvent.ACTION_MOVE){
			//player 图片范围
			float px=event.getX()-player.getBmpPlayer().getWidth()/2;
			float py=event.getY()-player.getBmpPlayer().getHeight();
			float px1=px+player.getBmpPlayer().getWidth();
			float py1=py+player.getBmpPlayer().getHeight();
			//心图片范围  x,y,x1,y1;
			int x1=x+bmpPlayerHp.getWidth();
			int y1=y+bmpPlayerHp.getHeight();
			//(x,y),(x1,y),(x1,y),(x1,y1)
			//(px,py),(px,py1),(px1,py),(px1,py1);
			if(x>px && x<px1 && y>py && y<py1 || x1>px &&x1<px1 && y>py&&y<py1 
					|| x1>px&&x1<px1 && y>py&&y<py1 
					|| x1>px&&x1<px1 &&y1>py&&y1<py1){
				if(player.getPlayerHp()<6){
					player.setPlayerHp(player.getPlayerHp()+1);
				}
				isDead=true;
			}
		}
	}


	//主角的逻辑
	public void logic() {
		--leftTime;
		if(leftTime<0){
			isDead=true;
		}
	}
	public boolean isDead(){
		return isDead;
	}
	
	public HpData getHpData(){
		HpData hd=new HpData();
		hd.setDead(isDead);
		hd.setLeftTime(leftTime);
		hd.setX(x);
		hd.setY(y);
		
		return hd;
	}
	
	public void setHpData(HpData hd){
		this.isDead=hd.isDead();
		this.leftTime=hd.getLeftTime();
		this.x=hd.getX();
		this.y=hd.getY();
	}
	
	
}
