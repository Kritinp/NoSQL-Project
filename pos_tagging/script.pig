REGISTER 'lib/opennlp-tools-1.9.3.jar';
REGISTER 'POSTaggerUDF.jar';

DEFINE POSTagger POSTaggerUDF();

-- Load the input data
data = LOAD 'input.txt' USING PigStorage(',') AS (id: int, text: chararray);

-- Apply the POS tagging UDF to the 'text' field
tagged = FOREACH data GENERATE id, POSTagger(text) AS tagged_text;

-- Save the output data
STORE tagged INTO 'output.txt' USING PigStorage('\t');