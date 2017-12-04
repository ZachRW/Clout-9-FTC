package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This opmode is for the autonomous period of the competition.
 * The robot starts on the left balance plate on the blue side of the field.
 * More information will be added further into development of this opmode.
 */

@Autonomous
public class BlueLeft extends LinearOpMode {
    private final Hardware jeff = new Hardware();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        jeff.init(hardwareMap, telemetry);
        waitForStart();

        jeff.runToPos(.75, 500, 0, 1);
        sleep(500);
        boolean moreBlue = jeff.moreBlue();
        telemetry.addData("Blue", moreBlue);
        telemetry.update();
        sleep(500);
        jeff.slideRunToPos(1, 2000, 2000, 5);
        jeff.runToPos(1, -500, -500, 1);
    }
}
