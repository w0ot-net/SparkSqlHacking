package org.json4s;

import scala.Function1;
import scala.Tuple2;
import scala.package.;

public final class JsonAssoc$ {
   public static final JsonAssoc$ MODULE$ = new JsonAssoc$();

   public final JObject $tilde$extension(final Tuple2 $this, final Tuple2 right, final Function1 ev1, final Function1 ev2) {
      JValue l = (JValue)ev1.apply($this._2());
      JValue r = (JValue)ev2.apply(right._2());
      JObject$ var10000 = JsonAST$.MODULE$.JObject();
      Tuple2 var7 = JsonAST$.MODULE$.JField().apply((String)$this._1(), l);
      Tuple2 var8 = JsonAST$.MODULE$.JField().apply((String)right._1(), r);
      return var10000.apply(.MODULE$.Nil().$colon$colon(var8).$colon$colon(var7));
   }

   public final JObject $tilde$extension(final Tuple2 $this, final JObject right, final Function1 ev) {
      JValue l = (JValue)ev.apply($this._2());
      JObject$ var10000 = JsonAST$.MODULE$.JObject();
      Tuple2 var5 = JsonAST$.MODULE$.JField().apply((String)$this._1(), l);
      return var10000.apply(right.obj().$colon$colon(var5));
   }

   public final JObject $tilde$tilde$extension(final Tuple2 $this, final Tuple2 right, final Function1 ev1, final Function1 ev2) {
      return this.$tilde$extension($this, right, ev1, ev2);
   }

   public final JObject $tilde$tilde$extension(final Tuple2 $this, final JObject right, final Function1 ev) {
      return this.$tilde$extension($this, right, ev);
   }

   public final int hashCode$extension(final Tuple2 $this) {
      return $this.hashCode();
   }

   public final boolean equals$extension(final Tuple2 $this, final Object x$1) {
      boolean var3;
      if (x$1 instanceof JsonAssoc) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var7;
      if (var3) {
         label32: {
            label31: {
               Tuple2 var5 = x$1 == null ? null : ((JsonAssoc)x$1).org$json4s$JsonAssoc$$left();
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

   private JsonAssoc$() {
   }
}
