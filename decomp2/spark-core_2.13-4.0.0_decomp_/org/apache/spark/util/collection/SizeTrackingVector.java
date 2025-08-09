package org.apache.spark.util.collection;

import scala.collection.mutable.Queue;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t3Q!\u0002\u0004\u0001\u0015AA\u0001\u0002\u000b\u0001\u0003\u0004\u0003\u0006Y!\u000b\u0005\u0006_\u0001!\t\u0001\r\u0005\u0006i\u0001!\t%\u000e\u0005\u0006w\u0001!\t\u0005\u0010\u0002\u0013'&TX\r\u0016:bG.Lgn\u001a,fGR|'O\u0003\u0002\b\u0011\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0005%Q\u0011\u0001B;uS2T!a\u0003\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00055q\u0011AB1qC\u000eDWMC\u0001\u0010\u0003\ry'oZ\u000b\u0003#a\u00192\u0001\u0001\n&!\r\u0019BCF\u0007\u0002\r%\u0011QC\u0002\u0002\u0010!JLW.\u001b;jm\u00164Vm\u0019;peB\u0011q\u0003\u0007\u0007\u0001\t\u0015I\u0002A1\u0001\u001c\u0005\u0005!6\u0001A\t\u00039\t\u0002\"!\b\u0011\u000e\u0003yQ\u0011aH\u0001\u0006g\u000e\fG.Y\u0005\u0003Cy\u0011qAT8uQ&tw\r\u0005\u0002\u001eG%\u0011AE\b\u0002\u0004\u0003:L\bCA\n'\u0013\t9cAA\u0006TSj,GK]1dW\u0016\u0014\u0018AC3wS\u0012,gnY3%cA\u0019!&\f\f\u000e\u0003-R!\u0001\f\u0010\u0002\u000fI,g\r\\3di&\u0011af\u000b\u0002\t\u00072\f7o\u001d+bO\u00061A(\u001b8jiz\"\u0012!\r\u000b\u0003eM\u00022a\u0005\u0001\u0017\u0011\u0015A#\u0001q\u0001*\u0003!!\u0003\u000f\\;tI\u0015\fHC\u0001\u001c:!\tir'\u0003\u00029=\t!QK\\5u\u0011\u0015Q4\u00011\u0001\u0017\u0003\u00151\u0018\r\\;f\u0003\u0019\u0011Xm]5{KR\u0011!#\u0010\u0005\u0006}\u0011\u0001\raP\u0001\n]\u0016<H*\u001a8hi\"\u0004\"!\b!\n\u0005\u0005s\"aA%oi\u0002"
)
public class SizeTrackingVector extends PrimitiveVector implements SizeTracker {
   private double org$apache$spark$util$collection$SizeTracker$$SAMPLE_GROWTH_RATE;
   private Queue org$apache$spark$util$collection$SizeTracker$$samples;
   private double org$apache$spark$util$collection$SizeTracker$$bytesPerUpdate;
   private long org$apache$spark$util$collection$SizeTracker$$numUpdates;
   private long org$apache$spark$util$collection$SizeTracker$$nextSampleNum;

   public void resetSamples() {
      SizeTracker.resetSamples$(this);
   }

   public void afterUpdate() {
      SizeTracker.afterUpdate$(this);
   }

   public long estimateSize() {
      return SizeTracker.estimateSize$(this);
   }

   public double org$apache$spark$util$collection$SizeTracker$$SAMPLE_GROWTH_RATE() {
      return this.org$apache$spark$util$collection$SizeTracker$$SAMPLE_GROWTH_RATE;
   }

   public Queue org$apache$spark$util$collection$SizeTracker$$samples() {
      return this.org$apache$spark$util$collection$SizeTracker$$samples;
   }

   public double org$apache$spark$util$collection$SizeTracker$$bytesPerUpdate() {
      return this.org$apache$spark$util$collection$SizeTracker$$bytesPerUpdate;
   }

   public void org$apache$spark$util$collection$SizeTracker$$bytesPerUpdate_$eq(final double x$1) {
      this.org$apache$spark$util$collection$SizeTracker$$bytesPerUpdate = x$1;
   }

   public long org$apache$spark$util$collection$SizeTracker$$numUpdates() {
      return this.org$apache$spark$util$collection$SizeTracker$$numUpdates;
   }

   public void org$apache$spark$util$collection$SizeTracker$$numUpdates_$eq(final long x$1) {
      this.org$apache$spark$util$collection$SizeTracker$$numUpdates = x$1;
   }

   public long org$apache$spark$util$collection$SizeTracker$$nextSampleNum() {
      return this.org$apache$spark$util$collection$SizeTracker$$nextSampleNum;
   }

   public void org$apache$spark$util$collection$SizeTracker$$nextSampleNum_$eq(final long x$1) {
      this.org$apache$spark$util$collection$SizeTracker$$nextSampleNum = x$1;
   }

   public final void org$apache$spark$util$collection$SizeTracker$_setter_$org$apache$spark$util$collection$SizeTracker$$SAMPLE_GROWTH_RATE_$eq(final double x$1) {
      this.org$apache$spark$util$collection$SizeTracker$$SAMPLE_GROWTH_RATE = x$1;
   }

   public final void org$apache$spark$util$collection$SizeTracker$_setter_$org$apache$spark$util$collection$SizeTracker$$samples_$eq(final Queue x$1) {
      this.org$apache$spark$util$collection$SizeTracker$$samples = x$1;
   }

   public void $plus$eq(final Object value) {
      super.$plus$eq(value);
      SizeTracker.afterUpdate$(this);
   }

   public PrimitiveVector resize(final int newLength) {
      super.resize(newLength);
      this.resetSamples();
      return this;
   }

   public SizeTrackingVector(final ClassTag evidence$1) {
      super(PrimitiveVector$.MODULE$.$lessinit$greater$default$1(), evidence$1);
      SizeTracker.$init$(this);
      Statics.releaseFence();
   }
}
