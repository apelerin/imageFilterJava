package org.axel.imageFilterJava;

import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;

import java.io.File;

import static org.bytedeco.opencv.global.opencv_imgproc.GaussianBlur;

public class BlurrFilter implements Filter {
    public void applyFilter(String pathName) {
        File f = new File(pathName);
        Mat image = opencv_imgcodecs.imread(f.getAbsolutePath());
        image = filter(image);

        File outputDir = new File("output");
        File outputFile = new File(outputDir, "result.jpg");
        opencv_imgcodecs.imwrite(outputFile.getAbsolutePath(), image);
    }

    public Mat filter(Mat image) {
        int size = 3;
        Mat result = image.clone();
        GaussianBlur(image, result, new Size(size, size), 0);
        return result;
    }
}