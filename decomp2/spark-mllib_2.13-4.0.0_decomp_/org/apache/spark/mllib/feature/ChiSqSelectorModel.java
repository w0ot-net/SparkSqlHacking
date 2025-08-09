package org.apache.spark.mllib.feature;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.linalg.DenseVector$;
import org.apache.spark.mllib.linalg.SparseVector;
import org.apache.spark.mllib.linalg.SparseVector$;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.util.Loader$;
import org.apache.spark.mllib.util.Saveable;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import org.json4s.Formats;
import org.json4s.JValue;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Some;
import scala.Tuple1;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.SeqOps;
import scala.collection.ArrayOps.;
import scala.collection.mutable.ArrayBuilder;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\tMc\u0001B\u00181\u0001mB\u0001\u0002\u0014\u0001\u0003\u0006\u0004%\t!\u0014\u0005\t;\u0002\u0011\t\u0011)A\u0005\u001d\")q\f\u0001C\u0001A\"9Q\r\u0001b\u0001\n\u0013i\u0005B\u00024\u0001A\u0003%a\nC\u0003h\u0001\u0011\u0005\u0003\u000eC\u0003s\u0001\u0011%1\u000f\u0003\u0004w\u0001\u0011\u0005Ag\u001e\u0005\t\u0003\u000f\u0001A\u0011\u0001\u001b\u0002\n!9\u0011Q\u0002\u0001\u0005B\u0005=qaBA#a!\u0005\u0011q\t\u0004\u0007_AB\t!!\u0013\t\r}cA\u0011AA1\u0011\u001d\t\u0019\u0007\u0004C!\u0003K:\u0001\"!\u001c\r\u0011\u0003\u0001\u0014q\u000e\u0004\t\u0003gb\u0001\u0012\u0001\u0019\u0002v!1q\f\u0005C\u0001\u0003oB\u0011\"!\u001f\u0011\u0005\u0004%I!a\u001f\t\u0011\u0005\u001d\u0005\u0003)A\u0005\u0003{2a!!#\u0011\u0001\u0006-\u0005\"C\u0019\u0015\u0005+\u0007I\u0011AAR\u0011%\t)\u000b\u0006B\tB\u0003%\u0011\u000b\u0003\u0004`)\u0011\u0005\u0011q\u0015\u0005\n\u0003_#\u0012\u0011!C\u0001\u0003cC\u0011\"!.\u0015#\u0003%\t!a.\t\u0013\u0005-G#!A\u0005B\u0005m\u0004\"CAg)\u0005\u0005I\u0011AAR\u0011%\ty\rFA\u0001\n\u0003\t\t\u000eC\u0005\u0002^R\t\t\u0011\"\u0011\u0002`\"I\u0011Q\u001e\u000b\u0002\u0002\u0013\u0005\u0011q\u001e\u0005\n\u0003s$\u0012\u0011!C!\u0003wD\u0011\"a@\u0015\u0003\u0003%\tE!\u0001\t\u0013\t\rA#!A\u0005B\t\u0015\u0001\"\u0003B\u0004)\u0005\u0005I\u0011\tB\u0005\u000f%\u0011i\u0001EA\u0001\u0012\u0003\u0011yAB\u0005\u0002\nB\t\t\u0011#\u0001\u0003\u0012!1q\f\nC\u0001\u0005?A\u0011Ba\u0001%\u0003\u0003%)E!\u0002\t\u0013\t\u0005B%!A\u0005\u0002\n\r\u0002\"\u0003B\u0014I\u0005\u0005I\u0011\u0011B\u0015\u0011%\u0011)\u0004JA\u0001\n\u0013\u00119\u0004\u0003\u0006\u0003@A\u0011\r\u0011\"\u00011\u0003wB\u0001B!\u0011\u0011A\u0003%\u0011Q\u0010\u0005\b\u0003\u001b\u0001B\u0011\u0001B\"\u0011\u001d\t\u0019\u0007\u0005C\u0001\u0005\u001bB\u0011B!\u000e\r\u0003\u0003%IAa\u000e\u0003%\rC\u0017nU9TK2,7\r^8s\u001b>$W\r\u001c\u0006\u0003cI\nqAZ3biV\u0014XM\u0003\u00024i\u0005)Q\u000e\u001c7jE*\u0011QGN\u0001\u0006gB\f'o\u001b\u0006\u0003oa\na!\u00199bG\",'\"A\u001d\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001a$I\u0012\t\u0003{\u0001k\u0011A\u0010\u0006\u0002\u007f\u0005)1oY1mC&\u0011\u0011I\u0010\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\r#U\"\u0001\u0019\n\u0005\u0015\u0003$!\u0005,fGR|'\u000f\u0016:b]N4wN]7feB\u0011qIS\u0007\u0002\u0011*\u0011\u0011JM\u0001\u0005kRLG.\u0003\u0002L\u0011\nA1+\u0019<fC\ndW-\u0001\ttK2,7\r^3e\r\u0016\fG/\u001e:fgV\ta\nE\u0002>\u001fFK!\u0001\u0015 \u0003\u000b\u0005\u0013(/Y=\u0011\u0005u\u0012\u0016BA*?\u0005\rIe\u000e\u001e\u0015\u0004\u0003U[\u0006C\u0001,Z\u001b\u00059&B\u0001-5\u0003)\tgN\\8uCRLwN\\\u0005\u00035^\u0013QaU5oG\u0016\f\u0013\u0001X\u0001\u0006c9\u001ad\u0006M\u0001\u0012g\u0016dWm\u0019;fI\u001a+\u0017\r^;sKN\u0004\u0003f\u0001\u0002V7\u00061A(\u001b8jiz\"\"!\u00192\u0011\u0005\r\u0003\u0001\"\u0002'\u0004\u0001\u0004q\u0005f\u00012V7\"\u001a1!V.\u0002\u001b\u0019LG\u000e^3s\u0013:$\u0017nY3t\u000391\u0017\u000e\u001c;fe&sG-[2fg\u0002\n\u0011\u0002\u001e:b]N4wN]7\u0015\u0005%|\u0007C\u00016n\u001b\u0005Y'B\u000173\u0003\u0019a\u0017N\\1mO&\u0011an\u001b\u0002\u0007-\u0016\u001cGo\u001c:\t\u000bA4\u0001\u0019A5\u0002\rY,7\r^8sQ\r1QkW\u0001\tG>l\u0007O]3tgR\u0011\u0011\u000e\u001e\u0005\u0006k\u001e\u0001\r![\u0001\tM\u0016\fG/\u001e:fg\u0006q1m\\7qe\u0016\u001c8o\u00159beN,G\u0003\u0002=\u0000\u0003\u0007\u0001B!P=Ow&\u0011!P\u0010\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0007uzE\u0010\u0005\u0002>{&\u0011aP\u0010\u0002\u0007\t>,(\r\\3\t\r\u0005\u0005\u0001\u00021\u0001O\u0003\u001dIg\u000eZ5dKNDa!!\u0002\t\u0001\u0004Y\u0018A\u0002<bYV,7/A\u0007d_6\u0004(/Z:t\t\u0016t7/\u001a\u000b\u0004w\u0006-\u0001BBA\u0003\u0013\u0001\u000710\u0001\u0003tCZ,GCBA\t\u0003/\t\u0019\u0003E\u0002>\u0003'I1!!\u0006?\u0005\u0011)f.\u001b;\t\u000f\u0005e!\u00021\u0001\u0002\u001c\u0005\u00111o\u0019\t\u0005\u0003;\ty\"D\u00015\u0013\r\t\t\u0003\u000e\u0002\r'B\f'o[\"p]R,\u0007\u0010\u001e\u0005\b\u0003KQ\u0001\u0019AA\u0014\u0003\u0011\u0001\u0018\r\u001e5\u0011\t\u0005%\u0012q\u0007\b\u0005\u0003W\t\u0019\u0004E\u0002\u0002.yj!!a\f\u000b\u0007\u0005E\"(\u0001\u0004=e>|GOP\u0005\u0004\u0003kq\u0014A\u0002)sK\u0012,g-\u0003\u0003\u0002:\u0005m\"AB*ue&twMC\u0002\u00026yBCAC+\u0002@\u0005\u0012\u0011\u0011I\u0001\u0006c92d\u0006\r\u0015\u0004\u0001U[\u0016AE\"iSN\u000b8+\u001a7fGR|'/T8eK2\u0004\"a\u0011\u0007\u0014\r1a\u00141JA)!\u00119\u0015QJ1\n\u0007\u0005=\u0003J\u0001\u0004M_\u0006$WM\u001d\t\u0005\u0003'\ni&\u0004\u0002\u0002V)!\u0011qKA-\u0003\tIwN\u0003\u0002\u0002\\\u0005!!.\u0019<b\u0013\u0011\ty&!\u0016\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u0005\u001d\u0013\u0001\u00027pC\u0012$R!YA4\u0003SBq!!\u0007\u000f\u0001\u0004\tY\u0002C\u0004\u0002&9\u0001\r!a\n)\t9)\u0016qH\u0001\r'\u00064X\rT8bIZ\u000bt\f\r\t\u0004\u0003c\u0002R\"\u0001\u0007\u0003\u0019M\u000bg/\u001a'pC\u00124\u0016g\u0018\u0019\u0014\u0005AaDCAA8\u0003E!\b.[:G_Jl\u0017\r\u001e,feNLwN\\\u000b\u0003\u0003{\u0002B!a \u0002\u00066\u0011\u0011\u0011\u0011\u0006\u0005\u0003\u0007\u000bI&\u0001\u0003mC:<\u0017\u0002BA\u001d\u0003\u0003\u000b!\u0003\u001e5jg\u001a{'/\\1u-\u0016\u00148/[8oA\t!A)\u0019;b'\u0019!B(!$\u0002\u0014B\u0019Q(a$\n\u0007\u0005EeHA\u0004Qe>$Wo\u0019;\u0011\t\u0005U\u0015q\u0014\b\u0005\u0003/\u000bYJ\u0004\u0003\u0002.\u0005e\u0015\"A \n\u0007\u0005ue(A\u0004qC\u000e\\\u0017mZ3\n\t\u0005}\u0013\u0011\u0015\u0006\u0004\u0003;sT#A)\u0002\u0011\u0019,\u0017\r^;sK\u0002\"B!!+\u0002.B\u0019\u00111\u0016\u000b\u000e\u0003AAQ!M\fA\u0002E\u000bAaY8qsR!\u0011\u0011VAZ\u0011\u001d\t\u0004\u0004%AA\u0002E\u000babY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002:*\u001a\u0011+a/,\u0005\u0005u\u0006\u0003BA`\u0003\u000fl!!!1\u000b\t\u0005\r\u0017QY\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001\u0017 \n\t\u0005%\u0017\u0011\u0019\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005M\u0017\u0011\u001c\t\u0004{\u0005U\u0017bAAl}\t\u0019\u0011I\\=\t\u0011\u0005mG$!AA\u0002E\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAAq!\u0019\t\u0019/!;\u0002T6\u0011\u0011Q\u001d\u0006\u0004\u0003Ot\u0014AC2pY2,7\r^5p]&!\u00111^As\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005E\u0018q\u001f\t\u0004{\u0005M\u0018bAA{}\t9!i\\8mK\u0006t\u0007\"CAn=\u0005\u0005\t\u0019AAj\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005u\u0014Q \u0005\t\u00037|\u0012\u0011!a\u0001#\u0006A\u0001.Y:i\u0007>$W\rF\u0001R\u0003!!xn\u0015;sS:<GCAA?\u0003\u0019)\u0017/^1mgR!\u0011\u0011\u001fB\u0006\u0011%\tYNIA\u0001\u0002\u0004\t\u0019.\u0001\u0003ECR\f\u0007cAAVIM)AEa\u0005\u0002RA9!Q\u0003B\u000e#\u0006%VB\u0001B\f\u0015\r\u0011IBP\u0001\beVtG/[7f\u0013\u0011\u0011iBa\u0006\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0006\u0002\u0003\u0010\u0005)\u0011\r\u001d9msR!\u0011\u0011\u0016B\u0013\u0011\u0015\tt\u00051\u0001R\u0003\u001d)h.\u00199qYf$BAa\u000b\u00032A!QH!\fR\u0013\r\u0011yC\u0010\u0002\u0007\u001fB$\u0018n\u001c8\t\u0013\tM\u0002&!AA\u0002\u0005%\u0016a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011!\u0011\b\t\u0005\u0003\u007f\u0012Y$\u0003\u0003\u0003>\u0005\u0005%AB(cU\u0016\u001cG/A\u0007uQ&\u001c8\t\\1tg:\u000bW.Z\u0001\u000fi\"L7o\u00117bgNt\u0015-\\3!)!\t\tB!\u0012\u0003H\t-\u0003bBA\rY\u0001\u0007\u00111\u0004\u0005\u0007\u0005\u0013b\u0003\u0019A1\u0002\u000b5|G-\u001a7\t\u000f\u0005\u0015B\u00061\u0001\u0002(Q)\u0011Ma\u0014\u0003R!9\u0011\u0011D\u0017A\u0002\u0005m\u0001bBA\u0013[\u0001\u0007\u0011q\u0005"
)
public class ChiSqSelectorModel implements VectorTransformer, Saveable {
   private final int[] selectedFeatures;
   private final int[] filterIndices;

