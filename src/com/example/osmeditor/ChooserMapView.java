package com.example.osmeditor;

import org.osmdroid.views.MapView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.util.AttributeSet;

public class ChooserMapView extends MapView{
	
	ChooserMapView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	/*protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int w = canvas.getWidth();
		int h = canvas.getHeight();
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pin);
		int pin_w = w/25;
		int pin_h = pin_w * bitmap.getHeight() / bitmap.getWidth();
		bitmap = Bitmap.createScaledBitmap(bitmap,  pin_w,  pin_h, true);
		int x = (w - pin_w)/2;
		int y = h/2 - pin_h;
		canvas.drawBitmap(bitmap, x, y, null);
	}*/

}
