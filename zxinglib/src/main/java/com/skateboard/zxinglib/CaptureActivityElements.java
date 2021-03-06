package com.skateboard.zxinglib;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;

import com.google.zxing.Result;
import com.skateboard.zxinglib.camera.CameraManager;

public interface CaptureActivityElements
{

    ViewfinderView findViewfinderView();

    void handleDecode(Result rawResult, Bitmap barcode, float scaleFactor);

    Activity getActivity();

    Handler findHandler();

    CameraManager getCameraManager();

    void drawViewfinder();
}
