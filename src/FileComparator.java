

import java.io.File;
import java.util.Comparator;

public class FileComparator implements Comparator <File> {

    @Override
    public int compare(File o1, File o2) {
        return o2.length() > o1.length() ? 1 : -1;
    }
}