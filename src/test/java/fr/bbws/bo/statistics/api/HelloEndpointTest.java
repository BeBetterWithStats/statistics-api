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


/*
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest({"server.port:0",
        "spring.datasource.url:jdbc:h2:mem:statistics-api;DB_CLOSE_ON_EXIT=FALSE"})
public class HelloEndpointTest {
    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @Test
    public void testHello() throws Exception {
        when().get("/").then()
                .body(is("Hello World!"));
    }

    @Test
    public void testCalc() throws Exception {
        given().param("left", 100)
                .param("right", 200)
                .get("/calc")
                .then()
                .body("left", is(100))
                .body("right", is(200))
                .body("answer", is(300));
    }
}
*/