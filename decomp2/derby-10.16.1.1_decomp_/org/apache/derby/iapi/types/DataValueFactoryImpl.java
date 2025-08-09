package org.apache.derby.iapi.types;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.Locale;
import java.util.Properties;
import org.apache.derby.iapi.db.DatabaseContext;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.impl.store.access.heap.HeapRowLocation;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.LocaleFinder;

public final class DataValueFactoryImpl implements DataValueFactory, ModuleControl {
   private LocaleFinder localeFinder;
   private Locale databaseLocale;
   private RuleBasedCollator collatorForCharacterTypes;

   public void boot(boolean var1, Properties var2) throws StandardException {
      ModuleFactory var3 = getMonitor();
      this.databaseLocale = var3.getLocale(this);
      if (var1) {
         String var4 = var2.getProperty("collation");
         if (var4 != null) {
            int var5 = DataTypeDescriptor.getCollationType(var4);
            if (var5 != 0) {
               if (var5 < 1 || var5 >= 5) {
                  throw StandardException.newException("XBM03.D", new Object[]{var4});
               }

               int var6 = var5 - 2;
               this.collatorForCharacterTypes = this.verifyCollatorSupport(var6);
            }
         }
      }

   }

   public void stop() {
   }

   public NumberDataValue getDataValue(int var1, NumberDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLInteger(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public NumberDataValue getDataValue(Integer var1, NumberDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLInteger(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public NumberDataValue getDataValue(char var1, NumberDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLInteger(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public NumberDataValue getDataValue(short var1, NumberDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLSmallint(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public NumberDataValue getDataValue(Short var1, NumberDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLSmallint(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public NumberDataValue getDataValue(byte var1, NumberDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLTinyint(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public NumberDataValue getDataValue(Byte var1, NumberDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLTinyint(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public NumberDataValue getDataValue(long var1, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         return new SQLLongint(var1);
      } else {
         var3.setValue(var1);
         return var3;
      }
   }

   public NumberDataValue getDataValue(Long var1, NumberDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLLongint(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public NumberDataValue getDataValue(float var1, NumberDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLReal(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public NumberDataValue getDataValue(Float var1, NumberDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLReal(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public NumberDataValue getDataValue(double var1, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         return new SQLDouble(var1);
      } else {
         var3.setValue(var1);
         return var3;
      }
   }

   public NumberDataValue getDataValue(Double var1, NumberDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLDouble(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public final NumberDataValue getDecimalDataValue(Number var1, NumberDataValue var2) throws StandardException {
      NumberDataValue var3 = var2 == null ? this.getNullDecimal((NumberDataValue)null) : var2;
      var3.setValue(var1);
      return var3;
   }

   public BooleanDataValue getDataValue(boolean var1, BooleanDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLBoolean(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public BooleanDataValue getDataValue(Boolean var1, BooleanDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLBoolean(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public BitDataValue getBitDataValue(byte[] var1) throws StandardException {
      return new SQLBit(var1);
   }

   public BitDataValue getBitDataValue(byte[] var1, BitDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLBit(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public BitDataValue getVarbitDataValue(byte[] var1, BitDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLVarbit(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public BitDataValue getLongVarbitDataValue(byte[] var1, BitDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLLongVarbit(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public BitDataValue getBlobDataValue(byte[] var1, BitDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLBlob(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public BitDataValue getBlobDataValue(Blob var1, BitDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLBlob(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public StringDataValue getCharDataValue(String var1) {
      return new SQLChar(var1);
   }

   public StringDataValue getCharDataValue(String var1, StringDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLChar(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public StringDataValue getCharDataValue(String var1, StringDataValue var2, int var3) throws StandardException {
      if (var3 == 0) {
         return this.getCharDataValue(var1, var2);
      } else if (var2 == null) {
         return new CollatorSQLChar(var1, this.getCharacterCollator(var3));
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public StringDataValue getVarcharDataValue(String var1) {
      return new SQLVarchar(var1);
   }

   public StringDataValue getVarcharDataValue(String var1, StringDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLVarchar(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public StringDataValue getVarcharDataValue(String var1, StringDataValue var2, int var3) throws StandardException {
      if (var3 == 0) {
         return this.getVarcharDataValue(var1, var2);
      } else if (var2 == null) {
         return new CollatorSQLVarchar(var1, this.getCharacterCollator(var3));
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public StringDataValue getLongvarcharDataValue(String var1) {
      return new SQLLongvarchar(var1);
   }

   public StringDataValue getLongvarcharDataValue(String var1, StringDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLLongvarchar(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public StringDataValue getLongvarcharDataValue(String var1, StringDataValue var2, int var3) throws StandardException {
      if (var3 == 0) {
         return this.getLongvarcharDataValue(var1, var2);
      } else if (var2 == null) {
         return new CollatorSQLLongvarchar(var1, this.getCharacterCollator(var3));
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public StringDataValue getClobDataValue(String var1, StringDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLClob(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public StringDataValue getClobDataValue(Clob var1, StringDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLClob(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public StringDataValue getClobDataValue(Clob var1, StringDataValue var2, int var3) throws StandardException {
      if (var3 == 0) {
         return this.getClobDataValue(var1, var2);
      } else if (var2 == null) {
         return new CollatorSQLClob(var1, this.getCharacterCollator(var3));
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public StringDataValue getClobDataValue(String var1, StringDataValue var2, int var3) throws StandardException {
      if (var3 == 0) {
         return this.getClobDataValue(var1, var2);
      } else if (var2 == null) {
         return new CollatorSQLClob(var1, this.getCharacterCollator(var3));
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public DateTimeDataValue getDataValue(Date var1, DateTimeDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLDate(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public DateTimeDataValue getDataValue(Time var1, DateTimeDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLTime(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public DateTimeDataValue getDataValue(Timestamp var1, DateTimeDataValue var2) throws StandardException {
      if (var2 == null) {
         return new SQLTimestamp(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public DateTimeDataValue getDate(DataValueDescriptor var1) throws StandardException {
      return SQLDate.computeDateFunction(var1, this);
   }

   public DateTimeDataValue getTimestamp(DataValueDescriptor var1) throws StandardException {
      return SQLTimestamp.computeTimestampFunction(var1, this);
   }

   public DateTimeDataValue getTimestamp(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return new SQLTimestamp(var1, var2);
   }

   public UserDataValue getDataValue(Object var1, UserDataValue var2) {
      if (var2 == null) {
         return new UserType(var1);
      } else {
         ((UserType)var2).setValue(var1);
         return var2;
      }
   }

   public RefDataValue getDataValue(RowLocation var1, RefDataValue var2) {
      if (var2 == null) {
         return new SQLRef(var1);
      } else {
         var2.setValue(var1);
         return var2;
      }
   }

   public NumberDataValue getNullInteger(NumberDataValue var1) {
      if (var1 == null) {
         return new SQLInteger();
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public NumberDataValue getNullShort(NumberDataValue var1) {
      if (var1 == null) {
         return new SQLSmallint();
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public NumberDataValue getNullLong(NumberDataValue var1) {
      if (var1 == null) {
         return new SQLLongint();
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public NumberDataValue getNullByte(NumberDataValue var1) {
      if (var1 == null) {
         return new SQLTinyint();
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public NumberDataValue getNullFloat(NumberDataValue var1) {
      if (var1 == null) {
         return new SQLReal();
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public NumberDataValue getNullDouble(NumberDataValue var1) {
      if (var1 == null) {
         return new SQLDouble();
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public final NumberDataValue getNullDecimal(NumberDataValue var1) {
      if (var1 == null) {
         return new SQLDecimal();
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public BooleanDataValue getNullBoolean(BooleanDataValue var1) {
      if (var1 == null) {
         return new SQLBoolean();
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public BitDataValue getNullBit(BitDataValue var1) throws StandardException {
      if (var1 == null) {
         return this.getBitDataValue((byte[])null);
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public BitDataValue getNullVarbit(BitDataValue var1) throws StandardException {
      if (var1 == null) {
         return new SQLVarbit();
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public BitDataValue getNullLongVarbit(BitDataValue var1) throws StandardException {
      if (var1 == null) {
         return new SQLLongVarbit();
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public BitDataValue getNullBlob(BitDataValue var1) throws StandardException {
      if (var1 == null) {
         return new SQLBlob();
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public StringDataValue getNullChar(StringDataValue var1) {
      if (var1 == null) {
         return this.getCharDataValue((String)null);
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public StringDataValue getNullChar(StringDataValue var1, int var2) throws StandardException {
      if (var2 == 0) {
         return this.getNullChar(var1);
      } else if (var1 == null) {
         return new CollatorSQLChar(this.getCharacterCollator(var2));
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public StringDataValue getNullVarchar(StringDataValue var1) {
      if (var1 == null) {
         return this.getVarcharDataValue((String)null);
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public StringDataValue getNullVarchar(StringDataValue var1, int var2) throws StandardException {
      if (var2 == 0) {
         return this.getNullChar(var1);
      } else if (var1 == null) {
         return new CollatorSQLVarchar(this.getCharacterCollator(var2));
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public StringDataValue getNullLongvarchar(StringDataValue var1) {
      if (var1 == null) {
         return this.getLongvarcharDataValue((String)null);
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public StringDataValue getNullLongvarchar(StringDataValue var1, int var2) throws StandardException {
      if (var2 == 0) {
         return this.getNullChar(var1);
      } else if (var1 == null) {
         return new CollatorSQLLongvarchar(this.getCharacterCollator(var2));
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public StringDataValue getNullClob(StringDataValue var1) {
      if (var1 == null) {
         return new SQLClob();
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public StringDataValue getNullClob(StringDataValue var1, int var2) throws StandardException {
      if (var2 == 0) {
         return this.getNullChar(var1);
      } else if (var1 == null) {
         return new CollatorSQLClob(this.getCharacterCollator(var2));
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public UserDataValue getNullObject(UserDataValue var1) {
      if (var1 == null) {
         return new UserType((Object)null);
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public RefDataValue getNullRef(RefDataValue var1) {
      if (var1 == null) {
         return new SQLRef();
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public DateTimeDataValue getNullDate(DateTimeDataValue var1) {
      if (var1 == null) {
         try {
            return new SQLDate((Date)null);
         } catch (StandardException var3) {
            return null;
         }
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public DateTimeDataValue getNullTime(DateTimeDataValue var1) {
      if (var1 == null) {
         try {
            return new SQLTime((Time)null);
         } catch (StandardException var3) {
            return null;
         }
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public DateTimeDataValue getNullTimestamp(DateTimeDataValue var1) {
      if (var1 == null) {
         try {
            return new SQLTimestamp((Timestamp)null);
         } catch (StandardException var3) {
            return null;
         }
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public DateTimeDataValue getDateValue(String var1, boolean var2) throws StandardException {
      return new SQLDate(var1, var2, this.getLocaleFinder());
   }

   public DateTimeDataValue getTimeValue(String var1, boolean var2) throws StandardException {
      return new SQLTime(var1, var2, this.getLocaleFinder());
   }

   public DateTimeDataValue getTimestampValue(String var1, boolean var2) throws StandardException {
      return new SQLTimestamp(var1, var2, this.getLocaleFinder());
   }

   public XMLDataValue getXMLDataValue(XMLDataValue var1) throws StandardException {
      return this.getNullXML(var1);
   }

   public XMLDataValue getNullXML(XMLDataValue var1) {
      if (var1 == null) {
         return new XML();
      } else {
         var1.setToNull();
         return var1;
      }
   }

   public RuleBasedCollator getCharacterCollator(int var1) throws StandardException {
      if (var1 == 0) {
         return (RuleBasedCollator)null;
      } else if (this.collatorForCharacterTypes == null) {
         int var2 = var1 - 2;
         this.collatorForCharacterTypes = this.verifyCollatorSupport(var2);
         return this.collatorForCharacterTypes;
      } else {
         return this.collatorForCharacterTypes;
      }
   }

   private RuleBasedCollator verifyCollatorSupport(int var1) throws StandardException {
      Locale[] var2 = Collator.getAvailableLocales();
      boolean var3 = false;

      for(int var4 = 0; var4 < var2.length; ++var4) {
         if (var2[var4].equals(this.databaseLocale)) {
            var3 = true;
            break;
         }
      }

      if (!var3) {
         throw StandardException.newException("XBM04.D", new Object[]{this.databaseLocale.toString()});
      } else {
         RuleBasedCollator var5 = (RuleBasedCollator)Collator.getInstance(this.databaseLocale);
         if (var1 != -1) {
            var5.setStrength(var1);
         }

         return var5;
      }
   }

   public DataValueDescriptor getNull(int var1, int var2) throws StandardException {
      DataValueDescriptor var3 = getNullDVDWithUCS_BASICcollation(var1);
      if (var2 == 0) {
         return var3;
      } else {
         return (DataValueDescriptor)(var3 instanceof StringDataValue ? ((StringDataValue)var3).getValue(this.getCharacterCollator(var2)) : var3);
      }
   }

   public static DataValueDescriptor getNullDVDWithUCS_BASICcollation(int var0) {
      switch (var0) {
         case 31 -> {
            return new SQLTimestamp();
         }
         case 77 -> {
            return new SQLBoolean();
         }
         case 78 -> {
            return new SQLChar();
         }
         case 79 -> {
            return new SQLDouble();
         }
         case 80 -> {
            return new SQLInteger();
         }
         case 81 -> {
            return new SQLReal();
         }
         case 82 -> {
            return new SQLRef();
         }
         case 83 -> {
            return new SQLSmallint();
         }
         case 84 -> {
            return new SQLLongint();
         }
         case 85 -> {
            return new SQLVarchar();
         }
         case 87 -> {
            return new SQLBit();
         }
         case 88 -> {
            return new SQLVarbit();
         }
         case 90 -> {
            return new HeapRowLocation();
         }
         case 199 -> {
            return new SQLTinyint();
         }
         case 200 -> {
            return new SQLDecimal();
         }
         case 234 -> {
            return new SQLLongVarbit();
         }
         case 235 -> {
            return new SQLLongvarchar();
         }
         case 266 -> {
            return new UserType();
         }
         case 298 -> {
            return new SQLDate();
         }
         case 299 -> {
            return new SQLTime();
         }
         case 443 -> {
            return new SQLBlob();
         }
         case 447 -> {
            return new SQLClob();
         }
         case 458 -> {
            return new XML();
         }
         default -> {
            return null;
         }
      }
   }

   private LocaleFinder getLocaleFinder() {
      if (this.localeFinder == null) {
         DatabaseContext var1 = (DatabaseContext)getContext("Database");
         if (var1 != null) {
            this.localeFinder = var1.getDatabase();
         }
      }

      return this.localeFinder;
   }

   static Context getContext(String var0) {
      return ContextService.getContext(var0);
   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }
}
