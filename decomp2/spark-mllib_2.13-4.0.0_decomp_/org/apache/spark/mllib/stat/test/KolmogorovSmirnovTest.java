package org.apache.spark.mllib.stat.test;

import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.spark.internal.Logging;
import org.apache.spark.rdd.RDD;
import scala.Enumeration;
import scala.Function1;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-tAB\t\u0013\u0011\u0003!bD\u0002\u0004!%!\u0005A#\t\u0005\u0006]\u0005!\t\u0001M\u0004\u0006c\u0005A\tA\r\u0004\u0006i\u0005A\t!\u000e\u0005\u0006]\u0011!\t!O\u0003\u0005i\u0011\u0001!\bC\u0004?\t\t\u0007I\u0011A \t\r\u0001#\u0001\u0015!\u0003;\u0011\u001d\tE!!A\u0005\n\tCQaS\u0001\u0005\u00021CQaS\u0001\u0005\u0002\u0001DQA\\\u0001\u0005\n=Dq!a\u0003\u0002\t\u0013\ti\u0001C\u0004\u0002\u001c\u0005!I!!\b\t\r-\u000bA\u0011AA\u0016\u0011\u001d\tY&\u0001C\u0005\u0003;\nQcS8m[><wN]8w'6L'O\\8w)\u0016\u001cHO\u0003\u0002\u0014)\u0005!A/Z:u\u0015\t)b#\u0001\u0003ti\u0006$(BA\f\u0019\u0003\u0015iG\u000e\\5c\u0015\tI\"$A\u0003ta\u0006\u00148N\u0003\u0002\u001c9\u00051\u0011\r]1dQ\u0016T\u0011!H\u0001\u0004_J<\u0007CA\u0010\u0002\u001b\u0005\u0011\"!F&pY6|wm\u001c:pmNk\u0017N\u001d8pmR+7\u000f^\n\u0004\u0003\tB\u0003CA\u0012'\u001b\u0005!#\"A\u0013\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u001d\"#AB!osJ+g\r\u0005\u0002*Y5\t!F\u0003\u0002,1\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002.U\t9Aj\\4hS:<\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003y\taBT;mY\"K\bo\u001c;iKNL7\u000f\u0005\u00024\t5\t\u0011A\u0001\bOk2d\u0007*\u001f9pi\",7/[:\u0014\u0005\u00111\u0004CA\u00128\u0013\tADEA\u0006F]VlWM]1uS>tG#\u0001\u001a\u0011\u0005mbT\"\u0001\u0003\n\u0005u:$!\u0002,bYV,\u0017!E(oKN\u000bW\u000e\u001d7f)^|7+\u001b3fIV\t!(\u0001\nP]\u0016\u001c\u0016-\u001c9mKR;xnU5eK\u0012\u0004\u0013\u0001D<sSR,'+\u001a9mC\u000e,G#A\"\u0011\u0005\u0011KU\"A#\u000b\u0005\u0019;\u0015\u0001\u00027b]\u001eT\u0011\u0001S\u0001\u0005U\u00064\u0018-\u0003\u0002K\u000b\n1qJ\u00196fGR\fQ\u0002^3ti>sWmU1na2,GcA'Q7B\u0011qDT\u0005\u0003\u001fJ\u00111dS8m[><wN]8w'6L'O\\8w)\u0016\u001cHOU3tk2$\b\"B)\u000b\u0001\u0004\u0011\u0016\u0001\u00023bi\u0006\u00042a\u0015,Y\u001b\u0005!&BA+\u0019\u0003\r\u0011H\rZ\u0005\u0003/R\u00131A\u0015#E!\t\u0019\u0013,\u0003\u0002[I\t1Ai\\;cY\u0016DQ\u0001\u0018\u0006A\u0002u\u000b1a\u00193g!\u0011\u0019c\f\u0017-\n\u0005}##!\u0003$v]\u000e$\u0018n\u001c82)\ri\u0015M\u0019\u0005\u0006#.\u0001\rA\u0015\u0005\u0006G.\u0001\r\u0001Z\u0001\bI&\u001cHo\u00142k!\t)G.D\u0001g\u0015\t9\u0007.\u0001\u0007eSN$(/\u001b2vi&|gN\u0003\u0002jU\u0006)Q.\u0019;ig)\u00111NG\u0001\bG>lWn\u001c8t\u0013\tigM\u0001\tSK\u0006dG)[:ue&\u0014W\u000f^5p]\u0006!rN\\3TC6\u0004H.\u001a#jM\u001a,'/\u001a8dKN$b\u0001]@\u0002\u0006\u0005%\u0001cA9zy:\u0011!o\u001e\b\u0003gZl\u0011\u0001\u001e\u0006\u0003k>\na\u0001\u0010:p_Rt\u0014\"A\u0013\n\u0005a$\u0013a\u00029bG.\fw-Z\u0005\u0003un\u0014\u0001\"\u0013;fe\u0006$xN\u001d\u0006\u0003q\u0012\u0002BaI?Y1&\u0011a\u0010\n\u0002\u0007)V\u0004H.\u001a\u001a\t\u000f\u0005\u0005A\u00021\u0001\u0002\u0004\u0005A\u0001/\u0019:u\t\u0006$\u0018\rE\u0002rsbCa!a\u0002\r\u0001\u0004A\u0016!\u00018\t\u000bqc\u0001\u0019A/\u00023M,\u0017M]2i\u001f:,7+Y7qY\u0016\u001c\u0015M\u001c3jI\u0006$Xm\u001d\u000b\u0005\u0003\u001f\t9\u0002\u0005\u0003rs\u0006E\u0001CB\u0012\u0002\u0014aC\u0006,C\u0002\u0002\u0016\u0011\u0012a\u0001V;qY\u0016\u001c\u0004BBA\r\u001b\u0001\u0007\u0001/A\u0005qCJ$H)\u001b4gg\u0006A2/Z1sG\"|e.Z*b[BdWm\u0015;bi&\u001cH/[2\u0015\u000ba\u000by\"!\u000b\t\u000f\u0005\u0005b\u00021\u0001\u0002$\u0005IAn\\2bY\u0012\u000bG/\u0019\t\u0006G\u0005\u0015\u0012\u0011C\u0005\u0004\u0003O!#!B!se\u0006L\bBBA\u0004\u001d\u0001\u0007\u0001\fF\u0004N\u0003[\ty#a\u0011\t\u000bE{\u0001\u0019\u0001*\t\u000f\u0005Er\u00021\u0001\u00024\u0005AA-[:u\u001d\u0006lW\r\u0005\u0003\u00026\u0005ub\u0002BA\u001c\u0003s\u0001\"a\u001d\u0013\n\u0007\u0005mB%\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003\u007f\t\tE\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003w!\u0003bBA#\u001f\u0001\u0007\u0011qI\u0001\u0007a\u0006\u0014\u0018-\\:\u0011\t\r\nI\u0005W\u0005\u0004\u0003\u0017\"#A\u0003\u001fsKB,\u0017\r^3e}!\u001aq\"a\u0014\u0011\t\u0005E\u0013qK\u0007\u0003\u0003'R1!!\u0016%\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u00033\n\u0019FA\u0004wCJ\f'oZ:\u0002\u001d\u00154\u0018\r\\(oKN\u000bW\u000e\u001d7f!R)Q*a\u0018\u0002d!1\u0011\u0011\r\tA\u0002a\u000baa[:Ti\u0006$\bbBA\u0004!\u0001\u0007\u0011Q\r\t\u0004G\u0005\u001d\u0014bAA5I\t!Aj\u001c8h\u0001"
)
public final class KolmogorovSmirnovTest {
   public static KolmogorovSmirnovTestResult testOneSample(final RDD data, final String distName, final double... params) {
      return KolmogorovSmirnovTest$.MODULE$.testOneSample(data, distName, params);
   }

   public static KolmogorovSmirnovTestResult testOneSample(final RDD data, final String distName, final Seq params) {
      return KolmogorovSmirnovTest$.MODULE$.testOneSample(data, distName, params);
   }

   public static KolmogorovSmirnovTestResult testOneSample(final RDD data, final RealDistribution distObj) {
      return KolmogorovSmirnovTest$.MODULE$.testOneSample(data, distObj);
   }

   public static KolmogorovSmirnovTestResult testOneSample(final RDD data, final Function1 cdf) {
      return KolmogorovSmirnovTest$.MODULE$.testOneSample(data, cdf);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return KolmogorovSmirnovTest$.MODULE$.LogStringContext(sc);
   }

   public static class NullHypothesis$ extends Enumeration {
      public static final NullHypothesis$ MODULE$ = new NullHypothesis$();
      private static final Enumeration.Value OneSampleTwoSided;

      static {
         OneSampleTwoSided = MODULE$.Value("Sample follows theoretical distribution");
      }

      public Enumeration.Value OneSampleTwoSided() {
         return OneSampleTwoSided;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(NullHypothesis$.class);
      }
   }
}
