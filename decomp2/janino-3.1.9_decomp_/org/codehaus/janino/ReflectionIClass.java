package org.codehaus.janino;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.InternalCompilerException;
import org.codehaus.commons.compiler.Location;
import org.codehaus.commons.nullanalysis.Nullable;

class ReflectionIClass extends IClass {
   private final Class clazz;
   private final IClassLoader iClassLoader;

   ReflectionIClass(Class clazz, IClassLoader iClassLoader) {
      this.clazz = clazz;
      clazz.getTypeParameters();
      this.iClassLoader = iClassLoader;
   }

   public ITypeVariable[] getITypeVariables2() {
      TypeVariable<?>[] tps = this.clazz.getTypeParameters();
      ITypeVariable[] result = new ITypeVariable[tps.length];

      for(int i = 0; i < result.length; ++i) {
         result[i] = this.typeVariableToITypeVariable(tps[i]);
      }

      return result;
   }

   private ITypeVariable typeVariableToITypeVariable(final TypeVariable tv) {
      return new ITypeVariable() {
         public String getName() {
            return tv.getName();
         }

         public ITypeVariableOrIClass[] getBounds() throws CompileException {
            IType[] tmp = ReflectionIClass.this.typesToITypes(tv.getBounds());
            return (ITypeVariableOrIClass[])Arrays.copyOf(tmp, tmp.length, ITypeVariableOrIClass[].class);
         }
      };
   }

   private IType[] typesToITypes(Type[] types) throws CompileException {
      IType[] result = new IType[types.length];

      for(int i = 0; i < result.length; ++i) {
         result[i] = this.typeToIType(types[i]);
      }

      return result;
   }

   private IType typeToIType(Type type) throws CompileException {
      if (type instanceof Class) {
         IClass iClass;
         try {
            iClass = this.iClassLoader.loadIClass(Descriptor.fromClassName(((Class)type).getName()));
         } catch (ClassNotFoundException cnfe) {
            throw new CompileException("Loading \"" + type + "\"", (Location)null, cnfe);
         }

         if (iClass == null) {
            throw new CompileException("Could not load \"" + type + "\"", (Location)null);
         } else {
            return iClass;
         }
      } else if (type instanceof GenericArrayType) {
         throw new AssertionError("NYI");
      } else if (type instanceof ParameterizedType) {
         throw new AssertionError("NYI");
      } else if (type instanceof TypeVariable) {
         throw new AssertionError("NYI");
      } else if (type instanceof WildcardType) {
         throw new AssertionError("NYI");
      } else {
         throw new AssertionError(type.getClass());
      }
   }

   protected IClass.IConstructor[] getDeclaredIConstructors2() {
      Constructor<?>[] constructors = this.clazz.getDeclaredConstructors();
      IClass.IConstructor[] result = new IClass.IConstructor[constructors.length];

      for(int i = 0; i < constructors.length; ++i) {
         result[i] = new ReflectionIConstructor(constructors[i]);
      }

      return result;
   }

   protected IClass.IMethod[] getDeclaredIMethods2() {
      Method[] methods = this.clazz.getDeclaredMethods();
      return methods.length == 0 && this.clazz.isArray() ? new IClass.IMethod[]{new IClass.IMethod() {
         public IClass.IAnnotation[] getAnnotations() {
            return new IClass.IAnnotation[0];
         }

         public Access getAccess() {
            return Access.PUBLIC;
         }

         public boolean isStatic() {
            return false;
         }

         public boolean isAbstract() {
            return false;
         }

         public String getName() {
            return "clone";
         }

         public IClass[] getParameterTypes2() {
            return new IClass[0];
         }

         public boolean isVarargs() {
            return false;
         }

         public IClass[] getThrownExceptions2() {
            return new IClass[0];
         }

         public IClass getReturnType() {
            return ReflectionIClass.this.iClassLoader.TYPE_java_lang_Object;
         }
      }} : this.methodsToIMethods(methods);
   }

   protected IClass.IField[] getDeclaredIFields2() {
      return this.fieldsToIFields(this.clazz.getDeclaredFields());
   }

   protected IClass[] getDeclaredIClasses2() {
      return this.classesToIClasses(this.clazz.getDeclaredClasses());
   }

   @Nullable
   protected IClass getDeclaringIClass2() {
      Class<?> declaringClass = this.clazz.getDeclaringClass();
      return declaringClass == null ? null : this.classToIClass(declaringClass);
   }

   @Nullable
   protected IClass getOuterIClass2() throws CompileException {
      return Modifier.isStatic(this.clazz.getModifiers()) ? null : this.getDeclaringIClass();
   }

   @Nullable
   protected IClass getSuperclass2() {
      Class<?> superclass = this.clazz.getSuperclass();
      return superclass == null ? null : this.classToIClass(superclass);
   }

   @Nullable
   protected IClass getComponentType2() {
      Class<?> componentType = this.clazz.getComponentType();
      return componentType == null ? null : this.classToIClass(componentType);
   }

