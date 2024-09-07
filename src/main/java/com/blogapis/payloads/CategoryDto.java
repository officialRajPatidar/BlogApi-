package com.blogapis.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Integer categoryId;

	@NotEmpty
	@Size(min = 4, message = "title must be contain 4 chars !!")
	private String categoryTitle;
	@NotEmpty
	@Size(min = 10, message = "description must be contain 10 chars !!")
	private String categoryDescription;
}
