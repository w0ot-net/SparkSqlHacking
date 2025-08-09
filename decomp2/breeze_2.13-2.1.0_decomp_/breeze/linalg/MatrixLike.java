package breeze.linalg;

import breeze.linalg.support.CanMapValues;
import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M4qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003N\u0001\u0011\u0005a\nC\u0003S\u0001\u0011\u00051K\u0001\u0006NCR\u0014\u0018\u000e\u001f'jW\u0016T!!\u0002\u0004\u0002\r1Lg.\u00197h\u0015\u00059\u0011A\u00022sK\u0016TXm\u0001\u0001\u0016\u0007)iri\u0005\u0003\u0001\u0017E\u0019\u0005C\u0001\u0007\u0010\u001b\u0005i!\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Ai!AB!osJ+g\r\u0005\u0003\u0013'UYR\"\u0001\u0003\n\u0005Q!!A\u0002+f]N|'\u000f\u0005\u0003\r-aA\u0012BA\f\u000e\u0005\u0019!V\u000f\u001d7feA\u0011A\"G\u0005\u000355\u00111!\u00138u!\taR\u0004\u0004\u0001\u0005\u0013y\u0001\u0001\u0015!A\u0001\u0006\u0004y\"!\u0001,\u0012\u0005\u0001\u001a\u0003C\u0001\u0007\"\u0013\t\u0011SBA\u0004O_RD\u0017N\\4\u0011\u00051!\u0013BA\u0013\u000e\u0005\r\te.\u001f\u0015\u0007;\u001dRC'\u000f \u0011\u00051A\u0013BA\u0015\u000e\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\rZCFL\u0017\u000f\u00051a\u0013BA\u0017\u000e\u0003\u0019!u.\u001e2mKF\"AeL\u001a\u000f\u001d\t\u00014'D\u00012\u0015\t\u0011\u0004\"\u0001\u0004=e>|GOP\u0005\u0002\u001dE*1%\u000e\u001c9o9\u0011ABN\u0005\u0003o5\t1!\u00138uc\u0011!sf\r\b2\u000b\rR4(\u0010\u001f\u000f\u00051Y\u0014B\u0001\u001f\u000e\u0003\u00151En\\1uc\u0011!sf\r\b2\u000b\rz\u0004IQ!\u000f\u00051\u0001\u0015BA!\u000e\u0003\u0011auN\\42\t\u0011z3G\u0004\t\u0006%\u0011+2DR\u0005\u0003\u000b\u0012\u0011!\u0002V3og>\u0014H*[6f!\tar\t\u0002\u0004I\u0001\u0011\u0015\r!\u0013\u0002\u0005'\u0016dg-\u0005\u0002!\u0015B\u0019!cS\u000e\n\u00051#!AB'biJL\u00070\u0001\u0004%S:LG\u000f\n\u000b\u0002\u001fB\u0011A\u0002U\u0005\u0003#6\u0011A!\u00168ji\u0006\u0019Q.\u00199\u0016\u0007Qcw\u000b\u0006\u0002V]R\u0011a+\u0017\t\u00039]#Q\u0001\u0017\u0002C\u0002}\u0011A\u0001\u00165bi\")!L\u0001a\u00027\u0006a1-\u00198NCB4\u0016\r\\;fgB1AlX1\u001cWZk\u0011!\u0018\u0006\u0003=\u0012\tqa];qa>\u0014H/\u0003\u0002a;\na1)\u00198NCB4\u0016\r\\;fg*\u0012aIY\u0016\u0002GB\u0011A-[\u0007\u0002K*\u0011amZ\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001[\u0007\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002kK\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0011\u0005qaG!B7\u0003\u0005\u0004y\"A\u0001,3\u0011\u0015y'\u00011\u0001q\u0003\t1g\u000e\u0005\u0003\rcnY\u0017B\u0001:\u000e\u0005%1UO\\2uS>t\u0017\u0007"
)
public interface MatrixLike extends Tensor {
   // $FF: synthetic method
   static Object map$(final MatrixLike $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map(fn, canMapValues);
   }

   default Object map(final Function1 fn, final CanMapValues canMapValues) {
      return this.values().map(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcD$sp$(final MatrixLike $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcD$sp(fn, canMapValues);
   }

   default Object map$mcD$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.map(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcF$sp$(final MatrixLike $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcF$sp(fn, canMapValues);
   }

   default Object map$mcF$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.map(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcI$sp$(final MatrixLike $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcI$sp(fn, canMapValues);
   }

   default Object map$mcI$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.map(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcJ$sp$(final MatrixLike $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcJ$sp(fn, canMapValues);
   }

   default Object map$mcJ$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.map(fn, canMapValues);
   }

   static void $init$(final MatrixLike $this) {
   }
}
