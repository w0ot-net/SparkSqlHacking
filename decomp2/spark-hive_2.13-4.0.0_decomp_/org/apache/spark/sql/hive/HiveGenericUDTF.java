package org.apache.spark.sql.hive;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Type;
import java.util.List;
import org.apache.hadoop.hive.ql.udf.generic.Collector;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StandardStructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.catalyst.expressions.Generator;
import org.apache.spark.sql.catalyst.expressions.InterpretedProjection;
import org.apache.spark.sql.catalyst.expressions.UserDefinedExpression;
import org.apache.spark.sql.catalyst.expressions.codegen.CodegenContext;
import org.apache.spark.sql.catalyst.expressions.codegen.CodegenFallback;
import org.apache.spark.sql.catalyst.expressions.codegen.ExprCode;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.Function3;
import scala.Option;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tMh!\u0002\u00192\u0001FZ\u0004\u0002C4\u0001\u0005+\u0007I\u0011\u00015\t\u0011E\u0004!\u0011#Q\u0001\n%D\u0001B\u001d\u0001\u0003\u0016\u0004%\ta\u001d\u0005\n\u0003\u0017\u0001!\u0011#Q\u0001\nQD!\"!\u0004\u0001\u0005+\u0007I\u0011AA\b\u0011)\t9\u0002\u0001B\tB\u0003%\u0011\u0011\u0003\u0005\b\u00033\u0001A\u0011AA\u000e\u0011)\t)\u0003\u0001EC\u0002\u0013E\u0011q\u0005\u0005\u000b\u0003\u0017\u0002\u0001R1A\u0005\u0012\u00055\u0003BCA1\u0001!\u0015\r\u0011\"\u0005\u0002d!Q\u0011Q\u000e\u0001\t\u0006\u0004%\t\"a\u001c\t\u0015\u0005}\u0004\u0001#b\u0001\n#\t\t\t\u0003\u0006\u0002`\u0002A)\u0019!C!\u0003CD!\"a<\u0001\u0011\u000b\u0007I\u0011BAy\u0011)\ti\u0010\u0001EC\u0002\u0013%\u0011q \u0005\u000b\u0005#\u0001\u0001R1A\u0005\n\tM\u0001B\u0003B\f\u0001!\u0015\r\u0011\"\u0003\u0003\u001a!9!1\u0005\u0001\u0005B\t\u0015bABAD\u0001!\tI\tC\u0004\u0002\u001aM!\t!!)\t\u0013\u0005\r6\u00031A\u0005\u0002\u0005\u0015\u0006\"CA`'\u0001\u0007I\u0011AAa\u0011!\tim\u0005Q!\n\u0005\u001d\u0006bBAh'\u0011\u0005\u0013\u0011\u001b\u0005\b\u0003/\u001cB\u0011AAm\u0011\u001d\u0011y\u0003\u0001C!\u0005cAqAa\r\u0001\t\u0003\u0012)\u0004\u0003\u0004\u00038\u0001!\t\u0005\u001b\u0005\b\u0005s\u0001A\u0011\u000bB\u001e\u0011%\u00119\u0005AA\u0001\n\u0003\u0011I\u0005C\u0005\u0003R\u0001\t\n\u0011\"\u0001\u0003T!I!\u0011\u000e\u0001\u0012\u0002\u0013\u0005!1\u000e\u0005\n\u0005_\u0002\u0011\u0013!C\u0001\u0005cB\u0011B!\u001e\u0001\u0003\u0003%\tEa\u001e\t\u0013\tu\u0004!!A\u0005\u0002\t}\u0004\"\u0003BD\u0001\u0005\u0005I\u0011\u0001BE\u0011%\u0011i\tAA\u0001\n\u0003\u0012y\tC\u0005\u0003\u001a\u0002\t\t\u0011\"\u0001\u0003\u001c\"I!Q\u0015\u0001\u0002\u0002\u0013\u0005#q\u0015\u0005\n\u0005W\u0003\u0011\u0011!C!\u0005[;!B!-2\u0003\u0003E\t!\rBZ\r%\u0001\u0014'!A\t\u0002E\u0012)\fC\u0004\u0002\u001a)\"\tA!4\t\u0013\tM\"&!A\u0005F\t=\u0007\"\u0003BiU\u0005\u0005I\u0011\u0011Bj\u0011%\u0011YNKA\u0001\n\u0003\u0013i\u000eC\u0005\u0003p*\n\t\u0011\"\u0003\u0003r\ny\u0001*\u001b<f\u000f\u0016tWM]5d+\u0012#fI\u0003\u00023g\u0005!\u0001.\u001b<f\u0015\t!T'A\u0002tc2T!AN\u001c\u0002\u000bM\u0004\u0018M]6\u000b\u0005aJ\u0014AB1qC\u000eDWMC\u0001;\u0003\ry'oZ\n\t\u0001q\"uiS)U5B\u0011QHQ\u0007\u0002})\u0011q\bQ\u0001\fKb\u0004(/Z:tS>t7O\u0003\u0002Bg\u0005A1-\u0019;bYf\u001cH/\u0003\u0002D}\tQQ\t\u001f9sKN\u001c\u0018n\u001c8\u0011\u0005u*\u0015B\u0001$?\u0005%9UM\\3sCR|'\u000f\u0005\u0002I\u00136\t\u0011'\u0003\u0002Kc\tq\u0001*\u001b<f\u0013:\u001c\b/Z2u_J\u001c\bC\u0001'P\u001b\u0005i%B\u0001(?\u0003\u001d\u0019w\u000eZ3hK:L!\u0001U'\u0003\u001f\r{G-Z4f]\u001a\u000bG\u000e\u001c2bG.\u0004\"!\u0010*\n\u0005Ms$!F+tKJ$UMZ5oK\u0012,\u0005\u0010\u001d:fgNLwN\u001c\t\u0003+bk\u0011A\u0016\u0006\u0002/\u0006)1oY1mC&\u0011\u0011L\u0016\u0002\b!J|G-^2u!\tYFM\u0004\u0002]E:\u0011Q,Y\u0007\u0002=*\u0011q\fY\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tq+\u0003\u0002d-\u00069\u0001/Y2lC\u001e,\u0017BA3g\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0019g+\u0001\u0003oC6,W#A5\u0011\u0005)tgBA6m!\tif+\u0003\u0002n-\u00061\u0001K]3eK\u001aL!a\u001c9\u0003\rM#(/\u001b8h\u0015\tig+A\u0003oC6,\u0007%A\u0006gk:\u001cwK]1qa\u0016\u0014X#\u0001;\u0011\u0007U\f)AD\u0002w\u0003\u0003q!a^@\u000f\u0005athBA=~\u001d\tQHP\u0004\u0002^w&\t!(\u0003\u00029s%\u0011agN\u0005\u0003iUJ!AM\u001a\n\u0007\u0005\r\u0011'\u0001\u0005ISZ,7\u000b[5n\u0013\u0011\t9!!\u0003\u0003'!Kg/\u001a$v]\u000e$\u0018n\u001c8Xe\u0006\u0004\b/\u001a:\u000b\u0007\u0005\r\u0011'\u0001\u0007gk:\u001cwK]1qa\u0016\u0014\b%\u0001\u0005dQ&dGM]3o+\t\t\t\u0002\u0005\u0003\\\u0003'a\u0014bAA\u000bM\n\u00191+Z9\u0002\u0013\rD\u0017\u000e\u001c3sK:\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0005\u0002\u001e\u0005}\u0011\u0011EA\u0012!\tA\u0005\u0001C\u0003h\u000f\u0001\u0007\u0011\u000eC\u0003s\u000f\u0001\u0007A\u000fC\u0004\u0002\u000e\u001d\u0001\r!!\u0005\u0002\u0011\u0019,hn\u0019;j_:,\"!!\u000b\u0011\t\u0005-\u0012qH\u0007\u0003\u0003[QA!a\f\u00022\u00059q-\u001a8fe&\u001c'\u0002BA\u001a\u0003k\t1!\u001e3g\u0015\u0011\t9$!\u000f\u0002\u0005Ed'b\u0001\u001a\u0002<)\u0019\u0011QH\u001c\u0002\r!\fGm\\8q\u0013\u0011\t\t%!\f\u0003\u0017\u001d+g.\u001a:jGV#EK\u0012\u0015\u0004\u0011\u0005\u0015\u0003cA+\u0002H%\u0019\u0011\u0011\n,\u0003\u0013Q\u0014\u0018M\\:jK:$\u0018AD5oaV$\u0018J\\:qK\u000e$xN]\u000b\u0003\u0003\u001f\u0002B!!\u0015\u0002\\5\u0011\u00111\u000b\u0006\u0005\u0003+\n9&A\bpE*,7\r^5ogB,7\r^8s\u0015\u0011\tI&!\u000f\u0002\rM,'\u000fZ33\u0013\u0011\ti&a\u0015\u0003;M#\u0018M\u001c3be\u0012\u001cFO];di>\u0013'.Z2u\u0013:\u001c\b/Z2u_JD3!CA#\u0003=yW\u000f\u001e9vi&s7\u000f]3di>\u0014XCAA3!\u0011\t\t&a\u001a\n\t\u0005%\u00141\u000b\u0002\u0016'R\u0014Xo\u0019;PE*,7\r^%ogB,7\r^8sQ\rQ\u0011QI\u0001\tk\u0012$\u0018J\u001c9viV\u0011\u0011\u0011\u000f\t\u0006+\u0006M\u0014qO\u0005\u0004\u0003k2&!B!se\u0006L\bcA+\u0002z%\u0019\u00111\u0010,\u0003\r\u0005s\u0017PU3gQ\rY\u0011QI\u0001\nG>dG.Z2u_J,\"!a!\u0011\u0007\u0005\u00155#D\u0001\u0001\u00055)F\t\u0016$D_2dWm\u0019;peN)1#a#\u0002\u001cB!\u0011QRAL\u001b\t\tyI\u0003\u0003\u0002\u0012\u0006M\u0015\u0001\u00027b]\u001eT!!!&\u0002\t)\fg/Y\u0005\u0005\u00033\u000byI\u0001\u0004PE*,7\r\u001e\t\u0005\u0003W\ti*\u0003\u0003\u0002 \u00065\"!C\"pY2,7\r^8s)\t\t\u0019)A\u0005d_2dWm\u0019;fIV\u0011\u0011q\u0015\t\u0007\u0003S\u000b\u0019,a.\u000e\u0005\u0005-&\u0002BAW\u0003_\u000bq!\\;uC\ndWMC\u0002\u00022Z\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\t),a+\u0003\u0017\u0005\u0013(/Y=Ck\u001a4WM\u001d\t\u0005\u0003s\u000bY,D\u0001A\u0013\r\ti\f\u0011\u0002\f\u0013:$XM\u001d8bYJ{w/A\u0007d_2dWm\u0019;fI~#S-\u001d\u000b\u0005\u0003\u0007\fI\rE\u0002V\u0003\u000bL1!a2W\u0005\u0011)f.\u001b;\t\u0013\u0005-g#!AA\u0002\u0005\u001d\u0016a\u0001=%c\u0005Q1m\u001c7mK\u000e$X\r\u001a\u0011\u0002\u000f\r|G\u000e\\3diR!\u00111YAj\u0011\u001d\t)\u000e\u0007a\u0001\u0003\u0017\u000bQ!\u001b8qkR\f1bY8mY\u0016\u001cGOU8xgR\u0011\u00111\u001c\t\u00067\u0006M\u0011q\u0017\u0015\u0004\u0019\u0005\u0015\u0013!D3mK6,g\u000e^*dQ\u0016l\u0017-\u0006\u0002\u0002dB!\u0011Q]Av\u001b\t\t9OC\u0002\u0002jN\nQ\u0001^=qKNLA!!<\u0002h\nQ1\u000b\u001e:vGR$\u0016\u0010]3\u0002\u001d%t\u0007/\u001e;ECR\fG+\u001f9fgV\u0011\u00111\u001f\t\u0006+\u0006M\u0014Q\u001f\t\u0005\u0003K\f90\u0003\u0003\u0002z\u0006\u001d(\u0001\u0003#bi\u0006$\u0016\u0010]3)\u00079\t)%\u0001\u0005xe\u0006\u0004\b/\u001a:t+\t\u0011\t\u0001E\u0003V\u0003g\u0012\u0019\u0001E\u0004V\u0005\u000b\u0011IA!\u0003\n\u0007\t\u001daKA\u0005Gk:\u001cG/[8ocA\u0019QKa\u0003\n\u0007\t5aKA\u0002B]fD3aDA#\u0003%)hn\u001e:baB,'/\u0006\u0002\u0003\u0004!\u001a\u0001#!\u0012\u0002\u001f%t\u0007/\u001e;Qe>TWm\u0019;j_:,\"Aa\u0007\u0011\u0007u\u0012i\"C\u0002\u0003 y\u0012Q#\u00138uKJ\u0004(/\u001a;fIB\u0013xN[3di&|g\u000eK\u0002\u0012\u0003\u000b\nA!\u001a<bYR!!q\u0005B\u0017!\u0015Y&\u0011FA\\\u0013\r\u0011YC\u001a\u0002\r\u0013R,'/\u00192mK>s7-\u001a\u0005\n\u0003+\u0014\u0002\u0013!a\u0001\u0003o\u000b\u0011\u0002^3s[&t\u0017\r^3\u0015\u0005\t\u001d\u0012\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003%\f!\u0002\u001d:fiRLh*Y7f\u0003]9\u0018\u000e\u001e5OK^\u001c\u0005.\u001b7ee\u0016t\u0017J\u001c;fe:\fG\u000eF\u0002=\u0005{AqAa\u0010\u001e\u0001\u0004\u0011\t%A\u0006oK^\u001c\u0005.\u001b7ee\u0016t\u0007\u0003B.\u0003DqJ1A!\u0012g\u0005)Ie\u000eZ3yK\u0012\u001cV-]\u0001\u0005G>\u0004\u0018\u0010\u0006\u0005\u0002\u001e\t-#Q\nB(\u0011\u001d9g\u0004%AA\u0002%DqA\u001d\u0010\u0011\u0002\u0003\u0007A\u000fC\u0005\u0002\u000ey\u0001\n\u00111\u0001\u0002\u0012\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nTC\u0001B+U\rI'qK\u0016\u0003\u00053\u0002BAa\u0017\u0003f5\u0011!Q\f\u0006\u0005\u0005?\u0012\t'A\u0005v]\u000eDWmY6fI*\u0019!1\r,\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0003h\tu#!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TC\u0001B7U\r!(qK\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\t\u0011\u0019H\u000b\u0003\u0002\u0012\t]\u0013!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0003zA!\u0011Q\u0012B>\u0013\ry\u0017qR\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0005\u0003\u00032!\u0016BB\u0013\r\u0011)I\u0016\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0005\u0013\u0011Y\tC\u0005\u0002L\u0012\n\t\u00111\u0001\u0003\u0002\u0006y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0003\u0012B1!1\u0013BK\u0005\u0013i!!a,\n\t\t]\u0015q\u0016\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0003\u001e\n\r\u0006cA+\u0003 &\u0019!\u0011\u0015,\u0003\u000f\t{w\u000e\\3b]\"I\u00111\u001a\u0014\u0002\u0002\u0003\u0007!\u0011B\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0003z\t%\u0006\"CAfO\u0005\u0005\t\u0019\u0001BA\u0003\u0019)\u0017/^1mgR!!Q\u0014BX\u0011%\tY\rKA\u0001\u0002\u0004\u0011I!A\bISZ,w)\u001a8fe&\u001cW\u000b\u0012+G!\tA%fE\u0003+\u0005o\u0013\u0019\r\u0005\u0006\u0003:\n}\u0016\u000e^A\t\u0003;i!Aa/\u000b\u0007\tuf+A\u0004sk:$\u0018.\\3\n\t\t\u0005'1\u0018\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u001c\u0004\u0003\u0002Bc\u0005\u0017l!Aa2\u000b\t\t%\u00171S\u0001\u0003S>L1!\u001aBd)\t\u0011\u0019\f\u0006\u0002\u0003z\u0005)\u0011\r\u001d9msRA\u0011Q\u0004Bk\u0005/\u0014I\u000eC\u0003h[\u0001\u0007\u0011\u000eC\u0003s[\u0001\u0007A\u000fC\u0004\u0002\u000e5\u0002\r!!\u0005\u0002\u000fUt\u0017\r\u001d9msR!!q\u001cBv!\u0015)&\u0011\u001dBs\u0013\r\u0011\u0019O\u0016\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000fU\u00139/\u001b;\u0002\u0012%\u0019!\u0011\u001e,\u0003\rQ+\b\u000f\\34\u0011%\u0011iOLA\u0001\u0002\u0004\ti\"A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a#"
)
public class HiveGenericUDTF extends Expression implements Generator, HiveInspectors, CodegenFallback, UserDefinedExpression, Serializable {
   private transient GenericUDTF function;
   private transient StandardStructObjectInspector inputInspector;
   private transient StructObjectInspector outputInspector;
   private transient Object[] udtInput;
   private transient UDTFCollector collector;
   private StructType elementSchema;
   private transient DataType[] inputDataTypes;
   private transient Function1[] wrappers;
   private transient Function1 org$apache$spark$sql$hive$HiveGenericUDTF$$unwrapper;
   private transient InterpretedProjection inputProjection;
   private final String name;
   private final HiveShim.HiveFunctionWrapper funcWrapper;
   private final Seq children;
   private Seq nodePatterns;
   private volatile boolean bitmap$0;
   private transient volatile int bitmap$trans$0;

