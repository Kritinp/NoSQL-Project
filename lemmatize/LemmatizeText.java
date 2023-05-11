import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
import org.apache.pig.impl.util.UDFContext;

import java.util.Properties;

public class LemmatizeText extends EvalFunc<String> {
    private StanfordCoreNLP pipeline;

    public LemmatizeText() {
        // Initialize StanfordCoreNLP pipeline
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma");
        pipeline = new StanfordCoreNLP(props);
    }

    @Override
    public String exec(Tuple input) {
        if (input == null || input.size() == 0) {
            return null;
        }

        try {
            // Retrieve the input text from the tuple
            String text = (String) input.get(0);

            // Create an Annotation object with the input text
            Annotation document = new Annotation(text);

            // Run the pipeline on the document
            pipeline.annotate(document);

            // Extract lemmas from the annotated document
            StringBuilder lemmas = new StringBuilder();
            for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
                for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                    lemmas.append(token.get(CoreAnnotations.LemmaAnnotation.class)).append(" ");
                }
            }

            return lemmas.toString().trim();
        } catch (Exception e) {
            // Handle any exceptions that occur during processing
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }
}
