package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class RobotHanger {
    private Servo stopArm;

    public static double armDownPos = 1.00;
    public static double armUpPos = 0.38;
    public static double autoEndArmPos = 0.38;

    public enum States {
        DOWN,
        UP
    }

    private States state = States.DOWN;

    public void init(HardwareMap hardwareMap) {
        stopArm = hardwareMap.get(Servo.class, "hangerArmServo");
        stopArm.setDirection(Servo.Direction.FORWARD);
        stopArm.setPosition(armDownPos);
        state = States.DOWN;

    }
    public void liftArm () {
        stopArm.setPosition(armUpPos);
        state = States.UP;
    }
    public void downArm (){
        stopArm.setPosition(armDownPos);
        state = States.DOWN;
    }

    public void autoEndArm (){
        stopArm.setPosition(autoEndArmPos);
        state = States.DOWN;
    }

    public double getPosition(){
        return stopArm.getPosition();
    }
    public void togglePostion(){
        switch (state) {
            case UP:
                stopArm.setPosition(armDownPos);
                state = States.DOWN;
                break;
            case DOWN:
                stopArm.setPosition(armUpPos);
                state = States.UP;
                break;

        }

        //FtcDashboard.getInstance().getTelemetry().addData("State", state);
        //FtcDashboard.getInstance().getTelemetry().addData("Claw Position", claw.getPosition());
        //FtcDashboard.getInstance().getTelemetry().update();
    }

}
