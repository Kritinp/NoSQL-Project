REGISTER 'lemmatize/lib/stanford-corenlp-4.5.4-javadoc.jar';
REGISTER 'lemmatize/lib/stanford-corenlp-4.5.4-models.jar';
REGISTER 'lemmatize/lib/stanford-corenlp-4.5.4-sources.jar';
REGISTER 'lemmatize/lib/stanford-corenlp-4.5.4.jar';
REGISTER 'lemmatize/lib/opennlp-tools-1.9.3.jar';
REGISTER 'lemmatize/lib/ejml-simple-0.39.jar';
REGISTER 'lemmatize/lib/ejml-simple-0.39-sources.jar';
REGISTER 'lemmatize/lib/ejml-core-0.39.jar';
REGISTER 'lemmatize/lib/ejml-core-0.39-sources.jar';
REGISTER 'lemmatize/lib/ejml-ddense-0.39-sources.jar';
REGISTER 'lemmatize/lib/ejml-ddense-0.39.jar';
REGISTER 'lemmatize/lib/protobuf-java-2.5.0.jar'
REGISTEr 'lemmatize/lib/protobuf-java-3.19.6.jar'



-- Register the UDF
REGISTER 'lemmatize/lemmatizetext.jar';

-- Load the data
input_data = LOAD 'lemmatize/input.txt' AS (text:chararray);

-- Apply the UDF
lemmatized_data = FOREACH input_data GENERATE LemmatizeText(text) AS lemmatized_text;

-- Store the expanded data
STORE lemmatized_data INTO 'lemmatize/output' USING PigStorage('\t');

