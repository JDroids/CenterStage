package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.processor.CenterStageVisionProcessor;

import org.firstinspires.ftc.vision.VisionPortal;

import java.util.List;

@Autonomous
public class CenterStageVisionOpMode extends OpMode {
    private CenterStageVisionProcessor centerStageVisionProcessor; //processor to identify correct alignment on the image captured
    private VisionPortal visionPortal;  //provides a way to handle the camera and the stream from the camera

    @Override
    public void init (){
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1"); // get reference to to the webcamera hooked to the hub
        centerStageVisionProcessor = new CenterStageVisionProcessor();
        visionPortal = VisionPortal.easyCreateWithDefaults(webcamName, centerStageVisionProcessor);  //create vision portal with all the default and pass the web camera and the AprilTagProcessor
    }
    @Override
    public void init_loop(){

    }
    @Override
    public void start () {
        visionPortal.stopStreaming();
    }
    @Override
    public void loop () {
        telemetry.addData("Identified:", centerStageVisionProcessor.getSelection());
    }
}
