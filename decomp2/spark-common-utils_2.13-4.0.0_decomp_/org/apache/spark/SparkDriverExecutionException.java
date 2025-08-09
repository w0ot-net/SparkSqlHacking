package org.apache.spark;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r2Qa\u0001\u0003\u0001\t)A\u0001b\u0004\u0001\u0003\u0002\u0003\u0006I!\u0005\u0005\u0006?\u0001!\t\u0001\t\u0002\u001e'B\f'o\u001b#sSZ,'/\u0012=fGV$\u0018n\u001c8Fq\u000e,\u0007\u000f^5p]*\u0011QAB\u0001\u0006gB\f'o\u001b\u0006\u0003\u000f!\ta!\u00199bG\",'\"A\u0005\u0002\u0007=\u0014xm\u0005\u0002\u0001\u0017A\u0011A\"D\u0007\u0002\t%\u0011a\u0002\u0002\u0002\u000f'B\f'o[#yG\u0016\u0004H/[8o\u0003\u0015\u0019\u0017-^:f\u0007\u0001\u0001\"A\u0005\u000f\u000f\u0005MIbB\u0001\u000b\u0018\u001b\u0005)\"B\u0001\f\u0011\u0003\u0019a$o\\8u}%\t\u0001$A\u0003tG\u0006d\u0017-\u0003\u0002\u001b7\u00059\u0001/Y2lC\u001e,'\"\u0001\r\n\u0005uq\"!\u0003+ie><\u0018M\u00197f\u0015\tQ2$\u0001\u0004=S:LGO\u0010\u000b\u0003C\t\u0002\"\u0001\u0004\u0001\t\u000b=\u0011\u0001\u0019A\t"
)
public class SparkDriverExecutionException extends SparkException {
   public SparkDriverExecutionException(final Throwable cause) {
      super("Execution error", cause);
   }
}
