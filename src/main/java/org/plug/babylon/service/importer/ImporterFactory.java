package org.plug.babylon.service.importer;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.lang.StringUtils;
import org.plug.babylon.common.FileUtil;

/**
 * {@link Importer} factory from filename.
 * @author Sryl <cyril.lacote@gmail.com>
 */
@Stateless
public class ImporterFactory {
    
    /** File extension for OFX file */
    private static final String EXTENSION_OFX = "ofx";
    /** File extension for QIF file */
    private static final String EXTENSION_QIF = "qif";
    
    @EJB(beanName="ofxImporter") Importer ofxImporter;
    @EJB(beanName="qifImporter") Importer qifImporter;
    
    // FIXME Shouldn't MIME type being used instead of filename?
    /**
     * Factory method : return {@link Importer} instance from given filename
     * @param filename
     * @return {@link Importer} instance, or null if unmanaged filename
     */
    public Importer getImporter(String filename) {
        Importer importer = null;
        
        final String extension = FileUtil.getExtension(filename);
        if (extension != null) {
            if (StringUtils.equalsIgnoreCase(EXTENSION_OFX, extension)) {
                importer = ofxImporter;
            } else if (StringUtils.equalsIgnoreCase(EXTENSION_QIF, extension)) {
                importer = qifImporter;
            }
        }
        
        return importer;
    }

    /** For unit tests */
    void setOfxImporter(OFXImporter ofxImporter) {
        this.ofxImporter = ofxImporter;
    }

    /** For unit tests */
    void setQifImporter(QIFImporter qifImporter) {
        this.qifImporter = qifImporter;
    }
}
