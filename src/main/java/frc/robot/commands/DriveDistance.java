package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class DriveDistance extends Command {

    private double m_speed;
    private double m_dist;
    private double targetDistL;

    public DriveDistance(double speed, double dist) {
        m_speed = speed;
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
    }


    @Override
    protected boolean isFinished() {
        return true;
    }
}