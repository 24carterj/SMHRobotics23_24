package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LauncherSubsystem;

public class Launch extends CommandBase {
    private final LauncherSubsystem launcher;

    public Launch(LauncherSubsystem l) {
        launcher = l;
        addRequirements(launcher);
    }

    @Override
    public void initialize() {
        launcher.launch(); // grab cone
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
