package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.constants.*;
import frc.robot.constants.Constants.Lift;
import frc.robot.lib.SpeedRamp;

public class RunToPosition extends Command {
    private Constants.LiftPosition position;
    private final double speedRampTolerance = 1, speedRampStartDist = 10;

    public RunToPosition(Constants.LiftPosition position) {
        requires(Robot.m_lift);
        this.position = position;
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

        // System.out.println("Height: " + height);
        // Robot.m_lift.runTo(position.getPosition());

        double distance = position.getPosition() - Robot.m_lift.getLiftHeight();
        double speed = SpeedRamp.speedRamp(speedRampTolerance, distance, speedRampStartDist, 1.0);
        Robot.m_lift.setSpeed(speed);

        // this.finished = Math.abs(height - Robot.m_lift.getLiftHeight()) < 1;
    }

    @Override
    protected boolean isFinished() {
        double tolerance = 1; // Move to constants
        return Math.abs(position.getPosition() - Robot.m_lift.getLiftHeight()) < tolerance;
    }

    @Override
    protected void end() {
        Robot.m_lift.setSpeed(Lift.BACKDRIVE);
    }

    @Override
    protected void interrupted() {
    }
}