package org.apache.derby.impl.sql.execute;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.sql.ResultDescription;

public class AggregatorInfo implements Formatable {
   String aggregateName;
   int inputColumn;
   int outputColumn;
   int aggregatorColumn;
   String aggregatorClassName;
   boolean isDistinct;
   ResultDescription rd;

   public AggregatorInfo() {
   }

   public AggregatorInfo(String var1, String var2, int var3, int var4, int var5, boolean var6, ResultDescription var7) {
      this.aggregateName = var1;
      this.aggregatorClassName = var2;
      this.inputColumn = var3;
      this.outputColumn = var4;
      this.aggregatorColumn = var5;
      this.isDistinct = var6;
      this.rd = var7;
   }

   public String getAggregateName() {
      return this.aggregateName;
   }

   public String getAggregatorClassName() {
      return this.aggregatorClassName;
   }

   public int getAggregatorColNum() {
      return this.aggregatorColumn;
   }

   public int getInputColNum() {
      return this.inputColumn;
   }

   public int getOutputColNum() {
      return this.outputColumn;
   }

   public boolean isDistinct() {
      return this.isDistinct;
   }

   public ResultDescription getResultDescription() {
      return this.rd;
   }

   public String toString() {
      return "";
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeObject(this.aggregateName);
      var1.writeInt(this.inputColumn);
      var1.writeInt(this.outputColumn);
      var1.writeInt(this.aggregatorColumn);
      var1.writeObject(this.aggregatorClassName);
      var1.writeBoolean(this.isDistinct);
      var1.writeObject(this.rd);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.aggregateName = (String)var1.readObject();
      this.inputColumn = var1.readInt();
      this.outputColumn = var1.readInt();
      this.aggregatorColumn = var1.readInt();
      this.aggregatorClassName = (String)var1.readObject();
      this.isDistinct = var1.readBoolean();
      this.rd = (ResultDescription)var1.readObject();
   }

   public int getTypeFormatId() {
      return 223;
   }
}
