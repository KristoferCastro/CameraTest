package com.example.cameratest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

public class FileContentProvider extends ContentProvider{

	public static final Uri CONTENT_URI = Uri.parse("");
	private static final HashMap<String, String> MIME_TYPES = new HashMap<String, String>();
	
	static {
		MIME_TYPES.put(".jpg", "image/jpeg");
		MIME_TYPES.put(".jpeg", "image/jpeg");
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		String path = uri.toString();
		for(String ext : MIME_TYPES.keySet()){
			if (path.endsWith(ext)){
				return MIME_TYPES.get(ext);
			}
		}
		
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Operation not supported");
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		
		try{
			File file = new File(this.getContext().getFilesDir(), "newImage.jpg");
			if ( !file.exists() ){
				file.createNewFile();
			}
			getContext().getContentResolver().notifyChange(CONTENT_URI, null);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ParcelFileDescriptor openFile(Uri uri, String mode)
	throws FileNotFoundException{
		
		File f = new File(getContext().getFilesDir(), "newImage.jpg");
		if (f.exists() ){
			return (ParcelFileDescriptor.open(f, ParcelFileDescriptor.MODE_READ_WRITE));
		}
		throw new FileNotFoundException(uri.getPath());
	}
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Operation not supported");
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Operation not supported");
	}
	
}
