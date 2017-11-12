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
 * Pressing the 'B' button on {@link #gamepad1} toggles the flipper to move up and down.
 * Pressing the 'X' button on {@link #gamepad1} reverses the wheels.
 * The y of {@link #gamepad1}'s sticks control the wheels.
 * The y of {@link #gamepad2}'s sticks control the conveyor belts.
 * The triggers of {@link #gamepad1} control the center wheel of the robot for strafing.
 * The triggers and bumpers of {@link #gamepad2} control the linear slides.
 * The 'Y' and 'A' buttons of {@link #gamepad2} control the claw arm.
 */

@TeleOp
public class TeleOpMode extends OpMode {
    private static final double CLAW_ARM_SPEED = .05;
    private final Hardware jeff = new Hardware();
    private double wheelSpeed = 1;
    private double slideSpeed = 1;
    private boolean prevB, prevX, reverse;

    @Override
    public void init() {
        jeff.init(hardwareMap);
    }

    @Override
    public void loop() {
        jeff.setWheelPower(
                (reverse ? gamepad1.right_stick_y : -gamepad1.left_stick_y) * wheelSpeed,
                (reverse ? gamepad1.left_stick_y : -gamepad1.right_stick_y) * wheelSpeed);
        jeff.setCenterPower(
                (reverse ? -gamepad1.right_trigger : -gamepad1.left_trigger) * wheelSpeed);
        if ((reverse ? gamepad1.left_trigger : gamepad1.right_trigger) != 0) {
            jeff.setCenterPower(
                    (reverse ? gamepad1.left_trigger : gamepad1.right_trigger) * wheelSpeed);
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
        if (!prevB && gamepad1.b) {
            jeff.toggleFlipper();
        }
        if (!prevX && gamepad1.x) {
            reverse = !reverse;
        }

        jeff.setConveyorPower(-gamepad2.left_stick_y, -gamepad2.right_stick_y);
        if (gamepad2.y) {
            jeff.setClawArmPower(CLAW_ARM_SPEED);
        } else if (gamepad2.a) {
            jeff.setClawArmPower(-CLAW_ARM_SPEED);
        } else {
            jeff.setClawArmPower(0);
        }

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

        prevX = gamepad1.x;
        prevB = gamepad1.b;

        jeff.encoderTelemetry(telemetry);
    }
}
