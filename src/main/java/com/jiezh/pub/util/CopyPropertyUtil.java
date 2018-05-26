package com.jiezh.pub.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * F层和P层之间，物体间的该属性（名字，型也同样）等价的设定。
 * 
 * @version 01-00 2012/11/16
 * @since 01-00
 */
public class CopyPropertyUtil {

    /**
     * 全属性复制拷贝，拷贝前的值和拷贝目的地的同属性（名字，型也一样）的值设定< br / >
     * 
     * @since 01-00
     */
    @SuppressWarnings("rawtypes")
	public static void copyAll(Object des, Object src) {

        if (des == null || src == null) {
            return;
        }
        // 拷贝前Map和拷贝后Map作成
        Map destMap = createBeanMap(des);
        Map srcMap = createBeanMap(src);
        copyAllCommon(des, src, destMap, srcMap, null, null, false);
        destMap = null;
        srcMap = null;
    }

    /**
     * 指定的属性复制指定的属性（名字，也同样，只复制型）进行< br / >
     * 
     * @since 01-00
     */
    @SuppressWarnings("rawtypes")
	public static void copySpecified(Object des, Object src, String[] propertyNames) {
        if (des == null || src == null) {
            return;
        }
        Map destMap = createBeanMap(des);
        Map srcMap = createBeanMap(src);
        copyAllCommon(des, src, destMap, srcMap, propertyNames, null, false);
        destMap = null;
        srcMap = null;
    }

    /**
     * 指定的属性以外复制指定的属性以外，进行复制< br / >
     * 
     * @since 01-00
     */
    @SuppressWarnings("rawtypes")
	public static void copyExcept(Object des, Object src, String[] propertyNames) {
        if (des == null || src == null) {
            return;
        }
        Map destMap = createBeanMap(des);
        Map srcMap = createBeanMap(src);
        copyAllCommon(des, src, destMap, srcMap, null, propertyNames, false);
        destMap = null;
        srcMap = null;
    }


    /**
     * P层F层的属性设定P层F层的同名属性值设定< br / >
     * 
     * @since 01-00
     */
    @SuppressWarnings("rawtypes")
    public static void copyAllPtoF(Object fObj, Object pObj, String prefix,
            ArrayList<String> pNumRskeys, ArrayList<String> pDateRskeys) {
        if (fObj == null || pObj == null) {
            return;
        }
        Map destMap = createBeanMap(fObj, prefix);
        Map srcMap = createBeanMap(pObj);
        Map pNumRskeysMap = createListToMap(pNumRskeys);
        Map pDateRskeysMap = createListToMap(pDateRskeys);

        copyAllPtoFCopy(fObj, pObj, destMap, srcMap, pNumRskeysMap, pDateRskeysMap);
        destMap = null;
        srcMap = null;
        pNumRskeysMap = null;
        pDateRskeysMap = null;
    }

