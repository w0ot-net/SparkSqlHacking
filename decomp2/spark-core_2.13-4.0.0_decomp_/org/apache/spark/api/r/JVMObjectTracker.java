package org.apache.spark.api.r;

import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import scala.Option;
import scala.Option.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%4QAC\u0006\u0001\u0017UAQ\u0001\b\u0001\u0005\u0002yAa!\t\u0001!\u0002\u0013\u0011\u0003BB\u001b\u0001A\u0003%a\u0007C\u0003=\u0001\u0011\u0015Q\bC\u0003D\u0001\u0011\u0015A\tC\u0003Y\u0001\u0011\u0015\u0011\fC\u0003]\u0001\u0011\u0015Q\fC\u0003`\u0001\u0011\u0015\u0001\rC\u0003e\u0001\u0011\u0015QM\u0001\tK-6{%M[3diR\u0013\u0018mY6fe*\u0011A\"D\u0001\u0002e*\u0011abD\u0001\u0004CBL'B\u0001\t\u0012\u0003\u0015\u0019\b/\u0019:l\u0015\t\u00112#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002)\u0005\u0019qN]4\u0014\u0005\u00011\u0002CA\f\u001b\u001b\u0005A\"\"A\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005mA\"AB!osJ+g-\u0001\u0004=S:LGOP\u0002\u0001)\u0005y\u0002C\u0001\u0011\u0001\u001b\u0005Y\u0011AB8cU6\u000b\u0007\u000f\u0005\u0003$U1zS\"\u0001\u0013\u000b\u0005\u00152\u0013AC2p]\u000e,(O]3oi*\u0011q\u0005K\u0001\u0005kRLGNC\u0001*\u0003\u0011Q\u0017M^1\n\u0005-\"#!E\"p]\u000e,(O]3oi\"\u000b7\u000f['baB\u0011\u0001%L\u0005\u0003]-\u00111B\u0013,N\u001f\nTWm\u0019;JIB\u0011\u0001gM\u0007\u0002c)\u0011!\u0007K\u0001\u0005Y\u0006tw-\u0003\u00025c\t1qJ\u00196fGR\f!b\u001c2k\u0007>,h\u000e^3s!\t9$(D\u00019\u0015\tID%\u0001\u0004bi>l\u0017nY\u0005\u0003wa\u0012Q\"\u0011;p[&\u001c\u0017J\u001c;fO\u0016\u0014\u0018aA4fiR\u0011a(\u0011\t\u0004/}z\u0013B\u0001!\u0019\u0005\u0019y\u0005\u000f^5p]\")!\t\u0002a\u0001Y\u0005\u0011\u0011\u000eZ\u0001\u0006CB\u0004H.\u001f\u000b\u0003_\u0015CQAQ\u0003A\u00021B3!B$W!\r9\u0002JS\u0005\u0003\u0013b\u0011a\u0001\u001e5s_^\u001c\bCA&T\u001d\ta\u0015K\u0004\u0002N!6\taJ\u0003\u0002P;\u00051AH]8pizJ\u0011!G\u0005\u0003%b\tq\u0001]1dW\u0006<W-\u0003\u0002U+\n1bj\\*vG\",E.Z7f]R,\u0005pY3qi&|gN\u0003\u0002S1\u0005\nq+\u0001\fjM\u0002ZW-\u001f\u0011e_\u0016\u001c\bE\\8uA\u0015D\u0018n\u001d;/\u0003-\tG\rZ!oI\u001e+G/\u00133\u0015\u00051R\u0006\"B.\u0007\u0001\u0004y\u0013aA8cU\u00061!/Z7pm\u0016$\"A\u00100\t\u000b\t;\u0001\u0019\u0001\u0017\u0002\tML'0Z\u000b\u0002CB\u0011qCY\u0005\u0003Gb\u00111!\u00138u\u0003\u0015\u0019G.Z1s)\u00051\u0007CA\fh\u0013\tA\u0007D\u0001\u0003V]&$\b"
)
public class JVMObjectTracker {
   private final ConcurrentHashMap objMap = new ConcurrentHashMap();
   private final AtomicInteger objCounter = new AtomicInteger();

   public final Option get(final JVMObjectId id) {
      return .MODULE$.apply(this.objMap.get(id));
   }

   public final Object apply(final JVMObjectId id) throws NoSuchElementException {
      return this.get(id).getOrElse(() -> {
         throw new NoSuchElementException(id + " does not exist.");
      });
   }

   public final JVMObjectId addAndGetId(final Object obj) {
      JVMObjectId id = new JVMObjectId(Integer.toString(this.objCounter.getAndIncrement()));
      this.objMap.put(id, obj);
      return id;
   }

   public final Option remove(final JVMObjectId id) {
      return .MODULE$.apply(this.objMap.remove(id));
   }

   public final int size() {
      return this.objMap.size();
   }

   public final void clear() {
      this.objMap.clear();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
