package scala.xml.dtd;

import java.lang.invoke.SerializedLambda;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.xml.Utility$;

@ScalaSignature(
   bytes = "\u0006\u0005a2Q\u0001B\u0003\u0002\"1AQ!\u0005\u0001\u0005\u0002IAQ\u0001\u0006\u0001\u0005BUAQ!\t\u0001\u0007\u0002\t\u0012!\"T1sWV\u0004H)Z2m\u0015\t1q!A\u0002ei\u0012T!\u0001C\u0005\u0002\u0007alGNC\u0001\u000b\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001A\u0007\u0011\u00059yQ\"A\u0003\n\u0005A)!\u0001\u0002#fG2\fa\u0001P5oSRtD#A\n\u0011\u00059\u0001\u0011\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003Y\u0001\"a\u0006\u0010\u000f\u0005aa\u0002CA\r\n\u001b\u0005Q\"BA\u000e\f\u0003\u0019a$o\\8u}%\u0011Q$C\u0001\u0007!J,G-\u001a4\n\u0005}\u0001#AB*ue&twM\u0003\u0002\u001e\u0013\u0005Y!-^5mIN#(/\u001b8h)\t\u00193\u0006\u0005\u0002%Q9\u0011QEJ\u0007\u0002\u0013%\u0011q%C\u0001\ba\u0006\u001c7.Y4f\u0013\tI#FA\u0007TiJLgn\u001a\"vS2$WM\u001d\u0006\u0003O%AQ\u0001L\u0002A\u0002\r\n!a\u001d2*\r\u0001q\u0003G\r\u001b7\u0013\tySAA\u0006BiRd\u0015n\u001d;EK\u000ed\u0017BA\u0019\u0006\u0005!)E.Z7EK\u000ed\u0017BA\u001a\u0006\u0005))e\u000e^5us\u0012+7\r\\\u0005\u0003k\u0015\u0011ABT8uCRLwN\u001c#fG2L!aN\u0003\u0003\u0017A+%+\u001a4fe\u0016t7-\u001a"
)
public abstract class MarkupDecl extends Decl {
   public String toString() {
      return Utility$.MODULE$.sbToString((sb) -> {
         $anonfun$toString$1(this, sb);
         return BoxedUnit.UNIT;
      });
   }

   public abstract StringBuilder buildString(final StringBuilder sb);

   // $FF: synthetic method
   public static final void $anonfun$toString$1(final MarkupDecl $this, final StringBuilder sb) {
      $this.buildString(sb);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
