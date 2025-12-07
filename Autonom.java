package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Autonom extends LinearOpMode {
    DcMotor left_front;
    DcMotor right_front;
    DcMotor left_back;
    DcMotor right_back;
    DcMotor fw;  // Захват с1
    DcMotor bw;  // Шутер с2

    ElapsedTime runtime; // импорт класса который отвечает за продолжительность моего opMode

    @Override
    public void runOpMode() throws InterruptedException {
        left_front = hardwareMap.get(DcMotor.class, "lf");
        right_front = hardwareMap.get(DcMotor.class, "rf");
        left_back = hardwareMap.get(DcMotor.class, "lb");
        right_back = hardwareMap.get(DcMotor.class, "rb");
        fw = hardwareMap.get(DcMotor.class, "c1");
        bw = hardwareMap.get(DcMotor.class, "c2");

        left_front.setDirection(DcMotor.Direction.REVERSE);
        left_back.setDirection(DcMotor.Direction.REVERSE);

        // Проезд в autonom по времени
        runtime.reset();  // Сбрасываем время

        while(opModeIsActive() && runtime.milliseconds() < 3000){   // проехать 3 секунды (по выбранной траектории)
            double axial = 1;     // вперед / (-) назад
            double lateral = 0;   // (-) влево / вправо
            double yaw = 0;       // вокруг своей оси

            // формировка мощности на каждый мотор (что бы все было равномерно)
            double lfp = axial + lateral + yaw;   // левый передний
            double rfp = axial - lateral - yaw;   // правый передний
            double lbp = axial - lateral + yaw;   // левый задний
            double rbp = axial + lateral - yaw;   // правый задний

            // выставляем мощность
            left_front.setPower(lfp);
            right_front.setPower(rfp);
            left_back.setPower(lbp);
            right_back.setPower(rbp);
        }
    }
}
