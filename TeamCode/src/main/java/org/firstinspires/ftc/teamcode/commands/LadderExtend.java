package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.LadderSubsystem;

public class LadderExtend extends CommandBase {

    private final LadderSubsystem ladder;

    public LadderExtend(LadderSubsystem l) {
        ladder = l;
        addRequirements(ladder);
    }

    @Override
    public void execute() {
        ladder.extend();
    }

}
