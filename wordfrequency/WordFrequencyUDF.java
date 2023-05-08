import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
// import org.apache.pig.impl.logicalLayer.schema.Schema;

public class WordFrequencyUDF extends EvalFunc<String> {
  
  @Override
  public String exec(Tuple input) throws IOException {
    if (input == null || input.size() == 0) {
      return null;
    }
    
    String document = (String) input.get(0);
    String[] words = document.toLowerCase().split("\\s+"); // Split on whitespace
    
    Map<String, Integer> wordFreq = new HashMap<>();
    
    for (String word : words) {
      if (wordFreq.containsKey(word)) {
        wordFreq.put(word, wordFreq.get(word) + 1);
      } else {
        wordFreq.put(word, 1);
      }
    }

    StringBuilder result = new StringBuilder();
    for (Map.Entry<String, Integer> entry : wordFreq.entrySet()) {
        result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
    }
    
    return result.toString();
  }
}
