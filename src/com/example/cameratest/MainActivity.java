package com.example.cameratest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.provider.MediaStore.Files.FileColumns;

public class MainActivity extends Activity {

	Button cameraButton;
	private Uri fileUri;
	ImageView imageView;
	Button saveButton;
	Bitmap thumbImage; 

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int THUMBSIZE = 512;
	private int MEDIA_TYPE_IMAGE = 1;
	private String currentPhotoPath;
	
	// database stuff
	RestaurantOpenHelper restaurantTable = new RestaurantOpenHelper(this);
	//MenuItemOpenHelper menuItemTable = new MenuItemOpenHelper(this);
	//MomentOpenHelper momentTable = new MomentOpenHelper(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Toast.makeText(this,"OnCreate()", Toast.LENGTH_LONG).show();
		setContentView(R.layout.activity_main);
		
		cameraButton = (Button) this.findViewById(R.id.cameraButton);
		imageView = (ImageView) this.findViewById(R.id.imageView);
		saveButton = (Button) this.findViewById(R.id.saveButton);
		
		cameraButton.setOnClickListener(cameraButtonOnClickHandler);
	}
	
	OnClickListener cameraButtonOnClickHandler = new OnClickListener(){


		@Override
		public void onClick(View v) {
			Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
			i.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
			Toast.makeText(MainActivity.this, "Image saved to: \n" + fileUri.getPath(), Toast.LENGTH_LONG).show();

			startActivityForResult(i, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
		}

		private Uri getOutputMediaFileUri(int mediaTypeImage) {
			
			File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"MyCameraApp");
			
			if ( !mediaStorageDir.exists()){
				if ( !mediaStorageDir.mkdir() ){
					Log.d("MyCameraApp", "failed to create directory");
					return null;
				}
			}
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			File mediaFile = null;
			
			if(mediaTypeImage == MEDIA_TYPE_IMAGE){
				mediaFile = new File(mediaStorageDir + File.separator + "IMG_" + timeStamp + ".jpg");
				currentPhotoPath = mediaFile.getParent();
			}
			
			return Uri.fromFile(mediaFile);
		}		
	};
	
	private void handleCameraPhoto(){
		imageView.setImageBitmap(this.thumbImage);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		if ( requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE ){
			if ( resultCode == RESULT_OK ){
				//if ( data == null) return;
				thumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(this.fileUri.getPath()),  THUMBSIZE, THUMBSIZE);
				handleCameraPhoto();
			}else if ( resultCode == RESULT_CANCELED){
				// cancelled the image capture
			}else{
				// failed to capture image
				Toast.makeText(this, "Failed!", Toast.LENGTH_LONG).show();

			}
		}
	}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Toast.makeText(this,"OnDestroy()", Toast.LENGTH_LONG).show();
		super.onDestroy();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		Toast.makeText(this,"OnPause()", Toast.LENGTH_LONG).show();
		super.onPause();

	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		Toast.makeText(this,"OnRestart()", Toast.LENGTH_LONG).show();
		super.onResume();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Toast.makeText(this,"OnResume()", Toast.LENGTH_LONG).show();
		super.onResume();

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		Toast.makeText(this,"OnStart()", Toast.LENGTH_LONG).show();
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		Toast.makeText(this,"OnStop()", Toast.LENGTH_LONG).show();
		super.onStop();
	}

}
