package org.apache.spark.status.protobuf;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011;a!\u0002\u0004\t\u0002\u0019\u0001bA\u0002\n\u0007\u0011\u000311\u0003C\u0003\u001b\u0003\u0011\u0005A\u0004C\u0003\u001e\u0003\u0011\u0005a\u0004C\u0003A\u0003\u0011\u0005\u0011)\u0001\u000fEKR,'/\\5oSN$\u0018n\u0019'fm\u0016d7+\u001a:jC2L'0\u001a:\u000b\u0005\u001dA\u0011\u0001\u00039s_R|'-\u001e4\u000b\u0005%Q\u0011AB:uCR,8O\u0003\u0002\f\u0019\u0005)1\u000f]1sW*\u0011QBD\u0001\u0007CB\f7\r[3\u000b\u0003=\t1a\u001c:h!\t\t\u0012!D\u0001\u0007\u0005q!U\r^3s[&t\u0017n\u001d;jG2+g/\u001a7TKJL\u0017\r\\5{KJ\u001c\"!\u0001\u000b\u0011\u0005UAR\"\u0001\f\u000b\u0003]\tQa]2bY\u0006L!!\u0007\f\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}\r\u0001A#\u0001\t\u0002\u0013M,'/[1mSj,GCA\u00104!\t\u0001\u0003G\u0004\u0002\"]9\u0011!%\f\b\u0003G1r!\u0001J\u0016\u000f\u0005\u0015RcB\u0001\u0014*\u001b\u00059#B\u0001\u0015\u001c\u0003\u0019a$o\\8u}%\tq\"\u0003\u0002\u000e\u001d%\u00111\u0002D\u0005\u0003\u0013)I!a\u0002\u0005\n\u0005=2\u0011AC*u_J,G+\u001f9fg&\u0011\u0011G\r\u0002\u0013\t\u0016$XM]7j]&\u001cH/[2MKZ,GN\u0003\u00020\r!)Ag\u0001a\u0001k\u0005)\u0011N\u001c9viB\u0011a\u0007\u0010\b\u0003oij\u0011\u0001\u000f\u0006\u0003s)\t1A\u001d3e\u0013\tY\u0004(\u0001\nEKR,'/\\5oSN$\u0018n\u0019'fm\u0016d\u0017BA\u001f?\u0005\u00151\u0016\r\\;f\u0013\tydCA\u0006F]VlWM]1uS>t\u0017a\u00033fg\u0016\u0014\u0018.\u00197ju\u0016$\"!\u000e\"\t\u000b\r#\u0001\u0019A\u0010\u0002\r\tLg.\u0019:z\u0001"
)
public final class DeterministicLevelSerializer {
   public static Enumeration.Value deserialize(final StoreTypes.DeterministicLevel binary) {
      return DeterministicLevelSerializer$.MODULE$.deserialize(binary);
   }

   public static StoreTypes.DeterministicLevel serialize(final Enumeration.Value input) {
      return DeterministicLevelSerializer$.MODULE$.serialize(input);
   }
}
