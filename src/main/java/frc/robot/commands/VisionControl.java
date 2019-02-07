package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class VisionControl extends Command {

    public VisionControl() {

        requires(Robot.m_vision);
        
    }

     // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        
    }

    @Override
    protected void execute() {
        
        
    }
  

    // Make this return true when this Command no longer needs to run execute()
      @Override
    protected boolean isFinished() {
     return true;
    }

}