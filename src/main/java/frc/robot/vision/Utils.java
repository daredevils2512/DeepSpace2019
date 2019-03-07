package frc.robot.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
// import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;
import frc.robot.Robot;
import frc.robot.subsystems.LineFind;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.networktables.*;

public class Utils {

    public Utils() {
    }

    public static NetworkTableInstance convexHullsFinal = NetworkTableInstance.getDefault();
    public static NetworkTable convexHullsTable = null;
    public static NetworkTable ballTable = null;
    public static NetworkTable hatchTable = null;

    public static Double getNetworkTableDouble(NetworkTable networkTable, String entryName){
        Double result = null;
        NetworkTableValue networkTableValue;
        
        if ((networkTableValue = getNetworkTableValue(networkTable, entryName)) != null) {
            try{
                result = networkTableValue.getDouble();
                // System.out.println("Retrieved: "+result+" for entry: "+entryName);

            }catch(ClassCastException cce){
                System.out.println("Cannot get "+entryName+" as a double as it is a "+networkTableValue.getType());
            }
        }
        
        return result;
    }

    private static NetworkTableValue getNetworkTableValue(NetworkTable networkTable, String entryName){
        NetworkTableValue result = null;
        NetworkTableEntry entry = null;
        if((entry = networkTable.getEntry(entryName)) != null){
            result = entry.getValue();
        }
        return result;
    }

    public static void dumpNetworkTable(NetworkTable table){
        for(String key : table.getKeys()){
            System.out.println("Table contains key: "+key);
        }
    }

    public static boolean startDriverVision(int port, int imageWidth, int imageHeight, Boolean dvOnline) {
        if (!dvOnline) {
            System.out.println("connencting Driver vision");
            UsbCamera driverCam = CameraServer.getInstance().startAutomaticCapture(port);
            driverCam.setResolution(imageWidth, imageHeight);
            dvOnline = driverCam.isConnected();
        }
        System.out.println("driver vision status: " + dvOnline);
        return dvOnline;
    }

    public static void updateNTData() {
        Robot.cameraSize = getCameraSize();
        if (Robot.cameraSize >= 1.0) {
          LineFind.centerXBall = getCenterXBall();
          LineFind.widthBall = getWidthBall();
          LineFind.bottomBall = getBottomBall();
          LineFind.heightBall =  getHeightBall();
          LineFind.topBall = getTopBall();
          LineFind.widthPosBall = getWidthPosBall();
          LineFind.centerYBall = getCenterYBall();
          LineFind.areaBall = getAreaBall();
          if (Robot.cameraSize >= 2.0) {
            LineFind.centerXHatch = getCenterXHatch();
            LineFind.widthHatch = getWidthHatch();
            LineFind.bottomHatch = getBottomHatch();
            LineFind.heightHatch =  getHeightHatch();
            LineFind.topHatch = getTopHatch();
            LineFind.widthPosHatch = getWidthPosHatch();
            LineFind.centerYHatch = getCenterYHatch();
            LineFind.areaHatch = getAreaHatch();
          }
        }
      }

    public static void getConnInfo() {
        for (ConnectionInfo conninfo : convexHullsFinal.getConnections()){
            System.out.println("Remoteid: "+conninfo.remote_id+", Remote IP: "+conninfo.remote_ip+", Port: "+conninfo.remote_port+", LastUpddate: "+conninfo.last_update);
        }
    }

    public static Double getCameraSize() {
        return getNetworkTableDouble(convexHullsTable, "Camera Size");
    }
    
    public static Double getCenterXBall() {
        return getNetworkTableDouble(ballTable, "centerXBall");
    } 
    
    public static Double getWidthBall() {
        return getNetworkTableDouble(ballTable, "widthBall");
    }
    
    public static Double getHeightBall() {
        return getNetworkTableDouble(ballTable, "heightBall");
    }

    public static Double getBottomBall() {
        return getNetworkTableDouble(ballTable, "bottomBall");
    }
    
    public static Double getTopBall() {
        return getNetworkTableDouble(ballTable, "topBall");
    }

    public static Double getWidthPosBall() {
        return getNetworkTableDouble(ballTable, "widthPosBall");
    }

    public static Double getCenterYBall() {
        return getNetworkTableDouble(ballTable, "centerYBall");
    }
    
    public static Double getAreaBall() {
        return getNetworkTableDouble(ballTable, "areaBall");
    }
    
    public static Double getCenterXHatch() {
        return getNetworkTableDouble(hatchTable, "centerXHatch");
    }
    
    public static Double getWidthHatch() {
        return getNetworkTableDouble(hatchTable, "widthHatch");
    }
    
    public static Double getHeightHatch() {
        return getNetworkTableDouble(hatchTable, "heightHatch");
    }
    
    public static Double getBottomHatch() {
        return getNetworkTableDouble(hatchTable, "bottomHatch");
    }
    
    public static Double getTopHatch() {
        return getNetworkTableDouble(hatchTable, "topHatch");
    }
    
    public static Double getWidthPosHatch() {
        return getNetworkTableDouble(hatchTable, "widthHatch");
    }
     
    public static Double getCenterYHatch() {
        return getNetworkTableDouble(hatchTable, "centerYHatch");
    }
    
    public static Double getAreaHatch() {
        return getNetworkTableDouble(hatchTable, "areaHatch");
    }
    

}