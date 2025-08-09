package org.apache.derby.vti;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XmlVTI extends StringColumnVTI {
   private String _rowTag;
   private InputStream _xmlResource;
   private int _rowIdx = -1;
   private int _rowCount = -1;
   private String[] _currentRow;
   private DocumentBuilder _builder;
   private NodeList _rawRows;
   private int _firstChildTagIdx;

   public XmlVTI(InputStream var1, String var2, int var3, String... var4) {
      super(var4);
      this._xmlResource = var1;
      this._rowTag = var2;
      this._firstChildTagIdx = var3;
   }

   public static XmlVTI xmlVTI(String var0, String var1, String... var2) throws Exception {
      return xmlVTI((String)var0, var1, (ArrayList)null, asList(var2));
   }

   public static XmlVTI xmlVTIFromURL(String var0, String var1, String... var2) throws Exception {
      return xmlVTIFromURL(var0, var1, (ArrayList)null, asList(var2));
   }

   public static XmlVTI xmlVTI(String var0, String var1, ArrayList var2, ArrayList var3) throws Exception {
      Object var4 = null;

      try {
         var7 = new FileInputStream(new File(var0));
      } catch (IOException var6) {
         throw new IllegalArgumentException(var6.getMessage(), var6);
      }

      return xmlVTI((InputStream)var7, var1, var2, var3);
   }

   public static XmlVTI xmlVTIFromURL(String var0, String var1, ArrayList var2, ArrayList var3) throws Exception {
      Object var4 = null;

      try {
         var7 = (new URL(var0)).openStream();
      } catch (IOException var6) {
         throw new IllegalArgumentException(var6.getMessage(), var6);
      }

      return xmlVTI(var7, var1, var2, var3);
   }

   private static XmlVTI xmlVTI(InputStream var0, String var1, ArrayList var2, ArrayList var3) throws Exception {
      if (var2 == null) {
         var2 = new ArrayList();
      }

      if (var3 == null) {
         var3 = new ArrayList();
      }

      String[] var4 = new String[var2.size() + var3.size()];
      int var5 = 0;

      for(String var7 : var2) {
         var4[var5++] = var7;
      }

      for(String var9 : var3) {
         var4[var5++] = var9;
      }

      return new XmlVTI(var0, var1, var2.size(), var4);
   }

   public static ArrayList asList(String... var0) {
      ArrayList var1 = new ArrayList();

      for(String var5 : var0) {
         var1.add(var5);
      }

      return var1;
   }

   protected String getRawColumn(int var1) throws SQLException {
      try {
         return this._currentRow[var1 - 1];
      } catch (Throwable var3) {
         throw new SQLException(var3.getMessage());
      }
   }

   public void close() throws SQLException {
      this._builder = null;
      this._rawRows = null;
   }

   public ResultSetMetaData getMetaData() throws SQLException {
      throw new SQLException("Not implemented.");
   }

   public boolean next() throws SQLException {
      try {
         if (this._rowIdx < 0) {
            this.readRows();
         }

         if (++this._rowIdx < this._rowCount) {
            this.parseRow(this._rowIdx);
            return true;
         } else {
            return false;
         }
      } catch (Throwable var2) {
         throw new SQLException(var2.getMessage(), var2);
      }
   }

   private void readRows() throws Exception {
      DocumentBuilderFactory var1 = DocumentBuilderFactory.newInstance();
      var1.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
      var1.setFeature("http://xml.org/sax/features/external-general-entities", false);
      this._builder = var1.newDocumentBuilder();
      this._builder.setErrorHandler(new XMLErrorHandler());
      Document var2 = this._builder.parse(this._xmlResource);
      Element var3 = var2.getDocumentElement();
      this._rawRows = var3.getElementsByTagName(this._rowTag);
      this._rowCount = this._rawRows.getLength();
      this._xmlResource.close();
   }

   private void parseRow(int var1) throws Exception {
      Element var2 = (Element)this._rawRows.item(var1);
      int var3 = this.getColumnCount();
      this._currentRow = new String[var3];

      for(int var4 = 0; var4 < var3; ++var4) {
         this._currentRow[var4] = this.findColumnValue(var2, var4);
      }

   }

   private String findColumnValue(Element var1, int var2) throws Exception {
      boolean var3 = var2 < this._firstChildTagIdx;
      if (var3) {
         Node var4 = var1.getParentNode();
         if (var4 == null || !(var4 instanceof Element)) {
            return null;
         }

         var1 = (Element)var4;
      }

      String var11 = this.getColumnName(var2 + 1);
      String var5 = var1.getAttribute(var11);
      if ("".equals(var5)) {
         var5 = null;
      }

      if (var5 == null) {
         NodeList var6 = var1.getElementsByTagName(var11);
         if (var6 != null && var6.getLength() > 0) {
            int var7 = var6.getLength();
            StringBuilder var8 = new StringBuilder();

            for(int var9 = 0; var9 < var7; ++var9) {
               Element var10 = (Element)var6.item(var9);
               if (var9 != 0) {
                  var8.append(" ");
               }

               var8.append(this.squeezeText(var10));
            }

            var5 = var8.toString();
         }
      }

      if (var3 && var5 == null) {
         return this.findColumnValue(var1, var2);
      } else {
         return var5;
      }
   }

   private String squeezeText(Element var1) throws Exception {
      String var2 = null;
      Node var3 = var1.getFirstChild();
      if (var3 != null) {
         var2 = var3.getNodeValue();
      }

      return var2;
   }

   private class XMLErrorHandler implements ErrorHandler {
      private void closeInput() {
         try {
            if (XmlVTI.this._xmlResource != null) {
               XmlVTI.this._xmlResource.close();
            }
         } catch (Exception var2) {
         }

      }

      public void error(SAXParseException var1) throws SAXException {
         this.closeInput();
         throw new SAXException(var1);
      }

      public void fatalError(SAXParseException var1) throws SAXException {
         this.closeInput();
         throw new SAXException(var1);
      }

      public void warning(SAXParseException var1) throws SAXException {
         this.closeInput();
         throw new SAXException(var1);
      }
   }
}
