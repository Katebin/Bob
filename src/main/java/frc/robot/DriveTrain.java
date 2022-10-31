package frc.robot;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.XboxController;

import java.math.*;
import static frc.robot.Constants.*;

public class DriveTrain {
    // motor init
    public final MotorGroup motorLeft = new MotorGroup(new VictorSPX[] {new VictorSPX(LEFT_MOTOR_PORT_A), new VictorSPX(LEFT_MOTOR_PORT_B), new VictorSPX(LEFT_MOTOR_PORT_C)} );  // ensure that these ports are correct
    public final MotorGroup motorRight = new MotorGroup(new VictorSPX[] {new VictorSPX(RIGHT_MOTOR_PORT_A), new VictorSPX(RIGHT_MOTOR_PORT_B), new VictorSPX(RIGHT_MOTOR_PORT_C)} ); // ensure that these ports are correct
  
    public final XboxController controller;

    public DriveTrain(XboxController controller) {
        this.controller = controller;
    }

    public void move() {
        // tank drive
        //motorLeft.safeSet(controller.getLeftY());
        //motorRight.safeSet(controller.getRightY() * -1); // invert motor

        // cooler drive
        double curvature = controller.getLeftX() / MIN_TURNING_RADIUS_M; // inverse meters
        double speed = controller.getLeftY();

        double speedLeft = speed * (1 + curvature * WHEEL_BASE_M);
        double speedRight = speed * (1 - curvature * WHEEL_BASE_M); 

        double maxSpeed = Math.max(Math.abs(speedLeft), Math.abs(speedRight)); // abs = flip negatives
        
        if(maxSpeed > MAX_MOTOR_PERCENT) {
        // if motor faster than max speed, divide both motors to perserve ratio (stay in range)
        speedLeft /= maxSpeed;
        speedRight /= maxSpeed;
        }

        motorLeft.safeSet(speedLeft);
        motorRight.safeSet(speedRight * -1); // inverted
    }
}
