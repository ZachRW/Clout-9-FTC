package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * This is the opmode for the teleop period of the competition.
 * <p>
 * Pressing up, down, and left/right on the {@link #gamepad1} directional pad changes the wheel
 * speed to 1, .75, and .5 respectively.
 * The directional pad on {@link #gamepad2} controls the speed of the linear slides, setting
 * the speed to 1, .5, and .1.
 * Pressing the 'A' button on {@link #gamepad1} toggles the flipper to move up and down.
 * Pressing the 'X' button on {@link #gamepad1} reverses the wheels.
 * The y of {@link #gamepad1}'s sticks control the wheels.
 * The y of {@link #gamepad2}'s sticks control the conveyor belts.
 * The triggers of {@link #gamepad1} control the center wheel of the robot for strafing.
 * The triggers and bumpers of {@link #gamepad2} control the linear slides.
 */

@TeleOp
public class TeleOpMode extends OpMode {
    private Hardware jeff = new Hardware();
    private double wheelSpeed = 1;
    private double slideSpeed = 1;
    private boolean prevA1 = false;
    private boolean prevX = false;
//    private boolean prevA2 = false;

    @Override
    public void init() {
        jeff.init(hardwareMap);
    }

    @Override
    public void loop() {
        jeff.setWheelPower(-gamepad1.left_stick_y * wheelSpeed,
                -gamepad1.right_stick_y * wheelSpeed);
        jeff.setCenterPower(-gamepad1.left_trigger * wheelSpeed);
        if (gamepad1.right_trigger != 0) {
            jeff.setCenterPower(gamepad1.right_trigger * wheelSpeed);
        }
        if (gamepad1.dpad_up) {
            wheelSpeed = 1;
        }
        if (gamepad1.dpad_left || gamepad1.dpad_right) {
            wheelSpeed = .75;
        }
        if (gamepad1.dpad_down) {
            wheelSpeed = .5;
        }
        if (!prevA1 && gamepad1.a) {
            jeff.toggleFlipper();
        }
        if (!prevX && gamepad1.x) {
            jeff.reverseWheels();
        }

        jeff.setConveyorPower(-gamepad2.left_stick_y, -gamepad2.right_stick_y);
        if (gamepad2.dpad_up) {
            slideSpeed = 1;
        }
        if (gamepad2.dpad_left || gamepad2.dpad_right) {
            slideSpeed = .5;
        }
        if (gamepad2.dpad_down) {
            slideSpeed = .1;
        }
        if (gamepad2.left_trigger > 0) {
            jeff.setLeftSlidePower(slideSpeed);
        } else if (gamepad2.left_bumper) {
            jeff.setLeftSlidePower(-slideSpeed);
        } else {
            jeff.setLeftSlidePower(0);
        }
        if (gamepad2.right_trigger > 0) {
            jeff.setRightSlidePower(1);
        } else if (gamepad2.right_bumper) {
            jeff.setRightSlidePower(-1);
        } else {
            jeff.setRightSlidePower(0);
        }
//        if (prevA2 && gamepad2.a) {
//            jeff.toggleGrabber();
//        }

        prevX = gamepad1.x;
        prevA1 = gamepad1.a;
//        prevA2 = gamepad2.a;
    }
}
