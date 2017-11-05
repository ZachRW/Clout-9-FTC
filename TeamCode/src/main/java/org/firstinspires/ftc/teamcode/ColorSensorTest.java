package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * This opmode will run the right conveyor belt down when it detects mostly blue and reverse it
 * when it detects mostly red.
 * It also output the RGB values for debugging.
 */

@Autonomous
public class ColorSensorTest extends OpMode {
    private Hardware jeff = new Hardware();

    @Override
    public void init() {
        jeff.init(hardwareMap);
        jeff.enableColorSensorLed(true);
    }

    @Override
    public void loop() {
        if (jeff.mostlyBlue()) {
            jeff.setConveyorPower(0, 1);
        } else if (jeff.mostlyRed()) {
            jeff.setConveyorPower(0, -1);
        } else {
            jeff.setConveyorPower(0, 0);
        }
        telemetry.addData("RGB", jeff.getRGB());
    }
}