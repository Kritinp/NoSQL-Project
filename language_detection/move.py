import shutil
f = open("tmp", "r")

for line in f.readlines():
    a, b = line[:-1].split()
    shutil.move('profiles/{}'.format(a), 'profile/{}'.format(b))