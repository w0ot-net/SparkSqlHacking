package org.apache.spark.sql.hive.execution;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Type;
import java.util.NoSuchElementException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.ql.exec.RecordReader;
import org.apache.hadoop.hive.serde2.AbstractSerDe;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.io.Writable;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.expressions.AttributeSet;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.catalyst.expressions.InterpretedProjection;
import org.apache.spark.sql.catalyst.expressions.SpecificInternalRow;
import org.apache.spark.sql.catalyst.plans.physical.Partitioning;
import org.apache.spark.sql.catalyst.trees.TreeNode;
import org.apache.spark.sql.catalyst.trees.UnaryLike;
import org.apache.spark.sql.execution.BaseScriptTransformationExec;
import org.apache.spark.sql.execution.BaseScriptTransformationWriterThread;
import org.apache.spark.sql.execution.ScriptTransformationIOSchema;
import org.apache.spark.sql.execution.SparkPlan;
import org.apache.spark.sql.execution.UnaryExecNode;
import org.apache.spark.sql.hive.HiveInspectors;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.util.CircularBuffer;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple4;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0005c!\u0002\u0011\"\u0001\u000ej\u0003\u0002C%\u0001\u0005+\u0007I\u0011\u0001&\t\u0011M\u0003!\u0011#Q\u0001\n-C\u0001\u0002\u0016\u0001\u0003\u0016\u0004%\t!\u0016\u0005\tC\u0002\u0011\t\u0012)A\u0005-\"A!\r\u0001BK\u0002\u0013\u00051\r\u0003\u0005e\u0001\tE\t\u0015!\u0003/\u0011!)\u0007A!f\u0001\n\u00031\u0007\u0002\u00036\u0001\u0005#\u0005\u000b\u0011B4\t\u000b-\u0004A\u0011\u00017\t\u000bM\u0004A\u0011\u0002;\t\u000f\u00055\u0004\u0001\"\u0011\u0002p!9\u0011q\u000f\u0001\u0005R\u0005e\u0004\"CA@\u0001\u0005\u0005I\u0011AAA\u0011%\tY\tAI\u0001\n\u0003\ti\tC\u0005\u0002$\u0002\t\n\u0011\"\u0001\u0002&\"I\u0011\u0011\u0016\u0001\u0012\u0002\u0013\u0005\u00111\u0016\u0005\n\u0003_\u0003\u0011\u0013!C\u0001\u0003cC\u0011\"!.\u0001\u0003\u0003%\t%a.\t\u0013\u0005u\u0006!!A\u0005\u0002\u0005}\u0006\"CAd\u0001\u0005\u0005I\u0011AAe\u0011%\t)\u000eAA\u0001\n\u0003\n9\u000eC\u0005\u0002d\u0002\t\t\u0011\"\u0001\u0002f\"I\u0011q\u001e\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u001f\u0005\n\u0003k\u0004\u0011\u0011!C!\u0003o<!\"a?\"\u0003\u0003E\taIA\u007f\r%\u0001\u0013%!A\t\u0002\r\ny\u0010\u0003\u0004l5\u0011\u0005!\u0011\u0003\u0005\n\u0005'Q\u0012\u0011!C#\u0005+A\u0011Ba\u0006\u001b\u0003\u0003%\tI!\u0007\t\u0013\t\r\"$!A\u0005\u0002\n\u0015\u0002\"\u0003B\u001c5\u0005\u0005I\u0011\u0002B\u001d\u0005qA\u0015N^3TGJL\u0007\u000f\u001e+sC:\u001chm\u001c:nCRLwN\\#yK\u000eT!AI\u0012\u0002\u0013\u0015DXmY;uS>t'B\u0001\u0013&\u0003\u0011A\u0017N^3\u000b\u0005\u0019:\u0013aA:rY*\u0011\u0001&K\u0001\u0006gB\f'o\u001b\u0006\u0003U-\na!\u00199bG\",'\"\u0001\u0017\u0002\u0007=\u0014xmE\u0003\u0001]M2D\b\u0005\u00020c5\t\u0001G\u0003\u0002#K%\u0011!\u0007\r\u0002\n'B\f'o\u001b)mC:\u0004\"a\f\u001b\n\u0005U\u0002$\u0001\b\"bg\u0016\u001c6M]5qiR\u0013\u0018M\\:g_Jl\u0017\r^5p]\u0016CXm\u0019\t\u0003oij\u0011\u0001\u000f\u0006\u0002s\u0005)1oY1mC&\u00111\b\u000f\u0002\b!J|G-^2u!\tidI\u0004\u0002?\t:\u0011qhQ\u0007\u0002\u0001*\u0011\u0011IQ\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t\u0011(\u0003\u0002Fq\u00059\u0001/Y2lC\u001e,\u0017BA$I\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t)\u0005(\u0001\u0004tGJL\u0007\u000f^\u000b\u0002\u0017B\u0011A\n\u0015\b\u0003\u001b:\u0003\"a\u0010\u001d\n\u0005=C\u0014A\u0002)sK\u0012,g-\u0003\u0002R%\n11\u000b\u001e:j]\u001eT!a\u0014\u001d\u0002\u000fM\u001c'/\u001b9uA\u00051q.\u001e;qkR,\u0012A\u0016\t\u0004{]K\u0016B\u0001-I\u0005\r\u0019V-\u001d\t\u00035~k\u0011a\u0017\u0006\u00039v\u000b1\"\u001a=qe\u0016\u001c8/[8og*\u0011a,J\u0001\tG\u0006$\u0018\r\\=ti&\u0011\u0001m\u0017\u0002\n\u0003R$(/\u001b2vi\u0016\fqa\\;uaV$\b%A\u0003dQ&dG-F\u0001/\u0003\u0019\u0019\u0007.\u001b7eA\u0005A\u0011n\\:dQ\u0016l\u0017-F\u0001h!\ty\u0003.\u0003\u0002ja\ta2k\u0019:jaR$&/\u00198tM>\u0014X.\u0019;j_:LujU2iK6\f\u0017!C5pg\u000eDW-\\1!\u0003\u0019a\u0014N\\5u}Q)Qn\u001c9reB\u0011a\u000eA\u0007\u0002C!)\u0011*\u0003a\u0001\u0017\")A+\u0003a\u0001-\")!-\u0003a\u0001]!)Q-\u0003a\u0001O\u0006i2M]3bi\u0016|U\u000f\u001e9vi&#XM]1u_J<\u0016\u000e\u001e5TKJ$W\r\u0006\bvy\u0006\r\u0011qCA\u0014\u0003o\ti%!\u0018\u0011\u0007u2\b0\u0003\u0002x\u0011\nA\u0011\n^3sCR|'\u000f\u0005\u0002zu6\tQ,\u0003\u0002|;\nY\u0011J\u001c;fe:\fGNU8x\u0011\u0015i(\u00021\u0001\u007f\u000319(/\u001b;feRC'/Z1e!\tys0C\u0002\u0002\u0002A\u0012AEQ1tKN\u001b'/\u001b9u)J\fgn\u001d4pe6\fG/[8o/JLG/\u001a:UQJ,\u0017\r\u001a\u0005\b\u0003\u000bQ\u0001\u0019AA\u0004\u0003-Ig\u000e];u'R\u0014X-Y7\u0011\t\u0005%\u00111C\u0007\u0003\u0003\u0017QA!!\u0004\u0002\u0010\u0005\u0011\u0011n\u001c\u0006\u0003\u0003#\tAA[1wC&!\u0011QCA\u0006\u0005-Ie\u000e];u'R\u0014X-Y7\t\u000f\u0005e!\u00021\u0001\u0002\u001c\u0005!\u0001O]8d!\u0011\ti\"a\t\u000e\u0005\u0005}!\u0002BA\u0011\u0003\u001f\tA\u0001\\1oO&!\u0011QEA\u0010\u0005\u001d\u0001&o\\2fgNDq!!\u000b\u000b\u0001\u0004\tY#\u0001\u0007ti\u0012,'O\u001d\"vM\u001a,'\u000f\u0005\u0003\u0002.\u0005MRBAA\u0018\u0015\r\t\tdJ\u0001\u0005kRLG.\u0003\u0003\u00026\u0005=\"AD\"je\u000e,H.\u0019:Ck\u001a4WM\u001d\u0005\b\u0003sQ\u0001\u0019AA\u001e\u0003-yW\u000f\u001e9viN+'\u000fZ3\u0011\t\u0005u\u0012\u0011J\u0007\u0003\u0003\u007fQA!!\u0011\u0002D\u000511/\u001a:eKJR1\u0001JA#\u0015\r\t9%K\u0001\u0007Q\u0006$wn\u001c9\n\t\u0005-\u0013q\b\u0002\u000e\u0003\n\u001cHO]1diN+'\u000fR3\t\u000f\u0005=#\u00021\u0001\u0002R\u0005Iq.\u001e;qkR\u001cv.\u001b\t\u0005\u0003'\nI&\u0004\u0002\u0002V)!\u0011qKA \u0003=y'M[3di&t7\u000f]3di>\u0014\u0018\u0002BA.\u0003+\u0012Qc\u0015;sk\u000e$xJ\u00196fGRLen\u001d9fGR|'\u000fC\u0004\u0002`)\u0001\r!!\u0019\u0002\u0015!\fGm\\8q\u0007>tg\r\u0005\u0003\u0002d\u0005%TBAA3\u0015\u0011\t9'!\u0012\u0002\t\r|gNZ\u0005\u0005\u0003W\n)GA\u0007D_:4\u0017nZ;sCRLwN\\\u0001\u0010aJ|7-Z:t\u0013R,'/\u0019;peR)Q/!\u001d\u0002v!1\u00111O\u0006A\u0002U\fQ\"\u001b8qkRLE/\u001a:bi>\u0014\bbBA0\u0017\u0001\u0007\u0011\u0011M\u0001\u0015o&$\bNT3x\u0007\"LG\u000eZ%oi\u0016\u0014h.\u00197\u0015\u00075\fY\b\u0003\u0004\u0002~1\u0001\rAL\u0001\t]\u0016<8\t[5mI\u0006!1m\u001c9z)%i\u00171QAC\u0003\u000f\u000bI\tC\u0004J\u001bA\u0005\t\u0019A&\t\u000fQk\u0001\u0013!a\u0001-\"9!-\u0004I\u0001\u0002\u0004q\u0003bB3\u000e!\u0003\u0005\raZ\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\tyIK\u0002L\u0003#[#!a%\u0011\t\u0005U\u0015qT\u0007\u0003\u0003/SA!!'\u0002\u001c\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003;C\u0014AC1o]>$\u0018\r^5p]&!\u0011\u0011UAL\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\t9KK\u0002W\u0003#\u000babY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u0002.*\u001aa&!%\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%iU\u0011\u00111\u0017\u0016\u0004O\u0006E\u0015!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002:B!\u0011QDA^\u0013\r\t\u0016qD\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003\u0003\u00042aNAb\u0013\r\t)\r\u000f\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003\u0017\f\t\u000eE\u00028\u0003\u001bL1!a49\u0005\r\te.\u001f\u0005\n\u0003'$\u0012\u0011!a\u0001\u0003\u0003\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAAm!\u0019\tY.!9\u0002L6\u0011\u0011Q\u001c\u0006\u0004\u0003?D\u0014AC2pY2,7\r^5p]&\u0019q/!8\u0002\u0011\r\fg.R9vC2$B!a:\u0002nB\u0019q'!;\n\u0007\u0005-\bHA\u0004C_>dW-\u00198\t\u0013\u0005Mg#!AA\u0002\u0005-\u0017A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!/\u0002t\"I\u00111[\f\u0002\u0002\u0003\u0007\u0011\u0011Y\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005\u001d\u0018\u0011 \u0005\n\u0003'D\u0012\u0011!a\u0001\u0003\u0017\fA\u0004S5wKN\u001b'/\u001b9u)J\fgn\u001d4pe6\fG/[8o\u000bb,7\r\u0005\u0002o5M)!D!\u0001\u0003\u000eAI!1\u0001B\u0005\u0017Zss-\\\u0007\u0003\u0005\u000bQ1Aa\u00029\u0003\u001d\u0011XO\u001c;j[\u0016LAAa\u0003\u0003\u0006\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001b\u0011\t\u0005%!qB\u0005\u0004\u000f\u0006-ACAA\u007f\u0003!!xn\u0015;sS:<GCAA]\u0003\u0015\t\u0007\u000f\u001d7z)%i'1\u0004B\u000f\u0005?\u0011\t\u0003C\u0003J;\u0001\u00071\nC\u0003U;\u0001\u0007a\u000bC\u0003c;\u0001\u0007a\u0006C\u0003f;\u0001\u0007q-A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\t\u001d\"1\u0007\t\u0006o\t%\"QF\u0005\u0004\u0005WA$AB(qi&|g\u000eE\u00048\u0005_YeKL4\n\u0007\tE\u0002H\u0001\u0004UkBdW\r\u000e\u0005\t\u0005kq\u0012\u0011!a\u0001[\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\tm\u0002\u0003BA\u000f\u0005{IAAa\u0010\u0002 \t1qJ\u00196fGR\u0004"
)
public class HiveScriptTransformationExec extends SparkPlan implements BaseScriptTransformationExec {
   private final String script;
   private final Seq output;
   private final SparkPlan child;
   private final ScriptTransformationIOSchema ioschema;
   private Seq inputExpressionsWithoutSerde;
   private Seq org$apache$spark$sql$execution$BaseScriptTransformationExec$$outputFieldWriters;
   private Function2 org$apache$spark$sql$execution$BaseScriptTransformationExec$$wrapperConvertException;
   private transient Seq children;
   private volatile byte bitmap$0;
   private transient volatile boolean bitmap$trans$0;

