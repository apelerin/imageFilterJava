package org.axel.imageFilterJava;

import org.apache.commons.cli.*;

import java.io.File;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws ParseException {
        final Options firstOptions = configFirstParameters();
        final Options options = configParameters(firstOptions);

        final CommandLineParser parser = new DefaultParser();
        final CommandLine firstLine = parser.parse(firstOptions, args, true);
        boolean help = firstLine.hasOption("help");

        final CommandLine line = parser.parse(options, args);
        String input = line.getOptionValue("input");
        String output = line.getOptionValue("output");

        if (help) {
            final HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("FilterCLI", options, true);
            System.exit(0);
        }

        ArrayList<String> list_path = getRelPath();
        grayScaleFilter myGrayScaleFilter = new grayScaleFilter();
        list_path.forEach(myGrayScaleFilter::applyFilter);
    }

    public static ArrayList<String> getRelPath() {
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

    public static Options configFirstParameters() {
        final Option helpOption = Option.builder("h")
                .longOpt("help")
                .desc("Display help message")
                .build();

        final Options firstOptions = new Options();
        firstOptions.addOption(helpOption);
        return firstOptions;
    }
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
