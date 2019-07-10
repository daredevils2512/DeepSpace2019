package frc.robot.lib;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.constants.Constants;

public class Limelight {
    private NetworkTable table;
    private NetworkTableEntry tv;
    private NetworkTableEntry tx;
    private NetworkTableEntry ty;

    public Limelight() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
        tv = table.getEntry("tv");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
    }

    public double getV() {
        return tv.getDouble(0.0);
    }

    public double getX() {
        return tx.getDouble(0.0);
    }

    public double getY() {
        return ty.getDouble(0.0);
    }

    // Angles are in degrees
    public static double getDistance(double heightDifference, double angleToTarget) {
        double sumAngle = angleToTarget;
        double sumAngleRadians = sumAngle * Math.PI / 180.0;
        double distance = heightDifference / Math.tan(sumAngleRadians);
        return distance;
    }
}