package frc.robot.lib;

public final class SpeedRamp {
    private SpeedRamp() {
        
    }

    /**
     * @param tolerance  the distance room for error
     * @param distDiffrence the distance between the robot and the point you are going to
     * @param rampStartDist the distance from where it will start to deccelerate
     * @param defaultSpeed the speed it will go while not ramping
     * @return a double from -1 to 1
     */
    public static double speedRamp(double tolerance, double distDiffrence, double rampStartDist, double defaultSpeed) {
        if (distDiffrence > tolerance) {
            return Math.min(defaultSpeed, distDiffrence / rampStartDist);
        } else if (distDiffrence < -tolerance) {
            return Math.max(-defaultSpeed, distDiffrence / rampStartDist);
        } else {
            return 0;
        }
    }
}