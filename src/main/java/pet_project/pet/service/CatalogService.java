package pet_project.pet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet_project.pet.dto.CatalogDto;
import pet_project.pet.mapper.CatalogMapper;
import pet_project.pet.model.Catalog;
import pet_project.pet.repository.CatalogRepository;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final CatalogRepository catalogRepository;

    private final CatalogMapper catalogMapper;

    public Catalog getCatalog(Long id){
        return catalogRepository.findById(id).orElseThrow();
    }
    public CatalogDto getCatalogDto(Long id) {
        CatalogDto catalogDto = catalogMapper.toDto(getCatalog(id));
        return catalogDto;
    }

    public Catalog addCatalog(Catalog catalog) {
        return catalogRepository.save(catalog);
    }
    public CatalogDto addCatalogDto(CatalogDto catalogDto) {
        return catalogMapper.toDto(catalogRepository.save(catalogMapper.toEntity(catalogDto)));
    }
}
