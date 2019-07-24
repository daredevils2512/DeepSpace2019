package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.constants.*;

public class RunToPosition extends Command {
    private Constants.LiftPosition position;
    private double height;
    private boolean finished;
    public RunToPosition(Constants.LiftPosition value) {
        requires(Robot.m_lift);
        this.position = value;
    }

    public RunToPosition(double height) {
        requires(Robot.m_lift);
        this.height = height;
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
        
        double height;
        if (this.position != null) {
            switch (this.position) {
                case FEEDER:
                    height = 12;
                case CARGOBOTTOM:
                    height = 27;
                    break;
                case CARGOMIDDLE:
                    height = 57;
                    break;
                case CARGOTOP:
                    height = 75;
                    break;
                case HATCHBOTTOM:
                    height = 14;
                    break;
                case HATCHMIDDLE:
                    height = 46;
                    break;
                case HATCHTOP:
                    height = 68;
                    break;
                default:
                    height = 0;
                    break;
            }
        } else {
            height = this.height;
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