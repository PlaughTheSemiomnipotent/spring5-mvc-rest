package guru.springframework.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import guru.springframework.api.v1.mapper.CategoryMapper;
import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.domain.Category;

public class CategoryMapperTest {
	CategoryMapper categoryMapper = CategoryMapper.INSTANCE;
	static final String NAME = "Joe";
	static final Long ID = 1L;
	
	@Test
	public void categoryToCategoryDTO() throws Exception {
		//given
		Category category = new Category();

		category.setName(NAME);
		category.setId(ID);
		
		//when
		CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
		
		//then
		assertEquals(Long.valueOf(ID), categoryDTO.getId());
		assertEquals(NAME, categoryDTO.getName());
	}
}
