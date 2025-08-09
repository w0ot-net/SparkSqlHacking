package org.json4s;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=q!B\u0007\u000f\u0011\u0003\u0019b!B\u000b\u000f\u0011\u00031\u0002\"\u0002\u0017\u0002\t\u0003i\u0003b\u0002\u0018\u0002\u0003\u0003%Ia\f\u0004\u0006+9\t\tC\u000e\u0005\u0006Y\u0011!\t\u0001\u0014\u0003\u0006\u001d\u0012\u0011\ta\u0014\u0005\u0006-\u00121\ta\u0016\u0005\u00065\u0012!\ta\u0017\u0005\u0006?\u0012!\t\u0001\u0019\u0005\u0006M\u0012!\ta\u001a\u0005\u0006U\u0012!\ta\u001b\u0005\u0006_\u0012!\ta[\u0001\u0007\u0015Z\u000bG.^3\u000b\u0005=\u0001\u0012A\u00026t_:$4OC\u0001\u0012\u0003\ry'oZ\u0002\u0001!\t!\u0012!D\u0001\u000f\u0005\u0019Qe+\u00197vKN!\u0011aF\u000f%!\tA2$D\u0001\u001a\u0015\u0005Q\u0012!B:dC2\f\u0017B\u0001\u000f\u001a\u0005\u0019\te.\u001f*fMB\u0011a$\t\b\u0003)}I!\u0001\t\b\u0002\u000b5+'oZ3\n\u0005\t\u001a#!C'fe\u001e,\u0017M\u00197f\u0015\t\u0001c\u0002\u0005\u0002&U5\taE\u0003\u0002(Q\u0005\u0011\u0011n\u001c\u0006\u0002S\u0005!!.\u0019<b\u0013\tYcE\u0001\u0007TKJL\u0017\r\\5{C\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0002'\u0005aqO]5uKJ+\u0007\u000f\\1dKR\t\u0001\u0007\u0005\u00022i5\t!G\u0003\u00024Q\u0005!A.\u00198h\u0013\t)$G\u0001\u0004PE*,7\r^\n\u0006\t]9d(\u0011\t\u0003qmr!\u0001F\u001d\n\u0005ir\u0011\u0001\u0002#jM\u001aL!\u0001P\u001f\u0003\u0011\u0011KgMZ1cY\u0016T!A\u000f\b\u0011\u0005ay\u0014B\u0001!\u001a\u0005\u001d\u0001&o\u001c3vGR\u0004\"A\u0011&\u000f\u0005\rCeB\u0001#H\u001b\u0005)%B\u0001$\u0013\u0003\u0019a$o\\8u}%\t!$\u0003\u0002J3\u00059\u0001/Y2lC\u001e,\u0017BA\u0016L\u0015\tI\u0015\u0004F\u0001N!\t!BA\u0001\u0004WC2,Xm]\t\u0003!N\u0003\"\u0001G)\n\u0005IK\"a\u0002(pi\"Lgn\u001a\t\u00031QK!!V\r\u0003\u0007\u0005s\u00170\u0001\u0004wC2,Xm]\u000b\u00021B\u0011\u0011LB\u0007\u0002\t\u0005A1\r[5mIJ,g.F\u0001]!\r\u0011U,T\u0005\u0003=.\u0013A\u0001T5ti\u0006)\u0011\r\u001d9msR\u0011Q*\u0019\u0005\u0006E&\u0001\raY\u0001\u0002SB\u0011\u0001\u0004Z\u0005\u0003Kf\u00111!\u00138u\u0003)!\u0003\u000f\\;tIAdWo\u001d\u000b\u0003\u001b\"DQ!\u001b\u0006A\u00025\u000bQa\u001c;iKJ\f\u0001\u0002^8PaRLwN\\\u000b\u0002YB\u0019\u0001$\\'\n\u00059L\"AB(qi&|g.\u0001\u0004u_N{W.Z\u0015\u0010\tE\u001cXo^=|{~\f\u0019!a\u0002\u0002\f%\u0011!O\u0004\u0002\u0007\u0015\u0006\u0013(/Y=\n\u0005Qt!!\u0002&C_>d\u0017B\u0001<\u000f\u0005!QE)Z2j[\u0006d\u0017B\u0001=\u000f\u0005\u001dQEi\\;cY\u0016L!A\u001f\b\u0003\t)Ke\u000e^\u0005\u0003y:\u0011QA\u0013'p]\u001eT!A \b\u0002\u0011)su\u000e\u001e5j]\u001eT1!!\u0001\u000f\u0003\u0015Qe*\u001e7m\u0013\r\t)A\u0004\u0002\b\u0015>\u0013'.Z2u\u0013\r\tIA\u0004\u0002\u0005\u0015N+G/C\u0002\u0002\u000e9\u0011qAS*ue&tw\r"
)
public abstract class JValue implements Diff.Diffable, Product, Serializable {
   public static JValue j2m(final JValue json) {
      return JValue$.MODULE$.j2m(json);
   }

