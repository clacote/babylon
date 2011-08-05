package org.plug.babylon.service;

import java.io.InputStream;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.plug.babylon.service.importer.Importer;
import org.plug.babylon.service.importer.ImporterFactory;

/**
 * File import service
 * @author Sryl <cyril.lacote@gmail.com>
 */
@Stateless
public class ImporterService {
    
    @EJB
    private ImporterFactory importerFactory;
    
    public void importFile( String filename, InputStream data) throws ImportException {
        Importer importer = importerFactory.getImporter(filename);
        if (importer != null) {
            importer.importData(data);
        } else {
            throw new UnmanagedFileTypeException(filename);
        }
    }
}
