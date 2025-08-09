package org.json4s.scalap;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00052qa\u0001\u0003\u0011\u0002\u0007\u00051\u0002C\u0003\u001a\u0001\u0011\u0005!\u0004C\u0004\u001f\u0001\t\u0007I\u0011A\u0010\u0003\u001dI+H.Z:XSRD7\u000b^1uK*\u0011QAB\u0001\u0007g\u000e\fG.\u00199\u000b\u0005\u001dA\u0011A\u00026t_:$4OC\u0001\n\u0003\ry'oZ\u0002\u0001'\u0011\u0001AB\u0005\f\u0011\u00055\u0001R\"\u0001\b\u000b\u0003=\tQa]2bY\u0006L!!\u0005\b\u0003\r\u0005s\u0017PU3g!\t\u0019B#D\u0001\u0005\u0013\t)BAA\u0003Sk2,7\u000f\u0005\u0002\u0014/%\u0011\u0001\u0004\u0002\u0002\u000b'R\fG/\u001a*vY\u0016\u001c\u0018A\u0002\u0013j]&$H\u0005F\u0001\u001c!\tiA$\u0003\u0002\u001e\u001d\t!QK\\5u\u0003\u001d1\u0017m\u0019;pef,\u0012\u0001\t\t\u0003'\u0001\u0001"
)
public interface RulesWithState extends Rules, StateRules {
   void org$json4s$scalap$RulesWithState$_setter_$factory_$eq(final RulesWithState x$1);

   RulesWithState factory();

   static void $init$(final RulesWithState $this) {
      $this.org$json4s$scalap$RulesWithState$_setter_$factory_$eq($this);
   }
}
