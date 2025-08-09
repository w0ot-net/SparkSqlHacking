package scala.collection.immutable;

import scala.collection.Iterator$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-2A\u0001B\u0003\u0007\u0019!AA\u0005\u0001B\u0001B\u0003%a\u0004C\u0003&\u0001\u0011\u0005a\u0005C\u0003*\u0001\u0011\u0005!F\u0001\bNCB\\U-_%uKJ\fGo\u001c:\u000b\u0005\u00199\u0011!C5n[V$\u0018M\u00197f\u0015\tA\u0011\"\u0001\u0006d_2dWm\u0019;j_:T\u0011AC\u0001\u0006g\u000e\fG.Y\u0002\u0001+\riACI\n\u0003\u00019\u0001Ba\u0004\t\u0013=5\tQ!\u0003\u0002\u0012\u000b\t\t2\t[1na\n\u000b7/Z%uKJ\fGo\u001c:\u0011\u0005M!B\u0002\u0001\u0003\u0006+\u0001\u0011\rA\u0006\u0002\u0002\u0017F\u0011qc\u0007\t\u00031ei\u0011!C\u0005\u00035%\u0011qAT8uQ&tw\r\u0005\u0002\u00199%\u0011Q$\u0003\u0002\u0004\u0003:L\b\u0003B\b %\u0005J!\u0001I\u0003\u0003\u000f5\u000b\u0007OT8eKB\u00111C\t\u0003\u0006G\u0001\u0011\rA\u0006\u0002\u0002-\u0006A!o\\8u\u001d>$W-\u0001\u0004=S:LGO\u0010\u000b\u0003O!\u0002Ba\u0004\u0001\u0013C!)AE\u0001a\u0001=\u0005!a.\u001a=u)\u0005\u0011\u0002"
)
public final class MapKeyIterator extends ChampBaseIterator {
   public Object next() {
      if (!this.hasNext()) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      Object key = ((MapNode)this.currentValueNode()).getKey(this.currentValueCursor());
      this.currentValueCursor_$eq(this.currentValueCursor() + 1);
      return key;
   }

   public MapKeyIterator(final MapNode rootNode) {
      super(rootNode);
   }
}
