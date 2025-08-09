package net.razorvine.pickle.objects;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import net.razorvine.pickle.IObjectConstructor;
import net.razorvine.pickle.PickleException;

public class AnyClassConstructor implements IObjectConstructor {
   private final Class type;

   public AnyClassConstructor(Class type) {
      this.type = type;
   }

   public Object construct(Object[] args) {
      try {
         Class<?>[] paramtypes = new Class[args.length];

         for(int i = 0; i < args.length; ++i) {
            paramtypes[i] = args[i].getClass();
         }

         Constructor<?> cons = this.type.getConstructor(paramtypes);
         if (this.type == BigDecimal.class && args.length == 1) {
            String nan = (String)args[0];
            if (nan.equalsIgnoreCase("nan")) {
               return Double.NaN;
            }
         }

         return cons.newInstance(args);
      } catch (Exception x) {
         throw new PickleException("problem construction object: " + x);
      }
   }
}
