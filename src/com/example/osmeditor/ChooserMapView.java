package com.example.osmeditor;

import org.osmdroid.views.MapView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

public class ChooserMapView extends MapView{
	
	ChooserMapView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
	}

}
