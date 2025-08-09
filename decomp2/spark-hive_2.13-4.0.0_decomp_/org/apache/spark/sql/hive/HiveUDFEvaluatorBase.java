package org.apache.spark.sql.hive;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Type;
import org.apache.hadoop.hive.ql.udf.UDFType;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.errors.QueryExecutionErrors.;
import org.apache.spark.sql.types.DataType;
import scala.Function1;
import scala.Function3;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}4QAC\u0006\u0002\u0002YA\u0001B\f\u0001\u0003\u0002\u0003\u0006Ia\f\u0005\t\u0001\u0002\u0011\t\u0011)A\u0005\u0003\")A\n\u0001C\u0001\u001b\"A\u0011\f\u0001EC\u0002\u0013\u0005!\f\u0003\u0005`\u0001!\u0015\r\u0011\"\u0001a\u0011\u0015)\u0007A\"\u0001g\u0011\u0015i\u0007A\"\u0001o\u0011\u0015a\bA\"\u0001~\u0011\u0015q\b\u0001\"\u0002~\u0005QA\u0015N^3V\t\u001a+e/\u00197vCR|'OQ1tK*\u0011A\"D\u0001\u0005Q&4XM\u0003\u0002\u000f\u001f\u0005\u00191/\u001d7\u000b\u0005A\t\u0012!B:qCJ\\'B\u0001\n\u0014\u0003\u0019\t\u0007/Y2iK*\tA#A\u0002pe\u001e\u001c\u0001!\u0006\u0002\u0018#N!\u0001\u0001\u0007\u0010#!\tIB$D\u0001\u001b\u0015\u0005Y\u0012!B:dC2\f\u0017BA\u000f\u001b\u0005\u0019\te.\u001f*fMB\u0011q\u0004I\u0007\u0002\u0017%\u0011\u0011e\u0003\u0002\u000f\u0011&4X-\u00138ta\u0016\u001cGo\u001c:t!\t\u00193F\u0004\u0002%S9\u0011Q\u0005K\u0007\u0002M)\u0011q%F\u0001\u0007yI|w\u000e\u001e \n\u0003mI!A\u000b\u000e\u0002\u000fA\f7m[1hK&\u0011A&\f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003Ui\t1BZ;oG^\u0013\u0018\r\u001d9feB\u0011\u0001'\u0010\b\u0003cmr!A\r\u001e\u000f\u0005MJdB\u0001\u001b9\u001d\t)tG\u0004\u0002&m%\tA#\u0003\u0002\u0013'%\u0011\u0001#E\u0005\u0003\u001d=I!\u0001D\u0007\n\u0005qZ\u0011\u0001\u0003%jm\u0016\u001c\u0006.[7\n\u0005yz$a\u0005%jm\u00164UO\\2uS>twK]1qa\u0016\u0014(B\u0001\u001f\f\u0003!\u0019\u0007.\u001b7ee\u0016t\u0007cA\u0012C\t&\u00111)\f\u0002\u0004'\u0016\f\bCA#K\u001b\u00051%BA$I\u0003-)\u0007\u0010\u001d:fgNLwN\\:\u000b\u0005%k\u0011\u0001C2bi\u0006d\u0017p\u001d;\n\u0005-3%AC#yaJ,7o]5p]\u00061A(\u001b8jiz\"2AT,Y!\ry\u0002a\u0014\t\u0003!Fc\u0001\u0001B\u0003S\u0001\t\u00071KA\u0004V\t\u001a#\u0016\u0010]3\u0012\u0005QC\u0002CA\rV\u0013\t1&DA\u0004O_RD\u0017N\\4\t\u000b9\u001a\u0001\u0019A\u0018\t\u000b\u0001\u001b\u0001\u0019A!\u0002\u0011\u0019,hn\u0019;j_:,\u0012a\u0014\u0015\u0003\tq\u0003\"!G/\n\u0005yS\"!\u0003;sC:\u001c\u0018.\u001a8u\u0003II7/\u0016#G\t\u0016$XM]7j]&\u001cH/[2\u0016\u0003\u0005\u0004\"!\u00072\n\u0005\rT\"a\u0002\"p_2,\u0017M\u001c\u0015\u0003\u000bq\u000b!B]3ukJtG+\u001f9f+\u00059\u0007C\u00015l\u001b\u0005I'B\u00016\u000e\u0003\u0015!\u0018\u0010]3t\u0013\ta\u0017N\u0001\u0005ECR\fG+\u001f9f\u0003\u0019\u0019X\r^!sOR\u0019qN]<\u0011\u0005e\u0001\u0018BA9\u001b\u0005\u0011)f.\u001b;\t\u000bM<\u0001\u0019\u0001;\u0002\u000b%tG-\u001a=\u0011\u0005e)\u0018B\u0001<\u001b\u0005\rIe\u000e\u001e\u0005\u0006q\u001e\u0001\r!_\u0001\u0004CJ<\u0007CA\r{\u0013\tY(DA\u0002B]f\f!\u0002Z8Fm\u0006dW/\u0019;f)\u0005I\u0018\u0001C3wC2,\u0018\r^3"
)
public abstract class HiveUDFEvaluatorBase implements HiveInspectors, Serializable {
   private transient Object function;
   private transient boolean isUDFDeterministic;
   private final HiveShim.HiveFunctionWrapper funcWrapper;
   private final Seq children;
   private transient volatile byte bitmap$trans$0;

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

   private Object function$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 1) == 0) {
            this.function = this.funcWrapper.createFunction();
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.function;
   }

   public Object function() {
      return (byte)(this.bitmap$trans$0 & 1) == 0 ? this.function$lzycompute() : this.function;
   }

   private boolean isUDFDeterministic$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 2) == 0) {
            UDFType udfType = (UDFType)this.function().getClass().getAnnotation(UDFType.class);
            this.isUDFDeterministic = udfType != null && udfType.deterministic() && !udfType.stateful();
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 2);
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.isUDFDeterministic;
   }

   public boolean isUDFDeterministic() {
      return (byte)(this.bitmap$trans$0 & 2) == 0 ? this.isUDFDeterministic$lzycompute() : this.isUDFDeterministic;
   }

   public abstract DataType returnType();

   public abstract void setArg(final int index, final Object arg);

   public abstract Object doEvaluate();

   public final Object evaluate() {
      try {
         return this.doEvaluate();
      } catch (Throwable var2) {
         throw .MODULE$.failedExecuteUserDefinedFunctionError(String.valueOf(this.funcWrapper.functionClassName()), String.valueOf(((IterableOnceOps)this.children.map((x$1) -> x$1.dataType().catalogString())).mkString(", ")), String.valueOf(this.returnType().catalogString()), var2);
      }
   }

   public HiveUDFEvaluatorBase(final HiveShim.HiveFunctionWrapper funcWrapper, final Seq children) {
      this.funcWrapper = funcWrapper;
      this.children = children;
      HiveInspectors.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
