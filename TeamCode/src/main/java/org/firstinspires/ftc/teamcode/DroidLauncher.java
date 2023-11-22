package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;

@Config
public class DroidLauncher {

    private DcMotor slowMotor;
    private DcMotor fastMotor;
    public static double slowMotorSpeed = 0.25, fastMotorSpeed = 0.50;

    public void init(HardwareMap hardwareMap) {
        slowMotor = hardwareMap.dcMotor.get("droidLauncherSlowMotor");
        fastMotor = hardwareMap.dcMotor.get("droidLauncherFastMotor");
        slowMotor.setDirection(DcMotor.Direction.FORWARD);
        fastMotor.setDirection(DcMotor.Direction.FORWARD);
        slowMotor.setPower(0);
        fastMotor.setPower(0);
    }

    public void launchDroid() {


        fastMotor.setPower(fastMotorSpeed);
        slowMotor.setPower(slowMotorSpeed);
    }

    public void stopLauncher () {
        slowMotor.setPower(0);
        fastMotor.setPower(0);
    }

}
