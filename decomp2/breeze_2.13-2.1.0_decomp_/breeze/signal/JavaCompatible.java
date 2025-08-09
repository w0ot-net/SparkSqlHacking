package breeze.signal;

import breeze.math.Complex;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}t!B\u0010!\u0011\u0003)c!B\u0014!\u0011\u0003A\u0003\"B\u0018\u0002\t\u0003\u0001\u0004\"B\u0019\u0002\t\u0003\u0011\u0004\"B\u001f\u0002\t\u0003q\u0004\"B!\u0002\t\u0003\u0011\u0005\"B&\u0002\t\u0003a\u0005\"\u0002(\u0002\t\u0003y\u0005\"B)\u0002\t\u0003\u0011\u0006\"B+\u0002\t\u00031\u0006\"\u0002-\u0002\t\u0003I\u0006\"B.\u0002\t\u0003a\u0006\"\u00020\u0002\t\u0003y\u0006\"B1\u0002\t\u0003\u0011\u0007\"B1\u0002\t\u0003y\u0007\"\u0002:\u0002\t\u0003\u0019\b\"\u0002:\u0002\t\u0003i\bB\u0002:\u0002\t\u0003\t)\u0001C\u0004\u0002\u000e\u0005!\t!a\u0004\t\u000f\u00055\u0011\u0001\"\u0001\u0002\u001c!9\u0011QB\u0001\u0005\u0002\u0005\u0015\u0002bBA\u0017\u0003\u0011\u0005\u0011q\u0006\u0005\b\u0003[\tA\u0011AA\u001e\u0011\u001d\ti#\u0001C\u0001\u0003\u0007Bq!!\u0013\u0002\t\u0003\tY\u0005C\u0004\u0002J\u0005!\t!!\u0016\t\u000f\u0005%\u0013\u0001\"\u0001\u0002^!9\u00111M\u0001\u0005\u0002\u0005\u0015\u0004bBA5\u0003\u0011\u0005\u00111\u000e\u0005\b\u0003c\nA\u0011AA:\u0011\u001d\t9(\u0001C\u0001\u0003s\naBS1wC\u000e{W\u000e]1uS\ndWM\u0003\u0002\"E\u000511/[4oC2T\u0011aI\u0001\u0007EJ,WM_3\u0004\u0001A\u0011a%A\u0007\u0002A\tq!*\u0019<b\u0007>l\u0007/\u0019;jE2,7CA\u0001*!\tQS&D\u0001,\u0015\u0005a\u0013!B:dC2\f\u0017B\u0001\u0018,\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012!J\u0001\tG>tgo\u001c7wKR\u00191'O\u001e\u0011\u0007)\"d'\u0003\u00026W\t)\u0011I\u001d:bsB\u0011!fN\u0005\u0003q-\u0012a\u0001R8vE2,\u0007\"\u0002\u001e\u0004\u0001\u0004\u0019\u0014\u0001\u00023bi\u0006DQ\u0001P\u0002A\u0002M\naa[3s]\u0016d\u0017!C2peJ,G.\u0019;f)\r\u0019t\b\u0011\u0005\u0006u\u0011\u0001\ra\r\u0005\u0006y\u0011\u0001\raM\u0001\u000bM>,(/[3s)J$ECA\"K!\rQC\u0007\u0012\t\u0003\u000b\"k\u0011A\u0012\u0006\u0003\u000f\n\nA!\\1uQ&\u0011\u0011J\u0012\u0002\b\u0007>l\u0007\u000f\\3y\u0011\u0015QT\u00011\u00014\u0003)1w.\u001e:jKJ$&o\u0011\u000b\u0003\u00076CQA\u000f\u0004A\u0002\r\u000b1\"\u001b$pkJLWM\u001d+s\u0007R\u00111\t\u0015\u0005\u0006u\u001d\u0001\raQ\u0001\fM>,(/[3s)J\u00144\t\u0006\u0002T)B\u0019!\u0006N\"\t\u000biB\u0001\u0019A*\u0002\u001b\u0019|WO]5feNC\u0017N\u001a;E)\t\u0019t\u000bC\u0003;\u0013\u0001\u00071'A\u0007g_V\u0014\u0018.\u001a:TQ&4Go\u0011\u000b\u0003\u0007jCQA\u000f\u0006A\u0002\r\u000ba\"\u001b$pkJLWM]*iS\u001a$H\t\u0006\u00024;\")!h\u0003a\u0001g\u0005q\u0011NR8ve&,'o\u00155jMR\u001cECA\"a\u0011\u0015QD\u00021\u0001D\u000311w.\u001e:jKJ4%/Z9E)\u0011\u00194\r\u001b6\t\u000b\u0011l\u0001\u0019A3\u0002\u0019]Lg\u000eZ8x\u0019\u0016tw\r\u001e5\u0011\u0005)2\u0017BA4,\u0005\rIe\u000e\u001e\u0005\u0006S6\u0001\rAN\u0001\u0003MNDQa[\u0007A\u00021\fqa\u001d5jMR,G\r\u0005\u0002+[&\u0011an\u000b\u0002\b\u0005>|G.Z1o)\r\u0019\u0004/\u001d\u0005\u0006I:\u0001\r!\u001a\u0005\u0006S:\u0001\rAN\u0001\tM&dG/\u001a:C!R11\u0007^;xsnDQAO\bA\u0002MBQA^\bA\u0002Y\n\u0001b\\7fO\u0006dun\u001e\u0005\u0006q>\u0001\rAN\u0001\n_6,w-\u0019%jO\"DQA_\bA\u0002Y\n!b]1na2,'+\u0019;f\u0011\u0015ax\u00021\u0001f\u0003\u0011!\u0018\r]:\u0015\u000fMrx0!\u0001\u0002\u0004!)!\b\u0005a\u0001g!)a\u000f\u0005a\u0001m!)\u0001\u0010\u0005a\u0001m!)!\u0010\u0005a\u0001mQ91'a\u0002\u0002\n\u0005-\u0001\"\u0002\u001e\u0012\u0001\u0004\u0019\u0004\"\u0002<\u0012\u0001\u00041\u0004\"\u0002=\u0012\u0001\u00041\u0014\u0001\u00034jYR,'OQ*\u0015\u0017M\n\t\"a\u0005\u0002\u0016\u0005]\u0011\u0011\u0004\u0005\u0006uI\u0001\ra\r\u0005\u0006mJ\u0001\rA\u000e\u0005\u0006qJ\u0001\rA\u000e\u0005\u0006uJ\u0001\rA\u000e\u0005\u0006yJ\u0001\r!\u001a\u000b\ng\u0005u\u0011qDA\u0011\u0003GAQAO\nA\u0002MBQA^\nA\u0002YBQ\u0001_\nA\u0002YBQA_\nA\u0002Y\"raMA\u0014\u0003S\tY\u0003C\u0003;)\u0001\u00071\u0007C\u0003w)\u0001\u0007a\u0007C\u0003y)\u0001\u0007a'\u0001\u0005gS2$XM\u001d'Q)%\u0019\u0014\u0011GA\u001a\u0003o\tI\u0004C\u0003;+\u0001\u00071\u0007\u0003\u0004\u00026U\u0001\rAN\u0001\u0006_6,w-\u0019\u0005\u0006uV\u0001\rA\u000e\u0005\u0006yV\u0001\r!\u001a\u000b\bg\u0005u\u0012qHA!\u0011\u0015Qd\u00031\u00014\u0011\u0019\t)D\u0006a\u0001m!)!P\u0006a\u0001mQ)1'!\u0012\u0002H!)!h\u0006a\u0001g!1\u0011QG\fA\u0002Y\n\u0001BZ5mi\u0016\u0014\b\n\u0015\u000b\ng\u00055\u0013qJA)\u0003'BQA\u000f\rA\u0002MBa!!\u000e\u0019\u0001\u00041\u0004\"\u0002>\u0019\u0001\u00041\u0004\"\u0002?\u0019\u0001\u0004)GcB\u001a\u0002X\u0005e\u00131\f\u0005\u0006ue\u0001\ra\r\u0005\u0007\u0003kI\u0002\u0019\u0001\u001c\t\u000biL\u0002\u0019\u0001\u001c\u0015\u000bM\ny&!\u0019\t\u000biR\u0002\u0019A\u001a\t\r\u0005U\"\u00041\u00017\u0003\u001dA\u0017-\u0019:Ue\u0012#2aMA4\u0011\u0015Q4\u00041\u00014\u0003!A\u0017-\u0019:UeJ\"E\u0003BA7\u0003_\u00022A\u000b\u001b4\u0011\u0019QD\u00041\u0001\u0002n\u0005y!o\\8u\u001b\u0016\fgnU9vCJ,G\tF\u00027\u0003kBQAO\u000fA\u0002M\nQBZ5mi\u0016\u0014X*\u001a3jC:$E#B\u001a\u0002|\u0005u\u0004\"\u0002\u001e\u001f\u0001\u0004\u0019\u0004\"\u00023\u001f\u0001\u0004)\u0007"
)
public final class JavaCompatible {
   public static double[] filterMedianD(final double[] data, final int windowLength) {
      return JavaCompatible$.MODULE$.filterMedianD(data, windowLength);
   }

