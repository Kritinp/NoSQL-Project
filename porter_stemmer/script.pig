REGISTER 'porter_stemmer/Stem.jar';

DEFINE Stem Stem();

-- Load the input data
data = LOAD 'porter_stemmer/input.txt' AS (text: chararray);

-- Apply the POS tagging UDF to the 'text' field
tagged = FOREACH data GENERATE Stem(text) AS tagged_text;

-- Save the output data
STORE tagged INTO 'porter_stemmer/output' USING PigStorage('\t');