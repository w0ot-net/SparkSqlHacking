package com.twitter.chill.config;

import java.lang.invoke.SerializedLambda;
import scala.Predef.ArrowAssoc.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.Null;

@ScalaSignature(
   bytes = "\u0006\u0005Q;QAD\b\t\u0002a1QAG\b\t\u0002mAQAI\u0001\u0005\u0002\rBQ\u0001J\u0001\u0005\u0002\u0015BQAU\u0001\u0005\u0002M3AAG\b\u0001O!A1&\u0002B\u0001B\u0003%A\u0006C\u0003#\u000b\u0011\u0005!\bC\u0004=\u000b\u0001\u0007I\u0011B\u001f\t\u000fy*\u0001\u0019!C\u0005\u007f!1Q)\u0002Q!\n1BQAR\u0003\u0005\u0002uBQaR\u0003\u0005\u0002!CQaS\u0003\u0005\u00021\u000babU2bY\u0006l\u0015\r]\"p]\u001aLwM\u0003\u0002\u0011#\u000511m\u001c8gS\u001eT!AE\n\u0002\u000b\rD\u0017\u000e\u001c7\u000b\u0005Q)\u0012a\u0002;xSR$XM\u001d\u0006\u0002-\u0005\u00191m\\7\u0004\u0001A\u0011\u0011$A\u0007\u0002\u001f\tq1kY1mC6\u000b\u0007oQ8oM&<7CA\u0001\u001d!\ti\u0002%D\u0001\u001f\u0015\u0005y\u0012!B:dC2\f\u0017BA\u0011\u001f\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012\u0001G\u0001\u0006CB\u0004H.\u001f\u000b\u0003MA\u0003\"!G\u0003\u0014\u0005\u0015A\u0003CA\r*\u0013\tQsB\u0001\u0004D_:4\u0017nZ\u0001\u0003S:\u0004B!\f\u001b8o9\u0011aF\r\t\u0003_yi\u0011\u0001\r\u0006\u0003c]\ta\u0001\u0010:p_Rt\u0014BA\u001a\u001f\u0003\u0019\u0001&/\u001a3fM&\u0011QG\u000e\u0002\u0004\u001b\u0006\u0004(BA\u001a\u001f!\ti\u0003(\u0003\u0002:m\t11\u000b\u001e:j]\u001e$\"AJ\u001e\t\u000b-:\u0001\u0019\u0001\u0017\u0002\t\r|gNZ\u000b\u0002Y\u0005A1m\u001c8g?\u0012*\u0017\u000f\u0006\u0002A\u0007B\u0011Q$Q\u0005\u0003\u0005z\u0011A!\u00168ji\"9A)CA\u0001\u0002\u0004a\u0013a\u0001=%c\u0005)1m\u001c8gA\u0005)Ao\\'ba\u0006\u0019q-\u001a;\u0015\u0005]J\u0005\"\u0002&\r\u0001\u00049\u0014!A6\u0002\u0007M,G\u000fF\u0002A\u001b:CQAS\u0007A\u0002]BQaT\u0007A\u0002]\n\u0011A\u001e\u0005\u0006#\u000e\u0001\r\u0001L\u0001\u0002[\u0006)Q-\u001c9usV\ta\u0005"
)
public class ScalaMapConfig extends Config {
   private Map conf;

   public static ScalaMapConfig empty() {
      return ScalaMapConfig$.MODULE$.empty();
   }

   public static ScalaMapConfig apply(final Map m) {
      return ScalaMapConfig$.MODULE$.apply(m);
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
      return (String)this.conf().getOrElse(k, () -> null);
   }

   public void set(final String k, final String v) {
      if (v == null) {
         this.conf_$eq((Map)this.conf().$minus(k));
      } else {
         this.conf_$eq((Map)this.conf().$plus(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(k), v)));
      }

   }

   public ScalaMapConfig(final Map in) {
      this.conf = in;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
