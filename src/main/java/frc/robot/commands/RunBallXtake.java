package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunBallXtake extends Command {

    public RunBallXtake() {
        requires(Robot.m_ballXtake);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Robot.m_ballXtake.setBallXtakeSpeed(1);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }

}