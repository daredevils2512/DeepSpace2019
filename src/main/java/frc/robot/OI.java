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

import frc.robot.constants.Constants;
import frc.robot.TriggerButton;
import frc.robot.commands.*;

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
  public Joystick buttonBox = new Joystick(this.coDriverPort);
  public Joystick extreme = new Joystick(this.buttonBoxPort);

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
  Button greenBoi = new JoystickButton(buttonBox, 7);
  Button midWhite = new JoystickButton(buttonBox, 8);
  Button bigRed = new JoystickButton(buttonBox, 14);
  Button yellowBoi = new JoystickButton(buttonBox, 15);
  Button bottomRed = new JoystickButton(buttonBox, 16); 

  public OI() {

    rightTrigger.whenPressed(new ShiftUp());
    rightTrigger.whenReleased(new ShiftDown());  

    yButton.whenPressed(new CargoFoldIntake(RobotMap.cargoUpPos));
    aButton.whenPressed(new CargoFoldIntake(RobotMap.cargoDownPos));

    xButton.whileHeld(new CargoRunIntake(0.5, 0.5));
    bButton.whileHeld(new CargoRunIntake(-0.5, -0.5));
    // bottomRed.whenPressed(new RunToPosition(Constants.LiftPosition.CARGOBOTTOM));
    // bottomWhite.whenPressed(new RunToPosition(Constants.LiftPosition.HATCHBOTTOM));
    // midRed.whenPressed(new RunToPosition(Constants.LiftPosition.CARGOMIDDLE));
    // midWhite.whenPressed(new RunToPosition(Constants.LiftPosition.HATCHMIDDLE));
    // topRed.whenPressed(new RunToPosition(Constants.LiftPosition.CARGOTOP));
    // topWhite.whenPressed(new RunToPosition(Constants.LiftPosition.HATCHTOP));
    bigRed.whenPressed(new Compressor()); 
    bigWhite.whenPressed(new CMG_IntakeBall());
    topLeft.whileHeld(new RunBallXtake(-1.0));
    topRight.whileHeld(new RunBallXtake(1.0));
    // topRight.whenPressed(new FlowerControl());

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
