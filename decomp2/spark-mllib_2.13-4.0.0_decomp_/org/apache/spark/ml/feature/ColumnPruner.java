package org.apache.spark.ml.feature;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Transformer;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t5d\u0001B\u00181\tmB\u0001B\u0012\u0001\u0003\u0006\u0004%\te\u0012\u0005\t+\u0002\u0011\t\u0011)A\u0005\u0011\"Aa\u000b\u0001BC\u0002\u0013\u0005q\u000b\u0003\u0005\\\u0001\t\u0005\t\u0015!\u0003Y\u0011\u0015a\u0006\u0001\"\u0001^\u0011\u0015a\u0006\u0001\"\u0001c\u0011\u0015!\u0007\u0001\"\u0011f\u0011\u001d\t)\u0002\u0001C!\u0003/Aq!!\u000b\u0001\t\u0003\nY\u0003C\u0004\u0002>\u0001!\t%a\u0010\b\u000f\u0005\u001d\u0003\u0007#\u0003\u0002J\u00191q\u0006\rE\u0005\u0003\u0017Ba\u0001\u0018\u0007\u0005\u0002\u0005%\u0004bBA6\u0019\u0011\u0005\u0013Q\u000e\u0005\b\u0003kbA\u0011IA<\r\u001d\ti\b\u0004\u0001\r\u0003\u007fB\u0011\"!!\u0011\u0005\u0003\u0005\u000b\u0011\u00020\t\rq\u0003B\u0011AAB\r\u0019\tY\t\u0005#\u0002\u000e\"Iak\u0005BK\u0002\u0013\u0005\u00111\u0015\u0005\n7N\u0011\t\u0012)A\u0005\u0003KCa\u0001X\n\u0005\u0002\u0005-\u0006\"CA\u0015'\u0005\u0005I\u0011AAZ\u0011%\t9lEI\u0001\n\u0003\tI\fC\u0005\u0002PN\t\t\u0011\"\u0011\u0002R\"I\u0011Q\\\n\u0002\u0002\u0013\u0005\u0011q\u001c\u0005\n\u0003O\u001c\u0012\u0011!C\u0001\u0003SD\u0011\"a<\u0014\u0003\u0003%\t%!=\t\u0013\u0005}8#!A\u0005\u0002\t\u0005\u0001\"\u0003B\u0006'\u0005\u0005I\u0011\tB\u0007\u0011%\u0011\tbEA\u0001\n\u0003\u0012\u0019\u0002C\u0005\u0003\u0016M\t\t\u0011\"\u0011\u0003\u0018!I!\u0011D\n\u0002\u0002\u0013\u0005#1D\u0004\n\u0005?\u0001\u0012\u0011!E\u0005\u0005C1\u0011\"a#\u0011\u0003\u0003EIAa\t\t\rq\u001bC\u0011\u0001B\u0019\u0011%\u0011)bIA\u0001\n\u000b\u00129\u0002C\u0005\u00034\r\n\t\u0011\"!\u00036!I!\u0011H\u0012\u0002\u0002\u0013\u0005%1\b\u0005\b\u0005\u000f\u0002B\u0011\u000bB%\r\u0019\u0011\u0019\u0006\u0004\u0003\u0003V!1A,\u000bC\u0001\u0005/B\u0011Ba\u0017*\u0005\u0004%I!!5\t\u0011\tu\u0013\u0006)A\u0005\u0003'Dq!!\u001e*\t\u0003\u0012y\u0006C\u0005\u0003d1\t\t\u0011\"\u0003\u0003f\ta1i\u001c7v[:\u0004&/\u001e8fe*\u0011\u0011GM\u0001\bM\u0016\fG/\u001e:f\u0015\t\u0019D'\u0001\u0002nY*\u0011QGN\u0001\u0006gB\f'o\u001b\u0006\u0003oa\na!\u00199bG\",'\"A\u001d\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001a\u0004\t\u0005\u0002>}5\t!'\u0003\u0002@e\tYAK]1og\u001a|'/\\3s!\t\tE)D\u0001C\u0015\t\u0019%'\u0001\u0003vi&d\u0017BA#C\u0005)iEj\u0016:ji\u0006\u0014G.Z\u0001\u0004k&$W#\u0001%\u0011\u0005%\u0013fB\u0001&Q!\tYe*D\u0001M\u0015\ti%(\u0001\u0004=e>|GO\u0010\u0006\u0002\u001f\u0006)1oY1mC&\u0011\u0011KT\u0001\u0007!J,G-\u001a4\n\u0005M#&AB*ue&twM\u0003\u0002R\u001d\u0006!Q/\u001b3!\u00039\u0019w\u000e\\;n]N$v\u000e\u0015:v]\u0016,\u0012\u0001\u0017\t\u0004\u0013fC\u0015B\u0001.U\u0005\r\u0019V\r^\u0001\u0010G>dW/\u001c8t)>\u0004&/\u001e8fA\u00051A(\u001b8jiz\"2A\u00181b!\ty\u0006!D\u00011\u0011\u00151U\u00011\u0001I\u0011\u00151V\u00011\u0001Y)\tq6\rC\u0003W\r\u0001\u0007\u0001,A\u0005ue\u0006t7OZ8s[R\u0011am\u001e\t\u0003ORt!\u0001[9\u000f\u0005%|gB\u00016o\u001d\tYWN\u0004\u0002LY&\t\u0011(\u0003\u00028q%\u0011QGN\u0005\u0003aR\n1a]9m\u0013\t\u00118/A\u0004qC\u000e\\\u0017mZ3\u000b\u0005A$\u0014BA;w\u0005%!\u0015\r^1Ge\u0006lWM\u0003\u0002sg\")\u0001p\u0002a\u0001s\u00069A-\u0019;bg\u0016$\bg\u0001>\u0002\u0002A\u00191\u0010 @\u000e\u0003ML!!`:\u0003\u000f\u0011\u000bG/Y:fiB\u0019q0!\u0001\r\u0001\u0011Y\u00111A<\u0002\u0002\u0003\u0005)\u0011AA\u0003\u0005\ryF\u0005N\t\u0005\u0003\u000f\ty\u0001\u0005\u0003\u0002\n\u0005-Q\"\u0001(\n\u0007\u00055aJA\u0004O_RD\u0017N\\4\u0011\t\u0005%\u0011\u0011C\u0005\u0004\u0003'q%aA!os\u0006yAO]1og\u001a|'/\\*dQ\u0016l\u0017\r\u0006\u0003\u0002\u001a\u0005\u0015\u0002\u0003BA\u000e\u0003Ci!!!\b\u000b\u0007\u0005}1/A\u0003usB,7/\u0003\u0003\u0002$\u0005u!AC*ueV\u001cG\u000fV=qK\"9\u0011q\u0005\u0005A\u0002\u0005e\u0011AB:dQ\u0016l\u0017-\u0001\u0003d_BLHc\u00010\u0002.!9\u0011qF\u0005A\u0002\u0005E\u0012!B3yiJ\f\u0007\u0003BA\u001a\u0003si!!!\u000e\u000b\u0007\u0005]\"'A\u0003qCJ\fW.\u0003\u0003\u0002<\u0005U\"\u0001\u0003)be\u0006lW*\u00199\u0002\u000b]\u0014\u0018\u000e^3\u0016\u0005\u0005\u0005\u0003cA!\u0002D%\u0019\u0011Q\t\"\u0003\u00115cuK]5uKJ\fAbQ8mk6t\u0007K];oKJ\u0004\"a\u0018\u0007\u0014\u000f1\ti%a\u0015\u0002ZA!\u0011\u0011BA(\u0013\r\t\tF\u0014\u0002\u0007\u0003:L(+\u001a4\u0011\t\u0005\u000b)FX\u0005\u0004\u0003/\u0012%AC'M%\u0016\fG-\u00192mKB!\u00111LA3\u001b\t\tiF\u0003\u0003\u0002`\u0005\u0005\u0014AA5p\u0015\t\t\u0019'\u0001\u0003kCZ\f\u0017\u0002BA4\u0003;\u0012AbU3sS\u0006d\u0017N_1cY\u0016$\"!!\u0013\u0002\tI,\u0017\rZ\u000b\u0003\u0003_\u0002B!QA9=&\u0019\u00111\u000f\"\u0003\u00115c%+Z1eKJ\fA\u0001\\8bIR\u0019a,!\u001f\t\r\u0005mt\u00021\u0001I\u0003\u0011\u0001\u0018\r\u001e5\u0003%\r{G.^7o!J,h.\u001a:Xe&$XM]\n\u0004!\u0005\u0005\u0013\u0001C5ogR\fgnY3\u0015\t\u0005\u0015\u0015\u0011\u0012\t\u0004\u0003\u000f\u0003R\"\u0001\u0007\t\r\u0005\u0005%\u00031\u0001_\u0005\u0011!\u0015\r^1\u0014\u000fM\ti%a$\u0002\u0016B!\u0011\u0011BAI\u0013\r\t\u0019J\u0014\u0002\b!J|G-^2u!\u0011\t9*a(\u000f\t\u0005e\u0015Q\u0014\b\u0004\u0017\u0006m\u0015\"A(\n\u0005It\u0015\u0002BA4\u0003CS!A\u001d(\u0016\u0005\u0005\u0015\u0006#BAL\u0003OC\u0015\u0002BAU\u0003C\u00131aU3r)\u0011\ti+!-\u0011\u0007\u0005=6#D\u0001\u0011\u0011\u00191f\u00031\u0001\u0002&R!\u0011QVA[\u0011!1v\u0003%AA\u0002\u0005\u0015\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0003wSC!!*\u0002>.\u0012\u0011q\u0018\t\u0005\u0003\u0003\fY-\u0004\u0002\u0002D*!\u0011QYAd\u0003%)hn\u00195fG.,GMC\u0002\u0002J:\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\ti-a1\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003'\u0004B!!6\u0002\\6\u0011\u0011q\u001b\u0006\u0005\u00033\f\t'\u0001\u0003mC:<\u0017bA*\u0002X\u0006a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u0011\u0011\u001d\t\u0005\u0003\u0013\t\u0019/C\u0002\u0002f:\u00131!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u0004\u0002l\"I\u0011Q^\u000e\u0002\u0002\u0003\u0007\u0011\u0011]\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005M\bCBA{\u0003w\fy!\u0004\u0002\u0002x*\u0019\u0011\u0011 (\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002~\u0006](\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$BAa\u0001\u0003\nA!\u0011\u0011\u0002B\u0003\u0013\r\u00119A\u0014\u0002\b\u0005>|G.Z1o\u0011%\ti/HA\u0001\u0002\u0004\ty!\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BAj\u0005\u001fA\u0011\"!<\u001f\u0003\u0003\u0005\r!!9\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!9\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a5\u0002\r\u0015\fX/\u00197t)\u0011\u0011\u0019A!\b\t\u0013\u00055\u0018%!AA\u0002\u0005=\u0011\u0001\u0002#bi\u0006\u00042!a,$'\u0015\u0019#QEA-!!\u00119C!\f\u0002&\u00065VB\u0001B\u0015\u0015\r\u0011YCT\u0001\beVtG/[7f\u0013\u0011\u0011yC!\u000b\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0006\u0002\u0003\"\u0005)\u0011\r\u001d9msR!\u0011Q\u0016B\u001c\u0011\u00191f\u00051\u0001\u0002&\u00069QO\\1qa2LH\u0003\u0002B\u001f\u0005\u0007\u0002b!!\u0003\u0003@\u0005\u0015\u0016b\u0001B!\u001d\n1q\n\u001d;j_:D\u0011B!\u0012(\u0003\u0003\u0005\r!!,\u0002\u0007a$\u0003'\u0001\u0005tCZ,\u0017*\u001c9m)\u0011\u0011YE!\u0015\u0011\t\u0005%!QJ\u0005\u0004\u0005\u001fr%\u0001B+oSRDa!a\u001f)\u0001\u0004A%AE\"pYVlg\u000e\u0015:v]\u0016\u0014(+Z1eKJ\u001c2!KA8)\t\u0011I\u0006E\u0002\u0002\b&\n\u0011b\u00197bgNt\u0015-\\3\u0002\u0015\rd\u0017m]:OC6,\u0007\u0005F\u0002_\u0005CBa!a\u001f.\u0001\u0004A\u0015\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B4!\u0011\t)N!\u001b\n\t\t-\u0014q\u001b\u0002\u0007\u001f\nTWm\u0019;"
)
public class ColumnPruner extends Transformer implements MLWritable {
   private final String uid;
   private final Set columnsToPrune;

