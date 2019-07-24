package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.constants.Constants;
import frc.robot.subsystems.Lift;

public class AlignRocketCargo extends CommandGroup {
    private boolean finished;

    public AlignRocketCargo() {
        addSequential(new RunToPosition(Lift.fromActualHeight(Constants.VisionTape.Height.ROCKETCARGO + Constants.Limelight.HEIGHTOFFSET)));
        addParallel(new MaintainPosition(Lift.fromActualHeight(Constants.VisionTape.Height.ROCKETCARGO + Constants.Limelight.HEIGHTOFFSET)));
        
    }

    @Override
    protected void execute() {
        

        finished = true;
    }
}