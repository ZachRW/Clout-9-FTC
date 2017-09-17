package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Zach on 9/17/17.
 */

@TeleOp(name = "Grabber")
public class Grabber extends OpMode {
    private Servo grabberServo1;
    private Servo grabberServo2;

    @Override
    public void init() {
        grabberServo1 = hardwareMap.servo.get("grabber1");
        grabberServo2 = hardwareMap.servo.get("grabber2");
        grabberServo1.setPosition(0);
        grabberServo2.setPosition(1);
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            grabberServo1.setPosition(.5);
            grabberServo2.setPosition(.5);
        } else {
            grabberServo1.setPosition(0);
            grabberServo2.setPosition(1);
        }
    }
}
