package guru.springframework.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.api.v1.mapper.CategoryMapper;
import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.domain.Category;
import guru.springframework.repositories.CategoryRepository;

public class CategoryServiceTest {
	public static final Long ID = 2L;
	public static final String NAME = "Jimmy";
	CategoryService categoryService;
	CategoryMapper categoryMapper;
	
	@Mock
	CategoryRepository categoryRepository;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
		//categoryMapper = new CategoryMapper();
	}
	
	@Test
	public void getAllCategories() throws Exception {
		//given
		List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
		
		//when
		when(categoryRepository.findAll()).thenReturn(categories);
		
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategories();
		
		//then
		assertEquals(3, categoryDTOs.size());
	}
	
	@Test
	public void getCategoryByName() throws Exception {
		//given
		Category category = new Category();
		category.setId(ID);
		category.setName(NAME);
		
		//when
		when(categoryRepository.findByName(anyString())).thenReturn(category);
		
		CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);
		
		//then
		assertEquals(ID, categoryDTO.getId());
		assertEquals(NAME, categoryDTO.getName());
	}
}
