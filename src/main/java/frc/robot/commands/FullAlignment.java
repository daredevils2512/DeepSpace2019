package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.robot.constants.Constants.DistanceSensorSide;

public class FullAlignment extends CommandGroup {

    public FullAlignment(double tolerance, int angleTolerance, Double centerX, Double centerY,
     double dist, double speed, float targetAngle, double wallDist, DistanceSensorSide side) {

        addSequential(new LineAlignX(tolerance, centerX));
        addSequential(new LineAlignY(tolerance, centerY));
        addSequential(new DriveDistance(speed, dist));
        addSequential(new TurnToAngle(targetAngle, angleTolerance));
        addSequential(new DriveToWall(wallDist, side));
    }

}