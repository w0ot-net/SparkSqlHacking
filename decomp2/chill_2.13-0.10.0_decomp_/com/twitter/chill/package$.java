package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Predef.;
import scala.collection.Iterable;
import scala.runtime.BoxedUnit;

public final class package$ {
   public static final package$ MODULE$ = new package$();

   public RichKryo toRich(final Kryo k) {
      return new RichKryo(k);
   }

   public KryoInstantiator toInstantiator(final Function0 fn) {
      return new KryoInstantiator(fn) {
         private final Function0 fn$1;

         public Kryo newKryo() {
            return (Kryo)this.fn$1.apply();
         }

         public {
            this.fn$1 = fn$1;
         }
      };
   }

   public IKryoRegistrar toRegistrar(final Function1 fn) {
      return new IKryoRegistrar(fn) {
         private final Function1 fn$2;

         public void apply(final Kryo k) {
            this.fn$2.apply(k);
         }

         public {
            this.fn$2 = fn$2;
         }
      };
   }

   public IKryoRegistrar toRegistrar(final Iterable items) {
      return new IKryoRegistrar(items) {
         private final Iterable items$1;

         public void apply(final Kryo k) {
            this.items$1.foreach((x$1) -> {
               $anonfun$apply$1(k, x$1);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$1(final Kryo k$1, final IKryoRegistrar x$1) {
            x$1.apply(k$1);
         }

         public {
            this.items$1 = items$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public IKryoRegistrar printIfRegistered(final Class cls) {
      return new IKryoRegistrar(cls) {
         private final Class cls$1;

         public void apply(final Kryo k) {
            if (package$.MODULE$.toRich(k).alreadyRegistered(this.cls$1)) {
               System.err.printf("%s is already registered.", this.cls$1.getName());
            }

         }

         public {
            this.cls$1 = cls$1;
         }
      };
   }

   public IKryoRegistrar assertNotRegistered(final Class cls) {
      return new IKryoRegistrar(cls) {
         private final Class cls$2;

         public void apply(final Kryo k) {
            .MODULE$.assert(!package$.MODULE$.toRich(k).alreadyRegistered(this.cls$2), () -> String.format("%s is already registered.", this.cls$2.getName()));
         }

         public {
            this.cls$2 = cls$2;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   private package$() {
   }
}
