package frc.robot.vision;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;

public class Utils {


    public static Double getNetworkTableDouble(NetworkTableInstance networkTableInstance, String entryName){
        Double result = null;
        NetworkTableValue networkTableValue = null;
        if ((networkTableValue = getNetworkTableValue(networkTableInstance, entryName)) != null){
            result = networkTableValue.getDouble();
        }
    
        return result;
    }

    private static NetworkTableValue getNetworkTableValue(NetworkTableInstance networkTableInstance, String entryName){
        NetworkTableValue result = null;
        NetworkTableEntry entry = null;
        if(networkTableInstance.isConnected() && ((entry = networkTableInstance.getEntry(entryName)) != null)){
            result = entry.getValue();
        }
        return result;
    }

}