package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.constants.Constants;

public class AlignRocketCargo extends Command {
    private boolean finished;

    public AlignRocketCargo() {
        requires(Robot.m_Drivetrain);
        requires(Robot.m_lift);
    }

    @Override
    protected void execute() {
        Robot.m_lift.runTo(Math.max(Constants.VisionTape.Height.ROCKETCARGO + Constants.Limelight.HEIGHTOFFSET - Constants.Lift.HEIGHTOFFSET, 0.0));
        if(!Robot.m_lift.isFinishedRunTo()) {
            return;
        }

        

        finished = true;
    }

    @Override
    protected boolean isFinished() {
        return this.finished;
    }
}