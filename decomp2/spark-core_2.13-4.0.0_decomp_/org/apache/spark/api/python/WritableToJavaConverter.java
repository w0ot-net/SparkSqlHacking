package org.apache.spark.api.python;

import java.lang.invoke.SerializedLambda;
import java.util.HashMap;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.ByteWritable;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.ShortWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.util.SerializableConfiguration;
import scala.MatchError;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013Q!\u0002\u0004\u0001\rAA\u0001B\b\u0001\u0003\u0002\u0003\u0006I\u0001\t\u0005\u0006Y\u0001!\t!\f\u0005\u0006a\u0001!I!\r\u0005\u0006y\u0001!\t%\u0010\u0002\u0018/JLG/\u00192mKR{'*\u0019<b\u0007>tg/\u001a:uKJT!a\u0002\u0005\u0002\rALH\u000f[8o\u0015\tI!\"A\u0002ba&T!a\u0003\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00055q\u0011AB1qC\u000eDWMC\u0001\u0010\u0003\ry'oZ\n\u0004\u0001E9\u0002C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"AB!osJ+g\r\u0005\u0003\u00193mYR\"\u0001\u0004\n\u0005i1!!C\"p]Z,'\u000f^3s!\t\u0011B$\u0003\u0002\u001e'\t\u0019\u0011I\\=\u0002\t\r|gNZ\u0002\u0001!\r\tCEJ\u0007\u0002E)\u00111EC\u0001\nEJ|\u0017\rZ2bgRL!!\n\u0012\u0003\u0013\t\u0013x.\u00193dCN$\bCA\u0014+\u001b\u0005A#BA\u0015\u000b\u0003\u0011)H/\u001b7\n\u0005-B#!G*fe&\fG.\u001b>bE2,7i\u001c8gS\u001e,(/\u0019;j_:\fa\u0001P5oSRtDC\u0001\u00180!\tA\u0002\u0001C\u0003\u001f\u0005\u0001\u0007\u0001%A\bd_:4XM\u001d;Xe&$\u0018M\u00197f)\tY\"\u0007C\u00034\u0007\u0001\u0007A'\u0001\u0005xe&$\u0018M\u00197f!\t)$(D\u00017\u0015\t9\u0004(\u0001\u0002j_*\u0011\u0011\bD\u0001\u0007Q\u0006$wn\u001c9\n\u0005m2$\u0001C,sSR\f'\r\\3\u0002\u000f\r|gN^3siR\u00111D\u0010\u0005\u0006\u007f\u0011\u0001\raG\u0001\u0004_\nT\u0007"
)
public class WritableToJavaConverter implements Converter {
   private final Broadcast conf;

   private Object convertWritable(final Writable writable) {
      if (writable instanceof IntWritable var4) {
         return BoxesRunTime.boxToInteger(var4.get());
      } else if (writable instanceof DoubleWritable var5) {
         return BoxesRunTime.boxToDouble(var5.get());
      } else if (writable instanceof LongWritable var6) {
         return BoxesRunTime.boxToLong(var6.get());
      } else if (writable instanceof ShortWritable var7) {
         return BoxesRunTime.boxToShort(var7.get());
      } else if (writable instanceof FloatWritable var8) {
         return BoxesRunTime.boxToFloat(var8.get());
      } else if (writable instanceof Text var9) {
         return var9.toString();
      } else if (writable instanceof BooleanWritable var10) {
         return BoxesRunTime.boxToBoolean(var10.get());
      } else if (writable instanceof ByteWritable var11) {
         return BoxesRunTime.boxToByte(var11.get());
      } else if (writable instanceof BytesWritable var12) {
         byte[] bytes = new byte[var12.getLength()];
         System.arraycopy(var12.getBytes(), 0, bytes, 0, var12.getLength());
         return bytes;
      } else if (writable instanceof NullWritable) {
         return null;
      } else if (writable instanceof ArrayWritable) {
         ArrayWritable var14 = (ArrayWritable)writable;
         return .MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])var14.get()), (x$1) -> this.convertWritable(x$1), scala.reflect.ClassTag..MODULE$.Any());
      } else if (writable instanceof MapWritable) {
         MapWritable var15 = (MapWritable)writable;
         HashMap map = new HashMap();
         scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(var15).asScala().foreach((x0$1) -> {
            if (x0$1 != null) {
               Writable k = (Writable)x0$1._1();
               Writable v = (Writable)x0$1._2();
               return map.put(this.convertWritable(k), this.convertWritable(v));
            } else {
               throw new MatchError(x0$1);
            }
         });
         return map;
      } else {
         return writable != null ? WritableUtils.clone(writable, ((SerializableConfiguration)this.conf.value()).value()) : writable;
      }
   }

   public Object convert(final Object obj) {
      if (obj instanceof Writable var4) {
         return this.convertWritable(var4);
      } else {
         return obj;
      }
   }

   public WritableToJavaConverter(final Broadcast conf) {
      this.conf = conf;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
