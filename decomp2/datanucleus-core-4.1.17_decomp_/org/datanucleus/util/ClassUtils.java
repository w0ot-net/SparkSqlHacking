package org.datanucleus.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.datanucleus.ClassConstants;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;

public class ClassUtils {
   protected static final Map constructorsCache = new SoftValueMap();

   public static Object newInstance(Class type, Class[] parameterTypes, Object[] parameters) {
      try {
         StringBuilder name = new StringBuilder("" + type.getName());
         if (parameterTypes != null) {
            for(int i = 0; i < parameterTypes.length; ++i) {
               name.append("-").append(parameterTypes[i].getName());
            }
         }

         Constructor ctor = (Constructor)constructorsCache.get(name.toString());
         if (ctor == null) {
            ctor = type.getConstructor(parameterTypes);
            constructorsCache.put(name.toString(), ctor);
         }

         Object obj = ctor.newInstance(parameters);
         return obj;
      } catch (NoSuchMethodException e) {
         throw (new NucleusException(Localiser.msg("030004", type.getName(), Arrays.asList(parameterTypes).toString() + " " + Arrays.asList(type.getConstructors()).toString()), new Exception[]{e})).setFatal();
      } catch (IllegalAccessException e) {
         throw (new NucleusException(Localiser.msg("030005", type.getName()), new Exception[]{e})).setFatal();
      } catch (InstantiationException e) {
         throw (new NucleusException(Localiser.msg("030006", type.getName()), new Exception[]{e})).setFatal();
      } catch (InvocationTargetException e) {
         Throwable t = e.getTargetException();
         if (t instanceof RuntimeException) {
            throw (RuntimeException)t;
         } else if (t instanceof Error) {
            throw (Error)t;
         } else {
            throw (new NucleusException(Localiser.msg("030007", type.getName(), t))).setFatal();
         }
      }
   }

   public static Constructor getConstructorWithArguments(Class cls, Class[] argTypes) {
      try {
         Constructor[] constructors = cls.getConstructors();
         if (constructors != null) {
            for(int i = 0; i < constructors.length; ++i) {
               Class[] ctrParams = constructors[i].getParameterTypes();
               boolean ctrIsValid = true;
               if (ctrParams != null && ctrParams.length == argTypes.length) {
                  for(int j = 0; j < ctrParams.length; ++j) {
                     Class primType = getPrimitiveTypeForType(argTypes[j]);
                     if (argTypes[j] == null && ctrParams[j].isPrimitive()) {
                        ctrIsValid = false;
                        break;
                     }

                     if (argTypes[j] != null && !ctrParams[j].isAssignableFrom(argTypes[j]) && (primType == null || ctrParams[j] != primType)) {
                        ctrIsValid = false;
                        break;
                     }
                  }
               } else {
                  ctrIsValid = false;
               }

               if (ctrIsValid) {
                  return constructors[i];
               }
            }
         }
      } catch (SecurityException var8) {
      }

      return null;
   }

   public static Method getMethodWithArgument(Class cls, String methodName, Class argType) {
      Method m = getMethodForClass(cls, methodName, new Class[]{argType});
      if (m == null) {
         Class primitive = getPrimitiveTypeForType(argType);
         if (primitive != null) {
            m = getMethodForClass(cls, methodName, new Class[]{primitive});
         }
      }

      return m;
   }

   public static Method getMethodForClass(Class cls, String methodName, Class[] argtypes) {
      try {
         return cls.getDeclaredMethod(methodName, argtypes);
      } catch (NoSuchMethodException var4) {
         if (cls.getSuperclass() != null) {
            return getMethodForClass(cls.getSuperclass(), methodName, argtypes);
         }
      } catch (Exception var5) {
      }

      return null;
   }

   public static String getClassnameForFilename(String filename, String rootfilename) {
      if (filename == null) {
         return null;
      } else {
         String classname = filename;
         if (rootfilename != null) {
            classname = filename.substring(rootfilename.length());
         }

         classname = classname.substring(0, classname.length() - 6);
         char file_separator = File.separatorChar;
         if (classname.indexOf(file_separator) == 0) {
            classname = classname.substring(1);
         }

         classname = classname.replace(file_separator, '.');
         return classname;
      }
   }

