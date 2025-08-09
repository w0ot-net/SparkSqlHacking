package org.apache.spark;

import scala.Option;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u3Qa\u0003\u0007\u0001\u0019IA\u0001B\n\u0001\u0003\u0002\u0003\u0006Ia\n\u0005\t_\u0001\u0011\t\u0011)A\u0005a!AA\u0007\u0001B\u0001B\u0003%Q\u0007\u0003\u00059\u0001\t\u0005\t\u0015!\u0003:\u0011\u0015y\u0004\u0001\"\u0003A\u0011\u0015y\u0004\u0001\"\u0001G\u0011\u0015y\u0004\u0001\"\u0001M\u0011\u0015\u0001\u0006\u0001\"\u0011R\u0011\u0015I\u0006\u0001\"\u0011[\u0011\u0015Y\u0006\u0001\"\u0011]\u0005i\u0019\u0006/\u0019:l\u001dVl'-\u001a:G_Jl\u0017\r^#yG\u0016\u0004H/[8o\u0015\tia\"A\u0003ta\u0006\u00148N\u0003\u0002\u0010!\u00051\u0011\r]1dQ\u0016T\u0011!E\u0001\u0004_J<7c\u0001\u0001\u0014EA\u0011Ac\b\b\u0003+qq!A\u0006\u000e\u000e\u0003]Q!\u0001G\r\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011aG\u0001\u0006g\u000e\fG.Y\u0005\u0003;y\tq\u0001]1dW\u0006<WMC\u0001\u001c\u0013\t\u0001\u0013EA\u000bOk6\u0014WM\u001d$pe6\fG/\u0012=dKB$\u0018n\u001c8\u000b\u0005uq\u0002CA\u0012%\u001b\u0005a\u0011BA\u0013\r\u00059\u0019\u0006/\u0019:l)\"\u0014xn^1cY\u0016\fq!\\3tg\u0006<W\r\u0005\u0002)Y9\u0011\u0011F\u000b\t\u0003-yI!a\u000b\u0010\u0002\rA\u0013X\rZ3g\u0013\ticF\u0001\u0004TiJLgn\u001a\u0006\u0003Wy\t!\"\u001a:s_J\u001cE.Y:t!\r\t$gJ\u0007\u0002=%\u00111G\b\u0002\u0007\u001fB$\u0018n\u001c8\u0002#5,7o]1hKB\u000b'/Y7fi\u0016\u00148\u000f\u0005\u0003)m\u001d:\u0013BA\u001c/\u0005\ri\u0015\r]\u0001\bG>tG/\u001a=u!\r\t$\bP\u0005\u0003wy\u0011Q!\u0011:sCf\u0004\"aI\u001f\n\u0005yb!\u0001D)vKJL8i\u001c8uKb$\u0018A\u0002\u001fj]&$h\bF\u0003B\u0005\u000e#U\t\u0005\u0002$\u0001!)a%\u0002a\u0001O!)q&\u0002a\u0001a!)A'\u0002a\u0001k!)\u0001(\u0002a\u0001sQ)\u0011i\u0012%J\u0015\")qF\u0002a\u0001O!)AG\u0002a\u0001k!)\u0001H\u0002a\u0001s!)1J\u0002a\u0001O\u000591/^7nCJLH\u0003B!N\u001d>CQaL\u0004A\u0002\u001dBQ\u0001N\u0004A\u0002UBQ\u0001O\u0004A\u0002e\nAcZ3u\u001b\u0016\u001c8/Y4f!\u0006\u0014\u0018-\\3uKJ\u001cH#\u0001*\u0011\tMCveJ\u0007\u0002)*\u0011QKV\u0001\u0005kRLGNC\u0001X\u0003\u0011Q\u0017M^1\n\u0005]\"\u0016\u0001D4fi\u000e{g\u000eZ5uS>tG#A\u0014\u0002\u001f\u001d,G/U;fef\u001cuN\u001c;fqR$\u0012!\u000f"
)
public class SparkNumberFormatException extends NumberFormatException implements SparkThrowable {
   private final Option errorClass;
   private final Map messageParameters;
   private final QueryContext[] context;

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

   private SparkNumberFormatException(final String message, final Option errorClass, final Map messageParameters, final QueryContext[] context) {
      super(message);
      this.errorClass = errorClass;
      this.messageParameters = messageParameters;
      this.context = context;
   }

   public SparkNumberFormatException(final String errorClass, final Map messageParameters, final QueryContext[] context, final String summary) {
      this(SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters, summary), scala.Option..MODULE$.apply(errorClass), messageParameters, context);
   }

   public SparkNumberFormatException(final String errorClass, final Map messageParameters, final QueryContext[] context) {
      this(errorClass, messageParameters, context, "");
   }
}
