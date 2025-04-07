package com.digging.gingstyle.products;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.digging.gingstagram.post.dto.CardView;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/products")
@Controller
public class ProductsController {

	@GetMapping
	public String getProducts() {
		return "products/all";
	}
	
//	@GetMapping("/timeline-view")
//	public String timeline(
//			HttpSession session
//			, Model model) {
//		
//		int userId = (Integer) session.getAttribute("userId");
//		
//		List<CardView> cardList = postService.getPostList(userId);
//		
//		
//		model.addAttribute("cardList", cardList);
//		
//		
//		return "post/timeline";
//	}
}
