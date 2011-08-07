package org.plug.babylon.service;

import org.plug.babylon.service.importer.UnmanagedFileTypeException;
import java.io.InputStream;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.plug.babylon.model.Owner;
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
    
    @EJB
    private OwnerService ownerService;
    
    public void importFile( String filename, InputStream data) throws ImportException {
        
        // Find owner
        // FIXME Retrieve owner with current authenticated Principal
        Owner owner = ownerService.getByEmail("cyril.lacote@gmail.com");
        if (owner == null) {
            throw new ImportException("Unable to find owner for import");
        }
                
        Importer importer = importerFactory.getImporter(filename);
        if (importer != null) {
            importer.importData(owner, data);
        } else {
            throw new UnmanagedFileTypeException(filename);
        }
    }
}
