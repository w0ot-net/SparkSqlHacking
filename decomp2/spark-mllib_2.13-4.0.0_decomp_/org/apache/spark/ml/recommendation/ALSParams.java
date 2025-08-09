package org.apache.spark.ml.recommendation;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasCheckpointInterval;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasRegParam;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.FloatType.;
import org.apache.spark.storage.StorageLevelMapper;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ma\u0001C\u000b\u0017!\u0003\r\tA\u0006\u0011\t\u000bq\u0002A\u0011\u0001 \t\u000f\t\u0003!\u0019!C\u0001\u0007\")\u0001\n\u0001C\u0001\u0013\"9Q\n\u0001b\u0001\n\u0003\u0019\u0005\"\u0002(\u0001\t\u0003I\u0005bB(\u0001\u0005\u0004%\ta\u0011\u0005\u0006!\u0002!\t!\u0013\u0005\b#\u0002\u0011\r\u0011\"\u0001S\u0011\u00151\u0006\u0001\"\u0001X\u0011\u001dY\u0006A1A\u0005\u0002qCQ\u0001\u0019\u0001\u0005\u0002\u0005Dq!\u001a\u0001C\u0002\u0013\u0005a\rC\u0003v\u0001\u0011\u0005a\u000fC\u0004x\u0001\t\u0007I\u0011\u0001*\t\u000ba\u0004A\u0011A,\t\u000fe\u0004!\u0019!C\u0001M\")!\u0010\u0001C\u0001m\"91\u0010\u0001b\u0001\n\u00031\u0007\"\u0002?\u0001\t\u00031\b\"B?\u0001\t#q(!C!M'B\u000b'/Y7t\u0015\t9\u0002$\u0001\bsK\u000e|W.\\3oI\u0006$\u0018n\u001c8\u000b\u0005eQ\u0012AA7m\u0015\tYB$A\u0003ta\u0006\u00148N\u0003\u0002\u001e=\u00051\u0011\r]1dQ\u0016T\u0011aH\u0001\u0004_J<7c\u0002\u0001\"O-\u001ad'\u000f\t\u0003E\u0015j\u0011a\t\u0006\u0002I\u0005)1oY1mC&\u0011ae\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0005!JS\"\u0001\f\n\u0005)2\"AD!M'6{G-\u001a7QCJ\fWn\u001d\t\u0003YEj\u0011!\f\u0006\u0003]=\naa\u001d5be\u0016$'B\u0001\u0019\u0019\u0003\u0015\u0001\u0018M]1n\u0013\t\u0011TF\u0001\u0006ICNl\u0015\r_%uKJ\u0004\"\u0001\f\u001b\n\u0005Uj#a\u0003%bgJ+w\rU1sC6\u0004\"\u0001L\u001c\n\u0005aj#!\u0006%bg\u000eCWmY6q_&tG/\u00138uKJ4\u0018\r\u001c\t\u0003YiJ!aO\u0017\u0003\u000f!\u000b7oU3fI\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001@!\t\u0011\u0003)\u0003\u0002BG\t!QK\\5u\u0003\u0011\u0011\u0018M\\6\u0016\u0003\u0011\u0003\"!\u0012$\u000e\u0003=J!aR\u0018\u0003\u0011%sG\u000fU1sC6\fqaZ3u%\u0006t7.F\u0001K!\t\u00113*\u0003\u0002MG\t\u0019\u0011J\u001c;\u0002\u001b9,X.V:fe\ncwnY6t\u0003A9W\r\u001e(v[V\u001bXM\u001d\"m_\u000e\\7/A\u0007ok6LE/Z7CY>\u001c7n]\u0001\u0011O\u0016$h*^7Ji\u0016l'\t\\8dWN\fQ\"[7qY&\u001c\u0017\u000e\u001e)sK\u001a\u001cX#A*\u0011\u0005\u0015#\u0016BA+0\u00051\u0011un\u001c7fC:\u0004\u0016M]1n\u0003A9W\r^%na2L7-\u001b;Qe\u001647/F\u0001Y!\t\u0011\u0013,\u0003\u0002[G\t9!i\\8mK\u0006t\u0017!B1ma\"\fW#A/\u0011\u0005\u0015s\u0016BA00\u0005-!u.\u001e2mKB\u000b'/Y7\u0002\u0011\u001d,G/\u00117qQ\u0006,\u0012A\u0019\t\u0003E\rL!\u0001Z\u0012\u0003\r\u0011{WO\u00197f\u0003%\u0011\u0018\r^5oO\u000e{G.F\u0001h!\r)\u0005N[\u0005\u0003S>\u0012Q\u0001U1sC6\u0004\"a\u001b:\u000f\u00051\u0004\bCA7$\u001b\u0005q'BA8>\u0003\u0019a$o\\8u}%\u0011\u0011oI\u0001\u0007!J,G-\u001a4\n\u0005M$(AB*ue&twM\u0003\u0002rG\u0005aq-\u001a;SCRLgnZ\"pYV\t!.A\u0006o_:tWmZ1uSZ,\u0017AD4fi:{gN\\3hCRLg/Z\u0001\u0019S:$XM]7fI&\fG/Z*u_J\fw-\u001a'fm\u0016d\u0017aG4fi&sG/\u001a:nK\u0012L\u0017\r^3Ti>\u0014\u0018mZ3MKZ,G.A\tgS:\fGn\u0015;pe\u0006<W\rT3wK2\fAcZ3u\r&t\u0017\r\\*u_J\fw-\u001a'fm\u0016d\u0017A\u0007<bY&$\u0017\r^3B]\u0012$&/\u00198tM>\u0014XnU2iK6\fGcA@\u0002\u0010A!\u0011\u0011AA\u0006\u001b\t\t\u0019A\u0003\u0003\u0002\u0006\u0005\u001d\u0011!\u0002;za\u0016\u001c(bAA\u00055\u0005\u00191/\u001d7\n\t\u00055\u00111\u0001\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007BBA\t)\u0001\u0007q0\u0001\u0004tG\",W.\u0019"
)
public interface ALSParams extends ALSModelParams, HasMaxIter, HasRegParam, HasCheckpointInterval, HasSeed {
   void org$apache$spark$ml$recommendation$ALSParams$_setter_$rank_$eq(final IntParam x$1);

