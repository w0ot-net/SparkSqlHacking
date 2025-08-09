package scala.collection.concurrent;

import scala.math.Equiv;

public final class CNode$ {
   public static final CNode$ MODULE$ = new CNode$();

   public MainNode dual(final SNode x, final int xhc, final SNode y, final int yhc, final int lev, final Gen gen, final Equiv equiv) {
      if (lev < 35) {
         int xidx = xhc >>> lev & 31;
         int yidx = yhc >>> lev & 31;
         int bmp = 1 << xidx | 1 << yidx;
         if (xidx == yidx) {
            INode subinode = new INode(gen, equiv);
            subinode.mainnode = this.dual(x, xhc, y, yhc, lev + 5, gen, equiv);
            return new CNode(bmp, new BasicNode[]{subinode}, gen);
         } else {
            return xidx < yidx ? new CNode(bmp, new BasicNode[]{x, y}, gen) : new CNode(bmp, new BasicNode[]{y, x}, gen);
         }
      } else {
         return new LNode(x.k(), x.v(), y.k(), y.v(), equiv);
      }
   }

   private CNode$() {
   }
}
