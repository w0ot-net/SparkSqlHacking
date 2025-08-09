package org.apache.spark.streaming.ui;

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
   bytes = "\u0006\u0005\u00055e!B\r\u001b\u0001j!\u0003\u0002C\u001e\u0001\u0005+\u0007I\u0011\u0001\u001f\t\u00119\u0003!\u0011#Q\u0001\nuB\u0001b\u0014\u0001\u0003\u0016\u0004%\t\u0001\u0015\u0005\t=\u0002\u0011\t\u0012)A\u0005#\")q\f\u0001C\u0001A\"9Q\rAA\u0001\n\u00031\u0007bB5\u0001#\u0003%\tA\u001b\u0005\bk\u0002\t\n\u0011\"\u0001w\u0011\u001dA\b!!A\u0005BeD\u0011\"!\u0002\u0001\u0003\u0003%\t!a\u0002\t\u0013\u0005=\u0001!!A\u0005\u0002\u0005E\u0001\"CA\u000f\u0001\u0005\u0005I\u0011IA\u0010\u0011%\ti\u0003AA\u0001\n\u0003\ty\u0003C\u0005\u0002:\u0001\t\t\u0011\"\u0011\u0002<!I\u0011q\b\u0001\u0002\u0002\u0013\u0005\u0013\u0011\t\u0005\n\u0003\u0007\u0002\u0011\u0011!C!\u0003\u000bB\u0011\"a\u0012\u0001\u0003\u0003%\t%!\u0013\b\u0015\u00055#$!A\t\u0002i\tyEB\u0005\u001a5\u0005\u0005\t\u0012\u0001\u000e\u0002R!1ql\u0005C\u0001\u0003SB\u0011\"a\u0011\u0014\u0003\u0003%)%!\u0012\t\u0013\u0005-4#!A\u0005\u0002\u00065\u0004\"CA:'\u0005\u0005I\u0011QA;\u0011%\t\u0019iEA\u0001\n\u0013\t)I\u0001\u000bTa\u0006\u00148NS8c\u0013\u0012<\u0016\u000e\u001e5V\u0013\u0012\u000bG/\u0019\u0006\u00037q\t!!^5\u000b\u0005uq\u0012!C:ue\u0016\fW.\u001b8h\u0015\ty\u0002%A\u0003ta\u0006\u00148N\u0003\u0002\"E\u00051\u0011\r]1dQ\u0016T\u0011aI\u0001\u0004_J<7\u0003\u0002\u0001&W9\u0002\"AJ\u0015\u000e\u0003\u001dR\u0011\u0001K\u0001\u0006g\u000e\fG.Y\u0005\u0003U\u001d\u0012a!\u00118z%\u00164\u0007C\u0001\u0014-\u0013\tisEA\u0004Qe>$Wo\u0019;\u0011\u0005=BdB\u0001\u00197\u001d\t\tT'D\u00013\u0015\t\u0019D'\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005A\u0013BA\u001c(\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u000f\u001e\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005]:\u0013AC:qCJ\\'j\u001c2JIV\tQ\b\u0005\u0002?\u0017:\u0011q(\u0013\b\u0003\u0001\"s!!Q$\u000f\u0005\t3eBA\"F\u001d\t\tD)C\u0001$\u0013\t\t#%\u0003\u0002 A%\u0011QDH\u0005\u00037qI!A\u0013\u000e\u00029M#(/Z1nS:<'j\u001c2Qe><'/Z:t\u0019&\u001cH/\u001a8fe&\u0011A*\u0014\u0002\u000b'B\f'o\u001b&pE&#'B\u0001&\u001b\u0003-\u0019\b/\u0019:l\u0015>\u0014\u0017\n\u001a\u0011\u0002\u000f)|'\rR1uCV\t\u0011\u000bE\u0002'%RK!aU\u0014\u0003\r=\u0003H/[8o!\t)F,D\u0001W\u0015\t9\u0006,\u0001\u0002wc)\u0011\u0011LW\u0001\u0004CBL'BA.\u001f\u0003\u0019\u0019H/\u0019;vg&\u0011QL\u0016\u0002\b\u0015>\u0014G)\u0019;b\u0003!QwN\u0019#bi\u0006\u0004\u0013A\u0002\u001fj]&$h\bF\u0002bG\u0012\u0004\"A\u0019\u0001\u000e\u0003iAQaO\u0003A\u0002uBQaT\u0003A\u0002E\u000bAaY8qsR\u0019\u0011m\u001a5\t\u000fm2\u0001\u0013!a\u0001{!9qJ\u0002I\u0001\u0002\u0004\t\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0002W*\u0012Q\b\\\u0016\u0002[B\u0011an]\u0007\u0002_*\u0011\u0001/]\u0001\nk:\u001c\u0007.Z2lK\u0012T!A]\u0014\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002u_\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\tqO\u000b\u0002RY\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012A\u001f\t\u0004w\u0006\u0005Q\"\u0001?\u000b\u0005ut\u0018\u0001\u00027b]\u001eT\u0011a`\u0001\u0005U\u00064\u0018-C\u0002\u0002\u0004q\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLXCAA\u0005!\r1\u00131B\u0005\u0004\u0003\u001b9#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA\n\u00033\u00012AJA\u000b\u0013\r\t9b\n\u0002\u0004\u0003:L\b\"CA\u000e\u0017\u0005\u0005\t\u0019AA\u0005\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011\u0011\u0005\t\u0007\u0003G\tI#a\u0005\u000e\u0005\u0005\u0015\"bAA\u0014O\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005-\u0012Q\u0005\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u00022\u0005]\u0002c\u0001\u0014\u00024%\u0019\u0011QG\u0014\u0003\u000f\t{w\u000e\\3b]\"I\u00111D\u0007\u0002\u0002\u0003\u0007\u00111C\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002{\u0003{A\u0011\"a\u0007\u000f\u0003\u0003\u0005\r!!\u0003\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!\u0003\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012A_\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005E\u00121\n\u0005\n\u00037\t\u0012\u0011!a\u0001\u0003'\tAc\u00159be.TuNY%e/&$\b.V%ECR\f\u0007C\u00012\u0014'\u0015\u0019\u00121KA0!\u001d\t)&a\u0017>#\u0006l!!a\u0016\u000b\u0007\u0005es%A\u0004sk:$\u0018.\\3\n\t\u0005u\u0013q\u000b\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BA1\u0003Oj!!a\u0019\u000b\u0007\u0005\u0015d0\u0001\u0002j_&\u0019\u0011(a\u0019\u0015\u0005\u0005=\u0013!B1qa2LH#B1\u0002p\u0005E\u0004\"B\u001e\u0017\u0001\u0004i\u0004\"B(\u0017\u0001\u0004\t\u0016aB;oCB\u0004H.\u001f\u000b\u0005\u0003o\ny\b\u0005\u0003'%\u0006e\u0004#\u0002\u0014\u0002|u\n\u0016bAA?O\t1A+\u001e9mKJB\u0001\"!!\u0018\u0003\u0003\u0005\r!Y\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAD!\rY\u0018\u0011R\u0005\u0004\u0003\u0017c(AB(cU\u0016\u001cG\u000f"
)
public class SparkJobIdWithUIData implements Product, Serializable {
   private final int sparkJobId;
   private final Option jobData;