   public static ColumnPruner load(final String path) {
      return ColumnPruner$.MODULE$.load(path);
   }

   public static MLReader read() {
      return ColumnPruner$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public String uid() {
      return this.uid;
   }

   public Set columnsToPrune() {
      return this.columnsToPrune;
   }

   public Dataset transform(final Dataset dataset) {
      String[] columnsToKeep = (String[]).MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])dataset.columns()), (x$4) -> BoxesRunTime.boxToBoolean($anonfun$transform$1(this, x$4)));
      return dataset.select(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])columnsToKeep), (colName) -> dataset.col(colName), scala.reflect.ClassTag..MODULE$.apply(Column.class))).toImmutableArraySeq());
   }

   public StructType transformSchema(final StructType schema) {
      return new StructType((StructField[]).MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema.fields()), (col) -> BoxesRunTime.boxToBoolean($anonfun$transformSchema$2(this, col))));
   }

   public ColumnPruner copy(final ParamMap extra) {
      return (ColumnPruner)this.defaultCopy(extra);
   }

   public MLWriter write() {
      return new ColumnPrunerWriter(this);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$transform$1(final ColumnPruner $this, final String x$4) {
      return !$this.columnsToPrune().contains(x$4);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$transformSchema$2(final ColumnPruner $this, final StructField col) {
      return !$this.columnsToPrune().contains(col.name());
   }

   public ColumnPruner(final String uid, final Set columnsToPrune) {
      this.uid = uid;
      this.columnsToPrune = columnsToPrune;
      MLWritable.$init$(this);
   }

   public ColumnPruner(final Set columnsToPrune) {
      this(Identifiable$.MODULE$.randomUID("columnPruner"), columnsToPrune);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class ColumnPrunerWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final ColumnPruner instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(this.instance.columnsToPrune().toSeq());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(ColumnPrunerWriter.class.getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.feature.ColumnPruner.ColumnPrunerWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.feature.ColumnPruner.ColumnPrunerWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$2() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2())).write().parquet(dataPath);
      }

      private final void Data$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.Data$module == null) {
               this.Data$module = new Data$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      public ColumnPrunerWriter(final ColumnPruner instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final Seq columnsToPrune;
         // $FF: synthetic field
         public final ColumnPrunerWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public Seq columnsToPrune() {
            return this.columnsToPrune;
         }

         public Data copy(final Seq columnsToPrune) {
            return this.org$apache$spark$ml$feature$ColumnPruner$ColumnPrunerWriter$Data$$$outer().new Data(columnsToPrune);
         }

         public Seq copy$default$1() {
            return this.columnsToPrune();
         }

         public String productPrefix() {
            return "Data";
         }

         public int productArity() {
            return 1;
         }

         public Object productElement(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return this.columnsToPrune();
               }
               default -> {
                  return Statics.ioobe(x$1);
               }
            }
         }

         public Iterator productIterator() {
            return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
         }

         public boolean canEqual(final Object x$1) {
            return x$1 instanceof Data;
         }

         public String productElementName(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return "columnsToPrune";
               }
               default -> {
                  return (String)Statics.ioobe(x$1);
               }
            }
         }

         public int hashCode() {
            return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var6;
            if (this != x$1) {
               label52: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$feature$ColumnPruner$ColumnPrunerWriter$Data$$$outer() == this.org$apache$spark$ml$feature$ColumnPruner$ColumnPrunerWriter$Data$$$outer()) {
                     label42: {
                        Data var4 = (Data)x$1;
                        Seq var10000 = this.columnsToPrune();
                        Seq var5 = var4.columnsToPrune();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label42;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label42;
                        }

                        if (var4.canEqual(this)) {
                           break label52;
                        }
                     }
                  }

                  var6 = false;
                  return var6;
               }
            }

            var6 = true;
            return var6;
         }

         // $FF: synthetic method
         public ColumnPrunerWriter org$apache$spark$ml$feature$ColumnPruner$ColumnPrunerWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final Seq columnsToPrune) {
            this.columnsToPrune = columnsToPrune;
            if (ColumnPrunerWriter.this == null) {
               throw null;
            } else {
               this.$outer = ColumnPrunerWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction1 implements Serializable {
         // $FF: synthetic field
         private final ColumnPrunerWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final Seq columnsToPrune) {
            return this.$outer.new Data(columnsToPrune);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.columnsToPrune()));
         }

         public Data$() {
            if (ColumnPrunerWriter.this == null) {
               throw null;
            } else {
               this.$outer = ColumnPrunerWriter.this;
               super();
            }
         }
      }
   }

   private static class ColumnPrunerReader extends MLReader {
      private final String className = ColumnPruner.class.getName();

      private String className() {
         return this.className;
      }

      public ColumnPruner load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Row data = (Row)this.sparkSession().read().parquet(dataPath).select("columnsToPrune", scala.collection.immutable.Nil..MODULE$).head();
         Set columnsToPrune = ((IterableOnceOps)data.getAs(0)).toSet();
         ColumnPruner pruner = new ColumnPruner(metadata.uid(), columnsToPrune);
         metadata.getAndSetParams(pruner, metadata.getAndSetParams$default$2());
         return pruner;
      }

      public ColumnPrunerReader() {
      }
   }
}
