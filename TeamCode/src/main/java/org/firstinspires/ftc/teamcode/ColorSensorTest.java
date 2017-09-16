package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Color Test")
public class ColorSensorTest extends OpMode {
    private Hardware robot = new Hardware();
    private DcMotor motor;

    @Override
    public void init() {
        robot.init(hardwareMap);
        motor = hardwareMap.dcMotor.get("motor");
    }

    @Override
    public void loop() {
        if (robot.mostlyRed()) {
            motor.setPower(1);
        } else {
            motor.setPower(0);
        }
    }
}
