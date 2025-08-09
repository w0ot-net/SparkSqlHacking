package org.apache.spark.sql.catalyst.encoders;

import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructField$;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.StructType$;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3q\u0001C\u0005\u0011\u0002\u0007\u0005a\u0003C\u0003.\u0001\u0011\u0005a\u0006C\u00033\u0001\u0019\u00051\u0007C\u00038\u0001\u0011\u00051\u0007C\u00039\u0001\u0019\u0005\u0011\bC\u0003A\u0001\u0011\u0005\u0013\tC\u0003F\u0001\u0011\u00051\u0007C\u0003G\u0001\u0011\u00051GA\bBO:|7\u000f^5d\u000b:\u001cw\u000eZ3s\u0015\tQ1\"\u0001\u0005f]\u000e|G-\u001a:t\u0015\taQ\"\u0001\u0005dCR\fG._:u\u0015\tqq\"A\u0002tc2T!\u0001E\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005I\u0019\u0012AB1qC\u000eDWMC\u0001\u0015\u0003\ry'oZ\u0002\u0001+\t9BeE\u0002\u00011y\u0001\"!\u0007\u000f\u000e\u0003iQ\u0011aG\u0001\u0006g\u000e\fG.Y\u0005\u0003;i\u0011a!\u00118z%\u00164\u0007cA\u0010!E5\tQ\"\u0003\u0002\"\u001b\t9QI\\2pI\u0016\u0014\bCA\u0012%\u0019\u0001!Q!\n\u0001C\u0002\u0019\u0012\u0011\u0001V\t\u0003O)\u0002\"!\u0007\u0015\n\u0005%R\"a\u0002(pi\"Lgn\u001a\t\u00033-J!\u0001\f\u000e\u0003\u0007\u0005s\u00170\u0001\u0004%S:LG\u000f\n\u000b\u0002_A\u0011\u0011\u0004M\u0005\u0003ci\u0011A!\u00168ji\u0006Y\u0011n\u001d)sS6LG/\u001b<f+\u0005!\u0004CA\r6\u0013\t1$DA\u0004C_>dW-\u00198\u0002\u00119,H\u000e\\1cY\u0016\f\u0001\u0002Z1uCRK\b/Z\u000b\u0002uA\u00111HP\u0007\u0002y)\u0011Q(D\u0001\u0006if\u0004Xm]\u0005\u0003\u007fq\u0012\u0001\u0002R1uCRK\b/Z\u0001\u0007g\u000eDW-\\1\u0016\u0003\t\u0003\"aO\"\n\u0005\u0011c$AC*ueV\u001cG\u000fV=qK\u0006!B.\u001a8jK:$8+\u001a:jC2L'0\u0019;j_:\f\u0001\"[:TiJ,8\r\u001e"
)
public interface AgnosticEncoder extends Encoder {
   boolean isPrimitive();

   // $FF: synthetic method
   static boolean nullable$(final AgnosticEncoder $this) {
      return $this.nullable();
   }

   default boolean nullable() {
      return !this.isPrimitive();
   }

   DataType dataType();

   // $FF: synthetic method
   static StructType schema$(final AgnosticEncoder $this) {
      return $this.schema();
   }

   default StructType schema() {
      StructType$ var10000 = StructType$.MODULE$;
      StructField var1 = new StructField("value", this.dataType(), this.nullable(), StructField$.MODULE$.apply$default$4());
      return var10000.apply((Seq).MODULE$.$colon$colon(var1));
   }

   // $FF: synthetic method
   static boolean lenientSerialization$(final AgnosticEncoder $this) {
      return $this.lenientSerialization();
   }

   default boolean lenientSerialization() {
      return false;
   }

   // $FF: synthetic method
   static boolean isStruct$(final AgnosticEncoder $this) {
      return $this.isStruct();
   }

   default boolean isStruct() {
      return false;
   }

   static void $init$(final AgnosticEncoder $this) {
   }
}
