package scala.collection.immutable;

import scala.collection.Iterator$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!2A\u0001B\u0003\u0007\u0019!A\u0011\u0005\u0001B\u0001B\u0003%a\u0004C\u0003#\u0001\u0011\u00051\u0005C\u0003'\u0001\u0011\u0005qEA\u0006TKRLE/\u001a:bi>\u0014(B\u0001\u0004\b\u0003%IW.\\;uC\ndWM\u0003\u0002\t\u0013\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003)\tQa]2bY\u0006\u001c\u0001!\u0006\u0002\u000e)M\u0011\u0001A\u0004\t\u0005\u001fA\u0011b$D\u0001\u0006\u0013\t\tRAA\tDQ\u0006l\u0007OQ1tK&#XM]1u_J\u0004\"a\u0005\u000b\r\u0001\u0011)Q\u0003\u0001b\u0001-\t\t\u0011)\u0005\u0002\u00187A\u0011\u0001$G\u0007\u0002\u0013%\u0011!$\u0003\u0002\b\u001d>$\b.\u001b8h!\tAB$\u0003\u0002\u001e\u0013\t\u0019\u0011I\\=\u0011\u0007=y\"#\u0003\u0002!\u000b\t91+\u001a;O_\u0012,\u0017\u0001\u0003:p_Rtu\u000eZ3\u0002\rqJg.\u001b;?)\t!S\u0005E\u0002\u0010\u0001IAQ!\t\u0002A\u0002y\tAA\\3yiR\t!\u0003"
)
public final class SetIterator extends ChampBaseIterator {
   public Object next() {
      if (!this.hasNext()) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      Object payload = ((SetNode)this.currentValueNode()).getPayload(this.currentValueCursor());
      this.currentValueCursor_$eq(this.currentValueCursor() + 1);
      return payload;
   }

   public SetIterator(final SetNode rootNode) {
      super(rootNode);
   }
}
