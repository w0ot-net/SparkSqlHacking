package org.apache.commons.collections.functors;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.apache.commons.collections.Transformer;

public class CloneTransformer implements Transformer, Serializable {
   private static final long serialVersionUID = -8188742709499652567L;
   public static final Transformer INSTANCE = new CloneTransformer();
   // $FF: synthetic field
   static Class class$org$apache$commons$collections$functors$CloneTransformer;

   public static Transformer getInstance() {
      return INSTANCE;
   }

   private CloneTransformer() {
   }

   public Object transform(Object input) {
      return input == null ? null : PrototypeFactory.getInstance(input).create();
   }

   private void writeObject(ObjectOutputStream os) throws IOException {
      FunctorUtils.checkUnsafeSerialization(class$org$apache$commons$collections$functors$CloneTransformer == null ? (class$org$apache$commons$collections$functors$CloneTransformer = class$("org.apache.commons.collections.functors.CloneTransformer")) : class$org$apache$commons$collections$functors$CloneTransformer);
      os.defaultWriteObject();
   }

   private void readObject(ObjectInputStream is) throws ClassNotFoundException, IOException {
      FunctorUtils.checkUnsafeSerialization(class$org$apache$commons$collections$functors$CloneTransformer == null ? (class$org$apache$commons$collections$functors$CloneTransformer = class$("org.apache.commons.collections.functors.CloneTransformer")) : class$org$apache$commons$collections$functors$CloneTransformer);
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
