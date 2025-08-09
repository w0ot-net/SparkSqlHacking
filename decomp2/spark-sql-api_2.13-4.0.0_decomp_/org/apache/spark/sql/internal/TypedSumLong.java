package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders$;
import org.apache.spark.sql.TypedColumn;
import org.apache.spark.sql.expressions.Aggregator;
import scala.Function1;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000514A\u0001D\u0007\u00011!A\u0011\u0007\u0001BC\u0002\u0013\u0005!\u0007\u0003\u00057\u0001\t\u0005\t\u0015!\u00034\u0011\u00159\u0004\u0001\"\u00019\u0011\u0015a\u0004\u0001\"\u0011>\u0011\u0015q\u0004\u0001\"\u0011@\u0011\u0015!\u0005\u0001\"\u0011F\u0011\u0015Q\u0005\u0001\"\u0011L\u0011\u0015q\u0005\u0001\"\u0011P\u0011\u0015!\u0006\u0001\"\u0011P\u0011\u00159\u0004\u0001\"\u0001V\u0011\u00159\u0007\u0001\"\u0001i\u00051!\u0016\u0010]3e'VlGj\u001c8h\u0015\tqq\"\u0001\u0005j]R,'O\\1m\u0015\t\u0001\u0012#A\u0002tc2T!AE\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005Q)\u0012AB1qC\u000eDWMC\u0001\u0017\u0003\ry'oZ\u0002\u0001+\tI\"e\u0005\u0002\u00015A)1D\b\u0011/]5\tAD\u0003\u0002\u001e\u001f\u0005YQ\r\u001f9sKN\u001c\u0018n\u001c8t\u0013\tyBD\u0001\u0006BO\u001e\u0014XmZ1u_J\u0004\"!\t\u0012\r\u0001\u0011)1\u0005\u0001b\u0001I\t\u0011\u0011JT\t\u0003K-\u0002\"AJ\u0015\u000e\u0003\u001dR\u0011\u0001K\u0001\u0006g\u000e\fG.Y\u0005\u0003U\u001d\u0012qAT8uQ&tw\r\u0005\u0002'Y%\u0011Qf\n\u0002\u0004\u0003:L\bC\u0001\u00140\u0013\t\u0001tE\u0001\u0003M_:<\u0017!\u00014\u0016\u0003M\u0002BA\n\u001b!]%\u0011Qg\n\u0002\n\rVt7\r^5p]F\n!A\u001a\u0011\u0002\rqJg.\u001b;?)\tI4\bE\u0002;\u0001\u0001j\u0011!\u0004\u0005\u0006c\r\u0001\raM\u0001\u0005u\u0016\u0014x.F\u0001/\u0003\u0019\u0011X\rZ;dKR\u0019a\u0006\u0011\"\t\u000b\u0005+\u0001\u0019\u0001\u0018\u0002\u0003\tDQaQ\u0003A\u0002\u0001\n\u0011!Y\u0001\u0006[\u0016\u0014x-\u001a\u000b\u0004]\u0019C\u0005\"B$\u0007\u0001\u0004q\u0013A\u000122\u0011\u0015Ie\u00011\u0001/\u0003\t\u0011''\u0001\u0004gS:L7\u000f\u001b\u000b\u0003]1CQ!T\u0004A\u00029\n\u0011B]3ek\u000e$\u0018n\u001c8\u0002\u001b\t,hMZ3s\u000b:\u001cw\u000eZ3s+\u0005\u0001\u0006cA)S]5\tq\"\u0003\u0002T\u001f\t9QI\\2pI\u0016\u0014\u0018!D8viB,H/\u00128d_\u0012,'\u000f\u0006\u0002:-\")\u0011G\u0003a\u0001/B!\u0001l\u0018\u0011b\u001b\u0005I&B\u0001.\\\u0003!1WO\\2uS>t'B\u0001/^\u0003\u0011Q\u0017M^1\u000b\u0005y\u000b\u0012aA1qS&\u0011\u0001-\u0017\u0002\f\u001b\u0006\u0004h)\u001e8di&|g\u000e\u0005\u0002cM6\t1M\u0003\u0002eK\u0006!A.\u00198h\u0015\u0005a\u0016B\u0001\u0019d\u00031!xnQ8mk6t'*\u0019<b+\u0005I\u0007\u0003B)kA\u0005L!a[\b\u0003\u0017QK\b/\u001a3D_2,XN\u001c"
)
public class TypedSumLong extends Aggregator {
   private final Function1 f;

   public Function1 f() {
      return this.f;
   }

   public long zero() {
      return 0L;
   }

   public long reduce(final long b, final Object a) {
      return b + BoxesRunTime.unboxToLong(this.f().apply(a));
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

   public TypedSumLong(final Function1 f) {
      this.f = f;
   }

   public TypedSumLong(final MapFunction f) {
      this((Function1)(new Serializable(f) {
         private static final long serialVersionUID = 0L;
         private final MapFunction f$2;

         public final long apply(final Object x) {
            return BoxesRunTime.unboxToLong(this.f$2.call(x));
         }

         public {
            this.f$2 = f$2;
         }
      }));
   }
}
