package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.UnaryTransformer;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StringType;
import scala.Function1;
import scala.Predef;
import scala.collection.StringOps.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.util.matching.Regex;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mg\u0001B\u0010!\u0001-B\u0001B\u0014\u0001\u0003\u0006\u0004%\te\u0014\u0005\t3\u0002\u0011\t\u0011)A\u0005a!)1\f\u0001C\u00019\")1\f\u0001C\u0001A\"9!\r\u0001b\u0001\n\u0003\u0019\u0007BB6\u0001A\u0003%A\rC\u0003n\u0001\u0011\u0005a\u000eC\u0003x\u0001\u0011\u0005\u0001\u0010C\u0004{\u0001\t\u0007I\u0011A>\t\u000f\u0005\u0005\u0001\u0001)A\u0005y\"9\u0011Q\u0001\u0001\u0005\u0002\u0005\u001d\u0001bBA\n\u0001\u0011\u0005\u0011Q\u0003\u0005\n\u00033\u0001!\u0019!C\u0001\u00037A\u0001\"!\n\u0001A\u0003%\u0011Q\u0004\u0005\b\u0003S\u0001A\u0011AA\u0016\u0011\u0019\t\t\u0004\u0001C\u0001\u001f\"A\u0011Q\u0007\u0001C\u0002\u0013\u00151\u0010C\u0004\u0002>\u0001\u0001\u000bQ\u0002?\t\u000f\u0005\u0005\u0003\u0001\"\u0001\u0002D!9\u0011\u0011\n\u0001\u0005\u0002\u0005U\u0001bBA'\u0001\u0011E\u0013q\n\u0005\b\u0003/\u0002A\u0011KA-\u0011\u001d\t)\b\u0001C)\u0003oBq!!\u001f\u0001\t\u0003\nY\bC\u0004\u0002\u000e\u0002!\t%a$\b\u000f\u0005e\u0005\u0005#\u0001\u0002\u001c\u001a1q\u0004\tE\u0001\u0003;CaaW\u000e\u0005\u0002\u0005m\u0006bBA_7\u0011\u0005\u0013q\u0018\u0005\n\u0003\u000f\\\u0012\u0011!C\u0005\u0003\u0013\u0014aBU3hKb$vn[3oSj,'O\u0003\u0002\"E\u00059a-Z1ukJ,'BA\u0012%\u0003\tiGN\u0003\u0002&M\u0005)1\u000f]1sW*\u0011q\u0005K\u0001\u0007CB\f7\r[3\u000b\u0003%\n1a\u001c:h\u0007\u0001\u00192\u0001\u0001\u0017I!\u0015ic\u0006M\u001fG\u001b\u0005\u0011\u0013BA\u0018#\u0005A)f.\u0019:z)J\fgn\u001d4pe6,'\u000f\u0005\u00022u9\u0011!\u0007\u000f\t\u0003gYj\u0011\u0001\u000e\u0006\u0003k)\na\u0001\u0010:p_Rt$\"A\u001c\u0002\u000bM\u001c\u0017\r\\1\n\u0005e2\u0014A\u0002)sK\u0012,g-\u0003\u0002<y\t11\u000b\u001e:j]\u001eT!!\u000f\u001c\u0011\u0007y\u001a\u0005G\u0004\u0002@\u0003:\u00111\u0007Q\u0005\u0002o%\u0011!IN\u0001\ba\u0006\u001c7.Y4f\u0013\t!UIA\u0002TKFT!A\u0011\u001c\u0011\u0005\u001d\u0003Q\"\u0001\u0011\u0011\u0005%cU\"\u0001&\u000b\u0005-\u0013\u0013\u0001B;uS2L!!\u0014&\u0003+\u0011+g-Y;miB\u000b'/Y7t/JLG/\u00192mK\u0006\u0019Q/\u001b3\u0016\u0003AB3!A)X!\t\u0011V+D\u0001T\u0015\t!F%\u0001\u0006b]:|G/\u0019;j_:L!AV*\u0003\u000bMKgnY3\"\u0003a\u000bQ!\r\u00185]A\nA!^5eA!\u001a!!U,\u0002\rqJg.\u001b;?)\t1U\fC\u0003O\u0007\u0001\u0007\u0001\u0007K\u0002^#^C3aA)X)\u00051\u0005f\u0001\u0003R/\u0006qQ.\u001b8U_.,g\u000eT3oORDW#\u00013\u0011\u0005\u0015DW\"\u00014\u000b\u0005\u001d\u0014\u0013!\u00029be\u0006l\u0017BA5g\u0005!Ie\u000e\u001e)be\u0006l\u0007fA\u0003R/\u0006yQ.\u001b8U_.,g\u000eT3oORD\u0007\u0005K\u0002\u0007#^\u000b\u0011c]3u\u001b&tGk\\6f]2+gn\u001a;i)\ty\u0007/D\u0001\u0001\u0011\u0015\tx\u00011\u0001s\u0003\u00151\u0018\r\\;f!\t\u0019H/D\u00017\u0013\t)hGA\u0002J]RD3aB)X\u0003E9W\r^'j]R{7.\u001a8MK:<G\u000f[\u000b\u0002e\"\u001a\u0001\"U,\u0002\t\u001d\f\u0007o]\u000b\u0002yB\u0011Q-`\u0005\u0003}\u001a\u0014ABQ8pY\u0016\fg\u000eU1sC6D3!C)X\u0003\u00159\u0017\r]:!Q\rQ\u0011kV\u0001\bg\u0016$x)\u00199t)\ry\u0017\u0011\u0002\u0005\u0007c.\u0001\r!a\u0003\u0011\u0007M\fi!C\u0002\u0002\u0010Y\u0012qAQ8pY\u0016\fg\u000eK\u0002\f#^\u000bqaZ3u\u000f\u0006\u00048/\u0006\u0002\u0002\f!\u001aA\"U,\u0002\u000fA\fG\u000f^3s]V\u0011\u0011Q\u0004\t\u0005K\u0006}\u0001'C\u0002\u0002\"\u0019\u0014Q\u0001U1sC6D3!D)X\u0003!\u0001\u0018\r\u001e;fe:\u0004\u0003f\u0001\bR/\u0006Q1/\u001a;QCR$XM\u001d8\u0015\u0007=\fi\u0003C\u0003r\u001f\u0001\u0007\u0001\u0007K\u0002\u0010#^\u000b!bZ3u!\u0006$H/\u001a:oQ\r\u0001\u0012kV\u0001\fi>dun^3sG\u0006\u001cX\r\u000b\u0003\u0012#\u0006e\u0012EAA\u001e\u0003\u0015\tdF\u000e\u00181\u00031!x\u000eT8xKJ\u001c\u0017m]3!Q\u0011\u0011\u0012+!\u000f\u0002\u001dM,G\u000fV8M_^,'oY1tKR\u0019q.!\u0012\t\rE\u001c\u0002\u0019AA\u0006Q\u0011\u0019\u0012+!\u000f\u0002\u001d\u001d,G\u000fV8M_^,'oY1tK\"\"A#UA\u001d\u0003M\u0019'/Z1uKR\u0013\u0018M\\:g_Jlg)\u001e8d+\t\t\t\u0006E\u0003t\u0003'\u0002T(C\u0002\u0002VY\u0012\u0011BR;oGRLwN\\\u0019\u0002#Y\fG.\u001b3bi\u0016Le\u000e];u)f\u0004X\r\u0006\u0003\u0002\\\u0005\u0005\u0004cA:\u0002^%\u0019\u0011q\f\u001c\u0003\tUs\u0017\u000e\u001e\u0005\b\u0003G2\u0002\u0019AA3\u0003%Ig\u000e];u)f\u0004X\r\u0005\u0003\u0002h\u0005ETBAA5\u0015\u0011\tY'!\u001c\u0002\u000bQL\b/Z:\u000b\u0007\u0005=D%A\u0002tc2LA!a\u001d\u0002j\tAA)\u0019;b)f\u0004X-\u0001\bpkR\u0004X\u000f\u001e#bi\u0006$\u0016\u0010]3\u0016\u0005\u0005\u0015\u0014\u0001B2paf$2ARA?\u0011\u001d\ty\b\u0007a\u0001\u0003\u0003\u000bQ!\u001a=ue\u0006\u00042!ZAB\u0013\r\t)I\u001a\u0002\t!\u0006\u0014\u0018-\\'ba\"\"\u0001$UAEC\t\tY)A\u00032]Qr\u0013'\u0001\u0005u_N#(/\u001b8h)\u0005\u0001\u0004\u0006B\rR\u0003'\u000b#!!&\u0002\u000bMr\u0003G\f\u0019)\u0007\u0001\tv+\u0001\bSK\u001e,\u0007\u0010V8lK:L'0\u001a:\u0011\u0005\u001d[2cB\u000e\u0002 \u0006\u0015\u00161\u0016\t\u0004g\u0006\u0005\u0016bAARm\t1\u0011I\\=SK\u001a\u0004B!SAT\r&\u0019\u0011\u0011\u0016&\u0003+\u0011+g-Y;miB\u000b'/Y7t%\u0016\fG-\u00192mKB!\u0011QVA\\\u001b\t\tyK\u0003\u0003\u00022\u0006M\u0016AA5p\u0015\t\t),\u0001\u0003kCZ\f\u0017\u0002BA]\u0003_\u0013AbU3sS\u0006d\u0017N_1cY\u0016$\"!a'\u0002\t1|\u0017\r\u001a\u000b\u0004\r\u0006\u0005\u0007BBAb;\u0001\u0007\u0001'\u0001\u0003qCRD\u0007\u0006B\u000fR\u0003s\tAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a3\u0011\t\u00055\u00171[\u0007\u0003\u0003\u001fTA!!5\u00024\u0006!A.\u00198h\u0013\u0011\t).a4\u0003\r=\u0013'.Z2uQ\u0011Y\u0012+!\u000f)\ti\t\u0016\u0011\b"
)
public class RegexTokenizer extends UnaryTransformer implements DefaultParamsWritable {
   private final String uid;
   private final IntParam minTokenLength;
   private final BooleanParam gaps;
   private final Param pattern;
   private final BooleanParam toLowercase;

