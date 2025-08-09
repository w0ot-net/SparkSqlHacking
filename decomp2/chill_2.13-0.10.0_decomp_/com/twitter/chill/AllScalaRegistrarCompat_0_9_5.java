package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import scala.collection.immutable.Range;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000512Qa\u0001\u0003\u0001\t)AQa\u0006\u0001\u0005\u0002eAQa\u0007\u0001\u0005Bq\u0011Q$\u00117m'\u000e\fG.\u0019*fO&\u001cHO]1s\u0007>l\u0007/\u0019;`a}Kt,\u000e\u0006\u0003\u000b\u0019\tQa\u00195jY2T!a\u0002\u0005\u0002\u000fQ<\u0018\u000e\u001e;fe*\t\u0011\"A\u0002d_6\u001c2\u0001A\u0006\u0014!\ta\u0011#D\u0001\u000e\u0015\tqq\"\u0001\u0003mC:<'\"\u0001\t\u0002\t)\fg/Y\u0005\u0003%5\u0011aa\u00142kK\u000e$\bC\u0001\u000b\u0016\u001b\u0005!\u0011B\u0001\f\u0005\u00059I5J]=p%\u0016<\u0017n\u001d;sCJ\fa\u0001P5oSRt4\u0001\u0001\u000b\u00025A\u0011A\u0003A\u0001\u0006CB\u0004H.\u001f\u000b\u0003;\r\u0002\"AH\u0011\u000e\u0003}Q\u0011\u0001I\u0001\u0006g\u000e\fG.Y\u0005\u0003E}\u0011A!\u00168ji\")AE\u0001a\u0001K\u0005!a.Z<L!\t1\u0013F\u0004\u0002\u0015O%\u0011\u0001\u0006B\u0001\ba\u0006\u001c7.Y4f\u0013\tQ3F\u0001\u0003Lef|'B\u0001\u0015\u0005\u0001"
)
public class AllScalaRegistrarCompat_0_9_5 implements IKryoRegistrar {
   public void apply(final Kryo newK) {
      newK.register(Range.Exclusive.class);
   }
}
