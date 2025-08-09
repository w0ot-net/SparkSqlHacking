package org.apache.zookeeper;

import org.apache.yetus.audience.InterfaceAudience.Public;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Public
public enum CreateMode {
   PERSISTENT(0, false, false, false, false),
   PERSISTENT_SEQUENTIAL(2, false, true, false, false),
   EPHEMERAL(1, true, false, false, false),
   EPHEMERAL_SEQUENTIAL(3, true, true, false, false),
   CONTAINER(4, false, false, true, false),
   PERSISTENT_WITH_TTL(5, false, false, false, true),
   PERSISTENT_SEQUENTIAL_WITH_TTL(6, false, true, false, true);

   private static final Logger LOG = LoggerFactory.getLogger(CreateMode.class);
   private boolean ephemeral;
   private boolean sequential;
   private final boolean isContainer;
   private int flag;
   private boolean isTTL;

   private CreateMode(int flag, boolean ephemeral, boolean sequential, boolean isContainer, boolean isTTL) {
      this.flag = flag;
      this.ephemeral = ephemeral;
      this.sequential = sequential;
      this.isContainer = isContainer;
      this.isTTL = isTTL;
   }

   public boolean isEphemeral() {
      return this.ephemeral;
   }

   public boolean isSequential() {
      return this.sequential;
   }

   public boolean isContainer() {
      return this.isContainer;
   }

   public boolean isTTL() {
      return this.isTTL;
   }

   public int toFlag() {
      return this.flag;
   }

   public static CreateMode fromFlag(int flag) throws KeeperException {
      switch (flag) {
         case 0:
            return PERSISTENT;
         case 1:
            return EPHEMERAL;
         case 2:
            return PERSISTENT_SEQUENTIAL;
         case 3:
            return EPHEMERAL_SEQUENTIAL;
         case 4:
            return CONTAINER;
         case 5:
            return PERSISTENT_WITH_TTL;
         case 6:
            return PERSISTENT_SEQUENTIAL_WITH_TTL;
         default:
            String errMsg = "Received an invalid flag value: " + flag + " to convert to a CreateMode";
            LOG.error(errMsg);
            throw new KeeperException.BadArgumentsException(errMsg);
      }
   }

   public static CreateMode fromFlag(int flag, CreateMode defaultMode) {
      switch (flag) {
         case 0:
            return PERSISTENT;
         case 1:
            return EPHEMERAL;
         case 2:
            return PERSISTENT_SEQUENTIAL;
         case 3:
            return EPHEMERAL_SEQUENTIAL;
         case 4:
            return CONTAINER;
         case 5:
            return PERSISTENT_WITH_TTL;
         case 6:
            return PERSISTENT_SEQUENTIAL_WITH_TTL;
         default:
            return defaultMode;
      }
   }
}
