package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ResetEncoders extends Command {

    public ResetEncoders() {
        requires(Robot.m_Drivetrain);
    }

    @Override
    protected void execute() {
        Robot.m_Drivetrain.resetEncoders();
    }

    @Override
    protected boolean isFinished() {
      return true;
    }
}