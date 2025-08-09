package org.apache.derby.impl.tools.planexporter;

import java.io.IOException;
import java.io.Writer;

public class CreateXMLFile {
   AccessDatabase access;

   public CreateXMLFile(AccessDatabase var1) {
      this.access = var1;
   }

   public void writeTheXMLFile(String var1, String var2, Writer var3, String var4) throws IOException {
      String var5 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
      String var6 = "";
      if (var4 != null) {
         var6 = "<?xml-stylesheet type=\"text/xsl\" href=\"" + var4 + "\"?>\n";
      }

      String var7 = "<!-- Apache Derby Query Explainer (DERBY-4587)-->\n";
      String var8 = "<plan>\n";
      String var9 = "</plan>\n";
      String var10 = "<details>\n";
      String var11 = "</details>\n";
      var3.write(var5);
      var3.write(var6);
      var3.write(var7);
      var3.write(var8);
      var3.write(this.access.indent(0));
      var3.write(var1);
      var3.write(this.access.indent(0));
      var3.write(var2);
      var3.write(this.access.indent(0));
      var3.write(this.access.stmtID());
      var3.write(this.access.indent(0));
      var3.write(var10);
      var3.write(this.access.getXmlString());
      var3.write(this.access.indent(0));
      var3.write(var11);
      var3.write(var9);
   }
}
