package org.axel.imageFilterJava;

import org.bytedeco.opencv.opencv_core.Mat;

public interface Filter {
    Mat filter(Mat image);
}


