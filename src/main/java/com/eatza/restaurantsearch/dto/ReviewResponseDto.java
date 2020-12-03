package com.eatza.restaurantsearch.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewResponseDto {

	private Long id;
	private String reviewDescription;
	private Long customerId;
	private Long restaurantId;
	private float rating;

	public ReviewResponseDto(Long id, String reviewDescription, Long customerId, Long restaurantId, float rating) {
		super();
		this.id = id;
		this.reviewDescription = reviewDescription;
		this.customerId = customerId;
		this.restaurantId = restaurantId;
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "ReviewResponseDto [id=" + id + ", reviewDescription=" + reviewDescription + ", customerId=" + customerId
				+ ", restaurantId=" + restaurantId + ", rating=" + rating + "]";
	}

	
	
}
