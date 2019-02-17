package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LineAlignX extends Command {

    double m_tolerance;
    Double m_centerX;

    public LineAlignX(double tolerance, Double centerX) {
        m_tolerance = tolerance;
        m_centerX = centerX;
        requires(Robot.m_LineFind);
    }

    @Override
    protected void execute() {
        Robot.m_LineFind.pointToLine(m_tolerance, m_centerX);
    }

    @Override
    protected void end() {
        Robot.m_LineFind.resetVars();
    }

    @Override
    protected boolean isFinished() {
      return true;
    }
}