/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

import frc.robot.TriggerButton;
import frc.robot.commands.*;
import frc.robot.constants.Constants;
import frc.robot.constants.Constants.*;
import frc.robot.subsystems.CargoExtake;
import frc.robot.subsystems.HatchIntake;
import frc.robot.subsystems.Lift;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */



public class OI {
    private static OI instance;

    private int driverPort = 0;
    private int coDriverPort = 1;
    private int buttonBoxPort = 2;
    //Joysticks
    public Joystick driver = new Joystick(this.driverPort);
    public Joystick extreme = new Joystick(this.coDriverPort);
    public Joystick buttonBox = new Joystick(this.buttonBoxPort);

    //All buttons
    Button aButton = new JoystickButton(driver, 1);
    Button bButton = new JoystickButton(driver, 2);
    Button xButton = new JoystickButton(driver, 3);
    Button yButton = new JoystickButton(driver, 4);
    Button leftBumper = new JoystickButton(driver, 5);
    Button rightBumper = new JoystickButton(driver, 6);
    Button select = new JoystickButton(driver, 7);
    Button start = new JoystickButton(driver, 8);
    Button leftStick = new JoystickButton(driver, 9);
    Button rightStick = new JoystickButton(driver, 10);
    TriggerButton leftTrigger = new TriggerButton(driver, 2);
    TriggerButton rightTrigger = new TriggerButton(driver, 3);

    Button triggerBoi = new JoystickButton(extreme, 1);
    Button sideButton = new JoystickButton(extreme, 2);
    Button bottomLeft = new JoystickButton(extreme, 3);
    Button bottomRight = new JoystickButton(extreme, 4);
    Button topLeft = new JoystickButton(extreme, 5);
    Button topRight = new JoystickButton(extreme, 6);
    Button frontLeft = new JoystickButton(extreme, 7);
    Button frontRight = new JoystickButton(extreme, 8);
    Button midLeft = new JoystickButton(extreme, 9);
    Button midRight = new JoystickButton(extreme, 10);
    Button backLeft = new JoystickButton(extreme, 11);
    Button backRight = new JoystickButton(extreme, 12);

    Button topWhite = new JoystickButton(buttonBox, 2);
    Button bigWhite = new JoystickButton(buttonBox, 3);
    Button midRed = new JoystickButton(buttonBox, 4);
    Button bottomWhite = new JoystickButton(buttonBox, 5);
    Button topRed = new JoystickButton(buttonBox, 6);
    Button green = new JoystickButton(buttonBox, 7);
    Button midWhite = new JoystickButton(buttonBox, 8);
    Button bigRed = new JoystickButton(buttonBox, 14);
    Button yellow = new JoystickButton(buttonBox, 15);
    Button bottomRed = new JoystickButton(buttonBox, 16);

    Trigger cargoSwitch = new DigitalInputTrigger(CargoExtake.getBallOccupancySwitch());
    Trigger liftSwitch = new DigitalInputTrigger(Lift.getLimitSwitch());

    private OI() {
        rightTrigger.whenPressed(new ShiftDown());
        rightTrigger.whenReleased(new ShiftUp());
        leftTrigger.whenPressed(new SetInvertedDriving(true));
        leftTrigger.whenReleased(new SetInvertedDriving(false));

        liftSwitch.whenActive(new ResetLiftEncoder());

        // center flower begins 1'3" off ground
        // center ball begins 7.5" off ground
        bottomRed.whenPressed(new RunToBottom(true));
        bottomWhite.whenPressed(new RunToHeight(LiftHeight.FEEDER, true));
        midRed.whenPressed(new RunToHeight(LiftHeight.ROCKET_CARGO_BOTTOM, true));
        midWhite.whenPressed(new RunToHeight(LiftHeight.ROCKET_CARGO_MIDDLE, true));
        topRed.whenPressed(new RunToHeight(LiftHeight.CARGO_SHIP_CARGO, true));
        topWhite.whenPressed(new RunToHeight(LiftHeight.ROCKET_CARGO_TOP, true));

        // Make this togglable?
        yButton.whenPressed(new FoldCargoIntakeUp());
        aButton.whenPressed(new FoldCargoIntakeDown());

        xButton.whileHeld(new RunCargoIntake(1.0, 1.0, false)); // out
        bButton.whileHeld(new RunCargoIntake(-1.0, -1.0, false)); // in

        topLeft.whileHeld(new RunCargoExtake(1.0, true)); // out
        bottomLeft.whileHeld(new RunCargoExtake(-0.75, true)); // in
        topRight.whileHeld(new RunCargoIntake(1.0, 1.0, true));
        bottomRight.whileHeld(new RunCargoIntake(-1.0, -1.0, true));

        bigRed.whenPressed(new ToggleCompressor());
        bigWhite.whenPressed(new CMG_IntakeCargo());

        green.whenPressed(new ToggleHatchIntakeLatch());
        yellow.whenPressed(new ToggleHatchIntakeFoldPosition());

        // Control both intakes and extakes simultaneously
        // green.whileHeld(new ExtakeCargo());
        // yellow.whileHeld(new IntakeCargo());

        // buttonBox7.whenPressed(new ToggleDriverVision());

        // topWhite.whenPressed(new FlowerControl());
        // topRed.whenPressed(new FlowerSlideControl());

        // Add in disable until ball is extaked. then reset trigger
        // cargoSwitch.whenActive(new CMG_LiftCargo());

        // cargoSwitch.whenActive(new CMG_IntakeBall());

        // topRight.whenPressed(new FlowerControl());

        sideButton.whenPressed(new PutCargoInShip());
    }

    public static OI getInstance() {
        if(instance == null) {
            instance = new OI();
        }
        return instance;
    }

    public double desensitize(double val) {
        double result = val;
        if (Math.abs(result) < Constants.OI.DEADZONE) {
            result = 0.0;
        }
        return result;
    }

    public Double liftControl() {
        return desensitize(extreme.getRawAxis(1));
    }

    public Double getMove() {
        double value = desensitize(driver.getRawAxis(1));
        double dir = Math.signum(value);
        return dir * value * value;
    }

    public Double getTurn() {
        double value = desensitize(-driver.getRawAxis(4));
        return value;
    }

    public Double getRight() {
        return desensitize(driver.getRawAxis(5));
    }
}