package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * This opmode "Grabber" closes the grabber when the "A" button on gamepad 1
 * is pressed and opens when it is released.
 */

@TeleOp(name = "Grabber")
public class Grabber extends OpMode {
    private Hardware robot = new Hardware();

    @Override
    public void init() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        robot.grab(gamepad1.a);
    }
}
