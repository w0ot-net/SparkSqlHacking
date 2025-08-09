package org.apache.spark.scheduler;

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
   bytes = "\u0006\u0005\u0005ue!B\u000e\u001d\u0001r!\u0003\u0002C \u0001\u0005+\u0007I\u0011\u0001!\t\u0011%\u0003!\u0011#Q\u0001\n\u0005C\u0001\"\u0015\u0001\u0003\u0016\u0004%\tA\u0015\u0005\t-\u0002\u0011\t\u0012)A\u0005'\")q\u000b\u0001C\u00011\"9\u0001\rAA\u0001\n\u0003\t\u0007b\u00023\u0001#\u0003%\t!\u001a\u0005\bi\u0002\t\n\u0011\"\u0001v\u0011\u001d9\b!!A\u0005BaD\u0001\"a\u0001\u0001\u0003\u0003%\tA\u0015\u0005\n\u0003\u000b\u0001\u0011\u0011!C\u0001\u0003\u000fA\u0011\"!\u0004\u0001\u0003\u0003%\t%a\u0004\t\u0013\u0005u\u0001!!A\u0005\u0002\u0005}\u0001\"CA\u0015\u0001\u0005\u0005I\u0011IA\u0016\u0011%\ty\u0003AA\u0001\n\u0003\n\t\u0004C\u0005\u00024\u0001\t\t\u0011\"\u0011\u00026!I\u0011q\u0007\u0001\u0002\u0002\u0013\u0005\u0013\u0011H\u0004\u000b\u0003{a\u0012\u0011!E\u00019\u0005}b!C\u000e\u001d\u0003\u0003E\t\u0001HA!\u0011\u001996\u0003\"\u0001\u0002b!I\u00111G\n\u0002\u0002\u0013\u0015\u0013Q\u0007\u0005\n\u0003G\u001a\u0012\u0011!CA\u0003KB\u0001\"a\u001d\u0014#\u0003%\t!\u001e\u0005\n\u0003k\u001a\u0012\u0011!CA\u0003oB\u0001\"!%\u0014#\u0003%\t!\u001e\u0005\n\u0003'\u001b\u0012\u0011!C\u0005\u0003+\u0013\u0001d\u00159fGVd\u0017\r^5wKR\u000b7o[*vE6LG\u000f^3e\u0015\tib$A\u0005tG\",G-\u001e7fe*\u0011q\u0004I\u0001\u0006gB\f'o\u001b\u0006\u0003C\t\na!\u00199bG\",'\"A\u0012\u0002\u0007=\u0014xmE\u0003\u0001K-z#\u0007\u0005\u0002'S5\tqEC\u0001)\u0003\u0015\u00198-\u00197b\u0013\tQsE\u0001\u0004B]f\u0014VM\u001a\t\u0003Y5j\u0011\u0001H\u0005\u0003]q\u0011\u0011\u0003R!H'\u000eDW\rZ;mKJ,e/\u001a8u!\t1\u0003'\u0003\u00022O\t9\u0001K]8ek\u000e$\bCA\u001a=\u001d\t!$H\u0004\u00026s5\taG\u0003\u00028q\u00051AH]8piz\u001a\u0001!C\u0001)\u0013\tYt%A\u0004qC\u000e\\\u0017mZ3\n\u0005ur$\u0001D*fe&\fG.\u001b>bE2,'BA\u001e(\u0003\u0011!\u0018m]6\u0016\u0003\u0005\u0003$AQ$\u0011\u00071\u001aU)\u0003\u0002E9\t!A+Y:l!\t1u\t\u0004\u0001\u0005\u0013!\u0013\u0011\u0011!A\u0001\u0006\u0003Q%\u0001B0%cE\nQ\u0001^1tW\u0002\n\"a\u0013(\u0011\u0005\u0019b\u0015BA'(\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"AJ(\n\u0005A;#aA!os\u0006IA/Y:l\u0013:$W\r_\u000b\u0002'B\u0011a\u0005V\u0005\u0003+\u001e\u00121!\u00138u\u0003)!\u0018m]6J]\u0012,\u0007\u0010I\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007eSv\f\u0005\u0002-\u0001!)q(\u0002a\u00017B\u0012AL\u0018\t\u0004Y\rk\u0006C\u0001$_\t%A%,!A\u0001\u0002\u000b\u0005!\nC\u0004R\u000bA\u0005\t\u0019A*\u0002\t\r|\u0007/\u001f\u000b\u00043\n\u001c\u0007bB \u0007!\u0003\u0005\ra\u0017\u0005\b#\u001a\u0001\n\u00111\u0001T\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012A\u001a\u0019\u0003O*T#\u0001[6\u0011\u00071\u001a\u0015\u000e\u0005\u0002GU\u0012I\u0001jBA\u0001\u0002\u0003\u0015\tAS\u0016\u0002YB\u0011QN]\u0007\u0002]*\u0011q\u000e]\u0001\nk:\u001c\u0007.Z2lK\u0012T!!]\u0014\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002t]\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\taO\u000b\u0002TW\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012!\u001f\t\u0003u~l\u0011a\u001f\u0006\u0003yv\fA\u0001\\1oO*\ta0\u0001\u0003kCZ\f\u0017bAA\u0001w\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002O\u0003\u0013A\u0001\"a\u0003\f\u0003\u0003\u0005\raU\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005E\u0001#BA\n\u00033qUBAA\u000b\u0015\r\t9bJ\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\u000e\u0003+\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011\u0011EA\u0014!\r1\u00131E\u0005\u0004\u0003K9#a\u0002\"p_2,\u0017M\u001c\u0005\t\u0003\u0017i\u0011\u0011!a\u0001\u001d\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\rI\u0018Q\u0006\u0005\t\u0003\u0017q\u0011\u0011!a\u0001'\u0006A\u0001.Y:i\u0007>$W\rF\u0001T\u0003!!xn\u0015;sS:<G#A=\u0002\r\u0015\fX/\u00197t)\u0011\t\t#a\u000f\t\u0011\u0005-\u0011#!AA\u00029\u000b\u0001d\u00159fGVd\u0017\r^5wKR\u000b7o[*vE6LG\u000f^3e!\ta3cE\u0003\u0014\u0003\u0007\n9\u0006\u0005\u0005\u0002F\u0005-\u0013qJ*Z\u001b\t\t9EC\u0002\u0002J\u001d\nqA];oi&lW-\u0003\u0003\u0002N\u0005\u001d#!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA\"\u0011\u0011KA+!\u0011a3)a\u0015\u0011\u0007\u0019\u000b)\u0006B\u0005I'\u0005\u0005\t\u0011!B\u0001\u0015B!\u0011\u0011LA0\u001b\t\tYFC\u0002\u0002^u\f!![8\n\u0007u\nY\u0006\u0006\u0002\u0002@\u0005)\u0011\r\u001d9msR)\u0011,a\u001a\u0002r!1qH\u0006a\u0001\u0003S\u0002D!a\u001b\u0002pA!AfQA7!\r1\u0015q\u000e\u0003\u000b\u0011\u0006\u001d\u0014\u0011!A\u0001\u0006\u0003Q\u0005bB)\u0017!\u0003\u0005\raU\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%e\u00059QO\\1qa2LH\u0003BA=\u0003\u001b\u0003RAJA>\u0003\u007fJ1!! (\u0005\u0019y\u0005\u000f^5p]B1a%!!\u0002\u0006NK1!a!(\u0005\u0019!V\u000f\u001d7feA\"\u0011qQAF!\u0011a3)!#\u0011\u0007\u0019\u000bY\tB\u0005I1\u0005\u0005\t\u0011!B\u0001\u0015\"A\u0011q\u0012\r\u0002\u0002\u0003\u0007\u0011,A\u0002yIA\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u0012\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAL!\rQ\u0018\u0011T\u0005\u0004\u00037[(AB(cU\u0016\u001cG\u000f"
)
public class SpeculativeTaskSubmitted implements DAGSchedulerEvent, Product, Serializable {
   private final Task task;
   private final int taskIndex;

