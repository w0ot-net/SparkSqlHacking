package breeze.util;

import breeze.collection.mutable.AutoUpdater;
import breeze.collection.mutable.AutoUpdater$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.ModuleSerializationProxy;

public final class Interner$ implements Serializable {
   public static final Interner$ MODULE$ = new Interner$();
   private static final AutoUpdater typedInterners;

   static {
      typedInterners = AutoUpdater$.MODULE$.apply(() -> new Interner());
   }

   private AutoUpdater typedInterners() {
      return typedInterners;
   }

   public Interner apply(final ClassTag evidence$1) {
      return this.forClass(((ClassTag).MODULE$.implicitly(evidence$1)).runtimeClass());
   }

   public Interner forClass(final Class c) {
      return (Interner)this.typedInterners().apply(c);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Interner$.class);
   }

   private Interner$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
