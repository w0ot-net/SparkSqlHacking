package org.apache.spark.sql.hive;

import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorUtils;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.types.DataType;
import scala.Function1;
import scala.MatchError;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mc\u0001\u0002\u0007\u000e\u0001aA\u0001B\u000b\u0001\u0003\u0002\u0003\u0006Ia\u000b\u0005\t\u007f\u0001\u0011\t\u0011)A\u0005\u0001\")1\u000b\u0001C\u0001)\"A\u0001\f\u0001EC\u0002\u0013%\u0011\f\u0003\u0005k\u0001!\u0015\r\u0011\"\u0001l\u0011!i\u0007\u0001#b\u0001\n\u0013q\u0007BCA\u0003\u0001!\u0015\r\u0011\"\u0003\u0002\b!9\u0011q\u0003\u0001\u0005B\u0005e\u0001bBA\u0014\u0001\u0011\u0005\u0011\u0011\u0006\u0005\b\u0003\u007f\u0001A\u0011AA!\u0011\u001d\ty\u0005\u0001C!\u0003#\u0012q\u0003S5wK\u001e+g.\u001a:jGV#e)\u0012<bYV\fGo\u001c:\u000b\u00059y\u0011\u0001\u00025jm\u0016T!\u0001E\t\u0002\u0007M\fHN\u0003\u0002\u0013'\u0005)1\u000f]1sW*\u0011A#F\u0001\u0007CB\f7\r[3\u000b\u0003Y\t1a\u001c:h\u0007\u0001\u0019\"\u0001A\r\u0011\u0007iYR$D\u0001\u000e\u0013\taRB\u0001\u000bISZ,W\u000b\u0012$Fm\u0006dW/\u0019;pe\n\u000b7/\u001a\t\u0003=!j\u0011a\b\u0006\u0003A\u0005\nqaZ3oKJL7M\u0003\u0002#G\u0005\u0019Q\u000f\u001a4\u000b\u0005\u0011*\u0013AA9m\u0015\tqaE\u0003\u0002('\u00051\u0001.\u00193p_BL!!K\u0010\u0003\u0015\u001d+g.\u001a:jGV#e)A\u0006gk:\u001cwK]1qa\u0016\u0014\bC\u0001\u0017=\u001d\ti#H\u0004\u0002/s9\u0011q\u0006\u000f\b\u0003a]r!!\r\u001c\u000f\u0005I*T\"A\u001a\u000b\u0005Q:\u0012A\u0002\u001fs_>$h(C\u0001\u0017\u0013\t!R#\u0003\u0002\u0013'%\u0011\u0001#E\u0005\u0003\u001d=I!aO\u0007\u0002\u0011!Kg/Z*iS6L!!\u0010 \u0003'!Kg/\u001a$v]\u000e$\u0018n\u001c8Xe\u0006\u0004\b/\u001a:\u000b\u0005mj\u0011\u0001C2iS2$'/\u001a8\u0011\u0007\u0005C5J\u0004\u0002C\u000b:\u0011!gQ\u0005\u0002\t\u0006)1oY1mC&\u0011aiR\u0001\ba\u0006\u001c7.Y4f\u0015\u0005!\u0015BA%K\u0005\r\u0019V-\u001d\u0006\u0003\r\u001e\u0003\"\u0001T)\u000e\u00035S!AT(\u0002\u0017\u0015D\bO]3tg&|gn\u001d\u0006\u0003!>\t\u0001bY1uC2L8\u000f^\u0005\u0003%6\u0013!\"\u0012=qe\u0016\u001c8/[8o\u0003\u0019a\u0014N\\5u}Q\u0019QKV,\u0011\u0005i\u0001\u0001\"\u0002\u0016\u0004\u0001\u0004Y\u0003\"B \u0004\u0001\u0004\u0001\u0015AE1sOVlWM\u001c;J]N\u0004Xm\u0019;peN,\u0012A\u0017\t\u00047rsV\"A$\n\u0005u;%!B!se\u0006L\bCA0e\u001b\u0005\u0001'BA1c\u0003=y'M[3di&t7\u000f]3di>\u0014(BA2&\u0003\u0019\u0019XM\u001d3fe%\u0011Q\r\u0019\u0002\u0010\u001f\nTWm\u0019;J]N\u0004Xm\u0019;pe\"\u0012Aa\u001a\t\u00037\"L!![$\u0003\u0013Q\u0014\u0018M\\:jK:$\u0018a\u0004:fiV\u0014h.\u00138ta\u0016\u001cGo\u001c:\u0016\u0003yC#!B4\u0002\u001f\u0011,g-\u001a:sK\u0012|%M[3diN,\u0012a\u001c\t\u00047r\u0003\bCA9\u007f\u001d\t\u0011HP\u0004\u0002tw:\u0011AO\u001f\b\u0003kft!A\u001e=\u000f\u0005A:\u0018BA\u0014\u0014\u0013\tqa%\u0003\u0002%K%\u0011!eI\u0005\u0003A\u0005J!!`\u0010\u0002\u0015\u001d+g.\u001a:jGV#e)C\u0002\u0000\u0003\u0003\u0011a\u0002R3gKJ\u0014X\rZ(cU\u0016\u001cGO\u0003\u0002~?!\u0012aaZ\u0001\nk:<(/\u00199qKJ,\"!!\u0003\u0011\u000fm\u000bY!a\u0004\u0002\u0010%\u0019\u0011QB$\u0003\u0013\u0019+hn\u0019;j_:\f\u0004cA.\u0002\u0012%\u0019\u00111C$\u0003\u0007\u0005s\u0017\u0010\u000b\u0002\bO\u0006Q!/\u001a;ve:$\u0016\u0010]3\u0016\u0005\u0005m\u0001\u0003BA\u000f\u0003Gi!!a\b\u000b\u0007\u0005\u0005r\"A\u0003usB,7/\u0003\u0003\u0002&\u0005}!\u0001\u0003#bi\u0006$\u0016\u0010]3\u0002\rM,G/\u0011:h)\u0019\tY#!\r\u0002<A\u00191,!\f\n\u0007\u0005=rI\u0001\u0003V]&$\bbBA\u001a\u0013\u0001\u0007\u0011QG\u0001\u0006S:$W\r\u001f\t\u00047\u0006]\u0012bAA\u001d\u000f\n\u0019\u0011J\u001c;\t\u000f\u0005u\u0012\u00021\u0001\u0002\u0010\u0005\u0019\u0011M]4\u0002\u0019M,G/\u0012=dKB$\u0018n\u001c8\u0015\r\u0005-\u00121IA#\u0011\u001d\t\u0019D\u0003a\u0001\u0003kAq!a\u0012\u000b\u0001\u0004\tI%A\u0002fqB\u00042!QA&\u0013\r\tiE\u0013\u0002\n)\"\u0014xn^1cY\u0016\f!\u0002Z8Fm\u0006dW/\u0019;f)\t\ty\u0001"
)
public class HiveGenericUDFEvaluator extends HiveUDFEvaluatorBase {
   private transient ObjectInspector[] argumentInspectors;
   private transient ObjectInspector returnInspector;
   private transient GenericUDF.DeferredObject[] deferredObjects;
   private transient Function1 unwrapper;
   private final Seq children;
   private transient volatile byte bitmap$trans$0;

