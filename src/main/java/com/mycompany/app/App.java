package com.mycompany.app;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageFitWidthDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;

import java.util.List;

/**
 * Hello world!
 * 
 */
public class App {

	/**
	 * This will print the documents data.
	 * 
	 * @param args
	 *            The command line arguments.
	 * 
	 * @throws Exception
	 *             If there is an error parsing the document.
	 */
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			usage();
		} else {
			PDDocument document = null;
			try {
				document = PDDocument.load(args[0]);
				if (document.isEncrypted()) {
					System.err
							.println("Error: Cannot add bookmarks to encrypted document.");
					System.exit(1);
				}

				PDDocumentOutline outline = document.getDocumentCatalog()
						.getDocumentOutline();

				PDOutlineItem item = outline.getFirstChild();
				while (item != null) {
					//System.out.println(item.getTitle());
					PDOutlineItem itemN = item.getFirstChild();
					while (itemN != null) {
						if(itemN.getTitle().equals("メソッド名")){
							System.out.println(itemN.getTitle());
							itemN.setTitle("メソッド名");
						}
						itemN = itemN.getNextSibling();
					}
					item = item.getNextSibling();
				}
				
				document.save("new.pdf");
			} finally {
				if (document != null) {
					document.close();
				}
			}
		}
	}

	/**
	 * This will print the usage for this document.
	 */
	private static void usage() {
		System.err
				.println("Usage: java org.apache.pdfbox.examples.pdmodel.CreateBookmarks <input-pdf> <output-pdf>");
	}

}