   public static Collection getClassFilesForDirectory(File dir, boolean normal_classes, boolean inner_classes) {
      if (dir == null) {
         return null;
      } else {
         Collection classes = new HashSet();
         File[] files = dir.listFiles();
         if (files != null) {
            for(int i = 0; i < files.length; ++i) {
               if (files[i].isFile()) {
                  if (files[i].getName().endsWith(".class")) {
                     boolean is_inner_class = isInnerClass(files[i].getName());
                     if (normal_classes && !is_inner_class || inner_classes && is_inner_class) {
                        classes.add(files[i]);
                     }
                  }
               } else {
                  Collection child_classes = getClassFilesForDirectory(files[i], normal_classes, inner_classes);
                  if (child_classes != null && child_classes.size() > 0) {
                     classes.addAll(child_classes);
                  }
               }
            }
         }

         return classes;
      }
   }

   public static Collection getFilesForDirectory(File dir) {
      if (dir == null) {
         return null;
      } else {
         Collection files = new HashSet();
         File[] dirFiles = dir.listFiles();
         if (dirFiles != null) {
            for(int i = 0; i < dirFiles.length; ++i) {
               if (dirFiles[i].isFile()) {
                  files.add(dirFiles[i]);
               } else {
                  Collection childFiles = getFilesForDirectory(dirFiles[i]);
                  if (childFiles != null && childFiles.size() > 0) {
                     files.addAll(childFiles);
                  }
               }
            }
         }

         return files;
      }
   }

   public static String[] getClassNamesForJarFile(String jarFileName) {
      try {
         JarFile jar = new JarFile(jarFileName);
         return getClassNamesForJarFile(jar);
      } catch (IOException ioe) {
         NucleusLogger.GENERAL.warn("Error opening the jar file " + jarFileName + " : " + ioe.getMessage());
         return null;
      }
   }

   public static String[] getClassNamesForJarFile(URL jarFileURL) {
      File jarFile = new File(jarFileURL.getFile());

      try {
         JarFile jar = new JarFile(jarFile);
         return getClassNamesForJarFile(jar);
      } catch (IOException ioe) {
         NucleusLogger.GENERAL.warn("Error opening the jar file " + jarFileURL.getFile() + " : " + ioe.getMessage());
         return null;
      }
   }

   public static String[] getClassNamesForJarFile(URI jarFileURI) {
      try {
         return getClassNamesForJarFile(jarFileURI.toURL());
      } catch (MalformedURLException mue) {
         throw new NucleusException("Error opening the jar file " + jarFileURI, mue);
      }
   }

   private static String[] getClassNamesForJarFile(JarFile jar) {
      Enumeration jarEntries = jar.entries();
      Set<String> classes = new HashSet();

      while(jarEntries.hasMoreElements()) {
         String entry = ((JarEntry)jarEntries.nextElement()).getName();
         if (entry.endsWith(".class") && !isInnerClass(entry)) {
            String className = entry.substring(0, entry.length() - 6);
            className = className.replace(File.separatorChar, '.');
            classes.add(className);
         }
      }

      return (String[])classes.toArray(new String[classes.size()]);
   }

   public static String[] getPackageJdoFilesForJarFile(String jarFileName) {
      try {
         JarFile jar = new JarFile(jarFileName);
         return getFileNamesWithSuffixForJarFile(jar, "package.jdo");
      } catch (IOException ioe) {
         NucleusLogger.GENERAL.warn("Error opening the jar file " + jarFileName + " : " + ioe.getMessage());
         return null;
      }
   }

   public static String[] getPackageJdoFilesForJarFile(URL jarFileURL) {
      File jarFile = new File(jarFileURL.getFile());

      try {
         JarFile jar = new JarFile(jarFile);
         return getFileNamesWithSuffixForJarFile(jar, "package.jdo");
      } catch (IOException ioe) {
         NucleusLogger.GENERAL.warn("Error opening the jar file " + jarFileURL.getFile() + " : " + ioe.getMessage());
         return null;
      }
   }

   public static String[] getPackageJdoFilesForJarFile(URI jarFileURI) {
      URL jarFileURL = null;

      try {
         jarFileURL = jarFileURI.toURL();
      } catch (MalformedURLException var3) {
         throw new NucleusException("JAR file at " + jarFileURI + " not openable. Invalid URL");
      }

      return getPackageJdoFilesForJarFile(jarFileURL);
   }

   private static String[] getFileNamesWithSuffixForJarFile(JarFile jar, String suffix) {
      Enumeration jarEntries = jar.entries();
      Set<String> files = new HashSet();

      while(jarEntries.hasMoreElements()) {
         String entry = ((JarEntry)jarEntries.nextElement()).getName();
         if (entry.endsWith(suffix)) {
            files.add(entry);
         }
      }

      return (String[])files.toArray(new String[files.size()]);
   }

