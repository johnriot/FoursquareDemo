package converter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.CharBuffer;

class ResourceReader extends Reader {

    private final InputStreamReader mDelegateReader;

    public ResourceReader(String fileName) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        mDelegateReader = new InputStreamReader(inputStream);
    }

    @Override
    public int read(CharBuffer charBuffer) throws IOException {
        return mDelegateReader.read(charBuffer);
    }

    @Override
    public int read(char[] chars) throws IOException {
        return mDelegateReader.read(chars);
    }

    @Override
    public int read(char[] chars, int i, int i2) throws IOException {
        return mDelegateReader.read(chars, i, i2);
    }

    @Override
    public long skip(long l) throws IOException {
        return mDelegateReader.skip(l);
    }

    @Override
    public boolean markSupported() {
        return mDelegateReader.markSupported();
    }

    @Override
    public void mark(int i) throws IOException {
        mDelegateReader.mark(i);
    }

    @Override
    public void reset() throws IOException {
        mDelegateReader.reset();
    }

    @Override
    public void close() throws IOException {
        mDelegateReader.close();
    }
}

