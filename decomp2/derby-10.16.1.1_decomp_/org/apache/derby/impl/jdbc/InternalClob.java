package org.apache.derby.impl.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.SQLException;

interface InternalClob {
   long getCharLength() throws IOException, SQLException;

   long getCharLengthIfKnown();

   InputStream getRawByteStream() throws IOException, SQLException;

   Reader getReader(long var1) throws IOException, SQLException;

   Reader getInternalReader(long var1) throws IOException, SQLException;

   long getUpdateCount();

   Writer getWriter(long var1) throws IOException, SQLException;

   long insertString(String var1, long var2) throws IOException, SQLException;

   boolean isReleased();

   boolean isWritable();

   void release() throws IOException, SQLException;

   void truncate(long var1) throws IOException, SQLException;
}
