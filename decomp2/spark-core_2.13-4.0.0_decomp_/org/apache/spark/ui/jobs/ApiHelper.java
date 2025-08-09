package org.apache.spark.ui.jobs;

import org.apache.spark.status.AppStatusStore;
import org.apache.spark.status.api.v1.JobData;
import org.apache.spark.status.api.v1.ShuffleReadMetrics;
import org.apache.spark.status.api.v1.StageData;
import scala.Option;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mwA\u0002\"D\u0011\u00039UJ\u0002\u0004P\u0007\"\u0005q\t\u0015\u0005\u0006/\u0006!\t!\u0017\u0005\b5\u0006\u0011\r\u0011\"\u0001\\\u0011\u0019!\u0017\u0001)A\u00059\"9Q-\u0001b\u0001\n\u0003Y\u0006B\u00024\u0002A\u0003%A\fC\u0004h\u0003\t\u0007I\u0011A.\t\r!\f\u0001\u0015!\u0003]\u0011\u001dI\u0017A1A\u0005\u0002mCaA[\u0001!\u0002\u0013a\u0006bB6\u0002\u0005\u0004%\ta\u0017\u0005\u0007Y\u0006\u0001\u000b\u0011\u0002/\t\u000f5\f!\u0019!C\u00017\"1a.\u0001Q\u0001\nqCqa\\\u0001C\u0002\u0013\u00051\f\u0003\u0004q\u0003\u0001\u0006I\u0001\u0018\u0005\bc\u0006\u0011\r\u0011\"\u0001\\\u0011\u0019\u0011\u0018\u0001)A\u00059\"91/\u0001b\u0001\n\u0003Y\u0006B\u0002;\u0002A\u0003%A\fC\u0004v\u0003\t\u0007I\u0011A.\t\rY\f\u0001\u0015!\u0003]\u0011\u001d9\u0018A1A\u0005\u0002mCa\u0001_\u0001!\u0002\u0013a\u0006bB=\u0002\u0005\u0004%\ta\u0017\u0005\u0007u\u0006\u0001\u000b\u0011\u0002/\t\u000fm\f!\u0019!C\u00017\"1A0\u0001Q\u0001\nqCq!`\u0001C\u0002\u0013\u00051\f\u0003\u0004\u007f\u0003\u0001\u0006I\u0001\u0018\u0005\b\u007f\u0006\u0011\r\u0011\"\u0001\\\u0011\u001d\t\t!\u0001Q\u0001\nqC\u0001\"a\u0001\u0002\u0005\u0004%\ta\u0017\u0005\b\u0003\u000b\t\u0001\u0015!\u0003]\u0011!\t9!\u0001b\u0001\n\u0003Y\u0006bBA\u0005\u0003\u0001\u0006I\u0001\u0018\u0005\t\u0003\u0017\t!\u0019!C\u00017\"9\u0011QB\u0001!\u0002\u0013a\u0006\u0002CA\b\u0003\t\u0007I\u0011A.\t\u000f\u0005E\u0011\u0001)A\u00059\"A\u00111C\u0001C\u0002\u0013\u00051\fC\u0004\u0002\u0016\u0005\u0001\u000b\u0011\u0002/\t\u0011\u0005]\u0011A1A\u0005\u0002mCq!!\u0007\u0002A\u0003%A\f\u0003\u0005\u0002\u001c\u0005\u0011\r\u0011\"\u0001\\\u0011\u001d\ti\"\u0001Q\u0001\nqC\u0001\"a\b\u0002\u0005\u0004%\ta\u0017\u0005\b\u0003C\t\u0001\u0015!\u0003]\u0011!\t\u0019#\u0001b\u0001\n\u0003Y\u0006bBA\u0013\u0003\u0001\u0006I\u0001\u0018\u0005\t\u0003O\t!\u0019!C\u00017\"9\u0011\u0011F\u0001!\u0002\u0013a\u0006\u0002CA\u0016\u0003\t\u0007I\u0011A.\t\u000f\u00055\u0012\u0001)A\u00059\"Q\u0011qF\u0001C\u0002\u0013\u0005Q)!\r\t\u0011\u0005\r\u0013\u0001)A\u0005\u0003gAq!!\u0012\u0002\t\u0003\t9\u0005C\u0004\u0002h\u0005!\t!!\u001b\t\u000f\u00055\u0014\u0001\"\u0001\u0002p!9\u00111O\u0001\u0005\u0002\u0005U\u0004bBA=\u0003\u0011\u0005\u00111\u0010\u0005\b\u0003\u007f\nA\u0011AAA\u0011\u001d\t))\u0001C\u0001\u0003\u000fCq!!'\u0002\t\u0003\tY\nC\u0004\u0002<\u0006!\t!!0\u0002\u0013\u0005\u0003\u0018\u000eS3ma\u0016\u0014(B\u0001#F\u0003\u0011QwNY:\u000b\u0005\u0019;\u0015AA;j\u0015\tA\u0015*A\u0003ta\u0006\u00148N\u0003\u0002K\u0017\u00061\u0011\r]1dQ\u0016T\u0011\u0001T\u0001\u0004_J<\u0007C\u0001(\u0002\u001b\u0005\u0019%!C!qS\"+G\u000e]3s'\t\t\u0011\u000b\u0005\u0002S+6\t1KC\u0001U\u0003\u0015\u00198-\u00197b\u0013\t16K\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tQ*A\u0005I\u000b\u0006#UIU0J\tV\tA\f\u0005\u0002^E6\taL\u0003\u0002`A\u0006!A.\u00198h\u0015\u0005\t\u0017\u0001\u00026bm\u0006L!a\u00190\u0003\rM#(/\u001b8h\u0003)AU)\u0011#F%~KE\tI\u0001\u0012\u0011\u0016\u000bE)\u0012*`)\u0006\u001b6jX%O\t\u0016C\u0016A\u0005%F\u0003\u0012+%k\u0018+B'.{\u0016J\u0014#F1\u0002\na\u0002S#B\t\u0016\u0013v,\u0011+U\u000b6\u0003F+A\bI\u000b\u0006#UIU0B)R+U\n\u0015+!\u00035AU)\u0011#F%~\u001bF+\u0011+V'\u0006q\u0001*R!E\u000bJ{6\u000bV!U+N\u0003\u0013a\u0004%F\u0003\u0012+%k\u0018'P\u0007\u0006c\u0015\nV-\u0002!!+\u0015\tR#S?2{5)\u0011'J)f\u0003\u0013a\u0004%F\u0003\u0012+%kX#Y\u000b\u000e+Fk\u0014*\u0002!!+\u0015\tR#S?\u0016CViQ+U\u001fJ\u0003\u0013a\u0003%F\u0003\u0012+%k\u0018%P'R\u000bA\u0002S#B\t\u0016\u0013v\fS(T)\u0002\n!\u0003S#B\t\u0016\u0013v\fT!V\u001d\u000eCu\fV%N\u000b\u0006\u0019\u0002*R!E\u000bJ{F*Q+O\u0007\"{F+S'FA\u0005y\u0001*R!E\u000bJ{F)\u0016*B)&{e*\u0001\tI\u000b\u0006#UIU0E+J\u000bE+S(OA\u00051\u0002*R!E\u000bJ{6k\u0011%F\tVcUIU0E\u000b2\u000b\u0015,A\fI\u000b\u0006#UIU0T\u0007\"+E)\u0016'F%~#U\tT!ZA\u0005\t\u0002*R!E\u000bJ{F)R*F%~#\u0016*T#\u0002%!+\u0015\tR#S?\u0012+5+\u0012*`)&kU\tI\u0001\u000f\u0011\u0016\u000bE)\u0012*`\u000f\u000e{F+S'F\u0003=AU)\u0011#F%~;5i\u0018+J\u001b\u0016\u0003\u0013a\u0004%F\u0003\u0012+%kX*F%~#\u0016*T#\u0002!!+\u0015\tR#S?N+%k\u0018+J\u001b\u0016\u0003\u0013A\u0007%F\u0003\u0012+%kX$F)RKejR0S\u000bN+F\nV0U\u00136+\u0015a\u0007%F\u0003\u0012+%kX$F)RKejR0S\u000bN+F\nV0U\u00136+\u0005%A\bI\u000b\u0006#UIU0Q\u000b\u0006[u,T#N\u0003AAU)\u0011#F%~\u0003V)Q&`\u001b\u0016k\u0005%A\nI\u000b\u0006#UIU0B\u0007\u000e+V*\u0016'B)>\u00136+\u0001\u000bI\u000b\u0006#UIU0B\u0007\u000e+V*\u0016'B)>\u00136\u000bI\u0001\u0012\u0011\u0016\u000bE)\u0012*`\u0013:\u0003V\u000bV0T\u0013j+\u0015A\u0005%F\u0003\u0012+%kX%O!V#vlU%[\u000b\u0002\n!\u0003S#B\t\u0016\u0013vlT+U!V#vlU%[\u000b\u0006\u0019\u0002*R!E\u000bJ{v*\u0016+Q+R{6+\u0013.FA\u0005\u0019\u0003*R!E\u000bJ{6\u000bS+G\r2+uLU#B\t~3U\tV\"I?^\u000b\u0015\nV0U\u00136+\u0015\u0001\n%F\u0003\u0012+%kX*I+\u001a3E*R0S\u000b\u0006#uLR#U\u0007\"{v+Q%U?RKU*\u0012\u0011\u00025!+\u0015\tR#S?NCUK\u0012$M\u000b~#v\nV!M?J+\u0015\tR*\u00027!+\u0015\tR#S?NCUK\u0012$M\u000b~#v\nV!M?J+\u0015\tR*!\u0003mAU)\u0011#F%~\u001b\u0006*\u0016$G\u0019\u0016{&+R'P)\u0016{&+R!E'\u0006a\u0002*R!E\u000bJ{6\u000bS+G\r2+uLU#N\u001fR+uLU#B\tN\u0003\u0013!\u0007%F\u0003\u0012+%kX*I+\u001a3E*R0X%&#Vi\u0018+J\u001b\u0016\u000b!\u0004S#B\t\u0016\u0013vl\u0015%V\r\u001acUiX,S\u0013R+u\fV%N\u000b\u0002\n\u0011\u0004S#B\t\u0016\u0013vl\u0015%V\r\u001acUiX,S\u0013R+ulU%[\u000b\u0006Q\u0002*R!E\u000bJ{6\u000bS+G\r2+ul\u0016*J)\u0016{6+\u0013.FA\u0005\u0001\u0002*R!E\u000bJ{V*R'`'BKE\nT\u0001\u0012\u0011\u0016\u000bE)\u0012*`\u001b\u0016kul\u0015)J\u00192\u0003\u0013!\u0005%F\u0003\u0012+%k\u0018#J'.{6\u000bU%M\u0019\u0006\u0011\u0002*R!E\u000bJ{F)S*L?N\u0003\u0016\n\u0014'!\u00031AU)\u0011#F%~+%KU(S\u00035AU)\u0011#F%~+%KU(SA\u0005y1i\u0014'V\u001b:{FkT0J\u001d\u0012+\u0005,\u0006\u0002\u00024A1\u0011QGA 9rk!!a\u000e\u000b\t\u0005e\u00121H\u0001\nS6lW\u000f^1cY\u0016T1!!\u0010T\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003\u0003\n9DA\u0002NCB\f\u0001cQ(M+6su\fV(`\u0013:#U\t\u0017\u0011\u0002\u001f!\f7/Q2dk6,H.\u0019;peN$B!!\u0013\u0002PA\u0019!+a\u0013\n\u0007\u000553KA\u0004C_>dW-\u00198\t\u000f\u0005E\u0013\b1\u0001\u0002T\u0005I1\u000f^1hK\u0012\u000bG/\u0019\t\u0005\u0003+\n\u0019'\u0004\u0002\u0002X)!\u0011\u0011LA.\u0003\t1\u0018G\u0003\u0003\u0002^\u0005}\u0013aA1qS*\u0019\u0011\u0011M$\u0002\rM$\u0018\r^;t\u0013\u0011\t)'a\u0016\u0003\u0013M#\u0018mZ3ECR\f\u0017\u0001\u00035bg&s\u0007/\u001e;\u0015\t\u0005%\u00131\u000e\u0005\b\u0003#R\u0004\u0019AA*\u0003%A\u0017m](viB,H\u000f\u0006\u0003\u0002J\u0005E\u0004bBA)w\u0001\u0007\u00111K\u0001\u000fQ\u0006\u001c8\u000b[;gM2,'+Z1e)\u0011\tI%a\u001e\t\u000f\u0005EC\b1\u0001\u0002T\u0005y\u0001.Y:TQV4g\r\\3Xe&$X\r\u0006\u0003\u0002J\u0005u\u0004bBA){\u0001\u0007\u00111K\u0001\u0010Q\u0006\u001c()\u001f;fgN\u0003\u0018\u000e\u001c7fIR!\u0011\u0011JAB\u0011\u001d\t\tF\u0010a\u0001\u0003'\na\u0002^8uC2\u0014\u0015\u0010^3t%\u0016\fG\r\u0006\u0003\u0002\n\u0006=\u0005c\u0001*\u0002\f&\u0019\u0011QR*\u0003\t1{gn\u001a\u0005\b\u0003#{\u0004\u0019AAJ\u0003\u001diW\r\u001e:jGN\u0004B!!\u0016\u0002\u0016&!\u0011qSA,\u0005I\u0019\u0006.\u001e4gY\u0016\u0014V-\u00193NKR\u0014\u0018nY:\u0002\u0013%tG-\u001a=OC6,G\u0003BAO\u0003o\u0003RAUAP\u0003GK1!!)T\u0005\u0019y\u0005\u000f^5p]B!\u0011QUAZ\u001d\u0011\t9+a,\u0011\u0007\u0005%6+\u0004\u0002\u0002,*\u0019\u0011Q\u0016-\u0002\rq\u0012xn\u001c;?\u0013\r\t\tlU\u0001\u0007!J,G-\u001a4\n\u0007\r\f)LC\u0002\u00022NCq!!/A\u0001\u0004\t\u0019+\u0001\u0006t_J$8i\u001c7v[:\f1\u0004\\1tiN#\u0018mZ3OC6,\u0017I\u001c3EKN\u001c'/\u001b9uS>tGCBA`\u0003\u000b\f\t\u000eE\u0004S\u0003\u0003\f\u0019+a)\n\u0007\u0005\r7K\u0001\u0004UkBdWM\r\u0005\b\u0003\u000f\f\u0005\u0019AAe\u0003\u0015\u0019Ho\u001c:f!\u0011\tY-!4\u000e\u0005\u0005}\u0013\u0002BAh\u0003?\u0012a\"\u00119q'R\fG/^:Ti>\u0014X\rC\u0004\u0002T\u0006\u0003\r!!6\u0002\u0007)|'\r\u0005\u0003\u0002V\u0005]\u0017\u0002BAm\u0003/\u0012qAS8c\t\u0006$\u0018\r"
)
public final class ApiHelper {
   public static Tuple2 lastStageNameAndDescription(final AppStatusStore store, final JobData job) {
      return ApiHelper$.MODULE$.lastStageNameAndDescription(store, job);
   }

