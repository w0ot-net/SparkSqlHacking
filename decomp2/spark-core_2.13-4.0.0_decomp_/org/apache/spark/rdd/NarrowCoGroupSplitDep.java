package org.apache.spark.rdd;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partition;
import org.apache.spark.util.Utils$;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015h!\u0002\u0010 \u0001\u0006:\u0003\u0002\u0003\u0011\u0001\u0005+\u0007I\u0011\u0001 \t\u0011!\u0003!\u0011#Q\u0001\n}B\u0001\u0002\u0016\u0001\u0003\u0016\u0004%\t!\u0016\u0005\t3\u0002\u0011\t\u0012)A\u0005-\"A1\f\u0001BI\u0002\u0013\u0005A\f\u0003\u0005b\u0001\t\u0005\r\u0011\"\u0001c\u0011!A\u0007A!E!B\u0013i\u0006\"B5\u0001\t\u0003Q\u0007\"B:\u0001\t\u0013!\b\"CA\b\u0001\u0005\u0005I\u0011AA\t\u0011%\tI\u0002AI\u0001\n\u0003\tY\u0002C\u0005\u0002:\u0001\t\n\u0011\"\u0001\u0002<!I\u0011q\b\u0001\u0012\u0002\u0013\u0005\u0011\u0011\t\u0005\n\u0003\u000b\u0002\u0011\u0011!C!\u0003\u000fB\u0001\"!\u0016\u0001\u0003\u0003%\t!\u0016\u0005\n\u0003/\u0002\u0011\u0011!C\u0001\u00033B\u0011\"!\u0018\u0001\u0003\u0003%\t%a\u0018\t\u0013\u00055\u0004!!A\u0005\u0002\u0005=\u0004\"CA=\u0001\u0005\u0005I\u0011IA>\u0011%\ty\bAA\u0001\n\u0003\n\t\tC\u0005\u0002\u0004\u0002\t\t\u0011\"\u0011\u0002\u0006\"I\u0011q\u0011\u0001\u0002\u0002\u0013\u0005\u0013\u0011R\u0004\u000b\u0003\u001b{\u0012\u0011!E\u0001C\u0005=e!\u0003\u0010 \u0003\u0003E\t!IAI\u0011\u0019I\u0007\u0004\"\u0001\u0002,\"I\u00111\u0011\r\u0002\u0002\u0013\u0015\u0013Q\u0011\u0005\n\u0003[C\u0012\u0011!CA\u0003_C\u0011\"a0\u0019\u0003\u0003%\t)!1\t\u0013\u0005m\u0007$!A\u0005\n\u0005u'!\u0006(beJ|woQ8He>,\bo\u00159mSR$U\r\u001d\u0006\u0003A\u0005\n1A\u001d3e\u0015\t\u00113%A\u0003ta\u0006\u00148N\u0003\u0002%K\u00051\u0011\r]1dQ\u0016T\u0011AJ\u0001\u0004_J<7\u0003\u0002\u0001)]m\u0002\"!\u000b\u0017\u000e\u0003)R\u0011aK\u0001\u0006g\u000e\fG.Y\u0005\u0003[)\u0012a!\u00118z%\u00164\u0007CA\u00189\u001d\t\u0001dG\u0004\u00022k5\t!G\u0003\u00024i\u00051AH]8piz\u001a\u0001!C\u0001,\u0013\t9$&A\u0004qC\u000e\\\u0017mZ3\n\u0005eR$\u0001D*fe&\fG.\u001b>bE2,'BA\u001c+!\tIC(\u0003\u0002>U\t9\u0001K]8ek\u000e$X#A 1\u0005\u00013\u0005cA!C\t6\tq$\u0003\u0002D?\t\u0019!\u000b\u0012#\u0011\u0005\u00153E\u0002\u0001\u0003\n\u000f\n\t\t\u0011!A\u0003\u00025\u00131a\u0018\u00132\u0003\u0011\u0011H\r\u001a\u0011)\u0005\tQ\u0005CA\u0015L\u0013\ta%FA\u0005ue\u0006t7/[3oiF\u0011a*\u0015\t\u0003S=K!\u0001\u0015\u0016\u0003\u000f9{G\u000f[5oOB\u0011\u0011FU\u0005\u0003'*\u00121!\u00118z\u0003)\u0019\b\u000f\\5u\u0013:$W\r_\u000b\u0002-B\u0011\u0011fV\u0005\u00031*\u00121!\u00138u\u0003-\u0019\b\u000f\\5u\u0013:$W\r\u001f\u0011)\u0005\u0011Q\u0015!B:qY&$X#A/\u0011\u0005y{V\"A\u0011\n\u0005\u0001\f#!\u0003)beRLG/[8o\u0003%\u0019\b\u000f\\5u?\u0012*\u0017\u000f\u0006\u0002dMB\u0011\u0011\u0006Z\u0005\u0003K*\u0012A!\u00168ji\"9qMBA\u0001\u0002\u0004i\u0016a\u0001=%c\u000511\u000f\u001d7ji\u0002\na\u0001P5oSRtD\u0003B6mcJ\u0004\"!\u0011\u0001\t\u000b\u0001B\u0001\u0019A71\u00059\u0004\bcA!C_B\u0011Q\t\u001d\u0003\n\u000f2\f\t\u0011!A\u0003\u00025CQ\u0001\u0016\u0005A\u0002YCQa\u0017\u0005A\u0002u\u000b1b\u001e:ji\u0016|%M[3diR\u00111-\u001e\u0005\u0006m&\u0001\ra^\u0001\u0004_>\u001c\bC\u0001=~\u001b\u0005I(B\u0001>|\u0003\tIwNC\u0001}\u0003\u0011Q\u0017M^1\n\u0005yL(AE(cU\u0016\u001cGoT;uaV$8\u000b\u001e:fC6DS!CA\u0001\u0003\u001b\u0001R!KA\u0002\u0003\u000fI1!!\u0002+\u0005\u0019!\bN]8xgB\u0019\u00010!\u0003\n\u0007\u0005-\u0011PA\u0006J\u001f\u0016C8-\u001a9uS>t7EAA\u0004\u0003\u0011\u0019w\u000e]=\u0015\u000f-\f\u0019\"!\u0006\u0002\u0018!9\u0001E\u0003I\u0001\u0002\u0004i\u0007b\u0002+\u000b!\u0003\u0005\rA\u0016\u0005\b7*\u0001\n\u00111\u0001^\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!!\b1\t\u0005}\u0011Q\u0005\u0016\u0005\u0003C\t9\u0003\u0005\u0003B\u0005\u0006\r\u0002cA#\u0002&\u0011IqiCA\u0001\u0002\u0003\u0015\t!T\u0016\u0003\u0003S\u0001B!a\u000b\u000265\u0011\u0011Q\u0006\u0006\u0005\u0003_\t\t$A\u0005v]\u000eDWmY6fI*\u0019\u00111\u0007\u0016\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u00028\u00055\"!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TCAA\u001fU\r1\u0016qE\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\t\t\u0019EK\u0002^\u0003O\tQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA%!\u0011\tY%!\u0015\u000e\u0005\u00055#bAA(w\u0006!A.\u00198h\u0013\u0011\t\u0019&!\u0014\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$2!UA.\u0011\u001d9\u0007#!AA\u0002Y\u000bq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003C\u0002R!a\u0019\u0002jEk!!!\u001a\u000b\u0007\u0005\u001d$&\u0001\u0006d_2dWm\u0019;j_:LA!a\u001b\u0002f\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t\t(a\u001e\u0011\u0007%\n\u0019(C\u0002\u0002v)\u0012qAQ8pY\u0016\fg\u000eC\u0004h%\u0005\u0005\t\u0019A)\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003\u0013\ni\bC\u0004h'\u0005\u0005\t\u0019\u0001,\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012AV\u0001\ti>\u001cFO]5oOR\u0011\u0011\u0011J\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005E\u00141\u0012\u0005\bOZ\t\t\u00111\u0001R\u0003Uq\u0015M\u001d:po\u000e{wI]8vaN\u0003H.\u001b;EKB\u0004\"!\u0011\r\u0014\u000ba\t\u0019*a*\u0011\u0013\u0005U\u00151TAP-v[WBAAL\u0015\r\tIJK\u0001\beVtG/[7f\u0013\u0011\ti*a&\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t7\u0007\r\u0003\u0002\"\u0006\u0015\u0006\u0003B!C\u0003G\u00032!RAS\t%9\u0005$!A\u0001\u0002\u000b\u0005Q\nE\u0002y\u0003SK!!O=\u0015\u0005\u0005=\u0015!B1qa2LHcB6\u00022\u0006m\u0016Q\u0018\u0005\u0007Am\u0001\r!a-1\t\u0005U\u0016\u0011\u0018\t\u0005\u0003\n\u000b9\fE\u0002F\u0003s#!bRAY\u0003\u0003\u0005\tQ!\u0001N\u0011\u0015!6\u00041\u0001W\u0011\u0015Y6\u00041\u0001^\u0003\u001d)h.\u00199qYf$B!a1\u0002XB)\u0011&!2\u0002J&\u0019\u0011q\u0019\u0016\u0003\r=\u0003H/[8o!\u001dI\u00131ZAh-vK1!!4+\u0005\u0019!V\u000f\u001d7fgA\"\u0011\u0011[Ak!\u0011\t%)a5\u0011\u0007\u0015\u000b)\u000eB\u0005H9\u0005\u0005\t\u0011!B\u0001\u001b\"A\u0011\u0011\u001c\u000f\u0002\u0002\u0003\u00071.A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a8\u0011\t\u0005-\u0013\u0011]\u0005\u0005\u0003G\fiE\u0001\u0004PE*,7\r\u001e"
)
public class NarrowCoGroupSplitDep implements Serializable, Product {
   private final transient RDD rdd;
   private final transient int splitIndex;
   private Partition split;

