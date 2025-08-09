package org.apache.spark.ml.classification;

import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.sql.SparkSession;
import scala.Option;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eb\u0001\u0003\u0007\u000e!\u0003\r\taD\f\t\u000b5\u0002A\u0011A\u0018\t\u000fM\u0002!\u0019!C\u0001i!)Q\b\u0001C\u0001}\u001d1q(\u0004E\u0001\u001f\u00013a\u0001D\u0007\t\u0002=\t\u0005\"\u0002&\u0006\t\u0003Y\u0005\"\u0002'\u0006\t\u0003i\u0005\"B)\u0006\t\u0003\u0011\u0006bB:\u0006#\u0003%\t\u0001\u001e\u0005\u0007\u007f\u0016!\t!!\u0001\t\u0013\u0005%R!!A\u0005\n\u0005-\"aD(oKZ\u001b(+Z:u!\u0006\u0014\u0018-\\:\u000b\u00059y\u0011AD2mCN\u001c\u0018NZ5dCRLwN\u001c\u0006\u0003!E\t!!\u001c7\u000b\u0005I\u0019\u0012!B:qCJ\\'B\u0001\u000b\u0016\u0003\u0019\t\u0007/Y2iK*\ta#A\u0002pe\u001e\u001cR\u0001\u0001\r\u001fE\u0015\u0002\"!\u0007\u000f\u000e\u0003iQ\u0011aG\u0001\u0006g\u000e\fG.Y\u0005\u0003;i\u0011a!\u00118z%\u00164\u0007CA\u0010!\u001b\u0005i\u0011BA\u0011\u000e\u0005A\u0019E.Y:tS\u001aLWM\u001d)be\u0006l7\u000f\u0005\u0002 G%\u0011A%\u0004\u0002\u0014\u00072\f7o]5gS\u0016\u0014H+\u001f9f)J\f\u0017\u000e\u001e\t\u0003M-j\u0011a\n\u0006\u0003Q%\naa\u001d5be\u0016$'B\u0001\u0016\u0010\u0003\u0015\u0001\u0018M]1n\u0013\tasE\u0001\u0007ICN<V-[4ii\u000e{G.\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005\u0001\u0004CA\r2\u0013\t\u0011$D\u0001\u0003V]&$\u0018AC2mCN\u001c\u0018NZ5feV\tQ\u0007E\u00027oej\u0011!K\u0005\u0003q%\u0012Q\u0001U1sC6\u0004\"AO\u001e\u000e\u0003\u0001I!\u0001P\u0012\u0003\u001d\rc\u0017m]:jM&,'\u000fV=qK\u0006iq-\u001a;DY\u0006\u001c8/\u001b4jKJ,\u0012!O\u0001\u0010\u001f:,gk\u001d*fgR\u0004\u0016M]1ngB\u0011q$B\n\u0005\u000ba\u0011#\t\u0005\u0002D\u00116\tAI\u0003\u0002F\r\u0006\u0011\u0011n\u001c\u0006\u0002\u000f\u0006!!.\u0019<b\u0013\tIEI\u0001\u0007TKJL\u0017\r\\5{C\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0002\u0001\u0006qa/\u00197jI\u0006$X\rU1sC6\u001cHC\u0001\u0019O\u0011\u0015yu\u00011\u0001Q\u0003!Ign\u001d;b]\u000e,\u0007CA\u0010\u0001\u0003!\u0019\u0018M^3J[BdG#\u0002\u0019TA\u0006D\u0007\"\u0002+\t\u0001\u0004)\u0016\u0001\u00029bi\"\u0004\"AV/\u000f\u0005][\u0006C\u0001-\u001b\u001b\u0005I&B\u0001./\u0003\u0019a$o\\8u}%\u0011ALG\u0001\u0007!J,G-\u001a4\n\u0005y{&AB*ue&twM\u0003\u0002]5!)q\n\u0003a\u0001!\")!\u0003\u0003a\u0001EB\u00111MZ\u0007\u0002I*\u0011Q-E\u0001\u0004gFd\u0017BA4e\u00051\u0019\u0006/\u0019:l'\u0016\u001c8/[8o\u0011\u001dI\u0007\u0002%AA\u0002)\fQ\"\u001a=ue\u0006lU\r^1eCR\f\u0007cA\rl[&\u0011AN\u0007\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u00059\fX\"A8\u000b\u0005A,\u0012A\u00026t_:$4/\u0003\u0002s_\n9!j\u00142kK\u000e$\u0018AE:bm\u0016LU\u000e\u001d7%I\u00164\u0017-\u001e7uIQ*\u0012!\u001e\u0016\u0003UZ\\\u0013a\u001e\t\u0003qvl\u0011!\u001f\u0006\u0003un\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005qT\u0012AC1o]>$\u0018\r^5p]&\u0011a0\u001f\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017\u0001\u00037pC\u0012LU\u000e\u001d7\u0015\u0011\u0005\r\u0011\u0011EA\u0012\u0003K\u0001r!GA\u0003\u0003\u0013\ti\"C\u0002\u0002\bi\u0011a\u0001V;qY\u0016\u0014\u0004\u0003BA\u0006\u0003/qA!!\u0004\u0002\u00145\u0011\u0011q\u0002\u0006\u0004\u0003#y\u0011\u0001B;uS2LA!!\u0006\u0002\u0010\u0005\u0019B)\u001a4bk2$\b+\u0019:b[N\u0014V-\u00193fe&!\u0011\u0011DA\u000e\u0005!iU\r^1eCR\f'\u0002BA\u000b\u0003\u001f\u00012!a\b<\u001b\u0005)\u0001\"\u0002+\u000b\u0001\u0004)\u0006\"\u0002\n\u000b\u0001\u0004\u0011\u0007BBA\u0014\u0015\u0001\u0007Q+A\tfqB,7\r^3e\u00072\f7o\u001d(b[\u0016\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\f\u0011\t\u0005=\u0012QG\u0007\u0003\u0003cQ1!a\rG\u0003\u0011a\u0017M\\4\n\t\u0005]\u0012\u0011\u0007\u0002\u0007\u001f\nTWm\u0019;"
)
public interface OneVsRestParams extends ClassifierParams, ClassifierTypeTrait, HasWeightCol {
   static Tuple2 loadImpl(final String path, final SparkSession spark, final String expectedClassName) {
      return OneVsRestParams$.MODULE$.loadImpl(path, spark, expectedClassName);
   }

   static Option saveImpl$default$4() {
      return OneVsRestParams$.MODULE$.saveImpl$default$4();
   }

   static void saveImpl(final String path, final OneVsRestParams instance, final SparkSession spark, final Option extraMetadata) {
      OneVsRestParams$.MODULE$.saveImpl(path, instance, spark, extraMetadata);
   }

   static void validateParams(final OneVsRestParams instance) {
      OneVsRestParams$.MODULE$.validateParams(instance);
   }

   void org$apache$spark$ml$classification$OneVsRestParams$_setter_$classifier_$eq(final Param x$1);

   Param classifier();

   // $FF: synthetic method
   static Classifier getClassifier$(final OneVsRestParams $this) {
      return $this.getClassifier();
   }

   default Classifier getClassifier() {
      return (Classifier)this.$(this.classifier());
   }

   static void $init$(final OneVsRestParams $this) {
      $this.org$apache$spark$ml$classification$OneVsRestParams$_setter_$classifier_$eq(new Param($this, "classifier", "base binary classifier", .MODULE$.apply(Classifier.class)));
   }
}
