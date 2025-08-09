package org.apache.spark.scheduler;

import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Me!B\u0011#\u0001\u0012R\u0003\u0002\u0003\"\u0001\u0005+\u0007I\u0011A\"\t\u00131\u0003!\u0011#Q\u0001\n\u0011k\u0005\u0002C(\u0001\u0005+\u0007I\u0011\u0001)\t\u0011Q\u0003!\u0011#Q\u0001\nEC\u0001\"\u0016\u0001\u0003\u0016\u0004%\tA\u0016\u0005\t5\u0002\u0011\t\u0012)A\u0005/\")1\f\u0001C\u00019\"9\u0011\rAA\u0001\n\u0003\u0011\u0007b\u00024\u0001#\u0003%\ta\u001a\u0005\be\u0002\t\n\u0011\"\u0001t\u0011\u001d)\b!%A\u0005\u0002YDq\u0001\u001f\u0001\u0002\u0002\u0013\u0005\u0013\u0010C\u0005\u0002\u0004\u0001\t\t\u0011\"\u0001\u0002\u0006!I\u0011Q\u0002\u0001\u0002\u0002\u0013\u0005\u0011q\u0002\u0005\n\u00037\u0001\u0011\u0011!C!\u0003;A\u0011\"a\u000b\u0001\u0003\u0003%\t!!\f\t\u0013\u0005E\u0002!!A\u0005B\u0005M\u0002\"CA\u001c\u0001\u0005\u0005I\u0011IA\u001d\u0011%\tY\u0004AA\u0001\n\u0003\nid\u0002\u0006\u0002B\t\n\t\u0011#\u0001%\u0003\u00072\u0011\"\t\u0012\u0002\u0002#\u0005A%!\u0012\t\rm+B\u0011AA/\u0011%\ty&FA\u0001\n\u000b\n\t\u0007C\u0005\u0002dU\t\t\u0011\"!\u0002f!A\u0011QN\u000b\u0012\u0002\u0013\u0005q\r\u0003\u0005\u0002pU\t\n\u0011\"\u0001t\u0011!\t\t(FI\u0001\n\u00031\b\"CA:+\u0005\u0005I\u0011QA;\u0011!\t\u0019)FI\u0001\n\u00039\u0007\u0002CAC+E\u0005I\u0011A:\t\u0011\u0005\u001dU#%A\u0005\u0002YD\u0011\"!#\u0016\u0003\u0003%I!a#\u0003'\u0015CXmY;u_J\u0004&o\\2fgNdun\u001d;\u000b\u0005\r\"\u0013!C:dQ\u0016$W\u000f\\3s\u0015\t)c%A\u0003ta\u0006\u00148N\u0003\u0002(Q\u00051\u0011\r]1dQ\u0016T\u0011!K\u0001\u0004_J<7\u0003\u0002\u0001,_U\u0002\"\u0001L\u0017\u000e\u0003\tJ!A\f\u0012\u0003%\u0015CXmY;u_Jdun]:SK\u0006\u001cxN\u001c\t\u0003aMj\u0011!\r\u0006\u0002e\u0005)1oY1mC&\u0011A'\r\u0002\b!J|G-^2u!\t1tH\u0004\u00028{9\u0011\u0001\bP\u0007\u0002s)\u0011!hO\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t!'\u0003\u0002?c\u00059\u0001/Y2lC\u001e,\u0017B\u0001!B\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tq\u0014'\u0001\u0005`[\u0016\u001c8/Y4f+\u0005!\u0005CA#J\u001d\t1u\t\u0005\u00029c%\u0011\u0001*M\u0001\u0007!J,G-\u001a4\n\u0005)[%AB*ue&twM\u0003\u0002Ic\u0005Iq,\\3tg\u0006<W\rI\u0005\u0003\u001d6\nq!\\3tg\u0006<W-\u0001\u0006x_J\\WM\u001d%pgR,\u0012!\u0015\t\u0004aI#\u0015BA*2\u0005\u0019y\u0005\u000f^5p]\u0006Yqo\u001c:lKJDun\u001d;!\u0003-\u0019\u0017-^:fI\nK\u0018\t\u001d9\u0016\u0003]\u0003\"\u0001\r-\n\u0005e\u000b$a\u0002\"p_2,\u0017M\\\u0001\rG\u0006,8/\u001a3Cs\u0006\u0003\b\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\tusv\f\u0019\t\u0003Y\u0001AqAQ\u0004\u0011\u0002\u0003\u0007A\tC\u0004P\u000fA\u0005\t\u0019A)\t\u000fU;\u0001\u0013!a\u0001/\u0006!1m\u001c9z)\u0011i6\rZ3\t\u000f\tC\u0001\u0013!a\u0001\t\"9q\n\u0003I\u0001\u0002\u0004\t\u0006bB+\t!\u0003\u0005\raV\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005A'F\u0001#jW\u0005Q\u0007CA6q\u001b\u0005a'BA7o\u0003%)hn\u00195fG.,GM\u0003\u0002pc\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005Ed'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#\u0001;+\u0005EK\u0017AD2paf$C-\u001a4bk2$HeM\u000b\u0002o*\u0012q+[\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003i\u00042a_A\u0001\u001b\u0005a(BA?\u007f\u0003\u0011a\u0017M\\4\u000b\u0003}\fAA[1wC&\u0011!\n`\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003\u000f\u00012\u0001MA\u0005\u0013\r\tY!\r\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003#\t9\u0002E\u00021\u0003'I1!!\u00062\u0005\r\te.\u001f\u0005\n\u00033q\u0011\u0011!a\u0001\u0003\u000f\t1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u0010!\u0019\t\t#a\n\u0002\u00125\u0011\u00111\u0005\u0006\u0004\u0003K\t\u0014AC2pY2,7\r^5p]&!\u0011\u0011FA\u0012\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0007]\u000by\u0003C\u0005\u0002\u001aA\t\t\u00111\u0001\u0002\u0012\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\rQ\u0018Q\u0007\u0005\n\u00033\t\u0012\u0011!a\u0001\u0003\u000f\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u000f\ta!Z9vC2\u001cHcA,\u0002@!I\u0011\u0011D\n\u0002\u0002\u0003\u0007\u0011\u0011C\u0001\u0014\u000bb,7-\u001e;peB\u0013xnY3tg2{7\u000f\u001e\t\u0003YU\u0019R!FA$\u0003'\u0002\u0002\"!\u0013\u0002P\u0011\u000bv+X\u0007\u0003\u0003\u0017R1!!\u00142\u0003\u001d\u0011XO\u001c;j[\u0016LA!!\u0015\u0002L\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u001a\u0011\t\u0005U\u00131L\u0007\u0003\u0003/R1!!\u0017\u007f\u0003\tIw.C\u0002A\u0003/\"\"!a\u0011\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012A_\u0001\u0006CB\u0004H.\u001f\u000b\b;\u0006\u001d\u0014\u0011NA6\u0011\u001d\u0011\u0005\u0004%AA\u0002\u0011Cqa\u0014\r\u0011\u0002\u0003\u0007\u0011\u000bC\u0004V1A\u0005\t\u0019A,\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIE\nq\"\u00199qYf$C-\u001a4bk2$HEM\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%g\u00059QO\\1qa2LH\u0003BA<\u0003\u007f\u0002B\u0001\r*\u0002zA1\u0001'a\u001fE#^K1!! 2\u0005\u0019!V\u000f\u001d7fg!A\u0011\u0011\u0011\u000f\u0002\u0002\u0003\u0007Q,A\u0002yIA\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\n\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$#'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HeM\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u001b\u00032a_AH\u0013\r\t\t\n \u0002\u0007\u001f\nTWm\u0019;"
)
public class ExecutorProcessLost extends ExecutorLossReason implements Product {
   private final Option workerHost;
   private final boolean causedByApp;

