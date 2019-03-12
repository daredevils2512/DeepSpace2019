package frc.robot.commands;

import java.util.Date;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Lift;
import frc.robot.lib.SpeedRamp;

public class DriveToWall extends Command {

    private double m_distToWall;

    public DriveToWall(double distToWall) {
        requires(Robot.m_Drivetrain);
        m_distToWall = distToWall;
    }

    @Override
    public void execute() {
        double distDifference = Robot.m_ballDistanceSensor.getDistance();
        Robot.m_Drivetrain.arcadeDrive(SpeedRamp.speedRamp(4, distDifference, m_distToWall, 1), 0);
    }

    @Override
    public boolean isFinished() {
        return m_distToWall > Robot.m_ballDistanceSensor.getDistance();
    }

    @Override
    public void interrupted() {
        Robot.m_Drivetrain.arcadeDrive(0, 0);
    }
}