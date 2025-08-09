package org.apache.spark.streaming.dstream;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.StateSpecImpl;
import org.apache.spark.streaming.Time;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Md!B\n\u0015\u0001Yq\u0002\u0002\u0003\u001f\u0001\u0005\u0003\u0005\u000b\u0011B\u001f\t\u0011\r\u0003!\u0011!Q\u0001\n\u0011C\u0001\u0002\u0013\u0001\u0003\u0004\u0003\u0006Y!\u0013\u0005\t\u001f\u0002\u0011\u0019\u0011)A\u0006!\"A\u0011\u000b\u0001B\u0002B\u0003-!\u000b\u0003\u0005T\u0001\t\r\t\u0015a\u0003U\u0011\u0015)\u0006\u0001\"\u0001W\u0011\u001dy\u0006A1A\u0005\n\u0001Da\u0001\u001a\u0001!\u0002\u0013\t\u0007\"B3\u0001\t\u00032\u0007\"\u00026\u0001\t\u0003Z\u0007\"B?\u0001\t\u0003r\bbBA\u000e\u0001\u0011\u0005\u0013Q\u0004\u0005\b\u0003K\u0001A\u0011AA\u0014\u0011\u001d\ti\u0003\u0001C\u0001\u0003_Aq!!\u0013\u0001\t\u0003\tY\u0005C\u0004\u0002X\u0001!\t!!\u0017\t\u000f\u0005\u0015\u0004\u0001\"\u0001\u0002h\t9R*\u00199XSRD7\u000b^1uK\u0012\u001bFO]3b[&k\u0007\u000f\u001c\u0006\u0003+Y\tq\u0001Z:ue\u0016\fWN\u0003\u0002\u00181\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u00033i\tQa\u001d9be.T!a\u0007\u000f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005i\u0012aA8sOV)qD\n\u001b8uM\u0011\u0001\u0001\t\t\u0007C\t\"3GN\u001d\u000e\u0003QI!a\t\u000b\u0003'5\u000b\u0007oV5uQN#\u0018\r^3E'R\u0014X-Y7\u0011\u0005\u00152C\u0002\u0001\u0003\u0006O\u0001\u0011\r!\u000b\u0002\b\u0017\u0016LH+\u001f9f\u0007\u0001\t\"A\u000b\u0019\u0011\u0005-rS\"\u0001\u0017\u000b\u00035\nQa]2bY\u0006L!a\f\u0017\u0003\u000f9{G\u000f[5oOB\u00111&M\u0005\u0003e1\u00121!\u00118z!\t)C\u0007B\u00036\u0001\t\u0007\u0011FA\u0005WC2,X\rV=qKB\u0011Qe\u000e\u0003\u0006q\u0001\u0011\r!\u000b\u0002\n'R\fG/\u001a+za\u0016\u0004\"!\n\u001e\u0005\u000bm\u0002!\u0019A\u0015\u0003\u00155\u000b\u0007\u000f]3e)f\u0004X-\u0001\u0006eCR\f7\u000b\u001e:fC6\u00042!\t A\u0013\tyDCA\u0004E'R\u0014X-Y7\u0011\t-\nEeM\u0005\u0003\u00052\u0012a\u0001V;qY\u0016\u0014\u0014\u0001B:qK\u000e\u0004b!\u0012$%gYJT\"\u0001\f\n\u0005\u001d3\"!D*uCR,7\u000b]3d\u00136\u0004H.\u0001\u0006fm&$WM\\2fII\u00022AS'%\u001b\u0005Y%B\u0001'-\u0003\u001d\u0011XM\u001a7fGRL!AT&\u0003\u0011\rc\u0017m]:UC\u001e\f!\"\u001a<jI\u0016t7-\u001a\u00134!\rQUjM\u0001\u000bKZLG-\u001a8dK\u0012\"\u0004c\u0001&Nm\u0005QQM^5eK:\u001cW\rJ\u001b\u0011\u0007)k\u0015(\u0001\u0004=S:LGO\u0010\u000b\u0004/vsF#\u0002-Z5nc\u0006CB\u0011\u0001IM2\u0014\bC\u0003I\u000f\u0001\u000f\u0011\nC\u0003P\u000f\u0001\u000f\u0001\u000bC\u0003R\u000f\u0001\u000f!\u000bC\u0003T\u000f\u0001\u000fA\u000bC\u0003=\u000f\u0001\u0007Q\bC\u0003D\u000f\u0001\u0007A)\u0001\bj]R,'O\\1m'R\u0014X-Y7\u0016\u0003\u0005\u0004b!\t2%gYJ\u0014BA2\u0015\u0005mIe\u000e^3s]\u0006dW*\u00199XSRD7\u000b^1uK\u0012\u001bFO]3b[\u0006y\u0011N\u001c;fe:\fGn\u0015;sK\u0006l\u0007%A\u0007tY&$W\rR;sCRLwN\\\u000b\u0002OB\u0011Q\t[\u0005\u0003SZ\u0011\u0001\u0002R;sCRLwN\\\u0001\rI\u0016\u0004XM\u001c3f]\u000eLWm]\u000b\u0002YB\u0019Q.\u001e=\u000f\u00059\u001chBA8s\u001b\u0005\u0001(BA9)\u0003\u0019a$o\\8u}%\tQ&\u0003\u0002uY\u00059\u0001/Y2lC\u001e,\u0017B\u0001<x\u0005\u0011a\u0015n\u001d;\u000b\u0005Qd\u0003GA=|!\r\tcH\u001f\t\u0003Km$\u0011\u0002`\u0006\u0002\u0002\u0003\u0005)\u0011A\u0015\u0003\u0007}#\u0013'A\u0004d_6\u0004X\u000f^3\u0015\u0007}\f\t\u0002E\u0003,\u0003\u0003\t)!C\u0002\u0002\u00041\u0012aa\u00149uS>t\u0007#BA\u0004\u0003\u001bITBAA\u0005\u0015\r\tY\u0001G\u0001\u0004e\u0012$\u0017\u0002BA\b\u0003\u0013\u00111A\u0015#E\u0011\u001d\t\u0019\u0002\u0004a\u0001\u0003+\t\u0011B^1mS\u0012$\u0016.\\3\u0011\u0007\u0015\u000b9\"C\u0002\u0002\u001aY\u0011A\u0001V5nK\u0006Q1\r[3dWB|\u0017N\u001c;\u0015\t\u0005}\u0011\u0011\u0005\t\u0004CyJ\u0004BBA\u0012\u001b\u0001\u0007q-\u0001\ndQ\u0016\u001c7\u000e]8j]RLe\u000e^3sm\u0006d\u0017AD:uCR,7K\\1qg\"|Go\u001d\u000b\u0003\u0003S\u0001B!\t \u0002,A!1&\u0011\u00137\u0003!YW-_\"mCN\u001cXCAA\u0019a\u0011\t\u0019$!\u0012\u0011\r\u0005U\u0012QHA\"\u001d\u0011\t9$!\u000f\u0011\u0005=d\u0013bAA\u001eY\u00051\u0001K]3eK\u001aLA!a\u0010\u0002B\t)1\t\\1tg*\u0019\u00111\b\u0017\u0011\u0007\u0015\n)\u0005\u0002\u0006\u0002H=\t\t\u0011!A\u0003\u0002%\u00121a\u0018\u00133\u0003)1\u0018\r\\;f\u00072\f7o]\u000b\u0003\u0003\u001b\u0002D!a\u0014\u0002TA1\u0011QGA\u001f\u0003#\u00022!JA*\t)\t)\u0006EA\u0001\u0002\u0003\u0015\t!\u000b\u0002\u0004?\u0012\u001a\u0014AC:uCR,7\t\\1tgV\u0011\u00111\f\u0019\u0005\u0003;\n\t\u0007\u0005\u0004\u00026\u0005u\u0012q\f\t\u0004K\u0005\u0005DACA2#\u0005\u0005\t\u0011!B\u0001S\t\u0019q\f\n\u001b\u0002\u00175\f\u0007\u000f]3e\u00072\f7o]\u000b\u0003\u0003S\u0002D!a\u001b\u0002pA1\u0011QGA\u001f\u0003[\u00022!JA8\t)\t\tHEA\u0001\u0002\u0003\u0015\t!\u000b\u0002\u0004?\u0012*\u0004"
)
public class MapWithStateDStreamImpl extends MapWithStateDStream {
   private final ClassTag evidence$2;
   private final ClassTag evidence$3;
   private final ClassTag evidence$4;
   private final ClassTag evidence$5;
   private final InternalMapWithStateDStream internalStream;

