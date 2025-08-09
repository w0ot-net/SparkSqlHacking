package org.apache.derby.iapi.types;

import java.sql.Date;
import org.apache.derby.shared.common.error.StandardException;

public interface DateTimeDataValue extends DataValueDescriptor {
   int YEAR_FIELD = 0;
   int MONTH_FIELD = 1;
   int DAY_FIELD = 2;
   int HOUR_FIELD = 3;
   int MINUTE_FIELD = 4;
   int SECOND_FIELD = 5;
   int FRAC_SECOND_INTERVAL = 0;
   int SECOND_INTERVAL = 1;
   int MINUTE_INTERVAL = 2;
   int HOUR_INTERVAL = 3;
   int DAY_INTERVAL = 4;
   int WEEK_INTERVAL = 5;
   int MONTH_INTERVAL = 6;
   int QUARTER_INTERVAL = 7;
   int YEAR_INTERVAL = 8;

   NumberDataValue getYear(NumberDataValue var1) throws StandardException;

   NumberDataValue getMonth(NumberDataValue var1) throws StandardException;

   NumberDataValue getDate(NumberDataValue var1) throws StandardException;

   NumberDataValue getHours(NumberDataValue var1) throws StandardException;

   NumberDataValue getMinutes(NumberDataValue var1) throws StandardException;

   NumberDataValue getSeconds(NumberDataValue var1) throws StandardException;

   DateTimeDataValue timestampAdd(int var1, NumberDataValue var2, Date var3, DateTimeDataValue var4) throws StandardException;

   NumberDataValue timestampDiff(int var1, DateTimeDataValue var2, Date var3, NumberDataValue var4) throws StandardException;
}
