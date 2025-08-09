package org.apache.derby.iapi.types;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.apache.derby.shared.common.error.StandardException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SqlXmlUtil {
   private DocumentBuilder dBuilder;
   private Transformer serializer;
   private XPathExpression query;
   private QName returnType;

   public SqlXmlUtil() throws StandardException {
      try {
         Object var1 = null;

         try {
            var6 = DocumentBuilderFactory.newInstance();
         } catch (Throwable var3) {
            throw StandardException.newException("XML00", new Object[]{"JAXP"});
         }

         var6.setValidating(false);
         var6.setNamespaceAware(true);
         var6.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
         var6.setFeature("http://xml.org/sax/features/external-general-entities", false);
         this.dBuilder = var6.newDocumentBuilder();
         this.dBuilder.setErrorHandler(new XMLErrorHandler());
         this.loadSerializer();
      } catch (StandardException var4) {
         throw var4;
      } catch (Throwable var5) {
         throw StandardException.newException("XML01", var5, new Object[]{var5.getMessage()});
      }

      this.query = null;
   }

   public void compileXQExpr(String var1, String var2) throws StandardException {
      try {
         XPath var3 = XPathFactory.newInstance().newXPath();
         var3.setNamespaceContext(SqlXmlUtil.NullNamespaceContext.SINGLETON);
         this.query = var3.compile(var1);
      } catch (Throwable var4) {
         throw StandardException.newException("10000", var4, new Object[]{var2, var4.getMessage()});
      }
   }

   protected String serializeToString(String var1) throws Exception {
      InputSource var3 = new InputSource(new StringReader(var1));
      Document var2 = this.dBuilder.parse(var3);
      return this.serializeToString(Collections.singletonList(var2), (XMLDataValue)null);
   }

   protected String serializeToString(List var1, XMLDataValue var2) throws TransformerException {
      if (var1.isEmpty()) {
         return "";
      } else if (var1.size() == 1 && var1.get(0) instanceof String) {
         return (String)var1.get(0);
      } else {
         StringWriter var3 = new StringWriter();

         for(Object var5 : var1) {
            if (var5 instanceof Attr) {
               var2.markAsHavingTopLevelAttr();
               this.serializer.transform(new DOMSource((Node)var5), new StreamResult(var3));
            } else {
               Node var6 = (Node)var5;
               if (var6 instanceof Text) {
                  var3.write(var6.getNodeValue());
               } else {
                  this.serializer.transform(new DOMSource(var6), new StreamResult(var3));
               }
            }
         }

         var3.flush();
         return var3.toString();
      }
   }

   protected List evalXQExpression(XMLDataValue var1, boolean var2, int[] var3) throws Exception {
      if (var1.getXType() != 0) {
         throw StandardException.newException("2200V", new Object[]{var2 ? "XMLQUERY" : "XMLEXISTS"});
      } else {
         Document var4 = this.dBuilder.parse(new InputSource(new StringReader(var1.getString())));
         Object var5 = this.evaluate(var4);
         if (!var2) {
            return var5 instanceof NodeList && ((NodeList)var5).getLength() == 0 ? null : Collections.emptyList();
         } else {
            Object var6;
            if (var5 instanceof NodeList) {
               NodeList var7 = (NodeList)var5;
               ArrayList var8 = new ArrayList();

               for(int var9 = 0; var9 < var7.getLength(); ++var9) {
                  var8.add(var7.item(var9));
               }

               var6 = var8;
            } else {
               var6 = Collections.singletonList(var5);
            }

            if (((List)var6).size() == 1 && ((List)var6).get(0) instanceof Document) {
               var3[0] = 0;
            } else {
               var3[0] = 1;
            }

            return (List)var6;
         }
      }
   }

   private Object evaluate(Document var1) throws XPathExpressionException {
      if (this.returnType != null) {
         return this.query.evaluate(var1, this.returnType);
      } else {
         try {
            Object var2 = this.query.evaluate(var1, XPathConstants.NODESET);
            this.returnType = XPathConstants.NODESET;
            return var2;
         } catch (Exception var4) {
            Object var3 = this.query.evaluate(var1, XPathConstants.STRING);
            this.returnType = XPathConstants.STRING;
            return var3;
         }
      }
   }

   private void loadSerializer() throws TransformerConfigurationException {
      Properties var1 = new Properties();
      var1.setProperty("method", "xml");
      var1.setProperty("omit-xml-declaration", "yes");
      var1.setProperty("encoding", "UTF-8");
      this.serializer = TransformerFactory.newInstance().newTransformer();
      this.serializer.setOutputProperties(var1);
   }

   private class XMLErrorHandler implements ErrorHandler {
      public void error(SAXParseException var1) throws SAXException {
         throw new SAXException(var1);
      }

      public void fatalError(SAXParseException var1) throws SAXException {
         throw new SAXException(var1);
      }

      public void warning(SAXParseException var1) throws SAXException {
         throw new SAXException(var1);
      }
   }

   private static class NullNamespaceContext implements NamespaceContext {
      private static final NullNamespaceContext SINGLETON = new NullNamespaceContext();

      public String getNamespaceURI(String var1) {
         return "";
      }

      public String getPrefix(String var1) {
         return null;
      }

      public Iterator getPrefixes(String var1) {
         List var2 = Collections.emptyList();
         return var2.iterator();
      }
   }
}
