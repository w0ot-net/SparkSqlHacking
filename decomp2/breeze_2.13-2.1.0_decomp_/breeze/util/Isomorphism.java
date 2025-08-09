package breeze.util;

import java.io.Serializable;
import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000594qa\u0003\u0007\u0011\u0002\u0007\u0005\u0011\u0003C\u0003&\u0001\u0011\u0005a\u0005C\u0003+\u0001\u0019\u00051\u0006C\u0003=\u0001\u0019\u0005Q\bC\u0003A\u0001\u0011\u0005\u0011iB\u0003E\u0019!\u0005QIB\u0003\f\u0019!\u0005a\tC\u0003O\r\u0011\u0005q\nC\u0003Q\r\u0011\u0005\u0011\u000bC\u0003a\r\u0011\r\u0011\rC\u0004g\r\u0005\u0005I\u0011B4\u0003\u0017%\u001bx.\\8sa\"L7/\u001c\u0006\u0003\u001b9\tA!\u001e;jY*\tq\"\u0001\u0004ce\u0016,'0Z\u0002\u0001+\r\u0011\"HL\n\u0004\u0001MI\u0002C\u0001\u000b\u0018\u001b\u0005)\"\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005a)\"AB!osJ+g\r\u0005\u0002\u001bE9\u00111\u0004\t\b\u00039}i\u0011!\b\u0006\u0003=A\ta\u0001\u0010:p_Rt\u0014\"\u0001\f\n\u0005\u0005*\u0012a\u00029bG.\fw-Z\u0005\u0003G\u0011\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!!I\u000b\u0002\r\u0011Jg.\u001b;%)\u00059\u0003C\u0001\u000b)\u0013\tISC\u0001\u0003V]&$\u0018a\u00024pe^\f'\u000f\u001a\u000b\u0003Y]\u0002\"!\f\u0018\r\u0001\u0011)q\u0006\u0001b\u0001a\t\tQ+\u0005\u00022iA\u0011ACM\u0005\u0003gU\u0011qAT8uQ&tw\r\u0005\u0002\u0015k%\u0011a'\u0006\u0002\u0004\u0003:L\b\"\u0002\u001d\u0003\u0001\u0004I\u0014!\u0001;\u0011\u00055RD!B\u001e\u0001\u0005\u0004\u0001$!\u0001+\u0002\u0011\t\f7m[<be\u0012$\"!\u000f \t\u000b}\u001a\u0001\u0019\u0001\u0017\u0002\u0003U\fqA]3wKJ\u001cX-F\u0001C!\u0011\u0019\u0005\u0001L\u001d\u000e\u00031\t1\"S:p[>\u0014\b\u000f[5t[B\u00111IB\n\u0004\rM9\u0005C\u0001%N\u001b\u0005I%B\u0001&L\u0003\tIwNC\u0001M\u0003\u0011Q\u0017M^1\n\u0005\rJ\u0015A\u0002\u001fj]&$h\bF\u0001F\u0003\u0015\t\u0007\u000f\u001d7z+\r\u0011Vk\u0016\u000b\u0004'bk\u0006\u0003B\"\u0001)Z\u0003\"!L+\u0005\u000bmB!\u0019\u0001\u0019\u0011\u00055:F!B\u0018\t\u0005\u0004\u0001\u0004\"B-\t\u0001\u0004Q\u0016A\u0001;v!\u0011!2\f\u0016,\n\u0005q+\"!\u0003$v]\u000e$\u0018n\u001c82\u0011\u0015q\u0006\u00021\u0001`\u0003\t)H\u000f\u0005\u0003\u00157Z#\u0016\u0001C5eK:$\u0018\u000e^=\u0016\u0005\t,W#A2\u0011\t\r\u0003A\r\u001a\t\u0003[\u0015$QaO\u0005C\u0002A\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012\u0001\u001b\t\u0003S2l\u0011A\u001b\u0006\u0003W.\u000bA\u0001\\1oO&\u0011QN\u001b\u0002\u0007\u001f\nTWm\u0019;"
)
public interface Isomorphism extends Serializable {
   static Isomorphism identity() {
      return Isomorphism$.MODULE$.identity();
   }

   static Isomorphism apply(final Function1 tu, final Function1 ut) {
      return Isomorphism$.MODULE$.apply(tu, ut);
   }

   Object forward(final Object t);

   Object backward(final Object u);

   default Isomorphism reverse() {
      return new Isomorphism() {
         // $FF: synthetic field
         private final Isomorphism $outer;

         public Isomorphism reverse() {
            return Isomorphism.super.reverse();
         }

         public Object forward(final Object u) {
            return this.$outer.backward(u);
         }

         public Object backward(final Object t) {
            return this.$outer.forward(t);
         }

         public {
            if (Isomorphism.this == null) {
               throw null;
            } else {
               this.$outer = Isomorphism.this;
               Isomorphism.$init$(this);
            }
         }
      };
   }

   static void $init$(final Isomorphism $this) {
   }
}
