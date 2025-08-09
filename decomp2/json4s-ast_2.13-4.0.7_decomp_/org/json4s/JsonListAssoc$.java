package org.json4s;

import scala.Tuple2;
import scala.collection.immutable.List;
import scala.package.;

public final class JsonListAssoc$ {
   public static final JsonListAssoc$ MODULE$ = new JsonListAssoc$();

   public final JObject $tilde$extension(final List $this, final Tuple2 right) {
      return JsonAST$.MODULE$.JObject().apply(((List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{JsonAST$.MODULE$.JField().apply((String)right._1(), (JValue)right._2())})))).$colon$colon$colon($this));
   }

   public final JObject $tilde$extension(final List $this, final JObject right) {
      return JsonAST$.MODULE$.JObject().apply(right.obj().$colon$colon$colon($this));
   }

   public final JObject $tilde$tilde$extension(final List $this, final Tuple2 right) {
      return this.$tilde$extension($this, right);
   }

   public final JObject $tilde$tilde$extension(final List $this, final JObject right) {
      return this.$tilde$extension($this, right);
   }

   public final int hashCode$extension(final List $this) {
      return $this.hashCode();
   }

   public final boolean equals$extension(final List $this, final Object x$1) {
      boolean var3;
      if (x$1 instanceof JsonListAssoc) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var7;
      if (var3) {
         label32: {
            label31: {
               List var5 = x$1 == null ? null : ((JsonListAssoc)x$1).org$json4s$JsonListAssoc$$left();
               if ($this == null) {
                  if (var5 == null) {
                     break label31;
                  }
               } else if ($this.equals(var5)) {
                  break label31;
               }

               var7 = false;
               break label32;
            }

            var7 = true;
         }

         if (var7) {
            var7 = true;
            return var7;
         }
      }

      var7 = false;
      return var7;
   }

   private JsonListAssoc$() {
   }
}
