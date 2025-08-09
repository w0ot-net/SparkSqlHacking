package org.apache.spark.ml.feature;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Transformer;
import org.apache.spark.ml.attribute.AttributeGroup;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasNumFeatures;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.functions.;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.util.collection.OpenHashMap;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.Names;
import scala.reflect.api.Symbols;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\t=c\u0001\u0002\u0013&\u0001AB\u0001\"\u0013\u0001\u0003\u0006\u0004%\tE\u0013\u0005\tC\u0002\u0011\t\u0011)A\u0005\u0017\"A1\r\u0001BC\u0002\u0013\u0005A\r\u0003\u0005m\u0001\t\u0005\t\u0015!\u0003f\u0011\u0019q\u0007\u0001\"\u0001(_\")a\u000e\u0001C\u0001s\")a\u000e\u0001C\u0001{\"9\u0011\u0011\u0001\u0001\u0005\u0002\u0005\r\u0001bBA\u0007\u0001\u0011\u0005\u0011q\u0002\u0005\n\u0003+\u0001!\u0019!C\u0001\u0003/A\u0001\"a\n\u0001A\u0003%\u0011\u0011\u0004\u0005\b\u0003W\u0001A\u0011AA\u0017\u0011\u001d\t\u0019\u0004\u0001C\u0001\u0003kAq!a\u0010\u0001\t\u0003\t\t\u0005C\u0004\u0002H\u0001!\t%!\u0013\t\u000f\u0005M\u0005\u0001\"\u0011\u0002\u0016\"9\u0011\u0011\u0016\u0001\u0005\u0002\u0005-\u0006bBAZ\u0001\u0011\u0005\u0013Q\u0017\u0005\b\u0003\u000f\u0004A\u0011IAe\u0011\u001d\ti\r\u0001C!\u0003\u001f<q!a8&\u0011\u0003\t\tO\u0002\u0004%K!\u0005\u00111\u001d\u0005\u0007]Z!\tA!\u0001\t\u0013\t\raC1A\u0005\u0002\u001d\"\u0007b\u0002B\u0003-\u0001\u0006I!\u001a\u0005\n\u0005\u000f1\"\u0019!C\u0001O\u0011DqA!\u0003\u0017A\u0003%QM\u0002\u0004\u0003\fY!!Q\u0002\u0005\u0007]r!\tA!\u0006\t\u0013\tmAD1A\u0005\n\tu\u0001\u0002\u0003B\u00159\u0001\u0006IAa\b\t\u000f\t-B\u0004\"\u0011\u0003.!9!\u0011\u0007\f\u0005B\tM\u0002b\u0002B\u0016-\u0011\u0005#q\u0007\u0005\n\u0005\u00032\u0012\u0011!C\u0005\u0005\u0007\u0012\u0011\u0002S1tQ&tw\r\u0016$\u000b\u0005\u0019:\u0013a\u00024fCR,(/\u001a\u0006\u0003Q%\n!!\u001c7\u000b\u0005)Z\u0013!B:qCJ\\'B\u0001\u0017.\u0003\u0019\t\u0007/Y2iK*\ta&A\u0002pe\u001e\u001c\u0001a\u0005\u0004\u0001cUj\u0004i\u0011\t\u0003eMj\u0011aJ\u0005\u0003i\u001d\u00121\u0002\u0016:b]N4wN]7feB\u0011agO\u0007\u0002o)\u0011\u0001(O\u0001\u0007g\"\f'/\u001a3\u000b\u0005i:\u0013!\u00029be\u0006l\u0017B\u0001\u001f8\u0005-A\u0015m]%oaV$8i\u001c7\u0011\u0005Yr\u0014BA 8\u00051A\u0015m](viB,HoQ8m!\t1\u0014)\u0003\u0002Co\tq\u0001*Y:Ok64U-\u0019;ve\u0016\u001c\bC\u0001#H\u001b\u0005)%B\u0001$(\u0003\u0011)H/\u001b7\n\u0005!+%!\u0006#fM\u0006,H\u000e\u001e)be\u0006l7o\u0016:ji\u0006\u0014G.Z\u0001\u0004k&$W#A&\u0011\u00051+fBA'T!\tq\u0015+D\u0001P\u0015\t\u0001v&\u0001\u0004=e>|GO\u0010\u0006\u0002%\u0006)1oY1mC&\u0011A+U\u0001\u0007!J,G-\u001a4\n\u0005Y;&AB*ue&twM\u0003\u0002U#\"\u001a\u0011!W0\u0011\u0005ikV\"A.\u000b\u0005qK\u0013AC1o]>$\u0018\r^5p]&\u0011al\u0017\u0002\u0006'&t7-Z\u0011\u0002A\u0006)\u0011G\f\u001b/a\u0005!Q/\u001b3!Q\r\u0011\u0011lX\u0001\u0010Q\u0006\u001c\bNR;oGZ+'o]5p]V\tQ\r\u0005\u0002gO6\t\u0011+\u0003\u0002i#\n\u0019\u0011J\u001c;)\u0007\rI&.I\u0001l\u0003\u0015\u0019d&\r\u00181\u0003AA\u0017m\u001d5Gk:\u001cg+\u001a:tS>t\u0007\u0005K\u0002\u00053*\fa\u0001P5oSRtDc\u00019siB\u0011\u0011\u000fA\u0007\u0002K!)\u0011*\u0002a\u0001\u0017\"\u001a!/W0\t\u000b\r,\u0001\u0019A3)\u0007QL&\u000eK\u0002\u00063^\f\u0013\u0001_\u0001\u0006g9\u0002d\u0006\r\u000b\u0002a\"\u001aa!W>\"\u0003q\fQ!\r\u00183]A\"\"\u0001\u001d@\t\u000b%;\u0001\u0019A&)\u0007\u001dIv,A\u0006tKRLe\u000e];u\u0007>dG\u0003BA\u0003\u0003\u000fi\u0011\u0001\u0001\u0005\u0007\u0003\u0013A\u0001\u0019A&\u0002\u000bY\fG.^3)\u0007!Iv,\u0001\u0007tKR|U\u000f\u001e9vi\u000e{G\u000e\u0006\u0003\u0002\u0006\u0005E\u0001BBA\u0005\u0013\u0001\u00071\nK\u0002\n3~\u000baAY5oCJLXCAA\r!\u0011\tY\"!\b\u000e\u0003eJ1!a\b:\u00051\u0011un\u001c7fC:\u0004\u0016M]1nQ\u0011Q\u0011,a\t\"\u0005\u0005\u0015\u0012!\u0002\u001a/a9\u0002\u0014a\u00022j]\u0006\u0014\u0018\u0010\t\u0015\u0005\u0017e\u000b\u0019#\u0001\btKRtU/\u001c$fCR,(/Z:\u0015\t\u0005\u0015\u0011q\u0006\u0005\u0007\u0003\u0013a\u0001\u0019A3)\u00071I60A\u0005hKR\u0014\u0015N\\1ssV\u0011\u0011q\u0007\t\u0004M\u0006e\u0012bAA\u001e#\n9!i\\8mK\u0006t\u0007\u0006B\u0007Z\u0003G\t\u0011b]3u\u0005&t\u0017M]=\u0015\t\u0005\u0015\u00111\t\u0005\b\u0003\u0013q\u0001\u0019AA\u001cQ\u0011q\u0011,a\t\u0002\u0013Q\u0014\u0018M\\:g_JlG\u0003BA&\u0003[\u0002B!!\u0014\u0002h9!\u0011qJA1\u001d\u0011\t\t&!\u0018\u000f\t\u0005M\u00131\f\b\u0005\u0003+\nIFD\u0002O\u0003/J\u0011AL\u0005\u0003Y5J!AK\u0016\n\u0007\u0005}\u0013&A\u0002tc2LA!a\u0019\u0002f\u00059\u0001/Y2lC\u001e,'bAA0S%!\u0011\u0011NA6\u0005%!\u0015\r^1Ge\u0006lWM\u0003\u0003\u0002d\u0005\u0015\u0004bBA8\u001f\u0001\u0007\u0011\u0011O\u0001\bI\u0006$\u0018m]3ua\u0011\t\u0019(a \u0011\r\u0005U\u0014qOA>\u001b\t\t)'\u0003\u0003\u0002z\u0005\u0015$a\u0002#bi\u0006\u001cX\r\u001e\t\u0005\u0003{\ny\b\u0004\u0001\u0005\u0019\u0005\u0005\u0015QNA\u0001\u0002\u0003\u0015\t!a!\u0003\u0007}#\u0013'\u0005\u0003\u0002\u0006\u0006-\u0005c\u00014\u0002\b&\u0019\u0011\u0011R)\u0003\u000f9{G\u000f[5oOB\u0019a-!$\n\u0007\u0005=\u0015KA\u0002B]fDCaD-\u0002$\u0005yAO]1og\u001a|'/\\*dQ\u0016l\u0017\r\u0006\u0003\u0002\u0018\u0006\r\u0006\u0003BAM\u0003?k!!a'\u000b\t\u0005u\u0015QM\u0001\u0006if\u0004Xm]\u0005\u0005\u0003C\u000bYJ\u0001\u0006TiJ,8\r\u001e+za\u0016Dq!!*\u0011\u0001\u0004\t9*\u0001\u0004tG\",W.\u0019\u0015\u0004!e{\u0016aB5oI\u0016DxJ\u001a\u000b\u0004K\u00065\u0006bBAX#\u0001\u0007\u00111R\u0001\u0005i\u0016\u0014X\u000eK\u0002\u00123^\fAaY8qsR\u0019\u0001/a.\t\u000f\u0005e&\u00031\u0001\u0002<\u0006)Q\r\u001f;sCB!\u00111DA_\u0013\r\ty,\u000f\u0002\t!\u0006\u0014\u0018-\\'ba\"\"!#WAbC\t\t)-A\u00032]Qr\u0013'\u0001\u0005u_N#(/\u001b8h)\u0005Y\u0005fA\nZo\u0006!1/\u0019<f)\u0011\t\t.a6\u0011\u0007\u0019\f\u0019.C\u0002\u0002VF\u0013A!\u00168ji\"1\u0011\u0011\u001c\u000bA\u0002-\u000bA\u0001]1uQ\"\u001aA#W<)\u0007\u0001I60A\u0005ICND\u0017N\\4U\rB\u0011\u0011OF\n\b-\u0005\u0015\u00181^Ay!\r1\u0017q]\u0005\u0004\u0003S\f&AB!osJ+g\r\u0005\u0003E\u0003[\u0004\u0018bAAx\u000b\n)B)\u001a4bk2$\b+\u0019:b[N\u0014V-\u00193bE2,\u0007\u0003BAz\u0003{l!!!>\u000b\t\u0005]\u0018\u0011`\u0001\u0003S>T!!a?\u0002\t)\fg/Y\u0005\u0005\u0003\u007f\f)P\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0006\u0002\u0002b\u0006!2\u000bU!S\u0017~\u0013t,T+S\u001bV\u00136g\u0018%B'\"\u000bQc\u0015)B%.{&gX'V%6+&kM0I\u0003NC\u0005%\u0001\u000bT!\u0006\u00136jX\u001a`\u001bV\u0013V*\u0016*4?\"\u000b5\u000bS\u0001\u0016'B\u000b%kS04?6+&+T+Sg}C\u0015i\u0015%!\u0005=A\u0015m\u001d5j]\u001e$fIU3bI\u0016\u00148c\u0001\u000f\u0003\u0010A!AI!\u0005q\u0013\r\u0011\u0019\"\u0012\u0002\t\u001b2\u0013V-\u00193feR\u0011!q\u0003\t\u0004\u00053aR\"\u0001\f\u0002\u0013\rd\u0017m]:OC6,WC\u0001B\u0010!\u0011\u0011\tCa\n\u000e\u0005\t\r\"\u0002\u0002B\u0013\u0003s\fA\u0001\\1oO&\u0019aKa\t\u0002\u0015\rd\u0017m]:OC6,\u0007%\u0001\u0003m_\u0006$Gc\u00019\u00030!1\u0011\u0011\u001c\u0011A\u0002-\u000bAA]3bIV\u0011!q\u0002\u0015\u0004Ce;Hc\u00019\u0003:!1\u0011\u0011\u001c\u0012A\u0002-CCAI-\u0003>\u0005\u0012!qH\u0001\u0006c92d\u0006M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005\u000b\u0002BA!\t\u0003H%!!\u0011\nB\u0012\u0005\u0019y%M[3di\"\"a#\u0017B\u001fQ\u0011)\u0012L!\u0010"
)
public class HashingTF extends Transformer implements HasInputCol, HasOutputCol, HasNumFeatures, DefaultParamsWritable {
   private final String uid;
   private final int hashFuncVersion;
   private final BooleanParam binary;
   private IntParam numFeatures;
   private Param outputCol;
   private Param inputCol;

