package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.acmerobotics.dashboard.FtcDashboard;

public class AutoMecanumDrive {

    private DcMotor frontLeftMotor;
    private DcMotor rearLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor rearRightMotor;
    private double driveMaxPower = 1.0;

    public void init(HardwareMap hardwareMap) {
        frontLeftMotor = hardwareMap.dcMotor.get("leftFront");
        frontRightMotor = hardwareMap.dcMotor.get("rightFront");
        rearLeftMotor = hardwareMap.dcMotor.get("leftRear");
        rearRightMotor = hardwareMap.dcMotor.get("rightRear");

        setZeroPowerBehavior();
        resetEncoders();

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        rearLeftMotor.setDirection(DcMotor.Direction.REVERSE);

    }

    public void moveForward(double ticks) {
        resetTargetPostions();
        setModeToRunToPosition();

        setTargetPositions(ticks, ticks, ticks, ticks);
        setPowers();

        while (frontLeftMotor.isBusy() || frontRightMotor.isBusy() || rearLeftMotor.isBusy() || rearRightMotor.isBusy()) {
            updateTelemetry();
        }
        resetEncoders();

    }

    public void moveBackward(double ticks) {
        resetTargetPostions();
        setModeToRunToPosition();

        setTargetPositions(-ticks, -ticks,-ticks, -ticks);
        setPowers();

        while (frontLeftMotor.isBusy() || frontRightMotor.isBusy() || rearLeftMotor.isBusy() || rearRightMotor.isBusy()) {
            updateTelemetry();
        }
        resetEncoders();

    }

    public void moveClockwise(double ticks) {
        resetTargetPostions();
        setModeToRunToPosition();

        setTargetPositions(ticks, -ticks, ticks, -ticks);
        setPowers();

        while (frontLeftMotor.isBusy() || frontRightMotor.isBusy() || rearLeftMotor.isBusy() || rearRightMotor.isBusy()) {
            updateTelemetry();
        }
        resetEncoders();

    }
    public void moveCounterClockwise(double ticks) {

        resetTargetPostions();
        setModeToRunToPosition();

        setTargetPositions(-ticks, ticks,-ticks, ticks);
        setPowers();

        while (frontLeftMotor.isBusy() || frontRightMotor.isBusy() || rearLeftMotor.isBusy() || rearRightMotor.isBusy()) {
            updateTelemetry();
        }
        resetEncoders();

    }

    public void setTargetPositions (double frontLeftTicks, double frontRightTicks, double rearLeftTicks, double rearRightTicks ) {
        frontLeftMotor.setTargetPosition((int)frontLeftTicks);
        frontRightMotor.setTargetPosition((int)frontRightTicks);
        rearLeftMotor.setTargetPosition((int)rearLeftTicks);
        rearRightMotor.setTargetPosition((int)rearRightTicks);
    }

    private void setPowers() {
        frontLeftMotor.setPower(driveMaxPower);
        rearLeftMotor.setPower(driveMaxPower);
        frontRightMotor.setPower(driveMaxPower);
        rearRightMotor.setPower(driveMaxPower);
    }
    private void resetTargetPostions(){
        frontLeftMotor.setTargetPosition(frontLeftMotor.getCurrentPosition()); // setting it as it seems to be a bug.  A error is thrown if using RUN_TO_POSITION and setTargetPosition not set
        frontRightMotor.setTargetPosition(frontRightMotor.getCurrentPosition());
        rearLeftMotor.setTargetPosition(rearLeftMotor.getCurrentPosition());
        rearRightMotor.setTargetPosition(rearRightMotor.getCurrentPosition());
    }
    public void setDriveMaxPower(double maxPower) {
        driveMaxPower = maxPower;
    }
    private void resetEncoders () {
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }
    private void setZeroPowerBehavior(){
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    private void setModeToRunToPosition () {
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    private void updateTelemetry () {
        FtcDashboard.getInstance().getTelemetry().addData("Front Left Motor: ", frontLeftMotor.getCurrentPosition());
        FtcDashboard.getInstance().getTelemetry().update();
        FtcDashboard.getInstance().getTelemetry().addData("Front Right Motor: ", frontRightMotor.getCurrentPosition());
        FtcDashboard.getInstance().getTelemetry().update();
        FtcDashboard.getInstance().getTelemetry().addData("Rear Left Motor: ", rearLeftMotor.getCurrentPosition());
        FtcDashboard.getInstance().getTelemetry().update();
        FtcDashboard.getInstance().getTelemetry().addData("Rear Right Motor: ", rearRightMotor.getCurrentPosition());
        FtcDashboard.getInstance().getTelemetry().update();
    }

    /**
    public void testFrontLeftMotor(double ticks) {
        frontLeftMotor.setTargetPosition(frontLeftMotor.getCurrentPosition()); // setting it as it seems to be a bug.  A error is thrown if using RUN_TO_POSITION and setTargetPosition not set
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeftMotor.setTargetPosition((int)ticks);
        frontLeftMotor.setPower(driveMaxPower);  // Set power to 1 as other power setting do not seem to work

        while (frontLeftMotor.isBusy()) {
            FtcDashboard.getInstance().getTelemetry().addData("Front Left Motor: ", frontLeftMotor.getCurrentPosition());
            FtcDashboard.getInstance().getTelemetry().update();

        }
        resetEncoders();
    }
    public void testFrontRightMotor(double ticks) {
        frontRightMotor.setTargetPosition(frontRightMotor.getCurrentPosition()); // setting it as it seems to be a bug.  A error is thrown if using RUN_TO_POSITION and setTargetPosition not set
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontRightMotor.setTargetPosition((int)ticks);
        frontRightMotor.setPower(driveMaxPower);  // Set power to 1 as other power setting do not seem to work

        while (frontRightMotor.isBusy()) {
            FtcDashboard.getInstance().getTelemetry().addData("Front Right Motor: ", frontRightMotor.getCurrentPosition());
            FtcDashboard.getInstance().getTelemetry().update();
        }
        resetEncoders();
    }
    public void testRearLeftMotor(double ticks) {
        rearLeftMotor.setTargetPosition(rearLeftMotor.getCurrentPosition()); // setting it as it seems to be a bug.  A error is thrown if using RUN_TO_POSITION and setTargetPosition not set
        rearLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rearLeftMotor.setTargetPosition((int)ticks);
        rearLeftMotor.setPower(driveMaxPower);  // Set power to 1 as other power setting do not seem to work

        while (rearLeftMotor.isBusy()) {
            FtcDashboard.getInstance().getTelemetry().addData("Rear Left Motor: ", rearLeftMotor.getCurrentPosition());
            FtcDashboard.getInstance().getTelemetry().update();
        }
        resetEncoders();

    }
    public void testRearRightMotor(double ticks) {
        rearRightMotor.setTargetPosition(rearRightMotor.getCurrentPosition()); // setting it as it seems to be a bug.  A error is thrown if using RUN_TO_POSITION and setTargetPosition not set
        rearRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rearRightMotor.setTargetPosition((int)ticks);
        rearRightMotor.setPower(driveMaxPower);  // Set power to 1 as other power setting do not seem to work

        while (rearRightMotor.isBusy()) {
            FtcDashboard.getInstance().getTelemetry().addData("Rear Right Motor: ", rearRightMotor.getCurrentPosition());
            FtcDashboard.getInstance().getTelemetry().update();
        }
        resetEncoders();
    }
    **/
}