   public static Option unapply(final SparkJobIdWithUIData x$0) {
      return SparkJobIdWithUIData$.MODULE$.unapply(x$0);
   }

   public static SparkJobIdWithUIData apply(final int sparkJobId, final Option jobData) {
      return SparkJobIdWithUIData$.MODULE$.apply(sparkJobId, jobData);
   }

   public static Function1 tupled() {
      return SparkJobIdWithUIData$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkJobIdWithUIData$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int sparkJobId() {
      return this.sparkJobId;
   }

   public Option jobData() {
      return this.jobData;
   }

   public SparkJobIdWithUIData copy(final int sparkJobId, final Option jobData) {
      return new SparkJobIdWithUIData(sparkJobId, jobData);
   }

   public int copy$default$1() {
      return this.sparkJobId();
   }

   public Option copy$default$2() {
      return this.jobData();
   }

   public String productPrefix() {
      return "SparkJobIdWithUIData";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.sparkJobId());
         }
         case 1 -> {
            return this.jobData();
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
      return x$1 instanceof SparkJobIdWithUIData;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "sparkJobId";
         }
         case 1 -> {
            return "jobData";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.sparkJobId());
      var1 = Statics.mix(var1, Statics.anyHash(this.jobData()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof SparkJobIdWithUIData) {
               SparkJobIdWithUIData var4 = (SparkJobIdWithUIData)x$1;
               if (this.sparkJobId() == var4.sparkJobId()) {
                  label44: {
                     Option var10000 = this.jobData();
                     Option var5 = var4.jobData();
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

   public SparkJobIdWithUIData(final int sparkJobId, final Option jobData) {
      this.sparkJobId = sparkJobId;
      this.jobData = jobData;
      Product.$init$(this);
   }
}
