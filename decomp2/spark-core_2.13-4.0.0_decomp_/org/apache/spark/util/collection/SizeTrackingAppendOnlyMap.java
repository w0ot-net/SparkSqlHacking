package org.apache.spark.util.collection;

import scala.Function2;
import scala.collection.mutable.Queue;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u00113Q!\u0002\u0004\u0001\u0015AAQa\u000b\u0001\u0005\u00021BQA\f\u0001\u0005B=BQa\u000e\u0001\u0005BaBQA\u0011\u0001\u0005R\r\u0013\u0011dU5{KR\u0013\u0018mY6j]\u001e\f\u0005\u000f]3oI>sG._'ba*\u0011q\u0001C\u0001\u000bG>dG.Z2uS>t'BA\u0005\u000b\u0003\u0011)H/\u001b7\u000b\u0005-a\u0011!B:qCJ\\'BA\u0007\u000f\u0003\u0019\t\u0007/Y2iK*\tq\"A\u0002pe\u001e,2!\u0005\r''\r\u0001!\u0003\u000b\t\u0005'Q1R%D\u0001\u0007\u0013\t)bAA\u0007BaB,g\u000eZ(oYfl\u0015\r\u001d\t\u0003/aa\u0001\u0001B\u0003\u001a\u0001\t\u00071DA\u0001L\u0007\u0001\t\"\u0001\b\u0012\u0011\u0005u\u0001S\"\u0001\u0010\u000b\u0003}\tQa]2bY\u0006L!!\t\u0010\u0003\u000f9{G\u000f[5oOB\u0011QdI\u0005\u0003Iy\u00111!\u00118z!\t9b\u0005B\u0003(\u0001\t\u00071DA\u0001W!\t\u0019\u0012&\u0003\u0002+\r\tY1+\u001b>f)J\f7m[3s\u0003\u0019a\u0014N\\5u}Q\tQ\u0006\u0005\u0003\u0014\u0001Y)\u0013AB;qI\u0006$X\rF\u00021gU\u0002\"!H\u0019\n\u0005Ir\"\u0001B+oSRDQ\u0001\u000e\u0002A\u0002Y\t1a[3z\u0011\u00151$\u00011\u0001&\u0003\u00151\u0018\r\\;f\u0003-\u0019\u0007.\u00198hKZ\u000bG.^3\u0015\u0007\u0015J$\bC\u00035\u0007\u0001\u0007a\u0003C\u0003<\u0007\u0001\u0007A(\u0001\u0006va\u0012\fG/\u001a$v]\u000e\u0004R!H\u001f@K\u0015J!A\u0010\u0010\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004CA\u000fA\u0013\t\teDA\u0004C_>dW-\u00198\u0002\u0013\u001d\u0014xn\u001e+bE2,G#\u0001\u0019"
)
public class SizeTrackingAppendOnlyMap extends AppendOnlyMap implements SizeTracker {
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

   public void update(final Object key, final Object value) {
      super.update(key, value);
      SizeTracker.afterUpdate$(this);
   }

   public Object changeValue(final Object key, final Function2 updateFunc) {
      Object newValue = super.changeValue(key, updateFunc);
      SizeTracker.afterUpdate$(this);
      return newValue;
   }

   public void growTable() {
      super.growTable();
      this.resetSamples();
   }

   public SizeTrackingAppendOnlyMap() {
      super(AppendOnlyMap$.MODULE$.$lessinit$greater$default$1());
      SizeTracker.$init$(this);
      Statics.releaseFence();
   }
}
