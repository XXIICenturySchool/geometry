package com.db.geometry.generators;

import com.db.geometry.drawers.TriangularDrawer;
import com.db.geometry.services.RandomService;
import com.db.geometry.tasks.Constraint;
import com.db.geometry.tasks.Task;
import com.db.geometry.tasks.TaskInfo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import static com.db.geometry.drawers.helpers.MathHelper.getCathet;
import static com.db.geometry.drawers.helpers.MathHelper.getHypot;

@TaskName("FindCatheters")
public class FindCatheterTaskGenerator implements TaskGenerator {
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

        int cat1 = -1, hypot = -1;

        List<Constraint> constraints = taskInfo.getConstraints();

        Constraint constr1 = constraints.get(0);
        Constraint constr2 = constraints.get(1);


        if (constr2.getLower() != 0 && constr2.getUpper() !=0 ) {
            if(constr1.getLower() > constr2.getLower()) {
                throw new RuntimeException("Catheter's constraint is greater than hypotenuse!");
            }
            hypot = randomService.getInRange(constr2.getLower(), constr2.getUpper() + 1);
        }
        if (constr1.getLower() != 0 && constr1.getUpper() !=0 ) {
            cat1 = randomService.getInRange(constr1.getLower(), constr1.getUpper() + 1);
            if (hypot < cat1 && hypot !=-1) {
                cat1 = randomService.getInRange(constr1.getLower(), hypot);
            }
        }


        if (cat1 + hypot == -2) {
            hypot = randomService.getInRange(15, 21);
            cat1 = randomService.getInRange(10, hypot - 1);
        } else if (cat1 == -1) {
            cat1 = randomService.getInRange(hypot / 3, hypot/3 * 2);
        } else if (hypot == -1) {
            hypot = randomService.getInRange(cat1*3/2, cat1 * 2);
        }


        BufferedImage bi = triangularDrawer.createOnCathetAndHypotenuse(cat1, hypot);

        taskBuilder.answer(String.valueOf((int)getCathet(cat1, hypot)));

        String question = "В прямоугольном треугольнике дан один кактет = %d, и гипотенуза = %d. Найдите оставшиеся катет.";
        taskBuilder.question(String.format(question, cat1, hypot));
        String fileFullName = String.format("%s-%s.gif", examId, taskNum);
        String safeFileName = staticFolder + imagesPath + fileFullName;
        ImageIO.write(bi, "gif", new File(safeFileName));
        taskBuilder.url(imagesUrl + fileFullName);

        return taskBuilder.build();
    }
}
