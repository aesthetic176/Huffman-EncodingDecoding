import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Desktop;
import java.net.URI;

public class main {

	public static void main(String[] args) throws Exception {

		String orgFile = "./inputStringFile.txt";
		String dotFile = "./outputDescriptionFile.dot";
		String givenString = readFile(orgFile);
		HuffManDisplay h = new HuffManDisplay(givenString, dotFile);
		boolean isThisTestData = false;
		h.DisplayHuffman(isThisTestData);
		h.WriteToDictionary();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HuffmanGUI frame = new HuffmanGUI(h.DataArray, h.encodedString, h.DecodedString, h.sizeAfterCoding,h.sizeForGivenString, h.reductionPercentage);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		Desktop d = Desktop.getDesktop();
		Runtime.getRuntime().exec("dot -Tpng ./outputDescriptionFile.dot -o ./finalOutputHuffmanTree.png");
		String filePath = "./finalOutputHuffmanTree.png";
		File file = new File(filePath);

		URI uri = file.toURI();
		d.browse(new URI(uri.toString()));
	}

	public static String readFile(String fname) {
		StringBuilder sb = new StringBuilder();
		File filename = new File(fname);
		try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
			String line = in.readLine();
			while (line != null) {
				sb.append(line + "");
				line = in.readLine();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		return sb.toString();
	}

}