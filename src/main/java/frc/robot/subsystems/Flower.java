package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Flower extends Subsystem {

    private DoubleSolenoid flowerSolenoid;
    private DoubleSolenoid flowerSlide;

    public Flower() {

        this.flowerSolenoid = new DoubleSolenoid(RobotMap.flowerSolenoidForwardChannel, RobotMap.flowerSolenoidReverseChannel);
        this.flowerSlide = new DoubleSolenoid(RobotMap.flowerSlideForwardChannel, RobotMap.flowerSlideReverseChannel);
    }

    @Override
    public void initDefaultCommand() {

    }

    private void openFlower(Value dir) {
        this.flowerSolenoid.set(dir);
    }

    public Value getOpeningPos() {
        return this.flowerSolenoid.get();
    }

    public void open() {
        System.out.println("Flower open height: " + Robot.m_lift.getLiftHeight());
        this.openFlower(RobotMap.flowerOpenPos);
    }

    public void close() {
        System.out.println("Flower close height: " + Robot.m_lift.getLiftHeight());
        this.openFlower(RobotMap.flowerClosedPos);
    }

    public void toggleFlower() {
        if (this.getOpeningPos().equals(RobotMap.flowerClosedPos)) {
            this.open();
        } else {
            this.close();
        }
    }

    private void slideFlower(Value dir) {
        this.flowerSlide.set(dir);
    }

    public Value getSlidePos() {
        return this.flowerSlide.get();
    }

    public void slideOut() {
        this.slideFlower(RobotMap.flowerOutPos);
    }

    public void slideIn() {
        this.slideFlower(RobotMap.flowerInPos);
    }

    public void toggleSlide() {
        if (this.getSlidePos().equals(RobotMap.flowerInPos)) {
            this.slideOut();
        } else {
            this.slideIn();
        }
    }

}