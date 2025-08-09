package au.com.bytecode.opencsv;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetHelper {
   String[] getColumnNames(ResultSet var1) throws SQLException;

   String[] getColumnValues(ResultSet var1) throws SQLException, IOException;
}
