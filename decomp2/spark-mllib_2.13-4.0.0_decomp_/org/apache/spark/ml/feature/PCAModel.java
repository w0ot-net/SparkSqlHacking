package org.apache.spark.ml.feature;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.linalg.DenseMatrix;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.functions.;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tUh\u0001B\u001c9\u0001\rC\u0001b\u0015\u0001\u0003\u0006\u0004%\t\u0005\u0016\u0005\tW\u0002\u0011\t\u0011)A\u0005+\"AQ\u000e\u0001BC\u0002\u0013\u0005a\u000e\u0003\u0005y\u0001\t\u0005\t\u0015!\u0003p\u0011!Q\bA!b\u0001\n\u0003Y\b\"CA\u0001\u0001\t\u0005\t\u0015!\u0003}\u0011!\t)\u0001\u0001C\u0001u\u0005\u001d\u0001\u0002CA\u0003\u0001\u0011\u0005!(!\u0006\t\u000f\u0005]\u0001\u0001\"\u0001\u0002\u001a!9\u00111\u0005\u0001\u0005\u0002\u0005\u0015\u0002bBA\u0016\u0001\u0011\u0005\u0013Q\u0006\u0005\b\u0003s\u0002A\u0011IA>\u0011\u001d\ty\t\u0001C!\u0003#Cq!!*\u0001\t\u0003\n9\u000bC\u0004\u00026\u0002!\t%a.\b\u000f\u0005\u0005\u0007\b#\u0001\u0002D\u001a1q\u0007\u000fE\u0001\u0003\u000bDq!!\u0002\u0012\t\u0003\t\u0019OB\u0004\u0002fF\u0001\u0011#a:\t\u0013\u0005%8C!A!\u0002\u0013A\u0005bBA\u0003'\u0011\u0005\u00111\u001e\u0004\u0007\u0003g\u001cB)!>\t\u001154\"Q3A\u0005\u00029D\u0001\u0002\u001f\f\u0003\u0012\u0003\u0006Ia\u001c\u0005\tuZ\u0011)\u001a!C\u0001w\"I\u0011\u0011\u0001\f\u0003\u0012\u0003\u0006I\u0001 \u0005\b\u0003\u000b1B\u0011\u0001B\u0006\u0011%\tyIFA\u0001\n\u0003\u0011)\u0002C\u0005\u0003\u001cY\t\n\u0011\"\u0001\u0003\u001e!I!\u0011\u0007\f\u0012\u0002\u0013\u0005!1\u0007\u0005\n\u0005o1\u0012\u0011!C!\u0005sA\u0011B!\u0012\u0017\u0003\u0003%\tAa\u0012\t\u0013\t=c#!A\u0005\u0002\tE\u0003\"\u0003B,-\u0005\u0005I\u0011\tB-\u0011%\u00119GFA\u0001\n\u0003\u0011I\u0007C\u0005\u0003tY\t\t\u0011\"\u0011\u0003v!I!\u0011\u0010\f\u0002\u0002\u0013\u0005#1\u0010\u0005\n\u0003k3\u0012\u0011!C!\u0005{B\u0011Ba \u0017\u0003\u0003%\tE!!\b\u0013\t\u00155#!A\t\n\t\u001de!CAz'\u0005\u0005\t\u0012\u0002BE\u0011\u001d\t)!\u000bC\u0001\u0005/C\u0011\"!.*\u0003\u0003%)E! \t\u0013\te\u0015&!A\u0005\u0002\nm\u0005\"\u0003BQS\u0005\u0005I\u0011\u0011BR\u0011\u001d\u0011)l\u0005C)\u0005o3aAa1\u0012\t\t\u0015\u0007bBA\u0003_\u0011\u0005!Q\u001a\u0005\n\u0005#|#\u0019!C\u0005\u0005sA\u0001Ba50A\u0003%!1\b\u0005\b\u0005+|C\u0011\tBl\u0011\u001d\u0011Y.\u0005C!\u0005;DqA!6\u0012\t\u0003\u0012\t\u000fC\u0005\u0003hF\t\t\u0011\"\u0003\u0003j\nA\u0001kQ!N_\u0012,GN\u0003\u0002:u\u00059a-Z1ukJ,'BA\u001e=\u0003\tiGN\u0003\u0002>}\u0005)1\u000f]1sW*\u0011q\bQ\u0001\u0007CB\f7\r[3\u000b\u0003\u0005\u000b1a\u001c:h\u0007\u0001\u0019B\u0001\u0001#K\u001bB\u0019QI\u0012%\u000e\u0003iJ!a\u0012\u001e\u0003\u000b5{G-\u001a7\u0011\u0005%\u0003Q\"\u0001\u001d\u0011\u0005%[\u0015B\u0001'9\u0005%\u00016)\u0011)be\u0006l7\u000f\u0005\u0002O#6\tqJ\u0003\u0002Qu\u0005!Q\u000f^5m\u0013\t\u0011vJ\u0001\u0006N\u0019^\u0013\u0018\u000e^1cY\u0016\f1!^5e+\u0005)\u0006C\u0001,`\u001d\t9V\f\u0005\u0002Y76\t\u0011L\u0003\u0002[\u0005\u00061AH]8pizR\u0011\u0001X\u0001\u0006g\u000e\fG.Y\u0005\u0003=n\u000ba\u0001\u0015:fI\u00164\u0017B\u00011b\u0005\u0019\u0019FO]5oO*\u0011al\u0017\u0015\u0004\u0003\rL\u0007C\u00013h\u001b\u0005)'B\u00014=\u0003)\tgN\\8uCRLwN\\\u0005\u0003Q\u0016\u0014QaU5oG\u0016\f\u0013A[\u0001\u0006c9*d\u0006M\u0001\u0005k&$\u0007\u0005K\u0002\u0003G&\f!\u0001]2\u0016\u0003=\u0004\"\u0001]:\u000e\u0003ET!A\u001d\u001e\u0002\r1Lg.\u00197h\u0013\t!\u0018OA\u0006EK:\u001cX-T1ue&D\bfA\u0002dm\u0006\nq/A\u00033]Ar\u0003'A\u0002qG\u0002B3\u0001B2w\u0003E)\u0007\u0010\u001d7bS:,GMV1sS\u0006t7-Z\u000b\u0002yB\u0011\u0001/`\u0005\u0003}F\u00141\u0002R3og\u00164Vm\u0019;pe\"\u001aQa\u0019<\u0002%\u0015D\b\u000f\\1j]\u0016$g+\u0019:jC:\u001cW\r\t\u0015\u0004\r\r4\u0018A\u0002\u001fj]&$h\bF\u0004I\u0003\u0013\ti!!\u0005\t\u000bM;\u0001\u0019A+)\t\u0005%1-\u001b\u0005\u0006[\u001e\u0001\ra\u001c\u0015\u0005\u0003\u001b\u0019g\u000fC\u0003{\u000f\u0001\u0007A\u0010\u000b\u0003\u0002\u0012\r4H#\u0001%\u0002\u0017M,G/\u00138qkR\u001cu\u000e\u001c\u000b\u0005\u00037\ti\"D\u0001\u0001\u0011\u0019\ty\"\u0003a\u0001+\u0006)a/\u00197vK\"\u001a\u0011bY5\u0002\u0019M,GoT;uaV$8i\u001c7\u0015\t\u0005m\u0011q\u0005\u0005\u0007\u0003?Q\u0001\u0019A+)\u0007)\u0019\u0017.A\u0005ue\u0006t7OZ8s[R!\u0011qFA)!\u0011\t\t$a\u0013\u000f\t\u0005M\u0012Q\t\b\u0005\u0003k\t\tE\u0004\u0003\u00028\u0005}b\u0002BA\u001d\u0003{q1\u0001WA\u001e\u0013\u0005\t\u0015BA A\u0013\tid(C\u0002\u0002Dq\n1a]9m\u0013\u0011\t9%!\u0013\u0002\u000fA\f7m[1hK*\u0019\u00111\t\u001f\n\t\u00055\u0013q\n\u0002\n\t\u0006$\u0018M\u0012:b[\u0016TA!a\u0012\u0002J!9\u00111K\u0006A\u0002\u0005U\u0013a\u00023bi\u0006\u001cX\r\u001e\u0019\u0005\u0003/\n\u0019\u0007\u0005\u0004\u0002Z\u0005m\u0013qL\u0007\u0003\u0003\u0013JA!!\u0018\u0002J\t9A)\u0019;bg\u0016$\b\u0003BA1\u0003Gb\u0001\u0001\u0002\u0007\u0002f\u0005E\u0013\u0011!A\u0001\u0006\u0003\t9GA\u0002`II\nB!!\u001b\u0002rA!\u00111NA7\u001b\u0005Y\u0016bAA87\n9aj\u001c;iS:<\u0007\u0003BA6\u0003gJ1!!\u001e\\\u0005\r\te.\u001f\u0015\u0004\u0017\r4\u0018a\u0004;sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\t\u0005u\u0014\u0011\u0012\t\u0005\u0003\u007f\n))\u0004\u0002\u0002\u0002*!\u00111QA%\u0003\u0015!\u0018\u0010]3t\u0013\u0011\t9)!!\u0003\u0015M#(/^2u)f\u0004X\rC\u0004\u0002\f2\u0001\r!! \u0002\rM\u001c\u0007.Z7bQ\ra1-[\u0001\u0005G>\u0004\u0018\u0010F\u0002I\u0003'Cq!!&\u000e\u0001\u0004\t9*A\u0003fqR\u0014\u0018\r\u0005\u0003\u0002\u001a\u0006}UBAAN\u0015\r\tiJO\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0005\u0003C\u000bYJ\u0001\u0005QCJ\fW.T1qQ\ri1-[\u0001\u0006oJLG/Z\u000b\u0003\u0003S\u00032ATAV\u0013\r\tik\u0014\u0002\t\u001b2;&/\u001b;fe\"\"abYAYC\t\t\u0019,A\u00032]Yr\u0003'\u0001\u0005u_N#(/\u001b8h)\u0005)\u0006\u0006B\bd\u0003w\u000b#!!0\u0002\u000bMr\u0003G\f\u0019)\u0007\u0001\u0019\u0017.\u0001\u0005Q\u0007\u0006ku\u000eZ3m!\tI\u0015cE\u0004\u0012\u0003\u000f\fi-a5\u0011\t\u0005-\u0014\u0011Z\u0005\u0004\u0003\u0017\\&AB!osJ+g\r\u0005\u0003O\u0003\u001fD\u0015bAAi\u001f\nQQ\n\u0014*fC\u0012\f'\r\\3\u0011\t\u0005U\u0017q\\\u0007\u0003\u0003/TA!!7\u0002\\\u0006\u0011\u0011n\u001c\u0006\u0003\u0003;\fAA[1wC&!\u0011\u0011]Al\u00051\u0019VM]5bY&T\u0018M\u00197f)\t\t\u0019M\u0001\bQ\u0007\u0006ku\u000eZ3m/JLG/\u001a:\u0014\u0007M\tI+\u0001\u0005j]N$\u0018M\\2f)\u0011\ti/!=\u0011\u0007\u0005=8#D\u0001\u0012\u0011\u0019\tI/\u0006a\u0001\u0011\n!A)\u0019;b'\u001d1\u0012qYA|\u0003{\u0004B!a\u001b\u0002z&\u0019\u00111`.\u0003\u000fA\u0013x\u000eZ;diB!\u0011q B\u0004\u001d\u0011\u0011\tA!\u0002\u000f\u0007a\u0013\u0019!C\u0001]\u0013\r\t9eW\u0005\u0005\u0003C\u0014IAC\u0002\u0002Hm#bA!\u0004\u0003\u0012\tM\u0001c\u0001B\b-5\t1\u0003C\u0003n7\u0001\u0007q\u000eC\u0003{7\u0001\u0007A\u0010\u0006\u0004\u0003\u000e\t]!\u0011\u0004\u0005\b[r\u0001\n\u00111\u0001p\u0011\u001dQH\u0004%AA\u0002q\fabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0003 )\u001aqN!\t,\u0005\t\r\u0002\u0003\u0002B\u0013\u0005[i!Aa\n\u000b\t\t%\"1F\u0001\nk:\u001c\u0007.Z2lK\u0012T!AZ.\n\t\t=\"q\u0005\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0005kQ3\u0001 B\u0011\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011!1\b\t\u0005\u0005{\u0011\u0019%\u0004\u0002\u0003@)!!\u0011IAn\u0003\u0011a\u0017M\\4\n\u0007\u0001\u0014y$\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0003JA!\u00111\u000eB&\u0013\r\u0011ie\u0017\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003c\u0012\u0019\u0006C\u0005\u0003V\u0005\n\t\u00111\u0001\u0003J\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"Aa\u0017\u0011\r\tu#1MA9\u001b\t\u0011yFC\u0002\u0003bm\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\u0011)Ga\u0018\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0005W\u0012\t\b\u0005\u0003\u0002l\t5\u0014b\u0001B87\n9!i\\8mK\u0006t\u0007\"\u0003B+G\u0005\u0005\t\u0019AA9\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\tm\"q\u000f\u0005\n\u0005+\"\u0013\u0011!a\u0001\u0005\u0013\n\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0005\u0013\"\"Aa\u000f\u0002\r\u0015\fX/\u00197t)\u0011\u0011YGa!\t\u0013\tUs%!AA\u0002\u0005E\u0014\u0001\u0002#bi\u0006\u00042Aa\u0004*'\u0015I#1RAj!!\u0011iIa%py\n5QB\u0001BH\u0015\r\u0011\tjW\u0001\beVtG/[7f\u0013\u0011\u0011)Ja$\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t'\u0007\u0006\u0002\u0003\b\u0006)\u0011\r\u001d9msR1!Q\u0002BO\u0005?CQ!\u001c\u0017A\u0002=DQA\u001f\u0017A\u0002q\fq!\u001e8baBd\u0017\u0010\u0006\u0003\u0003&\nE\u0006CBA6\u0005O\u0013Y+C\u0002\u0003*n\u0013aa\u00149uS>t\u0007CBA6\u0005[{G0C\u0002\u00030n\u0013a\u0001V;qY\u0016\u0014\u0004\"\u0003BZ[\u0005\u0005\t\u0019\u0001B\u0007\u0003\rAH\u0005M\u0001\tg\u00064X-S7qYR!!\u0011\u0018B`!\u0011\tYGa/\n\u0007\tu6L\u0001\u0003V]&$\bB\u0002Ba]\u0001\u0007Q+\u0001\u0003qCRD'A\u0004)D\u00036{G-\u001a7SK\u0006$WM]\n\u0004_\t\u001d\u0007\u0003\u0002(\u0003J\"K1Aa3P\u0005!iEJU3bI\u0016\u0014HC\u0001Bh!\r\tyoL\u0001\nG2\f7o\u001d(b[\u0016\f!b\u00197bgNt\u0015-\\3!\u0003\u0011aw.\u00193\u0015\u0007!\u0013I\u000e\u0003\u0004\u0003BN\u0002\r!V\u0001\u0005e\u0016\fG-\u0006\u0002\u0003H\"\"AgYAY)\rA%1\u001d\u0005\u0007\u0005\u0003,\u0004\u0019A+)\tU\u001a\u0017\u0011W\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005W\u0004BA!\u0010\u0003n&!!q\u001eB \u0005\u0019y%M[3di\"\"\u0011cYAYQ\u0011\u00012-!-"
)
public class PCAModel extends Model implements PCAParams, MLWritable {
   private final String uid;
   private final DenseMatrix pc;
   private final DenseVector explainedVariance;
   private IntParam k;
   private Param outputCol;
   private Param inputCol;

