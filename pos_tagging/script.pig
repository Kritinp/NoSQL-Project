REGISTER 'pos_tagging/lib/opennlp-tools-1.9.3.jar';
REGISTER 'pos_tagging/POSTaggerUDF.jar';

DEFINE POSTagger POSTaggerUDF();

-- Load the input data
data = LOAD 'pos_tagging/input.txt' AS (text: chararray);

-- Apply the POS tagging UDF to the 'text' field
tagged = FOREACH data GENERATE POSTagger(text) AS tagged_text;

-- Save the output data
STORE tagged INTO 'pos_tagging/output' USING PigStorage('\t');