package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by Callum on 2/23/16.
 */
public class Utility extends OpMode {
    @Override
    public void init(){}
    @Override
    public void loop(){}

    public static int wheelRotations(int rotations)
    {
        return rotations * 1440;
    }

}
