package scala.collection.generic;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t2A!\u0002\u0004\u0001\u001b!A!\u0003\u0001B\u0001B\u0003%1\u0003\u0003\u0005\u0017\u0001\t\u0015\r\u0011\"\u0011\u0018\u0011!a\u0002A!A!\u0002\u0013A\u0002\"B\u000f\u0001\t\u0003q\"A\u0006+bO\u001e,G\rR3mK\u001e\fG/\u001a3D_:$X\r\u001f;\u000b\u0005\u001dA\u0011aB4f]\u0016\u0014\u0018n\u0019\u0006\u0003\u0013)\t!bY8mY\u0016\u001cG/[8o\u0015\u0005Y\u0011!B:dC2\f7\u0001A\n\u0003\u00019\u0001\"a\u0004\t\u000e\u0003\u0019I!!\u0005\u0004\u0003!\u0011+G.Z4bi\u0016$7i\u001c8uKb$\u0018!\u00023fY\u0016<\u0007CA\b\u0015\u0013\t)bA\u0001\u0006TS\u001et\u0017\r\u001c7j]\u001e\f1\u0001^1h+\u0005A\u0002CA\r\u001b\u001b\u0005Q\u0011BA\u000e\u000b\u0005\rIe\u000e^\u0001\u0005i\u0006<\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0004?\u0001\n\u0003CA\b\u0001\u0011\u0015\u0011B\u00011\u0001\u0014\u0011\u00151B\u00011\u0001\u0019\u0001"
)
public class TaggedDelegatedContext extends DelegatedContext {
   private final int tag;

   public int tag() {
      return this.tag;
   }

   public TaggedDelegatedContext(final Signalling deleg, final int tag) {
      super(deleg);
      this.tag = tag;
   }
}
