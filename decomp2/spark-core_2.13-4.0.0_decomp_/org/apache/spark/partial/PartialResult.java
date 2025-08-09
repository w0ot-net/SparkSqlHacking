package org.apache.spark.partial;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}b\u0001\u0002\u000e\u001c\u0001\u0011B\u0001\u0002\f\u0001\u0003\u0002\u0003\u0006I!\f\u0005\tq\u0001\u0011\t\u0011)A\u0005s!)A\b\u0001C\u0001{!9!\t\u0001a\u0001\n\u0013\u0019\u0005bB$\u0001\u0001\u0004%I\u0001\u0013\u0005\u0007\u001d\u0002\u0001\u000b\u0015\u0002#\t\u000f=\u0003\u0001\u0019!C\u0005!\"9a\f\u0001a\u0001\n\u0013y\u0006BB1\u0001A\u0003&\u0011\u000bC\u0004c\u0001\u0001\u0007I\u0011B2\t\u000f!\u0004\u0001\u0019!C\u0005S\"11\u000e\u0001Q!\n\u0011Dq\u0001\u001c\u0001A\u0002\u0013%Q\u000eC\u0004q\u0001\u0001\u0007I\u0011B9\t\rM\u0004\u0001\u0015)\u0003o\u0011\u0015!\b\u0001\"\u0001v\u0011\u00151\b\u0001\"\u0001x\u0011\u0015A\b\u0001\"\u0001z\u0011\u0015Q\b\u0001\"\u0001|\u0011\u0015q\b\u0001\"\u0001\u0000\u0011\u001d\t\u0019\u0001\u0001C\u0001\u0003\u000bA\u0001\"a\u0006\u0001\t\u0003i\u0012\u0011\u0004\u0005\b\u0003?\u0001A\u0011BA\u0011\u0011!\t\u0019\u0003\u0001C\u0001;\u0005\u0015\u0002bBA\u0016\u0001\u0011\u0005\u0013Q\u0006\u0002\u000e!\u0006\u0014H/[1m%\u0016\u001cX\u000f\u001c;\u000b\u0005qi\u0012a\u00029beRL\u0017\r\u001c\u0006\u0003=}\tQa\u001d9be.T!\u0001I\u0011\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0013aA8sO\u000e\u0001QCA\u00130'\t\u0001a\u0005\u0005\u0002(U5\t\u0001FC\u0001*\u0003\u0015\u00198-\u00197b\u0013\tY\u0003F\u0001\u0004B]f\u0014VMZ\u0001\u000bS:LG/[1m-\u0006d\u0007C\u0001\u00180\u0019\u0001!Q\u0001\r\u0001C\u0002E\u0012\u0011AU\t\u0003eU\u0002\"aJ\u001a\n\u0005QB#a\u0002(pi\"Lgn\u001a\t\u0003OYJ!a\u000e\u0015\u0003\u0007\u0005s\u00170A\u0004jg\u001aKg.\u00197\u0011\u0005\u001dR\u0014BA\u001e)\u0005\u001d\u0011un\u001c7fC:\fa\u0001P5oSRtDc\u0001 A\u0003B\u0019q\bA\u0017\u000e\u0003mAQ\u0001L\u0002A\u00025BQ\u0001O\u0002A\u0002e\n!BZ5oC24\u0016\r\\;f+\u0005!\u0005cA\u0014F[%\u0011a\t\u000b\u0002\u0007\u001fB$\u0018n\u001c8\u0002\u001d\u0019Lg.\u00197WC2,Xm\u0018\u0013fcR\u0011\u0011\n\u0014\t\u0003O)K!a\u0013\u0015\u0003\tUs\u0017\u000e\u001e\u0005\b\u001b\u0016\t\t\u00111\u0001E\u0003\rAH%M\u0001\fM&t\u0017\r\u001c,bYV,\u0007%A\u0004gC&dWO]3\u0016\u0003E\u00032aJ#S!\t\u00196L\u0004\u0002U3:\u0011Q\u000bW\u0007\u0002-*\u0011qkI\u0001\u0007yI|w\u000e\u001e \n\u0003%J!A\u0017\u0015\u0002\u000fA\f7m[1hK&\u0011A,\u0018\u0002\n\u000bb\u001cW\r\u001d;j_:T!A\u0017\u0015\u0002\u0017\u0019\f\u0017\u000e\\;sK~#S-\u001d\u000b\u0003\u0013\u0002Dq!\u0014\u0005\u0002\u0002\u0003\u0007\u0011+\u0001\u0005gC&dWO]3!\u0003E\u0019w.\u001c9mKRLwN\u001c%b]\u0012dWM]\u000b\u0002IB\u0019q%R3\u0011\t\u001d2W&S\u0005\u0003O\"\u0012\u0011BR;oGRLwN\\\u0019\u0002+\r|W\u000e\u001d7fi&|g\u000eS1oI2,'o\u0018\u0013fcR\u0011\u0011J\u001b\u0005\b\u001b.\t\t\u00111\u0001e\u0003I\u0019w.\u001c9mKRLwN\u001c%b]\u0012dWM\u001d\u0011\u0002\u001d\u0019\f\u0017\u000e\\;sK\"\u000bg\u000e\u001a7feV\ta\u000eE\u0002(\u000b>\u0004Ba\n4S\u0013\u0006\u0011b-Y5mkJ,\u0007*\u00198eY\u0016\u0014x\fJ3r)\tI%\u000fC\u0004N\u001d\u0005\u0005\t\u0019\u00018\u0002\u001f\u0019\f\u0017\u000e\\;sK\"\u000bg\u000e\u001a7fe\u0002\nA\"\u001b8ji&\fGNV1mk\u0016,\u0012!L\u0001\u0014SNLe.\u001b;jC24\u0016\r\\;f\r&t\u0017\r\\\u000b\u0002s\u0005iq-\u001a;GS:\fGNV1mk\u0016$\u0012!L\u0001\u000b_:\u001cu.\u001c9mKR,GC\u0001 }\u0011\u0015i8\u00031\u0001f\u0003\u001dA\u0017M\u001c3mKJ\faa\u001c8GC&dGcA%\u0002\u0002!)Q\u0010\u0006a\u0001_\u0006\u0019Q.\u00199\u0016\t\u0005\u001d\u0011Q\u0002\u000b\u0005\u0003\u0013\t\t\u0002\u0005\u0003@\u0001\u0005-\u0001c\u0001\u0018\u0002\u000e\u00111\u0011qB\u000bC\u0002E\u0012\u0011\u0001\u0016\u0005\b\u0003')\u0002\u0019AA\u000b\u0003\u00051\u0007#B\u0014g[\u0005-\u0011!D:fi\u001aKg.\u00197WC2,X\rF\u0002J\u00037Aa!!\b\u0017\u0001\u0004i\u0013!\u0002<bYV,\u0017!F4fi\u001aKg.\u00197WC2,X-\u00138uKJt\u0017\r\u001c\u000b\u0002\t\u0006Q1/\u001a;GC&dWO]3\u0015\u0007%\u000b9\u0003\u0003\u0004\u0002*a\u0001\rAU\u0001\nKb\u001cW\r\u001d;j_:\f\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003_\u0001B!!\r\u0002:9!\u00111GA\u001b!\t)\u0006&C\u0002\u00028!\na\u0001\u0015:fI\u00164\u0017\u0002BA\u001e\u0003{\u0011aa\u0015;sS:<'bAA\u001cQ\u0001"
)
public class PartialResult {
   public final Object org$apache$spark$partial$PartialResult$$initialVal;
   public final boolean org$apache$spark$partial$PartialResult$$isFinal;
   private Option finalValue;
   private Option failure;
   private Option completionHandler;
   private Option failureHandler;

