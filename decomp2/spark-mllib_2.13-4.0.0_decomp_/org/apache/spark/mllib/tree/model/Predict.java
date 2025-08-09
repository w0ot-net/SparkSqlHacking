package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import org.sparkproject.guava.base.Objects;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%a\u0001\u0002\b\u0010\u0001qA\u0001b\f\u0001\u0003\u0006\u0004%\t\u0001\r\u0005\t{\u0001\u0011\t\u0011)A\u0005c!Aq\b\u0001BC\u0002\u0013\u0005\u0001\u0007\u0003\u0005B\u0001\t\u0005\t\u0015!\u00032\u0011\u0015\u0019\u0005\u0001\"\u0001E\u0011\u0015a\u0005\u0001\"\u0011N\u0011\u00151\u0006\u0001\"\u0011X\u0011\u0015\u0001\u0007\u0001\"\u0011b\u000f\u001d1w\"!A\t\u0002\u001d4qAD\b\u0002\u0002#\u0005\u0001\u000eC\u0003D\u0015\u0011\u0005\u0001\u000fC\u0004r\u0015E\u0005I\u0011\u0001:\t\u000fqT\u0011\u0011!C\u0005{\n9\u0001K]3eS\u000e$(B\u0001\t\u0012\u0003\u0015iw\u000eZ3m\u0015\t\u00112#\u0001\u0003ue\u0016,'B\u0001\u000b\u0016\u0003\u0015iG\u000e\\5c\u0015\t1r#A\u0003ta\u0006\u00148N\u0003\u0002\u00193\u00051\u0011\r]1dQ\u0016T\u0011AG\u0001\u0004_J<7\u0001A\n\u0004\u0001u\u0019\u0003C\u0001\u0010\"\u001b\u0005y\"\"\u0001\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\tz\"AB!osJ+g\r\u0005\u0002%Y9\u0011QE\u000b\b\u0003M%j\u0011a\n\u0006\u0003Qm\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0011\n\u0005-z\u0012a\u00029bG.\fw-Z\u0005\u0003[9\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!aK\u0010\u0002\u000fA\u0014X\rZ5diV\t\u0011\u0007\u0005\u0002\u001fe%\u00111g\b\u0002\u0007\t>,(\r\\3)\u0007\u0005)4\b\u0005\u00027s5\tqG\u0003\u00029+\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005i:$!B*j]\u000e,\u0017%\u0001\u001f\u0002\u000bEr#G\f\u0019\u0002\u0011A\u0014X\rZ5di\u0002B3AA\u001b<\u0003\u0011\u0001(o\u001c2)\u0007\r)4(A\u0003qe>\u0014\u0007\u0005K\u0002\u0005km\na\u0001P5oSRtDcA#H\u0013B\u0011a\tA\u0007\u0002\u001f!)q&\u0002a\u0001c!\u001aq)N\u001e\t\u000f}*\u0001\u0013!a\u0001c!\u001a\u0011*N\u001e)\u0007\u0015)4(\u0001\u0005u_N#(/\u001b8h)\u0005q\u0005CA(T\u001d\t\u0001\u0016\u000b\u0005\u0002'?%\u0011!kH\u0001\u0007!J,G-\u001a4\n\u0005Q+&AB*ue&twM\u0003\u0002S?\u00051Q-];bYN$\"\u0001W.\u0011\u0005yI\u0016B\u0001. \u0005\u001d\u0011un\u001c7fC:DQ\u0001X\u0004A\u0002u\u000bQa\u001c;iKJ\u0004\"A\b0\n\u0005}{\"aA!os\u0006A\u0001.Y:i\u0007>$W\rF\u0001c!\tq2-\u0003\u0002e?\t\u0019\u0011J\u001c;)\u0007\u0001)4(A\u0004Qe\u0016$\u0017n\u0019;\u0011\u0005\u0019S1c\u0001\u0006\u001eSB\u0011!n\\\u0007\u0002W*\u0011A.\\\u0001\u0003S>T\u0011A\\\u0001\u0005U\u00064\u0018-\u0003\u0002.WR\tq-A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u000b\u0002g*\u0012\u0011\u0007^\u0016\u0002kB\u0011aO_\u0007\u0002o*\u0011\u00010_\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001O\u0010\n\u0005m<(!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006aqO]5uKJ+\u0007\u000f\\1dKR\ta\u0010E\u0002\u0000\u0003\u000bi!!!\u0001\u000b\u0007\u0005\rQ.\u0001\u0003mC:<\u0017\u0002BA\u0004\u0003\u0003\u0011aa\u00142kK\u000e$\b"
)
public class Predict implements Serializable {
   private final double predict;
   private final double prob;

   public static double $lessinit$greater$default$2() {
      return Predict$.MODULE$.$lessinit$greater$default$2();
   }

   public double predict() {
      return this.predict;
   }

   public double prob() {
      return this.prob;
   }

   public String toString() {
      double var10000 = this.predict();
      return var10000 + " (prob = " + this.prob() + ")";
   }

   public boolean equals(final Object other) {
      if (!(other instanceof Predict var4)) {
         return false;
      } else {
         return this.predict() == var4.predict() && this.prob() == var4.prob();
      }
   }

   public int hashCode() {
      return Objects.hashCode(new Object[]{.MODULE$.double2Double(this.predict()), .MODULE$.double2Double(this.prob())});
   }

   public Predict(final double predict, final double prob) {
      this.predict = predict;
      this.prob = prob;
   }
}
