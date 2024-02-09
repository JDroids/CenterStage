package org.firstinspires.ftc.teamcode;

//import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class BandedDroidLauncher {
    private Servo planeServo;

    public static double planeDownPos = 0.6;
    public static double planeUpPos = 0.0;

    public enum States {
        DOWN,
        UP
    }

    private States state = States.DOWN;

    public void init(HardwareMap hardwareMap) {
        planeServo = hardwareMap.get(Servo.class, "planeServo");
        planeServo.setDirection(Servo.Direction.FORWARD);
        planeServo.setPosition(planeUpPos);
        state = States.UP;

    }
    public void liftPlaneArm () {
        planeServo.setPosition(planeUpPos);
        state = States.UP;
    }
    public void downPlaneArm (){
        planeServo.setPosition(planeDownPos);
        state = States.DOWN;
    }

    public void autoEndArm (){
        planeServo.setPosition(planeUpPos);
        state = States.UP;
    }


}
