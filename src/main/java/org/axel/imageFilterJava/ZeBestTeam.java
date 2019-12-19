package org.axel.imageFilterJava;

import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Scalar;


import java.io.File;
import java.io.IOException;

public class ZeBestTeam {
    public void applyFilter(String pathName, String output) {
        File f = new File(pathName);
        String name = f.getName();
        System.out.println(f.getAbsolutePath());
        Mat image = opencv_imgcodecs.imread(f.getAbsolutePath());
        try {
            opencv_imgproc.putText(image, "It's me, Mario", new Point(1, 200), opencv_imgproc.CV_FONT_HERSHEY_TRIPLEX , 1, new Scalar(200, 200, 250, 128));
            File outputDir = new File(output);
            File outputFile = new File(outputDir, "[TEAM]" + name);
            opencv_imgcodecs.imwrite(outputFile.getAbsolutePath(), image);
            Logger scribe = new Logger();
            scribe.log(" Filtering of " + name + " with " + this.getClass().getSimpleName() + '\n');
        } catch (RuntimeException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
