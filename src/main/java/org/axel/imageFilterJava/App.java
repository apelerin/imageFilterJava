package org.axel.imageFilterJava;

public class App {
    public static void main(String[] args) {
        String pathName = "imgIn/poussePasMemeDansLesOrties.jpg";
        grayScaleFilter myGrayScaleFilter = new grayScaleFilter();
        myGrayScaleFilter.applyFilter(pathName);
    }
}
