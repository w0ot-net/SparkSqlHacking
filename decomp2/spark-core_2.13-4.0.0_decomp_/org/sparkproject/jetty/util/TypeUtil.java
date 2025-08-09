package org.sparkproject.jetty.util;

import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.module.Configuration;
import java.lang.module.ModuleReference;
import java.lang.module.ResolvedModule;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TypeUtil {
   private static final Logger LOG = LoggerFactory.getLogger(TypeUtil.class);
   public static final Class[] NO_ARGS = new Class[0];
   public static final int CR = 13;
   public static final int LF = 10;
   private static final HashMap name2Class = new HashMap();
   private static final HashMap class2Name;
   private static final HashMap class2Value;
   private static final MethodHandle[] LOCATION_METHODS;

   public static List asList(Object[] a) {
      return a == null ? Collections.emptyList() : Arrays.asList(a);
   }

   public static Class fromName(String name) {
      return (Class)name2Class.get(name);
   }

   public static String toName(Class type) {
      return (String)class2Name.get(type);
   }

   public static String toClassReference(Class clazz) {
      return toClassReference(clazz.getName());
   }

   public static String toClassReference(String className) {
      return StringUtil.replace(className, '.', '/').concat(".class");
   }

   public static Object valueOf(Class type, String value) {
      try {
         if (type.equals(String.class)) {
            return value;
         }

         Method m = (Method)class2Value.get(type);
         if (m != null) {
            return m.invoke((Object)null, value);
         }

         if (!type.equals(Character.TYPE) && !type.equals(Character.class)) {
            Constructor<?> c = type.getConstructor(String.class);
            return c.newInstance(value);
         }

         return value.charAt(0);
      } catch (IllegalAccessException | InstantiationException | NoSuchMethodException x) {
         LOG.trace("IGNORED", x);
      } catch (InvocationTargetException x) {
         if (x.getTargetException() instanceof Error) {
            throw (Error)x.getTargetException();
         }

         LOG.trace("IGNORED", x);
      }

      return null;
   }

   public static Object valueOf(String type, String value) {
      return valueOf(fromName(type), value);
   }

   public static int parseInt(String s, int offset, int length, int base) throws NumberFormatException {
      int value = 0;
      if (length < 0) {
         length = s.length() - offset;
      }

      for(int i = 0; i < length; ++i) {
         char c = s.charAt(offset + i);
         int digit = convertHexDigit((int)c);
         if (digit < 0 || digit >= base) {
            throw new NumberFormatException(s.substring(offset, offset + length));
         }

         value = value * base + digit;
      }

      return value;
   }

   public static int parseInt(byte[] b, int offset, int length, int base) throws NumberFormatException {
      int value = 0;
      if (length < 0) {
         length = b.length - offset;
      }

      for(int i = 0; i < length; ++i) {
         char c = (char)(255 & b[offset + i]);
         int digit = c - 48;
         if (digit < 0 || digit >= base || digit >= 10) {
            digit = 10 + c - 65;
            if (digit < 10 || digit >= base) {
               digit = 10 + c - 97;
            }
         }

         if (digit < 0 || digit >= base) {
            throw new NumberFormatException(new String(b, offset, length));
         }

         value = value * base + digit;
      }

      return value;
   }

   /** @deprecated */
   @Deprecated
   public static byte[] parseBytes(String s, int base) {
      byte[] bytes = new byte[s.length() / 2];

      for(int i = 0; i < s.length(); i += 2) {
         bytes[i / 2] = (byte)parseInt((String)s, i, 2, base);
      }

      return bytes;
   }

   public static String toString(byte[] bytes, int base) {
      StringBuilder buf = new StringBuilder();

      for(byte b : bytes) {
         int bi = 255 & b;
         int c = 48 + bi / base % base;
         if (c > 57) {
            c = 97 + (c - 48 - 10);
         }

         buf.append((char)c);
         c = 48 + bi % base;
         if (c > 57) {
            c = 97 + (c - 48 - 10);
         }

         buf.append((char)c);
      }

      return buf.toString();
   }

   public static byte convertHexDigit(byte c) {
      byte b = (byte)((c & 31) + (c >> 6) * 25 - 16);
      if (b >= 0 && b <= 15) {
         return b;
      } else {
         throw new NumberFormatException("!hex " + c);
      }
   }

   public static int convertHexDigit(char c) {
      int d = (c & 31) + (c >> 6) * 25 - 16;
      if (d >= 0 && d <= 15) {
         return d;
      } else {
         throw new NumberFormatException("!hex " + c);
      }
   }

   public static int convertHexDigit(int c) {
      int d = (c & 31) + (c >> 6) * 25 - 16;
      if (d >= 0 && d <= 15) {
         return d;
      } else {
         throw new NumberFormatException("!hex " + c);
      }
   }

   public static void toHex(byte b, Appendable buf) {
      try {
         int d = 15 & (240 & b) >> 4;
         buf.append((char)((d > 9 ? 55 : 48) + d));
         d = 15 & b;
         buf.append((char)((d > 9 ? 55 : 48) + d));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public static void toHex(int value, Appendable buf) throws IOException {
      int d = 15 & (-268435456 & value) >> 28;
      buf.append((char)((d > 9 ? 55 : 48) + d));
      d = 15 & (251658240 & value) >> 24;
      buf.append((char)((d > 9 ? 55 : 48) + d));
      d = 15 & (15728640 & value) >> 20;
      buf.append((char)((d > 9 ? 55 : 48) + d));
      d = 15 & (983040 & value) >> 16;
      buf.append((char)((d > 9 ? 55 : 48) + d));
      d = 15 & ('\uf000' & value) >> 12;
      buf.append((char)((d > 9 ? 55 : 48) + d));
      d = 15 & (3840 & value) >> 8;
      buf.append((char)((d > 9 ? 55 : 48) + d));
      d = 15 & (240 & value) >> 4;
      buf.append((char)((d > 9 ? 55 : 48) + d));
      d = 15 & value;
      buf.append((char)((d > 9 ? 55 : 48) + d));
      Integer.toString(0, 36);
   }

   public static void toHex(long value, Appendable buf) throws IOException {
      toHex((int)(value >> 32), buf);
      toHex((int)value, buf);
   }

   /** @deprecated */
   @Deprecated
   public static String toHexString(byte b) {
      return StringUtil.toHexString(b);
   }

   /** @deprecated */
   @Deprecated
   public static String toHexString(byte[] b) {
      return StringUtil.toHexString(b);
   }

   /** @deprecated */
   @Deprecated
   public static String toHexString(byte[] b, int offset, int length) {
      return StringUtil.toHexString(b, offset, length);
   }

   /** @deprecated */
   @Deprecated
   public static byte[] fromHexString(String s) {
      return StringUtil.fromHexString(s);
   }

   public static void dump(Class c) {
      System.err.println("Dump: " + String.valueOf(c));
      dump(c.getClassLoader());
   }

   public static void dump(ClassLoader cl) {
      System.err.println("Dump Loaders:");

      while(cl != null) {
         System.err.println("  loader " + String.valueOf(cl));
         cl = cl.getParent();
      }

   }

   public static boolean isTrue(Object o) {
      if (o == null) {
         return false;
      } else {
         return o instanceof Boolean ? (Boolean)o : Boolean.parseBoolean(o.toString());
      }
   }

   public static boolean isFalse(Object o) {
      if (o == null) {
         return false;
      } else if (o instanceof Boolean) {
         return !(Boolean)o;
      } else {
         return "false".equalsIgnoreCase(o.toString());
      }
   }

   public static URI getLocationOfClass(Class clazz) {
      for(MethodHandle locationMethod : LOCATION_METHODS) {
         try {
            URI location = locationMethod.invoke(clazz);
            if (location != null) {
               return location;
            }
         } catch (Throwable cause) {
            cause.printStackTrace(System.err);
         }
      }

      return null;
   }

   public static URI getSystemClassLoaderLocation(Class clazz) {
      return getClassLoaderLocation(clazz, ClassLoader.getSystemClassLoader());
   }

   public static URI getClassLoaderLocation(Class clazz) {
      return getClassLoaderLocation(clazz, clazz.getClassLoader());
   }

   public static URI getClassLoaderLocation(Class clazz, ClassLoader loader) {
      if (loader == null) {
         return null;
      } else {
         try {
            String resourceName = toClassReference(clazz);
            if (loader != null) {
               URL url = loader.getResource(resourceName);
               if (url != null) {
                  URI uri = url.toURI();
                  String uriStr = uri.toASCIIString();
                  if (uriStr.startsWith("jar:file:")) {
                     uriStr = uriStr.substring(4);
                     int idx = uriStr.indexOf("!/");
                     if (idx > 0) {
                        return URI.create(uriStr.substring(0, idx));
                     }
                  }

                  return uri;
               }
            }
         } catch (URISyntaxException var7) {
         }

         return null;
      }
   }

   public static URI getCodeSourceLocation(Class clazz) {
      try {
         ProtectionDomain domain = clazz.getProtectionDomain();
         if (domain != null) {
            CodeSource source = domain.getCodeSource();
            if (source != null) {
               URL location = source.getLocation();
               if (location != null) {
                  return location.toURI();
               }
            }
         }
      } catch (URISyntaxException var4) {
      }

      return null;
   }

   public static URI getModuleLocation(Class clazz) {
      Module module = clazz.getModule();
      if (module == null) {
         return null;
      } else {
         ModuleLayer layer = module.getLayer();
         if (layer == null) {
            return null;
         } else {
            Configuration configuration = layer.configuration();
            if (configuration == null) {
               return null;
            } else {
               Optional<ResolvedModule> resolvedModule = configuration.findModule(module.getName());
               if (resolvedModule != null && resolvedModule.isPresent()) {
                  ModuleReference moduleReference = ((ResolvedModule)resolvedModule.get()).reference();
                  if (moduleReference == null) {
                     return null;
                  } else {
                     Optional<URI> location = moduleReference.location();
                     return location.isPresent() ? (URI)location.get() : null;
                  }
               } else {
                  return null;
               }
            }
         }
      }
   }

   public static Iterator concat(final Iterator i1, final Iterator i2) {
      return new Iterator() {
         public boolean hasNext() {
            return i1.hasNext() || i2.hasNext();
         }

         public Object next() {
            return i1.hasNext() ? i1.next() : i2.next();
         }
      };
   }

   private static Stream mapToService(ServiceLoader.Provider provider) {
      try {
         return Stream.of(provider.get());
      } catch (ServiceConfigurationError error) {
         LOG.warn("Service Provider failed to load", error);
         return Stream.empty();
      }
   }

   public static Stream serviceStream(ServiceLoader serviceLoader) {
      return serviceProviderStream(serviceLoader).flatMap(TypeUtil::mapToService);
   }

   public static Stream serviceProviderStream(ServiceLoader serviceLoader) {
      return StreamSupport.stream(new ServiceLoaderSpliterator(serviceLoader), false);
   }

   static {
      name2Class.put("boolean", Boolean.TYPE);
      name2Class.put("byte", Byte.TYPE);
      name2Class.put("char", Character.TYPE);
      name2Class.put("double", Double.TYPE);
      name2Class.put("float", Float.TYPE);
      name2Class.put("int", Integer.TYPE);
      name2Class.put("long", Long.TYPE);
      name2Class.put("short", Short.TYPE);
      name2Class.put("void", Void.TYPE);
      name2Class.put("java.lang.Boolean.TYPE", Boolean.TYPE);
      name2Class.put("java.lang.Byte.TYPE", Byte.TYPE);
      name2Class.put("java.lang.Character.TYPE", Character.TYPE);
      name2Class.put("java.lang.Double.TYPE", Double.TYPE);
      name2Class.put("java.lang.Float.TYPE", Float.TYPE);
      name2Class.put("java.lang.Integer.TYPE", Integer.TYPE);
      name2Class.put("java.lang.Long.TYPE", Long.TYPE);
      name2Class.put("java.lang.Short.TYPE", Short.TYPE);
      name2Class.put("java.lang.Void.TYPE", Void.TYPE);
      name2Class.put("java.lang.Boolean", Boolean.class);
      name2Class.put("java.lang.Byte", Byte.class);
      name2Class.put("java.lang.Character", Character.class);
      name2Class.put("java.lang.Double", Double.class);
      name2Class.put("java.lang.Float", Float.class);
      name2Class.put("java.lang.Integer", Integer.class);
      name2Class.put("java.lang.Long", Long.class);
      name2Class.put("java.lang.Short", Short.class);
      name2Class.put("Boolean", Boolean.class);
      name2Class.put("Byte", Byte.class);
      name2Class.put("Character", Character.class);
      name2Class.put("Double", Double.class);
      name2Class.put("Float", Float.class);
      name2Class.put("Integer", Integer.class);
      name2Class.put("Long", Long.class);
      name2Class.put("Short", Short.class);
      name2Class.put((Object)null, Void.TYPE);
      name2Class.put("string", String.class);
      name2Class.put("String", String.class);
      name2Class.put("java.lang.String", String.class);
      class2Name = new HashMap();
      class2Name.put(Boolean.TYPE, "boolean");
      class2Name.put(Byte.TYPE, "byte");
      class2Name.put(Character.TYPE, "char");
      class2Name.put(Double.TYPE, "double");
      class2Name.put(Float.TYPE, "float");
      class2Name.put(Integer.TYPE, "int");
      class2Name.put(Long.TYPE, "long");
      class2Name.put(Short.TYPE, "short");
      class2Name.put(Void.TYPE, "void");
      class2Name.put(Boolean.class, "java.lang.Boolean");
      class2Name.put(Byte.class, "java.lang.Byte");
      class2Name.put(Character.class, "java.lang.Character");
      class2Name.put(Double.class, "java.lang.Double");
      class2Name.put(Float.class, "java.lang.Float");
      class2Name.put(Integer.class, "java.lang.Integer");
      class2Name.put(Long.class, "java.lang.Long");
      class2Name.put(Short.class, "java.lang.Short");
      class2Name.put((Object)null, "void");
      class2Name.put(String.class, "java.lang.String");
      class2Value = new HashMap();

      try {
         Class<?>[] s = new Class[]{String.class};
         class2Value.put(Boolean.TYPE, Boolean.class.getMethod("valueOf", s));
         class2Value.put(Byte.TYPE, Byte.class.getMethod("valueOf", s));
         class2Value.put(Double.TYPE, Double.class.getMethod("valueOf", s));
         class2Value.put(Float.TYPE, Float.class.getMethod("valueOf", s));
         class2Value.put(Integer.TYPE, Integer.class.getMethod("valueOf", s));
         class2Value.put(Long.TYPE, Long.class.getMethod("valueOf", s));
         class2Value.put(Short.TYPE, Short.class.getMethod("valueOf", s));
         class2Value.put(Boolean.class, Boolean.class.getMethod("valueOf", s));
         class2Value.put(Byte.class, Byte.class.getMethod("valueOf", s));
         class2Value.put(Double.class, Double.class.getMethod("valueOf", s));
         class2Value.put(Float.class, Float.class.getMethod("valueOf", s));
         class2Value.put(Integer.class, Integer.class.getMethod("valueOf", s));
         class2Value.put(Long.class, Long.class.getMethod("valueOf", s));
         class2Value.put(Short.class, Short.class.getMethod("valueOf", s));
      } catch (Exception e) {
         throw new Error(e);
      }

      List<MethodHandle> locationMethods = new ArrayList();
      MethodHandles.Lookup lookup = MethodHandles.lookup();
      MethodType type = MethodType.methodType(URI.class, Class.class);

      try {
         locationMethods.add(lookup.findStatic(TypeUtil.class, "getCodeSourceLocation", type));
         locationMethods.add(lookup.findStatic(TypeUtil.class, "getModuleLocation", type));
         locationMethods.add(lookup.findStatic(TypeUtil.class, "getClassLoaderLocation", type));
         locationMethods.add(lookup.findStatic(TypeUtil.class, "getSystemClassLoaderLocation", type));
         LOCATION_METHODS = (MethodHandle[])locationMethods.toArray(new MethodHandle[0]);
      } catch (Exception e) {
         throw new RuntimeException("Unable to establish Location Lookup Handles", e);
      }
   }
}
