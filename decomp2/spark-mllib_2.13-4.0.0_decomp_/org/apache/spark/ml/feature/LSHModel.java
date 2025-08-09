package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.catalyst.util.QuantileSummaries;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.functions.;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.Function2;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t}ba\u0002\t\u0012\u0003\u0003)2$\t\u0005\u0006q\u0001!\t!\u000f\u0005\u0006u\u0001!\ta\u000f\u0005\u0006\u0015\u0002!\ta\u0013\u0005\u0007\u001b\u00021\tb\u0005(\t\ri\u0003a\u0011C\n\\\u0011\u0019\u0019\u0007A\"\u0005\u0014I\")q\r\u0001C!Q\"9\u0011\u0011\u0003\u0001\u0005B\u0005M\u0001\u0002CA\u0013\u0001\u0011\u0005\u0011#a\n\t\u000f\u0005\u0015\u0002\u0001\"\u0001\u0002\\!9\u0011Q\u0005\u0001\u0005\u0002\u0005e\u0004\u0002CAK\u0001\u0001&I!a&\t\u0011\u0005\u001d\u0007\u0001)C\u0005\u0003\u0013Dq!!;\u0001\t\u0003\tY\u000fC\u0004\u0002j\u0002!\tA!\u0007\u0003\u00111\u001b\u0006*T8eK2T!AE\n\u0002\u000f\u0019,\u0017\r^;sK*\u0011A#F\u0001\u0003[2T!AF\f\u0002\u000bM\u0004\u0018M]6\u000b\u0005aI\u0012AB1qC\u000eDWMC\u0001\u001b\u0003\ry'oZ\u000b\u00039\r\u001aB\u0001A\u000f0eA\u0019adH\u0011\u000e\u0003MI!\u0001I\n\u0003\u000b5{G-\u001a7\u0011\u0005\t\u001aC\u0002\u0001\u0003\u0006I\u0001\u0011\rA\n\u0002\u0002)\u000e\u0001\u0011CA\u0014.!\tA3&D\u0001*\u0015\u0005Q\u0013!B:dC2\f\u0017B\u0001\u0017*\u0005\u001dqu\u000e\u001e5j]\u001e\u00042A\f\u0001\"\u001b\u0005\t\u0002C\u0001\u00181\u0013\t\t\u0014CA\u0005M'\"\u0003\u0016M]1ngB\u00111GN\u0007\u0002i)\u0011QgE\u0001\u0005kRLG.\u0003\u00028i\tQQ\nT,sSR\f'\r\\3\u0002\rqJg.\u001b;?)\u0005i\u0013aC:fi&s\u0007/\u001e;D_2$\"\u0001P\u001f\u000e\u0003\u0001AQA\u0010\u0002A\u0002}\nQA^1mk\u0016\u0004\"\u0001Q$\u000f\u0005\u0005+\u0005C\u0001\"*\u001b\u0005\u0019%B\u0001#&\u0003\u0019a$o\\8u}%\u0011a)K\u0001\u0007!J,G-\u001a4\n\u0005!K%AB*ue&twM\u0003\u0002GS\u0005a1/\u001a;PkR\u0004X\u000f^\"pYR\u0011A\b\u0014\u0005\u0006}\r\u0001\raP\u0001\rQ\u0006\u001c\bNR;oGRLwN\u001c\u000b\u0003\u001fb\u00032\u0001\u000b)S\u0013\t\t\u0016FA\u0003BeJ\f\u0017\u0010\u0005\u0002T-6\tAK\u0003\u0002V'\u00051A.\u001b8bY\u001eL!a\u0016+\u0003\rY+7\r^8s\u0011\u0015IF\u00011\u0001S\u0003\u0015)G.Z7t\u0003-YW-\u001f#jgR\fgnY3\u0015\u0007q{\u0016\r\u0005\u0002);&\u0011a,\u000b\u0002\u0007\t>,(\r\\3\t\u000b\u0001,\u0001\u0019\u0001*\u0002\u0003aDQAY\u0003A\u0002I\u000b\u0011!_\u0001\rQ\u0006\u001c\b\u000eR5ti\u0006t7-\u001a\u000b\u00049\u00164\u0007\"\u00021\u0007\u0001\u0004y\u0005\"\u00022\u0007\u0001\u0004y\u0015!\u0003;sC:\u001chm\u001c:n)\tI'\u0010\u0005\u0002ko:\u00111\u000e\u001e\b\u0003YJt!!\\9\u000f\u00059\u0004hB\u0001\"p\u0013\u0005Q\u0012B\u0001\r\u001a\u0013\t1r#\u0003\u0002t+\u0005\u00191/\u001d7\n\u0005U4\u0018a\u00029bG.\fw-\u001a\u0006\u0003gVI!\u0001_=\u0003\u0013\u0011\u000bG/\u0019$sC6,'BA;w\u0011\u0015Yx\u00011\u0001}\u0003\u001d!\u0017\r^1tKR\u00044!`A\u0003!\u0011qx0a\u0001\u000e\u0003YL1!!\u0001w\u0005\u001d!\u0015\r^1tKR\u00042AIA\u0003\t-\t9A_A\u0001\u0002\u0003\u0015\t!!\u0003\u0003\u0007}#\u0013'E\u0002(\u0003\u0017\u00012\u0001KA\u0007\u0013\r\ty!\u000b\u0002\u0004\u0003:L\u0018a\u0004;sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\t\u0005U\u0011\u0011\u0005\t\u0005\u0003/\ti\"\u0004\u0002\u0002\u001a)\u0019\u00111\u0004<\u0002\u000bQL\b/Z:\n\t\u0005}\u0011\u0011\u0004\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007bBA\u0012\u0011\u0001\u0007\u0011QC\u0001\u0007g\u000eDW-\\1\u0002-\u0005\u0004\bO]8y\u001d\u0016\f'/Z:u\u001d\u0016Lw\r\u001b2peN$B\"!\u000b\u00024\u0005}\u00121IA'\u0003/\u0002D!a\u000b\u00020A!ap`A\u0017!\r\u0011\u0013q\u0006\u0003\f\u0003cI\u0011\u0011!A\u0001\u0006\u0003\tIAA\u0002`IMBaa_\u0005A\u0002\u0005U\u0002\u0007BA\u001c\u0003w\u0001BA`@\u0002:A\u0019!%a\u000f\u0005\u0019\u0005u\u00121GA\u0001\u0002\u0003\u0015\t!!\u0003\u0003\u0007}##\u0007\u0003\u0004\u0002B%\u0001\rAU\u0001\u0004W\u0016L\bbBA#\u0013\u0001\u0007\u0011qI\u0001\u0014]Vlg*Z1sKN$h*Z5hQ\n|'o\u001d\t\u0004Q\u0005%\u0013bAA&S\t\u0019\u0011J\u001c;\t\u000f\u0005=\u0013\u00021\u0001\u0002R\u0005Y1/\u001b8hY\u0016\u0004&o\u001c2f!\rA\u00131K\u0005\u0004\u0003+J#a\u0002\"p_2,\u0017M\u001c\u0005\u0007\u00033J\u0001\u0019A \u0002\u000f\u0011L7\u000f^\"pYRQ\u0011QLA4\u0003g\n)(a\u001e1\t\u0005}\u00131\r\t\u0005}~\f\t\u0007E\u0002#\u0003G\"1\"!\u001a\u000b\u0003\u0003\u0005\tQ!\u0001\u0002\n\t\u0019q\fJ\u001b\t\rmT\u0001\u0019AA5a\u0011\tY'a\u001c\u0011\ty|\u0018Q\u000e\t\u0004E\u0005=D\u0001DA9\u0003O\n\t\u0011!A\u0003\u0002\u0005%!aA0%i!1\u0011\u0011\t\u0006A\u0002ICq!!\u0012\u000b\u0001\u0004\t9\u0005\u0003\u0004\u0002Z)\u0001\ra\u0010\u000b\t\u0003w\n))!%\u0002\u0014B\"\u0011QPAA!\u0011qx0a \u0011\u0007\t\n\t\tB\u0006\u0002\u0004.\t\t\u0011!A\u0003\u0002\u0005%!aA0%o!11p\u0003a\u0001\u0003\u000f\u0003D!!#\u0002\u000eB!ap`AF!\r\u0011\u0013Q\u0012\u0003\r\u0003\u001f\u000b))!A\u0001\u0002\u000b\u0005\u0011\u0011\u0002\u0002\u0004?\u00122\u0004BBA!\u0017\u0001\u0007!\u000bC\u0004\u0002F-\u0001\r!a\u0012\u0002\u001dA\u0014xnY3tg\u0012\u000bG/Y:fiRA\u0011\u0011TAR\u0003_\u000b\u0019\f\r\u0003\u0002\u001c\u0006}\u0005\u0003\u0002@\u0000\u0003;\u00032AIAP\t-\t\t\u000bDA\u0001\u0002\u0003\u0015\t!!\u0003\u0003\u0007}#\u0013\b\u0003\u0004|\u0019\u0001\u0007\u0011Q\u0015\u0019\u0005\u0003O\u000bY\u000b\u0005\u0003\u007f\u007f\u0006%\u0006c\u0001\u0012\u0002,\u0012a\u0011QVAR\u0003\u0003\u0005\tQ!\u0001\u0002\n\t\u0019q\f\n\u001d\t\r\u0005EF\u00021\u0001@\u0003%Ig\u000e];u\u001d\u0006lW\rC\u0004\u000262\u0001\r!a.\u0002\u0017\u0015D\b\u000f\\8eK\u000e{Gn\u001d\t\u0006\u0003s\u000b\tm\u0010\b\u0005\u0003w\u000byLD\u0002C\u0003{K\u0011AK\u0005\u0003k&JA!a1\u0002F\n\u00191+Z9\u000b\u0005UL\u0013a\u0003:fGJ,\u0017\r^3D_2$\u0002\"a3\u0002V\u0006\u0005\u0018Q\u001d\u0019\u0005\u0003\u001b\f\t\u000e\u0005\u0003\u007f\u007f\u0006=\u0007c\u0001\u0012\u0002R\u0012Y\u00111[\u0007\u0002\u0002\u0003\u0005)\u0011AA\u0005\u0005\u0011yF%M\u0019\t\rml\u0001\u0019AAla\u0011\tI.!8\u0011\ty|\u00181\u001c\t\u0004E\u0005uG\u0001DAp\u0003+\f\t\u0011!A\u0003\u0002\u0005%!\u0001B0%cABa!a9\u000e\u0001\u0004y\u0014aB2pY:\u000bW.\u001a\u0005\u0007\u0003Ol\u0001\u0019A \u0002\u0015Ql\u0007oQ8m\u001d\u0006lW-\u0001\u000bbaB\u0014x\u000e_*j[&d\u0017M]5us*{\u0017N\u001c\u000b\u000b\u0003[\f9P!\u0002\u0003\u0014\t]\u0001\u0007BAx\u0003g\u0004BA`@\u0002rB\u0019!%a=\u0005\u0017\u0005Uh\"!A\u0001\u0002\u000b\u0005\u0011\u0011\u0002\u0002\u0005?\u0012\nD\u0007C\u0004\u0002z:\u0001\r!a?\u0002\u0011\u0011\fG/Y:fi\u0006\u0003D!!@\u0003\u0002A!ap`A\u0000!\r\u0011#\u0011\u0001\u0003\r\u0005\u0007\t90!A\u0001\u0002\u000b\u0005\u0011\u0011\u0002\u0002\u0005?\u0012\n$\u0007C\u0004\u0003\b9\u0001\rA!\u0003\u0002\u0011\u0011\fG/Y:fi\n\u0003DAa\u0003\u0003\u0010A!ap B\u0007!\r\u0011#q\u0002\u0003\r\u0005#\u0011)!!A\u0001\u0002\u000b\u0005\u0011\u0011\u0002\u0002\u0005?\u0012\n4\u0007\u0003\u0004\u0003\u00169\u0001\r\u0001X\u0001\ni\"\u0014Xm\u001d5pY\u0012Da!!\u0017\u000f\u0001\u0004yD\u0003\u0003B\u000e\u0005K\u0011\tD!\u00101\t\tu!\u0011\u0005\t\u0005}~\u0014y\u0002E\u0002#\u0005C!1Ba\t\u0010\u0003\u0003\u0005\tQ!\u0001\u0002\n\t!q\fJ\u00198\u0011\u001d\tIp\u0004a\u0001\u0005O\u0001DA!\u000b\u0003.A!ap B\u0016!\r\u0011#Q\u0006\u0003\r\u0005_\u0011)#!A\u0001\u0002\u000b\u0005\u0011\u0011\u0002\u0002\u0005?\u0012\nT\u0007C\u0004\u0003\b=\u0001\rAa\r1\t\tU\"\u0011\b\t\u0005}~\u00149\u0004E\u0002#\u0005s!ABa\u000f\u00032\u0005\u0005\t\u0011!B\u0001\u0003\u0013\u0011Aa\u0018\u00132m!1!QC\bA\u0002q\u0003"
)
public abstract class LSHModel extends Model implements LSHParams, MLWritable {
   private IntParam numHashTables;
   private Param outputCol;
   private Param inputCol;

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final int getNumHashTables() {
      return LSHParams.getNumHashTables$(this);
   }

