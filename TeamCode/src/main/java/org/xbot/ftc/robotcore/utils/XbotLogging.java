/*
package org.xbot.ftc.robotcore.utils;

import android.content.Context;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.xbot.ftc.operatingcode.BaseRobot;
import org.xbot.ftc.operatingcode.teleop.XbotTeleOp;
import org.xbot.ftc.robotcore.XbotRobotConstants;
import org.xbot.ftc.robotcore.XbotSubsystemRegister;
import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;
import org.xbot.ftc.robotcore.subsystems.drive.ArcadeDrive;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import com.qualcomm.robotcore.hardware.Gamepad;


public class XbotLogging {


    Context context;
    double waittime;
    double lastLogTime = 0;
    File file;
    String filename = "test.txt";
    String fileContents;
    FileOutputStream outputStream;
    PrintWriter printWriter;
    boolean isInitialized;

    public void init() {
            isInitialized = true;
            context = RobotSubsystemManager.getInstance().getHardwareMap().appContext;
            file = new File(context.getExternalFilesDir(null), "FTCLog.txt");
            file.delete();
            lastLogTime = GameClock.getInstance().getTimeElapsed();
    }
    public void run() {
        while (isInitialized) {
            if (lastLogTime + .25 > GameClock.getInstance().getTimeElapsed()) {
                return;
            }

            try {
                lastLogTime = GameClock.getInstance().getTimeElapsed();
                outputStream = new FileOutputStream(file, true);
                fileContents = Double.toString(RobotSubsystemManager.getInstance().getGameClock().getTimeElapsed()) + ", "  + "\n";
                printWriter = new PrintWriter(outputStream);
                printWriter.append(fileContents);
                printWriter.close();
                lastLogTime = RobotSubsystemManager.getInstance().getGameClock().getTimeElapsed();

            } catch (Exception e) {
                e.printStackTrace();
                isInitialized = false;
                //this seems wrong
            }

        }
    }

}
*/