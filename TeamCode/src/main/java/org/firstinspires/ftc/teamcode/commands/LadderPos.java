package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.LadderSubsystem;

public class LadderPos extends CommandBase {

    private LadderSubsystem ladder;
    private final int position;

    public LadderPos(LadderSubsystem l, int pos) {
        ladder = l;
        addRequirements(ladder);

        position = pos;
        this.interruptOn(ladder::cantGo);
    }

    @Override
    public void initialize() {
        ladder.runToPosInit(position);
    }

    @Override
    public boolean isFinished() {
        return ladder.atPos();
    }

    @Override
    public void end(boolean interrupted) { ladder.stop(); }
}

