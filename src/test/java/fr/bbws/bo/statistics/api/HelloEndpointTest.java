package fr.bbws.bo.statistics.api;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HelloEndpointTest {
	
	final static Logger logger = LogManager.getLogger(HelloEndpointTest.class.getName());
	
	@Before
    public void setUp() throws Exception {
        logger.info("--- Démarrage des tests ----");
        
    }
	

    @After
    public void tearDown() throws Exception {
    	logger.info("--- Fin des tests ----");
    }
    
    @Test
    public void welcomeTest() throws Exception {
        
    }
}