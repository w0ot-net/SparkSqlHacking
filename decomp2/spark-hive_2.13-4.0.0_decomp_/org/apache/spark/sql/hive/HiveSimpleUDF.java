package org.apache.spark.sql.hive;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Type;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.catalyst.expressions.UserDefinedExpression;
import org.apache.spark.sql.catalyst.expressions.codegen.CodegenContext;
import org.apache.spark.sql.catalyst.expressions.codegen.ExprCode;
import org.apache.spark.sql.types.DataType;
import scala.Function1;
import scala.Function3;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.StringOps.;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tUb!\u0002\u0014(\u0001\u001e\n\u0004\u0002\u0003+\u0001\u0005+\u0007I\u0011A+\t\u0011y\u0003!\u0011#Q\u0001\nYC\u0001b\u0018\u0001\u0003\u0016\u0004%\t\u0001\u0019\u0005\te\u0002\u0011\t\u0012)A\u0005C\"A1\u000f\u0001BK\u0002\u0013\u0005A\u000f\u0003\u0005y\u0001\tE\t\u0015!\u0003v\u0011\u0015I\b\u0001\"\u0001{\u0011%y\b\u0001#b\u0001\n\u0013\t\t\u0001\u0003\u0006\u0002\u0012\u0001A)\u0019!C!\u0003'Aq!a\u0007\u0001\t\u0003\n\u0019\u0002C\u0004\u0002\u001e\u0001!\t%a\u0005\t\u000f\u0005}\u0001\u0001\"\u0011\u0002\u0014!Q\u0011\u0011\u0005\u0001\t\u0006\u0004%\t%a\t\t\u000f\u0005E\u0002\u0001\"\u0011\u00024!9\u0011q\t\u0001\u0005B\u0005%\u0003BBA&\u0001\u0011\u0005S\u000bC\u0003+\u0001\u0011\u0005S\u000bC\u0004\u0002N\u0001!\t&a\u0014\t\u000f\u0005m\u0003\u0001\"\u0005\u0002^!I\u0011\u0011\u0010\u0001\u0002\u0002\u0013\u0005\u00111\u0010\u0005\n\u0003\u0007\u0003\u0011\u0013!C\u0001\u0003\u000bC\u0011\"a'\u0001#\u0003%\t!!(\t\u0013\u0005\u0005\u0006!%A\u0005\u0002\u0005\r\u0006\"CAT\u0001\u0005\u0005I\u0011IAU\u0011%\tI\fAA\u0001\n\u0003\tY\fC\u0005\u0002D\u0002\t\t\u0011\"\u0001\u0002F\"I\u00111\u001a\u0001\u0002\u0002\u0013\u0005\u0013Q\u001a\u0005\n\u00037\u0004\u0011\u0011!C\u0001\u0003;D\u0011\"!9\u0001\u0003\u0003%\t%a9\t\u0013\u0005\u001d\b!!A\u0005B\u0005%xACAwO\u0005\u0005\t\u0012A\u0014\u0002p\u001aIaeJA\u0001\u0012\u00039\u0013\u0011\u001f\u0005\u0007s\u0002\"\tA!\u0003\t\u0013\u0005\u001d\u0003%!A\u0005F\t-\u0001\"\u0003B\u0007A\u0005\u0005I\u0011\u0011B\b\u0011%\u00119\u0002IA\u0001\n\u0003\u0013I\u0002C\u0005\u0003,\u0001\n\t\u0011\"\u0003\u0003.\ti\u0001*\u001b<f'&l\u0007\u000f\\3V\t\u001aS!\u0001K\u0015\u0002\t!Lg/\u001a\u0006\u0003U-\n1a]9m\u0015\taS&A\u0003ta\u0006\u00148N\u0003\u0002/_\u00051\u0011\r]1dQ\u0016T\u0011\u0001M\u0001\u0004_J<7C\u0002\u00013uy\nu\t\u0005\u00024q5\tAG\u0003\u00026m\u0005YQ\r\u001f9sKN\u001c\u0018n\u001c8t\u0015\t9\u0014&\u0001\u0005dCR\fG._:u\u0013\tIDG\u0001\u0006FqB\u0014Xm]:j_:\u0004\"a\u000f\u001f\u000e\u0003\u001dJ!!P\u0014\u0003\u001d!Kg/Z%ogB,7\r^8sgB\u00111gP\u0005\u0003\u0001R\u0012Q#V:fe\u0012+g-\u001b8fI\u0016C\bO]3tg&|g\u000e\u0005\u0002C\u000b6\t1IC\u0001E\u0003\u0015\u00198-\u00197b\u0013\t15IA\u0004Qe>$Wo\u0019;\u0011\u0005!\u000bfBA%P\u001d\tQe*D\u0001L\u0015\taU*\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005!\u0015B\u0001)D\u0003\u001d\u0001\u0018mY6bO\u0016L!AU*\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005A\u001b\u0015\u0001\u00028b[\u0016,\u0012A\u0016\t\u0003/ns!\u0001W-\u0011\u0005)\u001b\u0015B\u0001.D\u0003\u0019\u0001&/\u001a3fM&\u0011A,\u0018\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005i\u001b\u0015!\u00028b[\u0016\u0004\u0013a\u00034v]\u000e<&/\u00199qKJ,\u0012!\u0019\t\u0003E>t!aY7\u000f\u0005\u0011dgBA3l\u001d\t1'N\u0004\u0002hS:\u0011!\n[\u0005\u0002a%\u0011afL\u0005\u0003Y5J!AK\u0016\n\u0005!J\u0013B\u00018(\u0003!A\u0015N^3TQ&l\u0017B\u00019r\u0005MA\u0015N^3Gk:\u001cG/[8o/J\f\u0007\u000f]3s\u0015\tqw%\u0001\u0007gk:\u001cwK]1qa\u0016\u0014\b%\u0001\u0005dQ&dGM]3o+\u0005)\bc\u0001%we%\u0011qo\u0015\u0002\u0004'\u0016\f\u0018!C2iS2$'/\u001a8!\u0003\u0019a\u0014N\\5u}Q!1\u0010`?\u007f!\tY\u0004\u0001C\u0003U\u000f\u0001\u0007a\u000bC\u0003`\u000f\u0001\u0007\u0011\rC\u0003t\u000f\u0001\u0007Q/A\u0005fm\u0006dW/\u0019;peV\u0011\u00111\u0001\t\u0004w\u0005\u0015\u0011bAA\u0004O\t1\u0002*\u001b<f'&l\u0007\u000f\\3V\t\u001a+e/\u00197vCR|'\u000fK\u0002\t\u0003\u0017\u00012AQA\u0007\u0013\r\tya\u0011\u0002\niJ\fgn]5f]R\fQ\u0002Z3uKJl\u0017N\\5ti&\u001cWCAA\u000b!\r\u0011\u0015qC\u0005\u0004\u00033\u0019%a\u0002\"p_2,\u0017M\\\u0001\tgR\fG/\u001a4vY\u0006Aa.\u001e7mC\ndW-\u0001\u0005g_2$\u0017M\u00197f\u0003!!\u0017\r^1UsB,WCAA\u0013!\u0011\t9#!\f\u000e\u0005\u0005%\"bAA\u0016S\u0005)A/\u001f9fg&!\u0011qFA\u0015\u0005!!\u0015\r^1UsB,\u0017\u0001B3wC2$B!!\u000e\u0002<A\u0019!)a\u000e\n\u0007\u0005e2IA\u0002B]fD\u0011\"!\u0010\u000f!\u0003\u0005\r!a\u0010\u0002\u000b%t\u0007/\u001e;\u0011\t\u0005\u0005\u00131I\u0007\u0002m%\u0019\u0011Q\t\u001c\u0003\u0017%sG/\u001a:oC2\u0014vn^\u0001\ti>\u001cFO]5oOR\ta+\u0001\u0006qe\u0016$H/\u001f(b[\u0016\fqc^5uQ:+wo\u00115jY\u0012\u0014XM\\%oi\u0016\u0014h.\u00197\u0015\u0007I\n\t\u0006C\u0004\u0002TI\u0001\r!!\u0016\u0002\u00179,wo\u00115jY\u0012\u0014XM\u001c\t\u0005\u0011\u0006]#'C\u0002\u0002ZM\u0013!\"\u00138eKb,GmU3r\u0003%!wnR3o\u0007>$W\r\u0006\u0004\u0002`\u0005-\u0014Q\u000f\t\u0005\u0003C\n9'\u0004\u0002\u0002d)\u0019\u0011Q\r\u001b\u0002\u000f\r|G-Z4f]&!\u0011\u0011NA2\u0005!)\u0005\u0010\u001d:D_\u0012,\u0007bBA7'\u0001\u0007\u0011qN\u0001\u0004GRD\b\u0003BA1\u0003cJA!a\u001d\u0002d\tq1i\u001c3fO\u0016t7i\u001c8uKb$\bbBA<'\u0001\u0007\u0011qL\u0001\u0003KZ\fAaY8qsR910! \u0002\u0000\u0005\u0005\u0005b\u0002+\u0015!\u0003\u0005\rA\u0016\u0005\b?R\u0001\n\u00111\u0001b\u0011\u001d\u0019H\u0003%AA\u0002U\fabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002\b*\u001aa+!#,\u0005\u0005-\u0005\u0003BAG\u0003/k!!a$\u000b\t\u0005E\u00151S\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!&D\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u00033\u000byIA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0006\u0002\u0002 *\u001a\u0011-!#\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\u0011\u0011Q\u0015\u0016\u0004k\u0006%\u0015!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002,B!\u0011QVA\\\u001b\t\tyK\u0003\u0003\u00022\u0006M\u0016\u0001\u00027b]\u001eT!!!.\u0002\t)\fg/Y\u0005\u00049\u0006=\u0016\u0001\u00049s_\u0012,8\r^!sSRLXCAA_!\r\u0011\u0015qX\u0005\u0004\u0003\u0003\u001c%aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA\u001b\u0003\u000fD\u0011\"!3\u001b\u0003\u0003\u0005\r!!0\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\ty\r\u0005\u0004\u0002R\u0006]\u0017QG\u0007\u0003\u0003'T1!!6D\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u00033\f\u0019N\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u000b\u0003?D\u0011\"!3\u001d\u0003\u0003\u0005\r!!\u000e\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003W\u000b)\u000fC\u0005\u0002Jv\t\t\u00111\u0001\u0002>\u00061Q-];bYN$B!!\u0006\u0002l\"I\u0011\u0011\u001a\u0010\u0002\u0002\u0003\u0007\u0011QG\u0001\u000e\u0011&4XmU5na2,W\u000b\u0012$\u0011\u0005m\u00023#\u0002\u0011\u0002t\u0006}\b\u0003CA{\u0003w4\u0016-^>\u000e\u0005\u0005](bAA}\u0007\u00069!/\u001e8uS6,\u0017\u0002BA\u007f\u0003o\u0014\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c84!\u0011\u0011\tAa\u0002\u000e\u0005\t\r!\u0002\u0002B\u0003\u0003g\u000b!![8\n\u0007I\u0013\u0019\u0001\u0006\u0002\u0002pR\u0011\u00111V\u0001\u0006CB\u0004H.\u001f\u000b\bw\nE!1\u0003B\u000b\u0011\u0015!6\u00051\u0001W\u0011\u0015y6\u00051\u0001b\u0011\u0015\u00198\u00051\u0001v\u0003\u001d)h.\u00199qYf$BAa\u0007\u0003(A)!I!\b\u0003\"%\u0019!qD\"\u0003\r=\u0003H/[8o!\u0019\u0011%1\u0005,bk&\u0019!QE\"\u0003\rQ+\b\u000f\\34\u0011!\u0011I\u0003JA\u0001\u0002\u0004Y\u0018a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011!q\u0006\t\u0005\u0003[\u0013\t$\u0003\u0003\u00034\u0005=&AB(cU\u0016\u001cG\u000f"
)
public class HiveSimpleUDF extends Expression implements HiveInspectors, UserDefinedExpression, Serializable {
   private transient HiveSimpleUDFEvaluator evaluator;
   private boolean deterministic;
   private DataType dataType;
   private final String name;
   private final HiveShim.HiveFunctionWrapper funcWrapper;
   private final Seq children;
   private volatile byte bitmap$0;
   private transient volatile boolean bitmap$trans$0;

