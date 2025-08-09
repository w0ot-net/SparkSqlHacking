package org.glassfish.jaxb.core.v2.model.nav;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Collection;
import org.glassfish.jaxb.core.v2.runtime.Location;

final class ReflectionNavigator implements Navigator {
   private static final ReflectionNavigator INSTANCE = new ReflectionNavigator();
   private static final TypeVisitor baseClassFinder = new TypeVisitor() {
      public Type onClass(Class c, Class sup) {
         if (sup == c) {
            return sup;
         } else {
            Type sc = c.getGenericSuperclass();
            if (sc != null) {
               Type r = (Type)this.visit(sc, sup);
               if (r != null) {
                  return r;
               }
            }

            for(Type i : c.getGenericInterfaces()) {
               Type r = (Type)this.visit(i, sup);
               if (r != null) {
                  return r;
               }
            }

            return null;
         }
      }

      public Type onParameterizdType(ParameterizedType p, Class sup) {
         Class<?> raw = (Class)p.getRawType();
         if (raw == sup) {
            return p;
         } else {
            Type r = raw.getGenericSuperclass();
            if (r != null) {
               r = (Type)this.visit(this.bind(r, raw, p), sup);
            }

            if (r != null) {
               return r;
            } else {
               for(Type i : raw.getGenericInterfaces()) {
                  r = (Type)this.visit(this.bind(i, raw, p), sup);
                  if (r != null) {
                     return r;
                  }
               }

               return null;
            }
         }
      }

      public Type onGenericArray(GenericArrayType g, Class sup) {
         return null;
      }

      public Type onVariable(TypeVariable v, Class sup) {
         return (Type)this.visit(v.getBounds()[0], sup);
      }

      public Type onWildcard(WildcardType w, Class sup) {
         return null;
      }

      private Type bind(Type t, GenericDeclaration decl, ParameterizedType args) {
         return (Type)ReflectionNavigator.binder.visit(t, new BinderArg(decl, args.getActualTypeArguments()));
      }
   };
   private static final TypeVisitor binder = new TypeVisitor() {
      public Type onClass(Class c, BinderArg args) {
         return c;
      }

      public Type onParameterizdType(ParameterizedType p, BinderArg args) {
         Type[] params = p.getActualTypeArguments();
         boolean different = false;

         for(int i = 0; i < params.length; ++i) {
            Type t = params[i];
            params[i] = (Type)this.visit(t, args);
            different |= t != params[i];
         }

         Type newOwner = p.getOwnerType();
         if (newOwner != null) {
            newOwner = (Type)this.visit(newOwner, args);
         }

         different |= p.getOwnerType() != newOwner;
         if (!different) {
            return p;
         } else {
            return new ParameterizedTypeImpl((Class)p.getRawType(), params, newOwner);
         }
      }

      public Type onGenericArray(GenericArrayType g, BinderArg types) {
         Type c = (Type)this.visit(g.getGenericComponentType(), types);
         return (Type)(c == g.getGenericComponentType() ? g : new GenericArrayTypeImpl(c));
      }

      public Type onVariable(TypeVariable v, BinderArg types) {
         return types.replace(v);
      }

      public Type onWildcard(WildcardType w, BinderArg types) {
         Type[] lb = w.getLowerBounds();
         Type[] ub = w.getUpperBounds();
         boolean diff = false;

         for(int i = 0; i < lb.length; ++i) {
            Type t = lb[i];
            lb[i] = (Type)this.visit(t, types);
            diff |= t != lb[i];
         }

         for(int i = 0; i < ub.length; ++i) {
            Type t = ub[i];
            ub[i] = (Type)this.visit(t, types);
            diff |= t != ub[i];
         }

         if (!diff) {
            return w;
         } else {
            return new WildcardTypeImpl(lb, ub);
         }
      }
   };
   private static final TypeVisitor eraser = new TypeVisitor() {
      public Class onClass(Class c, Void v) {
         return c;
      }

      public Class onParameterizdType(ParameterizedType p, Void v) {
         return (Class)this.visit(p.getRawType(), (Object)null);
      }

      public Class onGenericArray(GenericArrayType g, Void v) {
         return Array.newInstance((Class)this.visit(g.getGenericComponentType(), (Object)null), 0).getClass();
      }

      public Class onVariable(TypeVariable tv, Void v) {
         return (Class)this.visit(tv.getBounds()[0], (Object)null);
      }

      public Class onWildcard(WildcardType w, Void v) {
         return (Class)this.visit(w.getUpperBounds()[0], (Object)null);
      }
   };

   static ReflectionNavigator getInstance() {
      return INSTANCE;
   }

