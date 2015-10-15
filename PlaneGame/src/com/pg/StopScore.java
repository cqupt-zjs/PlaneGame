package com.pg;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class StopScore {

	SharedPreferences sh=MainActivity.instance.getSharedPreferences("table", 0);
	SharedPreferences.Editor editor=sh.edit();
	private int score_ed;
	private int score;
	public  StopScore(int score) {
		this.score=score;
	}
	public void logic(int score) {
		this.score=score;
		score_ed=sh.getInt("highest", 0);
		if (score_ed<score) {
			editor.putInt("highest", score);
			editor.commit();
		}
		
	}
	public void drawscore(Canvas canvas,Paint paint){
		canvas.save();
		canvas.scale(2, 2,  MySurfaceView.screenW-50, 40);
		paint.setColor(Color.BLUE);
		canvas.drawText("最高分："+score_ed, MySurfaceView.screenW/2,40, paint);
		canvas.drawText("最终得分："+score, MySurfaceView.screenW/2,80, paint);
		canvas.restore();
	}

}

