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

    public static final double MAX_RPM = 435;
    public static final double MAX_POSITION = 100;
    public static final double MIN_POSITION = 0;

    public ArmSubsystem(final HardwareMap hwMap, final String name) {
        arm = new MotorEx(hwMap, name, Motor.GoBILDA.RPM_435);
        arm.setRunMode(Motor.RunMode.VelocityControl);
        arm.encoder.reset();

        aff = new ArmFeedforward(0,0,0);

    }
    public void upPos() {
        armToPos(0);
    }

    public void downPos() {
        armToPos(6);
    }

    public void up() {
        arm.setRunMode(Motor.RunMode.VelocityControl);
        arm.set(.5);
    }
    public void down() {
        arm.setRunMode(Motor.RunMode.VelocityControl);
        arm.set(-.5);
    }

    public void armToPos(int position) {
        arm.setRunMode(Motor.RunMode.PositionControl);
        arm.setTargetPosition(position);
        arm.set(0.5);
    }

    public double getPos() {
        return arm.getCurrentPosition();
    }

    public boolean atPos() {
        return arm.atTargetPosition();
    }

    public boolean cantGo() {
        return arm.getVelocity() < 0 && arm.getCurrentPosition() >= MAX_POSITION ||
               arm.getVelocity() > 0 && arm.getCurrentPosition() <= MIN_POSITION;
    }

    public void incrSpeed() { speed+=0.2; }

    public void decrSpeed() { speed-=0.2; }

    public void stop() {
        arm.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        arm.stopMotor();
    }


    public MotorEx getMotor() {
        return arm;
    }
    public Motor.Encoder getEncoder() {
        return arm.encoder;
    }

}
