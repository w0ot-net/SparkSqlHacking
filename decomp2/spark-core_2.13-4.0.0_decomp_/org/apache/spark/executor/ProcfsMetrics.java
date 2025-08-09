package org.apache.spark.executor;

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
   bytes = "\u0006\u0005\u0005ee!B\u0013'\u0001\"r\u0003\u0002C#\u0001\u0005+\u0007I\u0011\u0001$\t\u0011)\u0003!\u0011#Q\u0001\n\u001dC\u0001b\u0013\u0001\u0003\u0016\u0004%\tA\u0012\u0005\t\u0019\u0002\u0011\t\u0012)A\u0005\u000f\"AQ\n\u0001BK\u0002\u0013\u0005a\t\u0003\u0005O\u0001\tE\t\u0015!\u0003H\u0011!y\u0005A!f\u0001\n\u00031\u0005\u0002\u0003)\u0001\u0005#\u0005\u000b\u0011B$\t\u0011E\u0003!Q3A\u0005\u0002\u0019C\u0001B\u0015\u0001\u0003\u0012\u0003\u0006Ia\u0012\u0005\t'\u0002\u0011)\u001a!C\u0001\r\"AA\u000b\u0001B\tB\u0003%q\tC\u0003V\u0001\u0011\u0005a\u000bC\u0004`\u0001\u0005\u0005I\u0011\u00011\t\u000f\u001d\u0004\u0011\u0013!C\u0001Q\"91\u000fAI\u0001\n\u0003A\u0007b\u0002;\u0001#\u0003%\t\u0001\u001b\u0005\bk\u0002\t\n\u0011\"\u0001i\u0011\u001d1\b!%A\u0005\u0002!Dqa\u001e\u0001\u0012\u0002\u0013\u0005\u0001\u000eC\u0004y\u0001\u0005\u0005I\u0011I=\t\u0013\u0005\u0015\u0001!!A\u0005\u0002\u0005\u001d\u0001\"CA\b\u0001\u0005\u0005I\u0011AA\t\u0011%\ti\u0002AA\u0001\n\u0003\ny\u0002C\u0005\u0002.\u0001\t\t\u0011\"\u0001\u00020!I\u0011\u0011\b\u0001\u0002\u0002\u0013\u0005\u00131\b\u0005\n\u0003\u007f\u0001\u0011\u0011!C!\u0003\u0003B\u0011\"a\u0011\u0001\u0003\u0003%\t%!\u0012\t\u0013\u0005\u001d\u0003!!A\u0005B\u0005%sACA'M\u0005\u0005\t\u0012\u0001\u0015\u0002P\u0019IQEJA\u0001\u0012\u0003A\u0013\u0011\u000b\u0005\u0007+~!\t!!\u001b\t\u0013\u0005\rs$!A\u0005F\u0005\u0015\u0003\"CA6?\u0005\u0005I\u0011QA7\u0011%\tYhHA\u0001\n\u0003\u000bi\bC\u0005\u0002\u0010~\t\t\u0011\"\u0003\u0002\u0012\ni\u0001K]8dMNlU\r\u001e:jGNT!a\n\u0015\u0002\u0011\u0015DXmY;u_JT!!\u000b\u0016\u0002\u000bM\u0004\u0018M]6\u000b\u0005-b\u0013AB1qC\u000eDWMC\u0001.\u0003\ry'oZ\n\u0005\u0001=*\u0004\b\u0005\u00021g5\t\u0011GC\u00013\u0003\u0015\u00198-\u00197b\u0013\t!\u0014G\u0001\u0004B]f\u0014VM\u001a\t\u0003aYJ!aN\u0019\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0011H\u0011\b\u0003u\u0001s!aO \u000e\u0003qR!!\u0010 \u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011AM\u0005\u0003\u0003F\nq\u0001]1dW\u0006<W-\u0003\u0002D\t\na1+\u001a:jC2L'0\u00192mK*\u0011\u0011)M\u0001\rUZlg+\\3n)>$\u0018\r\\\u000b\u0002\u000fB\u0011\u0001\u0007S\u0005\u0003\u0013F\u0012A\u0001T8oO\u0006i!N^7W[\u0016lGk\u001c;bY\u0002\n1B\u001b<n%N\u001bFk\u001c;bY\u0006a!N^7S'N#v\u000e^1mA\u0005y\u0001/\u001f;i_:4V.Z7U_R\fG.\u0001\tqsRDwN\u001c,nK6$v\u000e^1mA\u0005q\u0001/\u001f;i_:\u00146k\u0015+pi\u0006d\u0017a\u00049zi\"|gNU*T)>$\u0018\r\u001c\u0011\u0002\u001d=$\b.\u001a:W[\u0016lGk\u001c;bY\u0006yq\u000e\u001e5feZkW-\u001c+pi\u0006d\u0007%A\u0007pi\",'OU*T)>$\u0018\r\\\u0001\u000f_RDWM\u001d*T'R{G/\u00197!\u0003\u0019a\u0014N\\5u}Q9q+\u0017.\\9vs\u0006C\u0001-\u0001\u001b\u00051\u0003\"B#\u000e\u0001\u00049\u0005\"B&\u000e\u0001\u00049\u0005\"B'\u000e\u0001\u00049\u0005\"B(\u000e\u0001\u00049\u0005\"B)\u000e\u0001\u00049\u0005\"B*\u000e\u0001\u00049\u0015\u0001B2paf$raV1cG\u0012,g\rC\u0004F\u001dA\u0005\t\u0019A$\t\u000f-s\u0001\u0013!a\u0001\u000f\"9QJ\u0004I\u0001\u0002\u00049\u0005bB(\u000f!\u0003\u0005\ra\u0012\u0005\b#:\u0001\n\u00111\u0001H\u0011\u001d\u0019f\u0002%AA\u0002\u001d\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001jU\t9%nK\u0001l!\ta\u0017/D\u0001n\u0015\tqw.A\u0005v]\u000eDWmY6fI*\u0011\u0001/M\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001:n\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM\nabY8qs\u0012\"WMZ1vYR$C'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001b\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%m\u0005i\u0001O]8ek\u000e$\bK]3gSb,\u0012A\u001f\t\u0004w\u0006\u0005Q\"\u0001?\u000b\u0005ut\u0018\u0001\u00027b]\u001eT\u0011a`\u0001\u0005U\u00064\u0018-C\u0002\u0002\u0004q\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLXCAA\u0005!\r\u0001\u00141B\u0005\u0004\u0003\u001b\t$aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA\n\u00033\u00012\u0001MA\u000b\u0013\r\t9\"\r\u0002\u0004\u0003:L\b\"CA\u000e/\u0005\u0005\t\u0019AA\u0005\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011\u0011\u0005\t\u0007\u0003G\tI#a\u0005\u000e\u0005\u0005\u0015\"bAA\u0014c\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005-\u0012Q\u0005\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u00022\u0005]\u0002c\u0001\u0019\u00024%\u0019\u0011QG\u0019\u0003\u000f\t{w\u000e\\3b]\"I\u00111D\r\u0002\u0002\u0003\u0007\u00111C\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002{\u0003{A\u0011\"a\u0007\u001b\u0003\u0003\u0005\r!!\u0003\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!\u0003\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012A_\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005E\u00121\n\u0005\n\u00037i\u0012\u0011!a\u0001\u0003'\tQ\u0002\u0015:pG\u001a\u001cX*\u001a;sS\u000e\u001c\bC\u0001- '\u0015y\u00121KA0!-\t)&a\u0017H\u000f\u001e;uiR,\u000e\u0005\u0005]#bAA-c\u00059!/\u001e8uS6,\u0017\u0002BA/\u0003/\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c87!\u0011\t\t'a\u001a\u000e\u0005\u0005\r$bAA3}\u0006\u0011\u0011n\\\u0005\u0004\u0007\u0006\rDCAA(\u0003\u0015\t\u0007\u000f\u001d7z)59\u0016qNA9\u0003g\n)(a\u001e\u0002z!)QI\ta\u0001\u000f\")1J\ta\u0001\u000f\")QJ\ta\u0001\u000f\")qJ\ta\u0001\u000f\")\u0011K\ta\u0001\u000f\")1K\ta\u0001\u000f\u00069QO\\1qa2LH\u0003BA@\u0003\u0017\u0003R\u0001MAA\u0003\u000bK1!a!2\u0005\u0019y\u0005\u000f^5p]BI\u0001'a\"H\u000f\u001e;uiR\u0005\u0004\u0003\u0013\u000b$A\u0002+va2,g\u0007\u0003\u0005\u0002\u000e\u000e\n\t\u00111\u0001X\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003'\u00032a_AK\u0013\r\t9\n \u0002\u0007\u001f\nTWm\u0019;"
)
public class ProcfsMetrics implements Product, Serializable {
   private final long jvmVmemTotal;
   private final long jvmRSSTotal;
   private final long pythonVmemTotal;
   private final long pythonRSSTotal;
   private final long otherVmemTotal;
   private final long otherRSSTotal;

