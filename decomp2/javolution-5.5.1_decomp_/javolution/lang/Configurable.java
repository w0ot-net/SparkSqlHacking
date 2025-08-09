package javolution.lang;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.Properties;
import javolution.context.LogContext;
import javolution.context.SecurityContext;
import javolution.text.TextFormat;
import javolution.util.FastTable;
import javolution.xml.XMLBinding;
import javolution.xml.XMLFormat;
import javolution.xml.XMLObjectReader;
import javolution.xml.stream.XMLStreamException;

public class Configurable {
   private Object _value;
   private final Object _default;
   private final Class _container;

   public Configurable(Object defaultValue) {
      if (defaultValue == null) {
         throw new IllegalArgumentException("Default value cannot be null");
      } else {
         this._default = defaultValue;
         this._value = defaultValue;
         this._container = findContainer();
      }
   }

   private static Class findContainer() {
      try {
         StackTraceElement[] stack = (new Throwable()).getStackTrace();
         String className = stack[2].getClassName();
         int sep = className.indexOf("$");
         if (sep >= 0) {
            className = className.substring(0, sep);
         }

         return Class.forName(className);
      } catch (Throwable error) {
         LogContext.error(error);
         return null;
      }
   }

   public Object get() {
      return this._value;
   }

   public Object getDefault() {
      return this._default;
   }

   public Class getContainer() {
      return this._container;
   }

   public String getName() {
      if (this._container == null) {
         return null;
      } else {
         try {
            Field[] fields = this._container.getDeclaredFields();

            for(int i = 0; i < fields.length; ++i) {
               Field field = fields[i];
               if (Modifier.isPublic(field.getModifiers()) && field.get((Object)null) == this) {
                  return this._container.getName() + '#' + field.getName();
               }
            }
         } catch (Throwable error) {
            LogContext.error(error);
         }

         return null;
      }
   }

   protected void notifyChange(Object oldValue, Object newValue) throws UnsupportedOperationException {
   }

   public String toString() {
      return String.valueOf(this._value);
   }

   public static Configurable getInstance(String name) {
      int sep = name.lastIndexOf(35);
      if (sep < 0) {
         return null;
      } else {
         String className = name.substring(0, sep);
         String fieldName = name.substring(sep + 1);
         Class cls = Reflection.getInstance().getClass(className);
         if (cls == null) {
            LogContext.warning((CharSequence)("Class " + className + " not found"));
            return null;
         } else {
            try {
               Configurable cfg = (Configurable)cls.getDeclaredField(fieldName).get((Object)null);
               if (cfg == null) {
                  LogContext.warning((CharSequence)("Configurable " + name + " not found"));
               }

               return cfg;
            } catch (Exception ex) {
               LogContext.error((Throwable)ex);
               return null;
            }
         }
      }
   }

   public static void configure(Configurable cfg, Object newValue) throws SecurityException {
      if (newValue == null) {
         throw new IllegalArgumentException("Default value cannot be null");
      } else {
         SecurityContext policy = SecurityContext.getCurrentSecurityContext();
         if (!policy.isConfigurable(cfg)) {
            throw new SecurityException("Configuration disallowed by SecurityContext");
         } else {
            T oldValue = (T)cfg._value;
            if (!newValue.equals(oldValue)) {
               LogContext.info((CharSequence)("Configurable " + cfg.getName() + " set to " + newValue));
               cfg._value = newValue;
               cfg.notifyChange(oldValue, newValue);
            }

         }
      }
   }

   public static void read(Properties properties) {
      Enumeration e = properties.keys();

      while(e.hasMoreElements()) {
         String name = (String)e.nextElement();
         String textValue = properties.getProperty(name);
         Configurable cfg = getInstance(name);
         if (cfg != null) {
            Class type = cfg.getDefault().getClass();
            TextFormat format = TextFormat.getInstance(type);
            if (!format.isParsingSupported()) {
               LogContext.error((CharSequence)("Cannot find suitable TextFormat to parse instances of " + type));
            } else {
               Object newValue = format.parse(toCsq(textValue));
               configure(cfg, newValue);
            }
         }
      }

   }

   public static void read(InputStream inputStream) {
      try {
         XMLObjectReader reader = XMLObjectReader.newInstance(inputStream);
         XMLBinding binding = new XMLBinding() {
            protected XMLFormat getFormat(Class forClass) throws XMLStreamException {
               return (XMLFormat)(Configurable.class.isAssignableFrom(forClass) ? new ConfigurableXMLFormat() : super.getFormat(forClass));
            }
         };
         binding.setAlias(Configurable.class, "Configurable");
         reader.setBinding(binding);
         reader.read("Configuration", FastTable.class);
      } catch (Exception ex) {
         LogContext.error((Throwable)ex);
      }

   }

   private static CharSequence toCsq(Object str) {
      return (CharSequence)str;
   }

   private static class ConfigurableXMLFormat extends XMLFormat {
      ConfigurableXMLFormat() {
         super((Class)null);
      }

      public Object newInstance(Class cls, XMLFormat.InputElement xml) throws XMLStreamException {
         return Configurable.getInstance(xml.getAttribute("name", ""));
      }

      public void write(Object c, XMLFormat.OutputElement xml) throws XMLStreamException {
         throw new UnsupportedOperationException();
      }

      public void read(XMLFormat.InputElement xml, Object c) throws XMLStreamException {
         Object value = xml.get("Value");
         if (value != null) {
            Configurable.configure((Configurable)c, value);
         }
      }
   }
}
