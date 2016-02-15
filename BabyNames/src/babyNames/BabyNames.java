package babyNames;

import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class BabyNames {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getTotalBirthsRankedHigher(1990, "Drew", "M"));
	}

	public static void printNames() {
		FileResource fr = new FileResource();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			System.out.println("Name " + rec.get(0) + " Gender " + rec.get(1) + " Num Born " + rec.get(2));
		}
	}

	public static void totalBirth(FileResource fr) {
		int total = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		int totalBoyNames = 0;
		int totalGirlNames = 0;
		CSVParser parser = fr.getCSVParser(false);
		for (CSVRecord rec : parser) {
			int thisNum = Integer.parseInt(rec.get(2));
			total += thisNum;
			if (rec.get(1).equals("F")) {
				totalGirls += thisNum;
				totalGirlNames++;
			} else {
				totalBoys += thisNum;
				totalBoyNames++;
			}
		}

		System.out.println("Total: " + total);
		System.out.println("Total Boys: " + totalBoys);
		System.out.println("Total Girls: " + totalGirls);
		System.out.println("Total Boy Names: " + totalBoyNames);
		System.out.println("Total Girl Names: " + totalGirlNames);
	}

	public static void testTotalBirth() {
		FileResource fr = new FileResource("yob1880.csv");
		totalBirth(fr);
	}

	public static int getRank(int year, String name, String gender) {
		int rank = 0;
		boolean nameExist = false;
		String fname = "yob" + year + ".csv";
		FileResource fr = new FileResource(fname);
		CSVParser parser = fr.getCSVParser(false);
		for (CSVRecord rec : parser) {
			if (rec.get(1).equals(gender)) {
				rank++;
				if (rec.get(0).equals(name)) {
					nameExist = true;
					break;
				}
			}
		}
		if (nameExist) {
			return rank;
		} else {
			return -1;
		}
	}

	public static void testGetRank() {
		System.out.println(getRank(2012, "Atharvafsserg", "M"));
	}

	public static String getName(int year, int rank, String gender) {
		String name = null;
		int order = 0;
		String fname = "yob" + year + ".csv";
		FileResource fr = new FileResource(fname);
		CSVParser parser = fr.getCSVParser(false);
		for (CSVRecord rec : parser) {
			if (rec.get(1).equals(gender)) {
				order++;
			}
			if (order == rank) {
				name = rec.get(0);
			}
		}
		if (name == null) {
			return "NO NAME";
		} else {
			return name;
		}
	}

	public static void testGetName() {
		System.out.println(getName(2012, 212312312, "M"));
	}

	public static void whatIsNameInYear(String name, int year, int newYear, String gender) {
		int rank = getRank(year, name, gender);
		String newName = getName(newYear, rank, gender);
		if (gender.equals("F")) {
			System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
		} else {
			System.out.println(name + " born in " + year + " would be " + newName + " if he was born in " + newYear);
		}
	}

	public static int yearOfHighestRank(String name, String gender) {
		int yohr = 0;
		int highestRankSoFar = 999999999;
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			String fname = f.getName();
			int year = Integer.parseInt(fname.substring(3, 7));
			int thisRank = getRank(year, name, gender);
			if (thisRank < highestRankSoFar & thisRank != -1) {
				highestRankSoFar = thisRank;
				yohr = year;
			}
		}
		if (yohr != 0) {
			return yohr;
		} else {
			return -1;
		}

	}

	public static double getAverageRank(String name, String gender) {
		int count = 0;
		int totalRank = 0;
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			String fname = f.getName();
			int year = Integer.parseInt(fname.substring(3, 7));
			int thisRank = getRank(year, name, gender);
			if (thisRank != -1) {
				count++;
				totalRank += thisRank;
			}
		}
		if (count == 0) {
			return -1.0;
		} else {
			return (double) totalRank / count;
		}

	}

	public static int getTotalBirthsRankedHigher(int year, String name, String gender) {
		int total = 0;
		String fname = "yob" + year + ".csv";
		//FileResource fr = new FileResource();
		FileResource fr = new FileResource(fname);
		CSVParser parser = fr.getCSVParser(false);
		for (CSVRecord rec : parser) {
			if (rec.get(1).equals(gender)) {
				if (rec.get(0).equals(name)) {
					break;
				}
				total += Integer.parseInt(rec.get(2));
			}
		}
		return total;
	}
}
