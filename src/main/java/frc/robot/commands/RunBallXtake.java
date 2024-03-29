package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.BallXtake;

public class RunBallXtake extends Command {

    private double m_speed;
    private boolean m_ovveride;

    public RunBallXtake(double speed, boolean override) {
        super(2);
        requires(Robot.m_ballXtake);
        this.m_speed = speed;
        this.m_ovveride = override;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Robot.m_ballXtake.setBallXtakeSpeed(this.m_speed);
    }

    @Override
    protected boolean isFinished() {
        // If intaking, stop when limit switch is tripped
        // else, just keep spinning 
        boolean result = true;
        if (!this.m_ovveride && this.m_speed < 0) {
            result = BallXtake.getBallOccupancy();
        }else if (!this.m_ovveride && this.m_speed > 0){
            result = false;
        }else{
            result = false;
        }

        if (result && this.m_speed > 0) {
            CMG_IntakeBall.ballOut();
        }
        return result;
    }

    @Override
    protected void end() {
        Robot.m_ballXtake.setBallXtakeSpeed(0.0);
    }

    @Override
    protected void interrupted() {
        this.end();
    }

}