   public static int $lessinit$greater$default$2() {
      return SpeculativeTaskSubmitted$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final SpeculativeTaskSubmitted x$0) {
      return SpeculativeTaskSubmitted$.MODULE$.unapply(x$0);
   }

   public static int apply$default$2() {
      return SpeculativeTaskSubmitted$.MODULE$.apply$default$2();
   }

   public static SpeculativeTaskSubmitted apply(final Task task, final int taskIndex) {
      return SpeculativeTaskSubmitted$.MODULE$.apply(task, taskIndex);
   }

   public static Function1 tupled() {
      return SpeculativeTaskSubmitted$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SpeculativeTaskSubmitted$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Task task() {
      return this.task;
   }

   public int taskIndex() {
      return this.taskIndex;
   }

   public SpeculativeTaskSubmitted copy(final Task task, final int taskIndex) {
      return new SpeculativeTaskSubmitted(task, taskIndex);
   }

   public Task copy$default$1() {
      return this.task();
   }

   public int copy$default$2() {
      return this.taskIndex();
   }

   public String productPrefix() {
      return "SpeculativeTaskSubmitted";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.task();
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.taskIndex());
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
      return x$1 instanceof SpeculativeTaskSubmitted;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "task";
         }
         case 1 -> {
            return "taskIndex";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.task()));
      var1 = Statics.mix(var1, this.taskIndex());
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof SpeculativeTaskSubmitted) {
               SpeculativeTaskSubmitted var4 = (SpeculativeTaskSubmitted)x$1;
               if (this.taskIndex() == var4.taskIndex()) {
                  label44: {
                     Task var10000 = this.task();
                     Task var5 = var4.task();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label44;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label44;
                     }

                     if (var4.canEqual(this)) {
                        break label51;
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

   public SpeculativeTaskSubmitted(final Task task, final int taskIndex) {
      this.task = task;
      this.taskIndex = taskIndex;
      Product.$init$(this);
   }
}
