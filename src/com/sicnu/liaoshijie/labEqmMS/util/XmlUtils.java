package com.sicnu.liaoshijie.labEqmMS.util;

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;

/**
 * @author guokan
 *
 */
public final class XmlUtils {

    /**
     * 添加字段注释.
     */
    private static final Logger LOG = Logger.getLogger(XmlUtils.class);

    /**
     * 构造函数.
     * 
     */
    private XmlUtils() {
        throw new RuntimeException("this is a util class ,can not instance");
    }

    /**
     * TODO 添加方法注释.
     * 
     * @param clz
     *            a
     * @param obj
     *            a
     * @exception Exception
     *                e
     * @return ByteArrayOutputStream
     */
    public static ByteArrayOutputStream object2Xml(Class<?> clz, Object obj) throws Exception {
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            JAXBContext context = JAXBContext.newInstance(clz);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(obj, out);
        } catch (JAXBException e) {
            if (LOG.isDebugEnabled()) {
                e.printStackTrace();
            }
            LOG.error("对象转xml出错", e);
            throw new JAXBException("xml转对象出错");
        }
        return out;
    }
}
