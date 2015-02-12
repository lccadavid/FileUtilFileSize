
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author leonardo.cardozo
 *
 */
public class LargeFilesFinder {

    private List<File> weightfiles = new ArrayList<File>();
    private static String[] extFilterS;

    public static void exec(String path, long minWeightInMB, String... extFilter) {

        extFilterS = extFilter;
        LargeFilesFinder lff = new LargeFilesFinder();

        long minWeight = minWeightInMB * 1024 * 1024; //byte to megabyte

        System.out.println("\nFinding files larger than " + minWeightInMB + "MB (" + minWeight + " bytes)");

        lff.findFiles(path, minWeight);
    }

    private void findFiles(String path, long minWeight) {
        find(path, minWeight);
        Collections.sort(weightfiles, new FileComparator());

        System.out.println("\n- - - DONE - - -");
        System.out.println("\n\n- - - LONGEST FILES - - -");

        for (File f : weightfiles) {
            System.out.println("- File:" + f.getAbsoluteFile() + " Weight: " + Formater.formatBytes(f.length()));
        }

        System.out.println("TOTAL: " + weightfiles.size());

    }

    private void find(String path, long minWeight) {
        File root = new File(path);
        File[] list = root.listFiles();

        for (File f : list) {
            if (f.isDirectory()) {
                find(f.getAbsolutePath(), minWeight);
            } else {
                if (f.length() > minWeight && canAdd(f.getAbsolutePath())) {
                    weightfiles.add(f);
                }
            }
        }
    }
    
    private static boolean canAdd(String ext){
        if(extFilterS == null || extFilterS.length == 0)
            return true;
        else {
            for (String e : extFilterS) {
                if(ext.toLowerCase().endsWith(e.toLowerCase())){
                    return true;
                }
            }
            return false;
        }
    }
}
