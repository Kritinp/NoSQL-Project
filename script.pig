MyRecords = LOAD './sample.txt' AS (name:chararray, rollno:long, emailid:chararray, groupid:int);
FilteredRecords = FILTER MyRecords BY groupid <= 10;
GroupedRecords = GROUP FilteredRecords BY groupid;
Counts = FOREACH GroupedRecords GENERATE group AS ID, COUNT(FilteredRecords) AS StudentCount;
STORE Counts INTO 'output.txt';