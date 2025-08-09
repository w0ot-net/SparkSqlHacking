package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Array.;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction1;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tud\u0001\u0002\u001a4\u0005yB\u0001\u0002\u0012\u0001\u0003\u0006\u0004%\t%\u0012\u0005\n9\u0002\u0011\t\u0011)A\u0005\rvC\u0001b\u0018\u0001\u0003\u0006\u0004%\t\u0005\u0019\u0005\nW\u0002\u0011\t\u0011)A\u0005C2DaA\u001c\u0001\u0005\u0002Uz\u0007B\u00028\u0001\t\u0003)D\u000fC\u0003v\u0001\u0011Ec\u000fC\u0003{\u0001\u0011\u00053\u0010C\u0004\u0002\u0002\u0001!\t%a\u0001\t\u000f\u0005%\u0001\u0001\"\u0011\u0002\f!9\u00111\u0005\u0001\u0005B\u0005\u0015\u0002bBA\u001d\u0001\u0011\u0005\u00131\b\u0005\b\u0003\u0017\u0002A\u0011IA'\u000f\u001d\t9f\rE\u0001\u000332aAM\u001a\t\u0002\u0005m\u0003B\u00028\u0010\t\u0003\tIH\u0002\u0004\u0002|=\u0001\u0011Q\u0010\u0005\n\u0003\u007f\n\"\u0011!Q\u0001\n\rCaA\\\t\u0005\u0002\u0005\u0005eABAE#\u0011\u000bY\tC\u0005`)\tU\r\u0011\"\u0001\u0002$\"I1\u000e\u0006B\tB\u0003%\u0011Q\u0015\u0005\u0007]R!\t!a+\t\u0013\u0005\rB#!A\u0005\u0002\u0005M\u0006\"CA\\)E\u0005I\u0011AA]\u0011%\ti\rFA\u0001\n\u0003\ny\rC\u0005\u0002\\R\t\t\u0011\"\u0001\u0002^\"I\u0011q\u001c\u000b\u0002\u0002\u0013\u0005\u0011\u0011\u001d\u0005\n\u0003[$\u0012\u0011!C!\u0003_D\u0011\"!@\u0015\u0003\u0003%\t!a@\t\u0013\t\rA#!A\u0005B\t\u0015\u0001\"\u0003B\u0005)\u0005\u0005I\u0011\tB\u0006\u0011%\tY\u0005FA\u0001\n\u0003\u0012i\u0001C\u0005\u0003\u0010Q\t\t\u0011\"\u0011\u0003\u0012\u001dI!QC\t\u0002\u0002#%!q\u0003\u0004\n\u0003\u0013\u000b\u0012\u0011!E\u0005\u00053AaA\u001c\u0013\u0005\u0002\t\u001d\u0002\"CA&I\u0005\u0005IQ\tB\u0007\u0011%\u0011I\u0003JA\u0001\n\u0003\u0013Y\u0003C\u0005\u00030\u0011\n\t\u0011\"!\u00032!9!QH\t\u0005R\t}bA\u0002B&\u001f\u0011\u0011i\u0005\u0003\u0004oU\u0011\u0005!Q\u000b\u0005\n\u00053R#\u0019!C\u0005\u0003\u001fD\u0001Ba\u0017+A\u0003%\u0011\u0011\u001b\u0005\b\u0005;RC\u0011\tB0\u0011\u001d\u0011\u0019g\u0004C!\u0005KBqA!\u0018\u0010\t\u0003\u0012I\u0007C\u0005\u0003p=\t\t\u0011\"\u0003\u0003r\t\u00112\t[5TcN+G.Z2u_Jlu\u000eZ3m\u0015\t!T'A\u0004gK\u0006$XO]3\u000b\u0005Y:\u0014AA7m\u0015\tA\u0014(A\u0003ta\u0006\u00148N\u0003\u0002;w\u00051\u0011\r]1dQ\u0016T\u0011\u0001P\u0001\u0004_J<7\u0001A\n\u0003\u0001}\u00022\u0001Q!D\u001b\u0005\u0019\u0014B\u0001\"4\u00055\u0019V\r\\3di>\u0014Xj\u001c3fYB\u0011\u0001\tA\u0001\u0004k&$W#\u0001$\u0011\u0005\u001d\u0003fB\u0001%O!\tIE*D\u0001K\u0015\tYU(\u0001\u0004=e>|GO\u0010\u0006\u0002\u001b\u0006)1oY1mC&\u0011q\nT\u0001\u0007!J,G-\u001a4\n\u0005E\u0013&AB*ue&twM\u0003\u0002P\u0019\"\u001a\u0011\u0001\u0016.\u0011\u0005UCV\"\u0001,\u000b\u0005];\u0014AC1o]>$\u0018\r^5p]&\u0011\u0011L\u0016\u0002\u0006'&t7-Z\u0011\u00027\u0006)\u0011G\f\u001c/a\u0005!Q/\u001b3!\u0013\t!\u0015\tK\u0002\u0003)j\u000b\u0001c]3mK\u000e$X\r\u001a$fCR,(/Z:\u0016\u0003\u0005\u00042AY2f\u001b\u0005a\u0015B\u00013M\u0005\u0015\t%O]1z!\t\u0011g-\u0003\u0002h\u0019\n\u0019\u0011J\u001c;)\u0007\r!\u0016.I\u0001k\u0003\u0015\u0019d&\r\u00181\u0003E\u0019X\r\\3di\u0016$g)Z1ukJ,7\u000fI\u0005\u0003?\u0006C3\u0001\u0002+j\u0003\u0019a\u0014N\\5u}Q\u00191\t\u001d:\t\u000b\u0011+\u0001\u0019\u0001$)\u0007A$&\fC\u0003`\u000b\u0001\u0007\u0011\rK\u0002s)&$\u0012aQ\u0001\u0013SNtU/\\3sS\u000e\fE\u000f\u001e:jEV$X-F\u0001x!\t\u0011\u00070\u0003\u0002z\u0019\n9!i\\8mK\u0006t\u0017AD:fi\u001a+\u0017\r^;sKN\u001cu\u000e\u001c\u000b\u0003yvl\u0011\u0001\u0001\u0005\u0006}\"\u0001\rAR\u0001\u0006m\u0006dW/\u001a\u0015\u0004\u0011QS\u0016\u0001D:fi>+H\u000f];u\u0007>dGc\u0001?\u0002\u0006!)a0\u0003a\u0001\r\"\u001a\u0011\u0002\u0016.\u0002\u001fQ\u0014\u0018M\\:g_Jl7k\u00195f[\u0006$B!!\u0004\u0002\u001eA!\u0011qBA\r\u001b\t\t\tB\u0003\u0003\u0002\u0014\u0005U\u0011!\u0002;za\u0016\u001c(bAA\fo\u0005\u00191/\u001d7\n\t\u0005m\u0011\u0011\u0003\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007bBA\u0010\u0015\u0001\u0007\u0011QB\u0001\u0007g\u000eDW-\\1)\u0007)!&,\u0001\u0003d_BLHcA\"\u0002(!9\u0011\u0011F\u0006A\u0002\u0005-\u0012!B3yiJ\f\u0007\u0003BA\u0017\u0003gi!!a\f\u000b\u0007\u0005ER'A\u0003qCJ\fW.\u0003\u0003\u00026\u0005=\"\u0001\u0003)be\u0006lW*\u00199)\u0007-!&,A\u0003xe&$X-\u0006\u0002\u0002>A!\u0011qHA#\u001b\t\t\tEC\u0002\u0002DU\nA!\u001e;jY&!\u0011qIA!\u0005!iEj\u0016:ji\u0016\u0014\bf\u0001\u0007U5\u0006AAo\\*ue&tw\rF\u0001GQ\u0011iA+!\u0015\"\u0005\u0005M\u0013!B\u001a/a9\u0002\u0004f\u0001\u0001U5\u0006\u00112\t[5TcN+G.Z2u_Jlu\u000eZ3m!\t\u0001ubE\u0004\u0010\u0003;\n\u0019'!\u001b\u0011\u0007\t\fy&C\u0002\u0002b1\u0013a!\u00118z%\u00164\u0007#BA \u0003K\u001a\u0015\u0002BA4\u0003\u0003\u0012!\"\u0014'SK\u0006$\u0017M\u00197f!\u0011\tY'!\u001e\u000e\u0005\u00055$\u0002BA8\u0003c\n!![8\u000b\u0005\u0005M\u0014\u0001\u00026bm\u0006LA!a\u001e\u0002n\ta1+\u001a:jC2L'0\u00192mKR\u0011\u0011\u0011\f\u0002\u0019\u0007\"L7+]*fY\u0016\u001cGo\u001c:N_\u0012,Gn\u0016:ji\u0016\u00148cA\t\u0002>\u0005A\u0011N\\:uC:\u001cW\r\u0006\u0003\u0002\u0004\u0006\u001d\u0005cAAC#5\tq\u0002\u0003\u0004\u0002\u0000M\u0001\ra\u0011\u0002\u0005\t\u0006$\u0018mE\u0004\u0015\u0003;\ni)a%\u0011\u0007\t\fy)C\u0002\u0002\u00122\u0013q\u0001\u0015:pIV\u001cG\u000f\u0005\u0003\u0002\u0016\u0006}e\u0002BAL\u00037s1!SAM\u0013\u0005i\u0015bAAO\u0019\u00069\u0001/Y2lC\u001e,\u0017\u0002BA<\u0003CS1!!(M+\t\t)\u000bE\u0003\u0002\u0016\u0006\u001dV-\u0003\u0003\u0002*\u0006\u0005&aA*fcR!\u0011QVAY!\r\ty\u000bF\u0007\u0002#!1ql\u0006a\u0001\u0003K#B!!,\u00026\"Aq\f\u0007I\u0001\u0002\u0004\t)+\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005m&\u0006BAS\u0003{[#!a0\u0011\t\u0005\u0005\u0017\u0011Z\u0007\u0003\u0003\u0007TA!!2\u0002H\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003/2KA!a3\u0002D\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\t\t\u000e\u0005\u0003\u0002T\u0006eWBAAk\u0015\u0011\t9.!\u001d\u0002\t1\fgnZ\u0005\u0004#\u0006U\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#A3\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u00111]Au!\r\u0011\u0017Q]\u0005\u0004\u0003Od%aA!os\"A\u00111\u001e\u000f\u0002\u0002\u0003\u0007Q-A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003c\u0004b!a=\u0002z\u0006\rXBAA{\u0015\r\t9\u0010T\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA~\u0003k\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0019qO!\u0001\t\u0013\u0005-h$!AA\u0002\u0005\r\u0018A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!5\u0003\b!A\u00111^\u0010\u0002\u0002\u0003\u0007Q-\u0001\u0005iCND7i\u001c3f)\u0005)GCAAi\u0003\u0019)\u0017/^1mgR\u0019qOa\u0005\t\u0013\u0005-(%!AA\u0002\u0005\r\u0018\u0001\u0002#bi\u0006\u00042!a,%'\u0015!#1DA5!!\u0011iBa\t\u0002&\u00065VB\u0001B\u0010\u0015\r\u0011\t\u0003T\u0001\beVtG/[7f\u0013\u0011\u0011)Ca\b\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0006\u0002\u0003\u0018\u0005)\u0011\r\u001d9msR!\u0011Q\u0016B\u0017\u0011\u0019yv\u00051\u0001\u0002&\u00069QO\\1qa2LH\u0003\u0002B\u001a\u0005s\u0001RA\u0019B\u001b\u0003KK1Aa\u000eM\u0005\u0019y\u0005\u000f^5p]\"I!1\b\u0015\u0002\u0002\u0003\u0007\u0011QV\u0001\u0004q\u0012\u0002\u0014\u0001C:bm\u0016LU\u000e\u001d7\u0015\t\t\u0005#q\t\t\u0004E\n\r\u0013b\u0001B#\u0019\n!QK\\5u\u0011\u0019\u0011I%\u000ba\u0001\r\u0006!\u0001/\u0019;i\u0005a\u0019\u0005.[*r'\u0016dWm\u0019;pe6{G-\u001a7SK\u0006$WM]\n\u0004U\t=\u0003#BA \u0005#\u001a\u0015\u0002\u0002B*\u0003\u0003\u0012\u0001\"\u0014'SK\u0006$WM\u001d\u000b\u0003\u0005/\u00022!!\"+\u0003%\u0019G.Y:t\u001d\u0006lW-\u0001\u0006dY\u0006\u001c8OT1nK\u0002\nA\u0001\\8bIR\u00191I!\u0019\t\r\t%c\u00061\u0001G\u0003\u0011\u0011X-\u00193\u0016\u0005\t=\u0003fA\u0018U5R\u00191Ia\u001b\t\r\t%\u0003\u00071\u0001GQ\r\u0001DKW\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005g\u0002B!a5\u0003v%!!qOAk\u0005\u0019y%M[3di\"\u001aq\u0002\u0016.)\u00079!&\f"
)
public final class ChiSqSelectorModel extends SelectorModel {
   public static ChiSqSelectorModel load(final String path) {
      return ChiSqSelectorModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return ChiSqSelectorModel$.MODULE$.read();
   }

