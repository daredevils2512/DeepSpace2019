/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public final class CargoIntake extends Subsystem {
    public enum FoldPosition {
        NONE, UP, DOWN
    }

    private static CargoIntake instance;

    private final WPI_TalonSRX infinityMotor;
    private final WPI_TalonSRX inMotor;
    private final DoubleSolenoid foldSolenoid;

    private final Value foldUpValue = Value.kForward;
    private final Value foldDownValue = Value.kReverse;

    private CargoIntake() {
        infinityMotor = new WPI_TalonSRX(RobotMap.cargoInfinityPort);
        inMotor = new WPI_TalonSRX(RobotMap.cargoInMotorPort);
        foldSolenoid = new DoubleSolenoid(RobotMap.cargoUpDownAPort, RobotMap.cargoUpDownBPort);
        foldSolenoid.set(foldUpValue);
    }

    @Override
    public void initDefaultCommand() {
        
    }

    public static CargoIntake getInstance() {
        if(instance == null) {
            instance = new CargoIntake();
        }
        return instance;
    }

    /**
     * Get the current intake position
     * @return UP if the intake is up, DOWN if the intake is down, or NONE if the neither output is activated on the double solenoid responsible for folding
     */
    public FoldPosition getFoldPosition() {
        Value value = foldSolenoid.get();
        FoldPosition result;

        if(value == foldUpValue) {
            result = FoldPosition.UP;
        } else if(value == foldDownValue) {
            result = FoldPosition.DOWN;
        } else {
            result = FoldPosition.NONE;
        }

        return result;
    }

    /**
     * Fold the hatch intake down out of the frame perimeter
     */
    public void foldDown() {
        foldSolenoid.set(foldDownValue);
    }

    /**
     * Fold the hatch intake up into the frame perimeter
     */
    public void foldUp() {
        foldSolenoid.set(foldUpValue);
    }

    /**
     * Run cargo intake
     * @param infinitySpeed speed to turn the bands that center cargo (negative values move towards center, positive values move away from center)
     * @param inSpeed speed to run the bands that intake/extake cargo (negative values intake, positive values extake)
     */
    public void setSpeed(double infinitySpeed, double inSpeed) {
        if(foldSolenoid.get() == foldDownValue) {
            this.infinityMotor.set(infinitySpeed);
            this.inMotor.set(inSpeed);
        }
    }

}