   private ReflectionNavigator() {
   }

   public Class getSuperClass(Class clazz) {
      if (clazz == Object.class) {
         return null;
      } else {
         Class<?> sc = clazz.getSuperclass();
         if (sc == null) {
            sc = Object.class;
         }

         return sc;
      }
   }

   public Type getBaseClass(Type t, Class sup) {
      return (Type)baseClassFinder.visit(t, sup);
   }

   public String getClassName(Class clazz) {
      return clazz.getName();
   }

   public String getTypeName(Type type) {
      if (type instanceof Class) {
         Class<?> c = (Class)type;
         if (c.isArray()) {
            String var10000 = this.getTypeName((Type)c.getComponentType());
            return var10000 + "[]";
         } else {
            return c.getName();
         }
      } else {
         return type.toString();
      }
   }

   public String getClassShortName(Class clazz) {
      return clazz.getSimpleName();
   }

   public Collection getDeclaredFields(final Class clazz) {
      Field[] fields = (Field[])AccessController.doPrivileged(new PrivilegedAction() {
         public Field[] run() {
            return clazz.getDeclaredFields();
         }
      });
      return Arrays.asList(fields);
   }

   public Field getDeclaredField(final Class clazz, final String fieldName) {
      return (Field)AccessController.doPrivileged(new PrivilegedAction() {
         public Field run() {
            try {
               return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException var2) {
               return null;
            }
         }
      });
   }

   public Collection getDeclaredMethods(final Class clazz) {
      Method[] methods = (Method[])AccessController.doPrivileged(new PrivilegedAction() {
         public Method[] run() {
            return clazz.getDeclaredMethods();
         }
      });
      return Arrays.asList(methods);
   }

   public Class getDeclaringClassForField(Field field) {
      return field.getDeclaringClass();
   }

   public Class getDeclaringClassForMethod(Method method) {
      return method.getDeclaringClass();
   }

   public Type getFieldType(Field field) {
      if (field.getType().isArray()) {
         Class<?> c = field.getType().getComponentType();
         if (c.isPrimitive()) {
            return Array.newInstance(c, 0).getClass();
         }
      }

      return this.fix(field.getGenericType());
   }

   public String getFieldName(Field field) {
      return field.getName();
   }

   public String getMethodName(Method method) {
      return method.getName();
   }

   public Type getReturnType(Method method) {
      return this.fix(method.getGenericReturnType());
   }

   public Type[] getMethodParameters(Method method) {
      return method.getGenericParameterTypes();
   }

   public boolean isStaticMethod(Method method) {
      return Modifier.isStatic(method.getModifiers());
   }

   public boolean isFinalMethod(Method method) {
      return Modifier.isFinal(method.getModifiers());
   }

   public boolean isSubClassOf(Type sub, Type sup) {
      return this.erasure(sup).isAssignableFrom(this.erasure(sub));
   }

   public Class ref(Class c) {
      return c;
   }

   public Class use(Class c) {
      return c;
   }

   public Class asDecl(Type t) {
      return this.erasure(t);
   }

   public Class asDecl(Class c) {
      return c;
   }

   public Class erasure(Type t) {
      return (Class)eraser.visit(t, (Object)null);
   }

   public boolean isAbstract(Class clazz) {
      return Modifier.isAbstract(clazz.getModifiers());
   }

   public boolean isFinal(Class clazz) {
      return Modifier.isFinal(clazz.getModifiers());
   }

   public Type createParameterizedType(Class rawType, Type... arguments) {
      return new ParameterizedTypeImpl(rawType, arguments, (Type)null);
   }

   public boolean isArray(Type t) {
      if (t instanceof Class) {
         Class<?> c = (Class)t;
         return c.isArray();
      } else {
         return t instanceof GenericArrayType;
      }
   }

   public boolean isArrayButNotByteArray(Type t) {
      if (!(t instanceof Class)) {
         if (t instanceof GenericArrayType) {
            t = ((GenericArrayType)t).getGenericComponentType();
            return t != Byte.TYPE;
         } else {
            return false;
         }
      } else {
         Class<?> c = (Class)t;
         return c.isArray() && c != byte[].class;
      }
   }

   public Type getComponentType(Type t) {
      if (t instanceof Class) {
         Class<?> c = (Class)t;
         return c.getComponentType();
      } else if (t instanceof GenericArrayType) {
         return ((GenericArrayType)t).getGenericComponentType();
      } else {
         throw new IllegalArgumentException();
      }
   }

