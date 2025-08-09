package org.apache.spark.api.python;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
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
import org.apache.spark.SparkException;
import scala.MatchError;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A2Q\u0001B\u0003\u0001\u000b=AQ!\n\u0001\u0005\u0002\u001dBQ!\u000b\u0001\u0005\n)BQ!\f\u0001\u0005B9\u0012qCS1wCR{wK]5uC\ndWmQ8om\u0016\u0014H/\u001a:\u000b\u0005\u00199\u0011A\u00029zi\"|gN\u0003\u0002\t\u0013\u0005\u0019\u0011\r]5\u000b\u0005)Y\u0011!B:qCJ\\'B\u0001\u0007\u000e\u0003\u0019\t\u0007/Y2iK*\ta\"A\u0002pe\u001e\u001c2\u0001\u0001\t\u0017!\t\tB#D\u0001\u0013\u0015\u0005\u0019\u0012!B:dC2\f\u0017BA\u000b\u0013\u0005\u0019\te.\u001f*fMB!q\u0003\u0007\u000e\u001e\u001b\u0005)\u0011BA\r\u0006\u0005%\u0019uN\u001c<feR,'\u000f\u0005\u0002\u00127%\u0011AD\u0005\u0002\u0004\u0003:L\bC\u0001\u0010$\u001b\u0005y\"B\u0001\u0011\"\u0003\tIwN\u0003\u0002#\u0017\u00051\u0001.\u00193p_BL!\u0001J\u0010\u0003\u0011]\u0013\u0018\u000e^1cY\u0016\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002QA\u0011q\u0003A\u0001\u0012G>tg/\u001a:u)><&/\u001b;bE2,GCA\u000f,\u0011\u0015a#\u00011\u0001\u001b\u0003\ry'M[\u0001\bG>tg/\u001a:u)\tir\u0006C\u0003-\u0007\u0001\u0007!\u0004"
)
public class JavaToWritableConverter implements Converter {
   private Writable convertToWritable(final Object obj) {
      if (obj instanceof Integer var4) {
         return new IntWritable(.MODULE$.Integer2int(var4));
      } else if (obj instanceof Double var5) {
         return new DoubleWritable(.MODULE$.Double2double(var5));
      } else if (obj instanceof Long var6) {
         return new LongWritable(.MODULE$.Long2long(var6));
      } else if (obj instanceof Short var7) {
         return new ShortWritable(.MODULE$.Short2short(var7));
      } else if (obj instanceof Float var8) {
         return new FloatWritable(.MODULE$.Float2float(var8));
      } else if (obj instanceof String var9) {
         return new Text(var9);
      } else if (obj instanceof Boolean var10) {
         return new BooleanWritable(.MODULE$.Boolean2boolean(var10));
      } else if (obj instanceof Byte var11) {
         return new ByteWritable(.MODULE$.Byte2byte(var11));
      } else if (obj instanceof byte[] var12) {
         return new BytesWritable(var12);
      } else if (obj == null) {
         return NullWritable.get();
      } else if (obj instanceof Map) {
         Map var13 = (Map)obj;
         MapWritable mapWritable = new MapWritable();
         scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(var13).asScala().foreach((x0$1) -> {
            if (x0$1 != null) {
               Object k = x0$1._1();
               Object v = x0$1._2();
               return mapWritable.put(this.convertToWritable(k), this.convertToWritable(v));
            } else {
               throw new MatchError(x0$1);
            }
         });
         return mapWritable;
      } else if (obj instanceof Object[]) {
         Object[] var15 = obj;
         ArrayWritable arrayWriteable = new ArrayWritable(Writable.class);
         arrayWriteable.set((Writable[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.genericArrayOps(var15), (x$2) -> this.convertToWritable(x$2), scala.reflect.ClassTag..MODULE$.apply(Writable.class)));
         return arrayWriteable;
      } else {
         throw new SparkException("Data of type " + obj.getClass().getName() + " cannot be used");
      }
   }

   public Writable convert(final Object obj) {
      if (obj instanceof Writable var4) {
         return var4;
      } else {
         return this.convertToWritable(obj);
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
