package org.apache.spark.util;

import java.util.concurrent.atomic.AtomicInteger;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000552Q!\u0002\u0004\u0001\u00119AQ!\u0006\u0001\u0005\u0002]AqA\u0007\u0001C\u0002\u0013%1\u0004\u0003\u0004(\u0001\u0001\u0006I\u0001\b\u0005\u0006Q\u0001!\t!\u000b\u0002\f\u0013\u0012<UM\\3sCR|'O\u0003\u0002\b\u0011\u0005!Q\u000f^5m\u0015\tI!\"A\u0003ta\u0006\u00148N\u0003\u0002\f\u0019\u00051\u0011\r]1dQ\u0016T\u0011!D\u0001\u0004_J<7C\u0001\u0001\u0010!\t\u00012#D\u0001\u0012\u0015\u0005\u0011\u0012!B:dC2\f\u0017B\u0001\u000b\u0012\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u0019!\tI\u0002!D\u0001\u0007\u0003\tIG-F\u0001\u001d!\tiR%D\u0001\u001f\u0015\ty\u0002%\u0001\u0004bi>l\u0017n\u0019\u0006\u0003C\t\n!bY8oGV\u0014(/\u001a8u\u0015\t91EC\u0001%\u0003\u0011Q\u0017M^1\n\u0005\u0019r\"!D!u_6L7-\u00138uK\u001e,'/A\u0002jI\u0002\nAA\\3yiV\t!\u0006\u0005\u0002\u0011W%\u0011A&\u0005\u0002\u0004\u0013:$\b"
)
public class IdGenerator {
   private final AtomicInteger id = new AtomicInteger();

   private AtomicInteger id() {
      return this.id;
   }

   public int next() {
      return this.id().incrementAndGet();
   }
}
