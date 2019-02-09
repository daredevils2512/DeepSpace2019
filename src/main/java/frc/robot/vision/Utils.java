package frc.robot.vision;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableType;
import edu.wpi.first.networktables.NetworkTableValue;

public class Utils {


    public static Double getNetworkTableDouble(NetworkTableInstance networkTableInstance, String entryName){
        Double result = null;
        NetworkTableValue networkTableValue;
        
        if ((networkTableValue = getNetworkTableValue(networkTableInstance, entryName)) != null) {
            if (networkTableValue.getType() == NetworkTableType.kDouble) {
                result = networkTableValue.getDouble();
            } else {
                System.out.println( entryName + " not a kDouble");
                result = 999.00;
            }
        } else {
            System.out.println("networkTableValue or the double were null");
            result = 999.00;
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