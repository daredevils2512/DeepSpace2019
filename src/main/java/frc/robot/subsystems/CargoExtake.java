package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.RobotMap;

public final class CargoExtake extends Subsystem {
    private final WPI_TalonSRX cargoExtake1;
    private final WPI_TalonSRX cargoExtake2;
    private final SpeedControllerGroup cargoExtake;
    
    private static DigitalInput ballOccupancy;

    public CargoExtake() {
        cargoExtake1 = new WPI_TalonSRX(RobotMap.cargoExtake1ID);
        cargoExtake2 = new WPI_TalonSRX(RobotMap.cargoExtake2ID);
        cargoExtake2.setInverted(true);
        
        cargoExtake = new SpeedControllerGroup(cargoExtake1, cargoExtake2);

        ballOccupancy = new DigitalInput(RobotMap.ballOccupancy);
    }

    @Override
    public void initDefaultCommand() {

    }

    /**
     * Run the cargo extake
     * @param speed the extake speed (negative values intake, positive values extake)
     */
    public void setSpeed(double speed) {
        cargoExtake.set(speed);
    }

    public static boolean getBallOccupancy() {
        return ballOccupancy.get();
    }

    public static DigitalInput getBallOccupancySwitch() {
        return ballOccupancy;
    }

    public void updateDashboard() {
        SmartDashboard.putBoolean("Cargo Limit Switch", getBallOccupancy());
    }
}