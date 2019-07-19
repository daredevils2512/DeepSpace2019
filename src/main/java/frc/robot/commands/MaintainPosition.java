package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MaintainPosition extends Command {
    protected double height;
    public MaintainPosition(double height) {
        requires(Robot.m_lift);
        this.height = height;
    }

    @Override
    protected void execute() {
        Robot.m_lift.runTo(height);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}