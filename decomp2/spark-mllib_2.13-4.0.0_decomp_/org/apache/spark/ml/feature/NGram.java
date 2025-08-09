package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.UnaryTransformer;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.DataType;
import scala.Function1;
import scala.Predef.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ec\u0001\u0002\n\u0014\u0001yA\u0001\"\u0011\u0001\u0003\u0006\u0004%\tE\u0011\u0005\t\u0019\u0002\u0011\t\u0011)A\u0005c!)a\n\u0001C\u0001\u001f\")a\n\u0001C\u0001'\"9Q\u000b\u0001b\u0001\n\u00031\u0006B\u00020\u0001A\u0003%q\u000bC\u0003a\u0001\u0011\u0005\u0011\rC\u0003k\u0001\u0011\u00051\u000eC\u0003n\u0001\u0011Ec\u000eC\u0003s\u0001\u0011E3\u000fC\u0004\u0002\u0004\u0001!\t&!\u0002\t\u000f\u0005\u001d\u0001\u0001\"\u0011\u0002\n\u001d9\u00111C\n\t\u0002\u0005UaA\u0002\n\u0014\u0011\u0003\t9\u0002\u0003\u0004O\u001d\u0011\u0005\u0011Q\u0007\u0005\b\u0003oqA\u0011IA\u001d\u0011%\t)EDA\u0001\n\u0013\t9EA\u0003O\u000fJ\fWN\u0003\u0002\u0015+\u00059a-Z1ukJ,'B\u0001\f\u0018\u0003\tiGN\u0003\u0002\u00193\u0005)1\u000f]1sW*\u0011!dG\u0001\u0007CB\f7\r[3\u000b\u0003q\t1a\u001c:h\u0007\u0001\u00192\u0001A\u0010<!\u0015\u0001\u0013eI\u0012:\u001b\u0005)\u0012B\u0001\u0012\u0016\u0005A)f.\u0019:z)J\fgn\u001d4pe6,'\u000fE\u0002%]Er!!J\u0016\u000f\u0005\u0019JS\"A\u0014\u000b\u0005!j\u0012A\u0002\u001fs_>$h(C\u0001+\u0003\u0015\u00198-\u00197b\u0013\taS&A\u0004qC\u000e\\\u0017mZ3\u000b\u0003)J!a\f\u0019\u0003\u0007M+\u0017O\u0003\u0002-[A\u0011!G\u000e\b\u0003gQ\u0002\"AJ\u0017\n\u0005Uj\u0013A\u0002)sK\u0012,g-\u0003\u00028q\t11\u000b\u001e:j]\u001eT!!N\u0017\u0011\u0005i\u0002Q\"A\n\u0011\u0005qzT\"A\u001f\u000b\u0005y*\u0012\u0001B;uS2L!\u0001Q\u001f\u0003+\u0011+g-Y;miB\u000b'/Y7t/JLG/\u00192mK\u0006\u0019Q/\u001b3\u0016\u0003EB3!\u0001#K!\t)\u0005*D\u0001G\u0015\t9u#\u0001\u0006b]:|G/\u0019;j_:L!!\u0013$\u0003\u000bMKgnY3\"\u0003-\u000bQ!\r\u00186]A\nA!^5eA!\u001a!\u0001\u0012&\u0002\rqJg.\u001b;?)\tI\u0004\u000bC\u0003B\u0007\u0001\u0007\u0011\u0007K\u0002Q\t*C3a\u0001#K)\u0005I\u0004f\u0001\u0003E\u0015\u0006\ta.F\u0001X!\tA6,D\u0001Z\u0015\tQV#A\u0003qCJ\fW.\u0003\u0002]3\nA\u0011J\u001c;QCJ\fW\u000eK\u0002\u0006\t*\u000b!A\u001c\u0011)\u0007\u0019!%*\u0001\u0003tKRtEC\u00012d\u001b\u0005\u0001\u0001\"\u00023\b\u0001\u0004)\u0017!\u0002<bYV,\u0007C\u00014h\u001b\u0005i\u0013B\u00015.\u0005\rIe\u000e\u001e\u0015\u0004\u000f\u0011S\u0015\u0001B4fi:+\u0012!\u001a\u0015\u0004\u0011\u0011S\u0015aE2sK\u0006$X\r\u0016:b]N4wN]7Gk:\u001cW#A8\u0011\t\u0019\u00048eI\u0005\u0003c6\u0012\u0011BR;oGRLwN\\\u0019\u0002#Y\fG.\u001b3bi\u0016Le\u000e];u)f\u0004X\r\u0006\u0002uoB\u0011a-^\u0005\u0003m6\u0012A!\u00168ji\")\u0001P\u0003a\u0001s\u0006I\u0011N\u001c9viRK\b/\u001a\t\u0003u~l\u0011a\u001f\u0006\u0003yv\fQ\u0001^=qKNT!A`\f\u0002\u0007M\fH.C\u0002\u0002\u0002m\u0014\u0001\u0002R1uCRK\b/Z\u0001\u000f_V$\b/\u001e;ECR\fG+\u001f9f+\u0005I\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003EBC\u0001\u0004#\u0002\u000e\u0005\u0012\u0011qB\u0001\u0006g9\u0002d\u0006\r\u0015\u0004\u0001\u0011S\u0015!\u0002(He\u0006l\u0007C\u0001\u001e\u000f'\u001dq\u0011\u0011DA\u0010\u0003K\u00012AZA\u000e\u0013\r\ti\"\f\u0002\u0007\u0003:L(+\u001a4\u0011\tq\n\t#O\u0005\u0004\u0003Gi$!\u0006#fM\u0006,H\u000e\u001e)be\u0006l7OU3bI\u0006\u0014G.\u001a\t\u0005\u0003O\t\t$\u0004\u0002\u0002*)!\u00111FA\u0017\u0003\tIwN\u0003\u0002\u00020\u0005!!.\u0019<b\u0013\u0011\t\u0019$!\u000b\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u0005U\u0011\u0001\u00027pC\u0012$2!OA\u001e\u0011\u0019\ti\u0004\u0005a\u0001c\u0005!\u0001/\u0019;iQ\u0011\u0001B)!\u0011\"\u0005\u0005\r\u0013!B\u0019/m9\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA%!\u0011\tY%!\u0015\u000e\u0005\u00055#\u0002BA(\u0003[\tA\u0001\\1oO&!\u00111KA'\u0005\u0019y%M[3di\"\"a\u0002RA!Q\u0011iA)!\u0011"
)
public class NGram extends UnaryTransformer implements DefaultParamsWritable {
   private final String uid;
   private final IntParam n;

