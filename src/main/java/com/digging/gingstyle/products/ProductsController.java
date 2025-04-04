package com.digging.gingstyle.products;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/products")
@Controller
public class ProductsController {

	@GetMapping
	public String getProducts() {
		return "products/all";
	}
}
