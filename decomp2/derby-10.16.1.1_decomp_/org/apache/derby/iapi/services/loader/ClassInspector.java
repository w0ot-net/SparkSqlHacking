package org.apache.derby.iapi.services.loader;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.derby.shared.common.error.StandardException;

public class ClassInspector {
   private static final String[] primTypeNames = new String[]{"boolean", "byte", "char", "short", "int", "long", "float", "double"};
   private static final String[] nonPrimTypeNames = new String[]{"java.lang.Boolean", "java.lang.Byte", "java.lang.Character", "java.lang.Short", "java.lang.Integer", "java.lang.Long", "java.lang.Float", "java.lang.Double"};
   private static final String OBJECT_TYPE_NAME = "java.lang.Object";
   private static final String STRING_TYPE_NAME = "java.lang.String";
   private static final String BIGDECIMAL_TYPE_NAME = "java.math.BigDecimal";
   private final ClassFactory cf;

   public ClassInspector(ClassFactory var1) {
      this.cf = var1;
   }

   public boolean instanceOf(String var1, Object var2) throws ClassNotFoundException {
      Class var3 = this.getClass(var1);
      return var3 == null ? false : var3.isInstance(var2);
   }

   public boolean assignableTo(String var1, String var2) {
      try {
         Class var3 = this.getClass(var2);
         if (var3 == null) {
            return false;
         } else {
            Class var4 = this.getClass(var1);
            if (var4 != null) {
               return var3.isAssignableFrom(var4);
            } else {
               return !var3.isPrimitive() || var3 == Void.TYPE;
            }
         }
      } catch (ClassNotFoundException var5) {
         return false;
      }
   }

   public boolean accessible(String var1) throws ClassNotFoundException {
      Class var2 = this.getClass(var1);
      if (var2 == null) {
         return false;
      } else {
         return Modifier.isPublic(var2.getModifiers());
      }
   }

   public String getType(Member var1) {
      Class var2;
      if (var1 instanceof Method) {
         var2 = ((Method)var1).getReturnType();
      } else if (var1 instanceof Field) {
         var2 = ((Field)var1).getType();
      } else if (var1 instanceof Constructor) {
         var2 = ((Constructor)var1).getDeclaringClass();
      } else {
         var2 = Void.TYPE;
      }

      return readableClassName(var2);
   }

   public Member findPublicMethod(String var1, String var2, String[] var3, String[] var4, boolean[] var5, boolean var6, boolean var7, boolean var8) throws ClassNotFoundException, StandardException {
      Class var9 = this.getClass(var1);
      if (var9 == null) {
         return null;
      } else if (var9.isPrimitive()) {
         return null;
      } else if (var3 == null) {
         Method[] var16 = var9.getMethods();

         for(int var17 = 0; var17 < var16.length; ++var17) {
            if ((!var6 || Modifier.isStatic(var16[var17].getModifiers())) && (!var8 || this.isVarArgsMethod(var16[var17])) && var2.equals(var16[var17].getName())) {
               return var16[var17];
            }
         }

         return null;
      } else {
         Class[] var10 = new Class[var3.length];
         Class[] var11 = null;
         if (var4 != null) {
            var11 = new Class[var4.length];
         }

         for(int var12 = 0; var12 < var10.length; ++var12) {
            var10[var12] = this.getClass(var3[var12]);
            if (var4 != null) {
               if (var4[var12].equals(var3[var12])) {
                  var11[var12] = null;
               } else {
                  var11[var12] = this.getClass(var4[var12]);
               }
            }
         }

         if (var10.length == 0) {
            try {
               Method var19 = var9.getMethod(var2, var10);
               if (var6 && !Modifier.isStatic(var19.getModifiers())) {
                  return null;
               }

               return var19;
            } catch (NoSuchMethodException var15) {
               if (!var9.isInterface()) {
                  return null;
               }
            }
         }

         Object var18 = var9.getMethods();
         if (var9.isInterface()) {
            Method[] var13 = Object.class.getMethods();
            if (((Object[])var18).length == 0) {
               var18 = var13;
            } else {
               Member[] var14 = new Member[((Object[])var18).length + var13.length];
               System.arraycopy(var18, 0, var14, 0, ((Object[])var18).length);
               System.arraycopy(var13, 0, var14, ((Object[])var18).length, var13.length);
               var18 = var14;
            }
         }

         return this.resolveMethod(var9, var2, var10, var11, var5, var6, var7, (Member[])var18, var8);
      }
   }

