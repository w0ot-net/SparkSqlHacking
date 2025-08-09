package org.apache.spark.storage.memory;

import org.apache.spark.memory.MemoryMode;
import org.apache.spark.util.SizeEstimator$;
import org.apache.spark.util.collection.SizeTrackingVector;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-4A!\u0004\b\u00053!A\u0001\u0007\u0001B\u0001B\u0003%\u0011\u0007\u0003\u00058\u0001\t\u0005\t\u0015!\u00039\u0011\u0015i\u0004\u0001\"\u0001?\u0011\u001d\u0011\u0005\u00011A\u0005\u0002\rCq\u0001\u0014\u0001A\u0002\u0013\u0005Q\n\u0003\u0004T\u0001\u0001\u0006K\u0001\u0012\u0005\b)\u0002\u0001\r\u0011\"\u0001V\u0011\u001dI\u0006\u00011A\u0005\u0002iCa\u0001\u0018\u0001!B\u00131\u0006\"B/\u0001\t\u0003r\u0006\"B1\u0001\t\u0003\u0012\u0007\"\u00024\u0001\t\u0003:'\u0001\u0007#fg\u0016\u0014\u0018.\u00197ju\u0016$g+\u00197vKNDu\u000e\u001c3fe*\u0011q\u0002E\u0001\u0007[\u0016lwN]=\u000b\u0005E\u0011\u0012aB:u_J\fw-\u001a\u0006\u0003'Q\tQa\u001d9be.T!!\u0006\f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00059\u0012aA8sO\u000e\u0001QC\u0001\u000e('\r\u00011$\t\t\u00039}i\u0011!\b\u0006\u0002=\u0005)1oY1mC&\u0011\u0001%\b\u0002\u0007\u0003:L(+\u001a4\u0011\u0007\t\u001aS%D\u0001\u000f\u0013\t!cB\u0001\u0007WC2,Xm\u001d%pY\u0012,'\u000f\u0005\u0002'O1\u0001A!\u0002\u0015\u0001\u0005\u0004I#!\u0001+\u0012\u0005)j\u0003C\u0001\u000f,\u0013\taSDA\u0004O_RD\u0017N\\4\u0011\u0005qq\u0013BA\u0018\u001e\u0005\r\te._\u0001\tG2\f7o\u001d+bOB\u0019!'N\u0013\u000e\u0003MR!\u0001N\u000f\u0002\u000fI,g\r\\3di&\u0011ag\r\u0002\t\u00072\f7o\u001d+bO\u0006QQ.Z7peflu\u000eZ3\u0011\u0005eZT\"\u0001\u001e\u000b\u0005=\u0011\u0012B\u0001\u001f;\u0005)iU-\\8ss6{G-Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007}\u0002\u0015\tE\u0002#\u0001\u0015BQ\u0001M\u0002A\u0002EBQaN\u0002A\u0002a\naA^3di>\u0014X#\u0001#\u0011\u0007\u0015SU%D\u0001G\u0015\t9\u0005*\u0001\u0006d_2dWm\u0019;j_:T!!\u0013\n\u0002\tU$\u0018\u000e\\\u0005\u0003\u0017\u001a\u0013!cU5{KR\u0013\u0018mY6j]\u001e4Vm\u0019;pe\u0006Qa/Z2u_J|F%Z9\u0015\u00059\u000b\u0006C\u0001\u000fP\u0013\t\u0001VD\u0001\u0003V]&$\bb\u0002*\u0006\u0003\u0003\u0005\r\u0001R\u0001\u0004q\u0012\n\u0014a\u0002<fGR|'\u000fI\u0001\fCJ\u0014\u0018-\u001f,bYV,7/F\u0001W!\rar+J\u0005\u00031v\u0011Q!\u0011:sCf\fq\"\u0019:sCf4\u0016\r\\;fg~#S-\u001d\u000b\u0003\u001dnCqA\u0015\u0005\u0002\u0002\u0003\u0007a+\u0001\u0007beJ\f\u0017PV1mk\u0016\u001c\b%\u0001\u0006ti>\u0014XMV1mk\u0016$\"AT0\t\u000b\u0001T\u0001\u0019A\u0013\u0002\u000bY\fG.^3\u0002\u001b\u0015\u001cH/[7bi\u0016$7+\u001b>f)\u0005\u0019\u0007C\u0001\u000fe\u0013\t)WD\u0001\u0003M_:<\u0017AC4fi\n+\u0018\u000e\u001c3feR\t\u0001\u000eE\u0002#S\u0016J!A\u001b\b\u0003%5+Wn\u001c:z\u000b:$(/\u001f\"vS2$WM\u001d"
)
public class DeserializedValuesHolder implements ValuesHolder {
   public final ClassTag org$apache$spark$storage$memory$DeserializedValuesHolder$$classTag;
   public final MemoryMode org$apache$spark$storage$memory$DeserializedValuesHolder$$memoryMode;
   private SizeTrackingVector vector;
   private Object arrayValues;

   public SizeTrackingVector vector() {
      return this.vector;
   }

   public void vector_$eq(final SizeTrackingVector x$1) {
      this.vector = x$1;
   }

   public Object arrayValues() {
      return this.arrayValues;
   }

   public void arrayValues_$eq(final Object x$1) {
      this.arrayValues = x$1;
   }

   public void storeValue(final Object value) {
      this.vector().$plus$eq(value);
   }

   public long estimatedSize() {
      return this.vector().estimateSize();
   }

   public MemoryEntryBuilder getBuilder() {
      return new MemoryEntryBuilder() {
         private final long preciseSize;
         // $FF: synthetic field
         private final DeserializedValuesHolder $outer;

         public long preciseSize() {
            return this.preciseSize;
         }

         public MemoryEntry build() {
            return new DeserializedMemoryEntry(this.$outer.arrayValues(), this.preciseSize(), this.$outer.org$apache$spark$storage$memory$DeserializedValuesHolder$$memoryMode, this.$outer.org$apache$spark$storage$memory$DeserializedValuesHolder$$classTag);
         }

         public {
            if (DeserializedValuesHolder.this == null) {
               throw null;
            } else {
               this.$outer = DeserializedValuesHolder.this;
               DeserializedValuesHolder.this.arrayValues_$eq(DeserializedValuesHolder.this.vector().toArray());
               DeserializedValuesHolder.this.vector_$eq((SizeTrackingVector)null);
               this.preciseSize = SizeEstimator$.MODULE$.estimate(DeserializedValuesHolder.this.arrayValues());
            }
         }
      };
   }

   public DeserializedValuesHolder(final ClassTag classTag, final MemoryMode memoryMode) {
      this.org$apache$spark$storage$memory$DeserializedValuesHolder$$classTag = classTag;
      this.org$apache$spark$storage$memory$DeserializedValuesHolder$$memoryMode = memoryMode;
      this.vector = new SizeTrackingVector(classTag);
      this.arrayValues = null;
   }
}
