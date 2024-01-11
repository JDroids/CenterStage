package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.acmerobotics.dashboard.FtcDashboard;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.processor.CenterStageVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@Config
@Autonomous
public class CenterStageAutoBlueLanderB6 extends LinearOpMode {

    public static double initialPause = 0.00;
    //public static double testDistance = 23.74;
    private ElapsedTime runtime = new ElapsedTime();
    private final double MOTOR_TICKS = 537.7; //GOBILDA 5202
    private final double INCHES_TO_TICKS = 45.30; //GOBILD 96mm Mecanum wheels
    private final double INCHES_TO_NINETY_DEGREE = 19.50;
    private final double INCHES_TO_ONEEIGHTY_DEGREE = 39.00;
    private final double INCHES_TO_SPIKE = 26.00;
    private final double INCHES_OFFSET = 0.00;
    private final double INCHES_TO_STAGE_SPIKE = 49.00;
    private final double INCHES_TO_STAGE = 36.00;
    private final double INCHES_TO_NEXT_TILE = 22;
    private final double DRIVE_MAX_POWER = 0.35;


    AutoMecanumDrive drive = new AutoMecanumDrive();
    //Intake intake = new Intake();
    Claw claw = new Claw();
    Wrist wrist = new Wrist();
    private CenterStageVisionProcessor centerStageVisionProcessor; //processor to identify correct alignment on the image captured
    private VisionPortal visionPortal;  //provides a way to handle the camera and the stream from the camera

    @Override
    public void runOpMode(){
        drive.init(hardwareMap);
        claw.init(hardwareMap);
        wrist.init(hardwareMap);
        //intake.init(hardwareMap);
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1"); // get reference to to the webcamera hooked to the hub
        centerStageVisionProcessor = new CenterStageVisionProcessor();
        visionPortal = VisionPortal.easyCreateWithDefaults(webcamName, centerStageVisionProcessor);

        waitForStart();
        visionPortal.stopStreaming();
        drive.setDriveMaxPower(DRIVE_MAX_POWER);
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < initialPause)) {

        }

        drive.moveForward(inchesToTicks(INCHES_TO_SPIKE));

        switch (centerStageVisionProcessor.getSelection()) {
            case LEFT:
                drive.moveCounterClockwise(inchesToTicks(INCHES_TO_NINETY_DEGREE));
                drive.moveForward(inchesToTicks(INCHES_OFFSET));
                depositRandomizedPixel();
                //drive.moveBackward(INCHES_OFFSET);
                //drive.moveCounterClockwise(inchesToTicks(INCHES_TO_ONEEIGHTY_DEGREE));
                drive.moveForward(inchesToTicks(INCHES_TO_STAGE_SPIKE));
                drive.moveForward(inchesToTicks(INCHES_TO_STAGE));
                break;
            case MIDDLE:
                drive.moveForward(inchesToTicks(INCHES_OFFSET));
                depositRandomizedPixel();
                drive.moveBackward(inchesToTicks(INCHES_OFFSET));
                drive.moveCounterClockwise(inchesToTicks(INCHES_TO_NINETY_DEGREE));
                drive.moveForward(inchesToTicks(INCHES_TO_STAGE_SPIKE));
                drive.moveForward(inchesToTicks(INCHES_TO_STAGE));
                break;
            case RIGHT:
                drive.moveClockwise(inchesToTicks(INCHES_TO_NINETY_DEGREE));
                drive.moveForward(inchesToTicks(INCHES_OFFSET));
                depositRandomizedPixel();
                drive.moveBackward(INCHES_OFFSET);
                drive.moveCounterClockwise(inchesToTicks(INCHES_TO_ONEEIGHTY_DEGREE));
                drive.moveForward(inchesToTicks(INCHES_TO_STAGE_SPIKE));
                drive.moveForward(inchesToTicks(INCHES_TO_STAGE));
                break;
            case NONE:
                drive.moveForward(inchesToTicks(INCHES_OFFSET));
                depositRandomizedPixel();
                drive.moveBackward(inchesToTicks(INCHES_OFFSET));
                drive.moveCounterClockwise(inchesToTicks(INCHES_TO_NINETY_DEGREE));
                drive.moveForward(inchesToTicks(INCHES_TO_STAGE_SPIKE));
                drive.moveForward(inchesToTicks(INCHES_TO_STAGE));
                break;
        }

    }

    private double inchesToTicks(double distance){
        return distance*INCHES_TO_TICKS;
    }

    private void depositRandomizedPixel(){
        wrist.downWrist();
        claw.openClaw();
        //intake.depositPixels();
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < 1000)) {  }
        claw.closeClaw();
        wrist.autoEndWrist();
        //intake.stopIntake();
    }

}
