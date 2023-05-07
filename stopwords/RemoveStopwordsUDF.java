import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

public class RemoveStopwordsUDF extends EvalFunc<String> {
  
  private Set<String> stopwords;
  
  public RemoveStopwordsUDF() {
    stopwords = new HashSet<String>();
    stopwords.add("the");
    stopwords.add("and");
    stopwords.add("a");
    stopwords.add("is");
    // Add more stopwords as needed
  }
  
  public String exec(Tuple input) throws IOException {
    if (input == null || input.size() == 0 || input.get(0)==null)
      return null;
    
    try {
      String text = (String) input.get(0);
      
      // Tokenize the input text into individual words
      String[] words = text.toLowerCase().split("\\s+");
      
      // Remove stopwords from the words array
      StringBuilder result = new StringBuilder();
      for (String word : words) {
        if (!stopwords.contains(word)) {
          result.append(word);
          result.append(" ");
        }
      }
      
      return result.toString().trim();
      
    } catch (Exception e) {
      throw new IOException("Error processing input tuple", e);
    }
  }
}
