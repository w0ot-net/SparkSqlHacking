package org.apache.curator.framework.api;

import java.util.List;

public interface ParentACLable extends ACLable {
   Object withACL(List var1, boolean var2);
}
