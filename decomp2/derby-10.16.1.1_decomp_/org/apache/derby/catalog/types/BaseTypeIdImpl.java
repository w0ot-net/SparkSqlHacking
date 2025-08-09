package org.apache.derby.catalog.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.shared.common.i18n.MessageService;

public class BaseTypeIdImpl implements Formatable {
   private int formatId;
   protected String schemaName;
   String unqualifiedName;
   transient int JDBCTypeId;

   public BaseTypeIdImpl() {
   }

   public BaseTypeIdImpl(int var1) {
      this.formatId = var1;
      this.setTypeIdSpecificInstanceVariables();
   }

   BaseTypeIdImpl(String var1) {
      this.schemaName = null;
      this.unqualifiedName = var1;
   }

   BaseTypeIdImpl(String var1, String var2) {
      this.schemaName = var1;
      this.unqualifiedName = var2;
   }

   public String getSQLTypeName() {
      return this.schemaName == null ? this.unqualifiedName : IdUtil.mkQualifiedName(this.schemaName, this.unqualifiedName);
   }

   public String getSchemaName() {
      return this.schemaName;
   }

   public String getUnqualifiedName() {
      return this.unqualifiedName;
   }

   public boolean isAnsiUDT() {
      return this.schemaName != null;
   }

   public int getJDBCTypeId() {
      return this.JDBCTypeId;
   }

   public String toParsableString(TypeDescriptor var1) {
      String var2 = this.getSQLTypeName();
      switch (this.getTypeFormatId()) {
         case 17:
         case 25:
         case 442:
         case 446:
            var2 = var2 + "(" + var1.getMaximumWidth() + ")";
            break;
         case 28:
         case 30:
            int var3 = var2.indexOf(41);
            String var4 = var2.substring(0, var3);
            var2 = var4 + var1.getMaximumWidth() + var2.substring(var3);
            break;
         case 198:
            var2 = var2 + "(" + var1.getPrecision() + "," + var1.getScale() + ")";
      }

      return var2;
   }

   public boolean userType() {
      return false;
   }

   public String toString() {
      String var10000 = MessageService.getTextMessage("44X00.U", new Object[0]);
      return var10000 + ": " + this.getSQLTypeName();
   }

   public boolean equals(Object var1) {
      return var1 instanceof BaseTypeIdImpl ? this.getSQLTypeName().equals(((BaseTypeIdImpl)var1).getSQLTypeName()) : false;
   }

   public int hashCode() {
      return this.getSQLTypeName().hashCode();
   }

   public int getTypeFormatId() {
      if (this.formatId != 0) {
         return this.formatId;
      } else if ("BOOLEAN".equals(this.unqualifiedName)) {
         return 16;
      } else if ("BIGINT".equals(this.unqualifiedName)) {
         return 23;
      } else if ("INTEGER".equals(this.unqualifiedName)) {
         return 19;
      } else if ("SMALLINT".equals(this.unqualifiedName)) {
         return 22;
      } else if ("TINYINT".equals(this.unqualifiedName)) {
         return 196;
      } else if ("DECIMAL".equals(this.unqualifiedName)) {
         return 198;
      } else if ("NUMERIC".equals(this.unqualifiedName)) {
         return 198;
      } else if ("DOUBLE".equals(this.unqualifiedName)) {
         return 18;
      } else if ("REAL".equals(this.unqualifiedName)) {
         return 20;
      } else if ("REF".equals(this.unqualifiedName)) {
         return 21;
      } else if ("CHAR".equals(this.unqualifiedName)) {
         return 17;
      } else if ("VARCHAR".equals(this.unqualifiedName)) {
         return 25;
      } else if ("LONG VARCHAR".equals(this.unqualifiedName)) {
         return 231;
      } else if ("CLOB".equals(this.unqualifiedName)) {
         return 446;
      } else if ("CHAR () FOR BIT DATA".equals(this.unqualifiedName)) {
         return 28;
      } else if ("CHAR FOR BIT DATA".equals(this.unqualifiedName)) {
         return 28;
      } else if ("VARCHAR () FOR BIT DATA".equals(this.unqualifiedName)) {
         return 30;
      } else if ("VARCHAR FOR BIT DATA".equals(this.unqualifiedName)) {
         return 30;
      } else if ("LONG VARCHAR FOR BIT DATA".equals(this.unqualifiedName)) {
         return 233;
      } else if ("BLOB".equals(this.unqualifiedName)) {
         return 442;
      } else if ("DATE".equals(this.unqualifiedName)) {
         return 32;
      } else if ("TIME".equals(this.unqualifiedName)) {
         return 33;
      } else if ("TIMESTAMP".equals(this.unqualifiedName)) {
         return 34;
      } else {
         return "XML".equals(this.unqualifiedName) ? 457 : 0;
      }
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.unqualifiedName = var1.readUTF();
      if (this.unqualifiedName.charAt(0) == '"') {
         this.schemaName = this.stripQuotes(this.unqualifiedName);
         this.unqualifiedName = var1.readUTF();
      }

   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      if (this.schemaName == null) {
         var1.writeUTF(this.unqualifiedName);
      } else {
         var1.writeUTF(this.doubleQuote(this.schemaName));
         var1.writeUTF(this.unqualifiedName);
      }

   }

