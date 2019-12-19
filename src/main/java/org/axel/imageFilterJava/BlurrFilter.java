package org.axel.imageFilterJava;

import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;

import java.io.File;
import java.io.IOException;

import static org.bytedeco.opencv.global.opencv_imgproc.GaussianBlur;

public class BlurrFilter implements Filter {
    public void applyFilter(String pathName, String output) {
        File f = new File(pathName);
        String name = f.getName();
        Mat image = opencv_imgcodecs.imread(f.getAbsolutePath());
        try {
            image = filter(image);
            File outputDir = new File(output);
            File outputFile = new File(outputDir, "[Bl]" + name + ".jpg");
            opencv_imgcodecs.imwrite(outputFile.getAbsolutePath(), image);
            Logger scribe = new Logger();
            scribe.log(" Filtering of " + name + " with " + this.getClass().getSimpleName() + '\n');
        } catch (RuntimeException | IOException e) {
            System.out.println("Problem happened during filtering");
        }
    }

    public Mat filter(Mat image) {
        int size = 3;
        Mat result = image.clone();
        GaussianBlur(image, result, new Size(size, size), 0);
        return result;
    }
}