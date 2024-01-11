package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class CenterStageAutoBlueStageC6ByTime extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();


    public static double leg1 = 650.00;   //125.00, 375.00, 650.00  //550.00
    public static double leg2 = 325.00;  //330.00, 325.00 //225
    public static double leg3 = 250.00;  //350.00, 250.00, 350.00 //200
    public static double leg4 = 100.00;   //75.00
    public static double pause = 2000.00;
    public static double initialPause = 0.00;

    MecanumDrive drive = new MecanumDrive();
    //Claw claw = new Claw();
    //Wrist wrist = new Wrist();
    BandedDroidLauncher launcher = new BandedDroidLauncher();
    RobotHanger hanger = new RobotHanger();
    Intake intake = new Intake();

    @Override
    public void runOpMode(){
        telemetry.addData("Autonomous","Blue Stage");
        telemetry.update();
        drive.init(hardwareMap);
        //claw.init(hardwareMap);
        //wrist.init(hardwareMap);
        launcher.init(hardwareMap);
        hanger.init(hardwareMap);
        intake.init(hardwareMap);

        waitForStart();

        // Step 0:  Add time for alliance partner to get out of the way
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < initialPause)) {
            telemetry.addData("Path", "Leg 0 Pause: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(0, 0, 0);
        }

        // Step 1:  Drive forward
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < leg1)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(1, 0, 0);
        }
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < pause)) {
            telemetry.addData("Path", "Leg 1 Pause: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(0, 0, 0);
        }

        // Step 2:  Rotate 90 degree
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < leg2)) {
            telemetry.addData("Path", "Leg 2: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(0, 0, -1);
        }
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < pause)) {
            telemetry.addData("Path", "Leg 2 Pause: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(0, 0, 0);
        }

        // Step 3:  Drive forward
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < leg3)) {
            telemetry.addData("Path", "Leg 3: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(1, 0, 0);
        }
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < pause)) {
            telemetry.addData("Path", "Leg 3 Pause: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(0, 0, 0);
        }

        //Open claw and drop pixel
        //claw.openClaw();
        //telemetry.addData("Claw Postion", claw.getPosition());
        //telemetry.update();
        intake.depositPixels();

        // Step 4:  Drive backward
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < leg4)) {
            telemetry.addData("Path", "Leg 3: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(-1, 0, 0);
        }
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < pause)) {
            telemetry.addData("Path", "Leg 4 Pause: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(0, 0, 0);
        }
        //Close claw and to get ready for teleop
        //claw.closeClaw();
        //wrist.autoEndWrist();
        intake.stopIntake();
    }

}
