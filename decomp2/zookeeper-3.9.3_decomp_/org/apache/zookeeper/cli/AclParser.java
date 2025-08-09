package org.apache.zookeeper.cli;

import java.util.ArrayList;
import java.util.List;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;

public class AclParser {
   public static List parse(String aclString) {
      String[] acls = aclString.split(",");
      List<ACL> acl = new ArrayList();

      for(String a : acls) {
         int firstColon = a.indexOf(58);
         int lastColon = a.lastIndexOf(58);
         if (firstColon != -1 && lastColon != -1 && firstColon != lastColon) {
            ACL newAcl = new ACL();
            newAcl.setId(new Id(a.substring(0, firstColon), a.substring(firstColon + 1, lastColon)));
            newAcl.setPerms(getPermFromString(a.substring(lastColon + 1)));
            acl.add(newAcl);
         } else {
            System.err.println(a + " does not have the form scheme:id:perm");
         }
      }

      return acl;
   }

   private static int getPermFromString(String permString) {
      int perm = 0;

      for(int i = 0; i < permString.length(); ++i) {
         switch (permString.charAt(i)) {
            case 'a':
               perm |= 16;
               break;
            case 'c':
               perm |= 4;
               break;
            case 'd':
               perm |= 8;
               break;
            case 'r':
               perm |= 1;
               break;
            case 'w':
               perm |= 2;
               break;
            default:
               System.err.println("Unknown perm type: " + permString.charAt(i));
         }
      }

      return perm;
   }
}