   public static String[] getClassNamesForDirectoryAndBelow(File dir) {
      if (dir == null) {
         return null;
      } else {
         Collection<File> classFiles = getClassFilesForDirectory(dir, true, false);
         if (classFiles != null && !classFiles.isEmpty()) {
            String[] classNames = new String[classFiles.size()];
            Iterator<File> iter = classFiles.iterator();

            String classname;
            for(int i = 0; iter.hasNext(); classNames[i++] = classname.replace(File.separatorChar, '.')) {
               String filename = ((File)iter.next()).getAbsolutePath();
               classname = filename.substring(dir.getAbsolutePath().length() + 1, filename.length() - 6);
            }

            return classNames;
         } else {
            return null;
         }
      }
   }

   public static boolean isInnerClass(String class_name) {
      if (class_name == null) {
         return false;
      } else {
         return class_name.indexOf(36) >= 0;
      }
   }

   public static boolean hasDefaultConstructor(Class the_class) {
      if (the_class == null) {
         return false;
      } else {
         try {
            the_class.getDeclaredConstructor();
            return true;
         } catch (Exception var2) {
            return false;
         }
      }
   }

   public static Collection getSuperclasses(Class the_class) {
      List<Class<?>> result = new ArrayList();

      for(Class<?> superclass = the_class.getSuperclass(); superclass != null; superclass = superclass.getSuperclass()) {
         result.add(superclass);
      }

      return result;
   }

   public static Collection getSuperinterfaces(Class the_class) {
      List<Class<?>> result = new ArrayList();
      collectSuperinterfaces(the_class, result);
      return result;
   }

   private static void collectSuperinterfaces(Class c, List result) {
      for(Class i : c.getInterfaces()) {
         if (!result.contains(i)) {
            result.add(i);
            collectSuperinterfaces(i, result);
         }
      }

   }

   public static Field getFieldForClass(Class cls, String fieldName) {
      try {
         while(true) {
            try {
               return cls.getDeclaredField(fieldName);
            } catch (NoSuchFieldException var3) {
               cls = cls.getSuperclass();
               if (cls != null) {
                  continue;
               }
               break;
            }
         }
      } catch (Exception var4) {
      }

      return null;
   }

   public static Method getGetterMethodForClass(Class cls, String beanName) {
      Method getter = findDeclaredMethodInHeirarchy(cls, getJavaBeanGetterName(beanName, false));
      if (getter == null) {
         getter = findDeclaredMethodInHeirarchy(cls, getJavaBeanGetterName(beanName, true));
      }

      return getter;
   }

   public static Method getSetterMethodForClass(Class cls, String beanName, Class type) {
      return findDeclaredMethodInHeirarchy(cls, getJavaBeanSetterName(beanName), type);
   }

   private static Method findDeclaredMethodInHeirarchy(Class cls, String methodName, Class... parameterTypes) {
      try {
         while(true) {
            try {
               return cls.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException var4) {
               cls = cls.getSuperclass();
               if (cls != null) {
                  continue;
               }
               break;
            }
         }
      } catch (Exception var5) {
      }

      return null;
   }

   public static String getWrapperTypeNameForPrimitiveTypeName(String typeName) {
      if (typeName.equals("boolean")) {
         return ClassNameConstants.JAVA_LANG_BOOLEAN;
      } else if (typeName.equals("byte")) {
         return ClassNameConstants.JAVA_LANG_BYTE;
      } else if (typeName.equals("char")) {
         return ClassNameConstants.JAVA_LANG_CHARACTER;
      } else if (typeName.equals("double")) {
         return ClassNameConstants.JAVA_LANG_DOUBLE;
      } else if (typeName.equals("float")) {
         return ClassNameConstants.JAVA_LANG_FLOAT;
      } else if (typeName.equals("int")) {
         return ClassNameConstants.JAVA_LANG_INTEGER;
      } else if (typeName.equals("long")) {
         return ClassNameConstants.JAVA_LANG_LONG;
      } else {
         return typeName.equals("short") ? ClassNameConstants.JAVA_LANG_SHORT : typeName;
      }
   }

   public static Class getWrapperTypeForPrimitiveType(Class type) {
      if (type == Boolean.TYPE) {
         return ClassConstants.JAVA_LANG_BOOLEAN;
      } else if (type == Byte.TYPE) {
         return ClassConstants.JAVA_LANG_BYTE;
      } else if (type == Character.TYPE) {
         return ClassConstants.JAVA_LANG_CHARACTER;
      } else if (type == Double.TYPE) {
         return ClassConstants.JAVA_LANG_DOUBLE;
      } else if (type == Float.TYPE) {
         return ClassConstants.JAVA_LANG_FLOAT;
      } else if (type == Integer.TYPE) {
         return ClassConstants.JAVA_LANG_INTEGER;
      } else if (type == Long.TYPE) {
         return ClassConstants.JAVA_LANG_LONG;
      } else {
         return type == Short.TYPE ? ClassConstants.JAVA_LANG_SHORT : null;
      }
   }

