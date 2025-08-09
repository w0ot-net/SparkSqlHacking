package org.apache.spark.sql.hive;

import java.lang.reflect.Type;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.types.DataType;
import scala.Function0;
import scala.Function1;
import scala.Function3;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001da!\u0002\u0007\u000e\u000159\u0002\u0002\u0003$\u0001\u0005\u0003\u0005\u000b\u0011B$\t\u0011=\u0003!\u0011!Q\u0001\nACQA\u0016\u0001\u0005\u0002]Cqa\u0017\u0001C\u0002\u0013%A\f\u0003\u0004g\u0001\u0001\u0006I!\u0018\u0005\nO\u0002\u0001\r\u00111A\u0005\n!D\u0011\u0002\u001c\u0001A\u0002\u0003\u0007I\u0011B7\t\u0013M\u0004\u0001\u0019!A!B\u0013I\u0007\"\u0002;\u0001\t\u0003)\b\"B<\u0001\t\u0003B\b\"\u0002@\u0001\t\u0003z(!\u0006#fM\u0016\u0014(/\u001a3PE*,7\r^!eCB$XM\u001d\u0006\u0003\u001d=\tA\u0001[5wK*\u0011\u0001#E\u0001\u0004gFd'B\u0001\n\u0014\u0003\u0015\u0019\b/\u0019:l\u0015\t!R#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002-\u0005\u0019qN]4\u0014\t\u0001A\u0002E\u0011\t\u00033yi\u0011A\u0007\u0006\u00037q\tA\u0001\\1oO*\tQ$\u0001\u0003kCZ\f\u0017BA\u0010\u001b\u0005\u0019y%M[3diB\u0011\u0011e\u0010\b\u0003Eqr!aI\u001d\u000f\u0005\u00112dBA\u00134\u001d\t1\u0013G\u0004\u0002(_9\u0011\u0001F\f\b\u0003S5j\u0011A\u000b\u0006\u0003W1\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002-%\u0011A#F\u0005\u0003aM\ta\u0001[1e_>\u0004\u0018B\u0001\b3\u0015\t\u00014#\u0003\u00025k\u0005\u0011\u0011\u000f\u001c\u0006\u0003\u001dIJ!a\u000e\u001d\u0002\u0007U$gM\u0003\u00025k%\u0011!hO\u0001\bO\u0016tWM]5d\u0015\t9\u0004(\u0003\u0002>}\u0005Qq)\u001a8fe&\u001cW\u000b\u0012$\u000b\u0005iZ\u0014B\u0001!B\u00059!UMZ3se\u0016$wJ\u00196fGRT!!\u0010 \u0011\u0005\r#U\"A\u0007\n\u0005\u0015k!A\u0004%jm\u0016Len\u001d9fGR|'o]\u0001\u0003_&\u0004\"\u0001S'\u000e\u0003%S!AS&\u0002\u001f=\u0014'.Z2uS:\u001c\b/Z2u_JT!\u0001T\u001b\u0002\rM,'\u000fZ33\u0013\tq\u0015JA\bPE*,7\r^%ogB,7\r^8s\u0003!!\u0017\r^1UsB,\u0007CA)U\u001b\u0005\u0011&BA*\u0010\u0003\u0015!\u0018\u0010]3t\u0013\t)&K\u0001\u0005ECR\fG+\u001f9f\u0003\u0019a\u0014N\\5u}Q\u0019\u0001,\u0017.\u0011\u0005\r\u0003\u0001\"\u0002$\u0004\u0001\u00049\u0005\"B(\u0004\u0001\u0004\u0001\u0016aB<sCB\u0004XM]\u000b\u0002;B!a,Y2d\u001b\u0005y&\"\u00011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\t|&!\u0003$v]\u000e$\u0018n\u001c82!\tqF-\u0003\u0002f?\n\u0019\u0011I\\=\u0002\u0011]\u0014\u0018\r\u001d9fe\u0002\nAAZ;oGV\t\u0011\u000eE\u0002_U\u000eL!a[0\u0003\u0013\u0019+hn\u0019;j_:\u0004\u0014\u0001\u00034v]\u000e|F%Z9\u0015\u00059\f\bC\u00010p\u0013\t\u0001xL\u0001\u0003V]&$\bb\u0002:\b\u0003\u0003\u0005\r![\u0001\u0004q\u0012\n\u0014!\u00024v]\u000e\u0004\u0013aA:fiR\u0011aN\u001e\u0005\u0006O&\u0001\r![\u0001\baJ,\u0007/\u0019:f)\tq\u0017\u0010C\u0003{\u0015\u0001\u000710A\u0001j!\tqF0\u0003\u0002~?\n\u0019\u0011J\u001c;\u0002\u0007\u001d,G\u000f\u0006\u0002\u0002\u0002A\u0019a,a\u0001\n\u0007\u0005\u0015qL\u0001\u0004B]f\u0014VM\u001a"
)
public class DeferredObjectAdapter implements GenericUDF.DeferredObject, HiveInspectors {
   private final Function1 wrapper;
   private Function0 func;

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

   private Function1 wrapper() {
      return this.wrapper;
   }

   private Function0 func() {
      return this.func;
   }

   private void func_$eq(final Function0 x$1) {
      this.func = x$1;
   }

   public void set(final Function0 func) {
      this.func_$eq(func);
   }

   public void prepare(final int i) {
   }

   public Object get() {
      return this.wrapper().apply(this.func().apply());
   }

   public DeferredObjectAdapter(final ObjectInspector oi, final DataType dataType) {
      HiveInspectors.$init$(this);
      this.wrapper = this.wrapperFor(oi, dataType);
   }
}
