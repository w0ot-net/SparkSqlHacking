package org.apache.spark.sql;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.QueryContext;
import org.apache.spark.SparkThrowable;
import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.catalyst.trees.Origin;
import org.apache.spark.sql.catalyst.trees.Origin$;
import org.apache.spark.sql.catalyst.trees.WithOrigin;
import scala.Option;
import scala.Some;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005\tUa\u0001B\u00181\u0001eB\u0001b\u0016\u0001\u0003\u0006\u0004%\t\u0001\u0017\u0005\tC\u0002\u0011\t\u0011)A\u00053\"A!\r\u0001BC\u0002\u0013\u00051\r\u0003\u0005l\u0001\t\u0005\t\u0015!\u0003e\u0011!a\u0007A!b\u0001\n\u0003\u0019\u0007\u0002C7\u0001\u0005\u0003\u0005\u000b\u0011\u00023\t\u00119\u0004!Q1A\u0005\u0002=D\u0001\u0002\u001e\u0001\u0003\u0002\u0003\u0006I\u0001\u001d\u0005\tk\u0002\u0011)\u0019!C\u0001m\"A\u0001\u0010\u0001B\u0001B\u0003%q\u000f\u0003\u0005z\u0001\t\u0015\r\u0011\"\u0001{\u0011!q\bA!A!\u0002\u0013Y\b\"C@\u0001\u0005\u000b\u0007I\u0011AA\u0001\u0011)\ty\u0001\u0001B\u0001B\u0003%\u00111\u0001\u0005\b\u0003#\u0001A\u0011CA\n\u0011\u001d\t\t\u0002\u0001C\u0001\u0003OAq!!\u0005\u0001\t\u0003\ty\u0003C\u0004\u0002\u0012\u0001!\t!!\u000f\t\u000f\u0005E\u0001\u0001\"\u0001\u0002F!9\u0011\u0011\u0003\u0001\u0005\u0002\u0005-\u0003bBA\t\u0001\u0011\u0005\u00111\f\u0005\b\u0003K\u0002A\u0011AA4\u0011%\t9\bAI\u0001\n\u0003\tI\bC\u0005\u0002\u0010\u0002\t\n\u0011\"\u0001\u0002\u0012\"I\u0011Q\u0013\u0001\u0012\u0002\u0013\u0005\u0011\u0011\u0013\u0005\n\u0003/\u0003\u0011\u0013!C\u0001\u00033C\u0011\"!(\u0001#\u0003%\t!a(\t\u0013\u0005\r\u0006!%A\u0005\u0002\u0005\u0015\u0006\"CAU\u0001E\u0005I\u0011AAV\u0011\u001d\ty\u000b\u0001C\u0001\u0003cCq!!.\u0001\t\u0003\n9\f\u0003\u0004\u0002:\u0002!\t\u0001\u0017\u0005\b\u0003w\u0003A\u0011IA_\u0011\u001d\ti\r\u0001C!\u0003oCq!a4\u0001\t\u0003\n\t\u000e\u0003\u0006\u0002T\u0001A)\u0019!C!\u0003'<\u0011\"!91\u0003\u0003E\t!a9\u0007\u0011=\u0002\u0014\u0011!E\u0001\u0003KDq!!\u0005'\t\u0003\t9\u0010C\u0005\u0002z\u001a\n\n\u0011\"\u0005\u0002\u0012\"I\u00111 \u0014\u0012\u0002\u0013E\u0011\u0011\u0013\u0005\n\u0003{4\u0013\u0013!C\t\u00033C\u0011\"a@'#\u0003%\t\"a(\t\u0013\t\u0005a%%A\u0005\u0012\u0005\u0015\u0006\"\u0003B\u0002ME\u0005I\u0011CAV\u0011%\u0011)AJA\u0001\n\u0013\u00119AA\tB]\u0006d\u0017p]5t\u000bb\u001cW\r\u001d;j_:T!!\r\u001a\u0002\u0007M\fHN\u0003\u00024i\u0005)1\u000f]1sW*\u0011QGN\u0001\u0007CB\f7\r[3\u000b\u0003]\n1a\u001c:h\u0007\u0001\u0019R\u0001\u0001\u001eI\u0019>\u0003\"aO#\u000f\u0005q\u0012eBA\u001fA\u001b\u0005q$BA 9\u0003\u0019a$o\\8u}%\t\u0011)A\u0003tG\u0006d\u0017-\u0003\u0002D\t\u00069\u0001/Y2lC\u001e,'\"A!\n\u0005\u0019;%!C#yG\u0016\u0004H/[8o\u0015\t\u0019E\t\u0005\u0002J\u00156\t!'\u0003\u0002Le\tq1\u000b]1sWRC'o\\<bE2,\u0007CA\u001eN\u0013\tquI\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0005\u0002Q+6\t\u0011K\u0003\u0002S'\u0006)AO]3fg*\u0011A\u000bM\u0001\tG\u0006$\u0018\r\\=ti&\u0011a+\u0015\u0002\u000b/&$\bn\u0014:jO&t\u0017aB7fgN\fw-Z\u000b\u00023B\u0011!L\u0018\b\u00037r\u0003\"!\u0010#\n\u0005u#\u0015A\u0002)sK\u0012,g-\u0003\u0002`A\n11\u000b\u001e:j]\u001eT!!\u0018#\u0002\u00115,7o]1hK\u0002\nA\u0001\\5oKV\tA\rE\u0002fM\"l\u0011\u0001R\u0005\u0003O\u0012\u0013aa\u00149uS>t\u0007CA3j\u0013\tQGIA\u0002J]R\fQ\u0001\\5oK\u0002\nQb\u001d;beR\u0004vn]5uS>t\u0017AD:uCJ$\bk\\:ji&|g\u000eI\u0001\u0006G\u0006,8/Z\u000b\u0002aB\u0019QMZ9\u0011\u0005m\u0012\u0018BA:H\u0005%!\u0006N]8xC\ndW-\u0001\u0004dCV\u001cX\rI\u0001\u000bKJ\u0014xN]\"mCN\u001cX#A<\u0011\u0007\u00154\u0017,A\u0006feJ|'o\u00117bgN\u0004\u0013!E7fgN\fw-\u001a)be\u0006lW\r^3sgV\t1\u0010\u0005\u0003[yfK\u0016BA?a\u0005\ri\u0015\r]\u0001\u0013[\u0016\u001c8/Y4f!\u0006\u0014\u0018-\\3uKJ\u001c\b%A\u0004d_:$X\r\u001f;\u0016\u0005\u0005\r\u0001#B3\u0002\u0006\u0005%\u0011bAA\u0004\t\n)\u0011I\u001d:bsB\u0019\u0011*a\u0003\n\u0007\u00055!G\u0001\u0007Rk\u0016\u0014\u0018pQ8oi\u0016DH/\u0001\u0005d_:$X\r\u001f;!\u0003\u0019a\u0014N\\5u}Q\u0001\u0012QCA\r\u00037\ti\"a\b\u0002\"\u0005\r\u0012Q\u0005\t\u0004\u0003/\u0001Q\"\u0001\u0019\t\u000b]{\u0001\u0019A-\t\u000f\t|\u0001\u0013!a\u0001I\"9An\u0004I\u0001\u0002\u0004!\u0007b\u00028\u0010!\u0003\u0005\r\u0001\u001d\u0005\bk>\u0001\n\u00111\u0001x\u0011\u001dIx\u0002%AA\u0002mD\u0001b`\b\u0011\u0002\u0003\u0007\u00111\u0001\u000b\t\u0003+\tI#a\u000b\u0002.!)Q\u000f\u0005a\u00013\")\u0011\u0010\u0005a\u0001w\")a\u000e\u0005a\u0001aRQ\u0011QCA\u0019\u0003g\t)$a\u000e\t\u000bU\f\u0002\u0019A-\t\u000be\f\u0002\u0019A>\t\r}\f\u0002\u0019AA\u0002\u0011\u0015q\u0017\u00031\u0001q))\t)\"a\u000f\u0002>\u0005}\u0012\u0011\t\u0005\u0006kJ\u0001\r!\u0017\u0005\u0006sJ\u0001\ra\u001f\u0005\u0007\u007fJ\u0001\r!a\u0001\t\r\u0005\r#\u00031\u0001Z\u0003\u001d\u0019X/\\7bef$b!!\u0006\u0002H\u0005%\u0003\"B;\u0014\u0001\u0004I\u0006\"B=\u0014\u0001\u0004YH\u0003CA\u000b\u0003\u001b\ny%!\u0015\t\u000bU$\u0002\u0019A-\t\u000be$\u0002\u0019A>\t\u000f\u0005MC\u00031\u0001\u0002V\u00051qN]5hS:\u00042\u0001UA,\u0013\r\tI&\u0015\u0002\u0007\u001fJLw-\u001b8\u0015\u0015\u0005U\u0011QLA0\u0003C\n\u0019\u0007C\u0003v+\u0001\u0007\u0011\fC\u0003z+\u0001\u00071\u0010C\u0004\u0002TU\u0001\r!!\u0016\t\u000b9,\u0002\u0019\u00019\u0002\t\r|\u0007/\u001f\u000b\u0011\u0003+\tI'a\u001b\u0002n\u0005=\u0014\u0011OA:\u0003kBqa\u0016\f\u0011\u0002\u0003\u0007\u0011\fC\u0004c-A\u0005\t\u0019\u00013\t\u000f14\u0002\u0013!a\u0001I\"9aN\u0006I\u0001\u0002\u0004\u0001\bbB;\u0017!\u0003\u0005\ra\u001e\u0005\bsZ\u0001\n\u00111\u0001|\u0011!yh\u0003%AA\u0002\u0005\r\u0011AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0003wR3!WA?W\t\ty\b\u0005\u0003\u0002\u0002\u0006-UBAAB\u0015\u0011\t))a\"\u0002\u0013Ut7\r[3dW\u0016$'bAAE\t\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u00055\u00151\u0011\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0003'S3\u0001ZA?\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM\nabY8qs\u0012\"WMZ1vYR$C'\u0006\u0002\u0002\u001c*\u001a\u0001/! \u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%kU\u0011\u0011\u0011\u0015\u0016\u0004o\u0006u\u0014AD2paf$C-\u001a4bk2$HEN\u000b\u0003\u0003OS3a_A?\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uI]*\"!!,+\t\u0005\r\u0011QP\u0001\ro&$\b\u000eU8tSRLwN\u001c\u000b\u0005\u0003+\t\u0019\fC\u0004\u0002Ty\u0001\r!!\u0016\u0002\u0015\u001d,G/T3tg\u0006<W\rF\u0001Z\u0003A9W\r^*j[BdW-T3tg\u0006<W-\u0001\u000bhKRlUm]:bO\u0016\u0004\u0016M]1nKR,'o\u001d\u000b\u0003\u0003\u007f\u0003b!!1\u0002LfKVBAAb\u0015\u0011\t)-a2\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u0003\u0013\fAA[1wC&\u0019Q0a1\u0002\u0019\u001d,GoQ8oI&$\u0018n\u001c8\u0002\u001f\u001d,G/U;fef\u001cuN\u001c;fqR$\"!a\u0001\u0016\u0005\u0005U\u0003f\u0001\u0001\u0002XB!\u0011\u0011\\Ao\u001b\t\tYNC\u0002\u0002\nJJA!a8\u0002\\\n11\u000b^1cY\u0016\f\u0011#\u00118bYf\u001c\u0018n]#yG\u0016\u0004H/[8o!\r\t9BJ\n\u0006M\u0005\u001d\u0018Q\u001e\t\u0004K\u0006%\u0018bAAv\t\n1\u0011I\\=SK\u001a\u0004B!a<\u0002v6\u0011\u0011\u0011\u001f\u0006\u0005\u0003g\f9-\u0001\u0002j_&\u0019a*!=\u0015\u0005\u0005\r\u0018a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$#'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HeM\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001b\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00136\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%m\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uI]\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"A!\u0003\u0011\t\t-!\u0011C\u0007\u0003\u0005\u001bQAAa\u0004\u0002H\u0006!A.\u00198h\u0013\u0011\u0011\u0019B!\u0004\u0003\r=\u0013'.Z2u\u0001"
)
public class AnalysisException extends Exception implements SparkThrowable, WithOrigin {
   private Origin origin;
   private final String message;
   private final Option line;
   private final Option startPosition;
   private final Option cause;
   private final Option errorClass;
   private final Map messageParameters;
   private final QueryContext[] context;
   private volatile boolean bitmap$0;

