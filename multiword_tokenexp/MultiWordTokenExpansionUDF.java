import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
import java.util.Properties;

public class MultiWordTokenExpansionUDF extends EvalFunc<String> {
    private static StanfordCoreNLP pipeline;

    public MultiWordTokenExpansionUDF() {
        // Initialize Stanford CoreNLP pipeline
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize");
        pipeline = new StanfordCoreNLP(props);
    }

    public String exec(Tuple input) {
        if (input == null || input.size() == 0) {
            return null;
        }

        try {
            // Get the input text from the tuple
            String text = (String) input.get(0);

            // Create an Annotation object
            Annotation annotation = new Annotation(text);

            // Run the pipeline on the input text
            pipeline.annotate(annotation);

            // Expand multi-word tokens
            StringBuilder output = new StringBuilder();
            for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
                for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                    String word = token.word();
                    output.append(word).append(" ");
                }
            }

            return output.toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

