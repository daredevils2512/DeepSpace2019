package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.lib.DistanceSensor;
import frc.robot.lib.SpeedRamp;
import frc.robot.subsystems.Drivetrain;

public class DriveToWall extends Command {

    private double m_distToWall;
    private double tolerance = 4;
    private double defaultSpeed = 1;
    private DistanceSensor m_sensor;

    public DriveToWall(DistanceSensor sensor) {
        requires(Drivetrain.getInstance());
        m_distToWall = Robot.driveToWallChooser.getSelected();
        m_sensor = sensor;
    }

    @Override
    public void execute() {
        double dist = m_sensor.getDistance();
        Drivetrain.getInstance().arcadeDrive(-SpeedRamp.speedRamp(tolerance, dist, m_distToWall + 12, defaultSpeed), 0);
    }

    @Override
    public boolean isFinished() {
        return m_distToWall < m_sensor.getDistance();
    }

    @Override
    public void interrupted() {
        Drivetrain.getInstance().arcadeDrive(0, 0);
    }
}