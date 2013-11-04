package com.example.osmeditor;

import org.osmdroid.views.MapView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;

public class ChooserMapView extends MapView{
	
	private Bitmap pin;
	private Paint paint;
	private Rect r;
	
	public ChooserMapView(Context context, AttributeSet attrs) {
		super(context, attrs);
		pin = BitmapFactory.decodeResource(getResources(), R.drawable.pin);
		paint = new Paint();
		paint.setARGB(150,150,150,150);
		paint.setStrokeWidth(15);
		r = new Rect();
	}
	
	@Override
	protected void onMeasure(int w, int h) {
		super.onMeasure(w, h);
		Log.v("aadil", "h = "+this.getHeight());
		Log.v("aadil", "w = "+this.getWidth());
		Log.v("aadil", "hh = "+this.getMeasuredHeight());
		Log.v("aadil", "ww = "+this.getMeasuredWidth());
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int w = canvas.getWidth();
		Log.v("aadil", "width = "+w);
		int h = canvas.getHeight();
		Log.v("aadil", "height = "+h);
		int pin_w = w/20;
		int pin_h = pin_w * pin.getHeight() / pin.getWidth();
		int x = (w - pin_w)/2;
		int y = h/2 - pin_h;
		r.set(x, y, x+pin_w, y+pin_h);
		canvas.drawBitmap(pin, null, r, paint);
		canvas.drawBitmap(pin, x, y, paint);
		canvas.drawRect(10,  10,  100,  100, paint);
		canvas.drawRect(0,0,10,10,paint);
		Log.v("aadil", "aadil");
	}
}
