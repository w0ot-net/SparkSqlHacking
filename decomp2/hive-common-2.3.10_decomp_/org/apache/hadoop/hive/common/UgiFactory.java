package org.apache.hadoop.hive.common;

import java.io.IOException;
import org.apache.hadoop.security.UserGroupInformation;

public interface UgiFactory {
   UserGroupInformation createUgi() throws IOException;
}
