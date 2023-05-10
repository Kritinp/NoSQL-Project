1. Create class file: $  javac -cp .:lib/* -d build SentenceSplitterUDF.java 

2. Complile class file into jar: $ jar cvfm sentencesplitter.jar MANIFEST.MF -C build .

3. run the script.pig file on the grunt shell