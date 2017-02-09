package com.sicnu.liaoshijie.labEqmMS.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

/**
 * @author guokan
 *
 */
public final class ZipUtils {

    /**
     * 添加字段注释.
     */
    private static final Logger LOG = Logger.getLogger(ZipUtils.class);

    /**
     * 构造函数.
     * 
     */
    private ZipUtils() {
        throw new RuntimeException("this is a util class,can not instance");
    }

    /**
     * 
     * @param data
     *            D
     * @param name
     *            d
     * @return byte[]
     */
    public static byte[] compress(ByteArrayInputStream data, String name) {
        ByteArrayOutputStream out = null;
        ZipOutputStream outZip = null;
        try {
            out = new ByteArrayOutputStream();
            outZip = new ZipOutputStream(out);
            outZip.putNextEntry(new ZipEntry(name));
            int b = -1;
            while ((b = data.read()) != -1) {
                outZip.write(b);
            }
            outZip.closeEntry();
            return out.toByteArray();
        } catch (IOException e) {
            if (LOG.isDebugEnabled()) {
                e.printStackTrace();
            }
            LOG.error("压缩出错", e);
        } finally {
            if (null != data) {
                try {
                    data.close();
                } catch (IOException e) {
                    if (LOG.isDebugEnabled()) {
                        e.printStackTrace();
                    }
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    if (LOG.isDebugEnabled()) {
                        e.printStackTrace();
                    }
                }
            }
            if (null != outZip) {
                try {
                    outZip.close();
                } catch (IOException e) {
                    if (LOG.isDebugEnabled()) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

}