   public static HashingTF load(final String path) {
      return HashingTF$.MODULE$.load(path);
   }

   public static MLReader read() {
      return HashingTF$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public final int getNumFeatures() {
      return HasNumFeatures.getNumFeatures$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public final IntParam numFeatures() {
      return this.numFeatures;
   }

   public final void org$apache$spark$ml$param$shared$HasNumFeatures$_setter_$numFeatures_$eq(final IntParam x$1) {
      this.numFeatures = x$1;
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

   public int hashFuncVersion() {
      return this.hashFuncVersion;
   }

   public HashingTF setInputCol(final String value) {
      return (HashingTF)this.set(this.inputCol(), value);
   }

   public HashingTF setOutputCol(final String value) {
      return (HashingTF)this.set(this.outputCol(), value);
   }

   public BooleanParam binary() {
      return this.binary;
   }

   public HashingTF setNumFeatures(final int value) {
      return (HashingTF)this.set(this.numFeatures(), BoxesRunTime.boxToInteger(value));
   }

   public boolean getBinary() {
      return BoxesRunTime.unboxToBoolean(this.$(this.binary()));
   }

   public HashingTF setBinary(final boolean value) {
      return (HashingTF)this.set(this.binary(), BoxesRunTime.boxToBoolean(value));
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema());
      int n = BoxesRunTime.unboxToInt(this.$(this.numFeatures()));
      Function1 updateFunc = BoxesRunTime.unboxToBoolean(this.$(this.binary())) ? (v) -> (double)1.0F : (v) -> v + (double)1.0F;
      functions var10000 = .MODULE$;
      Function1 var10001 = (terms) -> {
         OpenHashMap map = new OpenHashMap.mcD.sp(scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.Double());
         terms.foreach((term) -> BoxesRunTime.boxToDouble($anonfun$transform$4(this, map, updateFunc, term)));
         return org.apache.spark.ml.linalg.Vectors..MODULE$.sparse(n, map.toSeq());
      };
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(HashingTF.class.getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator1$1() {
         }
      }

      TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1());
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(HashingTF.class.getClassLoader());

      final class $typecreator2$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            Symbols.SymbolApi symdef$hashUDF1 = $u.internal().reificationSupport().newNestedSymbol($u.internal().reificationSupport().selectTerm($m$untyped.staticClass("org.apache.spark.ml.feature.HashingTF"), "transform"), (Names.NameApi)$u.TermName().apply("hashUDF"), $u.NoPosition(), $u.internal().reificationSupport().FlagsRepr().apply(549755813888L), false);
            Symbols.SymbolApi symdef$$anonfun1 = $u.internal().reificationSupport().newNestedSymbol(symdef$hashUDF1, (Names.NameApi)$u.TermName().apply("$anonfun"), $u.NoPosition(), $u.internal().reificationSupport().FlagsRepr().apply(2097152L), false);
            Symbols.SymbolApi symdef$terms1 = $u.internal().reificationSupport().newNestedSymbol(symdef$$anonfun1, (Names.NameApi)$u.TermName().apply("terms"), $u.NoPosition(), $u.internal().reificationSupport().FlagsRepr().apply(17592186052608L), false);
            Symbols.SymbolApi symdef$_$21 = $u.internal().reificationSupport().newNestedSymbol(symdef$terms1, (Names.NameApi)$u.TypeName().apply("_$2"), $u.NoPosition(), $u.internal().reificationSupport().FlagsRepr().apply(34359738384L), false);
            $u.internal().reificationSupport().setInfo(symdef$hashUDF1, $u.NoType());
            $u.internal().reificationSupport().setInfo(symdef$$anonfun1, $u.NoType());
            $u.internal().reificationSupport().setInfo(symdef$terms1, (Types.TypeApi)$u.internal().reificationSupport().ExistentialType(new scala.collection.immutable..colon.colon(symdef$_$21, scala.collection.immutable.Nil..MODULE$), $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "Seq"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.NoPrefix(), symdef$_$21, scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$))));
            $u.internal().reificationSupport().setInfo(symdef$_$21, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
            return (Types.TypeApi)$u.internal().reificationSupport().ExistentialType(new scala.collection.immutable..colon.colon(symdef$_$21, scala.collection.immutable.Nil..MODULE$), $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "Seq"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.NoPrefix(), symdef$_$21, scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$)));
         }

         public $typecreator2$1() {
         }
      }

      UserDefinedFunction hashUDF = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
      return dataset.withColumn((String)this.$(this.outputCol()), hashUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.col((String)this.$(this.inputCol()))}))), outputSchema.apply((String)this.$(this.outputCol())).metadata());
   }

   public StructType transformSchema(final StructType schema) {
      DataType inputType = SchemaUtils$.MODULE$.getSchemaFieldType(schema, (String)this.$(this.inputCol()));
      scala.Predef..MODULE$.require(inputType instanceof ArrayType, () -> {
         String var10000 = org.apache.spark.sql.types.ArrayType..MODULE$.simpleString();
         return "The input column must be " + var10000 + ", but got " + inputType.catalogString() + ".";
      });
      AttributeGroup attrGroup = new AttributeGroup((String)this.$(this.outputCol()), BoxesRunTime.unboxToInt(this.$(this.numFeatures())));
      return SchemaUtils$.MODULE$.appendColumn(schema, attrGroup.toStructField());
   }

   public int indexOf(final Object term) {
      int var4 = this.hashFuncVersion();
      int var10000;
      if (HashingTF$.MODULE$.SPARK_2_MURMUR3_HASH() == var4) {
         var10000 = org.apache.spark.mllib.feature.HashingTF$.MODULE$.murmur3Hash(term);
      } else {
         if (HashingTF$.MODULE$.SPARK_3_MURMUR3_HASH() != var4) {
            throw new IllegalArgumentException("Illegal hash function version setting.");
         }

         var10000 = FeatureHasher$.MODULE$.murmur3Hash(term);
      }

      int hashValue = var10000;
      return org.apache.spark.util.Utils..MODULE$.nonNegativeMod(hashValue, BoxesRunTime.unboxToInt(this.$(this.numFeatures())));
   }

   public HashingTF copy(final ParamMap extra) {
      return (HashingTF)this.defaultCopy(extra);
   }

   public String toString() {
      String var10000 = this.uid();
      return "HashingTF: uid=" + var10000 + ", binary=" + this.$(this.binary()) + ", numFeatures=" + this.$(this.numFeatures());
   }

   public void save(final String path) {
      scala.Predef..MODULE$.require(this.hashFuncVersion() == HashingTF$.MODULE$.SPARK_3_MURMUR3_HASH(), () -> "Cannot save model which is loaded from lower version spark saved model. We can address it by (1) use old spark version to save the model, or (2) use new version spark to re-train the pipeline.");
      MLWritable.save$(this, path);
   }

   // $FF: synthetic method
   public static final double $anonfun$transform$4(final HashingTF $this, final OpenHashMap map$1, final Function1 updateFunc$1, final Object term) {
      return map$1.changeValue$mcD$sp(BoxesRunTime.boxToInteger($this.indexOf(term)), (JFunction0.mcD.sp)() -> (double)1.0F, updateFunc$1);
   }

   public HashingTF(final String uid, final int hashFuncVersion) {
      this.uid = uid;
      this.hashFuncVersion = hashFuncVersion;
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      HasNumFeatures.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      this.binary = new BooleanParam(this, "binary", "If true, all non zero counts are set to 1. This is useful for discrete probabilistic models that model binary events rather than integer counts");
      this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{this.binary().$minus$greater(BoxesRunTime.boxToBoolean(false))}));
      Statics.releaseFence();
   }

   public HashingTF() {
      this(Identifiable$.MODULE$.randomUID("hashingTF"), HashingTF$.MODULE$.SPARK_3_MURMUR3_HASH());
   }

   public HashingTF(final String uid) {
      this(uid, HashingTF$.MODULE$.SPARK_3_MURMUR3_HASH());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private static class HashingTFReader extends MLReader {
      private final String className = HashingTF.class.getName();

      private String className() {
         return this.className;
      }

      public HashingTF load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         Tuple2 var5 = org.apache.spark.util.VersionUtils..MODULE$.majorMinorVersion(metadata.sparkVersion());
         if (var5 != null) {
            int majorVersion = var5._1$mcI$sp();
            int hashFuncVersion = majorVersion < 3 ? HashingTF$.MODULE$.SPARK_2_MURMUR3_HASH() : HashingTF$.MODULE$.SPARK_3_MURMUR3_HASH();
            HashingTF hashingTF = new HashingTF(metadata.uid(), hashFuncVersion);
            metadata.getAndSetParams(hashingTF, metadata.getAndSetParams$default$2());
            return hashingTF;
         } else {
            throw new MatchError(var5);
         }
      }

      public HashingTFReader() {
      }
   }
}