   private Option finalValue() {
      return this.finalValue;
   }

   private void finalValue_$eq(final Option x$1) {
      this.finalValue = x$1;
   }

   private Option failure() {
      return this.failure;
   }

   private void failure_$eq(final Option x$1) {
      this.failure = x$1;
   }

   private Option completionHandler() {
      return this.completionHandler;
   }

   private void completionHandler_$eq(final Option x$1) {
      this.completionHandler = x$1;
   }

   private Option failureHandler() {
      return this.failureHandler;
   }

   private void failureHandler_$eq(final Option x$1) {
      this.failureHandler = x$1;
   }

   public Object initialValue() {
      return this.org$apache$spark$partial$PartialResult$$initialVal;
   }

   public boolean isInitialValueFinal() {
      return this.org$apache$spark$partial$PartialResult$$isFinal;
   }

   public synchronized Object getFinalValue() {
      while(this.finalValue().isEmpty() && this.failure().isEmpty()) {
         this.wait();
      }

      if (this.finalValue().isDefined()) {
         return this.finalValue().get();
      } else {
         throw (Throwable)this.failure().get();
      }
   }

   public synchronized PartialResult onComplete(final Function1 handler) {
      if (this.completionHandler().isDefined()) {
         throw new UnsupportedOperationException("onComplete cannot be called twice");
      } else {
         this.completionHandler_$eq(new Some(handler));
         if (this.finalValue().isDefined()) {
            handler.apply(this.finalValue().get());
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         return this;
      }
   }

   public synchronized void onFail(final Function1 handler) {
      if (this.failureHandler().isDefined()) {
         throw new UnsupportedOperationException("onFail cannot be called twice");
      } else {
         this.failureHandler_$eq(new Some(handler));
         if (this.failure().isDefined()) {
            handler.apply(this.failure().get());
         }
      }
   }

   public PartialResult map(final Function1 f) {
      return new PartialResult(f) {
         // $FF: synthetic field
         private final PartialResult $outer;
         private final Function1 f$1;

         public synchronized Object getFinalValue() {
            return this.f$1.apply(this.$outer.getFinalValue());
         }

         public synchronized PartialResult onComplete(final Function1 handler) {
            return this.$outer.onComplete(handler.compose(this.f$1)).map(this.f$1);
         }

         public synchronized void onFail(final Function1 handler) {
            this.$outer.onFail(handler);
         }

         public synchronized String toString() {
            Option var2 = this.$outer.org$apache$spark$partial$PartialResult$$getFinalValueInternal();
            if (var2 instanceof Some var3) {
               Object value = var3.value();
               Object var10000 = this.f$1.apply(value);
               return "(final: " + var10000 + ")";
            } else if (.MODULE$.equals(var2)) {
               return "(partial: " + this.initialValue() + ")";
            } else {
               throw new MatchError(var2);
            }
         }

         private Option getFinalValueInternal() {
            return this.$outer.org$apache$spark$partial$PartialResult$$getFinalValueInternal().map(this.f$1);
         }

         public {
            if (PartialResult.this == null) {
               throw null;
            } else {
               this.$outer = PartialResult.this;
               this.f$1 = f$1;
            }
         }
      };
   }

   public synchronized void setFinalValue(final Object value) {
      if (this.finalValue().isDefined()) {
         throw new UnsupportedOperationException("setFinalValue called twice on a PartialResult");
      } else {
         this.finalValue_$eq(new Some(value));
         this.completionHandler().foreach((h) -> {
            $anonfun$setFinalValue$1(value, h);
            return BoxedUnit.UNIT;
         });
         this.notifyAll();
      }
   }

   public Option org$apache$spark$partial$PartialResult$$getFinalValueInternal() {
      return this.finalValue();
   }

   public synchronized void setFailure(final Exception exception) {
      if (this.failure().isDefined()) {
         throw new UnsupportedOperationException("setFailure called twice on a PartialResult");
      } else {
         this.failure_$eq(new Some(exception));
         this.failureHandler().foreach((h) -> {
            $anonfun$setFailure$1(exception, h);
            return BoxedUnit.UNIT;
         });
         this.notifyAll();
      }
   }

   public synchronized String toString() {
      Option var2 = this.finalValue();
      if (var2 instanceof Some var3) {
         Object value = var3.value();
         return "(final: " + value + ")";
      } else if (.MODULE$.equals(var2)) {
         return "(partial: " + this.initialValue() + ")";
      } else {
         throw new MatchError(var2);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$setFinalValue$1(final Object value$1, final Function1 h) {
      h.apply(value$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$setFailure$1(final Exception exception$1, final Function1 h) {
      h.apply(exception$1);
   }

   public PartialResult(final Object initialVal, final boolean isFinal) {
      this.org$apache$spark$partial$PartialResult$$initialVal = initialVal;
      this.org$apache$spark$partial$PartialResult$$isFinal = isFinal;
      this.finalValue = (Option)(isFinal ? new Some(initialVal) : .MODULE$);
      this.failure = .MODULE$;
      this.completionHandler = .MODULE$;
      this.failureHandler = .MODULE$;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
