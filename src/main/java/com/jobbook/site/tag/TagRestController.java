package com.jobbook.site.tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jobbook.site.model.KakaoUser;
import com.jobbook.site.model.PostDetail;
import com.jobbook.site.tag.bo.TagBO;



@Controller
@RequestMapping("/tag")
public class TagRestController {
	
}
