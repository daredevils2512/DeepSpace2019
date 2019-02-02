package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LineAlign extends Command {

    public LineAlign() {
        requires(Robot.m_LineFind);
    }

    @Override
    protected void execute() {
        Robot.m_LineFind.center(10, 10);
    }

    @Override
    protected boolean isFinished() {
      return true;
    }
}