   protected IClass[] getInterfaces2() {
      return this.classesToIClasses(this.clazz.getInterfaces());
   }

   protected String getDescriptor2() {
      return Descriptor.fromClassName(this.clazz.getName());
   }

   public Access getAccess() {
      return modifiers2Access(this.clazz.getModifiers());
   }

   public boolean isFinal() {
      return Modifier.isFinal(this.clazz.getModifiers());
   }

   public boolean isEnum() {
      return this.clazz.isEnum();
   }

   public boolean isInterface() {
      return this.clazz.isInterface();
   }

   public boolean isAbstract() {
      return Modifier.isAbstract(this.clazz.getModifiers());
   }

   public boolean isArray() {
      return this.clazz.isArray();
   }

   public boolean isPrimitive() {
      return this.clazz.isPrimitive();
   }

   public boolean isPrimitiveNumeric() {
      return this.clazz == Byte.TYPE || this.clazz == Short.TYPE || this.clazz == Integer.TYPE || this.clazz == Long.TYPE || this.clazz == Character.TYPE || this.clazz == Float.TYPE || this.clazz == Double.TYPE;
   }

   public IClass.IAnnotation[] getIAnnotations2() throws CompileException {
      Annotation[] as = this.clazz.getAnnotations();
      if (as.length == 0) {
         return IClass.NO_ANNOTATIONS;
      } else {
         IClass.IAnnotation[] result = new IClass.IAnnotation[as.length];

         for(int i = 0; i < as.length; ++i) {
            final Annotation a = as[i];
            final Class<? extends Annotation> annotationType = a.annotationType();

            final IClass annotationTypeIClass;
            try {
               annotationTypeIClass = this.iClassLoader.loadIClass(Descriptor.fromClassName(annotationType.getName()));
            } catch (ClassNotFoundException cnfe) {
               throw new CompileException("Loading annotation type", (Location)null, cnfe);
            }

            if (annotationTypeIClass == null) {
               throw new CompileException("Could not load \"" + annotationType.getName() + "\"", (Location)null);
            }

            result[i] = new IClass.IAnnotation() {
               public IClass getAnnotationType() {
                  return annotationTypeIClass;
               }

               public Object getElementValue(String name) throws CompileException {
                  try {
                     Object v = a.getClass().getMethod(name).invoke(a);
                     if (!Enum.class.isAssignableFrom(v.getClass())) {
                        return v;
                     } else {
                        Class<?> enumClass = v.getClass();
                        String enumConstantName = (String)enumClass.getMethod("name").invoke(v);
                        IClass enumIClass = ReflectionIClass.this.classToIClass(enumClass);
                        IClass.IField enumConstField = enumIClass.getDeclaredIField(enumConstantName);
                        if (enumConstField == null) {
                           throw new CompileException("Enum \"" + enumIClass + "\" has no constant \"" + enumConstantName + "", (Location)null);
                        } else {
                           return enumConstField;
                        }
                     }
                  } catch (NoSuchMethodException var7) {
                     throw new CompileException("Annotation \"" + annotationType.getName() + "\" has no element \"" + name + "\"", (Location)null);
                  } catch (Exception e) {
                     throw new AssertionError(e);
                  }
               }

               public String toString() {
                  return "@" + annotationTypeIClass;
               }
            };
         }

         return result;
      }
   }

   public Class getClazz() {
      return this.clazz;
   }

   public String toString() {
      int brackets = 0;

      Class<?> c;
      for(c = this.clazz; c.isArray(); c = c.getComponentType()) {
         ++brackets;
      }

      String s;
      for(s = c.getName(); brackets-- > 0; s = s + "[]") {
      }

      return s;
   }

   private IClass classToIClass(Class c) {
      IClass iClass;
      try {
         iClass = this.iClassLoader.loadIClass(Descriptor.fromClassName(c.getName()));
      } catch (ClassNotFoundException ex) {
         throw new InternalCompilerException("Loading IClass \"" + c.getName() + "\": " + ex);
      }

      if (iClass == null) {
         throw new InternalCompilerException("Cannot load class \"" + c.getName() + "\" through the given ClassLoader");
      } else {
         return iClass;
      }
   }

   private IClass[] classesToIClasses(Class[] cs) {
      IClass[] result = new IClass[cs.length];

      for(int i = 0; i < cs.length; ++i) {
         result[i] = this.classToIClass(cs[i]);
      }

      return result;
   }

   private IClass.IMethod[] methodsToIMethods(Method[] methods) {
      IClass.IMethod[] result = new IClass.IMethod[methods.length];

      for(int i = 0; i < result.length; ++i) {
         result[i] = new ReflectionIMethod(methods[i]);
      }

      return result;
   }

