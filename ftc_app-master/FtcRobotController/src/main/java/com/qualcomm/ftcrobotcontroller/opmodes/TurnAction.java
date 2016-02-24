package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Callum on 2/12/16.
 */
public class TurnAction extends RobotManager
{
    private float speed;
    private int distance;
    private String direction;
    protected boolean isComplete; public boolean isComplete() {return isComplete;}

    public TurnAction(String myDirection, int myDistance, float mySpeed)
    {
        speed = mySpeed;
        distance = myDistance;
        direction = myDirection;
    }

    public void run()
    {
        //motorRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        //motorLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorRight.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motorLeft.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        float powerLeft = 0;
        float powerRight = 0;
        if(direction.equals("left"))
        {
            powerLeft = -speed;
            powerRight = speed;
        }
        if(direction.equals("right"))
        {
            powerLeft = speed;
            powerRight = -speed;
        }
        powerLeft = Range.clip(powerLeft, -1, 1);
        powerRight = Range.clip(powerRight, -1, 1);
        motorLeft.setTargetPosition(distance);
        motorLeft.setPower(powerLeft);
        motorRight.setTargetPosition(distance);
        motorRight.setPower(powerRight);
    }

    public void loopCheck()
    {
        if(direction.equals("left") && motorRight.getCurrentPosition() >= distance)
        {
            isComplete = true;
        }
        if(direction.equals("right") && -motorRight.getCurrentPosition() >= distance)
        {
            isComplete = true;
        }
    }
}
