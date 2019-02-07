package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.kauailabs.navx.frc.AHRS;

public class NavX extends Subsystem {

    public static AHRS navX;

    public NavX() {
        navX = new AHRS(SPI.Port.kMXP);
    }

    //The white lines will be at 0, 60, 90, 120, and 180, positive or negative degrees

    public float getYaw() {
        return navX.getYaw();
    }



    @Override
    public void initDefaultCommand() {
      // Set the default command for a subsystem here.
      // setDefaultCommand(new MySpecialCommand());
    }
}