package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.constants.*;
import frc.robot.constants.Constants.LiftPosition;

public class RunToPosition extends Command {
    private Constants.LiftPosition position;
    private boolean finished;
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
                height = 20;
                break;
            case CARGOMIDDLE:
                height = 47;
                break;
            case CARGOTOP:
                height = 75;
                break;
            case HATCHBOTTOM:
                height = 12;
                break;
            case HATCHMIDDLE:
                height = 40;
                break;
            case HATCHTOP:
                height = 68;
                break;
            default:
                height = 0;
                break;
        }
        // System.out.println("Height: " + height);
        Robot.m_lift.runTo(height);

        this.finished = Robot.m_lift.isFinishedRunTo();

    }

    @Override
    protected boolean isFinished() {
        return (this.finished);
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}