package breeze.math;

import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanZipMapValues;

public final class MutablizingAdaptor$ {
   public static final MutablizingAdaptor$ MODULE$ = new MutablizingAdaptor$();

   public MutablizingAdaptor ensureMutable(final VectorSpace vs) {
      return (MutablizingAdaptor)(vs instanceof MutableVectorSpace ? new MutablizingAdaptor.IdentityWrapper((MutableVectorSpace)vs) : new MutablizingAdaptor.VectorSpaceAdaptor(vs));
   }

   public MutablizingAdaptor ensureMutable(final InnerProductVectorSpace vs) {
      return (MutablizingAdaptor)(vs instanceof MutableInnerProductVectorSpace ? new MutablizingAdaptor.IdentityWrapper((MutableInnerProductVectorSpace)vs) : new MutablizingAdaptor.InnerProductSpaceAdaptor(vs));
   }

   public MutablizingAdaptor ensureMutable(final VectorField vs, final CanTraverseValues canIterate, final CanMapValues canMap, final CanZipMapValues canZipMap) {
      return (MutablizingAdaptor)(vs instanceof MutableVectorField ? new MutablizingAdaptor.IdentityWrapper((MutableVectorField)vs) : new MutablizingAdaptor.VectorFieldAdaptor(vs, canIterate, canMap, canZipMap));
   }

   public MutablizingAdaptor ensureMutable(final VectorRing vs, final CanTraverseValues canIterate, final CanMapValues canMap, final CanZipMapValues canZipMap) {
      return (MutablizingAdaptor)(vs instanceof MutableVectorRing ? new MutablizingAdaptor.IdentityWrapper((MutableVectorRing)vs) : new MutablizingAdaptor.VectorRingAdaptor(vs, canIterate, canMap, canZipMap));
   }

   public MutablizingAdaptor ensureMutable(final CoordinateField vs, final CanTraverseValues canIterate, final CanMapValues canMap, final CanZipMapValues canZipMap) {
      return (MutablizingAdaptor)(vs instanceof MutableCoordinateField ? new MutablizingAdaptor.IdentityWrapper((MutableCoordinateField)vs) : new MutablizingAdaptor.CoordinateFieldAdaptor(vs, canIterate, canMap, canZipMap));
   }

   private MutablizingAdaptor$() {
   }
}
