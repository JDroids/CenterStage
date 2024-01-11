package org.firstinspires.ftc.teamcode;

//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

//@Config
public class Wrist {
    private Servo wrist;

    public static double wristDownPos = 0.49;
    public static double wristUpPos = 0.2;
    public static double autoEndWristPos = 0.1;
/*
    public enum States {
        DOWN,
        UP
    }

    private States state = States.UP;
*/
    public void init(HardwareMap hardwareMap) {
        wrist = hardwareMap.get(Servo.class, "wristServo");
        wrist.setPosition(0.72);
        //state = States.UP;

    }
    public void liftWrist () {
        //wrist.setPosition(wristUpPos);
        wrist.setPosition(0.72);
        //state = States.UP;
    }
    public void downWrist (){
        //wrist.setPosition(wristDownPos);
        //wrist.setDirection(Servo.Direction.FORWARD);
        wrist.setPosition(0.15);
        //state = States.DOWN;
    }

    public void autoEndWrist (){
        wrist.setPosition(0.72);
        //state = States.UP;
    }

    public double getPosition(){
        return wrist.getPosition();
    }
    /*
    public void toggleWrist(){
        switch (state) {
            case UP:
                wrist.setPosition(wristDownPos);
                state = States.DOWN;
                break;
            case DOWN:
                wrist.setPosition(wristUpPos);
                state = States.UP;
                break;

        }

        //FtcDashboard.getInstance().getTelemetry().addData("State", state);
        //FtcDashboard.getInstance().getTelemetry().addData("Claw Position", claw.getPosition());
        //FtcDashboard.getInstance().getTelemetry().update();
    }
*/
}
