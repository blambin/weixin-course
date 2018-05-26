package com.jiezh.controller.weixin;

import com.jiezh.pub.Env;
import com.jiezh.pub.util.UploadUtil;
import com.jiezh.pub.web.WebAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 课程控制类
 *
 * @author yclimb
 * @date 2018/4/30
 */
@Controller
@Scope("prototype")
@RequestMapping("/weixin/upload")
public class UploadController extends WebAction {

    /**
     * 图片上传
     */
    @RequestMapping("/uploadImg")
    @ResponseBody
    public Map<String, Object> uploadImg() {

        Map<String, Object> resp = new HashMap<>(2);

        // 可取图片的Request对象，SpringMVC使用
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile uploadimage = multipartRequest.getFile("uploadimage");

        try {


            // 图片
            if (null != uploadimage && !uploadimage.isEmpty()) {
                String filePath = UploadUtil.upload(uploadimage, Env.UPLOAD_CODE_IMG).get("filePath");
            }

            resp.put("state", "SUCCESS");
            resp.put("info", "ok");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "提交失败，请联系管理员！！！");
            resp.put("state", "ERROR");
            resp.put("info", "error");
        }

        // 返回分页查询页面
        return resp;
    }

}
