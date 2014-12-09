package com.example.roundimageview;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

public class RoundBitmap {
	
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
	
}