   public static double rootMeanSquareD(final double[] data) {
      return JavaCompatible$.MODULE$.rootMeanSquareD(data);
   }

   public static double[][] haarTr2D(final double[][] data) {
      return JavaCompatible$.MODULE$.haarTr2D(data);
   }

   public static double[] haarTrD(final double[] data) {
      return JavaCompatible$.MODULE$.haarTrD(data);
   }

   public static double[] filterHP(final double[] data, final double omega) {
      return JavaCompatible$.MODULE$.filterHP(data, omega);
   }

   public static double[] filterHP(final double[] data, final double omega, final double sampleRate) {
      return JavaCompatible$.MODULE$.filterHP(data, omega, sampleRate);
   }

   public static double[] filterHP(final double[] data, final double omega, final double sampleRate, final int taps) {
      return JavaCompatible$.MODULE$.filterHP(data, omega, sampleRate, taps);
   }

   public static double[] filterLP(final double[] data, final double omega) {
      return JavaCompatible$.MODULE$.filterLP(data, omega);
   }

   public static double[] filterLP(final double[] data, final double omega, final double sampleRate) {
      return JavaCompatible$.MODULE$.filterLP(data, omega, sampleRate);
   }

   public static double[] filterLP(final double[] data, final double omega, final double sampleRate, final int taps) {
      return JavaCompatible$.MODULE$.filterLP(data, omega, sampleRate, taps);
   }

