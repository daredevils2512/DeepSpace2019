package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.cameraserver.CameraServer;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

// import org.opencv.imgproc.Imgproc;
// import edu.wpi.cscore.CvSink;
// import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
// import java.util.ArrayList;
import org.opencv.core.*;
import frc.robot.GripWhiteLine;
// import frc.robot.*;
// import edu.wpi.first.vision.VisionRunner;
import edu.wpi.first.vision.VisionThread;

public class Vision extends Subsystem {

    private UsbCamera camera;
    private final Object imgLock = new Object();
    private double centerX = 0.0;

    public void init(int width , int height) {

        camera = CameraServer.getInstance().startAutomaticCapture(0);
        camera.setResolution(width, height);
        System.out.println("finished camera init");
        
    }

    public void view(Mat input) {
        /*
        CvSink gripInput = CameraServer.getInstance().getVideo();
        System.out.println("video sunk");
        while(!Thread.interrupted()) {
            if (gripInput.grabFrame(Robot.source) == 0) {
                System.out.println(gripInput.getError());

            }
        }
        */

        // GripWhiteLine pipeline = new GripWhiteLine();

        // pipeline.process(input);

        // Robot.convexHullsOutput = pipeline.convexHullsOutput();
        VisionThread visionThread;
        
        visionThread = new VisionThread(camera, new GripWhiteLine(), pipeline -> {
            if (!pipeline.convexHullsOutput().isEmpty()) {
                // Imgproc Imgproc = new Imgproc();
                Rect r = Imgproc.boundingRect(pipeline.convexHullsOutput().get(0));
                synchronized (imgLock) {
                    centerX = r.x + (r.width / 2);
                    System.out.println("centerX updated " + centerX);
                }
            } else { 
                System.out.println("FilterContoursOutput is empty");
            }
        });
        visionThread.start();  
        System.out.println("vision Thread Started");

    }

    public synchronized double getCenterX(){
        return centerX;
    }

    @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}