   public static Option unapply(final HiveGenericUDTF x$0) {
      return HiveGenericUDTF$.MODULE$.unapply(x$0);
   }

   public static Function1 tupled() {
      return HiveGenericUDTF$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return HiveGenericUDTF$.MODULE$.curried();
   }

   public ExprCode doGenCode(final CodegenContext ctx, final ExprCode ev) {
      return CodegenFallback.doGenCode$(this, ctx, ev);
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

   public DataType dataType() {
      return Generator.dataType$(this);
   }

   public boolean foldable() {
      return Generator.foldable$(this);
   }

   public boolean nullable() {
      return Generator.nullable$(this);
   }

   public boolean supportCodegen() {
      return Generator.supportCodegen$(this);
   }

   public Seq nodePatterns() {
      return this.nodePatterns;
   }

   public void org$apache$spark$sql$catalyst$expressions$Generator$_setter_$nodePatterns_$eq(final Seq x$1) {
      this.nodePatterns = x$1;
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

   private GenericUDTF function$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$trans$0 & 1) == 0) {
            GenericUDTF fun = (GenericUDTF)this.funcWrapper().createFunction();
            fun.setCollector(this.collector());
            this.function = fun;
            this.bitmap$trans$0 |= 1;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.function;
   }

   public GenericUDTF function() {
      return (this.bitmap$trans$0 & 1) == 0 ? this.function$lzycompute() : this.function;
   }

