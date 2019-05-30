package cn.bsd.learn.ndk.demo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private String path = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"demo.gif";
    private Bitmap bitmap;
    private GifInfoHandle infoHandle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView= findViewById(R.id.image);
        Glide.with(MainActivity.this).load(path).into((ImageView) findViewById(R.id.glide));
        infoHandle = new GifInfoHandle(path);
        int width = infoHandle.getWidth();
        int height  = infoHandle.getHeight();
        bitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        long nextFrameTime = infoHandle.renderFrame(bitmap);
        imageView.setImageBitmap(bitmap);
        //循环绘制
        handler.sendEmptyMessageDelayed(1,nextFrameTime);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            long nextFrameTime = infoHandle.renderFrame(bitmap);
            imageView.setImageBitmap(bitmap);
            handler.sendEmptyMessageDelayed(1,nextFrameTime);
        }
    };

}