   /** @deprecated */
   @Deprecated
   public String getErrorClass() {
      return super.getErrorClass();
   }

   public String getSqlState() {
      return super.getSqlState();
   }

   public boolean isInternalError() {
      return super.isInternalError();
   }

   public String message() {
      return this.message;
   }

   public Option line() {
      return this.line;
   }

   public Option startPosition() {
      return this.startPosition;
   }

   public Option cause() {
      return this.cause;
   }

   public Option errorClass() {
      return this.errorClass;
   }

   public Map messageParameters() {
      return this.messageParameters;
   }

   public QueryContext[] context() {
      return this.context;
   }

   public AnalysisException copy(final String message, final Option line, final Option startPosition, final Option cause, final Option errorClass, final Map messageParameters, final QueryContext[] context) {
      return new AnalysisException(message, line, startPosition, cause, errorClass, messageParameters, context);
   }

   public String copy$default$1() {
      return this.message();
   }

   public Option copy$default$2() {
      return this.line();
   }

   public Option copy$default$3() {
      return this.startPosition();
   }

   public Option copy$default$4() {
      return this.cause();
   }

   public Option copy$default$5() {
      return this.errorClass();
   }

   public Map copy$default$6() {
      return this.messageParameters();
   }

   public QueryContext[] copy$default$7() {
      return this.context();
   }

