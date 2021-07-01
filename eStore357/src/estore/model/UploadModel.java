package estore.model;

import java.io.File;

import estore.util.XWeb;

public class UploadModel {
	String uploadFolder = "uploads";
	File file;
	String fileFileName, fileContentType;
	
	public String saveAs(String fileName, String...folder){
		if(folder.length > 0){
			uploadFolder = folder[0];
		}
		File path = new File(XWeb.getRealPath(uploadFolder));
		if(!path.exists()){
			path.mkdirs();
		}
		File newFile = new File(path, fileName);
		if(newFile.exists()){
			newFile.delete();
		}
		file.renameTo(newFile);
		return fileName;
	}
	
	public String saveIn(String...folder){
		
		return this.saveAs(fileFileName, folder);
	}
	
	public String saveAsId(Object id, String...folder){
		String fileName = id + fileFileName.substring(fileFileName.lastIndexOf("."));
		this.saveAs(fileName, folder);
		return fileName;
	}
	
	public boolean hasFile() {
		
		boolean k=file != null;
		return k;
	}

	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
}
