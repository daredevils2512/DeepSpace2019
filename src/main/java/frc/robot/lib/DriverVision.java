/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.lib;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

/**
 * Add your docs here.
 */
public class DriverVision {
  private boolean isEnabled;

  UsbCamera frontCamera;
  String path;
  
  public DriverVision() {
    frontCamera = CameraServer.getInstance().startAutomaticCapture();
    isEnabled = true;
  }

  // public boolean toggleDriverVision() {
  //   if (ToggleDriverVision.driverVision) {
  //     frontCamera.setFPS(0);
  //     return false;
  //   } else {
  //     frontCamera.setFPS(30);
  //     return true;
  //   }
  // }

  public boolean getIsEnabled() {
    return isEnabled;
  }

  public void disable() {
    frontCamera.setFPS(0);
    isEnabled = false;
  }

  public void enable() {
    frontCamera.setFPS(30);
    isEnabled = true;
  }
}
