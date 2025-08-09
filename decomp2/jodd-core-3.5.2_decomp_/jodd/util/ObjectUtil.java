package jodd.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import jodd.io.FastByteArrayOutputStream;
import jodd.io.StreamUtil;

public class ObjectUtil {
   public static boolean equals(Object obj1, Object obj2) {
      return obj1 != null ? obj1.equals(obj2) : obj2 == null;
   }

   public static boolean equalsEx(Object obj1, Object obj2) {
      if (obj1 == null) {
         return obj2 == null;
      } else if (obj2 == null) {
         return false;
      } else if (obj1.getClass().isArray()) {
         return !obj2.getClass().isArray() ? false : Arrays.equals(obj1, obj2);
      } else {
         return obj1.equals(obj2);
      }
   }

   public static boolean equalsType(Object object, Object thiz) {
      return object != null && object.getClass().equals(thiz.getClass());
   }

   public static Object clone(Object source) throws CloneNotSupportedException {
      if (source == null) {
         return null;
      } else {
         try {
            return ReflectUtil.invokeDeclared(source, "clone", new Class[0], new Object[0]);
         } catch (Exception ex) {
            throw new CloneNotSupportedException("Can't clone() the object: " + ex.getMessage());
         }
      }
   }

   public static Object cloneViaSerialization(Serializable obj) throws IOException, ClassNotFoundException {
      FastByteArrayOutputStream bos = new FastByteArrayOutputStream();
      ObjectOutputStream out = null;
      ObjectInputStream in = null;
      Object objCopy = null;

      try {
         out = new ObjectOutputStream(bos);
         out.writeObject(obj);
         out.flush();
         byte[] bytes = bos.toByteArray();
         in = new ObjectInputStream(new ByteArrayInputStream(bytes));
         objCopy = in.readObject();
      } finally {
         StreamUtil.close((OutputStream)out);
         StreamUtil.close((InputStream)in);
      }

      return objCopy;
   }

   public static void writeObject(String dest, Object object) throws IOException {
      writeObject(new File(dest), object);
   }

   public static void writeObject(File dest, Object object) throws IOException {
      FileOutputStream fos = null;
      BufferedOutputStream bos = null;
      ObjectOutputStream oos = null;

      try {
         fos = new FileOutputStream(dest);
         bos = new BufferedOutputStream(fos);
         oos = new ObjectOutputStream(bos);
         oos.writeObject(object);
      } finally {
         StreamUtil.close((OutputStream)oos);
         StreamUtil.close((OutputStream)bos);
         StreamUtil.close((OutputStream)fos);
      }

   }

   public static Object readObject(String source) throws IOException, ClassNotFoundException {
      return readObject(new File(source));
   }

   public static Object readObject(File source) throws IOException, ClassNotFoundException {
      Object result = null;
      FileInputStream fis = null;
      BufferedInputStream bis = null;
      ObjectInputStream ois = null;

      try {
         fis = new FileInputStream(source);
         bis = new BufferedInputStream(fis);
         ois = new ObjectInputStream(bis);
         result = ois.readObject();
      } finally {
         StreamUtil.close((InputStream)ois);
         StreamUtil.close((InputStream)bis);
         StreamUtil.close((InputStream)fis);
      }

      return result;
   }

   public static byte[] objectToByteArray(Object obj) throws IOException {
      FastByteArrayOutputStream bos = new FastByteArrayOutputStream();
      ObjectOutputStream oos = null;

      try {
         oos = new ObjectOutputStream(bos);
         oos.writeObject(obj);
      } finally {
         StreamUtil.close((OutputStream)oos);
      }

      return bos.toByteArray();
   }

   public static Object byteArrayToObject(byte[] data) throws IOException, ClassNotFoundException {
      Object retObj = null;
      ByteArrayInputStream bais = new ByteArrayInputStream(data);
      ObjectInputStream ois = null;

      try {
         ois = new ObjectInputStream(bais);
         retObj = ois.readObject();
      } finally {
         StreamUtil.close((InputStream)ois);
      }

      return retObj;
   }

   public static int length(Object obj) {
      if (obj == null) {
         return 0;
      } else if (obj instanceof String) {
         return ((String)obj).length();
      } else if (obj instanceof Collection) {
         return ((Collection)obj).size();
      } else if (obj instanceof Map) {
         return ((Map)obj).size();
      } else if (obj instanceof Iterator) {
         Iterator iter = (Iterator)obj;
         int count = 0;

         while(iter.hasNext()) {
            ++count;
            iter.next();
         }

         return count;
      } else if (!(obj instanceof Enumeration)) {
         return obj.getClass().isArray() ? Array.getLength(obj) : -1;
      } else {
         Enumeration enumeration = (Enumeration)obj;
         int count = 0;

         while(enumeration.hasMoreElements()) {
            ++count;
            enumeration.nextElement();
         }

         return count;
      }
   }

   public static boolean containsElement(Object obj, Object element) {
      if (obj == null) {
         return false;
      } else if (obj instanceof String) {
         return element == null ? false : ((String)obj).contains(element.toString());
      } else if (obj instanceof Collection) {
         return ((Collection)obj).contains(element);
      } else if (obj instanceof Map) {
         return ((Map)obj).values().contains(element);
      } else if (obj instanceof Iterator) {
         Iterator iter = (Iterator)obj;

         while(iter.hasNext()) {
            Object o = iter.next();
            if (equals(o, element)) {
               return true;
            }
         }

         return false;
      } else if (obj instanceof Enumeration) {
         Enumeration enumeration = (Enumeration)obj;

         while(enumeration.hasMoreElements()) {
            Object o = enumeration.nextElement();
            if (equals(o, element)) {
               return true;
            }
         }

         return false;
      } else {
         if (obj.getClass().isArray()) {
            int len = Array.getLength(obj);

            for(int i = 0; i < len; ++i) {
               Object o = Array.get(obj, i);
               if (equals(o, element)) {
                  return true;
               }
            }
         }

         return false;
      }
   }
}
