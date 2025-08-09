package org.apache.spark.ml.recommendation;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.shared.HasBlockSize;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.NumericType;
import org.apache.spark.sql.types.IntegerType.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054\u0001\"\u0003\u0006\u0011\u0002\u0007\u0005!\u0002\u0006\u0005\u0006U\u0001!\t\u0001\f\u0005\ba\u0001\u0011\r\u0011\"\u00012\u0011\u0015\u0001\u0005\u0001\"\u0001B\u0011\u001d\u0011\u0005A1A\u0005\u0002EBQa\u0011\u0001\u0005\u0002\u0005Ca\u0001\u0012\u0001\u0005\u0012))\u0005bB0\u0001\u0005\u0004%\t!\r\u0005\u0006A\u0002!\t!\u0011\u0002\u000f\u00032\u001bVj\u001c3fYB\u000b'/Y7t\u0015\tYA\"\u0001\bsK\u000e|W.\\3oI\u0006$\u0018n\u001c8\u000b\u00055q\u0011AA7m\u0015\ty\u0001#A\u0003ta\u0006\u00148N\u0003\u0002\u0012%\u00051\u0011\r]1dQ\u0016T\u0011aE\u0001\u0004_J<7#\u0002\u0001\u00167\u0005:\u0003C\u0001\f\u001a\u001b\u00059\"\"\u0001\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005i9\"AB!osJ+g\r\u0005\u0002\u001d?5\tQD\u0003\u0002\u001f\u0019\u0005)\u0001/\u0019:b[&\u0011\u0001%\b\u0002\u0007!\u0006\u0014\u0018-\\:\u0011\u0005\t*S\"A\u0012\u000b\u0005\u0011j\u0012AB:iCJ,G-\u0003\u0002'G\t\u0001\u0002*Y:Qe\u0016$\u0017n\u0019;j_:\u001cu\u000e\u001c\t\u0003E!J!!K\u0012\u0003\u0019!\u000b7O\u00117pG.\u001c\u0016N_3\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012!\f\t\u0003-9J!aL\f\u0003\tUs\u0017\u000e^\u0001\bkN,'oQ8m+\u0005\u0011\u0004c\u0001\u000f4k%\u0011A'\b\u0002\u0006!\u0006\u0014\u0018-\u001c\t\u0003mur!aN\u001e\u0011\u0005a:R\"A\u001d\u000b\u0005iZ\u0013A\u0002\u001fs_>$h(\u0003\u0002=/\u00051\u0001K]3eK\u001aL!AP \u0003\rM#(/\u001b8h\u0015\tat#\u0001\u0006hKR,6/\u001a:D_2,\u0012!N\u0001\bSR,WnQ8m\u0003)9W\r^%uK6\u001cu\u000e\\\u0001\u000eG\",7m[%oi\u0016<WM]:\u0015\u0007\u0019cU\f\u0005\u0002H\u00156\t\u0001J\u0003\u0002J\u001d\u0005\u00191/\u001d7\n\u0005-C%AB\"pYVlg\u000eC\u0003N\r\u0001\u0007a*A\u0004eCR\f7/\u001a;1\u0005=#\u0006cA$Q%&\u0011\u0011\u000b\u0013\u0002\b\t\u0006$\u0018m]3u!\t\u0019F\u000b\u0004\u0001\u0005\u0013Uc\u0015\u0011!A\u0001\u0006\u00031&aA0%cE\u0011qK\u0017\t\u0003-aK!!W\f\u0003\u000f9{G\u000f[5oOB\u0011acW\u0005\u00039^\u00111!\u00118z\u0011\u0015qf\u00011\u00016\u0003\u001d\u0019w\u000e\u001c(b[\u0016\f\u0011cY8mIN#\u0018M\u001d;TiJ\fG/Z4z\u0003Q9W\r^\"pY\u0012\u001cF/\u0019:u'R\u0014\u0018\r^3hs\u0002"
)
public interface ALSModelParams extends HasPredictionCol, HasBlockSize {
   void org$apache$spark$ml$recommendation$ALSModelParams$_setter_$userCol_$eq(final Param x$1);

