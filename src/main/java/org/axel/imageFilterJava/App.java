package org.axel.imageFilterJava;

import org.apache.commons.cli.*;
import java.io.File;
import java.util.ArrayList;

public class App {
    /**
     *
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        final Options firstOptions = configFirstParameters();
        final Options options = configParameters(firstOptions);

        final CommandLineParser parser = new DefaultParser();
        final CommandLine firstLine = parser.parse(firstOptions, args, true);
        boolean help = firstLine.hasOption("help");
        boolean list = firstLine.hasOption("list-filters");

        if (help) {
            final HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("FilterCLI", options, true);
            System.exit(0);
        }
        if (list) {
            System.out.println("-Grayscale filter" + '\n' + "-Blur filter" + '\n' + "-Dilate filter" + '\n' + "-ZebestTeam filter");
            System.exit(0);
        }


        final CommandLine line = parser.parse(options, args);
        String input = line.getOptionValue("input");
        String output = line.getOptionValue("output");

        ArrayList<String> list_path = getRelPath(input);
        GrayScaleFilter myGrayScaleFilter;
        myGrayScaleFilter = new GrayScaleFilter();
        BlurrFilter myBlurFilter = new BlurrFilter();
        DilateFilter myDilateFilter = new DilateFilter();
        ZeBestTeam myZbeub = new ZeBestTeam();

        for (int i = 0; i < list_path.size(); i++) {
            myGrayScaleFilter.applyFilter(list_path.get(i), output);
            myBlurFilter.applyFilter(list_path.get(i), output);
            myDilateFilter.applyFilter(list_path.get(i), output);
            myZbeub.applyFilter(list_path.get(i), output);
        }
    }



    /**
     * Get the relatives paths of all files into an input directory
     * @param input, the name of the input directory.
     * @return an ArrayList of relative path of the images
     */
    public static ArrayList<String> getRelPath(String input) {
        ArrayList<String> list_path = new ArrayList<String>();
        File folder = new File(input);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                list_path.add(input + '/' + listOfFiles[i].getName());
            }
        }
        return list_path;
    }

    /**
     * Config optional arguments
     * @return
     */
    public static Options configFirstParameters() {
        final Option helpOption = Option.builder("h")
                .longOpt("help")
                .desc("Display help message")
                .build();

        final Option listFilterOption = Option.builder("lf")
                .longOpt("list-filters")
                .desc("List existing filter")
                .build();

        final Options firstOptions = new Options();
        firstOptions.addOption(helpOption);
        firstOptions.addOption(listFilterOption);
        return firstOptions;
    }

    /**
     * Config mandatory arguments
     * @param firstOptions Options object that take optional args in account
     * @return options
     */
    public static Options configParameters(final Options firstOptions) {

        final Option inputOption = Option.builder("i")
                .longOpt("input")
                .hasArg(true)
                .argName("input")
                .required(true)
                .build();

        final Option outputOption = Option.builder("o")
                .longOpt("output")
                .hasArg(true)
                .argName("output")
                .required(true)
                .build();

        final Options options = new Options();

        for (final Option fo : firstOptions.getOptions()) {
            options.addOption(fo);
        }

        options.addOption(inputOption);
        options.addOption(outputOption);

        return options;
    }
}
