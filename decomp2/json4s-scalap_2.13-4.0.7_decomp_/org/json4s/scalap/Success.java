package org.json4s.scalap;

import java.io.Serializable;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t}a\u0001\u0002\u0011\"\u0001\"B\u0001B\u0014\u0001\u0003\u0016\u0004%\ta\u0014\u0005\t!\u0002\u0011\t\u0012)A\u0005]!A\u0011\u000b\u0001BK\u0002\u0013\u0005!\u000b\u0003\u0005T\u0001\tE\t\u0015!\u0003=\u0011\u0015!\u0006\u0001\"\u0001V\u0011\u0015I\u0006\u0001\"\u0001[\u0011\u0015Y\u0006\u0001\"\u0001]\u0011\u0015\u0001\u0007\u0001\"\u0001b\u0011\u0015a\u0007\u0001\"\u0001n\u0011\u0015\u0001\u0007\u0001\"\u0001v\u0011\u001d\t9\u0001\u0001C\u0001\u0003\u0013Aq!a\u0007\u0001\t\u0003\ti\u0002C\u0005\u0002:\u0001\t\t\u0011\"\u0001\u0002<!I\u0011Q\n\u0001\u0012\u0002\u0013\u0005\u0011q\n\u0005\n\u0003W\u0002\u0011\u0013!C\u0001\u0003[B\u0011\"a\u001e\u0001\u0003\u0003%\t%!\u001f\t\u0013\u0005-\u0005!!A\u0005\u0002\u00055\u0005\"CAK\u0001\u0005\u0005I\u0011AAL\u0011%\ti\nAA\u0001\n\u0003\ny\nC\u0005\u0002.\u0002\t\t\u0011\"\u0001\u00020\"I\u0011\u0011\u0018\u0001\u0002\u0002\u0013\u0005\u00131\u0018\u0005\n\u0003\u007f\u0003\u0011\u0011!C!\u0003\u0003D\u0011\"a1\u0001\u0003\u0003%\t%!2\t\u0013\u0005\u001d\u0007!!A\u0005B\u0005%w!CAgC\u0005\u0005\t\u0012AAh\r!\u0001\u0013%!A\t\u0002\u0005E\u0007B\u0002+\u001b\t\u0003\t\u0019\u000fC\u0005\u0002Dj\t\t\u0011\"\u0012\u0002F\"I\u0011Q\u001d\u000e\u0002\u0002\u0013\u0005\u0015q\u001d\u0005\n\u0003sT\u0012\u0011!CA\u0003wD\u0011B!\u0006\u001b\u0003\u0003%IAa\u0006\u0003\u000fM+8mY3tg*\u0011!eI\u0001\u0007g\u000e\fG.\u00199\u000b\u0005\u0011*\u0013A\u00026t_:$4OC\u0001'\u0003\ry'oZ\u0002\u0001+\rI\u0003'P\n\u0005\u0001)z$\tE\u0003,Y9b4'D\u0001\"\u0013\ti\u0013E\u0001\u0004SKN,H\u000e\u001e\t\u0003_Ab\u0001\u0001\u0002\u00042\u0001\u0011\u0015\rA\r\u0002\u0004\u001fV$\u0018CA\u001a:!\t!t'D\u00016\u0015\u00051\u0014!B:dC2\f\u0017B\u0001\u001d6\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u000e\u001e\n\u0005m*$aA!osB\u0011q&\u0010\u0003\u0007}\u0001!)\u0019\u0001\u001a\u0003\u0003\u0005\u0003\"\u0001\u000e!\n\u0005\u0005+$a\u0002)s_\u0012,8\r\u001e\t\u0003\u0007.s!\u0001R%\u000f\u0005\u0015CU\"\u0001$\u000b\u0005\u001d;\u0013A\u0002\u001fs_>$h(C\u00017\u0013\tQU'A\u0004qC\u000e\\\u0017mZ3\n\u00051k%\u0001D*fe&\fG.\u001b>bE2,'B\u0001&6\u0003\ryW\u000f^\u000b\u0002]\u0005!q.\u001e;!\u0003\u00151\u0018\r\\;f+\u0005a\u0014A\u0002<bYV,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0004-^C\u0006\u0003B\u0016\u0001]qBQAT\u0003A\u00029BQ!U\u0003A\u0002q\nQ!\u001a:s_J,\u0012aM\u0001\ti>|\u0005\u000f^5p]V\tQ\fE\u00025=rJ!aX\u001b\u0003\tM{W.Z\u0001\u0004[\u0006\u0004XC\u00012f)\t\u0019w\rE\u0003,Y9\"7\u0007\u0005\u00020K\u0012)a\r\u0003b\u0001e\t\t!\tC\u0003i\u0011\u0001\u0007\u0011.A\u0001g!\u0011!$\u000e\u00103\n\u0005-,$!\u0003$v]\u000e$\u0018n\u001c82\u0003\u0019i\u0017\r](viV\u0011a.\u001d\u000b\u0003_N\u0004Ra\u000b\u0017qyM\u0002\"aL9\u0005\u000bIL!\u0019\u0001\u001a\u0003\t=+HO\r\u0005\u0006Q&\u0001\r\u0001\u001e\t\u0005i)t\u0003/F\u0002wsn$\"a\u001e?\u0011\t-\u0002\u0001P\u001f\t\u0003_e$QA\u001d\u0006C\u0002I\u0002\"aL>\u0005\u000b\u0019T!\u0019\u0001\u001a\t\u000b!T\u0001\u0019A?\u0011\rQrh\u0006PA\u0001\u0013\tyXGA\u0005Gk:\u001cG/[8oeA)A'a\u0001yu&\u0019\u0011QA\u001b\u0003\rQ+\b\u000f\\33\u0003\u001d1G.\u0019;NCB,b!a\u0003\u0002\u0012\u0005UA\u0003BA\u0007\u0003/\u0001ra\u000b\u0017\u0002\u0010\u0005M1\u0007E\u00020\u0003#!QA]\u0006C\u0002I\u00022aLA\u000b\t\u001517B1\u00013\u0011\u0019A7\u00021\u0001\u0002\u001aA1AG \u0018=\u0003\u001b\taa\u001c:FYN,WCBA\u0010\u0003K\tY\u0003\u0006\u0003\u0002\"\u0005=\u0002cB\u0016-\u0003G\tIc\r\t\u0004_\u0005\u0015BA\u0002:\r\u0005\u0004\t9#\u0005\u0002/sA\u0019q&a\u000b\u0005\r\u0019d!\u0019AA\u0017#\ta\u0014\b\u0003\u0005\u000221!\t\u0019AA\u001a\u0003\u0015yG\u000f[3s!\u0015!\u0014QGA\u0011\u0013\r\t9$\u000e\u0002\ty\tLh.Y7f}\u0005!1m\u001c9z+\u0019\ti$a\u0011\u0002HQ1\u0011qHA%\u0003\u0017\u0002ba\u000b\u0001\u0002B\u0005\u0015\u0003cA\u0018\u0002D\u0011)\u0011'\u0004b\u0001eA\u0019q&a\u0012\u0005\u000byj!\u0019\u0001\u001a\t\u00119k\u0001\u0013!a\u0001\u0003\u0003B\u0001\"U\u0007\u0011\u0002\u0003\u0007\u0011QI\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0019\t\t&a\u001a\u0002jU\u0011\u00111\u000b\u0016\u0004]\u0005U3FAA,!\u0011\tI&a\u0019\u000e\u0005\u0005m#\u0002BA/\u0003?\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005\u0005T'\u0001\u0006b]:|G/\u0019;j_:LA!!\u001a\u0002\\\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000bEr!\u0019\u0001\u001a\u0005\u000byr!\u0019\u0001\u001a\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU1\u0011qNA:\u0003k*\"!!\u001d+\u0007q\n)\u0006B\u00032\u001f\t\u0007!\u0007B\u0003?\u001f\t\u0007!'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003w\u0002B!! \u0002\b6\u0011\u0011q\u0010\u0006\u0005\u0003\u0003\u000b\u0019)\u0001\u0003mC:<'BAAC\u0003\u0011Q\u0017M^1\n\t\u0005%\u0015q\u0010\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005=\u0005c\u0001\u001b\u0002\u0012&\u0019\u00111S\u001b\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0007e\nI\nC\u0005\u0002\u001cJ\t\t\u00111\u0001\u0002\u0010\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!)\u0011\u000b\u0005\r\u0016\u0011V\u001d\u000e\u0005\u0005\u0015&bAATk\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005-\u0016Q\u0015\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u00022\u0006]\u0006c\u0001\u001b\u00024&\u0019\u0011QW\u001b\u0003\u000f\t{w\u000e\\3b]\"A\u00111\u0014\u000b\u0002\u0002\u0003\u0007\u0011(\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA>\u0003{C\u0011\"a'\u0016\u0003\u0003\u0005\r!a$\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a$\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\u001f\u0002\r\u0015\fX/\u00197t)\u0011\t\t,a3\t\u0011\u0005m\u0005$!AA\u0002e\nqaU;dG\u0016\u001c8\u000f\u0005\u0002,5M)!$a5\u0002ZB\u0019A'!6\n\u0007\u0005]WG\u0001\u0004B]f\u0014VM\u001a\t\u0005\u00037\f\t/\u0004\u0002\u0002^*!\u0011q\\AB\u0003\tIw.C\u0002M\u0003;$\"!a4\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\r\u0005%\u0018q^Az)\u0019\tY/!>\u0002xB11\u0006AAw\u0003c\u00042aLAx\t\u0015\tTD1\u00013!\ry\u00131\u001f\u0003\u0006}u\u0011\rA\r\u0005\u0007\u001dv\u0001\r!!<\t\rEk\u0002\u0019AAy\u0003\u001d)h.\u00199qYf,b!!@\u0003\n\t5A\u0003BA\u0000\u0005\u001f\u0001R\u0001\u000eB\u0001\u0005\u000bI1Aa\u00016\u0005\u0019y\u0005\u000f^5p]B9A'a\u0001\u0003\b\t-\u0001cA\u0018\u0003\n\u0011)\u0011G\bb\u0001eA\u0019qF!\u0004\u0005\u000byr\"\u0019\u0001\u001a\t\u0013\tEa$!AA\u0002\tM\u0011a\u0001=%aA11\u0006\u0001B\u0004\u0005\u0017\tAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"A!\u0007\u0011\t\u0005u$1D\u0005\u0005\u0005;\tyH\u0001\u0004PE*,7\r\u001e"
)
public class Success extends Result implements Product, Serializable {
   private final Object out;
   private final Object value;

