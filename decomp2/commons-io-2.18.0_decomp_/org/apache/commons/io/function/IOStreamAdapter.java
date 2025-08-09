package org.apache.commons.io.function;

import java.util.stream.Stream;

final class IOStreamAdapter extends IOBaseStreamAdapter implements IOStream {
   static IOStream adapt(Stream delegate) {
      return (IOStream)(delegate != null ? new IOStreamAdapter(delegate) : IOStream.empty());
   }

   private IOStreamAdapter(Stream delegate) {
      super(delegate);
   }

   public IOStream wrap(Stream delegate) {
      return (IOStream)(this.unwrap() == delegate ? this : adapt(delegate));
   }
}
