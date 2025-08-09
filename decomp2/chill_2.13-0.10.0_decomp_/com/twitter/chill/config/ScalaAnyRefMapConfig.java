package com.twitter.chill.config;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Option.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Null;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005<QAD\b\t\u0002a1QAG\b\t\u0002mAQAI\u0001\u0005\u0002\rBQ\u0001J\u0001\u0005\u0002\u0015BQaX\u0001\u0005\u0002\u00014AAG\b\u0001O!A1&\u0002B\u0001B\u0003%A\u0006C\u0003#\u000b\u0011\u0005q\u0007C\u0004:\u000b\u0001\u0007I\u0011\u0002\u001e\t\u000fm*\u0001\u0019!C\u0005y!1!)\u0002Q!\n1BQaQ\u0003\u0005\u0002iBQ\u0001R\u0003\u0005B\u0015CQaS\u0003\u0005B1\u000bAcU2bY\u0006\fe.\u001f*fM6\u000b\u0007oQ8oM&<'B\u0001\t\u0012\u0003\u0019\u0019wN\u001c4jO*\u0011!cE\u0001\u0006G\"LG\u000e\u001c\u0006\u0003)U\tq\u0001^<jiR,'OC\u0001\u0017\u0003\r\u0019w.\\\u0002\u0001!\tI\u0012!D\u0001\u0010\u0005Q\u00196-\u00197b\u0003:L(+\u001a4NCB\u001cuN\u001c4jON\u0011\u0011\u0001\b\t\u0003;\u0001j\u0011A\b\u0006\u0002?\u0005)1oY1mC&\u0011\u0011E\b\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005A\u0012!B1qa2LHC\u0001\u0014Q!\tIRa\u0005\u0002\u0006QA\u0011\u0011$K\u0005\u0003U=\u0011aaQ8oM&<\u0017AA5o!\u0011iC\u0007\b\u000f\u000f\u00059\u0012\u0004CA\u0018\u001f\u001b\u0005\u0001$BA\u0019\u0018\u0003\u0019a$o\\8u}%\u00111GH\u0001\u0007!J,G-\u001a4\n\u0005U2$aA'ba*\u00111G\b\u000b\u0003MaBQaK\u0004A\u00021\nAaY8oMV\tA&\u0001\u0005d_:4w\fJ3r)\ti\u0004\t\u0005\u0002\u001e}%\u0011qH\b\u0002\u0005+:LG\u000fC\u0004B\u0013\u0005\u0005\t\u0019\u0001\u0017\u0002\u0007a$\u0013'A\u0003d_:4\u0007%A\u0003u_6\u000b\u0007/A\u0002hKR$\"AR%\u0011\u00055:\u0015B\u0001%7\u0005\u0019\u0019FO]5oO\")!\n\u0004a\u0001\r\u0006\t1.A\u0002tKR$2!P'O\u0011\u0015QU\u00021\u0001G\u0011\u0015yU\u00021\u0001G\u0003\u00051\b\"B)\u0004\u0001\u0004\u0011\u0016!A71\u0007M3V\f\u0005\u0003.iQc\u0006CA+W\u0019\u0001!\u0011b\u0016)\u0002\u0002\u0003\u0005)\u0011\u0001-\u0003\u0007}#\u0013'\u0005\u0002Z9A\u0011QDW\u0005\u00037z\u0011qAT8uQ&tw\r\u0005\u0002V;\u0012Ia\fUA\u0001\u0002\u0003\u0015\t\u0001\u0017\u0002\u0004?\u0012\u0012\u0014!B3naRLX#\u0001\u0014"
)
public class ScalaAnyRefMapConfig extends Config {
   private Map conf;

   public static ScalaAnyRefMapConfig empty() {
      return ScalaAnyRefMapConfig$.MODULE$.empty();
   }

   public static ScalaAnyRefMapConfig apply(final Map m) {
      return ScalaAnyRefMapConfig$.MODULE$.apply(m);
   }

   private Map conf() {
      return this.conf;
   }

   private void conf_$eq(final Map x$1) {
      this.conf = x$1;
   }

   public Map toMap() {
      return this.conf();
   }

   public String get(final String k) {
      return (String)this.conf().get(k).map((x$1) -> x$1.toString()).getOrElse(() -> null);
   }

   public void set(final String k, final String v) {
      Option var4 = .MODULE$.apply(v);
      if (var4 instanceof Some) {
         this.conf_$eq((Map)this.conf().$plus(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(k), v)));
         BoxedUnit var3 = BoxedUnit.UNIT;
      } else {
         if (!scala.None..MODULE$.equals(var4)) {
            throw new MatchError(var4);
         }

         this.conf_$eq((Map)this.conf().$minus(k));
         BoxedUnit var5 = BoxedUnit.UNIT;
      }

   }

   public ScalaAnyRefMapConfig(final Map in) {
      this.conf = in;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