   public static ChiSqSelectorModel load(final SparkContext sc, final String path) {
      return ChiSqSelectorModel$.MODULE$.load(sc, path);
   }

   public RDD transform(final RDD data) {
      return VectorTransformer.transform$(this, (RDD)data);
   }

   public JavaRDD transform(final JavaRDD data) {
      return VectorTransformer.transform$(this, (JavaRDD)data);
   }

   public int[] selectedFeatures() {
      return this.selectedFeatures;
   }

   private int[] filterIndices() {
      return this.filterIndices;
   }

   public Vector transform(final Vector vector) {
      return this.compress(vector);
   }

   private Vector compress(final Vector features) {
      if (features instanceof SparseVector var5) {
         Option var6 = SparseVector$.MODULE$.unapply(var5);
         if (!var6.isEmpty()) {
            int[] indices = (int[])((Tuple3)var6.get())._2();
            double[] values = (double[])((Tuple3)var6.get())._3();
            Tuple2 var10 = this.compressSparse(indices, values);
            if (var10 != null) {
               int[] newIndices = (int[])var10._1();
               double[] newValues = (double[])var10._2();
               Tuple2 var9 = new Tuple2(newIndices, newValues);
               int[] newIndices = (int[])var9._1();
               double[] newValues = (double[])var9._2();
               return Vectors$.MODULE$.sparse(this.filterIndices().length, newIndices, newValues);
            }

            throw new MatchError(var10);
         }
      }

      if (features instanceof DenseVector var15) {
         Option var16 = DenseVector$.MODULE$.unapply(var15);
         if (!var16.isEmpty()) {
            double[] values = (double[])var16.get();
            return Vectors$.MODULE$.dense(this.compressDense(values));
         }
      }

      throw new UnsupportedOperationException("Only sparse and dense vectors are supported but got " + features.getClass() + ".");
   }