   public AnalysisException withPosition(final Origin origin) {
      Option x$1 = origin.line();
      Option x$2 = origin.startPosition();
      QueryContext[] x$3 = origin.getQueryContext();
      String x$4 = this.copy$default$1();
      Option x$5 = this.copy$default$4();
      Option x$6 = this.copy$default$5();
      Map x$7 = this.copy$default$6();
      AnalysisException newException = this.copy(x$4, x$1, x$2, x$5, x$6, x$7, x$3);
      newException.setStackTrace(this.getStackTrace());
      return newException;
   }

   public String getMessage() {
      return this.getSimpleMessage();
   }

   public String getSimpleMessage() {
      if (!this.line().isDefined() && !this.startPosition().isDefined()) {
         return this.message();
      } else {
         String lineAnnotation = (String)this.line().map((l) -> $anonfun$getSimpleMessage$1(BoxesRunTime.unboxToInt(l))).getOrElse(() -> "");
         String positionAnnotation = (String)this.startPosition().map((p) -> $anonfun$getSimpleMessage$3(BoxesRunTime.unboxToInt(p))).getOrElse(() -> "");
         return this.message() + ";" + lineAnnotation + positionAnnotation;
      }
   }

   public java.util.Map getMessageParameters() {
      return .MODULE$.MapHasAsJava(this.messageParameters()).asJava();
   }

