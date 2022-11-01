package frc.robot;
import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj.XboxController;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class DriveTrain {
    // motor init
    public final MotorGroup motorLeft = new MotorGroup(new VictorSPX[] {new VictorSPX(LEFT_MOTOR_PORT_A), new VictorSPX(LEFT_MOTOR_PORT_B), new VictorSPX(LEFT_MOTOR_PORT_C)});
    public final MotorGroup motorRight = new MotorGroup(new VictorSPX[] {new VictorSPX(RIGHT_MOTOR_PORT_A), new VictorSPX(RIGHT_MOTOR_PORT_B), new VictorSPX(RIGHT_MOTOR_PORT_C)});

    // joystic init
    public final XboxController controller;

    public DriveTrain(XboxController controller) {
        this.controller = controller;
    }

    private boolean isStill(double value) {
        // returns bool if joystick is not moving
        // basic but needed to save me time & fix joystick
        if(value > 0.15) {
            return false;
        } else if(value < -0.15) {
            return false;
        } else if(value == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void move() {
        // method moves robot according to user input
        double speed = controller.getLeftX();
        double bias = controller.getRightY();

        // joystick controlls
        if(isStill(speed) == true && bias > 0.1) {
            // static turn right
            motorLeft.safeSet(bias);
            motorRight.safeSet(bias * -1);

            System.out.println("static right");

        } else if(isStill(speed) == true && bias < -0.1) {
            // static turn left
            motorLeft.safeSet(bias * -1);
            motorRight.safeSet(bias);

            System.out.println("static left");

        } else if(isStill(speed) != true && bias > 0.1) {
            // dynamic turn right
            motorLeft.safeSet(speed);

            System.out.println("dynamic right");

            // ensure that robot wont perform a dangerous static turn while at speed
            if(speed - bias < 0) {
                motorRight.safeSet(0);
            } else {
                motorRight.safeSet(speed - bias);
            }
        } else if(isStill(speed) != true && bias < -0.1) {
            // dynamic turn left
            motorLeft.safeSet(speed);

            System.out.println("dynamic left");

            // ensure that robot wont perform a dangerous static turn while at speed
            if(speed - (bias * -1) < 0) {
                motorLeft.safeSet(0);
            } else {
                motorLeft.safeSet(speed - (bias * -1));
            }
        } else if(isStill(speed) != true) {
            // vertical movement
            motorLeft.safeSet(speed);
            motorRight.safeSet(speed);

            System.out.println("moving");

        } else {
            // no input
            motorLeft.safeSet(0);
            motorRight.safeSet(0);

            System.out.println("still");
        }
    }
}