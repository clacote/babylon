/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plug.babylon.service.importer;

import java.io.InputStream;
import javax.ejb.Stateless;

/**
 * OFX data importer
 * @author Sryl <cyril.lacote@gmail.com>
 */
@Stateless
public class OFXImporter implements Importer {

    @Override
    public void importData(InputStream data) {
        // TODO;
    }
    
}
