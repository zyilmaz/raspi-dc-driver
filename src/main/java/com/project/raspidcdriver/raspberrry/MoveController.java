package com.project.raspidcdriver.raspberrry;

import com.pi4j.io.gpio.*;
import com.project.raspidcdriver.utills.Configuration;

public class MoveController {

    /**
     * l298n driver controller pin diagram
     * <p>
     * 1. DC motor 1 "+" or stepper motor A+
     * 2. DC motor 1 "-" or stepper motor A-
     * 3. 12V jumper - remove this if using a supply voltage greater than 12V DC. This enables power to the onboard 5V regulator
     * 4. Connect your motor supply voltage here, maximum of 35V DC. Remove 12V jumper if >12V DC
     * 5. GND
     * 6. 5V output if 12V jumper in place, ideal for powering your Arduino (etc)
     * 7. DC motor 1 enable jumper. Leave this in place when using a stepper motor. Connect to PWM output for DC motor speed control.
     * 8. IN1
     * 9. IN2
     * 10. IN3
     * 11. IN4
     * 12. DC motor 2 enable jumper. Leave this in place when using a stepper motor. Connect to PWM output for DC motor speed control.
     * 13. DC motor 2 "+" or stepper motor B+
     * 14. DC motor 2 "-" or stepper motor B-
     */

    private GpioPinPwmOutput rightMotorPwm; //ENA
    private GpioPinPwmOutput leftMotorPwm; //ENB

    private GpioPinDigitalOutput rightMotorControl1; //IN1
    private GpioPinDigitalOutput rightMotorControl2; //IN2
    private GpioPinDigitalOutput leftMotorControl1; //IN3
    private GpioPinDigitalOutput leftMotorControl2; //IN4

    /**
     *
     */
    public MoveController() {

        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        rightMotorControl1 = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(Configuration.getConfiguration().getRightMotorControl1Pin()), PinState.LOW);
        rightMotorControl2 = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(Configuration.getConfiguration().getRightMotorControl2Pin()), PinState.LOW);
        leftMotorControl1 = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(Configuration.getConfiguration().getLeftMotorControl1Pin()), PinState.LOW);
        leftMotorControl2 = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(Configuration.getConfiguration().getLeftMotorControl2Pin()), PinState.LOW);

        rightMotorPwm = gpio.provisionSoftPwmOutputPin(RaspiPin.getPinByAddress(Configuration.getConfiguration().getRightMotorPwmPin()));
        leftMotorPwm = gpio.provisionSoftPwmOutputPin(RaspiPin.getPinByAddress(Configuration.getConfiguration().getLeftMotorPwmPin()));

        // optionally set the PWM range (100 is default range)
        rightMotorPwm.setPwmRange(Configuration.getConfiguration().getSoftPwmRangeForMove());
        leftMotorPwm.setPwmRange(Configuration.getConfiguration().getSoftPwmRangeForMove());

        // set the PWM rate to 100 (FULLY ON)
        rightMotorPwm.setPwm(Configuration.getConfiguration().getSoftPwmRangeForMove());

        // set the PWM rate to 100 (FULLY ON)
        leftMotorPwm.setPwm(Configuration.getConfiguration().getSoftPwmRangeForMove());
    }

    public void forward() {
        // turn on motor A
        rightMotorControl1.setState(true);
        rightMotorControl2.setState(false);

        // turn on motor B
        leftMotorControl1.setState(true);
        leftMotorControl2.setState(false);
    }

    public void backward() {
        // turn on motor A
        rightMotorControl1.setState(false);
        rightMotorControl2.setState(true);

        // turn on motor B
        leftMotorControl1.setState(false);
        leftMotorControl2.setState(true);
    }

    public void stop() {
        // turn off motor A
        rightMotorControl1.setState(false);
        rightMotorControl2.setState(false);

        // turn off motor B
        leftMotorControl1.setState(false);
        leftMotorControl2.setState(false);
    }

    public void right() {
        // turn off motor A
        rightMotorControl1.setState(false);
        rightMotorControl2.setState(false);

        // turn on motor B
        leftMotorControl1.setState(true);
        leftMotorControl2.setState(false);
    }

    public void left() {
        // turn on motor A
        rightMotorControl1.setState(true);
        rightMotorControl2.setState(false);

        // turn off motor B
        leftMotorControl1.setState(false);
        leftMotorControl2.setState(false);
    }

}
