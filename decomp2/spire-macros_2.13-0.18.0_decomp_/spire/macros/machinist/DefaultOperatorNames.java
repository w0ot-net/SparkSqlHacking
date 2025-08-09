package spire.macros.machinist;

import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%2qa\u0001\u0003\u0011\u0002\u0007\u00051\u0002C\u0003\u0013\u0001\u0011\u00051\u0003C\u0004\u0018\u0001\t\u0007I\u0011\u0001\r\u0003)\u0011+g-Y;mi>\u0003XM]1u_Jt\u0015-\\3t\u0015\t)a!A\u0005nC\u000eD\u0017N\\5ti*\u0011q\u0001C\u0001\u0007[\u0006\u001c'o\\:\u000b\u0003%\tQa\u001d9je\u0016\u001c\u0001a\u0005\u0002\u0001\u0019A\u0011Q\u0002E\u0007\u0002\u001d)\tq\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0012\u001d\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#\u0001\u000b\u0011\u00055)\u0012B\u0001\f\u000f\u0005\u0011)f.\u001b;\u0002\u001b=\u0004XM]1u_Jt\u0015-\\3t+\u0005I\u0002\u0003\u0002\u000e C\u0005j\u0011a\u0007\u0006\u00039u\t\u0011\"[7nkR\f'\r\\3\u000b\u0005yq\u0011AC2pY2,7\r^5p]&\u0011\u0001e\u0007\u0002\u0004\u001b\u0006\u0004\bC\u0001\u0012(\u001b\u0005\u0019#B\u0001\u0013&\u0003\u0011a\u0017M\\4\u000b\u0003\u0019\nAA[1wC&\u0011\u0001f\t\u0002\u0007'R\u0014\u0018N\\4"
)
public interface DefaultOperatorNames {
   void spire$macros$machinist$DefaultOperatorNames$_setter_$operatorNames_$eq(final Map x$1);

   Map operatorNames();

   static void $init$(final DefaultOperatorNames $this) {
      $this.spire$macros$machinist$DefaultOperatorNames$_setter_$operatorNames_$eq((Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2("$eq$eq$eq", "eqv"), new Tuple2("$eq$bang$eq", "neqv"), new Tuple2("$greater", "gt"), new Tuple2("$greater$eq", "gteqv"), new Tuple2("$less", "lt"), new Tuple2("$less$eq", "lteqv"), new Tuple2("$bar$plus$bar", "combine"), new Tuple2("$bar$minus$bar", "remove"), new Tuple2("unary_$minus", "negate"), new Tuple2("$plus", "plus"), new Tuple2("$minus", "minus"), new Tuple2("$times", "times"), new Tuple2("$times$times", "pow"), new Tuple2("$div$tilde", "quot"), new Tuple2("$percent", "mod"), new Tuple2("$div$percent", "quotmod"), new Tuple2("$div", "div"), new Tuple2("$up", "xor"), new Tuple2("$bar", "or"), new Tuple2("$amp", "and"), new Tuple2("unary_$tilde", "complement"), new Tuple2("$less$less", "leftShift"), new Tuple2("$greater$greater$greater", "rightShift"), new Tuple2("$greater$greater", "signedRightShift"), new Tuple2("$times$colon", "timesl"), new Tuple2("$colon$times", "timesr"), new Tuple2("$colon$div", "divr"), new Tuple2("$u22C5", "dot"), new Tuple2("$bar$plus$bar$greater", "actl"), new Tuple2("$less$bar$plus$bar", "actr"), new Tuple2("$plus$greater", "gplusl"), new Tuple2("$less$plus", "gplusr"), new Tuple2("$times$greater", "gtimesl"), new Tuple2("$less$times", "gtimesr"), new Tuple2("$less$bar$minus$bar$greater", "pdiff"), new Tuple2("$less$minus$greater", "pminus"), new Tuple2("$less$div$greater", "pdiv")}))));
   }
}
