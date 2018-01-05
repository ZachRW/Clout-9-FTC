package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Robotics on 12/14/2017.
 */

@Autonomous
public class RedRight extends LinearOpMode {
    private final Hardware jeff = new Hardware();

    @Override
    public void runOpMode() throws InterruptedException {
        jeff.setTelemetry(telemetry);
        jeff.init(hardwareMap);
        jeff.wheelBrake(true);
        waitForStart();
        jeff.setServoPositions();
        sleep(1_000);
        jeff.setJewelSweeperPosition(1);
        sleep(500);
        boolean blue = jeff.moreBlue();
        telemetry.addData("Blue", blue);
        telemetry.update();
        if (blue) {
            jeff.runToPos(.1, -200, 200, 2);
        } else {
            jeff.runToPos(.1, 200, -200, 2);
        }
        jeff.setJewelSweeperPosition(.45);
        sleep(1_000);
        if (blue) {
            jeff.runToPos(.1, 200, -200, 2);
        } else {
            jeff.runToPos(.1, -200, 200, 2);
        }
    }
}