   public static Class getPrimitiveTypeForType(Class type) {
      if (type == Boolean.class) {
         return ClassConstants.BOOLEAN;
      } else if (type == Byte.class) {
         return ClassConstants.BYTE;
      } else if (type == Character.class) {
         return ClassConstants.CHAR;
      } else if (type == Double.class) {
         return ClassConstants.DOUBLE;
      } else if (type == Float.class) {
         return ClassConstants.FLOAT;
      } else if (type == Integer.class) {
         return ClassConstants.INT;
      } else if (type == Long.class) {
         return ClassConstants.LONG;
      } else {
         return type == Short.class ? ClassConstants.SHORT : null;
      }
   }

   public static boolean isPrimitiveWrapperType(String typeName) {
      return typeName.equals(ClassNameConstants.JAVA_LANG_BOOLEAN) || typeName.equals(ClassNameConstants.JAVA_LANG_BYTE) || typeName.equals(ClassNameConstants.JAVA_LANG_CHARACTER) || typeName.equals(ClassNameConstants.JAVA_LANG_DOUBLE) || typeName.equals(ClassNameConstants.JAVA_LANG_FLOAT) || typeName.equals(ClassNameConstants.JAVA_LANG_INTEGER) || typeName.equals(ClassNameConstants.JAVA_LANG_LONG) || typeName.equals(ClassNameConstants.JAVA_LANG_SHORT);
   }

   public static boolean isPrimitiveArrayType(String typeName) {
      return typeName.equals(ClassNameConstants.BOOLEAN_ARRAY) || typeName.equals(ClassNameConstants.BYTE_ARRAY) || typeName.equals(ClassNameConstants.CHAR_ARRAY) || typeName.equals(ClassNameConstants.DOUBLE_ARRAY) || typeName.equals(ClassNameConstants.FLOAT_ARRAY) || typeName.equals(ClassNameConstants.INT_ARRAY) || typeName.equals(ClassNameConstants.LONG_ARRAY) || typeName.equals(ClassNameConstants.SHORT_ARRAY);
   }

   public static boolean isPrimitiveType(String typeName) {
      return typeName.equals(ClassNameConstants.BOOLEAN) || typeName.equals(ClassNameConstants.BYTE) || typeName.equals(ClassNameConstants.CHAR) || typeName.equals(ClassNameConstants.DOUBLE) || typeName.equals(ClassNameConstants.FLOAT) || typeName.equals(ClassNameConstants.INT) || typeName.equals(ClassNameConstants.LONG) || typeName.equals(ClassNameConstants.SHORT);
   }

   public static Object convertValue(Object value, Class cls) {
      if (value == null) {
         return null;
      } else {
         Class type = cls;
         if (cls.isPrimitive()) {
            type = getWrapperTypeForPrimitiveType(cls);
         }

         if (type.isAssignableFrom(value.getClass())) {
            return value;
         } else if (type == Long.class && value instanceof Number) {
            return ((Number)value).longValue();
         } else if (type == Integer.class && value instanceof Number) {
            return ((Number)value).intValue();
         } else if (type == Short.class && value instanceof Number) {
            return ((Number)value).shortValue();
         } else if (type == Float.class && value instanceof Number) {
            return ((Number)value).floatValue();
         } else if (type == Double.class && value instanceof Number) {
            return ((Number)value).doubleValue();
         } else if (type == Boolean.class && value instanceof Long) {
            return (Long)value == 0L ? Boolean.FALSE : ((Long)value == 1L ? Boolean.TRUE : null);
         } else if (type == Boolean.class && value instanceof Integer) {
            return (Integer)value == 0 ? Boolean.FALSE : ((Integer)value == 1 ? Boolean.TRUE : null);
         } else {
            return null;
         }
      }
   }

   public static boolean typesAreCompatible(Class cls1, String clsName2, ClassLoaderResolver clr) {
      if (clr.isAssignableFrom(cls1, clsName2)) {
         return true;
      } else if (cls1.isPrimitive()) {
         return clr.isAssignableFrom(getWrapperTypeForPrimitiveType(cls1), clsName2);
      } else {
         return isPrimitiveWrapperType(cls1.getName()) ? clr.isAssignableFrom(getPrimitiveTypeForType(cls1), clsName2) : false;
      }
   }

