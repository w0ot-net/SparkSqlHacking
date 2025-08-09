package org.apache.spark.api.r;

import java.io.Serializable;
import java.util.Map;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaRDD$;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.rdd.RDD;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ud\u0001\u0002\n\u0014\tyA\u0001\u0002\u000f\u0001\u0003\u0002\u0003\u0006I!\u000f\u0005\t\u007f\u0001\u0011\t\u0011)A\u0005e!A\u0001\t\u0001B\u0001B\u0003%\u0011\t\u0003\u0005M\u0001\t\u0005\t\u0015!\u0003B\u0011!i\u0005A!A!\u0002\u0013\u0011\u0004\u0002\u0003(\u0001\u0005\u0003\u0005\u000b\u0011B(\t\u0011a\u0003!1!Q\u0001\feCQa\u0018\u0001\u0005\u0002\u0001D\u0001B\u001b\u0001\t\u0006\u0004%\ta[\u0004\u0007cNA\ta\u0006:\u0007\rI\u0019\u0002\u0012A\ft\u0011\u0015y6\u0002\"\u0001~\u0011\u0015q8\u0002\"\u0001\u0000\u0011\u001d\tic\u0003C\u0001\u0003_Aq!a\u000f\f\t\u0003\ti\u0004\u0003\u0005\u0002P-!\taFA)\u0011%\t\thCA\u0001\n\u0013\t\u0019H\u0001\u0003S%\u0012#%B\u0001\u000b\u0016\u0003\u0005\u0011(B\u0001\f\u0018\u0003\r\t\u0007/\u001b\u0006\u00031e\tQa\u001d9be.T!AG\u000e\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0012aA8sO\u000e\u0001QCA\u0010''\t\u0001\u0001\u0005\u0005\u0003\"E\u0011\u0012T\"A\n\n\u0005\r\u001a\"\u0001\u0003\"bg\u0016\u0014&\u000b\u0012#\u0011\u0005\u00152C\u0002\u0001\u0003\u0006O\u0001\u0011\r\u0001\u000b\u0002\u0002)F\u0011\u0011f\f\t\u0003U5j\u0011a\u000b\u0006\u0002Y\u0005)1oY1mC&\u0011af\u000b\u0002\b\u001d>$\b.\u001b8h!\tQ\u0003'\u0003\u00022W\t\u0019\u0011I\\=\u0011\u0007)\u001aT'\u0003\u00025W\t)\u0011I\u001d:bsB\u0011!FN\u0005\u0003o-\u0012AAQ=uK\u00061\u0001/\u0019:f]R\u00042AO\u001f%\u001b\u0005Y$B\u0001\u001f\u0018\u0003\r\u0011H\rZ\u0005\u0003}m\u00121A\u0015#E\u0003\u00111WO\\2\u0002\u0019\u0011,7/\u001a:jC2L'0\u001a:\u0011\u0005\tKeBA\"H!\t!5&D\u0001F\u0015\t1U$\u0001\u0004=e>|GOP\u0005\u0003\u0011.\na\u0001\u0015:fI\u00164\u0017B\u0001&L\u0005\u0019\u0019FO]5oO*\u0011\u0001jK\u0001\u000bg\u0016\u0014\u0018.\u00197ju\u0016\u0014\u0018\u0001\u00049bG.\fw-\u001a(b[\u0016\u001c\u0018!\u00042s_\u0006$7-Y:u-\u0006\u00148\u000fE\u0002+gA\u0003\"!\u0015,\u000e\u0003IS!a\u0015+\u0002\t1\fgn\u001a\u0006\u0002+\u0006!!.\u0019<b\u0013\t9&K\u0001\u0004PE*,7\r^\u0001\u000bKZLG-\u001a8dK\u0012\"\u0004c\u0001.^I5\t1L\u0003\u0002]W\u00059!/\u001a4mK\u000e$\u0018B\u00010\\\u0005!\u0019E.Y:t)\u0006<\u0017A\u0002\u001fj]&$h\bF\u0004bI\u00164w\r[5\u0015\u0005\t\u001c\u0007cA\u0011\u0001I!)\u0001\f\u0003a\u00023\")\u0001\b\u0003a\u0001s!)q\b\u0003a\u0001e!)\u0001\t\u0003a\u0001\u0003\")A\n\u0003a\u0001\u0003\")Q\n\u0003a\u0001e!)a\n\u0003a\u0001\u001f\u0006I\u0011m\u001d&bm\u0006\u0014F\tR\u000b\u0002YB\u0019Qn\u001c\u001a\u000e\u00039T!!V\u000b\n\u0005At'a\u0002&bm\u0006\u0014F\tR\u0001\u0005%J#E\t\u0005\u0002\"\u0017M\u00191\u0002^<\u0011\u0005)*\u0018B\u0001<,\u0005\u0019\te.\u001f*fMB\u0011\u0001p_\u0007\u0002s*\u0011!\u0010V\u0001\u0003S>L!\u0001`=\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0003I\f!c\u0019:fCR,7\u000b]1sW\u000e{g\u000e^3yiRq\u0011\u0011AA\u0004\u0003\u0017\ty!a\u0005\u0002\u001a\u0005%\u0002cA7\u0002\u0004%\u0019\u0011Q\u00018\u0003!)\u000bg/Y*qCJ\\7i\u001c8uKb$\bBBA\u0005\u001b\u0001\u0007\u0011)\u0001\u0004nCN$XM\u001d\u0005\u0007\u0003\u001bi\u0001\u0019A!\u0002\u000f\u0005\u0004\bOT1nK\"1\u0011\u0011C\u0007A\u0002\u0005\u000b\u0011b\u001d9be.Du.\\3\t\u000f\u0005UQ\u00021\u0001\u0002\u0018\u0005!!.\u0019:t!\rQ3'\u0011\u0005\b\u00037i\u0001\u0019AA\u000f\u00035\u0019\b/\u0019:l\u000b:4\u0018N]'baB1\u0011qDA\u0013!Bk!!!\t\u000b\u0007\u0005\rB+\u0001\u0003vi&d\u0017\u0002BA\u0014\u0003C\u00111!T1q\u0011\u001d\tY#\u0004a\u0001\u0003;\t1c\u001d9be.,\u00050Z2vi>\u0014XI\u001c<NCB\f!c\u0019:fCR,'\u000b\u0012#Ge>l\u0017I\u001d:bsR)A.!\r\u00026!9\u00111\u0007\bA\u0002\u0005\u0005\u0011a\u00016tG\"9\u0011q\u0007\bA\u0002\u0005e\u0012aA1seB\u0019!f\r\u001a\u0002#\r\u0014X-\u0019;f%\u0012#eI]8n\r&dW\rF\u0004m\u0003\u007f\t\t%!\u0012\t\u000f\u0005Mr\u00021\u0001\u0002\u0002!1\u00111I\bA\u0002\u0005\u000b\u0001BZ5mK:\u000bW.\u001a\u0005\b\u0003\u000fz\u0001\u0019AA%\u0003-\u0001\u0018M]1mY\u0016d\u0017n]7\u0011\u0007)\nY%C\u0002\u0002N-\u00121!\u00138u\u00035\u0019XM\u001d<f)>\u001cFO]3b[R!\u00111KA7)\u0011\t)&a\u0016\u0011\u0007)\u001at\u0006C\u0004\u0002ZA\u0001\r!a\u0017\u0002\u0013]\u0014\u0018\u000e^3Gk:\u001c\u0007c\u0002\u0016\u0002^\u0005\u0005\u0014qM\u0005\u0004\u0003?Z#!\u0003$v]\u000e$\u0018n\u001c82!\rA\u00181M\u0005\u0004\u0003KJ(\u0001D(viB,Ho\u0015;sK\u0006l\u0007c\u0001\u0016\u0002j%\u0019\u00111N\u0016\u0003\tUs\u0017\u000e\u001e\u0005\u0007\u0003_\u0002\u0002\u0019A!\u0002\u0015QD'/Z1e\u001d\u0006lW-\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001Q\u0001"
)
public class RRDD extends BaseRRDD {
   private JavaRDD asJavaRDD;
   private volatile boolean bitmap$0;

   public static JavaRDD createRDDFromFile(final JavaSparkContext jsc, final String fileName, final int parallelism) {
      return RRDD$.MODULE$.createRDDFromFile(jsc, fileName, parallelism);
   }

   public static JavaRDD createRDDFromArray(final JavaSparkContext jsc, final byte[][] arr) {
      return RRDD$.MODULE$.createRDDFromArray(jsc, arr);
   }

   public static JavaSparkContext createSparkContext(final String master, final String appName, final String sparkHome, final String[] jars, final Map sparkEnvirMap, final Map sparkExecutorEnvMap) {
      return RRDD$.MODULE$.createSparkContext(master, appName, sparkHome, jars, sparkEnvirMap, sparkExecutorEnvMap);
   }

   private JavaRDD asJavaRDD$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.asJavaRDD = JavaRDD$.MODULE$.fromRDD(this, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.asJavaRDD;
   }

   public JavaRDD asJavaRDD() {
      return !this.bitmap$0 ? this.asJavaRDD$lzycompute() : this.asJavaRDD;
   }

   public RRDD(final RDD parent, final byte[] func, final String deserializer, final String serializer, final byte[] packageNames, final Object[] broadcastVars, final ClassTag evidence$4) {
      super(parent, -1, func, deserializer, serializer, packageNames, (Broadcast[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(broadcastVars), new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Broadcast apply(final Object x) {
            return (Broadcast)x;
         }
      }, .MODULE$.apply(Broadcast.class)), evidence$4, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
   }
}
