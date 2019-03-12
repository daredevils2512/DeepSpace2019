package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.lib.DistanceSensor;
import frc.robot.lib.SpeedRamp;

public class DriveToWall extends Command {

    private double m_distToWall;
    private double tolerance = 4;
    private double defaultSpeed = 1;
    private DistanceSensor m_sensor;

    public DriveToWall(DistanceSensor sensor) {
        requires(Robot.m_Drivetrain);
        m_distToWall = Robot.driveToWallChooser.getSelected();
        m_sensor = sensor;
    }

    @Override
    public void execute() {
        double dist = m_sensor.getDistance();
        Robot.m_Drivetrain.arcadeDrive(SpeedRamp.speedRamp(tolerance, dist, m_distToWall, defaultSpeed), 0);
    }

    @Override
    public boolean isFinished() {
        return m_distToWall > m_sensor.getDistance();
    }

    @Override
    public void interrupted() {
        Robot.m_Drivetrain.arcadeDrive(0, 0);
    }
}