package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public abstract class Action extends RobotManager
{
    public abstract void run();
    protected boolean isComplete; public boolean isComplete() {return isComplete;}
    public Action()
    {
        isComplete = false;
    }
}