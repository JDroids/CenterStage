package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp()
public class CenterStageTeleOp extends OpMode {
    MecanumDrive drive = new MecanumDrive();
    Claw claw = new Claw();
    //private Servo claw;

    Wrist wrist = new Wrist();

    @Override
    public void init() {
        drive.init(hardwareMap);
        claw.init(hardwareMap);
        wrist.init(hardwareMap);
        //claw = hardwareMap.get(Servo.class, "clawServo");
        telemetry.addData("init Done", "initDone");
    }

    @Override
    public void loop () {
        double forward = -gamepad1.left_stick_y; //Remember, Y stick is reversed!
        double right = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rotate = gamepad1.right_stick_x;

        drive.drive(forward, right, rotate);

        if (gamepad2.cross) {
            //claw.toggleClaw();
            //claw.setDirection(Servo.Direction.REVERSE);
            //claw.setPosition(0.6);

            claw.openClaw();
            telemetry.addData("Claw Postion", claw.getPosition());
        }
        else if (gamepad2.triangle || gamepad2.right_bumper) {
            claw.closeClaw();
            telemetry.addData("Claw Postion", claw.getPosition());
        }

        if (gamepad2.dpad_up) {
            wrist.liftWrist();
            telemetry.addData("Wrist Postion", wrist.getPosition());
        }
        else if (gamepad2.dpad_down) {
            wrist.downWrist();
            telemetry.addData("Wrist Postion", wrist.getPosition());
        }


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