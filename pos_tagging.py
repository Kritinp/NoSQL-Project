import subprocess
from flask import send_file
from os import rmdir, remove
import glob

def pos_tagging(input_text):
	input_file = "pos_tagging/input.txt"
	f = open(input_file, "w")
	f.write(input_text)
	f.close()
	
	# Set the path to your Pig script
	pig_script_path = "pos_tagging/script.pig"

	# delete ouput directory if exists
	output_directory = "pos_tagging/output/"
	if len(glob.glob(output_directory)) > 0:
		files = glob.glob(output_directory + ".*")
		print(files)
		for file in files:
			remove(file)
		files = glob.glob(output_directory + "*")
		print(files)
		for file in files:
			remove(file)
		rmdir(output_directory)
	
	# Set the arguments to pass to the Pig command
	pig_args = ["pig", "-x", "local", pig_script_path]

	# Execute the Pig command
	result = subprocess.run(pig_args, stdout=subprocess.PIPE, stderr=subprocess.PIPE)

	# Print the output and error messages
	print(result.stdout.decode("utf-8"))
	print(result.stderr.decode("utf-8"))

	output_file = glob.glob(output_directory + "part*")
	print(output_file)
	if len(output_file) > 0:
		with open(output_file[0], 'r') as file:
			file_content = file.read()
		return file_content	
	return "error"