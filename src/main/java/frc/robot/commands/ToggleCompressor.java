package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Compressorsorus;

public class ToggleCompressor extends InstantCommand {
    public ToggleCompressor() {
        
    }

    @Override
    protected void initialize() { 
        Compressorsorus.getInstance().toggle();
    }
}