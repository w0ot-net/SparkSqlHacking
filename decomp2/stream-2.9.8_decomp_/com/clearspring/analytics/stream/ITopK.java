package com.clearspring.analytics.stream;

import java.util.List;

public interface ITopK {
   boolean offer(Object var1);

   boolean offer(Object var1, int var2);

   List peek(int var1);
}
