
package fr.bbws.bo.statistics.api;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/** 
 * Example resource class hosted at the URI path "/welcome"
 */
@Path("/welcome")
public class MyResource {
    
	final static Logger logger = LogManager.getLogger(MyResource.class.getName());
	
    /** 
     * Method processing HTTP GET requests, producing "text/plain" MIME media type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET 
    @Produces("text/plain")
    public String welcome() {
    	
    	logger.trace("BEGIN Endpoint /welcome");
        return "ceci est un autre endpoint";
    }
    
    
    @SuppressWarnings("resource")
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
    @Path("lastplays")
    public String getLastPlays(@QueryParam("player-id") String playerID) {
    	
    	logger.entry(playerID);
    	
    	List<Object> result = new ArrayList<Object>(); // the ES search result
		
    	
    	
    	// ############## OUVERTURE DU SERVEUR ELASTICSEARCH
    	TransportClient client = null;
		
		try {
			
			Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
			client = new PreBuiltTransportClient(settings).addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
			
		} catch (UnknownHostException e) {
			
			logger.fatal("[FATAL] Impossible de se connecter au serveur ElasticSearch : {}", "localhost:9300");
			e.printStackTrace();
		}
		
		
		// ############## EXECUTION DE LA REQUETE
		// parcourir l'index _baseball-eu_
		// requete exacte sur l'attribut _paleyr-id_
		// correspondant au parametre de la requete REST
		SearchResponse response = client.prepareSearch("baseball-eu")
		        .setSearchType(SearchType.DEFAULT)
		        .setQuery(QueryBuilders.matchQuery("player-id", playerID))
		        .setFrom(0).setSize(100).setExplain(true)
		        .get();
		
		// ############## PARCOURIR LE RESULTAT DE LA REQUETE
		SearchHits hits = response.getHits();
		for (SearchHit hit : hits) {
			logger.debug("Résultat = {}", hit.getSourceAsMap());
			result.add(hit.getSourceAsMap());
		}
		
		
		
		// ############## FERMETURE DE LA CONNEXION A ELASTICSEARCH
		if (client != null) {
			client.close();
		}
		
		
		// ############## GENERER LE JSon DE SORTIE
		final Gson gson = new GsonBuilder().create();
		String json = gson.toJson(result);
		
		
		
		logger.traceExit(response.getHits().getTotalHits());
		return json;
    }
    
}
