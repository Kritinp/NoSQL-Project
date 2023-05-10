REGISTER 'multiword_tokenexp/lib/stanford-corenlp-4.5.4-javadoc.jar';
REGISTER 'multiword_tokenexp/lib/stanford-corenlp-4.5.4-models.jar';
REGISTER 'multiword_tokenexp/lib/stanford-corenlp-4.5.4-sources.jar';
REGISTER 'multiword_tokenexp/lib/stanford-corenlp-4.5.4.jar';
REGISTER 'multiword_tokenexp/lib/opennlp-tools-1.9.3.jar';
REGISTER 'multiword_tokenexp/lib/ejml-simple-0.39.jar';
REGISTER 'multiword_tokenexp/lib/ejml-simple-0.39-sources.jar';
REGISTER 'multiword_tokenexp/lib/ejml-core-0.39.jar';
REGISTER 'multiword_tokenexp/lib/ejml-core-0.39-sources.jar';
REGISTER 'multiword_tokenexp/lib/ejml-ddense-0.39-sources.jar';
REGISTER 'multiword_tokenexp/lib/ejml-ddense-0.39.jar';
REGISTER 'multiword_tokenexp/lib/protobuf-java-2.5.0.jar'
REGISTEr 'multiword_tokenexp/lib/protobuf-java-3.19.6.jar'



-- Register the UDF
REGISTER 'multiword_tokenexp/multiwordtokenexpansion.jar';

-- Load the data
input_data = LOAD 'multiword_tokenexp/input.txt' AS (text:chararray);

-- Apply the UDF
expanded_data = FOREACH input_data GENERATE MultiWordTokenExpansionUDF(text) AS expanded_text;

-- Store the expanded data
STORE expanded_data INTO 'multiword_tokenexp/output';

