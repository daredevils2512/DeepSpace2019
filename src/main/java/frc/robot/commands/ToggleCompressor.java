package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Compressorsorus;

public final class ToggleCompressor extends InstantCommand {
    protected final Compressorsorus compressor;

    public ToggleCompressor(Compressorsorus compressor) {
        requires(compressor);
        this.compressor = compressor;
    }

    @Override
    protected void initialize() { 
        compressor.toggle();
    }
}