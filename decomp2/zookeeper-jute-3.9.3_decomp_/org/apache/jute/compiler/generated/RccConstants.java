package org.apache.jute.compiler.generated;

public interface RccConstants {
   int EOF = 0;
   int MODULE_TKN = 11;
   int RECORD_TKN = 12;
   int INCLUDE_TKN = 13;
   int BYTE_TKN = 14;
   int BOOLEAN_TKN = 15;
   int INT_TKN = 16;
   int LONG_TKN = 17;
   int FLOAT_TKN = 18;
   int DOUBLE_TKN = 19;
   int USTRING_TKN = 20;
   int BUFFER_TKN = 21;
   int VECTOR_TKN = 22;
   int MAP_TKN = 23;
   int LBRACE_TKN = 24;
   int RBRACE_TKN = 25;
   int LT_TKN = 26;
   int GT_TKN = 27;
   int SEMICOLON_TKN = 28;
   int COMMA_TKN = 29;
   int DOT_TKN = 30;
   int CSTRING_TKN = 31;
   int IDENT_TKN = 32;
   int DEFAULT = 0;
   int WithinOneLineComment = 1;
   int WithinMultiLineComment = 2;
   String[] tokenImage = new String[]{"<EOF>", "\" \"", "\"\\t\"", "\"\\n\"", "\"\\r\"", "\"//\"", "<token of kind 6>", "<token of kind 7>", "\"/*\"", "\"*/\"", "<token of kind 10>", "\"module\"", "\"class\"", "\"include\"", "\"byte\"", "\"boolean\"", "\"int\"", "\"long\"", "\"float\"", "\"double\"", "\"ustring\"", "\"buffer\"", "\"vector\"", "\"map\"", "\"{\"", "\"}\"", "\"<\"", "\">\"", "\";\"", "\",\"", "\".\"", "<CSTRING_TKN>", "<IDENT_TKN>"};
}
