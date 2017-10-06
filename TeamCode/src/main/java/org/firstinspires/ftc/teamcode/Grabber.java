package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * This opmode "Grabber" closes the grabber when the "A" button on {@link #gamepad1}
 * is pressed and opens when it is released.
 */

@Disabled
@TeleOp
public class Grabber extends OpMode {
    private Servo grabberServo1;
    private Servo grabberServo2;

    @Override
    public void init() {
        grabberServo1 = hardwareMap.servo.get("grabber1");
        grabberServo2 = hardwareMap.servo.get("grabber2");
        grabberServo1.setPosition(.55);
        grabberServo2.setPosition(.5);
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            grabberServo1.setPosition(1);
            grabberServo2.setPosition(0);
        } else {
            grabberServo1.setPosition(.55);
            grabberServo2.setPosition(.5);
        }
    }
}
