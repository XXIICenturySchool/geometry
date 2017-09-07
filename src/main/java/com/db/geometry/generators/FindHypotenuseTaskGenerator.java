package com.db.geometry.generators;

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
import java.util.LinkedList;
import java.util.List;

import static com.db.geometry.drawers.helpers.MathHelper.getCathet;
import static com.db.geometry.drawers.helpers.MathHelper.getHypot;


@TaskName("FindHypotenuse")
public class FindHypotenuseTaskGenerator implements TaskGenerator {

    @Autowired
    RandomService randomService;

    @Value("${app.hostUrl}")
    String hostUrl;

    @Value("${app.task.static.folder}")
    String staticFolder;

    @Value("${app.task.images.path}")
    String imagesPath;

    @Value("${app.task.images.url}")
    String imagesUrl;

    @Override
    @SneakyThrows
    public Task generateTask(TaskInfo taskInfo, String examId, int taskNum) {
        Task.TaskBuilder taskBuilder = Task.builder();
        TriangularDrawer triangularDrawer = new TriangularDrawer();

        int cat1 = -1, cat2 = -1;

        List<Constraint> constraints = taskInfo.getConstraints();

        Constraint constr1 = constraints.get(0);
        Constraint constr2 = constraints.get(1);
        if (constr1.getLower() != 0 && constr1.getUpper() !=0 ) {
            cat1 = randomService.getInRange(constr1.getLower(), constr1.getUpper() + 1);
        }
        if (constr2.getLower() != 0 && constr2.getUpper() !=0 ) {
            cat2 = randomService.getInRange(constr2.getLower(), constr2.getUpper() + 1);
        }
        if (cat1 + cat2 == -2) {
            cat1 = randomService.getInRange(10, 21);
            cat2 = randomService.getInRange(10, 21);
        } else if (cat1 == -1) {
            cat1 = randomService.getInRange(cat2 / 2, cat2 * 2);
        } else if (cat2 == -1) {
            cat2 = randomService.getInRange(cat1 / 2, cat1 * 2);
        }


        BufferedImage bi = triangularDrawer.createOnCathetus(cat1, cat2);

        taskBuilder.answer(String.valueOf((int)getHypot(cat1, cat2)));

        String question = "В прямоугольном треугольнике даны 2 катета %d, %d. Найдите гипотенузу.";
        taskBuilder.question(String.format(question, cat1, cat2));
        String fileFullName = String.format("%s-%s.gif", examId, taskNum);
        String safeFileName = staticFolder + imagesPath + fileFullName;
        ImageIO.write(bi, "gif", new File(safeFileName));
        taskBuilder.url(imagesUrl + fileFullName);

        return taskBuilder.build();
    }
}
