package scala.collection.immutable;

import scala.Tuple2;
import scala.collection.Iterator$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000592A\u0001B\u0003\u0007\u0019!Aq\u0005\u0001B\u0001B\u0003%A\u0005C\u0003)\u0001\u0011\u0005\u0011\u0006C\u0003-\u0001\u0011\u0005QFA\u0010NCB\\U-\u001f,bYV,G+\u001e9mKJ+g/\u001a:tK&#XM]1u_JT!AB\u0004\u0002\u0013%lW.\u001e;bE2,'B\u0001\u0005\n\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u0015\u0005)1oY1mC\u000e\u0001QcA\u0007\u0019EM\u0011\u0001A\u0004\t\u0005\u001fA\u0011B%D\u0001\u0006\u0013\t\tRA\u0001\rDQ\u0006l\u0007OQ1tKJ+g/\u001a:tK&#XM]1u_J\u0004Ba\u0005\u000b\u0017C5\t\u0011\"\u0003\u0002\u0016\u0013\t1A+\u001e9mKJ\u0002\"a\u0006\r\r\u0001\u0011)\u0011\u0004\u0001b\u00015\t\t1*\u0005\u0002\u001c=A\u00111\u0003H\u0005\u0003;%\u0011qAT8uQ&tw\r\u0005\u0002\u0014?%\u0011\u0001%\u0003\u0002\u0004\u0003:L\bCA\f#\t\u0015\u0019\u0003A1\u0001\u001b\u0005\u00051\u0006\u0003B\b&-\u0005J!AJ\u0003\u0003\u000f5\u000b\u0007OT8eK\u0006A!o\\8u\u001d>$W-\u0001\u0004=S:LGO\u0010\u000b\u0003U-\u0002Ba\u0004\u0001\u0017C!)qE\u0001a\u0001I\u0005!a.\u001a=u)\u0005\u0011\u0002"
)
public final class MapKeyValueTupleReverseIterator extends ChampBaseReverseIterator {
   public Tuple2 next() {
      if (!this.hasNext()) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      Tuple2 payload = ((MapNode)this.currentValueNode()).getPayload(this.currentValueCursor());
      this.currentValueCursor_$eq(this.currentValueCursor() - 1);
      return payload;
   }

   public MapKeyValueTupleReverseIterator(final MapNode rootNode) {
      super(rootNode);
   }
}
