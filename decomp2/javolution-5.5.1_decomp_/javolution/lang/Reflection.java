package javolution.lang;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import javolution.context.LogContext;
import javolution.text.TextBuilder;
import javolution.util.FastComparator;
import javolution.util.FastMap;
import javolution.util.FastSet;

public abstract class Reflection {
   private static volatile Reflection INSTANCE = new Default();
   public static final Configurable CLASS = new Configurable(Default.class) {
      protected void notifyChange(Object oldValue, Object newValue) {
         try {
            Reflection.INSTANCE = (Reflection)((Class)newValue).newInstance();
         } catch (Throwable error) {
            LogContext.error(error);
         }

      }
   };
   private static final Object[] EMPTY_ARRAY = new Object[0];

   protected Reflection() {
   }

   public static final Reflection getInstance() {
      return INSTANCE;
   }

   public abstract void add(Object var1);

   public abstract void remove(Object var1);

   public abstract Class getClass(CharSequence var1);

   public Class getClass(String name) {
      if (name instanceof CharSequence) {
         return this.getClass((CharSequence)name);
      } else {
         TextBuilder tmp = TextBuilder.newInstance();

         Class var4;
         try {
            tmp.append(name);
            var4 = this.getClass((CharSequence)tmp);
         } finally {
            TextBuilder.recycle(tmp);
         }

         return var4;
      }
   }

   public abstract Class getSuperclass(Class var1);

   public abstract Class[] getInterfaces(Class var1);

   public abstract Constructor getConstructor(String var1);

   public abstract Method getMethod(String var1);

   public abstract Object getField(Class var1, Class var2, boolean var3);

   public abstract void setField(Object var1, Class var2, Class var3);

   private static final class Default extends Reflection {
      private final FastMap _fields;
      private final Collection _classLoaders;
      private final FastMap _nameToClass;

      private Default() {
         this._fields = (new FastMap()).shared();
         this._classLoaders = (new FastSet()).shared();
         this._nameToClass = (new FastMap()).shared().setKeyComparator(FastComparator.LEXICAL);
      }

      public void add(Object classLoader) {
         this._classLoaders.add(classLoader);
      }

      public void remove(Object classLoader) {
         this._classLoaders.remove(classLoader);
         this._nameToClass.clear();

         for(FastMap.Entry entry : this._fields.entrySet()) {
            Class cls = (Class)entry.getKey();
            if (cls.getClassLoader().equals(classLoader)) {
               this._fields.remove(cls);
            }
         }

      }

      public Class getClass(CharSequence name) {
         Class cls = (Class)this._nameToClass.get(name);
         return cls != null ? cls : this.searchClass(name.toString());
      }

      private Class searchClass(String name) {
         Class cls = null;

         try {
            cls = Class.forName(name);
         } catch (ClassNotFoundException var8) {
            for(ClassLoader classLoader : this._classLoaders) {
               try {
                  cls = Class.forName(name, true, classLoader);
               } catch (ClassNotFoundException var7) {
               }
            }
         }

         if (cls != null) {
            this._nameToClass.put(name, cls);
         }

         return cls;
      }

      public Constructor getConstructor(String signature) {
         int argStart = signature.indexOf(40) + 1;
         if (argStart < 0) {
            throw new IllegalArgumentException("Parenthesis '(' not found");
         } else {
            int argEnd = signature.indexOf(41);
            if (argEnd < 0) {
               throw new IllegalArgumentException("Parenthesis ')' not found");
            } else {
               String className = signature.substring(0, argStart - 1);
               Class theClass = this.getClass(className);
               if (theClass == null) {
                  return null;
               } else {
                  String args = signature.substring(argStart, argEnd);
                  if (args.length() == 0) {
                     return new DefaultConstructor(theClass);
                  } else {
                     Class[] argsTypes = this.classesFor(args);
                     if (argsTypes == null) {
                        return null;
                     } else {
                        try {
                           return new ReflectConstructor(theClass.getConstructor(argsTypes), signature);
                        } catch (NoSuchMethodException var9) {
                           LogContext.warning((CharSequence)"Reflection not supported (Reflection.getConstructor(String)");
                           return null;
                        }
                     }
                  }
               }
            }
         }
      }

