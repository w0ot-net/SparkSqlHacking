package org.apache.spark;

import scala.Option;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ua!\u0002\n\u0014\u0001MI\u0002\u0002C\u0017\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0018\t\u0011Y\u0002!\u0011!Q\u0001\n]B\u0001B\u0010\u0001\u0003\u0002\u0003\u0006Ia\u0010\u0005\t\u0001\u0002\u0011\t\u0011)A\u0005\u0003\"AA\t\u0001B\u0001B\u0003%Q\tC\u0003L\u0001\u0011%A\nC\u0003L\u0001\u0011\u00051\u000bC\u0003[\u0001\u0011\u00053\fC\u0003d\u0001\u0011\u0005C\rC\u0003f\u0001\u0011\u0005cm\u0002\u0005h'\u0005\u0005\t\u0012A\ni\r!\u00112#!A\t\u0002MI\u0007\"B&\r\t\u0003\u0019\bb\u0002;\r#\u0003%\t!\u001e\u0005\n\u0003\u0003a\u0011\u0013!C\u0001\u0003\u0007A\u0011\"a\u0002\r#\u0003%\t!!\u0003\t\u0013\u00055A\"!A\u0005\n\u0005=!\u0001F*qCJ\\\u0007+\u001f;i_:,\u0005pY3qi&|gN\u0003\u0002\u0015+\u0005)1\u000f]1sW*\u0011acF\u0001\u0007CB\f7\r[3\u000b\u0003a\t1a\u001c:h'\r\u0001!$\u000b\t\u00037\u0019r!\u0001H\u0012\u000f\u0005u\tS\"\u0001\u0010\u000b\u0005}\u0001\u0013A\u0002\u001fs_>$hh\u0001\u0001\n\u0003\t\nQa]2bY\u0006L!\u0001J\u0013\u0002\u000fA\f7m[1hK*\t!%\u0003\u0002(Q\t\u0001\"+\u001e8uS6,W\t_2faRLwN\u001c\u0006\u0003I\u0015\u0002\"AK\u0016\u000e\u0003MI!\u0001L\n\u0003\u001dM\u0003\u0018M]6UQJ|w/\u00192mK\u00069Q.Z:tC\u001e,\u0007CA\u00184\u001d\t\u0001\u0014\u0007\u0005\u0002\u001eK%\u0011!'J\u0001\u0007!J,G-\u001a4\n\u0005Q*$AB*ue&twM\u0003\u00023K\u0005)1-Y;tKB\u0019\u0001(O\u001e\u000e\u0003\u0015J!AO\u0013\u0003\r=\u0003H/[8o!\tYB(\u0003\u0002>Q\tIA\u000b\u001b:po\u0006\u0014G.Z\u0001\u000bKJ\u0014xN]\"mCN\u001c\bc\u0001\u001d:]\u0005\tR.Z:tC\u001e,\u0007+\u0019:b[\u0016$XM]:\u0011\t=\u0012eFL\u0005\u0003\u0007V\u00121!T1q\u0003\u001d\u0019wN\u001c;fqR\u00042\u0001\u000f$I\u0013\t9UEA\u0003BeJ\f\u0017\u0010\u0005\u0002+\u0013&\u0011!j\u0005\u0002\r#V,'/_\"p]R,\u0007\u0010^\u0001\u0007y%t\u0017\u000e\u001e \u0015\r5su\nU)S!\tQ\u0003\u0001C\u0003.\r\u0001\u0007a\u0006C\u00037\r\u0001\u0007q\u0007C\u0003?\r\u0001\u0007q\bC\u0003A\r\u0001\u0007\u0011\tC\u0003E\r\u0001\u0007Q\t\u0006\u0004N)V3v\u000b\u0017\u0005\u0006}\u001d\u0001\rA\f\u0005\u0006\u0001\u001e\u0001\r!\u0011\u0005\bm\u001d\u0001\n\u00111\u0001<\u0011\u001d!u\u0001%AA\u0002\u0015Cq!W\u0004\u0011\u0002\u0003\u0007a&A\u0004tk6l\u0017M]=\u0002)\u001d,G/T3tg\u0006<W\rU1sC6,G/\u001a:t)\u0005a\u0006\u0003B/c]9j\u0011A\u0018\u0006\u0003?\u0002\fA!\u001e;jY*\t\u0011-\u0001\u0003kCZ\f\u0017BA\"_\u000319W\r^\"p]\u0012LG/[8o)\u0005q\u0013aD4fiF+XM]=D_:$X\r\u001f;\u0015\u0003\u0015\u000bAc\u00159be.\u0004\u0016\u0010\u001e5p]\u0016C8-\u001a9uS>t\u0007C\u0001\u0016\r'\ra!.\u001c\t\u0003q-L!\u0001\\\u0013\u0003\r\u0005s\u0017PU3g!\tq\u0017/D\u0001p\u0015\t\u0001\b-\u0001\u0002j_&\u0011!o\u001c\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0002Q\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIM*\u0012A\u001e\u0016\u0003w]\\\u0013\u0001\u001f\t\u0003szl\u0011A\u001f\u0006\u0003wr\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005u,\u0013AC1o]>$\u0018\r^5p]&\u0011qP\u001f\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$C'\u0006\u0002\u0002\u0006)\u0012Qi^\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001b\u0016\u0005\u0005-!F\u0001\u0018x\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\t\u0002\u0005\u0003\u0002\u0014\u0005eQBAA\u000b\u0015\r\t9\u0002Y\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\u001c\u0005U!AB(cU\u0016\u001cG\u000f"
)
public class SparkPythonException extends RuntimeException implements SparkThrowable {
   private final Option errorClass;
   private final Map messageParameters;
   private final QueryContext[] context;

   public static String $lessinit$greater$default$5() {
      return SparkPythonException$.MODULE$.$lessinit$greater$default$5();
   }

   public static QueryContext[] $lessinit$greater$default$4() {
      return SparkPythonException$.MODULE$.$lessinit$greater$default$4();
   }

   public static Throwable $lessinit$greater$default$3() {
      return SparkPythonException$.MODULE$.$lessinit$greater$default$3();
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

   private SparkPythonException(final String message, final Option cause, final Option errorClass, final Map messageParameters, final QueryContext[] context) {
      super(message, (Throwable)cause.orNull(scala..less.colon.less..MODULE$.refl()));
      this.errorClass = errorClass;
      this.messageParameters = messageParameters;
      this.context = context;
   }

   public SparkPythonException(final String errorClass, final Map messageParameters, final Throwable cause, final QueryContext[] context, final String summary) {
      this(SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters, summary), scala.Option..MODULE$.apply(cause), scala.Option..MODULE$.apply(errorClass), messageParameters, context);
   }
}