   void org$apache$spark$ml$recommendation$ALSParams$_setter_$numUserBlocks_$eq(final IntParam x$1);

   void org$apache$spark$ml$recommendation$ALSParams$_setter_$numItemBlocks_$eq(final IntParam x$1);

   void org$apache$spark$ml$recommendation$ALSParams$_setter_$implicitPrefs_$eq(final BooleanParam x$1);

   void org$apache$spark$ml$recommendation$ALSParams$_setter_$alpha_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$recommendation$ALSParams$_setter_$ratingCol_$eq(final Param x$1);

   void org$apache$spark$ml$recommendation$ALSParams$_setter_$nonnegative_$eq(final BooleanParam x$1);

   void org$apache$spark$ml$recommendation$ALSParams$_setter_$intermediateStorageLevel_$eq(final Param x$1);

   void org$apache$spark$ml$recommendation$ALSParams$_setter_$finalStorageLevel_$eq(final Param x$1);

   IntParam rank();

   // $FF: synthetic method
   static int getRank$(final ALSParams $this) {
      return $this.getRank();
   }

   default int getRank() {
      return BoxesRunTime.unboxToInt(this.$(this.rank()));
   }

   IntParam numUserBlocks();

   // $FF: synthetic method
   static int getNumUserBlocks$(final ALSParams $this) {
      return $this.getNumUserBlocks();
   }

   default int getNumUserBlocks() {
      return BoxesRunTime.unboxToInt(this.$(this.numUserBlocks()));
   }

   IntParam numItemBlocks();

   // $FF: synthetic method
   static int getNumItemBlocks$(final ALSParams $this) {
      return $this.getNumItemBlocks();
   }

   default int getNumItemBlocks() {
      return BoxesRunTime.unboxToInt(this.$(this.numItemBlocks()));
   }

   BooleanParam implicitPrefs();

   // $FF: synthetic method
   static boolean getImplicitPrefs$(final ALSParams $this) {
      return $this.getImplicitPrefs();
   }

   default boolean getImplicitPrefs() {
      return BoxesRunTime.unboxToBoolean(this.$(this.implicitPrefs()));
   }

   DoubleParam alpha();

   // $FF: synthetic method
   static double getAlpha$(final ALSParams $this) {
      return $this.getAlpha();
   }

   default double getAlpha() {
      return BoxesRunTime.unboxToDouble(this.$(this.alpha()));
   }

   Param ratingCol();

   // $FF: synthetic method
   static String getRatingCol$(final ALSParams $this) {
      return $this.getRatingCol();
   }

   default String getRatingCol() {
      return (String)this.$(this.ratingCol());
   }

   BooleanParam nonnegative();

   // $FF: synthetic method
   static boolean getNonnegative$(final ALSParams $this) {
      return $this.getNonnegative();
   }

   default boolean getNonnegative() {
      return BoxesRunTime.unboxToBoolean(this.$(this.nonnegative()));
   }

   Param intermediateStorageLevel();

   // $FF: synthetic method
   static String getIntermediateStorageLevel$(final ALSParams $this) {
      return $this.getIntermediateStorageLevel();
   }

   default String getIntermediateStorageLevel() {
      return (String)this.$(this.intermediateStorageLevel());
   }

   Param finalStorageLevel();

   // $FF: synthetic method
   static String getFinalStorageLevel$(final ALSParams $this) {
      return $this.getFinalStorageLevel();
   }

