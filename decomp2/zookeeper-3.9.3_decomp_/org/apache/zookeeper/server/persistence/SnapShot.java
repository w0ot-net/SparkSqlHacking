package org.apache.zookeeper.server.persistence;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.apache.zookeeper.server.DataTree;

public interface SnapShot {
   long deserialize(DataTree var1, Map var2) throws IOException;

   void serialize(DataTree var1, Map var2, File var3, boolean var4) throws IOException;

   File findMostRecentSnapshot() throws IOException;

   SnapshotInfo getLastSnapshotInfo();

   void close() throws IOException;
}
