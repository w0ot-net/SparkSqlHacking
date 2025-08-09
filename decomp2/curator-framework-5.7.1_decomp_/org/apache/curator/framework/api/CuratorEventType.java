package org.apache.curator.framework.api;

public enum CuratorEventType {
   CREATE,
   DELETE,
   EXISTS,
   GET_DATA,
   SET_DATA,
   CHILDREN,
   SYNC,
   GET_ACL,
   SET_ACL,
   TRANSACTION,
   GET_CONFIG,
   RECONFIG,
   WATCHED,
   REMOVE_WATCHES,
   CLOSING,
   ADD_WATCH;
}
