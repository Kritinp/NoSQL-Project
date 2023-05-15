# For first time users:
After cloning, navigate out of the "Project" folder (so that the upcoming venv folder isnt made inside the project)

Type the following in the terminal
```
$ python3 -m venv venv
$ source ./venv/bin/activate
$ pip install flask flask-cors
```

# Otherwise
Start the virtural environment:
```
$ source ./venv/bin/activate
```

# Start the server
```
$ cd NoSQL-Project
$ python3 app.py
```

$ python3 app.py

# To deactivate the virtual environment:
```
$ deactivate
```
# About the source code:
Each folder contains an already compiled jar as well as its source code for the user defined function to perform that operation. If, the jar does not work on your system, then you can create it using the commands present in the readme or the compile file. You can execute the commands by using:
```
$ cd <folder_name>
$ bash compile 
```

This jar is then used to register a user defined function in pig to process some input text.
