package TEST;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import main.CrawlMovieInformationFromImdb;
import model.Data;

public class Test {

	public static void main(String[] args) throws IOException {
		final File[] listOfFiles = new File(CrawlMovieInformationFromImdb.OUTPUT).listFiles();
		for(File file:listOfFiles) {
			List<String> readAllLines = Files.readAllLines(file.toPath());
			Data fromJson = Data.fromJson(readAllLines.get(0));
			//System.err.println(fromJson.toPrettyJson());
		}
	}

}
