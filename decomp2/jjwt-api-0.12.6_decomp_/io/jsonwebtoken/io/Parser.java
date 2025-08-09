package io.jsonwebtoken.io;

import java.io.InputStream;
import java.io.Reader;

public interface Parser {
   Object parse(CharSequence var1);

   Object parse(CharSequence var1, int var2, int var3);

   Object parse(Reader var1);

   Object parse(InputStream var1);
}
