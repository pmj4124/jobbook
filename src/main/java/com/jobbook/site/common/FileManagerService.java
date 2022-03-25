package com.jobbook.site.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {
	public static final String FILE_UPLOAD_PATH ="C:\\mega_spring\\memo_file";// final은 수정할 수 없도록 하는 것 
	//파일 경로는 프로젝트 안에는 하면 안됨. 개발자가 한 거기 때문에 사용자가 한 거를 여기에 올리면 안 됨. 완전 다른 디렉토리에다가...
	
	private static Logger logger = LoggerFactory.getLogger(FileManagerService.class);
	//org.slf4j~이거를 임포트해줘야함
	//객체 생성 없이 사용하는 메소드에서는 객체생성없이 활용하는 변수가 활용이 되어야함.. 그래서 얘도 static 붙여야 함
	
	//파일 저장
	public static String saveFile(int userId, MultipartFile file) {
		//항상 같은 값이라 구지 객체 생성 필요 없어서 static 키워드 붙여도됨
		//static키워드 붙이면 이거 자체로 사용할 수 있게 함. 그런 역할. 
		//객체생성 필요 없이 바로 사용할 수 있는 멤버변수 
		
		
		if(file == null) {
			logger.error("FileManagerService::saveFile - 업로드 파일 없음");
			return null;
		}
		
		//파일 경로
		//사용자 별로 구분할 수 있도록
		//사용자가 파일을 올린 시간 
		//1970년 1월 1일 기준으로 현지 밀리 세컨이 경과 되었는지 나타내는 수 
		String directoryName = userId + "_" + System.currentTimeMillis() + "/";
		//실제 파일 경로
		String filePath = FILE_UPLOAD_PATH + directoryName; 
		
		
		
		
		//디렉토리 만들기
		File directory = new File(filePath);
		if(directory.mkdir()==false) {
			//디렉토리 생성 에러
			return null;
		}
		//저장관리. 
		try {
			byte[] bytes = file.getBytes();
			//파일 저장 경로 + 파일 이름 경로 객체 
			Path path = Paths.get(filePath + file.getOriginalFilename());
			//파일 저장
			Files.write(path,  bytes);
		} catch (IOException e) {
			logger.error("FileManagerService::saveFile - 파일 저장 에러");
			//파일 저장 실패
			e.printStackTrace();
			return null;
		}
		
		//파일 접근 가능한 주소 리턴
		//<img src = "/images/12_123125/test.png">
		return "/images/" + directoryName + file.getOriginalFilename();
		
	}
	
	
	//파일 삭제
	public static void removeFile(String filePath) {
	//삭제할 파일 경로		
	//filePath : /images/2_~~
	//실제 파일 경로 : FILE_UPLOAD_PATH
		//filepath앞의 /images/는 우리가 임의로 붙인거니까 지워줘야함
		
		if(filePath == null) {
			logger.error("FileManagerService::removeFile - 삭제할 파일 없음");
			return ; 
		}
	
	String realFilePath = FILE_UPLOAD_PATH + filePath.replace("/images/", "");
	Path path = Paths.get(realFilePath);
	// 파일 있는지 확인
	if(Files.exists(path)) {
		try {
			Files.delete(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("FileManagerService::removeFile - 파일 삭제 실패");
			e.printStackTrace();
		}
	}
	
	//디렉토리 삭제(폴더)
	//실제 디렉토리 경로 : 
	path = path.getParent();
	// 파일 있는지 확인
	if(Files.exists(path)) {
		try {
			Files.delete(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("FileManagerService::removeFile - 디렉토리 삭제 실패");
			e.printStackTrace();
		}
	}
	
		
	}
	
}
