package org.plug.babylon.common;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link FileUtil}
 * @author Sryl <cyril.lacote@gmail.com>
 */
public class FileUtilTest {

    @Test
    public void testGetExtensionNull() {
        assertNull(FileUtil.getExtension(null));
    }

    @Test
    public void testGetExtensionEmpty() {
        assertNull(FileUtil.getExtension(""));
    }

    @Test
    public void testGetExtensionNoExtension() {
        assertNull(FileUtil.getExtension("abcdef"));
    }

    @Test
    public void testGetExtensionOK() {
        assertEquals("abc", FileUtil.getExtension("afgh.abc"));
    }

    @Test
    public void testGetExtensionOKMultipleDots1() {
        assertEquals("abc", FileUtil.getExtension("afgh...abc"));
    }

    @Test
    public void testGetExtensionOKMultipleDots2() {
        assertEquals("abc", FileUtil.getExtension("af.gh.abc"));
    }

    @Test
    public void testGetExtensionGiven() {
        assertEquals("abc", FileUtil.getExtension("af.gh#abc", '#'));
    }
}
