package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import org.sparkproject.guava.base.Objects;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I4A!\u0005\n\u0001?!A!\u0007\u0001BC\u0002\u0013\u00051\u0007\u0003\u00058\u0001\t\u0005\t\u0015!\u00035\u0011!A\u0004A!b\u0001\n\u0003\u0019\u0004\u0002C\u001d\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001b\t\u0011i\u0002!Q1A\u0005\u0002MB\u0001b\u000f\u0001\u0003\u0002\u0003\u0006I\u0001\u000e\u0005\ty\u0001\u0011)\u0019!C\u0001g!AQ\b\u0001B\u0001B\u0003%A\u0007\u0003\u0005?\u0001\t\u0015\r\u0011\"\u0001@\u0011!!\u0005A!A!\u0002\u0013\u0001\u0005\u0002C#\u0001\u0005\u000b\u0007I\u0011A \t\u0011\u0019\u0003!\u0011!Q\u0001\n\u0001CQa\u0012\u0001\u0005\u0002!CQ\u0001\u0015\u0001\u0005BECQA\u0017\u0001\u0005BmCQ\u0001\u001a\u0001\u0005B\u0015\u0014A#\u00138g_Jl\u0017\r^5p]\u001e\u000b\u0017N\\*uCR\u001c(BA\n\u0015\u0003\u0015iw\u000eZ3m\u0015\t)b#\u0001\u0003ue\u0016,'BA\f\u0019\u0003\u0015iG\u000e\\5c\u0015\tI\"$A\u0003ta\u0006\u00148N\u0003\u0002\u001c9\u00051\u0011\r]1dQ\u0016T\u0011!H\u0001\u0004_J<7\u0001A\n\u0004\u0001\u00012\u0003CA\u0011%\u001b\u0005\u0011#\"A\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0015\u0012#AB!osJ+g\r\u0005\u0002(_9\u0011\u0001&\f\b\u0003S1j\u0011A\u000b\u0006\u0003Wy\ta\u0001\u0010:p_Rt\u0014\"A\u0012\n\u00059\u0012\u0013a\u00029bG.\fw-Z\u0005\u0003aE\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!A\f\u0012\u0002\t\u001d\f\u0017N\\\u000b\u0002iA\u0011\u0011%N\u0005\u0003m\t\u0012a\u0001R8vE2,\u0017!B4bS:\u0004\u0013\u0001C5naV\u0014\u0018\u000e^=\u0002\u0013%l\u0007/\u001e:jif\u0004\u0013\u0001\u00047fMRLU\u000e];sSRL\u0018!\u00047fMRLU\u000e];sSRL\b%A\u0007sS\u001eDG/S7qkJLG/_\u0001\u000fe&<\u0007\u000e^%naV\u0014\u0018\u000e^=!\u0003-aWM\u001a;Qe\u0016$\u0017n\u0019;\u0016\u0003\u0001\u0003\"!\u0011\"\u000e\u0003II!a\u0011\n\u0003\u000fA\u0013X\rZ5di\u0006aA.\u001a4u!J,G-[2uA\u0005a!/[4iiB\u0013X\rZ5di\u0006i!/[4iiB\u0013X\rZ5di\u0002\na\u0001P5oSRtDcB%K\u00172kej\u0014\t\u0003\u0003\u0002AQAM\u0007A\u0002QBQ\u0001O\u0007A\u0002QBQAO\u0007A\u0002QBQ\u0001P\u0007A\u0002QBQAP\u0007A\u0002\u0001CQ!R\u0007A\u0002\u0001\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0002%B\u00111k\u0016\b\u0003)V\u0003\"!\u000b\u0012\n\u0005Y\u0013\u0013A\u0002)sK\u0012,g-\u0003\u0002Y3\n11\u000b\u001e:j]\u001eT!A\u0016\u0012\u0002\r\u0015\fX/\u00197t)\tav\f\u0005\u0002\";&\u0011aL\t\u0002\b\u0005>|G.Z1o\u0011\u0015\u0001w\u00021\u0001b\u0003\u0005y\u0007CA\u0011c\u0013\t\u0019'EA\u0002B]f\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002MB\u0011\u0011eZ\u0005\u0003Q\n\u00121!\u00138uQ\r\u0001!\u000e\u001d\t\u0003W:l\u0011\u0001\u001c\u0006\u0003[b\t!\"\u00198o_R\fG/[8o\u0013\tyGNA\u0003TS:\u001cW-I\u0001r\u0003\u0015\td\u0006\r\u00181\u0001"
)
public class InformationGainStats implements Serializable {
   private final double gain;
   private final double impurity;
   private final double leftImpurity;
   private final double rightImpurity;
   private final Predict leftPredict;
   private final Predict rightPredict;

   public double gain() {
      return this.gain;
   }

   public double impurity() {
      return this.impurity;
   }

   public double leftImpurity() {
      return this.leftImpurity;
   }

   public double rightImpurity() {
      return this.rightImpurity;
   }

   public Predict leftPredict() {
      return this.leftPredict;
   }

   public Predict rightPredict() {
      return this.rightPredict;
   }

   public String toString() {
      double var10000 = this.gain();
      return "gain = " + var10000 + ", impurity = " + this.impurity() + ", left impurity = " + this.leftImpurity() + ", right impurity = " + this.rightImpurity();
   }

   public boolean equals(final Object o) {
      if (!(o instanceof InformationGainStats var4)) {
         return false;
      } else {
         boolean var8;
         label57: {
            if (this.gain() == var4.gain() && this.impurity() == var4.impurity() && this.leftImpurity() == var4.leftImpurity() && this.rightImpurity() == var4.rightImpurity()) {
               label51: {
                  Predict var10000 = this.leftPredict();
                  Predict var5 = var4.leftPredict();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label51;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label51;
                  }

                  var10000 = this.rightPredict();
                  Predict var6 = var4.rightPredict();
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label57;
                     }
                  } else if (var10000.equals(var6)) {
                     break label57;
                  }
               }
            }

            var8 = false;
            return var8;
         }

         var8 = true;
         return var8;
      }
   }

   public int hashCode() {
      return Objects.hashCode(new Object[]{.MODULE$.double2Double(this.gain()), .MODULE$.double2Double(this.impurity()), .MODULE$.double2Double(this.leftImpurity()), .MODULE$.double2Double(this.rightImpurity()), this.leftPredict(), this.rightPredict()});
   }

   public InformationGainStats(final double gain, final double impurity, final double leftImpurity, final double rightImpurity, final Predict leftPredict, final Predict rightPredict) {
      this.gain = gain;
      this.impurity = impurity;
      this.leftImpurity = leftImpurity;
      this.rightImpurity = rightImpurity;
      this.leftPredict = leftPredict;
      this.rightPredict = rightPredict;
   }
}
