package org.plug.babylon.common;

import org.apache.commons.lang.StringUtils;

/**
 * Common File utils.
 * @author Sryl <cyril.lacote@gmail.com>
 */
final public class FileUtil {
    
    private static final char DEFAULT_EXTENSION_SEPARATOR = '.';

    /** useless constructor */
    private FileUtil() {}
    
    /**
     * Get extension from filename, with default extension separator '.'. Null-proof.
     * @param fileName Filename to get extension from
     * @return extension found, null if not found
     */
    public static String getExtension(String fileName) {
        return getExtension(fileName, DEFAULT_EXTENSION_SEPARATOR);
    }
    
    /**
     * Get extension from filename, with given extension separator. Null-proof.
     * @param fileName Filename to get extension from
     * @param char extension separator
     * @return extension found, null if not found
     */
    public static String getExtension(String fileName, char extensionSeparator) {
        String ext = null;
        if (StringUtils.isNotEmpty(fileName)) {
            final int dot = fileName.lastIndexOf(extensionSeparator);
            if (dot > 0) {
                ext = fileName.substring(dot + 1);
            }
        }
        return ext;
    }
    
}
