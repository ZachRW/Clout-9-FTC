package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * This simple opmode will move the conveyor belts based on {@link #gamepad1}'s right stick y.
 */

@TeleOp
public class Conveyor extends OpMode {
    private Hardware robot = new Hardware();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        robot.setConveyorPower(-gamepad1.right_stick_y);
    }
}