   public String uid() {
      return super.uid();
   }

   public int[] selectedFeatures() {
      return super.selectedFeatures();
   }

   public boolean isNumericAttribute() {
      return false;
   }

   public ChiSqSelectorModel setFeaturesCol(final String value) {
      return (ChiSqSelectorModel)super.setFeaturesCol(value);
   }

   public ChiSqSelectorModel setOutputCol(final String value) {
      return (ChiSqSelectorModel)super.setOutputCol(value);
   }

   public StructType transformSchema(final StructType schema) {
      return super.transformSchema(schema);
   }

   public ChiSqSelectorModel copy(final ParamMap extra) {
      ChiSqSelectorModel copied = new ChiSqSelectorModel(this.uid(), this.selectedFeatures());
      return (ChiSqSelectorModel)((Model)this.copyValues(copied, extra)).setParent(this.parent());
   }

   public MLWriter write() {
      return new ChiSqSelectorModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "ChiSqSelectorModel: uid=" + var10000 + ", numSelectedFeatures=" + this.selectedFeatures().length;
   }

   public ChiSqSelectorModel(final String uid, final int[] selectedFeatures) {
      super(uid, selectedFeatures);
   }

   public ChiSqSelectorModel() {
      this("", .MODULE$.emptyIntArray());
   }