   public static boolean typesAreCompatible(Class cls1, Class cls2) {
      if (cls1.isAssignableFrom(cls2)) {
         return true;
      } else {
         return cls1.isPrimitive() ? getWrapperTypeForPrimitiveType(cls1).isAssignableFrom(cls2) : false;
      }
   }

   public static String createFullClassName(String pkg_name, String cls_name) {
      if (StringUtils.isWhitespace(cls_name)) {
         throw new IllegalArgumentException("Class name not specified");
      } else if (StringUtils.isWhitespace(pkg_name)) {
         return cls_name;
      } else {
         return cls_name.indexOf(46) >= 0 ? cls_name : pkg_name + "." + cls_name;
      }
   }

   public static String getJavaLangClassForType(String type) {
      String baseType = null;
      if (type.lastIndexOf(46) < 0) {
         baseType = type;
      } else {
         baseType = type.substring(type.lastIndexOf(46) + 1);
      }

      return !baseType.equals("String") && !baseType.equals("Object") && !baseType.equals("Boolean") && !baseType.equals("Byte") && !baseType.equals("Character") && !baseType.equals("Double") && !baseType.equals("Float") && !baseType.equals("Integer") && !baseType.equals("Long") && !baseType.equals("Short") && !baseType.equals("Number") && !baseType.equals("StringBuffer") && !baseType.equals("StringBuilder") ? type : "java.lang." + baseType;
   }

   public static boolean classesAreDescendents(ClassLoaderResolver clr, String class_name_1, String class_name_2) {
      Class class_1 = clr.classForName(class_name_1);
      Class class_2 = clr.classForName(class_name_2);
      if (class_1 != null && class_2 != null) {
         return class_1.isAssignableFrom(class_2) || class_2.isAssignableFrom(class_1);
      } else {
         return false;
      }
   }

   public static void dumpClassInformation(Class cls) {
      NucleusLogger.GENERAL.info("----------------------------------------");
      NucleusLogger.GENERAL.info("Class Information for class " + cls.getName());

      for(Class superclass : getSuperclasses(cls)) {
         NucleusLogger.GENERAL.info("    Superclass : " + superclass.getName());
      }

      Class[] interfaces = cls.getInterfaces();
      if (interfaces != null) {
         for(int i = 0; i < interfaces.length; ++i) {
            NucleusLogger.GENERAL.info("    Interface : " + interfaces[i].getName());
         }
      }

      try {
         Method[] methods = cls.getDeclaredMethods();

         for(int i = 0; i < methods.length; ++i) {
            NucleusLogger.GENERAL.info("    Method : " + methods[i].toString());
            Annotation[] annots = methods[i].getAnnotations();
            if (annots != null) {
               for(int j = 0; j < annots.length; ++j) {
                  NucleusLogger.GENERAL.info("        annotation=" + annots[j]);
               }
            }
         }
      } catch (Exception var7) {
      }

      try {
         Field[] fields = cls.getDeclaredFields();

         for(int i = 0; i < fields.length; ++i) {
            NucleusLogger.GENERAL.info("    Field : " + fields[i].toString());
            Annotation[] annots = fields[i].getAnnotations();
            if (annots != null) {
               for(int j = 0; j < annots.length; ++j) {
                  NucleusLogger.GENERAL.info("        annotation=" + annots[j]);
               }
            }
         }
      } catch (Exception var6) {
      }

      NucleusLogger.GENERAL.info("----------------------------------------");
   }

   public static String getJavaBeanGetterName(String fieldName, boolean isBoolean) {
      return fieldName == null ? null : buildJavaBeanName(isBoolean ? "is" : "get", fieldName);
   }

   public static String getJavaBeanSetterName(String fieldName) {
      return fieldName == null ? null : buildJavaBeanName("set", fieldName);
   }

   private static String buildJavaBeanName(String prefix, String fieldName) {
      int prefixLength = prefix.length();
      StringBuilder sb = new StringBuilder(prefixLength + fieldName.length());
      sb.append(prefix);
      sb.append(fieldName);
      sb.setCharAt(prefixLength, Character.toUpperCase(sb.charAt(prefixLength)));
      return sb.toString();
   }

   public static String getFieldNameForJavaBeanGetter(String methodName) {
      if (methodName == null) {
         return null;
      } else if (methodName.startsWith("get")) {
         return truncateJavaBeanMethodName(methodName, 3);
      } else {
         return methodName.startsWith("is") ? truncateJavaBeanMethodName(methodName, 2) : null;
      }
   }

