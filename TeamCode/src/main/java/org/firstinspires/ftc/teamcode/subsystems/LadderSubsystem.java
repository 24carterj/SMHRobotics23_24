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

    private final double MAX_DIST = 100;

    public LadderSubsystem(final HardwareMap hwMap, final String name) {
        ladder = new MotorEx(hwMap, name, Motor.GoBILDA.RPM_435);
        ladder.setRunMode(Motor.RunMode.PositionControl);
        ladder.encoder.reset();

        eff = new ElevatorFeedforward(0,0,0,0);

    }

    public void extend() {
        // completely made up value plz help
        ladder.set(-4 * speed);
    }

    public void retract() {
        // completely made up value plz help
        ladder.set(2 * speed);
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
