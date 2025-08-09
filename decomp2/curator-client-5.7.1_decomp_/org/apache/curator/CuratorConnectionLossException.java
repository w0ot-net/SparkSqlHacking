package org.apache.curator;

import org.apache.zookeeper.KeeperException;

public class CuratorConnectionLossException extends KeeperException.ConnectionLossException {
   private static final long serialVersionUID = 1L;
}
