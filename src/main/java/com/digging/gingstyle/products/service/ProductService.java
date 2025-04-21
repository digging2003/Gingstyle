package com.digging.gingstyle.products.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.digging.gingstyle.common.FileManager;
import com.digging.gingstyle.products.domain.Image;
import com.digging.gingstyle.products.domain.Product;
import com.digging.gingstyle.products.dto.Detail;
import com.digging.gingstyle.products.repository.ImageRepository;
import com.digging.gingstyle.products.repository.ProductRepository;

import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 부여된 멤버변수만
@Service
public class ProductService {
	private final ProductRepository productRepository;
	
	private final ImageRepository imageRepository;
	
	// 사용자 관련 - 상품 상세페이지 조회 API
	public Product getProduct(int id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		Product product = optionalProduct.get();
		
		return product;
	}
	
	// 관리자 관련 - 제품 이미지 등록 API
	public boolean addImage(
			int productId
			, MultipartFile imageFile) {
		
		String imagePath = FileManager.saveFile(imageFile);
		
		Image image = Image.builder()
				.productId(productId)
				.imagePath(imagePath)
				.build();
		
		try {
			imageRepository.save(image);
		} catch(PersistenceException e) {
			return false;
		}
		
		return true;
	}
	
	// 관리자 관련 - 제품 등록 API
	public boolean addProduct(
			String name
			, String discription
			, int beforePrice
			, int price
			, int stock
			, int categoryId) {
		
		Product product = Product.builder()
				.name(name)
				.discription(discription)
				.beforePrice(beforePrice)
				.price(price)
				.stock(stock)
				.categoryId(categoryId)
				.build();
		
		try {
			productRepository.save(product);
		} catch(PersistenceException e) {
			return false;
		}
		
		return true;
		
		
	}
	
	
	// 관리자 관련 - 제품 수정 API
	public boolean updateProduct(
			int id
			, String name
			, String discription
			, int beforePrice
			, int price
			, int stock
			, int categoryId) {
		Optional<Product> optionalProducts = productRepository.findById(id);
		
		if(optionalProducts.isPresent()) {
			
			Product products = optionalProducts.get();
			
			products = products.toBuilder()
				.name(name)
				.discription(discription)
				.beforePrice(beforePrice)
				.price(price)
				.stock(stock)
				.categoryId(categoryId)
				.build();
			
			try {
				productRepository.save(products);
			} catch(PersistenceException e) {
				return false;
			}
		} else {
			return false;
		}
		
		return true;
	}
	
	// 관리자 관련 - 제품 삭제 API
	public boolean deleteProduct(int id) {
		Optional<Product> optionalProducts = productRepository.findById(id);
		if(optionalProducts.isPresent()) {
			
			Product product = optionalProducts.get();
			List<Image> imageList = imageRepository.findByProductId(product.getId());
			
			try {
				productRepository.deleteById(id);
				
				// 해당 제품 이미지 파일 전체 삭제
				for(Image image:imageList) {
					FileManager.removeFile(image.getImagePath());
				}

			} catch(PersistenceException e) {
				return false;
			}
			
		} else {
			return false;
		}
		
		return true;
		
	}
	
	// 관리자 관련 - 제품 이미지 개별 삭제 API
	public boolean deleteImage(int id) {
		Optional<Image> optionalImage = imageRepository.findById(id);
		if(optionalImage.isPresent()) {
			
			Image image = optionalImage.get();
			
			try {
				imageRepository.deleteById(id);
				FileManager.removeFile(image.getImagePath()); // 이미지 파일 삭제
			} catch(PersistenceException e) {
				return false;
			}
			
		} else {
			return false;
		}
		
		return true;
	}
	
	// 사용자 관련 - 카테고리Id로 페이지 조회 기능
	public List<Detail> getCategoryView(Integer categoryId) {
		
		List<Product> productList = new ArrayList<>();
		
		if(categoryId == null) {
			productList = productRepository.findAll();
		} else {
			productList = productRepository.findByCategoryId(categoryId);
		}
		
		List<Detail> detailList = new ArrayList<>();
		
		for(Product product:productList) {

			int productId = product.getId();
			List<Image> imageList = imageRepository.findByProductId(productId);
			String mainImagePath = imageRepository.findTopByProductId(productId).getImagePath();
			
			Detail detail = Detail.builder()
					.productId(productId)
					.name(product.getName())
					.beforePrice(product.getBeforePrice())
					.price(product.getPrice())
					.imageList(imageList)
					.mainImagePath(mainImagePath)
					.categoryId(product.getCategoryId())
					.discription(product.getDiscription())
					.stock(product.getStock())
					.build();
			
			detailList.add(detail);
		}
		
		return detailList;
		
	}
	
	public Detail getDetailView(int productId) {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		Product product = optionalProduct.get();
		
		List<Image> imageList = imageRepository.findByProductId(productId);
		String mainImagePath = imageRepository.findTopByProductId(productId).getImagePath();
		
		Detail detail = Detail.builder()
				.productId(productId)
				.name(product.getName())
				.beforePrice(product.getBeforePrice())
				.price(product.getPrice())
				.imageList(imageList)
				.mainImagePath(mainImagePath)
				.categoryId(product.getCategoryId())
				.discription(product.getDiscription())
				.stock(product.getStock())
				.build();
		
		return detail;
		
	}
}
