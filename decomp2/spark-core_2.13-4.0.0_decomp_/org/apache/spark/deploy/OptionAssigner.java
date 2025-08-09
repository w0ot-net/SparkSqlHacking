package org.apache.spark.deploy;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-g\u0001B\u0016-\tVB\u0001b\u0013\u0001\u0003\u0016\u0004%\t\u0001\u0014\u0005\t+\u0002\u0011\t\u0012)A\u0005\u001b\"Aa\u000b\u0001BK\u0002\u0013\u0005q\u000b\u0003\u0005\\\u0001\tE\t\u0015!\u0003Y\u0011!a\u0006A!f\u0001\n\u00039\u0006\u0002C/\u0001\u0005#\u0005\u000b\u0011\u0002-\t\u0011y\u0003!Q3A\u0005\u00021C\u0001b\u0018\u0001\u0003\u0012\u0003\u0006I!\u0014\u0005\tA\u0002\u0011)\u001a!C\u0001\u0019\"A\u0011\r\u0001B\tB\u0003%Q\n\u0003\u0005c\u0001\tU\r\u0011\"\u0001d\u0011!Q\u0007A!E!\u0002\u0013!\u0007\"B6\u0001\t\u0003a\u0007bB;\u0001\u0003\u0003%\tA\u001e\u0005\b{\u0002\t\n\u0011\"\u0001\u007f\u0011%\t\u0019\u0002AI\u0001\n\u0003\t)\u0002C\u0005\u0002\u001a\u0001\t\n\u0011\"\u0001\u0002\u0016!A\u00111\u0004\u0001\u0012\u0002\u0013\u0005a\u0010\u0003\u0005\u0002\u001e\u0001\t\n\u0011\"\u0001\u007f\u0011%\ty\u0002AI\u0001\n\u0003\t\t\u0003C\u0005\u0002&\u0001\t\t\u0011\"\u0011\u0002(!A\u0011q\u0007\u0001\u0002\u0002\u0013\u0005q\u000bC\u0005\u0002:\u0001\t\t\u0011\"\u0001\u0002<!I\u0011q\t\u0001\u0002\u0002\u0013\u0005\u0013\u0011\n\u0005\n\u0003/\u0002\u0011\u0011!C\u0001\u00033B\u0011\"a\u0019\u0001\u0003\u0003%\t%!\u001a\t\u0013\u0005%\u0004!!A\u0005B\u0005-\u0004\"CA7\u0001\u0005\u0005I\u0011IA8\u0011%\t\t\bAA\u0001\n\u0003\n\u0019hB\u0005\u0002x1\n\t\u0011#\u0003\u0002z\u0019A1\u0006LA\u0001\u0012\u0013\tY\b\u0003\u0004l?\u0011\u0005\u00111\u0013\u0005\n\u0003[z\u0012\u0011!C#\u0003_B\u0011\"!& \u0003\u0003%\t)a&\t\u0011\u0005\u0015v$%A\u0005\u0002yD\u0001\"a* #\u0003%\tA \u0005\n\u0003S{\u0012\u0013!C\u0001\u0003CA\u0011\"a+ \u0003\u0003%\t)!,\t\u0011\u0005mv$%A\u0005\u0002yD\u0001\"!0 #\u0003%\tA \u0005\n\u0003\u007f{\u0012\u0013!C\u0001\u0003CA\u0011\"!1 \u0003\u0003%I!a1\u0003\u001d=\u0003H/[8o\u0003N\u001c\u0018n\u001a8fe*\u0011QFL\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005=\u0002\u0014!B:qCJ\\'BA\u00193\u0003\u0019\t\u0007/Y2iK*\t1'A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001mqz\u0004CA\u001c;\u001b\u0005A$\"A\u001d\u0002\u000bM\u001c\u0017\r\\1\n\u0005mB$AB!osJ+g\r\u0005\u00028{%\u0011a\b\u000f\u0002\b!J|G-^2u!\t\u0001\u0005J\u0004\u0002B\r:\u0011!)R\u0007\u0002\u0007*\u0011A\tN\u0001\u0007yI|w\u000e\u001e \n\u0003eJ!a\u0012\u001d\u0002\u000fA\f7m[1hK&\u0011\u0011J\u0013\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u000fb\nQA^1mk\u0016,\u0012!\u0014\t\u0003\u001dJs!a\u0014)\u0011\u0005\tC\u0014BA)9\u0003\u0019\u0001&/\u001a3fM&\u00111\u000b\u0016\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005EC\u0014A\u0002<bYV,\u0007%\u0001\bdYV\u001cH/\u001a:NC:\fw-\u001a:\u0016\u0003a\u0003\"aN-\n\u0005iC$aA%oi\u0006y1\r\\;ti\u0016\u0014X*\u00198bO\u0016\u0014\b%\u0001\u0006eKBdw._'pI\u0016\f1\u0002Z3qY>LXj\u001c3fA\u0005A1\r\\(qi&|g.A\u0005dY>\u0003H/[8oA\u000591m\u001c8g\u0017\u0016L\u0018\u0001C2p]\u001a\\U-\u001f\u0011\u0002\u000f5,'oZ3G]V\tA\rE\u00028K\u001eL!A\u001a\u001d\u0003\r=\u0003H/[8o!\u00159\u0004.T'N\u0013\tI\u0007HA\u0005Gk:\u001cG/[8oe\u0005AQ.\u001a:hK\u001as\u0007%\u0001\u0004=S:LGO\u0010\u000b\b[>\u0004\u0018O]:u!\tq\u0007!D\u0001-\u0011\u0015YU\u00021\u0001N\u0011\u00151V\u00021\u0001Y\u0011\u0015aV\u00021\u0001Y\u0011\u001dqV\u0002%AA\u00025Cq\u0001Y\u0007\u0011\u0002\u0003\u0007Q\nC\u0004c\u001bA\u0005\t\u0019\u00013\u0002\t\r|\u0007/\u001f\u000b\b[^D\u0018P_>}\u0011\u001dYe\u0002%AA\u00025CqA\u0016\b\u0011\u0002\u0003\u0007\u0001\fC\u0004]\u001dA\u0005\t\u0019\u0001-\t\u000fys\u0001\u0013!a\u0001\u001b\"9\u0001M\u0004I\u0001\u0002\u0004i\u0005b\u00022\u000f!\u0003\u0005\r\u0001Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005y(fA'\u0002\u0002-\u0012\u00111\u0001\t\u0005\u0003\u000b\ty!\u0004\u0002\u0002\b)!\u0011\u0011BA\u0006\u0003%)hn\u00195fG.,GMC\u0002\u0002\u000ea\n!\"\u00198o_R\fG/[8o\u0013\u0011\t\t\"a\u0002\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u0005]!f\u0001-\u0002\u0002\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u001a\u0014AD2paf$C-\u001a4bk2$H\u0005N\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00136\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIY*\"!a\t+\u0007\u0011\f\t!A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003S\u0001B!a\u000b\u000265\u0011\u0011Q\u0006\u0006\u0005\u0003_\t\t$\u0001\u0003mC:<'BAA\u001a\u0003\u0011Q\u0017M^1\n\u0007M\u000bi#\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005u\u00121\t\t\u0004o\u0005}\u0012bAA!q\t\u0019\u0011I\\=\t\u0011\u0005\u0015s#!AA\u0002a\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA&!\u0019\ti%a\u0015\u0002>5\u0011\u0011q\n\u0006\u0004\u0003#B\u0014AC2pY2,7\r^5p]&!\u0011QKA(\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005m\u0013\u0011\r\t\u0004o\u0005u\u0013bAA0q\t9!i\\8mK\u0006t\u0007\"CA#3\u0005\u0005\t\u0019AA\u001f\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005%\u0012q\r\u0005\t\u0003\u000bR\u0012\u0011!a\u00011\u0006A\u0001.Y:i\u0007>$W\rF\u0001Y\u0003!!xn\u0015;sS:<GCAA\u0015\u0003\u0019)\u0017/^1mgR!\u00111LA;\u0011%\t)%HA\u0001\u0002\u0004\ti$\u0001\bPaRLwN\\!tg&<g.\u001a:\u0011\u00059|2#B\u0010\u0002~\u0005%\u0005cCA@\u0003\u000bk\u0005\fW'NI6l!!!!\u000b\u0007\u0005\r\u0005(A\u0004sk:$\u0018.\\3\n\t\u0005\u001d\u0015\u0011\u0011\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:4\u0004\u0003BAF\u0003#k!!!$\u000b\t\u0005=\u0015\u0011G\u0001\u0003S>L1!SAG)\t\tI(A\u0003baBd\u0017\u0010F\u0007n\u00033\u000bY*!(\u0002 \u0006\u0005\u00161\u0015\u0005\u0006\u0017\n\u0002\r!\u0014\u0005\u0006-\n\u0002\r\u0001\u0017\u0005\u00069\n\u0002\r\u0001\u0017\u0005\b=\n\u0002\n\u00111\u0001N\u0011\u001d\u0001'\u0005%AA\u00025CqA\u0019\u0012\u0011\u0002\u0003\u0007A-A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00135\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012*\u0014aD1qa2LH\u0005Z3gCVdG\u000f\n\u001c\u0002\u000fUt\u0017\r\u001d9msR!\u0011qVA\\!\u00119T-!-\u0011\u0013]\n\u0019,\u0014-Y\u001b6#\u0017bAA[q\t1A+\u001e9mKZB\u0001\"!/'\u0003\u0003\u0005\r!\\\u0001\u0004q\u0012\u0002\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$C'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%N\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001c\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005\u0015\u0007\u0003BA\u0016\u0003\u000fLA!!3\u0002.\t1qJ\u00196fGR\u0004"
)
public class OptionAssigner implements Product, Serializable {
   private final String value;
   private final int clusterManager;
   private final int deployMode;
   private final String clOption;
   private final String confKey;
   private final Option mergeFn;

