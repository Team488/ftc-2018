package  org.xbot.ftc;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name="Auto Sampling Test", group="Pushbot")
public class SamplingTest extends LinearOpMode {

    private SamplingOrderDetector detector;
    HardwarePushbot robot = new HardwarePushbot();   // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();
    public String cubeLocation = "NULL";
    public double timeToWait = 0;

    public void runOpMode() {
        detector = new SamplingOrderDetector();
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        detector.useDefaults();

        detector.downscale = 0.4; // How much to downscale the input frames

        robot.init(hardwareMap);
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();
// Optional Tuning
        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.001;

        detector.ratioScorer.weight = 15;
        detector.ratioScorer.perfectRatio = 1.0;
        waitForStart();

        detector.enable();

        while ((runtime.seconds() < 15 ) && (cubeLocation.equals("NULL"))) {
            telemetry.addData("Entering Loop:", "Now" );
            if (detector.getCurrentOrder().toString().equals("LEFT")) {
                telemetry.addData("Testing for:", "Left?");
                timeToWait = runtime.seconds();
                while ((runtime.seconds() < timeToWait + 1)) {
                    }
                if(detector.getCurrentOrder().toString().equals("LEFT")) {
                    telemetry.addData("Cube is:", "Left.");
                    cubeLocation = "LEFT";
                }
            } else if (detector.getCurrentOrder().toString().equals("CENTER")) {
                telemetry.addData("Testing for:", "Center?");
                timeToWait = runtime.seconds();
                while ((runtime.seconds() < timeToWait + 1)) {
                    }
                if(detector.getCurrentOrder().toString().equals("CENTER")) {
                    telemetry.addData("Cube is:", "Center.");
                    cubeLocation = "CENTER";
                }
            } else if (detector.getCurrentOrder().toString().equals("RIGHT")) {
                telemetry.addData("Testing for:", "Right?");
                timeToWait = runtime.seconds();
                while ((runtime.seconds() < timeToWait + 1)) {
                    }
                if(detector.getCurrentOrder().toString().equals("RIGHT")) {
                    telemetry.addData("Cube is:", "Right.");
                    cubeLocation = "RIGHT";
                }
            }
        }
        if (cubeLocation.equals("LEFT")) {

        }
        else if (cubeLocation.equals("CENTER")) {

        }
        else if (cubeLocation.equals("RIGHT")) {

        }
        else {
            //just go center
        }

    }

}