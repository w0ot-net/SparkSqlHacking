package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.linalg.Vector;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ed!\u0002\u000f\u001e\u0001\u0006:\u0003\u0002\u0003 \u0001\u0005+\u0007I\u0011A \t\u0011\r\u0003!\u0011#Q\u0001\n\u0001C\u0001\u0002\u0012\u0001\u0003\u0016\u0004%\ta\u0010\u0005\t\u000b\u0002\u0011\t\u0012)A\u0005\u0001\"Aa\t\u0001BK\u0002\u0013\u0005q\t\u0003\u0005O\u0001\tE\t\u0015!\u0003I\u0011\u0015y\u0005\u0001\"\u0001Q\u0011\u001d1\u0006!!A\u0005\u0002]Cqa\u0017\u0001\u0012\u0002\u0013\u0005A\fC\u0004h\u0001E\u0005I\u0011\u0001/\t\u000f!\u0004\u0011\u0013!C\u0001S\"91\u000eAA\u0001\n\u0003b\u0007bB;\u0001\u0003\u0003%\tA\u001e\u0005\bu\u0002\t\t\u0011\"\u0001|\u0011%\t\u0019\u0001AA\u0001\n\u0003\n)\u0001C\u0005\u0002\u0014\u0001\t\t\u0011\"\u0001\u0002\u0016!I\u0011q\u0004\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0005\u0005\n\u0003K\u0001\u0011\u0011!C!\u0003OA\u0011\"!\u000b\u0001\u0003\u0003%\t%a\u000b\t\u0013\u00055\u0002!!A\u0005B\u0005=rACA\u001a;\u0005\u0005\t\u0012A\u0011\u00026\u0019IA$HA\u0001\u0012\u0003\t\u0013q\u0007\u0005\u0007\u001fZ!\t!a\u0014\t\u0013\u0005%b#!A\u0005F\u0005-\u0002\"CA)-\u0005\u0005I\u0011QA*\u0011%\tYFFA\u0001\n\u0003\u000bi\u0006C\u0005\u0002pY\t\t\u0011\"\u0003\u0002r\tA\u0011J\\:uC:\u001cWM\u0003\u0002\u001f?\u00059a-Z1ukJ,'B\u0001\u0011\"\u0003\tiGN\u0003\u0002#G\u0005)1\u000f]1sW*\u0011A%J\u0001\u0007CB\f7\r[3\u000b\u0003\u0019\n1a\u001c:h'\u0011\u0001\u0001FL\u0019\u0011\u0005%bS\"\u0001\u0016\u000b\u0003-\nQa]2bY\u0006L!!\f\u0016\u0003\r\u0005s\u0017PU3g!\tIs&\u0003\u00021U\t9\u0001K]8ek\u000e$\bC\u0001\u001a<\u001d\t\u0019\u0014H\u0004\u00025q5\tQG\u0003\u00027o\u00051AH]8piz\u001a\u0001!C\u0001,\u0013\tQ$&A\u0004qC\u000e\\\u0017mZ3\n\u0005qj$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001e+\u0003\u0015a\u0017MY3m+\u0005\u0001\u0005CA\u0015B\u0013\t\u0011%F\u0001\u0004E_V\u0014G.Z\u0001\u0007Y\u0006\u0014W\r\u001c\u0011\u0002\r],\u0017n\u001a5u\u0003\u001d9X-[4ii\u0002\n\u0001BZ3biV\u0014Xm]\u000b\u0002\u0011B\u0011\u0011\nT\u0007\u0002\u0015*\u00111jH\u0001\u0007Y&t\u0017\r\\4\n\u00055S%A\u0002,fGR|'/A\u0005gK\u0006$XO]3tA\u00051A(\u001b8jiz\"B!U*U+B\u0011!\u000bA\u0007\u0002;!)ah\u0002a\u0001\u0001\")Ai\u0002a\u0001\u0001\")ai\u0002a\u0001\u0011\u0006!1m\u001c9z)\u0011\t\u0006,\u0017.\t\u000fyB\u0001\u0013!a\u0001\u0001\"9A\t\u0003I\u0001\u0002\u0004\u0001\u0005b\u0002$\t!\u0003\u0005\r\u0001S\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005i&F\u0001!_W\u0005y\u0006C\u00011f\u001b\u0005\t'B\u00012d\u0003%)hn\u00195fG.,GM\u0003\u0002eU\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\u0019\f'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012\u0014AD2paf$C-\u001a4bk2$HeM\u000b\u0002U*\u0012\u0001JX\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u00035\u0004\"A\\:\u000e\u0003=T!\u0001]9\u0002\t1\fgn\u001a\u0006\u0002e\u0006!!.\u0019<b\u0013\t!xN\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002oB\u0011\u0011\u0006_\u0005\u0003s*\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"\u0001`@\u0011\u0005%j\u0018B\u0001@+\u0005\r\te.\u001f\u0005\t\u0003\u0003q\u0011\u0011!a\u0001o\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a\u0002\u0011\u000b\u0005%\u0011q\u0002?\u000e\u0005\u0005-!bAA\u0007U\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005E\u00111\u0002\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\u0018\u0005u\u0001cA\u0015\u0002\u001a%\u0019\u00111\u0004\u0016\u0003\u000f\t{w\u000e\\3b]\"A\u0011\u0011\u0001\t\u0002\u0002\u0003\u0007A0\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,GcA7\u0002$!A\u0011\u0011A\t\u0002\u0002\u0003\u0007q/\u0001\u0005iCND7i\u001c3f)\u00059\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u00035\fa!Z9vC2\u001cH\u0003BA\f\u0003cA\u0001\"!\u0001\u0015\u0003\u0003\u0005\r\u0001`\u0001\t\u0013:\u001cH/\u00198dKB\u0011!KF\n\u0006-\u0005e\u0012Q\t\t\t\u0003w\t\t\u0005\u0011!I#6\u0011\u0011Q\b\u0006\u0004\u0003\u007fQ\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003\u0007\niDA\tBEN$(/Y2u\rVt7\r^5p]N\u0002B!a\u0012\u0002N5\u0011\u0011\u0011\n\u0006\u0004\u0003\u0017\n\u0018AA5p\u0013\ra\u0014\u0011\n\u000b\u0003\u0003k\tQ!\u00199qYf$r!UA+\u0003/\nI\u0006C\u0003?3\u0001\u0007\u0001\tC\u0003E3\u0001\u0007\u0001\tC\u0003G3\u0001\u0007\u0001*A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005}\u00131\u000e\t\u0006S\u0005\u0005\u0014QM\u0005\u0004\u0003GR#AB(qi&|g\u000e\u0005\u0004*\u0003O\u0002\u0005\tS\u0005\u0004\u0003SR#A\u0002+va2,7\u0007\u0003\u0005\u0002ni\t\t\u00111\u0001R\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003g\u00022A\\A;\u0013\r\t9h\u001c\u0002\u0007\u001f\nTWm\u0019;"
)
public class Instance implements Product, Serializable {
   private final double label;
   private final double weight;
   private final Vector features;

