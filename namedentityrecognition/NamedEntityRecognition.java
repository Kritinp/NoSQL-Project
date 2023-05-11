import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

import java.util.List;

public class NamedEntityRecognition extends EvalFunc<String> {
    private CRFClassifier<CoreLabel> classifier;

    public NamedEntityRecognition() {
        // Initialize the classifier for Named Entity Recognition
        String serializedClassifier = "namedentityrecognition/english.all.3class.distsim.crf.ser.gz";
        classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
    }

    @Override
    public String exec(Tuple input) {
        if (input == null || input.size() == 0) {
            return null;
        }

        try {
            // Retrieve the input text from the tuple
            String text = (String) input.get(0);

            // Perform Named Entity Recognition on the input text
            List<List<CoreLabel>> entityAnnotations = classifier.classify(text);

            // Extract named entities from the annotations
            StringBuilder namedEntities = new StringBuilder();
            for (List<CoreLabel> sentence : entityAnnotations) {
                for (CoreLabel entity : sentence) {
                    if (entity.get(CoreAnnotations.AnswerAnnotation.class).equals("PERSON")
                            || entity.get(CoreAnnotations.AnswerAnnotation.class).equals("LOCATION")
                            || entity.get(CoreAnnotations.AnswerAnnotation.class).equals("ORGANIZATION")) {
                        namedEntities.append(entity.word()).append(" ");
                    }
                }
            }

            return namedEntities.toString().trim();
        } catch (Exception e) {
            // Handle any exceptions that occur during processing
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }
}
