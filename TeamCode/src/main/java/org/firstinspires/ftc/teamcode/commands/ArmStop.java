package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmStop extends CommandBase {

    private final ArmSubsystem arm;
    private final double targetPos;

    public ArmStop(ArmSubsystem a) {
        arm = a;
        addRequirements(arm);

        targetPos = a.getPos();

        // this.interruptOn(arm::cantGo);
    }


}
