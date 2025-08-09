package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders$;
import org.apache.spark.sql.TypedColumn;
import org.apache.spark.sql.expressions.Aggregator;
import scala.Function1;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005Q4A\u0001D\u0007\u00011!Aq\u0007\u0001BC\u0002\u0013\u0005\u0001\b\u0003\u0005=\u0001\t\u0005\t\u0015!\u0003:\u0011\u0015i\u0004\u0001\"\u0001?\u0011\u0015\u0011\u0005\u0001\"\u0011D\u0011\u0015!\u0005\u0001\"\u0011F\u0011\u0015Q\u0005\u0001\"\u0011L\u0011\u0015q\u0005\u0001\"\u0011P\u0011\u0015!\u0006\u0001\"\u0011V\u0011\u0015Q\u0006\u0001\"\u0011\\\u0011\u0015i\u0004\u0001\"\u0001^\u0011\u0015y\u0007\u0001\"\u0001q\u00051!\u0016\u0010]3e\u0003Z,'/Y4f\u0015\tqq\"\u0001\u0005j]R,'O\\1m\u0015\t\u0001\u0012#A\u0002tc2T!AE\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005Q)\u0012AB1qC\u000eDWMC\u0001\u0017\u0003\ry'oZ\u0002\u0001+\tI\"e\u0005\u0002\u00015A)1D\b\u0011/c5\tAD\u0003\u0002\u001e\u001f\u0005YQ\r\u001f9sKN\u001c\u0018n\u001c8t\u0013\tyBD\u0001\u0006BO\u001e\u0014XmZ1u_J\u0004\"!\t\u0012\r\u0001\u0011)1\u0005\u0001b\u0001I\t\u0011\u0011JT\t\u0003K-\u0002\"AJ\u0015\u000e\u0003\u001dR\u0011\u0001K\u0001\u0006g\u000e\fG.Y\u0005\u0003U\u001d\u0012qAT8uQ&tw\r\u0005\u0002'Y%\u0011Qf\n\u0002\u0004\u0003:L\b\u0003\u0002\u00140cQJ!\u0001M\u0014\u0003\rQ+\b\u000f\\33!\t1#'\u0003\u00024O\t1Ai\\;cY\u0016\u0004\"AJ\u001b\n\u0005Y:#\u0001\u0002'p]\u001e\f\u0011AZ\u000b\u0002sA!aE\u000f\u00112\u0013\tYtEA\u0005Gk:\u001cG/[8oc\u0005\u0011a\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005}\n\u0005c\u0001!\u0001A5\tQ\u0002C\u00038\u0007\u0001\u0007\u0011(\u0001\u0003{KJ|W#\u0001\u0018\u0002\rI,G-^2f)\rqc\t\u0013\u0005\u0006\u000f\u0016\u0001\rAL\u0001\u0002E\")\u0011*\u0002a\u0001A\u0005\t\u0011-\u0001\u0004gS:L7\u000f\u001b\u000b\u0003c1CQ!\u0014\u0004A\u00029\n\u0011B]3ek\u000e$\u0018n\u001c8\u0002\u000b5,'oZ3\u0015\u00079\u0002&\u000bC\u0003R\u000f\u0001\u0007a&\u0001\u0002cc!)1k\u0002a\u0001]\u0005\u0011!MM\u0001\u000eEV4g-\u001a:F]\u000e|G-\u001a:\u0016\u0003Y\u00032a\u0016-/\u001b\u0005y\u0011BA-\u0010\u0005\u001d)enY8eKJ\fQb\\;uaV$XI\\2pI\u0016\u0014X#\u0001/\u0011\u0007]C\u0016\u0007\u0006\u0002@=\")qG\u0003a\u0001?B!\u0001m\u001a\u0011j\u001b\u0005\t'B\u00012d\u0003!1WO\\2uS>t'B\u00013f\u0003\u0011Q\u0017M^1\u000b\u0005\u0019\f\u0012aA1qS&\u0011\u0001.\u0019\u0002\f\u001b\u0006\u0004h)\u001e8di&|g\u000e\u0005\u0002k]6\t1N\u0003\u0002m[\u0006!A.\u00198h\u0015\u0005!\u0017BA\u001al\u00031!xnQ8mk6t'*\u0019<b+\u0005\t\b\u0003B,sA%L!a]\b\u0003\u0017QK\b/\u001a3D_2,XN\u001c"
)
public class TypedAverage extends Aggregator {
   private final Function1 f;

   public Function1 f() {
      return this.f;
   }

   public Tuple2 zero() {
      return new Tuple2.mcDJ.sp((double)0.0F, 0L);
   }

   public Tuple2 reduce(final Tuple2 b, final Object a) {
      return new Tuple2.mcDJ.sp(BoxesRunTime.unboxToDouble(this.f().apply(a)) + b._1$mcD$sp(), 1L + b._2$mcJ$sp());
   }

   public double finish(final Tuple2 reduction) {
      return reduction._1$mcD$sp() / (double)reduction._2$mcJ$sp();
   }

   public Tuple2 merge(final Tuple2 b1, final Tuple2 b2) {
      return new Tuple2.mcDJ.sp(b1._1$mcD$sp() + b2._1$mcD$sp(), b1._2$mcJ$sp() + b2._2$mcJ$sp());
   }

   public Encoder bufferEncoder() {
      return Encoders$.MODULE$.tuple(Encoders$.MODULE$.scalaDouble(), Encoders$.MODULE$.scalaLong());
   }

   public Encoder outputEncoder() {
      return Encoders$.MODULE$.scalaDouble();
   }

   public TypedColumn toColumnJava() {
      return this.toColumn();
   }

   public TypedAverage(final Function1 f) {
      this.f = f;
   }

   public TypedAverage(final MapFunction f) {
      this((Function1)(new Serializable(f) {
         private static final long serialVersionUID = 0L;
         private final MapFunction f$4;

         public final double apply(final Object x) {
            return BoxesRunTime.unboxToDouble(this.f$4.call(x));
         }

         public {
            this.f$4 = f$4;
         }
      }));
   }
}
