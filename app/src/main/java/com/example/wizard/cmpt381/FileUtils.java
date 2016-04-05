package com.example.wizard.cmpt381;

/*
 * This file is modified from the Drawchemy project - https://code.google.com/p/drawchemy/
 * Their license:
 * Copyright (c) 2014 Pilmeyer Patrick
 *
 * Drawchemy is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Drawchemy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Drawchemy.  If not, see <http://www.gnu.org/licenses/>.
 */


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;

public class FileUtils {

    private static final String TAG = "FileUtils";
    private static String FILENAME;
    private DrawManager fManager;
    private Context fContext = null;

    public FileUtils(DrawManager aManager, Context aContext, String ID) {
        fManager = aManager;
        fContext = aContext;
        FILENAME = ID + ".png";
    }

    private static File getDirectory(Context aContext) {
        return aContext.getFilesDir();
    }

    public void saveSmaller(Context aContext) {
        new SavingImageTask(aContext, fManager.copyResizedBitmap()).execute();
    }

    public void save(Context aContext) throws ExecutionException, InterruptedException {
        new SavingImageTask(aContext, fManager.copyBitmap()).execute().get();
    }


    public void load(Context aContext) {
        new LoadImageTask(aContext, fManager).execute();
    }

    public static class LoadImageButtonTask extends AsyncTask<Object, Integer, Bitmap> {

        private Context fContext;
        private ImageButton fImageButton;

        public LoadImageButtonTask(Context aContext, ImageButton aImageButton) {
            super();
            fContext = aContext;
            fImageButton = aImageButton;
        }

        @Override
        protected Bitmap doInBackground(Object... objects) {
            Bitmap bitmap;
            File fFile = new File(getDirectory(fContext), FILENAME);
            Log.d(TAG, "LoadImageTask background: \n Loading image found in: " + fFile.getAbsolutePath());

            try {

                bitmap = BitmapFactory.decodeStream(new FileInputStream(fFile));

            } catch (FileNotFoundException e) {
                bitmap = null;
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                fImageButton.setImageBitmap(bitmap);
                fImageButton.invalidate();
                Log.d(TAG, "Bitmap has been loaded");
            } else {
                Toast.makeText(fContext, "No bitmap loaded", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public static class SavingImageTask extends AsyncTask<Object, Integer, Boolean> {

        private final Context fContext;
        protected File fFile;
        private Bitmap fBitmap;

        public SavingImageTask(Context aContext, Bitmap aBitmap) {
            fContext = aContext;
            fBitmap = aBitmap;
        }

        protected String getFileName() {
            return FILENAME;
        }

        @Override
        protected Boolean doInBackground(Object... objects) {

            File dir = getDirectory(fContext);
            try {

                fFile = new File(dir, getFileName());
                Log.d(TAG, "Saving bitmap into location: " + fFile.getAbsolutePath());

                OutputStream outStream = new FileOutputStream(fFile);
                Log.d(TAG, "Saving bitimap: made output stream " + outStream.toString());
                Log.d(TAG, "Saving bitmap: Compressing to PNG");

                fBitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                outStream.close();

            } catch (Exception e) {
                fBitmap = null;
                return false;
            }
            fBitmap = null;
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

        }

        /*protected void addFileToMedia(File aFile) {
            if (aFile != null && fContext != null && fContext.getApplicationContext() != null) {
                MediaScannerConnection.scanFile(fContext.getApplicationContext(),
                        new String[]{aFile.toString()}, new String[]{"image/png"},
                        new MediaScannerConnection.OnScanCompletedListener() {
                            public void onScanCompleted(String path, Uri uri) {
                            }
                        }
                );
            }
        }*/
    }

    public static class LoadImageTask extends AsyncTask<Object, Integer, Bitmap> {

        private Context fContext;
        private DrawManager fManager;

        public LoadImageTask(Context aContext, DrawManager aManager) {
            super();
            fContext = aContext;
            fManager = aManager;
        }

        @Override
        protected Bitmap doInBackground(Object... objects) {
            Bitmap bitmap;
            File fFile = new File(getDirectory(fContext), FILENAME);
            Log.d(TAG, "LoadImageTask background: \n Loading image found in: " + fFile.getAbsolutePath());

            try {

                bitmap = BitmapFactory.decodeStream(new FileInputStream(fFile));

            } catch (FileNotFoundException e) {
                bitmap = null;
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                fManager.putBitmapAsBackground(bitmap);
                Log.d(TAG, "DrawManager putBitmapAsBackground has been called");

            }
        }
    }

    public static class ReloadImageTask extends AsyncTask<Object, Integer, Bitmap> {

        private Context fContext;
        private Uri fTargetUri;
        private DrawManager fManager;

        public ReloadImageTask(Context aContext, Uri aTargetUri, DrawManager aManager) {
            super();
            fContext = aContext;
            fTargetUri = aTargetUri;
            fManager = aManager;
        }

        @Override
        protected Bitmap doInBackground(Object... objects) {
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(fContext.getContentResolver()
                        .openInputStream(fTargetUri));
            } catch (FileNotFoundException e) {
                bitmap = null;
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                fManager.putBitmapAsBackground(bitmap);
            }
        }
    }
}
