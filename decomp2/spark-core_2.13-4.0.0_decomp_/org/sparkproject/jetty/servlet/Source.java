package org.sparkproject.jetty.servlet;

public class Source {
   public static final Source EMBEDDED;
   public static final Source JAVAX_API;
   public Origin _origin;
   public String _resource;

   public Source(Origin o, String resource) {
      if (o == null) {
         throw new IllegalArgumentException("Origin is null");
      } else {
         this._origin = o;
         this._resource = resource;
      }
   }

   public Origin getOrigin() {
      return this._origin;
   }

   public String getResource() {
      return this._resource;
   }

   public String toString() {
      String var10000 = String.valueOf(this._origin);
      return var10000 + ":" + this._resource;
   }

   static {
      EMBEDDED = new Source(Source.Origin.EMBEDDED, (String)null);
      JAVAX_API = new Source(Source.Origin.JAKARTA_API, (String)null);
   }

   public static enum Origin {
      EMBEDDED,
      JAKARTA_API,
      DESCRIPTOR,
      ANNOTATION;

      // $FF: synthetic method
      private static Origin[] $values() {
         return new Origin[]{EMBEDDED, JAKARTA_API, DESCRIPTOR, ANNOTATION};
      }
   }
}
