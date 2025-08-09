package org.glassfish.jersey.message.internal;

import java.text.ParseException;

class HttpHeaderListAdapter extends HttpHeaderReader {
   private final HttpHeaderReader reader;
   private boolean isTerminated;

   public HttpHeaderListAdapter(HttpHeaderReader reader) {
      this.reader = reader;
   }

   public void reset() {
      this.isTerminated = false;
   }

   public boolean hasNext() {
      if (this.isTerminated) {
         return false;
      } else if (this.reader.hasNext()) {
         if (this.reader.hasNextSeparator(',', true)) {
            this.isTerminated = true;
            return false;
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   public boolean hasNextSeparator(char separator, boolean skipWhiteSpace) {
      if (this.isTerminated) {
         return false;
      } else if (this.reader.hasNextSeparator(',', skipWhiteSpace)) {
         this.isTerminated = true;
         return false;
      } else {
         return this.reader.hasNextSeparator(separator, skipWhiteSpace);
      }
   }

   public HttpHeaderReader.Event next() throws ParseException {
      return this.next(true);
   }

   public HttpHeaderReader.Event next(boolean skipWhiteSpace) throws ParseException {
      return this.next(skipWhiteSpace, false);
   }

   public HttpHeaderReader.Event next(boolean skipWhiteSpace, boolean preserveBackslash) throws ParseException {
      if (this.isTerminated) {
         throw new ParseException("End of header", this.getIndex());
      } else if (this.reader.hasNextSeparator(',', skipWhiteSpace)) {
         this.isTerminated = true;
         throw new ParseException("End of header", this.getIndex());
      } else {
         return this.reader.next(skipWhiteSpace, preserveBackslash);
      }
   }

   public CharSequence nextSeparatedString(char startSeparator, char endSeparator) throws ParseException {
      if (this.isTerminated) {
         throw new ParseException("End of header", this.getIndex());
      } else if (this.reader.hasNextSeparator(',', true)) {
         this.isTerminated = true;
         throw new ParseException("End of header", this.getIndex());
      } else {
         return this.reader.nextSeparatedString(startSeparator, endSeparator);
      }
   }

   public HttpHeaderReader.Event getEvent() {
      return this.reader.getEvent();
   }

   public CharSequence getEventValue() {
      return this.reader.getEventValue();
   }

   public CharSequence getRemainder() {
      return this.reader.getRemainder();
   }

   public int getIndex() {
      return this.reader.getIndex();
   }
}
