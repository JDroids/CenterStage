package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Claw {
    private Servo claw;

    public static double clawClosedPos = 0.4;
    public static double clawOpenPos = 0.60;


    public enum States {
        CLOSED,
        OPEN
    }

    private States state = States.OPEN;

    public void init(HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "clawServo");
        claw.setDirection(Servo.Direction.REVERSE);
        claw.setPosition(clawClosedPos);
        state = States.CLOSED;

    }
    public void openClaw () {
        claw.setPosition(clawOpenPos);
        state = States.OPEN;
    }
    public void closeClaw (){
        claw.setPosition(clawClosedPos);
        state = States.CLOSED;
    }

    public double getPosition(){
        return claw.getPosition();
    }
    public void toggleClaw(){
        switch (state) {
            case OPEN:
                claw.setPosition(clawClosedPos);
                state = States.CLOSED;
                break;
            case CLOSED:
                claw.setPosition(clawOpenPos);
                state = States.OPEN;
                break;

        }

        //FtcDashboard.getInstance().getTelemetry().addData("State", state);
        //FtcDashboard.getInstance().getTelemetry().addData("Claw Position", claw.getPosition());
        //FtcDashboard.getInstance().getTelemetry().update();
    }

}
