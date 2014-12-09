package com.example.roundimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class DrawView extends View {
	
	Paint mPaint;
	Context context = MainActivity.mainContext;
	
	public DrawView(Context context){
		super(context, null);
	}
	
	public DrawView(Context context, AttributeSet attrs){
		super(context, attrs);
		mPaint = new Paint();
	}
	
	@Override
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		if(context != null){
			ImageView imageView = new ImageView(context);
			imageView.setImageResource(R.drawable.asd);
			Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
			Bitmap bitmap2 = zoomBitmap(bitmap, 100, 100);
			Bitmap roundBitmap = getRoundBitmap(bitmap2);
			
			Rect src = new Rect();// ����Ǳ�ʾ�滭ͼƬ�Ĵ�С
	        Rect dst = new Rect();// ��Ļλ�ü��ߴ�
	        src.left = 0;
	        src.top = 0;
	        src.right = roundBitmap.getWidth();//���������ͼ�Ŀ�ȣ�
	        src.bottom = roundBitmap.getHeight();//���������ͼ�ĸ߶ȵ�һ��
	        dst.left = 0;
	        dst.top = 0;
	        dst.right = roundBitmap.getWidth();    //��ʾ��滭��ͼƬ�����Ͻ�
	        dst.bottom = roundBitmap.getHeight();    //��ʾ��滭��ͼƬ�����½�
	        canvas.drawBitmap(roundBitmap,src,dst,mPaint);
		}
	}
	
	public static Bitmap getRoundBitmap(Bitmap bitmap){
		
		int width = bitmap.getWidth();//Բ��ͼƬ�Ŀ�
		int height = bitmap.getHeight();//Բ��ͼƬ�ĳ�
		int r = 0;//�����εı߳�
		
		if(width > height)//ȡ��̵���Ϊ�߳�
			r = height;
		else 
			r = width;
		
		Bitmap backRound = Bitmap.createBitmap(width, height, Config.ARGB_8888);//����һ��bitmap
		Canvas canvas = new Canvas(backRound);//һ���µ�canvas����backRound���滭ͼ
		Paint paint = new Paint();
		paint.setAntiAlias(true);//���ñ�Ե�⻬��ȥ�����
		RectF rect = new RectF(0, 0, r, r);//��ͳ���ȣ���Ϊ������
		canvas.drawRoundRect(rect, r/2, r/2, paint);//ͨ���ƶ���rect��һ��Բ�Ǿ��Σ���Բ��X�᷽��İ뾶����Y�᷽��İ뾶�Ҷ�Ϊr/2ʱ����������Բ�Ǿ��μ�ʱԲ��
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, null, rect, paint);
		return backRound;
	}
	
	private static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {  
        int width = bitmap.getWidth();  
        int height = bitmap.getHeight(); 
        Bitmap oldbmp = bitmap;
        Matrix matrix = new Matrix();  
        float scaleWidth = ((float) w / width);  
        float scaleHeight = ((float) h / height);  
        matrix.postScale(scaleWidth, scaleHeight);  
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,matrix, true);  
        newbmp = getRCB(newbmp, 20f);  
        return newbmp;  
    } 
	
	public static Bitmap getRCB(Bitmap bitmap, float roundPX) {  
        Bitmap dstbmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);  
        Canvas canvas = new Canvas(dstbmp);  
        final Paint paint = new Paint();  
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());  
        final RectF rectF = new RectF(rect);  
        paint.setAntiAlias(true);  
        canvas.drawARGB(0, 0, 0, 0);  
        paint.setColor(0xff424242);  
        canvas.drawRoundRect(rectF, roundPX, roundPX, paint);  
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));  
        canvas.drawBitmap(bitmap, rect, rect, paint);  
        return dstbmp;  
    } 
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		setMeasuredDimension(100, 100);
	}
}