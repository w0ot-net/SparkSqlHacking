package spire.syntax;

import scala.reflect.ScalaSignature;
import spire.algebra.Action;
import spire.algebra.AdditiveAction;
import spire.algebra.CModule;
import spire.algebra.MultiplicativeAction;
import spire.algebra.VectorSpace;

/** @deprecated */
@ScalaSignature(
   bytes = "\u0006\u0005\u0005uaaB\u0004\t!\u0003\r\t!\u0004\u0005\u0006)\u0001!\t!\u0006\u0005\u00063\u0001!\u0019A\u0007\u0005\u0006u\u0001!\u0019a\u000f\u0005\u0006\u0019\u0002!\u0019!\u0014\u0005\u0006A\u0002!\u0019!\u0019\u0005\u0006e\u0002!\u0019a\u001d\u0002\u000e+:\u0014w.\u001e8e'ftG/\u0019=\u000b\u0005%Q\u0011AB:z]R\f\u0007PC\u0001\f\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u0019\"\u0001\u0001\b\u0011\u0005=\u0011R\"\u0001\t\u000b\u0003E\tQa]2bY\u0006L!a\u0005\t\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\ta\u0003\u0005\u0002\u0010/%\u0011\u0001\u0004\u0005\u0002\u0005+:LG/\u0001\tn_\u0012,H.Z+oE>,h\u000eZ(qgV\u00111d\t\u000b\u00039a\"\"!\b\u0017\u0011\u0007yy\u0012%D\u0001\t\u0013\t\u0001\u0003B\u0001\tN_\u0012,H.Z+oE>,h\u000eZ(qgB\u0011!e\t\u0007\u0001\t\u0015!#A1\u0001&\u0005\u00051\u0015C\u0001\u0014*!\tyq%\u0003\u0002)!\t9aj\u001c;iS:<\u0007CA\b+\u0013\tY\u0003CA\u0002B]fDQ!\f\u0002A\u00049\n!!\u001a<1\u0005=2\u0004\u0003\u0002\u00194k\u0005j\u0011!\r\u0006\u0003e)\tq!\u00197hK\n\u0014\u0018-\u0003\u00025c\t91)T8ek2,\u0007C\u0001\u00127\t%9D&!A\u0001\u0002\u000b\u0005QEA\u0002`IEBQ!\u000f\u0002A\u0002\u0005\n\u0011AZ\u0001\u0016m\u0016\u001cGo\u001c:Ta\u0006\u001cW-\u00168c_VtGm\u00149t+\ta$\t\u0006\u0002>\u0017R\u0011ah\u0011\t\u0004=}\n\u0015B\u0001!\t\u0005U1Vm\u0019;peN\u0003\u0018mY3V]\n|WO\u001c3PaN\u0004\"A\t\"\u0005\u000b\u0011\u001a!\u0019A\u0013\t\u000b5\u001a\u00019\u0001#1\u0005\u0015K\u0005\u0003\u0002\u0019G\u0011\u0006K!aR\u0019\u0003\u0017Y+7\r^8s'B\f7-\u001a\t\u0003E%#\u0011BS\"\u0002\u0002\u0003\u0005)\u0011A\u0013\u0003\u0007}##\u0007C\u0003:\u0007\u0001\u0007\u0011)A\u000bhe>,\b/Q2uS>tWK\u001c2pk:$w\n]:\u0016\u00059#FCA(_)\t\u0001f\u000bE\u0002\u001f#NK!A\u0015\u0005\u0003!\u0005\u001bG/[8o+:\u0014w.\u001e8e\u001fB\u001c\bC\u0001\u0012U\t\u0015)FA1\u0001&\u0005\u00059\u0005\"B\u0017\u0005\u0001\b9\u0006G\u0001-]!\u0011\u0001\u0014lW*\n\u0005i\u000b$AB!di&|g\u000e\u0005\u0002#9\u0012IQLVA\u0001\u0002\u0003\u0015\t!\n\u0002\u0004?\u0012\u001a\u0004\"B0\u0005\u0001\u0004\u0019\u0016!A4\u00021\u0005$G-\u001b;jm\u0016\f5\r^5p]Vs'm\\;oI>\u00038/\u0006\u0002cQR\u00111-\u001d\u000b\u0003I&\u00042AH3h\u0013\t1\u0007B\u0001\rBI\u0012LG/\u001b<f\u0003\u000e$\u0018n\u001c8V]\n|WO\u001c3PaN\u0004\"A\t5\u0005\u000bU+!\u0019A\u0013\t\u000b5*\u00019\u000161\u0005-|\u0007\u0003\u0002\u0019m]\u001eL!!\\\u0019\u0003\u001d\u0005#G-\u001b;jm\u0016\f5\r^5p]B\u0011!e\u001c\u0003\na&\f\t\u0011!A\u0003\u0002\u0015\u00121a\u0018\u00135\u0011\u0015yV\u00011\u0001h\u0003yiW\u000f\u001c;ja2L7-\u0019;jm\u0016\f5\r^5p]Vs'm\\;oI>\u00038/\u0006\u0002uuR\u0019Q/a\u0002\u0015\u0005Y\\\bc\u0001\u0010xs&\u0011\u0001\u0010\u0003\u0002\u001f\u001bVdG/\u001b9mS\u000e\fG/\u001b<f\u0003\u000e$\u0018n\u001c8V]\n|WO\u001c3PaN\u0004\"A\t>\u0005\u000bU3!\u0019A\u0013\t\u000b52\u00019\u0001?1\u0007u\f\u0019\u0001E\u00031}\u0006\u0005\u00110\u0003\u0002\u0000c\t!R*\u001e7uSBd\u0017nY1uSZ,\u0017i\u0019;j_:\u00042AIA\u0002\t)\t)a_A\u0001\u0002\u0003\u0015\t!\n\u0002\u0004?\u0012*\u0004\"B0\u0007\u0001\u0004I\bf\u0003\u0001\u0002\f\u0005E\u00111CA\f\u00033\u00012aDA\u0007\u0013\r\ty\u0001\u0005\u0002\u000bI\u0016\u0004(/Z2bi\u0016$\u0017aB7fgN\fw-Z\u0011\u0003\u0003+\ta$\u00168c_VtG\rI:z]R\f\u0007\u0010I<jY2\u0004#-\u001a\u0011sK6|g/\u001a3\u0002\u000bMLgnY3\"\u0005\u0005m\u0011\u0001D:qSJ,\u0007\u0005\r\u00182q9\u0002\u0004"
)
public interface UnboundSyntax {
   // $FF: synthetic method
   static ModuleUnboundOps moduleUnboundOps$(final UnboundSyntax $this, final Object f, final CModule ev) {
      return $this.moduleUnboundOps(f, ev);
   }

