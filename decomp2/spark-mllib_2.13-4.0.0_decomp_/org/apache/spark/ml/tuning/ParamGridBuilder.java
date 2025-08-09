package org.apache.spark.ml.tuning;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.FloatParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamPair;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.ObjectRef;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ud\u0001B\u0007\u000f\u0001eAQ\u0001\t\u0001\u0005\u0002\u0005Bq!\f\u0001C\u0002\u0013%a\u0006\u0003\u0004C\u0001\u0001\u0006Ia\f\u0005\u0006%\u0002!\ta\u0015\u0005\u0006%\u0002!\ta\u0017\u0005\u0006_\u0002!\t\u0001\u001d\u0005\u0007_\u0002!\t!a\u0003\t\r=\u0004A\u0011AA\u0013\u0011\u0019y\u0007\u0001\"\u0001\u0002<!1q\u000e\u0001C\u0001\u0003#Baa\u001c\u0001\u0005\u0002\u0005\u001d\u0004bBA:\u0001\u0011\u0005\u0011Q\u000f\u0002\u0011!\u0006\u0014\u0018-\\$sS\u0012\u0014U/\u001b7eKJT!a\u0004\t\u0002\rQ,h.\u001b8h\u0015\t\t\"#\u0001\u0002nY*\u00111\u0003F\u0001\u0006gB\f'o\u001b\u0006\u0003+Y\ta!\u00199bG\",'\"A\f\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001Q\u0002CA\u000e\u001f\u001b\u0005a\"\"A\u000f\u0002\u000bM\u001c\u0017\r\\1\n\u0005}a\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002EA\u00111\u0005A\u0007\u0002\u001d!\u001a\u0011!J\u0016\u0011\u0005\u0019JS\"A\u0014\u000b\u0005!\u0012\u0012AC1o]>$\u0018\r^5p]&\u0011!f\n\u0002\u0006'&t7-Z\u0011\u0002Y\u0005)\u0011G\f\u001a/a\u0005I\u0001/\u0019:b[\u001e\u0013\u0018\u000eZ\u000b\u0002_A!\u0001'N\u001cK\u001b\u0005\t$B\u0001\u001a4\u0003\u001diW\u000f^1cY\u0016T!\u0001\u000e\u000f\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u00027c\t\u0019Q*\u001991\u0005a\u0002\u0005cA\u001d=}5\t!H\u0003\u0002<!\u0005)\u0001/\u0019:b[&\u0011QH\u000f\u0002\u0006!\u0006\u0014\u0018-\u001c\t\u0003\u007f\u0001c\u0001\u0001B\u0005B\u0007\u0005\u0005\t\u0011!B\u0001\u0007\n\u0019q\fJ\u0019\u0002\u0015A\f'/Y7He&$\u0007%\u0005\u0002E\u000fB\u00111$R\u0005\u0003\rr\u0011qAT8uQ&tw\r\u0005\u0002\u001c\u0011&\u0011\u0011\n\b\u0002\u0004\u0003:L\bGA&Q!\raUjT\u0007\u0002g%\u0011aj\r\u0002\t\u0013R,'/\u00192mKB\u0011q\b\u0015\u0003\n#\u000e\t\t\u0011!A\u0003\u0002\r\u00131a\u0018\u00133\u0003\u0019\u0011\u0017m]3P]R\u0011A+V\u0007\u0002\u0001!)a\u000b\u0002a\u0001/\u0006A\u0001/\u0019:b[6\u000b\u0007\u000f\u0005\u0002:1&\u0011\u0011L\u000f\u0002\t!\u0006\u0014\u0018-\\'ba\"\u001aA!J\u0016\u0015\u0005Qc\u0006\"B/\u0006\u0001\u0004q\u0016A\u00039be\u0006l\u0007+Y5sgB\u00191dX1\n\u0005\u0001d\"A\u0003\u001fsKB,\u0017\r^3e}A\u0012!M\u001a\t\u0004s\r,\u0017B\u00013;\u0005%\u0001\u0016M]1n!\u0006L'\u000f\u0005\u0002@M\u0012Iq\rXA\u0001\u0002\u0003\u0015\ta\u0011\u0002\u0004?\u0012\u001a\u0004fA\u0003&W!\u0012QA\u001b\t\u0003W6l\u0011\u0001\u001c\u0006\u0003QqI!A\u001c7\u0003\u000fY\f'/\u0019:hg\u00069\u0011\r\u001a3He&$WCA9v)\r!&o\u001e\u0005\u0006w\u0019\u0001\ra\u001d\t\u0004sq\"\bCA v\t\u00151hA1\u0001D\u0005\u0005!\u0006\"\u0002=\u0007\u0001\u0004I\u0018A\u0002<bYV,7\u000f\u0005\u0003{\u0003\u000b!hbA>\u0002\u00029\u0011Ap`\u0007\u0002{*\u0011a\u0010G\u0001\u0007yI|w\u000e\u001e \n\u0003uI1!a\u0001\u001d\u0003\u001d\u0001\u0018mY6bO\u0016L1ATA\u0004\u0015\r\t\u0019\u0001\b\u0015\u0004\r\u0015ZC#\u0002+\u0002\u000e\u0005U\u0001BB\u001e\b\u0001\u0004\ty\u0001E\u0002:\u0003#I1!a\u0005;\u0005-!u.\u001e2mKB\u000b'/Y7\t\ra<\u0001\u0019AA\f!\u0015Y\u0012\u0011DA\u000f\u0013\r\tY\u0002\b\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u00047\u0005}\u0011bAA\u00119\t1Ai\\;cY\u0016D3aB\u0013,)\u0015!\u0016qEA\u0018\u0011\u0019Y\u0004\u00021\u0001\u0002*A\u0019\u0011(a\u000b\n\u0007\u00055\"H\u0001\u0005J]R\u0004\u0016M]1n\u0011\u0019A\b\u00021\u0001\u00022A)1$!\u0007\u00024A\u00191$!\u000e\n\u0007\u0005]BDA\u0002J]RD3\u0001C\u0013,)\u0015!\u0016QHA#\u0011\u0019Y\u0014\u00021\u0001\u0002@A\u0019\u0011(!\u0011\n\u0007\u0005\r#H\u0001\u0006GY>\fG\u000fU1sC6Da\u0001_\u0005A\u0002\u0005\u001d\u0003#B\u000e\u0002\u001a\u0005%\u0003cA\u000e\u0002L%\u0019\u0011Q\n\u000f\u0003\u000b\u0019cw.\u0019;)\u0007%)3\u0006F\u0003U\u0003'\nY\u0006\u0003\u0004<\u0015\u0001\u0007\u0011Q\u000b\t\u0004s\u0005]\u0013bAA-u\tIAj\u001c8h!\u0006\u0014\u0018-\u001c\u0005\u0007q*\u0001\r!!\u0018\u0011\u000bm\tI\"a\u0018\u0011\u0007m\t\t'C\u0002\u0002dq\u0011A\u0001T8oO\"\u001a!\"J\u0016\u0015\u0007Q\u000bI\u0007\u0003\u0004<\u0017\u0001\u0007\u00111\u000e\t\u0004s\u00055\u0014bAA8u\ta!i\\8mK\u0006t\u0007+\u0019:b[\"\u001a1\"J\u0016\u0002\u000b\t,\u0018\u000e\u001c3\u0015\u0005\u0005]\u0004\u0003B\u000e\u0002\u001a]C3\u0001D\u0013,Q\r\u0001Qe\u000b"
)
public class ParamGridBuilder {
   private final Map paramGrid;

