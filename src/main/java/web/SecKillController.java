package web;

import Dto.Exposer;
import Dto.SecKillResult;
import entity.SecKill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.SecKillService;

import java.util.List;

@Controller
@RequestMapping("/seckill") //url格式: 模块/资源/{id}/细分
public class SecKillController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SecKillService secKillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model){
        List<SecKill> list = secKillService.getSecKillList();
        model.addAttribute(list);
        return "list";
    }

    @RequestMapping(value = "/{secKillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable Long secKillId, Model model){
        if (secKillId == null){
            return "redirect:/seckill/list";
        }
        SecKill secKill = secKillService.getSecKillById(secKillId);
        if (secKill == null){
            return "";
        }
        model.addAttribute(secKill);
        return "detail";
    }

    //ajax json
    @RequestMapping(value = "/{secKillId}/exposer", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public SecKillResult<Exposer> exposer(Long secKillId){
        SecKillResult<Exposer> result;
        try {
            Exposer exposer = secKillService.exportSecKillUrl(secKillId);
            result = new SecKillResult<Exposer>(true, exposer);
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            result = new SecKillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }

}