   public static Option unapply(final HiveSimpleUDF x$0) {
      return HiveSimpleUDF$.MODULE$.unapply(x$0);
   }

   public static Function1 tupled() {
      return HiveSimpleUDF$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return HiveSimpleUDF$.MODULE$.curried();
   }

   public DataType javaTypeToDataType(final Type clz) {
      return HiveInspectors.javaTypeToDataType$(this, clz);
   }

   public Function1 wrapperFor(final ObjectInspector oi, final DataType dataType) {
      return HiveInspectors.wrapperFor$(this, oi, dataType);
   }

   public Function1 unwrapperFor(final ObjectInspector objectInspector) {
      return HiveInspectors.unwrapperFor$(this, (ObjectInspector)objectInspector);
   }

   public Function3 unwrapperFor(final StructField field) {
      return HiveInspectors.unwrapperFor$(this, (StructField)field);
   }

   public Object wrap(final Object a, final ObjectInspector oi, final DataType dataType) {
      return HiveInspectors.wrap$(this, (Object)a, (ObjectInspector)oi, (DataType)dataType);
   }

   public Object[] wrap(final InternalRow row, final Function1[] wrappers, final Object[] cache, final DataType[] dataTypes) {
      return HiveInspectors.wrap$(this, row, wrappers, cache, dataTypes);
   }