   public static String getFieldNameForJavaBeanSetter(String methodName) {
      if (methodName == null) {
         return null;
      } else {
         return methodName.startsWith("set") ? truncateJavaBeanMethodName(methodName, 3) : null;
      }
   }

   private static String truncateJavaBeanMethodName(String methodName, int prefixLength) {
      if (methodName.length() <= prefixLength) {
         return null;
      } else {
         methodName = methodName.substring(prefixLength);
         if (methodName.length() == 1) {
            return methodName.toLowerCase();
         } else {
            char firstChar = methodName.charAt(0);
            return Character.isUpperCase(firstChar) && Character.isLowerCase(methodName.charAt(1)) ? Character.toLowerCase(firstChar) + methodName.substring(1) : methodName;
         }
      }
   }

   public static String getClassNameForFileName(String fileName, ClassLoaderResolver clr) {
      if (!fileName.endsWith(".class")) {
         return null;
      } else {
         String name = fileName.substring(0, fileName.length() - 6);

         String className;
         for(String var6 = name.replace(File.separatorChar, '.'); var6.indexOf(".") >= 0; var6 = className) {
            className = var6.substring(var6.indexOf(46) + 1);

            try {
               Class cls = clr.classForName(className);
               if (cls != null) {
                  return className;
               }
            } catch (ClassNotResolvedException var5) {
            }
         }

         return null;
      }
   }

   public static String getClassNameForFileURL(final URL fileURL) throws ClassNotFoundException {
      ClassLoader loader = (ClassLoader)AccessController.doPrivileged(new PrivilegedAction() {
         public Object run() {
            return new ClassLoader() {
               protected Class findClass(String name) throws ClassNotFoundException {
                  InputStream in = null;

                  Class var6;
                  try {
                     in = new BufferedInputStream(fileURL.openStream());
                     ByteArrayOutputStream byteStr = new ByteArrayOutputStream();
                     int byt = -1;

                     while((byt = in.read()) != -1) {
                        byteStr.write(byt);
                     }

                     byte[] byteArr = byteStr.toByteArray();
                     var6 = this.defineClass((String)null, byteArr, 0, byteArr.length);
                  } catch (RuntimeException rex) {
                     throw rex;
                  } catch (Exception var17) {
                     throw new ClassNotFoundException(name);
                  } finally {
                     if (in != null) {
                        try {
                           in.close();
                        } catch (IOException var15) {
                        }
                     }

                  }

                  return var6;
               }
            };
         }
      });
      Class cls = loader.loadClass("garbage");
      return cls != null ? cls.getName() : null;
   }

   public static String getPackageNameForClass(Class cls) {
      if (cls.getPackage() != null) {
         return cls.getPackage().getName();
      } else {
         int separator = cls.getName().lastIndexOf(46);
         return separator < 0 ? null : cls.getName().substring(0, separator);
      }
   }

   public static String getClassNameForClass(Class cls) {
      int separator = cls.getName().lastIndexOf(46);
      return separator < 0 ? cls.getName() : cls.getName().substring(separator + 1);
   }

   public static Class getClassForGenericType(Type genericType, int pos) {
      if (genericType instanceof ParameterizedType) {
         ParameterizedType paramtype = (ParameterizedType)genericType;
         if (paramtype.getActualTypeArguments().length > pos) {
            Type argType = paramtype.getActualTypeArguments()[pos];
            if (argType instanceof Class) {
               return (Class)argType;
            }

            if (argType instanceof ParameterizedType) {
               return (Class)((ParameterizedType)argType).getRawType();
            }

            if (argType instanceof GenericArrayType) {
               Type cmptType = ((GenericArrayType)argType).getGenericComponentType();
               return Array.newInstance((Class)cmptType, 0).getClass();
            }
         }
      }

      return null;
   }

   public static String getCollectionElementType(Field field) {
      Class elementType = getCollectionElementType(field.getType(), field.getGenericType());
      return elementType != null ? elementType.getName() : null;
   }

   public static String getCollectionElementType(Method method) {
      Class elementType = getCollectionElementType(method.getReturnType(), method.getGenericReturnType());
      return elementType != null ? elementType.getName() : null;
   }

   public static Class getCollectionElementType(Class type, Type genericType) {
      return !Collection.class.isAssignableFrom(type) ? null : getClassForGenericType(genericType, 0);
   }

   public static String getMapKeyType(Field field) {
      Class keyType = getMapKeyType(field.getType(), field.getGenericType());
      return keyType != null ? keyType.getName() : null;
   }

