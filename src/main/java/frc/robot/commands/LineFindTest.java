package frc.robot.commands;

import edu.wpi.first.wpilibj.command.*;
import frc.robot.Robot;
// import frc.robot.subsystems.LineFind;

public class LineFindTest extends Command {

    public LineFindTest() {
        requires(Robot.m_LineFind);
    }

    @Override
    protected void execute() {
        System.out.println("LineFindTestActivater");
        Robot.m_LineFind.pointToLine(10.0);
        
    }

    @Override
    protected void end() {
        Robot.m_LineFind.resetVars();
    }

    @Override
    protected boolean isFinished() {
      return true;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
     @Override
    protected void interrupted() {
    }
}