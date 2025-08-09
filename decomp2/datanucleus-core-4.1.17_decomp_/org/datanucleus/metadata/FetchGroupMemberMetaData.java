package org.datanucleus.metadata;

import org.datanucleus.util.StringUtils;

public class FetchGroupMemberMetaData extends MetaData {
   private static final long serialVersionUID = 548676970076554443L;
   String name;
   int recursionDepth = 1;
   boolean isProperty = false;

   public FetchGroupMemberMetaData(MetaData parent, String name) {
      super(parent);
      this.name = name;
   }

   public void setProperty() {
      this.isProperty = true;
   }

   public boolean isProperty() {
      return this.isProperty;
   }

   public String getName() {
      return this.name;
   }

   public int getRecursionDepth() {
      return this.recursionDepth;
   }

   public void setRecursionDepth(int depth) {
      this.recursionDepth = depth;
   }

   public void setRecursionDepth(String depth) {
      if (!StringUtils.isWhitespace(depth)) {
         try {
            this.recursionDepth = Integer.parseInt(depth);
         } catch (NumberFormatException var3) {
         }
      }

   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      if (this.isProperty) {
         sb.append(prefix).append("<property ");
      } else {
         sb.append(prefix).append("<field ");
      }

      if (this.recursionDepth != 1) {
         sb.append("name=\"" + this.name + "\" recursion-depth=\"" + this.recursionDepth + "\"/>\n");
      } else {
         sb.append("name=\"" + this.name + "\"/>\n");
      }

      return sb.toString();
   }
}
