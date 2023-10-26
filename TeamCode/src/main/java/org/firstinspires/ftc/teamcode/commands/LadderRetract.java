package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.LadderSubsystem;

public class LadderRetract extends CommandBase {

    private final LadderSubsystem ladder;

    public LadderRetract(LadderSubsystem l) {
        ladder = l;
        addRequirements(ladder);
    }

    @Override
    public void execute() {
        ladder.retract();
    }

}
