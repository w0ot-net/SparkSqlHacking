package org.apache.spark.sql.hive;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import org.apache.hadoop.hive.ql.exec.DefaultUDFMethodResolver;
import org.apache.hadoop.hive.ql.exec.HiveFunctionRegistryUtils;
import org.apache.hadoop.hive.ql.exec.SparkDefaultUDFMethodResolver;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.exec.UDFMethodResolver;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDFUtils;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory.ObjectInspectorOptions;
import org.apache.spark.sql.types.DataType;
import scala.Function1;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ed\u0001B\u0007\u000f\u0001eA\u0001\"\u000b\u0001\u0003\u0002\u0003\u0006IA\u000b\u0005\t}\u0001\u0011\t\u0011)A\u0005\u007f!)!\u000b\u0001C\u0001'\"Aq\u000b\u0001EC\u0002\u0013\u0005\u0001\f\u0003\u0005i\u0001!\u0015\r\u0011\"\u0003j\u0011!!\b\u0001#b\u0001\n\u0013)\bBCA\u0001\u0001!\u0015\r\u0011\"\u0003\u0002\u0004!Q\u0011\u0011\u0007\u0001\t\u0006\u0004%I!a\r\t\u000f\u0005}\u0002\u0001\"\u0011\u0002B!9\u0011q\n\u0001\u0005B\u0005E\u0003BCA4\u0001!\u0015\r\u0011\"\u0003\u0002j!9\u0011Q\u000e\u0001\u0005B\u0005=$A\u0006%jm\u0016\u001c\u0016.\u001c9mKV#e)\u0012<bYV\fGo\u001c:\u000b\u0005=\u0001\u0012\u0001\u00025jm\u0016T!!\u0005\n\u0002\u0007M\fHN\u0003\u0002\u0014)\u0005)1\u000f]1sW*\u0011QCF\u0001\u0007CB\f7\r[3\u000b\u0003]\t1a\u001c:h\u0007\u0001\u0019\"\u0001\u0001\u000e\u0011\u0007mab$D\u0001\u000f\u0013\tibB\u0001\u000bISZ,W\u000b\u0012$Fm\u0006dW/\u0019;pe\n\u000b7/\u001a\t\u0003?\u001dj\u0011\u0001\t\u0006\u0003C\t\nA!\u001a=fG*\u00111\u0005J\u0001\u0003c2T!aD\u0013\u000b\u0005\u0019\"\u0012A\u00025bI>|\u0007/\u0003\u0002)A\t\u0019Q\u000b\u0012$\u0002\u0017\u0019,hnY,sCB\u0004XM\u001d\t\u0003Wmr!\u0001L\u001d\u000f\u00055BdB\u0001\u00188\u001d\tycG\u0004\u00021k9\u0011\u0011\u0007N\u0007\u0002e)\u00111\u0007G\u0001\u0007yI|w\u000e\u001e \n\u0003]I!!\u0006\f\n\u0005M!\u0012BA\t\u0013\u0013\ty\u0001#\u0003\u0002;\u001d\u0005A\u0001*\u001b<f'\"LW.\u0003\u0002={\t\u0019\u0002*\u001b<f\rVt7\r^5p]^\u0013\u0018\r\u001d9fe*\u0011!HD\u0001\tG\"LG\u000e\u001a:f]B\u0019\u0001i\u0012&\u000f\u0005\u0005#eBA\u0019C\u0013\u0005\u0019\u0015!B:dC2\f\u0017BA#G\u0003\u001d\u0001\u0018mY6bO\u0016T\u0011aQ\u0005\u0003\u0011&\u00131aU3r\u0015\t)e\t\u0005\u0002L!6\tAJ\u0003\u0002N\u001d\u0006YQ\r\u001f9sKN\u001c\u0018n\u001c8t\u0015\ty\u0005#\u0001\u0005dCR\fG._:u\u0013\t\tFJ\u0001\u0006FqB\u0014Xm]:j_:\fa\u0001P5oSRtDc\u0001+V-B\u00111\u0004\u0001\u0005\u0006S\r\u0001\rA\u000b\u0005\u0006}\r\u0001\raP\u0001\u0007[\u0016$\bn\u001c3\u0016\u0003e\u0003\"AW1\u000e\u0003mS!\u0001X/\u0002\u000fI,g\r\\3di*\u0011alX\u0001\u0005Y\u0006twMC\u0001a\u0003\u0011Q\u0017M^1\n\u0005\t\\&AB'fi\"|G\r\u000b\u0002\u0005IB\u0011QMZ\u0007\u0002\r&\u0011qM\u0012\u0002\niJ\fgn]5f]R\f\u0001b\u001e:baB,'o]\u000b\u0002UB\u0019Qm[7\n\u000514%!B!se\u0006L\b\u0003B3oaBL!a\u001c$\u0003\u0013\u0019+hn\u0019;j_:\f\u0004CA3r\u0013\t\u0011hIA\u0002B]fD#!\u00023\u0002\u0013\u0005\u0014x-^7f]R\u001cX#\u0001<\u0011\u0007\u0015\\w\u000f\u0005\u0002y{6\t\u0011P\u0003\u0002{w\u0006yqN\u00196fGRLgn\u001d9fGR|'O\u0003\u0002}I\u000511/\u001a:eKJJ!A`=\u0003\u001f=\u0013'.Z2u\u0013:\u001c\b/Z2u_JD#A\u00023\u0002!\r|gN^3sg&|g\u000eS3ma\u0016\u0014XCAA\u0003!\u0011\t9!!\u000b\u000f\t\u0005%\u00111\u0005\b\u0005\u0003\u0017\tiB\u0004\u0003\u0002\u000e\u0005ea\u0002BA\b\u0003/qA!!\u0005\u0002\u00169\u0019q&a\u0005\n\u0005\u0019\"\u0012BA\b&\u0013\t\u0019C%C\u0002\u0002\u001c\t\n1!\u001e3g\u0013\u0011\ty\"!\t\u0002\u000f\u001d,g.\u001a:jG*\u0019\u00111\u0004\u0012\n\t\u0005\u0015\u0012qE\u0001\u0010\u000f\u0016tWM]5d+\u00123U\u000b^5mg*!\u0011qDA\u0011\u0013\u0011\tY#!\f\u0003!\r{gN^3sg&|g\u000eS3ma\u0016\u0014(\u0002BA\u0013\u0003OA#a\u00023\u0002\r%t\u0007/\u001e;t+\t\t)\u0004\u0005\u0003fW\u0006]\u0002cA3\u0002:%\u0019\u00111\b$\u0003\r\u0005s\u0017PU3gQ\tAA-\u0001\u0006sKR,(O\u001c+za\u0016,\"!a\u0011\u0011\t\u0005\u0015\u00131J\u0007\u0003\u0003\u000fR1!!\u0013\u0011\u0003\u0015!\u0018\u0010]3t\u0013\u0011\ti%a\u0012\u0003\u0011\u0011\u000bG/\u0019+za\u0016\faa]3u\u0003J<GCBA*\u00033\n\u0019\u0007E\u0002f\u0003+J1!a\u0016G\u0005\u0011)f.\u001b;\t\u000f\u0005m#\u00021\u0001\u0002^\u0005)\u0011N\u001c3fqB\u0019Q-a\u0018\n\u0007\u0005\u0005dIA\u0002J]RDa!!\u001a\u000b\u0001\u0004\u0001\u0018aA1sO\u0006IQO\\<sCB\u0004XM]\u000b\u0002[\"\u00121\u0002Z\u0001\u000bI>,e/\u00197vCR,G#\u00019"
)
public class HiveSimpleUDFEvaluator extends HiveUDFEvaluatorBase {
   private transient Method method;
   private transient Function1[] wrappers;
   private transient ObjectInspector[] arguments;
   private transient GenericUDFUtils.ConversionHelper conversionHelper;
   private transient Object[] inputs;
   private transient Function1 unwrapper;
   private final Seq children;
   private transient volatile byte bitmap$trans$0;

