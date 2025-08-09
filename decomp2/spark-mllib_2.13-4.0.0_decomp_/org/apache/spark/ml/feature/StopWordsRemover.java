package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.spark.ml.Transformer;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.StringArrayParam;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasInputCols;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.param.shared.HasOutputCols;
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
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
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
   bytes = "\u0006\u0005\tud\u0001B\u0012%\u0001=B\u0001b\u0013\u0001\u0003\u0006\u0004%\t\u0005\u0014\u0005\tG\u0002\u0011\t\u0011)A\u0005\u001b\")Q\r\u0001C\u0001M\")Q\r\u0001C\u0001Y\")a\u000e\u0001C\u0001_\")A\u000f\u0001C\u0001k\")\u0001\u0010\u0001C\u0001s\"9\u0011Q\u0001\u0001\u0005\u0002\u0005\u001d\u0001\"CA\u0007\u0001\t\u0007I\u0011AA\b\u0011!\tY\u0002\u0001Q\u0001\n\u0005E\u0001bBA\u0010\u0001\u0011\u0005\u0011\u0011\u0005\u0005\b\u0003O\u0001A\u0011AA\u0015\u0011%\ti\u0003\u0001b\u0001\n\u0003\ty\u0003\u0003\u0005\u0002:\u0001\u0001\u000b\u0011BA\u0019\u0011\u001d\ti\u0004\u0001C\u0001\u0003\u007fAq!a\u0013\u0001\t\u0003\ti\u0005C\u0005\u0002R\u0001\u0011\r\u0011\"\u0001\u0002T!A\u0011\u0011\r\u0001!\u0002\u0013\t)\u0006C\u0004\u0002f\u0001!\t!a\u001a\t\r\u00055\u0004\u0001\"\u0001M\u0011!\t\t\b\u0001C\u0001I\u0005M\u0004bBA>\u0001\u0011\u0005\u0013Q\u0010\u0005\b\u0003\u0017\u0004A\u0011IAg\u0011\u001d\t\t\u000f\u0001C!\u0003GDq!!=\u0001\t\u0003\n\u0019pB\u0004\u0002z\u0012B\t!a?\u0007\r\r\"\u0003\u0012AA\u007f\u0011\u0019)7\u0004\"\u0001\u0003(!Q!\u0011F\u000eC\u0002\u0013\u0005AEa\u000b\t\u0011\t\u001d3\u0004)A\u0005\u0005[AqA!\u0013\u001c\t\u0003\u0012Y\u0005C\u0004\u0003Xm!\tA!\u0017\t\u0011\t\u00054\u0004\"\u0001)\u0005GB\u0011Ba\u001c\u001c\u0003\u0003%IA!\u001d\u0003!M#x\u000e],pe\u0012\u001c(+Z7pm\u0016\u0014(BA\u0013'\u0003\u001d1W-\u0019;ve\u0016T!a\n\u0015\u0002\u00055d'BA\u0015+\u0003\u0015\u0019\b/\u0019:l\u0015\tYC&\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002[\u0005\u0019qN]4\u0004\u0001M9\u0001\u0001\r\u001b=\u007f\t+\u0005CA\u00193\u001b\u00051\u0013BA\u001a'\u0005-!&/\u00198tM>\u0014X.\u001a:\u0011\u0005URT\"\u0001\u001c\u000b\u0005]B\u0014AB:iCJ,GM\u0003\u0002:M\u0005)\u0001/\u0019:b[&\u00111H\u000e\u0002\f\u0011\u0006\u001c\u0018J\u001c9vi\u000e{G\u000e\u0005\u00026{%\u0011aH\u000e\u0002\r\u0011\u0006\u001cx*\u001e;qkR\u001cu\u000e\u001c\t\u0003k\u0001K!!\u0011\u001c\u0003\u0019!\u000b7/\u00138qkR\u001cu\u000e\\:\u0011\u0005U\u001a\u0015B\u0001#7\u00055A\u0015m](viB,HoQ8mgB\u0011a)S\u0007\u0002\u000f*\u0011\u0001JJ\u0001\u0005kRLG.\u0003\u0002K\u000f\n)B)\u001a4bk2$\b+\u0019:b[N<&/\u001b;bE2,\u0017aA;jIV\tQ\n\u0005\u0002O/:\u0011q*\u0016\t\u0003!Nk\u0011!\u0015\u0006\u0003%:\na\u0001\u0010:p_Rt$\"\u0001+\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u001b\u0016A\u0002)sK\u0012,g-\u0003\u0002Y3\n11\u000b\u001e:j]\u001eT!AV*)\u0007\u0005Y\u0016\r\u0005\u0002]?6\tQL\u0003\u0002_Q\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\u0001l&!B*j]\u000e,\u0017%\u00012\u0002\u000bErSG\f\u0019\u0002\tULG\r\t\u0015\u0004\u0005m\u000b\u0017A\u0002\u001fj]&$h\b\u0006\u0002hSB\u0011\u0001\u000eA\u0007\u0002I!)1j\u0001a\u0001\u001b\"\u001a\u0011nW1)\u0007\rY\u0016\rF\u0001hQ\r!1,Y\u0001\fg\u0016$\u0018J\u001c9vi\u000e{G\u000e\u0006\u0002qc6\t\u0001\u0001C\u0003s\u000b\u0001\u0007Q*A\u0003wC2,X\rK\u0002\u00067\u0006\fAb]3u\u001fV$\b/\u001e;D_2$\"\u0001\u001d<\t\u000bI4\u0001\u0019A')\u0007\u0019Y\u0016-\u0001\u0007tKRLe\u000e];u\u0007>d7\u000f\u0006\u0002qu\")!o\u0002a\u0001wB\u0019A0`'\u000e\u0003MK!A`*\u0003\u000b\u0005\u0013(/Y=)\t\u001dY\u0016\u0011A\u0011\u0003\u0003\u0007\tQa\r\u00181]A\nQb]3u\u001fV$\b/\u001e;D_2\u001cHc\u00019\u0002\n!)!\u000f\u0003a\u0001w\"\"\u0001bWA\u0001\u0003%\u0019Ho\u001c9X_J$7/\u0006\u0002\u0002\u0012A!\u00111CA\u000b\u001b\u0005A\u0014bAA\fq\t\u00012\u000b\u001e:j]\u001e\f%O]1z!\u0006\u0014\u0018-\u001c\u0015\u0004\u0013m\u000b\u0017AC:u_B<vN\u001d3tA!\u001a!bW1\u0002\u0019M,Go\u0015;pa^{'\u000fZ:\u0015\u0007A\f\u0019\u0003C\u0003s\u0017\u0001\u00071\u0010K\u0002\f7\u0006\fAbZ3u'R|\u0007oV8sIN,\u0012a\u001f\u0015\u0004\u0019m\u000b\u0017!D2bg\u0016\u001cVM\\:ji&4X-\u0006\u0002\u00022A!\u00111CA\u001a\u0013\r\t)\u0004\u000f\u0002\r\u0005>|G.Z1o!\u0006\u0014\u0018-\u001c\u0015\u0004\u001bm\u000b\u0017AD2bg\u0016\u001cVM\\:ji&4X\r\t\u0015\u0004\u001dm\u000b\u0017\u0001E:fi\u000e\u000b7/Z*f]NLG/\u001b<f)\r\u0001\u0018\u0011\t\u0005\u0007e>\u0001\r!a\u0011\u0011\u0007q\f)%C\u0002\u0002HM\u0013qAQ8pY\u0016\fg\u000eK\u0002\u00107\u0006\f\u0001cZ3u\u0007\u0006\u001cXmU3og&$\u0018N^3\u0016\u0005\u0005\r\u0003f\u0001\t\\C\u00061An\\2bY\u0016,\"!!\u0016\u0011\u000b\u0005M\u0011qK'\n\u0007\u0005e\u0003HA\u0003QCJ\fW\u000e\u000b\u0003\u00127\u0006u\u0013EAA0\u0003\u0015\u0011d\u0006\u000e\u00181\u0003\u001dawnY1mK\u0002BCAE.\u0002^\u0005I1/\u001a;M_\u000e\fG.\u001a\u000b\u0004a\u0006%\u0004\"\u0002:\u0014\u0001\u0004i\u0005\u0006B\n\\\u0003;\n\u0011bZ3u\u0019>\u001c\u0017\r\\3)\tQY\u0016QL\u0001\rO\u0016$\u0018J\\(vi\u000e{Gn\u001d\u000b\u0003\u0003k\u0002R\u0001`A<wnL1!!\u001fT\u0005\u0019!V\u000f\u001d7fe\u0005IAO]1og\u001a|'/\u001c\u000b\u0005\u0003\u007f\n\t\u000b\u0005\u0003\u0002\u0002\u0006me\u0002BAB\u0003+sA!!\"\u0002\u0012:!\u0011qQAH\u001d\u0011\tI)!$\u000f\u0007A\u000bY)C\u0001.\u0013\tYC&\u0003\u0002*U%\u0019\u00111\u0013\u0015\u0002\u0007M\fH.\u0003\u0003\u0002\u0018\u0006e\u0015a\u00029bG.\fw-\u001a\u0006\u0004\u0003'C\u0013\u0002BAO\u0003?\u0013\u0011\u0002R1uC\u001a\u0013\u0018-\\3\u000b\t\u0005]\u0015\u0011\u0014\u0005\b\u0003G3\u0002\u0019AAS\u0003\u001d!\u0017\r^1tKR\u0004D!a*\u00024B1\u0011\u0011VAV\u0003_k!!!'\n\t\u00055\u0016\u0011\u0014\u0002\b\t\u0006$\u0018m]3u!\u0011\t\t,a-\r\u0001\u0011a\u0011QWAQ\u0003\u0003\u0005\tQ!\u0001\u00028\n\u0019q\fJ\u0019\u0012\t\u0005e\u0016q\u0018\t\u0004y\u0006m\u0016bAA_'\n9aj\u001c;iS:<\u0007c\u0001?\u0002B&\u0019\u00111Y*\u0003\u0007\u0005s\u0017\u0010\u000b\u0003\u00177\u0006\u001d\u0017EAAe\u0003\u0015\u0011d\u0006\r\u00181\u0003=!(/\u00198tM>\u0014XnU2iK6\fG\u0003BAh\u00037\u0004B!!5\u0002X6\u0011\u00111\u001b\u0006\u0005\u0003+\fI*A\u0003usB,7/\u0003\u0003\u0002Z\u0006M'AC*ueV\u001cG\u000fV=qK\"9\u0011Q\\\fA\u0002\u0005=\u0017AB:dQ\u0016l\u0017\rK\u0002\u00187\u0006\fAaY8qsR\u0019q-!:\t\u000f\u0005\u001d\b\u00041\u0001\u0002j\u0006)Q\r\u001f;sCB!\u00111CAv\u0013\r\ti\u000f\u000f\u0002\t!\u0006\u0014\u0018-\\'ba\"\u001a\u0001dW1\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012!\u0014\u0015\u00053m\u000b\t\u0001K\u0002\u00017\u0006\f\u0001c\u0015;pa^{'\u000fZ:SK6|g/\u001a:\u0011\u0005!\\2#C\u000e\u0002\u0000\n\u0015!1\u0002B\f!\ra(\u0011A\u0005\u0004\u0005\u0007\u0019&AB!osJ+g\r\u0005\u0003G\u0005\u000f9\u0017b\u0001B\u0005\u000f\n)B)\u001a4bk2$\b+\u0019:b[N\u0014V-\u00193bE2,\u0007\u0003\u0002B\u0007\u0005'i!Aa\u0004\u000b\u0007\tE\u0001&\u0001\u0005j]R,'O\\1m\u0013\u0011\u0011)Ba\u0004\u0003\u000f1{wmZ5oOB!!\u0011\u0004B\u0012\u001b\t\u0011YB\u0003\u0003\u0003\u001e\t}\u0011AA5p\u0015\t\u0011\t#\u0001\u0003kCZ\f\u0017\u0002\u0002B\u0013\u00057\u0011AbU3sS\u0006d\u0017N_1cY\u0016$\"!a?\u0002%M,\b\u000f]8si\u0016$G*\u00198hk\u0006<Wm]\u000b\u0003\u0005[\u0001bAa\f\u0003:\tuRB\u0001B\u0019\u0015\u0011\u0011\u0019D!\u000e\u0002\u0013%lW.\u001e;bE2,'b\u0001B\u001c'\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\tm\"\u0011\u0007\u0002\u0004'\u0016$\b\u0003\u0002B \u0005\u000bj!A!\u0011\u000b\t\t\r#qD\u0001\u0005Y\u0006tw-C\u0002Y\u0005\u0003\n1c];qa>\u0014H/\u001a3MC:<W/Y4fg\u0002\nA\u0001\\8bIR\u0019qM!\u0014\t\r\t=s\u00041\u0001N\u0003\u0011\u0001\u0018\r\u001e5)\t}Y&1K\u0011\u0003\u0005+\nQ!\r\u00187]A\nA\u0003\\8bI\u0012+g-Y;miN#x\u000e],pe\u0012\u001cHcA>\u0003\\!1!Q\f\u0011A\u00025\u000b\u0001\u0002\\1oOV\fw-\u001a\u0015\u0005Am\u000b9-\u0001\bhKR$UMZ1vYR|%/V*\u0016\u0005\t\u0015\u0004\u0003\u0002B4\u0005Wj!A!\u001b\u000b\u0007!\u0013y\"\u0003\u0003\u0003n\t%$A\u0002'pG\u0006dW-\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003tA!!q\bB;\u0013\u0011\u00119H!\u0011\u0003\r=\u0013'.Z2uQ\u0011Y2La\u0015)\tiY&1\u000b"
)
public class StopWordsRemover extends Transformer implements HasInputCol, HasOutputCol, HasInputCols, HasOutputCols, DefaultParamsWritable {
   private final String uid;
   private final StringArrayParam stopWords;
   private final BooleanParam caseSensitive;
   private final Param locale;
   private StringArrayParam outputCols;
   private StringArrayParam inputCols;
   private Param outputCol;
   private Param inputCol;

