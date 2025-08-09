package org.apache.curator.framework.api;

import java.util.List;

public interface Membersable {
   Object withNewMembers(String... var1);

   Object withNewMembers(List var1);
}
