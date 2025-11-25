package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.ScannedDevices;
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "лебедев жми сюда")    // Объявили TeleOp
public class TeleOp extends LinearOpMode{    // класс для создания переменных, функций и т.д.
    // переменный моторов
    DcMotor right_front;
    DcMotor left_front;
    DcMotor right_back;
    DcMotor left_back;

    @Override
    public void runOpMode() throws InterruptedException {
        // соеденился с моторами по их имени
        right_front = hardwareMap.get(DcMotor.class, "rf");
        left_front = hardwareMap.get(DcMotor.class, "lf");
        right_back = hardwareMap.get(DcMotor.class, "rb");
        left_back = hardwareMap.get(DcMotor.class, "lb");

        // инверсия моторов (левых)
        left_front.setDirection(DcMotor.Direction.REVERSE);
        left_back.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        while(opModeIsActive()){
            // локальные переменные движения
            double axial = -gamepad1.right_trigger+gamepad1.left_trigger;      // езда вперед - назад (по оси y)
            double lateral = -gamepad1.left_stick_x;                           // езда влево - вправо (по оси x)
            double yaw = -gamepad1.right_stick_x;                              // поворот вокруг своей оси



            // формировка мощности на каждый мотор (что бы все было равномерно)
            double lfp = axial + lateral + yaw;     // левый передний
            double rfp = axial - lateral - yaw;     // правый передний
            double lbp = axial - lateral + yaw;     // левый задний
            double rbp = axial + lateral - yaw;     // правый задний

            // подача мощности на моторы
            right_front.setPower(rfp);
            left_front.setPower(lfp);
            right_back.setPower(rbp);
            left_back.setPower(lbp);
        }
    }
}
