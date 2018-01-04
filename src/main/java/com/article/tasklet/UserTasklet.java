package com.article.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Service;

@Service
public class UserTasklet implements Tasklet {

    /* Try to create .txt file if not exist and after addUser initialize batch have to add user's name on it */
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        return RepeatStatus.FINISHED;
    }

}
