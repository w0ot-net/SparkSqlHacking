package org.apache.spark.mllib.tree.impurity;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I;Qa\u0002\u0005\t\u0002U1Qa\u0006\u0005\t\u0002aAQAI\u0001\u0005\u0002\rBQ\u0001J\u0001\u0005B\u0015BQ\u0001J\u0001\u0005BeBQ\u0001Q\u0001\u0005\u0002\u0005CqAR\u0001\u0002\u0002\u0013%q)\u0001\u0005WCJL\u0017M\\2f\u0015\tI!\"\u0001\u0005j[B,(/\u001b;z\u0015\tYA\"\u0001\u0003ue\u0016,'BA\u0007\u000f\u0003\u0015iG\u000e\\5c\u0015\ty\u0001#A\u0003ta\u0006\u00148N\u0003\u0002\u0012%\u00051\u0011\r]1dQ\u0016T\u0011aE\u0001\u0004_J<7\u0001\u0001\t\u0003-\u0005i\u0011\u0001\u0003\u0002\t-\u0006\u0014\u0018.\u00198dKN\u0019\u0011!G\u0010\u0011\u0005iiR\"A\u000e\u000b\u0003q\tQa]2bY\u0006L!AH\u000e\u0003\r\u0005s\u0017PU3g!\t1\u0002%\u0003\u0002\"\u0011\tA\u0011*\u001c9ve&$\u00180\u0001\u0004=S:LGO\u0010\u000b\u0002+\u0005I1-\u00197dk2\fG/\u001a\u000b\u0004M%r\u0003C\u0001\u000e(\u0013\tA3D\u0001\u0004E_V\u0014G.\u001a\u0005\u0006U\r\u0001\raK\u0001\u0007G>,h\u000e^:\u0011\u0007iac%\u0003\u0002.7\t)\u0011I\u001d:bs\")qf\u0001a\u0001M\u0005QAo\u001c;bY\u000e{WO\u001c;)\u0007\r\tt\u0007\u0005\u00023k5\t1G\u0003\u00025\u001d\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005Y\u001a$!B*j]\u000e,\u0017%\u0001\u001d\u0002\u000bEr\u0013G\f\u0019\u0015\t\u0019RDH\u0010\u0005\u0006w\u0011\u0001\rAJ\u0001\u0006G>,h\u000e\u001e\u0005\u0006{\u0011\u0001\rAJ\u0001\u0004gVl\u0007\"B \u0005\u0001\u00041\u0013AC:v[N\u000bX/\u0019:fg\u0006A\u0011N\\:uC:\u001cW-F\u0001C\u001b\u0005\t\u0001fA\u00032\t\u0006\nQ)A\u00032]Ar\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001I!\tIe*D\u0001K\u0015\tYE*\u0001\u0003mC:<'\"A'\u0002\t)\fg/Y\u0005\u0003\u001f*\u0013aa\u00142kK\u000e$\bfA\u00012\t\"\u001a\u0001!\r#"
)
public final class Variance {
   public static Variance$ instance() {
      return Variance$.MODULE$.instance();
   }

   public static double calculate(final double count, final double sum, final double sumSquares) {
      return Variance$.MODULE$.calculate(count, sum, sumSquares);
   }

   public static double calculate(final double[] counts, final double totalCount) {
      return Variance$.MODULE$.calculate(counts, totalCount);
   }
}