   public static String[] loadDefaultStopWords(final String language) {
      return StopWordsRemover$.MODULE$.loadDefaultStopWords(language);
   }

   public static StopWordsRemover load(final String path) {
      return StopWordsRemover$.MODULE$.load(path);
   }

   public static MLReader read() {
      return StopWordsRemover$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final String[] getOutputCols() {
      return HasOutputCols.getOutputCols$(this);
   }

   public final String[] getInputCols() {
      return HasInputCols.getInputCols$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public final StringArrayParam outputCols() {
      return this.outputCols;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCols$_setter_$outputCols_$eq(final StringArrayParam x$1) {
      this.outputCols = x$1;
   }

   public final StringArrayParam inputCols() {
      return this.inputCols;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCols$_setter_$inputCols_$eq(final StringArrayParam x$1) {
      this.inputCols = x$1;
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

   public StopWordsRemover setInputCol(final String value) {
      return (StopWordsRemover)this.set(this.inputCol(), value);
   }

   public StopWordsRemover setOutputCol(final String value) {
      return (StopWordsRemover)this.set(this.outputCol(), value);
   }

   public StopWordsRemover setInputCols(final String[] value) {
      return (StopWordsRemover)this.set(this.inputCols(), value);
   }

   public StopWordsRemover setOutputCols(final String[] value) {
      return (StopWordsRemover)this.set(this.outputCols(), value);
   }

   public StringArrayParam stopWords() {
      return this.stopWords;
   }

   public StopWordsRemover setStopWords(final String[] value) {
      return (StopWordsRemover)this.set(this.stopWords(), value);
   }

   public String[] getStopWords() {
      return (String[])this.$(this.stopWords());
   }

   public BooleanParam caseSensitive() {
      return this.caseSensitive;
   }

   public StopWordsRemover setCaseSensitive(final boolean value) {
      return (StopWordsRemover)this.set(this.caseSensitive(), BoxesRunTime.boxToBoolean(value));
   }

   public boolean getCaseSensitive() {
      return BoxesRunTime.unboxToBoolean(this.$(this.caseSensitive()));
   }

   public Param locale() {
      return this.locale;
   }

   public StopWordsRemover setLocale(final String value) {
      return (StopWordsRemover)this.set(this.locale(), value);
   }

   public String getLocale() {
      return (String)this.$(this.locale());
   }

   public Tuple2 getInOutCols() {
      return this.isSet(this.inputCol()) ? new Tuple2((Object[])(new String[]{(String)this.$(this.inputCol())}), (Object[])(new String[]{(String)this.$(this.outputCol())})) : new Tuple2(this.$(this.inputCols()), this.$(this.outputCols()));
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema());
      UserDefinedFunction var25;
      if (BoxesRunTime.unboxToBoolean(this.$(this.caseSensitive()))) {
         Set stopWordsSet = .MODULE$.wrapRefArray(this.$(this.stopWords())).toSet();
         functions var10000 = org.apache.spark.sql.functions..MODULE$;
         Function1 var10001 = (terms) -> (Seq)terms.filter((s) -> BoxesRunTime.boxToBoolean($anonfun$transform$2(stopWordsSet, s)));
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(StopWordsRemover.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala.collection.immutable").asModule().moduleClass()), $m$untyped.staticClass("scala.collection.immutable.Seq"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$1() {
            }
         }

         TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1());
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(StopWordsRemover.class.getClassLoader());

         final class $typecreator2$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "Seq"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator2$1() {
            }
         }

         var25 = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
      } else {
         Locale lc = new Locale((String)this.$(this.locale()));
         Function1 toLower = (s) -> s != null ? s.toLowerCase(lc) : s;
         Set lowerStopWords = .MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(this.$(this.stopWords())), (x$2) -> (String)toLower.apply(x$2), scala.reflect.ClassTag..MODULE$.apply(String.class))).toSet();
         functions var26 = org.apache.spark.sql.functions..MODULE$;
         Function1 var27 = (terms) -> (Seq)terms.filter((s) -> BoxesRunTime.boxToBoolean($anonfun$transform$6(lowerStopWords, toLower, s)));
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(StopWordsRemover.class.getClassLoader());

         final class $typecreator3$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala.collection.immutable").asModule().moduleClass()), $m$untyped.staticClass("scala.collection.immutable.Seq"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator3$1() {
            }
         }

         TypeTags.TypeTag var28 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator3$1());
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(StopWordsRemover.class.getClassLoader());

         final class $typecreator4$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "Seq"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator4$1() {
            }
         }

         var25 = var26.udf(var27, var28, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator4$1()));
      }

      UserDefinedFunction t = var25;
      Tuple2 var18 = this.getInOutCols();
      if (var18 != null) {
         String[] inputColNames = (String[])var18._1();
         String[] outputColNames = (String[])var18._2();
         Tuple2 var17 = new Tuple2(inputColNames, outputColNames);
         String[] inputColNames = (String[])var17._1();
         String[] outputColNames = (String[])var17._2();
         Column[] outputCols = (Column[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])inputColNames), (inputColName) -> t.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(inputColName)}))), scala.reflect.ClassTag..MODULE$.apply(Column.class));
         Metadata[] outputMetadata = (Metadata[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])outputColNames), (x$4) -> outputSchema.apply(x$4).metadata(), scala.reflect.ClassTag..MODULE$.apply(Metadata.class));
         return dataset.withColumns(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(outputColNames).toImmutableArraySeq(), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(outputCols).toImmutableArraySeq(), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(outputMetadata).toImmutableArraySeq());
      } else {
         throw new MatchError(var18);
      }
   }

   public StructType transformSchema(final StructType schema) {
      ParamValidators$.MODULE$.checkSingleVsMultiColumnParams(this, new scala.collection.immutable..colon.colon(this.outputCol(), scala.collection.immutable.Nil..MODULE$), new scala.collection.immutable..colon.colon(this.outputCols(), scala.collection.immutable.Nil..MODULE$));
      if (this.isSet(this.inputCols())) {
         .MODULE$.require(this.getInputCols().length == this.getOutputCols().length, () -> "StopWordsRemover " + this + " has mismatched Params for multi-column transform. Params (" + this.inputCols() + ", " + this.outputCols() + ") should have equal lengths, but they have different lengths: (" + this.getInputCols().length + ", " + this.getOutputCols().length + ").");
      }

      Tuple2 var4 = this.getInOutCols();
      if (var4 != null) {
         String[] inputColNames = (String[])var4._1();
         String[] outputColNames = (String[])var4._2();
         Tuple2 var3 = new Tuple2(inputColNames, outputColNames);
         String[] inputColNames = (String[])var3._1();
         String[] outputColNames = (String[])var3._2();
         StructField[] newCols = (StructField[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(.MODULE$.refArrayOps((Object[])inputColNames), .MODULE$.wrapRefArray((Object[])outputColNames))), (x0$1) -> {
            if (x0$1 != null) {
               String inputColName = (String)x0$1._1();
               String outputColName = (String)x0$1._2();
               .MODULE$.require(!scala.collection.ArrayOps..MODULE$.contains$extension(.MODULE$.refArrayOps((Object[])schema.fieldNames()), outputColName), () -> "Output Column " + outputColName + " already exists.");
               DataType inputType = SchemaUtils$.MODULE$.getSchemaFieldType(schema, inputColName);
               .MODULE$.require(org.apache.spark.sql.catalyst.types.DataTypeUtils..MODULE$.sameType(inputType, org.apache.spark.sql.types.ArrayType..MODULE$.apply(org.apache.spark.sql.types.StringType..MODULE$)), () -> {
                  String var10000 = org.apache.spark.sql.types.ArrayType..MODULE$.apply(org.apache.spark.sql.types.StringType..MODULE$).catalogString();
                  return "Input type must be " + var10000 + " but got " + inputType.catalogString() + ".";
               });
               return new StructField(outputColName, inputType, SchemaUtils$.MODULE$.getSchemaField(schema, inputColName).nullable(), org.apache.spark.sql.types.StructField..MODULE$.apply$default$4());
            } else {
               throw new MatchError(x0$1);
            }
         }, scala.reflect.ClassTag..MODULE$.apply(StructField.class));
         return new StructType((StructField[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(.MODULE$.refArrayOps((Object[])schema.fields()), newCols, scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
      } else {
         throw new MatchError(var4);
      }
   }

   public StopWordsRemover copy(final ParamMap extra) {
      return (StopWordsRemover)this.defaultCopy(extra);
   }

   public String toString() {
      String var10000 = this.uid();
      return "StopWordsRemover: uid=" + var10000 + ", numStopWords=" + ((String[])this.$(this.stopWords())).length + ", locale=" + this.$(this.locale()) + ", caseSensitive=" + this.$(this.caseSensitive());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$transform$2(final Set stopWordsSet$1, final String s) {
      return !stopWordsSet$1.contains(s);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$transform$6(final Set lowerStopWords$1, final Function1 toLower$1, final String s) {
      return !lowerStopWords$1.contains(toLower$1.apply(s));
   }

   public StopWordsRemover(final String uid) {
      this.uid = uid;
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      HasInputCols.$init$(this);
      HasOutputCols.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      this.stopWords = new StringArrayParam(this, "stopWords", "the words to be filtered out");
      this.caseSensitive = new BooleanParam(this, "caseSensitive", "whether to do a case-sensitive comparison over the stop words");
      this.locale = new Param(this, "locale", "Locale of the input for case insensitive matching. Ignored when caseSensitive is true.", ParamValidators$.MODULE$.inArray(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])Locale.getAvailableLocales()), (x$1) -> x$1.toString(), scala.reflect.ClassTag..MODULE$.apply(String.class))), scala.reflect.ClassTag..MODULE$.apply(String.class));
      this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{this.stopWords().$minus$greater(StopWordsRemover$.MODULE$.loadDefaultStopWords("english")), this.caseSensitive().$minus$greater(BoxesRunTime.boxToBoolean(false)), this.locale().$minus$greater(StopWordsRemover$.MODULE$.getDefaultOrUS().toString())}));
      Statics.releaseFence();
   }

   public StopWordsRemover() {
      this(Identifiable$.MODULE$.randomUID("stopWords"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