   public static Option indexName(final String sortColumn) {
      return ApiHelper$.MODULE$.indexName(sortColumn);
   }

   public static long totalBytesRead(final ShuffleReadMetrics metrics) {
      return ApiHelper$.MODULE$.totalBytesRead(metrics);
   }

   public static boolean hasBytesSpilled(final StageData stageData) {
      return ApiHelper$.MODULE$.hasBytesSpilled(stageData);
   }

   public static boolean hasShuffleWrite(final StageData stageData) {
      return ApiHelper$.MODULE$.hasShuffleWrite(stageData);
   }

   public static boolean hasShuffleRead(final StageData stageData) {
      return ApiHelper$.MODULE$.hasShuffleRead(stageData);
   }

   public static boolean hasOutput(final StageData stageData) {
      return ApiHelper$.MODULE$.hasOutput(stageData);
   }

   public static boolean hasInput(final StageData stageData) {
      return ApiHelper$.MODULE$.hasInput(stageData);
   }

   public static boolean hasAccumulators(final StageData stageData) {
      return ApiHelper$.MODULE$.hasAccumulators(stageData);
   }

   public static String HEADER_ERROR() {
      return ApiHelper$.MODULE$.HEADER_ERROR();
   }

   public static String HEADER_DISK_SPILL() {
      return ApiHelper$.MODULE$.HEADER_DISK_SPILL();
   }

