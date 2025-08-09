package org.datanucleus.query.inmemory;

import java.util.Date;

public class DateAggregateExpression extends AggregateExpression {
   Date value;

   public DateAggregateExpression(Date value) {
      this.value = value;
   }

   public Object add(Object obj) {
      if (obj instanceof Date) {
         long currentVal = this.value.getTime();
         long inputVal = ((Date)obj).getTime();
         return new Date(currentVal + inputVal);
      } else {
         return super.add(obj);
      }
   }

   public Object sub(Object obj) {
      if (obj instanceof Date) {
         long currentVal = this.value.getTime();
         long inputVal = ((Date)obj).getTime();
         return new Date(currentVal - inputVal);
      } else {
         return super.sub(obj);
      }
   }

   public Boolean gt(Object obj) {
      if (obj instanceof Date) {
         long currentVal = this.value.getTime();
         long inputVal = ((Date)obj).getTime();
         return currentVal > inputVal ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.gt(obj);
      }
   }

   public Boolean lt(Object obj) {
      if (obj instanceof Date) {
         long currentVal = this.value.getTime();
         long inputVal = ((Date)obj).getTime();
         return currentVal < inputVal ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.lt(obj);
      }
   }

   public Boolean eq(Object obj) {
      if (obj instanceof Date) {
         long currentVal = this.value.getTime();
         long inputVal = ((Date)obj).getTime();
         return currentVal == inputVal ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.eq(obj);
      }
   }
}
