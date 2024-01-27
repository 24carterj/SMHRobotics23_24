package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.RotatorSubsystem;

public class RotateUp extends CommandBase {
    private final RotatorSubsystem rotate;

    public RotateUp(RotatorSubsystem r) {
        rotate = r;
        addRequirements(rotate);
    }

    @Override
    public void initialize() {
        rotate.up(); // grab cone
    }

    @Override
    public boolean isFinished() {
        // need a way to time movement
        return true;
    }
}