   public Tuple2 compressSparse(final int[] indices, final double[] values) {
      ArrayBuilder.ofDouble newValues = new ArrayBuilder.ofDouble();
      ArrayBuilder.ofInt newIndices = new ArrayBuilder.ofInt();
      int i = 0;
      int j = 0;
      int indicesIdx = 0;
      int filterIndicesIdx = 0;

      while(i < indices.length && j < this.filterIndices().length) {
         indicesIdx = indices[i];
         filterIndicesIdx = this.filterIndices()[j];
         if (indicesIdx == filterIndicesIdx) {
            newIndices.$plus$eq(BoxesRunTime.boxToInteger(j));
            newValues.$plus$eq(BoxesRunTime.boxToDouble(values[i]));
            ++j;
            ++i;
         } else if (indicesIdx > filterIndicesIdx) {
            ++j;
         } else {
            ++i;
         }
      }

      return new Tuple2(newIndices.result(), newValues.result());
   }

   public double[] compressDense(final double[] values) {
      return (double[]).MODULE$.map$extension(scala.Predef..MODULE$.intArrayOps(this.filterIndices()), (JFunction1.mcDI.sp)(i) -> values[i], scala.reflect.ClassTag..MODULE$.Double());
   }

   public void save(final SparkContext sc, final String path) {
      ChiSqSelectorModel.SaveLoadV1_0$.MODULE$.save(sc, this, path);
   }

