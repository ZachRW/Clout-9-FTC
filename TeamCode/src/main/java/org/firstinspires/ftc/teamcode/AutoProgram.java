package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Robotics on 11/26/2017.
 */

@Autonomous
public class AutoProgram extends OpMode {
    private final Hardware jeff = new Hardware();

    @Override
    public void init() {
        jeff.init(hardwareMap);
    }


    @Override
    public void loop () {
        jeff.moveForward();
        jeff.shleep(2000);
        jeff.moveBackwards();
        jeff.shleep(2000);
        jeff.stop();
        return;
    }
}
