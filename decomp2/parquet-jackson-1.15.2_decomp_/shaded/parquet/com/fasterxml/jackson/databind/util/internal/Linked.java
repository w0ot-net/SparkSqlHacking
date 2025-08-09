package shaded.parquet.com.fasterxml.jackson.databind.util.internal;

interface Linked {
   Linked getPrevious();

   void setPrevious(Linked var1);

   Linked getNext();

   void setNext(Linked var1);
}