   public static String HEADER_MEM_SPILL() {
      return ApiHelper$.MODULE$.HEADER_MEM_SPILL();
   }

   public static String HEADER_SHUFFLE_WRITE_SIZE() {
      return ApiHelper$.MODULE$.HEADER_SHUFFLE_WRITE_SIZE();
   }

   public static String HEADER_SHUFFLE_WRITE_TIME() {
      return ApiHelper$.MODULE$.HEADER_SHUFFLE_WRITE_TIME();
   }

   public static String HEADER_SHUFFLE_REMOTE_READS() {
      return ApiHelper$.MODULE$.HEADER_SHUFFLE_REMOTE_READS();
   }

   public static String HEADER_SHUFFLE_TOTAL_READS() {
      return ApiHelper$.MODULE$.HEADER_SHUFFLE_TOTAL_READS();
   }

   public static String HEADER_SHUFFLE_READ_FETCH_WAIT_TIME() {
      return ApiHelper$.MODULE$.HEADER_SHUFFLE_READ_FETCH_WAIT_TIME();
   }

   public static String HEADER_OUTPUT_SIZE() {
      return ApiHelper$.MODULE$.HEADER_OUTPUT_SIZE();
   }

   public static String HEADER_INPUT_SIZE() {
      return ApiHelper$.MODULE$.HEADER_INPUT_SIZE();
   }

