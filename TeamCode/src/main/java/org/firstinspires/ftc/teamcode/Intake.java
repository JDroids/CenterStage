package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;

@Config
public class Intake {

    private DcMotor intakeMotor;
    public static double intakeMaxSpeed = 0.85, depositMaxSpeed = 0.85;

    public void init(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.dcMotor.get("intakeMotor");
        intakeMotor.setPower(0);
    }

    public void intakePixels() {
        intakeMotor.setDirection(DcMotor.Direction.REVERSE);
        intakeMotor.setPower(intakeMaxSpeed);
    }

    public void depositPixels() {
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
        intakeMotor.setPower(depositMaxSpeed);
    }

    public void stopIntake() {
        intakeMotor.setPower(0);
    }

}
