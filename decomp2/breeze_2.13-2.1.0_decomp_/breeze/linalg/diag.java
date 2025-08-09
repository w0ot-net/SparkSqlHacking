package breeze.linalg;

import breeze.generic.UFunc;
import breeze.math.Semiring;
import breeze.storage.Zero;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i<Qa\u0002\u0005\t\u000251Qa\u0004\u0005\t\u0002AAQ\u0001I\u0001\u0005\u0002\u0005BQAI\u0001\u0005\u0004\rBQAS\u0001\u0005\u0004-CQAU\u0001\u0005\u0004MCQ\u0001Z\u0001\u0005\u0004\u0015\fA\u0001Z5bO*\u0011\u0011BC\u0001\u0007Y&t\u0017\r\\4\u000b\u0003-\taA\u0019:fKj,7\u0001\u0001\t\u0003\u001d\u0005i\u0011\u0001\u0003\u0002\u0005I&\fwm\u0005\u0003\u0002#]i\u0002C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"AB!osJ+g\r\u0005\u0002\u001975\t\u0011D\u0003\u0002\u001b\u0015\u00059q-\u001a8fe&\u001c\u0017B\u0001\u000f\u001a\u0005\u0015)f)\u001e8d!\tqa$\u0003\u0002 \u0011\taA-[1h\u0019><\bK]5pe\u00051A(\u001b8jiz\"\u0012!D\u0001\rI&\fw\r\u0012,E\u001b&k\u0007\u000f\\\u000b\u0003I9\"2!\n\u001eC!\u00111s%K\u001c\u000f\u00059\u0001\u0011B\u0001\u0015\u001c\u0005\u0011IU\u000e\u001d7\u0011\u00079QC&\u0003\u0002,\u0011\tYA)\u001a8tKZ+7\r^8s!\tic\u0006\u0004\u0001\u0005\u000b=\u001a!\u0019\u0001\u0019\u0003\u0003Y\u000b\"!\r\u001b\u0011\u0005I\u0011\u0014BA\u001a\u0014\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"AE\u001b\n\u0005Y\u001a\"aA!osB\u0019a\u0002\u000f\u0017\n\u0005eB!a\u0003#f]N,W*\u0019;sSbDqaO\u0002\u0002\u0002\u0003\u000fA(\u0001\u0006fm&$WM\\2fIE\u00022!\u0010!-\u001b\u0005q$BA \u0014\u0003\u001d\u0011XM\u001a7fGRL!!\u0011 \u0003\u0011\rc\u0017m]:UC\u001eDqaQ\u0002\u0002\u0002\u0003\u000fA)\u0001\u0006fm&$WM\\2fII\u00022!\u0012%-\u001b\u00051%BA$\u000b\u0003\u001d\u0019Ho\u001c:bO\u0016L!!\u0013$\u0003\ti+'o\\\u0001\rI&\fw\rR'E-&k\u0007\u000f\\\u000b\u0003\u0019B+\u0012!\u0014\t\u0005M\u001dr\u0015\u000bE\u0002\u000fq=\u0003\"!\f)\u0005\u000b=\"!\u0019\u0001\u0019\u0011\u00079Qs*A\u0007eS\u0006<7iU\"T-&k\u0007\u000f\\\u000b\u0003)j#2!\u00160b!\u00111sEV.\u0011\u000799\u0016,\u0003\u0002Y\u0011\tI1iU\"NCR\u0014\u0018\u000e\u001f\t\u0003[i#QaL\u0003C\u0002A\u00022A\u0004/Z\u0013\ti\u0006B\u0001\u0007Ta\u0006\u00148/\u001a,fGR|'\u000fC\u0004`\u000b\u0005\u0005\t9\u00011\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$3\u0007E\u0002>\u0001fCqAY\u0003\u0002\u0002\u0003\u000f1-\u0001\u0006fm&$WM\\2fIQ\u00022!\u0012%Z\u00035!\u0017.Y4T-\u000e\u001b6)S7qYV\u0011aM\u001b\u000b\u0005O2|w\u000f\u0005\u0003'O!\\\u0007c\u0001\b]SB\u0011QF\u001b\u0003\u0006_\u0019\u0011\r\u0001\r\t\u0004\u001d]K\u0007bB7\u0007\u0003\u0003\u0005\u001dA\\\u0001\u000bKZLG-\u001a8dK\u0012*\u0004cA\u001fAS\"9\u0001OBA\u0001\u0002\b\t\u0018AC3wS\u0012,gnY3%mA\u0019!/^5\u000e\u0003MT!\u0001\u001e\u0006\u0002\t5\fG\u000f[\u0005\u0003mN\u0014\u0001bU3nSJLgn\u001a\u0005\bq\u001a\t\t\u0011q\u0001z\u0003))g/\u001b3f]\u000e,Ge\u000e\t\u0004\u000b\"K\u0007"
)
public final class diag {
   public static UFunc.UImpl diagSVCSCImpl(final ClassTag evidence$5, final Semiring evidence$6, final Zero evidence$7) {
      return diag$.MODULE$.diagSVCSCImpl(evidence$5, evidence$6, evidence$7);
   }

   public static UFunc.UImpl diagCSCSVImpl(final ClassTag evidence$3, final Zero evidence$4) {
      return diag$.MODULE$.diagCSCSVImpl(evidence$3, evidence$4);
   }

   public static UFunc.UImpl diagDMDVImpl() {
      return diag$.MODULE$.diagDMDVImpl();
   }

   public static UFunc.UImpl diagDVDMImpl(final ClassTag evidence$1, final Zero evidence$2) {
      return diag$.MODULE$.diagDVDMImpl(evidence$1, evidence$2);
   }

   public static Object withSink(final Object s) {
      return diag$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return diag$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return diag$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return diag$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return diag$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return diag$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return diag$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return diag$.MODULE$.apply(v, impl);
   }
}
