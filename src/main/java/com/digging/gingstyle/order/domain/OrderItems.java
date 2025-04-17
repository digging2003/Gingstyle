package com.digging.gingstyle.order.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="`orderItems`")
@Entity
public class OrderItems {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
    // 한번의 주문에 여러 개의 상품을 주문할 수 있으므로 주문상품 엔티티와 주문엔티티를 다대일 단방향 매핑
    @JoinColumn(name = "orderId")
    private Order order;
	
	private int productId;
	private int quantity;
	public void setId(int id) {
		this.id = id;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	
	
}