      public Class[] getInterfaces(Class cls) {
         return cls.getInterfaces();
      }

      public Class getSuperclass(Class cls) {
         return cls.getSuperclass();
      }

      public Method getMethod(String signature) {
         int argStart = signature.indexOf(40) + 1;
         if (argStart < 0) {
            throw new IllegalArgumentException("Parenthesis '(' not found");
         } else {
            int argEnd = signature.indexOf(41);
            if (argEnd < 0) {
               throw new IllegalArgumentException("Parenthesis ')' not found");
            } else {
               int nameStart = signature.substring(0, argStart).lastIndexOf(46) + 1;

               try {
                  String className = signature.substring(0, nameStart - 1);
                  Class theClass = this.getClass(className);
                  if (theClass == null) {
                     return null;
                  } else {
                     String methodName = signature.substring(nameStart, argStart - 1);
                     String args = signature.substring(argStart, argEnd);
                     Class[] argsTypes = this.classesFor(args);
                     return argsTypes == null ? null : new ReflectMethod(theClass.getMethod(methodName, argsTypes), signature);
                  }
               } catch (Throwable var10) {
                  LogContext.warning((CharSequence)"Reflection not supported (Reflection.getMethod(String)");
                  return null;
               }
            }
         }
      }

      public Object getField(Class forClass, Class type, boolean inherited) {
         ClassInitializer.initialize(forClass);
         return this.getField2(forClass, type, inherited);
      }

      private Object getField2(Class forClass, Class type, boolean inherited) {
         FastMap typeToField = (FastMap)this._fields.get(forClass);
         if (typeToField != null) {
            Object field = typeToField.get(type);
            if (field != null) {
               return field;
            }
         }

         if (!inherited) {
            return null;
         } else {
            Class[] interfaces = this.getInterfaces(forClass);

            for(int i = 0; i < interfaces.length; ++i) {
               Object field = this.getField2(interfaces[i], type, false);
               if (field != null) {
                  return field;
               }
            }

            Class parentClass = this.getSuperclass(forClass);
            return parentClass != null ? this.getField2(parentClass, type, inherited) : null;
         }
      }

      public void setField(Object obj, Class forClass, Class type) {
         synchronized(forClass) {
            FastMap typeToField = (FastMap)this._fields.get(forClass);
            if (typeToField != null && typeToField.containsKey(type)) {
               throw new IllegalArgumentException("Field of type " + type + " already attached to class " + forClass);
            } else {
               if (typeToField == null) {
                  typeToField = new FastMap();
                  this._fields.put(forClass, typeToField);
               }

               typeToField.put(type, obj);
            }
         }
      }

      private Class[] classesFor(String args) {
         args = args.trim();
         if (args.length() == 0) {
            return new Class[0];
         } else {
            int commas = 0;
            int i = 0;

            while(true) {
               i = args.indexOf(44, i);
               if (i++ < 0) {
                  Class[] classes = new Class[commas + 1];
                  int index = 0;

                  for(int i = 0; i < commas; ++i) {
                     int sep = args.indexOf(44, index);
                     classes[i] = this.classFor(args.substring(index, sep).trim());
                     if (classes[i] == null) {
                        return null;
                     }

                     index = sep + 1;
                  }

                  classes[commas] = this.classFor(args.substring(index).trim());
                  if (classes[commas] == null) {
                     return null;
                  } else {
                     return classes;
                  }
               }

               ++commas;
            }
         }
      }

