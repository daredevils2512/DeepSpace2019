package frc.robot.commands;

import java.util.Date;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveToWall extends Command {

    private double m_distance;

    public DriveToWall(double distance) {
        requires(Robot.m_Drivetrain);
        m_distance = distance;
    }

    @Override
    public void execute() {
        if (m_distance <= Robot.m_ballDistanceSensor.getDistance()) {
            Robot.m_Drivetrain.arcadeDrive(-1, 0.00);
        } else {
            Robot.m_Drivetrain.arcadeDrive(1, 0);
            Robot.m_Drivetrain.arcadeDrive(1, 0);
            Robot.m_Drivetrain.arcadeDrive(1, 0);
            Robot.m_Drivetrain.arcadeDrive(0, 0);
            System.out.println("STOPPING DRIVES @ "+new Date().toString());
        }
    }

    @Override
    public boolean isFinished() {
        return m_distance > Robot.m_ballDistanceSensor.getDistance();
    }

    @Override
    public void interrupted() {
        Robot.m_Drivetrain.arcadeDrive(0, 0);
    }
}