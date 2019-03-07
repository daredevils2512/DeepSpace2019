package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class FullAlignment extends CommandGroup {

    public FullAlignment(double tolerance, int angleTolerance, Double centerX, Double centerY, double dist, double speed, float targetAngle) {

        addSequential(new LineAlignX(tolerance, centerX));
        addSequential(new LineAlignY(tolerance, centerY));
        addSequential(new DriveDistance(speed, dist));
        addSequential(new TurnToAngle(targetAngle, angleTolerance));
    }

}