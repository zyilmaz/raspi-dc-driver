# raspi-dc-driver
Controlling L298N driver with using Rasppberry Pi

# How to use program
run below code and build and package project in zip format
```
mvn clean install
```

after command executed zip file will be ready.

copy zip file to raspberry and unzip the file.

project have dependencies so before running the program, run install script with below command

```
sh install.sh
```

this script installs oracle java and wiringpi (for more information look at [http://wiringpi.com/]())

after installation completed run start script,

```
sh start.sh
```

then go to [http://localhost:8090/control.html]()