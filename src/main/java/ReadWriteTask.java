import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ReadWriteTask implements Runnable {
	private final FIleReaderWriter fileReaderWriter;
	private final CountDownLatch latch;

	public ReadWriteTask(String readFileName, String writeFileName, CountDownLatch latch) {
		this.fileReaderWriter = new FIleReaderWriter(readFileName, writeFileName);
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			fileReaderWriter.readWriteFile();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			latch.countDown();
		}
	}

}
