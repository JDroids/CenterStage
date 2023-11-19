package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class CenterStageAutoRedLander extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();


    public static double leg1 = 300.00;   //Strafe
    public static double leg2 = 800.00;  //Go forward 400.00
    public static double leg3 = 360.00;  //Rotate clockwise 330.00
    public static double leg4 = 750.00;   //Go forward 650.00
    public static double leg5 = 375.00;   //Rotate counter clockwise 330.00
    public static double leg6 = 0.00;   //Go forward 350.00
    public static double leg7 = 0.00;   //Go backward 75.00
    public static double pause = 2000.00;
    public static double initialPause = 0.00;

    MecanumDrive drive = new MecanumDrive();
    Claw claw = new Claw();

    @Override
    public void runOpMode() {
        telemetry.addData("Autonomous", "Blue Lander");
        telemetry.update();
        drive.init(hardwareMap);
        claw.init(hardwareMap);

        waitForStart();

        // Step 0:  Add time for alliance partner to get out of the way
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < initialPause)) {
            telemetry.addData("Path", "Leg 0 Pause: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(0, 0, 0);
        }

        // Step 1:  Strafe so that robot does not get caught in the truss
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < leg1)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(0, 1, 0);
        }
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < pause)) {
            telemetry.addData("Path", "Leg 1 Pause: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(0, 0, 0);
        }

        // Step 2:  Drive Forward
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < leg2)) {
            telemetry.addData("Path", "Leg 2: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(1, 0, 0);
        }
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < pause)) {
            telemetry.addData("Path", "Leg 2 Pause: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(0, 0, 0);
        }

        // Step 3:  Rotate 90 degree clockwise
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < leg3)) {
            telemetry.addData("Path", "Leg 3: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(0, 0, 1);
        }
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < pause)) {
            telemetry.addData("Path", "Leg 3 Pause: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(0, 0, 0);
        }

        // Step 4: Drive Forward
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < leg4)) {
            telemetry.addData("Path", "Leg 4: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(1, 0, 0);
        }
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < pause)) {
            telemetry.addData("Path", "Leg 4 Pause: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(0, 0, 0);
        }

        // Step 5:  Rotate 90 degree counter clockwise
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < leg5)) {
            telemetry.addData("Path", "Leg 5: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(0, 0, -1);
        }
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < pause)) {
            telemetry.addData("Path", "Leg 5 Pause: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(0, 0, 0);
        }

        // Step 6:  Drive forward
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < leg6)) {
            telemetry.addData("Path", "Leg 6: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(1, 0, 0);
        }
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < pause)) {
            telemetry.addData("Path", "Leg 6 Pause: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(0, 0, 0);
        }

        //Open claw and drop pixel
        claw.openClaw();
        telemetry.addData("Claw Postion", claw.getPosition());
        telemetry.update();

        // Step 7: Drive Backward
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < leg7)) {
            telemetry.addData("Path", "Leg 7: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(-1, 0, 0);
        }
        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < pause)) {
            telemetry.addData("Path", "Leg 7 Pause: %4.1f S Elapsed", runtime.milliseconds());
            telemetry.update();
            drive.drive(0, 0, 0);
        }

    }
}
