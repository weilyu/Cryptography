package cryptography;

import java.io.File;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

//统计指定多个TXT文档中常见单词的数量
public class CommonWords {

	public String[] getCommon() {
		FileResource fr = new FileResource("data/common.txt");
		String[] common = new String[20];
		int index = 0;
		for (String s : fr.words()) {
			common[index] = s;
			index++;
		}
		return common;
	}
	
	public void countNumber() {
		DirectoryResource dr = new DirectoryResource();
		int[] count = new int[20];
		String[] commonWords = getCommon();
		for (File f : dr.selectedFiles() ) {
			FileResource fr = new FileResource(f);
			for (int i =0; i<20; i++) {
				for (String word : fr.words()) {
					if(word.equals(commonWords[i])) {
						count[i]++;
					}
				}
			}
				
			
		}
		for (int i = 0;i<20;i++) {
			System.out.println(commonWords[i] + " " + count[i]);
		}
		
	}
}
