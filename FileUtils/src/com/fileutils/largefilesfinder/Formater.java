package com.fileutils.largefilesfinder;

/**
  * @author lcardozo
 */
public class Formater {

    /**
     * Returns a pretty format for the given bytes
     * @param bytes
     * @return formated bytes
     */
    public static String formatBytes(long bytes) {
        int unit = LargeFilesFinder.MB;
        if (bytes < unit) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = ("KMGTPE").charAt(exp - 1) + "";
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
