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
        /* heights on rocket:

        front :
        2 3
        4 7
        6 11

        sides :
        1 7
        3 11
        6 3

        */
        if (this.position == LiftPosition.CARGOBOTTOM) {
            Robot.m_lift.runTo(0);

        } else if (this.position == LiftPosition.CARGOMIDDLE) {
            Robot.m_lift.runTo(1);

        } else if (this.position == LiftPosition.CARGOTOP) {
            Robot.m_lift.runTo(2);

        } else if (this.position == LiftPosition.HATCHBOTTOM) {
            Robot.m_lift.runTo(0.5);

        } else if (this.position == LiftPosition.HATCHMIDDLE) {
            Robot.m_lift.runTo(1.5);

        } else if (this.position == LiftPosition.HATCHTOP) {
            Robot.m_lift.runTo(2.5);

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