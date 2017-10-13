package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * This simple opmode will move the conveyor belts based on {@link #gamepad1}'s right stick y.
 */

@TeleOp
public class Conveyor extends OpMode {
    private DcMotor leftConveyor, rightConveyor;

    @Override
    public void init() {
        leftConveyor  = hardwareMap.dcMotor.get("leftConveyor");
        rightConveyor = hardwareMap.dcMotor.get("rightConveyor");
        rightConveyor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        setConveyorPower(-gamepad1.right_stick_y);
    }

    private void setConveyorPower(double power) {
        leftConveyor.setPower(power);
        rightConveyor.setPower(power);
    }
}
