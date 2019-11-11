package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.subsystems.Drivetrain;

public class DriveDistance extends Command { //TODO untested

    private double m_tolerance;
    private double m_dist;
    private double m_targetDistL;
    private double m_rampDist = 24;

    public DriveDistance(double tolerance, double dist) {
        m_tolerance = tolerance;
        m_dist = dist;
        requires(Drivetrain.getInstance());
    }

    @Override
    protected void initialize() {
        m_targetDistL = Drivetrain.getInstance().getAverageEncDist() + m_dist;
    }

    @Override
    protected void execute() {
        double currentDist = Drivetrain.getInstance().getAverageEncDist();
        double diff = m_targetDistL - currentDist;

        double direction = Math.signum(diff);
        double driveDist = Math.abs(diff);

        double speed = Math.min(1, driveDist / m_rampDist) * direction;

        Drivetrain.getInstance().arcadeDrive(speed, 0.0);
    }


    @Override
    protected boolean isFinished() {
        double diff = m_targetDistL - Drivetrain.getInstance().getAverageEncDist();
        return Math.abs(diff) <= m_tolerance;
    }

    @Override
    protected void end() {
        Drivetrain.getInstance().arcadeDrive(0.0, 0.0);
    }
}