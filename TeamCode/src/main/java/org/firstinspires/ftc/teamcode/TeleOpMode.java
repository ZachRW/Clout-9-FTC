package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * This is the opmode for the teleop period of the competition.
 *
 * Pressing up, down, and left/right on the {@link #gamepad1} directional pad changes the wheel
 * speed to 1, .75, and .5 respectively.
 * Pressing the 'A' button on {@link #gamepad1} toggles the flipper to move up and down.
 * The y of {@link #gamepad1}'s sticks control the wheels.
 * The y of {@link #gamepad2}'s sticks control the conveyor belts.
 * The triggers and bumpers of {@link #gamepad2} control the linear slides.
 */

@TeleOp
public class TeleOpMode extends OpMode {
    private Hardware jeff = new Hardware();
    private double wheelSpeed = 1;
    private boolean prevA1 = false;
//    private boolean prevA2 = false;

    @Override
    public void init() {
        jeff.init(hardwareMap);
    }

    @Override
    public void loop() {
        jeff.setWheelPower(-gamepad1.left_stick_y * wheelSpeed,
                -gamepad1.right_stick_y * wheelSpeed);
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

        jeff.setConveyorPower(-gamepad2.left_stick_y, -gamepad2.right_stick_y);
        if (gamepad2.left_trigger > 0) {
            jeff.setLeftSlidePower(1);
        } else if (gamepad2.left_bumper) {
            jeff.setLeftSlidePower(-1);
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

        prevA1 = gamepad1.a;
//        prevA2 = gamepad2.a;
    }
}