   public Member findPublicField(String var1, String var2, boolean var3) throws StandardException {
      Object var4 = null;

      try {
         Class var5 = this.getClass(var1);
         if (var5 == null) {
            return null;
         }

         if (var5.isArray() || var5.isPrimitive()) {
            return null;
         }

         int var6 = var3 ? 9 : 1;
         Field var7 = var5.getField(var2);
         if ((var7.getModifiers() & var6) == var6) {
            if (var5.isInterface() || var7.getDeclaringClass().equals(var5)) {
               return var7;
            }

            try {
               var5.getDeclaredField(var2);
            } catch (NoSuchFieldException var9) {
               return var7;
            }
         }
      } catch (ClassNotFoundException var10) {
         var4 = var10;
      } catch (NoSuchFieldException var11) {
         var4 = var11;
      }

      throw StandardException.newException(var3 ? "42X72" : "42X68", (Throwable)var4, new Object[]{var2, var1});
   }

   public Member findPublicConstructor(String var1, String[] var2, String[] var3, boolean[] var4) throws ClassNotFoundException, StandardException {
      Class var5 = this.getClass(var1);
      if (var5 == null) {
         return null;
      } else if (!var5.isArray() && !var5.isPrimitive() && !var5.isInterface()) {
         Class[] var6 = new Class[var2.length];
         Class[] var7 = null;
         if (var3 != null) {
            var7 = new Class[var3.length];
         }

         boolean var8 = false;

         for(int var9 = 0; var9 < var6.length; ++var9) {
            var6[var9] = this.getClass(var2[var9]);
            if (var6[var9] == null) {
               var8 = true;
            }

            if (var3 != null) {
               if (var3[var9].equals(var2[var9])) {
                  var7[var9] = null;
               } else {
                  var7[var9] = this.getClass(var3[var9]);
               }
            }
         }

         try {
            if (!var8 && var3 == null) {
               Constructor var11 = var5.getConstructor(var6);
               return var11;
            }
         } catch (NoSuchMethodException var10) {
            if (var6.length == 0) {
               return null;
            }
         }

         return this.resolveMethod(var5, "<init>", var6, var7, var4, false, false, var5.getConstructors(), false);
      } else {
         return null;
      }
   }

   public Class[][] getTypeBounds(Class var1, Class var2) throws StandardException {
      if (var2 == null) {
         return null;
      } else {
         Type[] var3 = var2.getGenericInterfaces();

         for(Type var7 : var3) {
            if (var7 instanceof ParameterizedType) {
               ParameterizedType var8 = (ParameterizedType)var7;
               Type var9 = var8.getRawType();
               if (var1 == var9) {
                  return this.findTypeBounds(var8);
               }
            }
         }

         return this.getTypeBounds(var1, var2.getSuperclass());
      }
   }

   public boolean isVarArgsMethod(Member var1) {
      if (var1 instanceof Method) {
         return ((Method)var1).isVarArgs();
      } else {
         return var1 instanceof Constructor ? ((Constructor)var1).isVarArgs() : false;
      }
   }

   public Class[] getGenericParameterTypes(Class var1, Class var2) throws StandardException {
      ArrayList var3 = this.getTypeChain(var1, var2);
      HashMap var4 = this.getResolvedTypes(var3);
      ArrayList var5 = this.getParameterTypes(var1, var4);
      return var5 == null ? null : (Class[])var5.toArray(new Class[var5.size()]);
   }

