package jodd.util;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import jodd.io.StreamUtil;

public class ObjectXmlUtil {
   public static void writeObjectAsXml(String dest, Object object) throws IOException {
      writeObjectAsXml(new File(dest), object);
   }

   public static void writeObjectAsXml(File dest, Object object) throws IOException {
      FileOutputStream fos = null;
      XMLEncoder xmlenc = null;

      try {
         fos = new FileOutputStream(dest);
         xmlenc = new XMLEncoder(new BufferedOutputStream(fos));
         xmlenc.writeObject(object);
      } finally {
         StreamUtil.close((OutputStream)fos);
         if (xmlenc != null) {
            xmlenc.close();
         }

      }

   }

   public static Object readObjectFromXml(File source) throws IOException {
      Object result = null;
      FileInputStream fis = null;
      XMLDecoder xmldec = null;

      try {
         fis = new FileInputStream(source);
         xmldec = new XMLDecoder(new BufferedInputStream(fis));
         result = xmldec.readObject();
      } finally {
         StreamUtil.close((InputStream)fis);
         if (xmldec != null) {
            xmldec.close();
         }

      }

      return result;
   }

   public static Object readObjectFromXml(String source) throws IOException {
      return readObjectFromXml(new File(source));
   }
}
