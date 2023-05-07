1. Create class file: $  javac -cp .:lib/* -d build NlpEstimatingSentiment.java

2. Complile class file into jar: $ jar cvfm nlpestimatingsentiment.jar MANIFEST.MF -C build .

3. run the script.pig file on the grunt shell