   public static Option $lessinit$greater$default$6() {
      return OptionAssigner$.MODULE$.$lessinit$greater$default$6();
   }

   public static String $lessinit$greater$default$5() {
      return OptionAssigner$.MODULE$.$lessinit$greater$default$5();
   }

   public static String $lessinit$greater$default$4() {
      return OptionAssigner$.MODULE$.$lessinit$greater$default$4();
   }

   public static Option unapply(final OptionAssigner x$0) {
      return OptionAssigner$.MODULE$.unapply(x$0);
   }

   public static Option apply$default$6() {
      return OptionAssigner$.MODULE$.apply$default$6();
   }

   public static String apply$default$5() {
      return OptionAssigner$.MODULE$.apply$default$5();
   }

   public static String apply$default$4() {
      return OptionAssigner$.MODULE$.apply$default$4();
   }

   public static OptionAssigner apply(final String value, final int clusterManager, final int deployMode, final String clOption, final String confKey, final Option mergeFn) {
      return OptionAssigner$.MODULE$.apply(value, clusterManager, deployMode, clOption, confKey, mergeFn);
   }

   public static Function1 tupled() {
      return OptionAssigner$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return OptionAssigner$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String value() {
      return this.value;
   }

   public int clusterManager() {
      return this.clusterManager;
   }

   public int deployMode() {
      return this.deployMode;
   }

   public String clOption() {
      return this.clOption;
   }

   public String confKey() {
      return this.confKey;
   }

   public Option mergeFn() {
      return this.mergeFn;
   }

   public OptionAssigner copy(final String value, final int clusterManager, final int deployMode, final String clOption, final String confKey, final Option mergeFn) {
      return new OptionAssigner(value, clusterManager, deployMode, clOption, confKey, mergeFn);
   }

   public String copy$default$1() {
      return this.value();
   }

   public int copy$default$2() {
      return this.clusterManager();
   }

   public int copy$default$3() {
      return this.deployMode();
   }

   public String copy$default$4() {
      return this.clOption();
   }

   public String copy$default$5() {
      return this.confKey();
   }

   public Option copy$default$6() {
      return this.mergeFn();
   }

   public String productPrefix() {
      return "OptionAssigner";
   }

   public int productArity() {
      return 6;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.value();
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.clusterManager());
         }
         case 2 -> {
            return BoxesRunTime.boxToInteger(this.deployMode());
         }
         case 3 -> {
            return this.clOption();
         }
         case 4 -> {
            return this.confKey();
         }
         case 5 -> {
            return this.mergeFn();
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
      return x$1 instanceof OptionAssigner;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "value";
         }
         case 1 -> {
            return "clusterManager";
         }
         case 2 -> {
            return "deployMode";
         }
         case 3 -> {
            return "clOption";
         }
         case 4 -> {
            return "confKey";
         }
         case 5 -> {
            return "mergeFn";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.value()));
      var1 = Statics.mix(var1, this.clusterManager());
      var1 = Statics.mix(var1, this.deployMode());
      var1 = Statics.mix(var1, Statics.anyHash(this.clOption()));
      var1 = Statics.mix(var1, Statics.anyHash(this.confKey()));
      var1 = Statics.mix(var1, Statics.anyHash(this.mergeFn()));
      return Statics.finalizeHash(var1, 6);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var12;
      if (this != x$1) {
         label79: {
            if (x$1 instanceof OptionAssigner) {
               OptionAssigner var4 = (OptionAssigner)x$1;
               if (this.clusterManager() == var4.clusterManager() && this.deployMode() == var4.deployMode()) {
                  label72: {
                     String var10000 = this.value();
                     String var5 = var4.value();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label72;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label72;
                     }

                     var10000 = this.clOption();
                     String var6 = var4.clOption();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label72;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label72;
                     }

                     var10000 = this.confKey();
                     String var7 = var4.confKey();
                     if (var10000 == null) {
                        if (var7 != null) {
                           break label72;
                        }
                     } else if (!var10000.equals(var7)) {
                        break label72;
                     }

                     Option var11 = this.mergeFn();
                     Option var8 = var4.mergeFn();
                     if (var11 == null) {
                        if (var8 != null) {
                           break label72;
                        }
                     } else if (!var11.equals(var8)) {
                        break label72;
                     }

                     if (var4.canEqual(this)) {
                        break label79;
                     }
                  }
               }
            }

            var12 = false;
            return var12;
         }
      }

      var12 = true;
      return var12;
   }

   public OptionAssigner(final String value, final int clusterManager, final int deployMode, final String clOption, final String confKey, final Option mergeFn) {
      this.value = value;
      this.clusterManager = clusterManager;
      this.deployMode = deployMode;
      this.clOption = clOption;
      this.confKey = confKey;
      this.mergeFn = mergeFn;
      Product.$init$(this);
   }
}
