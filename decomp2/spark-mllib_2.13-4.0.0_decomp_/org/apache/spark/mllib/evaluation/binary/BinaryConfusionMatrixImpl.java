package org.apache.spark.mllib.evaluation.binary;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}d!B\u0010!\u0001\nb\u0003\u0002C$\u0001\u0005+\u0007I\u0011\u0001%\t\u00111\u0003!\u0011#Q\u0001\n%C\u0001\"\u0014\u0001\u0003\u0016\u0004%\t\u0001\u0013\u0005\t\u001d\u0002\u0011\t\u0012)A\u0005\u0013\")q\n\u0001C\u0001!\")A\u000b\u0001C!+\")\u0011\f\u0001C!+\")!\f\u0001C!+\")1\f\u0001C!+\")A\f\u0001C!+\")Q\f\u0001C!+\"9a\fAA\u0001\n\u0003y\u0006b\u00022\u0001#\u0003%\ta\u0019\u0005\b]\u0002\t\n\u0011\"\u0001d\u0011\u001dy\u0007!!A\u0005BADq!\u001f\u0001\u0002\u0002\u0013\u0005!\u0010C\u0004\u007f\u0001\u0005\u0005I\u0011A@\t\u0013\u0005-\u0001!!A\u0005B\u00055\u0001\"CA\u000e\u0001\u0005\u0005I\u0011AA\u000f\u0011%\t9\u0003AA\u0001\n\u0003\nI\u0003C\u0005\u0002.\u0001\t\t\u0011\"\u0011\u00020!I\u0011\u0011\u0007\u0001\u0002\u0002\u0013\u0005\u00131\u0007\u0005\n\u0003k\u0001\u0011\u0011!C!\u0003o9!\"a\u000f!\u0003\u0003E\tAIA\u001f\r%y\u0002%!A\t\u0002\t\ny\u0004\u0003\u0004P3\u0011\u0005\u0011q\u000b\u0005\n\u0003cI\u0012\u0011!C#\u0003gA\u0011\"!\u0017\u001a\u0003\u0003%\t)a\u0017\t\u0013\u0005\u0005\u0014$!A\u0005\u0002\u0006\r\u0004\"CA;3\u0005\u0005I\u0011BA<\u0005e\u0011\u0015N\\1ss\u000e{gNZ;tS>tW*\u0019;sSbLU\u000e\u001d7\u000b\u0005\u0005\u0012\u0013A\u00022j]\u0006\u0014\u0018P\u0003\u0002$I\u0005QQM^1mk\u0006$\u0018n\u001c8\u000b\u0005\u00152\u0013!B7mY&\u0014'BA\u0014)\u0003\u0015\u0019\b/\u0019:l\u0015\tI#&\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002W\u0005\u0019qN]4\u0014\u000b\u0001i3g\u000e\u001e\u0011\u00059\nT\"A\u0018\u000b\u0003A\nQa]2bY\u0006L!AM\u0018\u0003\r\u0005s\u0017PU3g!\t!T'D\u0001!\u0013\t1\u0004EA\u000bCS:\f'/_\"p]\u001a,8/[8o\u001b\u0006$(/\u001b=\u0011\u00059B\u0014BA\u001d0\u0005\u001d\u0001&o\u001c3vGR\u0004\"a\u000f#\u000f\u0005q\u0012eBA\u001fB\u001b\u0005q$BA A\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\u0019\n\u0005\r{\u0013a\u00029bG.\fw-Z\u0005\u0003\u000b\u001a\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!aQ\u0018\u0002\u000b\r|WO\u001c;\u0016\u0003%\u0003\"\u0001\u000e&\n\u0005-\u0003#A\u0005\"j]\u0006\u0014\u0018\u0010T1cK2\u001cu.\u001e8uKJ\faaY8v]R\u0004\u0013A\u0003;pi\u0006d7i\\;oi\u0006YAo\u001c;bY\u000e{WO\u001c;!\u0003\u0019a\u0014N\\5u}Q\u0019\u0011KU*\u0011\u0005Q\u0002\u0001\"B$\u0006\u0001\u0004I\u0005\"B'\u0006\u0001\u0004I\u0015!F<fS\u001eDG/\u001a3UeV,\u0007k\\:ji&4Xm]\u000b\u0002-B\u0011afV\u0005\u00031>\u0012a\u0001R8vE2,\u0017AF<fS\u001eDG/\u001a3GC2\u001cX\rU8tSRLg/Z:\u0002-],\u0017n\u001a5uK\u00124\u0015\r\\:f\u001d\u0016<\u0017\r^5wKN\fQc^3jO\"$X\r\u001a+sk\u0016tUmZ1uSZ,7/A\txK&<\u0007\u000e^3e!>\u001c\u0018\u000e^5wKN\f\u0011c^3jO\"$X\r\u001a(fO\u0006$\u0018N^3t\u0003\u0011\u0019w\u000e]=\u0015\u0007E\u0003\u0017\rC\u0004H\u0019A\u0005\t\u0019A%\t\u000f5c\u0001\u0013!a\u0001\u0013\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u00013+\u0005%+7&\u00014\u0011\u0005\u001ddW\"\u00015\u000b\u0005%T\u0017!C;oG\",7m[3e\u0015\tYw&\u0001\u0006b]:|G/\u0019;j_:L!!\u001c5\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005\t\bC\u0001:x\u001b\u0005\u0019(B\u0001;v\u0003\u0011a\u0017M\\4\u000b\u0003Y\fAA[1wC&\u0011\u0001p\u001d\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003m\u0004\"A\f?\n\u0005u|#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA\u0001\u0003\u000f\u00012ALA\u0002\u0013\r\t)a\f\u0002\u0004\u0003:L\b\u0002CA\u0005#\u0005\u0005\t\u0019A>\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\ty\u0001\u0005\u0004\u0002\u0012\u0005]\u0011\u0011A\u0007\u0003\u0003'Q1!!\u00060\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u00033\t\u0019B\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u0010\u0003K\u00012ALA\u0011\u0013\r\t\u0019c\f\u0002\b\u0005>|G.Z1o\u0011%\tIaEA\u0001\u0002\u0004\t\t!\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,GcA9\u0002,!A\u0011\u0011\u0002\u000b\u0002\u0002\u0003\u000710\u0001\u0005iCND7i\u001c3f)\u0005Y\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003E\fa!Z9vC2\u001cH\u0003BA\u0010\u0003sA\u0011\"!\u0003\u0018\u0003\u0003\u0005\r!!\u0001\u00023\tKg.\u0019:z\u0007>tg-^:j_:l\u0015\r\u001e:jq&k\u0007\u000f\u001c\t\u0003ie\u0019R!GA!\u0003\u001b\u0002r!a\u0011\u0002J%K\u0015+\u0004\u0002\u0002F)\u0019\u0011qI\u0018\u0002\u000fI,h\u000e^5nK&!\u00111JA#\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\t\u0005\u0003\u001f\n)&\u0004\u0002\u0002R)\u0019\u00111K;\u0002\u0005%|\u0017bA#\u0002RQ\u0011\u0011QH\u0001\u0006CB\u0004H.\u001f\u000b\u0006#\u0006u\u0013q\f\u0005\u0006\u000fr\u0001\r!\u0013\u0005\u0006\u001br\u0001\r!S\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t)'!\u001d\u0011\u000b9\n9'a\u001b\n\u0007\u0005%tF\u0001\u0004PaRLwN\u001c\t\u0006]\u00055\u0014*S\u0005\u0004\u0003_z#A\u0002+va2,'\u0007\u0003\u0005\u0002tu\t\t\u00111\u0001R\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003s\u00022A]A>\u0013\r\tih\u001d\u0002\u0007\u001f\nTWm\u0019;"
)
public class BinaryConfusionMatrixImpl implements BinaryConfusionMatrix, Product, Serializable {
   private final BinaryLabelCounter count;
   private final BinaryLabelCounter totalCount;