   private InternalMapWithStateDStream internalStream() {
      return this.internalStream;
   }

   public Duration slideDuration() {
      return this.internalStream().slideDuration();
   }

   public List dependencies() {
      return new .colon.colon(this.internalStream(), scala.collection.immutable.Nil..MODULE$);
   }

   public Option compute(final Time validTime) {
      return this.internalStream().getOrCompute(validTime).map((x$1) -> x$1.flatMap((x$2) -> x$2.mappedData(), this.evidence$5));
   }

   public DStream checkpoint(final Duration checkpointInterval) {
      this.internalStream().checkpoint(checkpointInterval);
      return this;
   }

   public DStream stateSnapshots() {
      return this.internalStream().flatMap((x$3) -> (Iterable)x$3.stateMap().getAll().map((x0$1) -> {
            if (x0$1 != null) {
               Object k = x0$1._1();
               Object s = x0$1._2();
               return new Tuple2(k, s);
            } else {
               throw new MatchError(x0$1);
            }
         }).iterator().to(scala.collection.IterableFactory..MODULE$.toFactory(scala.package..MODULE$.Iterable())), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public Class keyClass() {
      return ((ClassTag)scala.Predef..MODULE$.implicitly(this.evidence$2)).runtimeClass();
   }

   public Class valueClass() {
      return ((ClassTag)scala.Predef..MODULE$.implicitly(this.evidence$3)).runtimeClass();
   }

   public Class stateClass() {
      return ((ClassTag)scala.Predef..MODULE$.implicitly(this.evidence$4)).runtimeClass();
   }

   public Class mappedClass() {
      return ((ClassTag)scala.Predef..MODULE$.implicitly(this.evidence$5)).runtimeClass();
   }

   public MapWithStateDStreamImpl(final DStream dataStream, final StateSpecImpl spec, final ClassTag evidence$2, final ClassTag evidence$3, final ClassTag evidence$4, final ClassTag evidence$5) {
      super(dataStream.context(), evidence$5);
      this.evidence$2 = evidence$2;
      this.evidence$3 = evidence$3;
      this.evidence$4 = evidence$4;
      this.evidence$5 = evidence$5;
      this.internalStream = new InternalMapWithStateDStream(dataStream, spec, evidence$2, evidence$3, evidence$4, evidence$5);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
