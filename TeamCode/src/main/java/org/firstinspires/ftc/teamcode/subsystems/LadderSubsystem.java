package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ElevatorFeedforward;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveMotorVoltages;
import com.qualcomm.hardware.motors.GoBILDA5202Series;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LadderSubsystem extends SubsystemBase {

    // lift motor
    private final MotorEx ladder;
    private ElevatorFeedforward eff;
    // speed
    private double speed = 1;

    private static final double MAX_POSITION = 100;
    private static final double MIN_POSITION = 0;

    public LadderSubsystem(final HardwareMap hwMap, final String name) {
        ladder = new MotorEx(hwMap, name, Motor.GoBILDA.RPM_435);
        ladder.setRunMode(Motor.RunMode.VelocityControl);

        // ladder.setPositionTolerance(13.6);

        ladder.encoder.reset();
        // eff = new ElevatorFeedforward(0,0,0,0);

    }

    public void extend() {
        // completely made up value plz help
        ladder.setRunMode(Motor.RunMode.VelocityControl);
        ladder.set(-4 * speed);
    }

    public void retract() {
        // completely made up value plz help
        ladder.setRunMode(Motor.RunMode.VelocityControl);
        ladder.set(2 * speed);
    }

    public void set(double power) {
        ladder.setRunMode(Motor.RunMode.RawPower);
        ladder.set(power);
    }
    public void runToPos(int position) {
        ladder.setTargetPosition(position);
        ladder.setRunMode(Motor.RunMode.PositionControl);
        ladder.set(speed);
    }

    public void runToPosInit(int position) {
        ladder.setTargetPosition(position);
        ladder.setRunMode(Motor.RunMode.PositionControl);
        while (!ladder.atTargetPosition())
            ladder.set(speed);
        stop();
    }

    public boolean atPos() {
        return ladder.atTargetPosition();
    }

    public double getPos() { return ladder.getCurrentPosition(); }

    public boolean cantGo() {
        return ladder.getVelocity() > 0 && ladder.getCurrentPosition() >= MAX_POSITION ||
               ladder.getVelocity() < 0 && ladder.getCurrentPosition() <= MIN_POSITION;
    }
    public void incrSpeed() { speed+=0.2; }

    public void decrSpeed() { speed-=0.2; }

    public void stop() {
        ladder.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        ladder.stopMotor();
    }


    public MotorEx getMotor() {
        return ladder;
    }
    public Motor.Encoder getEncoder() {
        return ladder.encoder;
    }

}
