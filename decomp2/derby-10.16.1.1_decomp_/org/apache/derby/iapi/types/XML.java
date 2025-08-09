package org.apache.derby.iapi.types;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.RuleBasedCollator;
import java.util.List;
import org.apache.derby.iapi.services.cache.ClassSize;
import org.apache.derby.iapi.services.io.ArrayInputStream;
import org.apache.derby.iapi.services.io.StreamStorable;
import org.apache.derby.iapi.sql.conn.ConnectionUtil;
import org.apache.derby.shared.common.error.StandardException;

public class XML extends DataType implements XMLDataValue, StreamStorable {
   protected static final short UTF8_IMPL_ID = 0;
   private static final int BASE_MEMORY_USAGE = ClassSize.estimateBaseFromCatalog(XML.class);
   public static final short XQ_PASS_BY_REF = 1;
   public static final short XQ_PASS_BY_VALUE = 2;
   public static final short XQ_RETURN_SEQUENCE = 3;
   public static final short XQ_RETURN_CONTENT = 4;
   public static final short XQ_EMPTY_ON_EMPTY = 5;
   public static final short XQ_NULL_ON_EMPTY = 6;
   public static final int XML_DOC_ANY = 0;
   public static final int XML_SEQUENCE = 1;
   private int xType;
   private SQLChar xmlStringValue;
   private static String xmlReqCheck = null;
   private boolean containsTopLevelAttr;
   private SqlXmlUtil tmpUtil;

   public XML() {
      this.xmlStringValue = null;
      this.xType = -1;
      this.containsTopLevelAttr = false;
   }

   private XML(SQLChar var1, int var2, boolean var3, boolean var4) {
      this.xmlStringValue = var1 == null ? null : (SQLChar)var1.cloneValue(var4);
      this.setXType(var2);
      if (var3) {
         this.markAsHavingTopLevelAttr();
      }

   }

   public DataValueDescriptor cloneValue(boolean var1) {
      return new XML(this.xmlStringValue, this.getXType(), this.hasTopLevelAttr(), var1);
   }

   public DataValueDescriptor getNewNull() {
      return new XML();
   }

   public String getTypeName() {
      return "XML";
   }

   public int typePrecedence() {
      return 180;
   }

   public String getString() throws StandardException {
      return this.xmlStringValue == null ? null : this.xmlStringValue.getString();
   }

   public int getLength() throws StandardException {
      return this.xmlStringValue == null ? 0 : this.xmlStringValue.getLength();
   }

   public int estimateMemoryUsage() {
      int var1 = BASE_MEMORY_USAGE;
      if (this.xmlStringValue != null) {
         var1 += this.xmlStringValue.estimateMemoryUsage();
      }

      return var1;
   }

   public void readExternalFromArray(ArrayInputStream var1) throws IOException {
      if (this.xmlStringValue == null) {
         this.xmlStringValue = new SQLChar();
      }

      var1.readShort();
      this.xmlStringValue.readExternalFromArray(var1);
      this.setXType(0);
   }

   protected void setFrom(DataValueDescriptor var1) throws StandardException {
      String var2 = var1.getString();
      if (var2 == null) {
         this.xmlStringValue = null;
         this.setXType(0);
      } else {
         if (this.xmlStringValue == null) {
            this.xmlStringValue = new SQLChar();
         }

         this.xmlStringValue.setValue(var2);
         if (var1 instanceof XMLDataValue) {
            this.setXType(((XMLDataValue)var1).getXType());
            if (((XMLDataValue)var1).hasTopLevelAttr()) {
               this.markAsHavingTopLevelAttr();
            }
         }

      }
   }

   public final void setValueFromResultSet(ResultSet var1, int var2, boolean var3) throws SQLException {
      if (this.xmlStringValue == null) {
         this.xmlStringValue = new SQLChar();
      }

      String var4 = var1.getString(var2);
      if (this.tmpUtil == null) {
         try {
            this.tmpUtil = new SqlXmlUtil();
         } catch (StandardException var7) {
            this.xmlStringValue.setValue(var4);
            this.setXType(-1);
            return;
         }
      }

      try {
         var4 = this.tmpUtil.serializeToString(var4);
         this.xmlStringValue.setValue(var4);
         this.setXType(0);
      } catch (Throwable var6) {
         this.xmlStringValue.setValue(var4);
         this.setXType(-1);
      }

   }

   public int compare(DataValueDescriptor var1) throws StandardException {
      if (this.isNull()) {
         return var1.isNull() ? 0 : -1;
      } else {
         return var1.isNull() ? 1 : 0;
      }
   }

   public void normalize(DataTypeDescriptor var1, DataValueDescriptor var2) throws StandardException {
      if (((XMLDataValue)var2).getXType() != 0) {
         throw StandardException.newException("2200L", new Object[0]);
      } else {
         this.setValue(var2);
      }
   }

   public int getTypeFormatId() {
      return 458;
   }

   public boolean isNull() {
      return this.xmlStringValue == null || this.xmlStringValue.isNull();
   }

   public void restoreToNull() {
      if (this.xmlStringValue != null) {
         this.xmlStringValue.restoreToNull();
      }

   }

