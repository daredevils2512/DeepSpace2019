package frc.robot.vision;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;

public class Utils {


    /* You don't needt o cast tthis to a kDouble. it will throw an exceptino if it's wrong */
    /** TODO:
     * PUT YOUR SYSTEM OUT IN HERE SO YOU DON"T NEED TO COPY IT
     */

    public static Double getNetworkTableDouble(NetworkTableInstance networkTableInstance, String entryName){
        Double result = null;
        NetworkTableValue networkTableValue;
        
        if ((networkTableValue = getNetworkTableValue(networkTableInstance, entryName)) != null) {
            result = networkTableValue.getDouble();
            System.out.println("Retrieved: "+result+" for entry: "+entryName);
        } 
        return result;
    }

    private static NetworkTableValue getNetworkTableValue(NetworkTableInstance networkTableInstance, String entryName){
        NetworkTableValue result = null;
        NetworkTableEntry entry = null;
        if(networkTableInstance.isConnected() && networkTableInstance.isValid() && ((entry = networkTableInstance.getEntry(entryName)) != null)){
            result = entry.getValue();
        }
        return result;
    }

}