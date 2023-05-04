// package porter_stemmer.src.main.java;
import java.io.*;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
import org.apache.hadoop.io.*;

public class Stem extends EvalFunc<String> {
	public String exec(Tuple input) throws IOException{
		if (input == null || input.size() == 0) {
            return null;
        }
	   char[] w = new char[501];
	   Stemmer s = new Stemmer();
	//    for (int i = 0; i < args.length; i++)

		String text = (String) input.get(0);
		int i = 0;
		String out = "";
		while(i < text.length()){
			int ch = text.charAt(i++);
			// i++;
			if(Character.isLetter((char) ch)){
				int j = 0;
				while(true){
					ch = Character.toLowerCase((char) ch);
					w[j] = (char) ch;
					if (j < 500) j++;
					ch = text.charAt(i++);
					if (!Character.isLetter((char) ch)){
						for (int c = 0; c < j; c++)
							s.add(w[c]);
						s.stem();
						out += s.toString();
						break;
					}

				}
			}
			if(i > text.length())
				break;
			out += (char)ch;
		}

		return out;
	}
}