   public void readExternal(ObjectInput var1) throws IOException {
      if (this.xmlStringValue == null) {
         this.xmlStringValue = new SQLChar();
      }

      var1.readShort();
      this.xmlStringValue.readExternal(var1);
      this.setXType(0);
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeShort(0);
      this.xmlStringValue.writeExternal(var1);
   }

   public InputStream returnStream() {
      return this.xmlStringValue == null ? null : this.xmlStringValue.returnStream();
   }

   public void setStream(InputStream var1) {
      if (this.xmlStringValue == null) {
         this.xmlStringValue = new SQLChar();
      }

      try {
         var1.read();
         var1.read();
      } catch (Exception var3) {
      }

      this.xmlStringValue.setStream(var1);
      this.setXType(0);
   }

   public void loadStream() throws StandardException {
      this.getString();
   }

   public XMLDataValue XMLParse(StringDataValue var1, boolean var2, SqlXmlUtil var3) throws StandardException {
      if (var1.isNull()) {
         this.setToNull();
         return this;
      } else {
         String var4 = var1.getString();

         try {
            if (var2) {
               var4 = var3.serializeToString(var4);
            }
         } catch (Throwable var6) {
            throw StandardException.newException("2200M", var6, new Object[]{var6.getMessage()});
         }

         this.setXType(0);
         if (this.xmlStringValue == null) {
            this.xmlStringValue = new SQLChar();
         }

         this.xmlStringValue.setValue(var4);
         return this;
      }
   }

   public StringDataValue XMLSerialize(StringDataValue var1, int var2, int var3, int var4) throws StandardException {
      if (var1 == null) {
         switch (var2) {
            case -1 -> var1 = new SQLLongvarchar();
            case 1 -> var1 = new SQLChar();
            case 12 -> var1 = new SQLVarchar();
            case 2005 -> var1 = new SQLClob();
            default -> {
               return null;
            }
         }

         try {
            RuleBasedCollator var5 = ConnectionUtil.getCurrentLCC().getDataValueFactory().getCharacterCollator(var4);
            var1 = var1.getValue(var5);
         } catch (SQLException var6) {
            throw StandardException.plainWrapException(var6);
         }
      }

      if (this.isNull()) {
         var1.setToNull();
         return var1;
      } else if (this.hasTopLevelAttr()) {
         throw StandardException.newException("2200W", new Object[0]);
      } else {
         var1.setValue(this.getString());
         var1.setWidth(var3, 0, true);
         return var1;
      }
   }

   public BooleanDataValue XMLExists(SqlXmlUtil var1) throws StandardException {
      if (this.isNull()) {
         return SQLBoolean.unknownTruthValue();
      } else {
         try {
            return new SQLBoolean(null != var1.evalXQExpression(this, false, new int[1]));
         } catch (StandardException var3) {
            throw var3;
         } catch (Throwable var4) {
            throw StandardException.newException("10000", var4, new Object[]{"XMLEXISTS", var4.getMessage()});
         }
      }
   }

   public XMLDataValue XMLQuery(SqlXmlUtil var1, XMLDataValue var2) throws StandardException {
      if (this.isNull()) {
         if (var2 == null) {
            var2 = (XMLDataValue)this.getNewNull();
         } else {
            ((XMLDataValue)var2).setToNull();
         }

         return (XMLDataValue)var2;
      } else {
         try {
            int[] var3 = new int[1];
            List var4 = var1.evalXQExpression(this, true, var3);
            if (var2 == null) {
               var2 = new XML();
            }

            String var5 = var1.serializeToString(var4, (XMLDataValue)var2);
            ((XMLDataValue)var2).setValue(new SQLChar(var5));
            ((XMLDataValue)var2).setXType(var3[0]);
            return (XMLDataValue)var2;
         } catch (StandardException var6) {
            throw var6;
         } catch (Throwable var7) {
            throw StandardException.newException("10000", var7, new Object[]{"XMLQUERY", var7.getMessage()});
         }
      }
   }

   public void setXType(int var1) {
      this.xType = var1;
      if (var1 == 0) {
         this.containsTopLevelAttr = false;
      }

   }

   public int getXType() {
      return this.xType;
   }

   public void markAsHavingTopLevelAttr() {
      this.containsTopLevelAttr = true;
   }

   public boolean hasTopLevelAttr() {
      return this.containsTopLevelAttr;
   }

   public static void checkXMLRequirements() throws StandardException {
      if (xmlReqCheck == null) {
         xmlReqCheck = "";
         if (!checkJAXPRequirement()) {
            xmlReqCheck = "JAXP";
         } else if (!checkXPathRequirement()) {
            xmlReqCheck = "XPath";
         }
      }

      if (xmlReqCheck.length() != 0) {
         throw StandardException.newException("XML00", new Object[]{xmlReqCheck});
      }
   }

   private static boolean checkJAXPRequirement() {
      try {
         Class.forName("javax.xml.parsers.DocumentBuilderFactory");
         return true;
      } catch (Throwable var1) {
         return false;
      }
   }

   private static boolean checkXPathRequirement() {
      try {
         Class.forName("javax.xml.xpath.XPathFactory");
         return true;
      } catch (Throwable var1) {
         return false;
      }
   }
}
