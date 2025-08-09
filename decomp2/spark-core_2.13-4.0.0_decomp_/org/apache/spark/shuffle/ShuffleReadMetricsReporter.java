package org.apache.spark.shuffle;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u3\u0001BE\n\u0011\u0002G\u0005Qc\u0007\u0005\u0007E\u00011\t!F\u0012\t\r5\u0002a\u0011A\u000b/\u0011\u0019\u0001\u0004A\"\u0001\u0016c!11\u0007\u0001D\u0001+QBaA\u000e\u0001\u0007\u0002U9\u0004BB\u001d\u0001\r\u0003)\"\b\u0003\u0004=\u0001\u0019\u0005Q#\u0010\u0005\u0007\u007f\u00011\t!\u0006!\t\r\t\u0003a\u0011A\u000bD\u0011\u0019)\u0005A\"\u0001\u0016\r\"1\u0001\n\u0001D\u0001+%Caa\u0013\u0001\u0007\u0002Ua\u0005B\u0002(\u0001\r\u0003)r\n\u0003\u0004R\u0001\u0019\u0005QC\u0015\u0005\u0007)\u00021\t!F+\t\r]\u0003a\u0011A\u000bY\u0011\u0019Q\u0006A\"\u0001\u00167\nQ2\u000b[;gM2,'+Z1e\u001b\u0016$(/[2t%\u0016\u0004xN\u001d;fe*\u0011A#F\u0001\bg\",hM\u001a7f\u0015\t1r#A\u0003ta\u0006\u00148N\u0003\u0002\u00193\u00051\u0011\r]1dQ\u0016T\u0011AG\u0001\u0004_J<7C\u0001\u0001\u001d!\ti\u0002%D\u0001\u001f\u0015\u0005y\u0012!B:dC2\f\u0017BA\u0011\u001f\u0005\u0019\te.\u001f*fM\u00061\u0012N\\2SK6|G/\u001a\"m_\u000e\\7OR3uG\",G\r\u0006\u0002%OA\u0011Q$J\u0005\u0003My\u0011A!\u00168ji\")\u0001&\u0001a\u0001U\u0005\tao\u0001\u0001\u0011\u0005uY\u0013B\u0001\u0017\u001f\u0005\u0011auN\\4\u0002+%t7\rT8dC2\u0014En\\2lg\u001a+Go\u00195fIR\u0011Ae\f\u0005\u0006Q\t\u0001\rAK\u0001\u0013S:\u001c'+Z7pi\u0016\u0014\u0015\u0010^3t%\u0016\fG\r\u0006\u0002%e!)\u0001f\u0001a\u0001U\u0005A\u0012N\\2SK6|G/\u001a\"zi\u0016\u001c(+Z1e)>$\u0015n]6\u0015\u0005\u0011*\u0004\"\u0002\u0015\u0005\u0001\u0004Q\u0013!E5oG2{7-\u00197CsR,7OU3bIR\u0011A\u0005\u000f\u0005\u0006Q\u0015\u0001\rAK\u0001\u0011S:\u001cg)\u001a;dQ^\u000b\u0017\u000e\u001e+j[\u0016$\"\u0001J\u001e\t\u000b!2\u0001\u0019\u0001\u0016\u0002\u001d%t7MU3d_J$7OU3bIR\u0011AE\u0010\u0005\u0006Q\u001d\u0001\rAK\u0001\u001cS:\u001c7i\u001c:skB$X*\u001a:hK\u0012\u0014En\\2l\u0007\",hn[:\u0015\u0005\u0011\n\u0005\"\u0002\u0015\t\u0001\u0004Q\u0013aG5oG6+'oZ3e\r\u0016$8\r\u001b$bY2\u0014\u0017mY6D_VtG\u000f\u0006\u0002%\t\")\u0001&\u0003a\u0001U\u0005a\u0012N\\2SK6|G/Z'fe\u001e,GM\u00117pG.\u001ch)\u001a;dQ\u0016$GC\u0001\u0013H\u0011\u0015A#\u00021\u0001+\u0003mIgn\u0019'pG\u0006dW*\u001a:hK\u0012\u0014En\\2lg\u001a+Go\u00195fIR\u0011AE\u0013\u0005\u0006Q-\u0001\rAK\u0001\u001dS:\u001c'+Z7pi\u0016lUM]4fI\u000eCWO\\6t\r\u0016$8\r[3e)\t!S\nC\u0003)\u0019\u0001\u0007!&A\u000ej]\u000edunY1m\u001b\u0016\u0014x-\u001a3DQVt7n\u001d$fi\u000eDW\r\u001a\u000b\u0003IACQ\u0001K\u0007A\u0002)\n\u0001$\u001b8d%\u0016lw\u000e^3NKJ<W\r\u001a\"zi\u0016\u001c(+Z1e)\t!3\u000bC\u0003)\u001d\u0001\u0007!&A\fj]\u000edunY1m\u001b\u0016\u0014x-\u001a3CsR,7OU3bIR\u0011AE\u0016\u0005\u0006Q=\u0001\rAK\u0001\u0016S:\u001c'+Z7pi\u0016\u0014V-]:EkJ\fG/[8o)\t!\u0013\fC\u0003)!\u0001\u0007!&A\u000ej]\u000e\u0014V-\\8uK6+'oZ3e%\u0016\f8\u000fR;sCRLwN\u001c\u000b\u0003IqCQ\u0001K\tA\u0002)\u0002"
)
public interface ShuffleReadMetricsReporter {
   void incRemoteBlocksFetched(final long v);

   void incLocalBlocksFetched(final long v);

   void incRemoteBytesRead(final long v);

   void incRemoteBytesReadToDisk(final long v);

   void incLocalBytesRead(final long v);

   void incFetchWaitTime(final long v);

   void incRecordsRead(final long v);

   void incCorruptMergedBlockChunks(final long v);

   void incMergedFetchFallbackCount(final long v);

   void incRemoteMergedBlocksFetched(final long v);

   void incLocalMergedBlocksFetched(final long v);

   void incRemoteMergedChunksFetched(final long v);

   void incLocalMergedChunksFetched(final long v);

   void incRemoteMergedBytesRead(final long v);

   void incLocalMergedBytesRead(final long v);

   void incRemoteReqsDuration(final long v);

   void incRemoteMergedReqsDuration(final long v);
}
