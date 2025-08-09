package org.apache.spark.ml.classification;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I2qa\u0001\u0003\u0011\u0002\u0007\u0005r\u0002C\u0003\u001b\u0001\u0011\u00051\u0004C\u0003 \u0001\u0011\u0005\u0001EA\u0011SC:$w.\u001c$pe\u0016\u001cHo\u00117bgNLg-[2bi&|gnU;n[\u0006\u0014\u0018P\u0003\u0002\u0006\r\u0005q1\r\\1tg&4\u0017nY1uS>t'BA\u0004\t\u0003\tiGN\u0003\u0002\n\u0015\u0005)1\u000f]1sW*\u00111\u0002D\u0001\u0007CB\f7\r[3\u000b\u00035\t1a\u001c:h\u0007\u0001\u00192\u0001\u0001\t\u0017!\t\tB#D\u0001\u0013\u0015\u0005\u0019\u0012!B:dC2\f\u0017BA\u000b\u0013\u0005\u0019\te.\u001f*fMB\u0011q\u0003G\u0007\u0002\t%\u0011\u0011\u0004\u0002\u0002\u0016\u00072\f7o]5gS\u000e\fG/[8o'VlW.\u0019:z\u0003\u0019!\u0013N\\5uIQ\tA\u0004\u0005\u0002\u0012;%\u0011aD\u0005\u0002\u0005+:LG/\u0001\u0005bg\nKg.\u0019:z+\u0005\t\u0003CA\f#\u0013\t\u0019CAA\u0014CS:\f'/\u001f*b]\u0012|WNR8sKN$8\t\\1tg&4\u0017nY1uS>t7+^7nCJL\bf\u0001\u0002&WA\u0011a%K\u0007\u0002O)\u0011\u0001\u0006C\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001\u0016(\u0005\u0015\u0019\u0016N\\2fC\u0005a\u0013!B\u001a/c9\u0002\u0014f\u0001\u0001/a%\u0011q\u0006\u0002\u0002&%\u0006tGm\\7G_J,7\u000f^\"mCN\u001c\u0018NZ5dCRLwN\\*v[6\f'/_%na2L!!\r\u0003\u0003SI\u000bg\u000eZ8n\r>\u0014Xm\u001d;DY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8Ue\u0006Lg.\u001b8h'VlW.\u0019:z\u0001"
)
public interface RandomForestClassificationSummary extends ClassificationSummary {
   // $FF: synthetic method
   static BinaryRandomForestClassificationSummary asBinary$(final RandomForestClassificationSummary $this) {
      return $this.asBinary();
   }

   default BinaryRandomForestClassificationSummary asBinary() {
      if (this instanceof BinaryRandomForestClassificationSummary) {
         return (BinaryRandomForestClassificationSummary)this;
      } else {
         throw new RuntimeException("Cannot cast to a binary summary.");
      }
   }

   static void $init$(final RandomForestClassificationSummary $this) {
   }
}
