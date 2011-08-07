package org.plug.babylon.service.importer;

import java.io.InputStream;
import javax.ejb.Stateless;
import org.plug.babylon.model.Owner;

/**
 * QIF data importer
 * @author Sryl <cyril.lacote@gmail.com>
 */
@Stateless(name="qifImporter")
public class QIFImporter implements Importer {

    @Override
    public void importData(Owner owner, InputStream data) {
        // TODO;
    }
}
