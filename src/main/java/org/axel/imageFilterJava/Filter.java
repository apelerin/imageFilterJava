package org.axel.imageFilterJava;

import org.bytedeco.opencv.opencv_core.Mat;

public interface Filter {
    void applyFilter(String pathName, String output) throws FilterException;
    Mat filter(Mat image) throws FilterException;
}
