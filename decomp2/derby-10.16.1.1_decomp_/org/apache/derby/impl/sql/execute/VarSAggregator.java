package org.apache.derby.impl.sql.execute;

public class VarSAggregator extends VarPAggregator {
   private static final long serialVersionUID = -741087542836440595L;

   protected Double computeVar() {
      return this.count <= 1 ? null : (this.sums.x2 - Math.pow(this.sums.x, (double)2.0F) / (double)this.count) / (double)(this.count - 1);
   }
}
