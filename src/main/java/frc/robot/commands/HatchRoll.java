package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.HatchIntake;
import frc.robot.OI;

public class HatchRoll extends Command {

    public HatchRoll() {
        requires(Robot.m_HatchIntake);
    }

   @Override
    protected void execute() {
        HatchIntake.runIntake(Robot.m_oi.getRightTrigger());
    } 

    @Override
    protected boolean isFinished() {
      return false;
    }
}