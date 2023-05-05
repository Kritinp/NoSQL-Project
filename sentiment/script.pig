REGISTER 'lib/stanford-corenlp-4.5.4-javadoc.jar';
REGISTER 'lib/stanford-corenlp-4.5.4-models.jar';
REGISTER 'lib/stanford-corenlp-4.5.4-sources.jar';
REGISTER 'lib/stanford-corenlp-4.5.4.jar';
REGISTER 'nlpestimatingsentiment.jar';
REGISTER 'lib/ejml-simple-0.39.jar';
REGISTER 'lib/ejml-simple-0.39-sources.jar';
REGISTER 'lib/ejml-core-0.39.jar';
REGISTER 'lib/ejml-core-0.39-sources.jar';
REGISTER 'lib/ejml-ddense-0.39-sources.jar';
REGISTER 'lib/ejml-ddense-0.39.jar';

-- DEFINE SentimentAnalysis SentimentAnalysisUDF();
DEFINE NLP_ESTIMATING_SENTIMENT NlpEstimatingSentiment();

data = LOAD 'sample.txt' AS (text:chararray);
-- sentiment = FOREACH data GENERATE SentimentAnalysis(text);
sentiment = FOREACH data GENERATE NLP_ESTIMATING_SENTIMENT(text);
DUMP sentiment;