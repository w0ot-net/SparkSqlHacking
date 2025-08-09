package jodd;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import jodd.exception.UncheckedException;

public class Jodd {
   public static final int CORE = 0;
   public static final int BEAN = 1;
   public static final int DB = 2;
   public static final int HTTP = 3;
   public static final int JTX = 4;
   public static final int LAGARTO = 5;
   public static final int LOG = 6;
   public static final int MADVOC = 7;
   public static final int MAIL = 8;
   public static final int PETITE = 9;
   public static final int PROPS = 10;
   public static final int PROXETTA = 11;
   public static final int SERVLET = 12;
   public static final int UPLOAD = 13;
   public static final int VTOR = 14;
   private static final boolean[] LOADED;
   private static final Object[] MODULES;

   static void module() {
   }

   public static boolean isModuleLoaded(int moduleNdx) {
      return LOADED[moduleNdx];
   }

   public static Object getModule(int moduleNdx) {
      return MODULES[moduleNdx];
   }

   public static void bind(int moduleNdx, Object... arguments) {
      Object module = MODULES[moduleNdx];
      Class[] types = new Class[arguments.length];

      for(int i = 0; i < arguments.length; ++i) {
         Object argument = arguments[i];
         types[i] = argument.getClass();
      }

      try {
         Method method = module.getClass().getMethod("bind", types);
         method.invoke(module, arguments);
      } catch (Exception ex) {
         throw new UncheckedException(ex);
      }
   }

   static {
      Field[] fields = Jodd.class.getFields();
      LOADED = new boolean[fields.length];
      MODULES = new Object[fields.length];
      ClassLoader classLoader = Jodd.class.getClassLoader();

      for(Field field : fields) {
         int index;
         try {
            index = (Integer)field.get((Object)null);
         } catch (IllegalAccessException iaex) {
            throw new UncheckedException(iaex);
         }

         String moduleName = field.getName();
         moduleName = moduleName.substring(0, 1).toUpperCase() + moduleName.substring(1, moduleName.length()).toLowerCase();
         String moduleClass = "jodd.Jodd" + moduleName;

         try {
            MODULES[index] = classLoader.loadClass(moduleClass);
         } catch (ClassNotFoundException var12) {
            continue;
         }

         LOADED[index] = true;
      }

      for(int i = 0; i < MODULES.length; ++i) {
         Class type = (Class)MODULES[i];
         if (type != null) {
            try {
               MODULES[i] = type.newInstance();
            } catch (Exception ex) {
               throw new UncheckedException(ex);
            }
         }
      }

   }
}
