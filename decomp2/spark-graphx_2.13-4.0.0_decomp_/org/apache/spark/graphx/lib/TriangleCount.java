package org.apache.spark.graphx.lib;

import org.apache.spark.graphx.Graph;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U;Q!\u0002\u0004\t\u0002E1Qa\u0005\u0004\t\u0002QAQaG\u0001\u0005\u0002qAQ!H\u0001\u0005\u0002yAQ\u0001R\u0001\u0005\u0002\u0015\u000bQ\u0002\u0016:jC:<G.Z\"pk:$(BA\u0004\t\u0003\ra\u0017N\u0019\u0006\u0003\u0013)\taa\u001a:ba\"D(BA\u0006\r\u0003\u0015\u0019\b/\u0019:l\u0015\tia\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001f\u0005\u0019qN]4\u0004\u0001A\u0011!#A\u0007\u0002\r\tiAK]5b]\u001edWmQ8v]R\u001c\"!A\u000b\u0011\u0005YIR\"A\f\u000b\u0003a\tQa]2bY\u0006L!AG\f\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\t\u0011#A\u0002sk:,2a\b\u001f+)\t\u0001\u0013\tF\u0002\"gy\u0002BAI\u0012&Q5\t\u0001\"\u0003\u0002%\u0011\t)qI]1qQB\u0011aCJ\u0005\u0003O]\u00111!\u00138u!\tI#\u0006\u0004\u0001\u0005\u000b-\u001a!\u0019\u0001\u0017\u0003\u0005\u0015#\u0015CA\u00171!\t1b&\u0003\u00020/\t9aj\u001c;iS:<\u0007C\u0001\f2\u0013\t\u0011tCA\u0002B]fDq\u0001N\u0002\u0002\u0002\u0003\u000fQ'\u0001\u0006fm&$WM\\2fIE\u00022AN\u001d<\u001b\u00059$B\u0001\u001d\u0018\u0003\u001d\u0011XM\u001a7fGRL!AO\u001c\u0003\u0011\rc\u0017m]:UC\u001e\u0004\"!\u000b\u001f\u0005\u000bu\u001a!\u0019\u0001\u0017\u0003\u0005Y#\u0005bB \u0004\u0003\u0003\u0005\u001d\u0001Q\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004c\u0001\u001c:Q!)!i\u0001a\u0001\u0007\u0006)qM]1qQB!!eI\u001e)\u0003M\u0011XO\u001c)sK\u000e\u000bgn\u001c8jG\u0006d\u0017N_3e+\r1uJ\u0013\u000b\u0003\u000fN#2\u0001S&Q!\u0011\u00113%J%\u0011\u0005%RE!B\u0016\u0005\u0005\u0004a\u0003b\u0002'\u0005\u0003\u0003\u0005\u001d!T\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004c\u0001\u001c:\u001dB\u0011\u0011f\u0014\u0003\u0006{\u0011\u0011\r\u0001\f\u0005\b#\u0012\t\t\u0011q\u0001S\u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u0004meJ\u0005\"\u0002\"\u0005\u0001\u0004!\u0006\u0003\u0002\u0012$\u001d&\u0003"
)
public final class TriangleCount {
   public static Graph runPreCanonicalized(final Graph graph, final ClassTag evidence$3, final ClassTag evidence$4) {
      return TriangleCount$.MODULE$.runPreCanonicalized(graph, evidence$3, evidence$4);
   }

   public static Graph run(final Graph graph, final ClassTag evidence$1, final ClassTag evidence$2) {
      return TriangleCount$.MODULE$.run(graph, evidence$1, evidence$2);
   }
}
