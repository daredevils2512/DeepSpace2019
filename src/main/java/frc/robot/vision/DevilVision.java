package frc.robot.vision;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Robot;

public abstract class DevilVision {

    //map the target to the vision table
    private static Map<DevilVisionTargetType, DevilVision> instanceMap = new HashMap<>();

    static {
        //init the network instances
        NetworkTableInstance.getDefault().startClientTeam(Robot.teamNumber);
        
        //register them here
        instanceMap.put(DevilVisionTargetType.LINE, new DevilVisionLine());
    }

    private static DevilVision getDevilVision(DevilVisionTargetType type){
        return instanceMap.get(type);
    }

    public static DevilVision getDevilLineVision(){
        return getDevilVision(DevilVisionTargetType.LINE);
    }

    /**
     * Member Variables
     */

    protected NetworkTable table;

    public enum DevilVisionTargetType {
        HATCH,
        LINE,
        CARGO
    }

    /**
     * 
     * @return String the network table name
     */
    protected abstract String getNetworkTableName();

    protected abstract DevilVisionTargetType getDevilVisionTargetType();

    /**
     * Constructor
     */
    protected DevilVision(){
        this.table = NetworkTableInstance.getDefault().getTable(this.getNetworkTableName());
    }

    public final Target getTarget(){
        Double distance = Utils.getNetworkTableDouble(this.table, "distance");
        Double heading = Utils.getNetworkTableDouble(this.table, "heading");
        return new DevilVisionTarget(getDevilVisionTargetType(), distance, heading);


    }
}