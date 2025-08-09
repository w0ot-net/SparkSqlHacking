package org.apache.spark;

import scala.Option;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E3Q!\u0003\u0006\u0001\u0015AA\u0001\u0002\n\u0001\u0003\u0002\u0003\u0006I!\n\u0005\t[\u0001\u0011\t\u0011)A\u0005]!AQ\u0007\u0001B\u0001B\u0003%a\u0007\u0003\u00058\u0001\t\u0005\t\u0015!\u00039\u0011\u0015Y\u0004\u0001\"\u0003=\u0011\u0015Y\u0004\u0001\"\u0001C\u0011\u00151\u0005\u0001\"\u0011H\u0011\u0015y\u0005\u0001\"\u0011Q\u0005U\u0019\u0006/\u0019:l+B<'/\u00193f\u000bb\u001cW\r\u001d;j_:T!a\u0003\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00055q\u0011AB1qC\u000eDWMC\u0001\u0010\u0003\ry'oZ\n\u0004\u0001E\u0001\u0003C\u0001\n\u001e\u001d\t\u0019\"D\u0004\u0002\u001515\tQC\u0003\u0002\u0017/\u00051AH]8piz\u001a\u0001!C\u0001\u001a\u0003\u0015\u00198-\u00197b\u0013\tYB$A\u0004qC\u000e\\\u0017mZ3\u000b\u0003eI!AH\u0010\u0003!I+h\u000e^5nK\u0016C8-\u001a9uS>t'BA\u000e\u001d!\t\t#%D\u0001\u000b\u0013\t\u0019#B\u0001\bTa\u0006\u00148\u000e\u00165s_^\f'\r\\3\u0002\u000f5,7o]1hKB\u0011aE\u000b\b\u0003O!\u0002\"\u0001\u0006\u000f\n\u0005%b\u0012A\u0002)sK\u0012,g-\u0003\u0002,Y\t11\u000b\u001e:j]\u001eT!!\u000b\u000f\u0002\u000b\r\fWo]3\u0011\u0007=\u0002$'D\u0001\u001d\u0013\t\tDD\u0001\u0004PaRLwN\u001c\t\u0003%MJ!\u0001N\u0010\u0003\u0013QC'o\\<bE2,\u0017AC3se>\u00148\t\\1tgB\u0019q\u0006M\u0013\u0002#5,7o]1hKB\u000b'/Y7fi\u0016\u00148\u000f\u0005\u0003's\u0015*\u0013B\u0001\u001e-\u0005\ri\u0015\r]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000burt\bQ!\u0011\u0005\u0005\u0002\u0001\"\u0002\u0013\u0006\u0001\u0004)\u0003\"B\u0017\u0006\u0001\u0004q\u0003\"B\u001b\u0006\u0001\u00041\u0004\"B\u001c\u0006\u0001\u0004AD\u0003B\u001fD\t\u0016CQ!\u000e\u0004A\u0002\u0015BQa\u000e\u0004A\u0002aBQ!\f\u0004A\u0002I\nAcZ3u\u001b\u0016\u001c8/Y4f!\u0006\u0014\u0018-\\3uKJ\u001cH#\u0001%\u0011\t%sU%J\u0007\u0002\u0015*\u00111\nT\u0001\u0005kRLGNC\u0001N\u0003\u0011Q\u0017M^1\n\u0005iR\u0015\u0001D4fi\u000e{g\u000eZ5uS>tG#A\u0013"
)
public class SparkUpgradeException extends RuntimeException implements SparkThrowable {
   private final Option errorClass;
   private final Map messageParameters;

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

   public QueryContext[] getQueryContext() {
      return SparkThrowable.super.getQueryContext();
   }

   public java.util.Map getMessageParameters() {
      return .MODULE$.MapHasAsJava(this.messageParameters).asJava();
   }

   public String getCondition() {
      return (String)this.errorClass.orNull(scala..less.colon.less..MODULE$.refl());
   }

   private SparkUpgradeException(final String message, final Option cause, final Option errorClass, final Map messageParameters) {
      super(message, (Throwable)cause.orNull(scala..less.colon.less..MODULE$.refl()));
      this.errorClass = errorClass;
      this.messageParameters = messageParameters;
   }

   public SparkUpgradeException(final String errorClass, final Map messageParameters, final Throwable cause) {
      this(SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters), scala.Option..MODULE$.apply(cause), scala.Option..MODULE$.apply(errorClass), messageParameters);
   }
}
