package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.LadderSubsystem;

import java.util.function.BooleanSupplier;

public class LadderRetract extends CommandBase {

    private final LadderSubsystem ladder;

    public LadderRetract(LadderSubsystem l) {
        ladder = l;
        addRequirements(ladder);
        //this.interruptOn(ladder::cantGo);
    }

    @Override
    public void execute() {
        ladder.retract();
    }

}
