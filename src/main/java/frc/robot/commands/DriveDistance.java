package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;
<<<<<<< HEAD
import frc.robot.subsystems.Drivetrain;

public class DriveDistance extends Command { //TODO untested

    private double m_tolerance;
    private double m_dist;
    private double m_targetDistL;
    private double m_rampDist = 24;

    public DriveDistance(double tolerance, double dist) {
        m_tolerance = tolerance;
=======

public class DriveDistance extends Command {

    private double m_speed;
    private double m_dist;
    private double targetDistL;

    public DriveDistance(double speed, double dist) {
        m_speed = speed;
>>>>>>> vision
        m_dist = dist;
        requires(Robot.m_Drivetrain);
    }

    @Override
    protected void initialize() {
<<<<<<< HEAD
        m_targetDistL = Robot.m_Drivetrain.getAverageEncDist() + m_dist;
=======
        targetDistL = Robot.m_Drivetrain.getLeftEncoderDistance() + m_dist;
>>>>>>> vision
    }

    @Override
    protected void execute() {
<<<<<<< HEAD
        double currentDist = Robot.m_Drivetrain.getAverageEncDist();
        double diff = m_targetDistL - currentDist;

        double direction = Math.signum(diff);
        double driveDist = Math.abs(diff);

        double speed = Math.min(1, driveDist / m_rampDist) * direction;

        Robot.m_Drivetrain.arcadeDrive(speed, 0.0);
=======
        if (m_dist >= 0) {
            if (Robot.m_Drivetrain.getLeftEncoderDistance() <= targetDistL) {
                Robot.m_Drivetrain.arcadeDrive(m_speed, 0.0);
            } else {
                Robot.m_Drivetrain.arcadeDrive(0.0, 0.0);
            }
        } else {
            if (Robot.m_Drivetrain.getLeftEncoderDistance() >= targetDistL) {
                Robot.m_Drivetrain.arcadeDrive(-m_speed, 0.0);
            } else {
                Robot.m_Drivetrain.arcadeDrive(0.0, 0.0);
            }
        }
>>>>>>> vision
    }


    @Override
    protected boolean isFinished() {
<<<<<<< HEAD
        double diff = m_targetDistL - Robot.m_Drivetrain.getAverageEncDist();
        return Math.abs(diff) <= m_tolerance;
    }

    @Override
    protected void end() {
        Robot.m_Drivetrain.arcadeDrive(0.0, 0.0);
=======
        return true;
>>>>>>> vision
    }
}