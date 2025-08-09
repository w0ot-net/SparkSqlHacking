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
   bytes = "\u0006\u000514A\u0001D\u0007\u00011!A\u0011\u0007\u0001BC\u0002\u0013\u0005!\u0007\u0003\u00057\u0001\t\u0005\t\u0015!\u00034\u0011\u00159\u0004\u0001\"\u00019\u0011\u0015a\u0004\u0001\"\u0011>\u0011\u0015q\u0004\u0001\"\u0011@\u0011\u0015!\u0005\u0001\"\u0011F\u0011\u0015Q\u0005\u0001\"\u0011L\u0011\u0015q\u0005\u0001\"\u0011P\u0011\u0015!\u0006\u0001\"\u0011P\u0011\u00159\u0004\u0001\"\u0001V\u0011\u00159\u0007\u0001\"\u0001i\u00059!\u0016\u0010]3e'VlGi\\;cY\u0016T!AD\b\u0002\u0011%tG/\u001a:oC2T!\u0001E\t\u0002\u0007M\fHN\u0003\u0002\u0013'\u0005)1\u000f]1sW*\u0011A#F\u0001\u0007CB\f7\r[3\u000b\u0003Y\t1a\u001c:h\u0007\u0001)\"!\u0007\u0012\u0014\u0005\u0001Q\u0002#B\u000e\u001fA9rS\"\u0001\u000f\u000b\u0005uy\u0011aC3yaJ,7o]5p]NL!a\b\u000f\u0003\u0015\u0005;wM]3hCR|'\u000f\u0005\u0002\"E1\u0001A!B\u0012\u0001\u0005\u0004!#AA%O#\t)3\u0006\u0005\u0002'S5\tqEC\u0001)\u0003\u0015\u00198-\u00197b\u0013\tQsEA\u0004O_RD\u0017N\\4\u0011\u0005\u0019b\u0013BA\u0017(\u0005\r\te.\u001f\t\u0003M=J!\u0001M\u0014\u0003\r\u0011{WO\u00197f\u0003\u00051W#A\u001a\u0011\t\u0019\"\u0004EL\u0005\u0003k\u001d\u0012\u0011BR;oGRLwN\\\u0019\u0002\u0005\u0019\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002:wA\u0019!\b\u0001\u0011\u000e\u00035AQ!M\u0002A\u0002M\nAA_3s_V\ta&\u0001\u0004sK\u0012,8-\u001a\u000b\u0004]\u0001\u0013\u0005\"B!\u0006\u0001\u0004q\u0013!\u00012\t\u000b\r+\u0001\u0019\u0001\u0011\u0002\u0003\u0005\fQ!\\3sO\u0016$2A\f$I\u0011\u00159e\u00011\u0001/\u0003\t\u0011\u0017\u0007C\u0003J\r\u0001\u0007a&\u0001\u0002ce\u00051a-\u001b8jg\"$\"A\f'\t\u000b5;\u0001\u0019\u0001\u0018\u0002\u0013I,G-^2uS>t\u0017!\u00042vM\u001a,'/\u00128d_\u0012,'/F\u0001Q!\r\t&KL\u0007\u0002\u001f%\u00111k\u0004\u0002\b\u000b:\u001cw\u000eZ3s\u00035yW\u000f\u001e9vi\u0016s7m\u001c3feR\u0011\u0011H\u0016\u0005\u0006c)\u0001\ra\u0016\t\u00051~\u0003\u0013-D\u0001Z\u0015\tQ6,\u0001\u0005gk:\u001cG/[8o\u0015\taV,\u0001\u0003kCZ\f'B\u00010\u0012\u0003\r\t\u0007/[\u0005\u0003Af\u00131\"T1q\rVt7\r^5p]B\u0011!MZ\u0007\u0002G*\u0011A-Z\u0001\u0005Y\u0006twMC\u0001]\u0013\t\u00014-\u0001\u0007u_\u000e{G.^7o\u0015\u00064\u0018-F\u0001j!\u0011\t&\u000eI1\n\u0005-|!a\u0003+za\u0016$7i\u001c7v[:\u0004"
)
public class TypedSumDouble extends Aggregator {
   private final Function1 f;

   public Function1 f() {
      return this.f;
   }

   public double zero() {
      return (double)0.0F;
   }

   public double reduce(final double b, final Object a) {
      return b + BoxesRunTime.unboxToDouble(this.f().apply(a));
   }

   public double merge(final double b1, final double b2) {
      return b1 + b2;
   }

   public double finish(final double reduction) {
      return reduction;
   }

   public Encoder bufferEncoder() {
      return Encoders$.MODULE$.scalaDouble();
   }

   public Encoder outputEncoder() {
      return Encoders$.MODULE$.scalaDouble();
   }

   public TypedColumn toColumnJava() {
      return this.toColumn();
   }

   public TypedSumDouble(final Function1 f) {
      this.f = f;
   }

   public TypedSumDouble(final MapFunction f) {
      this((Function1)(new Serializable(f) {
         private static final long serialVersionUID = 0L;
         private final MapFunction f$1;

         public final double apply(final Object x) {
            return BoxesRunTime.unboxToDouble(this.f$1.call(x));
         }

         public {
            this.f$1 = f$1;
         }
      }));
   }
}
