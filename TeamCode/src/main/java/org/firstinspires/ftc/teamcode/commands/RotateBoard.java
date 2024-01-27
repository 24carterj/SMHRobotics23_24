package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.RotatorSubsystem;

public class RotateBoard extends CommandBase {
    private final RotatorSubsystem rotate;

    public RotateBoard(RotatorSubsystem r) {
        rotate = r;
        addRequirements(rotate);
    }

    @Override
    public void execute() {
        rotate.up(); // grab cone
    }

    @Override
    public boolean isFinished() {
        // need a way to time movement
        return true;
    }
}
