package org.codehaus.janino;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.commons.compiler.InternalCompilerException;
import org.codehaus.commons.nullanalysis.Nullable;

public final class Descriptor {
   public static final String VOID = "V";
   public static final String BYTE = "B";
   public static final String CHAR = "C";
   public static final String DOUBLE = "D";
   public static final String FLOAT = "F";
   public static final String INT = "I";
   public static final String LONG = "J";
   public static final String SHORT = "S";
   public static final String BOOLEAN = "Z";
   public static final String JAVA_LANG_ANNOTATION_RETENTION = "Ljava/lang/annotation/Retention;";
   public static final String JAVA_LANG_OVERRIDE = "Ljava/lang/Override;";
   public static final String JAVA_LANG_ASSERTIONERROR = "Ljava/lang/AssertionError;";
   public static final String JAVA_LANG_BOOLEAN = "Ljava/lang/Boolean;";
   public static final String JAVA_LANG_BYTE = "Ljava/lang/Byte;";
   public static final String JAVA_LANG_CHARACTER = "Ljava/lang/Character;";
   public static final String JAVA_LANG_CLASS = "Ljava/lang/Class;";
   public static final String JAVA_LANG_DOUBLE = "Ljava/lang/Double;";
   public static final String JAVA_LANG_ENUM = "Ljava/lang/Enum;";
   public static final String JAVA_LANG_ERROR = "Ljava/lang/Error;";
   public static final String JAVA_LANG_EXCEPTION = "Ljava/lang/Exception;";
   public static final String JAVA_LANG_FLOAT = "Ljava/lang/Float;";
   public static final String JAVA_LANG_INTEGER = "Ljava/lang/Integer;";
   public static final String JAVA_LANG_LONG = "Ljava/lang/Long;";
   public static final String JAVA_LANG_OBJECT = "Ljava/lang/Object;";
   public static final String JAVA_LANG_RUNTIMEEXCEPTION = "Ljava/lang/RuntimeException;";
   public static final String JAVA_LANG_SHORT = "Ljava/lang/Short;";
   public static final String JAVA_LANG_STRING = "Ljava/lang/String;";
   public static final String JAVA_LANG_STRINGBUILDER = "Ljava/lang/StringBuilder;";
   public static final String JAVA_LANG_SYSTEM = "Ljava/lang/System;";
   public static final String JAVA_LANG_THROWABLE = "Ljava/lang/Throwable;";
   public static final String JAVA_LANG_VOID = "Ljava/lang/Void;";
   public static final String JAVA_IO_SERIALIZABLE = "Ljava/io/Serializable;";
   public static final String JAVA_LANG_CLONEABLE = "Ljava/lang/Cloneable;";
   public static final String JAVA_LANG_ITERABLE = "Ljava/lang/Iterable;";
   public static final String JAVA_UTIL_ITERATOR = "Ljava/util/Iterator;";
   private static final Map DESCRIPTOR_TO_CLASSNAME;
   private static final Map CLASS_NAME_TO_DESCRIPTOR;

   private Descriptor() {
   }

   public static boolean isReference(String d) {
      return d.length() > 1;
   }

   public static boolean isClassOrInterfaceReference(String d) {
      return d.charAt(0) == 'L';
   }

   public static boolean isArrayReference(String d) {
      return d.charAt(0) == '[';
   }

   public static String getComponentDescriptor(String d) {
      if (d.charAt(0) != '[') {
         throw new InternalCompilerException("Cannot determine component descriptor from non-array descriptor \"" + d + "\"");
      } else {
         return d.substring(1);
      }
   }

   public static short size(String d) {
      if (d.equals("V")) {
         return 0;
      } else if (hasSize1(d)) {
         return 1;
      } else if (hasSize2(d)) {
         return 2;
      } else {
         throw new InternalCompilerException("No size defined for type \"" + toString(d) + "\"");
      }
   }

   public static boolean hasSize1(String d) {
      if (d.length() == 1) {
         return "BCFISZ".indexOf(d) != -1;
      } else {
         return isReference(d);
      }
   }

   public static boolean hasSize2(String d) {
      return d.equals("J") || d.equals("D");
   }

   public static String toString(String d) {
      int idx = 0;
      StringBuilder sb = new StringBuilder();
      if (d.charAt(0) == '(') {
         ++idx;
         sb.append("(");

         for(; idx < d.length() && d.charAt(idx) != ')'; idx = toString(d, idx, sb)) {
            if (idx != 1) {
               sb.append(", ");
            }
         }

         if (idx >= d.length()) {
            throw new InternalCompilerException("Invalid descriptor \"" + d + "\"");
         }

         sb.append(") => ");
         ++idx;
      }

      toString(d, idx, sb);
      return sb.toString();
   }

