package org.apache.ivy.plugins.latest;

import java.util.Date;
import java.util.List;

public interface LatestStrategy {
   ArtifactInfo findLatest(ArtifactInfo[] var1, Date var2);

   List sort(ArtifactInfo[] var1);

   String getName();
}
