package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.HatchIntake;

public class HatchFlip extends Command {

  private DoubleSolenoid.Value m_dir;

  public HatchFlip(DoubleSolenoid.Value dir) {
    requires(Robot.m_HatchIntake);
    this.m_dir = dir;
  }

  @Override
  protected void execute() {
    Robot.m_HatchIntake.flipFlap(m_dir);
  }
  
  @Override
  protected boolean isFinished() {
    return false;
  }
}