package org.apache.spark.input;

import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.CombineFileSplit;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q2QAB\u0004\u0001\u0013=A\u0001b\u0006\u0001\u0003\u0002\u0003\u0006I!\u0007\u0005\tI\u0001\u0011\t\u0011)A\u0005K!A\u0011\u0006\u0001B\u0001B\u0003%!\u0006C\u00033\u0001\u0011\u00051\u0007C\u00039\u0001\u0011\u0005\u0011H\u0001\nTiJ,\u0017-\u001c*fG>\u0014HMU3bI\u0016\u0014(B\u0001\u0005\n\u0003\u0015Ig\u000e];u\u0015\tQ1\"A\u0003ta\u0006\u00148N\u0003\u0002\r\u001b\u00051\u0011\r]1dQ\u0016T\u0011AD\u0001\u0004_J<7C\u0001\u0001\u0011!\r\t\"\u0003F\u0007\u0002\u000f%\u00111c\u0002\u0002\u0018'R\u0014X-Y7CCN,GMU3d_J$'+Z1eKJ\u0004\"!E\u000b\n\u0005Y9!A\u0005)peR\f'\r\\3ECR\f7\u000b\u001e:fC6\fQa\u001d9mSR\u001c\u0001\u0001\u0005\u0002\u001bE5\t1D\u0003\u0002\t9)\u0011QDH\u0001\u0004Y&\u0014'BA\u0010!\u0003%i\u0017\r\u001d:fIV\u001cWM\u0003\u0002\"\u0017\u00051\u0001.\u00193p_BL!aI\u000e\u0003!\r{WNY5oK\u001aKG.Z*qY&$\u0018aB2p]R,\u0007\u0010\u001e\t\u0003M\u001dj\u0011AH\u0005\u0003Qy\u0011!\u0003V1tW\u0006#H/Z7qi\u000e{g\u000e^3yi\u0006)\u0011N\u001c3fqB\u00111\u0006M\u0007\u0002Y)\u0011QFL\u0001\u0005Y\u0006twMC\u00010\u0003\u0011Q\u0017M^1\n\u0005Eb#aB%oi\u0016<WM]\u0001\u0007y%t\u0017\u000e\u001e \u0015\tQ*dg\u000e\t\u0003#\u0001AQa\u0006\u0003A\u0002eAQ\u0001\n\u0003A\u0002\u0015BQ!\u000b\u0003A\u0002)\n1\u0002]1sg\u0016\u001cFO]3b[R\u0011AC\u000f\u0005\u0006w\u0015\u0001\r\u0001F\u0001\tS:\u001cFO]3b[\u0002"
)
public class StreamRecordReader extends StreamBasedRecordReader {
   public PortableDataStream parseStream(final PortableDataStream inStream) {
      return inStream;
   }

   public StreamRecordReader(final CombineFileSplit split, final TaskAttemptContext context, final Integer index) {
      super(split, context, index);
   }
}
