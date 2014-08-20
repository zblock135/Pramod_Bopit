package com.EPED.pramodbopit;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;

public class Play extends Activity{
	Handler handler = new Handler(); //This takes care of updating UI
	boolean shake = false; //This variable keeps track of whether the device has been shaken
	int score = 0;
	long timer = 2000;	//This variable will determine the time the user has to complete an action
	boolean keep_playing = true;	//This is how the game tells whether to stop
	
	
	
	private ShakeDetector mShakeDetector;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play);
		//ShakeDetector initialization
				mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
				mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
				mShakeDetector = new ShakeDetector(new OnShakeListener() {
					@Override
					public void onShake(){
						shake = true;
					
					}
				});
			}
			@Override
		    protected void onResume() {
		        super.onResume();
		        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
		    }

		    @Override
		    protected void onPause() {
		        mSensorManager.unregisterListener(mShakeDetector);
		        super.onPause();
		    }   	
	
	
	public void onStart(){
		super.onStart();
		Session();
		
	};
	
	
	
	
	
	
	
	
	public void Session() {
		Thread t = new Thread(){
		public void run(){
		handler.post(ready);
		
		try { Thread.sleep(2000); }
		catch (InterruptedException ex) { android.util.Log.d("YourApplicationName", ex.toString()); }
		handler.post(clear);
		
		handler.post(set);
		
		try { Thread.sleep(2000); }
		catch (InterruptedException ex) { android.util.Log.d("YourApplicationName", ex.toString()); }
		handler.post(clear);
		handler.post(go);
		
		try { Thread.sleep(2000); }
		catch (InterruptedException ex) { android.util.Log.d("YourApplicationName", ex.toString()); }
		handler.post(clear);
		handler.post(shakeAction);
		
		
		}
	
	};
	t.start();
	}
	
	Runnable clear = new Runnable(){
		public void run(){
	
			TextView text = (TextView)findViewById(R.id.command);
	text.setTextSize(2, 30);
	text.setText("");
	
		}	
	};
	//This prints ready to the screen
		Runnable ready = new Runnable(){
			public void run(){
		
				TextView text = (TextView)findViewById(R.id.command);
		text.setTextSize(2, 30);
		text.setText("Ready?");
		
			}	
		
	};
	Runnable set = new Runnable(){
		public void run(){
			TextView text = (TextView)findViewById(R.id.command);
	text.setTextSize(2, 50);
	text.setText("Set?");
		}		
};

	Runnable go = new Runnable(){
		public void run(){
			TextView text = (TextView)findViewById(R.id.command);	
			text.setTextSize(2, 100);
			text.setText("GO!");

	}		
};

	Runnable shakeAction = new Runnable(){
		public void run(){
			TextView text = (TextView)findViewById(R.id.command);
			text.setTextSize(2, 70);
			text.setText("SHAKE IT!");
			long time = System.currentTimeMillis();
			long curTime = time;
			while(curTime<(time+timer)){
				curTime = System.currentTimeMillis();
				
				if (shake==true){
					score = score+1;
					shake = false;
					text.setText("You Shook it!");
					break;
				}
				else{
					//Insert if statements to check for the completion of other actions
				}
			}
			keep_playing = false;
			text.setText("YOU SUCK!");
			//Insert method to reset the score and timer
			
		}
	};
	
}