   private void setTypeIdSpecificInstanceVariables() {
      switch (this.getTypeFormatId()) {
         case 16:
            this.schemaName = null;
            this.unqualifiedName = "BOOLEAN";
            this.JDBCTypeId = 16;
            break;
         case 17:
            this.schemaName = null;
            this.unqualifiedName = "CHAR";
            this.JDBCTypeId = 1;
            break;
         case 18:
            this.schemaName = null;
            this.unqualifiedName = "DOUBLE";
            this.JDBCTypeId = 8;
            break;
         case 19:
            this.schemaName = null;
            this.unqualifiedName = "INTEGER";
            this.JDBCTypeId = 4;
            break;
         case 20:
            this.schemaName = null;
            this.unqualifiedName = "REAL";
            this.JDBCTypeId = 7;
            break;
         case 21:
            this.schemaName = null;
            this.unqualifiedName = "REF";
            this.JDBCTypeId = 1111;
            break;
         case 22:
            this.schemaName = null;
            this.unqualifiedName = "SMALLINT";
            this.JDBCTypeId = 5;
            break;
         case 23:
            this.schemaName = null;
            this.unqualifiedName = "BIGINT";
            this.JDBCTypeId = -5;
            break;
         case 25:
            this.schemaName = null;
            this.unqualifiedName = "VARCHAR";
            this.JDBCTypeId = 12;
            break;
         case 28:
            this.schemaName = null;
            this.unqualifiedName = "CHAR () FOR BIT DATA";
            this.JDBCTypeId = -2;
            break;
         case 30:
            this.schemaName = null;
            this.unqualifiedName = "VARCHAR () FOR BIT DATA";
            this.JDBCTypeId = -3;
            break;
         case 32:
            this.schemaName = null;
            this.unqualifiedName = "DATE";
            this.JDBCTypeId = 91;
            break;
         case 33:
            this.schemaName = null;
            this.unqualifiedName = "TIME";
            this.JDBCTypeId = 92;
            break;
         case 34:
            this.schemaName = null;
            this.unqualifiedName = "TIMESTAMP";
            this.JDBCTypeId = 93;
            break;
         case 196:
            this.schemaName = null;
            this.unqualifiedName = "TINYINT";
            this.JDBCTypeId = -6;
            break;
         case 198:
            this.schemaName = null;
            this.unqualifiedName = "DECIMAL";
            this.JDBCTypeId = 3;
            break;
         case 231:
            this.schemaName = null;
            this.unqualifiedName = "LONG VARCHAR";
            this.JDBCTypeId = -1;
            break;
         case 233:
            this.schemaName = null;
            this.unqualifiedName = "LONG VARCHAR FOR BIT DATA";
            this.JDBCTypeId = -4;
            break;
         case 442:
            this.schemaName = null;
            this.unqualifiedName = "BLOB";
            this.JDBCTypeId = 2004;
            break;
         case 446:
            this.schemaName = null;
            this.unqualifiedName = "CLOB";
            this.JDBCTypeId = 2005;
            break;
         case 457:
            this.schemaName = null;
            this.unqualifiedName = "XML";
            this.JDBCTypeId = 2009;
      }

   }

   private String doubleQuote(String var1) {
      return "\"" + var1 + "\"";
   }

   private String stripQuotes(String var1) {
      return var1.substring(1, var1.length() - 1);
   }
}
