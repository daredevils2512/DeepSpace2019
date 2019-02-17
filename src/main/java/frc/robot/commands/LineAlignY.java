package frc.robot.commands;

import edu.wpi.first.wpilibj.command.*;
import frc.robot.Robot;
// import frc.robot.subsystems.LineFind;

public class LineAlignY extends Command {

    double m_tolerance;
    Double m_centerY;

    public LineAlignY(double tolerance, Double centerY) {
        m_tolerance = tolerance;
        m_centerY = centerY;
        // requires(Robot.m_LineFind);
        requires(Robot.m_LineFind);
    }

  // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        System.out.println("LineFindTestActivater");
        Robot.m_LineFind.pointToLineY(5.0, m_centerY);
        
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