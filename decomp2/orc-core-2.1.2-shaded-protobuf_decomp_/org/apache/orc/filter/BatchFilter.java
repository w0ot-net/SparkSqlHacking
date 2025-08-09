package org.apache.orc.filter;

import java.util.function.Consumer;

public interface BatchFilter extends Consumer {
   String[] getColumnNames();
}
