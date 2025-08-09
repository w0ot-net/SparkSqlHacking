package org.apache.spark.streaming.receiver;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Md!\u0002\f\u0018\u0001f\t\u0003\u0002\u0003\u001f\u0001\u0005+\u0007I\u0011A\u001f\t\u0011\u0019\u0003!\u0011#Q\u0001\nyBQA\u0014\u0001\u0005\u0002=CqA\u0016\u0001\u0002\u0002\u0013\u0005q\u000bC\u0004Z\u0001E\u0005I\u0011\u0001.\t\u000f)\u0004\u0011\u0011!C!W\"9A\u000fAA\u0001\n\u0003)\bbB=\u0001\u0003\u0003%\tA\u001f\u0005\b{\u0002\t\t\u0011\"\u0011\u007f\u0011!y\b!!A\u0005\u0002\u0005\u0005\u0001\"CA\u0006\u0001\u0005\u0005I\u0011IA\u0007\u0011%\t\t\u0002AA\u0001\n\u0003\n\u0019\u0002C\u0005\u0002\u0016\u0001\t\t\u0011\"\u0011\u0002\u0018!I\u0011\u0011\u0004\u0001\u0002\u0002\u0013\u0005\u00131D\u0004\u000b\u0003?9\u0012\u0011!E\u00013\u0005\u0005b!\u0003\f\u0018\u0003\u0003E\t!GA\u0012\u0011\u0019q\u0005\u0003\"\u0001\u0002D!I\u0011Q\u0003\t\u0002\u0002\u0013\u0015\u0013q\u0003\u0005\n\u0003\u000b\u0002\u0012\u0011!CA\u0003\u000fB\u0011\"a\u0015\u0011\u0003\u0003%\t)!\u0016\t\u0013\u0005%\u0004#!A\u0005\n\u0005-$!D%uKJ\fGo\u001c:CY>\u001c7N\u0003\u0002\u00193\u0005A!/Z2fSZ,'O\u0003\u0002\u001b7\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u00039u\tQa\u001d9be.T!AH\u0010\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0013aA8sON)\u0001A\t\u0015-_A\u00111EJ\u0007\u0002I)\tQ%A\u0003tG\u0006d\u0017-\u0003\u0002(I\t1\u0011I\\=SK\u001a\u0004\"!\u000b\u0016\u000e\u0003]I!aK\f\u0003\u001bI+7-Z5wK\u0012\u0014En\\2l!\t\u0019S&\u0003\u0002/I\t9\u0001K]8ek\u000e$\bC\u0001\u0019:\u001d\t\ttG\u0004\u00023m5\t1G\u0003\u00025k\u00051AH]8piz\u001a\u0001!C\u0001&\u0013\tAD%A\u0004qC\u000e\\\u0017mZ3\n\u0005iZ$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001d%\u0003!IG/\u001a:bi>\u0014X#\u0001 1\u0005}\"\u0005c\u0001\u0019A\u0005&\u0011\u0011i\u000f\u0002\t\u0013R,'/\u0019;peB\u00111\t\u0012\u0007\u0001\t%)%!!A\u0001\u0002\u000b\u0005qIA\u0002`II\n\u0011\"\u001b;fe\u0006$xN\u001d\u0011\u0012\u0005![\u0005CA\u0012J\u0013\tQEEA\u0004O_RD\u0017N\\4\u0011\u0005\rb\u0015BA'%\u0005\r\te._\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005A\u000b\u0006CA\u0015\u0001\u0011\u0015a4\u00011\u0001Sa\t\u0019V\u000bE\u00021\u0001R\u0003\"aQ+\u0005\u0013\u0015\u000b\u0016\u0011!A\u0001\u0006\u00039\u0015\u0001B2paf$\"\u0001\u0015-\t\u000fq\"\u0001\u0013!a\u0001%\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#A.+\u0005q\u000b\u0007cA/a\u00176\taL\u0003\u0002`I\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005\u0005s6&\u00012\u0011\u0005\rDW\"\u00013\u000b\u0005\u00154\u0017!C;oG\",7m[3e\u0015\t9G%\u0001\u0006b]:|G/\u0019;j_:L!!\u001b3\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002YB\u0011QN]\u0007\u0002]*\u0011q\u000e]\u0001\u0005Y\u0006twMC\u0001r\u0003\u0011Q\u0017M^1\n\u0005Mt'AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001w!\t\u0019s/\u0003\u0002yI\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u00111j\u001f\u0005\by\"\t\t\u00111\u0001w\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tA,\u0001\u0005dC:,\u0015/^1m)\u0011\t\u0019!!\u0003\u0011\u0007\r\n)!C\u0002\u0002\b\u0011\u0012qAQ8pY\u0016\fg\u000eC\u0004}\u0015\u0005\u0005\t\u0019A&\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004Y\u0006=\u0001b\u0002?\f\u0003\u0003\u0005\rA^\u0001\tQ\u0006\u001c\bnQ8eKR\ta/\u0001\u0005u_N#(/\u001b8h)\u0005a\u0017AB3rk\u0006d7\u000f\u0006\u0003\u0002\u0004\u0005u\u0001b\u0002?\u000f\u0003\u0003\u0005\raS\u0001\u000e\u0013R,'/\u0019;pe\ncwnY6\u0011\u0005%\u00022#\u0002\t\u0002&\u0005e\u0002cBA\u0014\u0003[\t\t\u0004U\u0007\u0003\u0003SQ1!a\u000b%\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\f\u0002*\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u00191\t\u0005M\u0012q\u0007\t\u0005a\u0001\u000b)\u0004E\u0002D\u0003o!\u0011\"\u0012\t\u0002\u0002\u0003\u0005)\u0011A$\u0011\t\u0005m\u0012\u0011I\u0007\u0003\u0003{Q1!a\u0010q\u0003\tIw.C\u0002;\u0003{!\"!!\t\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007A\u000bI\u0005\u0003\u0004='\u0001\u0007\u00111\n\u0019\u0005\u0003\u001b\n\t\u0006\u0005\u00031\u0001\u0006=\u0003cA\"\u0002R\u0011QQ)!\u0013\u0002\u0002\u0003\u0005)\u0011A$\u0002\u000fUt\u0017\r\u001d9msR!\u0011qKA3!\u0015\u0019\u0013\u0011LA/\u0013\r\tY\u0006\n\u0002\u0007\u001fB$\u0018n\u001c81\t\u0005}\u00131\r\t\u0005a\u0001\u000b\t\u0007E\u0002D\u0003G\"\u0011\"\u0012\u000b\u0002\u0002\u0003\u0005)\u0011A$\t\u0011\u0005\u001dD#!AA\u0002A\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ti\u0007E\u0002n\u0003_J1!!\u001do\u0005\u0019y%M[3di\u0002"
)
public class IteratorBlock implements ReceivedBlock, Product, Serializable {
   private final Iterator iterator;

   public static Option unapply(final IteratorBlock x$0) {
      return IteratorBlock$.MODULE$.unapply(x$0);
   }

   public static IteratorBlock apply(final Iterator iterator) {
      return IteratorBlock$.MODULE$.apply(iterator);
   }

   public static Function1 andThen(final Function1 g) {
      return IteratorBlock$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return IteratorBlock$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Iterator iterator() {
      return this.iterator;
   }

   public IteratorBlock copy(final Iterator iterator) {
      return new IteratorBlock(iterator);
   }

   public Iterator copy$default$1() {
      return this.iterator();
   }

   public String productPrefix() {
      return "IteratorBlock";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.iterator();
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
      return x$1 instanceof IteratorBlock;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "iterator";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof IteratorBlock) {
               label40: {
                  IteratorBlock var4 = (IteratorBlock)x$1;
                  Iterator var10000 = this.iterator();
                  Iterator var5 = var4.iterator();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
                  }
               }
            }

            var6 = false;
            return var6;
         }
      }

      var6 = true;
      return var6;
   }

   public IteratorBlock(final Iterator iterator) {
      this.iterator = iterator;
      Product.$init$(this);
   }
}