   public static String HEADER_ACCUMULATORS() {
      return ApiHelper$.MODULE$.HEADER_ACCUMULATORS();
   }

   public static String HEADER_PEAK_MEM() {
      return ApiHelper$.MODULE$.HEADER_PEAK_MEM();
   }

   public static String HEADER_GETTING_RESULT_TIME() {
      return ApiHelper$.MODULE$.HEADER_GETTING_RESULT_TIME();
   }

   public static String HEADER_SER_TIME() {
      return ApiHelper$.MODULE$.HEADER_SER_TIME();
   }

   public static String HEADER_GC_TIME() {
      return ApiHelper$.MODULE$.HEADER_GC_TIME();
   }

   public static String HEADER_DESER_TIME() {
      return ApiHelper$.MODULE$.HEADER_DESER_TIME();
   }

   public static String HEADER_SCHEDULER_DELAY() {
      return ApiHelper$.MODULE$.HEADER_SCHEDULER_DELAY();
   }

   public static String HEADER_DURATION() {
      return ApiHelper$.MODULE$.HEADER_DURATION();
   }

   public static String HEADER_LAUNCH_TIME() {
      return ApiHelper$.MODULE$.HEADER_LAUNCH_TIME();
   }

   public static String HEADER_HOST() {
      return ApiHelper$.MODULE$.HEADER_HOST();
   }

   public static String HEADER_EXECUTOR() {
      return ApiHelper$.MODULE$.HEADER_EXECUTOR();
   }

   public static String HEADER_LOCALITY() {
      return ApiHelper$.MODULE$.HEADER_LOCALITY();
   }

   public static String HEADER_STATUS() {
      return ApiHelper$.MODULE$.HEADER_STATUS();
   }

   public static String HEADER_ATTEMPT() {
      return ApiHelper$.MODULE$.HEADER_ATTEMPT();
   }

   public static String HEADER_TASK_INDEX() {
      return ApiHelper$.MODULE$.HEADER_TASK_INDEX();
   }

   public static String HEADER_ID() {
      return ApiHelper$.MODULE$.HEADER_ID();
   }
}
