1. Create class file: $ javac -cp .:lib/* -d build WordFrequencyUDF.java 

2. Complile class file into jar: $ jar cvfm wordfrequencyudf.jar MANIFEST.MF -C build .

3. run the script.pig file on the grunt shell