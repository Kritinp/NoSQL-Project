from flask import Flask
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

if __name__ == "__main__":
    app.run("localhost", 6969)



