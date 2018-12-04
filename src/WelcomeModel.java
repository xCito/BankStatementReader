import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class WelcomeModel {
	
	
	List<File> files;
	List<Image> images;
	List<PDDocument> pdfs;
	
	public WelcomeModel() {
		files = new ArrayList<>();
		images = new ArrayList<>();
		pdfs = new ArrayList<>();
	}
	
	
	public int getNumFiles() {
		return files.size();
	}
	
	// deletes all previous elements
	// and sets new list of elements as values in list
	public void setFiles(List<File> listOfFiles) {
		images.clear();
		pdfs.clear();
		files.clear();
		
		files.addAll(listOfFiles);

		try {
			for( File file: files) {
				PDDocument pdfFile;
				pdfFile = PDDocument.load(file);	
				pdfs.add( pdfFile );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void addFile( String filename ) {
		files.add( new File(filename) );
	}
	public void addFile( File file ) {
		files.add(file);
	}
	public void removeFile( int index ) {
		files.remove(index);
	}
	
	public List<Image> getImagesOfPDF() {
		float SCALE = 0.5f;
		
		try {
			for(PDDocument pdf: pdfs) {
				PDFRenderer rend = new PDFRenderer(pdf);
				BufferedImage buff = rend.renderImage(0, SCALE);
				Image img = SwingFXUtils.toFXImage(buff, null);
				images.add( img );
				pdf.close();
			}
		} catch(IOException e) {
			System.out.println(e);
		}
		
		return images;
	}
	
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
