package spire.syntax;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000512qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!DA\fJ]:,'\u000f\u0015:pIV\u001cGo\u00159bG\u0016\u001c\u0016P\u001c;bq*\u0011QAB\u0001\u0007gftG/\u0019=\u000b\u0003\u001d\tQa\u001d9je\u0016\u001c\u0001aE\u0002\u0001\u0015A\u0001\"a\u0003\b\u000e\u00031Q\u0011!D\u0001\u0006g\u000e\fG.Y\u0005\u0003\u001f1\u0011a!\u00118z%\u00164\u0007CA\t\u0013\u001b\u0005!\u0011BA\n\u0005\u0005E1Vm\u0019;peN\u0003\u0018mY3Ts:$\u0018\r_\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003Y\u0001\"aC\f\n\u0005aa!\u0001B+oSR\fA#\u001b8oKJ\u0004&o\u001c3vGR\u001c\u0006/Y2f\u001fB\u001cXCA\u000e\")\ta\"\u0006E\u0002\u0012;}I!A\b\u0003\u0003)%sg.\u001a:Qe>$Wo\u0019;Ta\u0006\u001cWm\u00149t!\t\u0001\u0013\u0005\u0004\u0001\u0005\u000b\t\u0012!\u0019A\u0012\u0003\u0003Y\u000b\"\u0001J\u0014\u0011\u0005-)\u0013B\u0001\u0014\r\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"a\u0003\u0015\n\u0005%b!aA!os\")1F\u0001a\u0001?\u0005\ta\u000f"
)
public interface InnerProductSpaceSyntax extends VectorSpaceSyntax {
   // $FF: synthetic method
   static InnerProductSpaceOps innerProductSpaceOps$(final InnerProductSpaceSyntax $this, final Object v) {
      return $this.innerProductSpaceOps(v);
   }

   default InnerProductSpaceOps innerProductSpaceOps(final Object v) {
      return new InnerProductSpaceOps(v);
   }

   static void $init$(final InnerProductSpaceSyntax $this) {
   }
}
