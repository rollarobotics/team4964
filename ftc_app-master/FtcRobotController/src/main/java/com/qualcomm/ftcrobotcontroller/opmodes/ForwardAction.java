package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

public class ForwardAction extends RobotManager
{
    private float speed;
    private int distance;
    private boolean isComplete; public boolean isComplete() {return isComplete;}
    public ForwardAction(int myDistance, float mySpeed)
    {
        speed = mySpeed;
        distance = myDistance;
    }

    public void run()
    {
        telemetry.addData("0:", "Running");
        super.init();
        //motorRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        //motorLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorRight.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motorLeft.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        float powerLeft = speed;
        float powerRight = speed;
        powerLeft = Range.clip(powerLeft, -1, 1);
        powerRight = Range.clip(powerRight, -1, 1);
        motorLeft.setTargetPosition(distance);
        motorLeft.setPower(powerLeft);
        motorRight.setTargetPosition(distance);
        motorRight.setPower(powerRight);

    }

    public void loopCheck()
    {
        if(motorRight.getCurrentPosition() >= distance)
            isComplete = true;
    }

}