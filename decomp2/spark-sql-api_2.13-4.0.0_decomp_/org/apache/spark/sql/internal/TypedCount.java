package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders$;
import org.apache.spark.sql.TypedColumn;
import org.apache.spark.sql.expressions.Aggregator;
import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=4A\u0001D\u0007\u00011!A\u0011\u0007\u0001BC\u0002\u0013\u0005!\u0007\u0003\u00057\u0001\t\u0005\t\u0015!\u00034\u0011\u00159\u0004\u0001\"\u00019\u0011\u0015a\u0004\u0001\"\u0011>\u0011\u0015q\u0004\u0001\"\u0011@\u0011\u0015!\u0005\u0001\"\u0011F\u0011\u0015Q\u0005\u0001\"\u0011L\u0011\u0015q\u0005\u0001\"\u0011P\u0011\u0015!\u0006\u0001\"\u0011P\u0011\u00159\u0004\u0001\"\u0001V\u0011\u0015A\u0007\u0001\"\u0001j\u0005)!\u0016\u0010]3e\u0007>,h\u000e\u001e\u0006\u0003\u001d=\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003!E\t1a]9m\u0015\t\u00112#A\u0003ta\u0006\u00148N\u0003\u0002\u0015+\u00051\u0011\r]1dQ\u0016T\u0011AF\u0001\u0004_J<7\u0001A\u000b\u00033\t\u001a\"\u0001\u0001\u000e\u0011\u000bmq\u0002E\f\u0018\u000e\u0003qQ!!H\b\u0002\u0017\u0015D\bO]3tg&|gn]\u0005\u0003?q\u0011!\"Q4he\u0016<\u0017\r^8s!\t\t#\u0005\u0004\u0001\u0005\u000b\r\u0002!\u0019\u0001\u0013\u0003\u0005%s\u0015CA\u0013,!\t1\u0013&D\u0001(\u0015\u0005A\u0013!B:dC2\f\u0017B\u0001\u0016(\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\n\u0017\n\u00055:#aA!osB\u0011aeL\u0005\u0003a\u001d\u0012A\u0001T8oO\u0006\ta-F\u00014!\u00111C\u0007I\u0016\n\u0005U:#!\u0003$v]\u000e$\u0018n\u001c82\u0003\t1\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003sm\u00022A\u000f\u0001!\u001b\u0005i\u0001\"B\u0019\u0004\u0001\u0004\u0019\u0014\u0001\u0002>fe>,\u0012AL\u0001\u0007e\u0016$WoY3\u0015\u00079\u0002%\tC\u0003B\u000b\u0001\u0007a&A\u0001c\u0011\u0015\u0019U\u00011\u0001!\u0003\u0005\t\u0017!B7fe\u001e,Gc\u0001\u0018G\u0011\")qI\u0002a\u0001]\u0005\u0011!-\r\u0005\u0006\u0013\u001a\u0001\rAL\u0001\u0003EJ\naAZ5oSNDGC\u0001\u0018M\u0011\u0015iu\u00011\u0001/\u0003%\u0011X\rZ;di&|g.A\u0007ck\u001a4WM]#oG>$WM]\u000b\u0002!B\u0019\u0011K\u0015\u0018\u000e\u0003=I!aU\b\u0003\u000f\u0015s7m\u001c3fe\u0006iq.\u001e;qkR,enY8eKJ$\"!\u000f,\t\u000bER\u0001\u0019A,\u0011\ta{\u0006%Y\u0007\u00023*\u0011!lW\u0001\tMVt7\r^5p]*\u0011A,X\u0001\u0005U\u00064\u0018M\u0003\u0002_#\u0005\u0019\u0011\r]5\n\u0005\u0001L&aC'ba\u001a+hn\u0019;j_:\u0004\"A\u00194\u000e\u0003\rT!\u0001Z3\u0002\t1\fgn\u001a\u0006\u00029&\u0011qm\u0019\u0002\u0007\u001f\nTWm\u0019;\u0002\u0019Q|7i\u001c7v[:T\u0015M^1\u0016\u0003)\u0004B!U6![&\u0011An\u0004\u0002\f)f\u0004X\rZ\"pYVlg\u000e\u0005\u0002c]&\u0011\u0001g\u0019"
)
public class TypedCount extends Aggregator {
   private final Function1 f;

   public Function1 f() {
      return this.f;
   }

   public long zero() {
      return 0L;
   }

   public long reduce(final long b, final Object a) {
      return this.f().apply(a) == null ? b : b + 1L;
   }

   public long merge(final long b1, final long b2) {
      return b1 + b2;
   }

   public long finish(final long reduction) {
      return reduction;
   }

   public Encoder bufferEncoder() {
      return Encoders$.MODULE$.scalaLong();
   }

   public Encoder outputEncoder() {
      return Encoders$.MODULE$.scalaLong();
   }

   public TypedColumn toColumnJava() {
      return this.toColumn();
   }

   public TypedCount(final Function1 f) {
      this.f = f;
   }

   public TypedCount(final MapFunction f) {
      this((Function1)(new Serializable(f) {
         private static final long serialVersionUID = 0L;
         private final MapFunction f$3;

         public final Object apply(final Object x) {
            return this.f$3.call(x);
         }

         public {
            this.f$3 = f$3;
         }
      }));
   }
}
