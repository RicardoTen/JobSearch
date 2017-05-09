package com.boss.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * @ClassName: ThumbnailView
 * @Description:  点击时显示明暗变化(滤镜效果)的ImageView
 * @author LinJ
 * @date 2015-1-6 下午2:13:46
 *
 */
public class ThumbnailView extends ImageView{

    public ThumbnailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                //在按下事件中设置滤镜
                setFilter();
                break;
            case MotionEvent.ACTION_UP:
                //由于捕获了Touch事件，需要手动触发Click事件
                performClick();
            case MotionEvent.ACTION_CANCEL:
                //在CANCEL和UP事件中清除滤镜
                removeFilter();
                break;
            default:
                break;
        }
        return true;
    }

    /**
     *   设置滤镜
     */
    private void setFilter() {
        //先获取设置的src图片
        Drawable drawable=getDrawable();
        //当src图片为Null，获取背景图片
        if (drawable==null) {
            drawable=getBackground();
        }



        if(drawable!=null){
            //设置滤镜
            drawable.setColorFilter(Color.GRAY,PorterDuff.Mode.MULTIPLY);
            //缩放
            //resizeImage(drawableToBitmap(drawable), 10, 10 );
        }
    }

    public static Drawable resizeImage(Bitmap bitmap, int w, int h) {

        // load the origial Bitmap
        Bitmap BitmapOrg = bitmap;

        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int newWidth = w;
        int newHeight = h;

        // calculate the scale
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the Bitmap
        matrix.postScale(scaleWidth, scaleHeight);
        // if you want to rotate the Bitmap
        // matrix.postRotate(45);

        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
                height, matrix, true);

        // make a Drawable from Bitmap to allow to set the Bitmap
        // to the ImageView, ImageButton or what ever
        return new BitmapDrawable(resizedBitmap);

    }


    public static Bitmap drawableToBitmap(Drawable drawable) {



        Bitmap bitmap = Bitmap.createBitmap(

                drawable.getIntrinsicWidth(),

                drawable.getIntrinsicHeight(),

                (drawable.getOpacity() != PixelFormat.OPAQUE) ? Bitmap.Config.ARGB_8888

                        : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);

        //canvas.setBitmap(bitmap);

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        drawable.draw(canvas);

        return bitmap;

    }


    /**
     *   清除滤镜
     */
    private void removeFilter() {
        //先获取设置的src图片
        Drawable drawable=getDrawable();
        //当src图片为Null，获取背景图片
        if (drawable==null) {
            drawable=getBackground();
        }
        if(drawable!=null){
            //清除滤镜
            drawable.clearColorFilter();
        }
    }



}