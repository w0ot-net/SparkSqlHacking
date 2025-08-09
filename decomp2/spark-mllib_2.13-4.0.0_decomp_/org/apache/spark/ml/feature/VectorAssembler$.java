package org.apache.spark.ml.feature;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import org.apache.spark.SparkException;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.functions.;
import org.apache.spark.sql.types.StructField;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.MapOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuilder;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction2;

public final class VectorAssembler$ implements DefaultParamsReadable, Serializable {
   public static final VectorAssembler$ MODULE$ = new VectorAssembler$();
   private static final String SKIP_INVALID;
   private static final String ERROR_INVALID;
   private static final String KEEP_INVALID;
   private static final String[] supportedHandleInvalids;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      SKIP_INVALID = "skip";
      ERROR_INVALID = "error";
      KEEP_INVALID = "keep";
      supportedHandleInvalids = (String[])((Object[])(new String[]{MODULE$.SKIP_INVALID(), MODULE$.ERROR_INVALID(), MODULE$.KEEP_INVALID()}));
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public String SKIP_INVALID() {
      return SKIP_INVALID;
   }

   public String ERROR_INVALID() {
      return ERROR_INVALID;
   }

   public String KEEP_INVALID() {
      return KEEP_INVALID;
   }

   public String[] supportedHandleInvalids() {
      return supportedHandleInvalids;
   }