   void org$apache$spark$ml$recommendation$ALSModelParams$_setter_$itemCol_$eq(final Param x$1);

   void org$apache$spark$ml$recommendation$ALSModelParams$_setter_$coldStartStrategy_$eq(final Param x$1);

   Param userCol();

   // $FF: synthetic method
   static String getUserCol$(final ALSModelParams $this) {
      return $this.getUserCol();
   }

   default String getUserCol() {
      return (String)this.$(this.userCol());
   }

   Param itemCol();

   // $FF: synthetic method
   static String getItemCol$(final ALSModelParams $this) {
      return $this.getItemCol();
   }

   default String getItemCol() {
      return (String)this.$(this.itemCol());
   }

   // $FF: synthetic method
   static Column checkIntegers$(final ALSModelParams $this, final Dataset dataset, final String colName) {
      return $this.checkIntegers(dataset, colName);
   }

   default Column checkIntegers(final Dataset dataset, final String colName) {
      DataType var4 = dataset.schema().apply(colName).dataType();
      if (.MODULE$.equals(var4)) {
         Column column = dataset.apply(colName);
         return org.apache.spark.sql.functions..MODULE$.when(column.isNull(), org.apache.spark.sql.functions..MODULE$.raise_error(org.apache.spark.sql.functions..MODULE$.lit(colName + " Ids MUST NOT be Null"))).otherwise(column);
      } else if (var4 instanceof NumericType) {
         Column column = dataset.apply(colName);
         Column casted = column.cast(.MODULE$);
         return org.apache.spark.sql.functions..MODULE$.when(column.isNull().$bar$bar(column.$eq$bang$eq(casted)), org.apache.spark.sql.functions..MODULE$.raise_error(org.apache.spark.sql.functions..MODULE$.concat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.lit("ALS only supports non-Null values in Integer range and without fractional part for column " + colName + ", but got "), column}))))).otherwise(casted);
      } else {
         throw new IllegalArgumentException("ALS only supports values in Integer range for column " + colName + ", but got type " + var4 + ".");
      }
   }

   Param coldStartStrategy();

   // $FF: synthetic method
   static String getColdStartStrategy$(final ALSModelParams $this) {
      return $this.getColdStartStrategy();
   }

   default String getColdStartStrategy() {
      return ((String)this.$(this.coldStartStrategy())).toLowerCase(Locale.ROOT);
   }

   // $FF: synthetic method
   static boolean $anonfun$coldStartStrategy$1(final String s) {
      return scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])ALSModel$.MODULE$.supportedColdStartStrategies()), s.toLowerCase(Locale.ROOT));
   }

   static void $init$(final ALSModelParams $this) {
      $this.org$apache$spark$ml$recommendation$ALSModelParams$_setter_$userCol_$eq(new Param($this, "userCol", "column name for user ids. Ids must be within the integer value range.", scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$recommendation$ALSModelParams$_setter_$itemCol_$eq(new Param($this, "itemCol", "column name for item ids. Ids must be within the integer value range.", scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$recommendation$ALSModelParams$_setter_$coldStartStrategy_$eq(new Param($this, "coldStartStrategy", "strategy for dealing with unknown or new users/items at prediction time. This may be useful in cross-validation or production scenarios, for handling user/item ids the model has not seen in the training data. Supported values: " + scala.Predef..MODULE$.wrapRefArray((Object[])ALSModel$.MODULE$.supportedColdStartStrategies()).mkString(",") + ".", (s) -> BoxesRunTime.boxToBoolean($anonfun$coldStartStrategy$1(s)), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.blockSize().$minus$greater(BoxesRunTime.boxToInteger(4096))}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