   public ChiSqSelectorModel(final int[] selectedFeatures) {
      this.selectedFeatures = selectedFeatures;
      VectorTransformer.$init$(this);
      this.filterIndices = (int[]).MODULE$.sorted$extension(scala.Predef..MODULE$.intArrayOps(selectedFeatures), scala.math.Ordering.Int..MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class SaveLoadV1_0$ {
      public static final SaveLoadV1_0$ MODULE$ = new SaveLoadV1_0$();
      private static final String thisFormatVersion = "1.0";
      private static final String thisClassName = "org.apache.spark.mllib.feature.ChiSqSelectorModel";

      private String thisFormatVersion() {
         return thisFormatVersion;
      }

      public String thisClassName() {
         return thisClassName;
      }

      public void save(final SparkContext sc, final ChiSqSelectorModel model, final String path) {
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         String metadata = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.thisClassName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("version"), this.thisFormatVersion()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(metadata), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple1"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$1() {
            }
         }

         spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).write().text(Loader$.MODULE$.metadataPath(path));
         ChiSqSelectorModel$SaveLoadV1_0$Data[] dataArray = (ChiSqSelectorModel$SaveLoadV1_0$Data[])scala.Array..MODULE$.tabulate(model.selectedFeatures().length, (i) -> $anonfun$save$4(model, BoxesRunTime.unboxToInt(i)), scala.reflect.ClassTag..MODULE$.apply(ChiSqSelectorModel$SaveLoadV1_0$Data.class));
         RDD var11 = sc.makeRDD(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(dataArray).toImmutableArraySeq(), 1, scala.reflect.ClassTag..MODULE$.apply(ChiSqSelectorModel$SaveLoadV1_0$Data.class));
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator2$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.feature.ChiSqSelectorModel.SaveLoadV1_0.Data").asType().toTypeConstructor();
            }