   public Map getVectorLengthsFromFirstRow(final Dataset dataset, final Seq columns) {
      try {
         Row first_row = (Row)dataset.toDF().select((Seq)columns.map((colName) -> .MODULE$.col(colName))).first();
         return ((IterableOnceOps)((IterableOps)columns.zip(first_row.toSeq())).map((x0$1) -> {
            if (x0$1 != null) {
               String c = (String)x0$1._1();
               Object x = x0$1._2();
               return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(c), BoxesRunTime.boxToInteger(((Vector)x).size()));
            } else {
               throw new MatchError(x0$1);
            }
         })).toMap(scala..less.colon.less..MODULE$.refl());
      } catch (NullPointerException var6) {
         String var8 = scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("Encountered null value while inferring lengths from the first row. Consider using\n           |VectorSizeHint to add metadata for columns: " + columns.mkString("[", ", ", "]") + ". ")).replaceAll("\n", " ");
         throw new NullPointerException(var8 + var6.toString());
      } catch (NoSuchElementException var7) {
         String var10002 = scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("Encountered empty dataframe while inferring lengths from the first row. Consider using\n           |VectorSizeHint to add metadata for columns: " + columns.mkString("[", ", ", "]") + ". ")).replaceAll("\n", " ");
         throw new NoSuchElementException(var10002 + var7.toString());
      }
   }

   public Map getLengths(final Dataset dataset, final Seq columns, final String handleInvalid) {
      Map groupSizes;
      Map var20;
      label62: {
         Seq missingColumns;
         label65: {
            groupSizes = ((IterableOnceOps)columns.map((c) -> {
               StructField field = SchemaUtils$.MODULE$.getSchemaField(dataset.schema(), c);
               return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(c), BoxesRunTime.boxToInteger(AttributeGroup$.MODULE$.fromStructField(field).size()));
            })).toMap(scala..less.colon.less..MODULE$.refl());
            missingColumns = ((MapOps)groupSizes.filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$getLengths$2(x$1)))).keys().toSeq();
            Tuple2 var8 = new Tuple2(BoxesRunTime.boxToBoolean(missingColumns.nonEmpty()), handleInvalid);
            if (var8 != null) {
               boolean var9 = var8._1$mcZ$sp();
               String var10 = (String)var8._2();
               if (var9) {
                  String var10000 = this.ERROR_INVALID();
                  if (var10000 == null) {
                     if (var10 == null) {
                        break label65;
                     }
                  } else if (var10000.equals(var10)) {
                     break label65;
                  }
               }
            }

            label66: {
               if (var8 != null) {
                  boolean var12 = var8._1$mcZ$sp();
                  String var13 = (String)var8._2();
                  if (var12) {
                     String var18 = this.SKIP_INVALID();
                     if (var18 == null) {
                        if (var13 == null) {
                           break label66;
                        }
                     } else if (var18.equals(var13)) {
                        break label66;
                     }
                  }
               }

               if (var8 != null) {
                  boolean var15 = var8._1$mcZ$sp();
                  String var16 = (String)var8._2();
                  if (var15) {
                     String var19 = this.KEEP_INVALID();
                     if (var19 == null) {
                        if (var16 == null) {
                           throw new RuntimeException(scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("Can not infer column lengths with handleInvalid = \"keep\". Consider using VectorSizeHint\n           |to add metadata for columns: " + missingColumns.mkString("[", ", ", "]") + ".")).replaceAll("\n", " "));
                        }
                     } else if (var19.equals(var16)) {
                        throw new RuntimeException(scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("Can not infer column lengths with handleInvalid = \"keep\". Consider using VectorSizeHint\n           |to add metadata for columns: " + missingColumns.mkString("[", ", ", "]") + ".")).replaceAll("\n", " "));
                     }
                  }
               }

               if (var8 == null) {
                  throw new MatchError(var8);
               }

               var20 = scala.Predef..MODULE$.Map().empty();
               break label62;
            }

            var20 = this.getVectorLengthsFromFirstRow(dataset.na().drop(missingColumns), missingColumns);
            break label62;
         }

         var20 = this.getVectorLengthsFromFirstRow(dataset, missingColumns);
      }

      Map firstSizes = var20;
      return (Map)groupSizes.$plus$plus(firstSizes);
   }

   public VectorAssembler load(final String path) {
      return (VectorAssembler)MLReadable.load$(this, path);
   }

   public Vector assemble(final int[] lengths, final boolean keepInvalid, final Seq vv) {
      ArrayBuilder indices = scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Int());
      ArrayBuilder values = scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Double());
      IntRef featureIndex = IntRef.create(0);
      IntRef inputColumnIndex = IntRef.create(0);
      vv.foreach((x0$1) -> {
         $anonfun$assemble$1(keepInvalid, indices, featureIndex, values, inputColumnIndex, lengths, x0$1);
         return BoxedUnit.UNIT;
      });
      int[] idxArray = (int[])indices.result();
      double[] valArray = (double[])values.result();
      return org.apache.spark.ml.linalg.Vectors..MODULE$.sparse(featureIndex.elem, idxArray, valArray).compressedWithNNZ(idxArray.length);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(VectorAssembler$.class);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getLengths$2(final Tuple2 x$1) {
      return x$1._2$mcI$sp() == -1;
   }

   // $FF: synthetic method
   public static final ArrayBuilder $anonfun$assemble$3(final ArrayBuilder indices$1, final IntRef featureIndex$1, final ArrayBuilder values$1, final int i) {
      indices$1.$plus$eq(BoxesRunTime.boxToInteger(featureIndex$1.elem + i));
      return (ArrayBuilder)values$1.$plus$eq(BoxesRunTime.boxToDouble(Double.NaN));
   }

   // $FF: synthetic method
   public static final void $anonfun$assemble$1(final boolean keepInvalid$2, final ArrayBuilder indices$1, final IntRef featureIndex$1, final ArrayBuilder values$1, final IntRef inputColumnIndex$1, final int[] lengths$2, final Object x0$1) {
      if (x0$1 instanceof Double) {
         double var9 = BoxesRunTime.unboxToDouble(x0$1);
         if (Double.isNaN(var9) && !keepInvalid$2) {
            throw new SparkException(scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("Encountered NaN while assembling a row with handleInvalid = \"error\". Consider\n               |removing NaNs from dataset or using handleInvalid = \"keep\" or \"skip\".")));
         } else {
            if (var9 != (double)0.0F) {
               indices$1.$plus$eq(BoxesRunTime.boxToInteger(featureIndex$1.elem));
               values$1.$plus$eq(BoxesRunTime.boxToDouble(var9));
            } else {
               BoxedUnit var14 = BoxedUnit.UNIT;
            }

            ++inputColumnIndex$1.elem;
            ++featureIndex$1.elem;
            BoxedUnit var15 = BoxedUnit.UNIT;
         }
      } else if (x0$1 instanceof Vector) {
         Vector var11 = (Vector)x0$1;
         var11.foreachNonZero((JFunction2.mcVID.sp)(x0$2, x1$1) -> {
            Tuple2.mcID.sp var7 = new Tuple2.mcID.sp(x0$2, x1$1);
            if (var7 != null) {
               int i = ((Tuple2)var7)._1$mcI$sp();
               double v = ((Tuple2)var7)._2$mcD$sp();
               indices$1.$plus$eq(BoxesRunTime.boxToInteger(featureIndex$1.elem + i));
               values$1.$plus$eq(BoxesRunTime.boxToDouble(v));
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(var7);
            }
         });
         ++inputColumnIndex$1.elem;
         featureIndex$1.elem += var11.size();
         BoxedUnit var13 = BoxedUnit.UNIT;
      } else if (x0$1 == null) {
         if (keepInvalid$2) {
            int length = lengths$2[inputColumnIndex$1.elem];
            scala.package..MODULE$.Iterator().range(0, length).foreach((i) -> $anonfun$assemble$3(indices$1, featureIndex$1, values$1, BoxesRunTime.unboxToInt(i)));
            ++inputColumnIndex$1.elem;
            featureIndex$1.elem += length;
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            throw new SparkException(scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("Encountered null while assembling a row with handleInvalid = \"error\". Consider\n               |removing nulls from dataset or using handleInvalid = \"keep\" or \"skip\".")));
         }
      } else {
         throw new SparkException(x0$1 + " of type " + x0$1.getClass().getName() + " is not supported.");
      }
   }

   private VectorAssembler$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
