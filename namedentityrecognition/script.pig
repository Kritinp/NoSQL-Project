REGISTER 'namedentityrecognition/lib/stanford-corenlp-4.5.4-javadoc.jar';
REGISTER 'namedentityrecognition/lib/stanford-corenlp-4.5.4-models.jar';
REGISTER 'namedentityrecognition/lib/stanford-corenlp-4.5.4-sources.jar';
REGISTER 'namedentityrecognition/lib/stanford-corenlp-4.5.4.jar';
REGISTER 'namedentityrecognition/lib/opennlp-tools-1.9.3.jar';
REGISTER 'namedentityrecognition/lib/ejml-simple-0.39.jar';
REGISTER 'namedentityrecognition/lib/ejml-simple-0.39-sources.jar';
REGISTER 'namedentityrecognition/lib/ejml-core-0.39.jar';
REGISTER 'namedentityrecognition/lib/ejml-core-0.39-sources.jar';
REGISTER 'namedentityrecognition/lib/ejml-ddense-0.39-sources.jar';
REGISTER 'namedentityrecognition/lib/ejml-ddense-0.39.jar';
REGISTER 'namedentityrecognition/lib/protobuf-java-2.5.0.jar'
REGISTEr 'namedentityrecognition/lib/protobuf-java-3.19.6.jar'



-- Register the UDF
REGISTER 'namedentityrecognition/namedentityrecognition.jar';

-- Load the data
input_data = LOAD 'namedentityrecognition/input.txt' AS (text:chararray);

-- Apply the UDF
named_entities = FOREACH input_data GENERATE NamedEntityRecognition(text) AS named_entities;

filtered_data = FILTER named_entities BY named_entities IS NOT NULL;

-- Store the expanded data
STORE filtered_data INTO 'namedentityrecognition/output' USING PigStorage('\t');

