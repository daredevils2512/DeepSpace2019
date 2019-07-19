package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ExtakeCargo extends CommandGroup {
    public ExtakeCargo() {
        addParallel(new CargoRunIntake(1.0, 1.0, true));
        addParallel(new RunBallXtake(1.0, true));
    }

    public boolean isFinished() {
        return false;
    }
}