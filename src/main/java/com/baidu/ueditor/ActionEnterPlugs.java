package com.baidu.ueditor;

import com.baidu.ueditor.define.ActionMap;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.hunter.FileManager;
import com.baidu.ueditor.hunter.ImageHunter;
import com.baidu.ueditor.upload.Uploader;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 重写百度富文本类，使其具有存储非项目路径
 */
public class ActionEnterPlugs extends ActionEnter {
    private HttpServletRequest request = null;
    private String rootPath = null;
    private String contextPath = null;
    private String actionType = null;
    private ConfigManager configManager = null;

    //图片存储路径
    private String saveRootPath;
    public ActionEnterPlugs(HttpServletRequest request, String rootPath,String saveRootPath){
        super(request,rootPath);
        this.request = request;
        this.rootPath = rootPath;
        this.actionType = request.getParameter("action");
        this.contextPath = request.getContextPath();
        this.configManager = ConfigManager.getInstance(this.rootPath, this.contextPath, request.getRequestURI());
        this.saveRootPath = saveRootPath;
    }
    @Override
    public String invoke() {
        if(this.actionType != null && ActionMap.mapping.containsKey(this.actionType)) {
            if(this.configManager != null && this.configManager.valid()) {
                State state = null;
                int actionCode = ActionMap.getType(this.actionType);
                Map<String, Object> conf = null;
                switch(actionCode) {
                    case 0:
                        return this.configManager.getAllConfig().toString();
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        conf = this.configManager.getConfig(actionCode);
                        //重点,这里修改了图片的存储路径
                        conf.put("rootPath",this.saveRootPath);
                        state = (new Uploader(this.request, conf)).doExec();
                        break;
                    case 5:
                        conf = this.configManager.getConfig(actionCode);
                        //重点,这里修改了图片的存储路径
                        conf.put("rootPath",this.saveRootPath);
                        String[] list = this.request.getParameterValues((String)conf.get("fieldName"));
                        state = (new ImageHunter(conf)).capture(list);
                        break;
                    case 6:
                    case 7:
                        conf = this.configManager.getConfig(actionCode);
                        //重点,这里修改了图片的存储路径
                        conf.put("rootPath",this.saveRootPath);
                        int start = this.getStartIndex();
                        state = (new FileManager(conf)).listFile(start);
                }

                return state.toJSONString();
            } else {
                return (new BaseState(false, 102)).toJSONString();
            }
        } else {
            return (new BaseState(false, 101)).toJSONString();
        }
    }
}