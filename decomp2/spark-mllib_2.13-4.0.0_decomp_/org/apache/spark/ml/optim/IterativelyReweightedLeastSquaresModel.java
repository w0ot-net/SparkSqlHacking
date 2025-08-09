package org.apache.spark.ml.optim;

import java.io.Serializable;
import org.apache.spark.ml.linalg.DenseVector;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!3QAC\u0006\u0001\u001bUA\u0001\"\u000b\u0001\u0003\u0006\u0004%\tA\u000b\u0005\tc\u0001\u0011\t\u0011)A\u0005W!A!\u0007\u0001BC\u0002\u0013\u00051\u0007\u0003\u00058\u0001\t\u0005\t\u0015!\u00035\u0011!A\u0004A!b\u0001\n\u0003Q\u0003\u0002C\u001d\u0001\u0005\u0003\u0005\u000b\u0011B\u0016\t\u0011i\u0002!Q1A\u0005\u0002mB\u0001b\u0010\u0001\u0003\u0002\u0003\u0006I\u0001\u0010\u0005\u0006\u0001\u0002!\t!\u0011\u0002'\u0013R,'/\u0019;jm\u0016d\u0017PU3xK&<\u0007\u000e^3e\u0019\u0016\f7\u000f^*rk\u0006\u0014Xm]'pI\u0016d'B\u0001\u0007\u000e\u0003\u0015y\u0007\u000f^5n\u0015\tqq\"\u0001\u0002nY*\u0011\u0001#E\u0001\u0006gB\f'o\u001b\u0006\u0003%M\ta!\u00199bG\",'\"\u0001\u000b\u0002\u0007=\u0014xmE\u0002\u0001-q\u0001\"a\u0006\u000e\u000e\u0003aQ\u0011!G\u0001\u0006g\u000e\fG.Y\u0005\u00037a\u0011a!\u00118z%\u00164\u0007CA\u000f'\u001d\tqBE\u0004\u0002 G5\t\u0001E\u0003\u0002\"E\u00051AH]8piz\u001a\u0001!C\u0001\u001a\u0013\t)\u0003$A\u0004qC\u000e\\\u0017mZ3\n\u0005\u001dB#\u0001D*fe&\fG.\u001b>bE2,'BA\u0013\u0019\u00031\u0019w.\u001a4gS\u000eLWM\u001c;t+\u0005Y\u0003C\u0001\u00170\u001b\u0005i#B\u0001\u0018\u000e\u0003\u0019a\u0017N\\1mO&\u0011\u0001'\f\u0002\f\t\u0016t7/\u001a,fGR|'/A\u0007d_\u00164g-[2jK:$8\u000fI\u0001\nS:$XM]2faR,\u0012\u0001\u000e\t\u0003/UJ!A\u000e\r\u0003\r\u0011{WO\u00197f\u0003)Ig\u000e^3sG\u0016\u0004H\u000fI\u0001\fI&\fw-\u00138w\u0003R<\u0016)\u0001\u0007eS\u0006<\u0017J\u001c<Bi^\u000b\u0005%A\u0007ok6LE/\u001a:bi&|gn]\u000b\u0002yA\u0011q#P\u0005\u0003}a\u00111!\u00138u\u00039qW/\\%uKJ\fG/[8og\u0002\na\u0001P5oSRtD#\u0002\"E\u000b\u001a;\u0005CA\"\u0001\u001b\u0005Y\u0001\"B\u0015\n\u0001\u0004Y\u0003\"\u0002\u001a\n\u0001\u0004!\u0004\"\u0002\u001d\n\u0001\u0004Y\u0003\"\u0002\u001e\n\u0001\u0004a\u0004"
)
public class IterativelyReweightedLeastSquaresModel implements Serializable {
   private final DenseVector coefficients;
   private final double intercept;
   private final DenseVector diagInvAtWA;
   private final int numIterations;

   public DenseVector coefficients() {
      return this.coefficients;
   }

   public double intercept() {
      return this.intercept;
   }

   public DenseVector diagInvAtWA() {
      return this.diagInvAtWA;
   }

   public int numIterations() {
      return this.numIterations;
   }

   public IterativelyReweightedLeastSquaresModel(final DenseVector coefficients, final double intercept, final DenseVector diagInvAtWA, final int numIterations) {
      this.coefficients = coefficients;
      this.intercept = intercept;
      this.diagInvAtWA = diagInvAtWA;
      this.numIterations = numIterations;
   }
}
