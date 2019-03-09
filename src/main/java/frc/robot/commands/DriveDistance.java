package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public class DriveDistance extends Command { //TODO untested

    private double m_speed;
    private double m_tolerance;
    private double m_dist;
    private double targetDistL;

    public DriveDistance(double tolerance, double dist) {
        m_tolerance = tolerance;
        m_dist = dist;
        requires(Robot.m_Drivetrain);
    }

    @Override
    protected void initialize() {
        targetDistL = Robot.m_Drivetrain.getLeftEncoderDistance() + m_dist;
    }

    @Override
    protected void execute() {
        if (m_dist >= 0) {
            if (Robot.m_Drivetrain.getLeftEncoderDistance() <= targetDistL - m_tolerance) {

                m_speed = Math.min(1, m_dist / 24);

                Robot.m_Drivetrain.arcadeDrive(m_speed, 0.0);
            } else {
                Robot.m_Drivetrain.arcadeDrive(0.0, 0.0);
            }
        } else {
            if (Robot.m_Drivetrain.getLeftEncoderDistance() >= targetDistL + m_tolerance) {

                m_speed = Math.max(-1, m_dist / 24);

                Robot.m_Drivetrain.arcadeDrive(m_speed, 0.0);
            } else {
                Robot.m_Drivetrain.arcadeDrive(0.0, 0.0);
            }
        }
    }


    @Override
    protected boolean isFinished() {
        return (Robot.m_Drivetrain.getLeftEncoderDistance() >= targetDistL + m_tolerance &&
        Robot.m_Drivetrain.getLeftEncoderDistance() <= targetDistL - m_tolerance);
    }

    @Override
    protected void end() {
        Robot.m_Drivetrain.arcadeDrive(0.0, 0.0);
    }
}