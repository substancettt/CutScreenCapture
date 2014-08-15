package com.xs.image;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ClipboardCopyAction {

	private static Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
	public void run() {
		clip.setContents(new StringSelection("Good Day"), new StringSelection("Happyy"));
	}
	
	public String getClipboard() throws Exception, IOException {
		Transferable clipData = clip.getContents(ClipboardCopyAction.this);
		String clipString = (String) clipData.getTransferData(DataFlavor.stringFlavor);
		System.out.println(clipString);
		return null;
	}
	
	public static void setClipboardImage(final Image image) {
		Transferable tf = new Transferable(){

			public Object getTransferData(DataFlavor flavor)
					throws UnsupportedFlavorException, IOException {
				if(isDataFlavorSupported(flavor))
					return image;
				throw new UnsupportedFlavorException(flavor);
			}

			public DataFlavor[] getTransferDataFlavors() {
				return new DataFlavor[]{DataFlavor.imageFlavor};
			}

			public boolean isDataFlavorSupported(DataFlavor flavor) {
				return DataFlavor.imageFlavor.equals(flavor);
			}
			
		};
		clip.setContents(tf, null);
		
	}
	
	public static Image getImageFromClipboard() throws Exception {
		Transferable tf = clip.getContents(null);
		if(tf == null) {
			return null;
		} else if(tf.isDataFlavorSupported(DataFlavor.imageFlavor)) {
			return (Image) tf.getTransferData(DataFlavor.imageFlavor);
		}
		return null;
	}
/*	
	public static void main(String[] args) throws Exception {
		ClipboardCopyAction cca = new ClipboardCopyAction();
		cca.run();
		Thread.sleep(3000);
		cca.getClipboard();
		//cca.setClipboardImage();
	}
*/

}
