//nlpPipeline.java
import java.io.IOException;
import java.util.Properties;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
// import org.apache.pig.impl.util.UDFContext;


public class NlpEstimatingSentiment extends EvalFunc<String> {
//     static StanfordCoreNLP pipeline;
//     public static void init() 
//     {
//         Properties props = new Properties();
//         props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
//         pipeline = new StanfordCoreNLP(props);
//     }
//     public static void estimatingSentiment(String text)
//     {
//    int sentimentInt;
//       String sentimentName; 
//       Annotation annotation = pipeline.process(text);
//       for(CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class))
//       {
//          Tree tree = sentence.get(SentimentAnnotatedTree.class);
//         sentimentInt = RNNCoreAnnotations.getPredictedClass(tree); 
//                 sentimentName = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
//         System.out.println(sentimentName + "\t" + sentimentInt + "\t" + sentence);
//       }
//      }

     public String exec(Tuple input) throws IOException {
        if (input == null || input.size() == 0) {
            return null;
        }
        try {
            String text = (String) input.get(0);
            Properties props = new Properties();
            props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
            StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
            Annotation annotation = pipeline.process(text);
            StringBuilder sb = new StringBuilder();
            for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
                Tree tree = sentence.get(SentimentAnnotatedTree.class);
                int sentimentInt = RNNCoreAnnotations.getPredictedClass(tree);
                String sentimentName = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
                sb.append(sentimentName).append("\t").append(sentimentInt).append("\t").append(sentence.toString()).append("\n");
            }
            return sb.toString().trim();
        } catch (Exception e) {
            throw new IOException("Caught exception processing input row ", e);
        }
    }
    
}