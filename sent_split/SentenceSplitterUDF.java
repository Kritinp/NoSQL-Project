import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
import java.util.Properties;

public class SentenceSplitterUDF extends EvalFunc<String> {
    private static StanfordCoreNLP pipeline;

    public SentenceSplitterUDF() {
        // Initialize Stanford CoreNLP pipeline
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit");
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

            // Retrieve the sentences
            StringBuilder output = new StringBuilder();
            for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
                String sentenceText = sentence.get(CoreAnnotations.TextAnnotation.class);
                output.append(sentenceText).append("\n");
            }

            return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
