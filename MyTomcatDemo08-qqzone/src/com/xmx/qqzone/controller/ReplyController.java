package com.xmx.qqzone.controller;

import com.xmx.qqzone.entity.Reply;
import com.xmx.qqzone.entity.UserBasic;
import com.xmx.qqzone.service.ReplyService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class ReplyController {
    private ReplyService replyService;

    public String addReply(HttpServletRequest request, Integer logId, String context) {
        UserBasic userBasic = (UserBasic)request.getSession().getAttribute("userBasic");
        Reply reply = new Reply(context, logId, new Date(), userBasic.getId());
        replyService.addReply(reply);
        return "redirect:log.do?operate=getLogById&logId="+logId;
    }

    public String delReply(Integer replyId) {
        Integer logId = replyService.delReply(replyId);
        return "redirect:log.do?operate=getLogById&logId="+logId;
    }
}
