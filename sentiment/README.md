1. Create class file: $  javac -cp .:sentiment/lib/* -d sentiment/build sentiment/NlpEstimatingSentiment.java

2. Complile class file into jar: $ jar cvfm sentiment/nlpestimatingsentiment.jar sentiment/MANIFEST.MF -C sentiment/build .

3. run the script.pig file on the grunt shell