package frc.robot;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import static frc.robot.Constants.*;

public class MotorGroup {
    public final VictorSPX[] motors;

    public MotorGroup(VictorSPX[] motors) {
        this.motors = motors;
    }

    public void set(double percent) {
        // set all motors in the motor group
        
        for(int i = 0; i < motors.length; i++) {
            motors[i].set(ControlMode.PercentOutput, percent);
        }
    }

    public void safeSet(double percent) {
        // ensures the motor does not go over defined limit 
        // ie: avoid motor burn out

        if(percent > MAX_MOTOR_PERCENT) {
            set(MAX_MOTOR_PERCENT);
        } else if(percent < MAX_MOTOR_PERCENT * -1) {
            set(MAX_MOTOR_PERCENT * -1);
        } else {
            set(percent);
        }
    }
}
