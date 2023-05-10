REGISTER 'sentiment/lib/stanford-corenlp-4.5.4-javadoc.jar';

REGISTER 'sentiment/lib/stanford-corenlp-4.5.4-models.jar';
REGISTER 'sentiment/lib/stanford-corenlp-4.5.4-sources.jar';
REGISTER 'sentiment/lib/stanford-corenlp-4.5.4.jar';
REGISTER 'sentiment/nlpestimatingsentiment.jar';
REGISTER 'sentiment/lib/ejml-simple-0.39.jar';
REGISTER 'sentiment/lib/ejml-simple-0.39-sources.jar';
REGISTER 'sentiment/lib/ejml-core-0.39.jar';
REGISTER 'sentiment/lib/ejml-core-0.39-sources.jar';
REGISTER 'sentiment/lib/ejml-ddense-0.39-sources.jar';
REGISTER 'sentiment/lib/ejml-ddense-0.39.jar';

-- DEFINE SentimentAnalysis SentimentAnalysisUDF();
DEFINE NLP_ESTIMATING_SENTIMENT NlpEstimatingSentiment();

data = LOAD 'sentiment/input.txt' AS (text:chararray);
-- sentiment = FOREACH data GENERATE SentimentAnalysis(text);
sentiment = FOREACH data GENERATE NLP_ESTIMATING_SENTIMENT(text);
-- DUMP sentiment;
STORE sentiment INTO 'sentiment/output' USING PigStorage('\t');