   public static RegexTokenizer load(final String path) {
      return RegexTokenizer$.MODULE$.load(path);
   }

   public static MLReader read() {
      return RegexTokenizer$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public String uid() {
      return this.uid;
   }

   public IntParam minTokenLength() {
      return this.minTokenLength;
   }

   public RegexTokenizer setMinTokenLength(final int value) {
      return (RegexTokenizer)this.set(this.minTokenLength(), BoxesRunTime.boxToInteger(value));
   }

   public int getMinTokenLength() {
      return BoxesRunTime.unboxToInt(this.$(this.minTokenLength()));
   }

   public BooleanParam gaps() {
      return this.gaps;
   }

   public RegexTokenizer setGaps(final boolean value) {
      return (RegexTokenizer)this.set(this.gaps(), BoxesRunTime.boxToBoolean(value));
   }

   public boolean getGaps() {
      return BoxesRunTime.unboxToBoolean(this.$(this.gaps()));
   }

   public Param pattern() {
      return this.pattern;
   }

   public RegexTokenizer setPattern(final String value) {
      return (RegexTokenizer)this.set(this.pattern(), value);
   }

   public String getPattern() {
      return (String)this.$(this.pattern());
   }

   public final BooleanParam toLowercase() {
      return this.toLowercase;
   }

   public RegexTokenizer setToLowercase(final boolean value) {
      return (RegexTokenizer)this.set(this.toLowercase(), BoxesRunTime.boxToBoolean(value));
   }

   public boolean getToLowercase() {
      return BoxesRunTime.unboxToBoolean(this.$(this.toLowercase()));
   }

   public Function1 createTransformFunc() {
      Regex re = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.pattern())));
      boolean localToLowercase = BoxesRunTime.unboxToBoolean(this.$(this.toLowercase()));
      boolean localGaps = BoxesRunTime.unboxToBoolean(this.$(this.gaps()));
      int localMinTokenLength = BoxesRunTime.unboxToInt(this.$(this.minTokenLength()));
      return (originStr) -> {
         String str = localToLowercase ? originStr.toLowerCase() : originStr;
         Seq tokens = (Seq)(localGaps ? org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(re.split(str)).toImmutableArraySeq() : re.findAllIn(str).toSeq());
         return (Seq)tokens.filter((x$2) -> BoxesRunTime.boxToBoolean($anonfun$createTransformFunc$3(localMinTokenLength, x$2)));
      };
   }

   public void validateInputType(final DataType inputType) {
      Predef var10000;
      boolean var10001;
      label17: {
         label16: {
            var10000 = scala.Predef..MODULE$;
            StringType var2 = org.apache.spark.sql.types.StringType..MODULE$;
            if (inputType == null) {
               if (var2 == null) {
                  break label16;
               }
            } else if (inputType.equals(var2)) {
               break label16;
            }

            var10001 = false;
            break label17;
         }

         var10001 = true;
      }

      var10000.require(var10001, () -> "Input type must be string type but got " + inputType + ".");
   }

   public DataType outputDataType() {
      return new ArrayType(org.apache.spark.sql.types.StringType..MODULE$, true);
   }

   public RegexTokenizer copy(final ParamMap extra) {
      return (RegexTokenizer)this.defaultCopy(extra);
   }

   public String toString() {
      String var10000 = this.uid();
      return "RegexTokenizer: uid=" + var10000 + ", minTokenLength=" + this.$(this.minTokenLength()) + ", gaps=" + this.$(this.gaps()) + ", pattern=" + this.$(this.pattern()) + ", toLowercase=" + this.$(this.toLowercase());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$createTransformFunc$3(final int localMinTokenLength$1, final String x$2) {
      return x$2.length() >= localMinTokenLength$1;
   }

   public RegexTokenizer(final String uid) {
      this.uid = uid;
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(RegexTokenizer.class.getClassLoader());

      final class $typecreator1$2 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$);
         }

         public $typecreator1$2() {
         }
      }

      TypeTags.TypeTag var10001 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2());
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(RegexTokenizer.class.getClassLoader());

      final class $typecreator2$2 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "Seq"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
         }

         public $typecreator2$2() {
         }
      }

      super(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$2()));
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      this.minTokenLength = new IntParam(this, "minTokenLength", "minimum token length (>= 0)", ParamValidators$.MODULE$.gtEq((double)0.0F));
      this.gaps = new BooleanParam(this, "gaps", "Set regex to match gaps or tokens");
      this.pattern = new Param(this, "pattern", "regex pattern used for tokenizing", scala.reflect.ClassTag..MODULE$.apply(String.class));
      this.toLowercase = new BooleanParam(this, "toLowercase", "whether to convert all characters to lowercase before tokenizing.");
      this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{this.minTokenLength().$minus$greater(BoxesRunTime.boxToInteger(1)), this.gaps().$minus$greater(BoxesRunTime.boxToBoolean(true)), this.pattern().$minus$greater("\\s+"), this.toLowercase().$minus$greater(BoxesRunTime.boxToBoolean(true))}));
   }

   public RegexTokenizer() {
      this(Identifiable$.MODULE$.randomUID("regexTok"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
