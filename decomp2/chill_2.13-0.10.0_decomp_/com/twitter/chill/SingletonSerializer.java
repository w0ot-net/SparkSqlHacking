package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=3A!\u0002\u0004\u0001\u001b!AQ\u0005\u0001B\u0001B\u0003%q\u0003C\u0003'\u0001\u0011\u0005q\u0005C\u0003+\u0001\u0011\u00051\u0006C\u0003;\u0001\u0011\u00051HA\nTS:<G.\u001a;p]N+'/[1mSj,'O\u0003\u0002\b\u0011\u0005)1\r[5mY*\u0011\u0011BC\u0001\bi^LG\u000f^3s\u0015\u0005Y\u0011aA2p[\u000e\u0001QC\u0001\b\u001a'\t\u0001q\u0002E\u0002\u0011)]q!!\u0005\n\u000e\u0003\u0019I!a\u0005\u0004\u0002\u000fA\f7m[1hK&\u0011QC\u0006\u0002\f\u0017N+'/[1mSj,'O\u0003\u0002\u0014\rA\u0011\u0001$\u0007\u0007\u0001\t\u0015Q\u0002A1\u0001\u001c\u0005\u0005!\u0016C\u0001\u000f#!\ti\u0002%D\u0001\u001f\u0015\u0005y\u0012!B:dC2\f\u0017BA\u0011\u001f\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!H\u0012\n\u0005\u0011r\"aA!os\u0006\u0019qN\u00196\u0002\rqJg.\u001b;?)\tA\u0013\u0006E\u0002\u0012\u0001]AQ!\n\u0002A\u0002]\tQa\u001e:ji\u0016$B\u0001L\u00185sA\u0011Q$L\u0005\u0003]y\u0011A!\u00168ji\")\u0001g\u0001a\u0001c\u0005!1n]3s!\t\u0001\"'\u0003\u00024-\t!1J]=p\u0011\u0015)4\u00011\u00017\u0003\ryW\u000f\u001e\t\u0003!]J!\u0001\u000f\f\u0003\r=+H\u000f];u\u0011\u0015)3\u00011\u0001\u0018\u0003\u0011\u0011X-\u00193\u0015\t]aTH\u0011\u0005\u0006a\u0011\u0001\r!\r\u0005\u0006}\u0011\u0001\raP\u0001\u0003S:\u0004\"\u0001\u0005!\n\u0005\u00053\"!B%oaV$\b\"B\"\u0005\u0001\u0004!\u0015aA2mgB\u0019Q\tT\f\u000f\u0005\u0019S\u0005CA$\u001f\u001b\u0005A%BA%\r\u0003\u0019a$o\\8u}%\u00111JH\u0001\u0007!J,G-\u001a4\n\u00055s%!B\"mCN\u001c(BA&\u001f\u0001"
)
public class SingletonSerializer extends Serializer {
   private final Object obj;

   public void write(final Kryo kser, final Output out, final Object obj) {
   }

   public Object read(final Kryo kser, final Input in, final Class cls) {
      return this.obj;
   }

   public SingletonSerializer(final Object obj) {
      this.obj = obj;
   }
}
