/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * The subsystem responsible for grabbing hatch panels from  feeder stations and placing them on the cargo ship or rockets
 */
public final class HatchIntake extends Subsystem {
    public enum FoldPosition {
        NONE, UP, DOWN
    }

    public enum LatchPosition {
        NONE, CLOSED, OPEN
    }

    private static HatchIntake instance;

    private final DoubleSolenoid foldSolenoid;
    private final DoubleSolenoid latchSolenoid;
    private Value foldUpValue = Value.kReverse;
    private Value foldDownValue = Value.kForward;
    private Value latchClosedValue = Value.kForward;
    private Value latchOpenValue = Value.kReverse;

    private HatchIntake() {
        foldSolenoid = new DoubleSolenoid(RobotMap.flowerSlideForwardChannel, RobotMap.flowerSlideReverseChannel);
        latchSolenoid = new DoubleSolenoid(RobotMap.flowerSolenoidForwardChannel, RobotMap.flowerSolenoidReverseChannel);
        foldSolenoid.set(foldUpValue);
        latchSolenoid.set(latchClosedValue);
    }

    @Override
    protected void initDefaultCommand() {
        
    }

    public static HatchIntake getInstance() {
        if(instance == null) {
            instance = new HatchIntake();
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
     * Get the current latch position
     * @return CLOSED if the latch is closed, OPEN if the latch is open, or NONE if the neither output is activated on the double solenoid responsible for latching
     */
    public LatchPosition getLatchPosition() {
        Value value = latchSolenoid.get();
        LatchPosition result;

        if(value == latchClosedValue) {
            result = LatchPosition.CLOSED;
        } else if(value == latchOpenValue) {
            result = LatchPosition.OPEN;
        } else {
            result = LatchPosition.NONE;
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
     * Open the latch to grab a hatch panel
     */
    public void openLatch() {
        latchSolenoid.set(latchOpenValue);
    }

    /**
     * Close the latch to release a hatch panel
     */
    public void closeLatch() {
        latchSolenoid.set(latchClosedValue);
    }

    /**
     * Fold the intake up if it is down and vice versa
     */
    public void toggleFoldPosition() {
        Value currentValue = foldSolenoid.get();
        if(currentValue == foldUpValue) {
            foldDown();
        } else if(currentValue == foldDownValue) {
            foldUp();
        }
    }

    /**
     * Open the latch if it is closed and vice versa
     */
    public void toggleLatch() {
        if(latchSolenoid.get() == latchClosedValue) {
            openLatch();
        } else if(latchSolenoid.get() == latchOpenValue) {
            closeLatch();
        }
    }
}