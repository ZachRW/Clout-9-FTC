package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import static java.lang.Thread.sleep;

/**
 * Instances of Hardware provide several methods for initializing
 * and controlling the robot.
 */

public class Hardware {
    private ColorSensor colorSensor;
    private DcMotor leftSlide, rightSlide,
            leftConveyor, rightConveyor,
            leftWheel, rightWheel, centerWheel,
            clawArm;
    private Servo flipper, claw;
    private boolean flipperDown, clawClosed;
    private final ElapsedTime runtime = new ElapsedTime();

    private VuforiaTrackable relicTemplate;

    void init(HardwareMap hardwareMap) {
        leftSlide     = hardwareMap.dcMotor.get("leftSlide");
        rightSlide    = hardwareMap.dcMotor.get("rightSlide");
        leftConveyor  = hardwareMap.dcMotor.get("leftConveyor");
        rightConveyor = hardwareMap.dcMotor.get("rightConveyor");
        leftWheel     = hardwareMap.dcMotor.get("leftWheel");
        rightWheel    = hardwareMap.dcMotor.get("rightWheel");
        centerWheel   = hardwareMap.dcMotor.get("centerWheel");
        clawArm       = hardwareMap.dcMotor.get("clawArm");
        flipper       = hardwareMap.servo.get("flipper");
        claw          = hardwareMap.servo.get("claw");
        colorSensor   = hardwareMap.colorSensor.get("colorSensor");

        leftSlide .setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftWheel .setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        clawArm   .setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftWheel .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftSlide .setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        clawArm   .setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rightSlide   .setDirection(DcMotorSimple.Direction.REVERSE);
        rightConveyor.setDirection(DcMotorSimple.Direction.REVERSE);
        centerWheel  .setDirection(DcMotorSimple.Direction.REVERSE);

        flipper.setPosition(.9);
        claw   .setPosition(0);
        enableColorSensorLed(false);
    }

    void setUpVuforia() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = "AZLv+a7/////AAAAGdyzndpq4khMnz5IMjSvhiR0XbtOlL7ZfQytGj9s" +
                "4zFCFoa+IqUA1Cjv4ghfSjfRAlRguu6cVbQVM+0Rxladi3AIKhUjIL6v5ToFrK/fxrWdwAzkQfEPM1S" +
                "3ijrTSm1N8DuZ6UoqiKoVmQGzyiWhDpTQoR1zIVkj88rOhBDYwBf0CnW++pxZ0pHlQBbh/bzBjt63AN" +
                "cuI9JyHU3/JLGSBhoIm04G3UnrjVrjKfPFlX9NOwWQLOYjQ+4B1l4M8u9BdihYgmfMST0BHON+MQ7qC" +
                "5dMs/2OSZlSKSZISN/L+x606xzc2Sv5G+ULUpaUiChG7Zlv/rncu337WhZjJ1X2pQGY7gIBcSH+TUw8" +
                "1n2jYKkm";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        VuforiaLocalizer vuforiaLocalizer = ClassFactory.createVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = vuforiaLocalizer.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");
        relicTrackables.activate();
    }

    void setWheelPower(double leftPower, double rightPower) {
        leftWheel .setPower(leftPower);
        rightWheel.setPower(rightPower);
    }

    void setCenterPower(double power) {
        centerWheel.setPower(power);
    }

    void setClawArmPower(double power) {
        clawArm.setPower(power);
    }

    void toggleFlipper() {
        flipperDown ^= true;
        if (flipperDown) {
            flipper.setPosition(.35);
        } else {
            flipper.setPosition(.9);
        }
    }

    void toggleClaw() {
        clawClosed = !clawClosed;
        if (clawClosed) {
            claw.setPosition(.4);
        } else {
            claw.setPosition(0);
        }
    }

    void enableColorSensorLed(boolean enable) {
        colorSensor.enableLed(enable);
    }

    void setLeftSlidePower(double power) {
        leftSlide.setPower(power);
    }

    void setRightSlidePower(double power) {
        rightSlide.setPower(power);
    }

    void setConveyorPower(double leftPower, double rightPower) {
        leftConveyor .setPower(leftPower);
        rightConveyor.setPower(rightPower);
    }

    void runToPos(double speed, int leftPos, int rightPos, double timeoutS) throws InterruptedException {
        leftWheel .setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftWheel .setTargetPosition(leftPos);
        rightWheel.setTargetPosition(rightPos);
        leftWheel .setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        runtime.reset();
        leftWheel .setPower(speed);
        rightWheel.setPower(speed);

        while ((leftWheel.isBusy() || rightWheel.isBusy()) && runtime.seconds() < timeoutS) {
            sleep(100);
        }

        leftWheel .setPower(0);
        rightWheel.setPower(0);
        leftWheel .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    void encoderTelemetry(Telemetry telemetry) {
        telemetry.addData("Left Slide Pos",  leftSlide .getCurrentPosition());
        telemetry.addData("Right Slide Pos", rightSlide.getCurrentPosition());
        telemetry.addData("Left Wheel Pos",  leftWheel .getCurrentPosition());
        telemetry.addData("Right Wheel Pos", rightWheel.getCurrentPosition());
    }

    RelicRecoveryVuMark getVuMark() {
        return RelicRecoveryVuMark.from(relicTemplate);
    }

    int getLeftSlidePos() {
        return leftSlide.getCurrentPosition();
    }

    int getRightSlidePos() {
        return rightSlide.getCurrentPosition();
    }

    int getClawArmPos() {
        return clawArm.getCurrentPosition();
    }

    void freezeClaw() {
        clawArm.setTargetPosition(getClawArmPos());
    }

    boolean mostlyRed() {
        return colorSensor.red() > colorSensor.blue() + colorSensor.green();
    }

    boolean mostlyBlue() {
        return colorSensor.blue() > colorSensor.red() + colorSensor.green();
    }

    String getRGB() {
        return colorSensor.red() + ", " + colorSensor.green() + ", " + colorSensor.blue();
    }


    // Autonomus methods

    public void shleep(long milliseconds){
        try {
            Thread.sleep(milliseconds);
        }catch(InterruptedException ie)
        {
            //nothing
        }

    }
    public void moveForward () {
        rightWheel.setPower(0.5);
        leftWheel.setPower(0.5);
    }
    public void moveBackwards () {
        rightWheel.setPower(-0.5);
        leftWheel.setPower(-0.5);
    }
    public void stop(){
        rightWheel.setPower(0);
        leftWheel.setPower(0);
    }
}
