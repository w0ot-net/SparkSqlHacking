package org.apache.spark.api.java;

import java.lang.invoke.SerializedLambda;
import java.util.Iterator;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.rdd.HadoopRDD;
import scala.jdk.CollectionConverters.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005=a\u0001B\u0005\u000b\u0001UA\u0011\u0002\f\u0001\u0003\u0002\u0003\u0006I!\f\u001a\t\u0011M\u0002!Q1A\u0005DQB\u0011b\u000f\u0001\u0003\u0002\u0003\u0006I!\u000e\u001f\t\u0011u\u0002!Q1A\u0005DyB\u0011\u0002\u0011\u0001\u0003\u0002\u0003\u0006IaP!\t\u000b\t\u0003A\u0011A\"\t\u000b%\u0003A\u0011\u0001&\t\u000fe\u0004\u0011\u0013!C\u0001u\ni!*\u0019<b\u0011\u0006$wn\u001c9S\t\u0012S!a\u0003\u0007\u0002\t)\fg/\u0019\u0006\u0003\u001b9\t1!\u00199j\u0015\ty\u0001#A\u0003ta\u0006\u00148N\u0003\u0002\u0012%\u00051\u0011\r]1dQ\u0016T\u0011aE\u0001\u0004_J<7\u0001A\u000b\u0004-uQ3C\u0001\u0001\u0018!\u0011A\u0012dG\u0015\u000e\u0003)I!A\u0007\u0006\u0003\u0017)\u000bg/\u0019)bSJ\u0014F\t\u0012\t\u00039ua\u0001\u0001B\u0003\u001f\u0001\t\u0007qDA\u0001L#\t\u0001c\u0005\u0005\u0002\"I5\t!EC\u0001$\u0003\u0015\u00198-\u00197b\u0013\t)#EA\u0004O_RD\u0017N\\4\u0011\u0005\u0005:\u0013B\u0001\u0015#\u0005\r\te.\u001f\t\u00039)\"Qa\u000b\u0001C\u0002}\u0011\u0011AV\u0001\u0004e\u0012$\u0007\u0003\u0002\u001817%j\u0011a\f\u0006\u0003Y9I!!M\u0018\u0003\u0013!\u000bGm\\8q%\u0012#\u0015B\u0001\u0017\u001a\u0003%Y7\t\\1tgR\u000bw-F\u00016!\r1\u0014hG\u0007\u0002o)\u0011\u0001HI\u0001\be\u00164G.Z2u\u0013\tQtG\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003)Y7\t\\1tgR\u000bw\rI\u0005\u0003ge\t\u0011B^\"mCN\u001cH+Y4\u0016\u0003}\u00022AN\u001d*\u0003)18\t\\1tgR\u000bw\rI\u0005\u0003{e\ta\u0001P5oSRtDC\u0001#I)\r)ei\u0012\t\u00051\u0001Y\u0012\u0006C\u00034\r\u0001\u000fQ\u0007C\u0003>\r\u0001\u000fq\bC\u0003-\r\u0001\u0007Q&A\u000enCB\u0004\u0016M\u001d;ji&|gn],ji\"Le\u000e];u'Bd\u0017\u000e^\u000b\u0003\u0017B#2\u0001\u0014*n!\rARjT\u0005\u0003\u001d*\u0011qAS1wCJ#E\t\u0005\u0002\u001d!\u0012)\u0011k\u0002b\u0001?\t\t!\u000bC\u0003T\u000f\u0001\u0007A+A\u0001g!\u0015)\u0006L\u00172m\u001b\u00051&BA,\u000b\u0003!1WO\\2uS>t\u0017BA-W\u0005%1UO\\2uS>t'\u0007\u0005\u0002\\A6\tAL\u0003\u0002^=\u00061Q.\u00199sK\u0012T!a\u0018\t\u0002\r!\fGm\\8q\u0013\t\tGL\u0001\u0006J]B,Ho\u00159mSR\u00042aY4j\u001b\u0005!'BA3g\u0003\u0011)H/\u001b7\u000b\u0003-I!\u0001\u001b3\u0003\u0011%#XM]1u_J\u0004B!\t6\u001cS%\u00111N\t\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0007\r<w\nC\u0004o\u000fA\u0005\t\u0019A8\u0002+A\u0014Xm]3sm\u0016\u001c\b+\u0019:uSRLwN\\5oOB\u0011\u0011\u0005]\u0005\u0003c\n\u0012qAQ8pY\u0016\fg\u000e\u000b\u0002\bgB\u0011Ao^\u0007\u0002k*\u0011aOD\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001=v\u00051!UM^3m_B,'/\u00119j\u0003\u0015j\u0017\r\u001d)beRLG/[8og^KG\u000f[%oaV$8\u000b\u001d7ji\u0012\"WMZ1vYR$#'F\u0002|\u0003\u0017)\u0012\u0001 \u0016\u0003_v\\\u0013A \t\u0004\u007f\u0006\u001dQBAA\u0001\u0015\u0011\t\u0019!!\u0002\u0002\u0013Ut7\r[3dW\u0016$'B\u0001<#\u0013\u0011\tI!!\u0001\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rB\u0003R\u0011\t\u0007q\u0004\u000b\u0002\u0001g\u0002"
)
public class JavaHadoopRDD extends JavaPairRDD {
   public ClassTag kClassTag() {
      return super.kClassTag();
   }

   public ClassTag vClassTag() {
      return super.vClassTag();
   }

   @DeveloperApi
   public JavaRDD mapPartitionsWithInputSplit(final Function2 f, final boolean preservesPartitioning) {
      return new JavaRDD(((HadoopRDD)super.rdd()).mapPartitionsWithInputSplit((a, b) -> .MODULE$.IteratorHasAsScala((Iterator)f.call(a, .MODULE$.IteratorHasAsJava(b).asJava())).asScala(), preservesPartitioning, JavaSparkContext$.MODULE$.fakeClassTag()), JavaSparkContext$.MODULE$.fakeClassTag());
   }

   public boolean mapPartitionsWithInputSplit$default$2() {
      return false;
   }

   public JavaHadoopRDD(final HadoopRDD rdd, final ClassTag kClassTag, final ClassTag vClassTag) {
      super(rdd, kClassTag, vClassTag);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
