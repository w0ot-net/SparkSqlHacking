package org.datanucleus.store.rdbms.query;

import java.sql.ResultSet;
import org.datanucleus.ExecutionContext;

public interface ResultObjectFactory {
   Object getObject(ExecutionContext var1, ResultSet var2);
}
