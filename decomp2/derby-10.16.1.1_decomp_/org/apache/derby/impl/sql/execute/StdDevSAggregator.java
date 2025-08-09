package org.apache.derby.impl.sql.execute;

public class StdDevSAggregator extends VarSAggregator {
   private static final long serialVersionUID = -265838381117407283L;

   public Double terminate() {
      Double var1 = this.computeVar();
      return var1 == null ? null : Math.sqrt(var1);
   }
}
