package scala.collection.immutable;

import scala.collection.Iterator$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M2AAB\u0004\u0007\u001d!Aa\u0005\u0001B\u0001B\u0003%\u0001\u0004C\u0003(\u0001\u0011\u0005\u0001\u0006\u0003\u0004,\u0001\u0001\u0006K\u0001\f\u0005\u0006_\u0001!\t\u0005\r\u0005\u0006c\u0001!\tA\r\u0002\u0010'\u0016$\b*Y:i\u0013R,'/\u0019;pe*\u0011\u0001\"C\u0001\nS6lW\u000f^1cY\u0016T!AC\u0006\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\r\u0003\u0015\u00198-\u00197b\u0007\u0001)\"aD\u000f\u0014\u0005\u0001\u0001\u0002\u0003B\t\u0013)ai\u0011aB\u0005\u0003'\u001d\u0011\u0011c\u00115b[B\u0014\u0015m]3Ji\u0016\u0014\u0018\r^8s!\t)b#D\u0001\f\u0013\t92B\u0001\u0004B]f\u0014VM\u001a\t\u0004#eY\u0012B\u0001\u000e\b\u0005\u001d\u0019V\r\u001e(pI\u0016\u0004\"\u0001H\u000f\r\u0001\u0011)a\u0004\u0001b\u0001?\t\t\u0011)\u0005\u0002!GA\u0011Q#I\u0005\u0003E-\u0011qAT8uQ&tw\r\u0005\u0002\u0016I%\u0011Qe\u0003\u0002\u0004\u0003:L\u0018\u0001\u0003:p_Rtu\u000eZ3\u0002\rqJg.\u001b;?)\tI#\u0006E\u0002\u0012\u0001mAQA\n\u0002A\u0002a\tA\u0001[1tQB\u0011Q#L\u0005\u0003]-\u00111!\u00138u\u0003!A\u0017m\u001d5D_\u0012,G#\u0001\u0017\u0002\t9,\u0007\u0010\u001e\u000b\u0002)\u0001"
)
public final class SetHashIterator extends ChampBaseIterator {
   private int hash = 0;

   public int hashCode() {
      return this.hash;
   }

   public Object next() {
      if (!this.hasNext()) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      this.hash = this.currentValueNode().getHash(this.currentValueCursor());
      this.currentValueCursor_$eq(this.currentValueCursor() + 1);
      return this;
   }

   public SetHashIterator(final SetNode rootNode) {
      super(rootNode);
   }
}
