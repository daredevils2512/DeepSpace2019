package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.constants.*;
import frc.robot.constants.Constants.LiftPosition;

public class RunToPosition extends Command {
    private Constants.LiftPosition position;
    public RunToPosition(Constants.LiftPosition value) {
        requires(Robot.m_lift);
        this.position = value;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        // the values will need to be changed
        if (this.position == LiftPosition.BOTTOM) {
            Robot.m_lift.runToBottom();

        } else if (this.position == LiftPosition.MIDDLE) {
            Robot.m_lift.runToMid();

        } else if (this.position == LiftPosition.TOP) {
            Robot.m_lift.runToTop();

        } else if (this.position == LiftPosition.HATCHBOTTOM) {
            Robot.m_lift.runToHatchBottom();

        } else if (this.position == LiftPosition.HATCHMIDDLE) {
            Robot.m_lift.runToHatchMid();

        } else if (this.position == LiftPosition.HATCHTOP) {
            Robot.m_lift.runToHatchTop();

        }
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