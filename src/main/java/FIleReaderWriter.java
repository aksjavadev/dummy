import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FIleReaderWriter {
	private final String readFileName;
	private final String writeFileName;

	public FIleReaderWriter(String readFileName, String writeFileName) {
		this.readFileName = readFileName;
		this.writeFileName = writeFileName;
	}

	public void readWriteFile() throws IOException {
		int counter = 0;
		int totalWritten = 0;
		String line;

		List<String> lines = new LinkedList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(readFileName));
				XSSFWorkbook workbook = new XSSFWorkbook();
				FileOutputStream out = new FileOutputStream(new File(writeFileName));) {
			XSSFSheet sheet = workbook.createSheet("Sheet1");
			while ((line = br.readLine()) != null) {
				lines.add(line);
				counter++;
				if (counter % 1000 == 0) {
					writeToExcel(sheet, lines, counter - 1000);
					lines.clear();
					totalWritten += 1000;
//					System.out.println("Wrote " + totalWritten);
				}
			}

			// System.out.println("final counter " + counter + ", total written " + totalWritten);

			if (counter - totalWritten > 0) {
				writeToExcel(sheet, lines, totalWritten);
				lines.clear();
			}

			workbook.write(out);
		}
	}

	private void writeToExcel(XSSFSheet sheet, List<String> lines, int rowNum) {
		for (String line : lines) {
			Row row = sheet.createRow(rowNum++);

			String[] lineSplit = line.split(",");

			int cellnum = 0;

			for (String splitLine : lineSplit) {
				Cell cell = row.createCell(cellnum++);

				String data = (splitLine == null) ? "" : splitLine.trim();
				cell.setCellValue(data);
			}
		}
	}
}