   public static Option unapply(final NarrowCoGroupSplitDep x$0) {
      return NarrowCoGroupSplitDep$.MODULE$.unapply(x$0);
   }

   public static NarrowCoGroupSplitDep apply(final RDD rdd, final int splitIndex, final Partition split) {
      return NarrowCoGroupSplitDep$.MODULE$.apply(rdd, splitIndex, split);
   }

   public static Function1 tupled() {
      return NarrowCoGroupSplitDep$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return NarrowCoGroupSplitDep$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public RDD rdd() {
      return this.rdd;
   }

   public int splitIndex() {
      return this.splitIndex;
   }

   public Partition split() {
      return this.split;
   }

   public void split_$eq(final Partition x$1) {
      this.split = x$1;
   }

   private void writeObject(final ObjectOutputStream oos) throws IOException {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.split_$eq(this.rdd().partitions()[this.splitIndex()]);
         oos.defaultWriteObject();
      });
   }

   public NarrowCoGroupSplitDep copy(final RDD rdd, final int splitIndex, final Partition split) {
      return new NarrowCoGroupSplitDep(rdd, splitIndex, split);
   }

   public RDD copy$default$1() {
      return this.rdd();
   }

   public int copy$default$2() {
      return this.splitIndex();
   }

   public Partition copy$default$3() {
      return this.split();
   }

   public String productPrefix() {
      return "NarrowCoGroupSplitDep";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.rdd();
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.splitIndex());
         }
         case 2 -> {
            return this.split();
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
      return x$1 instanceof NarrowCoGroupSplitDep;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "rdd";
         }
         case 1 -> {
            return "splitIndex";
         }
         case 2 -> {
            return "split";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.rdd()));
      var1 = Statics.mix(var1, this.splitIndex());
      var1 = Statics.mix(var1, Statics.anyHash(this.split()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof NarrowCoGroupSplitDep) {
               NarrowCoGroupSplitDep var4 = (NarrowCoGroupSplitDep)x$1;
               if (this.splitIndex() == var4.splitIndex()) {
                  label52: {
                     RDD var10000 = this.rdd();
                     RDD var5 = var4.rdd();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     Partition var7 = this.split();
                     Partition var6 = var4.split();
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

   public NarrowCoGroupSplitDep(final RDD rdd, final int splitIndex, final Partition split) {
      this.rdd = rdd;
      this.splitIndex = splitIndex;
      this.split = split;
      super();
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
