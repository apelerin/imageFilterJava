package org.axel.imageFilterJava;

import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;
import org.opencv.core.CvType;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.IOException;

import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;

public class GrayScaleFilter implements Filter {
    /**
     * Apply the filter to a given image and save it
     * @param pathName the relative path of the image
     * @param output the relative path of the directory we want the image to be saved to
     */
    public void applyFilter(String pathName, String output) {
        File f = new File(pathName);
        String name = f.getName();
        Mat image = opencv_imgcodecs.imread(f.getAbsolutePath());
        try {
            image = filter(image);
            File outputDir = new File(output);
            File outputFile = new File(outputDir, "[GS]" + name + ".jpg");
            opencv_imgcodecs.imwrite(outputFile.getAbsolutePath(), image);
            Logger scribe = new Logger();
            scribe.log(" Filtering of " + name + " with " + this.getClass().getSimpleName() + '\n');
        } catch (RuntimeException | IOException e) {
            System.out.println("Problem happened during filtering");
        }
    }

    /**
     *
     * @param image matrice of the image we want to filter
     * @return a matrice of the image filtered
     */
    public Mat filter (Mat image) {
        Mat result = new Mat(image.rows(), image.cols(), CvType.CV_8UC3);
        cvtColor(image, result, Imgproc.COLOR_RGB2GRAY);
        return result;
    }
}
