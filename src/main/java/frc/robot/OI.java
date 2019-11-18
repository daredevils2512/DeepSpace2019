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

import frc.robot.TriggerButton;
import frc.robot.commands.*;
import frc.robot.constants.Constants;
import frc.robot.subsystems.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
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

    public OI(Compressorsorus compressor, Drivetrain drivetrain, Lift lift, CargoExtake cargoExtake, CargoIntake cargoIntake, HatchIntake hatchIntake) {
        rightTrigger.whenPressed(new SetDrivetrainLowGear(drivetrain, true)); // Shift down
        rightTrigger.whenReleased(new SetDrivetrainLowGear(drivetrain, false)); // Shift up
        leftTrigger.whenPressed(new SetInvertedDriving(drivetrain, true)); // Inverted
        leftTrigger.whenReleased(new SetInvertedDriving(drivetrain, false)); // Normal

        // center flower begins 1'3" off ground
        // center ball begins 7.5" off ground
        bottomRed.whenPressed(new RunToBottom(lift, true));
        bottomWhite.whenPressed(new RunToHeight(lift, Constants.Lift.Height.FEEDER, false));
        midRed.whenPressed(new RunToHeight(lift,  Constants.Lift.Height.ROCKET_CARGO_BOTTOM, false));
        midWhite.whenPressed(new RunToHeight(lift,  Constants.Lift.Height.ROCKET_CARGO_MIDDLE, false));
        topRed.whenPressed(new RunToHeight(lift,  Constants.Lift.Height.CARGO_SHIP_CARGO, false));
        topWhite.whenPressed(new RunToHeight(lift,  Constants.Lift.Height.ROCKET_CARGO_TOP, false));

        // TODO: Make this togglable?
        aButton.whenPressed(new SetCargoIntakeExtended(cargoIntake, true)); // Extend
        yButton.whenPressed(new SetCargoIntakeExtended(cargoIntake, false)); //Retract

        bButton.whileHeld(new RunCargoIntake(cargoIntake, -1.0, -1.0, false)); // In
        xButton.whileHeld(new RunCargoIntake(cargoIntake, 1.0, 1.0, false)); // Out

        bottomLeft.whileHeld(new RunCargoExtake(cargoExtake, -0.75, true)); // In
        topLeft.whileHeld(new RunCargoExtake(cargoExtake, 1.0, false)); // Out
        bottomRight.whileHeld(new RunCargoIntake(cargoIntake, -1.0, -1.0, true)); // In
        topRight.whileHeld(new RunCargoIntake(cargoIntake, 1.0, 1.0, false)); // Out

        bigRed.whenPressed(new ToggleCompressor(compressor));
        bigWhite.whenPressed(new CMG_IntakeCargo(lift, cargoExtake, cargoIntake));

        green.whenPressed(new ToggleHatchIntakeOpen(hatchIntake));
        yellow.whenPressed(new ToggleHatchIntakeExtended(hatchIntake));

        sideButton.whenPressed(new PutCargoInShip(this, lift, cargoExtake));
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
        return value;
    }

    public Double getTurn() {
        double value = desensitize(-driver.getRawAxis(4));
        return value;
    }

    public Double getLeft() {
        return desensitize(driver.getRawAxis(1));
    }

    public Double getRight() {
        return desensitize(-driver.getRawAxis(4));
    }
}