// package JAVA.EASY.Text_File_Analyzer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.nio.file.Paths;

public class TextFileAnalyzer {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the file path: ");
    String filePath = sc.nextLine();

    int lines = 0;
    int words = 0;
    int characters = 0;

    HashMap<String, Integer> wordFrequency = new HashMap<>();
    try(BufferedReader reader = new BufferedReader(new FileReader(Paths.get(filePath).toFile()))) {
      String line;
      while((line = reader.readLine()) != null) {
        lines++;
        characters += line.length();

        if(line.trim().isEmpty()) {
          continue; // Skip empty lines
        }

        String[] tempWords = line.split("\\s+");
        words += tempWords.length;
        for(String word : tempWords) {
          word = word.toLowerCase().replaceAll("[^a-zA-Z]", ""); // Normalize the word
          if(!word.isEmpty()) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
          }
        }
      }

      String mostFrequentWord = null;
      int maxFrequency = 0;
      for(String word : wordFrequency.keySet()) {
        int frequency = wordFrequency.get(word);
        if(frequency > maxFrequency) {
          maxFrequency = frequency;
          mostFrequentWord = word;
        }
      }

    System.out.println("Lines: " + lines);
    System.out.println("Words: " + words);
    System.out.println("Characters: " + characters);
    System.out.println("Word Frequency: " + wordFrequency);

      if(maxFrequency > 0) {
        System.out.println("Most Frequent Word: '" + mostFrequentWord + "' with frequency: " + maxFrequency);
      } else {
        System.out.println("No words found in the file.");
      }

    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
    }
    sc.close();
  }
}
