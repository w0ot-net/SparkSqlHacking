package org.apache.derby.impl.sql.compile;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

interface CharStream {
   char readChar() throws IOException;

   /** @deprecated */
   @Deprecated
   int getColumn();

   /** @deprecated */
   @Deprecated
   int getLine();

   int getEndColumn();

   int getEndLine();

   int getBeginColumn();

   int getBeginLine();

   void backup(int var1);

   char BeginToken() throws IOException;

   String GetImage();

   char[] GetSuffix(int var1);

   void Done();

   int getBeginOffset();

   int getEndOffset();

   void ReInit(Reader var1, int var2, int var3, int var4);

   void ReInit(Reader var1, int var2, int var3);

   void ReInit(InputStream var1, int var2, int var3, int var4);

   void ReInit(InputStream var1, int var2, int var3);
}
