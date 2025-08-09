package org.apache.spark.sql;

import scala.reflect.ScalaSignature;
import scala.reflect.api.TypeTags;

@ScalaSignature(
   bytes = "\u0006\u0005!3qa\u0001\u0003\u0011\u0002\u0007\u0005Q\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!DA\fM_^\u0004&/[8sSRL8+\u0015'J[Bd\u0017nY5ug*\u0011QAB\u0001\u0004gFd'BA\u0004\t\u0003\u0015\u0019\b/\u0019:l\u0015\tI!\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0017\u0005\u0019qN]4\u0004\u0001M\u0011\u0001A\u0004\t\u0003\u001fIi\u0011\u0001\u0005\u0006\u0002#\u0005)1oY1mC&\u00111\u0003\u0005\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u00051\u0002CA\b\u0018\u0013\tA\u0002C\u0001\u0003V]&$\u0018!\u00058foB\u0013x\u000eZ;di\u0016s7m\u001c3feV\u00111D\t\u000b\u00039-\u00022!\b\u0010!\u001b\u0005!\u0011BA\u0010\u0005\u0005\u001d)enY8eKJ\u0004\"!\t\u0012\r\u0001\u0011)1E\u0001b\u0001I\t\tA+\u0005\u0002&QA\u0011qBJ\u0005\u0003OA\u0011qAT8uQ&tw\r\u0005\u0002\u0010S%\u0011!\u0006\u0005\u0002\b!J|G-^2u\u0011\u001da#!!AA\u00045\n!\"\u001a<jI\u0016t7-\u001a\u0013:!\rq#\t\t\b\u0003_}r!\u0001\r\u001f\u000f\u0005EJdB\u0001\u001a8\u001d\t\u0019d'D\u00015\u0015\t)D\"\u0001\u0004=e>|GOP\u0005\u0002#%\u0011\u0001\bE\u0001\be\u00164G.Z2u\u0013\tQ4(A\u0004sk:$\u0018.\\3\u000b\u0005a\u0002\u0012BA\u001f?\u0003\u001d\u0001\u0018mY6bO\u0016T!AO\u001e\n\u0005\u0001\u000b\u0015\u0001C;oSZ,'o]3\u000b\u0005ur\u0014BA\"E\u0005\u001d!\u0016\u0010]3UC\u001eL!!\u0012$\u0003\u0011QK\b/\u001a+bONT!aR\u001e\u0002\u0007\u0005\u0004\u0018\u000e"
)
public interface LowPrioritySQLImplicits {
   // $FF: synthetic method
   static Encoder newProductEncoder$(final LowPrioritySQLImplicits $this, final TypeTags.TypeTag evidence$9) {
      return $this.newProductEncoder(evidence$9);
   }

   default Encoder newProductEncoder(final TypeTags.TypeTag evidence$9) {
      return Encoders$.MODULE$.product(evidence$9);
   }

   static void $init$(final LowPrioritySQLImplicits $this) {
   }
}
