package pet_project.pet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pet_project.pet.dto.BookDto;
import pet_project.pet.dto.CatalogDto;
import pet_project.pet.dto.PermissionDto;
import pet_project.pet.dto.UserDto;
import pet_project.pet.mapper.BookMapper;
import pet_project.pet.mapper.PermissionMapper;
import pet_project.pet.mapper.UserMapper;
import pet_project.pet.model.Book;
import pet_project.pet.model.Permission;
import pet_project.pet.repository.PermissionRepository;
import pet_project.pet.repository.UserRepository;
import pet_project.pet.service.BookService;
import pet_project.pet.service.CatalogService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class PetApplicationTests {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookService bookService;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    /*@Test
    public void testAddRoleToUser() {
        PermissionDto roleAdmin = permissionMapper.toDto(permissionRepository.findByName("ROLE_ADMIN"));
        UserDto userDto = userMapper.toDto(userRepository.findById(1L).orElse(null));
        List<PermissionDto> permissionDtoList = permissionMapper.toDtoList(permissionRepository.findAll());
        if(userDto.getPermissionDtoList().isEmpty()) {
            permissionDtoList.add(roleAdmin);
            userDto.setPermissionDtoList(permissionDtoList);
            userRepository.save(userMapper.toEntity(userDto));
        }
    }*/

    @Test
    public void testCreateRole() {
        List<PermissionDto> permissionList = permissionMapper.toDtoList(permissionRepository.findAll());
        if (permissionList.isEmpty()) {
            PermissionDto admin = PermissionDto.builder()
                    .name("ROLE_ADMIN")
                    .build();

            PermissionDto user = PermissionDto.builder()
                    .name("ROLE_USER")
                    .build();

            permissionRepository.save(permissionMapper.toEntity(user));
            permissionRepository.save(permissionMapper.toEntity(admin));
        }
    }

    @Test
    void contextLoads() {
        testCourseDtoToEntity();
    }

    @Test
    void testCourseDtoToEntity() {
        BookDto bookDto = createBookDto(createNewCatalog());
        Book book = bookMapper.toEntity(bookDto);

        Assertions.assertNotNull(book);
        Assertions.assertEquals(book.getName(), bookDto.getNameDto());
        Assertions.assertEquals(book.getDescription(), bookDto.getDescription());
        Assertions.assertEquals(book.getAuthor(), bookDto.getAuthor());
        Assertions.assertEquals(book.getPrice(), bookDto.getPrice());
        Assertions.assertNotNull(bookDto.getCatalogDto());
        Assertions.assertEquals(book.getCatalog().getCategory(), bookDto.getCatalogDto().getCategoryDto());
    }

    @Test
    void checkAddBook() {
        CatalogDto catalogDto = createNewCatalog();
        CatalogDto insertedCatalogDto = catalogService.addCatalogDto(catalogDto);

        Assertions.assertNotNull(catalogDto);
        Assertions.assertNotNull(insertedCatalogDto);
        Assertions.assertEquals(catalogDto.getCategoryDto(), insertedCatalogDto.getCategoryDto());

        BookDto bookDto = createBookDto(catalogDto);
        BookDto insertedBookDto = bookService.addOneBook(bookDto);

        Assertions.assertNotNull(insertedBookDto);
        Assertions.assertNotNull(bookDto);

        matchBooks(bookDto, insertedBookDto);

        Assertions.assertNotNull(insertedBookDto.getId());
        BookDto checkBookDto = bookService.getOneBook(insertedBookDto.getId());

        Assertions.assertNotNull(checkBookDto);

        matchBooks(checkBookDto, insertedBookDto);

        bookService.deleteBook(insertedBookDto.getId());
    }

    private void matchBooks(BookDto firstBook, BookDto secondBook) {

        Assertions.assertEquals(firstBook.getNameDto(), secondBook.getNameDto());
        Assertions.assertEquals(firstBook.getDescription(), secondBook.getDescription());
        Assertions.assertEquals(firstBook.getAuthor(), secondBook.getAuthor());
        Assertions.assertEquals(firstBook.getPrice(), secondBook.getPrice());
        Assertions.assertNotNull(firstBook.getCatalogDto());
        Assertions.assertEquals(firstBook.getCatalogDto().getCategoryDto(), secondBook.getCatalogDto().getCategoryDto());
    }

    private BookDto createBookDto(CatalogDto catalogDto) {

        BookDto bookDto = BookDto.builder()
                .nameDto("bookName")
                .description("bookDescription")
                .author("bookAuthor")
                .price(001)
                .catalogDto(catalogDto)
                .build();

        bookDto.setCatalogDto(catalogDto);

        return bookDto;
    }

    private CatalogDto createNewCatalog() {
        CatalogDto catalogDto = CatalogDto.builder()
                .categoryDto("categoryBook")
                .build();
        return catalogDto;
    }
}
