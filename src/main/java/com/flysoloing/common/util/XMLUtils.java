package com.flysoloing.common.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * XML工具类
 *
 * @author laitao
 * @since 2016-04-29 17:23:59
 */
public class XMLUtils {
    private static final Logger logger = LoggerFactory.getLogger(XMLUtils.class);

    public static String obj2Xml(Object reqVo) {
        XStream xstream = new XStream(new DomDriver("UTF-8"));
        xstream.processAnnotations(reqVo.getClass());
        String reqXml = wrapXmlDom(xstream.toXML(reqVo));

        if (reqXml != null) {
            reqXml = reqXml.trim();
        }

        return reqXml;
    }

    public static <T> T xml2Obj(final String dataXml, final Class<T> resVo) {
        T obj = null;
//        try {
        String packageXml = deWrapXmlDom(dataXml);

        logger.info("packageXml=" + packageXml);
        XStream xstream = new XStream(new DomDriver("UTF-8"));
        xstream.processAnnotations(resVo);
//        xstream.alias(ConstantUtil.XML_PACKAGE, resVo);
//        xstream.aliasAttribute(resVo ,ConstantUtil.XML_BODY, ConstantUtil.XML_RESPONSE);

        obj = (T) xstream.fromXML(packageXml);
//        } catch (Exception e) {
//            log.info("网关转换保险公司返回报文出现异常：",e);
//        }

        return obj;
    }

    public static String deWrapXmlDom(String dataXml) {
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(dataXml);
            Element root = doc.getRootElement();

            return  root.element("Package").asXML();
        } catch (DocumentException e) {
            logger.info("XmlUtil.deWrapXmlDom DocumentException", e);
        } catch (Exception e) {
            logger.info("XmlUtil.deWrapXmlDom DocumentException", e);
        }

        return null;
    }

    public static String wrapXmlDom (String xmlBody) {
        StringBuilder xmlBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(System.getProperty("line.separator"));
        xmlBuilder.append("<PackageList>").append(System.getProperty("line.separator"));
        xmlBuilder.append(xmlBody).append(System.getProperty("line.separator"));
        xmlBuilder.append("</PackageList>");

        return xmlBuilder.toString();
    }

    public static String toXml (Object obj, Class clazz) {
        XStream xstream = new XStream(new DomDriver("UTF-8"));

//        xstream.alias("request", obj.getClass());
//        xstream.aliasAttribute(clazz, "holder", "MyHolder");

        return xstream.toXML(obj);
    }

    public static String toCommonReqXml (Object obj) {
        XStream xstream = new XStream(new DomDriver("UTF-8"));
//        xstream.alias

        return xstream.toXML(obj);
    }

}
