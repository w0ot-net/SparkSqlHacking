package org.apache.curator.framework.api;

import java.util.List;
import org.apache.curator.utils.InternalACLProvider;

public interface ACLProvider extends InternalACLProvider {
   List getDefaultAcl();

   List getAclForPath(String var1);
}
