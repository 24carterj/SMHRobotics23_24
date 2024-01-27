package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class LauncherSubsystem extends SubsystemBase {

    private ServoEx launcher;
    private Servo c2;

    public LauncherSubsystem(final HardwareMap hwMap, final String name) {
        launcher = new SimpleServo(hwMap, name, 0, 360);
    }

    // Servo turning
    public void launch() {
        launcher.setPosition(1.0);
    }
}
