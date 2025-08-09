package org.apache.curator.framework.recipes.nodes;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;

/** @deprecated */
@Deprecated
public class PersistentEphemeralNode extends PersistentNode {
   public PersistentEphemeralNode(CuratorFramework client, Mode mode, String basePath, byte[] initData) {
      super(client, mode.getCreateMode(false), mode.isProtected(), basePath, initData);
   }

   /** @deprecated */
   @Deprecated
   public static enum Mode {
      EPHEMERAL {
         protected CreateMode getCreateMode(boolean pathIsSet) {
            return CreateMode.EPHEMERAL;
         }

         protected boolean isProtected() {
            return false;
         }
      },
      EPHEMERAL_SEQUENTIAL {
         protected CreateMode getCreateMode(boolean pathIsSet) {
            return pathIsSet ? CreateMode.EPHEMERAL : CreateMode.EPHEMERAL_SEQUENTIAL;
         }

         protected boolean isProtected() {
            return false;
         }
      },
      PROTECTED_EPHEMERAL {
         protected CreateMode getCreateMode(boolean pathIsSet) {
            return CreateMode.EPHEMERAL;
         }

         protected boolean isProtected() {
            return true;
         }
      },
      PROTECTED_EPHEMERAL_SEQUENTIAL {
         protected CreateMode getCreateMode(boolean pathIsSet) {
            return pathIsSet ? CreateMode.EPHEMERAL : CreateMode.EPHEMERAL_SEQUENTIAL;
         }

         protected boolean isProtected() {
            return true;
         }
      };

      private Mode() {
      }

      protected abstract CreateMode getCreateMode(boolean var1);

      protected abstract boolean isProtected();
   }
}
