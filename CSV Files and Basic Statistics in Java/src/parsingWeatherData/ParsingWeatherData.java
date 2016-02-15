package parsingWeatherData;

import org.apache.commons.csv.*;
import java.io.*;
import edu.duke.*;

public class ParsingWeatherData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		fileWithColdestTemperature();
	}

	public static CSVRecord hottestHourInFile(CSVParser parser) {
		CSVRecord largestSoFar = null;
		for (CSVRecord currentRow : parser) {
			if (largestSoFar == null) {
				largestSoFar = currentRow;
			} else {
				largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
			}
		}
		return largestSoFar;
	}

	public static CSVRecord hottestInManyDays() {
		CSVRecord largestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
			if (largestSoFar == null) {
				largestSoFar = currentRow;
			} else {
				largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
			}
		}
		return largestSoFar;
	}

	public static CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
		double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
		double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
		if (currentTemp > largestTemp) {
			largestSoFar = currentRow;
		}
		return largestSoFar;
	}

	public static CSVRecord coldestHourInFile(CSVParser parser) {
		CSVRecord lowestSoFar = null;
		double lowestTemp = 999999;
		for (CSVRecord currentRow : parser) {
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			if (currentTemp != -9999 & currentTemp < lowestTemp) {
				lowestTemp = currentTemp;
				lowestSoFar = currentRow;
			}
		}
		return lowestSoFar;
	}

	public static CSVRecord getLowestOfTwo(CSVRecord currentRow, CSVRecord lowestSoFar) {
		double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
		double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));
		if (lowestTemp == -9999) {
			lowestSoFar = currentRow;
		}
		if (currentTemp < lowestTemp && currentTemp != -9999) {
			lowestSoFar = currentRow;
		}
		return lowestSoFar;
	}

	public static void testColdestHourInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		CSVRecord coldest = coldestHourInFile(parser);
		System.out.println(coldest.get("TemperatureF") + " at " + coldest.get("TimeEST"));
	}

	public static void fileWithColdestTemperature() {
		String fileName = null;
		CSVRecord lowestSoFar = null;
		double lowestTemp = 99999;
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			if (currentTemp < lowestTemp) {
				lowestTemp = currentTemp;
				lowestSoFar = currentRow;
				fileName = f.getName();
			}
			/*
			if (lowestSoFar == null) {
				lowestSoFar = currentRow;
				fileName = f.getName();
				selectedFr = fr;
			} else {
				lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar);
				fileName = f.getName();
				selectedFr = fr;
			}
			*/
		}
		System.out.println("Coldest day was in file " + fileName);
		System.out.println("Coldest temperature on that day was " + lowestSoFar.get("TemperatureF"));
		System.out.println("All the Temperatures on the coldest day were:");
		
	}

	public static void testLowestHumidityInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		CSVRecord csv = lowestHumidityInFile(parser);
		System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
	}

	public static CSVRecord lowestHumidityInFile(CSVParser parser) {
		CSVRecord lowestSoFarRecord = null;
		double lowestSoFar = 101;
		for (CSVRecord r : parser) {
			double currentHumidity;
			if (! r.get("Humidity").equals("N/A")) {
				currentHumidity = Double.parseDouble(r.get("Humidity"));
			} else {
				currentHumidity = 101;
			}
			if (currentHumidity < lowestSoFar) {
				lowestSoFar = currentHumidity;
				lowestSoFarRecord = r;
			}
		}
		return lowestSoFarRecord;

	}

	public static void testLowestHumidityInManyFiles() {
		CSVRecord cvr = lowestHumidityInManyFiles();
		System.out.println("Lowest Humidity was " + cvr.get("Humidity") + " at " + cvr.get("DateUTC"));
	}

	public static CSVRecord lowestHumidityInManyFiles() {
		CSVRecord lowestRecordSoFar = null;
		double lowestHumiditySoFar = 101;
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVParser parser = fr.getCSVParser();
			CSVRecord record = lowestHumidityInFile(parser);
			double currentHumidity = Double.parseDouble(record.get("Humidity"));
			if (currentHumidity < lowestHumiditySoFar) {
				lowestHumiditySoFar = currentHumidity;
				lowestRecordSoFar = record;
			}
		}
		return lowestRecordSoFar;
	}

	public static void testAverageTemperatureInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		double averageTemperature = averageTemperatureInFile(parser);
		System.out.println("Average temperature in file is " + averageTemperature);
	}

	public static double averageTemperatureInFile(CSVParser parser) {
		int count = 0;
		double tempAcc = 0;
		for (CSVRecord rec : parser) {
			tempAcc = tempAcc + Double.parseDouble(rec.get("TemperatureF"));
			count++;
		}
		return tempAcc / count;
	}

	public static void testAverageTemperatureWithHighHumidityInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		double avgTem = averageTemperatureWithHighHumidityInFile(parser, 80);
		if (avgTem == -9999) {
			System.out.println("No temperatures with that humidity");
		} else {
			System.out.println("Average Temp when high Humidity is " + avgTem);
		}
	}

	public static double averageTemperatureWithHighHumidityInFile(CSVParser parser, double value) {
		int count = 0;
		double tempAcc = 0;
		for (CSVRecord rec : parser) {
			double currentHumidity;
			if (! rec.get("Humidity").equals("N/A")) {
				currentHumidity = Double.parseDouble(rec.get("Humidity"));
			} else {
				currentHumidity = -1;
			}
			double currentTemp = Double.parseDouble(rec.get("TemperatureF"));
			if (currentHumidity > value && currentTemp != -9999) {
				tempAcc = tempAcc + currentTemp;
				count++;
			}
		}
		if (count == 0) {
			return -9999;
		} else {
			return tempAcc / count;
		}
	}

}
