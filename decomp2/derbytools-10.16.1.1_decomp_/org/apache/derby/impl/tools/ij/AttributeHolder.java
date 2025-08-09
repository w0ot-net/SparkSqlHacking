package org.apache.derby.impl.tools.ij;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Vector;
import org.apache.derby.iapi.tools.i18n.LocalizedResource;

public class AttributeHolder {
   String name;
   String value;
   String token;
   Vector errors = new Vector();

   public String getName() {
      return this.name;
   }

   public void setName(String var1) {
      this.name = var1;
   }

   String getValue() {
      return this.value;
   }

   public void setValue(String var1) {
      this.value = var1;
   }

   String getToken() {
      return this.token;
   }

   public void setToken(String var1) {
      this.token = var1;
   }

   public void addError(String var1) {
      if (!this.errors.contains(var1)) {
         this.errors.addElement(var1);
      }

   }

   public void check(Vector var1) {
      this.checkName(var1);
      this.displayErrors();
   }

   void displayErrors() {
      Enumeration var1 = this.errors.elements();
      if (var1.hasMoreElements()) {
         this.display(LocalizedResource.getMessage("TL_urlLabel1", "[", this.getToken(), "]"));
      }

      while(var1.hasMoreElements()) {
         String var2 = (String)var1.nextElement();
         this.displayIndented(var2);
      }

   }

   void checkName(Vector var1) {
      if (var1 != null) {
         String var2 = this.getName();

         try {
            if (!var1.contains(var2)) {
               if (var1.contains(var2.toLowerCase(Locale.ENGLISH))) {
                  this.errors.addElement(LocalizedResource.getMessage("TL_incorCase"));
               } else {
                  this.errors.addElement(LocalizedResource.getMessage("TL_unknownAtt"));
               }
            }
         } catch (Exception var4) {
            var4.printStackTrace();
         }

      }
   }

   void checkValue() {
      String var1 = this.getName();
      String var2 = this.getValue();

      try {
         if (URLCheck.getBooleanAttributes().contains(var1) && !this.checkBoolean(var2)) {
            this.errors.addElement(LocalizedResource.getMessage("TL_trueFalse"));
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   boolean checkBoolean(String var1) {
      if (var1 == null) {
         return false;
      } else {
         return var1.toLowerCase(Locale.ENGLISH).equals("true") || var1.toLowerCase(Locale.ENGLISH).equals("false");
      }
   }

   void display(String var1) {
      LocalizedResource.OutputWriter().println(var1);
   }

   void displayIndented(String var1) {
      LocalizedResource.OutputWriter().println("   " + var1);
   }
}
