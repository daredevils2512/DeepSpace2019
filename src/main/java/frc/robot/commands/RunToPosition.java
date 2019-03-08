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
        int height = 0;
        switch (this.position) {
            case CARGOBOTTOM:
                height = 27;
                break;
            case CARGOMIDDLE:
                height = 55;
                break;
            case CARGOTOP:
                height = 83;
                break;
            case HATCHBOTTOM:
                height = 19;
                break;
            case HATCHMIDDLE:
                height = 47;
                break;
            case HATCHTOP:
                height = 75;
                break;
            default:
                height = 0;
                break;
        }
        Robot.m_lift.runTo(height);

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