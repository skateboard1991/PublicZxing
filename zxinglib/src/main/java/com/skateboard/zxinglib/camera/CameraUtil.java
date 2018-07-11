package com.skateboard.zxinglib.camera;

import android.hardware.Camera;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CameraUtil
{

    public static Camera.Size findCloselySize(int surfaceWidth, int surfaceHeight, List<Camera.Size> preSizeList) {
        Collections.sort(preSizeList, new SizeComparator(surfaceWidth, surfaceHeight));
        return preSizeList.get(0);
    }

    private static class SizeComparator implements Comparator<Camera.Size>
    {

        private final int width;
        private final int height;
        private final float ratio;

        SizeComparator(int width, int height) {
            if (width < height) {
                this.width = height;
                this.height = width;
            } else {
                this.width = width;
                this.height = height;
            }
            this.ratio = (float) this.height / this.width;
        }

        @Override
        public int compare(Camera.Size size1, Camera.Size size2) {
            int width1 = size1.width;
            int height1 = size1.height;
            int width2 = size2.width;
            int height2 = size2.height;

            float ratio1 = Math.abs((float) height1 / width1 - ratio);
            float ratio2 = Math.abs((float) height2 / width2 - ratio);
            int result = Float.compare(ratio1, ratio2);
            if (result != 0) {
                return result;
            } else {
                int minGap1 = Math.abs(width - width1) + Math.abs(height - height1);
                int minGap2 = Math.abs(width - width2) + Math.abs(height - height2);
                return minGap1 - minGap2;
            }
        }
    }

}
