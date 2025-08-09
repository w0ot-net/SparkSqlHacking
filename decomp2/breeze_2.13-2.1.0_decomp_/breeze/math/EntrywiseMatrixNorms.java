package breeze.math;

import breeze.generic.UFunc;
import breeze.linalg.support.CanTraverseValues;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-;Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQ\u0001F\u0001\u0005\u0002UAQAF\u0001\u0005\u0002]\tA#\u00128uef<\u0018n]3NCR\u0014\u0018\u000e\u001f(pe6\u001c(B\u0001\u0004\b\u0003\u0011i\u0017\r\u001e5\u000b\u0003!\taA\u0019:fKj,7\u0001\u0001\t\u0003\u0017\u0005i\u0011!\u0002\u0002\u0015\u000b:$(/_<jg\u0016l\u0015\r\u001e:jq:{'/\\:\u0014\u0005\u0005q\u0001CA\b\u0013\u001b\u0005\u0001\"\"A\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0001\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002\u0015\u0005!Q.Y6f+\rA\u0012e\u000b\u000b\u000535\u00124IE\u0002\u001b\u001dq1AaG\u0002\u00013\taAH]3gS:,W.\u001a8u}A!1\"H\u0010+\u0013\tqRA\u0001\nNCR\u0014\u0018\u000e_%o]\u0016\u0014\bK]8ek\u000e$\bC\u0001\u0011\"\u0019\u0001!QAI\u0002C\u0002\r\u0012\u0011!T\t\u0003I\u001d\u0002\"aD\u0013\n\u0005\u0019\u0002\"a\u0002(pi\"Lgn\u001a\t\u0003\u001f!J!!\u000b\t\u0003\u0007\u0005s\u0017\u0010\u0005\u0002!W\u0011)Af\u0001b\u0001G\t\t1\u000bC\u0003/\u0007\u0001\u000fq&A\u0003gS\u0016dG\rE\u0002\fa)J!!M\u0003\u0003\u000b\u0019KW\r\u001c3\t\u000bM\u001a\u00019\u0001\u001b\u0002\u0011!\fG-Y7be\u0012\u0004R!N\u001f ?}q!AN\u001e\u000e\u0003]R!\u0001O\u001d\u0002\u0013=\u0004XM]1u_J\u001c(B\u0001\u001e\b\u0003\u0019a\u0017N\\1mO&\u0011AhN\u0001\f\u001fBlU\u000f\\*dC2\f'/\u0003\u0002?\u007f\t)\u0011*\u001c9me%\u0011\u0001)\u0011\u0002\u0006+\u001a+hn\u0019\u0006\u0003\u0005\u001e\tqaZ3oKJL7\rC\u0003E\u0007\u0001\u000fQ)\u0001\u0003ji\u0016\u0014\b\u0003\u0002$J?)j\u0011a\u0012\u0006\u0003\u0011f\nqa];qa>\u0014H/\u0003\u0002K\u000f\n\t2)\u00198Ue\u00064XM]:f-\u0006dW/Z:"
)
public final class EntrywiseMatrixNorms {
   public static MatrixInnerProduct make(final Field field, final UFunc.UImpl2 hadamard, final CanTraverseValues iter) {
      return EntrywiseMatrixNorms$.MODULE$.make(field, hadamard, iter);
   }
}
