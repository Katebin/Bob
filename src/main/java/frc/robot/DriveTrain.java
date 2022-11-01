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

    public void move() {
        // method moves robot according to user input
        double speed = controller.getLeftY();
        double bias = controller.getRightX();

        // joystick controlls
        if(speed == 0 && bias > 0) {
            // static turn right
            motorLeft.safeSet(bias);
            motorRight.safeSet(bias * -1);

        } else if(speed == 0 && bias < 0) {
            // static turn left
            motorLeft.safeSet(bias * -1);
            motorRight.safeSet(bias);

        } else if(speed != 0 && bias > 0) {
            // dynamic turn right
            motorLeft.safeSet(speed);

            // ensure that robot wont perform a dangerous static turn while at speed
            if(speed - bias < 0) {
                motorRight.safeSet(0);
            } else {
                motorRight.safeSet(speed - bias);
            }
        } else if(speed != 0 && bias < 0) {
            // dynamic turn left
            motorLeft.safeSet(speed);

            // ensure that robot wont perform a dangerous static turn while at speed
            if(speed - (bias * -1) < 0) {
                motorLeft.safeSet(0);
            } else {
                motorLeft.safeSet(speed - (bias * -1));
            }
        } else if(speed != 0) {
            // vertical movement
            motorLeft.safeSet(speed);
            motorRight.safeSet(speed);

        } else {
            // no input
            motorLeft.safeSet(0);
            motorRight.safeSet(0);
        }
    }
}