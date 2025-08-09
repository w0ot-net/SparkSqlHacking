package net.razorvine.pickle.objects;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import net.razorvine.pickle.IObjectConstructor;
import net.razorvine.pickle.PickleException;

public class ExceptionConstructor implements IObjectConstructor {
   private final Class type;
   private final String pythonExceptionType;

   public ExceptionConstructor(Class type, String module, String name) {
      if (module != null) {
         this.pythonExceptionType = module + "." + name;
      } else {
         this.pythonExceptionType = name;
      }

      this.type = type;
   }

   public Object construct(Object[] args) {
      try {
         if (this.pythonExceptionType != null) {
            if (args != null && args.length != 0) {
               String msg = "[" + this.pythonExceptionType + "] " + args[0];
               args = new String[]{msg};
            } else {
               args = new String[]{"[" + this.pythonExceptionType + "]"};
            }
         }

         Class<?>[] paramtypes = new Class[args.length];

         for(int i = 0; i < args.length; ++i) {
            paramtypes[i] = args[i].getClass();
         }

         Constructor<?> cons = this.type.getConstructor(paramtypes);
         Object ex = cons.newInstance(args);

         try {
            Field prop = ex.getClass().getField("pythonExceptionType");
            prop.set(ex, this.pythonExceptionType);
         } catch (NoSuchFieldException var6) {
         }

         return ex;
      } catch (Exception x) {
         throw new PickleException("problem construction object: " + x);
      }
   }
}
