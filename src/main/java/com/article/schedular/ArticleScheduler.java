package com.article.schedular;

import com.article.dao.ArticleDAO;
import com.article.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ArticleScheduler {

    private ArticleDAO articleDAO;

    @Autowired
    public ArticleScheduler(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

//    @Scheduled(cron = "*/5 * * * * *")
    public void scheduleUsingCron() {

    }

//    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void scheduleUsingFixedDelayWithInitialDelay() {

    }


//    @Scheduled(fixedDelay = 1000)
    public void scheduleUsingDelay() {

    }

//    @Scheduled(fixedRate = 5000)
    public void scheduleUsingRate() {
        articleDAO.getAllArticleList();
    }
}
