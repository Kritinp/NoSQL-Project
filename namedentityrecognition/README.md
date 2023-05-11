1. Create class file: $  javac -cp .:lib/* -d build NamedEntityRecognition.java 

2. Complile class file into jar: $ jar cvfm namedentityrecognition.jar MANIFEST.MF -C build .

3. run the script.pig file on the grunt shell