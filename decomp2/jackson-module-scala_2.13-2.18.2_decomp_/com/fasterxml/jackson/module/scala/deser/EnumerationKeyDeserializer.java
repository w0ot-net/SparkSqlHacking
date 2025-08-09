package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.module.scala.util.EnumResolver;
import com.fasterxml.jackson.module.scala.util.EnumResolver$;
import scala.Enumeration;
import scala.Option;
import scala.Some;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3A!\u0002\u0004\u0005'!Aq\u0004\u0001B\u0001B\u0003%\u0001\u0005C\u0003,\u0001\u0011\u0005A\u0006C\u00031\u0001\u0011\u0005\u0013\u0007C\u0003=\u0001\u0011\u0005QH\u0001\u000eF]VlWM]1uS>t7*Z=EKN,'/[1mSj,'O\u0003\u0002\b\u0011\u0005)A-Z:fe*\u0011\u0011BC\u0001\u0006g\u000e\fG.\u0019\u0006\u0003\u00171\ta!\\8ek2,'BA\u0007\u000f\u0003\u001dQ\u0017mY6t_:T!a\u0004\t\u0002\u0013\u0019\f7\u000f^3sq6d'\"A\t\u0002\u0007\r|Wn\u0001\u0001\u0014\u0007\u0001!\"\u0004\u0005\u0002\u001615\taC\u0003\u0002\u0018\u0019\u0005AA-\u0019;bE&tG-\u0003\u0002\u001a-\ty1*Z=EKN,'/[1mSj,'\u000f\u0005\u0002\u001c;5\tAD\u0003\u0002\b-%\u0011a\u0004\b\u0002\u001a\u0007>tG/\u001a=uk\u0006d7*Z=EKN,'/[1mSj,'/A\u0001s!\r\t3%J\u0007\u0002E)\t\u0011\"\u0003\u0002%E\t1q\n\u001d;j_:\u0004\"AJ\u0015\u000e\u0003\u001dR!\u0001\u000b\u0005\u0002\tU$\u0018\u000e\\\u0005\u0003U\u001d\u0012A\"\u00128v[J+7o\u001c7wKJ\fa\u0001P5oSRtDCA\u00170!\tq\u0003!D\u0001\u0007\u0011\u0015y\"\u00011\u0001!\u0003A\u0019'/Z1uK\u000e{g\u000e^3yiV\fG\u000eF\u0002\u0015e]BQaM\u0002A\u0002Q\nAa\u0019;yiB\u0011Q#N\u0005\u0003mY\u0011a\u0003R3tKJL\u0017\r\\5{CRLwN\\\"p]R,\u0007\u0010\u001e\u0005\u0006q\r\u0001\r!O\u0001\taJ|\u0007/\u001a:usB\u0011QCO\u0005\u0003wY\u0011ABQ3b]B\u0013x\u000e]3sif\fa\u0002Z3tKJL\u0017\r\\5{K.+\u0017\u0010F\u0002?\tF\u0003\"a\u0010\"\u0011\u0005\u0005\u0002\u0015BA!#\u0005-)e.^7fe\u0006$\u0018n\u001c8\n\u0005\r\u0003%!\u0002,bYV,\u0007\"B#\u0005\u0001\u00041\u0015!A:\u0011\u0005\u001dseB\u0001%M!\tI%%D\u0001K\u0015\tY%#\u0001\u0004=e>|GOP\u0005\u0003\u001b\n\na\u0001\u0015:fI\u00164\u0017BA(Q\u0005\u0019\u0019FO]5oO*\u0011QJ\t\u0005\u0006g\u0011\u0001\r\u0001\u000e"
)
public class EnumerationKeyDeserializer extends KeyDeserializer implements ContextualKeyDeserializer {
   private final Option r;

   public KeyDeserializer createContextual(final DeserializationContext ctxt, final BeanProperty property) {
      Option newResolver = EnumResolver$.MODULE$.apply(property);
      Option var4 = this.r;
      if (newResolver == null) {
         if (var4 != null) {
            return new EnumerationKeyDeserializer(newResolver);
         }
      } else if (!newResolver.equals(var4)) {
         return new EnumerationKeyDeserializer(newResolver);
      }

      return this;
   }

   public Enumeration.Value deserializeKey(final String s, final DeserializationContext ctxt) {
      Option var4 = this.r;
      if (var4 instanceof Some) {
         Some var5 = (Some)var4;
         EnumResolver resolved = (EnumResolver)var5.value();
         return resolved.getEnum(s);
      } else {
         throw JsonMappingException.from(ctxt, "Need @JsonScalaEnumeration to determine key type");
      }
   }

   public EnumerationKeyDeserializer(final Option r) {
      this.r = r;
   }
}
