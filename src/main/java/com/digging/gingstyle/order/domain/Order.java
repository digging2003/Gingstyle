package com.digging.gingstyle.order.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="`order`")
@Entity
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int userId;
	private int totalPrice;
	private String address;
	private String status;
	private String payment;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true) 
    // 일대다 관계 설정. Order 엔티티가 여러 OrderItems 엔티티와 매핑되었다.
    // mappedBy = "order" 는 OrderItems 엔티티에서 order 필드에 의해 매핑된다는것 의미
    // cascade = CascadeType.ALL : 주인 엔티티에 대한 모든 변경이 연관된 엔티티에 전파되도록 설정
    // orphanRemoval = true : 주인 엔티티에서 제거된 엔티티는 영속성 컨텍스트에 의해서도 제거되도록 설정
    private List<OrderItems> orderItems = new ArrayList<>();
	// 엔티티의 목록을 나타내는 필드로, 여러개의 주문상품을 담기 위한 리스트이다.
    // 리스트는 기본적으로 빈 리스트로 초기화 된다.
    // 하나의 주문이 여러 개의 주문상품을 갖음. List 자료형 사용
    // OrderItem 엔티티이다.
    // Order 엔티티가 주인이 아니므로 "mappedBy" 속성으로 연관관계 주인을 설정
    // 속성의 값으로 'Order" 엔티티 적어준 이유는 OrderItem 에 있는 Order 에 의해 관리된다는 의미.
    // 즉, 연관 관계의 주인의 필드인 order 를 mappedBy 의 값으로 셋팅하면 된다.
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public void setId(int id) {
		this.id = id;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	
	
}
