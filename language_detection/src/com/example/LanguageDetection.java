package com.example;

import java.io.IOException;
import com.cybozu.labs.langdetect.*;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
import org.apache.hadoop.io.*;

public class LanguageDetection extends EvalFunc<String> {
    public String exec(Tuple input) throws IOException {
        // String text = "This is an example text in English.";

        if (input == null || input.size() == 0) {
            return null;
        }
		String text = (String) input.get(0);
        String out = "";
        try {
            DetectorFactory.loadProfile("language_detection/profiles");
            Detector detector = DetectorFactory.create();
            detector.append(text);
            // System.out.println(detector.detect());
            out = detector.detect();
            DetectorFactory.clear();
            // return out;
        } catch (LangDetectException e) {
            out = e.getMessage();
            System.out.println("Error detecting language: " + e.getMessage());
        }
        return out;
    }
}


// package com.example;

// import java.io.IOException;
// import com.cybozu.labs.langdetect.*;

// // import org.apache.pig.EvalFunc;
// // import org.apache.pig.data.Tuple;
// // import org.apache.hadoop.io.*;

// public class LanguageDetection{
//     public static String detect(String text){
//         // String text = "This is an example text in English.";

//         // if (input == null || input.size() == 0) {
//         //     return null;
//         // }
// 		// String text = (String) input.get(0);

//         try {
//             DetectorFactory.loadProfile("profiles");
//             Detector detector = DetectorFactory.create();
//             detector.append(text);
//             // System.out.println(detector.detect());
//             String out = detector.detect();
//             DetectorFactory.clear();
//             return out;
//         } catch (LangDetectException e) {
//             System.out.println("Error detecting language: " + e.getMessage());
//         }
//         return "nan";
//     }

//     public static void main(String args[]){
//         System.out.println(detect("The quick brown fox jumps over the lazy dog. The five boxing wizards jump quickly."));
//         System.out.println(detect("El Quijote de la Mancha es una obra literaria escrita por Miguel de Cervantes. Es considerada una de las obras más importantes de la literatura española."));
//         System.out.println(detect("La vie est belle, il faut savoir en profiter. Les petits plaisirs de la vie sont souvent les plus importants."));
//         System.out.println(detect("Die deutsche Sprache ist eine der wichtigsten Sprachen in Europa. Sie wird von über 100 Millionen Menschen als Muttersprache gesprochen."));
//         System.out.println(detect("O Brasil é um país com uma rica diversidade cultural e natural. As praias brasileiras são famosas em todo o mundo."));
//     }
// }