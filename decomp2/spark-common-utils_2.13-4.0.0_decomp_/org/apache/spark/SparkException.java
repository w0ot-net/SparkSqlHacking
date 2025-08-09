package org.apache.spark;

import scala.Function0;
import scala.Option;
import scala.Some;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%e\u0001B\u000e\u001d\u0001\rB\u0001B\u000e\u0001\u0003\u0002\u0003\u0006Ia\u000e\u0005\t\u007f\u0001\u0011\t\u0011)A\u0005\u0001\"A1\t\u0001B\u0001B\u0003%A\t\u0003\u0005I\u0001\t\u0005\t\u0015!\u0003J\u0011!a\u0005A!A!\u0002\u0013i\u0005\"B*\u0001\t\u0003!\u0006\"B*\u0001\t\u0003Y\u0006\"B*\u0001\t\u0003q\u0006\"B*\u0001\t\u0003\u0001\u0007\"B*\u0001\t\u00039\u0007\"B*\u0001\t\u0003Y\u0007\"\u00029\u0001\t\u0003\n\b\"B=\u0001\t\u0003R\b\"B>\u0001\t\u0003bx!B?\u001d\u0011\u0003qh!B\u000e\u001d\u0011\u0003y\bBB*\u0011\t\u0003\t\u0019\u0002C\u0004\u0002\u0016A!\t!a\u0006\t\u000f\u0005U\u0001\u0003\"\u0001\u0002\"!9\u0011Q\u0003\t\u0005\u0002\u00055\u0002bBA\u000b!\u0011\u0005\u0011\u0011\u0007\u0005\b\u0003+\u0001B\u0011AA\u001c\u0011\u001d\ti\u0004\u0005C\u0001\u0003\u007fAq!a\u0017\u0011\t\u0003\ti\u0006C\u0005\u0002bA\t\n\u0011\"\u0001\u0002d!I\u0011\u0011\u0010\t\u0002\u0002\u0013%\u00111\u0010\u0002\u000f'B\f'o[#yG\u0016\u0004H/[8o\u0015\tib$A\u0003ta\u0006\u00148N\u0003\u0002 A\u00051\u0011\r]1dQ\u0016T\u0011!I\u0001\u0004_J<7\u0001A\n\u0004\u0001\u0011\u0012\u0004CA\u00130\u001d\t1CF\u0004\u0002(U5\t\u0001F\u0003\u0002*E\u00051AH]8pizJ\u0011aK\u0001\u0006g\u000e\fG.Y\u0005\u0003[9\nq\u0001]1dW\u0006<WMC\u0001,\u0013\t\u0001\u0014GA\u0005Fq\u000e,\u0007\u000f^5p]*\u0011QF\f\t\u0003gQj\u0011\u0001H\u0005\u0003kq\u0011ab\u00159be.$\u0006N]8xC\ndW-A\u0004nKN\u001c\u0018mZ3\u0011\u0005abdBA\u001d;!\t9c&\u0003\u0002<]\u00051\u0001K]3eK\u001aL!!\u0010 \u0003\rM#(/\u001b8h\u0015\tYd&A\u0003dCV\u001cX\r\u0005\u0002&\u0003&\u0011!)\r\u0002\n)\"\u0014xn^1cY\u0016\f!\"\u001a:s_J\u001cE.Y:t!\r)eiN\u0007\u0002]%\u0011qI\f\u0002\u0007\u001fB$\u0018n\u001c8\u0002#5,7o]1hKB\u000b'/Y7fi\u0016\u00148\u000f\u0005\u00039\u0015^:\u0014BA&?\u0005\ri\u0015\r]\u0001\bG>tG/\u001a=u!\r)e\nU\u0005\u0003\u001f:\u0012Q!\u0011:sCf\u0004\"aM)\n\u0005Ic\"\u0001D)vKJL8i\u001c8uKb$\u0018A\u0002\u001fj]&$h\b\u0006\u0004V-^C\u0016L\u0017\t\u0003g\u0001AQA\u000e\u0004A\u0002]BQa\u0010\u0004A\u0002\u0001CQa\u0011\u0004A\u0002\u0011CQ\u0001\u0013\u0004A\u0002%Cq\u0001\u0014\u0004\u0011\u0002\u0003\u0007Q\nF\u0002V9vCQAN\u0004A\u0002]BQaP\u0004A\u0002\u0001#\"!V0\t\u000bYB\u0001\u0019A\u001c\u0015\rU\u000b'm\u00193f\u0011\u0015\u0019\u0015\u00021\u00018\u0011\u0015A\u0015\u00021\u0001J\u0011\u0015y\u0014\u00021\u0001A\u0011\u0015a\u0015\u00021\u0001N\u0011\u00151\u0017\u00021\u00018\u0003\u001d\u0019X/\\7bef$B!\u00165jU\")1I\u0003a\u0001o!)\u0001J\u0003a\u0001\u0013\")qH\u0003a\u0001\u0001R)Q\u000b\\7o_\")1i\u0003a\u0001o!)\u0001j\u0003a\u0001\u0013\")qh\u0003a\u0001\u0001\")Aj\u0003a\u0001\u001b\u0006!r-\u001a;NKN\u001c\u0018mZ3QCJ\fW.\u001a;feN$\u0012A\u001d\t\u0005gb<t'D\u0001u\u0015\t)h/\u0001\u0003vi&d'\"A<\u0002\t)\fg/Y\u0005\u0003\u0017R\fAbZ3u\u0007>tG-\u001b;j_:$\u0012aN\u0001\u0010O\u0016$\u0018+^3ss\u000e{g\u000e^3yiR\tQ*\u0001\bTa\u0006\u00148.\u0012=dKB$\u0018n\u001c8\u0011\u0005M\u00022#\u0002\t\u0002\u0002\u0005\u001d\u0001cA#\u0002\u0004%\u0019\u0011Q\u0001\u0018\u0003\r\u0005s\u0017PU3g!\u0011\tI!a\u0004\u000e\u0005\u0005-!bAA\u0007m\u0006\u0011\u0011n\\\u0005\u0005\u0003#\tYA\u0001\u0007TKJL\u0017\r\\5{C\ndW\rF\u0001\u007f\u00035Ig\u000e^3s]\u0006dWI\u001d:peR9Q+!\u0007\u0002\u001e\u0005}\u0001BBA\u000e%\u0001\u0007q'A\u0002ng\u001eDQ\u0001\u0014\nA\u00025CQA\u001a\nA\u0002]\"\u0012\"VA\u0012\u0003K\t9#!\u000b\t\r\u0005m1\u00031\u00018\u0011\u0015a5\u00031\u0001N\u0011\u001517\u00031\u00018\u0011\u0019\tYc\u0005a\u0001\t\u0006A1-\u0019;fO>\u0014\u0018\u0010F\u0002V\u0003_Aa!a\u0007\u0015\u0001\u00049D#B+\u00024\u0005U\u0002BBA\u000e+\u0001\u0007q\u0007\u0003\u0004\u0002,U\u0001\ra\u000e\u000b\u0006+\u0006e\u00121\b\u0005\u0007\u000371\u0002\u0019A\u001c\t\u000b}2\u0002\u0019\u0001!\u0002\u000fI,\u0017/^5sKRA\u0011\u0011IA$\u0003#\n\u0019\u0006E\u0002F\u0003\u0007J1!!\u0012/\u0005\u0011)f.\u001b;\t\u000f\u0005%s\u00031\u0001\u0002L\u0005Y!/Z9vSJ,W.\u001a8u!\r)\u0015QJ\u0005\u0004\u0003\u001fr#a\u0002\"p_2,\u0017M\u001c\u0005\u0006\u0007^\u0001\ra\u000e\u0005\b\u0011^!\t\u0019AA+!\u0011)\u0015qK%\n\u0007\u0005ecF\u0001\u0005=Eft\u0017-\\3?\u0003Y\u0019wN\\:ueV\u001cG/T3tg\u0006<W\rU1sC6\u001cHcA%\u0002`!)\u0001\n\u0007a\u0001e\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIU*\"!!\u001a+\u00075\u000b9g\u000b\u0002\u0002jA!\u00111NA;\u001b\t\tiG\u0003\u0003\u0002p\u0005E\u0014!C;oG\",7m[3e\u0015\r\t\u0019HL\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA<\u0003[\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ti\b\u0005\u0003\u0002\u0000\u0005\u0015UBAAA\u0015\r\t\u0019I^\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\b\u0006\u0005%AB(cU\u0016\u001cG\u000f"
)
public class SparkException extends Exception implements SparkThrowable {
   private final Option errorClass;
   private final Map messageParameters;
   private final QueryContext[] context;

