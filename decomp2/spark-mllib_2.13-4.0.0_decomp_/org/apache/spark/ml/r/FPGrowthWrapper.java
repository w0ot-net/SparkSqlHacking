package org.apache.spark.ml.r;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.fpm.FPGrowthModel;
import org.apache.spark.ml.fpm.FPGrowthModel$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.json4s.jackson.JsonMethods.;
import scala.Tuple1;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]c!\u0002\u000b\u0016\u0001Uy\u0002\u0002\u0003\u0017\u0001\u0005\u000b\u0007I\u0011\u0001\u0018\t\u0011U\u0002!\u0011!Q\u0001\n=BQA\u000e\u0001\u0005\n]BQa\u000f\u0001\u0005\u0002qBQ!\u0015\u0001\u0005\u0002qBQA\u0015\u0001\u0005\u0002MCQA\u001a\u0001\u0005B\u001d<aa[\u000b\t\u0002UagA\u0002\u000b\u0016\u0011\u0003)R\u000eC\u00037\u0013\u0011\u0005\u0011\u000fC\u0003s\u0013\u0011\u00051\u000fC\u0004\u0002$%!\t%!\n\u0007\r\u00055\u0012\u0002AA\u0018\u0011\u00191T\u0002\"\u0001\u00022!9\u0011qG\u0007\u0005B\u0005ebABA \u0013\u0001\t\t\u0005C\u0005\u0002DA\u0011\t\u0011)A\u0005q!1a\u0007\u0005C\u0001\u0003\u000bBq!a\u0013\u0011\t#\niEA\bG!\u001e\u0013xn\u001e;i/J\f\u0007\u000f]3s\u0015\t1r#A\u0001s\u0015\tA\u0012$\u0001\u0002nY*\u0011!dG\u0001\u0006gB\f'o\u001b\u0006\u00039u\ta!\u00199bG\",'\"\u0001\u0010\u0002\u0007=\u0014xmE\u0002\u0001A\u0019\u0002\"!\t\u0013\u000e\u0003\tR\u0011aI\u0001\u0006g\u000e\fG.Y\u0005\u0003K\t\u0012a!\u00118z%\u00164\u0007CA\u0014+\u001b\u0005A#BA\u0015\u0018\u0003\u0011)H/\u001b7\n\u0005-B#AC'M/JLG/\u00192mK\u0006ia\r]$s_^$\b.T8eK2\u001c\u0001!F\u00010!\t\u00014'D\u00012\u0015\t\u0011t#A\u0002ga6L!\u0001N\u0019\u0003\u001b\u0019\u0003vI]8xi\"lu\u000eZ3m\u000391\u0007o\u0012:poRDWj\u001c3fY\u0002\na\u0001P5oSRtDC\u0001\u001d;!\tI\u0004!D\u0001\u0016\u0011\u0015a3\u00011\u00010\u000311'/Z9Ji\u0016l7/\u001a;t+\u0005i\u0004C\u0001 O\u001d\ty4J\u0004\u0002A\u0013:\u0011\u0011\t\u0013\b\u0003\u0005\u001es!a\u0011$\u000e\u0003\u0011S!!R\u0017\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0012B\u0001\u000f\u001e\u0013\tQ2$\u0003\u0002K3\u0005\u00191/\u001d7\n\u00051k\u0015a\u00029bG.\fw-\u001a\u0006\u0003\u0015fI!a\u0014)\u0003\u0013\u0011\u000bG/\u0019$sC6,'B\u0001'N\u0003A\t7o]8dS\u0006$\u0018n\u001c8Sk2,7/A\u0005ue\u0006t7OZ8s[R\u0011Q\b\u0016\u0005\u0006+\u001a\u0001\rAV\u0001\bI\u0006$\u0018m]3ua\t9V\fE\u0002Y3nk\u0011!T\u0005\u000356\u0013q\u0001R1uCN,G\u000f\u0005\u0002];2\u0001A!\u00030U\u0003\u0003\u0005\tQ!\u0001`\u0005\ryF%M\t\u0003A\u000e\u0004\"!I1\n\u0005\t\u0014#a\u0002(pi\"Lgn\u001a\t\u0003C\u0011L!!\u001a\u0012\u0003\u0007\u0005s\u00170A\u0003xe&$X-F\u0001i!\t9\u0013.\u0003\u0002kQ\tAQ\nT,sSR,'/A\bG!\u001e\u0013xn\u001e;i/J\f\u0007\u000f]3s!\tI\u0014bE\u0002\nA9\u00042aJ89\u0013\t\u0001\bF\u0001\u0006N\u0019J+\u0017\rZ1cY\u0016$\u0012\u0001\\\u0001\u0004M&$Hc\u0002\u001dumnl\u0018q\u0002\u0005\u0006k.\u0001\r!P\u0001\u0005I\u0006$\u0018\rC\u0003x\u0017\u0001\u0007\u00010\u0001\u0006nS:\u001cV\u000f\u001d9peR\u0004\"!I=\n\u0005i\u0014#A\u0002#pk\ndW\rC\u0003}\u0017\u0001\u0007\u00010A\u0007nS:\u001cuN\u001c4jI\u0016t7-\u001a\u0005\u0006}.\u0001\ra`\u0001\tSR,Wn]\"pYB!\u0011\u0011AA\u0005\u001d\u0011\t\u0019!!\u0002\u0011\u0005\r\u0013\u0013bAA\u0004E\u00051\u0001K]3eK\u001aLA!a\u0003\u0002\u000e\t11\u000b\u001e:j]\u001eT1!a\u0002#\u0011\u001d\t\tb\u0003a\u0001\u0003'\tQB\\;n!\u0006\u0014H/\u001b;j_:\u001c\b\u0003BA\u000b\u0003?i!!a\u0006\u000b\t\u0005e\u00111D\u0001\u0005Y\u0006twM\u0003\u0002\u0002\u001e\u0005!!.\u0019<b\u0013\u0011\t\t#a\u0006\u0003\u000f%sG/Z4fe\u0006!!/Z1e+\t\t9\u0003\u0005\u0003(\u0003SA\u0014bAA\u0016Q\tAQ\n\u0014*fC\u0012,'OA\u000bG!\u001e\u0013xn\u001e;i/J\f\u0007\u000f]3s%\u0016\fG-\u001a:\u0014\u00075\t9\u0003\u0006\u0002\u00024A\u0019\u0011QG\u0007\u000e\u0003%\tA\u0001\\8bIR\u0019\u0001(a\u000f\t\r\u0005ur\u00021\u0001\u0000\u0003\u0011\u0001\u0018\r\u001e5\u0003+\u0019\u0003vI]8xi\"<&/\u00199qKJ<&/\u001b;feN\u0011\u0001\u0003[\u0001\tS:\u001cH/\u00198dKR!\u0011qIA%!\r\t)\u0004\u0005\u0005\u0007\u0003\u0007\u0012\u0002\u0019\u0001\u001d\u0002\u0011M\fg/Z%na2$B!a\u0014\u0002VA\u0019\u0011%!\u0015\n\u0007\u0005M#E\u0001\u0003V]&$\bBBA\u001f'\u0001\u0007q\u0010"
)
public class FPGrowthWrapper implements MLWritable {
   private final FPGrowthModel fpGrowthModel;

