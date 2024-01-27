package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.RotatorSubsystem;

public class DropPixel extends CommandBase {
    private final RotatorSubsystem rotate;
    private final ClawSubsystem claw;

    public DropPixel(RotatorSubsystem r, ClawSubsystem c) {
        rotate = r;
        claw = c;
        addRequirements(rotate);
        addRequirements(claw);
    }

    @Override
    public void initialize() {
        claw.release();
        rotate.drop();
    }

    @Override
    public boolean isFinished() {
        // need a way to time movement
        return true;
    }
}