   public static Option unapply(final HiveScriptTransformationExec x$0) {
      return HiveScriptTransformationExec$.MODULE$.unapply(x$0);
   }

   public static Function1 tupled() {
      return HiveScriptTransformationExec$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return HiveScriptTransformationExec$.MODULE$.curried();
   }

   public AttributeSet producedAttributes() {
      return BaseScriptTransformationExec.producedAttributes$(this);
   }

   public Partitioning outputPartitioning() {
      return BaseScriptTransformationExec.outputPartitioning$(this);
   }

   public RDD doExecute() {
      return BaseScriptTransformationExec.doExecute$(this);
   }

   public Tuple4 initProc() {
      return BaseScriptTransformationExec.initProc$(this);
   }

   public Iterator createOutputIteratorWithoutSerde(final BaseScriptTransformationWriterThread writerThread, final InputStream inputStream, final Process proc, final CircularBuffer stderrBuffer) {
      return BaseScriptTransformationExec.createOutputIteratorWithoutSerde$(this, writerThread, inputStream, proc, stderrBuffer);
   }

   public void checkFailureAndPropagate(final BaseScriptTransformationWriterThread writerThread, final Throwable cause, final Process proc, final CircularBuffer stderrBuffer) {
      BaseScriptTransformationExec.checkFailureAndPropagate$(this, writerThread, cause, proc, stderrBuffer);
   }