   private Method method$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 1) == 0) {
            UDFMethodResolver var3 = ((UDF)this.function()).getResolver();
            Object var10001;
            if (var3 instanceof DefaultUDFMethodResolver) {
               DefaultUDFMethodResolver var4 = (DefaultUDFMethodResolver)var3;
               var10001 = new SparkDefaultUDFMethodResolver(var4);
            } else {
               var10001 = var3;
            }

            this.method = ((UDFMethodResolver)var10001).getEvalMethod(.MODULE$.SeqHasAsJava((scala.collection.Seq)this.children.map((x$2) -> this.typeInfoConversions(x$2.dataType()).toTypeInfo())).asJava());
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 1);
         }
      } catch (Throwable var6) {
         throw var6;
      }

      return this.method;
   }

   public Method method() {
      return (byte)(this.bitmap$trans$0 & 1) == 0 ? this.method$lzycompute() : this.method;
   }

   private Function1[] wrappers$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 2) == 0) {
            this.wrappers = (Function1[])((IterableOnceOps)this.children.map((x) -> this.wrapperFor(this.toInspector(x), x.dataType()))).toArray(scala.reflect.ClassTag..MODULE$.apply(Function1.class));
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.wrappers;
   }

   private Function1[] wrappers() {
      return (byte)(this.bitmap$trans$0 & 2) == 0 ? this.wrappers$lzycompute() : this.wrappers;
   }

   private ObjectInspector[] arguments$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 4) == 0) {
            this.arguments = (ObjectInspector[])((IterableOnceOps)this.children.map((expr) -> this.toInspector(expr))).toArray(scala.reflect.ClassTag..MODULE$.apply(ObjectInspector.class));
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.arguments;
   }

   private ObjectInspector[] arguments() {
      return (byte)(this.bitmap$trans$0 & 4) == 0 ? this.arguments$lzycompute() : this.arguments;
   }

   private GenericUDFUtils.ConversionHelper conversionHelper$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 8) == 0) {
            this.conversionHelper = new GenericUDFUtils.ConversionHelper(this.method(), this.arguments());
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.conversionHelper;
   }

   private GenericUDFUtils.ConversionHelper conversionHelper() {
      return (byte)(this.bitmap$trans$0 & 8) == 0 ? this.conversionHelper$lzycompute() : this.conversionHelper;
   }

   private Object[] inputs$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 16) == 0) {
            this.inputs = new Object[this.children.length()];
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 16);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.inputs;
   }

   private Object[] inputs() {
      return (byte)(this.bitmap$trans$0 & 16) == 0 ? this.inputs$lzycompute() : this.inputs;
   }

   public DataType returnType() {
      return this.javaTypeToDataType(this.method().getGenericReturnType());
   }

   public void setArg(final int index, final Object arg) {
      this.inputs()[index] = this.wrappers()[index].apply(arg);
   }

   private Function1 unwrapper$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 32) == 0) {
            this.unwrapper = this.unwrapperFor(ObjectInspectorFactory.getReflectionObjectInspector(this.method().getGenericReturnType(), ObjectInspectorOptions.JAVA));
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 32);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.unwrapper;
   }

   private Function1 unwrapper() {
      return (byte)(this.bitmap$trans$0 & 32) == 0 ? this.unwrapper$lzycompute() : this.unwrapper;
   }

   public Object doEvaluate() {
      Object ret = HiveFunctionRegistryUtils.invoke(this.method(), this.function(), this.conversionHelper().convertIfNecessary(this.inputs()));
      return this.unwrapper().apply(ret);
   }

   public HiveSimpleUDFEvaluator(final HiveShim.HiveFunctionWrapper funcWrapper, final Seq children) {
      super(funcWrapper, children);
      this.children = children;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
