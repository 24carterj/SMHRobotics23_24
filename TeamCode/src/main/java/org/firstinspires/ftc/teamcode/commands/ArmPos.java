package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmPos extends CommandBase {

    private final ArmSubsystem arm;
    private static int pos;

    public ArmPos(ArmSubsystem a, int p) {
        arm = a;
        addRequirements(arm);

        pos = p;

        // this.interruptOn(arm::cantGo);
    }

    @Override
    public void execute() {
        arm.armToPos(pos);
    }


}
