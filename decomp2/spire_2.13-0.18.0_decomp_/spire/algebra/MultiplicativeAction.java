package spire.algebra;

import algebra.ring.AdditiveGroup;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014q!\u0003\u0006\u0011\u0002\u0007\u0005q\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0011\u0005Q\u0004C\u0003<\u0001\u0019\u0005A\bC\u0003B\u0001\u0019\u0005!iB\u0003F\u0015!\u0005aIB\u0003\n\u0015!\u0005q\tC\u0003L\r\u0011\u0005A\nC\u0003N\r\u0011\raJ\u0001\u000bNk2$\u0018\u000e\u001d7jG\u0006$\u0018N^3BGRLwN\u001c\u0006\u0003\u00171\tq!\u00197hK\n\u0014\u0018MC\u0001\u000e\u0003\u0015\u0019\b/\u001b:f\u0007\u0001)2\u0001\u0005\u0013:'\t\u0001\u0011\u0003\u0005\u0002\u0013+5\t1CC\u0001\u0015\u0003\u0015\u00198-\u00197b\u0013\t12CA\u0002B]f\fa\u0001J5oSR$C#A\r\u0011\u0005IQ\u0012BA\u000e\u0014\u0005\u0011)f.\u001b;\u0002\u001d5,H\u000e^5qY&\u001c\u0017\r^5wKV\ta\u0004\u0005\u0003 A\tBT\"\u0001\u0006\n\u0005\u0005R!AB!di&|g\u000e\u0005\u0002$I1\u0001A!C\u0013\u0001A\u0003\u0005\tQ1\u0001'\u0005\u0005\u0001\u0016CA\u0014\u0012!\t\u0011\u0002&\u0003\u0002*'\t9aj\u001c;iS:<\u0007f\u0001\u0013,]A\u0011!\u0003L\u0005\u0003[M\u00111b\u001d9fG&\fG.\u001b>fIF*1e\f\u00193c9\u0011!\u0003M\u0005\u0003cM\t1!\u00138uc\u0011!3g\u000e\u000b\u000f\u0005Q:T\"A\u001b\u000b\u0005Yr\u0011A\u0002\u001fs_>$h(C\u0001\u0015!\t\u0019\u0013\bB\u0003;\u0001\t\u0007aEA\u0001H\u0003\u001d9G/[7fg2$2AI\u001f@\u0011\u0015q4\u00011\u00019\u0003\u00059\u0007\"\u0002!\u0004\u0001\u0004\u0011\u0013!\u00019\u0002\u000f\u001d$\u0018.\\3teR\u0019!e\u0011#\t\u000b\u0001#\u0001\u0019\u0001\u0012\t\u000by\"\u0001\u0019\u0001\u001d\u0002)5+H\u000e^5qY&\u001c\u0017\r^5wK\u0006\u001bG/[8o!\tyba\u0005\u0002\u0007\u0011B\u0011!#S\u0005\u0003\u0015N\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001G\u0003)\u0019\u0016n\u001a8BGRLwN\\\u000b\u0003\u001fJ#\"\u0001U.\u0011\t}\u0001\u0011\u000b\u0016\t\u0003GI#Qa\u0015\u0005C\u0002\u0019\u0012\u0011!\u0011\t\u0003+bs!a\b,\n\u0005]S\u0011a\u00029bG.\fw-Z\u0005\u00033j\u0013AaU5h]*\u0011qK\u0003\u0005\u00069\"\u0001\u001d!X\u0001\u0002\u0003B\u0019QKX)\n\u0005}S&!D!eI&$\u0018N^3He>,\b\u000f"
)
public interface MultiplicativeAction {
   static MultiplicativeAction SignAction(final AdditiveGroup A) {
      return MultiplicativeAction$.MODULE$.SignAction(A);
   }

   // $FF: synthetic method
   static Action multiplicative$(final MultiplicativeAction $this) {
      return $this.multiplicative();
   }

   default Action multiplicative() {
      return new Action() {
         // $FF: synthetic field
         private final MultiplicativeAction $outer;

         public int actr$mcI$sp(final int p, final Object g) {
            return RightAction.actr$mcI$sp$(this, p, g);
         }

         public int actl$mcI$sp(final Object g, final int p) {
            return LeftAction.actl$mcI$sp$(this, g, p);
         }

         public Object actl(final Object g, final Object p) {
            return this.$outer.gtimesl(g, p);
         }

         public Object actr(final Object p, final Object g) {
            return this.$outer.gtimesr(p, g);
         }

         public {
            if (MultiplicativeAction.this == null) {
               throw null;
            } else {
               this.$outer = MultiplicativeAction.this;
            }
         }
      };
   }

   Object gtimesl(final Object g, final Object p);

   Object gtimesr(final Object p, final Object g);

   // $FF: synthetic method
   static Action multiplicative$mcI$sp$(final MultiplicativeAction $this) {
      return $this.multiplicative$mcI$sp();
   }

   default Action multiplicative$mcI$sp() {
      return this.multiplicative();
   }

   // $FF: synthetic method
   static int gtimesl$mcI$sp$(final MultiplicativeAction $this, final Object g, final int p) {
      return $this.gtimesl$mcI$sp(g, p);
   }

   default int gtimesl$mcI$sp(final Object g, final int p) {
      return BoxesRunTime.unboxToInt(this.gtimesl(g, BoxesRunTime.boxToInteger(p)));
   }

   // $FF: synthetic method
   static int gtimesr$mcI$sp$(final MultiplicativeAction $this, final int p, final Object g) {
      return $this.gtimesr$mcI$sp(p, g);
   }

   default int gtimesr$mcI$sp(final int p, final Object g) {
      return BoxesRunTime.unboxToInt(this.gtimesr(BoxesRunTime.boxToInteger(p), g));
   }

   static void $init$(final MultiplicativeAction $this) {
   }
}