   public static MergeDep aaa() {
      return JValue$.MODULE$.aaa();
   }

   public static MergeDep ooo() {
      return JValue$.MODULE$.ooo();
   }

   public static MergeDep jjj() {
      return JValue$.MODULE$.jjj();
   }

   public Iterator productIterator() {
      return Product.productIterator$(this);
   }

   public String productPrefix() {
      return Product.productPrefix$(this);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Diff diff(final JValue other) {
      return Diff.Diffable.diff$(this, other);
   }

   public abstract Object values();

   public List children() {
      Object var1;
      if (this instanceof JObject) {
         JObject var3 = (JObject)this;
         List l = var3.obj();
         var1 = l.map((x$1) -> (JValue)x$1._2());
      } else if (this instanceof JArray) {
         JArray var5 = (JArray)this;
         List l = var5.arr();
         var1 = l;
      } else {
         var1 = .MODULE$.Nil();
      }

      return (List)var1;
   }

   public JValue apply(final int i) {
      return JNothing$.MODULE$;
   }

   public JValue $plus$plus(final JValue other) {
      return append$1(this, other);
   }

   public Option toOption() {
      boolean var2;
      if (JNothing$.MODULE$.equals(this)) {
         var2 = true;
      } else if (JNull$.MODULE$.equals(this)) {
         var2 = true;
      } else {
         var2 = false;
      }

      Object var1;
      if (var2) {
         var1 = scala.None..MODULE$;
      } else {
         var1 = new Some(this);
      }

      return (Option)var1;
   }

   public Option toSome() {
      Object var1;
      if (JNothing$.MODULE$.equals(this)) {
         var1 = scala.None..MODULE$;
      } else {
         var1 = new Some(this);
      }

      return (Option)var1;
   }

   private static final JValue append$1(final JValue value1, final JValue value2) {
      Tuple2 var3 = new Tuple2(value1, value2);
      Object var2;
      if (var3 != null) {
         JValue var4 = (JValue)var3._1();
         JValue x = (JValue)var3._2();
         if (JNothing$.MODULE$.equals(var4)) {
            var2 = x;
            return (JValue)var2;
         }
      }

      if (var3 != null) {
         JValue x = (JValue)var3._1();
         JValue var7 = (JValue)var3._2();
         if (JNothing$.MODULE$.equals(var7)) {
            var2 = x;
            return (JValue)var2;
         }
      }

      if (var3 != null) {
         JValue var8 = (JValue)var3._1();
         JValue var9 = (JValue)var3._2();
         if (var8 instanceof JArray) {
            JArray var10 = (JArray)var8;
            List xs = var10.arr();
            if (var9 instanceof JArray) {
               JArray var12 = (JArray)var9;
               List ys = var12.arr();
               var2 = new JArray(ys.$colon$colon$colon(xs));
               return (JValue)var2;
            }
         }
      }

      if (var3 != null) {
         JValue var15 = (JValue)var3._1();
         JValue v = (JValue)var3._2();
         if (var15 instanceof JArray) {
            JArray var17 = (JArray)var15;
            List xs = var17.arr();
            if (v != null) {
               var2 = new JArray(((List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new JValue[]{v}))).$colon$colon$colon(xs));
               return (JValue)var2;
            }
         }
      }

      if (var3 != null) {
         JValue v = (JValue)var3._1();
         JValue var22 = (JValue)var3._2();
         if (v != null && var22 instanceof JArray) {
            JArray var24 = (JArray)var22;
            List xs = var24.arr();
            var2 = new JArray(xs.$colon$colon(v));
            return (JValue)var2;
         }
      }

      if (var3 == null) {
         throw new MatchError(var3);
      } else {
         JValue x = (JValue)var3._1();
         JValue y = (JValue)var3._2();
         var2 = new JArray(.MODULE$.Nil().$colon$colon(y).$colon$colon(x));
         return (JValue)var2;
      }
   }

   public JValue() {
      Diff.Diffable.$init$(this);
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
