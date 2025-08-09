package org.apache.spark.sql.hive;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Type;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.catalyst.expressions.UserDefinedExpression;
import org.apache.spark.sql.catalyst.expressions.codegen.Block;
import org.apache.spark.sql.catalyst.expressions.codegen.CodegenContext;
import org.apache.spark.sql.catalyst.expressions.codegen.ExprCode;
import org.apache.spark.sql.types.DataType;
import scala.Function1;
import scala.Function3;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.StringOps;
import scala.collection.StringOps.;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tMb!B\u0013'\u0001\u001a\u0002\u0004\u0002C*\u0001\u0005+\u0007I\u0011\u0001+\t\u0011u\u0003!\u0011#Q\u0001\nUC\u0001B\u0018\u0001\u0003\u0016\u0004%\ta\u0018\u0005\tc\u0002\u0011\t\u0012)A\u0005A\"A!\u000f\u0001BK\u0002\u0013\u00051\u000f\u0003\u0005x\u0001\tE\t\u0015!\u0003u\u0011\u0015A\b\u0001\"\u0001z\u0011\u0015q\b\u0001\"\u0011\u0000\u0011\u0019\t9\u0001\u0001C!\u007f\"I\u0011\u0011\u0002\u0001\t\u0006\u0004%\te \u0005\u0007\u0003\u0017\u0001A\u0011I@\t\u0015\u00055\u0001\u0001#b\u0001\n\u0003\ny\u0001\u0003\u0006\u0002\u001e\u0001A)\u0019!C\u0005\u0003?Aq!a\f\u0001\t\u0003\n\t\u0004\u0003\u0004\u0002F\u0001!\t\u0005\u0016\u0005\b\u0003\u000f\u0002A\u0011IA%\u0011\u001d\tY\u0005\u0001C)\u0003\u001bBq!!\u0017\u0001\t#\tY\u0006C\u0005\u0002x\u0001\t\t\u0011\"\u0001\u0002z!I\u0011\u0011\u0011\u0001\u0012\u0002\u0013\u0005\u00111\u0011\u0005\n\u00033\u0003\u0011\u0013!C\u0001\u00037C\u0011\"a(\u0001#\u0003%\t!!)\t\u0013\u0005\u0015\u0006!!A\u0005B\u0005\u001d\u0006\"CA\\\u0001\u0005\u0005I\u0011AA]\u0011%\t\t\rAA\u0001\n\u0003\t\u0019\rC\u0005\u0002J\u0002\t\t\u0011\"\u0011\u0002L\"I\u0011\u0011\u001c\u0001\u0002\u0002\u0013\u0005\u00111\u001c\u0005\n\u0003?\u0004\u0011\u0011!C!\u0003CD\u0011\"!:\u0001\u0003\u0003%\t%a:\b\u0015\u0005-h%!A\t\u0002\u0019\niOB\u0005&M\u0005\u0005\t\u0012\u0001\u0014\u0002p\"1\u0001p\bC\u0001\u0005\u000fA\u0011\"a\u0012 \u0003\u0003%)E!\u0003\t\u0013\t-q$!A\u0005\u0002\n5\u0001\"\u0003B\u000b?\u0005\u0005I\u0011\u0011B\f\u0011%\u0011IcHA\u0001\n\u0013\u0011YC\u0001\bISZ,w)\u001a8fe&\u001cW\u000b\u0012$\u000b\u0005\u001dB\u0013\u0001\u00025jm\u0016T!!\u000b\u0016\u0002\u0007M\fHN\u0003\u0002,Y\u0005)1\u000f]1sW*\u0011QFL\u0001\u0007CB\f7\r[3\u000b\u0003=\n1a\u001c:h'\u0019\u0001\u0011'O\u001fA\rB\u0011!gN\u0007\u0002g)\u0011A'N\u0001\fKb\u0004(/Z:tS>t7O\u0003\u00027Q\u0005A1-\u0019;bYf\u001cH/\u0003\u00029g\tQQ\t\u001f9sKN\u001c\u0018n\u001c8\u0011\u0005iZT\"\u0001\u0014\n\u0005q2#A\u0004%jm\u0016Len\u001d9fGR|'o\u001d\t\u0003eyJ!aP\u001a\u0003+U\u001bXM\u001d#fM&tW\rZ#yaJ,7o]5p]B\u0011\u0011\tR\u0007\u0002\u0005*\t1)A\u0003tG\u0006d\u0017-\u0003\u0002F\u0005\n9\u0001K]8ek\u000e$\bCA$Q\u001d\tAeJ\u0004\u0002J\u001b6\t!J\u0003\u0002L\u0019\u00061AH]8piz\u001a\u0001!C\u0001D\u0013\ty%)A\u0004qC\u000e\\\u0017mZ3\n\u0005E\u0013&\u0001D*fe&\fG.\u001b>bE2,'BA(C\u0003\u0011q\u0017-\\3\u0016\u0003U\u0003\"A\u0016.\u000f\u0005]C\u0006CA%C\u0013\tI&)\u0001\u0004Qe\u0016$WMZ\u0005\u00037r\u0013aa\u0015;sS:<'BA-C\u0003\u0015q\u0017-\\3!\u0003-1WO\\2Xe\u0006\u0004\b/\u001a:\u0016\u0003\u0001\u0004\"!\u00198\u000f\u0005\tdgBA2l\u001d\t!'N\u0004\u0002fS:\u0011a\r\u001b\b\u0003\u0013\u001eL\u0011aL\u0005\u0003[9J!a\u000b\u0017\n\u0005%R\u0013BA\u0014)\u0013\tig%\u0001\u0005ISZ,7\u000b[5n\u0013\ty\u0007OA\nISZ,g)\u001e8di&|gn\u0016:baB,'O\u0003\u0002nM\u0005aa-\u001e8d/J\f\u0007\u000f]3sA\u0005A1\r[5mIJ,g.F\u0001u!\r9U/M\u0005\u0003mJ\u00131aU3r\u0003%\u0019\u0007.\u001b7ee\u0016t\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0005undX\u0010\u0005\u0002;\u0001!)1k\u0002a\u0001+\")al\u0002a\u0001A\")!o\u0002a\u0001i\u0006A1\u000f^1uK\u001a,H.\u0006\u0002\u0002\u0002A\u0019\u0011)a\u0001\n\u0007\u0005\u0015!IA\u0004C_>dW-\u00198\u0002\u00119,H\u000e\\1cY\u0016\fQ\u0002Z3uKJl\u0017N\\5ti&\u001c\u0017\u0001\u00034pY\u0012\f'\r\\3\u0002\u0011\u0011\fG/\u0019+za\u0016,\"!!\u0005\u0011\t\u0005M\u0011\u0011D\u0007\u0003\u0003+Q1!a\u0006)\u0003\u0015!\u0018\u0010]3t\u0013\u0011\tY\"!\u0006\u0003\u0011\u0011\u000bG/\u0019+za\u0016\f\u0011\"\u001a<bYV\fGo\u001c:\u0016\u0005\u0005\u0005\u0002c\u0001\u001e\u0002$%\u0019\u0011Q\u0005\u0014\u0003/!Kg/Z$f]\u0016\u0014\u0018nY+E\r\u00163\u0018\r\\;bi>\u0014\bfA\u0007\u0002*A\u0019\u0011)a\u000b\n\u0007\u00055\"IA\u0005ue\u0006t7/[3oi\u0006!QM^1m)\u0011\t\u0019$!\u000f\u0011\u0007\u0005\u000b)$C\u0002\u00028\t\u00131!\u00118z\u0011%\tYD\u0004I\u0001\u0002\u0004\ti$A\u0003j]B,H\u000f\u0005\u0003\u0002@\u0005\u0005S\"A\u001b\n\u0007\u0005\rSGA\u0006J]R,'O\\1m%><\u0018A\u00039sKR$\u0018PT1nK\u0006AAo\\*ue&tw\rF\u0001V\u0003]9\u0018\u000e\u001e5OK^\u001c\u0005.\u001b7ee\u0016t\u0017J\u001c;fe:\fG\u000eF\u00022\u0003\u001fBq!!\u0015\u0012\u0001\u0004\t\u0019&A\u0006oK^\u001c\u0005.\u001b7ee\u0016t\u0007\u0003B$\u0002VEJ1!a\u0016S\u0005)Ie\u000eZ3yK\u0012\u001cV-]\u0001\nI><UM\\\"pI\u0016$b!!\u0018\u0002j\u0005M\u0004\u0003BA0\u0003Kj!!!\u0019\u000b\u0007\u0005\r4'A\u0004d_\u0012,w-\u001a8\n\t\u0005\u001d\u0014\u0011\r\u0002\t\u000bb\u0004(oQ8eK\"9\u00111\u000e\nA\u0002\u00055\u0014aA2uqB!\u0011qLA8\u0013\u0011\t\t(!\u0019\u0003\u001d\r{G-Z4f]\u000e{g\u000e^3yi\"9\u0011Q\u000f\nA\u0002\u0005u\u0013AA3w\u0003\u0011\u0019w\u000e]=\u0015\u000fi\fY(! \u0002\u0000!91k\u0005I\u0001\u0002\u0004)\u0006b\u00020\u0014!\u0003\u0005\r\u0001\u0019\u0005\beN\u0001\n\u00111\u0001u\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!!\"+\u0007U\u000b9i\u000b\u0002\u0002\nB!\u00111RAK\u001b\t\tiI\u0003\u0003\u0002\u0010\u0006E\u0015!C;oG\",7m[3e\u0015\r\t\u0019JQ\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BAL\u0003\u001b\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\"!!(+\u0007\u0001\f9)\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\u0005\r&f\u0001;\u0002\b\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"!!+\u0011\t\u0005-\u0016QW\u0007\u0003\u0003[SA!a,\u00022\u0006!A.\u00198h\u0015\t\t\u0019,\u0001\u0003kCZ\f\u0017bA.\u0002.\u0006a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u00111\u0018\t\u0004\u0003\u0006u\u0016bAA`\u0005\n\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u00111GAc\u0011%\t9-GA\u0001\u0002\u0004\tY,A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u001b\u0004b!a4\u0002V\u0006MRBAAi\u0015\r\t\u0019NQ\u0001\u000bG>dG.Z2uS>t\u0017\u0002BAl\u0003#\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011\u0011AAo\u0011%\t9mGA\u0001\u0002\u0004\t\u0019$\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BAU\u0003GD\u0011\"a2\u001d\u0003\u0003\u0005\r!a/\u0002\r\u0015\fX/\u00197t)\u0011\t\t!!;\t\u0013\u0005\u001dW$!AA\u0002\u0005M\u0012A\u0004%jm\u0016<UM\\3sS\u000e,FI\u0012\t\u0003u}\u0019RaHAy\u0003{\u0004\u0002\"a=\u0002zV\u0003GO_\u0007\u0003\u0003kT1!a>C\u0003\u001d\u0011XO\u001c;j[\u0016LA!a?\u0002v\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u001a\u0011\t\u0005}(QA\u0007\u0003\u0005\u0003QAAa\u0001\u00022\u0006\u0011\u0011n\\\u0005\u0004#\n\u0005ACAAw)\t\tI+A\u0003baBd\u0017\u0010F\u0004{\u0005\u001f\u0011\tBa\u0005\t\u000bM\u0013\u0003\u0019A+\t\u000by\u0013\u0003\u0019\u00011\t\u000bI\u0014\u0003\u0019\u0001;\u0002\u000fUt\u0017\r\u001d9msR!!\u0011\u0004B\u0013!\u0015\t%1\u0004B\u0010\u0013\r\u0011iB\u0011\u0002\u0007\u001fB$\u0018n\u001c8\u0011\r\u0005\u0013\t#\u00161u\u0013\r\u0011\u0019C\u0011\u0002\u0007)V\u0004H.Z\u001a\t\u0011\t\u001d2%!AA\u0002i\f1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0011i\u0003\u0005\u0003\u0002,\n=\u0012\u0002\u0002B\u0019\u0003[\u0013aa\u00142kK\u000e$\b"
)
public class HiveGenericUDF extends Expression implements HiveInspectors, UserDefinedExpression, Serializable {
   private boolean deterministic;
   private DataType dataType;
   private transient HiveGenericUDFEvaluator evaluator;
   private final String name;
   private final HiveShim.HiveFunctionWrapper funcWrapper;
   private final Seq children;
   private volatile byte bitmap$0;
   private transient volatile boolean bitmap$trans$0;

   public static Option unapply(final HiveGenericUDF x$0) {
      return HiveGenericUDF$.MODULE$.unapply(x$0);
   }

   public static Function1 tupled() {
      return HiveGenericUDF$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return HiveGenericUDF$.MODULE$.curried();
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

   public boolean stateful() {
      return true;
   }

   public boolean nullable() {
      return true;
   }

   private boolean deterministic$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.deterministic = this.evaluator().isUDFDeterministic() && this.children().forall((x$6) -> BoxesRunTime.boxToBoolean($anonfun$deterministic$2(x$6)));
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

   public boolean foldable() {
      return this.evaluator().isUDFDeterministic() && this.evaluator().returnInspector() instanceof ConstantObjectInspector;
   }

   private DataType dataType$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.dataType = this.inspectorToDataType(this.evaluator().returnInspector());
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

   private HiveGenericUDFEvaluator evaluator$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            this.evaluator = new HiveGenericUDFEvaluator(this.funcWrapper(), this.children());
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.evaluator;
   }

   private HiveGenericUDFEvaluator evaluator() {
      return !this.bitmap$trans$0 ? this.evaluator$lzycompute() : this.evaluator;
   }

   public Object eval(final InternalRow input) {
      ((IterableOnceOps)this.children().zipWithIndex()).foreach((x0$1) -> {
         $anonfun$eval$2(this, input, x0$1);
         return BoxedUnit.UNIT;
      });
      return this.evaluator().evaluate();
   }

   public String prettyName() {
      return this.name();
   }

   public String toString() {
      String var10000 = this.nodeName();
      return var10000 + "#" + this.funcWrapper().functionClassName() + "(" + this.children().mkString(",") + ")";
   }

   public Expression withNewChildrenInternal(final IndexedSeq newChildren) {
      String x$2 = this.copy$default$1();
      HiveShim.HiveFunctionWrapper x$3 = this.copy$default$2();
      return this.copy(x$2, x$3, newChildren);
   }

   public ExprCode doGenCode(final CodegenContext ctx, final ExprCode ev) {
      String refEvaluator = ctx.addReferenceObj("evaluator", this.evaluator(), ctx.addReferenceObj$default$3());
      Seq evals = (Seq)this.children().map((x$7) -> x$7.genCode(ctx));
      Seq setValues = (Seq)((IterableOps)evals.zipWithIndex()).map((x0$1) -> {
         if (x0$1 != null) {
            ExprCode eval = (ExprCode)x0$1._1();
            int i = x0$1._2$mcI$sp();
            StringOps var10000 = .MODULE$;
            Predef var10001 = scala.Predef..MODULE$;
            Block var10002 = eval.code();
            return var10000.stripMargin$extension(var10001.augmentString("\n           |try {\n           |  " + var10002 + "\n           |  if (" + eval.isNull() + ") {\n           |    " + refEvaluator + ".setArg(" + i + ", null);\n           |  } else {\n           |    " + refEvaluator + ".setArg(" + i + ", " + eval.value() + ");\n           |  }\n           |} catch (Throwable t) {\n           |  " + refEvaluator + ".setException(" + i + ", t);\n           |}\n           |"));
         } else {
            throw new MatchError(x0$1);
         }
      });
      String resultType = org.apache.spark.sql.catalyst.expressions.codegen.CodeGenerator..MODULE$.boxedType(this.dataType());
      String resultTerm = ctx.freshName("result");
      return ev.copy(org.apache.spark.sql.catalyst.expressions.codegen.Block.BlockHelper..MODULE$.code$extension(org.apache.spark.sql.catalyst.expressions.codegen.Block..MODULE$.BlockHelper(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"\n         |", "\n         |", " ", " = (", ") ", ".evaluate();\n         |boolean ", " = ", " == null;\n         |", " ", " = ", ";\n         |if (!", ") {\n         |  ", " = ", ";\n         |}\n         |"})))), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{setValues.mkString("\n"), resultType, resultTerm, resultType, refEvaluator, ev.isNull(), resultTerm, org.apache.spark.sql.catalyst.expressions.codegen.CodeGenerator..MODULE$.javaType(this.dataType()), ev.value(), org.apache.spark.sql.catalyst.expressions.codegen.CodeGenerator..MODULE$.defaultValue(this.dataType(), org.apache.spark.sql.catalyst.expressions.codegen.CodeGenerator..MODULE$.defaultValue$default$2()), ev.isNull(), ev.value(), resultTerm})).stripMargin(), ev.copy$default$2(), ev.copy$default$3());
   }

   public HiveGenericUDF copy(final String name, final HiveShim.HiveFunctionWrapper funcWrapper, final Seq children) {
      return new HiveGenericUDF(name, funcWrapper, children);
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
      return "HiveGenericUDF";
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
      return x$1 instanceof HiveGenericUDF;
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
            if (x$1 instanceof HiveGenericUDF) {
               label56: {
                  HiveGenericUDF var4 = (HiveGenericUDF)x$1;
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
   public static final boolean $anonfun$deterministic$2(final Expression x$6) {
      return x$6.deterministic();
   }

   // $FF: synthetic method
   public static final void $anonfun$eval$2(final HiveGenericUDF $this, final InternalRow input$2, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Expression child = (Expression)x0$1._1();
         int idx = x0$1._2$mcI$sp();

         try {
            $this.evaluator().setArg(idx, child.eval(input$2));
            BoxedUnit var9 = BoxedUnit.UNIT;
         } catch (Throwable var8) {
            $this.evaluator().setException(idx, var8);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

      } else {
         throw new MatchError(x0$1);
      }
   }

   public HiveGenericUDF(final String name, final HiveShim.HiveFunctionWrapper funcWrapper, final Seq children) {
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
