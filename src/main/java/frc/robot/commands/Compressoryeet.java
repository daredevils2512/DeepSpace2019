package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Compressoryeet extends Command {

    public Compressoryeet() {

        requires(Robot.m_Compressorsorus);

    }

    @Override
    protected void initialize() { 

    }

    @Override
    protected void execute() {
        Robot.m_Compressorsorus.toggleCompressor();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
        
    }

    @Override
    protected void interrupted() {
    }
}