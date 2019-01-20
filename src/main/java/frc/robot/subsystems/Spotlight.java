/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Relay;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Spotlight extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Relay spotlight;

  public Spotlight() {
    spotlight = new Relay(RobotMap.spotlightRelayPort, Relay.Direction.kForward);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void toggleSpotlight() {
    if (this.spotlight.get() == Relay.Value.kOff) {
      this.spotlight.set(Relay.Value.kOn);
    } else {
      this.spotlight.set(Relay.Value.kOff);
    }
  }
}
