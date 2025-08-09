package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.lang.invoke.SerializedLambda;
import scala.Product;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005U2Aa\u0001\u0003\u0005#!)Q\u0004\u0001C\u0001=!)\u0011\u0005\u0001C\u0001E\tyA+\u001e9mKN+'/[1mSj,'O\u0003\u0002\u0006\r\u0005\u00191/\u001a:\u000b\u0005\u001dA\u0011!B:dC2\f'BA\u0005\u000b\u0003\u0019iw\u000eZ;mK*\u00111\u0002D\u0001\bU\u0006\u001c7n]8o\u0015\tia\"A\u0005gCN$XM\u001d=nY*\tq\"A\u0002d_6\u001c\u0001a\u0005\u0002\u0001%A\u00191C\u0006\r\u000e\u0003QQ!!\u0006\u0006\u0002\u0011\u0011\fG/\u00192j]\u0012L!a\u0006\u000b\u0003\u001d)\u001bxN\\*fe&\fG.\u001b>feB\u0011\u0011dG\u0007\u00025)\tq!\u0003\u0002\u001d5\t9\u0001K]8ek\u000e$\u0018A\u0002\u001fj]&$h\bF\u0001 !\t\u0001\u0003!D\u0001\u0005\u0003%\u0019XM]5bY&TX\r\u0006\u0003$M!\u0002\u0004CA\r%\u0013\t)#D\u0001\u0003V]&$\b\"B\u0014\u0003\u0001\u0004A\u0012!\u0002<bYV,\u0007\"B\u0015\u0003\u0001\u0004Q\u0013\u0001\u00026hK:\u0004\"a\u000b\u0018\u000e\u00031R!!\f\u0006\u0002\t\r|'/Z\u0005\u0003_1\u0012QBS:p]\u001e+g.\u001a:bi>\u0014\b\"B\u0019\u0003\u0001\u0004\u0011\u0014\u0001\u00039s_ZLG-\u001a:\u0011\u0005M\u0019\u0014B\u0001\u001b\u0015\u0005I\u0019VM]5bY&TXM\u001d)s_ZLG-\u001a:"
)
public class TupleSerializer extends JsonSerializer {
   public void serialize(final Product value, final JsonGenerator jgen, final SerializerProvider provider) {
      jgen.writeStartArray();
      value.productIterator().foreach((x$1) -> {
         $anonfun$serialize$1(provider, jgen, x$1);
         return BoxedUnit.UNIT;
      });
      jgen.writeEndArray();
   }

   // $FF: synthetic method
   public static final void $anonfun$serialize$1(final SerializerProvider provider$1, final JsonGenerator jgen$1, final Object x$1) {
      provider$1.defaultSerializeValue(x$1, jgen$1);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
