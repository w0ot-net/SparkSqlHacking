package com.twitter.chill;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u00059:Q!\u0002\u0004\t\u000251Qa\u0004\u0004\t\u0002AAQaH\u0001\u0005\u0002\u0001BQ!I\u0001\u0005\u0002\tBqAJ\u0001\u0002\u0002\u0013%q%A\fTG\u0006d\u0017\rV;qY\u0016\u001cVM]5bY&T\u0018\r^5p]*\u0011q\u0001C\u0001\u0006G\"LG\u000e\u001c\u0006\u0003\u0013)\tq\u0001^<jiR,'OC\u0001\f\u0003\r\u0019w.\\\u0002\u0001!\tq\u0011!D\u0001\u0007\u0005]\u00196-\u00197b)V\u0004H.Z*fe&\fG.\u001b>bi&|gnE\u0002\u0002#]\u0001\"AE\u000b\u000e\u0003MQ\u0011\u0001F\u0001\u0006g\u000e\fG.Y\u0005\u0003-M\u0011a!\u00118z%\u00164\u0007C\u0001\r\u001e\u001b\u0005I\"B\u0001\u000e\u001c\u0003\tIwNC\u0001\u001d\u0003\u0011Q\u0017M^1\n\u0005yI\"\u0001D*fe&\fG.\u001b>bE2,\u0017A\u0002\u001fj]&$h\bF\u0001\u000e\u0003!\u0011XmZ5ti\u0016\u0014X#A\u0012\u0011\u00059!\u0013BA\u0013\u0007\u00059I5J]=p%\u0016<\u0017n\u001d;sCJ\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012\u0001\u000b\t\u0003S1j\u0011A\u000b\u0006\u0003Wm\tA\u0001\\1oO&\u0011QF\u000b\u0002\u0007\u001f\nTWm\u0019;"
)
public final class ScalaTupleSerialization {
   public static IKryoRegistrar register() {
      return ScalaTupleSerialization$.MODULE$.register();
   }
}
