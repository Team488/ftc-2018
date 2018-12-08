package org.xbot.ftc.robotcore.subsystems.vision;

import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.robotcore.XbotRobotConstants;
import org.xbot.ftc.robotcore.subsystems.XbotSubsystem;

public class XbotSampler extends XbotSubsystem {

    private static XbotSubsystem instance = null;
    private static boolean initialized = false;

    private SamplingOrderDetector detector = null;

    private XbotSampler() {
    }
    @Override
    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        if (initialized) return;
        super.init(hardwareMap, telemetry);
        detector = hardwareMap.get(SamplingOrderDetector.class, XbotRobotConstants.DETECTOR);
        initialized = true;
    }
    @Override
    public void shutdownSubsystem() {
        initialized = false;
    }

    @Override
    public String getClassName() {
        return XbotSampler.class.getName();
    }
    public static XbotSubsystem getInstance() {
        if (instance == null) {
            instance = new XbotSampler();
        }
        return instance;
    }


}
