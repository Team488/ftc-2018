package  org.xbot.ftc;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import android.content.Context;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

import java.io.FileOutputStream;


@TeleOp(name="Logging Test - Not for Comp", group="Iterative Opmode")
public class LoggingTest extends OpMode {
    Context context;
    double waittime;
    File file;
    String filename = "test.txt";
    String fileContents;
    FileOutputStream outputStream;
    PrintWriter printWriter;

    double lastLogTime;
    public ElapsedTime runtime = new ElapsedTime();

    public void init() {
        telemetry.addLine("Logger Ready");
    }
    public void init_loop() {

    }
    public void start() {
       context = hardwareMap.appContext;
       file = new File(context.getExternalFilesDir(null), "FTCLog.txt");
       file.delete();
       runtime.reset();
       lastLogTime = getRuntime();
    }
    public void loop() {
        if(lastLogTime + 1 > getRuntime()) {

            return;
        }

        try {
            lastLogTime = getRuntime();
            outputStream = new FileOutputStream(file, true);
            fileContents =  Float.toString(gamepad1.left_stick_y) + " " + Double.toString(getRuntime()) + "\n";
            printWriter = new PrintWriter(outputStream);
            printWriter.append(fileContents);
            printWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
            requestOpModeStop();
        }
    }
    public void stop() {

    }
}