from lemmatize import *
from namedentityrecognition import *
from pos_tagging import *
from sentence_splitter import sent_split
from sentiment import *
from stopwords import *
from wordfrequency import *
from porter_stemmer import *
from multiword_tokenexp import *
from language_detection import *

import flask
import subprocess
from flask import send_file, Flask, request, jsonify, make_response
from flask_cors import CORS
import json as myjson
app = Flask(__name__)
cors = CORS(app)
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

@app.route('/query', methods=["POST"])
def processQuery():
    print("hi")
    content_type = request.headers.get('Content-Type')
    print(content_type)
    if (content_type == 'application/json'):
        json = request.json
        # print(json)
        if not ("query" in json and "input_text" in json):
            return myjson.dumps({"data":"Invalid Query"})
        
        if json["query"] == "POS Tagging":
            res= pos_tagging(json["input_text"])
            return jsonify({"data":res})
        elif json["query"] == "SENTIMENT":
            res= sentiment(json["input_text"])    
            return jsonify({"data": res})
        elif json["query"] == "STOPWORDS":
            res= stopwords(json["input_text"])
            return jsonify({"data":res})
        elif json["query"] == "WORDFREQUENCY":
            res= wordfrequency(json["input_text"])
            return jsonify({"data":res})
        elif json["query"] == "STEMMING":
            res= porter_stemmer(json["input_text"])
            return jsonify({"data":res})
        elif json["query"] == "MULTIWORD_TOKENEXP":
            res= multiword_tokenexp(json["input_text"])
            return jsonify({"data":res})
        elif json["query"] == "SENTENCE_SPLIT":
            res= sent_split(json["input_text"])
            return jsonify({"data":res})
        elif json["query"] == "LEMMATIZE":
            res= lemmatize(json["input_text"])
            return jsonify({"data":res})
        elif json["query"] == "NER":
            res= namedentityrecognition(json["input_text"])
            return jsonify({"data":res})
        elif json["query"] == "LANGUAGE_DETECTION":
            res= language_detection(json["input_text"])
            return jsonify({"data":res})
        return "query not recognized"
    
    else:
        return 'Content-Type not supported!'

if __name__ == "__main__":
    app.run("localhost", 6969)



