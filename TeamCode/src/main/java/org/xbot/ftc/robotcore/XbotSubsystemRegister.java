package org.xbot.ftc.robotcore;

import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;
import org.xbot.ftc.robotcore.subsystems.arm.Arm;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;
// import org.xbot.ftc.robotcore.subsystems.intake.Intake;

public class XbotSubsystemRegister {

    public void registerListeners(RobotSubsystemManager robotSubsystemManager) {
        robotSubsystemManager.registerSubsystem(Drive.getInstance());
        robotSubsystemManager.registerSubsystem(Arm.getInstance());
    //    robotSubsystemManager.registerSubsystem(Intake.getInstance());
    }
}
