package org.apache.spark.streaming.api.java;

import org.apache.spark.api.java.JavaSparkContext.;
import org.apache.spark.streaming.dstream.MapWithStateDStream;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}2A\u0001B\u0003\u0001%!Ia\u0005\u0001B\u0001B\u0003%q%\u000e\u0005\u0007m\u0001!\t!C\u001c\t\u000bi\u0002A\u0011A\u001e\u0003/)\u000bg/Y'ba^KG\u000f[*uCR,Gi\u0015;sK\u0006l'B\u0001\u0004\b\u0003\u0011Q\u0017M^1\u000b\u0005!I\u0011aA1qS*\u0011!bC\u0001\ngR\u0014X-Y7j]\u001eT!\u0001D\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00059y\u0011AB1qC\u000eDWMC\u0001\u0011\u0003\ry'oZ\u0002\u0001+\u0015\u0019R\u0006M\u001a\u001b'\t\u0001A\u0003E\u0002\u0016-ai\u0011!B\u0005\u0003/\u0015\u00111BS1wC\u0012\u001bFO]3b[B\u0011\u0011D\u0007\u0007\u0001\t\u0015Y\u0002A1\u0001\u001d\u0005)i\u0015\r\u001d9fIRK\b/Z\t\u0003;\r\u0002\"AH\u0011\u000e\u0003}Q\u0011\u0001I\u0001\u0006g\u000e\fG.Y\u0005\u0003E}\u0011qAT8uQ&tw\r\u0005\u0002\u001fI%\u0011Qe\b\u0002\u0004\u0003:L\u0018a\u00023tiJ,\u0017-\u001c\t\u0007Q)bsF\r\r\u000e\u0003%R!AJ\u0005\n\u0005-J#aE'ba^KG\u000f[*uCR,Gi\u0015;sK\u0006l\u0007CA\r.\t\u0015q\u0003A1\u0001\u001d\u0005\u001dYU-\u001f+za\u0016\u0004\"!\u0007\u0019\u0005\u000bE\u0002!\u0019\u0001\u000f\u0003\u0013Y\u000bG.^3UsB,\u0007CA\r4\t\u0015!\u0004A1\u0001\u001d\u0005%\u0019F/\u0019;f)f\u0004X-\u0003\u0002'-\u00051A(\u001b8jiz\"\"\u0001O\u001d\u0011\rU\u0001Af\f\u001a\u0019\u0011\u00151#\u00011\u0001(\u00039\u0019H/\u0019;f':\f\u0007o\u001d5piN$\u0012\u0001\u0010\t\u0005+ub#'\u0003\u0002?\u000b\ty!*\u0019<b!\u0006L'\u000fR*ue\u0016\fW\u000e"
)
public class JavaMapWithStateDStream extends JavaDStream {
   public JavaPairDStream stateSnapshots() {
      return new JavaPairDStream(((MapWithStateDStream)super.dstream()).stateSnapshots(), .MODULE$.fakeClassTag(), .MODULE$.fakeClassTag());
   }

   public JavaMapWithStateDStream(final MapWithStateDStream dstream) {
      super(dstream, .MODULE$.fakeClassTag());
   }
}