   public static double[] filterBS(final double[] data, final double omegaLow, final double omegaHigh) {
      return JavaCompatible$.MODULE$.filterBS(data, omegaLow, omegaHigh);
   }

   public static double[] filterBS(final double[] data, final double omegaLow, final double omegaHigh, final double sampleRate) {
      return JavaCompatible$.MODULE$.filterBS(data, omegaLow, omegaHigh, sampleRate);
   }

   public static double[] filterBS(final double[] data, final double omegaLow, final double omegaHigh, final double sampleRate, final int taps) {
      return JavaCompatible$.MODULE$.filterBS(data, omegaLow, omegaHigh, sampleRate, taps);
   }

   public static double[] filterBP(final double[] data, final double omegaLow, final double omegaHigh) {
      return JavaCompatible$.MODULE$.filterBP(data, omegaLow, omegaHigh);
   }

   public static double[] filterBP(final double[] data, final double omegaLow, final double omegaHigh, final double sampleRate) {
      return JavaCompatible$.MODULE$.filterBP(data, omegaLow, omegaHigh, sampleRate);
   }

   public static double[] filterBP(final double[] data, final double omegaLow, final double omegaHigh, final double sampleRate, final int taps) {
      return JavaCompatible$.MODULE$.filterBP(data, omegaLow, omegaHigh, sampleRate, taps);
   }

   public static double[] fourierFreqD(final int windowLength, final double fs) {
      return JavaCompatible$.MODULE$.fourierFreqD(windowLength, fs);
   }

   public static double[] fourierFreqD(final int windowLength, final double fs, final boolean shifted) {
      return JavaCompatible$.MODULE$.fourierFreqD(windowLength, fs, shifted);
   }

   public static Complex[] iFourierShiftC(final Complex[] data) {
      return JavaCompatible$.MODULE$.iFourierShiftC(data);
   }

   public static double[] iFourierShiftD(final double[] data) {
      return JavaCompatible$.MODULE$.iFourierShiftD(data);
   }

   public static Complex[] fourierShiftC(final Complex[] data) {
      return JavaCompatible$.MODULE$.fourierShiftC(data);
   }

   public static double[] fourierShiftD(final double[] data) {
      return JavaCompatible$.MODULE$.fourierShiftD(data);
   }

   public static Complex[][] fourierTr2C(final Complex[][] data) {
      return JavaCompatible$.MODULE$.fourierTr2C(data);
   }

   public static Complex[] iFourierTrC(final Complex[] data) {
      return JavaCompatible$.MODULE$.iFourierTrC(data);
   }

   public static Complex[] fourierTrC(final Complex[] data) {
      return JavaCompatible$.MODULE$.fourierTrC(data);
   }

   public static Complex[] fourierTrD(final double[] data) {
      return JavaCompatible$.MODULE$.fourierTrD(data);
   }

   public static double[] correlate(final double[] data, final double[] kernel) {
      return JavaCompatible$.MODULE$.correlate(data, kernel);
   }

   public static double[] convolve(final double[] data, final double[] kernel) {
      return JavaCompatible$.MODULE$.convolve(data, kernel);
   }
}
