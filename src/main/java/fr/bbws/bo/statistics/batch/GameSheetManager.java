
package fr.bbws.bo.statistics.batch;


import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;

public class GameSheetManager {

	static final Path ROOT = Paths.get("/Users/alexandrelods/Documents/Developpement/bbws/Games");
	
	static final String DESTINATION = "/Users/alexandrelods/Documents/Developpement/bbws/Games/tmp";
	
	static ArrayList<Path> directories = new ArrayList<Path>();
			
	public static void main(String[] args) {
		
		long begin = System.currentTimeMillis();
		
		directories.add( ROOT);
		
		
		System.out.println("##########  ------------------------ ##########");
		System.out.println("##########     GAME SHEET MANAGER    ##########");
		System.out.println("##########  ------------------------ ##########");
		
		for ( Path dir : directories) {
			
			try {
	
				DirectoryStream<Path> stream_dir = Files.newDirectoryStream(dir); // repertoire racine contenant l'ensemble des feuilles de score rangées dans des répertoires (année, niveau, pays)
				
				try {
					Iterator<Path> iterator_dir = stream_dir.iterator();
					
					while (iterator_dir.hasNext()) { // pour chaque repertoire du repertoire racine
						
						Path current_dir = iterator_dir.next();
						
						if (!current_dir.toString().endsWith("DS_Store") // pour eviter les pb sur MAC OS
								&& current_dir.toString().indexOf(".") < 0 // pour ne garder que les répertoires
								&& current_dir.toString().indexOf(DESTINATION) < 0) { // pour éviter le répertoire destination
							
							System.out.println("DEBUG -- " + current_dir);
							String[] current_dir_name = current_dir.toString().split(" ");
							String year = current_dir_name[1];
							String level = current_dir_name[2];
							String country = current_dir_name[3];
							
							DirectoryStream<Path> stream_file = Files.newDirectoryStream(current_dir); // repertoire racine contenant l'ensemble des feuilles de score rangées dans des répertoires (année, niveau, pays)
							
							try {
								Iterator<Path> iterator_file = stream_file.iterator();
								while (iterator_file.hasNext()) { // pour chaque fichier du repertoire
									Path current_file = iterator_file.next();
									System.out.println( "      -- copy from " + current_file.toString());
									
									Path output_file = Paths.get(DESTINATION + "/" + country + "_" + year + "_"+ level + "_" + current_file.getFileName());
									Files.copy(current_file, output_file, StandardCopyOption.REPLACE_EXISTING);
									System.out.println( "      -- to " + output_file.toString());
									System.out.println( "      -- done !");
								}
							} finally {
								stream_file.close();
							}
							
						}
					}
				} finally {
					stream_dir.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.println("##########  ------------------------ ##########");
		System.out.println("##########            FIN            ##########");
		System.out.println("##########  ------------------------ ##########");

		long time = System.currentTimeMillis() - begin;
		System.out.println("INFO -- Execution time = " + time);
		
	}


}
