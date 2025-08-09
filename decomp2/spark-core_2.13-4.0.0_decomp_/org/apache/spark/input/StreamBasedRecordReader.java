package org.apache.spark.input;

import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.CombineFileSplit;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%bAB\u000b\u0017\u0003\u0003Ab\u0004\u0003\u0005C\u0001\t\u0005\t\u0015!\u0003D\u0011!Q\u0005A!A!\u0002\u0013Y\u0005\u0002\u0003(\u0001\u0005\u0003\u0005\u000b\u0011B(\t\u000b]\u0003A\u0011\u0001-\t\u000fy\u0003\u0001\u0019!C\u0005?\"91\r\u0001a\u0001\n\u0013!\u0007B\u00026\u0001A\u0003&\u0001\rC\u0004l\u0001\u0001\u0007I\u0011\u00027\t\u000f=\u0004\u0001\u0019!C\u0005a\"1!\u000f\u0001Q!\n5Dqa\u001d\u0001A\u0002\u0013%A\u000fC\u0004v\u0001\u0001\u0007I\u0011\u0002<\t\ra\u0004\u0001\u0015)\u00037\u0011\u0015I\b\u0001\"\u0011{\u0011\u001d\t\t\u0001\u0001C!\u0003\u0007Aq!!\u0002\u0001\t\u0003\n9\u0001C\u0004\u0002\u0010\u0001!\t%!\u0005\t\u000f\u0005M\u0001\u0001\"\u0011\u0002\u0016!9\u0011q\u0003\u0001\u0005B\u0005e\u0001bBA\u000e\u0001\u0019\u0005\u0011Q\u0004\u0002\u0018'R\u0014X-Y7CCN,GMU3d_J$'+Z1eKJT!a\u0006\r\u0002\u000b%t\u0007/\u001e;\u000b\u0005eQ\u0012!B:qCJ\\'BA\u000e\u001d\u0003\u0019\t\u0007/Y2iK*\tQ$A\u0002pe\u001e,\"a\b\u001d\u0014\u0005\u0001\u0001\u0003\u0003B\u0011'QYj\u0011A\t\u0006\u0003G\u0011\n\u0011\"\\1qe\u0016$WoY3\u000b\u0005\u0015R\u0012A\u00025bI>|\u0007/\u0003\u0002(E\ta!+Z2pe\u0012\u0014V-\u00193feB\u0011\u0011f\r\b\u0003UE\u0002\"aK\u0018\u000e\u00031R!!\f\u0018\u0002\rq\u0012xn\u001c;?\u0007\u0001Q\u0011\u0001M\u0001\u0006g\u000e\fG.Y\u0005\u0003e=\na\u0001\u0015:fI\u00164\u0017B\u0001\u001b6\u0005\u0019\u0019FO]5oO*\u0011!g\f\t\u0003oab\u0001\u0001B\u0003:\u0001\t\u0007!HA\u0001U#\tYt\b\u0005\u0002={5\tq&\u0003\u0002?_\t9aj\u001c;iS:<\u0007C\u0001\u001fA\u0013\t\tuFA\u0002B]f\fQa\u001d9mSR\u0004\"\u0001\u0012%\u000e\u0003\u0015S!a\u0006$\u000b\u0005\u001d\u0013\u0013a\u00017jE&\u0011\u0011*\u0012\u0002\u0011\u0007>l'-\u001b8f\r&dWm\u00159mSR\fqaY8oi\u0016DH\u000f\u0005\u0002\"\u0019&\u0011QJ\t\u0002\u0013)\u0006\u001c8.\u0011;uK6\u0004HoQ8oi\u0016DH/A\u0003j]\u0012,\u0007\u0010\u0005\u0002Q+6\t\u0011K\u0003\u0002S'\u0006!A.\u00198h\u0015\u0005!\u0016\u0001\u00026bm\u0006L!AV)\u0003\u000f%sG/Z4fe\u00061A(\u001b8jiz\"B!W.];B\u0019!\f\u0001\u001c\u000e\u0003YAQA\u0011\u0003A\u0002\rCQA\u0013\u0003A\u0002-CQA\u0014\u0003A\u0002=\u000b\u0011\u0002\u001d:pG\u0016\u001c8/\u001a3\u0016\u0003\u0001\u0004\"\u0001P1\n\u0005\t|#a\u0002\"p_2,\u0017M\\\u0001\u000eaJ|7-Z:tK\u0012|F%Z9\u0015\u0005\u0015D\u0007C\u0001\u001fg\u0013\t9wF\u0001\u0003V]&$\bbB5\u0007\u0003\u0003\u0005\r\u0001Y\u0001\u0004q\u0012\n\u0014A\u00039s_\u000e,7o]3eA\u0005\u00191.Z=\u0016\u00035\u0004\"\u0001\u00158\n\u0005Q\n\u0016aB6fs~#S-\u001d\u000b\u0003KFDq![\u0005\u0002\u0002\u0003\u0007Q.\u0001\u0003lKf\u0004\u0013!\u0002<bYV,W#\u0001\u001c\u0002\u0013Y\fG.^3`I\u0015\fHCA3x\u0011\u001dIG\"!AA\u0002Y\naA^1mk\u0016\u0004\u0013AC5oSRL\u0017\r\\5{KR\u0019Qm_@\t\u000b\ts\u0001\u0019\u0001?\u0011\u0005\u0005j\u0018B\u0001@#\u0005)Ie\u000e];u'Bd\u0017\u000e\u001e\u0005\u0006\u0015:\u0001\raS\u0001\u0006G2|7/\u001a\u000b\u0002K\u0006Yq-\u001a;Qe><'/Z:t)\t\tI\u0001E\u0002=\u0003\u0017I1!!\u00040\u0005\u00151En\\1u\u000359W\r^\"veJ,g\u000e^&fsR\t\u0001&A\bhKR\u001cUO\u001d:f]R4\u0016\r\\;f)\u00051\u0014\u0001\u00048fqR\\U-\u001f,bYV,G#\u00011\u0002\u0017A\f'o]3TiJ,\u0017-\u001c\u000b\u0004m\u0005}\u0001bBA\u0011)\u0001\u0007\u00111E\u0001\tS:\u001cFO]3b[B\u0019!,!\n\n\u0007\u0005\u001dbC\u0001\nQ_J$\u0018M\u00197f\t\u0006$\u0018m\u0015;sK\u0006l\u0007"
)
public abstract class StreamBasedRecordReader extends RecordReader {
   private final CombineFileSplit split;
   private final TaskAttemptContext context;
   private final Integer index;
   private boolean processed;
   private String key;
   private Object value;

