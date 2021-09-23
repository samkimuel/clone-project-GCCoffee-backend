package com.gccoffee.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gccoffee.product.dto.ProductRequestDto;
import com.gccoffee.product.service.ProductService;

@Controller
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/products")
	public String productsPage(Model model) {
		var products = productService.getAllProducts();
		model.addAttribute("products", products);
		return "product-list";
	}

	@GetMapping("new-product")
	public String newProductPage() {
		return "new-product";
	}

	@PostMapping("/products")
	public String newProduct(ProductRequestDto productRequestDto) {
		productService.createProduct(
			productRequestDto.getProductName(),
			productRequestDto.getCategory(),
			productRequestDto.getPrice(),
			productRequestDto.getDescription());
		return "redirect:/products";
	}

}