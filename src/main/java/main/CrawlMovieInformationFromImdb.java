package main;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import model.Request;
import util.FileUtil;

/**
 * This class fetch information for each movie from IMDB website
 * @author fbm
 *
 */
public class CrawlMovieInformationFromImdb {

	/**
	 * Gmail
	 */
	//private static final String URL_BASE = "http://www.omdbapi.com/?i=ttID&apikey=f87a5d49";
	/**
	 * Yahoo
	 */
	private static final String URL_BASE = "http://www.omdbapi.com/?i=tt3896198&apikey=b5a53861";
	
	
	public static final String OUTPUT = "data/movieInformation";

	private static final List<String> allreadyProccessmovies = loadAlreadyProcessedMovies(); 

	public static void main(String[] args) throws InterruptedException {
		FileUtil.createFolder(OUTPUT);
		final List<String> imdbIds = new ArrayList<>(ExtractImdbIds.loadMovieLensIMDBIdMapping(ExtractImdbIds.OUTPUT).values());
		for(String id:imdbIds) {
			if(!allreadyProccessmovies.contains(id)) {
				handle(id);
			}			
		}
	}

	private static List<String> loadAlreadyProcessedMovies() {
		final File[] listOfFiles = new File(OUTPUT).listFiles();
		final List<String> result = new ArrayList<>();
		for(File file:listOfFiles) {
			result.add(file.getName());
		}
		return result;
	}

	/**
	 * This function make a result, get a json and save it for each movie
	 * @param id
	 */
	private static void handle(String id) {
		final String url = URL_BASE.replace("ID", id);
		final Request managerRequest = new Request();
		managerRequest.setQuery(url);
		String jsonResult = Caller.run(managerRequest);
		if(jsonResult!=null && !jsonResult.contains("Invalid API key!")) {
			FileUtil.writeDataToFile(jsonResult, OUTPUT+File.separator+id, false);
		}
	}

}
