package frc.robot.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public abstract class DevilVision implements Target {

    protected NetworkTable table;

    protected DevilVision(){
        this.table = NetworkTableInstance.getDefault().getTable(this.getNetworkTableName());
    }

    protected abstract String getNetworkTableName();



}