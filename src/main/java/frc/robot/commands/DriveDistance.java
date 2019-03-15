package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public class DriveDistance extends Command { //TODO untested

    private double m_tolerance;
    private double m_dist;
    private double m_targetDistL;
    private double m_rampDist = 24;

    public DriveDistance(double tolerance, double dist) {
        m_tolerance = tolerance;
        m_dist = dist;
        requires(Robot.m_Drivetrain);
    }

    @Override
    protected void initialize() {
        m_targetDistL = Robot.m_Drivetrain.getAverageEncDist() + m_dist;
    }

    @Override
    protected void execute() {
        double currentDist = Robot.m_Drivetrain.getAverageEncDist();
        double diff = m_targetDistL - currentDist;
        
        double direction = Math.signum(diff);
        double driveDist = Math.abs(diff);

        double speed = Math.min(1, driveDist / m_rampDist) * direction;

        Robot.m_Drivetrain.arcadeDrive(speed, 0.0);
    }


    @Override
    protected boolean isFinished() {
        return (Robot.m_Drivetrain.getAverageEncDist() >= m_targetDistL + m_tolerance &&
        Robot.m_Drivetrain.getAverageEncDist() <= m_targetDistL - m_tolerance);
    }

    @Override
    protected void end() {
        Robot.m_Drivetrain.arcadeDrive(0.0, 0.0);
    }
}