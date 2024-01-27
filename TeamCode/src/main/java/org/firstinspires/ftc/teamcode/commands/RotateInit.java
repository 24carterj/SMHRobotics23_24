package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.RotatorSubsystem;

public class RotateInit extends CommandBase {
    private final RotatorSubsystem rotate;

    public RotateInit(RotatorSubsystem r) {
        rotate = r;
        addRequirements(rotate);
    }

    @Override
    public void initialize() {
        rotate.initial(); // sets the thing out,
    }

    @Override
    public boolean isFinished() {
        // need a way to time movement
        return true;
    }
}
