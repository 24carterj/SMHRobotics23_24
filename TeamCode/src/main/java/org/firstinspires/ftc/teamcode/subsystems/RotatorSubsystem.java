package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RotatorSubsystem extends SubsystemBase {

    private final ServoEx rotator;

    public RotatorSubsystem(final HardwareMap hwMap, final String name) {
        rotator = new SimpleServo(hwMap, name, 0, 360);
    }
    // Servo turning
    public void floorPos() { rotator.setPosition(0);}
    public void boardPos() {
        rotator.setPosition(0.38);
    }
    public void drop() {
        rotator.setPosition(0.4);
        rotator.setPosition(0.38);
    }

    public void initial() {
        rotator.turnToAngle(50);
    }

    public void up() {
        rotator.turnToAngle(43);
    } // 43

    public void down() {
        rotator.turnToAngle(120);
    } // 120

    public double getAngle() { return rotator.getAngle(); }
    public double getPos() { return rotator.getPosition(); }
}
