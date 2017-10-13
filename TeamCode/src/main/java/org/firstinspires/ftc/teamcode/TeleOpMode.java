package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * This is the opmode for the teleop period of the competition.
 *
 * Pressing up, down, and left/right on the {@link #gamepad1} directional pad changes the wheel
 * speed to 1, .75, and .5 respectively.
 * The y of {@link #gamepad1}'s sticks control the wheels.
 * The y of {@link #gamepad2}'s sticks control the conveyor belts.
 * The triggers and bumpers of {@link #gamepad2} control the linear slides.
 */

@TeleOp
public class TeleOpMode extends OpMode {
    private Hardware robot = new Hardware();
    private double wheelSpeed = 1;

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.dpad_up) {
            wheelSpeed = 1;
        }
        if (gamepad1.dpad_left || gamepad1.dpad_right) {
            wheelSpeed = .75;
        }
        if (gamepad1.dpad_down) {
            wheelSpeed = .5;
        }
        robot.setWheelPower(-gamepad1.left_stick_y * wheelSpeed,
                -gamepad1.right_stick_y * wheelSpeed);
        robot.setConveyorPower(-gamepad2.left_stick_y, -gamepad2.right_stick_y);
        if (gamepad2.left_trigger > 0) {
            robot.setLeftSlidePower(1);
        }
        if (gamepad2.right_trigger > 0) {
            robot.setRightSlidePower(1);
        }
        if (gamepad2.left_bumper) {
            robot.setLeftSlidePower(-1);
        } else {
            robot.setLeftSlidePower(0);
        }
        if (gamepad2.right_bumper) {
            robot.setRightSlidePower(-1);
        } else {
            robot.setRightSlidePower(0);
        }
    }
}
