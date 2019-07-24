package frc.robot.lib;

public class ToleranceConverter {

    /**
     * retruns wether var input is within the target range which is the target +/- tolerance ---- 
     * inputs are doubles
     * 
     *  @param var          variable that is being measured
     * 
     *  @param target       target measurement ; center of tolerance zone
     * 
     *  @param tolerance    amout of +/-
     */
    public static boolean tolerance(double var, double target, double tolerance) {
        return (var >= target - tolerance && var <= target + tolerance);
    }

    /**
     * retruns wether var input is within the target range which is the target +/- tolerance ---- 
     * inputs are ints
     * 
     *  @param var          variable that is being measured
     * 
     *  @param target       target measurement ; center of tolerance zone
     * 
     *  @param tolerance    amout of +/-
     */
    public static boolean tolerance(int var, int target, int tolerance) {
        return (var >= target - tolerance && var <= target + tolerance);
    }
}