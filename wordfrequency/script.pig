REGISTER 'wordfrequency/lib/stanford-corenlp-4.5.4-javadoc.jar';
REGISTER 'wordfrequency/lib/stanford-corenlp-4.5.4-models.jar';
REGISTER 'wordfrequency/lib/stanford-corenlp-4.5.4-sources.jar';
REGISTER 'wordfrequency/lib/stanford-corenlp-4.5.4.jar';
REGISTER 'wordfrequency/lib/opennlp-tools-1.9.3.jar';
REGISTER 'wordfrequency/lib/ejml-simple-0.39.jar';
REGISTER 'wordfrequency/lib/ejml-simple-0.39-sources.jar';
REGISTER 'wordfrequency/lib/ejml-core-0.39.jar';
REGISTER 'wordfrequency/lib/ejml-core-0.39-sources.jar';
REGISTER 'wordfrequency/lib/ejml-ddense-0.39-sources.jar';
REGISTER 'wordfrequency/lib/ejml-ddense-0.39.jar';

REGISTER 'wordfrequency/wordfrequencyudf.jar';

-- Load your input data
input_data = LOAD 'wordfrequency/input.txt' AS (document: chararray);

-- Apply the UDF to calculate word frequencies
word_freq = FOREACH input_data GENERATE WordFrequencyUDF(document) AS word_freq_map;

-- Dump the result
STORE word_freq INTO 'wordfrequency/output' USING PigStorage('\t');
