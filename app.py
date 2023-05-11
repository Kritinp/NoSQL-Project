from lemmatize import *
from pos_tagging import *
from sentence_splitter import sent_split
from sentiment import *
from stopwords import *
from wordfrequency import *
from porter_stemmer import *
from multiword_tokenexp import *

from flask import Flask, request
import flask
import json
import subprocess
from flask import send_file
app = Flask(__name__)

@app.route("/")
def hello():
    return "Hello, World!"

@app.route('/pigtest', methods=["GET"])
def pigTest():
    print("users endpoint reached...")

    # Set the path to your Pig script
    pig_script_path = "./script.pig"

    # Set the arguments to pass to the Pig command
    pig_args = ["pig", "-x", "local", pig_script_path]

    # Execute the Pig command
    result = subprocess.run(pig_args, stdout=subprocess.PIPE, stderr=subprocess.PIPE)

    # Print the output and error messages
    print(result.stdout.decode("utf-8"))
    print(result.stderr.decode("utf-8"))

    return send_file("output.txt/part-r-00000", as_attachment=True)

@app.route('/query', methods=["GET"])
def processQuery():
    print("hi")
    content_type = request.headers.get('Content-Type')
    print(content_type)
    if (content_type == 'application/json'):
        json = request.json
        # print(json)
        if not ("query" in json and "input_text" in json):
            return "invalid query"
        
        if json["query"] == "POS Tagging":
            return pos_tagging(json["input_text"])
        
        elif json["query"] == "SENTIMENT":
            return sentiment(json["input_text"])
        
        elif json["query"] == "STOPWORDS":
            return stopwords(json["input_text"])
        
        elif json["query"] == "WORDFREQUENCY":
            return wordfrequency(json["input_text"])
        
        elif json["query"] == "SENTIMENT":
            return sentiment(json["input_text"])
        
        elif json["query"] == "STOPWORDS":
            return stopwords(json["input_text"])
        
        elif json["query"] == "STEMMING":
            return porter_stemmer(json["input_text"])
        
        elif json["query"] == "MULTIWORD_TOKENEXP":
            return multiword_tokenexp(json["input_text"])
        
        elif json["query"] == "SENTENCE_SPLIT":
            return sent_split(json["input_text"])

        elif json["query"] == "LEMMATIZE":
            return lemmatize(json["input_text"])
        
        return "query not recognized"
    
    else:
        return 'Content-Type not supported!'

if __name__ == "__main__":
    app.run("localhost", 6969)



