package org.datanucleus.enhancer;

import org.datanucleus.ClassConstants;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.asm.MethodVisitor;
import org.datanucleus.asm.Type;

public final class EnhanceUtils {
   public static final String ACN_boolean;
   public static final String ACN_byte;
   public static final String ACN_char;
   public static final String ACN_double;
   public static final String ACN_float;
   public static final String ACN_int;
   public static final String ACN_long;
   public static final String ACN_short;
   public static final String ACN_Boolean;
   public static final String ACN_Byte;
   public static final String ACN_Character;
   public static final String ACN_Double;
   public static final String ACN_Float;
   public static final String ACN_Integer;
   public static final String ACN_Long;
   public static final String ACN_Short;
   public static final String ACN_String;
   public static final String ACN_Object;
   public static final String CD_String;
   public static final String CD_Object;

   private EnhanceUtils() {
   }

   public static void addBIPUSHToMethod(MethodVisitor visitor, int i) {
      if (i < 6) {
         switch (i) {
            case 0:
               visitor.visitInsn(3);
               break;
            case 1:
               visitor.visitInsn(4);
               break;
            case 2:
               visitor.visitInsn(5);
               break;
            case 3:
               visitor.visitInsn(6);
               break;
            case 4:
               visitor.visitInsn(7);
               break;
            case 5:
               visitor.visitInsn(8);
         }
      } else if (i < 127) {
         visitor.visitIntInsn(16, i);
      } else if (i < 32767) {
         visitor.visitIntInsn(17, i);
      }

   }

   public static void addReturnForType(MethodVisitor visitor, Class type) {
      if (type != Integer.TYPE && type != Boolean.TYPE && type != Byte.TYPE && type != Character.TYPE && type != Short.TYPE) {
         if (type == Double.TYPE) {
            visitor.visitInsn(175);
         } else if (type == Float.TYPE) {
            visitor.visitInsn(174);
         } else if (type == Long.TYPE) {
            visitor.visitInsn(173);
         } else {
            visitor.visitInsn(176);
         }
      } else {
         visitor.visitInsn(172);
      }

   }

   public static void addLoadForType(MethodVisitor visitor, Class type, int number) {
      if (type != Integer.TYPE && type != Boolean.TYPE && type != Byte.TYPE && type != Character.TYPE && type != Short.TYPE) {
         if (type == Double.TYPE) {
            visitor.visitVarInsn(24, number);
         } else if (type == Float.TYPE) {
            visitor.visitVarInsn(23, number);
         } else if (type == Long.TYPE) {
            visitor.visitVarInsn(22, number);
         } else {
            visitor.visitVarInsn(25, number);
         }
      } else {
         visitor.visitVarInsn(21, number);
      }

   }

   public static String getTypeNameForPersistableMethod(Class cls) {
      if (cls == null) {
         return null;
      } else if (cls == ClassConstants.BOOLEAN) {
         return "Boolean";
      } else if (cls == ClassConstants.BYTE) {
         return "Byte";
      } else if (cls == ClassConstants.CHAR) {
         return "Char";
      } else if (cls == ClassConstants.DOUBLE) {
         return "Double";
      } else if (cls == ClassConstants.FLOAT) {
         return "Float";
      } else if (cls == ClassConstants.INT) {
         return "Int";
      } else if (cls == ClassConstants.LONG) {
         return "Long";
      } else if (cls == ClassConstants.SHORT) {
         return "Short";
      } else {
         return cls == ClassConstants.JAVA_LANG_STRING ? "String" : "Object";
      }
   }

   public static String getTypeDescriptorForType(String clsName) {
      if (clsName == null) {
         return null;
      } else if (clsName.equals(ClassNameConstants.BOOLEAN)) {
         return Type.BOOLEAN_TYPE.getDescriptor();
      } else if (clsName.equals(ClassNameConstants.BYTE)) {
         return Type.BYTE_TYPE.getDescriptor();
      } else if (clsName.equals(ClassNameConstants.CHAR)) {
         return Type.CHAR_TYPE.getDescriptor();
      } else if (clsName.equals(ClassNameConstants.DOUBLE)) {
         return Type.DOUBLE_TYPE.getDescriptor();
      } else if (clsName.equals(ClassNameConstants.FLOAT)) {
         return Type.FLOAT_TYPE.getDescriptor();
      } else if (clsName.equals(ClassNameConstants.INT)) {
         return Type.INT_TYPE.getDescriptor();
      } else if (clsName.equals(ClassNameConstants.LONG)) {
         return Type.LONG_TYPE.getDescriptor();
      } else if (clsName.equals(ClassNameConstants.SHORT)) {
         return Type.SHORT_TYPE.getDescriptor();
      } else {
         return clsName.equals(ClassNameConstants.JAVA_LANG_STRING) ? CD_String : "L" + clsName.replace('.', '/') + ";";
      }
   }