   private boolean processed() {
      return this.processed;
   }

   private void processed_$eq(final boolean x$1) {
      this.processed = x$1;
   }

   private String key() {
      return this.key;
   }

   private void key_$eq(final String x$1) {
      this.key = x$1;
   }

   private Object value() {
      return this.value;
   }

   private void value_$eq(final Object x$1) {
      this.value = x$1;
   }

   public void initialize(final InputSplit split, final TaskAttemptContext context) {
   }

   public void close() {
   }

   public float getProgress() {
      return this.processed() ? 1.0F : 0.0F;
   }

   public String getCurrentKey() {
      return this.key();
   }

   public Object getCurrentValue() {
      return this.value();
   }

   public boolean nextKeyValue() {
      if (!this.processed()) {
         PortableDataStream fileIn = new PortableDataStream(this.split, this.context, this.index);
         this.value_$eq(this.parseStream(fileIn));
         this.key_$eq(fileIn.getPath());
         this.processed_$eq(true);
         return true;
      } else {
         return false;
      }
   }

   public abstract Object parseStream(final PortableDataStream inStream);

   public StreamBasedRecordReader(final CombineFileSplit split, final TaskAttemptContext context, final Integer index) {
      this.split = split;
      this.context = context;
      this.index = index;
      this.processed = false;
      this.key = "";
      this.value = null;
   }
}
