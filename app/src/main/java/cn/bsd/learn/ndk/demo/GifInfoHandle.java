package cn.bsd.learn.ndk.demo;

import android.graphics.Bitmap;

public class GifInfoHandle {
    // Used to load the 'native-lib' library on application startup.
    private volatile long gifInfoPtr;

    static {
        System.loadLibrary("android_gif");
    }

    public GifInfoHandle(String path){
        gifInfoPtr = openFile(path);
    }

    public long renderFrame(Bitmap bitmap){
        return renderFrame(gifInfoPtr, bitmap);
    }

    public synchronized int getWidth(){
        return getWidth(gifInfoPtr);
    }

    public synchronized int getHeight(){
        return getHeight(gifInfoPtr);
    }

    //调用native
    private native long openFile(String path);

    private native long renderFrame(long gifInfoPtr, Bitmap bitmap);

    private native int getWidth(long gifInfoPtr);

    private native int getHeight(long gifInfoPtr);
}
