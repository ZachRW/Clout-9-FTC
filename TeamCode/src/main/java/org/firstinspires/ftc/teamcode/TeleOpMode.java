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
 * The 'X' button of {@link #gamepad2} toggles the claw.
 */

@TeleOp
public class TeleOpMode extends OpMode {
    private static final int SLIDE_UPPER_BOUND = 10_000;
    private static final int SLIDE_LOWER_BOUND = 100;
    private static final double CLAW_ARM_SPEED = .05;
    private final Hardware jeff = new Hardware();
    private double wheelSpeed = 1;
    private double slideSpeed = 1;
    private boolean clawControl = false;
    private boolean prevB, prevX1, prevX2, prevY2, reverse;

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
        if (!prevX1 && gamepad1.x) {
            reverse = !reverse;
        }
        if(gamepad2.y && !prevY2) {
            clawControl = !clawControl;
        }
        if(!clawControl) {
            jeff.setConveyorPower(-gamepad2.left_stick_y, -gamepad2.right_stick_y);
        }
        else {
                jeff.setClawArmPower(0.25*gamepad2.left_stick_y);

            // Old code for the butons moving the clawArm
            /*if (gamepad2.y) {
                jeff.setClawArmPower(CLAW_ARM_SPEED);
            } else if (gamepad2.a) {
                jeff.setClawArmPower(-CLAW_ARM_SPEED);
            } else {
                jeff.setClawArmPower(0);
            }*/
        }
        if (gamepad2.x && !prevX2) {
            jeff.toggleClaw();
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

        if (gamepad2.left_trigger > 0 /*&& -jeff.getLeftSlidePos() > SLIDE_LOWER_BOUND*/) {
            jeff.setLeftSlidePower(slideSpeed);
        } else if (gamepad2.left_bumper /*&& -jeff.getLeftSlidePos() < SLIDE_UPPER_BOUND*/) {
            jeff.setLeftSlidePower(-slideSpeed);
        } else {
            jeff.setLeftSlidePower(0);
        }
        if (gamepad2.right_trigger > 0 /*&& -jeff.getRightSlidePos() > SLIDE_LOWER_BOUND*/) {
            jeff.setRightSlidePower(1);
        } else if (gamepad2.right_bumper /*&& -jeff.getRightSlidePos() < SLIDE_UPPER_BOUND*/) {
            jeff.setRightSlidePower(-1);
        } else {
            jeff.setRightSlidePower(0);
        }

        prevX1 = gamepad1.x;
        prevX2 = gamepad2.x;
        prevB  = gamepad1.b;
        prevY2 = gamepad2.y;


        jeff.encoderTelemetry(telemetry);
    }
}
