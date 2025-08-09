package jakarta.xml.bind;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public final class JAXB {
   private static volatile WeakReference cache;

   private JAXB() {
   }

   private static JAXBContext getContext(Class type) throws JAXBException {
      WeakReference<Cache> c = cache;
      if (c != null) {
         Cache d = (Cache)c.get();
         if (d != null && d.type == type) {
            return d.context;
         }
      }

      Cache d = new Cache(type);
      cache = new WeakReference(d);
      return d.context;
   }

   public static Object unmarshal(File xml, Class type) {
      try {
         JAXBElement<T> item = getContext(type).createUnmarshaller().unmarshal((Source)(new StreamSource(xml)), type);
         return item.getValue();
      } catch (JAXBException e) {
         throw new DataBindingException(e);
      }
   }

   public static Object unmarshal(URL xml, Class type) {
      try {
         JAXBElement<T> item = getContext(type).createUnmarshaller().unmarshal(toSource(xml), type);
         return item.getValue();
      } catch (IOException | JAXBException e) {
         throw new DataBindingException(e);
      }
   }

   public static Object unmarshal(URI xml, Class type) {
      try {
         JAXBElement<T> item = getContext(type).createUnmarshaller().unmarshal(toSource(xml), type);
         return item.getValue();
      } catch (IOException | JAXBException e) {
         throw new DataBindingException(e);
      }
   }

   public static Object unmarshal(String xml, Class type) {
      try {
         JAXBElement<T> item = getContext(type).createUnmarshaller().unmarshal(toSource(xml), type);
         return item.getValue();
      } catch (IOException | JAXBException e) {
         throw new DataBindingException(e);
      }
   }

   public static Object unmarshal(InputStream xml, Class type) {
      try {
         JAXBElement<T> item = getContext(type).createUnmarshaller().unmarshal(toSource(xml), type);
         return item.getValue();
      } catch (IOException | JAXBException e) {
         throw new DataBindingException(e);
      }
   }

   public static Object unmarshal(Reader xml, Class type) {
      try {
         JAXBElement<T> item = getContext(type).createUnmarshaller().unmarshal(toSource(xml), type);
         return item.getValue();
      } catch (IOException | JAXBException e) {
         throw new DataBindingException(e);
      }
   }

   public static Object unmarshal(Source xml, Class type) {
      try {
         JAXBElement<T> item = getContext(type).createUnmarshaller().unmarshal(toSource(xml), type);
         return item.getValue();
      } catch (IOException | JAXBException e) {
         throw new DataBindingException(e);
      }
   }

   private static Source toSource(Object xml) throws IOException {
      if (xml == null) {
         throw new IllegalArgumentException("no XML is given");
      } else {
         if (xml instanceof String) {
            try {
               xml = new URI((String)xml);
            } catch (URISyntaxException var2) {
               xml = new File((String)xml);
            }
         }

         if (xml instanceof File) {
            File file = (File)xml;
            return new StreamSource(file);
         } else {
            if (xml instanceof URI) {
               URI uri = (URI)xml;
               xml = uri.toURL();
            }

            if (xml instanceof URL) {
               URL url = (URL)xml;
               return new StreamSource(url.toExternalForm());
            } else if (xml instanceof InputStream) {
               InputStream in = (InputStream)xml;
               return new StreamSource(in);
            } else if (xml instanceof Reader) {
               Reader r = (Reader)xml;
               return new StreamSource(r);
            } else if (xml instanceof Source) {
               return (Source)xml;
            } else {
               throw new IllegalArgumentException("I don't understand how to handle " + String.valueOf(xml.getClass()));
            }
         }
      }
   }

   public static void marshal(Object jaxbObject, File xml) {
      _marshal(jaxbObject, xml);
   }

   public static void marshal(Object jaxbObject, URL xml) {
      _marshal(jaxbObject, xml);
   }

   public static void marshal(Object jaxbObject, URI xml) {
      _marshal(jaxbObject, xml);
   }

   public static void marshal(Object jaxbObject, String xml) {
      _marshal(jaxbObject, xml);
   }

   public static void marshal(Object jaxbObject, OutputStream xml) {
      _marshal(jaxbObject, xml);
   }

   public static void marshal(Object jaxbObject, Writer xml) {
      _marshal(jaxbObject, xml);
   }

   public static void marshal(Object jaxbObject, Result xml) {
      _marshal(jaxbObject, xml);
   }

   private static void _marshal(Object jaxbObject, Object xml) {
      try {
         JAXBContext context;
         if (jaxbObject instanceof JAXBElement) {
            context = getContext(((JAXBElement)jaxbObject).getDeclaredType());
         } else {
            Class<?> clazz = jaxbObject.getClass();
            XmlRootElement r = (XmlRootElement)clazz.getAnnotation(XmlRootElement.class);
            context = getContext(clazz);
            if (r == null) {
               jaxbObject = new JAXBElement(new QName(inferName(clazz)), clazz, jaxbObject);
            }
         }

         Marshaller m = context.createMarshaller();
         m.setProperty("jaxb.formatted.output", true);
         m.marshal(jaxbObject, toResult(xml));
      } catch (IOException | JAXBException e) {
         throw new DataBindingException(e);
      }
   }

   private static String inferName(Class clazz) {
      String simpleName = clazz.getSimpleName();
      if (simpleName.isEmpty()) {
         return simpleName;
      } else if (simpleName.length() > 1 && Character.isUpperCase(simpleName.charAt(1)) && Character.isUpperCase(simpleName.charAt(0))) {
         return simpleName;
      } else {
         char[] chars = simpleName.toCharArray();
         chars[0] = Character.toLowerCase(chars[0]);
         return new String(chars);
      }
   }

   private static Result toResult(Object xml) throws IOException {
      if (xml == null) {
         throw new IllegalArgumentException("no XML is given");
      } else {
         if (xml instanceof String) {
            try {
               xml = new URI((String)xml);
            } catch (URISyntaxException var3) {
               xml = new File((String)xml);
            }
         }

         if (xml instanceof File) {
            File file = (File)xml;
            return new StreamResult(file);
         } else {
            if (xml instanceof URI) {
               URI uri = (URI)xml;
               xml = uri.toURL();
            }

            if (xml instanceof URL) {
               URL url = (URL)xml;
               URLConnection con = url.openConnection();
               con.setDoOutput(true);
               con.setDoInput(false);
               con.connect();
               return new StreamResult(con.getOutputStream());
            } else if (xml instanceof OutputStream) {
               OutputStream os = (OutputStream)xml;
               return new StreamResult(os);
            } else if (xml instanceof Writer) {
               Writer w = (Writer)xml;
               return new StreamResult(w);
            } else if (xml instanceof Result) {
               return (Result)xml;
            } else {
               throw new IllegalArgumentException("I don't understand how to handle " + String.valueOf(xml.getClass()));
            }
         }
      }
   }

   private static final class Cache {
      final Class type;
      final JAXBContext context;

      public Cache(Class type) throws JAXBException {
         this.type = type;
         this.context = JAXBContext.newInstance(type);
      }
   }
}