    /**
     * 属性列表地图存储< br / >
     * 
     * @param rskeys
     * @since 01-00
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static Map createListToMap(ArrayList<String> rskeys) {

        Map map = new HashMap();
        if (rskeys != null) {
            for (int i = 0; i < rskeys.size(); i++) {
                map.put(rskeys.get(i), i);
            }
        }
        return map;
    }

    /**
     * 
     * 进行复制< br / >
     * 
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static void copyAllPtoFCopy(Object dest, Object src, Map destMap, Map srcMap,
            Map pNumRskeysMap, Map pDateRskeysMap) {

        if (destMap == null || srcMap == null) {
            return;
        }

        // 复制原来的数值Map属性复制后的财产和同名的属性，保留
        if (!pNumRskeysMap.isEmpty()) {
            pNumRskeysMap.keySet().retainAll(destMap.keySet());
        }

        // 复制原来的日期Map属性复制后的财产和同名的属性，保留
        if (!pDateRskeysMap.isEmpty()) {
            pDateRskeysMap.keySet().retainAll(destMap.keySet());
        }

        // 复制原来拷贝目的地的财产和同名的属性，保留
        srcMap.keySet().retainAll(destMap.keySet());

        for (Iterator it = srcMap.keySet().iterator(); it.hasNext();) {
            // 复制原来的属性名取得
            String keyName = (String) it.next();
            // 属性名，比复制元拷贝目的地的取得财产
            PropertyDescriptor srcPd = (PropertyDescriptor) srcMap.get(keyName);
            PropertyDescriptor destPd = (PropertyDescriptor) destMap.get(keyName);
            try {
                // 复制原来的物业价值拷贝目的地的同名属性设定
                copyAllPtoFInnerCopy(dest, src, destPd, srcPd, pNumRskeysMap, pDateRskeysMap,
                        keyName);
            } catch (Exception e) {
                continue;
            }
        }
    }

    /**
     * 复制原来的物业价值拷贝目的地的同名属性设定< br / >
     * 
     * @exception Exception
     * @since 01-00
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static void copyAllPtoFInnerCopy(Object dest, Object src, PropertyDescriptor destPd,
            PropertyDescriptor srcPd, Map pNumRskeysMap, Map pDateRskeysMap, String keyName)
            throws Exception {

        Method srcReadMethod = srcPd.getReadMethod();
        if (srcReadMethod == null) {
            return;
        }
        Method destWriteMethod = destPd.getWriteMethod();
        if (destWriteMethod == null) {
            return;
        }

        Class destType = destPd.getPropertyType();
        Object srcValue = srcReadMethod.invoke(src, (Object[]) null);

        Object convertedValue = srcValue;
        if (srcValue == null || "".equals(srcValue)) {
            if (pNumRskeysMap.containsKey(keyName)) {
                destWriteMethod.invoke(dest, new Object[] { null });
            } else {
                if (destType.equals(Integer.class) || destType.equals(Short.class)
                        || destType.equals(BigDecimal.class) || destType.equals(Double.class)
                        || destType.equals(Float.class)) {
                    destWriteMethod.invoke(dest, new Object[] { null });
                }

            }
            if ("".equals(srcValue)) {
                convertedValue = destType.getConstructor(new Class[] { String.class }).newInstance(
                        new Object[] { srcValue });
                destWriteMethod.invoke(dest, new Object[] { convertedValue });
            }

        } else {
            if (pNumRskeysMap.containsKey(keyName)) {
                convertedValue = deleteComma(srcValue);

            } else if (pDateRskeysMap.containsKey(keyName)) {
                if (srcValue.toString().indexOf("/") > 0) {
                    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
                    String formatValue = sf.format(DateFormat.getDateInstance().parse(
                            srcValue.toString()));
                    convertedValue = formatValue.replaceAll("/", "");
                }
            } else {
                if (destType.equals(Integer.class) || destType.equals(Short.class)
                        || destType.equals(BigDecimal.class) || destType.equals(Double.class)
                        || destType.equals(Float.class)) {
                    convertedValue = deleteComma(srcValue);
                }
            }

            convertedValue = destType.getConstructor(new Class[] { String.class }).newInstance(
                    new Object[] { convertedValue });
            destWriteMethod.invoke(dest, new Object[] { convertedValue });
        }
    }

    /**
     * F层P层的属性设定F层P层的同名属性值设定< br / >
     * 
     * @since 01-00
     */
    @SuppressWarnings("rawtypes")
    public static void copyAllFtoP(Object pObj, Object fObj, String pPrefix) {
        if (pObj == null || fObj == null) {
            return;
        }
        Map destMap = createBeanMap(pObj);
        Map srcMap = createBeanMap(fObj, pPrefix);
        copyAllCommon(pObj, fObj, destMap, srcMap, null, null, true);
        destMap = null;
        srcMap = null;
    }

    /**
     * F层指定属性P层的属性设定F层指定属性P层的同名属性值设定< br / >
     * @since 01-00
     */
    @SuppressWarnings("rawtypes")
    public static void copySpecifiedFtoP(Object pObj, Object fObj, String[] propertyNames,
            String pPrefix) {
        if (pObj == null || fObj == null) {
            return;
        }
        Map destMap = createBeanMap(pObj);
        Map srcMap = createBeanMap(fObj, pPrefix);
        copyAllCommon(pObj, fObj, destMap, srcMap, propertyNames, null, true);
        destMap = null;
        srcMap = null;
    }

