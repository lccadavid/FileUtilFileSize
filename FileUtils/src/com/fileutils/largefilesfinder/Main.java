package com.fileutils.largefilesfinder;

import java.util.Arrays;
import java.util.List;

/**
 * To test
 * @author lcardozo
 */
public class Main {

    public static void main(String[] args) {

        //test: 
        /* if(args.length == 0) {
            args = new String []{"C:\\vm", "20", "dmg", "exe", "run"};
        } */
        
        if(args.length < 2) {
            throw new IllegalArgumentException("Please add args, ej1: C:\\myDir 8 OR ej2: C:\\myDir 8 mp3 pdf");
        }
        
        List<String> l = Arrays.asList(Arrays.copyOfRange(args, 2, args.length));
        new LargeFilesFinder(args[0], new Long(args[1]), l).exec();
    }

}
