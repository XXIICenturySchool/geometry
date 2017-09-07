package com.db.geometry.generators;

import com.db.geometry.services.ImageSaverService;
import com.db.geometry.services.RandomService;
import com.db.geometry.tasks.Constraint;
import com.db.geometry.tasks.Task;
import com.db.geometry.tasks.TaskInfo;
import com.db.geometry.drawers.TriangularDrawer;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.InetAddress;
import java.util.List;

import static com.db.geometry.drawers.helpers.MathHelper.getCathet;
import static com.db.geometry.drawers.helpers.MathHelper.getHypot;


@TaskName("Pythagoras")
public class PythagorasTaskGenerator implements TaskGenerator {

    @Autowired
    RandomService randomService;

    @Autowired
    ImageSaverService imageSaverService;

    @Override
    @SneakyThrows
    public Task generateTask(TaskInfo taskInfo, String examId, int taskNum) {
        Task.TaskBuilder taskBuilder = Task.builder();

        List<Constraint> constraints = taskInfo.getConstraints();

        int cathetA = -1, cathetB = -1, hypot = -1;
        Constraint constraintOnCathet1 = constraints.get(0);
        Constraint constraintOnCathet2 = constraints.get(1);
        Constraint constraintOnHypot = constraints.get(2);

        BufferedImage bi;
        TriangularDrawer triangularDrawer = new TriangularDrawer();

        if (constraintOnHypot.getLower() != 0 || constraintOnHypot.getUpper() != 0) {
            String question = "В прямоугольном треугольнике дана гипотенуза = %d, и один катет = %d, найдите неизвестный катет.";
            hypot = randomService.getInRange(constraintOnHypot.getLower(), constraintOnHypot.getUpper() + 1);
            if (constraintOnCathet1.getLower() != 0 || constraintOnCathet1.getUpper() != 0) {
                cathetA = randomService.getInRange(constraintOnCathet1.getLower(), Math.min(constraintOnCathet1.getUpper(), hypot) + 1);
                bi = triangularDrawer.createOnCathetAndHypotenuse(cathetA, hypot);
                taskBuilder.answer(String.valueOf(getCathet(cathetA, hypot)));
                taskBuilder.question(String.format(question, hypot, cathetA));
            } else if (constraintOnCathet2.getLower() != 0 || constraintOnCathet2.getUpper() != 0) {
                cathetB = randomService.getInRange(constraintOnCathet2.getLower(), Math.min(constraintOnCathet2.getUpper(), hypot) + 1);
                bi = triangularDrawer.createOnCathetAndHypotenuse(cathetB, hypot);
                taskBuilder.answer(String.valueOf(getCathet(cathetB, hypot)));
                taskBuilder.question(String.format(question, hypot, cathetB));
            } else {
                cathetA = randomService.getInRange(hypot/4, hypot/3 * 2);
                bi = triangularDrawer.createOnCathetAndHypotenuse(cathetA, hypot);
                taskBuilder.answer(String.valueOf(getCathet(cathetA, hypot)));
                taskBuilder.question(String.format(question, hypot, cathetA));
                System.out.println(cathetA + " " + hypot);
            }
        } else {
            String question = "В прямоугольном треугольнике даны два катета один = %d, и другой катет = %d, найдите гипотенузу.";
            if (constraintOnCathet1.getLower() != 0 || constraintOnCathet1.getUpper() != 0) {
                cathetA = randomService.getInRange(constraintOnCathet1.getLower(), constraintOnCathet1.getUpper() + 1);
            }
            if (constraintOnCathet2.getLower() != 0 || constraintOnCathet2.getUpper() != 0) {
                cathetB = randomService.getInRange(constraintOnCathet2.getLower(), constraintOnCathet2.getUpper() + 1);
            }
            if (cathetA + cathetB == -2) {
                cathetA = randomService.getInRange(3, 10);
                cathetB = randomService.getInRange(3, 10);
            } else {
                if (cathetA != -1) {
                    cathetB = randomService.getInRange(cathetA, cathetA * 2);
                } else if (cathetB != -1) {
                    cathetA = randomService.getInRange(cathetB, cathetB * 2);
                }
            }

            bi = triangularDrawer.createOnCathetus(cathetA, cathetB);
            taskBuilder.answer(String.valueOf(getHypot(cathetA, cathetB)));
            taskBuilder.question(String.format(question, cathetA, cathetB));
        }

        taskBuilder.url(imageSaverService.saveAndGetAddress(bi, examId, taskNum));

        return taskBuilder.build();
    }
}
