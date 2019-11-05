public class Iterators {
















        public boolean hasNext() {
            return false;
        }

        public E next() {
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }













        public boolean hasNext() {
            return current == 0;
        }

        public E next() {
            current++;
            return element;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }



















        public boolean hasNext() {
            return wrapped.hasNext();
        }

        public E next() {
            removedCurrent = false;
            nextIndex++;
            return wrapped.next();
        }

        public void remove() {
            if (nextIndex == 0)
                throw new IllegalStateException("Call next() first");
            if (removedCurrent)
                throw new IllegalStateException("Already removed current, call next()");
            synchronizedRemove(nextIndex-1);
            removedCurrent = true;
        }








}
