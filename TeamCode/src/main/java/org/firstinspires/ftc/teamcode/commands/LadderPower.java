package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.LadderSubsystem;

import java.util.function.BooleanSupplier;

public class LadderPower extends CommandBase {

    private final LadderSubsystem ladder;
    private final double power;

    public LadderPower(LadderSubsystem l, double p) {
        ladder = l;
        addRequirements(ladder);

        power = p;
        this.interruptOn(ladder::cantGo);


    }

    @Override
    public void execute() {
        ladder.set(power);
    }

    @Override
    public void end(boolean interrupted) { ladder.stop(); }
}
