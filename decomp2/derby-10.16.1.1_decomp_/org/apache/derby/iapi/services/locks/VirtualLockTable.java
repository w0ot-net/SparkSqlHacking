package org.apache.derby.iapi.services.locks;

public interface VirtualLockTable {
   int LATCH = 1;
   int TABLE_AND_ROWLOCK = 2;
   int SHEXLOCK = 4;
   int ALL = -1;
   String LOCKTYPE = "TYPE";
   String LOCKNAME = "LOCKNAME";
   String CONGLOMID = "CONGLOMID";
   String CONTAINERID = "CONTAINERID";
   String SEGMENTID = "SEGMENTID";
   String PAGENUM = "PAGENUM";
   String RECID = "RECID";
   String XACTID = "XID";
   String LOCKCOUNT = "LOCKCOUNT";
   String LOCKMODE = "MODE";
   String STATE = "STATE";
   String LOCKOBJ = "LOCKOBJ";
   String TABLENAME = "TABLENAME";
   String INDEXNAME = "INDEXNAME";
   String TABLETYPE = "TABLETYPE";
}
