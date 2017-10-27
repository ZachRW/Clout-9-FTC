package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Instances of Hardware provide several methods for initializing
 * and controlling the robot.
 */

public class Hardware {
    private ColorSensor colorSensor;
    private DcMotor leftSlide, rightSlide,
            leftConveyor, rightConveyor,
            leftWheel, rightWheel, centerWheel;
    private Servo grabber, flipper;
    private boolean grabberDown = false, flipperDown = false;

    private VuforiaTrackable relicTemplate;

    void init(HardwareMap hardwareMap) {
        leftSlide     = hardwareMap.dcMotor.get("leftSlide");
        rightSlide    = hardwareMap.dcMotor.get("rightSlide");
        leftConveyor  = hardwareMap.dcMotor.get("leftConveyor");
        rightConveyor = hardwareMap.dcMotor.get("rightConveyor");
        leftWheel     = hardwareMap.dcMotor.get("leftWheel");
        rightWheel    = hardwareMap.dcMotor.get("rightWheel");
        centerWheel   = hardwareMap.dcMotor.get("centerWheel");
//        grabber       = hardwareMap.servo.get("grabber");
        flipper       = hardwareMap.servo.get("flipper");
        colorSensor   = hardwareMap.colorSensor.get("colorSensor");
        rightConveyor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightWheel   .setDirection(DcMotorSimple.Direction.REVERSE);
//        grabber.setPosition(0);
        flipper.setPosition(.75);
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

    void reverseWheels() {
        rightWheel.setDirection(
                rightWheel.getDirection() == DcMotorSimple.Direction.FORWARD ?
                        DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);
        leftWheel.setDirection(
                leftWheel.getDirection() == DcMotorSimple.Direction.FORWARD ?
                        DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);

    }

    private void toggleDirection(DcMotor motor) {
        motor.setDirection(motor.getDirection() == DcMotorSimple.Direction.FORWARD ?
                DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);
    }

//    void toggleGrabber() {
//        grabberDown = !grabberDown;
//        if (grabberDown) {
//            grabber.setPosition(.5);
//        } else {
//            grabber.setPosition(0);
//        }
//    }

    void toggleFlipper() {
        flipperDown = !flipperDown;
        if (flipperDown) {
            flipper.setPosition(.75);
        } else {
            flipper.setPosition(0);
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

    RelicRecoveryVuMark getVuMark() {
        return RelicRecoveryVuMark.from(relicTemplate);
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

    void setConveyorPower(double leftPower, double rightPower) {
        leftConveyor.setPower(leftPower);
        rightConveyor.setPower(rightPower);
    }
}
