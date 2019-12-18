package org.axel.imageFilterJava;

import java.io.File;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        ArrayList<String> list_path = getPaths();
        grayScaleFilter myGrayScaleFilter = new grayScaleFilter();
        list_path.forEach(myGrayScaleFilter::applyFilter);
    }

    public static ArrayList<String> getPaths() {
        ArrayList<String> list_path = new ArrayList<String>();
        File folder = new File("imgIn");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                list_path.add("imgIn/" + listOfFiles[i].getName());
            }
        }
        return list_path;
    }
}
