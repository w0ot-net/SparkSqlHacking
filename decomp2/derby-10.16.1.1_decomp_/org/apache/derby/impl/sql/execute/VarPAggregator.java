package org.apache.derby.impl.sql.execute;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.agg.Aggregator;

public class VarPAggregator implements Aggregator, Externalizable {
   private static final long serialVersionUID = 239794626052067761L;
   protected Sums sums;
   protected int count;

   public void init() {
      this.sums = new Sums();
      this.count = 0;
   }

   public void accumulate(Number var1) {
      double var2 = var1.doubleValue();
      Sums var10000 = this.sums;
      var10000.x += var2;
      var10000 = this.sums;
      var10000.x2 += Math.pow(var2, (double)2.0F);
      ++this.count;
   }

   public void merge(VarPAggregator var1) {
      Sums var10000 = this.sums;
      var10000.x += var1.sums.x;
      var10000 = this.sums;
      var10000.x2 += var1.sums.x2;
      this.count += var1.count;
   }

   protected Double computeVar() {
      return this.count == 0 ? null : this.sums.x2 / (double)this.count - Math.pow(this.sums.x / (double)this.count, (double)2.0F);
   }

   public Double terminate() {
      return this.computeVar();
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.count = var1.readInt();
      this.sums = (Sums)var1.readObject();
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeInt(this.count);
      var1.writeObject(this.sums);
   }

   public static class Sums {
      double x = (double)0.0F;
      double x2 = (double)0.0F;
   }
}
