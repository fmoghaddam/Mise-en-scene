package main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.FileUtil;

/**
 * This class find the corresponding Id for mese-en-sene dataset
 * @author fbm
 *
 */
public class ExtractImdbIds {

	private static final String MOVELENS_IDS_INOUT = "data/AllMovieLensIds";
	private static final String MAPPING_FILE_INPUT = "data/links.csv";
	public static final String OUTPUT = "data/MovieLensImdbIdMapping.csv";

	public static void main(String[] args) {
		final List<String> movieLensIds = loadAllMovieLensId(MOVELENS_IDS_INOUT);
		final Map<String,String> movieLensIMDBIdMapping = loadMovieLensIMDBIdMapping(MAPPING_FILE_INPUT);
		
		final List<String> result = new ArrayList<>();
		result.add("movieId,imdbId");
		for(String mlId:movieLensIds) {
			String imdbId = movieLensIMDBIdMapping.get(mlId);
			if(imdbId!=null) {
				result.add(mlId+","+imdbId);
			}
		}
		
		FileUtil.writeDataToFile(result, OUTPUT, false);
	}

	public static Map<String, String> loadMovieLensIMDBIdMapping(final String file) {
		final Map<String,String> map = new HashMap<>();
		try (final BufferedReader br = new BufferedReader(new FileReader(file))) {
			String sCurrentLine;
			//Ignore the first line
			br.readLine();
			while ((sCurrentLine = br.readLine()) != null) {
				String[] split = sCurrentLine.split(",");
				String moveLensId = split[0];
				String imdbId = split[1];
				map.put(moveLensId, imdbId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	private static List<String> loadAllMovieLensId(String file) {
		try {
			return Files.readAllLines(Paths.get(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