   public String[] getParameterTypes(Member var1) {
      Class[] var2;
      if (var1 instanceof Method) {
         var2 = ((Method)var1).getParameterTypes();
      } else {
         var2 = ((Constructor)var1).getParameterTypes();
      }

      String[] var3 = new String[var2.length];

      for(int var4 = 0; var4 < var3.length; ++var4) {
         var3[var4] = readableClassName(var2[var4]);
      }

      return var3;
   }

   public static boolean primitiveType(String var0) {
      for(int var1 = 0; var1 < primTypeNames.length; ++var1) {
         if (var0.equals(primTypeNames[var1])) {
            return true;
         }
      }

      return false;
   }

   private Member resolveMethod(Class var1, String var2, Class[] var3, Class[] var4, boolean[] var5, boolean var6, boolean var7, Member[] var8, boolean var9) throws StandardException {
      int var10 = -1;
      boolean var11 = true;

      boolean var12;
      boolean var13;
      do {
         var12 = false;
         var13 = false;

         label109:
         for(int var14 = 0; var14 < var8.length; ++var14) {
            Member var15 = var8[var14];
            if (var15 != null && var14 != var10) {
               Class[] var16 = var15 instanceof Method ? ((Method)var15).getParameterTypes() : ((Constructor)var15).getParameterTypes();
               if (var11) {
                  if (var7) {
                     if (var16.length < var3.length) {
                        var8[var14] = null;
                        continue;
                     }
                  } else if (var16.length != var3.length) {
                     var8[var14] = null;
                     continue;
                  }

                  if (var6 && !Modifier.isStatic(var15.getModifiers())) {
                     var8[var14] = null;
                     continue;
                  }

                  if (var9 && !this.isVarArgsMethod(var15)) {
                     var8[var14] = null;
                     continue;
                  }

                  if (!var2.startsWith("<") && !var2.equals(var15.getName())) {
                     var8[var14] = null;
                     continue;
                  }

                  if (var7) {
                     for(int var17 = var3.length - 1; var17 < var16.length; ++var17) {
                        if (!var16[var17].equals(var3[var3.length - 1])) {
                           var8[var14] = null;
                           continue label109;
                        }
                     }
                  }
               }

               if (!this.signatureConvertableFromTo(var3, var4, var16, var5, true)) {
                  var8[var14] = null;
               } else if (var10 == -1) {
                  var10 = var14;
               } else {
                  var12 = true;
               }
            }
         }

         var11 = false;
      } while(var12 && var13);

      if (var12) {
         StringBuffer var18 = new StringBuffer();

         for(int var19 = 0; var19 < var3.length; ++var19) {
            if (var19 != 0) {
               var18.append(", ");
            }

            var18.append(var3[var19] == null ? "null" : var3[var19].getName());
            if (var4 != null && var4[var19] != null) {
               var18.append("(").append(var4[var19].getName()).append(")");
            }
         }

         throw StandardException.newException("42X73", new Object[]{var1.getName(), var2, var18.toString()});
      } else {
         return var10 == -1 ? null : var8[var10];
      }
   }

   public Class getClass(String var1) throws ClassNotFoundException {
      if (var1 != null && var1.length() != 0) {
         int var2 = 0;
         int var3 = var1.length();

         for(int var4 = var3 - 2; var4 >= 0 && var1.substring(var4, var4 + 2).equals("[]"); var3 -= 2) {
            ++var2;
            var4 -= 2;
         }

         if (var3 <= 0) {
            return Class.forName(var1);
         } else {
            if (var2 != 0) {
               var1 = var1.substring(0, var3);
            }

            Class var5 = null;
            if (var3 >= 3 && var3 <= 7) {
               if ("int".equals(var1)) {
                  var5 = Integer.TYPE;
               } else if ("short".equals(var1)) {
                  var5 = Short.TYPE;
               } else if ("boolean".equals(var1)) {
                  var5 = Boolean.TYPE;
               } else if ("byte".equals(var1)) {
                  var5 = Byte.TYPE;
               } else if ("float".equals(var1)) {
                  var5 = Float.TYPE;
               } else if ("double".equals(var1)) {
                  var5 = Double.TYPE;
               } else if ("long".equals(var1)) {
                  var5 = Long.TYPE;
               } else if ("char".equals(var1)) {
                  var5 = Character.TYPE;
               } else if ("void".equals(var1)) {
                  var5 = Void.TYPE;
               }
            }

            if (var5 == null) {
               var5 = this.cf.loadApplicationClass(var1);
            }

            if (var2 == 0) {
               return var5;
            } else {
               return var2 == 1 ? Array.newInstance(var5, 0).getClass() : Array.newInstance(var5, new int[var2]).getClass();
            }
         }
      } else {
         return null;
      }
   }

