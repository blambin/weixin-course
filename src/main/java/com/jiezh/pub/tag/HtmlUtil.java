package com.jiezh.pub.tag;

import com.jiezh.dao.base.cache.CacheDao;
import com.jiezh.pub.Env;
import com.jiezh.pub.util.DaoUtil;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义标签
 * 
 * @author liangds
 *
 */
@SuppressWarnings("deprecation")
public class HtmlUtil implements TemplateMethodModel {

    @SuppressWarnings("rawtypes")
    @Override
    public Object exec(List list) throws TemplateModelException {
        StringBuffer sb = new StringBuffer();
        try {
            // 参数要按顺序来传递 格式如下
            // list[0] 类型 list[1]..之后为参数 严格来传参
            if (list != null && list.size() > 0) {
                String tag = (String) list.get(0);
                CacheDao dao = DaoUtil.instance().cacheDao();
                /**
                 * 下拉框 0 标准 select type codeType defValue where haveHead
                 * headName 1 自定义 select type codeType defValue paramName
                 * paramValue haveHead headName
                 */
                // select 标准型
                if ("select".equalsIgnoreCase(tag)) {
                    String type = (String) list.get(1);
                    String codeType = (String) list.get(2);
                    String defValue = (String) list.get(3);
                    if (defValue == null || "".equals(defValue)) {
                        defValue = "";
                    }
                    // 标准
                    if ("0".equals(type)) {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("codeType", codeType);// codeType
                        map.put("whereCase", (String) list.get(4));// where
                        if ("true".equals((String) list.get(5))) {
                            sb.append("<option value=\"\">" + ((String) list.get(6)) + "</option>");
                        }
                        List<Map<String, String>> options = dao.getOptions(map);
                        if (options != null && options.size() > 0) {
                            for (Map<String, String> option : options) {
                                sb.append("<option " + (defValue.equals(option.get("VALUE")) ? "selected" : "") + " value=\"" + option.get("VALUE")
                                    + "\">" + option.get("NAME") + "</option>");
                            }
                        }
                    }
                    // 自定义
                    else if ("1".equals(type)) {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("codeType", codeType);// codeType
                        if ("true".equals((String) list.get(6))) {
                            sb.append("<option value=\"\">" + ((String) list.get(7)) + "</option>");
                        }
                        // 1.找出自定义sql
                        //String sql = dao.getCustomSql(map);
                        String sql = null;
                        if (sql == null || "".equals(sql)) {
                            return "";
                        }
                        Map<String, String> param = new HashMap<String, String>();
                        param.put("sql", sql);
                        splitStr((String) list.get(4), (String) list.get(5), param);
                        List<Map<String, Object>> options = dao.getCustomOptions(param);
                        if (options != null && options.size() > 0) {
                            for (Map<String, Object> option : options) {
                                sb.append("<option " + (defValue.equals(option.get("VALUE").toString()) ? "selected" : "") + " value=\""
                                    + option.get("VALUE").toString() + "\">" + option.get("NAME") + "</option>");

                            }
                        }
                    }
                }

                // 工程路径 webPath
                else if ("webPath".equalsIgnoreCase(tag)) {
                    sb.append(Env.getWebRoot());
                }
                // 级联下拉框数据
                else if ("cascadeselect".equalsIgnoreCase(tag)) {
                    String codeType = (String) list.get(2);
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("codeType", codeType);// codeType

                    // 1.找出自定义sql
                    //String sql = dao.getCustomSql(map);
                    String sql = null;
                    if (sql == null || "".equals(sql)) {
                        return "";
                    }
                    sb.append("[");
                    Map<String, String> param = new HashMap<String, String>();
                    param.put("sql", sql);
                    List<Map<String, Object>> options = dao.getCustomOptions(param);
                    if (options != null && options.size() > 0) {
                        for (Map<String, Object> option : options) {
                            // [[PARENT, TEXT, value]]
                            if (!("".equals(option.get("TEXT")) || option.get("TEXT") == null || "".equals(option.get("VALUE"))
                                || option.get("VALUE") == null)) {
                                sb.append(
                                    "[" + option.get("PARENT") + ",'" + option.get("TEXT").toString() + "'," + option.get("VALUE").toString() + "],");
                            }

                        }
                    }
                    sb.append("[]]");
                }
                // 模糊查询 下拉框数据
                else if ("queryselect".equalsIgnoreCase(tag)) {
                    String type = (String) list.get(1);
                    String codeType = (String) list.get(2);
                    String defValue = (String) list.get(3);
                    if (defValue == null || "".equals(defValue)) {
                        defValue = "";
                    }

                    sb.append("{data:[");
                    // 标准
                    if ("0".equals(type)) {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("codeType", codeType);// codeType
                        map.put("whereCase", (String) list.get(4));

                        List<Map<String, String>> options = dao.getOptions(map);
                        if (options != null && options.size() > 0) {
                            int cont = 0;
                            for (Map<String, String> option : options) {
                                cont++;
                                sb.append("{id:" + option.get("VALUE").toString() + ",name: \"" + option.get("NAME").toString() + "\"}");
                                if (cont != options.size()) sb.append(",");

                            }
                        }
                    }
                    // 自定义
                    else if ("1".equals(type)) {

                        Map<String, String> map = new HashMap<String, String>();
                        map.put("codeType", codeType);// codeType

                        // 1.找出自定义sql
                        //String sql = dao.getCustomSql(map);
                        String sql = null;
                        if (sql == null || "".equals(sql)) {
                            return "";
                        }
                        Map<String, String> param = new HashMap<String, String>();
                        param.put("sql", sql);
                        splitStr((String) list.get(5), (String) list.get(6), param);
                        List<Map<String, Object>> options = dao.getCustomOptions(param);
                        if (options != null && options.size() > 0) {
                            int cont = 0;
                            for (Map<String, Object> option : options) {
                                cont++;
                                sb.append("{id:\"" + option.get("VALUE").toString() + "\",name: \"" + option.get("NAME").toString() + "\"}");
                                if (cont != options.size()) sb.append(",");

                            }
                        }

                    }

                    sb.append("]}");

                    /**
                     * sb.append("{data:[");
                     * sb.append("{id:\"Breaking Bad\",name: \"chemistry teacher\"}");
                     * sb.append(",{id:\"Game of Thrones\",name:\"Several noble families fight\"}");
                     * sb.append(",{id:\"Dexter\",name:\"A Miami police forensics\"}");
                     * sb.append("]}");
                     **/
                }

            }
        } catch (

        Exception ex)

        {
            System.out.println("============TemplateModelException=======begin======");
            ex.printStackTrace();
            System.out.println("============TemplateModelException=======end======");
        }
        return sb.toString();

    }

    /**
     * 分割参数 以逗号为分割符
     * 
     * @param paramNames
     * @param paramValues
     * @param map
     */
    private void splitStr(String paramNames, String paramValues, Map<String, String> map) {
        String sql = map.get("sql").toString();
        String[] names = paramNames.split(",");
        String[] values = paramValues.split(",");
        if (names != null && values != null && names.length == values.length && paramNames.length() != 0 && paramValues.length() != 0) {

            if (sql.toLowerCase().contains("where")) {
                sql += "";
            } else {
                sql += " where 1=1 ";
            }
            for (int i = 0; i < names.length; i++) {
                map.put(names[i], values[i]);
                if (names[i].length() == 0) break;
                sql += " and " + names[i].toString() + "= '" + values[i].toString() + "'";
            }
            map.put("sql", sql);
        }
    }

}