   public static Option unapply(final Instance x$0) {
      return Instance$.MODULE$.unapply(x$0);
   }

   public static Instance apply(final double label, final double weight, final Vector features) {
      return Instance$.MODULE$.apply(label, weight, features);
   }

   public static Function1 tupled() {
      return Instance$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return Instance$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double label() {
      return this.label;
   }

   public double weight() {
      return this.weight;
   }

   public Vector features() {
      return this.features;
   }

   public Instance copy(final double label, final double weight, final Vector features) {
      return new Instance(label, weight, features);
   }

   public double copy$default$1() {
      return this.label();
   }

   public double copy$default$2() {
      return this.weight();
   }

   public Vector copy$default$3() {
      return this.features();
   }

   public String productPrefix() {
      return "Instance";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToDouble(this.label());
         }
         case 1 -> {
            return BoxesRunTime.boxToDouble(this.weight());
         }
         case 2 -> {
            return this.features();
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
      return x$1 instanceof Instance;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "label";
         }
         case 1 -> {
            return "weight";
         }
         case 2 -> {
            return "features";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.label()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.weight()));
      var1 = Statics.mix(var1, Statics.anyHash(this.features()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof Instance) {
               Instance var4 = (Instance)x$1;
               if (this.label() == var4.label() && this.weight() == var4.weight()) {
                  label48: {
                     Vector var10000 = this.features();
                     Vector var5 = var4.features();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label48;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label48;
                     }

                     if (var4.canEqual(this)) {
                        break label55;
                     }
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

   public Instance(final double label, final double weight, final Vector features) {
      this.label = label;
      this.weight = weight;
      this.features = features;
      Product.$init$(this);
   }
}
