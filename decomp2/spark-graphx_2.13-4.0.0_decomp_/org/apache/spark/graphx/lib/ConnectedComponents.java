package org.apache.spark.graphx.lib;

import org.apache.spark.graphx.Graph;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!<Q!\u0002\u0004\t\u0002E1Qa\u0005\u0004\t\u0002QAQaG\u0001\u0005\u0002qAQ!H\u0001\u0005\u0002yAQ!H\u0001\u0005\u0002a\u000b1cQ8o]\u0016\u001cG/\u001a3D_6\u0004xN\\3oiNT!a\u0002\u0005\u0002\u00071L'M\u0003\u0002\n\u0015\u00051qM]1qQbT!a\u0003\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00055q\u0011AB1qC\u000eDWMC\u0001\u0010\u0003\ry'oZ\u0002\u0001!\t\u0011\u0012!D\u0001\u0007\u0005M\u0019uN\u001c8fGR,GmQ8na>tWM\u001c;t'\t\tQ\u0003\u0005\u0002\u001735\tqCC\u0001\u0019\u0003\u0015\u00198-\u00197b\u0013\tQrC\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003E\t1A];o+\ry2*\u000f\u000b\u0004AA\u001bFcA\u0011C\u001bB!!eI\u00138\u001b\u0005A\u0011B\u0001\u0013\t\u0005\u00159%/\u00199i!\t1CG\u0004\u0002(e9\u0011\u0001&\r\b\u0003SAr!AK\u0018\u000f\u0005-rS\"\u0001\u0017\u000b\u00055\u0002\u0012A\u0002\u001fs_>$h(C\u0001\u0010\u0013\tia\"\u0003\u0002\f\u0019%\u0011\u0011BC\u0005\u0003g!\tq\u0001]1dW\u0006<W-\u0003\u00026m\tAa+\u001a:uKbLEM\u0003\u00024\u0011A\u0011\u0001(\u000f\u0007\u0001\t\u0015Q4A1\u0001<\u0005\t)E)\u0005\u0002=\u007fA\u0011a#P\u0005\u0003}]\u0011qAT8uQ&tw\r\u0005\u0002\u0017\u0001&\u0011\u0011i\u0006\u0002\u0004\u0003:L\bbB\"\u0004\u0003\u0003\u0005\u001d\u0001R\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004cA#I\u00156\taI\u0003\u0002H/\u00059!/\u001a4mK\u000e$\u0018BA%G\u0005!\u0019E.Y:t)\u0006<\u0007C\u0001\u001dL\t\u0015a5A1\u0001<\u0005\t1F\tC\u0004O\u0007\u0005\u0005\t9A(\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007E\u0002F\u0011^BQ!U\u0002A\u0002I\u000bQa\u001a:ba\"\u0004BAI\u0012Ko!)Ak\u0001a\u0001+\u0006iQ.\u0019=Ji\u0016\u0014\u0018\r^5p]N\u0004\"A\u0006,\n\u0005];\"aA%oiV\u0019\u0011LY/\u0015\u0005i3GcA._GB!!eI\u0013]!\tAT\fB\u0003;\t\t\u00071\bC\u0004`\t\u0005\u0005\t9\u00011\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$3\u0007E\u0002F\u0011\u0006\u0004\"\u0001\u000f2\u0005\u000b1#!\u0019A\u001e\t\u000f\u0011$\u0011\u0011!a\u0002K\u0006QQM^5eK:\u001cW\r\n\u001b\u0011\u0007\u0015CE\fC\u0003R\t\u0001\u0007q\r\u0005\u0003#G\u0005d\u0006"
)
public final class ConnectedComponents {
   public static Graph run(final Graph graph, final ClassTag evidence$3, final ClassTag evidence$4) {
      return ConnectedComponents$.MODULE$.run(graph, evidence$3, evidence$4);
   }

   public static Graph run(final Graph graph, final int maxIterations, final ClassTag evidence$1, final ClassTag evidence$2) {
      return ConnectedComponents$.MODULE$.run(graph, maxIterations, evidence$1, evidence$2);
   }
}