   public static Option unapply(final ProcfsMetrics x$0) {
      return ProcfsMetrics$.MODULE$.unapply(x$0);
   }

   public static ProcfsMetrics apply(final long jvmVmemTotal, final long jvmRSSTotal, final long pythonVmemTotal, final long pythonRSSTotal, final long otherVmemTotal, final long otherRSSTotal) {
      return ProcfsMetrics$.MODULE$.apply(jvmVmemTotal, jvmRSSTotal, pythonVmemTotal, pythonRSSTotal, otherVmemTotal, otherRSSTotal);
   }

   public static Function1 tupled() {
      return ProcfsMetrics$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ProcfsMetrics$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long jvmVmemTotal() {
      return this.jvmVmemTotal;
   }

   public long jvmRSSTotal() {
      return this.jvmRSSTotal;
   }

   public long pythonVmemTotal() {
      return this.pythonVmemTotal;
   }

   public long pythonRSSTotal() {
      return this.pythonRSSTotal;
   }

   public long otherVmemTotal() {
      return this.otherVmemTotal;
   }

   public long otherRSSTotal() {
      return this.otherRSSTotal;
   }

   public ProcfsMetrics copy(final long jvmVmemTotal, final long jvmRSSTotal, final long pythonVmemTotal, final long pythonRSSTotal, final long otherVmemTotal, final long otherRSSTotal) {
      return new ProcfsMetrics(jvmVmemTotal, jvmRSSTotal, pythonVmemTotal, pythonRSSTotal, otherVmemTotal, otherRSSTotal);
   }

   public long copy$default$1() {
      return this.jvmVmemTotal();
   }

   public long copy$default$2() {
      return this.jvmRSSTotal();
   }

   public long copy$default$3() {
      return this.pythonVmemTotal();
   }

   public long copy$default$4() {
      return this.pythonRSSTotal();
   }

   public long copy$default$5() {
      return this.otherVmemTotal();
   }

   public long copy$default$6() {
      return this.otherRSSTotal();
   }

   public String productPrefix() {
      return "ProcfsMetrics";
   }

   public int productArity() {
      return 6;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.jvmVmemTotal());
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.jvmRSSTotal());
         }
         case 2 -> {
            return BoxesRunTime.boxToLong(this.pythonVmemTotal());
         }
         case 3 -> {
            return BoxesRunTime.boxToLong(this.pythonRSSTotal());
         }
         case 4 -> {
            return BoxesRunTime.boxToLong(this.otherVmemTotal());
         }
         case 5 -> {
            return BoxesRunTime.boxToLong(this.otherRSSTotal());
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
      return x$1 instanceof ProcfsMetrics;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "jvmVmemTotal";
         }
         case 1 -> {
            return "jvmRSSTotal";
         }
         case 2 -> {
            return "pythonVmemTotal";
         }
         case 3 -> {
            return "pythonRSSTotal";
         }
         case 4 -> {
            return "otherVmemTotal";
         }
         case 5 -> {
            return "otherRSSTotal";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.jvmVmemTotal()));
      var1 = Statics.mix(var1, Statics.longHash(this.jvmRSSTotal()));
      var1 = Statics.mix(var1, Statics.longHash(this.pythonVmemTotal()));
      var1 = Statics.mix(var1, Statics.longHash(this.pythonRSSTotal()));
      var1 = Statics.mix(var1, Statics.longHash(this.otherVmemTotal()));
      var1 = Statics.mix(var1, Statics.longHash(this.otherRSSTotal()));
      return Statics.finalizeHash(var1, 6);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label46: {
            if (x$1 instanceof ProcfsMetrics) {
               ProcfsMetrics var4 = (ProcfsMetrics)x$1;
               if (this.jvmVmemTotal() == var4.jvmVmemTotal() && this.jvmRSSTotal() == var4.jvmRSSTotal() && this.pythonVmemTotal() == var4.pythonVmemTotal() && this.pythonRSSTotal() == var4.pythonRSSTotal() && this.otherVmemTotal() == var4.otherVmemTotal() && this.otherRSSTotal() == var4.otherRSSTotal() && var4.canEqual(this)) {
                  break label46;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public ProcfsMetrics(final long jvmVmemTotal, final long jvmRSSTotal, final long pythonVmemTotal, final long pythonRSSTotal, final long otherVmemTotal, final long otherRSSTotal) {
      this.jvmVmemTotal = jvmVmemTotal;
      this.jvmRSSTotal = jvmRSSTotal;
      this.pythonVmemTotal = pythonVmemTotal;
      this.pythonRSSTotal = pythonRSSTotal;
      this.otherVmemTotal = otherVmemTotal;
      this.otherRSSTotal = otherRSSTotal;
      Product.$init$(this);
   }
}
