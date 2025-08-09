package org.apache.derby.impl.tools.ij;

import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.derby.iapi.tools.i18n.LocalizedResource;
import org.apache.derby.shared.common.reference.Attribute;

public class URLCheck {
   private Vector attributes;
   private static Vector booleanAttributes;
   private LocalizedResource langUtil = LocalizedResource.getInstance();
   private Vector validProps;
   private static Vector validDerbyProps;

   public URLCheck(String var1) {
      try {
         this.getAttributes(var1, new Properties());
         this.check();
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public static void main(String[] var0) {
      if (var0.length > 0) {
         new URLCheck(var0[0]);
      }

   }

   public void check() {
      Enumeration var1 = this.attributes.elements();

      while(var1.hasMoreElements()) {
         AttributeHolder var2 = (AttributeHolder)var1.nextElement();
         this.checkForDuplicate(var2);
         var2.check(this.validProps);
      }

   }

   public void checkForDuplicate(AttributeHolder var1) {
      Enumeration var2 = this.attributes.elements();

      while(var2.hasMoreElements()) {
         AttributeHolder var3 = (AttributeHolder)var2.nextElement();
         if (var1 != var3 && var1.getName().equals(var3.getName())) {
            var1.addError(this.langUtil.getTextMessage("TL_dupAtt"));
         }
      }

   }

   public Properties getAttributes(String var1, Properties var2) throws Exception {
      String var3 = "";
      if (!var1.startsWith("jdbc:derby:net:") && !var1.startsWith("jdbc:derby://")) {
         if (var1.startsWith("jdbc:derby:")) {
            var3 = "jdbc:derby:";
            this.validProps = this.getValidDerbyProps();
         } else {
            this.validProps = null;
         }
      } else {
         this.validProps = null;
      }

      StringTokenizer var4 = new StringTokenizer(var1.substring(var3.length()), ";:\"");
      this.attributes = new Vector();

      while(var4.hasMoreTokens()) {
         AttributeHolder var5 = new AttributeHolder();
         String var6 = "";
         String var7 = "";
         String var8 = var4.nextToken();
         int var9 = var8.indexOf(61);
         if (var9 != -1) {
            var6 = var8.substring(0, var9).trim();
            var7 = var8.substring(var9 + 1).trim();
            var5.setName(var6);
            var5.setValue(var7);
            var5.setToken(var8);
            this.attributes.addElement(var5);
            var2.put(var6, var8);
         }
      }

      return var2;
   }

   public static Vector getBooleanAttributes() {
      if (booleanAttributes == null) {
         booleanAttributes = new Vector();
         booleanAttributes.addElement("dataEncryption");
         booleanAttributes.addElement("create");
         booleanAttributes.addElement("shutdown");
         booleanAttributes.addElement("upgrade");
      }

      return booleanAttributes;
   }

   private Vector getValidDerbyProps() {
      if (validDerbyProps == null) {
         try {
            Vector var1 = new Vector();
            Class var2 = Attribute.class;
            Field[] var3 = var2.getFields();

            for(int var4 = 0; var4 < var3.length; ++var4) {
               Field var5 = var3[var4];
               var1.addElement((String)var5.get(var2));
            }

            validDerbyProps = var1;
         } catch (Exception var6) {
            var6.printStackTrace();
         }
      }

      return validDerbyProps;
   }
}
