package org.plug.babylon.service.importer;

import java.io.InputStream;

/**
 * Bank statements importer
 * @author Sryl <cyril.lacote@gmail.com>
 */
public interface Importer {
    
    void importData(InputStream data);
}