   public ParamGridBuilder baseOn(final ParamPair... paramPairs) {
      return this.baseOn((Seq).MODULE$.wrapRefArray(paramPairs));
   }

   private Map paramGrid() {
      return this.paramGrid;
   }

   public ParamGridBuilder baseOn(final ParamMap paramMap) {
      this.baseOn(paramMap.toSeq());
      return this;
   }

   public ParamGridBuilder baseOn(final Seq paramPairs) {
      paramPairs.foreach((p) -> this.addGrid(p.param(), (Iterable)scala.package..MODULE$.Seq().apply(.MODULE$.genericWrapArray(new Object[]{p.value()}))));
      return this;
   }

   public ParamGridBuilder addGrid(final Param param, final Iterable values) {
      this.paramGrid().put(param, values);
      return this;
   }

   public ParamGridBuilder addGrid(final DoubleParam param, final double[] values) {
      return this.addGrid((Param)param, (Iterable)scala.Predef..MODULE$.wrapDoubleArray(values));
   }

   public ParamGridBuilder addGrid(final IntParam param, final int[] values) {
      return this.addGrid((Param)param, (Iterable)scala.Predef..MODULE$.wrapIntArray(values));
   }

   public ParamGridBuilder addGrid(final FloatParam param, final float[] values) {
      return this.addGrid((Param)param, (Iterable)scala.Predef..MODULE$.wrapFloatArray(values));
   }

   public ParamGridBuilder addGrid(final LongParam param, final long[] values) {
      return this.addGrid((Param)param, (Iterable)scala.Predef..MODULE$.wrapLongArray(values));
   }

   public ParamGridBuilder addGrid(final BooleanParam param) {
      return this.addGrid((Param)param, (Iterable)scala.Predef..MODULE$.wrapBooleanArray(new boolean[]{true, false}));
   }

   public ParamMap[] build() {
      ObjectRef paramMaps = ObjectRef.create((ParamMap[])(new ParamMap[]{new ParamMap()}));
      this.paramGrid().foreach((x0$1) -> {
         $anonfun$build$1(paramMaps, x0$1);
         return BoxedUnit.UNIT;
      });
      return (ParamMap[])paramMaps.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$build$1(final ObjectRef paramMaps$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Param param = (Param)x0$1._1();
         Iterable values = (Iterable)x0$1._2();
         Iterable newParamMaps = (Iterable)values.flatMap((v) -> scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((ParamMap[])paramMaps$1.elem), (x$1) -> x$1.copy().put(param, v), scala.reflect.ClassTag..MODULE$.apply(ParamMap.class))));
         paramMaps$1.elem = (ParamMap[])newParamMaps.toArray(scala.reflect.ClassTag..MODULE$.apply(ParamMap.class));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   public ParamGridBuilder() {
      this.paramGrid = (Map)scala.collection.mutable.Map..MODULE$.empty();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