   public static Option unapply(final Success x$0) {
      return Success$.MODULE$.unapply(x$0);
   }

   public static Success apply(final Object out, final Object value) {
      return Success$.MODULE$.apply(out, value);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Object out() {
      return this.out;
   }

   public Object value() {
      return this.value;
   }

   public Nothing error() {
      throw new ScalaSigParserError("No error");
   }

   public Some toOption() {
      return new Some(this.value());
   }

   public Result map(final Function1 f) {
      return new Success(this.out(), f.apply(this.value()));
   }

   public Result mapOut(final Function1 f) {
      return new Success(f.apply(this.out()), this.value());
   }

   public Success map(final Function2 f) {
      Tuple2 var3 = (Tuple2)f.apply(this.out(), this.value());
      if (var3 != null) {
         Object out2 = var3._1();
         Object b = var3._2();
         Success var2 = new Success(out2, b);
         return var2;
      } else {
         throw new MatchError(var3);
      }
   }

   public Result flatMap(final Function2 f) {
      return (Result)f.apply(this.out(), this.value());
   }

   public Result orElse(final Function0 other) {
      return this;
   }

   public Success copy(final Object out, final Object value) {
      return new Success(out, value);
   }

   public Object copy$default$1() {
      return this.out();
   }

   public Object copy$default$2() {
      return this.value();
   }

   public String productPrefix() {
      return "Success";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.out();
            break;
         case 1:
            var10000 = this.value();
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
      return x$1 instanceof Success;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "out";
            break;
         case 1:
            var10000 = "value";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label51: {
            boolean var2;
            if (x$1 instanceof Success) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Success var4 = (Success)x$1;
               if (BoxesRunTime.equals(this.out(), var4.out()) && BoxesRunTime.equals(this.value(), var4.value()) && var4.canEqual(this)) {
                  break label51;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public Success(final Object out, final Object value) {
      this.out = out;
      this.value = value;
      Product.$init$(this);
   }
}