   public final StructType validateAndTransformSchema(final StructType schema) {
      return LSHParams.validateAndTransformSchema$(this, schema);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public final IntParam numHashTables() {
      return this.numHashTables;
   }

   public final void org$apache$spark$ml$feature$LSHParams$_setter_$numHashTables_$eq(final IntParam x$1) {
      this.numHashTables = x$1;
   }

   public final Param outputCol() {
      return this.outputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCol$_setter_$outputCol_$eq(final Param x$1) {
      this.outputCol = x$1;
   }

   public final Param inputCol() {
      return this.inputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCol$_setter_$inputCol_$eq(final Param x$1) {
      this.inputCol = x$1;
   }

   public LSHModel setInputCol(final String value) {
      return (LSHModel)this.set(this.inputCol(), value);
   }

   public LSHModel setOutputCol(final String value) {
      return (LSHModel)this.set(this.outputCol(), value);
   }

   public abstract Vector[] hashFunction(final Vector elems);

   public abstract double keyDistance(final Vector x, final Vector y);

   public abstract double hashDistance(final Vector[] x, final Vector[] y);

   public Dataset transform(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      functions var10000 = .MODULE$;
      Function1 var10001 = (x$1) -> this.hashFunction(x$1);
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(LSHModel.class.getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$));
         }

         public $typecreator1$1() {
         }
      }

      TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1());
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(LSHModel.class.getClassLoader());

