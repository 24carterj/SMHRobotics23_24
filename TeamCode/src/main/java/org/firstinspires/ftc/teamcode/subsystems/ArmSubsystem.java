package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.PIDFController;
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
    public static final double PULSES_PER_REVOLUTION = 384.5;


    public ArmSubsystem(final HardwareMap hwMap, final String name) {
        arm = new MotorEx(hwMap, name, Motor.GoBILDA.RPM_435);
        arm.setRunMode(Motor.RunMode.PositionControl);
        arm.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
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
        arm.set(.5);
    }
    public void down() {
        arm.set(-.5);
    }

    public void armToPos(int position) {

        arm.setTargetPosition(position);
        arm.set(0.5);
    }

    public void upFF() {
        arm.set(aff.calculate(0,1,0));
    }

    public void downFF() {
        arm.set(aff.calculate(0,-1,0));
    }

    public void stopFF() {
        arm.set(aff.calculate(0,0,0));
    }

    public double[] affCoeffs() {
        return new double[]{aff.ks, aff.kcos, aff.kv};
    }

    public void changeCoeffBy(String coeff, double amt) {
        if (coeff.equals("ks"))
            aff = new ArmFeedforward(aff.ks + amt, aff.kcos, aff.kv);
        else if (coeff.equals("kcos"))
            aff = new ArmFeedforward(aff.ks, aff.kcos + amt, aff.kv);
        else if (coeff.equals("kv"))
            aff = new ArmFeedforward(aff.ks, aff.kcos, aff.kv + amt);
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

        arm.set(0.09);
    }


    public MotorEx getMotor() {
        return arm;
    }
    public Motor.Encoder getEncoder() {
        return arm.encoder;
    }

}
