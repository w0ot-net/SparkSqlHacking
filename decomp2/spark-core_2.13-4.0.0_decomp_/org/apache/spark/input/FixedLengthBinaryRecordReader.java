package org.apache.spark.input;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%b!B\u000f\u001f\u0001\u00012\u0003\"\u0002\u001d\u0001\t\u0003Q\u0004bB\u001f\u0001\u0001\u0004%IA\u0010\u0005\b\u000b\u0002\u0001\r\u0011\"\u0003G\u0011\u0019a\u0005\u0001)Q\u0005\u007f!9Q\n\u0001a\u0001\n\u0013q\u0004b\u0002(\u0001\u0001\u0004%Ia\u0014\u0005\u0007#\u0002\u0001\u000b\u0015B \t\u000fI\u0003\u0001\u0019!C\u0005}!91\u000b\u0001a\u0001\n\u0013!\u0006B\u0002,\u0001A\u0003&q\bC\u0004X\u0001\u0001\u0007I\u0011\u0002-\t\u000fq\u0003\u0001\u0019!C\u0005;\"1q\f\u0001Q!\neCq\u0001\u0019\u0001A\u0002\u0013%\u0011\rC\u0004i\u0001\u0001\u0007I\u0011B5\t\r-\u0004\u0001\u0015)\u0003c\u0011\u001da\u0007\u00011A\u0005\n5DqA\u001c\u0001A\u0002\u0013%q\u000e\u0003\u0004r\u0001\u0001\u0006Ka\f\u0005\be\u0002\u0001\r\u0011\"\u0003t\u0011\u001d!\b\u00011A\u0005\nUDaa\u001e\u0001!B\u0013)\u0004\"\u0002=\u0001\t\u0003J\b\"\u0002>\u0001\t\u0003Z\b\"\u0002?\u0001\t\u0003j\b\"\u0002@\u0001\t\u0003z\bbBA\u0004\u0001\u0011\u0005\u0013\u0011\u0002\u0005\b\u0003?\u0001A\u0011IA\u0011\u0005u1\u0015\u000e_3e\u0019\u0016tw\r\u001e5CS:\f'/\u001f*fG>\u0014HMU3bI\u0016\u0014(BA\u0010!\u0003\u0015Ig\u000e];u\u0015\t\t#%A\u0003ta\u0006\u00148N\u0003\u0002$I\u00051\u0011\r]1dQ\u0016T\u0011!J\u0001\u0004_J<7C\u0001\u0001(!\u0011ASfL\u001b\u000e\u0003%R!AK\u0016\u0002\u00135\f\u0007O]3ek\u000e,'B\u0001\u0017#\u0003\u0019A\u0017\rZ8pa&\u0011a&\u000b\u0002\r%\u0016\u001cwN\u001d3SK\u0006$WM\u001d\t\u0003aMj\u0011!\r\u0006\u0003e-\n!![8\n\u0005Q\n$\u0001\u0004'p]\u001e<&/\u001b;bE2,\u0007C\u0001\u00197\u0013\t9\u0014GA\u0007CsR,7o\u0016:ji\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\t1\b\u0005\u0002=\u00015\ta$\u0001\u0006ta2LGo\u0015;beR,\u0012a\u0010\t\u0003\u0001\u000ek\u0011!\u0011\u0006\u0002\u0005\u0006)1oY1mC&\u0011A)\u0011\u0002\u0005\u0019>tw-\u0001\bta2LGo\u0015;beR|F%Z9\u0015\u0005\u001dS\u0005C\u0001!I\u0013\tI\u0015I\u0001\u0003V]&$\bbB&\u0004\u0003\u0003\u0005\raP\u0001\u0004q\u0012\n\u0014aC:qY&$8\u000b^1si\u0002\n\u0001b\u001d9mSR,e\u000eZ\u0001\rgBd\u0017\u000e^#oI~#S-\u001d\u000b\u0003\u000fBCqa\u0013\u0004\u0002\u0002\u0003\u0007q(A\u0005ta2LG/\u00128eA\u0005y1-\u001e:sK:$\bk\\:ji&|g.A\ndkJ\u0014XM\u001c;Q_NLG/[8o?\u0012*\u0017\u000f\u0006\u0002H+\"91*CA\u0001\u0002\u0004y\u0014\u0001E2veJ,g\u000e\u001e)pg&$\u0018n\u001c8!\u00031\u0011XmY8sI2+gn\u001a;i+\u0005I\u0006C\u0001![\u0013\tY\u0016IA\u0002J]R\f\u0001C]3d_J$G*\u001a8hi\"|F%Z9\u0015\u0005\u001ds\u0006bB&\r\u0003\u0003\u0005\r!W\u0001\u000ee\u0016\u001cwN\u001d3MK:<G\u000f\u001b\u0011\u0002\u001f\u0019LG.Z%oaV$8\u000b\u001e:fC6,\u0012A\u0019\t\u0003G\u001al\u0011\u0001\u001a\u0006\u0003K.\n!AZ:\n\u0005\u001d$'!\u0005$T\t\u0006$\u0018-\u00138qkR\u001cFO]3b[\u0006\u0019b-\u001b7f\u0013:\u0004X\u000f^*ue\u0016\fWn\u0018\u0013fcR\u0011qI\u001b\u0005\b\u0017>\t\t\u00111\u0001c\u0003A1\u0017\u000e\\3J]B,Ho\u0015;sK\u0006l\u0007%A\u0005sK\u000e|'\u000fZ&fsV\tq&A\u0007sK\u000e|'\u000fZ&fs~#S-\u001d\u000b\u0003\u000fBDqa\u0013\n\u0002\u0002\u0003\u0007q&\u0001\u0006sK\u000e|'\u000fZ&fs\u0002\n1B]3d_J$g+\u00197vKV\tQ'A\bsK\u000e|'\u000f\u001a,bYV,w\fJ3r)\t9e\u000fC\u0004L+\u0005\u0005\t\u0019A\u001b\u0002\u0019I,7m\u001c:e-\u0006dW/\u001a\u0011\u0002\u000b\rdwn]3\u0015\u0003\u001d\u000bQbZ3u\u0007V\u0014(/\u001a8u\u0017\u0016LH#A\u0018\u0002\u001f\u001d,GoQ;se\u0016tGOV1mk\u0016$\u0012!N\u0001\fO\u0016$\bK]8he\u0016\u001c8\u000f\u0006\u0002\u0002\u0002A\u0019\u0001)a\u0001\n\u0007\u0005\u0015\u0011IA\u0003GY>\fG/\u0001\u0006j]&$\u0018.\u00197ju\u0016$RaRA\u0006\u0003+Aq!!\u0004\u001c\u0001\u0004\ty!\u0001\u0006j]B,Ho\u00159mSR\u00042\u0001KA\t\u0013\r\t\u0019\"\u000b\u0002\u000b\u0013:\u0004X\u000f^*qY&$\bbBA\f7\u0001\u0007\u0011\u0011D\u0001\bG>tG/\u001a=u!\rA\u00131D\u0005\u0004\u0003;I#A\u0005+bg.\fE\u000f^3naR\u001cuN\u001c;fqR\fAB\\3yi.+\u0017PV1mk\u0016$\"!a\t\u0011\u0007\u0001\u000b)#C\u0002\u0002(\u0005\u0013qAQ8pY\u0016\fg\u000e"
)
public class FixedLengthBinaryRecordReader extends RecordReader {
   private long splitStart = 0L;
   private long splitEnd = 0L;
   private long currentPosition = 0L;
   private int recordLength = 0;
   private FSDataInputStream fileInputStream = null;
   private LongWritable recordKey = null;
   private BytesWritable recordValue = null;

