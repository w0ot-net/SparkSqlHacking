package org.json4s;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.collection.Factory;
import scala.collection.immutable.List;
import scala.collection.mutable.Builder;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

@ScalaSignature(
   bytes = "\u0006\u0005a2\u0001b\u0001\u0003\u0011\u0002\u0007\u0005A\u0001\u0003\u0005\u0006\u001f\u0001!\t!\u0005\u0005\u0006+\u0001!\u0019A\u0006\u0002\u0010\t\u00164\u0017-\u001e7u%\u0016\fG-\u001a:ta)\u0011QAB\u0001\u0007UN|g\u000eN:\u000b\u0003\u001d\t1a\u001c:h'\t\u0001\u0011\u0002\u0005\u0002\u000b\u001b5\t1BC\u0001\r\u0003\u0015\u00198-\u00197b\u0013\tq1B\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\t!\u0003\u0005\u0002\u000b'%\u0011Ac\u0003\u0002\u0005+:LG/\u0001\bji\u0016\u0014\u0018M\u00197f%\u0016\fG-\u001a:\u0016\u0007]q2\u0006F\u0002\u0019[U\u00022!\u0007\u000e\u001d\u001b\u0005!\u0011BA\u000e\u0005\u0005\u0019\u0011V-\u00193feB\u0019QD\b\u0016\r\u0001\u0011)qD\u0001b\u0001A\t\ta)\u0006\u0002\"QE\u0011!%\n\t\u0003\u0015\rJ!\u0001J\u0006\u0003\u000f9{G\u000f[5oOB\u0011!BJ\u0005\u0003O-\u00111!\u00118z\t\u0015IcD1\u0001\"\u0005\u0011yF\u0005J\u0019\u0011\u0005uYC!\u0002\u0017\u0003\u0005\u0004\t#!\u0001,\t\u000b9\u0012\u00019A\u0018\u0002\u0003\u0019\u0004B\u0001M\u001a+95\t\u0011G\u0003\u00023\u0017\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005Q\n$a\u0002$bGR|'/\u001f\u0005\u0006m\t\u0001\u001daN\u0001\fm\u0006dW/\u001a*fC\u0012,'\u000fE\u0002\u001a5)\u0002"
)
public interface DefaultReaders0 {
   // $FF: synthetic method
   static Reader iterableReader$(final DefaultReaders0 $this, final Factory f, final Reader valueReader) {
      return $this.iterableReader(f, valueReader);
   }

   default Reader iterableReader(final Factory f, final Reader valueReader) {
      return Reader$.MODULE$.from((x0$1) -> {
         Object var3;
         if (x0$1 instanceof JArray) {
            JArray var5 = (JArray)x0$1;
            List items = var5.arr();
            Builder rights = f.newBuilder();
            Builder lefts = .MODULE$.List().newBuilder();
            items.foreach((v) -> {
               Either var5 = valueReader.readEither(v);
               Builder var4;
               if (var5 instanceof Right) {
                  Right var6 = (Right)var5;
                  Object a = var6.value();
                  var4 = (Builder)rights.$plus$eq(a);
               } else {
                  if (!(var5 instanceof Left)) {
                     throw new MatchError(var5);
                  }

                  Left var8 = (Left)var5;
                  MappingException a = (MappingException)var8.value();
                  var4 = (Builder)lefts.$plus$eq(a);
               }

               return var4;
            });
            List l = (List)lefts.result();
            var3 = l.isEmpty() ? .MODULE$.Right().apply(rights.result()) : .MODULE$.Left().apply(new MappingException.Multi(l));
         } else {
            var3 = .MODULE$.Left().apply(new MappingException((new StringBuilder(27)).append("Can't convert ").append(x0$1).append(" to Iterable.").toString()));
         }

         return (Either)var3;
      });
   }

   static void $init$(final DefaultReaders0 $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
