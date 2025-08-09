package org.apache.curator.utils;

import java.util.List;

public interface InternalACLProvider {
   List getDefaultAcl();

   List getAclForPath(String var1);
}
