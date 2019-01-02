package com.project.raspidcdriver.utills;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private static Configuration configuration;
    private Properties prop;


    private final String SOFT_PWM_RANGE_FOR_MOVE_KEY = "softPwmRangeForMove";
    private final String RIGHT_MOTOR_PWM_PIN_KEY = "rightMotorPwmPin";
    private final String LEFT_MOTOR_PWM_PIN_KEY = "leftMotorPwmPin";
    private final String RIGHT_MOTOR_CONTROL1_PIN_KEY = "rightMotorControl1Pin";
    private final String RIGHT_MOTOR_CONTROL2_PIN_KEY = "rightMotorControl2Pin";
    private final String LEFT_MOTOR_CONTROL1_PIN_KEY = "leftMotorControl1Pin";
    private final String LEFT_MOTOR_CONTROL2_PIN_KEY = "leftMotorControl2Pin";


    private int softPwmRangeForMove;
    private int rightMotorPwmPin;
    private int leftMotorPwmPin;
    private int rightMotorControl1Pin;
    private int rightMotorControl2Pin;
    private int leftMotorControl1Pin;
    private int leftMotorControl2Pin;


    public static Configuration getConfiguration() {
        if (configuration == null) {
            configuration = new Configuration();
        }

        return configuration;
    }

    private Configuration() {
        InputStream inputStream = null;
        prop = new Properties();

        /**
         * run jar file with config parameter like below
         * ex: java -Dconfig=<config_location> -jar raspidcdriver-1.0-SNAPSHOT.jar
         *
         * you can give any properties file with these parameter.
         * If you do not provide config parameter then system use config.properties file
         * which same path with jar file.
         */
        String propFileName = System.getProperty("config");
        if (propFileName == null) {
            propFileName = "config.properties";
        }

        try {
            inputStream = new FileInputStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Configuration file did not provided....\n"
                    + "Add config.properties file to same path with jar file\n"
                    + "Or run java with config parameter like below;\n"
                    + "java -Dconfig=<config_location> -jar raspidcdriver-1.0-SNAPSHOT.jar\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (prop.isEmpty()) {
                inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

                if (inputStream != null) {
                    prop.load(inputStream);
                } else {
                    throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        setSoftPwmRangeForMove();
        setLeftMotorControl1Pin();
        setLeftMotorControl2Pin();
        setLeftMotorPwmPin();
        setRightMotorControl1Pin();
        setRightMotorControl2Pin();
        setRightMotorPwmPin();

        System.out.println(this.toString());


        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public int getRightMotorPwmPin() {
        return rightMotorPwmPin;
    }

    private void setRightMotorPwmPin() {
        this.rightMotorPwmPin = Integer.parseInt(this.prop.getProperty(RIGHT_MOTOR_PWM_PIN_KEY));
    }

    public int getLeftMotorPwmPin() {
        return leftMotorPwmPin;
    }

    private void setLeftMotorPwmPin() {
        this.leftMotorPwmPin = Integer.parseInt(this.prop.getProperty(LEFT_MOTOR_PWM_PIN_KEY));
    }

    public int getRightMotorControl1Pin() {
        return rightMotorControl1Pin;
    }

    private void setRightMotorControl1Pin() {
        this.rightMotorControl1Pin = Integer.parseInt(this.prop.getProperty(RIGHT_MOTOR_CONTROL1_PIN_KEY));
    }

    public int getRightMotorControl2Pin() {
        return rightMotorControl2Pin;
    }

    private void setRightMotorControl2Pin() {
        this.rightMotorControl2Pin = Integer.parseInt(this.prop.getProperty(RIGHT_MOTOR_CONTROL2_PIN_KEY));
    }

    public int getLeftMotorControl1Pin() {
        return leftMotorControl1Pin;
    }

    private void setLeftMotorControl1Pin() {
        this.leftMotorControl1Pin = Integer.parseInt(this.prop.getProperty(LEFT_MOTOR_CONTROL1_PIN_KEY));
    }

    public int getLeftMotorControl2Pin() {
        return leftMotorControl2Pin;
    }

    private void setLeftMotorControl2Pin() {
        this.leftMotorControl2Pin = Integer.parseInt(this.prop.getProperty(LEFT_MOTOR_CONTROL2_PIN_KEY));
    }

    public int getSoftPwmRangeForMove() {
        return softPwmRangeForMove;
    }

    public void setSoftPwmRangeForMove() {
        this.softPwmRangeForMove = Integer.parseInt(this.prop.getProperty(SOFT_PWM_RANGE_FOR_MOVE_KEY));
    }


    @Override
    public String toString() {
        return "Configuration{" +
                "  softPwmRangeForMove=" + softPwmRangeForMove +
                ", rightMotorPwmPin=" + rightMotorPwmPin +
                ", leftMotorPwmPin=" + leftMotorPwmPin +
                ", rightMotorControl1Pin=" + rightMotorControl1Pin +
                ", rightMotorControl2Pin=" + rightMotorControl2Pin +
                ", leftMotorControl1Pin=" + leftMotorControl1Pin +
                ", leftMotorControl2Pin=" + leftMotorControl2Pin +
                '}';
    }
}
