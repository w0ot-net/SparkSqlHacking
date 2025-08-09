package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.List;
import scala.Option.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005]3AAD\b\u00011!)1\u0007\u0001C\u0001i!Ia\u0007\u0001a\u0001\u0002\u0004%Ia\u000e\u0005\nq\u0001\u0001\r\u00111A\u0005\neB\u0011b\u0010\u0001A\u0002\u0003\u0005\u000b\u0015\u0002\u0017\t\u000b\u0001\u0003A\u0011B\u001c\t\u000b\u0005\u0003A\u0011\t\"\t\u000b\u0019\u0003A\u0011\t\u001b\t\u000b\u001d\u0003A\u0011\t\u001b\t\u000b!\u0003A\u0011I%\t\u000b)\u0003A\u0011I&\t\u000b9\u0003A\u0011I(\t\u000bI\u0003A\u0011I\u001c\t\rM\u0003A\u0011A\tU\u0005U\u0019u\u000e\u001c7fGRLwN\\!dGVlW\u000f\\1u_JT!\u0001E\t\u0002\tU$\u0018\u000e\u001c\u0006\u0003%M\tQa\u001d9be.T!\u0001F\u000b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00051\u0012aA8sO\u000e\u0001QCA\r!'\t\u0001!\u0004\u0005\u0003\u001c9yaS\"A\b\n\u0005uy!!D!dGVlW\u000f\\1u_J4&\u0007\u0005\u0002 A1\u0001A!B\u0011\u0001\u0005\u0004\u0011#!\u0001+\u0012\u0005\rJ\u0003C\u0001\u0013(\u001b\u0005)#\"\u0001\u0014\u0002\u000bM\u001c\u0017\r\\1\n\u0005!*#a\u0002(pi\"Lgn\u001a\t\u0003I)J!aK\u0013\u0003\u0007\u0005s\u0017\u0010E\u0002.cyi\u0011A\f\u0006\u0003!=R\u0011\u0001M\u0001\u0005U\u00064\u0018-\u0003\u00023]\t!A*[:u\u0003\u0019a\u0014N\\5u}Q\tQ\u0007E\u0002\u001c\u0001y\tQa\u00187jgR,\u0012\u0001L\u0001\n?2L7\u000f^0%KF$\"AO\u001f\u0011\u0005\u0011Z\u0014B\u0001\u001f&\u0005\u0011)f.\u001b;\t\u000fy\u001a\u0011\u0011!a\u0001Y\u0005\u0019\u0001\u0010J\u0019\u0002\r}c\u0017n\u001d;!\u0003-9W\r^(s\u0007J,\u0017\r^3\u0002\r%\u001c(,\u001a:p+\u0005\u0019\u0005C\u0001\u0013E\u0013\t)UEA\u0004C_>dW-\u00198\u0002\u0019\r|\u0007/_!oIJ+7/\u001a;\u0002\t\r|\u0007/_\u0001\u0006e\u0016\u001cX\r\u001e\u000b\u0002u\u0005\u0019\u0011\r\u001a3\u0015\u0005ib\u0005\"B'\u000b\u0001\u0004q\u0012!\u0001<\u0002\u000b5,'oZ3\u0015\u0005i\u0002\u0006\"B)\f\u0001\u0004Q\u0012!B8uQ\u0016\u0014\u0018!\u0002<bYV,\u0017\u0001C:fiZ\u000bG.^3\u0015\u0005i*\u0006\"\u0002,\u000e\u0001\u0004a\u0013\u0001\u00038foZ\u000bG.^3"
)
public class CollectionAccumulator extends AccumulatorV2 {
   private List _list;

   private List _list() {
      return this._list;
   }

   private void _list_$eq(final List x$1) {
      this._list = x$1;
   }

   private List getOrCreate() {
      this._list_$eq((List).MODULE$.apply(this._list()).getOrElse(() -> new ArrayList()));
      return this._list();
   }

   public synchronized boolean isZero() {
      return this.getOrCreate().isEmpty();
   }

   public CollectionAccumulator copyAndReset() {
      return new CollectionAccumulator();
   }

   public CollectionAccumulator copy() {
      CollectionAccumulator newAcc = new CollectionAccumulator();
      synchronized(this){}

      try {
         newAcc.getOrCreate().addAll(this.getOrCreate());
      } catch (Throwable var4) {
         throw var4;
      }

      return newAcc;
   }

   public synchronized void reset() {
      this._list_$eq((List)null);
   }

   public void add(final Object v) {
      synchronized(this){}

      try {
         this.getOrCreate().add(v);
      } catch (Throwable var4) {
         throw var4;
      }

   }

   public void merge(final AccumulatorV2 other) {
      if (other instanceof CollectionAccumulator var4) {
         synchronized(this){}

         try {
            this.getOrCreate().addAll(var4.value());
         } catch (Throwable var7) {
            throw var7;
         }

         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         String var10002 = this.getClass().getName();
         throw new UnsupportedOperationException("Cannot merge " + var10002 + " with " + other.getClass().getName());
      }
   }

   public synchronized List value() {
      return List.copyOf(this.getOrCreate());
   }

   public void setValue(final List newValue) {
      synchronized(this){}

      try {
         this._list_$eq((List)null);
         this.getOrCreate().addAll(newValue);
      } catch (Throwable var4) {
         throw var4;
      }

   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