   public Throwable checkFailureAndPropagate$default$2() {
      return BaseScriptTransformationExec.checkFailureAndPropagate$default$2$(this);
   }

   public String verboseStringWithOperatorId() {
      return UnaryExecNode.verboseStringWithOperatorId$(this);
   }

   public final TreeNode mapChildren(final Function1 f) {
      return UnaryLike.mapChildren$(this, f);
   }

   public final TreeNode withNewChildrenInternal(final IndexedSeq newChildren) {
      return UnaryLike.withNewChildrenInternal$(this, newChildren);
   }

   private Seq inputExpressionsWithoutSerde$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.inputExpressionsWithoutSerde = BaseScriptTransformationExec.inputExpressionsWithoutSerde$(this);
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.inputExpressionsWithoutSerde;
   }

   public Seq inputExpressionsWithoutSerde() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.inputExpressionsWithoutSerde$lzycompute() : this.inputExpressionsWithoutSerde;
   }

   private Seq org$apache$spark$sql$execution$BaseScriptTransformationExec$$outputFieldWriters$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.org$apache$spark$sql$execution$BaseScriptTransformationExec$$outputFieldWriters = BaseScriptTransformationExec.org$apache$spark$sql$execution$BaseScriptTransformationExec$$outputFieldWriters$(this);
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.org$apache$spark$sql$execution$BaseScriptTransformationExec$$outputFieldWriters;
   }

   public Seq org$apache$spark$sql$execution$BaseScriptTransformationExec$$outputFieldWriters() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.org$apache$spark$sql$execution$BaseScriptTransformationExec$$outputFieldWriters$lzycompute() : this.org$apache$spark$sql$execution$BaseScriptTransformationExec$$outputFieldWriters;
   }

   public Function2 org$apache$spark$sql$execution$BaseScriptTransformationExec$$wrapperConvertException() {
      return this.org$apache$spark$sql$execution$BaseScriptTransformationExec$$wrapperConvertException;
   }

   public final void org$apache$spark$sql$execution$BaseScriptTransformationExec$_setter_$org$apache$spark$sql$execution$BaseScriptTransformationExec$$wrapperConvertException_$eq(final Function2 x$1) {
      this.org$apache$spark$sql$execution$BaseScriptTransformationExec$$wrapperConvertException = x$1;
   }

   private Seq children$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            this.children = UnaryLike.children$(this);
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.children;
   }

   public final Seq children() {
      return !this.bitmap$trans$0 ? this.children$lzycompute() : this.children;
   }

   public String script() {
      return this.script;
   }

   public Seq output() {
      return this.output;
   }

   public SparkPlan child() {
      return this.child;
   }

   public ScriptTransformationIOSchema ioschema() {
      return this.ioschema;
   }

   private Iterator createOutputIteratorWithSerde(final BaseScriptTransformationWriterThread writerThread, final InputStream inputStream, final Process proc, final CircularBuffer stderrBuffer, final AbstractSerDe outputSerde, final StructObjectInspector outputSoi, final Configuration hadoopConf) {
      return new Iterator(inputStream, hadoopConf, outputSerde, outputSoi, writerThread, proc, stderrBuffer) {
         private transient Buffer unwrappers;
         private boolean completed;
         private final DataInputStream scriptOutputStream;
         private final RecordReader scriptOutputReader;
         private Writable scriptOutputWritable;
         private final Writable reusedWritableObject;
         private final SpecificInternalRow mutableRow;
         private transient volatile boolean bitmap$trans$0;
         // $FF: synthetic field
         private final HiveScriptTransformationExec $outer;
         private final AbstractSerDe outputSerde$1;
         private final StructObjectInspector outputSoi$1;
         private final BaseScriptTransformationWriterThread writerThread$1;
         private final Process proc$1;
         private final CircularBuffer stderrBuffer$1;

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

         /** @deprecated */
         public final boolean hasDefiniteSize() {
            return Iterator.hasDefiniteSize$(this);
         }

         public final Iterator iterator() {
            return Iterator.iterator$(this);
         }

         public Option nextOption() {
            return Iterator.nextOption$(this);
         }

         public boolean contains(final Object elem) {
            return Iterator.contains$(this, elem);
         }

         public BufferedIterator buffered() {
            return Iterator.buffered$(this);
         }

         public Iterator padTo(final int len, final Object elem) {
            return Iterator.padTo$(this, len, elem);
         }

         public Tuple2 partition(final Function1 p) {
            return Iterator.partition$(this, p);
         }

         public Iterator.GroupedIterator grouped(final int size) {
            return Iterator.grouped$(this, size);
         }

         public Iterator.GroupedIterator sliding(final int size, final int step) {
            return Iterator.sliding$(this, size, step);
         }

         public int sliding$default$2() {
            return Iterator.sliding$default$2$(this);
         }

         public Iterator scanLeft(final Object z, final Function2 op) {
            return Iterator.scanLeft$(this, z, op);
         }

         /** @deprecated */
         public Iterator scanRight(final Object z, final Function2 op) {
            return Iterator.scanRight$(this, z, op);
         }

         public int indexWhere(final Function1 p, final int from) {
            return Iterator.indexWhere$(this, p, from);
         }

         public int indexWhere$default$2() {
            return Iterator.indexWhere$default$2$(this);
         }

         public int indexOf(final Object elem) {
            return Iterator.indexOf$(this, elem);
         }

         public int indexOf(final Object elem, final int from) {
            return Iterator.indexOf$(this, elem, from);
         }

         public final int length() {
            return Iterator.length$(this);
         }

         public boolean isEmpty() {
            return Iterator.isEmpty$(this);
         }

         public Iterator filter(final Function1 p) {
            return Iterator.filter$(this, p);
         }

         public Iterator filterNot(final Function1 p) {
            return Iterator.filterNot$(this, p);
         }

         public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
            return Iterator.filterImpl$(this, p, isFlipped);
         }

         public Iterator withFilter(final Function1 p) {
            return Iterator.withFilter$(this, p);
         }

         public Iterator collect(final PartialFunction pf) {
            return Iterator.collect$(this, pf);
         }

         public Iterator distinct() {
            return Iterator.distinct$(this);
         }

         public Iterator distinctBy(final Function1 f) {
            return Iterator.distinctBy$(this, f);
         }

         public Iterator map(final Function1 f) {
            return Iterator.map$(this, f);
         }

         public Iterator flatMap(final Function1 f) {
            return Iterator.flatMap$(this, f);
         }

         public Iterator flatten(final Function1 ev) {
            return Iterator.flatten$(this, ev);
         }

         public Iterator concat(final Function0 xs) {
            return Iterator.concat$(this, xs);
         }

         public final Iterator $plus$plus(final Function0 xs) {
            return Iterator.$plus$plus$(this, xs);
         }

         public Iterator take(final int n) {
            return Iterator.take$(this, n);
         }

         public Iterator takeWhile(final Function1 p) {
            return Iterator.takeWhile$(this, p);
         }

         public Iterator drop(final int n) {
            return Iterator.drop$(this, n);
         }

         public Iterator dropWhile(final Function1 p) {
            return Iterator.dropWhile$(this, p);
         }

         public Tuple2 span(final Function1 p) {
            return Iterator.span$(this, p);
         }

         public Iterator slice(final int from, final int until) {
            return Iterator.slice$(this, from, until);
         }

         public Iterator sliceIterator(final int from, final int until) {
            return Iterator.sliceIterator$(this, from, until);
         }

         public Iterator zip(final IterableOnce that) {
            return Iterator.zip$(this, that);
         }

         public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
            return Iterator.zipAll$(this, that, thisElem, thatElem);
         }

         public Iterator zipWithIndex() {
            return Iterator.zipWithIndex$(this);
         }

         public boolean sameElements(final IterableOnce that) {
            return Iterator.sameElements$(this, that);
         }

         public Tuple2 duplicate() {
            return Iterator.duplicate$(this);
         }

         public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
            return Iterator.patch$(this, from, patchElems, replaced);
         }

         public Iterator tapEach(final Function1 f) {
            return Iterator.tapEach$(this, f);
         }

         public String toString() {
            return Iterator.toString$(this);
         }

         /** @deprecated */
         public Iterator seq() {
            return Iterator.seq$(this);
         }

         public Tuple2 splitAt(final int n) {
            return IterableOnceOps.splitAt$(this, n);
         }

         public boolean isTraversableAgain() {
            return IterableOnceOps.isTraversableAgain$(this);
         }

         public void foreach(final Function1 f) {
            IterableOnceOps.foreach$(this, f);
         }

         public boolean forall(final Function1 p) {
            return IterableOnceOps.forall$(this, p);
         }

         public boolean exists(final Function1 p) {
            return IterableOnceOps.exists$(this, p);
         }

         public int count(final Function1 p) {
            return IterableOnceOps.count$(this, p);
         }

         public Option find(final Function1 p) {
            return IterableOnceOps.find$(this, p);
         }

         public Object foldLeft(final Object z, final Function2 op) {
            return IterableOnceOps.foldLeft$(this, z, op);
         }

         public Object foldRight(final Object z, final Function2 op) {
            return IterableOnceOps.foldRight$(this, z, op);
         }

         /** @deprecated */
         public final Object $div$colon(final Object z, final Function2 op) {
            return IterableOnceOps.$div$colon$(this, z, op);
         }

         /** @deprecated */
         public final Object $colon$bslash(final Object z, final Function2 op) {
            return IterableOnceOps.$colon$bslash$(this, z, op);
         }

         public Object fold(final Object z, final Function2 op) {
            return IterableOnceOps.fold$(this, z, op);
         }

         public Object reduce(final Function2 op) {
            return IterableOnceOps.reduce$(this, op);
         }

         public Option reduceOption(final Function2 op) {
            return IterableOnceOps.reduceOption$(this, op);
         }

         public Object reduceLeft(final Function2 op) {
            return IterableOnceOps.reduceLeft$(this, op);
         }

         public Object reduceRight(final Function2 op) {
            return IterableOnceOps.reduceRight$(this, op);
         }

         public Option reduceLeftOption(final Function2 op) {
            return IterableOnceOps.reduceLeftOption$(this, op);
         }

         public Option reduceRightOption(final Function2 op) {
            return IterableOnceOps.reduceRightOption$(this, op);
         }

         public boolean nonEmpty() {
            return IterableOnceOps.nonEmpty$(this);
         }

         public int size() {
            return IterableOnceOps.size$(this);
         }

         /** @deprecated */
         public final void copyToBuffer(final Buffer dest) {
            IterableOnceOps.copyToBuffer$(this, dest);
         }

         public int copyToArray(final Object xs) {
            return IterableOnceOps.copyToArray$(this, xs);
         }

         public int copyToArray(final Object xs, final int start) {
            return IterableOnceOps.copyToArray$(this, xs, start);
         }

         public int copyToArray(final Object xs, final int start, final int len) {
            return IterableOnceOps.copyToArray$(this, xs, start, len);
         }

         public Object sum(final Numeric num) {
            return IterableOnceOps.sum$(this, num);
         }

         public Object product(final Numeric num) {
            return IterableOnceOps.product$(this, num);
         }

         public Object min(final Ordering ord) {
            return IterableOnceOps.min$(this, ord);
         }

         public Option minOption(final Ordering ord) {
            return IterableOnceOps.minOption$(this, ord);
         }

         public Object max(final Ordering ord) {
            return IterableOnceOps.max$(this, ord);
         }

         public Option maxOption(final Ordering ord) {
            return IterableOnceOps.maxOption$(this, ord);
         }

         public Object maxBy(final Function1 f, final Ordering ord) {
            return IterableOnceOps.maxBy$(this, f, ord);
         }

         public Option maxByOption(final Function1 f, final Ordering ord) {
            return IterableOnceOps.maxByOption$(this, f, ord);
         }

         public Object minBy(final Function1 f, final Ordering ord) {
            return IterableOnceOps.minBy$(this, f, ord);
         }

         public Option minByOption(final Function1 f, final Ordering ord) {
            return IterableOnceOps.minByOption$(this, f, ord);
         }

         public Option collectFirst(final PartialFunction pf) {
            return IterableOnceOps.collectFirst$(this, pf);
         }

         /** @deprecated */
         public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
            return IterableOnceOps.aggregate$(this, z, seqop, combop);
         }

         public boolean corresponds(final IterableOnce that, final Function2 p) {
            return IterableOnceOps.corresponds$(this, that, p);
         }

         public final String mkString(final String start, final String sep, final String end) {
            return IterableOnceOps.mkString$(this, start, sep, end);
         }

         public final String mkString(final String sep) {
            return IterableOnceOps.mkString$(this, sep);
         }

         public final String mkString() {
            return IterableOnceOps.mkString$(this);
         }

         public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
            return IterableOnceOps.addString$(this, b, start, sep, end);
         }

         public final StringBuilder addString(final StringBuilder b, final String sep) {
            return IterableOnceOps.addString$(this, b, sep);
         }

         public final StringBuilder addString(final StringBuilder b) {
            return IterableOnceOps.addString$(this, b);
         }

         public Object to(final Factory factory) {
            return IterableOnceOps.to$(this, factory);
         }

         /** @deprecated */
         public final Iterator toIterator() {
            return IterableOnceOps.toIterator$(this);
         }

         public List toList() {
            return IterableOnceOps.toList$(this);
         }

         public Vector toVector() {
            return IterableOnceOps.toVector$(this);
         }

         public Map toMap(final .less.colon.less ev) {
            return IterableOnceOps.toMap$(this, ev);
         }

         public Set toSet() {
            return IterableOnceOps.toSet$(this);
         }

         public Seq toSeq() {
            return IterableOnceOps.toSeq$(this);
         }

         public IndexedSeq toIndexedSeq() {
            return IterableOnceOps.toIndexedSeq$(this);
         }

         /** @deprecated */
         public final Stream toStream() {
            return IterableOnceOps.toStream$(this);
         }

         public final Buffer toBuffer() {
            return IterableOnceOps.toBuffer$(this);
         }

         public Object toArray(final ClassTag evidence$2) {
            return IterableOnceOps.toArray$(this, evidence$2);
         }

         public Iterable reversed() {
            return IterableOnceOps.reversed$(this);
         }

         public Stepper stepper(final StepperShape shape) {
            return IterableOnce.stepper$(this, shape);
         }

         public int knownSize() {
            return IterableOnce.knownSize$(this);
         }

         private boolean completed() {
            return this.completed;
         }

         private void completed_$eq(final boolean x$1) {
            this.completed = x$1;
         }

         private DataInputStream scriptOutputStream() {
            return this.scriptOutputStream;
         }

         private RecordReader scriptOutputReader() {
            return this.scriptOutputReader;
         }

         private Writable scriptOutputWritable() {
            return this.scriptOutputWritable;
         }

         private void scriptOutputWritable_$eq(final Writable x$1) {
            this.scriptOutputWritable = x$1;
         }

         private Writable reusedWritableObject() {
            return this.reusedWritableObject;
         }

         private SpecificInternalRow mutableRow() {
            return this.mutableRow;
         }

         private Buffer unwrappers$lzycompute() {
            synchronized(this){}

            try {
               if (!this.bitmap$trans$0) {
                  this.unwrappers = (Buffer)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(this.outputSoi$1.getAllStructFieldRefs()).asScala().map((field) -> this.unwrapperFor(field));
                  this.bitmap$trans$0 = true;
               }
            } catch (Throwable var3) {
               throw var3;
            }

            return this.unwrappers;
         }

         private Buffer unwrappers() {
            return !this.bitmap$trans$0 ? this.unwrappers$lzycompute() : this.unwrappers;
         }

         public boolean hasNext() {
            if (this.completed()) {
               return false;
            } else {
               try {
                  if (this.scriptOutputWritable() == null) {
                     this.scriptOutputWritable_$eq(this.reusedWritableObject());
                     if (this.scriptOutputReader() != null) {
                        if (this.scriptOutputReader().next(this.scriptOutputWritable()) <= 0) {
                           this.$outer.checkFailureAndPropagate(this.writerThread$1, (Throwable)null, this.proc$1, this.stderrBuffer$1);
                           this.completed_$eq(true);
                           return false;
                        }
                     } else {
                        try {
                           this.scriptOutputWritable().readFields(this.scriptOutputStream());
                        } catch (EOFException var5) {
                           this.$outer.checkFailureAndPropagate(this.writerThread$1, (Throwable)null, this.proc$1, this.stderrBuffer$1);
                           this.completed_$eq(true);
                           return false;
                        }
                     }
                  }

                  return true;
               } catch (Throwable var6) {
                  if (var6 != null && scala.util.control.NonFatal..MODULE$.apply(var6)) {
                     this.$outer.checkFailureAndPropagate(this.writerThread$1, var6, this.proc$1, this.stderrBuffer$1);
                     throw var6;
                  } else {
                     throw var6;
                  }
               }
            }
         }

         public InternalRow next() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               Object raw = this.outputSerde$1.deserialize(this.scriptOutputWritable());
               this.scriptOutputWritable_$eq((Writable)null);
               java.util.List dataList = this.outputSoi$1.getStructFieldsDataAsList(raw);

               for(int i = 0; i < dataList.size(); ++i) {
                  if (dataList.get(i) == null) {
                     this.mutableRow().setNullAt(i);
                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  } else {
                     ((Function3)this.unwrappers().apply(i)).apply(dataList.get(i), this.mutableRow(), BoxesRunTime.boxToInteger(i));
                  }
               }

               return this.mutableRow();
            }
         }

         public {
            if (HiveScriptTransformationExec.this == null) {
               throw null;
            } else {
               this.$outer = HiveScriptTransformationExec.this;
               this.outputSerde$1 = outputSerde$1;
               this.outputSoi$1 = outputSoi$1;
               this.writerThread$1 = writerThread$1;
               this.proc$1 = proc$1;
               this.stderrBuffer$1 = stderrBuffer$1;
               IterableOnce.$init$(this);
               IterableOnceOps.$init$(this);
               Iterator.$init$(this);
               HiveInspectors.$init$(this);
               this.completed = false;
               this.scriptOutputStream = new DataInputStream(inputStream$1);
               this.scriptOutputReader = (RecordReader)HiveScriptIOSchema$.MODULE$.recordReader(HiveScriptTransformationExec.this.ioschema(), this.scriptOutputStream(), hadoopConf$1).orNull(scala..less.colon.less..MODULE$.refl());
               this.scriptOutputWritable = null;
               this.reusedWritableObject = (Writable)outputSerde$1.getSerializedClass().getConstructor().newInstance();
               this.mutableRow = new SpecificInternalRow((Seq)HiveScriptTransformationExec.this.output().map((x$1) -> x$1.dataType()));
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public Iterator processIterator(final Iterator inputIterator, final Configuration hadoopConf) {
      Tuple4 var7 = this.initProc();
      if (var7 != null) {
         OutputStream outputStream = (OutputStream)var7._1();
         Process proc = (Process)var7._2();
         InputStream inputStream = (InputStream)var7._3();
         CircularBuffer stderrBuffer = (CircularBuffer)var7._4();
         Tuple4 var6 = new Tuple4(outputStream, proc, inputStream, stderrBuffer);
         OutputStream outputStream = (OutputStream)var6._1();
         Process proc = (Process)var6._2();
         InputStream inputStream = (InputStream)var6._3();
         CircularBuffer stderrBuffer = (CircularBuffer)var6._4();
         Tuple2 var17 = (Tuple2)HiveScriptIOSchema$.MODULE$.initInputSerDe(this.ioschema(), this.child().output()).getOrElse(() -> new Tuple2((Object)null, (Object)null));
         if (var17 != null) {
            AbstractSerDe inputSerde = (AbstractSerDe)var17._1();
            StructObjectInspector inputSoi = (StructObjectInspector)var17._2();
            Tuple2 var16 = new Tuple2(inputSerde, inputSoi);
            AbstractSerDe inputSerde = (AbstractSerDe)var16._1();
            StructObjectInspector inputSoi = (StructObjectInspector)var16._2();
            Seq finalInput = inputSerde == null ? this.inputExpressionsWithoutSerde() : this.child().output();
            InterpretedProjection outputProjection = new InterpretedProjection(finalInput, this.child().output());
            HiveScriptTransformationWriterThread writerThread = new HiveScriptTransformationWriterThread(inputIterator.map(outputProjection), (Seq)finalInput.map((x$4) -> x$4.dataType()), inputSerde, inputSoi, this.ioschema(), outputStream, proc, stderrBuffer, org.apache.spark.TaskContext..MODULE$.get(), hadoopConf);
            Tuple2 var26 = (Tuple2)HiveScriptIOSchema$.MODULE$.initOutputSerDe(this.ioschema(), this.output()).getOrElse(() -> new Tuple2((Object)null, (Object)null));
            if (var26 != null) {
               AbstractSerDe outputSerde = (AbstractSerDe)var26._1();
               StructObjectInspector outputSoi = (StructObjectInspector)var26._2();
               Tuple2 var25 = new Tuple2(outputSerde, outputSoi);
               AbstractSerDe outputSerde = (AbstractSerDe)var25._1();
               StructObjectInspector outputSoi = (StructObjectInspector)var25._2();
               Iterator outputIterator = outputSerde == null ? this.createOutputIteratorWithoutSerde(writerThread, inputStream, proc, stderrBuffer) : this.createOutputIteratorWithSerde(writerThread, inputStream, proc, stderrBuffer, outputSerde, outputSoi, hadoopConf);
               writerThread.start();
               return outputIterator;
            } else {
               throw new MatchError(var26);
            }
         } else {
            throw new MatchError(var17);
         }
      } else {
         throw new MatchError(var7);
      }
   }

   public HiveScriptTransformationExec withNewChildInternal(final SparkPlan newChild) {
      String x$2 = this.copy$default$1();
      Seq x$3 = this.copy$default$2();
      ScriptTransformationIOSchema x$4 = this.copy$default$4();
      return this.copy(x$2, x$3, newChild, x$4);
   }

   public HiveScriptTransformationExec copy(final String script, final Seq output, final SparkPlan child, final ScriptTransformationIOSchema ioschema) {
      return new HiveScriptTransformationExec(script, output, child, ioschema);
   }

   public String copy$default$1() {
      return this.script();
   }

   public Seq copy$default$2() {
      return this.output();
   }

   public SparkPlan copy$default$3() {
      return this.child();
   }

   public ScriptTransformationIOSchema copy$default$4() {
      return this.ioschema();
   }

   public String productPrefix() {
      return "HiveScriptTransformationExec";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.script();
         }
         case 1 -> {
            return this.output();
         }
         case 2 -> {
            return this.child();
         }
         case 3 -> {
            return this.ioschema();
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
      return x$1 instanceof HiveScriptTransformationExec;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "script";
         }
         case 1 -> {
            return "output";
         }
         case 2 -> {
            return "child";
         }
         case 3 -> {
            return "ioschema";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public boolean equals(final Object x$1) {
      boolean var12;
      if (this != x$1) {
         label71: {
            if (x$1 instanceof HiveScriptTransformationExec) {
               label64: {
                  HiveScriptTransformationExec var4 = (HiveScriptTransformationExec)x$1;
                  String var10000 = this.script();
                  String var5 = var4.script();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label64;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label64;
                  }

                  Seq var9 = this.output();
                  Seq var6 = var4.output();
                  if (var9 == null) {
                     if (var6 != null) {
                        break label64;
                     }
                  } else if (!var9.equals(var6)) {
                     break label64;
                  }

                  SparkPlan var10 = this.child();
                  SparkPlan var7 = var4.child();
                  if (var10 == null) {
                     if (var7 != null) {
                        break label64;
                     }
                  } else if (!var10.equals(var7)) {
                     break label64;
                  }

                  ScriptTransformationIOSchema var11 = this.ioschema();
                  ScriptTransformationIOSchema var8 = var4.ioschema();
                  if (var11 == null) {
                     if (var8 != null) {
                        break label64;
                     }
                  } else if (!var11.equals(var8)) {
                     break label64;
                  }

                  if (var4.canEqual(this)) {
                     break label71;
                  }
               }
            }

            var12 = false;
            return var12;
         }
      }

      var12 = true;
      return var12;
   }

   public HiveScriptTransformationExec(final String script, final Seq output, final SparkPlan child, final ScriptTransformationIOSchema ioschema) {
      this.script = script;
      this.output = output;
      this.child = child;
      this.ioschema = ioschema;
      UnaryLike.$init$(this);
      UnaryExecNode.$init$(this);
      BaseScriptTransformationExec.$init$(this);
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
