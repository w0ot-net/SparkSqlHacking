package org.json4s;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Symbol;
import scala.Tuple2;
import scala.None.;
import scala.collection.Iterable;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055u!B\n\u0015\u0011\u0003Ib!B\u000e\u0015\u0011\u0003a\u0002bBA7\u0003\u0011\u0005\u0011qN\u0004\b\u0003c\n\u0001\u0012AA:\r\u001d\t9(\u0001E\u0001\u0003sBq!!\u001c\u0005\t\u0003\tYhB\u0004\u0002~\u0005A\t!a \u0007\u000f\u0005\u0005\u0015\u0001#\u0001\u0002\u0004\"9\u0011QN\u0004\u0005\u0002\u0005-eaB\u000e\u0015!\u0003\r\t\u0001\n\u0005\u0006Q%!\t!\u000b\u0005\u0006[%!\u0019A\f\u0005\u00063&!\u0019A\u0017\u0005\u0006c&!\u0019A\u001d\u0005\u0006}&!\u0019a \u0005\b\u0003#IA1AA\n\u0011\u001d\tY#\u0003C\u0002\u0003[Aq!a\u0010\n\t\u0007\t\t\u0005C\u0004\u0002N%!\u0019!a\u0014\u0002\u000f)\u001bxN\u001c#T\u0019*\u0011QCF\u0001\u0007UN|g\u000eN:\u000b\u0003]\t1a\u001c:h\u0007\u0001\u0001\"AG\u0001\u000e\u0003Q\u0011qAS:p]\u0012\u001bFjE\u0003\u0002;\r\n9\u0007\u0005\u0002\u001fC5\tqDC\u0001!\u0003\u0015\u00198-\u00197b\u0013\t\u0011sD\u0001\u0004B]f\u0014VM\u001a\t\u00035%\u00192!C\u000f&!\tQb%\u0003\u0002()\tI\u0011*\u001c9mS\u000eLGo]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003)\u0002\"AH\u0016\n\u00051z\"\u0001B+oSR\f!b]3re)4\u0018\r\\;f+\tys\b\u0006\u00021\u0017R\u0011\u0011\u0007\u000f\t\u0003eUr!AG\u001a\n\u0005Q\"\u0012a\u0002&t_:\f5\u000bV\u0005\u0003m]\u0012aAS!se\u0006L(B\u0001\u001b\u0015\u0011\u0015I4\u0002q\u0001;\u0003\t)g\u000f\u0005\u0003\u001fwuB\u0015B\u0001\u001f \u0005%1UO\\2uS>t\u0017\u0007\u0005\u0002?\u007f1\u0001A!\u0002!\f\u0005\u0004\t%!A!\u0012\u0005\t+\u0005C\u0001\u0010D\u0013\t!uDA\u0004O_RD\u0017N\\4\u0011\u0005y1\u0015BA$ \u0005\r\te.\u001f\t\u0003e%K!AS\u001c\u0003\r)3\u0016\r\\;f\u0011\u0015a5\u00021\u0001N\u0003\u0005\u0019\bc\u0001(W{9\u0011q\n\u0016\b\u0003!Nk\u0011!\u0015\u0006\u0003%b\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0011\n\u0005U{\u0012a\u00029bG.\fw-Z\u0005\u0003/b\u0013\u0001\"\u0013;fe\u0006\u0014G.\u001a\u0006\u0003+~\t!\"\\1qe)4\u0018\r\\;f+\tY6\r\u0006\u0002]IR\u0011Q\f\u0019\t\u0003eyK!aX\u001c\u0003\u000f){%M[3di\")\u0011\b\u0004a\u0002CB!ad\u000f2I!\tq4\rB\u0003A\u0019\t\u0007\u0011\tC\u0003f\u0019\u0001\u0007a-A\u0001n!\u001197N\u001c2\u000f\u0005!L\u0007C\u0001) \u0013\tQw$\u0001\u0004Qe\u0016$WMZ\u0005\u0003Y6\u00141!T1q\u0015\tQw\u0004\u0005\u0002h_&\u0011\u0001/\u001c\u0002\u0007'R\u0014\u0018N\\4\u0002\u001b=\u0004H/[8oe)4\u0018\r\\;f+\t\u0019\b\u0010\u0006\u0002usR\u0011\u0001*\u001e\u0005\u0006s5\u0001\u001dA\u001e\t\u0005=m:\b\n\u0005\u0002?q\u0012)\u0001)\u0004b\u0001\u0003\")!0\u0004a\u0001w\u0006\u0019q\u000e\u001d;\u0011\u0007yax/\u0003\u0002~?\t1q\n\u001d;j_:\fQb]=nE>d'G\u001b<bYV,G\u0003BA\u0001\u0003\u000f\u00012AMA\u0002\u0013\r\t)a\u000e\u0002\b\u0015N#(/\u001b8h\u0011\u001d\tIA\u0004a\u0001\u0003\u0017\t\u0011\u0001\u001f\t\u0004=\u00055\u0011bAA\b?\t11+_7c_2\f1\u0002]1jeJRg/\u00197vKV!\u0011QCA\u0010)\u0011\t9\"!\t\u0015\u0007u\u000bI\u0002\u0003\u0004:\u001f\u0001\u000f\u00111\u0004\t\u0006=m\ni\u0002\u0013\t\u0004}\u0005}A!\u0002!\u0010\u0005\u0004\t\u0005bBA\u0012\u001f\u0001\u0007\u0011QE\u0001\u0002iB1a$a\no\u0003;I1!!\u000b \u0005\u0019!V\u000f\u001d7fe\u0005YA.[:ue)4\u0018\r\\;f)\ri\u0016q\u0006\u0005\b\u0003c\u0001\u0002\u0019AA\u001a\u0003\u0005a\u0007#\u0002(\u00026\u0005e\u0012bAA\u001c1\n!A*[:u!\r\u0011\u00141H\u0005\u0004\u0003{9$A\u0002&GS\u0016dG-A\u0007k_\nTWm\u0019;3CN\u001cxn\u0019\u000b\u0005\u0003\u0007\nI\u0005E\u0002\u001b\u0003\u000bJ1!a\u0012\u0015\u00055Q5o\u001c8MSN$\u0018i]:pG\"1\u00111J\tA\u0002u\u000b\u0011a\\\u0001\u000ba\u0006L'OM!tg>\u001cW\u0003BA)\u0003;\"B!a\u0015\u0002dQ!\u0011QKA0!\u0015Q\u0012qKA.\u0013\r\tI\u0006\u0006\u0002\n\u0015N|g.Q:t_\u000e\u00042APA/\t\u0015\u0001%C1\u0001B\u0011\u0019I$\u0003q\u0001\u0002bA)adOA.\u0011\"9\u00111\u0005\nA\u0002\u0005\u0015\u0004C\u0002\u0010\u0002(9\fY\u0006E\u0002\u001b\u0003SJ1!a\u001b\u0015\u0005)!u.\u001e2mK6{G-Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003e\t!bV5uQ\u0012{WO\u00197f!\r\t)\bB\u0007\u0002\u0003\tQq+\u001b;i\t>,(\r\\3\u0014\u000b\u0011i2%a\u001a\u0015\u0005\u0005M\u0014AD,ji\"\u0014\u0015n\u001a#fG&l\u0017\r\u001c\t\u0004\u0003k:!AD,ji\"\u0014\u0015n\u001a#fG&l\u0017\r\\\n\u0006\u000fu\u0019\u0013Q\u0011\t\u00045\u0005\u001d\u0015bAAE)\tq!)[4EK\u000eLW.\u00197N_\u0012,GCAA@\u0001"
)
public interface JsonDSL extends Implicits {
   default JArray seq2jvalue(final Iterable s, final Function1 ev) {
      return JsonAST$.MODULE$.JArray().apply(s.toList().map(ev));
   }

