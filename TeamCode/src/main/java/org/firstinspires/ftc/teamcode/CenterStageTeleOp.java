package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp()
public class CenterStageTeleOp extends OpMode {
    MecanumDrive drive = new MecanumDrive();
    //Intake intake = new Intake();
    Claw claw = new Claw();
    Wrist wrist = new Wrist();
    private ElapsedTime runtime = new ElapsedTime();

    //DroidLauncher launcher = new DroidLauncher();
    BandedDroidLauncher launcher = new BandedDroidLauncher();
    RobotHanger hanger = new RobotHanger();

    @Override
    public void init() {
        drive.init(hardwareMap);
        claw.init(hardwareMap);
        wrist.init(hardwareMap);
        //intake.init(hardwareMap);
        //claw = hardwareMap.get(Servo.class, "clawServo");
        launcher.init(hardwareMap);
        hanger.init(hardwareMap);
        //telemetry.addData("init Done", "initDone");
    }

    @Override
    public void loop () {
        double forward = -gamepad1.left_stick_y; //Remember, Y stick is reversed!
        double right = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rotate = gamepad1.right_stick_x;

        drive.drive(forward, right, rotate);
        /**
        if (gamepad2.left_bumper) {
            intake.intakePixels();
            telemetry.addData("Intaking", "intaking");
        }
        else if (gamepad2.right_bumper) {
            intake.depositPixels();
            telemetry.addData("Depositing", "depositing");
        }
        else {
            intake.stopIntake();
        }
         **/

        if (gamepad2.square) {
            launcher.downPlaneArm();
            //telemetry.addData("Droid Launcher", "launching");
        }
        else {
            launcher.liftPlaneArm();
            //telemetry.addData("Droid Launcher", "stopping");
        }

        if (gamepad2.triangle) {
            hanger.liftArm();
            //telemetry.addData("Robot Hanger", hanger.getPosition());
        }
        /*
        } else if (gamepad2.cross) {
            hanger.downArm();
            telemetry.addData("Robot Hanger", hanger.getPosition());
        */
        else {
            hanger.downArm();
            //telemetry.addData("Robot Hanger", hanger.getPosition());
        }



        if (gamepad2.left_bumper) {
            //claw.toggleClaw();
            //claw.setDirection(Servo.Direction.REVERSE);
            //claw.setPosition(0.6);
            //wrist.downWrist();
            claw.openClaw();
            //telemetry.addData("Claw Postion", claw.getPosition());
        }
        else if (gamepad2.right_bumper) {
            claw.closeClaw();
            //runtime.reset();
            //while ((runtime.milliseconds() < 100)) {  }
            //wrist.liftWrist();
            //telemetry.addData("Claw Postion", claw.getPosition());
        }
        if (gamepad2.left_trigger > 0) {
            //claw.toggleClaw();
            //claw.setDirection(Servo.Direction.REVERSE);
            //claw.setPosition(0.6);
            wrist.downWrist();
            runtime.reset();
            while ((runtime.milliseconds() < 100)) {  }
            claw.openClaw();
            //telemetry.addData("Claw Postion", claw.getPosition());
        }
        else if (gamepad2.right_trigger > 0) {
            claw.closeClaw();
            runtime.reset();
            while ((runtime.milliseconds() < 100)) {  }
            wrist.liftWrist();
            //telemetry.addData("Claw Postion", claw.getPosition());
        }


         //Moving away from wrist as there is no claw
        if (gamepad2.dpad_up) {
            //claw.closeClaw();// close the claw before lifting the wrist otherwise it will get stuck
            claw.closeClaw();
            wrist.liftWrist();
            telemetry.addData("Wrist Postion", wrist.getPosition());
        }
        else if (gamepad2.dpad_down) {
            wrist.downWrist();
            telemetry.addData("Wrist Postion", wrist.getPosition());
        }
        else {
            wrist.updateWrist();
        }
        //FtcDashboard.getInstance().getTelemetry().addData("Claw Position", claw.getPosition());
        //FtcDashboard.getInstance().getTelemetry().update();
        //FtcDashboard.getInstance().getTelemetry().addData("Wrist Position", wrist.getPosition());
        //FtcDashboard.getInstance().getTelemetry().update();

        /** Code to test individual wheels and config file
        if (gamepad1.y) {
            drive.testFrontLeftMotor();
        } else if (gamepad1.x) {
            drive.testRearLeftMotor();

        } else if (gamepad1.b)
        {
            drive.testRearRightMotor();
        } else if (gamepad1.a) {
            drive.testFrontRightMotor();
        }
         **/


    }

}
