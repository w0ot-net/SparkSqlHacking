package spire.syntax;

import algebra.lattice.Logic;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0011\raCA\u0006M_\u001eL7mU=oi\u0006D(BA\u0003\u0007\u0003\u0019\u0019\u0018P\u001c;bq*\tq!A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0005\u0001Q\u0001CA\u0006\u000f\u001b\u0005a!\"A\u0007\u0002\u000bM\u001c\u0017\r\\1\n\u0005=a!AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002%A\u00111bE\u0005\u0003)1\u0011A!\u00168ji\u0006AAn\\4jG>\u00038/\u0006\u0002\u0018?Q\u0011\u0001D\u0010\u000b\u00033!\u00022AG\u000e\u001e\u001b\u0005!\u0011B\u0001\u000f\u0005\u0005!aunZ5d\u001fB\u001c\bC\u0001\u0010 \u0019\u0001!Q\u0001\t\u0002C\u0002\u0005\u0012\u0011!Q\t\u0003E\u0015\u0002\"aC\u0012\n\u0005\u0011b!a\u0002(pi\"Lgn\u001a\t\u0003\u0017\u0019J!a\n\u0007\u0003\u0007\u0005s\u0017\u0010C\u0004*\u0005\u0005\u0005\t9\u0001\u0016\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$#g\r\t\u0004WmjbB\u0001\u00179\u001d\tiSG\u0004\u0002/g9\u0011qFM\u0007\u0002a)\u0011\u0011\u0007C\u0001\u0007yI|w\u000e\u001e \n\u0003\u001dI!\u0001\u000e\u0004\u0002\u000f\u0005dw-\u001a2sC&\u0011agN\u0001\bY\u0006$H/[2f\u0015\t!d!\u0003\u0002:u\u00059\u0001/Y2lC\u001e,'B\u0001\u001c8\u0013\taTHA\u0003M_\u001eL7M\u0003\u0002:u!)qH\u0001a\u0001;\u0005\t\u0011\r"
)
public interface LogicSyntax {
   // $FF: synthetic method
   static LogicOps logicOps$(final LogicSyntax $this, final Object a, final Logic evidence$23) {
      return $this.logicOps(a, evidence$23);
   }

   default LogicOps logicOps(final Object a, final Logic evidence$23) {
      return new LogicOps(a, evidence$23);
   }

   static void $init$(final LogicSyntax $this) {
   }
}
