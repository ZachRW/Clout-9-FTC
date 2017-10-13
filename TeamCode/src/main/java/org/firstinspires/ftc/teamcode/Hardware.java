package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

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
//    private ColorSensor colorSensor;
    private DcMotor leftSlide, rightSlide,
            leftConveyor, rightConveyor,
            leftWheel, rightWheel;

    private VuforiaTrackable relicTemplate;

    void init(HardwareMap hardwareMap) {
        leftSlide       = hardwareMap.dcMotor.get("leftSlide");
        rightSlide      = hardwareMap.dcMotor.get("rightSlide");
        leftConveyor    = hardwareMap.dcMotor.get("leftConveyor");
        rightConveyor   = hardwareMap.dcMotor.get("rightConveyor");
        leftWheel  = hardwareMap.dcMotor.get("leftWheel");
        rightWheel = hardwareMap.dcMotor.get("rightWheel");
//        colorSensor     = hardwareMap.colorSensor.get("colorSensor");
//        colorSensor.enableLed(true);
        rightConveyor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightWheel   .setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private void setUpVuforia() {
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

    void setLeftSlidePower(double power) {
        leftSlide.setPower(power);
    }

    void setRightSlidePower(double power) {
        rightSlide.setPower(power);
    }

    RelicRecoveryVuMark getVuMark() {
        return RelicRecoveryVuMark.from(relicTemplate);
    }

//    boolean mostlyRed() {
//        return colorSensor.red() > colorSensor.blue() + colorSensor.green();
//    }

//    boolean mostlyBlue() {
//        return colorSensor.blue() > colorSensor.red() + colorSensor.green();
//    }

//    String getRGB() {
//        return colorSensor.red() + ", " + colorSensor.green() + ", " + colorSensor.blue();
//    }

    void setConveyorPower(double leftPower, double rightPower) {
        leftConveyor.setPower(leftPower);
        rightConveyor.setPower(rightPower);
    }
}
