package org.apache.spark.storage.memory;

import org.apache.spark.memory.MemoryMode;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q2q\u0001B\u0003\u0011\u0002G%\u0002\u0003C\u0003\u0019\u0001\u0019\u0005\u0011\u0004C\u0003\u001e\u0001\u0019\u0005a\u0004C\u0003%\u0001\u0019\u0005QEA\u0006NK6|'/_#oiJL(B\u0001\u0004\b\u0003\u0019iW-\\8ss*\u0011\u0001\"C\u0001\bgR|'/Y4f\u0015\tQ1\"A\u0003ta\u0006\u00148N\u0003\u0002\r\u001b\u00051\u0011\r]1dQ\u0016T\u0011AD\u0001\u0004_J<7\u0001A\u000b\u0003#9\u001a\"\u0001\u0001\n\u0011\u0005M1R\"\u0001\u000b\u000b\u0003U\tQa]2bY\u0006L!a\u0006\u000b\u0003\r\u0005s\u0017PU3g\u0003\u0011\u0019\u0018N_3\u0016\u0003i\u0001\"aE\u000e\n\u0005q!\"\u0001\u0002'p]\u001e\f!\"\\3n_JLXj\u001c3f+\u0005y\u0002C\u0001\u0011#\u001b\u0005\t#B\u0001\u0004\n\u0013\t\u0019\u0013E\u0001\u0006NK6|'/_'pI\u0016\f\u0001b\u00197bgN$\u0016mZ\u000b\u0002MA\u0019qE\u000b\u0017\u000e\u0003!R!!\u000b\u000b\u0002\u000fI,g\r\\3di&\u00111\u0006\u000b\u0002\t\u00072\f7o\u001d+bOB\u0011QF\f\u0007\u0001\t\u0015y\u0003A1\u00011\u0005\u0005!\u0016CA\u00195!\t\u0019\"'\u0003\u00024)\t9aj\u001c;iS:<\u0007CA\n6\u0013\t1DCA\u0002B]fL3\u0001\u0001\u001d;\u0013\tITAA\fEKN,'/[1mSj,G-T3n_JLXI\u001c;ss&\u00111(\u0002\u0002\u0016'\u0016\u0014\u0018.\u00197ju\u0016$W*Z7pef,e\u000e\u001e:z\u0001"
)
public interface MemoryEntry {
   long size();

   MemoryMode memoryMode();

   ClassTag classTag();
}
