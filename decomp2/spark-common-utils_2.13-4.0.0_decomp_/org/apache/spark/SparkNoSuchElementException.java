package org.apache.spark;

import java.util.NoSuchElementException;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a4Qa\u0004\t\u0001!YA\u0001B\u000b\u0001\u0003\u0002\u0003\u0006Ia\u000b\u0005\tg\u0001\u0011\t\u0011)A\u0005i!Aq\u0007\u0001B\u0001B\u0003%\u0001\b\u0003\u0005@\u0001\t\u0005\t\u0015!\u0003,\u0011\u0015\u0001\u0005\u0001\"\u0001B\u0011\u00159\u0005\u0001\"\u0011I\u0011\u0015\u0001\u0006\u0001\"\u0011R\u0011\u0015\u0011\u0006\u0001\"\u0011T\u000f!!\u0006#!A\t\u0002A)f\u0001C\b\u0011\u0003\u0003E\t\u0001\u0005,\t\u000b\u0001SA\u0011\u00011\t\u000f\u0005T\u0011\u0013!C\u0001E\"9QNCI\u0001\n\u0003q\u0007b\u00029\u000b\u0003\u0003%I!\u001d\u0002\u001c'B\f'o\u001b(p'V\u001c\u0007.\u00127f[\u0016tG/\u0012=dKB$\u0018n\u001c8\u000b\u0005E\u0011\u0012!B:qCJ\\'BA\n\u0015\u0003\u0019\t\u0007/Y2iK*\tQ#A\u0002pe\u001e\u001c2\u0001A\f'!\tA2E\u0004\u0002\u001aA9\u0011!DH\u0007\u00027)\u0011A$H\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tq$A\u0003tG\u0006d\u0017-\u0003\u0002\"E\u00059\u0001/Y2lC\u001e,'\"A\u0010\n\u0005\u0011*#A\u0006(p'V\u001c\u0007.\u00127f[\u0016tG/\u0012=dKB$\u0018n\u001c8\u000b\u0005\u0005\u0012\u0003CA\u0014)\u001b\u0005\u0001\u0012BA\u0015\u0011\u00059\u0019\u0006/\u0019:l)\"\u0014xn^1cY\u0016\f!\"\u001a:s_J\u001cE.Y:t!\ta\u0003G\u0004\u0002.]A\u0011!DI\u0005\u0003_\t\na\u0001\u0015:fI\u00164\u0017BA\u00193\u0005\u0019\u0019FO]5oO*\u0011qFI\u0001\u0012[\u0016\u001c8/Y4f!\u0006\u0014\u0018-\\3uKJ\u001c\b\u0003\u0002\u00176W-J!A\u000e\u001a\u0003\u00075\u000b\u0007/A\u0004d_:$X\r\u001f;\u0011\u0007eRD(D\u0001#\u0013\tY$EA\u0003BeJ\f\u0017\u0010\u0005\u0002({%\u0011a\b\u0005\u0002\r#V,'/_\"p]R,\u0007\u0010^\u0001\bgVlW.\u0019:z\u0003\u0019a\u0014N\\5u}Q)!i\u0011#F\rB\u0011q\u0005\u0001\u0005\u0006U\u0015\u0001\ra\u000b\u0005\u0006g\u0015\u0001\r\u0001\u000e\u0005\bo\u0015\u0001\n\u00111\u00019\u0011\u001dyT\u0001%AA\u0002-\nAcZ3u\u001b\u0016\u001c8/Y4f!\u0006\u0014\u0018-\\3uKJ\u001cH#A%\u0011\t){5fK\u0007\u0002\u0017*\u0011A*T\u0001\u0005kRLGNC\u0001O\u0003\u0011Q\u0017M^1\n\u0005YZ\u0015\u0001D4fi\u000e{g\u000eZ5uS>tG#A\u0016\u0002\u001f\u001d,G/U;fef\u001cuN\u001c;fqR$\u0012\u0001O\u0001\u001c'B\f'o\u001b(p'V\u001c\u0007.\u00127f[\u0016tG/\u0012=dKB$\u0018n\u001c8\u0011\u0005\u001dR1c\u0001\u0006X5B\u0011\u0011\bW\u0005\u00033\n\u0012a!\u00118z%\u00164\u0007CA._\u001b\u0005a&BA/N\u0003\tIw.\u0003\u0002`9\na1+\u001a:jC2L'0\u00192mKR\tQ+A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HeM\u000b\u0002G*\u0012\u0001\bZ\u0016\u0002KB\u0011am[\u0007\u0002O*\u0011\u0001.[\u0001\nk:\u001c\u0007.Z2lK\u0012T!A\u001b\u0012\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002mO\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00135+\u0005y'FA\u0016e\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005\u0011\bCA:w\u001b\u0005!(BA;N\u0003\u0011a\u0017M\\4\n\u0005]$(AB(cU\u0016\u001cG\u000f"
)
public class SparkNoSuchElementException extends NoSuchElementException implements SparkThrowable {
   private final String errorClass;
   private final Map messageParameters;
   private final QueryContext[] context;

   public static String $lessinit$greater$default$4() {
      return SparkNoSuchElementException$.MODULE$.$lessinit$greater$default$4();
   }

   public static QueryContext[] $lessinit$greater$default$3() {
      return SparkNoSuchElementException$.MODULE$.$lessinit$greater$default$3();
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
      return this.errorClass;
   }

   public QueryContext[] getQueryContext() {
      return this.context;
   }

   public SparkNoSuchElementException(final String errorClass, final Map messageParameters, final QueryContext[] context, final String summary) {
      super(SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters, summary));
      this.errorClass = errorClass;
      this.messageParameters = messageParameters;
      this.context = context;
   }
}
