package breeze.stats.distributions;

import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q3q!\u0002\u0004\u0011\u0002G\u0005Q\u0002B\u0003,\u0001\t\u0005A\u0006C\u00043\u0001\t\u0007i\u0011A\u001a\t\u000bY\u0002a\u0011A\u001c\t\u000b\t\u0003a\u0011A\"\u0003#!\u000b7oQ8oUV<\u0017\r^3Qe&|'O\u0003\u0002\b\u0011\u0005iA-[:ue&\u0014W\u000f^5p]NT!!\u0003\u0006\u0002\u000bM$\u0018\r^:\u000b\u0003-\taA\u0019:fKj,7\u0001A\u000b\u0004\u001dm)3c\u0001\u0001\u0010+A\u0011\u0001cE\u0007\u0002#)\t!#A\u0003tG\u0006d\u0017-\u0003\u0002\u0015#\t1\u0011I\\=SK\u001a\u0004BAF\f\u001aI5\ta!\u0003\u0002\u0019\r\t\tR\t\u001f9p]\u0016tG/[1m\r\u0006l\u0017\u000e\\=\u0011\u0005iYB\u0002\u0001\u0003\u00069\u0001\u0011\r!\b\u0002\u000b\u0019&\\W\r\\5i_>$\u0017C\u0001\u0010\"!\t\u0001r$\u0003\u0002!#\t9aj\u001c;iS:<\u0007c\u0001\f#I%\u00111E\u0002\u0002\b\t\u0016t7/\u001b;z!\tQR\u0005B\u0003'\u0001\t\u0007qEA\u0001U#\tq\u0002\u0006\u0005\u0002\u0011S%\u0011!&\u0005\u0002\u0004\u0003:L(AD\"p]*,x-\u0019;f!JLwN]\t\u0003=5\u00022A\u0006\u0012/!\ty\u0003'D\u0001\u0001\u0013\t\ttCA\u0005QCJ\fW.\u001a;fe\u0006y1m\u001c8kk\u001e\fG/\u001a$b[&d\u00170F\u00015!\u00111r#\u000e\u0018\u0011\u0005=\n\u0011A\u00039sK\u0012L7\r^5wKR\u0011\u0001H\u0010\u000b\u0003CeBQAO\u0002A\u0004m\nQAY1tSN\u0004\"A\u0006\u001f\n\u0005u2!!\u0003*b]\u0012\u0014\u0015m]5t\u0011\u0015y4\u00011\u0001A\u0003%\u0001\u0018M]1nKR,'\u000f\u0005\u0002Ba9\u0011qFA\u0001\na>\u001cH/\u001a:j_J$2\u0001\u0011#G\u0011\u0015)E\u00011\u0001A\u0003\u0015\u0001(/[8s\u0011\u00159E\u00011\u0001I\u0003!)g/\u001b3f]\u000e,\u0007cA%RI9\u0011!j\u0014\b\u0003\u0017:k\u0011\u0001\u0014\u0006\u0003\u001b2\ta\u0001\u0010:p_Rt\u0014\"\u0001\n\n\u0005A\u000b\u0012a\u00029bG.\fw-Z\u0005\u0003%N\u0013q\u0002\u0016:bm\u0016\u00148/\u00192mK>s7-\u001a\u0006\u0003!F\u0001"
)
public interface HasConjugatePrior extends ExponentialFamily {
   ExponentialFamily conjugateFamily();

   Density predictive(final Object parameter, final RandBasis basis);

   Object posterior(final Object prior, final IterableOnce evidence);
}
