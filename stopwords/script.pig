REGISTER 'stopwords/lib/stanford-corenlp-4.5.4-javadoc.jar';
REGISTER 'stopwords/lib/stanford-corenlp-4.5.4-models.jar';
REGISTER 'stopwords/lib/stanford-corenlp-4.5.4-sources.jar';
REGISTER 'stopwords/lib/stanford-corenlp-4.5.4.jar';
REGISTER 'stopwords/lib/opennlp-tools-1.9.3.jar';
REGISTER 'stopwords/lib/ejml-simple-0.39.jar';
REGISTER 'stopwords/lib/ejml-simple-0.39-sources.jar';
REGISTER 'stopwords/lib/ejml-core-0.39.jar';
REGISTER 'stopwords/lib/ejml-core-0.39-sources.jar';
REGISTER 'stopwords/lib/ejml-ddense-0.39-sources.jar';
REGISTER 'stopwords/lib/ejml-ddense-0.39.jar';

REGISTER 'stopwords/removestopwordsudf.jar';

DEFINE RemoveStopwords RemoveStopwordsUDF();

-- Load input data
input_data = LOAD 'stopwords/input.txt' AS (text: chararray);

-- Apply the UDF to remove stopwords
filtered_data = FOREACH input_data GENERATE RemoveStopwords(text) AS filtered_text;

-- Store the result
STORE filtered_data INTO 'stopwords/output' USING PigStorage('\t');
