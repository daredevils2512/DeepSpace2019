package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TurnToAngle extends Command {

    private float m_targetAngle;
    private double m_tolerance;

    public TurnToAngle(float targetAngle, double tolerance) {
        m_targetAngle = targetAngle;
        m_tolerance = tolerance;
        requires(Robot.m_LineFind);
    }

    @Override
    protected void execute() {
        Robot.m_Drivetrain.alignByGyro(m_targetAngle, m_tolerance);
    }

    @Override
    protected boolean isFinished() {
      return true;
    }
}