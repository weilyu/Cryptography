package parsingExportData;

import org.apache.commons.csv.*;
import edu.duke.*;

public class ParsingExportData {

	public static void main(String[] args) {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		bigExporters(parser, "$999,999,999,999");
	}

	public static void listExplorers(CSVParser parser, String exportOfInterest) {
		int count = 0;
		for (CSVRecord record : parser) {
			String export = record.get("Exports");
			if (export.indexOf(exportOfInterest) != -1) {
				String country = record.get("Country");
				System.out.println(country);
				count++;
			}
		}
		System.out.println("There are " + count + " countries export " + exportOfInterest);
	}

	public static String countryInfo(CSVParser parser, String country) {
		String result = null;
		for (CSVRecord record : parser) {
			String countryInCsv = record.get("Country");
			if (countryInCsv.equals(country)) {
				result = countryInCsv + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
				break;
			}
		}
		if (result == null) {
			return "NOT FOUND";
		} else {
			return result;
		}
	}

	public static void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
		for (CSVRecord record : parser) {
			String export = record.get("Exports");
			if (export.contains(exportItem1) && export.contains(exportItem2)) {
				System.out.println(record.get("Country"));
			}
		}
	}

	public static void bigExporters(CSVParser parser, String amount) {
		for (CSVRecord record : parser) {
			String amountInCsv = record.get("Value (dollars)");
			if (amountInCsv.length() > amount.length()) {
				System.out.println(record.get("Country") + " " + amountInCsv);
			}
		}
	}
}
