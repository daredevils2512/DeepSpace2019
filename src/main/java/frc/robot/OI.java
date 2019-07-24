/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTableInstance;

import frc.robot.TriggerButton;
import frc.robot.commands.*;
import frc.robot.constants.Constants.*;
import frc.robot.Robot;
import frc.robot.subsystems.BallXtake;
import frc.robot.subsystems.Lift;

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

  Trigger cargoSwitch = new DigitalInputTrigger(BallXtake.getBallOccupancySwitch());
  Trigger liftSwitch = new DigitalInputTrigger(Lift.getLimitSwitch());

  public OI() {

    rightTrigger.whenPressed(new ShiftDown());
    rightTrigger.whenReleased(new ShiftUp());
    // leftTrigger.whenPressed(new InvertDriving());
    yButton.whenPressed(new CargoFoldUp());
    aButton.whenPressed(new CargoFoldDown());
    xButton.whileHeld(new CargoRunIntake(1.0, 1.0, false)); // out
    bButton.whileHeld(new CargoRunIntake(-1.0, -1.0, false)); // in

    topLeft.whileHeld(new RunBallXtake(1.0, true)); //out
    bottomLeft.whileHeld(new RunBallXtake(-0.75, true)); //in
    topRight.whileHeld(new CargoRunIntake(1.0, 1.0, true));
    bottomRight.whileHeld(new CargoRunIntake(-1.0, -1.0, true));
    backLeft.whileHeld(new CMG_ExtakeBallBottom());
    
    
    // frontLeft.whenPressed(new FlowerOpen());
    // frontRight.whenPressed(new FlowerClose());
    // midLeft.whenPressed(new FlowerSlideOut());
    // midRight.whenPressed(new FlowerSlideIn());

    // center flower begins 1'3" off ground
    // center ball begins 7.5" off ground
    bottomRed.whenPressed(new RunToBottom(false));
    bottomWhite.whenPressed(new RunToPosition(LiftPosition.FEEDER, false));
    midRed.whenPressed(new RunToPosition(LiftPosition.ROCKET_CARGO_BOTTOM, false));
    midWhite.whenPressed(new RunToPosition(LiftPosition.ROCKET_CARGO_MIDDLE, false));
    topRed.whenPressed(new RunToPosition(LiftPosition.CARGO_SHIP_CARGO, false));
    topWhite.whenPressed(new RunToPosition(LiftPosition.ROCKET_CARGO_TOP, false));


    //---------Driver Vision on RIO----------------//

    // frontLeft.whenPressed(new ToggleDriverVision());

    //

    bigRed.whenPressed(new Compressor());
    bigWhite.whenPressed(new CMG_IntakeBall());

    // start.whenPressed(new DriveToWall());
    // greenBoi.whenPressed(new CargoFoldUp());
    // yellowBoi.whenPressed(new CargoFoldDown());

    // Flower not on robot
    // Using for controlling both intakes and extakes
    // buttonBox7.whenPressed(new FlowerSlideIn());
    // buttonBox15.whenPressed(new FlowerSlideOut());
    green.whileHeld(new ExtakeCargo());
    yellow.whileHeld(new IntakeCargo());
    // buttonBox7.whenPressed(new ToggleDriverVision());

    // topWhite.whenPressed(new FlowerControl());
    // topRed.whenPressed(new FlowerSlideControl());

    liftSwitch.whenActive(new ResetLiftEncoder());

    // Add in disable until ball is extaked. then reset trigger
    // cargoSwitch.whenActive(new CMG_LiftCargo());
    
    // cargoSwitch.whenActive(new CMG_IntakeBall());
    
    // topRight.whenPressed(new FlowerControl());

    sideButton.whenPressed(new PutCargoInShip());
  }

  public double desensitize(double val) {
    double result = val;
    if (Math.abs(result) < 0.15) {
			result = 0.0;
		}
		return result;
  }
  
  public Double liftControl() {
   return desensitize(extreme.getRawAxis(1));
  }
  
  public Double getMove() {
    return desensitize(driver.getRawAxis(1));
  }

  public Double getTurn() {
    return desensitize(-driver.getRawAxis(4));
  }

  public Double getRight() {
    return desensitize(driver.getRawAxis(5));
  }
}