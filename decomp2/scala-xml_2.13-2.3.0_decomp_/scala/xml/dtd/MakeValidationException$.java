package scala.xml.dtd;

import java.lang.invoke.SerializedLambda;
import scala.collection.immutable.Set;
import scala.runtime.BoxedUnit;

public final class MakeValidationException$ {
   public static final MakeValidationException$ MODULE$ = new MakeValidationException$();

   public ValidationException fromFixedAttribute(final String k, final String value, final String actual) {
      return new ValidationException((new StringBuilder(54)).append("value of attribute ").append(k).append(" FIXED to \"").append(value).append("\", but document tries \"").append(actual).append("\"").toString());
   }

   public ValidationException fromNonEmptyElement() {
      return new ValidationException("element should be *empty*");
   }

   public ValidationException fromUndefinedElement(final String label) {
      return new ValidationException((new StringBuilder(27)).append("element \"").append(label).append("\" not allowed here").toString());
   }

   public ValidationException fromUndefinedAttribute(final String key) {
      return new ValidationException((new StringBuilder(27)).append("attribute ").append(key).append(" not allowed here").toString());
   }

   public ValidationException fromMissingAttribute(final Set allKeys) {
      scala.collection.mutable.StringBuilder sb = new scala.collection.mutable.StringBuilder("missing value for REQUIRED attribute");
      if (allKeys.size() > 1) {
         sb.append('s');
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      allKeys.foreach((k) -> sb.append((new StringBuilder(2)).append("'").append(k).append("'").toString()));
      return new ValidationException(sb.toString());
   }

   public ValidationException fromMissingAttribute(final String key, final String tpe) {
      return new ValidationException((new StringBuilder(46)).append("missing value for REQUIRED attribute ").append(key).append(" of type ").append(tpe).toString());
   }

   private MakeValidationException$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
