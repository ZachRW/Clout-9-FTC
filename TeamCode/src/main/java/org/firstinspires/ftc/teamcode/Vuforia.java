package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * This opmode tests Vuforia's identification of the pictographs.
 * It uses telemetry to output the type of pictograph or "UNKNOWN" if it can't identify one.
 */

@Autonomous
public class Vuforia extends OpMode {
    private Hardware jeff = new Hardware();

    @Override
    public void init() {
        jeff.setUpVuforia();
    }

    @Override
    public void loop() {
        telemetry.addData("VuMark", jeff.getVuMark());
    }
}
