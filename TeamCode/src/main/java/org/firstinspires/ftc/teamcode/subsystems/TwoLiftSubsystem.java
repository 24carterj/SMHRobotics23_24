package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ElevatorFeedforward;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TwoLiftSubsystem extends SubsystemBase {

    // lift motor
    private final MotorEx leftLift;
    private final MotorEx rightLift;

    // private final ElevatorFeedforward eff;
    // private final MotorGroup dual;
    // speed
    private double speed = 1;
    // positions
    private double lowered;
    private double liftPos;
    private double lastLiftPos;
    // constants
    private final double ANGLE = 30;
    private final double COS30 = 0.86602540;


    public TwoLiftSubsystem(final HardwareMap hwMap, double s) {

        leftLift = new MotorEx(hwMap, "leftLift", Motor.GoBILDA.RPM_435);
        leftLift.setInverted(true);


        rightLift = new MotorEx(hwMap, "rightLift", Motor.GoBILDA.RPM_435);

        lowered = leftLift.getDistance();

        leftLift.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rightLift.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        leftLift.encoder.reset();
        rightLift.encoder.reset();

        speed = s;

        // eff = new ElevatorFeedforward();
        // leftLift.setFeedforwardCoefficients();
    }

    public void posMode() {
        leftLift.setRunMode(Motor.RunMode.PositionControl);
        rightLift.setRunMode(Motor.RunMode.PositionControl);
    }

    public void veloMode() {
        leftLift.setRunMode(Motor.RunMode.VelocityControl);
        rightLift.setRunMode(Motor.RunMode.VelocityControl);
    }

    public void up() {
        veloMode();
        leftLift.set(-2.5 * speed);
        rightLift.set(-2.5 * speed);

    }

    public void down() {
        veloMode();
        leftLift.set(2.5 * speed);
        rightLift.set(2.5 * speed);
    }

    public double getHeight() {
        return leftLift.getDistance() * COS30;
    }

    public void incrSpeed() {
        speed = Math.max(0,Math.min(speed+0.2,3));
    }

    public void decrSpeed() {
        speed=Math.max(0,Math.min(speed-0.2,3));
    }

    public void toPosFF(int position) {
        posMode();


    }

    public void setSpeed(double s) {
        speed *= s;
    }

    public void moveToPos(double s, double d) {
        // 7.5 -> 12.2 per rot == 4.7 in / rev
        int target = (int)(Math.PI * d / 4.7); // number of inches
    }
    /*
    public void moveToPos2(double d) {
        while ()
    }
     leftLift.setDistancePerPulse(0.2340702210663199);
        rightLift.setDistancePerPulse(0.2340702210663199);
    */
    public double getPos() {
        return leftLift.getDistance();

    }
    public double getRevs() {
        return leftLift.encoder.getRevolutions();
    }

    public void stop() {
        leftLift.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rightLift.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        leftLift.stopMotor();
        rightLift.stopMotor();
    }
}
