import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class OverviewModel {
	
	List<File> files;
	List<Image> images;
	
	public OverviewModel() {
		files = new ArrayList<>();
		images = new ArrayList<>();
	}
	
	/**
	 * Clears List of files and images held. Adds all elements
	 * from 'listOfFiles'. Clear main data structure.
	 * @param listOfFiles - Collection of Files (PDF)
	 */
	public void setFiles(List<File> listOfFiles) {
		images.clear();
		files.clear();
		ApplicationController.data.clear();
		
		files.addAll(listOfFiles);
	}

	/**
	 * Renders each PDF's first page as an image 
	 * @return Collection of images of the first page for each PDF.
	 */
	public List<Image> getImagesOfPDF() {
		float SCALE = 0.5f;
		
		try {
			for(File pdf: files) {
				PDDocument pdfFile = PDDocument.load(pdf);			// Load PDF as Object
				PDFRenderer rend = new PDFRenderer(pdfFile);		// Render Images from PDF
				BufferedImage buff = rend.renderImage(0, SCALE);	// Get image of page 0, scaled
				Image img = SwingFXUtils.toFXImage(buff, null);		// Cast BufferedImage to Image
				images.add( img );								// Add to list of files
				pdfFile.close();								// Close stream
			}
		} catch(IOException e) {
			System.out.println(e);
		}
		
		return new ArrayList<>(images);		// Returns shallow copy of images
	}
	
	/**
	 * Using ChaseStatementAnalyzer.java, extracts Transaction information
	 * from each PDF. Assuming its a Chase Bank Statement PDF. Stores Extracted
	 * Transaction information into HashMap.
	 * @param pdfFiles - List of Chase Bank Statement PDF(s).
	 */
	public void extractData(List<File> pdfFiles) {
		ChaseStatementAnalyzer chase = new ChaseStatementAnalyzer();
		
		for(File pdf: pdfFiles) {
			System.out.println("Name: " + pdf.getName());
			chase.setStatement(pdf);
			chase.analyze();
			
			ArrayList<Transaction> trans = chase.getTransactions();
			ApplicationController.data.put(pdf.getName(), trans);
		}
	}

}