   default String getFinalStorageLevel() {
      return (String)this.$(this.finalStorageLevel());
   }

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final ALSParams $this, final StructType schema) {
      return $this.validateAndTransformSchema(schema);
   }

   default StructType validateAndTransformSchema(final StructType schema) {
      SchemaUtils$.MODULE$.checkNumericType(schema, (String)this.$(this.userCol()), SchemaUtils$.MODULE$.checkNumericType$default$3());
      SchemaUtils$.MODULE$.checkNumericType(schema, (String)this.$(this.itemCol()), SchemaUtils$.MODULE$.checkNumericType$default$3());
      SchemaUtils$.MODULE$.checkNumericType(schema, (String)this.$(this.ratingCol()), SchemaUtils$.MODULE$.checkNumericType$default$3());
      return SchemaUtils$.MODULE$.appendColumn(schema, (String)this.$(this.predictionCol()), .MODULE$, SchemaUtils$.MODULE$.appendColumn$default$4());
   }

   // $FF: synthetic method
   static boolean $anonfun$intermediateStorageLevel$1(final String s) {
      boolean var10000;
      label25: {
         if (scala.util.Try..MODULE$.apply(() -> org.apache.spark.storage.StorageLevel..MODULE$.fromString(s)).isSuccess()) {
            String var1 = "NONE";
            if (s == null) {
               if (var1 != null) {
                  break label25;
               }
            } else if (!s.equals(var1)) {
               break label25;
            }
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   static boolean $anonfun$finalStorageLevel$1(final String s) {
      return scala.util.Try..MODULE$.apply(() -> org.apache.spark.storage.StorageLevel..MODULE$.fromString(s)).isSuccess();
   }

   static void $init$(final ALSParams $this) {
      $this.org$apache$spark$ml$recommendation$ALSParams$_setter_$rank_$eq(new IntParam($this, "rank", "rank of the factorization", ParamValidators$.MODULE$.gtEq((double)1.0F)));
      $this.org$apache$spark$ml$recommendation$ALSParams$_setter_$numUserBlocks_$eq(new IntParam($this, "numUserBlocks", "number of user blocks", ParamValidators$.MODULE$.gtEq((double)1.0F)));
      $this.org$apache$spark$ml$recommendation$ALSParams$_setter_$numItemBlocks_$eq(new IntParam($this, "numItemBlocks", "number of item blocks", ParamValidators$.MODULE$.gtEq((double)1.0F)));
      $this.org$apache$spark$ml$recommendation$ALSParams$_setter_$implicitPrefs_$eq(new BooleanParam($this, "implicitPrefs", "whether to use implicit preference"));
      $this.org$apache$spark$ml$recommendation$ALSParams$_setter_$alpha_$eq(new DoubleParam($this, "alpha", "alpha for implicit preference", ParamValidators$.MODULE$.gtEq((double)0.0F)));
      $this.org$apache$spark$ml$recommendation$ALSParams$_setter_$ratingCol_$eq(new Param($this, "ratingCol", "column name for ratings", scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$recommendation$ALSParams$_setter_$nonnegative_$eq(new BooleanParam($this, "nonnegative", "whether to use nonnegative constraint for least squares"));
      $this.org$apache$spark$ml$recommendation$ALSParams$_setter_$intermediateStorageLevel_$eq(new Param($this, "intermediateStorageLevel", "StorageLevel for intermediate datasets. Cannot be 'NONE'.", (s) -> BoxesRunTime.boxToBoolean($anonfun$intermediateStorageLevel$1(s)), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$recommendation$ALSParams$_setter_$finalStorageLevel_$eq(new Param($this, "finalStorageLevel", "StorageLevel for ALS model factors.", (s) -> BoxesRunTime.boxToBoolean($anonfun$finalStorageLevel$1(s)), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.rank().$minus$greater(BoxesRunTime.boxToInteger(10)), $this.maxIter().$minus$greater(BoxesRunTime.boxToInteger(10)), $this.regParam().$minus$greater(BoxesRunTime.boxToDouble(0.1)), $this.numUserBlocks().$minus$greater(BoxesRunTime.boxToInteger(10)), $this.numItemBlocks().$minus$greater(BoxesRunTime.boxToInteger(10)), $this.implicitPrefs().$minus$greater(BoxesRunTime.boxToBoolean(false)), $this.alpha().$minus$greater(BoxesRunTime.boxToDouble((double)1.0F)), $this.userCol().$minus$greater("user"), $this.itemCol().$minus$greater("item"), $this.ratingCol().$minus$greater("rating"), $this.nonnegative().$minus$greater(BoxesRunTime.boxToBoolean(false)), $this.checkpointInterval().$minus$greater(BoxesRunTime.boxToInteger(10)), $this.intermediateStorageLevel().$minus$greater(StorageLevelMapper.MEMORY_AND_DISK.name()), $this.finalStorageLevel().$minus$greater(StorageLevelMapper.MEMORY_AND_DISK.name()), $this.coldStartStrategy().$minus$greater("nan")}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
