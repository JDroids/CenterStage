package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDrive {

    private DcMotor frontLeftMotor;
    private DcMotor rearLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor rearRightMotor;
    private double driveMaxSpeed = 1.0;

    public void init(HardwareMap hardwareMap) {
        frontLeftMotor = hardwareMap.dcMotor.get("leftFront");
        rearLeftMotor = hardwareMap.dcMotor.get("leftRear");
        frontRightMotor = hardwareMap.dcMotor.get("rightFront");
        rearRightMotor = hardwareMap.dcMotor.get("rightRear");

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        rearLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void drive(double forward, double right, double rotate) {
        double frontLeftPower = forward + right + rotate;
        double frontRightPower = forward - right - rotate;
        double backLeftPower = forward - right + rotate;
        double backRightPower = forward + right - rotate;

        setPowers(frontLeftPower, frontRightPower, backLeftPower, backRightPower);

    }

    private void setPowers(double frontLeftPower, double frontRightPower, double backLeftPower, double backRightPower) {
        double maxSpeed = driveMaxSpeed;
        maxSpeed = Math.max(maxSpeed, Math.abs(frontLeftPower));
        maxSpeed = Math.max(maxSpeed, Math.abs(frontRightPower));
        maxSpeed = Math.max(maxSpeed, Math.abs(backLeftPower));
        maxSpeed = Math.max(maxSpeed, Math.abs(backRightPower));

        frontLeftPower /= maxSpeed;
        frontRightPower /= maxSpeed;
        backLeftPower /= maxSpeed;
        backRightPower /= maxSpeed;

        frontLeftMotor.setPower(frontLeftPower);
        rearLeftMotor.setPower(backLeftPower);
        frontRightMotor.setPower(frontRightPower);
        rearRightMotor.setPower(backRightPower);
    }
    public void setDriveMaxSpeed(double maxSpeed) {
        driveMaxSpeed = maxSpeed;
    }
    public void testFrontLeftMotor() {frontLeftMotor.setPower(1.0);

    }
    public void testFrontRightMotor() {
        frontRightMotor.setPower(1.0);
    }
    public void testRearLeftMotor() { rearLeftMotor.setPower(1.0);

    }
    public void testRearRightMotor() {
        rearRightMotor.setPower(1.0);
    }

}
