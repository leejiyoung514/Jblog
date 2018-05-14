package com.javaex.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.javaex.service.BlogService;
import com.javaex.service.CategoryService;
import com.javaex.service.PostService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UserVo;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
    @Autowired
    private CategoryService categoryService;
	@Autowired
    private PostService postService;
	
	// 내블로그페이지+리스트가져오기
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String myblog(Model model, @PathVariable("id") String id, HttpSession session,
		@RequestParam(value="cateNo", required=false, defaultValue="-1")int cateNo,
		@RequestParam(value="postNo", required=false, defaultValue="-1")int postNo) {
		
		PostVo post=new PostVo();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
     	//BlogList
		BlogVo blogVo = blogService.getBlogList(id);
		//PostList
		List<PostVo> postList = postService.getArticleList(cateNo, id);
        //Post
		post = postService.getArticle(postNo, cateNo, id);
	    //CategoryList
		List<CategoryVo> categorylist = categoryService.selectAll(id);
		model.addAttribute("post", post);
		model.addAttribute("blogVo", blogVo);
	    model.addAttribute("postList", postList);
		model.addAttribute("categorylist", categorylist);
		model.addAttribute("authUser", authUser);
		
		return "blog/blog-main";
	}

	// 어드민 기본설정 페이지
	@RequestMapping(value = "/{id}/admin/basic", method = RequestMethod.GET)
	public String admin(@PathVariable("id") String id, Model model) {
		BlogVo blogVo = blogService.getBlogList(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/admin/blog-admin-basic";
	}

	// 어드민 기본설정 등록 페이지
	@RequestMapping(value = "/{id}/admin/basic", method = RequestMethod.POST)
	public String adminSetting(@PathVariable("id") String id, Model model, @ModelAttribute BlogVo blogVo,
			@RequestParam("file") MultipartFile file) {
		System.out.println("파일업로드 뭐라찍히지?: "+file.toString() );
		
		if(file.isEmpty()) {
			//file = null;
			blogService.update(blogVo, null);	
		}else {
			blogService.update(blogVo, file);			
		}
		
		blogVo = blogService.getBlogList(id);
		return "redirect:/" + id;
	}
	
	// 카테고리페이지
	@RequestMapping(value = "/{id}/admin/category", method = RequestMethod.GET)
	public String category(@PathVariable("id") String id, Model model) {
		BlogVo blogVo = blogService.getBlogList(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/admin/blog-admin-cate";
	}

	// 글작성페이지
	@RequestMapping(value = "/{id}/admin/write", method = RequestMethod.GET)
	public String write(@PathVariable("id") String id, Model model) {
		BlogVo blogVo = new BlogVo();
		blogVo = blogService.getBlogList(id);
		List<CategoryVo> list=categoryService.selectAll(id);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("list", list);
		return "blog/admin/blog-admin-write";
	}

	//글쓰기
	@RequestMapping(value = "/{id}/admin/write", method = RequestMethod.POST)
	public String insert(@PathVariable("id") String id, @ModelAttribute PostVo postVo) { 
	    postService.insert(postVo);
		return"redirect:/"+id+"/admin/write";
	}
}



