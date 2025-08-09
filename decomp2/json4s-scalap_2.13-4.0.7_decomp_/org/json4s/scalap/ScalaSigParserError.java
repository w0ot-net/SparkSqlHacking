package org.json4s.scalap;

import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mb\u0001B\u000b\u0017\u0001vA\u0001b\r\u0001\u0003\u0016\u0004%\t\u0001\u000e\u0005\t{\u0001\u0011\t\u0012)A\u0005k!)a\b\u0001C\u0001\u007f!91\tAA\u0001\n\u0003!\u0005b\u0002$\u0001#\u0003%\ta\u0012\u0005\b%\u0002\t\t\u0011\"\u0011T\u0011\u001dY\u0006!!A\u0005\u0002qCq\u0001\u0019\u0001\u0002\u0002\u0013\u0005\u0011\rC\u0004h\u0001\u0005\u0005I\u0011\t5\t\u000f=\u0004\u0011\u0011!C\u0001a\"9Q\u000fAA\u0001\n\u00032\bb\u0002=\u0001\u0003\u0003%\t%\u001f\u0005\bu\u0002\t\t\u0011\"\u0011|\u000f\u001dih#!A\t\u0002y4q!\u0006\f\u0002\u0002#\u0005q\u0010\u0003\u0004?\u001f\u0011\u0005\u0011q\u0003\u0005\n\u00033y\u0011\u0011!C#\u00037A\u0011\"!\b\u0010\u0003\u0003%\t)a\b\t\u0013\u0005\rr\"!A\u0005\u0002\u0006\u0015\u0002\"CA\u0019\u001f\u0005\u0005I\u0011BA\u001a\u0005M\u00196-\u00197b'&<\u0007+\u0019:tKJ,%O]8s\u0015\t9\u0002$\u0001\u0004tG\u0006d\u0017\r\u001d\u0006\u00033i\taA[:p]R\u001a(\"A\u000e\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001qB\u0006\r\t\u0003?%r!\u0001\t\u0014\u000f\u0005\u0005\"S\"\u0001\u0012\u000b\u0005\rb\u0012A\u0002\u001fs_>$h(C\u0001&\u0003\u0015\u00198-\u00197b\u0013\t9\u0003&A\u0004qC\u000e\\\u0017mZ3\u000b\u0003\u0015J!AK\u0016\u0003!I+h\u000e^5nK\u0016C8-\u001a9uS>t'BA\u0014)!\tic&D\u0001)\u0013\ty\u0003FA\u0004Qe>$Wo\u0019;\u0011\u0005}\t\u0014B\u0001\u001a,\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\ri7oZ\u000b\u0002kA\u0011aG\u000f\b\u0003oa\u0002\"!\t\u0015\n\u0005eB\u0013A\u0002)sK\u0012,g-\u0003\u0002<y\t11\u000b\u001e:j]\u001eT!!\u000f\u0015\u0002\t5\u001cx\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0001\u0013\u0005CA!\u0001\u001b\u00051\u0002\"B\u001a\u0004\u0001\u0004)\u0014\u0001B2paf$\"\u0001Q#\t\u000fM\"\u0001\u0013!a\u0001k\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u0001%+\u0005UJ5&\u0001&\u0011\u0005-\u0003V\"\u0001'\u000b\u00055s\u0015!C;oG\",7m[3e\u0015\ty\u0005&\u0001\u0006b]:|G/\u0019;j_:L!!\u0015'\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002)B\u0011QKW\u0007\u0002-*\u0011q\u000bW\u0001\u0005Y\u0006twMC\u0001Z\u0003\u0011Q\u0017M^1\n\u0005m2\u0016\u0001\u00049s_\u0012,8\r^!sSRLX#A/\u0011\u00055r\u0016BA0)\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\t\u0011W\r\u0005\u0002.G&\u0011A\r\u000b\u0002\u0004\u0003:L\bb\u00024\t\u0003\u0003\u0005\r!X\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003%\u00042A[7c\u001b\u0005Y'B\u00017)\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003].\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0011\u0011\u000f\u001e\t\u0003[IL!a\u001d\u0015\u0003\u000f\t{w\u000e\\3b]\"9aMCA\u0001\u0002\u0004\u0011\u0017A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$\"\u0001V<\t\u000f\u0019\\\u0011\u0011!a\u0001;\u0006A\u0001.Y:i\u0007>$W\rF\u0001^\u0003\u0019)\u0017/^1mgR\u0011\u0011\u000f \u0005\bM6\t\t\u00111\u0001c\u0003M\u00196-\u00197b'&<\u0007+\u0019:tKJ,%O]8s!\t\tubE\u0003\u0010\u0003\u0003\ti\u0001\u0005\u0004\u0002\u0004\u0005%Q\u0007Q\u0007\u0003\u0003\u000bQ1!a\u0002)\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\u0003\u0002\u0006\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0011\t\u0005=\u0011QC\u0007\u0003\u0003#Q1!a\u0005Y\u0003\tIw.C\u00023\u0003#!\u0012A`\u0001\ti>\u001cFO]5oOR\tA+A\u0003baBd\u0017\u0010F\u0002A\u0003CAQa\r\nA\u0002U\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002(\u00055\u0002\u0003B\u0017\u0002*UJ1!a\u000b)\u0005\u0019y\u0005\u000f^5p]\"A\u0011qF\n\u0002\u0002\u0003\u0007\u0001)A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u000e\u0011\u0007U\u000b9$C\u0002\u0002:Y\u0013aa\u00142kK\u000e$\b"
)
public class ScalaSigParserError extends RuntimeException implements Product {
   private final String msg;

   public static Option unapply(final ScalaSigParserError x$0) {
      return ScalaSigParserError$.MODULE$.unapply(x$0);
   }

   public static ScalaSigParserError apply(final String msg) {
      return ScalaSigParserError$.MODULE$.apply(msg);
   }

   public static Function1 andThen(final Function1 g) {
      return ScalaSigParserError$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ScalaSigParserError$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String msg() {
      return this.msg;
   }

   public ScalaSigParserError copy(final String msg) {
      return new ScalaSigParserError(msg);
   }

   public String copy$default$1() {
      return this.msg();
   }

   public String productPrefix() {
      return "ScalaSigParserError";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.msg();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ScalaSigParserError;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "msg";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var7;
      if (this != x$1) {
         label53: {
            boolean var2;
            if (x$1 instanceof ScalaSigParserError) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     ScalaSigParserError var4 = (ScalaSigParserError)x$1;
                     String var10000 = this.msg();
                     String var5 = var4.msg();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label35;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label35;
                     }

                     if (var4.canEqual(this)) {
                        var7 = true;
                        break label36;
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label53;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public ScalaSigParserError(final String msg) {
      super(msg);
      this.msg = msg;
      Product.$init$(this);
   }
}
