package spire.syntax;

import scala.reflect.ScalaSignature;
import spire.algebra.Involution;

@ScalaSignature(
   bytes = "\u0006\u0005I2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0011\raC\u0001\tJ]Z|G.\u001e;j_:\u001c\u0016P\u001c;bq*\u0011QAB\u0001\u0007gftG/\u0019=\u000b\u0003\u001d\tQa\u001d9je\u0016\u001c\u0001a\u0005\u0002\u0001\u0015A\u00111BD\u0007\u0002\u0019)\tQ\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0010\u0019\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#\u0001\n\u0011\u0005-\u0019\u0012B\u0001\u000b\r\u0005\u0011)f.\u001b;\u0002\u001b%tgo\u001c7vi&|gn\u00149t+\t9r\u0004\u0006\u0002\u0019aQ\u0011\u0011\u0004\u000b\t\u00045miR\"\u0001\u0003\n\u0005q!!!D%om>dW\u000f^5p]>\u00038\u000f\u0005\u0002\u001f?1\u0001A!\u0002\u0011\u0003\u0005\u0004\t#!A!\u0012\u0005\t*\u0003CA\u0006$\u0013\t!CBA\u0004O_RD\u0017N\\4\u0011\u0005-1\u0013BA\u0014\r\u0005\r\te.\u001f\u0005\bS\t\t\t\u0011q\u0001+\u0003))g/\u001b3f]\u000e,GE\u000e\t\u0004W9jR\"\u0001\u0017\u000b\u000552\u0011aB1mO\u0016\u0014'/Y\u0005\u0003_1\u0012!\"\u00138w_2,H/[8o\u0011\u0015\t$\u00011\u0001\u001e\u0003\ra\u0007n\u001d"
)
public interface InvolutionSyntax {
   // $FF: synthetic method
   static InvolutionOps involutionOps$(final InvolutionSyntax $this, final Object lhs, final Involution evidence$6) {
      return $this.involutionOps(lhs, evidence$6);
   }

   default InvolutionOps involutionOps(final Object lhs, final Involution evidence$6) {
      return new InvolutionOps(lhs, evidence$6);
   }

   static void $init$(final InvolutionSyntax $this) {
   }
}
