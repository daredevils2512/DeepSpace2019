package frc.robot.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
// import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;

public class Utils {


    /* You don't needt o cast tthis to a kDouble. it will throw an exceptino if it's wrong */
    /** TODOz: s;alfkjg
     * PUT YOUR SYSTEM OUT IN HERE SO YOU DON"T NEED TO COPY IT
     */

    public static Double getNetworkTableDouble(NetworkTable networkTable, String entryName){
        Double result = null;
        NetworkTableValue networkTableValue;
        
        if ((networkTableValue = getNetworkTableValue(networkTable, entryName)) != null) {
            try{
                result = networkTableValue.getDouble();
                System.out.println("Retrieved: "+result+" for entry: "+entryName);

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

}