package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ArmFeedforward;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.motors.GoBILDA5202Series;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ArmSubsystem extends SubsystemBase {

    // lift motor
    private final MotorEx arm;
    private ArmFeedforward aff;

    private double speed = 1;

    public ArmSubsystem(final HardwareMap hwMap, final String name) {
        arm = new MotorEx(hwMap, name, Motor.GoBILDA.RPM_435);
        arm.setRunMode(Motor.RunMode.PositionControl);
        arm.encoder.reset();

        aff = new ArmFeedforward(0,0,0,0);

    }

    public void up() {
        // completely made up value plz help
        arm.set(-4 * speed);
    }

    public void down() {
        // completely made up value plz help
        arm.set(2 * speed);
    }

    public void incrSpeed() {
        // completely made up value plz help
        speed+=0.2;
    }

    public void decrSpeed() {
        // completely made up value plz help
        speed-=0.2;
    }

    public void stop() {
        arm.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        arm.stopMotor();
    }

    public boolean stopConditions() {
        return arm.getCurrentPosition() == 0 || arm.getCurrentPosition() == 100;
    }



    public MotorEx getMotor() {
        return arm;
    }
    public Motor.Encoder getEncoder() {
        return arm.encoder;
    }

}
