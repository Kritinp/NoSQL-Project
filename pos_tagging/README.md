1. Create class file: $ javac -cp .:lib/* -d build src/main/java/POSTaggerUDF.java

2. Complile class file into jar: $ jar cvfm POSTaggerUDF.jar src/main/META-INF/MANIFEST.MF -C build .

3. run the script.pig file on the grunt shell