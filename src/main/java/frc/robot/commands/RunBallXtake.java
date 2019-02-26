package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunBallXtake extends Command {

    private double m_speed;

    public RunBallXtake(double speed) {
        requires(Robot.m_ballXtake);
        this.m_speed = speed;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Robot.m_ballXtake.setBallXtakeSpeed(this.m_speed);
    }

    @Override
    protected boolean isFinished() {
        // If intaking, stop when limit switch is tripped
        // else, just keep spinning
        return (this.m_speed < 0) ? Robot.m_ballXtake.getBallOccupancy() : false;
    }

    @Override
    protected void end() {
        Robot.m_ballXtake.setBallXtakeSpeed(0.0);
    }

    @Override
    protected void interrupted() {
        this.end();
    }

}