   public static MLReader read() {
      return FPGrowthWrapper$.MODULE$.read();
   }

   public static FPGrowthWrapper fit(final Dataset data, final double minSupport, final double minConfidence, final String itemsCol, final Integer numPartitions) {
      return FPGrowthWrapper$.MODULE$.fit(data, minSupport, minConfidence, itemsCol, numPartitions);
   }

   public static Object load(final String path) {
      return FPGrowthWrapper$.MODULE$.load(path);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public FPGrowthModel fpGrowthModel() {
      return this.fpGrowthModel;
   }

   public Dataset freqItemsets() {
      return this.fpGrowthModel().freqItemsets();
   }

   public Dataset associationRules() {
      return this.fpGrowthModel().associationRules();
   }

   public Dataset transform(final Dataset dataset) {
      return this.fpGrowthModel().transform(dataset);
   }

   public MLWriter write() {
      return new FPGrowthWrapperWriter(this);
   }

   public FPGrowthWrapper(final FPGrowthModel fpGrowthModel) {
      this.fpGrowthModel = fpGrowthModel;
      MLWritable.$init$(this);
   }

   public static class FPGrowthWrapperReader extends MLReader {
      public FPGrowthWrapper load(final String path) {
         String modelPath = (new Path(path, "model")).toString();
         FPGrowthModel fPGrowthModel = FPGrowthModel$.MODULE$.load(modelPath);
         return new FPGrowthWrapper(fPGrowthModel);
      }
   }

   public static class FPGrowthWrapperWriter extends MLWriter {
      private final FPGrowthWrapper instance;

      public void saveImpl(final String path) {
         String modelPath = (new Path(path, "model")).toString();
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String rMetadataJson = .MODULE$.compact(.MODULE$.render(org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.instance.getClass().getName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), .MODULE$.render$default$2(), .MODULE$.render$default$3()));
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(rMetadataJson), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(FPGrowthWrapperWriter.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple1"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$1() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).write().text(rMetadataPath);
         this.instance.fpGrowthModel().save(modelPath);
      }

      public FPGrowthWrapperWriter(final FPGrowthWrapper instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
