package org.plug.babylon.service.importer;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ImporterFactory#getImporter(java.lang.String) }
 * @author Sryl <cyril.lacote@gmail.com>
 */
public class ImporterFactoryTest {
    
    private ImporterFactory factory;
    
    private OFXImporter mockOFX;
    private QIFImporter mockQIF;
    
    @Before
    public void setUp() {
        mockOFX = new OFXImporter();
        mockQIF = new QIFImporter();
        
        factory = new ImporterFactory();
        factory.setOfxImporter(mockOFX);
        factory.setQifImporter(mockQIF);
    }
    
    @Test
    public void testOFX() {
        assertSame(mockOFX, factory.getImporter("dummy.ofx"));
    }
    
    @Test
    public void testQIF() {
        assertSame(mockQIF, factory.getImporter("dummy.qif"));
    }
    
    @Test
    public void testUnknown1() {
        assertNull(factory.getImporter("dummy.txt"));
    }
    
    @Test
    public void testUnknown2() {
        assertNull(factory.getImporter("dummy"));
    }
}
