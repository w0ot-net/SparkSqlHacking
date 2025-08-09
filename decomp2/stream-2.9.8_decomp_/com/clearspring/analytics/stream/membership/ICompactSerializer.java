package com.clearspring.analytics.stream.membership;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface ICompactSerializer {
   void serialize(Object var1, DataOutputStream var2) throws IOException;

   Object deserialize(DataInputStream var1) throws IOException;
}
