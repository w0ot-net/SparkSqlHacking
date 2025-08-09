package com.twitter.chill;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.reflectasm.ConstructorAccess;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Constructor;
import org.objenesis.instantiator.ObjectInstantiator;
import scala.Function0;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceExtensionMethods.;
import scala.runtime.BoxesRunTime;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public final class Instantiators$ {
   public static final Instantiators$ MODULE$ = new Instantiators$();

   public ObjectInstantiator newOrElse(final Class cls, final IterableOnce it, final Function0 elsefn) {
      return (ObjectInstantiator).MODULE$.find$extension(scala.collection.IterableOnce..MODULE$.iterableOnceExtensionMethods(.MODULE$.flatMap$extension(scala.collection.IterableOnce..MODULE$.iterableOnceExtensionMethods(it), (fn) -> ((Try)fn.apply(cls)).toOption())), (x$4) -> BoxesRunTime.boxToBoolean($anonfun$newOrElse$2(x$4))).getOrElse(elsefn);
   }

   public ObjectInstantiator forClass(final Class t, final Function0 fn) {
      return new ObjectInstantiator(fn, t) {
         private final Function0 fn$1;
         private final Class t$1;

         public Object newInstance() {
            try {
               return this.fn$1.apply();
            } catch (Exception var2) {
               throw new KryoException((new StringBuilder(38)).append("Error constructing instance of class: ").append(this.t$1.getName()).toString(), var2);
            }
         }

         public {
            this.fn$1 = fn$1;
            this.t$1 = t$1;
         }
      };
   }

   public Try reflectAsm(final Class t) {
      Object var10000;
      try {
         ConstructorAccess access = ConstructorAccess.get(t);
         access.newInstance();
         var10000 = new Success(this.forClass(t, () -> access.newInstance()));
      } catch (Throwable var4) {
         var10000 = new Failure(var4);
      }

      return (Try)var10000;
   }

   public Constructor getConstructor(final Class c) {
      Constructor var10000;
      try {
         var10000 = c.getConstructor();
      } catch (Throwable var3) {
         Constructor cons = c.getDeclaredConstructor();
         cons.setAccessible(true);
         var10000 = cons;
      }

      return var10000;
   }

   public Try normalJava(final Class t) {
      Object var10000;
      try {
         Constructor cons = this.getConstructor(t);
         var10000 = new Success(this.forClass(t, () -> cons.newInstance()));
      } catch (Throwable var4) {
         var10000 = new Failure(var4);
      }

      return (Try)var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$newOrElse$2(final ObjectInstantiator x$4) {
      return true;
   }

   private Instantiators$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
