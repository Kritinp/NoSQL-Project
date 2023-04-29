After cloning, navigate out of the "Project" folder (so that the upcoming venv folder isnt made inside the project)

Type the following in the terminal

$ python3 -m venv venv

$ source ./venv/bin/activate

*First line(python -m venv venv) is only required the first time*

*Second line starts the venv*

$ pip install flask flask-cors

Navigate into the cloned folder

$ python3 app.py

To stop the venv:
$ deactivate
