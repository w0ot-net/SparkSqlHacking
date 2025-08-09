package scala.xml.dtd;

import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019;Q!\u0003\u0006\t\u0002E1Qa\u0005\u0006\t\u0002QAQ!G\u0001\u0005\u0002iAQaG\u0001\u0005\u0002qAQ!M\u0001\u0005\u0002IBQaM\u0001\u0005\u0002QBQaN\u0001\u0005\u0002aBQaO\u0001\u0005\u0002qBQaO\u0001\u0005\u0002\t\u000bq#T1lKZ\u000bG.\u001b3bi&|g.\u0012=dKB$\u0018n\u001c8\u000b\u0005-a\u0011a\u00013uI*\u0011QBD\u0001\u0004q6d'\"A\b\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001A\u0011!#A\u0007\u0002\u0015\t9R*Y6f-\u0006d\u0017\u000eZ1uS>tW\t_2faRLwN\\\n\u0003\u0003U\u0001\"AF\f\u000e\u00039I!\u0001\u0007\b\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\t\u0011#\u0001\nge>lg)\u001b=fI\u0006#HO]5ckR,G\u0003B\u000f![=\u0002\"A\u0005\u0010\n\u0005}Q!a\u0005,bY&$\u0017\r^5p]\u0016C8-\u001a9uS>t\u0007\"B\u0011\u0004\u0001\u0004\u0011\u0013!A6\u0011\u0005\rRcB\u0001\u0013)!\t)c\"D\u0001'\u0015\t9\u0003#\u0001\u0004=e>|GOP\u0005\u0003S9\ta\u0001\u0015:fI\u00164\u0017BA\u0016-\u0005\u0019\u0019FO]5oO*\u0011\u0011F\u0004\u0005\u0006]\r\u0001\rAI\u0001\u0006m\u0006dW/\u001a\u0005\u0006a\r\u0001\rAI\u0001\u0007C\u000e$X/\u00197\u0002'\u0019\u0014x.\u001c(p]\u0016k\u0007\u000f^=FY\u0016lWM\u001c;\u0015\u0003u\tAC\u001a:p[VsG-\u001a4j]\u0016$W\t\\3nK:$HCA\u000f6\u0011\u00151T\u00011\u0001#\u0003\u0015a\u0017MY3m\u0003Y1'o\\7V]\u0012,g-\u001b8fI\u0006#HO]5ckR,GCA\u000f:\u0011\u0015Qd\u00011\u0001#\u0003\rYW-_\u0001\u0015MJ|W.T5tg&tw-\u0011;ue&\u0014W\u000f^3\u0015\u0005ui\u0004\"\u0002 \b\u0001\u0004y\u0014aB1mY.+\u0017p\u001d\t\u0004G\u0001\u0013\u0013BA!-\u0005\r\u0019V\r\u001e\u000b\u0004;\r#\u0005\"\u0002\u001e\t\u0001\u0004\u0011\u0003\"B#\t\u0001\u0004\u0011\u0013a\u0001;qK\u0002"
)
public final class MakeValidationException {
   public static ValidationException fromMissingAttribute(final String key, final String tpe) {
      return MakeValidationException$.MODULE$.fromMissingAttribute(key, tpe);
   }

   public static ValidationException fromMissingAttribute(final Set allKeys) {
      return MakeValidationException$.MODULE$.fromMissingAttribute(allKeys);
   }

   public static ValidationException fromUndefinedAttribute(final String key) {
      return MakeValidationException$.MODULE$.fromUndefinedAttribute(key);
   }

   public static ValidationException fromUndefinedElement(final String label) {
      return MakeValidationException$.MODULE$.fromUndefinedElement(label);
   }

   public static ValidationException fromNonEmptyElement() {
      return MakeValidationException$.MODULE$.fromNonEmptyElement();
   }

   public static ValidationException fromFixedAttribute(final String k, final String value, final String actual) {
      return MakeValidationException$.MODULE$.fromFixedAttribute(k, value, actual);
   }
}
