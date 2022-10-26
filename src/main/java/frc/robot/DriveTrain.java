package frc.robot;

public class DriveTrain {
    public final MotorGroup motorLeft;
    public final MotorGroup motorRight;

    public DriveTrain(MotorGroup motorLeft, MotorGroup motorRight) {
        this.motorLeft = motorLeft;
        this.motorRight = motorRight;
    }

    public void vertical(int percent) {
        // move forward / backward
        motorLeft.safeSet(percent);
        motorRight.safeSet(percent);
    }

    public void biasLeft(int percent, int bias) {
        // negative values not reccomended, for wider turns
        motorLeft.safeSet(percent - bias);
        motorRight.safeSet(percent);
    }

    public void biasRight(int percent, int bias) {
        motorLeft.safeSet(percent);
        motorRight.safeSet(percent - bias);
    }

    public void staticTurn(int percent) {
        // turn left / right
        motorLeft.safeSet(percent);
        motorRight.safeSet(percent * -1);
    }
}
