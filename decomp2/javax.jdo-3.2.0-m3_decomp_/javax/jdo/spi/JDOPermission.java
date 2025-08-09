package javax.jdo.spi;

import java.security.BasicPermission;

public final class JDOPermission extends BasicPermission {
   private static final long serialVersionUID = 3087132628681636890L;
   public static final JDOPermission GET_METADATA = new JDOPermission("getMetadata");
   public static final JDOPermission MANAGE_METADATA = new JDOPermission("manageMetadata");
   public static final JDOPermission SET_STATE_MANAGER = new JDOPermission("setStateManager");
   public static final JDOPermission CLOSE_PERSISTENCE_MANAGER_FACTORY = new JDOPermission("closePersistenceManagerFactory");

   public JDOPermission(String name) {
      super(name);
   }

   public JDOPermission(String name, String actions) {
      super(name, actions);
   }
}