   public static NGram load(final String path) {
      return NGram$.MODULE$.load(path);
   }

   public static MLReader read() {
      return NGram$.MODULE$.read();
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

   public IntParam n() {
      return this.n;
   }

   public NGram setN(final int value) {
      return (NGram)this.set(this.n(), BoxesRunTime.boxToInteger(value));
   }

   public int getN() {
      return BoxesRunTime.unboxToInt(this.$(this.n()));
   }

   public Function1 createTransformFunc() {
      return (x$1) -> {
         Iterator qual$1 = x$1.iterator();
         int x$1 = BoxesRunTime.unboxToInt(this.$(this.n()));
         int x$2 = qual$1.sliding$default$2();
         return qual$1.sliding(x$1, x$2).withPartial(false).map((x$2x) -> x$2x.mkString(" ")).toSeq();
      };
   }

   public void validateInputType(final DataType inputType) {
      .MODULE$.require(org.apache.spark.sql.catalyst.types.DataTypeUtils..MODULE$.sameType(inputType, org.apache.spark.sql.types.ArrayType..MODULE$.apply(org.apache.spark.sql.types.StringType..MODULE$)), () -> {
         String var10000 = org.apache.spark.sql.types.ArrayType..MODULE$.apply(org.apache.spark.sql.types.StringType..MODULE$).catalogString();
         return "Input type must be " + var10000 + " but got " + inputType.catalogString();
      });
   }

   public DataType outputDataType() {
      return new ArrayType(org.apache.spark.sql.types.StringType..MODULE$, false);
   }

   public String toString() {
      String var10000 = this.uid();
      return "NGram: uid=" + var10000 + ", n=" + this.$(this.n());
   }

   public NGram(final String uid) {
      this.uid = uid;
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(NGram.class.getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "Seq"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
         }

         public $typecreator1$1() {
         }
      }

      TypeTags.TypeTag var10001 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1());
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(NGram.class.getClassLoader());

      final class $typecreator2$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "Seq"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
         }

         public $typecreator2$1() {
         }
      }

      super(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      this.n = new IntParam(this, "n", "number elements per n-gram (>=1)", ParamValidators$.MODULE$.gtEq((double)1.0F));
      this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{this.n().$minus$greater(BoxesRunTime.boxToInteger(2))}));
   }

   public NGram() {
      this(Identifiable$.MODULE$.randomUID("ngram"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