      final class $typecreator2$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator2$1() {
         }
      }

      UserDefinedFunction transformUDF = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
      return dataset.withColumn((String)this.$(this.outputCol()), transformUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{dataset.apply((String)this.$(this.inputCol()))}))));
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateAndTransformSchema(schema);
   }

   public Dataset approxNearestNeighbors(final Dataset dataset, final Vector key, final int numNearestNeighbors, final boolean singleProbe, final String distCol) {
      scala.Predef..MODULE$.require(numNearestNeighbors > 0, () -> "The number of nearest neighbors cannot be less than 1");
      Vector[] keyHash = this.hashFunction(key);
      Dataset modelDataset = !scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])dataset.columns()), this.$(this.outputCol())) ? this.transform(dataset) : dataset.toDF();
      Dataset var36;
      if (singleProbe) {
         functions var10000 = .MODULE$;
         Function1 var10001 = (x) -> BoxesRunTime.boxToBoolean($anonfun$approxNearestNeighbors$3(keyHash, x));
         TypeTags.TypeTag var10002 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Boolean();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(LSHModel.class.getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$2() {
            }
         }

         UserDefinedFunction sameBucketWithKeyUDF = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2()));
         var36 = modelDataset.filter(sameBucketWithKeyUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.col((String)this.$(this.outputCol()))}))));
      } else {
         functions var37 = .MODULE$;
         Function1 var39 = (x) -> BoxesRunTime.boxToDouble($anonfun$approxNearestNeighbors$4(this, keyHash, x));
         TypeTags.TypeTag var41 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(LSHModel.class.getClassLoader());

         final class $typecreator2$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator2$2() {
            }
         }

         UserDefinedFunction hashDistUDF = var37.udf(var39, var41, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$2()));
         Column hashDistCol = hashDistUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.col((String)this.$(this.outputCol()))})));
         Dataset modelDatasetWithDist = modelDataset.withColumn(distCol, hashDistCol);
         double relativeError = 0.05;
         RDD qual$1 = modelDatasetWithDist.select(distCol, scala.collection.immutable.Nil..MODULE$).rdd();
         Function1 x$1 = (iter) -> {
            if (iter.hasNext()) {
               QuantileSummaries s = new QuantileSummaries(org.apache.spark.sql.catalyst.util.QuantileSummaries..MODULE$.defaultCompressThreshold(), relativeError, org.apache.spark.sql.catalyst.util.QuantileSummaries..MODULE$.$lessinit$greater$default$3(), org.apache.spark.sql.catalyst.util.QuantileSummaries..MODULE$.$lessinit$greater$default$4(), org.apache.spark.sql.catalyst.util.QuantileSummaries..MODULE$.$lessinit$greater$default$5());

               while(iter.hasNext()) {
                  Row row = (Row)iter.next();
                  if (!row.isNullAt(0)) {
                     double v = row.getDouble(0);
                     if (!Double.isNaN(v)) {
                        s = s.insert(v);
                     }
                  }
               }

               return scala.package..MODULE$.Iterator().single(s.compress());
            } else {
               return scala.package..MODULE$.Iterator().empty();
            }
         };
         boolean x$2 = qual$1.mapPartitions$default$2();
         RDD qual$2 = qual$1.mapPartitions(x$1, x$2, scala.reflect.ClassTag..MODULE$.apply(QuantileSummaries.class));
         Function2 x$3 = (s1, s2) -> s1.merge(s2);
         int x$4 = qual$2.treeReduce$default$2();
         QuantileSummaries summary = (QuantileSummaries)qual$2.treeReduce(x$3, x$4);
         long count = summary.count();
         double approxQuantile = (double)numNearestNeighbors / (double)count + relativeError;
         if (approxQuantile >= (double)1) {
            var36 = modelDatasetWithDist;
         } else {
            double hashThreshold = BoxesRunTime.unboxToDouble(summary.query(approxQuantile).get());
            var36 = modelDatasetWithDist.filter(hashDistCol.$less$eq(BoxesRunTime.boxToDouble(hashThreshold)));
         }
      }

      Dataset modelSubset = var36;
      functions var38 = .MODULE$;
      Function1 var40 = (x) -> BoxesRunTime.boxToDouble($anonfun$approxNearestNeighbors$7(this, key, x));
      TypeTags.TypeTag var42 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(LSHModel.class.getClassLoader());

      final class $typecreator3$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator3$1() {
         }
      }

      UserDefinedFunction keyDistUDF = var38.udf(var40, var42, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator3$1()));
      Dataset modelSubsetWithDistCol = modelSubset.withColumn(distCol, keyDistUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.col((String)this.$(this.inputCol()))}))));
      return modelSubsetWithDistCol.sort(distCol, scala.collection.immutable.Nil..MODULE$).limit(numNearestNeighbors);
   }

   public Dataset approxNearestNeighbors(final Dataset dataset, final Vector key, final int numNearestNeighbors, final String distCol) {
      return this.approxNearestNeighbors(dataset, key, numNearestNeighbors, true, distCol);
   }

   public Dataset approxNearestNeighbors(final Dataset dataset, final Vector key, final int numNearestNeighbors) {
      return this.approxNearestNeighbors(dataset, key, numNearestNeighbors, true, "distCol");
   }

   private Dataset processDataset(final Dataset dataset, final String inputName, final Seq explodeCols) {
      scala.Predef..MODULE$.require(explodeCols.size() == 2, () -> "explodeCols must be two strings.");
      Dataset modelDataset = !scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])dataset.columns()), this.$(this.outputCol())) ? this.transform(dataset) : dataset.toDF();
      return modelDataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.struct(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.col("*")}))).as(inputName), .MODULE$.posexplode(.MODULE$.col((String)this.$(this.outputCol()))).as(explodeCols)})));
   }

   private Dataset recreateCol(final Dataset dataset, final String colName, final String tmpColName) {
      return dataset.withColumnRenamed(colName, tmpColName).withColumn(colName, .MODULE$.col(tmpColName)).drop(tmpColName);
   }

   public Dataset approxSimilarityJoin(final Dataset datasetA, final Dataset datasetB, final double threshold, final String distCol) {
      String leftColName;
      String rightColName;
      Seq explodeCols;
      Dataset explodedA;
      Dataset var10000;
      label17: {
         label16: {
            leftColName = "datasetA";
            rightColName = "datasetB";
            explodeCols = new scala.collection.immutable..colon.colon("entry", new scala.collection.immutable..colon.colon("hashValue", scala.collection.immutable.Nil..MODULE$));
            explodedA = this.processDataset(datasetA, leftColName, explodeCols);
            if (datasetA == null) {
               if (datasetB != null) {
                  break label16;
               }
            } else if (!datasetA.equals(datasetB)) {
               break label16;
            }

            Dataset recreatedB = this.recreateCol(datasetB, (String)this.$(this.inputCol()), Identifiable$.MODULE$.randomUID(this.inputCol().name()));
            var10000 = this.processDataset(recreatedB, rightColName, explodeCols);
            break label17;
         }

         var10000 = this.processDataset(datasetB, rightColName, explodeCols);
      }

      Dataset explodedB = var10000;
      Dataset joinedDataset = explodedA.join(explodedB, explodeCols).drop(explodeCols).distinct();
      functions var20 = .MODULE$;
      Function2 var10001 = (x, y) -> BoxesRunTime.boxToDouble($anonfun$approxSimilarityJoin$1(this, x, y));
      TypeTags.TypeTag var10002 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(LSHModel.class.getClassLoader());

      final class $typecreator1$3 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator1$3() {
         }
      }

      TypeTags.TypeTag var10003 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$3());
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(LSHModel.class.getClassLoader());

      final class $typecreator2$3 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator2$3() {
         }
      }

      UserDefinedFunction distUDF = var20.udf(var10001, var10002, var10003, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$3()));
      Dataset joinedDatasetWithDist = joinedDataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.col("*"), distUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.col(leftColName + "." + this.$(this.inputCol())), .MODULE$.col(rightColName + "." + this.$(this.inputCol()))}))).as(distCol)})));
      return joinedDatasetWithDist.filter(.MODULE$.col(distCol).$less(BoxesRunTime.boxToDouble(threshold)));
   }

   public Dataset approxSimilarityJoin(final Dataset datasetA, final Dataset datasetB, final double threshold) {
      return this.approxSimilarityJoin(datasetA, datasetB, threshold, "distCol");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$approxNearestNeighbors$2(final Tuple2 tuple) {
      return BoxesRunTime.equals(tuple._1(), tuple._2());
   }

   private static final boolean sameBucket$1(final Vector[] x, final Vector[] y) {
      return scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.refArrayOps((Object[])x)).zip(scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.refArrayOps((Object[])y))).exists((tuple) -> BoxesRunTime.boxToBoolean($anonfun$approxNearestNeighbors$2(tuple)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$approxNearestNeighbors$3(final Vector[] keyHash$1, final Vector[] x) {
      return sameBucket$1(x, keyHash$1);
   }

   // $FF: synthetic method
   public static final double $anonfun$approxNearestNeighbors$4(final LSHModel $this, final Vector[] keyHash$1, final Vector[] x) {
      return $this.hashDistance(x, keyHash$1);
   }

   // $FF: synthetic method
   public static final double $anonfun$approxNearestNeighbors$7(final LSHModel $this, final Vector key$1, final Vector x) {
      return $this.keyDistance(x, key$1);
   }

   // $FF: synthetic method
   public static final double $anonfun$approxSimilarityJoin$1(final LSHModel $this, final Vector x, final Vector y) {
      return $this.keyDistance(x, y);
   }

   public LSHModel() {
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      LSHParams.$init$(this);
      MLWritable.$init$(this);
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
