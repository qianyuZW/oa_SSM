package org.ppcirgo.oa.timedtask;


import lombok.extern.slf4j.Slf4j;
import org.ppcirgo.oa.utils.DateUtlis;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Heart {
    @Scheduled(fixedRate = 2500)
    public void  outl(){
        System.out.println(DateUtlis.long2str(System.currentTimeMillis())+":......感受到**小顽童oa**心跳......");
        log.info("::......感受到**小顽童oa**心跳........::");
    }
}