   private ObjectInspector[] argumentInspectors$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 1) == 0) {
            this.argumentInspectors = (ObjectInspector[])((IterableOnceOps)this.children.map((expr) -> this.toInspector(expr))).toArray(.MODULE$.apply(ObjectInspector.class));
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.argumentInspectors;
   }

   private ObjectInspector[] argumentInspectors() {
      return (byte)(this.bitmap$trans$0 & 1) == 0 ? this.argumentInspectors$lzycompute() : this.argumentInspectors;
   }

   private ObjectInspector returnInspector$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 2) == 0) {
            ObjectInspector oi = ((GenericUDF)this.function()).initialize(this.argumentInspectors());
            Object var10001;
            if (((GenericUDF)this.function()).getRequiredFiles() == null && ((GenericUDF)this.function()).getRequiredJars() == null && scala.collection.ArrayOps..MODULE$.forall$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.argumentInspectors()), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$returnInspector$1(x$1))) && !ObjectInspectorUtils.isConstantObjectInspector(oi) && this.isUDFDeterministic() && ObjectInspectorUtils.supportsConstantObjectInspector(oi)) {
               GenericUDF.DeferredObject[] argumentValues = (GenericUDF.DeferredObject[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.argumentInspectors()), (argumentInspector) -> new GenericUDF.DeferredJavaObject(((ConstantObjectInspector)argumentInspector).getWritableConstantValue()), .MODULE$.apply(GenericUDF.DeferredObject.class));
               var10001 = this.liftedTree1$1(argumentValues, oi);
            } else {
               var10001 = oi;
            }

            this.returnInspector = (ObjectInspector)var10001;
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 2);
         }
      } catch (Throwable var5) {
         throw var5;
      }

      return this.returnInspector;
   }

   public ObjectInspector returnInspector() {
      return (byte)(this.bitmap$trans$0 & 2) == 0 ? this.returnInspector$lzycompute() : this.returnInspector;
   }

   private GenericUDF.DeferredObject[] deferredObjects$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 4) == 0) {
            this.deferredObjects = (GenericUDF.DeferredObject[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.argumentInspectors()), this.children)), (x0$1) -> {
               if (x0$1 != null) {
                  ObjectInspector inspect = (ObjectInspector)x0$1._1();
                  Expression child = (Expression)x0$1._2();
                  return new DeferredObjectAdapter(inspect, child.dataType());
               } else {
                  throw new MatchError(x0$1);
               }
            }, .MODULE$.apply(GenericUDF.DeferredObject.class));
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.deferredObjects;
   }

   private GenericUDF.DeferredObject[] deferredObjects() {
      return (byte)(this.bitmap$trans$0 & 4) == 0 ? this.deferredObjects$lzycompute() : this.deferredObjects;
   }

   private Function1 unwrapper$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 8) == 0) {
            this.unwrapper = this.unwrapperFor(this.returnInspector());
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.unwrapper;
   }

   private Function1 unwrapper() {
      return (byte)(this.bitmap$trans$0 & 8) == 0 ? this.unwrapper$lzycompute() : this.unwrapper;
   }

   public DataType returnType() {
      return this.inspectorToDataType(this.returnInspector());
   }

   public void setArg(final int index, final Object arg) {
      ((DeferredObjectAdapter)this.deferredObjects()[index]).set(() -> arg);
   }

   public void setException(final int index, final Throwable exp) {
      ((DeferredObjectAdapter)this.deferredObjects()[index]).set(() -> {
         throw exp;
      });
   }

   public Object doEvaluate() {
      return this.unwrapper().apply(((GenericUDF)this.function()).evaluate(this.deferredObjects()));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$returnInspector$1(final ObjectInspector x$1) {
      return ObjectInspectorUtils.isConstantObjectInspector(x$1);
   }

   // $FF: synthetic method
   private final ConstantObjectInspector liftedTree1$1(final GenericUDF.DeferredObject[] argumentValues$1, final ObjectInspector oi$1) {
      try {
         Object constantValue = ((GenericUDF)this.function()).evaluate(argumentValues$1);
         return ObjectInspectorUtils.getConstantObjectInspector(oi$1, constantValue);
      } catch (HiveException var5) {
         throw new UDFArgumentException(var5);
      }
   }

   public HiveGenericUDFEvaluator(final HiveShim.HiveFunctionWrapper funcWrapper, final Seq children) {
      super(funcWrapper, children);
      this.children = children;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