   default JObject map2jvalue(final Map m, final Function1 ev) {
      return JsonAST$.MODULE$.JObject().apply(m.toList().map((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            Object v = x0$1._2();
            Tuple2 var2 = JsonAST$.MODULE$.JField().apply(k, (JValue)ev.apply(v));
            return var2;
         } else {
            throw new MatchError(x0$1);
         }
      }));
   }

   default JValue option2jvalue(final Option opt, final Function1 ev) {
      Object var3;
      if (opt instanceof Some) {
         Some var5 = (Some)opt;
         Object x = var5.value();
         var3 = (JValue)ev.apply(x);
      } else {
         if (!.MODULE$.equals(opt)) {
            throw new MatchError(opt);
         }

         var3 = JsonAST$.MODULE$.JNothing();
      }

      return (JValue)var3;
   }

   default JString symbol2jvalue(final Symbol x) {
      return JsonAST$.MODULE$.JString().apply(x.name());
   }

   default JObject pair2jvalue(final Tuple2 t, final Function1 ev) {
      return JsonAST$.MODULE$.JObject().apply((List)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{JsonAST$.MODULE$.JField().apply((String)t._1(), (JValue)ev.apply(t._2()))}))));
   }

   default JObject list2jvalue(final List l) {
      return JsonAST$.MODULE$.JObject().apply(l);
   }

   default List jobject2assoc(final JObject o) {
      return o.obj();
   }

   default Tuple2 pair2Assoc(final Tuple2 t, final Function1 ev) {
      return t;
   }

   static void $init$(final JsonDSL $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class WithDouble$ implements JsonDSL, DoubleMode {
      public static final WithDouble$ MODULE$ = new WithDouble$();

      static {
         Implicits.$init$(MODULE$);
         JsonDSL.$init$(MODULE$);
         DoubleMode.$init$(MODULE$);
      }

      public JValue double2jvalue(final double x) {
         return DoubleMode.double2jvalue$(this, x);
      }

      public JValue float2jvalue(final float x) {
         return DoubleMode.float2jvalue$(this, x);
      }

      public JValue bigdecimal2jvalue(final BigDecimal x) {
         return DoubleMode.bigdecimal2jvalue$(this, x);
      }

      public JArray seq2jvalue(final Iterable s, final Function1 ev) {
         return JsonDSL.super.seq2jvalue(s, ev);
      }

      public JObject map2jvalue(final Map m, final Function1 ev) {
         return JsonDSL.super.map2jvalue(m, ev);
      }

      public JValue option2jvalue(final Option opt, final Function1 ev) {
         return JsonDSL.super.option2jvalue(opt, ev);
      }

      public JString symbol2jvalue(final Symbol x) {
         return JsonDSL.super.symbol2jvalue(x);
      }

      public JObject pair2jvalue(final Tuple2 t, final Function1 ev) {
         return JsonDSL.super.pair2jvalue(t, ev);
      }

      public JObject list2jvalue(final List l) {
         return JsonDSL.super.list2jvalue(l);
      }

      public List jobject2assoc(final JObject o) {
         return JsonDSL.super.jobject2assoc(o);
      }

      public Tuple2 pair2Assoc(final Tuple2 t, final Function1 ev) {
         return JsonDSL.super.pair2Assoc(t, ev);
      }

      public JValue short2jvalue(final short x) {
         return Implicits.short2jvalue$(this, x);
      }

      public JValue byte2jvalue(final byte x) {
         return Implicits.byte2jvalue$(this, x);
      }

      public JValue char2jvalue(final char x) {
         return Implicits.char2jvalue$(this, x);
      }

      public JValue int2jvalue(final int x) {
         return Implicits.int2jvalue$(this, x);
      }

      public JValue long2jvalue(final long x) {
         return Implicits.long2jvalue$(this, x);
      }

      public JValue bigint2jvalue(final BigInt x) {
         return Implicits.bigint2jvalue$(this, x);
      }

      public JValue boolean2jvalue(final boolean x) {
         return Implicits.boolean2jvalue$(this, x);
      }

      public JValue string2jvalue(final String x) {
         return Implicits.string2jvalue$(this, x);
      }
   }

   public static class WithBigDecimal$ implements JsonDSL, BigDecimalMode {
      public static final WithBigDecimal$ MODULE$ = new WithBigDecimal$();

      static {
         Implicits.$init$(MODULE$);
         JsonDSL.$init$(MODULE$);
         BigDecimalMode.$init$(MODULE$);
      }

      public JValue double2jvalue(final double x) {
         return BigDecimalMode.double2jvalue$(this, x);
      }

      public JValue float2jvalue(final float x) {
         return BigDecimalMode.float2jvalue$(this, x);
      }

      public JValue bigdecimal2jvalue(final BigDecimal x) {
         return BigDecimalMode.bigdecimal2jvalue$(this, x);
      }

      public JArray seq2jvalue(final Iterable s, final Function1 ev) {
         return JsonDSL.super.seq2jvalue(s, ev);
      }

      public JObject map2jvalue(final Map m, final Function1 ev) {
         return JsonDSL.super.map2jvalue(m, ev);
      }

      public JValue option2jvalue(final Option opt, final Function1 ev) {
         return JsonDSL.super.option2jvalue(opt, ev);
      }

      public JString symbol2jvalue(final Symbol x) {
         return JsonDSL.super.symbol2jvalue(x);
      }

      public JObject pair2jvalue(final Tuple2 t, final Function1 ev) {
         return JsonDSL.super.pair2jvalue(t, ev);
      }

      public JObject list2jvalue(final List l) {
         return JsonDSL.super.list2jvalue(l);
      }

      public List jobject2assoc(final JObject o) {
         return JsonDSL.super.jobject2assoc(o);
      }

      public Tuple2 pair2Assoc(final Tuple2 t, final Function1 ev) {
         return JsonDSL.super.pair2Assoc(t, ev);
      }

      public JValue short2jvalue(final short x) {
         return Implicits.short2jvalue$(this, x);
      }

      public JValue byte2jvalue(final byte x) {
         return Implicits.byte2jvalue$(this, x);
      }

      public JValue char2jvalue(final char x) {
         return Implicits.char2jvalue$(this, x);
      }

      public JValue int2jvalue(final int x) {
         return Implicits.int2jvalue$(this, x);
      }

      public JValue long2jvalue(final long x) {
         return Implicits.long2jvalue$(this, x);
      }

      public JValue bigint2jvalue(final BigInt x) {
         return Implicits.bigint2jvalue$(this, x);
      }

      public JValue boolean2jvalue(final boolean x) {
         return Implicits.boolean2jvalue$(this, x);
      }

      public JValue string2jvalue(final String x) {
         return Implicits.string2jvalue$(this, x);
      }
   }
}
