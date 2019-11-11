package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.constants.Constants.LiftHeight;

public class CMG_LiftCargo extends CommandGroup {

    public CMG_LiftCargo() {
        System.out.println("lift cargo did");
        addSequential(new FoldCargoIntakeUp());
        addSequential(new RunToHeight(LiftHeight.ROCKET_HATCH_BOTTOM, true)); 
    }
}