   public static class ChiSqSelectorModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final ChiSqSelectorModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.selectedFeatures()).toImmutableArraySeq());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(ChiSqSelectorModelWriter.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.feature.ChiSqSelectorModel.ChiSqSelectorModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.feature.ChiSqSelectorModel.ChiSqSelectorModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$1() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).write().parquet(dataPath);
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

      public ChiSqSelectorModelWriter(final ChiSqSelectorModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final Seq selectedFeatures;
         // $FF: synthetic field
         public final ChiSqSelectorModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public Seq selectedFeatures() {
            return this.selectedFeatures;
         }

         public Data copy(final Seq selectedFeatures) {
            return this.org$apache$spark$ml$feature$ChiSqSelectorModel$ChiSqSelectorModelWriter$Data$$$outer().new Data(selectedFeatures);
         }

         public Seq copy$default$1() {
            return this.selectedFeatures();
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
                  return this.selectedFeatures();
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
                  return "selectedFeatures";
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
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$feature$ChiSqSelectorModel$ChiSqSelectorModelWriter$Data$$$outer() == this.org$apache$spark$ml$feature$ChiSqSelectorModel$ChiSqSelectorModelWriter$Data$$$outer()) {
                     label42: {
                        Data var4 = (Data)x$1;
                        Seq var10000 = this.selectedFeatures();
                        Seq var5 = var4.selectedFeatures();
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
         public ChiSqSelectorModelWriter org$apache$spark$ml$feature$ChiSqSelectorModel$ChiSqSelectorModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final Seq selectedFeatures) {
            this.selectedFeatures = selectedFeatures;
            if (ChiSqSelectorModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = ChiSqSelectorModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction1 implements Serializable {
         // $FF: synthetic field
         private final ChiSqSelectorModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final Seq selectedFeatures) {
            return this.$outer.new Data(selectedFeatures);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.selectedFeatures()));
         }

         public Data$() {
            if (ChiSqSelectorModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = ChiSqSelectorModelWriter.this;
               super();
            }
         }
      }
   }

   private static class ChiSqSelectorModelReader extends MLReader {
      private final String className = ChiSqSelectorModel.class.getName();

      private String className() {
         return this.className;
      }

      public ChiSqSelectorModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Row data = (Row)this.sparkSession().read().parquet(dataPath).select("selectedFeatures", scala.collection.immutable.Nil..MODULE$).head();
         int[] selectedFeatures = (int[])((IterableOnceOps)data.getAs(0)).toArray(scala.reflect.ClassTag..MODULE$.Int());
         ChiSqSelectorModel model = new ChiSqSelectorModel(metadata.uid(), selectedFeatures);
         metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
         return model;
      }

      public ChiSqSelectorModelReader() {
      }
   }
}
