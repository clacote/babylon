package org.plug.babylon.service.importer;

import java.io.InputStream;
import org.plug.babylon.model.Owner;

/**
 * Bank statements importer
 * @author Sryl <cyril.lacote@gmail.com>
 */
public interface Importer {
    
    void importData(Owner owner, InputStream data);
}
