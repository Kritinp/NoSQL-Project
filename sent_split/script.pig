REGISTER 'sent_split/lib/stanford-corenlp-4.5.4-javadoc.jar';
REGISTER 'sent_split/lib/stanford-corenlp-4.5.4-models.jar';
REGISTER 'sent_split/lib/stanford-corenlp-4.5.4-sources.jar';
REGISTER 'sent_split/lib/stanford-corenlp-4.5.4.jar';
REGISTER 'sent_split/lib/opennlp-tools-1.9.3.jar';
REGISTER 'sent_split/lib/ejml-simple-0.39.jar';
REGISTER 'sent_split/lib/ejml-simple-0.39-sources.jar';
REGISTER 'sent_split/lib/ejml-core-0.39.jar';
REGISTER 'sent_split/lib/ejml-core-0.39-sources.jar';
REGISTER 'sent_split/lib/ejml-ddense-0.39-sources.jar';
REGISTER 'sent_split/lib/ejml-ddense-0.39.jar';
REGISTER 'sent_split/lib/protobuf-java-2.5.0.jar'
REGISTER 'sent_split/lib/protobuf-java-3.19.6.jar'



-- Register the UDF
REGISTER 'sent_split/sentencesplitter.jar';

-- Load the data
input_data = LOAD 'sent_split/input.txt' AS (text:chararray);

-- Apply the UDF
split_data = FOREACH input_data GENERATE SentenceSplitterUDF(text) AS sentences;
-- Store the expanded data
STORE split_data INTO 'sent_split/output';

