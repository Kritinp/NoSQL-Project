import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.io.File;

import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;

import org.apache.hadoop.io.*;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

public class POSTaggerUDF extends EvalFunc<String> {
    // private static final String MODEL_FILE = "/home/avi/Desktop/college/sem8/nosql/project/src/test/lib/opennlp-en-ud-ewt-pos-1.0-1.9.3.bin";
    // private static final Tokenizer TOKENIZER = SimpleTokenizer.INSTANCE;
    // private static final POSModel MODEL;

    POSModel model = new POSModelLoader().load(new File("./pos_tagging/lib/opennlp-en-ud-ewt-pos-1.0-1.9.3.bin")); //Edit path to the pre-trained model file
    POSTaggerME tagger = new POSTaggerME(model);
    SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;

    // static {
    //     try {
    //         MODEL = new POSModel(POSTaggerUDF.class.getResourceAsStream(MODEL_FILE));
    //     } catch (IOException e) {
    //         throw new RuntimeException("Failed to load POS model: " + MODEL_FILE, e);
    //     }
    // }

    public String exec(Tuple input) throws IOException {
        if (input == null || input.size() == 0) {
            return null;
        }

        String text = (String) input.get(0);
        
        String[] tokenline = tokenizer.tokenize(text);
        String[] tags = tagger.tag(tokenline);
        POSSample sample = new POSSample(tokenline, tags); 
        
        // String[] words = tokenizer.tokenize(text);
        // POSTaggerME tagger = new POSTaggerME(MODEL);
        // List<String> tags = Arrays.asList(tagger.tag(words));

        String out = "";
        for (String token : sample.getTags()) {
            //                System.out.println(token);
            out += token + " ";
            // context.write(word, new IntWritable(1));
        }

        return out;
        // StringBuilder sb = new StringBuilder();
        // for (int i = 0; i < words.length; i++) {
        //     sb.append(words[i]).append("_").append(tags.get(i)).append(" ");
        // }

        // return sb.toString().trim();
    }
}