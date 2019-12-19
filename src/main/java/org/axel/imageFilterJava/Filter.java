package org.axel.imageFilterJava;

import org.bytedeco.opencv.opencv_core.Mat;

public interface Filter {
    /**
     *
     * @param pathName
     * @param output
     * @throws FilterException
     */
    void applyFilter(String pathName, String output) throws FilterException;

    /**
     *
     * @param image
     * @return
     * @throws FilterException
     */
    Mat filter(Mat image) throws FilterException;
}


