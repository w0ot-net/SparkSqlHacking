package org.apache.spark.sql.catalyst.util;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.errors.DataTypeErrors$;
import org.apache.spark.sql.internal.SqlApiConf$;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.CharType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.MapType;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StringType$;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.VarcharType;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005q2q\u0001C\u0005\u0011\u0002\u0007\u0005a\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0003#\u0001\u0011\u00051\u0005C\u00030\u0001\u0011\u0005\u0001\u0007C\u00033\u0001\u0011\u00051gB\u00036\u0013!\u0005aGB\u0003\t\u0013!\u0005\u0001\bC\u0003;\r\u0011\u00051HA\u000bTa\u0006\u00148n\u00115beZ\u000b'o\u00195beV#\u0018\u000e\\:\u000b\u0005)Y\u0011\u0001B;uS2T!\u0001D\u0007\u0002\u0011\r\fG/\u00197zgRT!AD\b\u0002\u0007M\fHN\u0003\u0002\u0011#\u0005)1\u000f]1sW*\u0011!cE\u0001\u0007CB\f7\r[3\u000b\u0003Q\t1a\u001c:h\u0007\u0001\u0019\"\u0001A\f\u0011\u0005aYR\"A\r\u000b\u0003i\tQa]2bY\u0006L!\u0001H\r\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\tq\u0004\u0005\u0002\u0019A%\u0011\u0011%\u0007\u0002\u0005+:LG/\u0001\biCN\u001c\u0005.\u0019:WCJ\u001c\u0007.\u0019:\u0015\u0005\u0011:\u0003C\u0001\r&\u0013\t1\u0013DA\u0004C_>dW-\u00198\t\u000b!\u0012\u0001\u0019A\u0015\u0002\u0005\u0011$\bC\u0001\u0016.\u001b\u0005Y#B\u0001\u0017\u000e\u0003\u0015!\u0018\u0010]3t\u0013\tq3F\u0001\u0005ECR\fG+\u001f9f\u0003Q1\u0017-\u001b7JM\"\u000b7o\u00115beZ\u000b'o\u00195beR\u0011\u0011&\r\u0005\u0006Q\r\u0001\r!K\u0001\u001de\u0016\u0004H.Y2f\u0007\"\f'OV1sG\"\f'oV5uQN#(/\u001b8h)\tIC\u0007C\u0003)\t\u0001\u0007\u0011&A\u000bTa\u0006\u00148n\u00115beZ\u000b'o\u00195beV#\u0018\u000e\\:\u0011\u0005]2Q\"A\u0005\u0014\u0007\u00199\u0012\b\u0005\u00028\u0001\u00051A(\u001b8jiz\"\u0012A\u000e"
)
public interface SparkCharVarcharUtils {
   // $FF: synthetic method
   static boolean hasCharVarchar$(final SparkCharVarcharUtils $this, final DataType dt) {
      return $this.hasCharVarchar(dt);
   }

   default boolean hasCharVarchar(final DataType dt) {
      return dt.existsRecursively((f) -> BoxesRunTime.boxToBoolean($anonfun$hasCharVarchar$1(f)));
   }

   // $FF: synthetic method
   static DataType failIfHasCharVarchar$(final SparkCharVarcharUtils $this, final DataType dt) {
      return $this.failIfHasCharVarchar(dt);
   }

   default DataType failIfHasCharVarchar(final DataType dt) {
      if (!SqlApiConf$.MODULE$.get().charVarcharAsString() && this.hasCharVarchar(dt)) {
         throw DataTypeErrors$.MODULE$.charOrVarcharTypeAsStringUnsupportedError();
      } else {
         return this.replaceCharVarcharWithString(dt);
      }
   }

   // $FF: synthetic method
   static DataType replaceCharVarcharWithString$(final SparkCharVarcharUtils $this, final DataType dt) {
      return $this.replaceCharVarcharWithString(dt);
   }

   default DataType replaceCharVarcharWithString(final DataType dt) {
      if (dt instanceof ArrayType var5) {
         DataType et = var5.elementType();
         boolean nullable = var5.containsNull();
         return new ArrayType(this.replaceCharVarcharWithString(et), nullable);
      } else if (dt instanceof MapType var8) {
         DataType kt = var8.keyType();
         DataType vt = var8.valueType();
         boolean nullable = var8.valueContainsNull();
         return new MapType(this.replaceCharVarcharWithString(kt), this.replaceCharVarcharWithString(vt), nullable);
      } else if (dt instanceof StructType var12) {
         StructField[] fields = var12.fields();
         return new StructType((StructField[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(fields), (field) -> {
            DataType x$1 = this.replaceCharVarcharWithString(field.dataType());
            String x$2 = field.copy$default$1();
            boolean x$3 = field.copy$default$3();
            Metadata x$4 = field.copy$default$4();
            return field.copy(x$2, x$1, x$3, x$4);
         }, scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
      } else {
         return (DataType)((dt instanceof CharType ? true : dt instanceof VarcharType) && !SqlApiConf$.MODULE$.get().preserveCharVarcharTypeInfo() ? StringType$.MODULE$ : dt);
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$hasCharVarchar$1(final DataType f) {
      return f instanceof CharType || f instanceof VarcharType;
   }

   static void $init$(final SparkCharVarcharUtils $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
