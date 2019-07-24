package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Climber extends Subsystem {

    private WPI_TalonSRX climberTalon1;
    private WPI_TalonSRX climberTalon2;

    public Climber() {
        climberTalon1 = new WPI_TalonSRX(RobotMap.climberTalon1ID);
        climberTalon2 = new WPI_TalonSRX(RobotMap.climberTalon2ID);
    }

    @Override
    protected void initDefaultCommand() {
        
    }

    public void climberSpeed(double speed) {
        climberTalon1.set(speed);
        climberTalon2.set(speed);
    }
}