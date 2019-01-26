package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Compressoryeet extends Command {

    public Boolean enabled = Robot.m_Compressorsorus.sorus.enabled();

    public Compressoryeet() {

        requires(Robot.m_Compressorsorus);

    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        if (enabled == false) {
            Robot.m_Compressorsorus.sorus.setClosedLoopControl(true);
        } else {
            Robot.m_Compressorsorus.sorus.setClosedLoopControl(false);
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