   private static int toString(String d, int idx, StringBuilder sb) {
      int dimensions;
      for(dimensions = 0; idx < d.length() && d.charAt(idx) == '['; ++idx) {
         ++dimensions;
      }

      if (idx >= d.length()) {
         throw new InternalCompilerException("Invalid descriptor \"" + d + "\"");
      } else {
         switch (d.charAt(idx)) {
            case 'B':
               sb.append("byte");
               break;
            case 'C':
               sb.append("char");
               break;
            case 'D':
               sb.append("double");
               break;
            case 'E':
            case 'G':
            case 'H':
            case 'K':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'T':
            case 'U':
            case 'W':
            case 'X':
            case 'Y':
            default:
               throw new InternalCompilerException("Invalid descriptor \"" + d + "\"");
            case 'F':
               sb.append("float");
               break;
            case 'I':
               sb.append("int");
               break;
            case 'J':
               sb.append("long");
               break;
            case 'L':
               int idx2 = d.indexOf(59, idx);
               if (idx2 == -1) {
                  throw new InternalCompilerException("Invalid descriptor \"" + d + "\"");
               }

               sb.append(d.substring(idx + 1, idx2).replace('/', '.'));
               idx = idx2;
               break;
            case 'S':
               sb.append("short");
               break;
            case 'V':
               sb.append("void");
               break;
            case 'Z':
               sb.append("boolean");
         }

         while(dimensions > 0) {
            sb.append("[]");
            --dimensions;
         }

         return idx + 1;
      }
   }

   public static String fromClassName(String className) {
      String res = (String)CLASS_NAME_TO_DESCRIPTOR.get(className);
      if (res != null) {
         return res;
      } else {
         return className.startsWith("[") ? className.replace('.', '/') : 'L' + className.replace('.', '/') + ';';
      }
   }

   public static String fromInternalForm(String internalForm) {
      return internalForm.charAt(0) == '[' ? internalForm : 'L' + internalForm + ';';
   }

   public static String toClassName(String d) {
      String res = (String)DESCRIPTOR_TO_CLASSNAME.get(d);
      if (res != null) {
         return res;
      } else {
         char firstChar = d.charAt(0);
         if (firstChar == 'L' && d.endsWith(";")) {
            return d.substring(1, d.length() - 1).replace('/', '.');
         } else if (firstChar == '[') {
            return d.replace('/', '.');
         } else {
            throw new InternalCompilerException("(Invalid field descriptor \"" + d + "\")");
         }
      }
   }

   public static String toInternalForm(String d) {
      if (d.charAt(0) != 'L') {
         throw new InternalCompilerException("Attempt to convert non-class descriptor \"" + d + "\" into internal form");
      } else {
         return d.substring(1, d.length() - 1);
      }
   }

   public static boolean isPrimitive(String d) {
      return d.length() == 1 && "VBCDFIJSZ".indexOf(d.charAt(0)) != -1;
   }

   public static boolean isPrimitiveNumeric(String d) {
      return d.length() == 1 && "BDFIJSC".indexOf(d.charAt(0)) != -1;
   }

   @Nullable
   public static String getPackageName(String d) {
      if (d.charAt(0) != 'L') {
         throw new InternalCompilerException("Attempt to get package name of non-class descriptor \"" + d + "\"");
      } else {
         int idx = d.lastIndexOf(47);
         return idx == -1 ? null : d.substring(1, idx).replace('/', '.');
      }
   }

   public static boolean areInSamePackage(String d1, String d2) {
      String packageName1 = getPackageName(d1);
      String packageName2 = getPackageName(d2);
      return packageName1 == null ? packageName2 == null : packageName1.equals(packageName2);
   }

   static {
      Map<String, String> m = new HashMap();
      m.put("V", "void");
      m.put("B", "byte");
      m.put("C", "char");
      m.put("D", "double");
      m.put("F", "float");
      m.put("I", "int");
      m.put("J", "long");
      m.put("S", "short");
      m.put("Z", "boolean");
      m.put("Ljava/lang/Override;", "java.lang.Override");
      m.put("Ljava/lang/AssertionError;", "java.lang.AssertionError");
      m.put("Ljava/lang/Boolean;", "java.lang.Boolean");
      m.put("Ljava/lang/Byte;", "java.lang.Byte");
      m.put("Ljava/lang/Character;", "java.lang.Character");
      m.put("Ljava/lang/Class;", "java.lang.Class");
      m.put("Ljava/lang/Double;", "java.lang.Double");
      m.put("Ljava/lang/Exception;", "java.lang.Exception");
      m.put("Ljava/lang/Error;", "java.lang.Error");
      m.put("Ljava/lang/Float;", "java.lang.Float");
      m.put("Ljava/lang/Integer;", "java.lang.Integer");
      m.put("Ljava/lang/Long;", "java.lang.Long");
      m.put("Ljava/lang/Object;", "java.lang.Object");
      m.put("Ljava/lang/RuntimeException;", "java.lang.RuntimeException");
      m.put("Ljava/lang/Short;", "java.lang.Short");
      m.put("Ljava/lang/String;", "java.lang.String");
      m.put("Ljava/lang/StringBuilder;", "java.lang.StringBuilder");
      m.put("Ljava/lang/Throwable;", "java.lang.Throwable");
      m.put("Ljava/io/Serializable;", "java.io.Serializable");
      m.put("Ljava/lang/Cloneable;", "java.lang.Cloneable");
      m.put("Ljava/lang/Iterable;", "java.lang.Iterable");
      m.put("Ljava/util/Iterator;", "java.util.Iterator");
      DESCRIPTOR_TO_CLASSNAME = Collections.unmodifiableMap(m);
      m = new HashMap();

      for(Map.Entry e : DESCRIPTOR_TO_CLASSNAME.entrySet()) {
         m.put(e.getValue(), e.getKey());
      }

      CLASS_NAME_TO_DESCRIPTOR = Collections.unmodifiableMap(m);
   }
}
