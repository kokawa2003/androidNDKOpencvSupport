package com.example.myapplication;

import static org.opencv.android.Utils.bitmapToMat;
import static org.opencv.android.Utils.matToBitmap;
import static org.opencv.imgproc.Imgproc.cvtColor;
import static org.opencv.imgproc.Imgproc.threshold;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity {
    static {
       System.loadLibrary("myapplication");
   }
    private final BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    //Log.i(TAG, "OpenCV loaded successfully");
                    //mOpenCvCameraView.enableView();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("tag","abbbbbbbbbbbbbbbbbbbcreate");


        TextView textView = findViewById(R.id.text1);
        textView.setText(OpenCVLoader.OPENCV_VERSION);  // ★OpenCVのバージョンを設定
    }

    @Override
    public void onResume()
    {
        Log.d("tag","aaaaaaaaaaaaaaaaaaaaaaresume");
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d("tag", "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION, this, mLoaderCallback);
        } else {
            Log.d("kkkk", "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
            mytex();
        }
    }


    void mytex(){
        Resources r = getResources();
        Bitmap bmp = BitmapFactory.decodeResource(r,R.drawable.ookawa);
        Mat mat=new Mat();
        bitmapToMat(bmp,mat);

        //cvtColor(mat, mat, Imgproc.COLOR_RGB2GRAY);  // まずグレースケールへ(明るさだけの形式)
        //threshold(mat, mat, 128.0, 255.0, Imgproc.THRESH_BINARY);

        Mat matOutput = new Mat(mat.rows(), mat.cols(), mat.type());

        processImage(mat.getNativeObjAddr(), matOutput.getNativeObjAddr());

        Bitmap output = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Bitmap.Config.ARGB_8888);
        matToBitmap(matOutput, output);
        ImageView  iView = findViewById(R.id.imageView);
        iView.setImageBitmap(output);

    }

    public native  int  processImage(long in,long out );
}