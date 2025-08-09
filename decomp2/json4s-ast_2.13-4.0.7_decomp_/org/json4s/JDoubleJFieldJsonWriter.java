package org.json4s;

import scala.Predef.ArrowAssoc.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q2AAB\u0004\u0007\u0019!A\u0011\u0003\u0001B\u0001B\u0003%!\u0003\u0003\u0005 \u0001\t\u0005\t\u0015!\u0003!\u0011\u0015\u0019\u0003\u0001\"\u0001%\u0011\u0015A\u0003\u0001\"\u0001*\u0011\u0015)\u0004\u0001\"\u00017\u0005]QEi\\;cY\u0016Te)[3mI*\u001bxN\\,sSR,'O\u0003\u0002\t\u0013\u00051!n]8oiMT\u0011AC\u0001\u0004_J<7\u0001A\n\u0003\u00015\u0001\"AD\b\u000e\u0003\u001dI!\u0001E\u0004\u0003))#u.\u001e2mK\u0006\u001bHOS:p]^\u0013\u0018\u000e^3s\u0003\u0011q\u0017-\\3\u0011\u0005MabB\u0001\u000b\u001b!\t)\u0002$D\u0001\u0017\u0015\t92\"\u0001\u0004=e>|GO\u0010\u0006\u00023\u0005)1oY1mC&\u00111\u0004G\u0001\u0007!J,G-\u001a4\n\u0005uq\"AB*ue&twM\u0003\u0002\u001c1\u00051\u0001/\u0019:f]R\u0004\"AD\u0011\n\u0005\t:!\u0001\u0007&E_V\u0014G.\u001a&PE*,7\r\u001e&t_:<&/\u001b;fe\u00061A(\u001b8jiz\"2!\n\u0014(!\tq\u0001\u0001C\u0003\u0012\u0007\u0001\u0007!\u0003C\u0003 \u0007\u0001\u0007\u0001%\u0001\u0004sKN,H\u000e^\u000b\u0002UA\u00111F\r\b\u0003YAr!!L\u0018\u000f\u0005Uq\u0013\"\u0001\u0006\n\u0005!I\u0011BA\u0019\b\u0003\u001dQ5o\u001c8B'RK!a\r\u001b\u0003\r)3\u0016\r\\;f\u0015\t\tt!A\u0004bI\u0012tu\u000eZ3\u0015\u0005]R\u0004c\u0001\b9U%\u0011\u0011h\u0002\u0002\u000b\u0015N|gn\u0016:ji\u0016\u0014\b\"B\u001e\u0006\u0001\u0004Q\u0013\u0001\u00028pI\u0016\u0004"
)
public final class JDoubleJFieldJsonWriter extends JDoubleAstJsonWriter {
   private final String name;
   private final JDoubleJObjectJsonWriter parent;

   public JValue result() {
      return JsonAST$.MODULE$.JNothing();
   }

   public JsonWriter addNode(final JValue node) {
      return this.parent.addNode(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(this.name), node));
   }

   public JDoubleJFieldJsonWriter(final String name, final JDoubleJObjectJsonWriter parent) {
      this.name = name;
      this.parent = parent;
   }
}
