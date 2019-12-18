package org.axel.imageFilterJava;

import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;
import org.opencv.core.CvType;
import org.opencv.imgproc.Imgproc;

import java.io.File;

import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;

public class grayScaleFilter implements Filter {
    public void applyFilter(String pathName) {
        File f = new File(pathName);
        String name = f.getName();
        Mat image = opencv_imgcodecs.imread(f.getAbsolutePath());
        try {
            image = filter(image);
            File outputDir = new File("imgOut");
            File outputFile = new File(outputDir, "[GS]" + name + ".jpg");
            opencv_imgcodecs.imwrite(outputFile.getAbsolutePath(), image);
        } catch (RuntimeException e) {
        System.out.println("Problem happened during filtering");
    }
    }

    public Mat filter (Mat image) {
        Mat result = new Mat(image.rows(), image.cols(), CvType.CV_8UC3);
        cvtColor(image, result, Imgproc.COLOR_RGB2GRAY);
        return result;
    }
}
