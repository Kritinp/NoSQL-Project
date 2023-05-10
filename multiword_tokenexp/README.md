1. Create class file: $  javac -cp .:lib/* -d build MultiWordTokenExpansionUDF.java 

2. Complile class file into jar: $ jar cvfm multiwordtokenexpansion.jar MANIFEST.MF -C build .

3. run the script.pig file on the grunt shell