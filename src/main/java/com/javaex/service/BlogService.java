package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;

	// 블로그 리스트 가져오기
	public BlogVo getBlogList(String id) {
		return blogDao.getBlogList(id);
	}

	// 기본 설정 업데이트
	public int update(BlogVo blogVo, MultipartFile file) {

		if (file == null) { // 파일 업로드 안했을 때
			String saveName = blogDao.getBlogList(blogVo.getId()).getLogoFile();
			blogVo = new BlogVo(blogVo.getId(), blogVo.getBlogTitle(), saveName);
		} else { // 파일 업로드 했을 때
			
			String saveDir = "D:\\spring\\upload";
			// 오리지널 파일명
			String orgName = file.getOriginalFilename();
			System.out.println("orgName: " + orgName);
			// 확장자
			String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			System.out.println("exName: " + exName);
			// 저정파일이름
			String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
			// 파일경로
			String filePath = saveDir + "\\" + saveName;
			System.out.println("filePath: " + filePath);

			blogVo = new BlogVo(blogVo.getId(), blogVo.getBlogTitle(), saveName);

			try {
				byte[] fileData = file.getBytes();
				System.out.println(saveName);
				OutputStream out = new FileOutputStream(saveDir + "/" + saveName);
				BufferedOutputStream bout = new BufferedOutputStream(out);

				bout.write(fileData);
				if (bout != null) {
					bout.close();
				}
			} catch (IOException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return blogDao.update(blogVo);
	}
}