   private boolean signatureConvertableFromTo(Class[] var1, Class[] var2, Class[] var3, boolean[] var4, boolean var5) {
      int var6 = var1.length;
      if (var3.length < var6) {
         var6 = var3.length;
      }

      for(int var7 = 0; var7 < var6; ++var7) {
         Class var8 = var1[var7];
         Class var9 = var3[var7];
         if (var8 == null) {
            if (var9.isPrimitive() && (var2 == null || var4 != null && !var4[var7])) {
               return false;
            }
         } else if (!this.classConvertableFromTo(var8, var9, var5) && (var2 == null || var2[var7] == null || !this.classConvertableFromTo(var2[var7], var9, var5))) {
            return false;
         }
      }

      return true;
   }

   protected boolean classConvertableFromTo(Class var1, Class var2, boolean var3) {
      if (var1.getName().equals(var2.getName())) {
         return true;
      } else if (var1.isArray() && var2.isArray()) {
         return this.classConvertableFromTo(var1.getComponentType(), var2.getComponentType(), var3);
      } else if ((!var2.isPrimitive() || !var1.isPrimitive()) && !var3) {
         return false;
      } else {
         String var4 = var1.getName();
         String var5 = var2.getName();
         if (var1 != Boolean.TYPE && !var4.equals(nonPrimTypeNames[0])) {
            if (var1 != Byte.TYPE && !var4.equals(nonPrimTypeNames[1])) {
               if (var1 != Character.TYPE && !var4.equals(nonPrimTypeNames[2])) {
                  if (var1 != Short.TYPE && !var4.equals(nonPrimTypeNames[3])) {
                     if (var1 != Integer.TYPE && !var4.equals(nonPrimTypeNames[4])) {
                        if (var1 != Long.TYPE && !var4.equals(nonPrimTypeNames[5])) {
                           if (var1 != Float.TYPE && !var4.equals(nonPrimTypeNames[6])) {
                              if ((var1 == Double.TYPE || var4.equals(nonPrimTypeNames[7])) && (var2 == Double.TYPE || var5.equals(nonPrimTypeNames[7]))) {
                                 return true;
                              }
                           } else if (var2 == Float.TYPE || var5.equals(nonPrimTypeNames[6])) {
                              return true;
                           }
                        } else if (var2 == Long.TYPE || var5.equals(nonPrimTypeNames[5])) {
                           return true;
                        }
                     } else if (var2 == Integer.TYPE || var5.equals(nonPrimTypeNames[4])) {
                        return true;
                     }
                  } else if (var2 == Short.TYPE || var5.equals(nonPrimTypeNames[4])) {
                     return true;
                  }
               } else if (var2 == Character.TYPE || var5.equals(nonPrimTypeNames[2]) || var2 == Integer.TYPE || var2 == Long.TYPE || var2 == Float.TYPE || var2 == Double.TYPE) {
                  return true;
               }
            } else if (var2 == Byte.TYPE || var5.equals(nonPrimTypeNames[1]) || var2 == Short.TYPE || var2 == Integer.TYPE || var2 == Long.TYPE || var2 == Float.TYPE || var2 == Double.TYPE) {
               return true;
            }
         } else if (var2 == Boolean.TYPE || var5.equals(nonPrimTypeNames[0])) {
            return true;
         }

         return false;
      }
   }

