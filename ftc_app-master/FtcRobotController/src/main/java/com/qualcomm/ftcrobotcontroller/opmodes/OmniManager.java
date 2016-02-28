package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
//import com.qualcomm.robotcore.util.Range;

/**
 * Created by Callum on 2/16/16.
 */
public class OmniManager extends OpMode {

    DcMotor frontRight;
    DcMotor frontLeft;
    DcMotor backRight;
    DcMotor backLeft;

    @Override
    public void init()
    {
        frontRight = hardwareMap.dcMotor.get("br");
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        frontLeft = hardwareMap.dcMotor.get("bl");
        backRight = hardwareMap.dcMotor.get("fr");
        backRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft = hardwareMap.dcMotor.get("fl");
    }

    @Override
    public void loop()
    {

    }

}
