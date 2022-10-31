package frc.robot;

import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj.XboxController;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class DriveTrainArcade {
    // motor init
    public final MotorGroup motorLeft = new MotorGroup(new VictorSPX[] { new VictorSPX(LEFT_MOTOR_PORT_A), new VictorSPX(LEFT_MOTOR_PORT_B), new VictorSPX(LEFT_MOTOR_PORT_C) }); // ensure that these ports are correct
    public final MotorGroup motorRight = new MotorGroup(new VictorSPX[] { new VictorSPX(RIGHT_MOTOR_PORT_A), new VictorSPX(RIGHT_MOTOR_PORT_B), new VictorSPX(RIGHT_MOTOR_PORT_C) }); // ensure that these ports are correct
    public final XboxController controller;
    public double bias = 0;

    public DriveTrainArcade(XboxController controller) {
        this.controller = controller;
    }

    public void move() {
        // move on joystick input
        // call in periodic

        if (controller.getAButton() == true) {
            if (bias <= 1 && bias > 0) {
                bias += 0.2;
            }
        } else if (controller.getBButton() == true) {
            if (bias <= 1 && bias > 0) {
                bias -= 0.2;
            }
        }

        // actual controlls
        if(controller.getRightX() > 0) {
            motorLeft.safeSet(controller.getLeftY());
            motorRight.safeSet(controller.getLeftY() - bias);
        } else if(controller.getRightX() < 0) {
            motorLeft.safeSet(controller.getLeftY() - bias);
            motorRight.safeSet(controller.getLeftY());
        } else if (controller.getLeftY() != 0) {
            motorLeft.safeSet(controller.getLeftY());
            motorRight.safeSet(controller.getLeftY());
        } else {
            motorLeft.safeSet(0);
            motorRight.safeSet(0);
        }
    }
}