   public Type getTypeArgument(Type type, int i) {
      if (type instanceof ParameterizedType) {
         ParameterizedType p = (ParameterizedType)type;
         return this.fix(p.getActualTypeArguments()[i]);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public boolean isParameterizedType(Type type) {
      return type instanceof ParameterizedType;
   }

   public boolean isPrimitive(Type type) {
      if (type instanceof Class) {
         Class<?> c = (Class)type;
         return c.isPrimitive();
      } else {
         return false;
      }
   }

   public Type getPrimitive(Class primitiveType) {
      assert primitiveType.isPrimitive();

      return primitiveType;
   }

   public Location getClassLocation(final Class clazz) {
      return new Location() {
         public String toString() {
            return clazz.getName();
         }
      };
   }

   public Location getFieldLocation(final Field field) {
      return new Location() {
         public String toString() {
            return field.toString();
         }
      };
   }

   public Location getMethodLocation(final Method method) {
      return new Location() {
         public String toString() {
            return method.toString();
         }
      };
   }

   public boolean hasDefaultConstructor(Class c) {
      try {
         c.getDeclaredConstructor();
         return true;
      } catch (NoSuchMethodException var3) {
         return false;
      }
   }

   public boolean isStaticField(Field field) {
      return Modifier.isStatic(field.getModifiers());
   }

   public boolean isPublicMethod(Method method) {
      return Modifier.isPublic(method.getModifiers());
   }

   public boolean isPublicField(Field field) {
      return Modifier.isPublic(field.getModifiers());
   }

   public boolean isEnum(Class c) {
      return Enum.class.isAssignableFrom(c);
   }

   public Field[] getEnumConstants(Class clazz) {
      try {
         Object[] values = clazz.getEnumConstants();
         Field[] fields = new Field[values.length];

         for(int i = 0; i < values.length; ++i) {
            fields[i] = clazz.getField(((Enum)values[i]).name());
         }

         return fields;
      } catch (NoSuchFieldException e) {
         throw new NoSuchFieldError(e.getMessage());
      }
   }

   public Type getVoidType() {
      return Void.class;
   }

   public String getPackageName(Class clazz) {
      String name = clazz.getName();
      int idx = name.lastIndexOf(46);
      return idx < 0 ? "" : name.substring(0, idx);
   }

   public Class loadObjectFactory(Class referencePoint, String pkg) {
      ClassLoader cl = SecureLoader.getClassClassLoader(referencePoint);
      if (cl == null) {
         cl = SecureLoader.getSystemClassLoader();
      }

      try {
         return cl.loadClass(pkg + ".ObjectFactory");
      } catch (ClassNotFoundException var5) {
         return null;
      }
   }

   public boolean isBridgeMethod(Method method) {
      return method.isBridge();
   }

   public boolean isOverriding(Method method, final Class base) {
      final String name = method.getName();
      final Class[] params = method.getParameterTypes();
      return (Boolean)AccessController.doPrivileged(new PrivilegedAction() {
         public Boolean run() {
            for(Class<?> clazz = base; clazz != null; clazz = clazz.getSuperclass()) {
               try {
                  Method m = clazz.getDeclaredMethod(name, params);
                  if (m != null) {
                     return Boolean.TRUE;
                  }
               } catch (NoSuchMethodException var3) {
               }
            }

            return Boolean.FALSE;
         }
      });
   }

   public boolean isInterface(Class clazz) {
      return clazz.isInterface();
   }

   public boolean isTransient(Field f) {
      return Modifier.isTransient(f.getModifiers());
   }

   public boolean isInnerClass(Class clazz) {
      return clazz.getEnclosingClass() != null && !Modifier.isStatic(clazz.getModifiers());
   }

   public boolean isSameType(Type t1, Type t2) {
      return t1.equals(t2);
   }

   private Type fix(Type t) {
      if (!(t instanceof GenericArrayType)) {
         return t;
      } else {
         GenericArrayType gat = (GenericArrayType)t;
         if (gat.getGenericComponentType() instanceof Class) {
            Class<?> c = (Class)gat.getGenericComponentType();
            return Array.newInstance(c, 0).getClass();
         } else {
            return t;
         }
      }
   }

   private static class BinderArg {
      final TypeVariable[] params;
      final Type[] args;

      BinderArg(TypeVariable[] params, Type[] args) {
         this.params = params;
         this.args = args;

         assert params.length == args.length;

      }

      public BinderArg(GenericDeclaration decl, Type[] args) {
         this(decl.getTypeParameters(), args);
      }

      Type replace(TypeVariable v) {
         for(int i = 0; i < this.params.length; ++i) {
            if (this.params[i].equals(v)) {
               return this.args[i];
            }
         }

         return v;
      }
   }
}
