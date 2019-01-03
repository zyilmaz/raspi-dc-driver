# Raspberry Pi DC Motor Controller
Controlling L298N driver with using Raspberry Pi

## Project Dependencies

* pi4j library for gpio controlling with using java (for more information [http://pi4j.com/]())

## How to use program
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

this script installs oracle java and wiringpi (for more information [http://wiringpi.com/]())

after installation completed run start script,

```
sh start.sh
```

then go to [http://localhost:8090/control.html]()

and it is how looks like web ui;

![alt text](https://github.com/zyilmaz/raspi-dc-driver/blob/master/src/documents/web_ui.png)