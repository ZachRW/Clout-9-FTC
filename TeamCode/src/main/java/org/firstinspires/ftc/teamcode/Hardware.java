package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Instances of Hardware provide several methods for initializing
 * and controlling the robot.
 */

class Hardware {
    private ColorSensor colorSensor;
    private Servo grabberServo1;
    private Servo grabberServo2;

    void init(HardwareMap hardwareMap) {
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        colorSensor.enableLed(true);
        grabberServo1 = hardwareMap.servo.get("grabber1");
        grabberServo2 = hardwareMap.servo.get("grabber2");
        grab(false);
    }

    boolean mostlyRed() {
        return colorSensor.red() > colorSensor.blue() + colorSensor.green();
    }

    boolean mostlyBlue() {
        return colorSensor.blue() > colorSensor.red() + colorSensor.green();
    }

    String getRGB() {
        return colorSensor.red() + ", " + colorSensor.green() + ", " + colorSensor.blue();
    }

    void grab(boolean grabOn) {
        if (grabOn) {
            grabberServo1.setPosition(1);
            grabberServo2.setPosition(0);
        } else {
            grabberServo1.setPosition(.55);
            grabberServo2.setPosition(.5);
        }
    }
}
