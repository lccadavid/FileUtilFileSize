package com.fileutils.largefilesfinder;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author leonardo.cardozo
*/
public class LargeFilesFinder {

    private List<File> weightfiles = new ArrayList<File>();
    private static List<String> extFilterS;
    public final static int MB = 1024;
    
    private String path;
    private long minWeightInMB;
    
    
    public LargeFilesFinder (String path, long minWeightInMB, List<String> extFilterS) {
        this.path = path;
        this.minWeightInMB = minWeightInMB;
        this.extFilterS = extFilterS;
    }

    /**
     * 
     * @param path
     * @param minWeightInMB
     * @param extFilter (extensions to be included as a filter)
     */
    public void exec() {
        
        long minWeight = minWeightInMB * MB * MB; //byte to megabyte

        System.out.println("\nFinding files larger than " + minWeightInMB + "MB (" + minWeight + " bytes)");

        findFiles(path, minWeight);
    }

    private void findFiles(String path, long minWeight) {
        find(path, minWeight);
        
        weightfiles = weightfiles.stream().sorted((o1, o2) -> o2.length() > o1.length() ? 1 : -1).
                collect(Collectors.toList());

        System.out.println("\n- - - DONE - - -");
        System.out.println("\n\n- - - LONGEST FILES - - -");

        weightfiles.forEach(f -> System.out.println("- File:" + f.getAbsoluteFile() + " Weight: " + Formater.formatBytes(f.length())));

        System.out.println("TOTAL: " + weightfiles.size());

    }

    private void find(String path, long minWeight) {
        File root = new File(path);

        File[] list = root.listFiles();
        for (File f : list) {
            if (f.isDirectory()) {
                find(f.getAbsolutePath(), minWeight);
            } else if (f.length() > minWeight && canAdd(f.getAbsolutePath())) {
                weightfiles.add(f);
            }
        }
    }

    private static boolean canAdd(String ext) {
        if (extFilterS == null || extFilterS.isEmpty()) {
            return true;
        } else {
            for (String e : extFilterS) {
                if (ext.toLowerCase().endsWith(e.toLowerCase())) {
                    return true;
                }
            }
            return false;
        }
    }
}
