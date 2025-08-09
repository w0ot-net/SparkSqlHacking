package org.apache.spark.ml.feature;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i2\u0001BB\u0004\u0011\u0002\u0007\u0005\u0012\"\u0005\u0005\u00061\u0001!\tA\u0007\u0005\u0006=\u0001!\ta\b\u0005\u0006I\u0001!\t!\n\u0005\u0006S\u0001!\tA\u000b\u0005\u0006Y\u0001!\t!\f\u0002\u0005)\u0016\u0014XN\u0003\u0002\t\u0013\u00059a-Z1ukJ,'B\u0001\u0006\f\u0003\tiGN\u0003\u0002\r\u001b\u0005)1\u000f]1sW*\u0011abD\u0001\u0007CB\f7\r[3\u000b\u0003A\t1a\u001c:h'\t\u0001!\u0003\u0005\u0002\u0014-5\tACC\u0001\u0016\u0003\u0015\u00198-\u00197b\u0013\t9BC\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\t1\u0004\u0005\u0002\u00149%\u0011Q\u0004\u0006\u0002\u0005+:LG/A\u0004bgR+'/\\:\u0016\u0003\u0001\u0002\"!\t\u0012\u000e\u0003\u001dI!aI\u0004\u0003\u000bQ+'/\\:\u0002\u0007\u0005$G\r\u0006\u0002'OA\u0011\u0011\u0005\u0001\u0005\u0006Q\r\u0001\rAJ\u0001\u0006_RDWM]\u0001\tgV\u0014GO]1diR\u0011ae\u000b\u0005\u0006Q\u0011\u0001\rAJ\u0001\tS:$XM]1diR\u0011aE\f\u0005\u0006Q\u0015\u0001\rAJ\u0015\b\u0001A\u0012DG\u000e\u001d#\u0013\t\ttAA\tD_2,XN\\%oi\u0016\u0014\u0018m\u0019;j_:L!aM\u0004\u0003\u0011\u0011+G.\u001a;j_:T!!N\u0004\u0002\u0013\u0015k\u0007\u000f^=UKJl\u0017BA\u001c\b\u0005AIe\u000e^3sC\u000e$\u0018M\u00197f)\u0016\u0014X.\u0003\u0002:\u000f\tI\u0011J\u001c;fe\u000e,\u0007\u000f\u001e"
)
public interface Term {
   // $FF: synthetic method
   static Terms asTerms$(final Term $this) {
      return $this.asTerms();
   }

   default Terms asTerms() {
      return new Terms(new .colon.colon(this, scala.collection.immutable.Nil..MODULE$));
   }

   // $FF: synthetic method
   static Term add$(final Term $this, final Term other) {
      return $this.add(other);
   }

   default Term add(final Term other) {
      return new Terms((Seq)this.asTerms().terms().$plus$plus(other.asTerms().terms()));
   }

   // $FF: synthetic method
   static Term subtract$(final Term $this, final Term other) {
      return $this.subtract(other);
   }

   default Term subtract(final Term other) {
      return (Term)other.asTerms().terms().foldLeft(this, (x0$1, x1$1) -> {
         Tuple2 var4 = new Tuple2(x0$1, x1$1);
         if (var4 != null) {
            Term left = (Term)var4._1();
            Term right = (Term)var4._2();
            if (right instanceof Deletion) {
               Deletion var8 = (Deletion)right;
               return left.add(var8);
            } else if (right != null) {
               return left.add(new Deletion(right));
            } else {
               throw new MatchError(right);
            }
         } else {
            throw new MatchError(var4);
         }
      });
   }

   // $FF: synthetic method
   static Term interact$(final Term $this, final Term other) {
      return $this.interact(other);
   }

   default Term interact(final Term other) {
      return EmptyTerm$.MODULE$;
   }

   static void $init$(final Term $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