   public static Option unapply(final BinaryConfusionMatrixImpl x$0) {
      return BinaryConfusionMatrixImpl$.MODULE$.unapply(x$0);
   }

   public static BinaryConfusionMatrixImpl apply(final BinaryLabelCounter count, final BinaryLabelCounter totalCount) {
      return BinaryConfusionMatrixImpl$.MODULE$.apply(count, totalCount);
   }

   public static Function1 tupled() {
      return BinaryConfusionMatrixImpl$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return BinaryConfusionMatrixImpl$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public BinaryLabelCounter count() {
      return this.count;
   }

   public BinaryLabelCounter totalCount() {
      return this.totalCount;
   }

   public double weightedTruePositives() {
      return this.count().weightedNumPositives();
   }

   public double weightedFalsePositives() {
      return this.count().weightedNumNegatives();
   }

   public double weightedFalseNegatives() {
      return this.totalCount().weightedNumPositives() - this.count().weightedNumPositives();
   }

   public double weightedTrueNegatives() {
      return this.totalCount().weightedNumNegatives() - this.count().weightedNumNegatives();
   }

   public double weightedPositives() {
      return this.totalCount().weightedNumPositives();
   }

   public double weightedNegatives() {
      return this.totalCount().weightedNumNegatives();
   }

   public BinaryConfusionMatrixImpl copy(final BinaryLabelCounter count, final BinaryLabelCounter totalCount) {
      return new BinaryConfusionMatrixImpl(count, totalCount);
   }

   public BinaryLabelCounter copy$default$1() {
      return this.count();
   }

   public BinaryLabelCounter copy$default$2() {
      return this.totalCount();
   }

   public String productPrefix() {
      return "BinaryConfusionMatrixImpl";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.count();
         }
         case 1 -> {
            return this.totalCount();
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
      return x$1 instanceof BinaryConfusionMatrixImpl;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "count";
         }
         case 1 -> {
            return "totalCount";
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
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof BinaryConfusionMatrixImpl) {
               label48: {
                  BinaryConfusionMatrixImpl var4 = (BinaryConfusionMatrixImpl)x$1;
                  BinaryLabelCounter var10000 = this.count();
                  BinaryLabelCounter var5 = var4.count();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  var10000 = this.totalCount();
                  BinaryLabelCounter var6 = var4.totalCount();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
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

   public BinaryConfusionMatrixImpl(final BinaryLabelCounter count, final BinaryLabelCounter totalCount) {
      this.count = count;
      this.totalCount = totalCount;
      BinaryConfusionMatrix.$init$(this);
      Product.$init$(this);
   }
}
