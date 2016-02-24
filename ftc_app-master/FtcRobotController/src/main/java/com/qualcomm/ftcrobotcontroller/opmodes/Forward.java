package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class Forward extends RobotManager {

    int leftEncoder = 0;
    int rightEncoder = 0;
    boolean isFinished = false;
    boolean shouldRun = true;
    boolean runOnce = true;
    int runningSecondAction = 0;
    int startasdf = 0;
    boolean aaa = true;
    int count = 0;

    public Forward() {

    }

    @Override
    public void init() {
        super.init();
        motorRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }


    @Override
    public void start() {
        motorRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorLeft.setPower(1.0f);
        motorRight.setPower(1.0f);
        startasdf++;
    }

    @Override

    public void loop() {

        leftEncoder = -motorLeft.getCurrentPosition();
        rightEncoder = -motorRight.getCurrentPosition();

        telemetry.addData("0", leftEncoder);
        telemetry.addData("1", rightEncoder);
        telemetry.addData("2", isFinished);
        telemetry.addData("3", shouldRun);
        telemetry.addData("4", runningSecondAction);
        telemetry.addData("5", startasdf);
        telemetry.addData("right power is", motorRight.getPower());
        telemetry.addData("left power is", motorLeft.getPower());

        if(leftEncoder >= 10000) {
            if (runOnce == true) {
                runOnce = false;
                motorLeft.setPower(0.0f);
                motorRight.setPower(0.0f);
                isFinished = true;
            }
        }
    }
    public void stop(){
        motorLeft.setPower(0.0f);
        motorRight.setPower(0.0f);
    }
}


