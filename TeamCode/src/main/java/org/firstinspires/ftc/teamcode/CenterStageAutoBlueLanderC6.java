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
public class CenterStageAutoBlueLanderC6 extends LinearOpMode {

    public static double initialPause = 0.00;
    public static double clawPause = 250;
    public static double testPause = 1000;
    //public static double testDistance = 23.74;
    private ElapsedTime runtime = new ElapsedTime();
    private final double MOTOR_TICKS = 537.7; //GOBILDA 5202
    private final double INCHES_TO_TICKS = 47.0; //GOBILD 96mm Mecanum wheels
    private final double INCHES_TO_NINETY_DEGREE = 19.00;
    private final double INCHES_TO_ONEEIGHTY_DEGREE = 39.00;
    private final double INCHES_TO_SPIKE = 29.00;
    private final double INCHES_OFFSET = 6.00;
    private final double INCHES_TO_STAGE = 36.00;
    private final double INCHES_TO_NEXT_TILE = 22;
    private final double DRIVE_MAX_POWER = 0.50;


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
                drive.moveBackward(inchesToTicks(4));
                wrist.downWrist();
                drive.moveForward(inchesToTicks(3));
                claw.openClaw();
                drive.moveBackward(inchesToTicks(2 ));
                claw.closeClaw();
                wrist.liftWrist();
                drive.moveClockwise(inchesToTicks(INCHES_TO_NINETY_DEGREE));
                drive.moveForward(inchesToTicks(INCHES_TO_NEXT_TILE - 5));
                drive.moveCounterClockwise(inchesToTicks(INCHES_TO_NINETY_DEGREE));
                drive.moveForward(inchesToTicks(INCHES_TO_NEXT_TILE *4 - INCHES_OFFSET));
                break;
            case MIDDLE:
                drive.moveClockwise(inchesToTicks(INCHES_TO_ONEEIGHTY_DEGREE));
                wrist.downWrist();
                drive.moveBackward(inchesToTicks(2*INCHES_OFFSET + 3));
                claw.openClaw();
                drive.moveBackward(inchesToTicks(3));
                claw.closeClaw();
                wrist.liftWrist();
                drive.moveClockwise(inchesToTicks(INCHES_TO_NINETY_DEGREE-1));
                drive.moveForward(inchesToTicks(INCHES_TO_NEXT_TILE *4 - INCHES_OFFSET));
                break;
            case RIGHT:
                drive.moveClockwise(inchesToTicks(INCHES_TO_NINETY_DEGREE));
                drive.moveBackward(inchesToTicks(3 ));
                wrist.downWrist();
                drive.moveForward(inchesToTicks(3 ));
                claw.openClaw();
                drive.moveBackward(inchesToTicks(2 ));
                wrist.liftWrist();
                claw.closeClaw();
                drive.moveCounterClockwise(inchesToTicks(INCHES_TO_NINETY_DEGREE ));
                drive.moveForward(inchesToTicks(INCHES_TO_NEXT_TILE-5));
                drive.moveCounterClockwise(inchesToTicks(INCHES_TO_NINETY_DEGREE));
                drive.moveForward(inchesToTicks(INCHES_TO_NEXT_TILE *4 - INCHES_OFFSET ));
                break;
            case NONE:
                drive.moveClockwise(inchesToTicks(INCHES_TO_ONEEIGHTY_DEGREE));
                wrist.downWrist();
                drive.moveBackward(inchesToTicks(2*INCHES_OFFSET + 3));
                claw.openClaw();
                drive.moveBackward(inchesToTicks(3));
                claw.closeClaw();
                wrist.liftWrist();
                drive.moveClockwise(inchesToTicks(INCHES_TO_NINETY_DEGREE-1));
                drive.moveForward(inchesToTicks(INCHES_TO_NEXT_TILE *4 - INCHES_OFFSET));
                break;

        }

        /**
        switch (centerStageVisionProcessor.getSelection()) {
            case LEFT:
                wrist.downWrist();
                drive.moveCounterClockwise(inchesToTicks(INCHES_TO_NINETY_DEGREE));
                claw.openClaw();
                drive.moveBackward(inchesToTicks(2 ));
                wrist.liftWrist();
                claw.closeClaw();
                drive.moveCounterClockwise(inchesToTicks(INCHES_TO_NINETY_DEGREE));
                drive.moveForward(inchesToTicks(INCHES_TO_SPIKE - INCHES_OFFSET));
                drive.moveClockwise(inchesToTicks(INCHES_TO_NINETY_DEGREE + 1));
                break;
            case MIDDLE:
                drive.moveBackward(inchesToTicks(INCHES_OFFSET ));
                wrist.downWrist();
                drive.moveForward(inchesToTicks(3 ));
                claw.openClaw();
                runtime.reset();
                while (opModeIsActive() && (runtime.milliseconds() < clawPause)) {  }
                wrist.liftWrist();
                claw.closeClaw();
                drive.moveBackward(inchesToTicks(INCHES_TO_SPIKE - INCHES_OFFSET));
                drive.moveCounterClockwise(inchesToTicks(INCHES_TO_NINETY_DEGREE + 1));
                break;
            case RIGHT:
                drive.moveClockwise(inchesToTicks(INCHES_TO_NINETY_DEGREE));
                drive.moveBackward(inchesToTicks(3 ));
                wrist.downWrist();
                drive.moveForward(inchesToTicks(3 ));
                claw.openClaw();
                drive.moveBackward(inchesToTicks(2 ));
                wrist.liftWrist();
                claw.closeClaw();
                drive.moveClockwise(inchesToTicks(INCHES_TO_NINETY_DEGREE +1));
                drive.moveForward(inchesToTicks(INCHES_TO_SPIKE - INCHES_OFFSET));
                drive.moveClockwise(inchesToTicks(INCHES_TO_NINETY_DEGREE + 1));

                break;
            case NONE:
                drive.moveBackward(inchesToTicks(INCHES_OFFSET ));
                wrist.downWrist();
                drive.moveForward(inchesToTicks(3 ));
                claw.openClaw();
                runtime.reset();
                while (opModeIsActive() && (runtime.milliseconds() < clawPause)) {  }
                wrist.liftWrist();
                claw.closeClaw();
                drive.moveBackward(inchesToTicks(INCHES_TO_SPIKE - INCHES_OFFSET));
                drive.moveCounterClockwise(inchesToTicks(INCHES_TO_NINETY_DEGREE + 1));
                break;
        }

        drive.moveForward(inchesToTicks(INCHES_TO_NEXT_TILE *2 ));
        drive.moveClockwise(inchesToTicks(INCHES_TO_NINETY_DEGREE/2));
        drive.moveForward(inchesToTicks(INCHES_TO_NEXT_TILE*2));
        //drive.moveCounterClockwise(inchesToTicks(INCHES_TO_NINETY_DEGREE));
        //drive.moveForward(inchesToTicks(INCHES_TO_NEXT_TILE));
        **/

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
