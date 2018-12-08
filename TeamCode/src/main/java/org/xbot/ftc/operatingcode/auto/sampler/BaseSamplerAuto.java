package org.xbot.ftc.operatingcode.auto.sampler;


import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.operatingcode.BaseRobot;
import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;
import org.xbot.ftc.robotcore.subsystems.arm.Arm;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;
// import org.xbot.ftc.robotcore.subsystems.intake.Intake;
import org.xbot.ftc.robotcore.utils.GameClock;
import org.xbot.ftc.robotcore.utils.XbotTelemetry;

public class BaseSamplerAuto {

    private LinearOpMode opMode;
    private Telemetry telemetry;
    private Drive drive;
    private Arm arm;
 //   private Intake intake;
    private SamplingOrderDetector detector;
    public String cubeLocation = "NULL";


    public BaseSamplerAuto(Boolean isCloseToCrater, LinearOpMode opMode, HardwareMap hardwareMap, Telemetry telemetry) {
        BaseRobot.initOpMode(opMode, hardwareMap, telemetry);
        RobotSubsystemManager robotSubsystemManager = RobotSubsystemManager.getInstance();
        this.opMode = opMode;
        this.telemetry = telemetry;
        drive = (Drive) robotSubsystemManager.getSubsystem(Drive.class.getName());
        arm = (Arm) robotSubsystemManager.getSubsystem(Arm.class.getName());
   //     intake = (Intake) robotSubsystemManager.getSubsystem(Intake.class.getName());
    }

    public void run() {
        if (opMode.isStopRequested() && !opMode.opModeIsActive() && !opMode.isStarted()) return;
        GameClock gameClock = RobotSubsystemManager.getInstance().getGameClock();
        elementChooser(keepDetectingUntilGoldFound());



    }
    public Drive getDrive() {
        return drive;
    }
    public Arm getArm() {
        return arm;
    }
 //   public Intake getIntake() {
 //       return intake;
 //   }
    private SamplingOrderDetector.GoldLocation keepDetectingUntilGoldFound() {
        SamplingOrderDetector.GoldLocation currentLocation = detector.getCurrentOrder();
        while (currentLocation == SamplingOrderDetector.GoldLocation.UNKNOWN
                && opMode.opModeIsActive()
                && RobotSubsystemManager.getInstance().getGameClock().getTimeElapsed() < 25.0) {
            if (opMode.isStopRequested()) return SamplingOrderDetector.GoldLocation.UNKNOWN;
            currentLocation = detector.getCurrentOrder();
            if (detector.getCurrentOrder() != SamplingOrderDetector.GoldLocation.UNKNOWN)
                break;
        }
        return currentLocation;
    }
    private void elementChooser(SamplingOrderDetector.GoldLocation goldLocation) {
        if (goldLocation.equals(SamplingOrderDetector.GoldLocation.LEFT)) {
            bumpLeftElement();
        }
        else if (goldLocation.equals(SamplingOrderDetector.GoldLocation.CENTER)) {
            bumpCenterElement();
        }
        else if (goldLocation.equals(SamplingOrderDetector.GoldLocation.RIGHT)) {
            bumpRightElement();
        } else {
            XbotTelemetry.addData("ERROR:", "No GoldLocation Found");
        }

    }

    public void bumpLeftElement() {

    }
    public void bumpCenterElement() {

    }
    public void bumpRightElement() {

    }



}