   default ModuleUnboundOps moduleUnboundOps(final Object f, final CModule ev) {
      return new ModuleUnboundOps(f, ev);
   }

   // $FF: synthetic method
   static VectorSpaceUnboundOps vectorSpaceUnboundOps$(final UnboundSyntax $this, final Object f, final VectorSpace ev) {
      return $this.vectorSpaceUnboundOps(f, ev);
   }

   default VectorSpaceUnboundOps vectorSpaceUnboundOps(final Object f, final VectorSpace ev) {
      return new VectorSpaceUnboundOps(f, ev);
   }

   // $FF: synthetic method
   static ActionUnboundOps groupActionUnboundOps$(final UnboundSyntax $this, final Object g, final Action ev) {
      return $this.groupActionUnboundOps(g, ev);
   }

   default ActionUnboundOps groupActionUnboundOps(final Object g, final Action ev) {
      return new ActionUnboundOps(g, ev);
   }

   // $FF: synthetic method
   static AdditiveActionUnboundOps additiveActionUnboundOps$(final UnboundSyntax $this, final Object g, final AdditiveAction ev) {
      return $this.additiveActionUnboundOps(g, ev);
   }

   default AdditiveActionUnboundOps additiveActionUnboundOps(final Object g, final AdditiveAction ev) {
      return new AdditiveActionUnboundOps(g, ev);
   }

   // $FF: synthetic method
   static MultiplicativeActionUnboundOps multiplicativeActionUnboundOps$(final UnboundSyntax $this, final Object g, final MultiplicativeAction ev) {
      return $this.multiplicativeActionUnboundOps(g, ev);
   }

   default MultiplicativeActionUnboundOps multiplicativeActionUnboundOps(final Object g, final MultiplicativeAction ev) {
      return new MultiplicativeActionUnboundOps(g, ev);
   }

   static void $init$(final UnboundSyntax $this) {
   }
}