    /**
     * 
     * 属性列表，复制元属性处理，然后进行复制< br / >
     * 
     * @since 01-00
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static void copyAllCommon(Object dest, Object src, Map destMap, Map srcMap,
            String[] includeArray, String[] excludeArray, boolean convertFlg) {

        if (destMap == null || srcMap == null) {
            return;
        }
        if (includeArray != null && includeArray.length > 0) {
            srcMap.keySet().retainAll(Arrays.asList(includeArray));
        }
        if (excludeArray != null && excludeArray.length > 0) {
            srcMap.keySet().removeAll(Arrays.asList(excludeArray));
        }
        srcMap.keySet().retainAll(destMap.keySet());

        for (Iterator it = srcMap.keySet().iterator(); it.hasNext();) {
            String keyName = (String) it.next();
            PropertyDescriptor srcPd = (PropertyDescriptor) srcMap.get(keyName);
            PropertyDescriptor destPd = (PropertyDescriptor) destMap.get(keyName);
            try {
                innerCopy(dest, src, destPd, srcPd, convertFlg);
            } catch (Exception e) {
                continue;
            }
        }
        destMap = null;
        srcMap = null;
    }

    /**
     * @exception Exception
     * @since 01-00
     */
    @SuppressWarnings("rawtypes")
    private static void innerCopy(Object dest, Object src, PropertyDescriptor destPd,
            PropertyDescriptor srcPd, boolean convertFlg) throws Exception {

        Method srcReadMethod = srcPd.getReadMethod();
        if (srcReadMethod == null) {
            return;
        }
        Method destWriteMethod = destPd.getWriteMethod();
        if (destWriteMethod == null) {
            return;
        }
        Class srcType = srcPd.getPropertyType();
        Class destType = destPd.getPropertyType();
        Object srcValue = srcReadMethod.invoke(src, (Object[]) null);

        innerCopySingleValue(dest, destWriteMethod, srcType, destType, srcValue, convertFlg);
    }

    /**
     * @exception Exception
     * @since 01-00
     */
    @SuppressWarnings("rawtypes")
    private static void innerCopySingleValue(Object dest, Method destWriteMethod, Class srcType,
            Class destType, Object srcValue, boolean convertFlg) throws Exception {

        if (srcValue == null) {
            destWriteMethod.invoke(dest, new Object[] { null });
        }

        if (convertFlg) {
            if (destType.equals(String.class) && !srcType.equals(String.class)) {
                String convertedValue = null;
                if (srcType.equals(Integer.class) || srcType.equals(Short.class)
                        || srcType.equals(BigDecimal.class) || srcType.equals(Double.class)
                        || srcType.equals(Float.class)) {
                    convertedValue = srcValue.toString();
                } else if (srcType.equals(Date.class)) {
                    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
                    convertedValue = sf.format(srcValue);
                } else if (srcType.equals(Timestamp.class)) {
                    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                    convertedValue = sf.format(srcValue);
                } else if (srcType.equals(Time.class)) {
                    SimpleDateFormat sf = new SimpleDateFormat("HHmmss");
                    convertedValue = sf.format(srcValue);
                }
                destWriteMethod.invoke(dest, new Object[] { convertedValue });
            } else {
                destWriteMethod.invoke(dest, new Object[] { srcValue });
            }
        } else {
            if (destType.equals(srcType)) {
                destWriteMethod.invoke(dest, new Object[] { srcValue });
            }
        }
    }

    /**
     * @since 01-00
     */
    private static Object deleteComma(Object srcValue) {
        return srcValue.toString().replaceAll(",", "");
    }

    /**
     * @since 01-00
     */
    @SuppressWarnings("rawtypes")
    private static Map createBeanMap(Object obj) {
        return createBeanMap(obj, null);
    }

    /**
     * @since 01-00
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static Map createBeanMap(Object obj, String prefix) {

        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(obj.getClass());
        } catch (IntrospectionException e) {
            return null;
        }

        Map map = new HashMap();
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < pds.length; i++) {
            PropertyDescriptor pd = pds[i];
            if (pd.getName().equals("class")) {
                continue;
            }
            String name = pd.getName();

            if (prefix != null && !"".equals(prefix)) {
                name = prefix + name;
            }
            map.put(name, pd);
        }
        return map;
    }


    /**
     * @since 01-00
     */
    public static Object copyObject(Object old) {
        Class<? extends Object> oldcClass = old.getClass();
        Object copy = null;
        try {
            copy = oldcClass.newInstance();
            copyAll(copy, old);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return copy;
    }
}