      private Class classFor(String className) {
         int arrayIndex = className.indexOf("[]");
         if (arrayIndex >= 0) {
            if (className.indexOf("[][]") >= 0) {
               if (className.indexOf("[][][]") >= 0) {
                  if (className.indexOf("[][][][]") >= 0) {
                     throw new UnsupportedOperationException("The maximum array dimension is 3");
                  } else {
                     return this.getClass("[[[" + descriptorFor(className.substring(0, arrayIndex)));
                  }
               } else {
                  return this.getClass("[[" + descriptorFor(className.substring(0, arrayIndex)));
               }
            } else {
               return this.getClass("[" + descriptorFor(className.substring(0, arrayIndex)));
            }
         } else if (className.equals("boolean")) {
            return Boolean.TYPE;
         } else if (className.equals("byte")) {
            return Byte.TYPE;
         } else if (className.equals("char")) {
            return Character.TYPE;
         } else if (className.equals("short")) {
            return Short.TYPE;
         } else if (className.equals("int")) {
            return Integer.TYPE;
         } else if (className.equals("long")) {
            return Long.TYPE;
         } else if (className.equals("float")) {
            return Float.TYPE;
         } else {
            return className.equals("double") ? Double.TYPE : this.getClass(className);
         }
      }

      private static String descriptorFor(String className) {
         if (className.equals("boolean")) {
            return "Z";
         } else if (className.equals("byte")) {
            return "B";
         } else if (className.equals("char")) {
            return "C";
         } else if (className.equals("short")) {
            return "S";
         } else if (className.equals("int")) {
            return "I";
         } else if (className.equals("long")) {
            return "J";
         } else if (className.equals("float")) {
            return "F";
         } else {
            return className.equals("double") ? "D" : "L" + className + ";";
         }
      }

      private abstract static class BaseConstructor implements Constructor {
         private final Class[] _parameterTypes;

         protected BaseConstructor(Class[] parameterTypes) {
            this._parameterTypes = parameterTypes;
         }

         public Class[] getParameterTypes() {
            return this._parameterTypes;
         }

         protected abstract Object allocate(Object[] var1);

         public final Object newInstance() {
            if (this._parameterTypes.length != 0) {
               throw new IllegalArgumentException("Expected number of parameters is " + this._parameterTypes.length);
            } else {
               return this.allocate(Reflection.EMPTY_ARRAY);
            }
         }

         public final Object newInstance(Object arg0) {
            if (this._parameterTypes.length != 1) {
               throw new IllegalArgumentException("Expected number of parameters is " + this._parameterTypes.length);
            } else {
               Object[] args = new Object[]{arg0};
               return this.allocate(args);
            }
         }

         public final Object newInstance(Object arg0, Object arg1) {
            if (this._parameterTypes.length != 2) {
               throw new IllegalArgumentException("Expected number of parameters is " + this._parameterTypes.length);
            } else {
               Object[] args = new Object[]{arg0, arg1};
               return this.allocate(args);
            }
         }

         public final Object newInstance(Object arg0, Object arg1, Object arg2) {
            if (this._parameterTypes.length != 3) {
               throw new IllegalArgumentException("Expected number of parameters is " + this._parameterTypes.length);
            } else {
               Object[] args = new Object[]{arg0, arg1, arg2};
               return this.allocate(args);
            }
         }

         public final Object newInstance(Object... args) {
            if (this._parameterTypes.length != args.length) {
               throw new IllegalArgumentException("Expected number of parameters is " + this._parameterTypes.length);
            } else {
               return this.allocate(args);
            }
         }
      }

      private static class DefaultConstructor extends BaseConstructor {
         final Class _class;

         DefaultConstructor(Class cl) {
            super(new Class[0]);
            this._class = cl;
         }

         public Object allocate(Object[] args) {
            try {
               return this._class.newInstance();
            } catch (InstantiationException e) {
               throw new RuntimeException("Default constructor instantiation error for " + this._class.getName() + " (" + e.getMessage() + ")");
            } catch (IllegalAccessException e) {
               throw new RuntimeException("Default constructor illegal access error for " + this._class.getName() + " (" + e.getMessage() + ")");
            }
         }

         public String toString() {
            return this._class + " default constructor";
         }
      }

      private final class ReflectConstructor extends BaseConstructor {
         private final java.lang.reflect.Constructor _value;
         private final String _signature;

         public ReflectConstructor(java.lang.reflect.Constructor value, String signature) {
            super(value.getParameterTypes());
            this._value = value;
            this._signature = signature;
         }