   public static String getMapKeyType(Method method) {
      Class keyType = getMapKeyType(method.getReturnType(), method.getGenericReturnType());
      return keyType != null ? keyType.getName() : null;
   }

   public static Class getMapKeyType(Class type, Type genericType) {
      return !Map.class.isAssignableFrom(type) ? null : getClassForGenericType(genericType, 0);
   }

   public static String getMapValueType(Field field) {
      Class valueType = getMapValueType(field.getType(), field.getGenericType());
      return valueType != null ? valueType.getName() : null;
   }

   public static String getMapValueType(Method method) {
      Class valueType = getMapValueType(method.getReturnType(), method.getGenericReturnType());
      return valueType != null ? valueType.getName() : null;
   }

   public static Class getMapValueType(Class type, Type genericType) {
      return !Map.class.isAssignableFrom(type) ? null : getClassForGenericType(genericType, 1);
   }

   public static int getModifiersForFieldOfClass(ClassLoaderResolver clr, String className, String fieldName) {
      try {
         Class cls = clr.classForName(className);
         Field fld = cls.getDeclaredField(fieldName);
         return fld.getModifiers();
      } catch (Exception var5) {
         return -1;
      }
   }

   public static boolean isReferenceType(Class cls) {
      if (cls == null) {
         return false;
      } else {
         return cls.isInterface() || cls.getName().equals("java.lang.Object");
      }
   }

   public static void assertClassForJarExistsInClasspath(ClassLoaderResolver clr, String className, String jarName) {
      try {
         Class cls = clr.classForName(className);
         if (cls == null) {
            throw new NucleusUserException(Localiser.msg("001006", className, jarName));
         }
      } catch (Error var4) {
         throw new NucleusUserException(Localiser.msg("001006", className, jarName));
      } catch (ClassNotResolvedException var5) {
         throw new NucleusUserException(Localiser.msg("001006", className, jarName));
      }
   }