   private StandardStructObjectInspector inputInspector$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$trans$0 & 2) == 0) {
            Seq inspectors = (Seq)this.children().map((expr) -> this.toInspector(expr));
            List fields = .MODULE$.SeqHasAsJava(inspectors.indices().map((index) -> $anonfun$inputInspector$2(BoxesRunTime.unboxToInt(index)))).asJava();
            this.inputInspector = ObjectInspectorFactory.getStandardStructObjectInspector(fields, .MODULE$.SeqHasAsJava(inspectors).asJava());
            this.bitmap$trans$0 |= 2;
         }
      } catch (Throwable var5) {
         throw var5;
      }

      return this.inputInspector;
   }

   public StandardStructObjectInspector inputInspector() {
      return (this.bitmap$trans$0 & 2) == 0 ? this.inputInspector$lzycompute() : this.inputInspector;
   }

   private StructObjectInspector outputInspector$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$trans$0 & 4) == 0) {
            this.outputInspector = this.function().initialize(this.inputInspector());
            this.bitmap$trans$0 |= 4;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.outputInspector;
   }

   public StructObjectInspector outputInspector() {
      return (this.bitmap$trans$0 & 4) == 0 ? this.outputInspector$lzycompute() : this.outputInspector;
   }

   private Object[] udtInput$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$trans$0 & 8) == 0) {
            this.udtInput = new Object[this.children().length()];
            this.bitmap$trans$0 |= 8;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.udtInput;
   }

   public Object[] udtInput() {
      return (this.bitmap$trans$0 & 8) == 0 ? this.udtInput$lzycompute() : this.udtInput;
   }

   private UDTFCollector collector$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$trans$0 & 16) == 0) {
            this.collector = new UDTFCollector();
            this.bitmap$trans$0 |= 16;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.collector;
   }

   public UDTFCollector collector() {
      return (this.bitmap$trans$0 & 16) == 0 ? this.collector$lzycompute() : this.collector;
   }

   private StructType elementSchema$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.elementSchema = new StructType((org.apache.spark.sql.types.StructField[])((IterableOnceOps).MODULE$.ListHasAsScala(this.outputInspector().getAllStructFieldRefs()).asScala().map((field) -> new org.apache.spark.sql.types.StructField(field.getFieldName(), this.inspectorToDataType(field.getFieldObjectInspector()), true, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()))).toArray(scala.reflect.ClassTag..MODULE$.apply(org.apache.spark.sql.types.StructField.class)));
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.elementSchema;
   }

   public StructType elementSchema() {
      return !this.bitmap$0 ? this.elementSchema$lzycompute() : this.elementSchema;
   }

   private DataType[] inputDataTypes$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$trans$0 & 32) == 0) {
            this.inputDataTypes = (DataType[])((IterableOnceOps)this.children().map((x$8) -> x$8.dataType())).toArray(scala.reflect.ClassTag..MODULE$.apply(DataType.class));
            this.bitmap$trans$0 |= 32;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.inputDataTypes;
   }

   private DataType[] inputDataTypes() {
      return (this.bitmap$trans$0 & 32) == 0 ? this.inputDataTypes$lzycompute() : this.inputDataTypes;
   }

   private Function1[] wrappers$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$trans$0 & 64) == 0) {
            this.wrappers = (Function1[])((IterableOnceOps)this.children().map((x) -> this.wrapperFor(this.toInspector(x), x.dataType()))).toArray(scala.reflect.ClassTag..MODULE$.apply(Function1.class));
            this.bitmap$trans$0 |= 64;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.wrappers;
   }

   private Function1[] wrappers() {
      return (this.bitmap$trans$0 & 64) == 0 ? this.wrappers$lzycompute() : this.wrappers;
   }

   private Function1 unwrapper$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$trans$0 & 128) == 0) {
            this.org$apache$spark$sql$hive$HiveGenericUDTF$$unwrapper = this.unwrapperFor((ObjectInspector)this.outputInspector());
            this.bitmap$trans$0 |= 128;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.org$apache$spark$sql$hive$HiveGenericUDTF$$unwrapper;
   }

   public Function1 org$apache$spark$sql$hive$HiveGenericUDTF$$unwrapper() {
      return (this.bitmap$trans$0 & 128) == 0 ? this.unwrapper$lzycompute() : this.org$apache$spark$sql$hive$HiveGenericUDTF$$unwrapper;
   }

   private InterpretedProjection inputProjection$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$trans$0 & 256) == 0) {
            this.inputProjection = new InterpretedProjection(this.children());
            this.bitmap$trans$0 |= 256;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.inputProjection;
   }

   private InterpretedProjection inputProjection() {
      return (this.bitmap$trans$0 & 256) == 0 ? this.inputProjection$lzycompute() : this.inputProjection;
   }

   public IterableOnce eval(final InternalRow input) {
      this.outputInspector();
      this.function().process(this.wrap(this.inputProjection().apply(input), this.wrappers(), this.udtInput(), this.inputDataTypes()));
      return this.collector().collectRows();
   }

   public IterableOnce terminate() {
      this.outputInspector();
      this.function().close();
      return this.collector().collectRows();
   }

   public String toString() {
      String var10000 = this.nodeName();
      return var10000 + "#" + this.funcWrapper().functionClassName() + "(" + this.children().mkString(",") + ")";
   }

   public String prettyName() {
      return this.name();
   }

   public Expression withNewChildrenInternal(final IndexedSeq newChildren) {
      String x$2 = this.copy$default$1();
      HiveShim.HiveFunctionWrapper x$3 = this.copy$default$2();
      return this.copy(x$2, x$3, newChildren);
   }

   public HiveGenericUDTF copy(final String name, final HiveShim.HiveFunctionWrapper funcWrapper, final Seq children) {
      return new HiveGenericUDTF(name, funcWrapper, children);
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
      return "HiveGenericUDTF";
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
      return x$1 instanceof HiveGenericUDTF;
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
            if (x$1 instanceof HiveGenericUDTF) {
               label56: {
                  HiveGenericUDTF var4 = (HiveGenericUDTF)x$1;
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
   public static final String $anonfun$inputInspector$2(final int index) {
      return "_col" + index;
   }

   public HiveGenericUDTF(final String name, final HiveShim.HiveFunctionWrapper funcWrapper, final Seq children) {
      this.name = name;
      this.funcWrapper = funcWrapper;
      this.children = children;
      Generator.$init$(this);
      HiveInspectors.$init$(this);
      CodegenFallback.$init$(this);
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class UDTFCollector implements Collector {
      private ArrayBuffer collected;
      // $FF: synthetic field
      public final HiveGenericUDTF $outer;

      public ArrayBuffer collected() {
         return this.collected;
      }

      public void collected_$eq(final ArrayBuffer x$1) {
         this.collected = x$1;
      }

      public void collect(final Object input) {
         this.collected().$plus$eq((InternalRow)this.org$apache$spark$sql$hive$HiveGenericUDTF$UDTFCollector$$$outer().org$apache$spark$sql$hive$HiveGenericUDTF$$unwrapper().apply(input));
      }

      public Seq collectRows() {
         ArrayBuffer toCollect = this.collected();
         this.collected_$eq(new ArrayBuffer());
         return toCollect.toSeq();
      }

      // $FF: synthetic method
      public HiveGenericUDTF org$apache$spark$sql$hive$HiveGenericUDTF$UDTFCollector$$$outer() {
         return this.$outer;
      }

      public UDTFCollector() {
         if (HiveGenericUDTF.this == null) {
            throw null;
         } else {
            this.$outer = HiveGenericUDTF.this;
            super();
            this.collected = new ArrayBuffer();
         }
      }
   }
}
