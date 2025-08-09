package org.json4s;

import java.lang.invoke.SerializedLambda;
import scala.Function1;

public final class AsJsonInput$ implements AsJsonInputInstances {
   public static final AsJsonInput$ MODULE$ = new AsJsonInput$();
   private static final AsJsonInput stringAsJsonInput;
   private static final AsJsonInput identity;
   private static AsJsonInput fileAsJsonInput;

   static {
      AsJsonInputInstances.$init$(MODULE$);
      stringAsJsonInput = MODULE$.fromFunction((x) -> new StringInput(x));
      identity = MODULE$.fromFunction((x) -> x);
   }

   public final AsJsonInput fileAsJsonInput() {
      return fileAsJsonInput;
   }

   public final void org$json4s$AsJsonInputInstances$_setter_$fileAsJsonInput_$eq(final AsJsonInput x$1) {
      fileAsJsonInput = x$1;
   }

   public AsJsonInput apply(final AsJsonInput a) {
      return a;
   }

   public JsonInput asJsonInput(final Object input, final AsJsonInput a) {
      return a.toJsonInput(input);
   }

   public AsJsonInput fromFunction(final Function1 f) {
      return new AsJsonInput(f) {
         private final Function1 f$2;

         public AsJsonInput contramap(final Function1 f) {
            return AsJsonInput.contramap$(this, f);
         }

         public JsonInput toJsonInput(final Object a) {
            return (JsonInput)this.f$2.apply(a);
         }

         public {
            this.f$2 = f$2;
            AsJsonInput.$init$(this);
         }
      };
   }

   public AsJsonInput stringAsJsonInput() {
      return stringAsJsonInput;
   }

   public AsJsonInput readerAsJsonInput() {
      return this.fromFunction((x) -> new ReaderInput(x));
   }

   public AsJsonInput streamAsJsonInput() {
      return this.fromFunction((x) -> new StreamInput(x));
   }

   public AsJsonInput identity() {
      return identity;
   }

   private AsJsonInput$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
