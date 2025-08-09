package org.apache.spark.resource;

import org.apache.spark.annotation.DeveloperApi;
import scala.collection.immutable.;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u000553A\u0001D\u0007\u0001-!AQ\u0004\u0001BC\u0002\u0013\u0005a\u0004\u0003\u0005+\u0001\t\u0005\t\u0015!\u0003 \u0011!Y\u0003A!b\u0001\n\u0003q\u0002\u0002\u0003\u0017\u0001\u0005\u0003\u0005\u000b\u0011B\u0010\t\u000b5\u0002A\u0011\u0001\u0018\t\rM\u0002A\u0011A\b\u001f\u0011\u0019!\u0004\u0001\"\u0001\u0010=!1Q\u0007\u0001C\u0001\u001fyAaA\u000e\u0001\u0005\u0002=q\u0002\"B\u001c\u0001\t\u0003B\u0004\"B!\u0001\t\u0003\u0012%A\u0003*fg>,(oY3J\t*\u0011abD\u0001\te\u0016\u001cx.\u001e:dK*\u0011\u0001#E\u0001\u0006gB\f'o\u001b\u0006\u0003%M\ta!\u00199bG\",'\"\u0001\u000b\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u00019\u0002C\u0001\r\u001c\u001b\u0005I\"\"\u0001\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005qI\"AB!osJ+g-A\u0007d_6\u0004xN\\3oi:\u000bW.Z\u000b\u0002?A\u0011\u0001e\n\b\u0003C\u0015\u0002\"AI\r\u000e\u0003\rR!\u0001J\u000b\u0002\rq\u0012xn\u001c;?\u0013\t1\u0013$\u0001\u0004Qe\u0016$WMZ\u0005\u0003Q%\u0012aa\u0015;sS:<'B\u0001\u0014\u001a\u00039\u0019w.\u001c9p]\u0016tGOT1nK\u0002\nAB]3t_V\u00148-\u001a(b[\u0016\fQB]3t_V\u00148-\u001a(b[\u0016\u0004\u0013A\u0002\u001fj]&$h\bF\u00020cI\u0002\"\u0001\r\u0001\u000e\u00035AQ!H\u0003A\u0002}AQaK\u0003A\u0002}\t!bY8oMB\u0013XMZ5y\u0003)\tWn\\;oi\u000e{gNZ\u0001\u0014I&\u001c8m\u001c<fef\u001c6M]5qi\u000e{gNZ\u0001\u000bm\u0016tGm\u001c:D_:4\u0017AB3rk\u0006d7\u000f\u0006\u0002:yA\u0011\u0001DO\u0005\u0003we\u0011qAQ8pY\u0016\fg\u000eC\u0003>\u0015\u0001\u0007a(A\u0002pE*\u0004\"\u0001G \n\u0005\u0001K\"aA!os\u0006A\u0001.Y:i\u0007>$W\rF\u0001D!\tAB)\u0003\u0002F3\t\u0019\u0011J\u001c;)\u0005\u00019\u0005C\u0001%L\u001b\u0005I%B\u0001&\u0010\u0003)\tgN\\8uCRLwN\\\u0005\u0003\u0019&\u0013A\u0002R3wK2|\u0007/\u001a:Ba&\u0004"
)
public class ResourceID {
   private final String componentName;
   private final String resourceName;

   public String componentName() {
      return this.componentName;
   }

   public String resourceName() {
      return this.resourceName;
   }

   public String confPrefix() {
      String var10000 = this.componentName();
      return var10000 + "." + ResourceUtils$.MODULE$.RESOURCE_PREFIX() + "." + this.resourceName() + ".";
   }

   public String amountConf() {
      String var10000 = this.confPrefix();
      return var10000 + ResourceUtils$.MODULE$.AMOUNT();
   }

   public String discoveryScriptConf() {
      String var10000 = this.confPrefix();
      return var10000 + ResourceUtils$.MODULE$.DISCOVERY_SCRIPT();
   }

   public String vendorConf() {
      String var10000 = this.confPrefix();
      return var10000 + ResourceUtils$.MODULE$.VENDOR();
   }

   public boolean equals(final Object obj) {
      if (!(obj instanceof ResourceID var4)) {
         return false;
      } else {
         boolean var10;
         label48: {
            label42: {
               Class var10000 = var4.getClass();
               Class var5 = this.getClass();
               if (var10000 == null) {
                  if (var5 != null) {
                     break label42;
                  }
               } else if (!var10000.equals(var5)) {
                  break label42;
               }

               String var8 = var4.componentName();
               String var6 = this.componentName();
               if (var8 == null) {
                  if (var6 != null) {
                     break label42;
                  }
               } else if (!var8.equals(var6)) {
                  break label42;
               }

               var8 = var4.resourceName();
               String var7 = this.resourceName();
               if (var8 == null) {
                  if (var7 == null) {
                     break label48;
                  }
               } else if (var8.equals(var7)) {
                  break label48;
               }
            }

            var10 = false;
            return var10;
         }

         var10 = true;
         return var10;
      }
   }

   public int hashCode() {
      return (new .colon.colon(this.componentName(), new .colon.colon(this.resourceName(), scala.collection.immutable.Nil..MODULE$))).hashCode();
   }

   public ResourceID(final String componentName, final String resourceName) {
      this.componentName = componentName;
      this.resourceName = resourceName;
   }
}