   public String getCondition() {
      return (String)this.errorClass().orNull(scala..less.colon.less..MODULE$.refl());
   }

   public QueryContext[] getQueryContext() {
      return this.context();
   }

   private Origin origin$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.origin = new Origin(this.line(), this.startPosition(), Origin$.MODULE$.apply$default$3(), Origin$.MODULE$.apply$default$4(), Origin$.MODULE$.apply$default$5(), Origin$.MODULE$.apply$default$6(), Origin$.MODULE$.apply$default$7(), Origin$.MODULE$.apply$default$8(), Origin$.MODULE$.apply$default$9());
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.origin;
   }

   public Origin origin() {
      return !this.bitmap$0 ? this.origin$lzycompute() : this.origin;
   }

   // $FF: synthetic method
   public static final String $anonfun$getSimpleMessage$1(final int l) {
      return " line " + l;
   }

   // $FF: synthetic method
   public static final String $anonfun$getSimpleMessage$3(final int p) {
      return " pos " + p;
   }

   public AnalysisException(final String message, final Option line, final Option startPosition, final Option cause, final Option errorClass, final Map messageParameters, final QueryContext[] context) {
      super(message, (Throwable)cause.orNull(scala..less.colon.less..MODULE$.refl()));
      this.message = message;
      this.line = line;
      this.startPosition = startPosition;
      this.cause = cause;
      this.errorClass = errorClass;
      this.messageParameters = messageParameters;
      this.context = context;
   }

   public AnalysisException(final String errorClass, final Map messageParameters, final Option cause) {
      String x$1 = org.apache.spark.SparkThrowableHelper..MODULE$.getMessage(errorClass, messageParameters);
      Some x$2 = new Some(errorClass);
      Option x$5 = AnalysisException$.MODULE$.$lessinit$greater$default$2();
      Option x$6 = AnalysisException$.MODULE$.$lessinit$greater$default$3();
      QueryContext[] x$7 = AnalysisException$.MODULE$.$lessinit$greater$default$7();
      this(x$1, x$5, x$6, cause, x$2, messageParameters, x$7);
   }

   public AnalysisException(final String errorClass, final Map messageParameters, final QueryContext[] context, final Option cause) {
      String x$1 = org.apache.spark.SparkThrowableHelper..MODULE$.getMessage(errorClass, messageParameters);
      Some x$2 = new Some(errorClass);
      Option x$6 = AnalysisException$.MODULE$.$lessinit$greater$default$2();
      Option x$7 = AnalysisException$.MODULE$.$lessinit$greater$default$3();
      this(x$1, x$6, x$7, cause, x$2, messageParameters, context);
   }

   public AnalysisException(final String errorClass, final Map messageParameters, final QueryContext[] context, final String summary) {
      String x$1 = org.apache.spark.SparkThrowableHelper..MODULE$.getMessage(errorClass, messageParameters, summary);
      Some x$2 = new Some(errorClass);
      Null x$4 = null;
      Option x$6 = AnalysisException$.MODULE$.$lessinit$greater$default$2();
      Option x$7 = AnalysisException$.MODULE$.$lessinit$greater$default$3();
      this(x$1, x$6, x$7, (Option)null, x$2, messageParameters, context);
   }

   public AnalysisException(final String errorClass, final Map messageParameters) {
      this(errorClass, messageParameters, (Option)scala.None..MODULE$);
   }

   public AnalysisException(final String errorClass, final Map messageParameters, final Origin origin) {
      String x$1 = org.apache.spark.SparkThrowableHelper..MODULE$.getMessage(errorClass, messageParameters);
      Option x$2 = origin.line();
      Option x$3 = origin.startPosition();
      Some x$4 = new Some(errorClass);
      QueryContext[] x$6 = origin.getQueryContext();
      Option x$7 = AnalysisException$.MODULE$.$lessinit$greater$default$4();
      this(x$1, x$2, x$3, x$7, x$4, messageParameters, x$6);
   }

   public AnalysisException(final String errorClass, final Map messageParameters, final Origin origin, final Option cause) {
      String x$1 = org.apache.spark.SparkThrowableHelper..MODULE$.getMessage(errorClass, messageParameters);
      Option x$2 = origin.line();
      Option x$3 = origin.startPosition();
      Some x$4 = new Some(errorClass);
      QueryContext[] x$6 = origin.getQueryContext();
      this(x$1, x$2, x$3, cause, x$4, messageParameters, x$6);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
