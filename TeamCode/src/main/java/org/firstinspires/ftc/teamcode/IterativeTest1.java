/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */
//@Disabled
@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
public class IterativeTest1 extends OpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor armJoint = null;
    private DcMotor armExtender = null;
    private DcMotor intake = null;
    private Servo trapdoor = null;

    private final double servoClosed = 0;
    private final double servoOpen = .5;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        armJoint = hardwareMap.get(DcMotor.class, "arm_joint");
        armExtender = hardwareMap.get(DcMotor.class, "arm_extender");
        intake = hardwareMap.get(DcMotor.class, "intake");
        trapdoor = hardwareMap.get(Servo.class, "trapdoor");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        armJoint.setDirection(DcMotor.Direction.FORWARD);
        armExtender.setDirection(DcMotor.Direction.FORWARD);
        intake.setDirection(DcMotor.Direction.FORWARD);
        trapdoor.setDirection(Servo.Direction.FORWARD);


        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

        gamepad2.setJoystickDeadzone(1);

        // Setup a variable for each drive wheel to save power level for telemetry
        double leftPower;
        double rightPower;

        // Choose to drive using either Tank Mode, or POV Mode
        // Comment out the method that's not used.  The default below is POV.

        // POV Mode uses left stick to go forward, and right stick to turn.
        // - This uses basic math to combine motions and is easier to drive straight.
        double drive = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        leftPower = Range.clip(drive + turn, -1.0, 1.0);
        rightPower = Range.clip(drive - turn, -1.0, 1.0);

        // Tank Mode uses one stick to control each wheel.
        // - This requires no math, but it is hard to drive forward slowly and keep straight.
        // leftPower  = -gamepad1.left_stick_y ;
        // rightPower = -gamepad1.right_stick_y ;

        // Send calculated power to wheels
        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);


        // move arm joint

        armJoint.setPower(gamepad2.right_stick_y);

        //move arm extender


        armExtender.setPower(gamepad2.left_stick_y);


        // spin intake

        if (gamepad2.right_bumper) {
            intake.setPower(1);
        } else if (gamepad2.left_bumper) {
            intake.setPower(-1);
        } else {
            intake.setPower(0);
        }

        //trapdoor servo help pls

        if (gamepad2.x) {
            trapdoor.setPosition(servoOpen);
        } else {
            trapdoor.setPosition(servoClosed);
        }


        // Show the elapsed game time.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        // Show the current Motor and Servo power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Drive Motors", "left (%.2f), right (%.2f)", leftDrive.getPower(), rightDrive.getPower());
        telemetry.addData("Arm Joint Power:", "Joint (%.2f)", armJoint.getPower());
        telemetry.addData("Arm Extender Power:","Extender (%.2f)", armExtender.getPower());
        telemetry.addData("Intake Power:","Intake (%.2f)", intake.getPower());
        telemetry.addData("Trapdoor Position:","Trapdoor (%.2f)", trapdoor.getPosition());
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
        @Override
        public void stop() {

        }

    }