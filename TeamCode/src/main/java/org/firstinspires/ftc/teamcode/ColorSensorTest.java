package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * This opmode will run a motor when the color sensor detects more red than blue and green.
 * It also outputs the RGB values for debugging.
 */

@Disabled
@Autonomous(name = "Color Test")
public class ColorSensorTest extends OpMode {
    private final Hardware jeff = new Hardware();
    private DcMotor motor;

    @Override
    public void init() {
        jeff.init(hardwareMap);
        motor = hardwareMap.dcMotor.get("motor");
    }

    @Override
    public void loop() {
//        telemetry.addData("RGB: ", jeff.getRGB());
//        if (jeff.mostlyRed()) {
//            motor.setPower(1);
//        } else {
//            motor.setPower(0);
//        }
    }
}
