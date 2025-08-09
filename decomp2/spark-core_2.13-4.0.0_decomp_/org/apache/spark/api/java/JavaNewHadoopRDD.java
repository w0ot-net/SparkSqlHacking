package org.apache.spark.api.java;

import java.lang.invoke.SerializedLambda;
import java.util.Iterator;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.rdd.NewHadoopRDD;
import scala.jdk.CollectionConverters.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005=a\u0001B\u0005\u000b\u0001UA\u0011\u0002\f\u0001\u0003\u0002\u0003\u0006I!\f\u001a\t\u0011M\u0002!Q1A\u0005DQB\u0011b\u000f\u0001\u0003\u0002\u0003\u0006I!\u000e\u001f\t\u0011u\u0002!Q1A\u0005DyB\u0011\u0002\u0011\u0001\u0003\u0002\u0003\u0006IaP!\t\u000b\t\u0003A\u0011A\"\t\u000b%\u0003A\u0011\u0001&\t\u000fe\u0004\u0011\u0013!C\u0001u\n\u0001\"*\u0019<b\u001d\u0016<\b*\u00193p_B\u0014F\t\u0012\u0006\u0003\u00171\tAA[1wC*\u0011QBD\u0001\u0004CBL'BA\b\u0011\u0003\u0015\u0019\b/\u0019:l\u0015\t\t\"#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002'\u0005\u0019qN]4\u0004\u0001U\u0019a#\b\u0016\u0014\u0005\u00019\u0002\u0003\u0002\r\u001a7%j\u0011AC\u0005\u00035)\u00111BS1wCB\u000b\u0017N\u001d*E\tB\u0011A$\b\u0007\u0001\t\u0015q\u0002A1\u0001 \u0005\u0005Y\u0015C\u0001\u0011'!\t\tC%D\u0001#\u0015\u0005\u0019\u0013!B:dC2\f\u0017BA\u0013#\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!I\u0014\n\u0005!\u0012#aA!osB\u0011AD\u000b\u0003\u0006W\u0001\u0011\ra\b\u0002\u0002-\u0006\u0019!\u000f\u001a3\u0011\t9\u00024$K\u0007\u0002_)\u0011AFD\u0005\u0003c=\u0012ABT3x\u0011\u0006$wn\u001c9S\t\u0012K!\u0001L\r\u0002\u0013-\u001cE.Y:t)\u0006<W#A\u001b\u0011\u0007YJ4$D\u00018\u0015\tA$%A\u0004sK\u001adWm\u0019;\n\u0005i:$\u0001C\"mCN\u001cH+Y4\u0002\u0015-\u001cE.Y:t)\u0006<\u0007%\u0003\u000243\u0005Iao\u00117bgN$\u0016mZ\u000b\u0002\u007fA\u0019a'O\u0015\u0002\u0015Y\u001cE.Y:t)\u0006<\u0007%\u0003\u0002>3\u00051A(\u001b8jiz\"\"\u0001\u0012%\u0015\u0007\u00153u\t\u0005\u0003\u0019\u0001mI\u0003\"B\u001a\u0007\u0001\b)\u0004\"B\u001f\u0007\u0001\by\u0004\"\u0002\u0017\u0007\u0001\u0004i\u0013aG7baB\u000b'\u000f^5uS>t7oV5uQ&s\u0007/\u001e;Ta2LG/\u0006\u0002L!R\u0019AJU7\u0011\u0007aiu*\u0003\u0002O\u0015\t9!*\u0019<b%\u0012#\u0005C\u0001\u000fQ\t\u0015\tvA1\u0001 \u0005\u0005\u0011\u0006\"B*\b\u0001\u0004!\u0016!\u00014\u0011\u000bUC&L\u00197\u000e\u0003YS!a\u0016\u0006\u0002\u0011\u0019,hn\u0019;j_:L!!\u0017,\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004CA.a\u001b\u0005a&BA/_\u0003%i\u0017\r\u001d:fIV\u001cWM\u0003\u0002`!\u00051\u0001.\u00193p_BL!!\u0019/\u0003\u0015%s\u0007/\u001e;Ta2LG\u000fE\u0002dO&l\u0011\u0001\u001a\u0006\u0003K\u001a\fA!\u001e;jY*\t1\"\u0003\u0002iI\nA\u0011\n^3sCR|'\u000f\u0005\u0003\"UnI\u0013BA6#\u0005\u0019!V\u000f\u001d7feA\u00191mZ(\t\u000f9<\u0001\u0013!a\u0001_\u0006)\u0002O]3tKJ4Xm\u001d)beRLG/[8oS:<\u0007CA\u0011q\u0013\t\t(EA\u0004C_>dW-\u00198)\u0005\u001d\u0019\bC\u0001;x\u001b\u0005)(B\u0001<\u000f\u0003)\tgN\\8uCRLwN\\\u0005\u0003qV\u0014A\u0002R3wK2|\u0007/\u001a:Ba&\fQ%\\1q!\u0006\u0014H/\u001b;j_:\u001cx+\u001b;i\u0013:\u0004X\u000f^*qY&$H\u0005Z3gCVdG\u000f\n\u001a\u0016\u0007m\fY!F\u0001}U\tyWpK\u0001\u007f!\ry\u0018qA\u0007\u0003\u0003\u0003QA!a\u0001\u0002\u0006\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0003m\nJA!!\u0003\u0002\u0002\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000bEC!\u0019A\u0010)\u0005\u0001\u0019\b"
)
public class JavaNewHadoopRDD extends JavaPairRDD {
   public ClassTag kClassTag() {
      return super.kClassTag();
   }

   public ClassTag vClassTag() {
      return super.vClassTag();
   }

   @DeveloperApi
   public JavaRDD mapPartitionsWithInputSplit(final Function2 f, final boolean preservesPartitioning) {
      return new JavaRDD(((NewHadoopRDD)super.rdd()).mapPartitionsWithInputSplit((a, b) -> .MODULE$.IteratorHasAsScala((Iterator)f.call(a, .MODULE$.IteratorHasAsJava(b).asJava())).asScala(), preservesPartitioning, JavaSparkContext$.MODULE$.fakeClassTag()), JavaSparkContext$.MODULE$.fakeClassTag());
   }

   public boolean mapPartitionsWithInputSplit$default$2() {
      return false;
   }

   public JavaNewHadoopRDD(final NewHadoopRDD rdd, final ClassTag kClassTag, final ClassTag vClassTag) {
      super(rdd, kClassTag, vClassTag);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
