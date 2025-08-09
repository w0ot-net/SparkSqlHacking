package scala.collection;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.runtime.ModuleSerializationProxy;

public final class Map$ extends MapFactory.Delegate {
   public static final Map$ MODULE$ = new Map$();
   private static final long serialVersionUID = 3L;
   private static final Object DefaultSentinel = new Object();
   private static final Function0 scala$collection$Map$$DefaultSentinelFn = () -> MODULE$.DefaultSentinel();

   private Object DefaultSentinel() {
      return DefaultSentinel;
   }

   public Function0 scala$collection$Map$$DefaultSentinelFn() {
      return scala$collection$Map$$DefaultSentinelFn;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Map$.class);
   }

   private Map$() {
      super(scala.collection.immutable.Map$.MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
