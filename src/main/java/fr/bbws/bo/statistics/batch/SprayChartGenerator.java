
package fr.bbws.bo.statistics.batch;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SprayChartGenerator {

	static Map<String, KEY_WORDS> at_bat_key_words = new HashMap<String, KEY_WORDS>();

	static Map<String, KEY_WORDS> hit_key_words = new HashMap<String, KEY_WORDS>();
	
	static List<String> no_show_error = new ArrayList<String>();
	
	static List<Player> team = new ArrayList<Player>();
	
	static List<String> errors = new ArrayList<String>();
	
	
	public static void main(String[] args) {
		
		long begin = System.currentTimeMillis();
		
		// ############## MOTS CLES > POSITION		
		at_bat_key_words.put(" advanced", KEY_WORDS.UNKNOWN);
		hit_key_words.put(" doubled down the lf line", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" doubled down the lf line", KEY_WORDS.LEFT_FIELD);
		hit_key_words.put(" doubled down the rf line", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" doubled down the rf line", KEY_WORDS.RIGHT_FIELD);
		hit_key_words.put(" doubled through the left side", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" doubled through the left side", KEY_WORDS.LEFT_FIELD);
		hit_key_words.put(" doubled through the right side", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" doubled through the right side", KEY_WORDS.RIGHT_FIELD);
		hit_key_words.put(" doubled to center field", KEY_WORDS.CENTER_FIELD);
		at_bat_key_words.put(" doubled to center field", KEY_WORDS.CENTER_FIELD);
		hit_key_words.put(" doubled to left center", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" doubled to left center", KEY_WORDS.LEFT_FIELD);
		hit_key_words.put(" doubled to left field", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" doubled to left field", KEY_WORDS.LEFT_FIELD);
		hit_key_words.put(" doubled to right center", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" doubled to right center", KEY_WORDS.RIGHT_FIELD);
		hit_key_words.put(" doubled to right field", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" doubled to right field", KEY_WORDS.RIGHT_FIELD);
		hit_key_words.put(" doubled to third base", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" doubled to third base", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" flied into double play 1b", KEY_WORDS.FIRST_BASE);
		at_bat_key_words.put(" flied into double play 2b", KEY_WORDS.SECOND_BASE);
		at_bat_key_words.put(" flied into double play 3b", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" flied into double play cf", KEY_WORDS.CENTER_FIELD);
		at_bat_key_words.put(" flied into double play lf", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" flied into double play p", KEY_WORDS.PITCHER);
		at_bat_key_words.put(" flied into double play rf", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" flied into double play ss", KEY_WORDS.SHORTSTOP);
		at_bat_key_words.put(" flied out to 1b", KEY_WORDS.FIRST_BASE);
		at_bat_key_words.put(" flied out to 2b", KEY_WORDS.SECOND_BASE);
		at_bat_key_words.put(" flied out to 3b", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" flied out to cf", KEY_WORDS.CENTER_FIELD);
		at_bat_key_words.put(" flied out to lf", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" flied out to p", KEY_WORDS.PITCHER);
		at_bat_key_words.put(" flied out to rf", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" flied out to ss", KEY_WORDS.SHORTSTOP);
		at_bat_key_words.put(" fouled into double play 1b", KEY_WORDS.FIRST_BASE);
		at_bat_key_words.put(" fouled into double play 2b", KEY_WORDS.SECOND_BASE);
		at_bat_key_words.put(" fouled into double play 3b", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" fouled into double play c", KEY_WORDS.CATCHER);
		at_bat_key_words.put(" fouled into double play lf", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" fouled into double play p", KEY_WORDS.PITCHER);
		at_bat_key_words.put(" fouled into double play rf", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" fouled into double play ss", KEY_WORDS.SHORTSTOP);
		at_bat_key_words.put(" fouled out to 1b", KEY_WORDS.FIRST_BASE);
		at_bat_key_words.put(" fouled out to 2b", KEY_WORDS.FIRST_BASE);
		at_bat_key_words.put(" fouled out to 3b", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" fouled out to c", KEY_WORDS.CATCHER);
		at_bat_key_words.put(" fouled out to lf", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" fouled out to p", KEY_WORDS.PITCHER);
		at_bat_key_words.put(" fouled out to rf", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" fouled out to ss", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" grounded into double play 1b", KEY_WORDS.FIRST_BASE);
		at_bat_key_words.put(" grounded into double play 2b", KEY_WORDS.SECOND_BASE);
		at_bat_key_words.put(" grounded into double play 3b", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" grounded into double play c", KEY_WORDS.CATCHER);
		at_bat_key_words.put(" grounded into double play p", KEY_WORDS.PITCHER);
		at_bat_key_words.put(" grounded into double play ss", KEY_WORDS.SHORTSTOP);
		at_bat_key_words.put(" grounded out to 1b", KEY_WORDS.FIRST_BASE);
		at_bat_key_words.put(" grounded out to 2b", KEY_WORDS.SECOND_BASE);
		at_bat_key_words.put(" grounded out to 3b", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" grounded out to c", KEY_WORDS.CATCHER);
		at_bat_key_words.put(" grounded out to p", KEY_WORDS.PITCHER);
		at_bat_key_words.put(" grounded out to ss", KEY_WORDS.SHORTSTOP);
		at_bat_key_words.put(" hit by pitch", KEY_WORDS.UNKNOWN);
		at_bat_key_words.put(" hit into double play 1b", KEY_WORDS.FIRST_BASE);
		at_bat_key_words.put(" hit into double play 2b", KEY_WORDS.SECOND_BASE);
		at_bat_key_words.put(" hit into double play 3b", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" hit into double play c", KEY_WORDS.CATCHER);
		at_bat_key_words.put(" hit into double play p", KEY_WORDS.PITCHER);
		at_bat_key_words.put(" hit into double play ss", KEY_WORDS.SHORTSTOP);
		hit_key_words.put(" homered down the lf line", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" homered down the lf line", KEY_WORDS.LEFT_FIELD);
		hit_key_words.put(" homered down the rf line", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" homered down the rf line", KEY_WORDS.RIGHT_FIELD);
		hit_key_words.put(" homered to center field", KEY_WORDS.CENTER_FIELD);
		at_bat_key_words.put(" homered to center field", KEY_WORDS.CENTER_FIELD);
		hit_key_words.put(" homered to left center", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" homered to left center", KEY_WORDS.LEFT_FIELD);
		hit_key_words.put(" homered to left field", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" homered to left field", KEY_WORDS.LEFT_FIELD);
		hit_key_words.put(" homered to right center", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" homered to right center", KEY_WORDS.RIGHT_FIELD);
		hit_key_words.put(" homered to right field", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" homered to right field", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" infield fly to 1b", KEY_WORDS.FIRST_BASE);
		at_bat_key_words.put(" infield fly to 2b", KEY_WORDS.SECOND_BASE);
		at_bat_key_words.put(" infield fly to 3b", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" infield fly to c", KEY_WORDS.CATCHER);
		at_bat_key_words.put(" infield fly to p", KEY_WORDS.PITCHER);
		at_bat_key_words.put(" infield fly to ss", KEY_WORDS.SHORTSTOP);
		at_bat_key_words.put(" intentionally walked", KEY_WORDS.WALK);
		at_bat_key_words.put(" lined into double play 1b", KEY_WORDS.FIRST_BASE);
		at_bat_key_words.put(" lined into double play 2b", KEY_WORDS.SECOND_BASE);
		at_bat_key_words.put(" lined into double play 3b", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" lined into double play cf", KEY_WORDS.CENTER_FIELD);
		at_bat_key_words.put(" lined into double play lf", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" lined into double play p", KEY_WORDS.PITCHER);
		at_bat_key_words.put(" lined into double play rf", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" lined into double play ss", KEY_WORDS.SHORTSTOP);
		at_bat_key_words.put(" lined into triple play 1b", KEY_WORDS.FIRST_BASE);
		at_bat_key_words.put(" lined into triple play 2b", KEY_WORDS.SECOND_BASE);
		at_bat_key_words.put(" lined into triple play 3b", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" lined into triple play cf", KEY_WORDS.CENTER_FIELD);
		at_bat_key_words.put(" lined into triple play lf", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" lined into triple play p", KEY_WORDS.PITCHER);
		at_bat_key_words.put(" lined into triple play rf", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" lined into triple play ss", KEY_WORDS.SHORTSTOP);
		at_bat_key_words.put(" lined out to 1b", KEY_WORDS.FIRST_BASE);
		at_bat_key_words.put(" lined out to 2b", KEY_WORDS.SECOND_BASE);
		at_bat_key_words.put(" lined out to 3b", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" lined out to cf", KEY_WORDS.CENTER_FIELD);
		at_bat_key_words.put(" lined out to lf", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" lined out to p", KEY_WORDS.PITCHER);
		at_bat_key_words.put(" lined out to rf", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" lined out to ss", KEY_WORDS.SHORTSTOP);
		at_bat_key_words.put(" out at first 1b to 2b", KEY_WORDS.FIRST_BASE);
		at_bat_key_words.put(" out at first 1b to p", KEY_WORDS.FIRST_BASE);
		at_bat_key_words.put(" out at first 2b to p", KEY_WORDS.SECOND_BASE);
		at_bat_key_words.put(" out at first c to 2b", KEY_WORDS.CATCHER);
		at_bat_key_words.put(" out at first p to 1b, on appeal", KEY_WORDS.UNKNOWN);
		at_bat_key_words.put(" out at first p to 2b", KEY_WORDS.SECOND_BASE);
		at_bat_key_words.put(" out at first rf to 1b", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" out at first ss to 2b", KEY_WORDS.SHORTSTOP);
		at_bat_key_words.put(" out at third", KEY_WORDS.UNKNOWN);
		at_bat_key_words.put(" out on batter's interference", KEY_WORDS.OBR);
		at_bat_key_words.put(" popped into double play 1b to 2b", KEY_WORDS.FIRST_BASE);
		at_bat_key_words.put(" popped into double play 2b to 1b", KEY_WORDS.SECOND_BASE);
		at_bat_key_words.put(" popped into double play 2b unassisted", KEY_WORDS.SECOND_BASE);
		at_bat_key_words.put(" popped up to 1b", KEY_WORDS.FIRST_BASE);
		at_bat_key_words.put(" popped up to 2b", KEY_WORDS.SECOND_BASE);
		at_bat_key_words.put(" popped up to 3b", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" popped up to c", KEY_WORDS.CATCHER);
		at_bat_key_words.put(" popped up to p", KEY_WORDS.PITCHER);
		at_bat_key_words.put(" popped up to ss", KEY_WORDS.SHORTSTOP);
		//at_bat_key_words.put(" reach on a fielding error by 1b, SAC", FieldingPosition.SACRIFICE);
		//at_bat_key_words.put(" reach on a fielding error by 2b, SAC", FieldingPosition.SACRIFICE);
		//at_bat_key_words.put(" reach on a fielding error by 3b, SAC", FieldingPosition.SACRIFICE);
		//at_bat_key_words.put(" reach on a fielding error by c, SAC", FieldingPosition.SACRIFICE);
		//at_bat_key_words.put(" reach on a fielding error by p, SAC", FieldingPosition.SACRIFICE);
		//at_bat_key_words.put(" reach on a fielding error by ss, SAC", FieldingPosition.SACRIFICE);
		at_bat_key_words.put(" reached on a dropped fly by 1b", KEY_WORDS.FIRST_BASE);
		at_bat_key_words.put(" reached on a dropped fly by 2b", KEY_WORDS.SECOND_BASE);
		at_bat_key_words.put(" reached on a dropped fly by 3b", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" reached on a dropped fly by catcher", KEY_WORDS.CATCHER);
		at_bat_key_words.put(" reached on a dropped fly by cf", KEY_WORDS.CENTER_FIELD);
		at_bat_key_words.put(" reached on a dropped fly by lf", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" reached on a dropped fly by p", KEY_WORDS.PITCHER);
		at_bat_key_words.put(" reached on a dropped fly by rf", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" reached on a dropped fly by ss", KEY_WORDS.SHORTSTOP);
		at_bat_key_words.put(" reached on a fielder's choice to catcher", KEY_WORDS.CATCHER);
		at_bat_key_words.put(" reached on a fielder's choice to center field", KEY_WORDS.CENTER_FIELD);
		at_bat_key_words.put(" reached on a fielder's choice to first base", KEY_WORDS.FIRST_BASE);
		at_bat_key_words.put(" reached on a fielder's choice to left field", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" reached on a fielder's choice to pitcher", KEY_WORDS.PITCHER);
		at_bat_key_words.put(" reached on a fielder's choice to right field", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" reached on a fielder's choice to second base", KEY_WORDS.SECOND_BASE);
		at_bat_key_words.put(" reached on a fielder's choice to shortstop", KEY_WORDS.SHORTSTOP);
		at_bat_key_words.put(" reached on a fielder's choice to third base", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" reached on a fielder's choice, grounded into double play 3b to c", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" reached on a fielding error by 1b", KEY_WORDS.FIRST_BASE);
		at_bat_key_words.put(" reached on a fielding error by 2b", KEY_WORDS.SECOND_BASE);
		at_bat_key_words.put(" reached on a fielding error by 3b", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" reached on a fielding error by c", KEY_WORDS.CATCHER);
		at_bat_key_words.put(" reached on a fielding error by p", KEY_WORDS.PITCHER);
		at_bat_key_words.put(" reached on a fielding error by ss", KEY_WORDS.SHORTSTOP);
		at_bat_key_words.put(" reached on a muffed throw by 1b", KEY_WORDS.FIRST_BASE);
		at_bat_key_words.put(" reached on a muffed throw by 2b", KEY_WORDS.SECOND_BASE);
		at_bat_key_words.put(" reached on a muffed throw by 3b", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" reached on a muffed throw by c", KEY_WORDS.CATCHER);
		at_bat_key_words.put(" reached on a muffed throw by p", KEY_WORDS.PITCHER);
		at_bat_key_words.put(" reached on a muffed throw by ss", KEY_WORDS.SHORTSTOP);
		at_bat_key_words.put(" reached on a throwing error by 1b", KEY_WORDS.FIRST_BASE);
		at_bat_key_words.put(" reached on a throwing error by 2b", KEY_WORDS.SECOND_BASE);
		at_bat_key_words.put(" reached on a throwing error by 3b", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" reached on a throwing error by c", KEY_WORDS.CATCHER);
		at_bat_key_words.put(" reached on a throwing error by cf", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" reached on a throwing error by lf", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" reached on a throwing error by p", KEY_WORDS.PITCHER);
		at_bat_key_words.put(" reached on a throwing error by rf", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" reached on a throwing error by ss", KEY_WORDS.SHORTSTOP);
		at_bat_key_words.put(" reached on an error by 2b", KEY_WORDS.SECOND_BASE);
		at_bat_key_words.put(" reached on an error by 3b", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" reached on an error by ss", KEY_WORDS.SHORTSTOP);
		at_bat_key_words.put(" reached on an error by cf", KEY_WORDS.CENTER_FIELD);
		at_bat_key_words.put(" reached on an error by lf", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" reached on an error by rf", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" scored", KEY_WORDS.UNKNOWN);
		hit_key_words.put(" singled down the lf line", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" singled down the lf line", KEY_WORDS.LEFT_FIELD);
		hit_key_words.put(" singled down the rf line", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" singled down the rf line", KEY_WORDS.RIGHT_FIELD);
		hit_key_words.put(" singled through the left side", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" singled through the left side", KEY_WORDS.LEFT_FIELD);
		hit_key_words.put(" singled through the right side", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" singled through the right side", KEY_WORDS.RIGHT_FIELD);
		hit_key_words.put(" singled to 1b", KEY_WORDS.FIRST_BASE);
		at_bat_key_words.put(" singled to 1b", KEY_WORDS.FIRST_BASE);
		hit_key_words.put(" singled to 2b", KEY_WORDS.SECOND_BASE);
		at_bat_key_words.put(" singled to 2b", KEY_WORDS.SECOND_BASE);
		hit_key_words.put(" singled to 3b", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" singled to 3b", KEY_WORDS.THIRD_BASE);
		hit_key_words.put(" singled to catcher", KEY_WORDS.CATCHER);
		at_bat_key_words.put(" singled to catcher", KEY_WORDS.CATCHER);
		hit_key_words.put(" singled to center field", KEY_WORDS.CENTER_FIELD);
		at_bat_key_words.put(" singled to center field", KEY_WORDS.CENTER_FIELD);
		hit_key_words.put(" singled to first base", KEY_WORDS.FIRST_BASE);
		at_bat_key_words.put(" singled to first base", KEY_WORDS.FIRST_BASE);
		hit_key_words.put(" singled to left center", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" singled to left center", KEY_WORDS.LEFT_FIELD);
		hit_key_words.put(" singled to left field", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" singled to left field", KEY_WORDS.LEFT_FIELD);
		hit_key_words.put(" singled to pitcher", KEY_WORDS.PITCHER);
		at_bat_key_words.put(" singled to pitcher", KEY_WORDS.PITCHER);
		hit_key_words.put(" singled to right center", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" singled to right center", KEY_WORDS.RIGHT_FIELD);
		hit_key_words.put(" singled to right field", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" singled to right field", KEY_WORDS.RIGHT_FIELD);
		hit_key_words.put(" singled to second base", KEY_WORDS.SECOND_BASE);
		at_bat_key_words.put(" singled to second base", KEY_WORDS.SECOND_BASE);
		hit_key_words.put(" singled to shortstop", KEY_WORDS.SHORTSTOP);
		at_bat_key_words.put(" singled to shortstop", KEY_WORDS.SHORTSTOP);
		hit_key_words.put(" singled to ss", KEY_WORDS.SHORTSTOP);
		at_bat_key_words.put(" singled to ss", KEY_WORDS.SHORTSTOP);
		hit_key_words.put(" singled to third base", KEY_WORDS.THIRD_BASE);
		at_bat_key_words.put(" singled to third base", KEY_WORDS.THIRD_BASE);
		hit_key_words.put(" singled up the middle", KEY_WORDS.CENTER_FIELD);
		at_bat_key_words.put(" singled up the middle", KEY_WORDS.CENTER_FIELD);
		at_bat_key_words.put(" stole", KEY_WORDS.STOLEN_BASE);
		at_bat_key_words.put(" struck out looking", KEY_WORDS.K_LOOKING);
		at_bat_key_words.put(" struck out swinging", KEY_WORDS.K_SWINGING);
		at_bat_key_words.put(" struck out, out at first", KEY_WORDS.K_SWINGING);
		at_bat_key_words.put(" struck out, reached first on a passed ball", KEY_WORDS.K_SWINGING);
		at_bat_key_words.put(" struck out, reached first on a throwing error", KEY_WORDS.K_SWINGING);
		at_bat_key_words.put(" struck out, reached first on a wild pitch", KEY_WORDS.K_SWINGING);
		at_bat_key_words.put(" struck out, reached on a fielder's choice", KEY_WORDS.K_SWINGING);
		hit_key_words.put(" tripled down the lf line", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" tripled down the lf line", KEY_WORDS.LEFT_FIELD);
		hit_key_words.put(" tripled down the rf line", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" tripled down the rf line", KEY_WORDS.RIGHT_FIELD);
		hit_key_words.put(" tripled through the left side", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" tripled through the left side", KEY_WORDS.LEFT_FIELD);
		hit_key_words.put(" tripled through the right side", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" tripled through the right side", KEY_WORDS.RIGHT_FIELD);
		hit_key_words.put(" tripled to center field", KEY_WORDS.CENTER_FIELD);
		at_bat_key_words.put(" tripled to center field", KEY_WORDS.CENTER_FIELD);
		hit_key_words.put(" tripled to left center", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" tripled to left center", KEY_WORDS.LEFT_FIELD);
		hit_key_words.put(" tripled to left field", KEY_WORDS.LEFT_FIELD);
		at_bat_key_words.put(" tripled to left field", KEY_WORDS.LEFT_FIELD);
		hit_key_words.put(" tripled to right center", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" tripled to right center", KEY_WORDS.RIGHT_FIELD);
		hit_key_words.put(" tripled to right field", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" tripled to right field", KEY_WORDS.RIGHT_FIELD);
		at_bat_key_words.put(" walked", KEY_WORDS.WALK);
		at_bat_key_words.put(".", KEY_WORDS.UNKNOWN);
		at_bat_key_words.put(";", KEY_WORDS.UNKNOWN);
	
// ############## FIN -- MOTS CLES > POSITION

		
		
		
		
// ############## LISTE DES JOUEURS & REPERTOIRE CONTENANT LES FEUILLES DE MATCH
		
		ArrayList<Path> file_directories = new ArrayList<Path>();
		file_directories.add( Paths.get("/Users/alexandrelods/Documents/Developpement/bbws/Games/stats 2022 D2 FR"));
		
//		team.add(new Player("name", "team", "#jersey", "position", "ordre a la frappe"));


		// ################## SENART 2
		/*
		team.add(new Player("LODS", "SENART", "", null, 0));
		team.add(new Player("ALFARA", "SENART", "", null, 0));
		team.add(new Player("ZOIA", "SENART", "", null, 0));
		team.add(new Player("MEURANT T", "SENART", "", null, 0));
		team.add(new Player("MEURANT M", "SENART", "", null, 0));
		team.add(new Player("GREELY", "SENART", "", null, 0));
		team.add(new Player("POURET", "SENART", "", null, 0));
		team.add(new Player("BRELLE", "SENART", "", null, 0));
		team.add(new Player("LAUNAY", "SENART", "", null, 0));
		team.add(new Player("COLLET", "SENART", "", null, 0));
		team.add(new Player("GHINTRAN", "SENART", "", null, 0));
		team.add(new Player("TSHIBAMBE", "SENART", "", null, 0));
		team.add(new Player("LANDRY", "SENART", "", null, 0));
		team.add(new Player("GRAS", "SENART", "", null, 0));
		team.add(new Player("FLAMENT", "SENART", "", null, 0));
		team.add(new Player("GUISTEL", "SENART", "", null, 0));
		team.add(new Player("LAUFENBUC Tr", "SENART", "", null, 0));
		team.add(new Player("LAUFENBUC Th", "SENART", "", null, 0));
		*/
		
		// ##################
		team.add(new Player("GHULAMI", "PUC", "", null, 0));
		team.add(new Player("--", "PUC", "&nbsp;", null, 0));
		
		
// ############## FIN -- LISTE DES JOUEURS

		
		
		System.out.println("##########  ------------------------ ##########");
		System.out.println("##########  BASEBALL STATS GENERATOR ##########");
		System.out.println("##########  ------------------------ ##########");
		
		
		
// ############## PARCOURIR LE REPERTOIRE DES FEUILLES DE MATCH
// ############## ET LE STOCKER EN MEMOIRE
		

		StringBuffer buffer = new StringBuffer();
		
		for ( Path file_dir : file_directories) {
		
			try {
	
				DirectoryStream<Path> stream = Files.newDirectoryStream(file_dir); // repertoire contenant les fichiers HTML
				
				try {
					Iterator<Path> iterator = stream.iterator();
					
					while (iterator.hasNext()) { // pour chaque fichier du repertoire
						
						Path current_file = iterator.next();
						
						if (!current_file.toString().endsWith("DS_Store")) { // pour eviter les pb sur MAC OS
							
							List<String> lignestmp = Files.readAllLines(current_file, Charset.forName("ISO-8859-1"));
							
							for (String ligne : lignestmp) { // pour chaque ligne du fichier
								
								buffer.append(" ").append(ligne.replaceAll( ">", "\\. ")); // on remplace les '>' par '. ' (point + espace)
							}
						}
					}
				} finally {
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		String[] lignes_triees = buffer.toString().split("\\. "); // decouper suivant la chaine de caracteres ". "
		
// ############## FIN -- PARCOURIR LE REPERTOIRE DES FEUILLES DE MATCH
// ############## FIN -- ET LE STOCKER EN MEMOIRE


		
// ############## RECHERCHER LES MOT CLES INDIQUANT LA POSITION DE LA FRAPPE

		for (Player player : team) {
			
			for (String ligne : lignes_triees) {
				
				if ( ligne.startsWith( player.getName())) { // si la ligne commence par le nom du joueur
					
					boolean print_error = true; // boolean pour afficher un message d'erreur si le nom du joueur n'est pas suivi d'un mot cle connu
					
					for (String key : at_bat_key_words.keySet()) {
						
						if (ligne.startsWith( player.getName() + key)) { // si le nom du joueur apparait dans la ligne avec le mot cle
							
							int fielding_position = at_bat_key_words.get(key).intValue();
							player.increaseAb( fielding_position); // aumgentation de l'at bat
							
							if (ligne.contains( ", SAC") 
									|| ligne.contains( ", SF, RBI") 
									|| ligne.contains( ", SF, 2 RBI") 
									|| ligne.contains( ", SF, 3 RBI") 
									|| ligne.contains( ", SF, 4 RBI")) {
								player.increaseAb( KEY_WORDS.SACRIFICE.intValue());
								
							}
							
							
							if ( null != hit_key_words.get(key)) {
								player.increaseHit( fielding_position);
							}
							
							print_error = false;
						}
						
					}
	
					if (print_error) {
						player.increaseAb( 0);
						errors.add( ligne + "\n");
						
					}
				}
			}
		}

		
// ############## FIN -- RECHERCHER LES MOT CLES INDIQUANT LA POSITION DE LA FRAPPE		

				

		
// ############## GENERATION HTML
		
		int size = team.size() % 2 != 0 ? team.size() - 1 : team.size();

		System.out.println("\n");
		System.out.println( generateHeaderHtml( team.get(0)));
		
		for (int p = 0; p < size; p = p+2) {
			System.out.println( generateHTML( team.get( p), team.get( p+1)));
		}
		
		if ( team.size() % 2 != 0) {
			System.out.println( generateHTML( team.get( team.size() - 1)));
		}
		
		System.out.println( generateFooterHtml());
		System.out.println("\n");
// ############## FIN -- GENERATION HTML
		
		
		System.out.println("\n");
		System.out.println(errors);
		System.out.println("\n");
		
		
		System.out.println("##########  ------------------------ ##########");
		System.out.println("##########            FIN            ##########");
		System.out.println("##########  ------------------------ ##########");

		long time = System.currentTimeMillis() - begin;
		System.out.println("INFO -- Execution time = " + time);
		
	}

	private static String generateHeaderHtml( Player player) {
		
		StringBuffer header = new StringBuffer()
				.append("<html><title>&TEAM</title><body><h1>&TEAM</h1>")
				.append("<style>")
				.append(".player {")
				.append("border : solid 1px;")
				.append("}")
				.append(".name {")
				.append("width: 150px;")
				.append("height: 100px;")
				.append("font-family: sans-serif;")
				.append("font-weight : bold;")
				.append("font-size : 9pt;")
				.append("vertical-align: top;")
				.append("padding-top: 3px;")
				.append("}")
				.append(".field {")
				.append("width: 100px;")
				.append("height: 100px;")
				.append("font-family: sans-serif;")
				.append("font-weight : bold;")
				.append("font-size : 7pt;")
				.append("border : solid 0px;")
				.append("background-image: url(./nTEEAzyTA.png);")
				.append("background-repeat: no-repeat;")
				.append("}")
				.append(".field td {")
				.append("//border : solid 1px;")
				.append("width: 10px;")
				.append("height: 10px;")
				.append("text-align: center;")
				.append("}")
				.append("</style>");
		
		String html_header = header.toString().replaceFirst( "&TEAM", player.getTeam());
		return html_header.toString();
		
	}

	private static String generateHTML( Player player) {
		
		StringBuffer template = new StringBuffer()
				.append("<table class=\"player\"><tr><td class=\"name\">&NOM<br>&JERSEY<br>&DEFENSE<br>KS=&KS, KL=&KL<br>BB=&BB, SF/SH=&SAC<br>AVG=&AVG</td><td>&ATBAT</td><td>&HIT</td></tr></table>");
				
		
		StringBuffer field = 
				new StringBuffer("<table class=\"field\"><tbody>")
				.append( "<tr><td >&a</td><td >&b</td><td >&c</td><td >&d</td><td >&e</td></tr>")
				.append( "<tr><td >&f</td><td >&g</td><td >&h</td><td >&i</td><td >&j</td></tr>")
				.append( "<tr><td >&k</td><td >&l</td><td >&m</td><td >&z</td><td >&o</td></tr>")
				.append( "<tr><td >&p</td><td >&q</td><td >&r</td><td >&s</td><td >&t</td></tr>")
				.append( "<tr><td >&u</td><td >&v</td><td >&w</td><td >&x</td><td >&y</td></tr>")
				.append( "</tbody></table>");
		
		String at_bat = field.toString();
		at_bat = at_bat.replaceFirst( "&a", "&nbsp;");
		at_bat = at_bat.replaceFirst( "&b", "&nbsp;");
		at_bat = at_bat.replaceFirst( "&c", player.getAb()[KEY_WORDS.CENTER_FIELD.intValue()] != 0 ? Integer.toString( player.getAb()[KEY_WORDS.CENTER_FIELD.intValue()]) : "&nbsp;");
		at_bat = at_bat.replaceFirst( "&d", "&nbsp;");
		at_bat = at_bat.replaceFirst( "&e", "&nbsp;");
		at_bat = at_bat.replaceFirst( "&f", player.getAb()[KEY_WORDS.LEFT_FIELD.intValue()] != 0 ? Integer.toString( player.getAb()[KEY_WORDS.LEFT_FIELD.intValue()]) : "&nbsp;");
		at_bat = at_bat.replaceFirst( "&g", player.getAb()[KEY_WORDS.SHORTSTOP.intValue()] != 0 ? Integer.toString( player.getAb()[KEY_WORDS.SHORTSTOP.intValue()]) : "&nbsp;");
		at_bat = at_bat.replaceFirst( "&h", "&nbsp;");
		at_bat = at_bat.replaceFirst( "&i", player.getAb()[KEY_WORDS.SECOND_BASE.intValue()] != 0 ? Integer.toString( player.getAb()[KEY_WORDS.SECOND_BASE.intValue()]) : "&nbsp;");
		at_bat = at_bat.replaceFirst( "&j", player.getAb()[KEY_WORDS.RIGHT_FIELD.intValue()] != 0 ? Integer.toString( player.getAb()[KEY_WORDS.RIGHT_FIELD.intValue()]) : "&nbsp;");
		at_bat = at_bat.replaceFirst( "&k", "&nbsp;");
		at_bat = at_bat.replaceFirst( "&l", player.getAb()[KEY_WORDS.THIRD_BASE.intValue()] != 0 ? Integer.toString( player.getAb()[KEY_WORDS.THIRD_BASE.intValue()]) : "&nbsp;");
		at_bat = at_bat.replaceFirst( "&m", player.getAb()[KEY_WORDS.PITCHER.intValue()] != 0 ? Integer.toString( player.getAb()[KEY_WORDS.PITCHER.intValue()]) : "&nbsp;");
		at_bat = at_bat.replaceFirst( "&z", player.getAb()[KEY_WORDS.FIRST_BASE.intValue()] != 0 ? Integer.toString( player.getAb()[KEY_WORDS.FIRST_BASE.intValue()]) : "&nbsp;");
		at_bat = at_bat.replaceFirst( "&o", "&nbsp;");
		at_bat = at_bat.replaceFirst( "&p", "&nbsp;");
		at_bat = at_bat.replaceFirst( "&q", "&nbsp;");
		at_bat = at_bat.replaceFirst( "&r", "&nbsp;");
		at_bat = at_bat.replaceFirst( "&s", "&nbsp;");
		at_bat = at_bat.replaceFirst( "&t", "&nbsp;");
		at_bat = at_bat.replaceFirst( "&u", "&nbsp;");
		at_bat = at_bat.replaceFirst( "&v", "&nbsp;");
		at_bat = at_bat.replaceFirst( "&w", player.getAb()[KEY_WORDS.CATCHER.intValue()] != 0 ? Integer.toString( player.getAb()[KEY_WORDS.CATCHER.intValue()]) : "&nbsp;");
		at_bat = at_bat.replaceFirst( "&x", "&nbsp;");
		at_bat = at_bat.replaceFirst( "&y", "&nbsp;");
		
		String hit = field.toString();
		hit = hit.replaceFirst( "&a", "&nbsp;");
		hit = hit.replaceFirst( "&b", "&nbsp;");
		hit = hit.replaceFirst( "&c", player.getHit()[KEY_WORDS.CENTER_FIELD.intValue()] != 0 ? Integer.toString( player.getHit()[KEY_WORDS.CENTER_FIELD.intValue()]) : "&nbsp;");
		hit = hit.replaceFirst( "&d", "&nbsp;");
		hit = hit.replaceFirst( "&e", "&nbsp;");
		hit = hit.replaceFirst( "&f", player.getHit()[KEY_WORDS.LEFT_FIELD.intValue()] != 0 ? Integer.toString( player.getHit()[KEY_WORDS.LEFT_FIELD.intValue()]) : "&nbsp;");
		hit = hit.replaceFirst( "&g", player.getHit()[KEY_WORDS.SHORTSTOP.intValue()] != 0 ? Integer.toString( player.getHit()[KEY_WORDS.SHORTSTOP.intValue()]) : "&nbsp;");
		hit = hit.replaceFirst( "&h", "&nbsp;");
		hit = hit.replaceFirst( "&i", player.getHit()[KEY_WORDS.SECOND_BASE.intValue()] != 0 ? Integer.toString( player.getHit()[KEY_WORDS.SECOND_BASE.intValue()]) : "&nbsp;");
		hit = hit.replaceFirst( "&j", player.getHit()[KEY_WORDS.RIGHT_FIELD.intValue()] != 0 ? Integer.toString( player.getHit()[KEY_WORDS.RIGHT_FIELD.intValue()]) : "&nbsp;");
		hit = hit.replaceFirst( "&k", "&nbsp;");
		hit = hit.replaceFirst( "&l", player.getHit()[KEY_WORDS.THIRD_BASE.intValue()] != 0 ? Integer.toString( player.getHit()[KEY_WORDS.THIRD_BASE.intValue()]) : "&nbsp;");
		hit = hit.replaceFirst( "&m", player.getHit()[KEY_WORDS.PITCHER.intValue()] != 0 ? Integer.toString( player.getHit()[KEY_WORDS.PITCHER.intValue()]) : "&nbsp;");
		hit = hit.replaceFirst( "&z", player.getHit()[KEY_WORDS.FIRST_BASE.intValue()] != 0 ? Integer.toString( player.getHit()[KEY_WORDS.FIRST_BASE.intValue()]) : "&nbsp;");
		hit = hit.replaceFirst( "&o", "&nbsp;");
		hit = hit.replaceFirst( "&p", "&nbsp;");
		hit = hit.replaceFirst( "&q", "&nbsp;");
		hit = hit.replaceFirst( "&r", "&nbsp;");
		hit = hit.replaceFirst( "&s", "&nbsp;");
		hit = hit.replaceFirst( "&t", "&nbsp;");
		hit = hit.replaceFirst( "&u", "&nbsp;");
		hit = hit.replaceFirst( "&v", "&nbsp;");
		hit = hit.replaceFirst( "&w", player.getHit()[KEY_WORDS.CATCHER.intValue()] != 0 ? Integer.toString( player.getHit()[KEY_WORDS.CATCHER.intValue()]) : "&nbsp;");
		hit = hit.replaceFirst( "&x", "&nbsp;");
		hit = hit.replaceFirst( "&y", "&nbsp;");
		
		String html_player = template.toString()
				.replaceFirst( "&NOM", player.getName())
				.replaceFirst( "&JERSEY", player.getJersey())
				// .replaceFirst( ", &DEFENSE", null != player.getPosition() && player.getPosition().length() > 0 ? ", " + player.getPosition() : "")
				.replaceFirst( "&DEFENSE", player.getFieldPreference())
				.replaceFirst( "&KL", Integer.toString( player.getAb()[KEY_WORDS.K_LOOKING.intValue()]))
				.replaceFirst( "&KS", Integer.toString( player.getAb()[KEY_WORDS.K_SWINGING.intValue()]))
				.replaceFirst( "&BB", Integer.toString( player.getAb()[KEY_WORDS.WALK.intValue()]))
				.replaceFirst( "&SAC", Integer.toString( player.getAb()[KEY_WORDS.SACRIFICE.intValue()]))
				.replaceFirst( "&ATBAT", at_bat)
				.replaceFirst( "&HIT", hit)
				.replaceFirst( "&AVG", player.getAVG());
		
		return html_player;
	}
	

	private static String generateHTML( Player player1, Player player2) {

		StringBuffer template = new StringBuffer()
				.append( "<table class=\"player\">")
				.append( "<tr>")
				.append( "<td class=\"name\">&1NOM<br>&1JERSEY<br>&1DEFENSE<br>KS=&1KS, KL=&1KL<br>BB=&1BB, SF/SH=&1SAC<br>AVG=&1AVG</td><td>&1ATBAT</td><td>&1HIT</td>")
				.append( "<td class=\"name\">&2NOM<br>&2JERSEY<br>&2DEFENSE<br>KS=&2KS, KL=&2KL<br>BB=&2BB, SF/SH=&2SAC<br>AVG=&2AVG</td><td>&2ATBAT</td><td>&2HIT</td>")
				.append( "</tr></table>");
				
		
		StringBuffer field = 
				new StringBuffer("<table class=\"field\"><tbody>")
				.append( "<tr><td >&a</td><td >&b</td><td >&c</td><td >&d</td><td >&e</td></tr>")
				.append( "<tr><td >&f</td><td >&g</td><td >&h</td><td >&i</td><td >&j</td></tr>")
				.append( "<tr><td >&k</td><td >&l</td><td >&m</td><td >&z</td><td >&o</td></tr>")
				.append( "<tr><td >&p</td><td >&q</td><td >&r</td><td >&s</td><td >&t</td></tr>")
				.append( "<tr><td >&u</td><td >&v</td><td >&w</td><td >&x</td><td >&y</td></tr>")
				.append( "</tbody></table>");
		

		String at_bat_player1 = field.toString();
		at_bat_player1 = at_bat_player1.replaceFirst( "&a", "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&b", "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&c", player1.getAb()[KEY_WORDS.CENTER_FIELD.intValue()] != 0 ? Integer.toString( player1.getAb()[KEY_WORDS.CENTER_FIELD.intValue()]) : "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&d", "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&e", "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&f", player1.getAb()[KEY_WORDS.LEFT_FIELD.intValue()] != 0 ? Integer.toString( player1.getAb()[KEY_WORDS.LEFT_FIELD.intValue()]) : "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&g", player1.getAb()[KEY_WORDS.SHORTSTOP.intValue()] != 0 ? Integer.toString( player1.getAb()[KEY_WORDS.SHORTSTOP.intValue()]) : "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&h", "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&i", player1.getAb()[KEY_WORDS.SECOND_BASE.intValue()] != 0 ? Integer.toString( player1.getAb()[KEY_WORDS.SECOND_BASE.intValue()]) : "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&j", player1.getAb()[KEY_WORDS.RIGHT_FIELD.intValue()] != 0 ? Integer.toString( player1.getAb()[KEY_WORDS.RIGHT_FIELD.intValue()]) : "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&k", "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&l", player1.getAb()[KEY_WORDS.THIRD_BASE.intValue()] != 0 ? Integer.toString( player1.getAb()[KEY_WORDS.THIRD_BASE.intValue()]) : "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&m", player1.getAb()[KEY_WORDS.PITCHER.intValue()] != 0 ? Integer.toString( player1.getAb()[KEY_WORDS.PITCHER.intValue()]) : "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&z", player1.getAb()[KEY_WORDS.FIRST_BASE.intValue()] != 0 ? Integer.toString( player1.getAb()[KEY_WORDS.FIRST_BASE.intValue()]) : "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&o", "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&p", "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&q", "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&r", "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&s", "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&t", "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&u", "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&v", "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&w", player1.getAb()[KEY_WORDS.CATCHER.intValue()] != 0 ? Integer.toString( player1.getAb()[KEY_WORDS.CATCHER.intValue()]) : "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&x", "&nbsp;");
		at_bat_player1 = at_bat_player1.replaceFirst( "&y", "&nbsp;");
		
		String hit_player1 = field.toString();
		hit_player1 = hit_player1.replaceFirst( "&a", "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&b", "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&c", player1.getHit()[KEY_WORDS.CENTER_FIELD.intValue()] != 0 ? Integer.toString( player1.getHit()[KEY_WORDS.CENTER_FIELD.intValue()]) : "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&d", "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&e", "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&f", player1.getHit()[KEY_WORDS.LEFT_FIELD.intValue()] != 0 ? Integer.toString( player1.getHit()[KEY_WORDS.LEFT_FIELD.intValue()]) : "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&g", player1.getHit()[KEY_WORDS.SHORTSTOP.intValue()] != 0 ? Integer.toString( player1.getHit()[KEY_WORDS.SHORTSTOP.intValue()]) : "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&h", "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&i", player1.getHit()[KEY_WORDS.SECOND_BASE.intValue()] != 0 ? Integer.toString( player1.getHit()[KEY_WORDS.SECOND_BASE.intValue()]) : "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&j", player1.getHit()[KEY_WORDS.RIGHT_FIELD.intValue()] != 0 ? Integer.toString( player1.getHit()[KEY_WORDS.RIGHT_FIELD.intValue()]) : "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&k", "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&l", player1.getHit()[KEY_WORDS.THIRD_BASE.intValue()] != 0 ? Integer.toString( player1.getHit()[KEY_WORDS.THIRD_BASE.intValue()]) : "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&m", player1.getHit()[KEY_WORDS.PITCHER.intValue()] != 0 ? Integer.toString( player1.getHit()[KEY_WORDS.PITCHER.intValue()]) : "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&z", player1.getHit()[KEY_WORDS.FIRST_BASE.intValue()] != 0 ? Integer.toString( player1.getHit()[KEY_WORDS.FIRST_BASE.intValue()]) : "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&o", "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&p", "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&q", "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&r", "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&s", "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&t", "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&u", "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&v", "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&w", player1.getHit()[KEY_WORDS.CATCHER.intValue()] != 0 ? Integer.toString( player1.getHit()[KEY_WORDS.CATCHER.intValue()]) : "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&x", "&nbsp;");
		hit_player1 = hit_player1.replaceFirst( "&y", "&nbsp;");
		

		String at_bat_player2 = field.toString();
		at_bat_player2 = at_bat_player2.replaceFirst( "&a", "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&b", "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&c", player2.getAb()[KEY_WORDS.CENTER_FIELD.intValue()] != 0 ? Integer.toString( player2.getAb()[KEY_WORDS.CENTER_FIELD.intValue()]) : "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&d", "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&e", "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&f", player2.getAb()[KEY_WORDS.LEFT_FIELD.intValue()] != 0 ? Integer.toString( player2.getAb()[KEY_WORDS.LEFT_FIELD.intValue()]) : "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&g", player2.getAb()[KEY_WORDS.SHORTSTOP.intValue()] != 0 ? Integer.toString( player2.getAb()[KEY_WORDS.SHORTSTOP.intValue()]) : "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&h", "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&i", player2.getAb()[KEY_WORDS.SECOND_BASE.intValue()] != 0 ? Integer.toString( player2.getAb()[KEY_WORDS.SECOND_BASE.intValue()]) : "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&j", player2.getAb()[KEY_WORDS.RIGHT_FIELD.intValue()] != 0 ? Integer.toString( player2.getAb()[KEY_WORDS.RIGHT_FIELD.intValue()]) : "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&k", "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&l", player2.getAb()[KEY_WORDS.THIRD_BASE.intValue()] != 0 ? Integer.toString( player2.getAb()[KEY_WORDS.THIRD_BASE.intValue()]) : "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&m", player2.getAb()[KEY_WORDS.PITCHER.intValue()] != 0 ? Integer.toString( player2.getAb()[KEY_WORDS.PITCHER.intValue()]) : "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&z", player2.getAb()[KEY_WORDS.FIRST_BASE.intValue()] != 0 ? Integer.toString( player2.getAb()[KEY_WORDS.FIRST_BASE.intValue()]) : "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&o", "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&p", "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&q", "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&r", "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&s", "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&t", "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&u", "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&v", "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&w", player2.getAb()[KEY_WORDS.CATCHER.intValue()] != 0 ? Integer.toString( player2.getAb()[KEY_WORDS.CATCHER.intValue()]) : "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&x", "&nbsp;");
		at_bat_player2 = at_bat_player2.replaceFirst( "&y", "&nbsp;");
		
		String hit_player2 = field.toString();
		hit_player2 = hit_player2.replaceFirst( "&a", "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&b", "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&c", player2.getHit()[KEY_WORDS.CENTER_FIELD.intValue()] != 0 ? Integer.toString( player2.getHit()[KEY_WORDS.CENTER_FIELD.intValue()]) : "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&d", "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&e", "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&f", player2.getHit()[KEY_WORDS.LEFT_FIELD.intValue()] != 0 ? Integer.toString( player2.getHit()[KEY_WORDS.LEFT_FIELD.intValue()]) : "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&g", player2.getHit()[KEY_WORDS.SHORTSTOP.intValue()] != 0 ? Integer.toString( player2.getHit()[KEY_WORDS.SHORTSTOP.intValue()]) : "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&h", "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&i", player2.getHit()[KEY_WORDS.SECOND_BASE.intValue()] != 0 ? Integer.toString( player2.getHit()[KEY_WORDS.SECOND_BASE.intValue()]) : "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&j", player2.getHit()[KEY_WORDS.RIGHT_FIELD.intValue()] != 0 ? Integer.toString( player2.getHit()[KEY_WORDS.RIGHT_FIELD.intValue()]) : "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&k", "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&l", player2.getHit()[KEY_WORDS.THIRD_BASE.intValue()] != 0 ? Integer.toString( player2.getHit()[KEY_WORDS.THIRD_BASE.intValue()]) : "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&m", player2.getHit()[KEY_WORDS.PITCHER.intValue()] != 0 ? Integer.toString( player2.getHit()[KEY_WORDS.PITCHER.intValue()]) : "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&z", player2.getHit()[KEY_WORDS.FIRST_BASE.intValue()] != 0 ? Integer.toString( player2.getHit()[KEY_WORDS.FIRST_BASE.intValue()]) : "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&o", "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&p", "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&q", "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&r", "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&s", "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&t", "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&u", "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&v", "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&w", player2.getHit()[KEY_WORDS.CATCHER.intValue()] != 0 ? Integer.toString( player2.getHit()[KEY_WORDS.CATCHER.intValue()]) : "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&x", "&nbsp;");
		hit_player2 = hit_player2.replaceFirst( "&y", "&nbsp;");
		
		String html_player = template.toString()
				.replaceFirst( "&1NOM", player1.getName())
				.replaceFirst( "&1JERSEY", player1.getJersey())
				// .replaceFirst( ", &1DEFENSE", null != player1.getPosition() && player1.getPosition().length() > 0 ? ", " + player1.getPosition() : "")
				.replaceFirst( "&1DEFENSE", player1.getFieldPreference())
				.replaceFirst( "&1KL", Integer.toString( player1.getAb()[KEY_WORDS.K_LOOKING.intValue()]))
				.replaceFirst( "&1KS", Integer.toString( player1.getAb()[KEY_WORDS.K_SWINGING.intValue()]))
				.replaceFirst( "&1BB", Integer.toString( player1.getAb()[KEY_WORDS.WALK.intValue()]))
				.replaceFirst( "&1SAC", Integer.toString( player1.getAb()[KEY_WORDS.SACRIFICE.intValue()]))
				.replaceFirst( "&1ATBAT", at_bat_player1)
				.replaceFirst( "&1HIT", hit_player1)
				.replaceFirst( "&1AVG", player1.getAVG())
				
				
				.replaceFirst( "&2NOM", player2.getName())
				.replaceFirst( "&2JERSEY", player2.getJersey())
				// .replaceFirst( ", &2DEFENSE", null != player2.getPosition() && player2.getPosition().length() > 0 ? ", " + player2.getPosition() : "")
				.replaceFirst( "&2DEFENSE", player2.getFieldPreference())
				.replaceFirst( "&2KL", Integer.toString( player2.getAb()[KEY_WORDS.K_LOOKING.intValue()]))
				.replaceFirst( "&2KS", Integer.toString( player2.getAb()[KEY_WORDS.K_SWINGING.intValue()]))
				.replaceFirst( "&2BB", Integer.toString( player2.getAb()[KEY_WORDS.WALK.intValue()]))
				.replaceFirst( "&2SAC", Integer.toString( player2.getAb()[KEY_WORDS.SACRIFICE.intValue()]))
				.replaceFirst( "&2ATBAT", at_bat_player2)
				.replaceFirst( "&2HIT", hit_player2)
				.replaceFirst( "&2AVG", player2.getAVG());
		
		return html_player;
	}

	private static String generateFooterHtml() {
		
		StringBuffer footer = new StringBuffer()
				.append("</td></tr></table></body></html>");
		
		return footer.toString();
		
	}
}