   public static boolean stringArrayContainsValue(String[] array, String value) {
      if (value != null && array != null) {
         for(int i = 0; i < array.length; ++i) {
            if (value.equals(array[i])) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public static Object getValueOfMethodByReflection(Object object, String methodName, Object... args) {
      if (object == null) {
         return null;
      } else {
         final Method method = getDeclaredMethodPrivileged(object.getClass(), methodName);
         if (method == null) {
            throw new NucleusUserException("Cannot access method: " + methodName + " in type " + object.getClass());
         } else {
            try {
               if (!method.isAccessible()) {
                  try {
                     AccessController.doPrivileged(new PrivilegedAction() {
                        public Object run() {
                           method.setAccessible(true);
                           return null;
                        }
                     });
                  } catch (SecurityException ex) {
                     throw (new NucleusException("Cannot access method: " + methodName, ex)).setFatal();
                  }
               }

               Object methodValue = method.invoke(object, args);
               return methodValue;
            } catch (InvocationTargetException e2) {
               throw new NucleusUserException("Cannot access method: " + methodName, e2);
            } catch (IllegalArgumentException e2) {
               throw new NucleusUserException("Cannot access method: " + methodName, e2);
            } catch (IllegalAccessException e2) {
               throw new NucleusUserException("Cannot access method: " + methodName, e2);
            }
         }
      }
   }

   public static Object getValueOfFieldByReflection(Object object, String fieldName) {
      if (object == null) {
         return null;
      } else {
         final Field field = getDeclaredFieldPrivileged(object.getClass(), fieldName);
         if (field == null) {
            throw new NucleusUserException("Cannot access field: " + fieldName + " in type " + object.getClass());
         } else {
            try {
               if (!field.isAccessible()) {
                  try {
                     AccessController.doPrivileged(new PrivilegedAction() {
                        public Object run() {
                           field.setAccessible(true);
                           return null;
                        }
                     });
                  } catch (SecurityException ex) {
                     throw (new NucleusException("Cannot access field: " + fieldName, ex)).setFatal();
                  }
               }

               Object fieldValue = field.get(object);
               return fieldValue;
            } catch (IllegalArgumentException e2) {
               throw new NucleusUserException("Cannot access field: " + fieldName, e2);
            } catch (IllegalAccessException e2) {
               throw new NucleusUserException("Cannot access field: " + fieldName, e2);
            }
         }
      }
   }

   private static Field getDeclaredFieldPrivileged(final Class clazz, final String fieldName) {
      return clazz != null && fieldName != null ? (Field)AccessController.doPrivileged(new PrivilegedAction() {
         public Object run() {
            Class seekingClass = clazz;

            while(true) {
               try {
                  return seekingClass.getDeclaredField(fieldName);
               } catch (SecurityException ex) {
                  throw (new NucleusException("CannotGetDeclaredField", ex)).setFatal();
               } catch (NoSuchFieldException var4) {
                  seekingClass = seekingClass.getSuperclass();
                  if (seekingClass == null) {
                     return null;
                  }
               } catch (LinkageError ex) {
                  throw (new NucleusException("ClassLoadingError", ex)).setFatal();
               }
            }
         }
      }) : null;
   }

   private static Method getDeclaredMethodPrivileged(final Class clazz, final String methodName, final Class... argTypes) {
      return clazz != null && methodName != null ? (Method)AccessController.doPrivileged(new PrivilegedAction() {
         public Object run() {
            Class seekingClass = clazz;

            while(true) {
               try {
                  return seekingClass.getDeclaredMethod(methodName, argTypes);
               } catch (SecurityException ex) {
                  throw (new NucleusException("Cannot get declared method " + methodName, ex)).setFatal();
               } catch (NoSuchMethodException var4) {
                  seekingClass = seekingClass.getSuperclass();
                  if (seekingClass == null) {
                     return null;
                  }
               } catch (LinkageError ex) {
                  throw (new NucleusException("ClassLoadingError", ex)).setFatal();
               }
            }
         }
      }) : null;
   }

   public static Object getValueForIdentityField(Object id, String fieldName) {
      String getterName = getJavaBeanGetterName(fieldName, false);

      try {
         return getValueOfMethodByReflection(id, getterName);
      } catch (NucleusException var5) {
         try {
            return getValueOfFieldByReflection(id, fieldName);
         } catch (NucleusException var4) {
            throw new NucleusUserException("Not possible to get value of field " + fieldName + " from identity " + id);
         }
      }
   }

   public static Class getClassForMemberOfClass(Class cls, String memberName) {
      Field fld = getFieldForClass(cls, memberName);
      if (fld != null) {
         return fld.getType();
      } else {
         Method method = getGetterMethodForClass(cls, memberName);
         return method != null ? method.getReturnType() : null;
      }
   }

   public static boolean isJavaBeanGetterMethod(Method method) {
      if (Modifier.isStatic(method.getModifiers())) {
         return false;
      } else if (!method.getName().startsWith("get") && !method.getName().startsWith("is")) {
         return false;
      } else if (method.getName().startsWith("get") && method.getName().length() == 3) {
         return false;
      } else if (method.getName().startsWith("is") && method.getName().length() == 2) {
         return false;
      } else if (method.getReturnType() == null) {
         return false;
      } else {
         return method.getParameterTypes() == null || method.getParameterTypes().length == 0;
      }
   }

   public static void clearFlags(boolean[] flags) {
      for(int i = 0; i < flags.length; ++i) {
         flags[i] = false;
      }

   }

   public static void clearFlags(boolean[] flags, int[] fields) {
      for(int i = 0; i < fields.length; ++i) {
         flags[fields[i]] = false;
      }

   }

   public static int[] getFlagsSetTo(boolean[] flags, boolean state) {
      int[] temp = new int[flags.length];
      int j = 0;

      for(int i = 0; i < flags.length; ++i) {
         if (flags[i] == state) {
            temp[j++] = i;
         }
      }

      if (j != 0) {
         int[] fieldNumbers = new int[j];
         System.arraycopy(temp, 0, fieldNumbers, 0, j);
         return fieldNumbers;
      } else {
         return null;
      }
   }

   public static int[] getFlagsSetTo(boolean[] flags, int[] indices, boolean state) {
      if (indices == null) {
         return null;
      } else {
         int[] temp = new int[indices.length];
         int j = 0;

         for(int i = 0; i < indices.length; ++i) {
            if (flags[indices[i]] == state) {
               temp[j++] = indices[i];
            }
         }

         if (j != 0) {
            int[] fieldNumbers = new int[j];
            System.arraycopy(temp, 0, fieldNumbers, 0, j);
            return fieldNumbers;
         } else {
            return null;
         }
      }
   }

   public static boolean getBitFromInt(int bits, int bitIndex) {
      if (bitIndex >= 0 && bitIndex <= 31) {
         return (bits & 1 << bitIndex) != 0;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static int setBitInInt(int bits, int bitIndex, boolean flag) {
      if (bitIndex >= 0 && bitIndex <= 31) {
         int mask = 1 << bitIndex;
         return bits & ~mask | (flag ? mask : 0);
      } else {
         throw new IllegalArgumentException();
      }
   }
}