   private long splitStart() {
      return this.splitStart;
   }

   private void splitStart_$eq(final long x$1) {
      this.splitStart = x$1;
   }

   private long splitEnd() {
      return this.splitEnd;
   }

   private void splitEnd_$eq(final long x$1) {
      this.splitEnd = x$1;
   }

   private long currentPosition() {
      return this.currentPosition;
   }

   private void currentPosition_$eq(final long x$1) {
      this.currentPosition = x$1;
   }

   private int recordLength() {
      return this.recordLength;
   }

   private void recordLength_$eq(final int x$1) {
      this.recordLength = x$1;
   }

   private FSDataInputStream fileInputStream() {
      return this.fileInputStream;
   }

   private void fileInputStream_$eq(final FSDataInputStream x$1) {
      this.fileInputStream = x$1;
   }

   private LongWritable recordKey() {
      return this.recordKey;
   }

   private void recordKey_$eq(final LongWritable x$1) {
      this.recordKey = x$1;
   }

   private BytesWritable recordValue() {
      return this.recordValue;
   }

   private void recordValue_$eq(final BytesWritable x$1) {
      this.recordValue = x$1;
   }

   public void close() {
      if (this.fileInputStream() != null) {
         this.fileInputStream().close();
      }
   }

   public LongWritable getCurrentKey() {
      return this.recordKey();
   }

   public BytesWritable getCurrentValue() {
      return this.recordValue();
   }

   public float getProgress() {
      long var2 = this.splitStart();
      return var2 == this.splitEnd() ? 0.0F : (float)Math.min((double)((float)((this.currentPosition() - this.splitStart()) / (this.splitEnd() - this.splitStart()))), (double)1.0F);
   }

   public void initialize(final InputSplit inputSplit, final TaskAttemptContext context) {
      FileSplit fileSplit = (FileSplit)inputSplit;
      this.splitStart_$eq(fileSplit.getStart());
      this.splitEnd_$eq(this.splitStart() + fileSplit.getLength());
      Path file = fileSplit.getPath();
      Configuration conf = context.getConfiguration();
      CompressionCodec codec = (new CompressionCodecFactory(conf)).getCodec(file);
      if (codec != null) {
         throw new IOException("FixedLengthRecordReader does not support reading compressed files");
      } else {
         this.recordLength_$eq(FixedLengthBinaryInputFormat$.MODULE$.getRecordLength(context));
         FileSystem fs = file.getFileSystem(conf);
         this.fileInputStream_$eq(fs.open(file));
         this.fileInputStream().seek(this.splitStart());
         this.currentPosition_$eq(this.splitStart());
      }
   }

   public boolean nextKeyValue() {
      if (this.recordKey() == null) {
         this.recordKey_$eq(new LongWritable());
      }

      this.recordKey().set(this.currentPosition() / (long)this.recordLength());
      if (this.recordValue() == null) {
         this.recordValue_$eq(new BytesWritable(new byte[this.recordLength()]));
      }

      if (this.currentPosition() < this.splitEnd()) {
         byte[] buffer = this.recordValue().getBytes();
         this.fileInputStream().readFully(buffer);
         this.currentPosition_$eq(this.currentPosition() + (long)this.recordLength());
         return true;
      } else {
         return false;
      }
   }
}
