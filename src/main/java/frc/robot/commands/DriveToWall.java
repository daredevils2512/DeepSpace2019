package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.lib.DistanceSensor;
import frc.robot.lib.SpeedRamp;
import frc.robot.subsystems.Drivetrain;

public final class DriveToWall extends Command {
    protected Drivetrain drivetrain;
    private double m_distToWall;
    private double tolerance = 4;
    private double defaultSpeed = 1;
    private DistanceSensor m_sensor;

    public DriveToWall(Drivetrain drivetrain, DistanceSensor sensor) {
        requires(drivetrain);
        this.drivetrain = drivetrain;
        m_distToWall = Robot.getTargetDriveToWallDistance();
        m_sensor = sensor;
    }

    @Override
    public void execute() {
        double dist = m_sensor.getDistance();
        drivetrain.arcadeDrive(-SpeedRamp.speedRamp(tolerance, dist, m_distToWall + 12, defaultSpeed), 0);
    }

    @Override
    public boolean isFinished() {
        return m_distToWall < m_sensor.getDistance();
    }

    @Override
    public void interrupted() {
        drivetrain.arcadeDrive(0, 0);
    }
}