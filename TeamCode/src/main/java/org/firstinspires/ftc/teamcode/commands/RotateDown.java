package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.RotatorSubsystem;

public class RotateDown extends CommandBase {
    private final RotatorSubsystem rotate;

    public RotateDown(RotatorSubsystem r) {
        rotate = r;
        addRequirements(rotate);
    }

    @Override
    public void initialize() {
        rotate.down(); // grab cone
    }

    @Override
    public boolean isFinished() {
        // need a way to time movement
        return true;
    }
}
