package org.json4s.prefs;

import java.lang.invoke.SerializedLambda;
import org.json4s.JArray;
import org.json4s.JField$;
import org.json4s.JNothing$;
import org.json4s.JNull$;
import org.json4s.JObject;
import org.json4s.JValue;
import org.json4s.SomeValue$;
import scala.MatchError;
import scala.None;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.List;

public final class EmptyValueStrategy$ {
   public static final EmptyValueStrategy$ MODULE$ = new EmptyValueStrategy$();
   private static final EmptyValueStrategy skip = new EmptyValueStrategy() {
      public None noneValReplacement() {
         return .MODULE$;
      }

      public JValue replaceEmpty(final JValue value) {
         return value;
      }
   };
   private static final EmptyValueStrategy preserve = new EmptyValueStrategy() {
      private final Some noneValReplacement;

      public Some noneValReplacement() {
         return this.noneValReplacement;
      }

      public JValue replaceEmpty(final JValue value) {
         Object var2;
         if (value instanceof JArray) {
            JArray var4 = (JArray)value;
            List items = var4.arr();
            var2 = new JArray(items.map((valuex) -> this.replaceEmpty(valuex)));
         } else if (value instanceof JObject) {
            JObject var6 = (JObject)value;
            List fields = var6.obj();
            var2 = new JObject(fields.map((x0$1) -> {
               if (x0$1 != null) {
                  Tuple2 var4 = JField$.MODULE$.unapply(x0$1);
                  if (!SomeValue$.MODULE$.isEmpty$extension(var4)) {
                     String name = (String)var4._1();
                     JValue value = (JValue)var4._2();
                     Tuple2 var2 = JField$.MODULE$.apply(name, this.replaceEmpty(value));
                     return var2;
                  }
               }

               throw new MatchError(x0$1);
            }));
         } else if (JNothing$.MODULE$.equals(value)) {
            var2 = JNull$.MODULE$;
         } else {
            var2 = value;
         }

         return (JValue)var2;
      }

      public {
         this.noneValReplacement = new Some(JNull$.MODULE$);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   };

   public EmptyValueStrategy default() {
      return this.skip();
   }

   public EmptyValueStrategy skip() {
      return skip;
   }

   public EmptyValueStrategy preserve() {
      return preserve;
   }

   private EmptyValueStrategy$() {
   }
}
