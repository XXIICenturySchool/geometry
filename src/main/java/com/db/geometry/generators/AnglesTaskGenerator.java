package com.db.geometry.generators;

import com.db.geometry.services.RandomService;
import com.db.geometry.tasks.Task;
import com.db.geometry.tasks.TaskInfo;
import com.db.geometry.drawers.TriangularDrawer;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@TaskName("Angles")
public class AnglesTaskGenerator implements TaskGenerator {

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

        List<Integer> newAngles = new LinkedList<>();

        int alpha = randomService.getInRange(30, 81);
        int beta = randomService.getInRange(30, 81);
        int gama = 180 - alpha - beta;


        String question = String.format("Дан треугольник с углами %d и %d, найдите третий угол.",alpha, beta);

        taskBuilder.question(question);
        taskBuilder.answer(String.valueOf(gama));


        TriangularDrawer triangularDrawer = new TriangularDrawer();
        BufferedImage bi = triangularDrawer.createOnAngles(Arrays.asList(alpha, beta, gama));

        String fileFullName = String.format("%s-%s.gif", examId, taskNum);
        String safeFileName = staticFolder + imagesPath + fileFullName;
        ImageIO.write(bi, "gif", new File(safeFileName));
        taskBuilder.url(imagesUrl + fileFullName);

        return taskBuilder.build();
    }
}
