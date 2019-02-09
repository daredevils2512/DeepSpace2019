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
            try{
                result = networkTableValue.getDouble();
                System.out.println("Retrieved: "+result+" for entry: "+entryName);

            }catch(ClassCastException cce){
                System.out.println("Cannot get "+entryName+" as a double it is a "+networkTableValue.getType());
            }
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

    public static void resetTables(NetworkTableInstance inst, String server) {
        System.out.println("networkTable " + inst + " reset to " + server);
        inst.startClient(server);
        System.out.println("networkTable connected: " + inst.isConnected());
    }

}