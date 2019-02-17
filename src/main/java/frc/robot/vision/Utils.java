package frc.robot.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
// import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.UsbCamera;

public class Utils {

    static boolean dvOnline;

    /* You don't needt o cast tthis to a kDouble. it will throw an exceptino if it's wrong */
    /** TODO:
     * PUT YOUR SYSTEM OUT IN HERE SO YOU DON"T NEED TO COPY IT
     */

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

    public static boolean startDriverVision(int port, int imageWidth, int imageHeight) {
        UsbCamera driverCam = CameraServer.getInstance().startAutomaticCapture(port);
        driverCam.setResolution(imageWidth, imageHeight);
        dvOnline = driverCam.isConnected();
        System.out.println("driver vision status: " + dvOnline);
        return dvOnline;
    }

}