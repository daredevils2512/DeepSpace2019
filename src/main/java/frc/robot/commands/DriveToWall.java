package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveToWall extends Command {

    private double m_distance;

    public DriveToWall() {
        requires(Robot.m_Drivetrain);
    }

    @Override
    protected void initialize() {
        m_distance = Robot.driveToWallChooser.getSelected();
    }

    @Override
    public void execute() {
        if (m_distance > Robot.m_hatchDistanceSensor.getDistance()) {
            Robot.m_Drivetrain.setSpeed(1, 1);
        } else {
            Robot.m_Drivetrain.setSpeed(0, 0);
        }
    }

    public boolean isFinished() {
        return true;
    }
}