package org.apache.spark;

import scala.Option;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ua!\u0002\n\u0014\u0001MI\u0002\u0002C\u0017\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0018\t\u0011Y\u0002!\u0011!Q\u0001\n]B\u0001B\u0010\u0001\u0003\u0002\u0003\u0006Ia\u0010\u0005\t\u0001\u0002\u0011\t\u0011)A\u0005\u0003\"AA\t\u0001B\u0001B\u0003%Q\tC\u0003L\u0001\u0011%A\nC\u0003L\u0001\u0011\u00051\u000bC\u0003[\u0001\u0011\u00053\fC\u0003d\u0001\u0011\u0005C\rC\u0003f\u0001\u0011\u0005cm\u0002\u0005h'\u0005\u0005\t\u0012A\ni\r!\u00112#!A\t\u0002MI\u0007\"B&\r\t\u0003\u0019\bb\u0002;\r#\u0003%\t!\u001e\u0005\n\u0003\u0003a\u0011\u0013!C\u0001\u0003\u0007A\u0011\"a\u0002\r#\u0003%\t!!\u0003\t\u0013\u00055A\"!A\u0005\n\u0005=!!F*qCJ\\'+\u001e8uS6,W\t_2faRLwN\u001c\u0006\u0003)U\tQa\u001d9be.T!AF\f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005A\u0012aA8sON\u0019\u0001AG\u0015\u0011\u0005m1cB\u0001\u000f$\u001d\ti\u0012%D\u0001\u001f\u0015\ty\u0002%\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005\u0011\u0013!B:dC2\f\u0017B\u0001\u0013&\u0003\u001d\u0001\u0018mY6bO\u0016T\u0011AI\u0005\u0003O!\u0012\u0001CU;oi&lW-\u0012=dKB$\u0018n\u001c8\u000b\u0005\u0011*\u0003C\u0001\u0016,\u001b\u0005\u0019\u0012B\u0001\u0017\u0014\u00059\u0019\u0006/\u0019:l)\"\u0014xn^1cY\u0016\fq!\\3tg\u0006<W\r\u0005\u00020g9\u0011\u0001'\r\t\u0003;\u0015J!AM\u0013\u0002\rA\u0013X\rZ3g\u0013\t!TG\u0001\u0004TiJLgn\u001a\u0006\u0003e\u0015\nQaY1vg\u0016\u00042\u0001O\u001d<\u001b\u0005)\u0013B\u0001\u001e&\u0005\u0019y\u0005\u000f^5p]B\u00111\u0004P\u0005\u0003{!\u0012\u0011\u0002\u00165s_^\f'\r\\3\u0002\u0015\u0015\u0014(o\u001c:DY\u0006\u001c8\u000fE\u00029s9\n\u0011#\\3tg\u0006<W\rU1sC6,G/\u001a:t!\u0011y#I\f\u0018\n\u0005\r+$aA'ba\u000691m\u001c8uKb$\bc\u0001\u001dG\u0011&\u0011q)\n\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003U%K!AS\n\u0003\u0019E+XM]=D_:$X\r\u001f;\u0002\rqJg.\u001b;?)\u0019iej\u0014)R%B\u0011!\u0006\u0001\u0005\u0006[\u0019\u0001\rA\f\u0005\u0006m\u0019\u0001\ra\u000e\u0005\u0006}\u0019\u0001\ra\u0010\u0005\u0006\u0001\u001a\u0001\r!\u0011\u0005\u0006\t\u001a\u0001\r!\u0012\u000b\u0007\u001bR+fk\u0016-\t\u000by:\u0001\u0019\u0001\u0018\t\u000b\u0001;\u0001\u0019A!\t\u000fY:\u0001\u0013!a\u0001w!9Ai\u0002I\u0001\u0002\u0004)\u0005bB-\b!\u0003\u0005\rAL\u0001\bgVlW.\u0019:z\u0003Q9W\r^'fgN\fw-\u001a)be\u0006lW\r^3sgR\tA\f\u0005\u0003^E:rS\"\u00010\u000b\u0005}\u0003\u0017\u0001B;uS2T\u0011!Y\u0001\u0005U\u00064\u0018-\u0003\u0002D=\u0006aq-\u001a;D_:$\u0017\u000e^5p]R\ta&A\bhKR\fV/\u001a:z\u0007>tG/\u001a=u)\u0005)\u0015!F*qCJ\\'+\u001e8uS6,W\t_2faRLwN\u001c\t\u0003U1\u00192\u0001\u00046n!\tA4.\u0003\u0002mK\t1\u0011I\\=SK\u001a\u0004\"A\\9\u000e\u0003=T!\u0001\u001d1\u0002\u0005%|\u0017B\u0001:p\u00051\u0019VM]5bY&T\u0018M\u00197f)\u0005A\u0017a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$3'F\u0001wU\tYtoK\u0001y!\tIh0D\u0001{\u0015\tYH0A\u0005v]\u000eDWmY6fI*\u0011Q0J\u0001\u000bC:tw\u000e^1uS>t\u0017BA@{\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\u0005\u0015!FA#x\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%kU\u0011\u00111\u0002\u0016\u0003]]\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u0005\u0011\t\u0005M\u0011\u0011D\u0007\u0003\u0003+Q1!a\u0006a\u0003\u0011a\u0017M\\4\n\t\u0005m\u0011Q\u0003\u0002\u0007\u001f\nTWm\u0019;"
)
public class SparkRuntimeException extends RuntimeException implements SparkThrowable {
   private final Option errorClass;
   private final Map messageParameters;
   private final QueryContext[] context;

   public static String $lessinit$greater$default$5() {
      return SparkRuntimeException$.MODULE$.$lessinit$greater$default$5();
   }

   public static QueryContext[] $lessinit$greater$default$4() {
      return SparkRuntimeException$.MODULE$.$lessinit$greater$default$4();
   }

   public static Throwable $lessinit$greater$default$3() {
      return SparkRuntimeException$.MODULE$.$lessinit$greater$default$3();
   }

   /** @deprecated */
   @Deprecated
   public String getErrorClass() {
      return SparkThrowable.super.getErrorClass();
   }

   public String getSqlState() {
      return SparkThrowable.super.getSqlState();
   }

   public boolean isInternalError() {
      return SparkThrowable.super.isInternalError();
   }

   public java.util.Map getMessageParameters() {
      return .MODULE$.MapHasAsJava(this.messageParameters).asJava();
   }

   public String getCondition() {
      return (String)this.errorClass.orNull(scala..less.colon.less..MODULE$.refl());
   }

   public QueryContext[] getQueryContext() {
      return this.context;
   }

   private SparkRuntimeException(final String message, final Option cause, final Option errorClass, final Map messageParameters, final QueryContext[] context) {
      super(message, (Throwable)cause.orNull(scala..less.colon.less..MODULE$.refl()));
      this.errorClass = errorClass;
      this.messageParameters = messageParameters;
      this.context = context;
   }

   public SparkRuntimeException(final String errorClass, final Map messageParameters, final Throwable cause, final QueryContext[] context, final String summary) {
      this(SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters, summary), scala.Option..MODULE$.apply(cause), scala.Option..MODULE$.apply(errorClass), messageParameters, context);
   }
}
