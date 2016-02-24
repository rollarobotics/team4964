package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
//import com.qualcomm.robotcore.util.Range;

public class RobotManager extends OpMode  {

    DcMotor motorRight;
    DcMotor motorLeft;
    DcMotor winch;
    DcMotor hook;
    Servo backClimber;
    Servo knockerLeft;
    Servo knockerRight;

    @Override
    public void init()
    {
        knockerRight = hardwareMap.servo.get("knockerRight");
        knockerLeft = hardwareMap.servo.get("knockerLeft");
        backClimber = hardwareMap.servo.get("backClimber");
        hook = hardwareMap.dcMotor.get("hookMotor");
        winch = hardwareMap.dcMotor.get("winchMotor");
        motorLeft = hardwareMap.dcMotor.get("leftMotor");
        motorRight = hardwareMap.dcMotor.get("rightMotor");
        motorRight.setDirection(DcMotor.Direction.REVERSE);
        //motorLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        //motorRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        knockerLeft.setPosition(0);
        knockerRight.setPosition(1);
        backClimber.setPosition(1);
    }

    @Override
    public void loop()
    {

    }
}
