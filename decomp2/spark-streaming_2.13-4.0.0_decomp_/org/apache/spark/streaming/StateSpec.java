package org.apache.spark.streaming;

import java.io.Serializable;
import org.apache.spark.Partitioner;
import org.apache.spark.annotation.Experimental;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.function.Function3;
import org.apache.spark.api.java.function.Function4;
import org.apache.spark.rdd.RDD;
import scala.reflect.ScalaSignature;

@Experimental
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005g!B\b\u0011\u0003CI\u0002\"B\u0017\u0001\t\u0003q\u0003\"B#\u0001\r\u00031\u0005\"B#\u0001\r\u0003\u0011\u0006\"B/\u0001\r\u0003q\u0006\"B2\u0001\r\u0003!\u0007\"\u00026\u0001\r\u0003Yw!B>\u0011\u0011\u0003ah!B\b\u0011\u0011\u0003i\bBB\u0017\t\t\u0003\tI\u0001C\u0004\u0002\f!!\t!!\u0004\t\u000f\u0005-\u0001\u0002\"\u0001\u0002B!9\u00111\u0002\u0005\u0005\u0002\u0005\r\u0004bBA\u0006\u0011\u0011\u0005\u0011Q\u0012\u0005\n\u0003[C\u0011\u0011!C\u0005\u0003_\u0013\u0011b\u0015;bi\u0016\u001c\u0006/Z2\u000b\u0005E\u0011\u0012!C:ue\u0016\fW.\u001b8h\u0015\t\u0019B#A\u0003ta\u0006\u00148N\u0003\u0002\u0016-\u00051\u0011\r]1dQ\u0016T\u0011aF\u0001\u0004_J<7\u0001A\u000b\u00065Mj\u0004iQ\n\u0004\u0001m\t\u0003C\u0001\u000f \u001b\u0005i\"\"\u0001\u0010\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0001j\"AB!osJ+g\r\u0005\u0002#U9\u00111\u0005\u000b\b\u0003I\u001dj\u0011!\n\u0006\u0003Ma\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0010\n\u0005%j\u0012a\u00029bG.\fw-Z\u0005\u0003W1\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!!K\u000f\u0002\rqJg.\u001b;?)\u0005y\u0003C\u0002\u0019\u0001cqz$)D\u0001\u0011!\t\u00114\u0007\u0004\u0001\u0005\u000bQ\u0002!\u0019A\u001b\u0003\u000f-+\u0017\u0010V=qKF\u0011a'\u000f\t\u00039]J!\u0001O\u000f\u0003\u000f9{G\u000f[5oOB\u0011ADO\u0005\u0003wu\u00111!\u00118z!\t\u0011T\bB\u0003?\u0001\t\u0007QGA\u0005WC2,X\rV=qKB\u0011!\u0007\u0011\u0003\u0006\u0003\u0002\u0011\r!\u000e\u0002\n'R\fG/\u001a+za\u0016\u0004\"AM\"\u0005\u000b\u0011\u0003!\u0019A\u001b\u0003\u00155\u000b\u0007\u000f]3e)f\u0004X-\u0001\u0007j]&$\u0018.\u00197Ti\u0006$X\r\u0006\u0002H\u00116\t\u0001\u0001C\u0003J\u0005\u0001\u0007!*A\u0002sI\u0012\u00042aS'P\u001b\u0005a%BA%\u0013\u0013\tqEJA\u0002S\t\u0012\u0003B\u0001\b)2\u007f%\u0011\u0011+\b\u0002\u0007)V\u0004H.\u001a\u001a\u0015\u0005\u001d\u001b\u0006\"\u0002+\u0004\u0001\u0004)\u0016a\u00036bm\u0006\u0004\u0016-\u001b:S\t\u0012\u0003BAV.2\u007f5\tqK\u0003\u0002Y3\u0006!!.\u0019<b\u0015\tQ&#A\u0002ba&L!\u0001X,\u0003\u0017)\u000bg/\u0019)bSJ\u0014F\tR\u0001\u000e]Vl\u0007+\u0019:uSRLwN\\:\u0015\u0005\u001d{\u0006\"B/\u0005\u0001\u0004\u0001\u0007C\u0001\u000fb\u0013\t\u0011WDA\u0002J]R\f1\u0002]1si&$\u0018n\u001c8feR\u0011q)\u001a\u0005\u0006G\u0016\u0001\rA\u001a\t\u0003O\"l\u0011AE\u0005\u0003SJ\u00111\u0002U1si&$\u0018n\u001c8fe\u00069A/[7f_V$HCA$m\u0011\u0015ig\u00011\u0001o\u00031IG\r\\3EkJ\fG/[8o!\t\u0001t.\u0003\u0002q!\tAA)\u001e:bi&|g.\u000b\u0002\u0001e&\u00111\u000f\u0005\u0002\u000e'R\fG/Z*qK\u000eLU\u000e\u001d7)\u0005\u0001)\bC\u0001<z\u001b\u00059(B\u0001=\u0013\u0003)\tgN\\8uCRLwN\\\u0005\u0003u^\u0014A\"\u0012=qKJLW.\u001a8uC2\f\u0011b\u0015;bi\u0016\u001c\u0006/Z2\u0011\u0005AB1c\u0001\u0005\u001c}B\u0019q0a\u0002\u000e\u0005\u0005\u0005!\u0002BA\u0002\u0003\u000b\t!![8\u000b\u0003aK1aKA\u0001)\u0005a\u0018\u0001\u00034v]\u000e$\u0018n\u001c8\u0016\u0015\u0005=\u0011QCA\r\u0003;\t\t\u0003\u0006\u0003\u0002\u0012\u0005\r\u0002C\u0003\u0019\u0001\u0003'\t9\"a\u0007\u0002 A\u0019!'!\u0006\u0005\u000bQR!\u0019A\u001b\u0011\u0007I\nI\u0002B\u0003?\u0015\t\u0007Q\u0007E\u00023\u0003;!Q!\u0011\u0006C\u0002U\u00022AMA\u0011\t\u0015!%B1\u00016\u0011\u001d\t)C\u0003a\u0001\u0003O\tq\"\\1qa&twMR;oGRLwN\u001c\t\u000e9\u0005%\u0012QFA\n\u0003g\tI$a\u0010\n\u0007\u0005-RDA\u0005Gk:\u001cG/[8oiA\u0019\u0001'a\f\n\u0007\u0005E\u0002C\u0001\u0003US6,\u0007#\u0002\u000f\u00026\u0005]\u0011bAA\u001c;\t1q\n\u001d;j_:\u0004R\u0001MA\u001e\u00037I1!!\u0010\u0011\u0005\u0015\u0019F/\u0019;f!\u0015a\u0012QGA\u0010+)\t\u0019%!\u0013\u0002N\u0005E\u0013Q\u000b\u000b\u0005\u0003\u000b\n9\u0006\u0005\u00061\u0001\u0005\u001d\u00131JA(\u0003'\u00022AMA%\t\u0015!4B1\u00016!\r\u0011\u0014Q\n\u0003\u0006}-\u0011\r!\u000e\t\u0004e\u0005EC!B!\f\u0005\u0004)\u0004c\u0001\u001a\u0002V\u0011)Ai\u0003b\u0001k!9\u0011QE\u0006A\u0002\u0005e\u0003c\u0003\u000f\u0002\\\u0005\u001d\u0013qLA1\u0003'J1!!\u0018\u001e\u0005%1UO\\2uS>t7\u0007E\u0003\u001d\u0003k\tY\u0005E\u00031\u0003w\ty%\u0006\u0006\u0002f\u0005-\u0014qNA:\u0003o\"B!a\u001a\u0002zAQ\u0001\u0007AA5\u0003[\n\t(!\u001e\u0011\u0007I\nY\u0007B\u00035\u0019\t\u0007Q\u0007E\u00023\u0003_\"QA\u0010\u0007C\u0002U\u00022AMA:\t\u0015\tEB1\u00016!\r\u0011\u0014q\u000f\u0003\u0006\t2\u0011\r!\u000e\u0005\b\u0003Ka\u0001\u0019AA>!9\ti(!!\u0002.\u0005%\u00141QAE\u0003\u0017k!!a \u000b\u0007\u0005-q+\u0003\u0003\u0002,\u0005}\u0004#\u0002,\u0002\u0006\u00065\u0014bAAD/\nAq\n\u001d;j_:\fG\u000eE\u00031\u0003w\t\t\bE\u0003W\u0003\u000b\u000b)(\u0006\u0006\u0002\u0010\u0006U\u0015\u0011TAO\u0003C#B!!%\u0002$BQ\u0001\u0007AAJ\u0003/\u000bY*a(\u0011\u0007I\n)\nB\u00035\u001b\t\u0007Q\u0007E\u00023\u00033#QAP\u0007C\u0002U\u00022AMAO\t\u0015\tUB1\u00016!\r\u0011\u0014\u0011\u0015\u0003\u0006\t6\u0011\r!\u000e\u0005\b\u0003Ki\u0001\u0019AAS!1\ti(a*\u0002\u0014\u0006%\u00161VAP\u0013\u0011\ti&a \u0011\u000bY\u000b))a&\u0011\u000bA\nY$a'\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005E\u0006\u0003BAZ\u0003sk!!!.\u000b\t\u0005]\u0016QA\u0001\u0005Y\u0006tw-\u0003\u0003\u0002<\u0006U&AB(cU\u0016\u001cG\u000f\u000b\u0002\tk\"\u0012q!\u001e"
)
public abstract class StateSpec implements Serializable {
   public static StateSpec function(final Function3 mappingFunction) {
      return StateSpec$.MODULE$.function(mappingFunction);
   }

   public static StateSpec function(final Function4 mappingFunction) {
      return StateSpec$.MODULE$.function(mappingFunction);
   }

   public static StateSpec function(final scala.Function3 mappingFunction) {
      return StateSpec$.MODULE$.function(mappingFunction);
   }

   public static StateSpec function(final scala.Function4 mappingFunction) {
      return StateSpec$.MODULE$.function(mappingFunction);
   }

   public abstract StateSpec initialState(final RDD rdd);

   public abstract StateSpec initialState(final JavaPairRDD javaPairRDD);

   public abstract StateSpec numPartitions(final int numPartitions);

   public abstract StateSpec partitioner(final Partitioner partitioner);

   public abstract StateSpec timeout(final Duration idleDuration);
}
