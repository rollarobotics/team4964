/*
package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import java.util.ArrayList;

public class Menu extends RobotManager {

    //private String type = "nil";
    private int cursorPosition = 0;
    private String currentPage = "main";
    private boolean dUp = true;
    private boolean dDown = true;
    private boolean a = true;
    private ArrayList<Action> actions = new ArrayList<Action>(); protected ArrayList<Action> getActions() {return actions;}
    private int nextAction = 0; protected int getNextAction() {return nextAction;}
    protected void setNextAction(int myNextAction) {nextAction = myNextAction;}
    private int currentEdit = 0;

    public Menu()
    {
        //type = myType;
    }
    @Override
    public void init()
    {
        //super.init();
    }
    @Override
    public void loop()
    {
        if(gamepad1.dpad_up && dUp)
        {
            dUp = false;
            if(isInBounds(cursorPosition--)) {
                cursorPosition--;
                print();
            }
        }
        else
            dUp = true;
        if(gamepad1.dpad_down && dDown)
        {
            dDown = false;
            if(isInBounds(cursorPosition++)) {
                cursorPosition++;
                print();
            }
        }
        else
            dDown = true;
        if(gamepad1.a && a)
        {
            a = false;
            runAction(cursorPosition);
        }
        else
            a = true;
    }

    private void runAction(int position)
    {
        if(currentPage == "main") {
            if (position == 0)
                run();
            else if (position == 1)
                add();
            else
                edit(position);
        }
        if(currentPage == "add")
        {
            switch(position) {
                case 1:
                    actions.add(new MoveAction());
                    break;
                case 2:
                    actions.add(new TurnAction());
                    break;
                default:
                    break;
            }
            cursorPosition = 0;
            currentPage = "main";
            print();
        }
        if(currentPage == "edit") {
            if (position == 0) {
                cursorPosition = 0;
                currentPage = "main";
                print();
            }
            if (position == 1) {

            }
            if (position == 2) {

            }
            if (position == 3) {

            }
        }
    }

    private boolean isInBounds(int position)
    {
        if(currentPage == "main") {
            if (position >= 0 && position < (2 + actions.size()))
                return true;
            return false;
        }
        if(currentPage == "add") {
            if (position >= 0 && position < 4)
                return true;
            return false;
        }
        if(currentPage == "edit")
        {
            if(position >= 0 && position < 4)
            {
                cursorPosition = 0;
                currentPage = "main";
                print();
            }
        }
        return false;
    }

    private String printCursor(int position)
    {
        if(position == cursorPosition)
            return "<--";
        return "";
    }

    public void run() {
        if(actions.size() > 0)
            actions.get(nextAction).run();
    }

    private void add()
    {
        cursorPosition = 0;
        currentPage = "add";
        print();
    }

    private void edit(int position)
    {
        cursorPosition = 0;
        currentPage = "edit";
        currentEdit = position - 2;
        print();
    }

    public void print()
    {
        //print menue of stuff
        if(currentPage.equals("main"))
        {
            telemetry.addData("0:", "Run" + printCursor(0));
            telemetry.addData("1:", "Add" + printCursor(1));
            int count = 1;
            for (Action myAction : actions) {
                count++;
                telemetry.addData((actions.size() + 2) + ":", myAction.getType() + printCursor(count));
            }
        }
        if(currentPage == "add")
        {
            telemetry.addData("0:", "Back" + printCursor(0));
            telemetry.addData("1:", "Move" + printCursor(1));
            telemetry.addData("2:", "Turn" + printCursor(2));
        }
        if(currentPage == "edit")
        {
            telemetry.addData("0:", "Back" + printCursor(0));
            if(actions.get(currentEdit).getType() == "move")
            {
                telemetry.addData("1:", "Direction" + printCursor(1));
                telemetry.addData("2:", "Distance" + printCursor(2));
                telemetry.addData("3:", "Speed" + printCursor(3));
            }
            if(actions.get(currentEdit).getType() == "turn")
            {
                telemetry.addData("1:", "Direction" + printCursor(1));
                telemetry.addData("2:", "Ammount" + printCursor(2));
                telemetry.addData("3:", "Speed" + printCursor(3));
            }
        }
    }
}


//abstract classes are for inheritence
abstract class Action extends Menu {

    //run() will run the run in all subclasses
    public abstract void run();

    private String type = "nil";
    private double speed = 1;
    protected boolean finished = false;

    public Action(String myType) {
        type = myType;
    }

    public void setSpeed(double mySpeed)
    {
        speed = mySpeed;
    }

    public String getType() {
        return type;
    }
}

class MoveAction extends Action
{
    private int distance = 3000;
    private boolean canMoveOn = true;

    public MoveAction() {
        super("move");
    }

    public void run()
    {
        motorRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorRight.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motorLeft.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        float powerLeft = 1;
        float powerRight = 1;
        powerLeft = Range.clip(powerLeft, -1, 1);
        powerRight = Range.clip(powerRight, -1, 1);
        motorLeft.setTargetPosition(distance);
        motorLeft.setPower(powerLeft);
        motorRight.setTargetPosition(distance);
        motorRight.setPower(powerRight);
    }

    @Override
    public void loop()
    {
        if(motorLeft.getCurrentPosition() >= distance)
        {
            finished = true;
        }
        if(finished == true && canMoveOn == true)
        {
            canMoveOn = false;
            //call next action here
            if((getNextAction() + 1) < getActions().size())
            {
                setNextAction(getNextAction() + 1);
                getActions().get(getNextAction()).run();
            }

        }
    }
}

class TurnAction extends Action
{
    public TurnAction() {
        super("turn");
    }

    public void run()
    {

    }
}
*/