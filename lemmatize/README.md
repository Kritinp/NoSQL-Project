1. Create class file: $  javac -cp .:lib/* -d build LemmatizeText.java 

2. Complile class file into jar: $ jar cvfm lemmatizetext.jar MANIFEST.MF -C build .

3. run the script.pig file on the grunt shell