   private IClass.IField[] fieldsToIFields(Field[] fields) {
      IClass.IField[] result = new IClass.IField[fields.length];

      for(int i = 0; i < fields.length; ++i) {
         result[i] = new ReflectionIField(fields[i]);
      }

      return result;
   }

   private static Access modifiers2Access(int modifiers) {
      return Modifier.isPrivate(modifiers) ? Access.PRIVATE : (Modifier.isProtected(modifiers) ? Access.PROTECTED : (Modifier.isPublic(modifiers) ? Access.PUBLIC : Access.DEFAULT));
   }

   private class ReflectionIConstructor extends IClass.IConstructor {
      final Constructor constructor;

      ReflectionIConstructor(Constructor constructor) {
         this.constructor = constructor;
      }

      public Access getAccess() {
         return ReflectionIClass.modifiers2Access(this.constructor.getModifiers());
      }

      public IClass.IAnnotation[] getAnnotations() {
         return new IClass.IAnnotation[0];
      }

      public boolean isVarargs() {
         return Modifier.isTransient(this.constructor.getModifiers());
      }

      public IClass[] getParameterTypes2() throws CompileException {
         IClass[] parameterTypes = ReflectionIClass.this.classesToIClasses(this.constructor.getParameterTypes());
         IClass outerClass = ReflectionIClass.this.getOuterIClass();
         if (outerClass != null) {
            if (parameterTypes.length < 1) {
               throw new CompileException("Constructor \"" + this.constructor + "\" lacks synthetic enclosing instance parameter", (Location)null);
            }

            if (parameterTypes[0] != outerClass) {
               throw new CompileException("Enclosing instance parameter of constructor \"" + this.constructor + "\" has wrong type -- \"" + parameterTypes[0] + "\" vs. \"" + outerClass + "\"", (Location)null);
            }

            IClass[] tmp = new IClass[parameterTypes.length - 1];
            System.arraycopy(parameterTypes, 1, tmp, 0, tmp.length);
            parameterTypes = tmp;
         }

         return parameterTypes;
      }

      public MethodDescriptor getDescriptor2() {
         Class<?>[] parameterTypes = this.constructor.getParameterTypes();
         String[] parameterDescriptors = new String[parameterTypes.length];

         for(int i = 0; i < parameterDescriptors.length; ++i) {
            parameterDescriptors[i] = Descriptor.fromClassName(parameterTypes[i].getName());
         }

         return new MethodDescriptor("V", parameterDescriptors);
      }

      public IClass[] getThrownExceptions2() {
         return ReflectionIClass.this.classesToIClasses(this.constructor.getExceptionTypes());
      }
   }

   public class ReflectionIMethod extends IClass.IMethod {
      private final Method method;

      ReflectionIMethod(Method method) {
         this.method = method;
      }

      public Access getAccess() {
         return ReflectionIClass.modifiers2Access(this.method.getModifiers());
      }

      public IClass.IAnnotation[] getAnnotations() {
         return new IClass.IAnnotation[0];
      }

      public String getName() {
         return this.method.getName();
      }

      public boolean isVarargs() {
         return Modifier.isTransient(this.method.getModifiers());
      }

      public IClass[] getParameterTypes2() {
         return ReflectionIClass.this.classesToIClasses(this.method.getParameterTypes());
      }

      public boolean isStatic() {
         return Modifier.isStatic(this.method.getModifiers());
      }

      public boolean isAbstract() {
         return Modifier.isAbstract(this.method.getModifiers());
      }

      public IClass getReturnType() {
         return ReflectionIClass.this.classToIClass(this.method.getReturnType());
      }

      public IClass[] getThrownExceptions2() {
         return ReflectionIClass.this.classesToIClasses(this.method.getExceptionTypes());
      }
   }

   private class ReflectionIField extends IClass.IField {
      final Field field;

      ReflectionIField(Field field) {
         this.field = field;
      }

      public Access getAccess() {
         return ReflectionIClass.modifiers2Access(this.field.getModifiers());
      }

      public IClass.IAnnotation[] getAnnotations() {
         return new IClass.IAnnotation[0];
      }

      public String getName() {
         return this.field.getName();
      }

      public boolean isStatic() {
         return Modifier.isStatic(this.field.getModifiers());
      }

      public IClass getType() {
         return ReflectionIClass.this.classToIClass(this.field.getType());
      }

      public String toString() {
         return Descriptor.toString(this.getDeclaringIClass().getDescriptor()) + "." + this.getName();
      }

      public Object getConstantValue() throws CompileException {
         int mod = this.field.getModifiers();
         Class<?> clazz = this.field.getType();
         if (Modifier.isStatic(mod) && Modifier.isFinal(mod) && (clazz.isPrimitive() || clazz == String.class)) {
            try {
               return this.field.get((Object)null);
            } catch (IllegalAccessException var4) {
               throw new CompileException("Field \"" + this.field.getName() + "\" is not accessible", (Location)null);
            }
         } else {
            return IClass.NOT_CONSTANT;
         }
      }
   }
}