   public static QueryContext[] $lessinit$greater$default$5() {
      return SparkException$.MODULE$.$lessinit$greater$default$5();
   }

   public static Map constructMessageParams(final java.util.Map messageParameters) {
      return SparkException$.MODULE$.constructMessageParams(messageParameters);
   }

   public static void require(final boolean requirement, final String errorClass, final Function0 messageParameters) {
      SparkException$.MODULE$.require(requirement, errorClass, messageParameters);
   }

   public static SparkException internalError(final String msg, final Throwable cause) {
      return SparkException$.MODULE$.internalError(msg, cause);
   }

   public static SparkException internalError(final String msg, final String category) {
      return SparkException$.MODULE$.internalError(msg, category);
   }

   public static SparkException internalError(final String msg) {
      return SparkException$.MODULE$.internalError(msg);
   }

   public static SparkException internalError(final String msg, final QueryContext[] context, final String summary, final Option category) {
      return SparkException$.MODULE$.internalError(msg, context, summary, category);
   }

   public static SparkException internalError(final String msg, final QueryContext[] context, final String summary) {
      return SparkException$.MODULE$.internalError(msg, context, summary);
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

   public SparkException(final String message, final Throwable cause, final Option errorClass, final Map messageParameters, final QueryContext[] context) {
      super(message, cause);
      this.errorClass = errorClass;
      this.messageParameters = messageParameters;
      this.context = context;
   }

   public SparkException(final String message, final Throwable cause) {
      this(message, (Throwable)cause, (Option)scala.None..MODULE$, (Map)scala.Predef..MODULE$.Map().empty(), (QueryContext[])SparkException$.MODULE$.$lessinit$greater$default$5());
   }

   public SparkException(final String message) {
      this(message, (Throwable)null);
   }

   public SparkException(final String errorClass, final Map messageParameters, final Throwable cause, final QueryContext[] context, final String summary) {
      this(SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters, summary), (Throwable)cause, (Option)(new Some(errorClass)), (Map)messageParameters, (QueryContext[])context);
   }

   public SparkException(final String errorClass, final Map messageParameters, final Throwable cause) {
      this(SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters), (Throwable)cause, (Option)(new Some(errorClass)), (Map)messageParameters, (QueryContext[])SparkException$.MODULE$.$lessinit$greater$default$5());
   }

   public SparkException(final String errorClass, final Map messageParameters, final Throwable cause, final QueryContext[] context) {
      this(SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters), (Throwable)cause, (Option)(new Some(errorClass)), (Map)messageParameters, (QueryContext[])context);
   }
}
