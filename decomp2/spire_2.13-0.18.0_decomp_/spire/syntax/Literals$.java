package spire.syntax;

import java.lang.invoke.SerializedLambda;
import scala.StringContext;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.collection.mutable.StringBuilder;
import spire.math.Polynomial;
import spire.math.Polynomial$;

public final class Literals$ {
   public static final Literals$ MODULE$ = new Literals$();

   public final Polynomial poly$extension(final StringContext $this, final Seq args) {
      StringBuilder sb = new StringBuilder();
      Iterator lits = $this.parts().iterator();
      Iterator vars = ((IterableOnce)args.map((x$1) -> x$1.toString())).iterator();
      sb.append((String)lits.next());

      while(vars.hasNext()) {
         sb.append((String)vars.next());
         sb.append((String)lits.next());
      }

      return Polynomial$.MODULE$.apply(sb.toString());
   }

   public final int hashCode$extension(final StringContext $this) {
      return $this.hashCode();
   }

   public final boolean equals$extension(final StringContext $this, final Object x$1) {
      boolean var3;
      if (x$1 instanceof Literals) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var7;
      if (var3) {
         label32: {
            label31: {
               StringContext var5 = x$1 == null ? null : ((Literals)x$1).s();
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

   private Literals$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
