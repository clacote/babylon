package org.plug.babylon.service.importer;

import java.io.InputStream;
import javax.ejb.Stateless;

/**
 * QIF data importer
 * @author Sryl <cyril.lacote@gmail.com>
 */
@Stateless
public class QIFImporter implements Importer {

    @Override
    public void importData(InputStream data) {
        // TODO;
    }
}
