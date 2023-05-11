REGISTER 'language_detection/lib/langdetect-1.1-20120112.jar';
REGISTER 'language_detection/lib/jsonic-1.1.2.jar';
REGISTER 'language_detection/LanguageDetection.jar';


DEFINE LanguageDetection com.example.LanguageDetection();

-- Load the input data
data = LOAD 'language_detection/input.txt' AS (text: chararray);

-- Apply the POS tagging UDF to the 'text' field
detected = FOREACH data GENERATE LanguageDetection(text) AS language;

-- Save the output data
STORE detected INTO 'language_detection/output' USING PigStorage('\t');