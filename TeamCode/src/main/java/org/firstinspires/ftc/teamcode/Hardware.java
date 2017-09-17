package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Zach on 9/16/17.
 */

class Hardware {
    private ColorSensor colorSensor;

    void init(HardwareMap hardwareMap) {
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        colorSensor.enableLed(true);
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
}
