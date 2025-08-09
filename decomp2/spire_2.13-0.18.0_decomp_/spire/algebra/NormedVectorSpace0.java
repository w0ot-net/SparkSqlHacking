package spire.algebra;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E3\u0001b\u0001\u0003\u0011\u0002\u0007\u0005A\u0001\u0003\u0005\u0006\u001f\u0001!\t!\u0005\u0005\u0006+\u0001!\u0019A\u0006\u0002\u0013\u001d>\u0014X.\u001a3WK\u000e$xN]*qC\u000e,\u0007G\u0003\u0002\u0006\r\u00059\u0011\r\\4fEJ\f'\"A\u0004\u0002\u000bM\u0004\u0018N]3\u0014\u0005\u0001I\u0001C\u0001\u0006\u000e\u001b\u0005Y!\"\u0001\u0007\u0002\u000bM\u001c\u0017\r\\1\n\u00059Y!AB!osJ+g-\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005\u0011\u0002C\u0001\u0006\u0014\u0013\t!2B\u0001\u0003V]&$\u0018\u0001J%o]\u0016\u0014\bK]8ek\u000e$8\u000b]1dK&\u001bhj\u001c:nK\u00124Vm\u0019;peN\u0003\u0018mY3\u0016\u0007]q\u0002\u0006F\u0002\u0019\u000f2\u0003B!\u0007\u000e\u001dO5\tA!\u0003\u0002\u001c\t\t\tbj\u001c:nK\u00124Vm\u0019;peN\u0003\u0018mY3\u0011\u0005uqB\u0002\u0001\u0003\u0006?\t\u0011\r\u0001\t\u0002\u0002-F\u0011\u0011\u0005\n\t\u0003\u0015\tJ!aI\u0006\u0003\u000f9{G\u000f[5oOB\u0011!\"J\u0005\u0003M-\u00111!\u00118z!\ti\u0002\u0006B\u0005*\u0005\u0001\u0006\t\u0011!b\u0001A\t\ta\t\u000b\u0004)W9BTH\u0011\t\u0003\u00151J!!L\u0006\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G=\u0002$'\r\b\u0003\u0015AJ!!M\u0006\u0002\u0007%sG/\r\u0003%g]baB\u0001\u001b8\u001b\u0005)$B\u0001\u001c\u0011\u0003\u0019a$o\\8u}%\tA\"M\u0003$sib4H\u0004\u0002\u000bu%\u00111hC\u0001\u0005\u0019>tw-\r\u0003%g]b\u0011'B\u0012?\u007f\u0005\u0003eB\u0001\u0006@\u0013\t\u00015\"A\u0003GY>\fG/\r\u0003%g]b\u0011'B\u0012D\t\u001a+eB\u0001\u0006E\u0013\t)5\"\u0001\u0004E_V\u0014G.Z\u0019\u0005IM:D\u0002C\u0003I\u0005\u0001\u000f\u0011*A\u0003ta\u0006\u001cW\r\u0005\u0003\u001a\u0015r9\u0013BA&\u0005\u0005EIeN\\3s!J|G-^2u'B\f7-\u001a\u0005\u0006\u001b\n\u0001\u001dAT\u0001\u0006]J|w\u000e\u001e\t\u00043=;\u0013B\u0001)\u0005\u0005\u0015q%k\\8u\u0001"
)
public interface NormedVectorSpace0 {
   // $FF: synthetic method
   static NormedVectorSpace InnerProductSpaceIsNormedVectorSpace$(final NormedVectorSpace0 $this, final InnerProductSpace space, final NRoot nroot) {
      return $this.InnerProductSpaceIsNormedVectorSpace(space, nroot);
   }

   default NormedVectorSpace InnerProductSpaceIsNormedVectorSpace(final InnerProductSpace space, final NRoot nroot) {
      return space.normed(nroot);
   }

   // $FF: synthetic method
   static NormedVectorSpace InnerProductSpaceIsNormedVectorSpace$mDc$sp$(final NormedVectorSpace0 $this, final InnerProductSpace space, final NRoot nroot) {
      return $this.InnerProductSpaceIsNormedVectorSpace$mDc$sp(space, nroot);
   }

   default NormedVectorSpace InnerProductSpaceIsNormedVectorSpace$mDc$sp(final InnerProductSpace space, final NRoot nroot) {
      return space.normed$mcD$sp(nroot);
   }

   // $FF: synthetic method
   static NormedVectorSpace InnerProductSpaceIsNormedVectorSpace$mFc$sp$(final NormedVectorSpace0 $this, final InnerProductSpace space, final NRoot nroot) {
      return $this.InnerProductSpaceIsNormedVectorSpace$mFc$sp(space, nroot);
   }

   default NormedVectorSpace InnerProductSpaceIsNormedVectorSpace$mFc$sp(final InnerProductSpace space, final NRoot nroot) {
      return space.normed$mcF$sp(nroot);
   }

   // $FF: synthetic method
   static NormedVectorSpace InnerProductSpaceIsNormedVectorSpace$mIc$sp$(final NormedVectorSpace0 $this, final InnerProductSpace space, final NRoot nroot) {
      return $this.InnerProductSpaceIsNormedVectorSpace$mIc$sp(space, nroot);
   }

   default NormedVectorSpace InnerProductSpaceIsNormedVectorSpace$mIc$sp(final InnerProductSpace space, final NRoot nroot) {
      return space.normed$mcI$sp(nroot);
   }

   // $FF: synthetic method
   static NormedVectorSpace InnerProductSpaceIsNormedVectorSpace$mJc$sp$(final NormedVectorSpace0 $this, final InnerProductSpace space, final NRoot nroot) {
      return $this.InnerProductSpaceIsNormedVectorSpace$mJc$sp(space, nroot);
   }

   default NormedVectorSpace InnerProductSpaceIsNormedVectorSpace$mJc$sp(final InnerProductSpace space, final NRoot nroot) {
      return space.normed$mcJ$sp(nroot);
   }

   static void $init$(final NormedVectorSpace0 $this) {
   }
}
