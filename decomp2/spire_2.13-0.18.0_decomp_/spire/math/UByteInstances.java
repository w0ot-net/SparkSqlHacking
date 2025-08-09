package spire.math;

import algebra.ring.CommutativeRig;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003\u0013\u0001\u0011\u00051\u0003C\u0004\u0018\u0001\t\u0007Iq\u0001\r\t\u000fu\u0002!\u0019!C\u0004}!9!\t\u0001b\u0001\n\u000f\u0019%AD+CsR,\u0017J\\:uC:\u001cWm\u001d\u0006\u0003\u000f!\tA!\\1uQ*\t\u0011\"A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0005\u0001a\u0001CA\u0007\u0011\u001b\u0005q!\"A\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Eq!AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002)A\u0011Q\"F\u0005\u0003-9\u0011A!\u00168ji\u0006aQKQ=uK\u0006cw-\u001a2sCV\t\u0011D\u0005\u0004\u001b9A\"tG\u000f\u0004\u00057\u0001\u0001\u0011D\u0001\u0007=e\u00164\u0017N\\3nK:$h\bE\u0002\u001eS1r!A\b\u0014\u000f\u0005}!cB\u0001\u0011$\u001b\u0005\t#B\u0001\u0012\u000b\u0003\u0019a$o\\8u}%\t\u0011\"\u0003\u0002&\u0011\u00059\u0011\r\\4fEJ\f\u0017BA\u0014)\u0003\u001d\u0001\u0018mY6bO\u0016T!!\n\u0005\n\u0005)Z#\u0001B\"SS\u001eT!a\n\u0015\u0011\u00055rS\"\u0001\u0004\n\u0005=2!!B+CsR,\u0007cA\u00193Y5\t\u0001&\u0003\u00024Q\tQ\u0011j]%oi\u0016<'/\u00197\u0011\u0007u)D&\u0003\u00027W\t\tBK];oG\u0006$X\r\u001a#jm&\u001c\u0018n\u001c8\u0011\u0007uAD&\u0003\u0002:W\t)2+[4oK\u0012\fE\rZ5uSZ,7)T8o_&$\u0007cA\u000f<Y%\u0011Ah\u000b\u0002\u0006\u001fJ$WM]\u0001\u000f+\nKH/\u001a\"jiN#(/\u001b8h+\u0005y\u0004cA\u0017AY%\u0011\u0011I\u0002\u0002\n\u0005&$8\u000b\u001e:j]\u001e\f\u0001\"\u0016\"zi\u0016$\u0016mZ\u000b\u0002\tB\u0019Q&\u0012\u0017\n\u0005\u00193!!\u0003(v[\n,'\u000fV1h\u0001"
)
public interface UByteInstances {
   void spire$math$UByteInstances$_setter_$UByteAlgebra_$eq(final CommutativeRig x$1);

   void spire$math$UByteInstances$_setter_$UByteBitString_$eq(final BitString x$1);

   void spire$math$UByteInstances$_setter_$UByteTag_$eq(final NumberTag x$1);

   CommutativeRig UByteAlgebra();

   BitString UByteBitString();

   NumberTag UByteTag();

   static void $init$(final UByteInstances $this) {
      $this.spire$math$UByteInstances$_setter_$UByteAlgebra_$eq(new UByteAlgebra());
      $this.spire$math$UByteInstances$_setter_$UByteBitString_$eq(new UByteBitString());
      $this.spire$math$UByteInstances$_setter_$UByteTag_$eq(new NumberTag.UnsignedIntTag(new UByte(UByte$.MODULE$.MinValue()), new UByte(UByte$.MODULE$.MaxValue())));
   }
}