         public Object allocate(Object[] args) {
            try {
               return this._value.newInstance(args);
            } catch (InstantiationException e) {
               throw new RuntimeException("Instantiation error for " + this._signature + " constructor", e);
            } catch (IllegalAccessException e) {
               throw new RuntimeException("Illegal access error for " + this._signature + " constructor", e);
            } catch (InvocationTargetException e) {
               if (e.getCause() instanceof RuntimeException) {
                  throw (RuntimeException)e.getCause();
               } else {
                  throw new RuntimeException("Invocation exception  for " + this._signature + " constructor", (InvocationTargetException)e.getCause());
               }
            }
         }

         public String toString() {
            return this._signature + " constructor";
         }
      }

      private abstract static class BaseMethod implements Method {
         private final Class[] _parameterTypes;

         protected BaseMethod(Class[] parameterTypes) {
            this._parameterTypes = parameterTypes;
         }

         public Class[] getParameterTypes() {
            return this._parameterTypes;
         }

         protected abstract Object execute(Object var1, Object[] var2);

         public final Object invoke(Object thisObject) {
            if (this._parameterTypes.length != 0) {
               throw new IllegalArgumentException("Expected number of parameters is " + this._parameterTypes.length);
            } else {
               return this.execute(thisObject, Reflection.EMPTY_ARRAY);
            }
         }

         public final Object invoke(Object thisObject, Object arg0) {
            if (this._parameterTypes.length != 1) {
               throw new IllegalArgumentException("Expected number of parameters is " + this._parameterTypes.length);
            } else {
               Object[] args = new Object[]{arg0};
               return this.execute(thisObject, args);
            }
         }

         public final Object invoke(Object thisObject, Object arg0, Object arg1) {
            if (this._parameterTypes.length != 2) {
               throw new IllegalArgumentException("Expected number of parameters is " + this._parameterTypes.length);
            } else {
               Object[] args = new Object[]{arg0, arg1};
               return this.execute(thisObject, args);
            }
         }

         public final Object invoke(Object thisObject, Object arg0, Object arg1, Object arg2) {
            if (this._parameterTypes.length != 3) {
               throw new IllegalArgumentException("Expected number of parameters is " + this._parameterTypes.length);
            } else {
               Object[] args = new Object[]{arg0, arg1, arg2};
               return this.execute(thisObject, args);
            }
         }

         public final Object invoke(Object thisObject, Object... args) {
            if (this._parameterTypes.length != args.length) {
               throw new IllegalArgumentException("Expected number of parameters is " + this._parameterTypes.length);
            } else {
               return this.execute(thisObject, args);
            }
         }
      }

      private final class ReflectMethod extends BaseMethod {
         private final java.lang.reflect.Method _value;
         private final String _signature;

         public ReflectMethod(java.lang.reflect.Method value, String signature) {
            super(value.getParameterTypes());
            this._value = value;
            this._signature = signature;
         }

         public Object execute(Object that, Object[] args) {
            try {
               return this._value.invoke(that, args);
            } catch (IllegalAccessException var4) {
               throw new IllegalAccessError("Illegal access error for " + this._signature + " method");
            } catch (InvocationTargetException e) {
               if (e.getCause() instanceof RuntimeException) {
                  throw (RuntimeException)e.getCause();
               } else {
                  throw new RuntimeException("Invocation exception for " + this._signature + " method", (InvocationTargetException)e.getCause());
               }
            }
         }

         public String toString() {
            return this._signature + " method";
         }
      }
   }

   public interface Constructor {
      Class[] getParameterTypes();

      Object newInstance();

      Object newInstance(Object var1);

      Object newInstance(Object var1, Object var2);

      Object newInstance(Object var1, Object var2, Object var3);

      Object newInstance(Object... var1);
   }

   public interface Method {
      Class[] getParameterTypes();

      Object invoke(Object var1);

      Object invoke(Object var1, Object var2);

      Object invoke(Object var1, Object var2, Object var3);

      Object invoke(Object var1, Object var2, Object var3, Object var4);

      Object invoke(Object var1, Object... var2);
   }
}
