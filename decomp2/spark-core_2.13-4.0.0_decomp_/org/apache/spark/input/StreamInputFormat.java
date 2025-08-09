package org.apache.spark.input;

import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.CombineFileRecordReader;
import org.apache.hadoop.mapreduce.lib.input.CombineFileSplit;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u2Qa\u0001\u0003\u0001\r1AQ\u0001\u0006\u0001\u0005\u0002YAQ\u0001\u0007\u0001\u0005Be\u0011\u0011c\u0015;sK\u0006l\u0017J\u001c9vi\u001a{'/\\1u\u0015\t)a!A\u0003j]B,HO\u0003\u0002\b\u0011\u0005)1\u000f]1sW*\u0011\u0011BC\u0001\u0007CB\f7\r[3\u000b\u0003-\t1a\u001c:h'\t\u0001Q\u0002E\u0002\u000f\u001fEi\u0011\u0001B\u0005\u0003!\u0011\u0011Qc\u0015;sK\u0006lg)\u001b7f\u0013:\u0004X\u000f\u001e$pe6\fG\u000f\u0005\u0002\u000f%%\u00111\u0003\u0002\u0002\u0013!>\u0014H/\u00192mK\u0012\u000bG/Y*ue\u0016\fW.\u0001\u0004=S:LGOP\u0002\u0001)\u00059\u0002C\u0001\b\u0001\u0003I\u0019'/Z1uKJ+7m\u001c:e%\u0016\fG-\u001a:\u0015\u0007i\u0011\u0004\b\u0005\u0003\u001cG\u0015\nR\"\u0001\u000f\u000b\u0005\u0015i\"B\u0001\u0010 \u0003\ra\u0017N\u0019\u0006\u0003A\u0005\n\u0011\"\\1qe\u0016$WoY3\u000b\u0005\tB\u0011A\u00025bI>|\u0007/\u0003\u0002%9\t92i\\7cS:,g)\u001b7f%\u0016\u001cwN\u001d3SK\u0006$WM\u001d\t\u0003M=r!aJ\u0017\u0011\u0005!ZS\"A\u0015\u000b\u0005)*\u0012A\u0002\u001fs_>$hHC\u0001-\u0003\u0015\u00198-\u00197b\u0013\tq3&\u0001\u0004Qe\u0016$WMZ\u0005\u0003aE\u0012aa\u0015;sS:<'B\u0001\u0018,\u0011\u0015\u0019$\u00011\u00015\u0003\u0015\u0019\b\u000f\\5u!\t)d'D\u0001 \u0013\t9tD\u0001\u0006J]B,Ho\u00159mSRDQ!\u000f\u0002A\u0002i\n\u0011\u0002^1D_:$X\r\u001f;\u0011\u0005UZ\u0014B\u0001\u001f \u0005I!\u0016m]6BiR,W\u000e\u001d;D_:$X\r\u001f;"
)
public class StreamInputFormat extends StreamFileInputFormat {
   public CombineFileRecordReader createRecordReader(final InputSplit split, final TaskAttemptContext taContext) {
      return new CombineFileRecordReader((CombineFileSplit)split, taContext, StreamRecordReader.class);
   }
}
