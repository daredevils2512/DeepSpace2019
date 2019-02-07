package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.constants.*;

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
        if( && !(Robot.m_lift.getLiftHeight() > 1)) {
            Robot.m_lift.setSpeed(1.0);
        } else if ( && !(Robot.m_lift.getLiftHeight() > 2)) {
            Robot.m_lift.setSpeed(1.0);
        }// } else if (Robot.m_oi.position == 3 && )
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