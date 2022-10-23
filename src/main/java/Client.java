import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
	private static final ExecutorService executor = Executors
			.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

	public static void main(String[] args) {

		String readDirectory = "C:\\read-write-problem\\test-github\\read\\";
		String serialWriteDirectory = "C:\\read-write-problem\\test-github\\serial-write\\";
		String parallelWriteDirectory = "C:\\read-write-problem\\test-github\\parallel-write\\";

		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Please input number of files");

			int n = scanner.nextInt();

			System.out.println("FIle names read will be as follows file-1.txt, file-2.txt...so on");

			long startTime = System.currentTimeMillis();
			for (int i = 0; i < n; i++) {
				String readFileName = readDirectory + "file-" + (i + 1) + ".txt";
				String writeFileName = serialWriteDirectory + "write-" + (i + 1) + ".xlsx";

				FIleReaderWriter fileReaderWriter = new FIleReaderWriter(readFileName, writeFileName);
				try {
					fileReaderWriter.readWriteFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			System.out.println(
					"Total time taken for serial write -- " + (System.currentTimeMillis() - startTime) + " ms");

			startTime = System.currentTimeMillis();
			CountDownLatch countDownLatch = new CountDownLatch(n);

			for (int i = 0; i < n; i++) {
				String readFileName = readDirectory + "file-" + (i + 1) + ".txt";
				String writeFileName = parallelWriteDirectory + "write-" + (i + 1) + ".xlsx";

				executor.execute(new ReadWriteTask(readFileName, writeFileName, countDownLatch));
			}

			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(
					"Total time taken for parallel write -- " + (System.currentTimeMillis() - startTime) + " ms");
			executor.shutdown();
		}
	}
}