   public Object[] wrap(final Seq row, final Function1[] wrappers, final Object[] cache) {
      return HiveInspectors.wrap$(this, (Seq)row, (Function1[])wrappers, (Object[])cache);
   }

   public ObjectInspector toInspector(final DataType dataType) {
      return HiveInspectors.toInspector$(this, (DataType)dataType);
   }

   public ObjectInspector toInspector(final Expression expr) {
      return HiveInspectors.toInspector$(this, (Expression)expr);
   }

   public DataType inspectorToDataType(final ObjectInspector inspector) {
      return HiveInspectors.inspectorToDataType$(this, inspector);
   }

   public HiveInspectors.typeInfoConversions typeInfoConversions(final DataType dt) {
      return HiveInspectors.typeInfoConversions$(this, dt);
   }

   public String name() {
      return this.name;
   }

   public HiveShim.HiveFunctionWrapper funcWrapper() {
      return this.funcWrapper;
   }

   public Seq children() {
      return this.children;
   }

   private HiveSimpleUDFEvaluator evaluator$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            this.evaluator = new HiveSimpleUDFEvaluator(this.funcWrapper(), this.children());
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.evaluator;
   }

   private HiveSimpleUDFEvaluator evaluator() {
      return !this.bitmap$trans$0 ? this.evaluator$lzycompute() : this.evaluator;
   }

   private boolean deterministic$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.deterministic = this.evaluator().isUDFDeterministic() && this.children().forall((x$1) -> BoxesRunTime.boxToBoolean($anonfun$deterministic$1(x$1)));
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.deterministic;
   }

   public boolean deterministic() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.deterministic$lzycompute() : this.deterministic;
   }

   public boolean stateful() {
      return true;
   }

   public boolean nullable() {
      return true;
   }

   public boolean foldable() {
      return this.evaluator().isUDFDeterministic() && this.children().forall((x$2) -> BoxesRunTime.boxToBoolean($anonfun$foldable$1(x$2)));
   }

   private DataType dataType$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.dataType = this.javaTypeToDataType(this.evaluator().method().getGenericReturnType());
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.dataType;
   }

   public DataType dataType() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.dataType$lzycompute() : this.dataType;
   }

   public Object eval(final InternalRow input) {
      ((IterableOnceOps)this.children().zipWithIndex()).foreach((x0$1) -> {
         $anonfun$eval$1(this, input, x0$1);
         return BoxedUnit.UNIT;
      });
      return this.evaluator().evaluate();
   }

   public String toString() {
      String var10000 = this.nodeName();
      return var10000 + "#" + this.funcWrapper().functionClassName() + "(" + this.children().mkString(",") + ")";
   }

   public String prettyName() {
      return this.name();
   }

   public String sql() {
      String var10000 = this.name();
      return var10000 + "(" + ((IterableOnceOps)this.children().map((x$3) -> x$3.sql())).mkString(", ") + ")";
   }

   public Expression withNewChildrenInternal(final IndexedSeq newChildren) {
      String x$2 = this.copy$default$1();
      HiveShim.HiveFunctionWrapper x$3 = this.copy$default$2();
      return this.copy(x$2, x$3, newChildren);
   }

   public ExprCode doGenCode(final CodegenContext ctx, final ExprCode ev) {
      String refEvaluator = ctx.addReferenceObj("evaluator", this.evaluator(), ctx.addReferenceObj$default$3());
      Seq evals = (Seq)this.children().map((x$4) -> x$4.genCode(ctx));
      Seq setValues = (Seq)((IterableOps)evals.zipWithIndex()).map((x0$1) -> {
         if (x0$1 != null) {
            ExprCode eval = (ExprCode)x0$1._1();
            int i = x0$1._2$mcI$sp();
            return .MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("\n           |if (" + eval.isNull() + ") {\n           |  " + refEvaluator + ".setArg(" + i + ", null);\n           |} else {\n           |  " + refEvaluator + ".setArg(" + i + ", " + eval.value() + ");\n           |}\n           |"));
         } else {
            throw new MatchError(x0$1);
         }
      });
      String resultType = org.apache.spark.sql.catalyst.expressions.codegen.CodeGenerator..MODULE$.boxedType(this.dataType());
      String resultTerm = ctx.freshName("result");
      return ev.copy(org.apache.spark.sql.catalyst.expressions.codegen.Block.BlockHelper..MODULE$.code$extension(org.apache.spark.sql.catalyst.expressions.codegen.Block..MODULE$.BlockHelper(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"\n         |", "\n         |", "\n         |", " ", " = (", ") ", ".evaluate();\n         |boolean ", " = ", " == null;\n         |", " ", " = ", ";\n         |if (!", ") {\n         |  ", " = ", ";\n         |}\n         |"})))), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{((IterableOnceOps)evals.map((x$5) -> x$5.code())).mkString("\n"), setValues.mkString("\n"), resultType, resultTerm, resultType, refEvaluator, ev.isNull(), resultTerm, org.apache.spark.sql.catalyst.expressions.codegen.CodeGenerator..MODULE$.javaType(this.dataType()), ev.value(), org.apache.spark.sql.catalyst.expressions.codegen.CodeGenerator..MODULE$.defaultValue(this.dataType(), org.apache.spark.sql.catalyst.expressions.codegen.CodeGenerator..MODULE$.defaultValue$default$2()), ev.isNull(), ev.value(), resultTerm})).stripMargin(), ev.copy$default$2(), ev.copy$default$3());
   }

   public HiveSimpleUDF copy(final String name, final HiveShim.HiveFunctionWrapper funcWrapper, final Seq children) {
      return new HiveSimpleUDF(name, funcWrapper, children);
   }

   public String copy$default$1() {
      return this.name();
   }

   public HiveShim.HiveFunctionWrapper copy$default$2() {
      return this.funcWrapper();
   }

   public Seq copy$default$3() {
      return this.children();
   }

   public String productPrefix() {
      return "HiveSimpleUDF";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.name();
         }
         case 1 -> {
            return this.funcWrapper();
         }
         case 2 -> {
            return this.children();
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
      return x$1 instanceof HiveSimpleUDF;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "name";
         }
         case 1 -> {
            return "funcWrapper";
         }
         case 2 -> {
            return "children";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label63: {
            if (x$1 instanceof HiveSimpleUDF) {
               label56: {
                  HiveSimpleUDF var4 = (HiveSimpleUDF)x$1;
                  String var10000 = this.name();
                  String var5 = var4.name();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  HiveShim.HiveFunctionWrapper var8 = this.funcWrapper();
                  HiveShim.HiveFunctionWrapper var6 = var4.funcWrapper();
                  if (var8 == null) {
                     if (var6 != null) {
                        break label56;
                     }
                  } else if (!var8.equals(var6)) {
                     break label56;
                  }

                  Seq var9 = this.children();
                  Seq var7 = var4.children();
                  if (var9 == null) {
                     if (var7 != null) {
                        break label56;
                     }
                  } else if (!var9.equals(var7)) {
                     break label56;
                  }

                  if (var4.canEqual(this)) {
                     break label63;
                  }
               }
            }

            var10 = false;
            return var10;
         }
      }

      var10 = true;
      return var10;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$deterministic$1(final Expression x$1) {
      return x$1.deterministic();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$foldable$1(final Expression x$2) {
      return x$2.foldable();
   }

   // $FF: synthetic method
   public static final void $anonfun$eval$1(final HiveSimpleUDF $this, final InternalRow input$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Expression child = (Expression)x0$1._1();
         int idx = x0$1._2$mcI$sp();
         $this.evaluator().setArg(idx, child.eval(input$1));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   public HiveSimpleUDF(final String name, final HiveShim.HiveFunctionWrapper funcWrapper, final Seq children) {
      this.name = name;
      this.funcWrapper = funcWrapper;
      this.children = children;
      HiveInspectors.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
