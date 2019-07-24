package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimberCommand extends Command {

    protected double climberSpeed;

    public ClimberCommand(double speed) {
        requires(Robot.m_climber);
        this.climberSpeed = speed;
    }

    @Override
    protected void execute() {
        Robot.m_climber.climberSpeed(climberSpeed);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}