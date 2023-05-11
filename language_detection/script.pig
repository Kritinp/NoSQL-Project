REGISTER 'LanguageDetection.jar';
REGISTER 'lib/langdetect-1.1-20120112.jar';
REGISTER 'lib/jsonic-1.1.2.jar com.example.LanguageDetection';

-- Load your input data
input_data = LOAD 'input.txt' AS (document: chararray);

-- Apply the UDF to calculate word frequencies
lang = FOREACH input_data GENERATE LanguageDetection(document) AS language;

-- Dump the result
STORE lang INTO 'wordfrequency/output' USING PigStorage('\t');