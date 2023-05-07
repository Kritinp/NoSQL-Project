REGISTER 'lib/stanford-corenlp-4.5.4-javadoc.jar';
REGISTER 'lib/stanford-corenlp-4.5.4-models.jar';
REGISTER 'lib/stanford-corenlp-4.5.4-sources.jar';
REGISTER 'lib/stanford-corenlp-4.5.4.jar';
REGISTER 'lib/opennlp-tools-1.9.3.jar';
REGISTER 'lib/ejml-simple-0.39.jar';
REGISTER 'lib/ejml-simple-0.39-sources.jar';
REGISTER 'lib/ejml-core-0.39.jar';
REGISTER 'lib/ejml-core-0.39-sources.jar';
REGISTER 'lib/ejml-ddense-0.39-sources.jar';
REGISTER 'lib/ejml-ddense-0.39.jar';

REGISTER 'removestopwordsudf.jar';

DEFINE RemoveStopwords RemoveStopwordsUDF();

-- Load input data
input_data = LOAD 'input_data.txt' AS (text: chararray);

-- Apply the UDF to remove stopwords
filtered_data = FOREACH input_data GENERATE RemoveStopwords(text) AS filtered_text;

-- Store the result
STORE filtered_data INTO 'output.txt';
