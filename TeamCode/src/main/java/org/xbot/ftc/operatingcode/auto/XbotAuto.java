package org.xbot.ftc.operatingcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.xbot.ftc.operatingcode.BaseRobot;
import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;
import org.xbot.ftc.robotcore.subsystems.arm.Arm;
//import org.xbot.ftc.robotcore.subsystems.intake.Intake;
public class XbotAuto extends LinearOpMode {
        @Override
        public void runOpMode() throws InterruptedException {
            BaseRobot.initOpMode(this, hardwareMap, telemetry);
            RobotSubsystemManager robotSubsystemManager = RobotSubsystemManager.getInstance();
            //initialize a base auto? Ask issa ig
            //BaseJewelAuto baseJewelAuto = new BaseJewelAuto(XbotColorSensor.Color.RED, this, hardwareMap, telemetry);
            Drive drive = (Drive) robotSubsystemManager.getSubsystem(Drive.class.getName());
            Arm arm = (Arm) robotSubsystemManager.getSubsystem(Arm.class.getName());
         //   Intake intake = (Intake) robotSubsystemManager.getSubsystem(Intake.class.getName());
            // not sure what sensor we have yet
            //  BoschIMU imu = (BoschIMU) robotSubsystemManager.getSubsystem(BoschIMU.class.getName());
            //   imu.enableImu();
            waitForStart();

            robotSubsystemManager.getGameClock().resetClock();

            // baseJewelAuto.run();


            RobotSubsystemManager.getInstance().stop();
        }
    }

