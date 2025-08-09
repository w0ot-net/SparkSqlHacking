package org.apache.spark.sql.catalyst.encoders;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Objects;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00113Q\u0001C\u0005\u0001\u0017UA\u0001B\n\u0001\u0003\u0002\u0003\u0006I\u0001\t\u0005\tQ\u0001\u0011\t\u0011)A\u0005S!)A\u0006\u0001C\u0001[!)A\u0006\u0001C\u0001e!1A\u0007\u0001Q\u0001\nUBQ\u0001\u000f\u0001\u0005BeBQA\u000f\u0001\u0005Bm\u0012Q\u0003S1tQ\u0006\u0014G.Z,fC.\u0014VMZ3sK:\u001cWM\u0003\u0002\u000b\u0017\u0005AQM\\2pI\u0016\u00148O\u0003\u0002\r\u001b\u0005A1-\u0019;bYf\u001cHO\u0003\u0002\u000f\u001f\u0005\u00191/\u001d7\u000b\u0005A\t\u0012!B:qCJ\\'B\u0001\n\u0014\u0003\u0019\t\u0007/Y2iK*\tA#A\u0002pe\u001e\u001c\"\u0001\u0001\f\u0011\u0007]q\u0002%D\u0001\u0019\u0015\tI\"$A\u0002sK\u001aT!a\u0007\u000f\u0002\t1\fgn\u001a\u0006\u0002;\u0005!!.\u0019<b\u0013\ty\u0002DA\u0007XK\u0006\\'+\u001a4fe\u0016t7-\u001a\t\u0003C\u0011j\u0011A\t\u0006\u0002G\u0005)1oY1mC&\u0011QE\t\u0002\u0007\u0003:L(+\u001a4\u0002\u0003Y\u001c\u0001!A\u0003rk\u0016,X\rE\u0002\u0018U\u0001J!a\u000b\r\u0003\u001dI+g-\u001a:f]\u000e,\u0017+^3vK\u00061A(\u001b8jiz\"2A\f\u00192!\ty\u0003!D\u0001\n\u0011\u001513\u00011\u0001!\u0011\u0015A3\u00011\u0001*)\tq3\u0007C\u0003'\t\u0001\u0007\u0001%\u0001\u0003iCND\u0007CA\u00117\u0013\t9$EA\u0002J]R\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002k\u00051Q-];bYN$\"\u0001P \u0011\u0005\u0005j\u0014B\u0001 #\u0005\u001d\u0011un\u001c7fC:DQ\u0001Q\u0004A\u0002\u0005\u000b1a\u001c2k!\t\t#)\u0003\u0002DE\t\u0019\u0011I\\="
)
public class HashableWeakReference extends WeakReference {
   private final int hash;

   public int hashCode() {
      return this.hash;
   }

   public boolean equals(final Object obj) {
      if (obj instanceof HashableWeakReference var4) {
         if (this == var4) {
            return true;
         } else {
            Object referent = this.get();
            Object otherReferent = var4.get();
            return referent != null && otherReferent != null && Objects.equals(referent, otherReferent);
         }
      } else {
         return false;
      }
   }

   public HashableWeakReference(final Object v, final ReferenceQueue queue) {
      super(v, queue);
      this.hash = v.hashCode();
   }

   public HashableWeakReference(final Object v) {
      this(v, (ReferenceQueue)null);
   }
}
