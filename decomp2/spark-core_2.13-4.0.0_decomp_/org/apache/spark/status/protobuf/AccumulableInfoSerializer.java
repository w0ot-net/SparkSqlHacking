package org.apache.spark.status.protobuf;

import java.util.List;
import org.apache.spark.status.api.v1.AccumulableInfo;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r;a!\u0002\u0004\t\u0002\u0019\u0001bA\u0002\n\u0007\u0011\u000311\u0003C\u0003\u001b\u0003\u0011\u0005A\u0004C\u0003\u001e\u0003\u0011\u0005a\u0004C\u00030\u0003\u0011\u0005\u0001'A\rBG\u000e,X.\u001e7bE2,\u0017J\u001c4p'\u0016\u0014\u0018.\u00197ju\u0016\u0014(BA\u0004\t\u0003!\u0001(o\u001c;pEV4'BA\u0005\u000b\u0003\u0019\u0019H/\u0019;vg*\u00111\u0002D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001b9\ta!\u00199bG\",'\"A\b\u0002\u0007=\u0014x\r\u0005\u0002\u0012\u00035\taAA\rBG\u000e,X.\u001e7bE2,\u0017J\u001c4p'\u0016\u0014\u0018.\u00197ju\u0016\u00148CA\u0001\u0015!\t)\u0002$D\u0001\u0017\u0015\u00059\u0012!B:dC2\f\u0017BA\r\u0017\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u0011\u0003%\u0019XM]5bY&TX\r\u0006\u0002 MA\u0011\u0001e\t\b\u0003#\u0005J!A\t\u0004\u0002\u0015M#xN]3UsB,7/\u0003\u0002%K\ty\u0011iY2v[Vd\u0017M\u00197f\u0013:4wN\u0003\u0002#\r!)qe\u0001a\u0001Q\u0005)\u0011N\u001c9viB\u0011\u0011FL\u0007\u0002U)\u00111\u0006L\u0001\u0003mFR!!\f\u0005\u0002\u0007\u0005\u0004\u0018.\u0003\u0002%U\u0005YA-Z:fe&\fG.\u001b>f)\t\t\u0014\bE\u00023o!j\u0011a\r\u0006\u0003iU\nq!\\;uC\ndWM\u0003\u00027-\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005a\u001a$aC!se\u0006L()\u001e4gKJDQA\u000f\u0003A\u0002m\nq!\u001e9eCR,7\u000fE\u0002=\u0003~i\u0011!\u0010\u0006\u0003}}\nA!\u001e;jY*\t\u0001)\u0001\u0003kCZ\f\u0017B\u0001\">\u0005\u0011a\u0015n\u001d;"
)
public final class AccumulableInfoSerializer {
   public static ArrayBuffer deserialize(final List updates) {
      return AccumulableInfoSerializer$.MODULE$.deserialize(updates);
   }

   public static StoreTypes.AccumulableInfo serialize(final AccumulableInfo input) {
      return AccumulableInfoSerializer$.MODULE$.serialize(input);
   }
}