   public static String getTypeDescriptorForEnhanceMethod(Class cls) {
      if (cls == null) {
         return null;
      } else if (cls == ClassConstants.BOOLEAN) {
         return Type.BOOLEAN_TYPE.getDescriptor();
      } else if (cls == ClassConstants.BYTE) {
         return Type.BYTE_TYPE.getDescriptor();
      } else if (cls == ClassConstants.CHAR) {
         return Type.CHAR_TYPE.getDescriptor();
      } else if (cls == ClassConstants.DOUBLE) {
         return Type.DOUBLE_TYPE.getDescriptor();
      } else if (cls == ClassConstants.FLOAT) {
         return Type.FLOAT_TYPE.getDescriptor();
      } else if (cls == ClassConstants.INT) {
         return Type.INT_TYPE.getDescriptor();
      } else if (cls == ClassConstants.LONG) {
         return Type.LONG_TYPE.getDescriptor();
      } else if (cls == ClassConstants.SHORT) {
         return Type.SHORT_TYPE.getDescriptor();
      } else {
         return cls == ClassConstants.JAVA_LANG_STRING ? CD_String : CD_Object;
      }
   }

   public static String getASMClassNameForSingleFieldIdentityConstructor(Class fieldType) {
      if (fieldType == null) {
         return null;
      } else if (fieldType != ClassConstants.BYTE && fieldType != ClassConstants.JAVA_LANG_BYTE) {
         if (fieldType != ClassConstants.CHAR && fieldType != ClassConstants.JAVA_LANG_CHARACTER) {
            if (fieldType != ClassConstants.INT && fieldType != ClassConstants.JAVA_LANG_INTEGER) {
               if (fieldType != ClassConstants.LONG && fieldType != ClassConstants.JAVA_LANG_LONG) {
                  if (fieldType != ClassConstants.SHORT && fieldType != ClassConstants.JAVA_LANG_SHORT) {
                     return fieldType == ClassConstants.JAVA_LANG_STRING ? ACN_String : ACN_Object;
                  } else {
                     return ACN_Short;
                  }
               } else {
                  return ACN_Long;
               }
            } else {
               return ACN_Integer;
            }
         } else {
            return ACN_Character;
         }
      } else {
         return ACN_Byte;
      }
   }

   public static int getAsmVersionForJRE() {
      return 51;
   }

   static {
      ACN_boolean = ClassNameConstants.BOOLEAN;
      ACN_byte = ClassNameConstants.BYTE;
      ACN_char = ClassNameConstants.CHAR;
      ACN_double = ClassNameConstants.DOUBLE;
      ACN_float = ClassNameConstants.FLOAT;
      ACN_int = ClassNameConstants.INT;
      ACN_long = ClassNameConstants.LONG;
      ACN_short = ClassNameConstants.SHORT;
      ACN_Boolean = ClassNameConstants.JAVA_LANG_BOOLEAN.replace('.', '/');
      ACN_Byte = ClassNameConstants.JAVA_LANG_BYTE.replace('.', '/');
      ACN_Character = ClassNameConstants.JAVA_LANG_CHARACTER.replace('.', '/');
      ACN_Double = ClassNameConstants.JAVA_LANG_DOUBLE.replace('.', '/');
      ACN_Float = ClassNameConstants.JAVA_LANG_FLOAT.replace('.', '/');
      ACN_Integer = ClassNameConstants.JAVA_LANG_INTEGER.replace('.', '/');
      ACN_Long = ClassNameConstants.JAVA_LANG_LONG.replace('.', '/');
      ACN_Short = ClassNameConstants.JAVA_LANG_SHORT.replace('.', '/');
      ACN_String = ClassNameConstants.JAVA_LANG_STRING.replace('.', '/');
      ACN_Object = Object.class.getName().replace('.', '/');
      CD_String = Type.getDescriptor(String.class);
      CD_Object = Type.getDescriptor(Object.class);
   }
}
