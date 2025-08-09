package breeze.stats.distributions;

import breeze.math.EnumeratedCoordinateField;

public class Dirichlet$mcI$sp extends Dirichlet {
   private final EnumeratedCoordinateField space;
   private final RandBasis rand;

   public Dirichlet$mcI$sp(final Object params, final EnumeratedCoordinateField space, final RandBasis rand) {
      super(params, space, rand);
      this.space = space;
      this.rand = rand;
   }
}
