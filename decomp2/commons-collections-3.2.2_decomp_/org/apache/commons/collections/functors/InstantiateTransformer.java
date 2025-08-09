package org.apache.commons.collections.functors;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.collections.FunctorException;
import org.apache.commons.collections.Transformer;

public class InstantiateTransformer implements Transformer, Serializable {
   private static final long serialVersionUID = 3786388740793356347L;
   public static final Transformer NO_ARG_INSTANCE = new InstantiateTransformer();
   private final Class[] iParamTypes;
   private final Object[] iArgs;
   // $FF: synthetic field
   static Class class$org$apache$commons$collections$functors$InstantiateTransformer;

   public static Transformer getInstance(Class[] paramTypes, Object[] args) {
      if ((paramTypes != null || args == null) && (paramTypes == null || args != null) && (paramTypes == null || args == null || paramTypes.length == args.length)) {
         if (paramTypes != null && paramTypes.length != 0) {
            paramTypes = (Class[])paramTypes.clone();
            args = args.clone();
            return new InstantiateTransformer(paramTypes, args);
         } else {
            return NO_ARG_INSTANCE;
         }
      } else {
         throw new IllegalArgumentException("Parameter types must match the arguments");
      }
   }

   private InstantiateTransformer() {
      this.iParamTypes = null;
      this.iArgs = null;
   }

   public InstantiateTransformer(Class[] paramTypes, Object[] args) {
      this.iParamTypes = paramTypes;
      this.iArgs = args;
   }

   public Object transform(Object input) {
      try {
         if (!(input instanceof Class)) {
            throw new FunctorException("InstantiateTransformer: Input object was not an instanceof Class, it was a " + (input == null ? "null object" : input.getClass().getName()));
         } else {
            Constructor con = ((Class)input).getConstructor(this.iParamTypes);
            return con.newInstance(this.iArgs);
         }
      } catch (NoSuchMethodException var3) {
         throw new FunctorException("InstantiateTransformer: The constructor must exist and be public ");
      } catch (InstantiationException ex) {
         throw new FunctorException("InstantiateTransformer: InstantiationException", ex);
      } catch (IllegalAccessException ex) {
         throw new FunctorException("InstantiateTransformer: Constructor must be public", ex);
      } catch (InvocationTargetException ex) {
         throw new FunctorException("InstantiateTransformer: Constructor threw an exception", ex);
      }
   }

   private void writeObject(ObjectOutputStream os) throws IOException {
      FunctorUtils.checkUnsafeSerialization(class$org$apache$commons$collections$functors$InstantiateTransformer == null ? (class$org$apache$commons$collections$functors$InstantiateTransformer = class$("org.apache.commons.collections.functors.InstantiateTransformer")) : class$org$apache$commons$collections$functors$InstantiateTransformer);
      os.defaultWriteObject();
   }

   private void readObject(ObjectInputStream is) throws ClassNotFoundException, IOException {
      FunctorUtils.checkUnsafeSerialization(class$org$apache$commons$collections$functors$InstantiateTransformer == null ? (class$org$apache$commons$collections$functors$InstantiateTransformer = class$("org.apache.commons.collections.functors.InstantiateTransformer")) : class$org$apache$commons$collections$functors$InstantiateTransformer);
      is.defaultReadObject();
   }

   // $FF: synthetic method
   static Class class$(String x0) {
      try {
         return Class.forName(x0);
      } catch (ClassNotFoundException x1) {
         throw new NoClassDefFoundError(x1.getMessage());
      }
   }
}
