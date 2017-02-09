package com.android.test.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Manish on 9/2/17.
 */


public class Common {


    static String timeZone;

    public Common(String userTimeZone) {
        this.timeZone = userTimeZone;
    }

    public Common() {
    }

    /**
     * @return yyyy-MM-dd HH:mm:ss formate date as string
     */


    public static String getCurrentDateTime() {
        try {

            Calendar cal = Calendar.getInstance();
            cal.setTimeZone(TimeZone.getTimeZone("UTC"));

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setTimeZone(cal.getTimeZone());
            String currentDateTime = dateFormat.format(cal.getTime());

            return currentDateTime;


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap decodeFile(File f) {
        try {

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = 4;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
        }
        return null;
    }

    public static Bitmap getRectShape(Bitmap scaleBitmapImage) {

        final Bitmap output = Bitmap.createBitmap(scaleBitmapImage.getWidth(),
                scaleBitmapImage.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, scaleBitmapImage.getWidth(), scaleBitmapImage.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRect(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(scaleBitmapImage, rect, rect, paint);

        //  scaleBitmapImage.recycle();

        return output;

    }

    public static String createDirectoryAndSaveFile(Bitmap imageToSave, String fileName) {

        File direct = new File(Environment.getExternalStorageDirectory() + "/DataCollection/images/");
        String path = null;
        if (!direct.exists()) {
            File file = new File("/DataCollection/images/");
            file.mkdirs();
        }

        File file = new File(new File("/DataCollection/images/"), fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            path = file.getAbsolutePath();
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }
}



