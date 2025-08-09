package org.apache.spark.storage;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import scala.Function2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m3Aa\u0003\u0007\u0005+!AA\u0004\u0001BC\u0002\u0013\u0005Q\u0004\u0003\u0005#\u0001\t\u0005\t\u0015!\u0003\u001f\u0011!\u0019\u0003A!b\u0001\n\u0013!\u0003\u0002C\u0019\u0001\u0005\u0003\u0005\u000b\u0011B\u0013\t\u0011I\u0002!Q1A\u0005\nMB\u0001b\u000e\u0001\u0003\u0002\u0003\u0006I\u0001\u000e\u0005\u0006q\u0001!\t!\u000f\u0005\u0006q\u0001!\tA\u0010\u0005\u0006\u0003\u0002!\tA\u0011\u0005\u0006)\u0002!\t!\u0016\u0002\u0011\u00052|7m[%oM><&/\u00199qKJT!!\u0004\b\u0002\u000fM$xN]1hK*\u0011q\u0002E\u0001\u0006gB\f'o\u001b\u0006\u0003#I\ta!\u00199bG\",'\"A\n\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u00011\u0002CA\f\u001b\u001b\u0005A\"\"A\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005mA\"AB!osJ+g-\u0001\u0003j]\u001a|W#\u0001\u0010\u0011\u0005}\u0001S\"\u0001\u0007\n\u0005\u0005b!!\u0003\"m_\u000e\\\u0017J\u001c4p\u0003\u0015IgNZ8!\u0003\u0011awnY6\u0016\u0003\u0015\u0002\"AJ\u0018\u000e\u0003\u001dR!\u0001K\u0015\u0002\u000b1|7m[:\u000b\u0005)Z\u0013AC2p]\u000e,(O]3oi*\u0011A&L\u0001\u0005kRLGNC\u0001/\u0003\u0011Q\u0017M^1\n\u0005A:#\u0001\u0002'pG.\fQ\u0001\\8dW\u0002\n\u0011bY8oI&$\u0018n\u001c8\u0016\u0003Q\u0002\"AJ\u001b\n\u0005Y:#!C\"p]\u0012LG/[8o\u0003)\u0019wN\u001c3ji&|g\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\tiZD(\u0010\t\u0003?\u0001AQ\u0001H\u0004A\u0002yAQaI\u0004A\u0002\u0015BQAM\u0004A\u0002Q\"2AO A\u0011\u0015a\u0002\u00021\u0001\u001f\u0011\u0015\u0019\u0003\u00021\u0001&\u0003!9\u0018\u000e\u001e5M_\u000e\\WCA\"G)\t!u\n\u0005\u0002F\r2\u0001A!B$\n\u0005\u0004A%!\u0001+\u0012\u0005%c\u0005CA\fK\u0013\tY\u0005DA\u0004O_RD\u0017N\\4\u0011\u0005]i\u0015B\u0001(\u0019\u0005\r\te.\u001f\u0005\u0006!&\u0001\r!U\u0001\u0002MB)qC\u0015\u00105\t&\u00111\u000b\u0007\u0002\n\rVt7\r^5p]J\nq\u0001\u001e:z\u0019>\u001c7\u000e\u0006\u0002W3B\u0011qcV\u0005\u00031b\u0011A!\u00168ji\")\u0001K\u0003a\u00015B)qC\u0015\u00105-\u0002"
)
public class BlockInfoWrapper {
   private final BlockInfo info;
   private final Lock lock;
   private final Condition condition;

   public BlockInfo info() {
      return this.info;
   }

   private Lock lock() {
      return this.lock;
   }

   private Condition condition() {
      return this.condition;
   }

   public Object withLock(final Function2 f) {
      this.lock().lock();

      Object var10000;
      try {
         var10000 = f.apply(this.info(), this.condition());
      } finally {
         this.lock().unlock();
      }

      return var10000;
   }

   public void tryLock(final Function2 f) {
      if (this.lock().tryLock()) {
         try {
            f.apply(this.info(), this.condition());
         } finally {
            this.lock().unlock();
         }

      }
   }

   public BlockInfoWrapper(final BlockInfo info, final Lock lock, final Condition condition) {
      this.info = info;
      this.lock = lock;
      this.condition = condition;
   }

   public BlockInfoWrapper(final BlockInfo info, final Lock lock) {
      this(info, lock, lock.newCondition());
   }
}
