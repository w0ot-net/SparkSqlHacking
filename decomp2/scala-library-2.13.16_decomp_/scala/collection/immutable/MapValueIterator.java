package scala.collection.immutable;

import scala.collection.Iterator$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-2A\u0001B\u0003\u0007\u0019!AA\u0005\u0001B\u0001B\u0003%a\u0004C\u0003&\u0001\u0011\u0005a\u0005C\u0003*\u0001\u0011\u0005!F\u0001\tNCB4\u0016\r\\;f\u0013R,'/\u0019;pe*\u0011aaB\u0001\nS6lW\u000f^1cY\u0016T!\u0001C\u0005\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\u000b\u0003\u0015\u00198-\u00197b\u0007\u0001)2!\u0004\u0012\u0015'\t\u0001a\u0002\u0005\u0003\u0010!IqR\"A\u0003\n\u0005E)!!E\"iC6\u0004()Y:f\u0013R,'/\u0019;peB\u00111\u0003\u0006\u0007\u0001\t\u0015)\u0002A1\u0001\u0017\u0005\u00051\u0016CA\f\u001c!\tA\u0012$D\u0001\n\u0013\tQ\u0012BA\u0004O_RD\u0017N\\4\u0011\u0005aa\u0012BA\u000f\n\u0005\r\te.\u001f\t\u0005\u001f}\t##\u0003\u0002!\u000b\t9Q*\u00199O_\u0012,\u0007CA\n#\t\u0015\u0019\u0003A1\u0001\u0017\u0005\u0005Y\u0015\u0001\u0003:p_Rtu\u000eZ3\u0002\rqJg.\u001b;?)\t9\u0003\u0006\u0005\u0003\u0010\u0001\u0005\u0012\u0002\"\u0002\u0013\u0003\u0001\u0004q\u0012\u0001\u00028fqR$\u0012A\u0005"
)
public final class MapValueIterator extends ChampBaseIterator {
   public Object next() {
      if (!this.hasNext()) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      Object value = ((MapNode)this.currentValueNode()).getValue(this.currentValueCursor());
      this.currentValueCursor_$eq(this.currentValueCursor() + 1);
      return value;
   }

   public MapValueIterator(final MapNode rootNode) {
      super(rootNode);
   }
}
