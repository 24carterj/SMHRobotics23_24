package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;

public class ClawRelease extends CommandBase {
    private final ClawSubsystem claw;

    public ClawRelease(ClawSubsystem c) {
        claw = c;
        addRequirements(claw);
    }

    @Override
    public void initialize() {
        claw.release();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