            public $typecreator2$1() {
            }
         }

         spark.createDataFrame(var11, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1())).write().parquet(Loader$.MODULE$.dataPath(path));
      }

      public ChiSqSelectorModel load(final SparkContext sc, final String path) {
         Formats formats = org.json4s.DefaultFormats..MODULE$;
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         Tuple3 var7 = Loader$.MODULE$.loadMetadata(sc, path);
         if (var7 == null) {
            throw new MatchError(var7);
         } else {
            String formatVersion;
            Predef var10000;
            boolean var10001;
            label36: {
               label35: {
                  String className = (String)var7._1();
                  String formatVersion = (String)var7._2();
                  JValue metadata = (JValue)var7._3();
                  Tuple3 var6 = new Tuple3(className, formatVersion, metadata);
                  String className = (String)var6._1();
                  formatVersion = (String)var6._2();
                  JValue var13 = (JValue)var6._3();
                  var10000 = scala.Predef..MODULE$;
                  String var14 = this.thisClassName();
                  if (className == null) {
                     if (var14 == null) {
                        break label35;
                     }
                  } else if (className.equals(var14)) {
                     break label35;
                  }

                  var10001 = false;
                  break label36;
               }

               var10001 = true;
            }

            label28: {
               label27: {
                  var10000.assert(var10001);
                  var10000 = scala.Predef..MODULE$;
                  String var15 = this.thisFormatVersion();
                  if (formatVersion == null) {
                     if (var15 == null) {
                        break label27;
                     }
                  } else if (formatVersion.equals(var15)) {
                     break label27;
                  }

                  var10001 = false;
                  break label28;
               }

               var10001 = true;
            }

            var10000.assert(var10001);
            Dataset dataFrame = spark.read().parquet(Loader$.MODULE$.dataPath(path));
            Dataset dataArray = dataFrame.select("feature", scala.collection.immutable.Nil..MODULE$);
            Loader$ var22 = Loader$.MODULE$;
            StructType var24 = dataFrame.schema();
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

            final class $typecreator1$2 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("org.apache.spark.mllib.feature.ChiSqSelectorModel.SaveLoadV1_0.Data").asType().toTypeConstructor();
               }

               public $typecreator1$2() {
               }
            }

            var22.checkSchema(var24, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2()));
            int[] features = (int[])dataArray.rdd().map((x0$1) -> BoxesRunTime.boxToInteger($anonfun$load$1(x0$1)), scala.reflect.ClassTag..MODULE$.Int()).collect();
            return new ChiSqSelectorModel(features);
         }
      }

      // $FF: synthetic method
      public static final ChiSqSelectorModel$SaveLoadV1_0$Data $anonfun$save$4(final ChiSqSelectorModel model$1, final int i) {
         return new ChiSqSelectorModel$SaveLoadV1_0$Data(model$1.selectedFeatures()[i]);
      }

      // $FF: synthetic method
      public static final int $anonfun$load$1(final Row x0$1) {
         if (x0$1 != null) {
            Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
            if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(1) == 0) {
               Object feature = ((SeqOps)var3.get()).apply(0);
               if (feature instanceof Integer) {
                  int var5 = BoxesRunTime.unboxToInt(feature);
                  return var5;
               }
            }
         }

         throw new MatchError(x0$1);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
