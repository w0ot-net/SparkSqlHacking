package org.apache.spark.mllib.evaluation.binary;

import java.io.Serializable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001da!B\n\u0015\u0001Y\u0001\u0003\u0002\u0003\u001b\u0001\u0005\u0003\u0007I\u0011A\u001b\t\u0011e\u0002!\u00111A\u0005\u0002iB\u0001\u0002\u0011\u0001\u0003\u0002\u0003\u0006KA\u000e\u0005\t\u0003\u0002\u0011\t\u0019!C\u0001k!A!\t\u0001BA\u0002\u0013\u00051\t\u0003\u0005F\u0001\t\u0005\t\u0015)\u00037\u0011\u00151\u0005\u0001\"\u0001H\u0011\u0015a\u0005\u0001\"\u0001N\u0011\u0015a\u0005\u0001\"\u0001Q\u0011\u0015a\u0005\u0001\"\u0001U\u0011\u00159\u0006\u0001\"\u0011Y\u0011\u0015I\u0006\u0001\"\u0011[\u000f!\u0019G#!A\t\u0002Y!g\u0001C\n\u0015\u0003\u0003E\tAF3\t\u000b\u0019sA\u0011A7\t\u000f9t\u0011\u0013!C\u0001_\"9!PDI\u0001\n\u0003y\u0007bB>\u000f\u0003\u0003%I\u0001 \u0002\u0013\u0005&t\u0017M]=MC\n,GnQ8v]R,'O\u0003\u0002\u0016-\u00051!-\u001b8befT!a\u0006\r\u0002\u0015\u00154\u0018\r\\;bi&|gN\u0003\u0002\u001a5\u0005)Q\u000e\u001c7jE*\u00111\u0004H\u0001\u0006gB\f'o\u001b\u0006\u0003;y\ta!\u00199bG\",'\"A\u0010\u0002\u0007=\u0014xmE\u0002\u0001C\u001d\u0002\"AI\u0013\u000e\u0003\rR\u0011\u0001J\u0001\u0006g\u000e\fG.Y\u0005\u0003M\r\u0012a!\u00118z%\u00164\u0007C\u0001\u00152\u001d\tIsF\u0004\u0002+]5\t1F\u0003\u0002-[\u00051AH]8piz\u001a\u0001!C\u0001%\u0013\t\u00014%A\u0004qC\u000e\\\u0017mZ3\n\u0005I\u001a$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u0019$\u0003Q9X-[4ii\u0016$g*^7Q_NLG/\u001b<fgV\ta\u0007\u0005\u0002#o%\u0011\u0001h\t\u0002\u0007\t>,(\r\\3\u00021],\u0017n\u001a5uK\u0012tU/\u001c)pg&$\u0018N^3t?\u0012*\u0017\u000f\u0006\u0002<}A\u0011!\u0005P\u0005\u0003{\r\u0012A!\u00168ji\"9qHAA\u0001\u0002\u00041\u0014a\u0001=%c\u0005)r/Z5hQR,GMT;n!>\u001c\u0018\u000e^5wKN\u0004\u0013\u0001F<fS\u001eDG/\u001a3Ok6tUmZ1uSZ,7/\u0001\rxK&<\u0007\u000e^3e\u001dVlg*Z4bi&4Xm]0%KF$\"a\u000f#\t\u000f}*\u0011\u0011!a\u0001m\u0005)r/Z5hQR,GMT;n\u001d\u0016<\u0017\r^5wKN\u0004\u0013A\u0002\u001fj]&$h\bF\u0002I\u0015.\u0003\"!\u0013\u0001\u000e\u0003QAq\u0001N\u0004\u0011\u0002\u0003\u0007a\u0007C\u0004B\u000fA\u0005\t\u0019\u0001\u001c\u0002\u0011\u0011\u0002H.^:%KF$\"\u0001\u0013(\t\u000b=C\u0001\u0019\u0001\u001c\u0002\u000b1\f'-\u001a7\u0015\u0007!\u000b&\u000bC\u0003P\u0013\u0001\u0007a\u0007C\u0003T\u0013\u0001\u0007a'\u0001\u0004xK&<\u0007\u000e\u001e\u000b\u0003\u0011VCQA\u0016\u0006A\u0002!\u000bQa\u001c;iKJ\fQa\u00197p]\u0016$\u0012\u0001S\u0001\ti>\u001cFO]5oOR\t1\f\u0005\u0002]A:\u0011QL\u0018\t\u0003U\rJ!aX\u0012\u0002\rA\u0013X\rZ3g\u0013\t\t'M\u0001\u0004TiJLgn\u001a\u0006\u0003?\u000e\n!CQ5oCJLH*\u00192fY\u000e{WO\u001c;feB\u0011\u0011JD\n\u0004\u001d\u00052\u0007CA4m\u001b\u0005A'BA5k\u0003\tIwNC\u0001l\u0003\u0011Q\u0017M^1\n\u0005IBG#\u00013\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00132+\u0005\u0001(F\u0001\u001crW\u0005\u0011\bCA:y\u001b\u0005!(BA;w\u0003%)hn\u00195fG.,GM\u0003\u0002xG\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005e$(!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uII\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012! \t\u0004}\u0006\rQ\"A@\u000b\u0007\u0005\u0005!.\u0001\u0003mC:<\u0017bAA\u0003\u007f\n1qJ\u00196fGR\u0004"
)
public class BinaryLabelCounter implements Serializable {
   private double weightedNumPositives;
   private double weightedNumNegatives;

   public static double $lessinit$greater$default$2() {
      return BinaryLabelCounter$.MODULE$.$lessinit$greater$default$2();
   }

   public static double $lessinit$greater$default$1() {
      return BinaryLabelCounter$.MODULE$.$lessinit$greater$default$1();
   }

   public double weightedNumPositives() {
      return this.weightedNumPositives;
   }

   public void weightedNumPositives_$eq(final double x$1) {
      this.weightedNumPositives = x$1;
   }

   public double weightedNumNegatives() {
      return this.weightedNumNegatives;
   }

   public void weightedNumNegatives_$eq(final double x$1) {
      this.weightedNumNegatives = x$1;
   }

   public BinaryLabelCounter $plus$eq(final double label) {
      if (label > (double)0.5F) {
         this.weightedNumPositives_$eq(this.weightedNumPositives() + (double)1.0F);
      } else {
         this.weightedNumNegatives_$eq(this.weightedNumNegatives() + (double)1.0F);
      }

      return this;
   }

   public BinaryLabelCounter $plus$eq(final double label, final double weight) {
      if (label > (double)0.5F) {
         this.weightedNumPositives_$eq(this.weightedNumPositives() + weight);
      } else {
         this.weightedNumNegatives_$eq(this.weightedNumNegatives() + weight);
      }

      return this;
   }

   public BinaryLabelCounter $plus$eq(final BinaryLabelCounter other) {
      this.weightedNumPositives_$eq(this.weightedNumPositives() + other.weightedNumPositives());
      this.weightedNumNegatives_$eq(this.weightedNumNegatives() + other.weightedNumNegatives());
      return this;
   }

   public BinaryLabelCounter clone() {
      return new BinaryLabelCounter(this.weightedNumPositives(), this.weightedNumNegatives());
   }

   public String toString() {
      double var10000 = this.weightedNumPositives();
      return "{numPos: " + var10000 + ", numNeg: " + this.weightedNumNegatives() + "}";
   }

   public BinaryLabelCounter(final double weightedNumPositives, final double weightedNumNegatives) {
      this.weightedNumPositives = weightedNumPositives;
      this.weightedNumNegatives = weightedNumNegatives;
      super();
   }
}