   public static boolean $lessinit$greater$default$3() {
      return ExecutorProcessLost$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option $lessinit$greater$default$2() {
      return ExecutorProcessLost$.MODULE$.$lessinit$greater$default$2();
   }

   public static String $lessinit$greater$default$1() {
      return ExecutorProcessLost$.MODULE$.$lessinit$greater$default$1();
   }

   public static Option unapply(final ExecutorProcessLost x$0) {
      return ExecutorProcessLost$.MODULE$.unapply(x$0);
   }

   public static boolean apply$default$3() {
      return ExecutorProcessLost$.MODULE$.apply$default$3();
   }

   public static Option apply$default$2() {
      return ExecutorProcessLost$.MODULE$.apply$default$2();
   }

   public static String apply$default$1() {
      return ExecutorProcessLost$.MODULE$.apply$default$1();
   }

   public static ExecutorProcessLost apply(final String _message, final Option workerHost, final boolean causedByApp) {
      return ExecutorProcessLost$.MODULE$.apply(_message, workerHost, causedByApp);
   }

   public static Function1 tupled() {
      return ExecutorProcessLost$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ExecutorProcessLost$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String _message() {
      return super.message();
   }

   public Option workerHost() {
      return this.workerHost;
   }

   public boolean causedByApp() {
      return this.causedByApp;
   }

   public ExecutorProcessLost copy(final String _message, final Option workerHost, final boolean causedByApp) {
      return new ExecutorProcessLost(_message, workerHost, causedByApp);
   }

   public String copy$default$1() {
      return this._message();
   }

   public Option copy$default$2() {
      return this.workerHost();
   }

   public boolean copy$default$3() {
      return this.causedByApp();
   }

   public String productPrefix() {
      return "ExecutorProcessLost";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this._message();
         }
         case 1 -> {
            return this.workerHost();
         }
         case 2 -> {
            return BoxesRunTime.boxToBoolean(this.causedByApp());
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ExecutorProcessLost;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "_message";
         }
         case 1 -> {
            return "workerHost";
         }
         case 2 -> {
            return "causedByApp";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this._message()));
      var1 = Statics.mix(var1, Statics.anyHash(this.workerHost()));
      var1 = Statics.mix(var1, this.causedByApp() ? 1231 : 1237);
      return Statics.finalizeHash(var1, 3);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof ExecutorProcessLost) {
               ExecutorProcessLost var4 = (ExecutorProcessLost)x$1;
               if (this.causedByApp() == var4.causedByApp()) {
                  label52: {
                     String var10000 = this._message();
                     String var5 = var4._message();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     Option var7 = this.workerHost();
                     Option var6 = var4.workerHost();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label52;
                        }
                     } else if (!var7.equals(var6)) {
                        break label52;
                     }

                     if (var4.canEqual(this)) {
                        break label59;
                     }
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public ExecutorProcessLost(final String _message, final Option workerHost, final boolean causedByApp) {
      super(_message);
      this.workerHost = workerHost;
      this.causedByApp = causedByApp;
      Product.$init$(this);
   }
}
