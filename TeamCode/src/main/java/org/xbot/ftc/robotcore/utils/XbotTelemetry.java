package org.xbot.ftc.robotcore.utils;

import android.content.Context;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.operatingcode.BaseRobot;
import org.xbot.ftc.operatingcode.teleop.XbotTeleOp;
import org.xbot.ftc.operatingcode.teleop.operator_1.TeleOpDrive;
import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;
import org.xbot.ftc.robotcore.subsystems.XbotSubsystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class XbotTelemetry {

    private String caption;
    private Object value;
    Context context;
    double waittime;
    double lastLogTime = 0;
    File file;
    String filename = "test.txt";
    String fileContents;
    FileOutputStream outputStream;
    PrintWriter printWriter;



    private static List<XbotTelemetry> toAddToTelemetry = new ArrayList<>();

    private XbotTelemetry(String caption, Object value) {
        this.caption = caption;
        this.value = value;
        context = RobotSubsystemManager.getInstance().getHardwareMap().appContext;
        file = new File(context.getExternalFilesDir(null), "FTCLog.txt");
        file.delete();


        try {
            if (lastLogTime + .5 < RobotSubsystemManager.getInstance().getGameClock().getTimeElapsed() ) {
                outputStream = new FileOutputStream(file, true);

            }
        } catch (Exception e) {
            e.printStackTrace();
            // close somehow
        }

    }

    public String getCaption() {
        return caption;
    }

    public Object getValue() {
        return value;
    }

    public static void addData(String caption, Object value) {
        toAddToTelemetry.add(new XbotTelemetry(caption, value));
    }

    public static List<XbotTelemetry> getDataToAddToTelemetry() {
        return toAddToTelemetry;
    }

    public static void clearData() {
        toAddToTelemetry.clear();
    }
}
