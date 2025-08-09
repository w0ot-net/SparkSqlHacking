package org.apache.spark.sql.hive;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkException;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Tuple2;
import scala.Predef.ArrowAssoc.;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.MapOps;
import scala.collection.SeqOps;
import scala.collection.StringOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;

public final class HiveMetastoreCatalog$ {
   public static final HiveMetastoreCatalog$ MODULE$ = new HiveMetastoreCatalog$();

   public StructType mergeWithMetastoreSchema(final StructType metastoreSchema, final StructType inferredSchema) {
      try {
         Map metastoreFields = ((IterableOnceOps)metastoreSchema.map((f) -> .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(f.name().toLowerCase()), f))).toMap(scala..less.colon.less..MODULE$.refl());
         Iterable missingNullables = (Iterable)((MapOps)metastoreFields.filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$mergeWithMetastoreSchema$2(inferredSchema, x0$1)))).values().filter((x$6) -> BoxesRunTime.boxToBoolean($anonfun$mergeWithMetastoreSchema$4(x$6)));
         Map inferredFields = ((IterableOnceOps)org.apache.spark.sql.types.StructType..MODULE$.apply((Seq)inferredSchema.$plus$plus(missingNullables)).map((f) -> .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(f.name().toLowerCase()), f))).toMap(scala..less.colon.less..MODULE$.refl());
         return org.apache.spark.sql.types.StructType..MODULE$.apply((Seq)metastoreSchema.map((f) -> f.copy(((StructField)inferredFields.apply(f.name().toLowerCase())).name(), f.copy$default$2(), f.copy$default$3(), f.copy$default$4())));
      } catch (Throwable var11) {
         if (var11 != null) {
            Option var9 = scala.util.control.NonFatal..MODULE$.unapply(var11);
            if (!var9.isEmpty()) {
               StringOps var10000 = scala.collection.StringOps..MODULE$;
               Predef var10001 = scala.Predef..MODULE$;
               String var10002 = metastoreSchema.prettyJson();
               String msg = var10000.stripMargin$extension(var10001.augmentString("Detected conflicting schemas when merging the schema obtained from the Hive\n         | Metastore with the one inferred from the file format. Metastore schema:\n         |" + var10002 + "\n         |\n         |Inferred schema:\n         |" + inferredSchema.prettyJson() + "\n       "));
               throw new SparkException(msg);
            }
         }

         throw var11;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$mergeWithMetastoreSchema$2(final StructType inferredSchema$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         return !((SeqOps)inferredSchema$1.map((x$5) -> x$5.name().toLowerCase())).contains(k);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$mergeWithMetastoreSchema$4(final StructField x$6) {
      return x$6.nullable();
   }

   private HiveMetastoreCatalog$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