   public static PCAModel load(final String path) {
      return PCAModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return PCAModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public int getK() {
      return PCAParams.getK$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return PCAParams.validateAndTransformSchema$(this, schema);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public final IntParam k() {
      return this.k;
   }

   public final void org$apache$spark$ml$feature$PCAParams$_setter_$k_$eq(final IntParam x$1) {
      this.k = x$1;
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

   public String uid() {
      return this.uid;
   }

   public DenseMatrix pc() {
      return this.pc;
   }

   public DenseVector explainedVariance() {
      return this.explainedVariance;
   }

   public PCAModel setInputCol(final String value) {
      return (PCAModel)this.set(this.inputCol(), value);
   }

   public PCAModel setOutputCol(final String value) {
      return (PCAModel)this.set(this.outputCol(), value);
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      DenseMatrix transposed = this.pc().transpose();
      functions var10000 = .MODULE$;
      Function1 var10001 = (vector) -> transposed.multiply(vector);
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(PCAModel.class.getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.DenseVector").asType().toTypeConstructor();
         }

         public $typecreator1$1() {
         }
      }

      TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1());
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(PCAModel.class.getClassLoader());

      final class $typecreator2$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator2$1() {
         }
      }

      UserDefinedFunction transformer = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
      return dataset.withColumn((String)this.$(this.outputCol()), transformer.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.col((String)this.$(this.inputCol()))}))), outputSchema.apply((String)this.$(this.outputCol())).metadata());
   }

   public StructType transformSchema(final StructType schema) {
      StructType outputSchema = this.validateAndTransformSchema(schema);
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.outputCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateAttributeGroupSize(outputSchema, (String)this.$(this.outputCol()), BoxesRunTime.unboxToInt(this.$(this.k())));
      }

      return outputSchema;
   }

   public PCAModel copy(final ParamMap extra) {
      PCAModel copied = new PCAModel(this.uid(), this.pc(), this.explainedVariance());
      return (PCAModel)((Model)this.copyValues(copied, extra)).setParent(this.parent());
   }

   public MLWriter write() {
      return new PCAModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "PCAModel: uid=" + var10000 + ", k=" + this.$(this.k());
   }

   public PCAModel(final String uid, final DenseMatrix pc, final DenseVector explainedVariance) {
      this.uid = uid;
      this.pc = pc;
      this.explainedVariance = explainedVariance;
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      PCAParams.$init$(this);
      MLWritable.$init$(this);
      Statics.releaseFence();
   }

   public PCAModel() {
      this("", org.apache.spark.ml.linalg.Matrices..MODULE$.empty(), org.apache.spark.ml.linalg.Vectors..MODULE$.empty());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class PCAModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final PCAModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(this.instance.pc(), this.instance.explainedVariance());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(PCAModelWriter.class.getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.feature.PCAModel.PCAModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.feature.PCAModel.PCAModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
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

      public PCAModelWriter(final PCAModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final DenseMatrix pc;
         private final DenseVector explainedVariance;
         // $FF: synthetic field
         public final PCAModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public DenseMatrix pc() {
            return this.pc;
         }

         public DenseVector explainedVariance() {
            return this.explainedVariance;
         }

         public Data copy(final DenseMatrix pc, final DenseVector explainedVariance) {
            return this.org$apache$spark$ml$feature$PCAModel$PCAModelWriter$Data$$$outer().new Data(pc, explainedVariance);
         }

         public DenseMatrix copy$default$1() {
            return this.pc();
         }

         public DenseVector copy$default$2() {
            return this.explainedVariance();
         }

         public String productPrefix() {
            return "Data";
         }

         public int productArity() {
            return 2;
         }

         public Object productElement(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return this.pc();
               }
               case 1 -> {
                  return this.explainedVariance();
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
                  return "pc";
               }
               case 1 -> {
                  return "explainedVariance";
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
            boolean var8;
            if (this != x$1) {
               label60: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$feature$PCAModel$PCAModelWriter$Data$$$outer() == this.org$apache$spark$ml$feature$PCAModel$PCAModelWriter$Data$$$outer()) {
                     label50: {
                        Data var4 = (Data)x$1;
                        DenseMatrix var10000 = this.pc();
                        DenseMatrix var5 = var4.pc();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label50;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label50;
                        }

                        DenseVector var7 = this.explainedVariance();
                        DenseVector var6 = var4.explainedVariance();
                        if (var7 == null) {
                           if (var6 != null) {
                              break label50;
                           }
                        } else if (!var7.equals(var6)) {
                           break label50;
                        }

                        if (var4.canEqual(this)) {
                           break label60;
                        }
                     }
                  }

                  var8 = false;
                  return var8;
               }
            }

            var8 = true;
            return var8;
         }

         // $FF: synthetic method
         public PCAModelWriter org$apache$spark$ml$feature$PCAModel$PCAModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final DenseMatrix pc, final DenseVector explainedVariance) {
            this.pc = pc;
            this.explainedVariance = explainedVariance;
            if (PCAModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = PCAModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction2 implements Serializable {
         // $FF: synthetic field
         private final PCAModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final DenseMatrix pc, final DenseVector explainedVariance) {
            return this.$outer.new Data(pc, explainedVariance);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.pc(), x$0.explainedVariance())));
         }

         public Data$() {
            if (PCAModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = PCAModelWriter.this;
               super();
            }
         }
      }
   }

   private static class PCAModelReader extends MLReader {
      private final String className = PCAModel.class.getName();

      private String className() {
         return this.className;
      }

      public PCAModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         PCAModel var10000;
         if (org.apache.spark.util.VersionUtils..MODULE$.majorVersion(metadata.sparkVersion()) >= 2) {
            Row var8 = (Row)this.sparkSession().read().parquet(dataPath).select("pc", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"explainedVariance"}))).head();
            if (var8 == null) {
               throw new MatchError(var8);
            }

            Some var9 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var8);
            if (var9.isEmpty() || var9.get() == null || ((SeqOps)var9.get()).lengthCompare(2) != 0) {
               throw new MatchError(var8);
            }

            Object pc = ((SeqOps)var9.get()).apply(0);
            Object explainedVariance = ((SeqOps)var9.get()).apply(1);
            if (!(pc instanceof DenseMatrix)) {
               throw new MatchError(var8);
            }

            DenseMatrix var12 = (DenseMatrix)pc;
            if (!(explainedVariance instanceof DenseVector)) {
               throw new MatchError(var8);
            }

            DenseVector var13 = (DenseVector)explainedVariance;
            Tuple2 var7 = new Tuple2(var12, var13);
            DenseMatrix pc = (DenseMatrix)var7._1();
            DenseVector explainedVariance = (DenseVector)var7._2();
            var10000 = new PCAModel(metadata.uid(), pc, explainedVariance);
         } else {
            Row var17 = (Row)this.sparkSession().read().parquet(dataPath).select("pc", scala.collection.immutable.Nil..MODULE$).head();
            if (var17 == null) {
               throw new MatchError(var17);
            }

            Some var18 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var17);
            if (var18.isEmpty() || var18.get() == null || ((SeqOps)var18.get()).lengthCompare(1) != 0) {
               throw new MatchError(var17);
            }

            Object pc = ((SeqOps)var18.get()).apply(0);
            if (!(pc instanceof org.apache.spark.mllib.linalg.DenseMatrix)) {
               throw new MatchError(var17);
            }

            org.apache.spark.mllib.linalg.DenseMatrix var20 = (org.apache.spark.mllib.linalg.DenseMatrix)pc;
            var10000 = new PCAModel(metadata.uid(), var20.asML(), new DenseVector(scala.Array..MODULE$.emptyDoubleArray()));
         }

         PCAModel model = var10000;
         metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
         return model;
      }

      public PCAModelReader() {
      }
   }
}
