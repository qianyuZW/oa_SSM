package org.ppcirgo.oa.timedtask;


import org.ppcirgo.oa.utils.DateUtlis;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Heart {
    @Scheduled(fixedRate = 1500)
    public void  outl(){
        System.out.println(DateUtlis.long2str(System.currentTimeMillis())+":......感受到**小顽童oa**心跳......");
    }
}