   public static String readableClassName(Class var0) {
      if (!var0.isArray()) {
         return var0.getName();
      } else {
         int var1 = 0;

         do {
            ++var1;
            var0 = var0.getComponentType();
         } while(var0.isArray());

         StringBuffer var2 = new StringBuffer(var0.getName());

         for(int var3 = 0; var3 < var1; ++var3) {
            var2.append("[]");
         }

         return var2.toString();
      }
   }

   public String getDeclaringClass(Member var1) {
      return var1.getDeclaringClass().getName();
   }

   private Class[][] findTypeBounds(ParameterizedType var1) {
      Type[] var2 = var1.getActualTypeArguments();
      int var3 = var2.length;
      Class[][] var4 = new Class[var3][];

      for(int var5 = 0; var5 < var3; ++var5) {
         var4[var5] = this.boundType(var2[var5]);
      }

      return var4;
   }

   private Class[] boundType(Type var1) {
      if (var1 instanceof Class) {
         return new Class[]{(Class)var1};
      } else if (!(var1 instanceof TypeVariable)) {
         return null;
      } else {
         Type[] var2 = ((TypeVariable)var1).getBounds();
         int var3 = var2.length;
         Class[] var4 = new Class[var3];

         for(int var5 = 0; var5 < var3; ++var5) {
            var4[var5] = this.getRawType(var2[var5]);
         }

         return var4;
      }
   }

   private Class getRawType(Type var1) {
      if (var1 instanceof Class) {
         return (Class)var1;
      } else {
         return var1 instanceof ParameterizedType ? this.getRawType(((ParameterizedType)var1).getRawType()) : null;
      }
   }

   private ArrayList getTypeChain(Class var1, Class var2) {
      ArrayList var3 = null;
      if (var2 == null) {
         return null;
      } else if (!var1.isAssignableFrom(var2)) {
         return null;
      } else {
         if (var2 == var1) {
            var3 = new ArrayList();
         }

         if (var3 == null) {
            var3 = this.getTypeChain(var1, var2.getSuperclass());
            if (var3 == null) {
               for(Class var7 : var2.getInterfaces()) {
                  var3 = this.getTypeChain(var1, var7);
                  if (var3 != null) {
                     break;
                  }
               }
            }
         }

         if (var3 != null) {
            var3.add(var2);
         }

         return var3;
      }
   }

   private HashMap getResolvedTypes(ArrayList var1) {
      if (var1 == null) {
         return null;
      } else {
         HashMap var2 = new HashMap();

         for(Class var4 : var1) {
            this.addResolvedTypes(var2, var4.getGenericSuperclass());

            for(Type var8 : var4.getGenericInterfaces()) {
               this.addResolvedTypes(var2, var8);
            }
         }

         return var2;
      }
   }

   private void addResolvedTypes(HashMap var1, Type var2) {
      if (var2 != null) {
         if (var2 instanceof ParameterizedType) {
            ParameterizedType var3 = (ParameterizedType)var2;
            Class var4 = (Class)var3.getRawType();
            Type[] var5 = var3.getActualTypeArguments();
            TypeVariable[] var6 = var4.getTypeParameters();

            for(int var7 = 0; var7 < var5.length; ++var7) {
               var1.put(var6[var7], var5[var7]);
            }
         }

      }
   }

   private ArrayList getParameterTypes(Class var1, HashMap var2) {
      if (var2 == null) {
         return null;
      } else {
         TypeVariable[] var3 = var1.getTypeParameters();
         ArrayList var4 = new ArrayList();
         TypeVariable[] var5 = var3;
         int var6 = var3.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            Object var8;
            for(var8 = var5[var7]; var2.containsKey(var8); var8 = (Type)var2.get(var8)) {
            }

            var4.add(this.getRawType((Type)var8));
         }

         return var4;
      }
   }
}
